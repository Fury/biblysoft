package org.biblyfx.controllers;

import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.stage.FileChooser;
import org.bibly.logic.dao.interfaces.IBoekDAO;
import org.bibly.logic.dao.interfaces.IExemplaarDAO;
import org.bibly.logic.util.ExcelImporter;
import org.biblyfx.Constants;
import org.biblyfx.MainApp;
import org.biblyfx.enums.PaneType;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * The type Beheer items controller.
 */
public class BeheerItemsController implements Initializable {

    private MainApp mainApp;

    /**
     * Gets main app.
     *
     * @return the main app
     */
    public MainApp getMainApp() {
        return mainApp;
    }

    /**
     * Sets main app.
     *
     * @param mainApp the main app
     */
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    /**
     * Go back.
     */
    public void goBack() {
        mainApp.removeTopScene(Constants.beheerStackPane);
    }

    public void initialize(URL arg0, ResourceBundle arg1) {
        // TODO Auto-generated method stub

    }

    /**
     * Show boeken beheer.
     */
    public void showBoekenBeheer() {
        try {
            BeheerBoekenController beheerBoeken = (BeheerBoekenController) mainApp
                    .addSceneContent(Constants.overlayBeheerBoeken,
                            Constants.beheerStackPane, PaneType.AnchorPane);
            beheerBoeken.setMainApp(mainApp);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * Show dVD beheer.
     */
    public void showDVDBeheer() {
        try {
            BeheerDVDController beheerDVD = (BeheerDVDController) mainApp
                    .addSceneContent(Constants.overlayBeheerDVD,
                            Constants.beheerStackPane, PaneType.AnchorPane);
            beheerDVD.setMainApp(mainApp);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * Show cD beheer.
     */
    public void showCDBeheer() {
        try {
            BeheerCDController beheerCD = (BeheerCDController) mainApp
                    .addSceneContent(Constants.overlayBeheerCD,
                            Constants.beheerStackPane, PaneType.AnchorPane);
            beheerCD.setMainApp(mainApp);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private File file;

    /**
     * Importexcel void.
     */
    public void importexcel() {
        FileChooser fileChooser = new FileChooser();

        //Set extension filter
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("XLS files (*.XLS)", "*.XLS");
        fileChooser.getExtensionFilters().add(extFilter);

        //Show open file dialog
        file = fileChooser.showOpenDialog(null);

        ExcelImportService service = new ExcelImportService();
        service.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
            public void handle(WorkerStateEvent t) {
                System.out.println("");
            }
        });
        service.start();
    }

    //Excel import
    private class ExcelImportService extends Service<Void> {

        @Override
        protected Task<Void> createTask() {
            return new Task<Void>() {
                @Override
                protected Void call() throws Exception {
                    ExcelImporter excel = new ExcelImporter();
                    excel.ImportExcel(file.getAbsolutePath(), (IBoekDAO) MainApp.applicationContext.getBean("boekDAO"), (IExemplaarDAO) MainApp.applicationContext.getBean("exemplaarDAO"));


                    return null;
                }
            };
        }
    }

}
