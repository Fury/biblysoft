package org.bibly.logic.dao;

/*
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/config/testApplicationContext.xml")
public class TestUitleningDAO extends BaseTest
{

    Date vandaag = new Date();
    @Resource
    private ILidDAO lidDAO;
    @Resource
    private IPersoonDAO persoonDAO;
    @Resource
    private IUitleningDAO uitleningDAO;
    @Resource
    private IGemeenteDAO gemeenteDAO;
    @Resource
    private IAdresDAO adresDAO;
    @Resource
    private IPersoneelDAO personeelDAO;
    private Gemeente g;
    private Adres a;
    private Lid l;
    private Personeel p;
    private Uitlening u;
    private Uitlening uitleningInDatabase;

    @Before
    public void setUp() throws Exception
    {
        super.setUp();

        g = new Gemeente("Jette", 1090);
        g = gemeenteDAO.save(g);
        a = new Adres(" straatNaam", "a", 100, g);
        a = adresDAO.save(a);
        l = new Lid(a, PersoonType.LID, vandaag);
        l =lidDAO.save(l);
        p = new Personeel(a, PersoonType.BIBLIOTHECARIS, vandaag);
        p = personeelDAO.save(p);
        u = new Uitlening(vandaag, l, p);
        u.setDetails(new HashSet<UitleningDetail>());
        u = uitleningDAO.save(u);
    }

    @After
    public void tearDown() throws Exception
    {
        super.tearDown();
        gemeenteDAO.delete(g);
        adresDAO.delete(a);
        lidDAO.delete(l);
        personeelDAO.delete(p);
        uitleningDAO.delete(u);
    }
    @Test
    public void testFindByExample()
    {
        List<Uitlening> uitleningen = uitleningDAO.FindByExample(u);
        assertEquals(1, uitleningen.size());

        for (Uitlening uitlening : uitleningen)
        {
            assertEquals("Fail! Examples komen niet overeen!", u, uitlening);
        }
    }

    @Test
    public void testInsert()
    {
        Uitlening UitleningInDatabase = uitleningDAO.save(u);
        assertEquals("Uitlening is niet in de database geplaatst", uitleningDAO.findById(u.getUitleenID()), u);
    }

    /*
    @Test
    public void testDelete()
    {
        Uitlening uitleningInDatabase = uitleningDAO.delete(u);
        assertNull("uitlening is niet verwijderd", uitleningDAO.FindByExample(u));
    }



}
*/