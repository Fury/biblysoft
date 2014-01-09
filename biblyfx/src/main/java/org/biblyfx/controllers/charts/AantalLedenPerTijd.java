package org.biblyfx.controllers.charts;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bibly.logic.dao.interfaces.ILidDAO;
import org.bibly.logic.dao.interfaces.IUitleningDetailsDAO;
import org.bibly.logic.models.Lid;
import org.bibly.logic.models.enums.PersoonType;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

public class AantalLedenPerTijd
{
	private int aantalLedenPerMaand[] = new int[13];
	
	private ILidDAO lidDAO;
	
	private Date currentDate = new Date();
	
	public enum Maand
	{
		JANUARI(1, "jan"),
		FEBRUARI(2, "feb"),
		MAART(3, "mrt"),
		APRIL(4, "apr"),
		MEI(5, "mei"),
		JUNI(6, "jun"),
		JULI(7, "jul"),
		AUGUSTUS(8, "aug"),
		SEPTEMBER(9, "sep"),
		OKTOBER(10, "okt"),
		NOVEMBER(11, "nov"),
		DECEMBER(12, "dec");
		
		private int code;
		private String maand;
		
		private static final Map lookup = new HashMap();
		
		private Maand(int c, String m){code = c;maand = m;}
		public int getCode(){return code;}
		public String getMaand(){return maand;}
		
		static 
		{
	         //Create reverse lookup hash map 
	         for(Maand m : Maand.values())
	             lookup.put(m.getCode(), m);
	     }
		
		public static Maand get(int code) 
		{
			//the reverse lookup by simply getting 
	        //the value from the lookup HsahMap. 
	        return (Maand) lookup.get(code);
	    }
	}
	
	private List<Lid> leden = new ArrayList<Lid>();
	
	public void getLedenPerMaand()
	{
		leden = lidDAO.findAll();
		
		for(Lid l : leden)
		{
			if(l.getPermissie() == PersoonType.LID)
			{
				Date date = l.getAanmaakDatum();
				
				if(date.getYear() == currentDate.getYear())
				{
					for(int i=1; i<=12; i++)
					{
						if(date.getMonth()+1 == i)
						{
							aantalLedenPerMaand[i]++;
						}
					}
				}
				else
				{
					if(date.getMonth() == 12 && date.getYear() == currentDate.getYear()-1)
						aantalLedenPerMaand[0]++;
				}
			}
		}
	}
	
    public LineChart<String,Number> start() 
    {
    	ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("config/SpringContext.xml");

        lidDAO = (ILidDAO) applicationContext.getBean("lidDAO");
        
        this.getLedenPerMaand();
        
        //defining the axes
        final CategoryAxis xAxis = new CategoryAxis();
        //final NumberAxis xAxis = new NumberAxis();
        final NumberAxis yAxis = new NumberAxis();
        
        //creating the chart
        final LineChart<String,Number> lineChart = new LineChart<String,Number>(xAxis,yAxis);
        //final LineChart<Number,Number> lineChart = new LineChart<Number,Number>(xAxis,yAxis);
                
        lineChart.setTitle("Aantal leden op tijdslijn");
        //defining a series
        XYChart.Series series = new XYChart.Series();
        series.setName("Aantal leden");
        
        //populating the series with data
        for(int i=0; i<=11; i++)
        {
        	Maand maand = Maand.get(i+1);
        	series.getData().add(new XYChart.Data(maand.getMaand(), aantalLedenPerMaand[i]));	
        }
        series.getData().add(new XYChart.Data("", aantalLedenPerMaand[12]));
        
        lineChart.setMaxSize(400,300);
        lineChart.getData().add(series);
        return lineChart;
    }
}
