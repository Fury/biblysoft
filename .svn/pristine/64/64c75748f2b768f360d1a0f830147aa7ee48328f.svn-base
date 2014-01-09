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
import org.bibly.logic.dao.interfaces.IDvdDAO;
import org.bibly.logic.dao.interfaces.IItemDAO;
import org.bibly.logic.models.Dvd;
import org.bibly.logic.models.Item;
import org.biblyfx.Constants;
import org.biblyfx.MainApp;
import org.biblyfx.enums.PaneType;

import java.net.URL;
import java.util.ResourceBundle;


public class ZoekDvdController implements Initializable
{

    private MainApp mainApp;

    public MainApp getMainApp()
    {
        return mainApp;
    }

    public void setMainApp(MainApp mainApp)
    {
        this.mainApp = mainApp;
    }

    private ObservableList<Dvd> items;
    private IDvdDAO dvdDAO;

    private IItemDAO itemDAO;

    @FXML
    private Tab zoekGUITab;
    @SuppressWarnings("rawtypes")
    @FXML
    private TableView dvdsListTable;
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
    private Button zoekenKnop;

    private Scene scene;

    public void setScene(Scene scene)
    {
        this.scene = scene;
    }


    public ZoekDvdController()
    {
        this.itemDAO = (IItemDAO) MainApp.applicationContext.getBean("itemDAO");
        this.dvdDAO = (IDvdDAO) MainApp.applicationContext.getBean("dvdDAO");
    }

    public ZoekDvdController(IDvdDAO dvdDAO)
    {
        this.dvdDAO = dvdDAO;
    }

    public void showDetails(Object item)
    {
        try
        {
            DvdDetailsOverlayController initializable = (DvdDetailsOverlayController) mainApp.addSceneContent(Constants.overlayDVDDetails, Constants.zoekenStackPane, PaneType.AnchorPane);
            initializable.setMainApp(getMainApp());
            initializable.setItem((Item) item);
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }

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

        items = FXCollections.observableList(dvdDAO.findAll());
        dvdsListTable.setItems(items);
        
        for (Item object : items)
        {
            object.setType(object.getClass().getSimpleName().toString());
        }

        dvdsListTable.setOnMouseClicked(new EventHandler<MouseEvent>()
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
                    Dvd item = new Dvd();

                    item.setTitel(zoekenTitel.getText());
                    item.setUitgaveJaar(zoekenJaar.getText());
                    item.setGenre(zoekenGenre.getText());

                    item.setUitgever(zoekenUitgeverij.getText());
                    items = FXCollections.observableList(dvdDAO.FindByExample(item));

                    for (Item object : items)
                    {
                        object.setType(object.getClass().getSimpleName().toString());
                    }
                    
                    dvdsListTable.setItems(items);
                    return null;
                }
            };
        }
    }
    
	public void goBack() {
		mainApp.removeTopScene(Constants.zoekenStackPane);
	}
}
