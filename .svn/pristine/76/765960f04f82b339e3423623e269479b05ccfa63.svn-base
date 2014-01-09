package org.bibly.logic.dao;

import org.bibly.logic.BaseTest;
import org.bibly.logic.dao.interfaces.IAdresDAO;
import org.bibly.logic.dao.interfaces.IGemeenteDAO;
import org.bibly.logic.dao.interfaces.ILidDAO;
import org.bibly.logic.dao.interfaces.IPersoonDAO;
import org.bibly.logic.models.Adres;
import org.bibly.logic.models.Gemeente;
import org.bibly.logic.models.Lid;
import org.bibly.logic.models.enums.PersoonType;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/config/testApplicationContext.xml")
public class TestLidDAO extends BaseTest
{

    Date vandaag = new Date();
    @Resource
    private ILidDAO lidDAO;
    @Resource
    private IPersoonDAO persoonDAO;
    @Resource
    private IAdresDAO adresDAO;
    @Resource
    private IGemeenteDAO gemeenteDAO;

    private Lid l;
    private Gemeente gemeente;
    private Adres a;
    private Lid lidInDatabase;

    @Before
    public void setUp() throws Exception
    {
        super.setUp();

        gemeente = new Gemeente("Jette", 1090);
        gemeenteDAO.save(gemeente);
        a = new Adres("straatNaam", "a", 100, gemeente);
        adresDAO.save(a);

        l = new Lid(a, PersoonType.LID, vandaag);
        l.setHuurder(true);
        l = lidDAO.save(l);
    }

    @After
    public void tearDown() throws Exception
    {
        super.tearDown();
        lidDAO.delete(l);
    }

    @Test
    public void testFindByExample()
    {
        List<Lid> leden = lidDAO.FindByExample(l);
        assertEquals(1, leden.size());

        for (Lid lid : leden)
        {
            assertEquals("Fail! Examples komen niet overeen!", l, lid);
        }
    }

    @Test
    public void testInsert()
    {
        Lid lidInDatabase = lidDAO.save(l);
        assertEquals("Lid niet in database geplaatst",
                persoonDAO.findById(l.getPersoonID()), l);
    }

	/*
     * @Test public void testDelete() { Lid lidInDatabase = lideDAO.delete(l);
	 * assertNull("lid is niet verwijderd", lidDAO.findByExample(l)); }
	 */
//
}
