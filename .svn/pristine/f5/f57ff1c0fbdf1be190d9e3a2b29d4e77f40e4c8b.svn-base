package org.bibly.logic;

import org.bibly.logic.dao.PersoneelDAO;
import org.bibly.logic.models.Adres;
import org.bibly.logic.models.Gemeente;
import org.bibly.logic.models.Personeel;
import org.bibly.logic.models.enums.PersoonType;

import java.util.Date;
import java.util.List;

/**
 * Hello world!
 */
public class TestClass
{

    public static void saySomething()
    {
        System.out.println("ALIVE CHANGED");
    }

    public static void saySomething2()
    {
        Adres a = new Adres("Zandstraat", "", 44, new Gemeente("Erondegem", 9420));
        Personeel personeel = new Personeel("Dries", "1234", a, PersoonType.BIBLIOTHECARIS, new Date());
        PersoneelDAO personeelDAO = new PersoneelDAO();
        personeelDAO.makePersistent(personeel);

        List<Personeel> x = personeelDAO.findAll();
        for (Personeel p : x)
        {
            System.out.println(p.toString());
        }
    }

    public static void main(String[] args)
    {
    }
}