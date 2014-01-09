package org.biblyfx.controllers.items;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import org.bibly.logic.models.Cd;
import org.biblyfx.Constants;
import org.biblyfx.MainApp;

public class CdDetailsOverlayController implements Initializable
{
	
	
    private Cd cd;

    public Cd getCd()
    {
        return cd;
    }

    

    @FXML
    private Label lblTitel;
    
    @FXML
    private Label lblAuteur;

    @FXML
    private Label lblPrijs;
    @FXML
    private Label lblUitgever;

    @FXML
    private Label lblGenre;

    @FXML
    private Label lblJaar;

    @FXML
    private Label lblTaal;



    private MainApp mainApp;
    
    public void setCd(Cd item)
    {
        this.cd = item;
        lblTitel.setText(item.getTitel());
        lblAuteur.setText(item.getArtiest());
        lblPrijs.setText(String.valueOf(item.getPrijs()));
        lblUitgever.setText(item.getUitgever());
        lblGenre.setText(item.getGenre());
        lblJaar.setText(item.getUitgaveJaar());
        lblTaal.setText(item.getTaal());
        
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
