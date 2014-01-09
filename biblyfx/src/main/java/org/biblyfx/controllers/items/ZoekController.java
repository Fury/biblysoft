package org.biblyfx.controllers.items;

import javafx.fxml.Initializable;
import org.biblyfx.MainApp;
import java.net.URL;
import java.util.ResourceBundle;
import org.biblyfx.Constants;
import org.biblyfx.enums.PaneType;

public class ZoekController implements Initializable
{
    private MainApp mainApp;
    
    
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
    
    public MainApp getMainApp() {
        return mainApp;
    }

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }
    
	public void showBoekZoeken() {
		try {
			ZoekBoekController zoekBoekController = (ZoekBoekController) mainApp
					.addSceneContent(Constants.zoekenBoekenOverlay,
							Constants.zoekenStackPane, PaneType.AnchorPane);
			zoekBoekController.setMainApp(mainApp);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void showCDZoeken() {
		try {
			ZoekCdController zoekCDController = (ZoekCdController) mainApp
					.addSceneContent(Constants.zoekenCDOverlay,
							Constants.zoekenStackPane, PaneType.AnchorPane);
			zoekCDController.setMainApp(mainApp);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void showDVDZoeken() {
		try {
			ZoekDvdController zoekDvdController = (ZoekDvdController) mainApp
					.addSceneContent(Constants.zoekenDVDOverlay,
							Constants.zoekenStackPane, PaneType.AnchorPane);
			zoekDvdController.setMainApp(mainApp);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
