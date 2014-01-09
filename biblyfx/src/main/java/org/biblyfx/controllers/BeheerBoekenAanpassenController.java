package org.biblyfx.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import org.bibly.logic.dao.interfaces.IBoekDAO;
import org.bibly.logic.models.Boek;
import org.biblyfx.Constants;
import org.biblyfx.MainApp;

public class BeheerBoekenAanpassenController implements Initializable {

	private MainApp mainApp;

	@FXML
	private TextField txtTitel;
	@FXML
	private TextField txtAuteur;
	@FXML
	private TextField txtGenre;
	@FXML
	private TextField txtUitgever;
	@FXML
	private TextField txtUitgavejaar;
	@FXML
	private TextField txtTaal;
	@FXML
	private TextField txtDruk;
	@FXML
	private TextField txtPrijs;
	@FXML
	private TextField txtISBN;

	private Boek boek;

	private IBoekDAO boekDAO;

	public MainApp getMainApp() {
		return mainApp;
	}

	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
	}

	public void vulAllesIn() {
		txtTitel.setText(boek.getTitel());
		txtGenre.setText(boek.getGenre());
		txtUitgever.setText(boek.getUitgever());
		txtUitgavejaar.setText(String.valueOf(boek.getUitgaveJaar()));
		txtAuteur.setText(boek.getAuteur());
		txtTaal.setText(boek.getTaal());
		txtPrijs.setText(String.valueOf(boek.getPrijs()));
		txtDruk.setText(String.valueOf(boek.getDruk()));
		txtISBN.setText(String.valueOf(boek.getIsbn()));

	}

	public void boekAanpassen() {

		boek.setTitel(txtTitel.getText());
		boek.setAuteur(txtAuteur.getText());
		boek.setGenre(txtGenre.getText());
		boek.setUitgever(txtUitgever.getText());
		boek.setUitgaveJaar(txtUitgavejaar.getText());
		boek.setTaal(txtTaal.getText());
		boek.setDruk(Integer.parseInt(txtDruk.getText()));
		boek.setPrijs(Integer.parseInt(txtPrijs.getText()));
		boek.setIsbn(Integer.parseInt(txtISBN.getText()));

		boekDAO.save(boek);

	}

	public void setBoek(Boek boek) {
		this.boek = boek;

	}

	public void initialize(URL arg0, ResourceBundle arg1) {
		boekDAO = (IBoekDAO) MainApp.applicationContext.getBean("boekDAO");

	}
	
	 public void goBack() {
	        mainApp.removeTopScene(Constants.beheerStackPane);
	    }
 



}
