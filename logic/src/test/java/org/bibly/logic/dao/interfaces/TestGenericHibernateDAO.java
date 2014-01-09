package org.bibly.logic.dao.interfaces;

/*
public class TestGenericHibernateDAO<T>
{
	protected Class<T> entityClass;
	protected SessionFactory dbMockSF; 
	protected Transaction dbMockT; 
	protected Session dbMockS; 
	protected GenericDAO ghDAO;

    public TestGenericHibernateDAO(GenericDAO DAOclass, Class<T> entityClass)
    {
    	super();
    	ghDAO = DAOclass;
    	this.entityClass = entityClass;
    }
    
    @Before
	protected void setUp() throws Exception 
	{
		super.setUp();
		
		this.dbMockSF = EasyMock.createMock(SessionFactory.class);
		this.dbMockS = EasyMock.createMock(Session.class);
		this.dbMockT = EasyMock.createMock(Transaction.class);
		
		EasyMock.expect(dbMockSF.getCurrentSession()).andReturn(dbMockS);
		EasyMock.expect(dbMockS.beginTransaction()).andReturn(dbMockT);
	}
    
    @After
	protected void tearDown() throws Exception
	{
		super.tearDown();
		dbMockSF = null;
		dbMockS = null;
		dbMockT = null;
		ghDAO = null;
	}
    
    @Test
    public void testFindById() throws InstantiationException, IllegalAccessException
    {
    	T t = entityClass.newInstance();
    	
    	EasyMock.expect(dbMockS.load(entityClass, 1)).andReturn(t);
    	replay(dbMockS);
    	
    	ghDAO.findById(1, false);
    	
    	verify(dbMockS);
    }
}
*/