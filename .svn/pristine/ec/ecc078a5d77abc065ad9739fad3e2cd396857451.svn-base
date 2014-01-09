package org.biblyfx.controllers.charts;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.bibly.logic.dao.interfaces.ILidDAO;
import org.bibly.logic.models.Lid;
import org.bibly.logic.models.enums.PersoonType;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


public class AantalLedenPerLeeftijdBarChart
{
	private ILidDAO lidDAO;
	
	private int catA,catB,catC,catD,catE;
	
	private Date vandaag = new Date();
	private Date geboorte;
	
	private List<Lid> leden = new ArrayList<Lid>();
	
	public void getLedenPerCategorie()
	{
		
		leden = lidDAO.findAll();
		
		for(Lid l : leden)
		{
			if(l.getPermissie() == PersoonType.LID)
			{
				geboorte = l.getGeboorteDatum();
				
				if(geboorte.getYear() > vandaag.getYear()-15)
				{
					catA ++;				
				}
				else if (geboorte.getYear() > vandaag.getYear()-25) 
				{
					catB ++;
				}
				else if(geboorte.getYear() > vandaag.getYear()-45) 
				{
					catC ++;				
				}
				else if(geboorte.getYear() > vandaag.getYear()-65) 
				{
					catD ++;				
				}
				else 
				{
					catE ++;
				}				
			}			
		}
	}
	
	public BarChart<String,Number> start() 
    {
    	ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("config/SpringContext.xml");

        lidDAO = (ILidDAO) applicationContext.getBean("lidDAO");
        
        this.getLedenPerCategorie();
        
        final CategoryAxis xAxis = new CategoryAxis();
        
        final NumberAxis yAxis = new NumberAxis();        
       
        final BarChart<String,Number> lineChart = new BarChart<String,Number>(xAxis,yAxis);       
                
        lineChart.setTitle("Aantal leden per leeftijd");
        
        XYChart.Series series = new XYChart.Series();
        
        series.setName("Aantal leden");
        
        series.getData().add(new XYChart.Data("O-15", catA));
        series.getData().add(new XYChart.Data("16-25",catB));
        series.getData().add(new XYChart.Data("26-45",catC));
        series.getData().add(new XYChart.Data("46-65",catD));
        series.getData().add(new XYChart.Data("65+",catE));
        
        lineChart.setMaxSize(400, 300);
        lineChart.getData().add(series);
        return lineChart;
        
        /*
        Scene scene  = new Scene(lineChart,400,300);
        lineChart.getData().add(series);
       
        stage.setScene(scene);
        stage.show();
    }
	
	public static void main(String[] args) 
    {
        launch(args);
    */
    }
}