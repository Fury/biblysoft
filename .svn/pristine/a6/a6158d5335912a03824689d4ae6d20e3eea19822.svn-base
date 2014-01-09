package org.bibly.logic.dao;

import org.bibly.logic.BaseTest;
import org.bibly.logic.dao.interfaces.ICdDAO;
import org.bibly.logic.models.Cd;
import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/config/testApplicationContext.xml")

public class testCdDAO extends BaseTest
{


    @Resource
    private ICdDAO cdDAO;

    private Cd c;


    @Before
    public void setUp() throws Exception
    {


        super.setUp();

        c = cdDAO.save(new Cd("titel", "uitgever", "jaar"));
    }


    @After
    public void tearDown() throws Exception
    {
        super.tearDown();
    }

/*
    @Test
    public void testFindById()
    {
    	try
    	{
    		
    	
        Cd cdInDatabase = cdDAO.findById(c.getItemID());
        assertEquals("Id's niet gelijk", c.getItemID(),cdInDatabase.getId());
    } catch (Exception e)
    {
    	e.printStackTrace();
    	fail("EXEPTION");
    }
    }
    	
    	*/
        /*

    	 @Test
    	    public void testFindByExample() throws Exeption
    	    {
    	    	try
    	    	{
    	    		List<Cd> cds = cdDAO.FindByExample(cd);
    	    		assertEquals(1,cds.size());
    	    		
    	    		for(Cd cd : cds){
    	    			assertEquals( c.getArtiest(),cdInDatabase.getArtiest());
    	    			assertEquals( c.getTitel(),cdInDatabase.getTitel());
    	    			
    	    			
    	    		}
    	    			
    	    			
    	    		
    	    } catch (Exeption e)
    	    {
    	    	e.printStackTrace();
    	    	fail("EXEPTION");
    	    }
}

    	    	
    	
    	
    	
    	
    	
    	
    	 @Test
    	    public void testSave() throws Exeption{
    	    try{
    	    	
    	    	Cd cdInDatabase = cdDAO.Save(c);
    	    	assertEquals("cd niet in database geplaatst",cdDAO.findById(c.getId),cdInDatabase.getId());
    	    	
    	    	
    	    	
    	    } catch(Exeption e)
    	    {
    	    	e.printStackTrace();
    	    	fail( "EXEPTION");
    	    }
    	 }
    	    
    	 

    	 @Test
    	    public void testDelete() throws Exeption
    	    {
    	    	try
    	    	{
    	        Cd cdInDatabase = cdDAO.delete(c);
    	        assertNull("cd niet verwijderd", cdDAO.findByExample(c));
    	    } catch (Exeption e)
    	    {
    	    	e.printStackTrace();
    	    	fail("EXEPTION");
    	    }
    	    }
    	    	
    	 
    	 
    	 
    	 
    	 */


}

