package org.biblyfx.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

import org.bibly.logic.dao.interfaces.IDvdDAO;
import org.bibly.logic.models.Dvd;
import org.biblyfx.Constants;
import org.biblyfx.MainApp;

public class BeheerDVDAanpassenController implements Initializable {

	private MainApp mainApp;

	@FXML
	private TextField txtTitel;

	@FXML
	private TextField txtGenre;

	@FXML
	private TextField txtUitgever;
	@FXML
	private TextField txtJaar;
	@FXML
	private TextField txtTaal;
	@FXML
	private TextField txtminLeeftijd;
	@FXML
	private TextField txtSpeelduur;

	private Dvd dvd;

	private IDvdDAO dvdDAO;

	public MainApp getMainApp() {
		return mainApp;
	}

	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
	}

	public void initialize(URL arg0, ResourceBundle arg1) {
		dvdDAO = (IDvdDAO) MainApp.applicationContext.getBean("dvdDAO");

	}

	public void vulAllesIn() {
		txtTitel.setText(dvd.getTitel());
		txtGenre.setText(dvd.getGenre());
		txtJaar.setText(String.valueOf(dvd.getUitgaveJaar()));
		txtminLeeftijd.setText(String.valueOf(dvd.getMinleeftijd()));
		txtSpeelduur.setText(String.valueOf(dvd.getSpeelduur()));
		txtTaal.setText(dvd.getTaal());
		txtUitgever.setText(dvd.getUitgever());

	}

	public void dvdAanpassen() {
		dvd.setTitel(txtTitel.getText());
		dvd.setGenre(txtGenre.getText());
		dvd.setUitgever(txtUitgever.getText());
		dvd.setUitgaveJaar(txtJaar.getText());
		dvd.setTaal(txtTaal.getText());
		dvd.setMinleeftijd(Integer.parseInt(txtminLeeftijd.getText()));
		dvd.setSpeelduur(Integer.parseInt(txtSpeelduur.getText()));

		dvdDAO.save(dvd);

	}

	public void setDvd(Dvd dvd) {
		this.dvd = dvd;

	}

	public Dvd getDvd() {
		return this.dvd;

	}

	public void goBack() {
		mainApp.removeTopScene(Constants.beheerStackPane);
	}

}
