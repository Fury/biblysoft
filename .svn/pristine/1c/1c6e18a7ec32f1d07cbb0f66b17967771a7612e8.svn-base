package org.biblyfx.controllers;


//import com.sun.javaws.exceptions.InvalidArgumentException;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import org.bibly.logic.CheckinBuilder;
import org.bibly.logic.dao.interfaces.IExemplaarDAO;
import org.bibly.logic.dao.interfaces.IUitleningDAO;
import org.bibly.logic.dao.interfaces.IUitleningDetailsDAO;
import org.bibly.logic.exceptions.ItemException;
import org.bibly.logic.exceptions.UitleningException;
import org.biblyfx.Constants;
import org.biblyfx.MainApp;
import org.biblyfx.enums.PaneType;

import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;


public class IncheckController implements Initializable
{

    private MainApp mainApp;
    /**
     * Variables
     */
    private IExemplaarDAO exemplaarDAO;
    /**
     * FXML components
     */

    @FXML
    private TextField boekID;
    @FXML
    private RadioButton beschadigd;
    @FXML
    private RadioButton nietBeschadigd;
    @FXML
    private Button Inchecken;

    public MainApp getMainApp()
    {
        return mainApp;
    }

    public void setMainApp(MainApp mainApp)
    {
        this.mainApp = mainApp;
    }

    public boolean checkIn()
    {
        if (boekID.getText() == "")
            return false;


        try
        {
            long exemplaarId = Long.valueOf(boekID.getText());
            CheckinBuilder c = new CheckinBuilder();
            c.setUitleningDAO((IUitleningDAO) mainApp.applicationContext.getBean("uitleningDAO"));
            c.setUitleningdetailsDAO((IUitleningDetailsDAO) MainApp.applicationContext.getBean("uitleningdetailsDAO"));
            if (c.checkin(exemplaarId, new Date()))
                return true;
            else
                return false;
        } catch (ItemException iE)
        {
            iE.getMessage();
        } catch (UitleningException uE)
        {
            uE.getMessage();
        } /*catch (InvalidArgumentException e)
        {
            //Zou nooit mogen gebeuren
        } */catch (NumberFormatException ex)
        {
            boekID.setText("");
        }
        return false;
    }

    /**
     * Show Warning popup met 1 knop (bevestig).
     *
     * @param message the message
     */
    public void showPopup(String message)
    {
        try
        {
            WarningController warningController = (WarningController) mainApp.addSceneContent(Constants.warningScreen, Constants.incheckStackPane, PaneType.AnchorPane);
            warningController.setMainApp(getMainApp());
            warningController.setMessage(message);
            warningController.setStackPane(Constants.incheckStackPane);
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public void initialize(URL url, ResourceBundle resourceBundle)
    {

        beschadigd.setOnMouseClicked(new EventHandler<MouseEvent>()
        {
            public void handle(MouseEvent arg0)
            {
                if (arg0.getButton().equals(MouseButton.PRIMARY))
                {
                    if (arg0.getClickCount() % 2 > 0)
                    {
                        nietBeschadigd.setSelected(false);
                    }
                }

            }
        });

        nietBeschadigd.setOnMouseClicked(new EventHandler<MouseEvent>()
        {
            public void handle(MouseEvent arg0)
            {
                if (arg0.getButton().equals(MouseButton.PRIMARY))
                {
                    if (arg0.getClickCount() % 2 > 0)
                    {
                        beschadigd.setSelected(false);
                    }
                }

            }
        });
    }

    public void inchecken(ActionEvent actionEvent)
    {
        if (boekID.getText() == "")
            return;


        if (checkIn())
            showPopup("Ingecheckt");

    }
}












