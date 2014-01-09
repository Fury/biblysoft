package org.biblyfx.controllers;

import javafx.fxml.Initializable;
import org.biblyfx.Constants;
import org.biblyfx.MainApp;
import org.biblyfx.enums.PaneType;

import java.net.URL;
import java.util.ResourceBundle;

public class BeheerController implements Initializable {

	private MainApp mainApp;

	public void showStatistiek() {
		try {
			BeheerStatistiekController overlayStatistiekController = (BeheerStatistiekController) mainApp
					.addSceneContent(Constants.beheerOverlay,
							Constants.beheerStackPane, PaneType.AnchorPane);
			overlayStatistiekController.setMainApp(mainApp);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void showBeheerPersoneel() {
		try {
			BeheerPersoneelController personeelController = (BeheerPersoneelController) mainApp
					.addSceneContent(Constants.overlayPersoneelBeheer,
							Constants.beheerStackPane, PaneType.AnchorPane);
			personeelController.setMainApp(mainApp);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void showBeheerItems() {
		try {
			BeheerItemsController itemsBeheer = (BeheerItemsController) mainApp
					.addSceneContent(Constants.overlayItemsBeheer,
							Constants.beheerStackPane, PaneType.AnchorPane);
			itemsBeheer.setMainApp(mainApp);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void showBeheerVergaderzaal() {
		try {
			BeheerVergaderruimteController vergaderruimteBeheer = (BeheerVergaderruimteController) mainApp
					.addSceneContent(Constants.overlayBeheerVergaderzaal, 
							Constants.beheerStackPane, PaneType.AnchorPane);
			vergaderruimteBeheer.setMainApp(mainApp);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void initialize(URL url, ResourceBundle resourceBundle) {

	}

	public MainApp getMainApp() {
		return mainApp;
	}

	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
	}
}