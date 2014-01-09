package org.bibly.logic.dao;

import org.bibly.logic.BaseTest;
import org.bibly.logic.dao.interfaces.IBoekDAO;
import org.bibly.logic.models.Boek;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.List;

import static org.junit.Assert.*;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/config/testApplicationContext.xml")
public class testBoekDAO extends BaseTest
{

    @Resource
    private IBoekDAO boekDAO;
    private Boek boekInDatabase;

    private Boek b;

    @Before
    public void setUp() throws Exception
    {
        super.setUp();
        b = boekDAO.save(new Boek("titel1", "auteur1", "uitgever", "datum", 2000));
    }


    @After
    public void tearDown() throws Exception
    {
        super.tearDown();
        boekDAO.delete(b);
    }

    @Test
    public void getBoekByISBN()
    {
        b.setIsbn(9999999999l);
        boekDAO.save(b);
        Boek boek1 = boekDAO.getBoekByISBN(b.getIsbn());
        assertNotNull(boek1);
    }


    @Test
    public void getBoekByISBNValue0()
    {
        Boek boek1 = boekDAO.getBoekByISBN(b.getIsbn());
        assertNull(boek1);
    }

    @Test
    public void testFindByExample()
    {
        List<Boek> boeken = boekDAO.FindByExample(b);
        assertEquals(1, boeken.size());
        assertEquals(boeken.get(0), b);
    }
    /*
    @Test
    public void testDelete()
    {
    	//Boek boekInDatabase = boekDAO.delete(b);
        assertNull("boek niet verwijderd", boekDAO.FindByExample(b));
    }
    */
}







