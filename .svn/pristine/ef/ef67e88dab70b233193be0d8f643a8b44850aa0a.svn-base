package org.bibly.logic.dao;

import org.bibly.logic.BaseTest;
import org.bibly.logic.dao.interfaces.IAdresDAO;
import org.bibly.logic.dao.interfaces.IGemeenteDAO;
import org.bibly.logic.dao.interfaces.IVergaderruimteDAO;
import org.bibly.logic.models.Adres;
import org.bibly.logic.models.Gemeente;
import org.bibly.logic.models.Vergaderruimte;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


/**
 * Created by Dries on 12/12/13.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/config/testApplicationContext.xml")
public class VergaderruimteDAOTest extends BaseTest
{
    @Resource
    private IGemeenteDAO gemeenteDAO;
    @Resource
    private IAdresDAO adresDAO;
    @Resource
    private IVergaderruimteDAO vergaderruimteDAO;

    private Vergaderruimte vergaderruimte;

    @Before
    public void setUp() throws Exception
    {
        Gemeente gemeente = new Gemeente("Erope", 9420);
        gemeente = gemeenteDAO.save(gemeente);
        Adres adres = new Adres("Ergens", "3", 1, gemeente);
        adres = adresDAO.save(adres);
        vergaderruimte = new Vergaderruimte(5, adres, 40);
        vergaderruimte = vergaderruimteDAO.save(vergaderruimte);
    }

    @After
    public void tearDown() throws Exception
    {
        vergaderruimteDAO.delete(vergaderruimte);
    }

    @Test
    public void testFindByMaxPersonen()
    {
        List<Vergaderruimte> vergaderruimtes = vergaderruimteDAO.findByMaxPersonen(2);
        assertEquals(0, vergaderruimtes.size());
    }

    @Test
    public void testFindByExample()
    {
        List<Vergaderruimte> vergaderruimtes = vergaderruimteDAO.findByExample(vergaderruimte);
        assertEquals(1, vergaderruimtes.size());

        for (Vergaderruimte vergaderruimte : vergaderruimtes)
        {
            assertEquals("Fail! Examples komen niet overeen!", vergaderruimte, vergaderruimte);
        }
    }
    
    /*
    @Test
    public void testDelete()
    {
       Vergaderruimte vergaderruimteInDatabase = vergaderruimteDAO.delete(vergaderruimte);
        assertNull(" Niet verwijderd", vergaderruimteDAO.findByExample(vergaderruimte));
    }
    */


    @Test
    public void testFindAll() throws Exception
    {
        List<Vergaderruimte> vergaderruimteList = vergaderruimteDAO.findAll();
        assertNotNull(vergaderruimteList);
        assertEquals(1, vergaderruimteList.size());
    }
}
