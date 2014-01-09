package org.biblyfx.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import org.bibly.logic.dao.interfaces.ICdDAO;
import org.bibly.logic.models.Cd;
import org.biblyfx.Constants;
import org.biblyfx.MainApp;
import org.biblyfx.enums.PaneType;

public class BeheerCDController implements Initializable {

	private MainApp mainApp;

	private ICdDAO cdDAO;

	private Cd cd;

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
		cdDAO = (ICdDAO) MainApp.applicationContext.getBean("cdDAO");

	}

	public void zoekCD() {

		if (txtNummer.getText() != "") {
			cd = cdDAO.findById(Long.parseLong(txtNummer.getText()));
			lblTitel.setText(cd.getTitel());
			lblGenre.setText(cd.getGenre());
			lblJaar.setText(cd.getUitgaveJaar());
			lblUitgever.setText(cd.getUitgever());
			
			

		}

	}
	
	 public void goBack() {
	        mainApp.removeTopScene(Constants.beheerStackPane);
	    }
	
	public void CDToevoegen(){
		  try
	        {
			  BeheerCDAanmakenController nieuwCd = (BeheerCDAanmakenController) mainApp
	                    .addSceneContent(Constants.overlayBeheerCDAanmaken,
	                            Constants.beheerStackPane, PaneType.AnchorPane);
			  nieuwCd.setMainApp(getMainApp());

	        } catch (Exception e)
	        {
	            e.printStackTrace();
	        }
		
	}
	
	

	public void CDAanpassen(){
		try
        {
			BeheerCDAanpassenController cdAanpassen = (BeheerCDAanpassenController) mainApp
                    .addSceneContent(Constants.overlayBeheerCDAanpassen,
                            Constants.beheerStackPane, PaneType.AnchorPane);
			cdAanpassen.setMainApp(mainApp);
			cdAanpassen.setCd(this.getCd());
			cdAanpassen.vulAllesIn();

        } catch (Exception e)
        {
            e.printStackTrace();
        }
		
	}
	
	private Cd getCd() {
		
		return cd;
	}

	public void CDVerwijderen(){
		
		cdDAO.delete(cd);
		
		
	}
}