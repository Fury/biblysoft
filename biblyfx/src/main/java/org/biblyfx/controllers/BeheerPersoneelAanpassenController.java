package org.biblyfx.controllers;

import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

import org.bibly.logic.dao.interfaces.IPersoneelDAO;
import org.bibly.logic.models.Adres;
import org.bibly.logic.models.Gemeente;
import org.bibly.logic.models.Personeel;
import org.bibly.logic.models.enums.PersoonType;
import org.biblyfx.Constants;
import org.biblyfx.MainApp;
import org.biblyfx.enums.PaneType;

import calendar.DatePicker;

public class BeheerPersoneelAanpassenController implements Initializable{

	private MainApp mainApp;
	
	private Personeel personeel;
	
private DatePicker d = new DatePicker(Locale.ENGLISH);

private Adres adres;

private Gemeente gemeente;
	

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

	
   
	
	 public void goBack()
	    {
			mainApp.removeTopScene(Constants.beheerStackPane);
	    }
	
	  public MainApp getMainApp()
	    {
	        return mainApp;
	    }

	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
		
	}

	public void vulAllesIn() {

		if (personeel == null)
            return;
		
		textUsername.setText(personeel.getUsername());
	
       
		if(personeel.getPermissie() == PersoonType.BIBLIOTHECARIS)
		{
			radioButtonBibliothecaris.setSelected(true);
			radioButtonAdmin.setSelected(false);
		}
		else
		{
			radioButtonBibliothecaris.setSelected(false);
			radioButtonAdmin.setSelected(true);
		}
		

        try
        {
                d.setSelectedDate(personeel.getGeboorteDatum());
            d.setDateFormat(new SimpleDateFormat("yyyy-MM-dd"));
            d.getCalendarView().todayButtonTextProperty().set("Today");
            d.getCalendarView().setShowWeeks(false);
            d.getStylesheets().add("/styles/calendar.css");
        } catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
 
        /**
         * show datepicker
         */
        hboxGeboortedatum.getChildren().add(d);
       
		
		 textStraat.setText(personeel.getAdres().getStraatNaam());
	        textGemeente.setText(personeel.getAdres().getGemeente().getGemeente());
	        textHuisnr.setText("" + personeel.getAdres().getHuisNummer());
	        textBus.setText(personeel.getAdres().getBus());
	        textPostcode.setText("" + personeel.getAdres().getGemeente().getPostcode());
	}

	public void initialize(URL arg0, ResourceBundle arg1) { 
		personeelDAO = (IPersoneelDAO) MainApp.applicationContext.getBean("personeelDAO");
	}

	public void setPersoneel(Personeel personeel) {
		this.personeel = personeel;
		
	}
	
	public void aanpassingenOpslaan(){
		
		adres = personeel.getAdres();
		 
		   gemeente = personeel.getAdres().getGemeente();
		 
		               
		                gemeente.setGemeente(textGemeente.getText());
		                gemeente.setPostcode(Integer.parseInt(textPostcode.getText()));
		               
		                adres.setBus(textBus.getText());
		                adres.setGemeente(gemeente);
		                adres.setHuisNummer(Integer.parseInt(textHuisnr.getText()));
		                adres.setStraatNaam(textStraat.getText());
		               
		       
		                
		                personeel.setAdres(adres);
		                
		                if (textPwControle.getText().equals(textPw.getText()))
		                	try {
		        				this.personeel.setPasswoord(InloggenController.encryptPassword(textPw.getText()));
		        			} catch (NoSuchAlgorithmException e) {
		        				
		        				e.printStackTrace();
		        			}
		                
		                personeel.setGeboorteDatum(d.getSelectedDate());
		                personeel.setUsername(textUsername.getText());
		               
		                personeelDAO.save(personeel); 
		                showPopup("Dit personeelslid is aangepast.");
		     
		       
		       
		    }
		    
		    public void showPopup(String message)
		    {
		        try
		        {
		            WarningController warningController = (WarningController) mainApp.addSceneContent(Constants.warningScreen, Constants.beheerStackPane, PaneType.AnchorPane);
		            warningController.setMainApp(getMainApp());
		            warningController.setMessage(message);
		            warningController.setStackPane(Constants.beheerStackPane);
		        } catch (Exception e)
		        {
		            e.printStackTrace();
		        }
		    }
		    
	
}
