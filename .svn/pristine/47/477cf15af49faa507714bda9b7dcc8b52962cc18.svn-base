package org.biblyfx.controllers;

import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import org.bibly.logic.models.enums.PersoonType;
import org.biblyfx.MainApp;
import org.biblyfx.controllers.items.ZoekController;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

public class StageController implements Initializable
{


    @FXML
    private ZoekController zoekTabContentController;
    @FXML
    private BeheerController beheerGuiTabController;
    @FXML
    private LedenController ledenGuiTabController;
    @FXML
    private UitleenController UitleenGuiTabController;
    @FXML
    private IncheckController incheckGuiTabController;
    @FXML
    private Label systeemTijd;
    @FXML
    private Label sysDate;
    @FXML
    private Button sluiten;
    @FXML
    private Label loginNaam;
    @FXML
    private Label loginFunctie;
    @FXML
    private TabPane tabPane;
    @FXML
    private Tab beheerGUI;
    private MainApp mainApp;

    public StageController()
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

    public void initialize(URL url, ResourceBundle resourceBundle)
    {

        Task dynamicTimeTask = new Task<Void>()
        {
            @Override
            protected Void call() throws Exception
            {
                SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
                while (true)
                {
                    updateMessage(sdf.format(new Date()));

                    try
                    {
                        Thread.sleep(100);
                    } catch (InterruptedException ex)
                    {
                        break;
                    }
                }
                return null;
            }
        };

        Task dynamicDayTask = new
                Task<Void>()
                {
                    @Override
                    protected Void call() throws Exception
                    {
                        SimpleDateFormat sdf = new SimpleDateFormat("EEE, dd MMM yyyy");
                        while (true)
                        {
                            updateMessage(sdf.format(new Date()));
                            try
                            {
                                Thread.sleep(3600);
                            } catch (InterruptedException ex)
                            {
                                break;
                            }
                        }
                        return null;
                    }
                };
        systeemTijd.textProperty().bind(dynamicTimeTask.messageProperty());
        sysDate.textProperty().bind(dynamicDayTask.messageProperty());
        Thread t2 = new Thread(dynamicTimeTask);
        t2.setName("Task Time Updater");
        t2.setDaemon(true);
        t2.start();

        Thread t = new Thread(dynamicDayTask);
        t.setName("Task Day Updater");
        t.setDaemon(true);
        t.start();

    }

    public void passMainApp()
    {

        zoekTabContentController.setMainApp(getMainApp());
        beheerGuiTabController.setMainApp(getMainApp());
        ledenGuiTabController.setMainApp(getMainApp());
        UitleenGuiTabController.setMainApp(getMainApp());
        incheckGuiTabController.setMainApp(getMainApp());
        loginNaam.setText(MainApp.personeelLoggedIn.getUsername());
        loginFunctie.setText(MainApp.personeelLoggedIn.getPermissie().toString());

        if (MainApp.personeelLoggedIn.getPermissie() != PersoonType.ADMINISTRATOR)
            beheerGUI.setDisable(true);
    }

    public void sluiten()
    {
        System.exit(0);
    }

    public void uitloggen()
    {
        try
        {
            mainApp.changeSceneStageToLogIn();
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}