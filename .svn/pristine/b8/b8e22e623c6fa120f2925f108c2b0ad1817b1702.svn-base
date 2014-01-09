package org.biblyfx;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import org.apache.log4j.chainsaw.Main;
import org.bibly.logic.models.Personeel;
import org.biblyfx.Util.FakeDataGenerator;
import org.biblyfx.controllers.InloggenController;
import org.biblyfx.controllers.RootPaneController;
import org.biblyfx.enums.PaneType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;
import java.io.InputStream;

/**
 * The type Main app.
 */
public class MainApp extends Application
{

    private static final Logger log = LoggerFactory.getLogger(MainApp.class);
    /**
     * The constant personeelLoggedIn.
     */
    public static Personeel personeelLoggedIn;
    /**
     * The constant applicationContext.
     */
    public static ApplicationContext applicationContext;
    /**
     * The Stage.
     */
    public Stage stage;
    /**
     * The Current scene.
     */
    public Scene currentScene;
    @FXML
    private Tab zoekGuiTab;

    private boolean generate = false;

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     * @throws Exception the exception
     */
    public static void main(String[] args) throws Exception
    {
        launch(args);
    }

    public void start(Stage stage) throws Exception
    {

        applicationContext = new ClassPathXmlApplicationContext(
                "config/SpringContext.xml");
        if (generate)
        {
            FakeDataGenerator fakeDataGenerator = new FakeDataGenerator();
            fakeDataGenerator.generateFakeData();
        }
        log.info("Starting Bibly application");

        String fxml = "/fxml/LogIn.fxml";
        FXMLLoader loader = new FXMLLoader();
        InputStream in = null;
        loader.setBuilderFactory(new JavaFXBuilderFactory());
        loader.setLocation(Main.class.getResource(fxml));
        BorderPane page;
        try
        {
            in = Main.class.getResourceAsStream(fxml);
            page = (BorderPane) loader.load(in);
        } finally
        {
            if (in != null)
            {
                in.close();
            }
        }
        InloggenController inloggenController = loader.getController();
        inloggenController.setMainApp(this);
        Scene scene = new Scene(page, 1024, 768);
        scene.getStylesheets().add("styles/styles.css");

        stage.setTitle("Bibly Application");
        stage.setScene(scene);
        this.stage = stage;
        currentScene = scene;
        stage.setScene(scene);
        stage.show();

        inloggenController.startFireWorks();
    }


    /**
     * Change scene log in to stage.
     *
     * @throws IOException the iO exception
     */
    public RootPaneController changeSceneLogInToStage() throws IOException
    {

        try
        {
            RootPaneController stage = (RootPaneController) replaceSceneContentLogin("/fxml/RootPane.fxml");

            return stage;

        } catch (Exception e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Change scene stage to log in.
     *
     * @throws IOException the iO exception
     */
    public void changeSceneStageToLogIn() throws IOException
    {

        try
        {
            InloggenController stage = (InloggenController) replaceSceneContentLogin("/fxml/LogIn.fxml");
            stage.setMainApp(this);
        } catch (Exception e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        stage.getScene().getStylesheets().add("styles/styles.css");
        currentScene = stage.getScene();
        personeelLoggedIn = null;
        stage.sizeToScene();
        stage.setFullScreen(false);
        stage.setFullScreen(true);
        stage.show();
    }

    /**
     * Add scene content.
     *
     * @param fxml     the fxml
     * @param id       the id
     * @param paneType the pane type
     * @return the initializable
     * @throws Exception the exception
     */
    public Initializable addSceneContent(String fxml, String id, PaneType paneType) throws Exception
    {
        FXMLLoader loader = new FXMLLoader();
        InputStream in = null;
        loader.setBuilderFactory(new JavaFXBuilderFactory());
        loader.setLocation(Main.class.getResource(fxml));
        Node page;
        try
        {
            in = Main.class.getResourceAsStream(fxml);
            switch (paneType)
            {
                case AnchorPane:
                    page = (AnchorPane) loader.load(in);
                    break;
                case BorderPane:
                    page = (BorderPane) loader.load(in);
                    break;
                default:
                    throw new IOException();
            }
        } finally
        {
            if (in != null)
            {
                in.close();
            }
        }
        StackPane stackPane = (StackPane) ((Node) currentScene.getRoot().lookup("#" + id));
        stackPane.getChildren().add(page);
        return (Initializable) loader.getController();
    }

    /**
     * Replace stage to login
     *
     * @param fxml the fxml
     * @return the initializable
     * @throws Exception the exception
     */
    private Initializable replaceSceneContentLogin(String fxml) throws Exception
    {
        FXMLLoader loader = new FXMLLoader();
        InputStream in = null;
        loader.setBuilderFactory(new JavaFXBuilderFactory());
        loader.setLocation(Main.class.getResource(fxml));
        BorderPane page;
        try
        {
            in = Main.class.getResourceAsStream(fxml);
            page = (BorderPane) loader.load(in);
        } finally
        {
            if (in != null)
            {
                in.close();
            }
        }

        Scene scene = new Scene(page);
        stage.setScene(scene);
        currentScene = stage.getScene();
        stage.setFullScreen(false);
        stage.setFullScreen(true);
        return (Initializable) loader.getController();
    }

    /**
     * Change stage to log in.
     *
     * @param inloggenController the inloggen controller
     * @throws IOException the iO exception
     */
    public void changeStageToLogIn(InloggenController inloggenController)
            throws IOException
    {

        StackPane stackPane;
        stackPane = (StackPane) ((Node) currentScene.getRoot().lookup(
                "#logInStackPane"));
        stackPane.getChildren().remove(1);
    }

    /**
     * Remove top scene.
     *
     * @param id the id
     */
    public void removeTopScene(String id)
    {
        StackPane stackPane;
        stackPane = (StackPane) ((Node) currentScene.getRoot().lookup("#" + id));
        stackPane.getChildren().remove(stackPane.getChildren().size() - 1);
    }

    public void setPersoneel(Personeel personeel)
    {
        this.personeelLoggedIn = personeel;

    }
}
