package org.bibly.logic;

import org.bibly.logic.dao.interfaces.*;
import org.bibly.logic.exceptions.BibliothecarisException;
import org.bibly.logic.exceptions.ItemException;
import org.bibly.logic.exceptions.LidException;
import org.bibly.logic.exceptions.LidPermissionException;
import org.bibly.logic.models.*;
import org.bibly.logic.models.enums.PersoonType;
import org.hibernate.SessionFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

import static org.easymock.EasyMock.createMock;
import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/config/testApplicationContext.xml")
public class TestUitleenBuilder extends BaseTest
{
    private UitleenBuilder uitleenBuilder;

    private Personeel bibl;
    private Lid lid;
    private Adres adres;
    private Gemeente gemeente;
    private Boek boek;
    private ItemExemplaar mockExemplaar;
    private ItemExemplaar itemExemplaar;
    @Resource
    private IUitleningDAO uitleningDAO;

    @Resource
    private IUitleningDetailsDAO uitleningdetailsDAO;
    @Resource
    private IGemeenteDAO gemeenteDAO;
    @Resource
    private IAdresDAO adresDAO;
    @Resource
    private IPersoneelDAO personeelDAO;
    @Resource
    private ILidDAO lidDAO;
    @Resource
    private IExemplaarDAO exemplaarDAO;
    @Resource
    private IBoekDAO boekDAO;

    @Resource
    private SessionFactory sessionFactory;

    @Before
    public void setUp() throws Exception
    {
        List obje = sessionFactory.openSession().createQuery("select new list(l.achternaam, ud.exemplaar.item.genre) from Lid as l JOIN l.uitleningen as u JOIN u.details as ud").list();

        gemeente = new Gemeente("Oordegem", 9420);
        adres = new Adres("Zandstraat", "", 44, gemeente);
        bibl = new Personeel("Bibly", "1234", adres, PersoonType.BIBLIOTHECARIS, new Date());
        lid = new Lid(adres, PersoonType.LID, new Date());
        boek = new Boek("Iets", "Iemand", "Nope", "1992", 1);
        itemExemplaar = new ItemExemplaar(boek);
        uitleenBuilder = new UitleenBuilder(bibl, lid);
        gemeenteDAO.save(gemeente);
        adresDAO.save(adres);
        personeelDAO.save(bibl);
        lidDAO.save(lid);
        boekDAO.save(boek);
        exemplaarDAO.save(itemExemplaar);

        uitleenBuilder.setUitleningDAO(uitleningDAO);
        uitleenBuilder.setUitleningdetailsDAO(uitleningdetailsDAO);
        uitleenBuilder.setExemplaarDAO(exemplaarDAO);


        //Maakt een mock object aan voor de klasse Item exemplaar.
        //Deze zal instaan voor het gedrag van een echt ItemExemplaar object,
        //maar zonder de effectieve implementatie van de functies te gebruiken.
        mockExemplaar = createMock(ItemExemplaar.class);

    }

    @After
    public void tearDown() throws Exception
    {

    }


    @Test
    /**    Opmerking: Er moet niet (nog) niet getest worden bij geval dat item gelijk of niet gelijk zou zijn aan null,
     want het mockobject verzorgd hier de initialisatie */
    public void testAddExemplaarBeschikbaar()
    {
        //Testing
        try
        {
            uitleenBuilder.addExemplaar(itemExemplaar);
        } catch (ItemException e)
        {
            fail("exception - ItemException");
        }
        assertTrue("Exemlaren doesn't contain the given example!", uitleenBuilder.getExemplaren().contains(itemExemplaar));
    }

    @Test
    public void testAddExemplaarNietBeschikbaar()
    {
        Uitlening uitlening = new Uitlening(new Date(), lid, bibl);
        UitleningDetail detail = new UitleningDetail(uitlening, null, 0, itemExemplaar);
        uitlening.addUitleningDetail(detail);

        uitleningDAO.save(uitlening);

        uitleningdetailsDAO.save(detail);


        //Testing
        try
        {
            uitleenBuilder.addExemplaar(itemExemplaar);
        } catch (ItemException e)
        {
            e.printStackTrace();
        }

        assertFalse("Exemlaren contains the given example!", uitleenBuilder.getExemplaren().contains(itemExemplaar));

    }

    @Test
    public void testCommitUitleningLidException()
    {
        try
        {
            //In dit geval moet de functie een LidException gooien omdat er geen lid geïnitialiseerd is
            uitleenBuilder.commitUitlening();
            //Geen exception is een fail, het programma moet een exception gooien bij een leeg lid
            fail("No Exception thrown!");
        } catch (LidException e)
        {
            //Test geslaagd! dit was verwacht!
        }
        //Elke Andere exception laat de test failen
        catch (LidPermissionException e)
        {
            fail("LidPermissionException thrown!");
        } catch (BibliothecarisException e)
        {
            fail("BibliothecarisException thrown!");
        } catch (ItemException e)
        {
            e.printStackTrace();
        }
    }

    @Test
    public void testCommitUitleningBibliothecarisException()
    {
        try
        {
            //Uitleenbuilder krijgt een lid toegewezen maar geen bibiothecaris, we verwachten de BibliothecarisException
            lid.setPermissie(PersoonType.LID);
            uitleenBuilder.setPersoon(lid);
            uitleenBuilder.setBibliothecaris(null);
            //In dit geval moet de functie een LidException gooien omdat er geen lid geïnitialiseerd is
            uitleenBuilder.commitUitlening();
            //Geen exception is een fail, het programma moet een exception gooien bij een leeg lid
            fail("No Exception thrown!");
        } catch (LidException e)
        {
            fail("LidException thrown!");
        } catch (LidPermissionException e)
        {
            fail("LidPermissionException thrown!");
        } catch (BibliothecarisException e)
        {
            //Test geslaagd! dit was verwacht!
        } catch (ItemException e)
        {
            fail("ItemException thrown!");
        }
    }

    @Test
    public void testCommitUitleningLidPermissionException()
    {
        /**
         * In deze test cae testen we of de LidPermissionException op de juiste momenten gegooid word.
         */
        try
        {
            /**
             * In deze testcase gaan we alle persoontype's testen op een commitUitlening,
             * decommentariseer welke je wil testen
             */

            //lid.setPermissie(PersoonType.ADMINISTRATOR);
            lid.setPermissie(PersoonType.AFGESLOTEN);
            //lid.setPermissie(PersoonType.BIBLIOTHECARIS);

            uitleenBuilder.setPersoon(lid);
            uitleenBuilder.setBibliothecaris(bibl);

            uitleenBuilder.commitUitlening();

            fail("No Exception thrown!");
        } catch (LidException e)
        {
            //Lid is niet null, dus mag niet gegooid worden
            fail("LidException thrown!");
        } catch (LidPermissionException e)
        {
            //Bij niet-leden, geen geldige permissie, test geslaagd!
        } catch (BibliothecarisException e)
        {
            fail("BibliothecarisException thrown!");
        } catch (ItemException e)
        {
            fail("ItemException thrown!");
        }
    }

    @Test
    public void testCommitUitleningBoekenException()
    {
        /**
         * We testen de boekenException zodanig dat er geen uitlening kan gebeuren zonder boeken
         */
        try
        {
            /**
             * We testen dit ook voor als we de permissie niet initialiseren... commentariseer hiervoor volgende lijn uit.
             * Resultaat: Persoon heeft niet standaard de permissie lid!
             */
            lid.setPermissie(PersoonType.LID);

            uitleenBuilder.setPersoon(lid);
            uitleenBuilder.setBibliothecaris(bibl);

            uitleenBuilder.commitUitlening();

            fail("No Exception thrown!");
        } catch (LidException e)
        {
            /*Lid is niet null, dus mag niet gegooid worden*/
            fail("LidException thrown!");
        } catch (LidPermissionException e)
        {
            //Persoon is een lid, LidException is dus fout!
            fail("LidPermissionException thrown!");
        } catch (BibliothecarisException e)
        {
            fail("BibliothecarisException thrown!");
        } catch (ItemException e)
        {
            e.printStackTrace();
        }
    }

    @Test
    public void testCommitUitleningSuccess()
    {
        /**
         * We testen de case waarbij de commit hoort te slagen!
         */

        try
        {
            lid.setPermissie(PersoonType.LID);
            bibl.setPermissie(PersoonType.BIBLIOTHECARIS);

            uitleenBuilder.setPersoon(lid);
            uitleenBuilder.setBibliothecaris(bibl);

            uitleenBuilder.addExemplaar(itemExemplaar);
            uitleenBuilder.commitUitlening();

            assertEquals("Fail - Geen uitlening in DB", uitleningDAO.findAll().size(), 1);

        } catch (LidException e)
        {
            //Lid is niet null, dus mag niet gegooid worden
            fail("LidException thrown!");
        } catch (LidPermissionException e)
        {
            //Persoon is heeft de permissie lid
            fail("LidPermissionException thrown!");
        } catch (BibliothecarisException e)
        {
            fail("BibliothecarisException thrown!");
        } catch (ItemException e)
        {
            e.printStackTrace();
        }
        /**
         * Geeft momenteel een boekenException wat niet zou mogen omdat er exemplaren al reeds zijn toegevoegd!
         */
    }
}
