
package org.bibly.logic.dao;

import org.bibly.logic.BaseTest;
import org.bibly.logic.dao.interfaces.IDvdDAO;
import org.bibly.logic.models.Dvd;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.List;

import static org.junit.Assert.assertEquals;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/config/testApplicationContext.xml")


public class TestDvdDAO extends BaseTest
{

    @Resource
    private IDvdDAO dvdDAO;

    private Dvd d;
    private Dvd dvdInDatabase;


    @Before
    public void setUp() throws Exception
    {
        super.setUp();
        d = new Dvd("Titel", " Uitgever", "Jaar");
        d = dvdDAO.save(d);
    }

    @After
    public void tearDown() throws Exception
    {
        super.tearDown();
        dvdDAO.delete(d);
    }


    @Test
    public void testFindByExample()
    {
        List<Dvd> dvds = dvdDAO.FindByExample(d);
        assertEquals(1, dvds.size());

        for (Dvd dvd : dvds)
        {
            assertEquals("Fail! Examples komen niet overeen!", d, dvd);
        }
    }

    @Test
    public void testSave()
    {
        Dvd dvdInDatabase = dvdDAO.save(d);
        assertEquals("Gemeente niet in database geplaatst", dvdDAO.findById(d.getItemID()), d);
    }
    
    /*
    @Test
    public void testDelete()
    {
       Dvd dvdInDatabase = dvdDAO.delete(d);
        assertNull(" Niet verwijderd", dvdDAO.FindByExample(d));
    }

    */
}

