package org.biblyfx.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

import org.bibly.logic.dao.interfaces.IPersoneelDAO;
import org.bibly.logic.exceptions.PersoneelBestaatAlException;
import org.bibly.logic.models.Adres;
import org.bibly.logic.models.Gemeente;
import org.bibly.logic.models.Personeel;
import org.bibly.logic.models.enums.PersoonType;
import org.biblyfx.Constants;
import org.biblyfx.MainApp;
import org.biblyfx.controllers.InloggenController;

import calendar.DatePicker;

import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;

public class BeheerPersoneelAanmakenController implements Initializable
{
	private DatePicker d = new DatePicker(Locale.ENGLISH);
	
    private Personeel personeel;

    private IPersoneelDAO personeelDAO;

    @FXML
    private TextField textUsername;
    @FXML
    private PasswordField textPw;
    @FXML
    private PasswordField textPwControle;
    @FXML
    private RadioButton radioButtonAdmin;
    @FXML
    private RadioButton radioButtonBibliothecaris;

    //Moet eventueel nog aangepast worden naar een gepast medium om een datum in te geven
    @FXML
	private HBox hboxGeboortedatum;

    @FXML
    private TextField textStraat;
    @FXML
    private TextField textGemeente;
    @FXML
    private TextField textHuisnr;
    @FXML
    private TextField textBus;
    @FXML
    private TextField textPostcode;

	private MainApp mainApp;



    public boolean initializePersoneel()
    {
        this.personeel = new Personeel();
        this.personeel.setAanmaakDatum(new Date());


        this.personeel.setUsername(textUsername.getText());

        //Passwoord ingeven en passwoordcontrole
        if (textPwControle.getText().equals(textPw.getText()))
			try {
				this.personeel.setPasswoord(InloggenController.encryptPassword(textPw.getText()));
			} catch (NoSuchAlgorithmException e) {
				
				e.printStackTrace();
			}
		else
            return false;

      

        //Kiezen of er een admin of Bibliothecaris word aangemaakt
        if (radioButtonBibliothecaris.isSelected())
            this.personeel.setPermissie(PersoonType.BIBLIOTHECARIS);
        else if (radioButtonAdmin.isSelected())
            this.personeel.setPermissie(PersoonType.ADMINISTRATOR);
        else
            return false;
        
        personeel.setGeboorteDatum(d.getSelectedDate());

        //Adres initialiseren
        Adres adres = new Adres();
        adres.setStraatNaam(textStraat.getText());

        adres.setHuisNummer(Integer.parseInt(textHuisnr.getText()));
        adres.setBus(textBus.getText());

        Gemeente gemeente = new Gemeente();

        gemeente.setPostcode(Integer.parseInt(textPostcode.getText()));
        gemeente.setGemeente(textGemeente.getText());

        adres.setGemeente(gemeente);

        this.personeel.setAdres(adres);

        return true;
    }

    public boolean voegPersoneelToe() throws PersoneelBestaatAlException
    {
        //De equals methode van personeel zal enkel vergelijken op username, 2 personeelsleden met eenzelfde username zijn dus als het ware gelijk
        //Hieruit volgt dat 2 dezelfde personeelsleden niet tesamen mogen bestaan.
        if (this.initializePersoneel())
        {
            if (this.personeel.equals(personeelDAO.FindByExample(personeel)))
            {
                throw new PersoneelBestaatAlException();
            } else
            {
                //Kunnen nog eventueel voorwaarden bijkomen alvorens dit naar de DB gesaved zal worden
                personeelDAO.save(personeel);
                return true;
            }
        } else
        {
            return false;
        }
    }

    

    public MainApp getMainApp()
    {
        return mainApp;
    }

    public void setMainApp(MainApp mainApp)
    {
        this.mainApp = mainApp;
    }
    
      

	public void initialize(URL arg0, ResourceBundle arg1) {
		  personeelDAO = (IPersoneelDAO) MainApp.applicationContext.getBean("personeelDAO");
		
		  
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
	
	 public void goBack()
	    {
	        mainApp.removeTopScene(Constants.beheerStackPane);
	    }
	
	
}
