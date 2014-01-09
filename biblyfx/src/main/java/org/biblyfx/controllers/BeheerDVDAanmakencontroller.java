package org.biblyfx.controllers;

import java.net.URL;
import java.util.Calendar;
import java.util.Date;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

import org.bibly.logic.dao.interfaces.IDvdDAO;
import org.bibly.logic.models.Adres;
import org.bibly.logic.models.Dvd;
import org.bibly.logic.models.Gemeente;
import org.bibly.logic.models.Item;
import org.bibly.logic.models.Lid;
import org.bibly.logic.models.enums.PersoonType;
import org.biblyfx.Constants;
import org.biblyfx.MainApp;

public class BeheerDVDAanmakencontroller implements Initializable{

	
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
	
	
	
	 public MainApp getMainApp()
	    {
	        return mainApp;
	    }

	    public void setMainApp(MainApp mainApp)
	    {
	        this.mainApp = mainApp;
	    }
	    
	    public void DVDAanmaken(){
	    	dvd = new Dvd();
			
		
			dvd.setTitel(txtTitel.getText());
			dvd.setGenre(txtGenre.getText());
			dvd.setUitgever(txtUitgever.getText());
			dvd.setUitgaveJaar(txtJaar.getText());
			dvd.setTaal(txtTaal.getText());
			dvd.setMinleeftijd(Integer.parseInt(txtminLeeftijd.getText()));
			dvd.setSpeelduur(Integer.parseInt(txtSpeelduur.getText()));

			
			dvdDAO.save(dvd);

	    	
	    }

		public void initialize(URL arg0, ResourceBundle arg1) {
			
			dvdDAO = (IDvdDAO) MainApp.applicationContext.getBean("dvdDAO");
		}
		
		
		 public void goBack() {
		        mainApp.removeTopScene(Constants.beheerStackPane);
		    }

	
}
