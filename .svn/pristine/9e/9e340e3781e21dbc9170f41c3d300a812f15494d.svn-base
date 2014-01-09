package org.biblyfx.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import org.biblyfx.MainApp;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Dries on 13/12/13.
 */
public class RootPaneController implements Initializable
{
    private MainApp mainApp;
    @FXML
    private StageController stageController;

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

    public void passMainApp()
    {
        stageController.setMainApp(getMainApp());
        stageController.passMainApp();
    }
}
