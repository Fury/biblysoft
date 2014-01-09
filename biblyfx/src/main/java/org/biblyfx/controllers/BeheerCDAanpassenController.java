package org.biblyfx.controllers;

import java.net.URL;
import java.util.ResourceBundle;


import javafx.scene.control.TextField;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import org.bibly.logic.dao.interfaces.ICdDAO;
import org.bibly.logic.models.Cd;
import org.biblyfx.Constants;
import org.biblyfx.MainApp;

public class BeheerCDAanpassenController implements Initializable {
	
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
	    
	    public void initialize(URL arg0, ResourceBundle arg1) {
			cdDAO = (ICdDAO) MainApp.applicationContext.getBean("cdDAO");
	    }
			

		public void vulAllesIn() {
			txtTitel.setText(cd.getTitel());
			txtArtiest.setText(cd.getArtiest());
			txtGenre.setText(cd.getGenre());
			txtUitgever.setText(cd.getUitgever());
			txtUitgavejaar.setText(String.valueOf(cd.getUitgaveJaar()));
			
			txtTaal.setText(cd.getTaal());
			txtPrijs.setText(String.valueOf(cd.getPrijs()));
			
		}
		
		
		
		public void cdAanpassen(){
			cd.setTitel(txtTitel.getText());
			cd.setArtiest(txtArtiest.getText());
			cd.setGenre(txtGenre.getText());
			cd.setUitgever(txtUitgever.getText());
			cd.setUitgaveJaar(txtUitgavejaar.getText());
			cd.setTaal(txtTaal.getText());
			cd.setPrijs(Integer.parseInt(txtPrijs.getText()));

			
			cdDAO.save(cd);
			
		}
		
		
		
		

		public void setCd(Cd cd) {
			this.cd = cd;
			
			
		}
		
		public Cd getCd(){
			return this.cd;
			
		}
		

		 public void goBack() {
		        mainApp.removeTopScene(Constants.beheerStackPane);
		    }

		

	

}
