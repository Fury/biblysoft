package org.biblyfx.controllers.charts;

import javafx.application.Application;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;
import org.bibly.logic.dao.interfaces.IUitleningDetailsDAO;
import org.bibly.logic.models.UitleningDetail;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.net.URL;
import java.util.*;

public class AantalUitleningenPerGenreBarChart /*implements Initializable*/
{
    private List<UitleningDetail> uitleningDetails = null;
    private Map<String, Integer> aantalPerGenre = new HashMap<String, Integer>();

    private IUitleningDetailsDAO uitleningdetailsDAO;

    public void getAantalPerGenre()
    {
        uitleningDetails = uitleningdetailsDAO.findAll();

        for (UitleningDetail ud : uitleningDetails)
        {
            String genre = ud.getExemplaar().getItem().getGenre();
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

    public BarChart<String, Number> start()
    {

        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("config/SpringContext.xml");

        uitleningdetailsDAO = (IUitleningDetailsDAO) applicationContext.getBean("uitleningdetailsDAO");

        this.getAantalPerGenre();

        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();

        final BarChart<String, Number> bc = new BarChart<String, Number>(xAxis, yAxis);

        bc.setTitle("Aantal exemplaren uitgeleend per genre");

        yAxis.setLabel("Aantal exemplaren");

        XYChart.Series series = new XYChart.Series();
        series.setName("Genres");

        for (Map.Entry<String, Integer> entry : aantalPerGenre.entrySet())
        {
            series.getData().add(new XYChart.Data(entry.getKey(), entry.getValue()));
        }

        bc.setMaxSize(400, 300);
        bc.getData().add(series);
        return bc;

    }
}
