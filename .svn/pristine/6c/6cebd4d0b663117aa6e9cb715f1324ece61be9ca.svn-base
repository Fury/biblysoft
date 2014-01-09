package org.biblyfx.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import org.bibly.logic.dao.interfaces.IBoekDAO;
import org.bibly.logic.models.Boek;
import org.biblyfx.Constants;
import org.biblyfx.MainApp;
import org.biblyfx.enums.PaneType;

public class BeheerBoekenController implements Initializable {

	private MainApp mainApp;

	private IBoekDAO boekDAO;

	private Boek boek;

	@FXML
	private TextField txtNummer;
	@FXML
	private Label lblTitel;
	@FXML
	private Label lblGenre;
	@FXML
	private Label lblJaar;
	@FXML
	private Label lblUitgever;
	

	public MainApp getMainApp() {
		return mainApp;
	}

	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
	}

	public void initialize(URL arg0, ResourceBundle arg1) {
		boekDAO = (IBoekDAO) MainApp.applicationContext.getBean("boekDAO");

	}

	public void zoekBoek() {

		if (txtNummer.getText() != "") {
			boek = boekDAO.findById(Long.parseLong(txtNummer.getText()));
			lblTitel.setText(boek.getTitel());
			lblGenre.setText(boek.getGenre());
			lblJaar.setText(boek.getUitgaveJaar());
			lblUitgever.setText(boek.getUitgever());
			
			

		}

	}
	
	 public void goBack() {
	        mainApp.removeTopScene(Constants.beheerStackPane);
	    }
	
	public void BoekToevoegen(){
		  try
	        {
	            BeheerBoekenAanmakenController nieuwBoek = (BeheerBoekenAanmakenController) mainApp
	                    .addSceneContent(Constants.overlayBeheerBoekenAanmaken,
	                            Constants.beheerStackPane, PaneType.AnchorPane);
	            nieuwBoek.setMainApp(getMainApp());

	        } catch (Exception e)
	        {
	            e.printStackTrace();
	        }
		
	}
	
	

	public void BoekAanpassen(){
		try
        {
			BeheerBoekenAanpassenController cdAanpassen = (BeheerBoekenAanpassenController) mainApp
                    .addSceneContent(Constants.overlayBeheerBoekenAanpassen,
                            Constants.beheerStackPane, PaneType.AnchorPane);
			cdAanpassen.setMainApp(mainApp);
			cdAanpassen.setBoek(this.getBoek());
			cdAanpassen.vulAllesIn();

        } catch (Exception e)
        {
            e.printStackTrace();
        }
		
		
	}
	
	private Boek getBoek() {
		return boek;
	}

	public void BoekVerwijderen(){
		
		boekDAO.delete(boek);
		
	}
}
