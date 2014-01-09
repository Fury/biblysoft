package org.biblyfx.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;



import org.bibly.logic.dao.interfaces.ICdDAO;
import org.bibly.logic.dao.interfaces.IDvdDAO;
import org.bibly.logic.models.Cd;
import org.bibly.logic.models.Dvd;
import org.biblyfx.Constants;
import org.biblyfx.MainApp;

public class BeheerCDAanmakenController implements Initializable {

	private MainApp mainApp;
	
	
	
	
	
	@FXML
	private TextField txtTitel;
	@FXML
	private TextField txtArtiest;
	@FXML
	private TextField txtGenre;
	@FXML
	private TextField txtUitgever;
	@FXML
	private TextField txtUitgavejaar;
	@FXML
	private TextField txtTaal;
	@FXML
	private TextField txtPrijs;
	
	private Cd cd;
	
	
	private ICdDAO cdDAO;
	
	
	
	 public MainApp getMainApp()
	    {
	        return mainApp;
	    }

	    public void setMainApp(MainApp mainApp)
	    {
	        this.mainApp = mainApp;
	    }
	    
	    
	    public void CDAanmaken(){
	    	
	    	cd = new Cd();
			
		
			cd.setTitel(txtTitel.getText());
			cd.setArtiest(txtArtiest.getText());
			cd.setGenre(txtGenre.getText());
			cd.setUitgever(txtUitgever.getText());
			cd.setUitgaveJaar(txtUitgavejaar.getText());
			cd.setTaal(txtTaal.getText());
			cd.setPrijs(Integer.parseInt(txtPrijs.getText()));

			
			cdDAO.save(cd);

	    	
	    }

		public void initialize(URL arg0, ResourceBundle arg1) {
			
			cdDAO = (ICdDAO) MainApp.applicationContext.getBean("cdDAO");
		}
		
		
		 public void goBack() {
		        mainApp.removeTopScene(Constants.beheerStackPane);
		    }



	
}
