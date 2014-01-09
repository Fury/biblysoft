package org.biblyfx.Util;

import org.bibly.logic.UitleenBuilder;
import org.bibly.logic.dao.interfaces.*;
import org.bibly.logic.exceptions.BibliothecarisException;
import org.bibly.logic.exceptions.ItemException;
import org.bibly.logic.exceptions.LidException;
import org.bibly.logic.exceptions.LidPermissionException;
import org.bibly.logic.models.*;
import org.bibly.logic.models.enums.PersoonType;
import org.bibly.logic.util.ExcelImporter;
import org.biblyfx.MainApp;
import org.joda.time.DateTime;
import org.springframework.context.ApplicationContext;
import org.springframework.transaction.annotation.Transactional;

import java.security.NoSuchAlgorithmException;
import java.text.DecimalFormat;
import java.util.*;

import static org.biblyfx.controllers.InloggenController.encryptPassword;

/**
 * Created by Dries on 12/17/13.
 */
@Transactional
public class FakeDataGenerator {

    private ApplicationContext applicationContext = MainApp.applicationContext;

    /**
     * Rand int.
     *
     * @param min the min
     * @param max the max
     * @return the int
     */
    public static int randInt(int min, int max) {

        // Usually this can be a field rather than a method variable
        Random rand = new Random();

        // nextInt is normally exclusive of the top value,
        // so add 1 to make it inclusive
        int randomNum = rand.nextInt((max - min) + 1) + min;

        return randomNum;
    }

    /**
     * Generate random String of fixed size
     *
     * @return random String of fixed size
     * @see org.biblyfx.Util.FakeDataGenerator
     */
    private String generateRandomString(int x) {
        char[] chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < x; i++) {
            char c = chars[random.nextInt(chars.length)];
            sb.append(c);
        }
        String output = sb.toString();
        return output;
    }

    /**
     * Generate random String of random size
     *
     * @return random String of random size
     * @see org.biblyfx.Util.FakeDataGenerator
     */
    private String generateRandomSizeString() {
        char[] chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        int x = random.nextInt(15) + 5;
        for (int i = 0; i < x; i++) {
            char c = chars[random.nextInt(chars.length)];
            sb.append(c);
        }
        String output = sb.toString();
        return output;
    }

    /**
     * Generates Random GSM Number
     *
     * @return GSM Number in String format
     */
    private String randomGSM() {
        Random rand = new Random();
        int num1 = (rand.nextInt(7) + 1) * 100 + (rand.nextInt(8) * 10) + rand.nextInt(8);
        int num2 = rand.nextInt(743);
        int num3 = rand.nextInt(1000);

        DecimalFormat df3 = new DecimalFormat("000"); // 3 zeros
        DecimalFormat df4 = new DecimalFormat("000"); // 4 zeros

        String phoneNumber = df3.format(num1) + "/" + df3.format(num2) + df4.format(num3);
        return phoneNumber;
    }

    /**
     * Starts generation of fake data.
     */
    public void generateFakeData() {
        Random random = new Random();
        IGemeenteDAO gemeenteDAO = (IGemeenteDAO) applicationContext.getBean("gemeenteDAO");
        ILidDAO lidDAO = (ILidDAO) applicationContext.getBean("lidDAO");
        IPersoneelDAO personeelDAO = (IPersoneelDAO) applicationContext.getBean("personeelDAO");


        int x = 1000;
        for (int i = 0; i < 200; i++) {
            gemeenteDAO.save(new Gemeente(generateRandomSizeString(), x));
            x = x + 10;
        }
        List<Gemeente> gemeentes = gemeenteDAO.findAll();

        for (int i = 0; i < 2000; i++) {
            Adres a = new Adres(generateRandomSizeString(), "", random.nextInt(9999), gemeentes.get(random.nextInt(gemeentes.size())));
            DateTime verval = DateTime.now();
            verval = verval.plusDays(random.nextInt(366));
            Lid l = new Lid(a, PersoonType.LID, new Date(), generateRandomString(10), generateRandomString(20), randomGSM(), "fakeemail@gmail.com", randomGSM(), verval.toDate());
            l.setGeboorteDatum(getRandomDate(random, 1940, 2013));
            lidDAO.save(l);
            x = x + 10;
        }

        Personeel bibly = null;
        try {
            personeelDAO.save(new Personeel("admin", encryptPassword("admin"), new Adres(generateRandomSizeString(), "", random.nextInt(9999), gemeentes.get(random.nextInt(gemeentes.size()))), PersoonType.ADMINISTRATOR, new Date()));
            bibly = new Personeel("bibly", encryptPassword("bibly"), new Adres(generateRandomSizeString(), "", random.nextInt(9999), gemeentes.get(random.nextInt(gemeentes.size()))), PersoonType.BIBLIOTHECARIS, new Date());
            bibly = personeelDAO.save(bibly);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }


        ExcelImporter excelImporter = new ExcelImporter();
        excelImporter.ImportExcel("C:\\boeken.xls", (IBoekDAO) MainApp.applicationContext.getBean("boekDAO"), (IExemplaarDAO) MainApp.applicationContext.getBean("exemplaarDAO"));

        IBoekDAO boekDAO = (IBoekDAO) applicationContext.getBean("boekDAO");
        //Genre koppeling
        List<Boek> boeken = boekDAO.findAll();
        List<String> genres = new ArrayList<String>();
        genres.add("horror");
        genres.add("drama");
        genres.add("roman");
        genres.add("ict");
        genres.add("erotiek");
        genres.add("programming");
        genres.add("science-fiction");
        genres.add("non-science-fiction");
        for (Boek boek : boeken) {
            boek.setGenre(genres.get(random.nextInt(8)));
        }
        try {
            boekDAO.save(boeken);
        } catch (Exception e) {
            e.printStackTrace();
        }
        boeken = boekDAO.findAll();
        List<Lid> leden = lidDAO.findAll();
        IUitleningDAO iUitleningDAO = (IUitleningDAO) applicationContext.getBean("uitleningDAO");
        IUitleningDetailsDAO iUitleningDetailsDAO = (IUitleningDetailsDAO) applicationContext.getBean("uitleningdetailsDAO");
        IExemplaarDAO exemplaarDAO = (IExemplaarDAO) applicationContext.getBean("exemplaarDAO");
        IDvdDAO dvdDAO = (IDvdDAO) applicationContext.getBean("dvdDAO");
        ICdDAO cdDAO = (ICdDAO) applicationContext.getBean("cdDAO");
        for (int i = 0; i < 1337; i++) {
            int aantalNum = randInt(3, 21);
            List<String> nummers = new ArrayList<String>();
            for (int j = 0; j < aantalNum; j++)
                nummers.add(generateRandomSizeString());
            Cd cd = new Cd(generateRandomSizeString(), nummers, random.nextDouble(), generateRandomSizeString(), generateRandomString(15), getRandomDate(random, 1900, 2013).toString(), genres.get(random.nextInt(8)));
            ItemExemplaar exemplaar = new ItemExemplaar(cd, "CD Rek");
            exemplaarDAO.save(exemplaar);
        }

        for (int i = 0; i < 1337; i++) {
            int aantalNum = randInt(3, 21);
            List<String> acteurs = new ArrayList<String>();
            for (int j = 0; j < aantalNum; j++)
                acteurs.add(generateRandomSizeString());
            Dvd dvd = new Dvd(randInt(10, 300), randInt(0, 99), acteurs, generateRandomSizeString(), generateRandomSizeString(), getRandomDate(random, 1900, 2013).toString(), genres.get(random.nextInt(8)));
            ItemExemplaar exemplaar = new ItemExemplaar(dvd, "CD Rek");
            exemplaarDAO.save(exemplaar);
        }

        List<ItemExemplaar> exemplaars = exemplaarDAO.findAll();
        //Uitlening creator
        for (int i = 0; i < 2300; i++) {
            Personeel b = personeelDAO.findById(bibly.getPersoonID());
            int aantalBoek = random.nextInt(4) + 1;
            Lid randomLid = leden.get(random.nextInt(leden.size()));
            Date now = getRandomDate(random, 2010, 2013);
            UitleenBuilder uitleenBuilder = new UitleenBuilder(b, randomLid);
            uitleenBuilder.setExemplaarDAO(exemplaarDAO);
            uitleenBuilder.setUitleningDAO(iUitleningDAO);
            uitleenBuilder.setUitleningdetailsDAO(iUitleningDetailsDAO);
            int exemplaarSize = exemplaars.size();
            for (int j = 0; j < aantalBoek; j++) {
                ItemExemplaar exemplaar = exemplaars.get(random.nextInt(exemplaarSize));
                try {
                    uitleenBuilder.addExemplaar(exemplaar);
                } catch (ItemException e) {
                    e.printStackTrace();
                }

            }
            uitleenBuilder.setDateCreated(now);
            DateTime dateTime = new DateTime(now.getTime());
            dateTime.plusDays(random.nextInt(30));
            uitleenBuilder.setInGeleverd(dateTime.toDate());
            try {
                uitleenBuilder.commitUitlening();
                System.out.println("Uitlening Voltooid");
            } catch (LidException e) {
                e.printStackTrace();
            } catch (ItemException e) {
                e.printStackTrace();
            } catch (LidPermissionException e) {
                e.printStackTrace();
            } catch (BibliothecarisException e) {
                e.printStackTrace();
            }
        }

    }

    private Date getRandomDate(Random random, int x, int y) {
        int month = random.nextInt(11);
        int year = randInt(x, y);
        int day;
        if (month == 1)
            day = random.nextInt(28);
        else
            day = random.nextInt(30);

        GregorianCalendar calendar = new GregorianCalendar(year, month, day);
        return calendar.getTime();
    }
}
