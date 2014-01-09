package org.bibly.logic.dao;

import org.bibly.logic.BaseTest;
import org.bibly.logic.dao.interfaces.IGemeenteDAO;
import org.bibly.logic.models.Gemeente;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by Dries on 11/20/13.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/config/testApplicationContext.xml")
public class GemeenteDAOTest extends BaseTest
{

    @Resource
    private IGemeenteDAO gemeenteDAO;

    private Gemeente x;
    private Gemeente gemeenteInDatabase;


    @Before
    public void setUp() throws Exception
    {
        super.setUp();
        x = new Gemeente("Erpe-Mere", 9420);
        x = gemeenteDAO.save(x);
    }

    @After
    public void tearDown() throws Exception
    {
        super.tearDown();
        gemeenteDAO.delete(x);
    }

    @Test
    public void testFindById()
    {
        Gemeente gemeenteInDatabase = gemeenteDAO.findById(x.getGemeenteID());
        assertEquals("Fail - IDs different", x.getGemeenteID(), gemeenteInDatabase.getGemeenteID());
    }

    @Test
    public void testFindByExample()
    {
        List<Gemeente> gemeentes = gemeenteDAO.FindByExample(x);
        assertEquals(1, gemeentes.size());

        for (Gemeente gemeente : gemeentes)
        {
            assertEquals("Fail! Examples komen niet overeen!", x, gemeente);
        }
    }

    @Test
    public void testInsert()
    {
        Gemeente gemeenteInDatabase = gemeenteDAO.save(x);
        assertEquals("Gemeente niet in database geplaatst", gemeenteDAO.findById(x.getGemeenteID()), x);
    }
    
    /*
    @Test
    public void testDelete()
    {
        Gemeente gemeenteInDatabase = gemeenteDAO.delete(x);
        assertNull("boek niet verwijderd", gemeenteDAO.findByExample(x));
    }
    */
}
