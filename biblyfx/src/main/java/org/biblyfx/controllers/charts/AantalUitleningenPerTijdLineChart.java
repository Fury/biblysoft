package org.biblyfx.controllers.charts;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

import org.bibly.logic.dao.interfaces.ILidDAO;
import org.bibly.logic.dao.interfaces.IUitleningDAO;
import org.bibly.logic.dao.interfaces.IUitleningDetailsDAO;
import org.bibly.logic.models.Boek;
import org.bibly.logic.models.Cd;
import org.bibly.logic.models.Dvd;
import org.bibly.logic.models.Lid;
import org.bibly.logic.models.UitleningDetail;
import org.bibly.logic.models.enums.PersoonType;
import org.biblyfx.controllers.charts.AantalLedenPerTijd.Maand;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AantalUitleningenPerTijdLineChart
{
	private int aantalUitleningenPerMaandBoek[] = new int[13];
	private int aantalUitleningenPerMaandCd[] = new int[13];
	private int aantalUitleningenPerMaandDvd[] = new int[13];
	
	private IUitleningDetailsDAO uitleningdetailsDAO;
	
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
	
	private List<UitleningDetail> uitleningen = new ArrayList<UitleningDetail>();
	
	public void getLedenPerMaand()
	{
		uitleningen = uitleningdetailsDAO.findAll();
		
		for(UitleningDetail ud : uitleningen)
		{
			if(ud.getExemplaar().getItem().getClass() == Boek.class)
			{
				Date date = ud.getUitlening().getUitleenStartDatum();
				
				if(date.getYear() == currentDate.getYear()-1)
				{
					for(int i=1; i<=12; i++)
					{
						if(date.getMonth()+1 == i)
						{
							aantalUitleningenPerMaandBoek[i]++;
						}
					}
				}
				else
				{
					if(date.getMonth() == 12 && date.getYear() == currentDate.getYear()-1)
						aantalUitleningenPerMaandBoek[0]++;
				}
			}
			else if(ud.getExemplaar().getItem().getClass() == Cd.class)
			{
				Date date = ud.getUitlening().getUitleenStartDatum();
				
				if(date.getYear() == currentDate.getYear())
				{
					for(int i=1; i<=12; i++)
					{
						if(date.getMonth()+1 == i)
						{
							aantalUitleningenPerMaandCd[i]++;
						}
					}
				}
				else
				{
					if(date.getMonth() == 12 && date.getYear() == currentDate.getYear()-1)
						aantalUitleningenPerMaandCd[0]++;
				}
			}
			else if (ud.getExemplaar().getItem().getClass() == Dvd.class)
			{
				Date date = ud.getUitlening().getUitleenStartDatum();
				
				if(date.getYear() == currentDate.getYear())
				{
					for(int i=1; i<=12; i++)
					{
						if(date.getMonth()+1 == i)
						{
							aantalUitleningenPerMaandDvd[i]++;
						}
					}
				}
				else
				{
					if(date.getMonth() == 12 && date.getYear() == currentDate.getYear()-1)
						aantalUitleningenPerMaandDvd[0]++;
				}
				
			}
		}
	}
	
    public LineChart<String,Number> start() 
    {
    	ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("config/SpringContext.xml");

    	uitleningdetailsDAO = (IUitleningDetailsDAO) applicationContext.getBean("uitleningdetailsDAO");
        
        this.getLedenPerMaand();
        
        //defining the axes
        final CategoryAxis xAxis = new CategoryAxis();
        //final NumberAxis xAxis = new NumberAxis();
        final NumberAxis yAxis = new NumberAxis();
        
        //creating the chart
        final LineChart<String,Number> lineChart = new LineChart<String,Number>(xAxis,yAxis);
        //final LineChart<Number,Number> lineChart = new LineChart<Number,Number>(xAxis,yAxis);
                
        lineChart.setTitle("Aantal uitleningen per maand");
        //defining a series
        XYChart.Series seriesBoek = new XYChart.Series();
        XYChart.Series seriesCd = new XYChart.Series();
        XYChart.Series seriesDvd = new XYChart.Series();
        XYChart.Series seriesTot = new XYChart.Series();
        seriesBoek.setName("Boeken");
        seriesCd.setName("Dvd's");
        seriesDvd.setName("Cd's");
        seriesTot.setName("Totaal");
        
        //populating the series with data
        
        /**BOEKEN*/
        for(int i=0; i<=11; i++)
        {
        	Maand maand = Maand.get(i+1);
        	seriesBoek.getData().add(new XYChart.Data(maand.getMaand(), aantalUitleningenPerMaandBoek[i]));	
        }
        seriesBoek.getData().add(new XYChart.Data("", aantalUitleningenPerMaandBoek[12]));
        
        /**CD'S*/
        for(int i=0; i<=11; i++)
        {
        	Maand maand = Maand.get(i+1);
        	seriesCd.getData().add(new XYChart.Data(maand.getMaand(), aantalUitleningenPerMaandCd[i]));	
        }
        seriesCd.getData().add(new XYChart.Data("", aantalUitleningenPerMaandCd[12]));
        
        /**DVD'S*/
        for(int i=0; i<=11; i++)
        {
        	Maand maand = Maand.get(i+1);
        	seriesDvd.getData().add(new XYChart.Data(maand.getMaand(), aantalUitleningenPerMaandDvd[i]));	
        }
        seriesDvd.getData().add(new XYChart.Data("", aantalUitleningenPerMaandDvd[12]));
        
        /**TOTAAL*/
        for(int i=0; i<=11; i++)
        {
        	Maand maand = Maand.get(i+1);
        	seriesTot.getData().add(new XYChart.Data(maand.getMaand(), aantalUitleningenPerMaandDvd[i]+aantalUitleningenPerMaandCd[i]+aantalUitleningenPerMaandBoek[i]));	
        }
        seriesTot.getData().add(new XYChart.Data("", aantalUitleningenPerMaandDvd[12]+aantalUitleningenPerMaandCd[12]+aantalUitleningenPerMaandBoek[12]));
        
       lineChart.setMaxSize(400, 300);
       lineChart.getData().addAll(seriesBoek, seriesCd, seriesDvd, seriesTot);
       return lineChart;
}
}
