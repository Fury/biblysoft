package org.biblyfx.controllers.items;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import org.bibly.logic.models.Boek;
import org.bibly.logic.models.Dvd;
import org.bibly.logic.models.Item;
import org.biblyfx.Constants;
import org.biblyfx.MainApp;

public class DvdDetailsOverlayController implements Initializable
{    
	private Item item;
	
	@FXML
    private Label lblTitel;
	@FXML
    public Label lblAuteur;
	@FXML
    public Label lblLeeftijd;
	@FXML
    public Label lblSpeelduur;
	@FXML
    public Label lblUitgever;
	@FXML
    public Label lblGenre;
	@FXML
    public Label lblJaar;
	
	
	

    public void setItem(Item item)
    {
        lblTitel.setText(item.getTitel());
        lblAuteur.setText(((Dvd) item).getActeurs().toString());
        lblLeeftijd.setText(String.valueOf(((Dvd) item).getMinleeftijd()));
        lblSpeelduur.setText(String.valueOf(((Dvd) item).getSpeelduur()));
        lblUitgever.setText(item.getUitgever());
        lblGenre.setText(item.getGenre());
        lblJaar.setText(item.getUitgaveJaar());
        
        
    }

    private MainApp mainApp;
    
    public Item getItem()
    {
        return item;
    }

    public MainApp getMainApp()
    {
        return mainApp;
    }

    public void setMainApp(MainApp mainApp)
    {
        this.mainApp = mainApp;
    }

    public void initialize(URL url, ResourceBundle resourceBundle)
    {
    }

    public void goBack()
    {
        mainApp.removeTopScene(Constants.zoekenStackPane);
    }
}
