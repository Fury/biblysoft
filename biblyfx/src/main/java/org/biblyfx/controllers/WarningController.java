package org.biblyfx.controllers;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import org.biblyfx.MainApp;

import java.net.URL;
import java.util.ResourceBundle;


public class WarningController implements Initializable
{

    private MainApp mainApp;
    private String message;

    @FXML
    private Button bevestigKnop;
    @FXML
    private Label messageLabel;
    private String stackPane;

    public String getMessage()
    {
        return message;
    }

    public void setMessage(String message)
    {
        this.message = message;
        messageLabel.setText(message);
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


    public void Bevestig(ActionEvent actionEvent)
    {
        mainApp.removeTopScene(stackPane);
    }

    public void setStackPane(String stackPane)
    {
        this.stackPane = stackPane;
    }

    public String getStackPane()
    {
        return stackPane;
    }
}

