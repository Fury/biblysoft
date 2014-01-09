package org.biblyfx.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import org.bibly.logic.dao.interfaces.IVergaderruimteDAO;
import org.bibly.logic.models.Vergaderruimte;
import org.biblyfx.Constants;
import org.biblyfx.MainApp;

import java.net.URL;
import java.util.ResourceBundle;

public class BeheerVergaderruimteController implements Initializable
{

    private MainApp mainApp;


    private IVergaderruimteDAO vergaderruimteDAO = (IVergaderruimteDAO) MainApp.applicationContext.getBean("vergaderruimteDAO");

    @FXML
    private TextField naam;
    @FXML
    private TextField adres;
    @FXML
    private TextField aantalPersonen;
    @FXML
    private TextField prijs;

    public void initialize(URL arg0, ResourceBundle arg1)
    {

    }

    public MainApp getMainApp()
    {
        return mainApp;
    }

    public void setMainApp(MainApp mainApp)
    {
        this.mainApp = mainApp;
    }

    public void goBack()
    {
        mainApp.removeTopScene(Constants.beheerStackPane);
    }

    public void addVergaderruimte(ActionEvent actionEvent)
    {
        if (naam.getText() != "" && aantalPersonen.getText() != "" && prijs.getText() != "")
        {
            try
            {
                int price = Integer.parseInt(prijs.getText());
                Vergaderruimte vergaderruimte = new Vergaderruimte();
                vergaderruimte.setTitel(naam.getText());
                vergaderruimte.setAdres(MainApp.personeelLoggedIn.getAdres());
                vergaderruimte.setPrijs(price);
                vergaderruimteDAO.save(vergaderruimte);

                prijs.setText("");
                naam.setText("");
                adres.setText("");
            } catch (NumberFormatException ex)
            {
                prijs.setText("0");
            }

        }

    }
}
