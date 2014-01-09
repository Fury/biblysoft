package org.biblyfx.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import org.biblyfx.Constants;
import org.biblyfx.MainApp;
import org.biblyfx.controllers.charts.*;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * The type Beheer statistiek controller.
 */
public class BeheerStatistiekController implements Initializable {

	private MainApp mainApp;
	@FXML
	private AnchorPane statistiekStage;
	
	public void initialize(URL url, ResourceBundle resourceBundle) {

	}

    /**
     * Gets main app.
     *
     * @return the main app
     */
    public MainApp getMainApp() {
        return mainApp;
	}

    /**
     * Sets main app.
     *
     * @param mainApp the main app
     */
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
	}

    /**
     * Go back.
     */
    public void goBack() {
        mainApp.removeTopScene(Constants.beheerStackPane);
	}

    /**
     * Aantal leden per leeftijd bar chart.
     */
    public void AantalLedenPerLeeftijdBarChart() {
        AantalLedenPerLeeftijdBarChart aantalLedenPerLeeftijdBarChart = new AantalLedenPerLeeftijdBarChart();
		//statistiekStage.getChildren().removeAll();
		statistiekStage.getChildren().clear();
		statistiekStage.getChildren().add(aantalLedenPerLeeftijdBarChart.start());
	}

    /**
     * Aantal leden per tijd.
     */
    public void AantalLedenPerTijd() {
        AantalLedenPerTijd aantalLedenPerTijd = new AantalLedenPerTijd();
		//statistiekStage.getChildren().removeAll();
		statistiekStage.getChildren().clear();
		statistiekStage.getChildren().add(aantalLedenPerTijd.start());
	}

    /**
     * Aantal uitleningen per bibliothecaris pie chart.
     */
    public void AantalUitleningenPerBibliothecarisPieChart() {
        AantalUitleningenPerBibliothecarisPieChart aantalUitleningenPerBibliothecarisPieChart = new AantalUitleningenPerBibliothecarisPieChart();
		//statistiekStage.getChildren().removeAll();
		statistiekStage.getChildren().clear();
		statistiekStage.getChildren().add(aantalUitleningenPerBibliothecarisPieChart.start());
	}

    /**
     * Aantal uitleningen per genre bar chart.
     */
    public void AantalUitleningenPerGenreBarChart() {
        AantalUitleningenPerGenreBarChart aantalUitleningenPerGenreBarChart = new AantalUitleningenPerGenreBarChart();
		//statistiekStage.getChildren().removeAll();
		statistiekStage.getChildren().clear();
		statistiekStage.getChildren().add(aantalUitleningenPerGenreBarChart.start());
	}

    /**
     * Aantal uitleningen per tijd line chart.
     */
    public void AantalUitleningenPerTijdLineChart() {
        AantalUitleningenPerTijdLineChart aantalUitleningenPerTijdLineChart = new AantalUitleningenPerTijdLineChart();
		//statistiekStage.getChildren().removeAll();
		statistiekStage.getChildren().clear();
		statistiekStage.getChildren().add(aantalUitleningenPerTijdLineChart.start());
	}

    /**
     * Boeken per genre pie chart.
     */
    public void BoekenPerGenrePieChart() {
        BoekenPerGenrePieChart boekenPerGenrePieChart = new BoekenPerGenrePieChart();
		//statistiekStage.getChildren().removeAll();
		statistiekStage.getChildren().clear();
		statistiekStage.getChildren().add(boekenPerGenrePieChart.start());
	}

    /**
     * Cds per genre pie chart.
     */
    public void CdsPerGenrePieChart() {
        CdsPerGenrePieChart cdsPerGenrePieChart = new CdsPerGenrePieChart();
		//statistiekStage.getChildren().removeAll();
		statistiekStage.getChildren().clear();
		statistiekStage.getChildren().add(cdsPerGenrePieChart.start());
	}

    /**
     * Grootste lezer per genre.
     */
    public void GrootsteLezerPerGenre() {
        GrootsteLezerPerGenre grootsteLezerPerGenre = new GrootsteLezerPerGenre();
		//statistiekStage.getChildren().removeAll();
		statistiekStage.getChildren().clear();
		statistiekStage.getChildren().add(grootsteLezerPerGenre.start());
	}
}
