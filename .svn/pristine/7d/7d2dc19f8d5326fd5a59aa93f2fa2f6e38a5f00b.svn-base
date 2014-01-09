package org.biblyfx.controllers;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import org.apache.commons.lang.time.DateUtils;
import org.bibly.logic.dao.interfaces.ILidDAO;
import org.bibly.logic.dao.interfaces.IUitleningDAO;
import org.bibly.logic.models.Lid;
import org.bibly.logic.models.Uitlening;
import org.bibly.logic.models.enums.PersoonType;
import org.biblyfx.Constants;
import org.biblyfx.MainApp;
import org.biblyfx.controllers.interfaces.YesNoInterface;
import org.biblyfx.enums.PaneType;
import org.joda.time.DateTime;
import org.joda.time.Days;

import java.util.Date;
import java.util.List;

public class LedenController implements YesNoInterface
{

    private MainApp mainApp;
    private Lid lid = null;
    private ILidDAO lidDAO = (ILidDAO) MainApp.applicationContext
            .getBean("lidDAO");
    @FXML
    private TextField txtLidnummer;
    @FXML
    private Label lblNaam;
    @FXML
    private Label lblVoornaam;
    @FXML
    private Label lblSchuld;
    @FXML
    private Label lblVervaldatum;
    @FXML
    private Button btnNieuw;
    @FXML
    private Button btnZoek;
    @FXML
    private Button btnBetaal;
    @FXML
    private Button btnAanpassen;
    @FXML
    private Button btnVerwijderen;
    private IUitleningDAO uitleningDAO;

    public MainApp getMainApp()
    {
        return mainApp;
    }

    public void setMainApp(MainApp mainApp)
    {
        this.mainApp = mainApp;
    }

    public TextField getTxtLidnummer()
    {
        return txtLidnummer;
    }

    public void setTxtLidnummer(TextField txtLidnummer)
    {
        this.txtLidnummer = txtLidnummer;
    }

    public void initialize()
    {
        btnZoek.setOnMouseClicked(new EventHandler<MouseEvent>()
        {

            public void handle(MouseEvent arg0)
            {
                if (arg0.getButton().equals(MouseButton.PRIMARY))
                {
                    if (Long.parseLong(txtLidnummer.getText()) >= 0
                            && txtLidnummer.getText() != null
                            && txtLidnummer.getText() != "")
                    {
                        lid = lidDAO.findById(Long.parseLong(getTxtLidnummer()
                                .getText()));
                        lblNaam.setText(lid.getAchternaam());
                        lblVoornaam.setText(lid.getVoornaam());
                        lblSchuld.setText("" + schuldBerekenen());
                        lblVervaldatum.setText(lid.getSubscriptieEindDatum()
                                .toString());

                    }
                }
            }
        });
    }

    private double schuldBerekenen()
    {
        double schulden = 0;
        Date date = new Date();
        uitleningDAO = (IUitleningDAO) getMainApp().applicationContext.getBean("uitleningDAO");
        List<Uitlening> lijst2 = uitleningDAO.findByLid(lid);

        for (Uitlening u : lijst2)
        {
            Date uitleenEindDatum = DateUtils.addDays(u.getUitleenStartDatum(),
                    21);

            int days = Days.daysBetween(new DateTime(uitleenEindDatum),
                    new DateTime(date)).getDays();
            if (days > 0)
            {
                schulden = schulden + ((double) days * 0.5);
            }

        }
        return schulden;

    }

    public void showNieuwLidAanmaken()
    {
        try
        {
            NieuwLidAanmakenController nieuwLidAanmaken = (NieuwLidAanmakenController) mainApp
                    .addSceneContent(Constants.overlayLidAanmaken,
                            Constants.ledenStackPane, PaneType.AnchorPane);
            nieuwLidAanmaken.setMainApp(getMainApp());

        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public void showAanpassenLid()
    {
        try
        {
            LidAanpassenController lidAanpassen = (LidAanpassenController) mainApp
                    .addSceneContent(Constants.overlayLidAanpassen,
                            Constants.ledenStackPane, PaneType.AnchorPane);
            lidAanpassen.setMainApp(mainApp);
            lidAanpassen.setLid(this.getLid());
            lidAanpassen.vulAllesIn();

        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    private Lid getLid()
    {
        return lid;
    }

    public void verwijderLid()
    {
        if (lid == null)
            return;

        showWarning("Weet u zeker dat u dit lid wil verwijderen");
    }

    public void showWarning(String message)
    {
        try
        {
            Warning2Controller warningController = (Warning2Controller) mainApp.addSceneContent(Constants.warningYesNoScreen, Constants.ledenStackPane, PaneType.AnchorPane);
            warningController.setMainApp(getMainApp());
            warningController.setMessage(message);
            warningController.setStackPane(Constants.ledenStackPane);
            warningController.setRequest(this);

        } catch (Exception e)
        {
            e.printStackTrace();
        }

    }

    public void klikJa()
    {
        if (lid == null)
            return;

        lid.setPermissie(PersoonType.AFGESLOTEN);

        lidDAO.save(lid);
    }

    public void klikNee()
    {

    }


}