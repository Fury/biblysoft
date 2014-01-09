package org.biblyfx.controllers;

import calendar.DatePicker;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;

import org.bibly.logic.dao.interfaces.IExemplaarDAO;
import org.bibly.logic.dao.interfaces.ILidDAO;
import org.bibly.logic.dao.interfaces.IUitleningDAO;
import org.bibly.logic.dao.interfaces.IVergaderruimteDAO;
import org.bibly.logic.models.Adres;
import org.bibly.logic.models.ItemExemplaar;
import org.bibly.logic.models.Lid;
import org.bibly.logic.models.Uitlening;
import org.bibly.logic.models.UitleningDetail;
import org.bibly.logic.models.Vergaderruimte;
import org.bibly.logic.models.enums.PersoonType;
import org.biblyfx.MainApp;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Set;

/**
 * 
 * This controller handles all action performed in the GUI 'Verhuur Vergaderruimtes'.
 *
 */
public class VergaderruimteController implements Initializable
{
    private IVergaderruimteDAO vergaderruimteDAO;
    private ILidDAO lidDAO;
    private IUitleningDAO uitleningDAO;
    private IExemplaarDAO exemplaarDAO;
    
    private DatePicker d = new DatePicker(Locale.ENGLISH);
    private long id = 0;

    @FXML
    private ComboBox<String> zaalLijst;
    @FXML
    private HBox hiredate;
    @FXML
    private TextField txtAantal;
    @FXML
    private TextField txtNaam;
    @FXML
    private TextField txtVoornaam;
    @FXML
    private TextField txtEmail;
    @FXML
    private TextField txtGsm;
    @FXML
    private TextField txtStraat;
    @FXML
    private TextField txtNummer;
    @FXML
    private TextField txtPostcode;
    @FXML
    private TextField txtGemeente;
    
    
    public ItemExemplaar getVergaderruimte()
    {
    	
    	exemplaarDAO = (IExemplaarDAO) MainApp.applicationContext.getBean("exemplaarDAO");
    	if(zaalLijst.getValue() != null && zaalLijst.getValue() != "Niets beschikbaar!") {
    		String[] ruimte = zaalLijst.getValue().split(" - ");
    		try {
    			ItemExemplaar exemplaar = exemplaarDAO.findById(Long.parseLong(ruimte[0]));
    			return exemplaar;
    		} catch(Exception e) {
    			e.printStackTrace();
    			return null;
    		}
    	}
		return null;
    }


    public ObservableList<String> getVergaderruimtesLijst(int maxPers) throws Exception
    {
        try
        {
            ArrayList<String> vRuimtes = new ArrayList<String>();

            vergaderruimteDAO = (IVergaderruimteDAO) MainApp.applicationContext.getBean("vergaderruimteDAO");

            List<Vergaderruimte> ruimtes = vergaderruimteDAO.findByMaxPersonen(maxPers);
            if (ruimtes.size() == 0)
            {
                vRuimtes.add("Niets beschikbaar!");
            } else
            {
                for (Vergaderruimte v : ruimtes)
                {
                	Set<ItemExemplaar> ex = v.getExemplaars();
                	if(ex.size() == 0) {
                		vRuimtes.add("Niets beschikbaar");
                	} else {
                		for(ItemExemplaar ie : ex) {
                			vRuimtes.add(ie.getExemplaarID()+" - "+v.getAdres().getStraatNaam() + " " + v.getAdres().getHuisNummer() + " - &euro;" + v.getPrijs());
                		}
                	}
                }
            }
            ObservableList<String> options = FXCollections.observableArrayList(vRuimtes);

            return options;
        } catch (Exception e)
        {
            throw new Exception(e);
        }
    }
    
    /**
     * Gets and sets all data for the person who wants to hire the 'vergaderruimte'
     * Get with findById, since all users in the system can hire, but not lend.
     * @param id is the identifier for a specific member.
     */
    public void setHuurderData(long id) {
    	lidDAO = (ILidDAO) MainApp.applicationContext.getBean("lidDAO");
    	try {
    		if(id > 0) {
    			this.id = id;
    			Lid huurder = lidDAO.findById(id);
    			disableFields(true);
    			
    			txtNaam.setText(huurder.getAchternaam());
    			txtVoornaam.setText(huurder.getVoornaam());
    			txtEmail.setText(huurder.getEmail());
    			txtGsm.setText(huurder.getGsmNummer());
    			txtStraat.setText(huurder.getAdres().getStraatNaam());
    			txtNummer.setText(""+huurder.getAdres().getHuisNummer());
    			txtGemeente.setText(huurder.getAdres().getGemeente().getGemeente());
    			txtPostcode.setText(""+huurder.getAdres().getGemeente().getPostcode());
    			
    		} else {
    			System.out.println("The ID supplied is not a valid ID");
    		}
    		
    	} catch(Exception e) {
    		System.out.println(e.getMessage());
    	}
    }
    
    /**
     * Enable or disable all fields except the name field, which is needed for further actions.
     * Don't disable the name field at any time, otherwise no further actions can happen in the GUI
     * @param b determines the state: enabled(false) or disabled(true)
     */
    public void disableFields(boolean b) {
    	txtVoornaam.setDisable(b);
		txtEmail.setDisable(b);
		txtGsm.setDisable(b);
		txtStraat.setDisable(b);
		txtNummer.setDisable(b);
		txtGemeente.setDisable(b);
		txtPostcode.setDisable(b);
    }
    
    public void saveVerhuur() {
    	
    	uitleningDAO = (IUitleningDAO) MainApp.applicationContext.getBean("uitleningDAO");
    	
    	Lid lid;
    	
    	if(this.id != 0) {
    		lid = lidDAO.findById(this.id);
    	}
    	else {
    		lid = new Lid(new Adres(), PersoonType.HUURDER, new Date(), txtVoornaam.getText(), txtNaam.getText(), "", txtEmail.getText(), txtGsm.getText(), false);
    		lid = lidDAO.save(lid);
    	}
    	
    	Uitlening uitlening = new Uitlening();
        uitlening.setUitleenStartDatum(d.getSelectedDate());
        uitlening.setUitgeleendDoor(MainApp.personeelLoggedIn);
        uitlening.setUitlener(lid);
        
        UitleningDetail uitleenDetail = new UitleningDetail();
        uitleenDetail.setExemplaar(this.getVergaderruimte());
        uitleenDetail.setUitlening(uitlening);
        uitleningDAO.save(uitlening);
    	
    }


    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        /**
         * loading
         */
        try
        {
        	
            d.setDateFormat(new SimpleDateFormat("yyyy-MM-dd"));
            d.getCalendarView().todayButtonTextProperty().set("Today");
            d.getCalendarView().setShowWeeks(false);
            d.getStylesheets().add("/styles/calendar.css");
        } catch (Exception e)
        {
            System.out.println(e.getMessage());
        }

        /**
         * show datepicker
         */
        hiredate.getChildren().add(d);

        /**
         * componenten disabelen
         */
        zaalLijst.setDisable(true);
        hiredate.getChildren().get(0).setDisable(true);

        /**
         * Handlers
         */
        txtAantal.setOnKeyPressed(new EventHandler<KeyEvent>()
        {

            public void handle(KeyEvent arg0)
            {
                if (arg0.getCode() == KeyCode.ENTER)
                {
                    try
                    {
                        if (Integer.parseInt(txtAantal.getText()) > 0)
                        {
                            try
                            {
                                zaalLijst.setDisable(false);
                                zaalLijst.getItems().clear();
                                zaalLijst.setItems(getVergaderruimtesLijst(Integer.parseInt(txtAantal.getText())));
                            } catch (NumberFormatException ne)
                            {
                                System.out.println(ne.getMessage());
                                ne.printStackTrace();
                            }
                        }
                    } catch (Exception e)
                    {
                        e.printStackTrace();
                    }
                }
            }
        });
        
        txtNaam.setOnKeyPressed(new EventHandler<KeyEvent>() {
            public void handle(KeyEvent arg0)
            {
                if (arg0.getCode() == KeyCode.ENTER)
                {
                    try
                    {
                    	if(txtNaam.getText() != "" && txtNaam.getText().trim().substring(0,1) != " ") {
	                    	String firstChar = txtNaam.getText().substring(0,1);
	                    		try {
	                    			long id = Long.parseLong(txtNaam.getText());
	                    			setHuurderData(id);
	                    			
	                    		} catch(NumberFormatException ne) {
	                    			throw new NumberFormatException(ne.getMessage());
	                    		}
                    	} else {
                    		System.out.println("Empty textfield");
                    	}
                    } catch(Exception e) {
                    	
                    }
                }
                else if(arg0.getCode() == KeyCode.BACK_SPACE || arg0.getCode() == KeyCode.DELETE) {
                	if(id > 0) {
                		disableFields(false);
                		id = 0;
                	}
                }
            }
        });
    }


}
