package org.biblyfx.controllers;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import org.biblyfx.Constants;
import org.biblyfx.MainApp;
import org.biblyfx.controllers.interfaces.YesNoInterface;

import java.net.URL;
import java.util.ResourceBundle;


public class Warning2Controller implements Initializable
{
    private MainApp mainApp;
    private String message;

    @FXML
    private Button btnYes;
    @FXML
    private Button btnNo;
    @FXML
    private Label messageLabel;

    private YesNoInterface yesNoInterface;

    private String stackPane;
    private YesNoInterface request;

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

    public String getStackPane()
    {
        return stackPane;
    }

    public void setStackPane(String stackPane)
    {
        this.stackPane = stackPane;
    }


    public void setRequest(YesNoInterface request)
    {
        this.request = request;
    }

    public YesNoInterface getRequest()
    {
        return request;
    }


    public void klikJa(ActionEvent actionEvent)
    {
        request.klikJa();
        mainApp.removeTopScene(Constants.ledenStackPane);
    }

    public void klikNee(ActionEvent actionEvent)
    {
        request.klikNee();
        mainApp.removeTopScene(Constants.ledenStackPane);
    }
}