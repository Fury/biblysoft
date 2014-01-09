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

import java.net.URL;
import java.util.*;









import org.bibly.logic.dao.interfaces.IBoekDAO;
import org.bibly.logic.models.Boek;
import org.springframework.context.support.ClassPathXmlApplicationContext;



public class BoekenPerGenrePieChart
{
    private List<Boek> boeken = null;
    private Map<String, Integer> aantalPerGenre = new HashMap<String, Integer>();

    private IBoekDAO boekDAO;

    public void getBoekenPerGenre()
    {
       boeken = boekDAO.findAll();

        for (Boek b : boeken)
        {
            String genre = b.getGenre();
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

       boekDAO = (IBoekDAO) applicationContext.getBean("boekDAO");
        
        this.getBoekenPerGenre();        
        
         ObservableList <PieChart.Data> chart = FXCollections.observableArrayList();

        
        for(Map.Entry<String, Integer>  entry : aantalPerGenre.entrySet())
        {
        		
        		chart.add(new PieChart.Data(entry.getKey(),entry.getValue()));
        }
        
      
        
        PieChart pieChart = new PieChart(chart);
        pieChart.setTitle("Aantal boeken per genre");
        pieChart.setMaxSize(400, 300);
        return pieChart;
    }
}

