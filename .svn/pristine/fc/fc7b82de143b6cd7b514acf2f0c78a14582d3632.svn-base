package org.biblyfx.controllers.items;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import org.bibly.logic.models.Boek;
import org.bibly.logic.models.Cd;
import org.bibly.logic.models.Dvd;
import org.bibly.logic.models.Item;
import org.biblyfx.Constants;
import org.biblyfx.MainApp;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Dries on 11/29/13.
 */
public class BoekDetailsOverlayController implements Initializable
{
    @FXML
    public Label lblAuteur;
    @FXML
    public Label lblISBN;
    @FXML
    public Label lblUitgever;
    @FXML
    public Label lblGenre;
    @FXML
    public Label lblJaar;
    @FXML
    public Label lblOrigineel;
    private Item item;
    @FXML
    private Label lblTitel;

    private MainApp mainApp;

    public Item getItem()
    {
        return item;
    }

    public void setItem(Item item)
    {
        lblTitel.setText(item.getTitel());
        lblUitgever.setText(item.getUitgever());
        lblGenre.setText(item.getGenre());
        lblJaar.setText(item.getUitgaveJaar());

        if (item.getClass() == Boek.class)
        {
            lblAuteur.setText(((Boek) item).getAuteur());
        }
        /*
        else if (item.getClass() == Dvd.class)
        {

            lblAuteur.setText(((Dvd) item).getActeurs().toString());
        } else if (item.getClass() == Cd.class)
        {
            lblAuteur.setText(((Cd) item).getArtiest());
        }
        */
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

    public void goBack()
    {
        mainApp.removeTopScene(Constants.zoekenStackPane);
    }
}
