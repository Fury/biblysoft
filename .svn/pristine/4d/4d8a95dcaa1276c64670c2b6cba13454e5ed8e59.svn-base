package org.biblyfx.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import org.bibly.logic.dao.interfaces.IPersoneelDAO;
import org.bibly.logic.models.Lid;
import org.bibly.logic.models.Personeel;
import org.bibly.logic.models.enums.PersoonType;
import org.biblyfx.Constants;
import org.biblyfx.MainApp;
import org.biblyfx.enums.PaneType;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class BeheerPersoneelController implements Initializable {

	private MainApp mainApp;

	@FXML
	private Button btnZoek;
	@FXML
	private Label lblAanmaakdatum;
	@FXML
	private Label lblPermissie;
	@FXML
	private TextField txtGebruikersnaam;

	private IPersoneelDAO personeelDAO;

	private Personeel personeel;

	public void initialize(URL url, ResourceBundle resourceBundle) {

		personeelDAO = (IPersoneelDAO) MainApp.applicationContext
				.getBean("personeelDAO");

	}

	public void zoekGebruiker() {
		if (txtGebruikersnaam.getText() != "") {

			personeel = personeelDAO.findByUsername((txtGebruikersnaam
					.getText().toString()));
			lblAanmaakdatum.setText("" + personeel.getAanmaakDatum());
			lblPermissie.setText("" + personeel.getPermissie());

		}

	}

	public void goBack() {
		mainApp.removeTopScene(Constants.beheerStackPane);
	}

	public MainApp getMainApp() {
		return mainApp;

	}

	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;

	}

	public void showAanmaken() {
		try {
			BeheerPersoneelAanmakenController personeelAanpassenController = (BeheerPersoneelAanmakenController) mainApp
					.addSceneContent(Constants.personeelBeheerAanmaken,
							Constants.beheerStackPane, PaneType.AnchorPane);
			personeelAanpassenController.setMainApp(mainApp);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void showAanpassen() {
		try {
			BeheerPersoneelAanpassenController personeelAanpassenController = (BeheerPersoneelAanpassenController) mainApp
					.addSceneContent(Constants.personeelBeheerAanpassen,
							Constants.beheerStackPane, PaneType.AnchorPane);
			personeelAanpassenController.setMainApp(mainApp);
			personeelAanpassenController.setPersoneel(this.getPersoneel());
			personeelAanpassenController.vulAllesIn();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private Personeel getPersoneel() {
		return personeel;
	}

	public void verwijderPersoneel() {
		long id = personeel.getPersoonID();
		Personeel personeel = new Personeel();
		personeel = personeelDAO.findById(id);

		// Als de dao geen personeelslid met dergelijk id vind, returnt de
		// functie 'false'

		personeel.setPermissie(PersoonType.AFGESLOTEN);

	}

}
