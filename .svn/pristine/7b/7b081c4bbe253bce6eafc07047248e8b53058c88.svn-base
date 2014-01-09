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

import org.bibly.logic.dao.interfaces.IUitleningDAO;
import org.bibly.logic.models.Uitlening;

import org.bibly.logic.models.enums.PersoonType;
import org.springframework.context.support.ClassPathXmlApplicationContext;



public class AantalUitleningenPerBibliothecarisPieChart
{
    private List<Uitlening> uitleningen = null;
    private Map<String, Integer> aantalPerBibliothecaris = new HashMap<String, Integer>();

    private IUitleningDAO uitleningDAO;

    public void getUitleningenPerBibliothecaris()
    {
       uitleningen = uitleningDAO.findAll();

        for (Uitlening u : uitleningen)
        {
            String bibliothecaris = u.getUitlener().getVoornaam();
            bibliothecaris = bibliothecaris.toLowerCase();
            
            

            if (u.getUitlener().getPermissie() == PersoonType.BIBLIOTHECARIS && aantalPerBibliothecaris.containsKey(bibliothecaris))
            {
               aantalPerBibliothecaris.put(bibliothecaris, aantalPerBibliothecaris.get(bibliothecaris) + 1);
            } 
            else
            {
               aantalPerBibliothecaris.put(bibliothecaris, 1);
            }
        }
    }

    public PieChart start()
    {
    	
    	ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("config/SpringContext.xml");

       uitleningDAO = (IUitleningDAO) applicationContext.getBean("uitleningDAO");
        
        this.getUitleningenPerBibliothecaris();
                
        ObservableList <PieChart.Data> chart = FXCollections.observableArrayList();
        
        for(Map.Entry<String, Integer>  entry : aantalPerBibliothecaris.entrySet())
        {
        		
        		chart.add(new PieChart.Data(entry.getKey(),entry.getValue()));
        }
        
      
        
        PieChart pieChart = new PieChart(chart);
        pieChart.setTitle("Uitleningen per bibliothecaris");
        pieChart.setMaxSize(400, 300);
        return pieChart;
        
    }
 
}

