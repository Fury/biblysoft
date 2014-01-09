package org.biblyfx.controllers.items;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import org.bibly.logic.dao.interfaces.IBoekDAO;
import org.bibly.logic.dao.interfaces.IItemDAO;
import org.bibly.logic.models.Item;
import org.biblyfx.Constants;
import org.biblyfx.MainApp;
import org.biblyfx.enums.PaneType;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * The type Zoek boek controller.
 */
public class ZoekBoekController implements Initializable
{

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

    private ObservableList<Item> items;
    private IBoekDAO boekDAO;

    private IItemDAO itemDAO;

    @FXML
    private Tab zoekGUITab;
    @SuppressWarnings("rawtypes")
    @FXML
    private TableView boekenListTable;
    @FXML
    private TextField zoekenTitel;
    @FXML
    private TextField zoekenGenre;
    @FXML
    private TextField zoekenJaar;
    @FXML
    private TextField zoekenUitgeverij;

    @FXML
    private TableColumn<Item, String> id;

    @FXML
    private TableColumn<Item, String> titel;
    @FXML
    private TableColumn<Item, String> uitgaveJaar;
    @FXML
    private TableColumn<Item, String> genre;
    @FXML
    private TableColumn<Item, String> uitgever;
    @FXML
    private TableColumn<Item, String> type;

    @FXML
    private Button zoekenKnop;

    private Scene scene;

    /**
     * Sets scene.
     *
     * @param scene the scene
     */
    public void setScene(Scene scene)
    {
        this.scene = scene;
    }


    /**
     * Instantiates a new Zoek boek controller.
     */
    public ZoekBoekController()
    {
        this.itemDAO = (IItemDAO) MainApp.applicationContext.getBean("itemDAO");
        this.boekDAO = (IBoekDAO) MainApp.applicationContext.getBean("boekDAO");
    }

    /**
     * Instantiates a new Zoek boek controller.
     *
     * @param boekDAO the boek dAO
     */
    public ZoekBoekController(IBoekDAO boekDAO)
    {
        this.boekDAO = boekDAO;

    }


    /**
     * Show details.
     *
     * @param item the item
     */
    public void showDetails(Object item)
    {
        try
        {
            BoekDetailsOverlayController initializable = (BoekDetailsOverlayController) mainApp.addSceneContent(Constants.overlayBoekDetails, Constants.zoekenStackPane, PaneType.AnchorPane);
            initializable.setMainApp(getMainApp());
            initializable.setItem((Item) item);
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    /**
     * Zoek items.
     */
    public void zoekItems()
    {
        ZoekitemsService service = new ZoekitemsService();
        System.out.println("Gogo");
        service.start();
        System.out.println("Launched&Complete");

    }


    @SuppressWarnings("unchecked")
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        // Initialize the person table
        id.setCellValueFactory(new PropertyValueFactory<Item, String>("itemID"));
        titel.setCellValueFactory(new PropertyValueFactory<Item, String>(
                "titel"));
        genre.setCellValueFactory(new PropertyValueFactory<Item, String>(
                "genre"));
        uitgaveJaar.setCellValueFactory(new PropertyValueFactory<Item, String>(
                "uitgaveJaar"));

        uitgever.setCellValueFactory(new PropertyValueFactory<Item, String>(
                "uitgever"));
        type.setCellValueFactory(new PropertyValueFactory<Item, String>(
                "type"));

        items = FXCollections.observableList(itemDAO.findAll());
        boekenListTable.setItems(items);

        for (Item object : items)
        {
            object.setType(object.getClass().getSimpleName().toString());
        }

        boekenListTable.setOnMouseClicked(new EventHandler<MouseEvent>()
        {
            public void handle(MouseEvent mouseEvent)
            {
                if (mouseEvent.getButton().equals(MouseButton.PRIMARY))
                {
                    if (mouseEvent.getClickCount() == 2)
                    {
                        TableView c = (TableView) mouseEvent.getSource();
                        System.out.println(c.getSelectionModel().getSelectedItem());
                        showDetails(c.getSelectionModel().getSelectedItem());
                    }
                }
            }
        });
    }



    private class ZoekitemsService extends Service<Void>
    {

        @Override
        protected Task<Void> createTask()
        {
            return new Task<Void>()
            {
                @SuppressWarnings("unchecked")
                @Override
                protected Void call() throws Exception
                {
                    Item item = new Item();

                    item.setTitel(zoekenTitel.getText());
                    item.setUitgaveJaar(zoekenJaar.getText());
                    item.setGenre(zoekenGenre.getText());

                    item.setUitgever(zoekenUitgeverij.getText());
                    items = FXCollections.observableList(itemDAO.FindByExample(item));

                    for (Item object : items)
                    {
                        object.setType(object.getClass().getSimpleName().toString());
                    }
                    boekenListTable.setItems(items);
                    return null;
                }
            };
        }
    }
    
	public void goBack() {
		mainApp.removeTopScene(Constants.zoekenStackPane);
	}
}
