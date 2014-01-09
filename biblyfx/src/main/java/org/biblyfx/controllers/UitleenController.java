package org.biblyfx.controllers;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.bibly.logic.UitleenBuilder;
import org.bibly.logic.dao.interfaces.IExemplaarDAO;
import org.bibly.logic.dao.interfaces.ILidDAO;
import org.bibly.logic.dao.interfaces.IUitleningDAO;
import org.bibly.logic.dao.interfaces.IUitleningDetailsDAO;
import org.bibly.logic.exceptions.BibliothecarisException;
import org.bibly.logic.exceptions.ItemException;
import org.bibly.logic.exceptions.LidException;
import org.bibly.logic.exceptions.LidPermissionException;
import org.bibly.logic.models.ItemExemplaar;
import org.bibly.logic.models.Lid;
import org.bibly.logic.models.Uitlening;
import org.biblyfx.Constants;
import org.biblyfx.MainApp;
import org.biblyfx.controllers.items.tempExemplaar;
import org.biblyfx.enums.PaneType;
import org.joda.time.DateTime;

import java.net.URL;
import java.util.ResourceBundle;


/**
 * The type Uitleen controller.
 */
public class UitleenController implements Initializable
{
    /**
     * The Lidnummer.
     */
    @FXML
    public TextField Lidnummer;
    private Lid lid;
    private UitleenBuilder uitleenBuilder;
    private ILidDAO lidDAO = (ILidDAO) MainApp.applicationContext.getBean("lidDAO");
    private IUitleningDAO uitleningDAO = (IUitleningDAO) MainApp.applicationContext.getBean("uitleningDAO");
    private IUitleningDetailsDAO uitleningDetailsDAO = (IUitleningDetailsDAO) MainApp.applicationContext.getBean("uitleningdetailsDAO");
    private IExemplaarDAO exemplaarDAO = (IExemplaarDAO) MainApp.applicationContext.getBean("exemplaarDAO");
    @FXML
    private TextField lidnummer;
    @FXML
    private Label lblNaam;
    @FXML
    private Label lblVoornaam;
    @FXML
    private Label lblSchuld;
    @FXML
    private Label lblVervaldatum;
    @FXML
    private Label lblAantal;
    @FXML
    private TextField Artikelnummer;
    @FXML
    private TableView boekenLijst;
    @FXML
    private TableColumn<tempExemplaar, String> id;
    @FXML
    private TableColumn<tempExemplaar, String> titel;
    @FXML
    private TableColumn<tempExemplaar, String> auteur;
    @FXML
    private TableColumn<tempExemplaar, String> startdatum;
    @FXML
    private TableColumn<tempExemplaar, String> stopdatum;
    @FXML
    private Button btnVerlengen;
    @FXML
    private Button btnKlaar;
    private ObservableList<tempExemplaar> exemplaren;
    private MainApp mainApp;

    /**
     * Gets main app.
     *
     * @return the main app
     */
    public MainApp getMainApp()
    {
        return mainApp;
    }

    /**
     * Sets main app.
     *
     * @param mainApp the main app
     */
    public void setMainApp(MainApp mainApp)
    {
        this.mainApp = mainApp;
    }

    /**
     * Geef lid.
     *
     * @param lidNr the lid nr
     * @return the boolean
     */
    public boolean geefLid(long lidNr)
    {
        this.lid = lidDAO.findById(lidNr);

        if (lid != null)
        {
            this.uitleenBuilder = new UitleenBuilder(MainApp.personeelLoggedIn, lid);
            uitleenBuilder.setUitleningDAO(this.uitleningDAO);
            uitleenBuilder.setUitleningdetailsDAO(this.uitleningDetailsDAO);
            uitleenBuilder.setExemplaarDAO(this.exemplaarDAO);
            return true;
        } else
        {
            return false;
        }
    }

    /**
     * Lid initialize.
     */
    public void lidInitialize()
    {
        long x = 0;
        try
        {
            x = Long.valueOf(Lidnummer.getText().toString());
        } catch (NumberFormatException ex)
        {
            showPopup("Geldig lidID ingeven alstublieft.");
        }
        if (geefLid(x))
        {
            if (lid != null)
            {
                lblNaam.setText(lid.getAchternaam());
                lblVoornaam.setText(lid.getVoornaam());
                lblSchuld.setText("Under construction");
                DateTime date = new DateTime(lid.getSubscriptieEindDatum());
                lblVervaldatum.setText(date.toLocalDate().toString());
                Artikelnummer.setEditable(true);
            }
        } else
        {
            showPopup("Geen lid gevonden.");
        }
    }

    private void uitleningInitialize()
    {

    }

    public void initialize(URL url, ResourceBundle resourceBundle)
    {

        // Initialize the person table
        id.setCellValueFactory(new PropertyValueFactory<tempExemplaar, String>("itemID"));
        titel.setCellValueFactory(new PropertyValueFactory<tempExemplaar, String>("titel"));
        auteur.setCellValueFactory(new PropertyValueFactory<tempExemplaar, String>("auteur"));
        startdatum.setCellValueFactory(new PropertyValueFactory<tempExemplaar, String>("startDatum"));
        stopdatum.setCellValueFactory(new PropertyValueFactory<tempExemplaar, String>("eindDatum"));
        boekenLijst.setItems(FXCollections.observableArrayList());

        Artikelnummer.setEditable(false);
    }

    /**
     * Add exemplaar.
     *
     * @param actionEvent the action event
     */
    public void addExemplaar(ActionEvent actionEvent)
    {
        if (lid == null)
            return;
        String artikelnr = Artikelnummer.getText();
        if (artikelnr == "")
            return;
        ItemExemplaar exemplaar;
        try
        {
            long id = Long.valueOf(artikelnr);
            exemplaar = exemplaarDAO.findById(id);
        } catch (NumberFormatException ex)
        {
            return;
        }
        if (exemplaar == null)
            return;
        if (exemplaar.getItem() == null)
            return;

        ObservableList<tempExemplaar> items = boekenLijst.getItems();
        for (tempExemplaar temp : items)
        {
            if (temp.itemID == String.valueOf(exemplaar.getItem().getItemID()))
            {
                return;
            }
        }

        try
        {
            uitleenBuilder.addExemplaar(exemplaar);
            boekenLijst.getItems().add(new tempExemplaar(exemplaar));
        } catch (ItemException e)
        {
            showPopup(e.getMessage());
        }


    }

    /**
     * Maak uitlening.
     *
     * @param actionEvent the action event
     */
    public void maakUitlening(ActionEvent actionEvent)
    {
        try
        {
            Uitlening uitlening = uitleenBuilder.commitUitlening();
            if (uitlening == null)
                return;

            uitleenBuilder = null;
            Lidnummer.setText("");
            lid = null;
            boekenLijst.getItems().removeAll();


            lblNaam.setText("");
            lblVoornaam.setText("");
            lblSchuld.setText("");
            lblVervaldatum.setText("");
            Artikelnummer.setText("");
            Artikelnummer.setEditable(false);
            System.out.println("Commit gelukt");
            System.out.println(uitlening.toString());
            showPopup("Uitlening voltooid");

        } catch (LidException e)
        {
            e.printStackTrace();
        } catch (ItemException e)
        {
            e.printStackTrace();
        } catch (LidPermissionException e)
        {
            showPopup("Probleem met het lid.");
            e.printStackTrace();
        } catch (BibliothecarisException e)
        {
            e.printStackTrace();
        }


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
            WarningController warningController = (WarningController) mainApp.addSceneContent(Constants.warningScreen, Constants.uitleenStackPane, PaneType.AnchorPane);
            warningController.setMainApp(getMainApp());
            warningController.setMessage(message);
            warningController.setStackPane(Constants.uitleenStackPane);
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}

