package org.biblyfx.controllers;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;

import org.bibly.logic.dao.interfaces.ILidDAO;
import org.bibly.logic.models.Adres;
import org.bibly.logic.models.Gemeente;
import org.bibly.logic.models.Lid;
import org.bibly.logic.models.enums.PersoonType;
import org.biblyfx.Constants;
import org.biblyfx.MainApp;

import calendar.DatePicker;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

public class NieuwLidAanmakenController implements Initializable {

	private DatePicker d = new DatePicker(Locale.ENGLISH);

	@FXML
	private TextField txtVoornaam;
	@FXML
	private TextField txtNaam;
	@FXML
	private HBox hboxGeboortedatum;
	@FXML
	private TextField txtTelefoon;
	@FXML
	private TextField txtGsm;
	@FXML
	private TextField txtEmail;
	@FXML
	private TextField txtStraat;
	@FXML
	private TextField txtGemeente;
	@FXML
	private TextField txtNummer;
	@FXML
	private TextField txtBus;
	@FXML
	private TextField txtPostcode;
	@FXML
	private Button btnAanmaken;
	@FXML
	private Button btnAnnuleren;

	private Lid lid;
	private Adres adres;

	private Gemeente gemeente;

	private ILidDAO lidDAO;

	private MainApp mainApp;

	public void initialize(URL url, ResourceBundle resourceBundle) {
		lidDAO = (ILidDAO) MainApp.applicationContext.getBean("lidDAO");

		try {
			d.setDateFormat(new SimpleDateFormat("yyyy-MM-dd"));
			d.getCalendarView().todayButtonTextProperty().set("Today");
			d.getCalendarView().setShowWeeks(false);
			d.getStylesheets().add("/styles/calendar.css");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		/**
		 * show datepicker
		 */
		hboxGeboortedatum.getChildren().add(d);
	}

	public MainApp getMainApp() {
		return mainApp;
	}

	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
	}

	public void aanmakenLid() {
		lid = new Lid();
		gemeente = new Gemeente();
		adres = new Adres();
		
		Date aangemaakt = new Date();
		Date einddatum = new Date();
		
		Calendar c = Calendar.getInstance();
		c.setTime(aangemaakt);
		c.add(Calendar.YEAR, 1);
		einddatum = c.getTime();
		

		gemeente.setGemeente(txtGemeente.getText());
		gemeente.setPostcode(Integer.parseInt(txtPostcode.getText()));

		adres.setBus(txtBus.getText());
		adres.setGemeente(gemeente);
		adres.setHuisNummer(Integer.parseInt(txtNummer.getText()));
		adres.setStraatNaam(txtStraat.getText());

		lid.setSubscriptieEindDatum(einddatum);
		lid.setAchternaam(txtNaam.getText());
		lid.setAdres(adres);
		lid.setAanmaakDatum(aangemaakt);
		lid.setEmail(txtEmail.getText());
		lid.setGeboorteDatum(d.getSelectedDate());
		lid.setGsmNummer(txtGsm.getText());
		lid.setTelefoon(txtTelefoon.getText());
		lid.setVoornaam(txtVoornaam.getText());
		lid.setPermissie(PersoonType.LID);

		lidDAO.save(lid);

	}

	public void goBack() {
		mainApp.removeTopScene(Constants.ledenStackPane);
	}
}
