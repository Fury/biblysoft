package org.biblyfx.controllers.charts;




import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.stage.Stage;








import org.bibly.logic.dao.CdDAO;
import org.bibly.logic.dao.interfaces.IBoekDAO;
import org.bibly.logic.dao.interfaces.ICdDAO;
import org.bibly.logic.models.Cd;
import org.springframework.context.support.ClassPathXmlApplicationContext;



public class CdsPerGenrePieChart
{
    private List<Cd> cds = null;
    private Map<String, Integer> aantalPerGenre = new HashMap<String, Integer>();

    private ICdDAO cdDAO;

    public void getCdsPerGenre()
    {
       cds = cdDAO.findAll();

        for (Cd c : cds)
        {
            String genre = c.getGenre();
            genre = genre.toLowerCase();

            if (aantalPerGenre.containsKey(genre))
            {
                aantalPerGenre.put(genre, aantalPerGenre.get(genre) + 1);
            } 
            else
            {
                aantalPerGenre.put(genre, 1);
            }
        }
    }

    public PieChart start()
    {
    	
    	
    	ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("config/SpringContext.xml");

        cdDAO = (ICdDAO) applicationContext.getBean("cdDAO");
         
         this.getCdsPerGenre();
        
        ObservableList <PieChart.Data> chart = FXCollections.observableArrayList();

        for(Map.Entry<String, Integer>  entry : aantalPerGenre.entrySet())
        {
        	
        	
        		
        	chart.add(new PieChart.Data(entry.getKey(),entry.getValue()));
        }
        
      
        
        PieChart pieChart = new PieChart(chart);
        pieChart.setTitle("Aantal CD's per genre");
        pieChart.setMaxSize(400, 300);
        return pieChart;
    }

}





