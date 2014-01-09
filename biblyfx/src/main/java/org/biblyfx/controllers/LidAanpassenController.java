package org.biblyfx.controllers;
 
 
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
 

import org.bibly.logic.dao.interfaces.ILidDAO;
import org.bibly.logic.models.Adres;
import org.bibly.logic.models.Gemeente;
import org.bibly.logic.models.Lid;
import org.biblyfx.Constants;
import org.biblyfx.MainApp;
 
import org.biblyfx.enums.PaneType;

import calendar.DatePicker;
 

import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;
 
public class LidAanpassenController implements Initializable {
 
    private MainApp mainApp;
   
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
    private Button btnAanpassen;
    @FXML
    private Button btnAnnuleren;
 
    private Lid lid;
 
    private ILidDAO lidDAO;
   
        private Adres adres;
 
        private Gemeente gemeente;
 
 
    public MainApp getMainApp() {
        return mainApp;
    }
 
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }
 
    public void setLid(Lid lid) {
        this.lid = lid;
 
    }
 
    public void initialize(URL url, ResourceBundle resourceBundle) {
        lidDAO = (ILidDAO) MainApp.applicationContext.getBean("lidDAO");
    }
 
    public void vulAllesIn() {
        if (lid == null)
            return;
        txtVoornaam.setText(lid.getVoornaam());
        txtNaam.setText(lid.getAchternaam());
       
       
        try
        {
                d.setSelectedDate(lid.getGeboorteDatum());
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
       
       
        txtTelefoon.setText(lid.getTelefoon());
        txtGsm.setText(lid.getGsmNummer());
        txtEmail.setText(lid.getEmail());
        txtStraat.setText(lid.getAdres().getStraatNaam());
        txtGemeente.setText(lid.getAdres().getGemeente().getGemeente());
        txtNummer.setText(String.valueOf(lid.getAdres().getHuisNummer()));
        txtBus.setText(lid.getAdres().getBus());
        txtPostcode.setText(String.valueOf(lid.getAdres().getGemeente().getPostcode()));
    }
 
    public void goBack() {
        mainApp.removeTopScene(Constants.ledenStackPane);
    }
   
    public void aanpassingenOpslaan(){
       adres = lid.getAdres();
 
   gemeente = lid.getAdres().getGemeente();
 
               
                gemeente.setGemeente(txtGemeente.getText());
                gemeente.setPostcode(Integer.parseInt(txtPostcode.getText()));
               
                adres.setBus(txtBus.getText());
                adres.setGemeente(gemeente);
                adres.setHuisNummer(Integer.parseInt(txtNummer.getText()));
                adres.setStraatNaam(txtStraat.getText());
               
       
                lid.setAchternaam(txtNaam.getText());
                lid.setAdres(adres);
                lid.setEmail(txtEmail.getText());
                lid.setGeboorteDatum(d.getSelectedDate());
                lid.setGsmNummer(txtGsm.getText());
                lid.setTelefoon(txtTelefoon.getText());
                lid.setVoornaam(txtVoornaam.getText());
               
                lidDAO.save(lid); 
                showPopup("Dit lid is aangepast.");
     
       
       
    }
    
    public void showPopup(String message)
    {
        try
        {
            WarningController warningController = (WarningController) mainApp.addSceneContent(Constants.warningScreen, Constants.ledenStackPane, PaneType.AnchorPane);
            warningController.setMainApp(getMainApp());
            warningController.setMessage(message);
            warningController.setStackPane(Constants.ledenStackPane);
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    
    
    
    
    }