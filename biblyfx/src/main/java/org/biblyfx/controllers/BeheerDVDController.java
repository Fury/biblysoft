package org.biblyfx.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import org.bibly.logic.dao.interfaces.IDvdDAO;
import org.bibly.logic.models.Dvd;
import org.biblyfx.Constants;
import org.biblyfx.MainApp;
import org.biblyfx.enums.PaneType;

public class BeheerDVDController implements Initializable {

	private MainApp mainApp;

	private IDvdDAO dvdDAO;

	private Dvd dvd;

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
		dvdDAO = (IDvdDAO) MainApp.applicationContext.getBean("dvdDAO");

	}

	public void zoekDVD() {

		if (txtNummer.getText() != "") {
			dvd = dvdDAO.findById(Long.parseLong(txtNummer.getText()));
			lblTitel.setText(dvd.getTitel());
			lblGenre.setText(dvd.getGenre());
			lblJaar.setText(dvd.getUitgaveJaar());
			lblUitgever.setText(dvd.getUitgever());
			
			

		}

	}
	
	 public void goBack() {
	        mainApp.removeTopScene(Constants.beheerStackPane);
	    }
	
	public void DVDToevoegen(){
		  try
	        {
	            BeheerDVDAanmakencontroller nieuwDvd = (BeheerDVDAanmakencontroller) mainApp
	                    .addSceneContent(Constants.overlayBeheerDVDAanmaken,
	                            Constants.beheerStackPane, PaneType.AnchorPane);
	            nieuwDvd.setMainApp(getMainApp());

	        } catch (Exception e)
	        {
	            e.printStackTrace();
	        }
		
	}
	
	

	public void DVDAanpassen(){
		 try
	        {
			 BeheerDVDAanpassenController dvdAanpassen = (BeheerDVDAanpassenController) mainApp
	                    .addSceneContent(Constants.overlayBeheerDVDAanpassen,
	                            Constants.beheerStackPane, PaneType.AnchorPane);
			 dvdAanpassen.setMainApp(mainApp);
			 dvdAanpassen.setDvd(this.getDvd());
			 dvdAanpassen.vulAllesIn();

	        } catch (Exception e)
	        {
	            e.printStackTrace();
	        }
		
	}
	
	private Dvd getDvd() {
		return this.dvd;
	}

	public void DVDVerwijderen(){
		dvdDAO.delete(dvd);
		
		
	}
}
