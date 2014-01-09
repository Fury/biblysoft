package org.biblyfx.controllers.charts;

import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import org.bibly.logic.dao.interfaces.IExemplaarDAO;
import org.bibly.logic.dao.interfaces.IUitleningDetailsDAO;
import org.bibly.logic.models.Item;
import org.bibly.logic.models.ItemExemplaar;
import org.bibly.logic.models.Lid;
import org.bibly.logic.models.UitleningDetail;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class GrootsteLezerPerGenre
{
    private IUitleningDetailsDAO uitleningdetailsDAO;
    private IExemplaarDAO exemplaarDAO;

    private List<UitleningDetail> uitleningDetails;
	
	Map<String, Map<Lid, Integer>> map = new HashMap<String, Map<Lid, Integer>>();
	Map<String, Temp> results = new HashMap<String, Temp>();
	
	private class Temp
	{
		private Lid lid;
		private int aantal;
		
		public Temp(Lid lid, int aantal) {
			super();
			this.lid = lid;
			this.aantal = aantal;
		}
		
		public Lid getLid(){return lid;}
		public void setLid(Lid lid){this.lid = lid;}
		public int getAantal(){return aantal;}
		public void setAantal(int aantal){this.aantal = aantal;}		
	}
	
	
	private TableView<GrootsteLezer> table = new TableView<GrootsteLezer>();
	private ObservableList<GrootsteLezer> data = FXCollections.observableArrayList();

    @Transactional
    public void getGrootsteLezerPerGenre()
    {
    	uitleningDetails = uitleningdetailsDAO.findAll();
    	/*
    	 * In deze for-lus word per genre en vervolgens per lid geteld, hoeveel keer ze in dat genre hebben uitgeleend.
    	 * Dit heeft als doel een prijs te kunnen uitrijken aan de grootste lezers van een specifiek genre.
    	 */
    	
    	for(UitleningDetail ud: uitleningDetails)
		{
            ItemExemplaar exemplaar = ud.getExemplaar();
            Item item = exemplaar.getItem();
            String genre = ud.getExemplaar().getItem().getGenre();
            genre = genre.toLowerCase();
			
			Lid lid = ud.getUitlening().getUitlener();
			
			if(map.containsKey(genre))
			{
				// Eerst word er voor elk lid geteld en gekeken of die al bestaat zo ja, bijgeteld
				Map<Lid, Integer> innerMap = map.get(genre);
				
				if(innerMap.containsKey(lid))
				{
					innerMap.put(lid, innerMap.get(lid) + 1);
				}
				else
				{
					innerMap.put(lid, 1);
				}
				
				//Hierna kijken we of hij de 'grootste' lezer is en slaan dit op in een aparte tabel
				Temp temp = results.get(genre);
				
				if(innerMap.get(lid) > temp.getAantal())
				{
					temp.setLid(lid);
					temp.setAantal(innerMap.get(lid));
					results.put(genre, temp);
				}
			}
			else
			{
				map.put(genre, new HashMap<Lid,Integer>());
				map.get(genre).put(lid, 1);
				
				results.put(genre, new Temp(lid, 1));
			}
    	}
    	
    	//Nu schrijven we de data over in een tabel leesbaar door de TableView 
    	for(Entry<String, Temp> entry : results.entrySet())
    	{
    		String lidnr = String.valueOf(entry.getValue().getLid().getPersoonID());
    		String naam = entry.getValue().getLid().getVoornaam() + " " + entry.getValue().getLid().getAchternaam();
    		String genre = entry.getKey();
    		String aantal = String.valueOf(entry.getValue().getAantal());
    		
    		GrootsteLezer g = new GrootsteLezer(lidnr, naam, genre, aantal);
    		
    		data.add(g);
    	}  	
    }
    
    public VBox start() 
    {
    	ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("config/SpringContext.xml");

        uitleningdetailsDAO = (IUitleningDetailsDAO) applicationContext.getBean("uitleningdetailsDAO");
        exemplaarDAO = (IExemplaarDAO) applicationContext.getBean("exemplaarDAO");
        this.getGrootsteLezerPerGenre();
    	/*
        Scene scene = new Scene(new Group());
        stage.setTitle("Grootste lezer per genre");
        stage.setWidth(1000);
        stage.setHeight(500);
		*/
        final Label label = new Label("Grootste lezer per genre");
        label.setFont(new Font("Arial", 20));

        table.setEditable(true);
        table.setMinWidth(400);

        TableColumn lidnrCol = new TableColumn("Lidnr");
        lidnrCol.setCellValueFactory(new PropertyValueFactory<GrootsteLezer, String>("Lidnr"));

        TableColumn naamCol = new TableColumn("Naam");
        naamCol.setCellValueFactory(new PropertyValueFactory<GrootsteLezer, String>("Naam"));

        TableColumn genreCol = new TableColumn("Genre");
        genreCol.setCellValueFactory(new PropertyValueFactory<GrootsteLezer, String>("Genre"));
        
        TableColumn aantalCol = new TableColumn("Aantal");
        aantalCol.setMinWidth(60);
        aantalCol.setCellValueFactory(new PropertyValueFactory<GrootsteLezer, String>("Aantal"));

        table.setItems(data);
        table.getColumns().addAll(lidnrCol, naamCol, genreCol, aantalCol);

        final VBox vbox = new VBox();
        vbox.setSpacing(5);
        vbox.setPadding(new Insets(10, 0, 0, 10));
        vbox.getChildren().addAll(label, table);
/*
        ((Group) scene.getRoot()).getChildren().addAll(vbox);

        stage.setScene(scene);
        stage.show();
*/
        
        return vbox;
    }
    
    public class GrootsteLezer 
    {
        private String lidnr;
        private String naam;
        private String genre;
        private String aantal;
        
		public GrootsteLezer(String lidnr, String naam, String genre, String aantal) 
		{
			super();
			this.lidnr = lidnr;
            this.naam = naam;
            this.genre = genre;
            this.aantal = aantal;
		}
		public String getLidnr(){return lidnr;}
		public void setLidnr(String lidnr){this.lidnr = lidnr;}
		
		public String getNaam(){return naam;}
		public void setNaam(String naam){this.naam = naam;}
		
		public String getGenre(){return genre;}
		public void setGenre(String genre){this.genre = genre;}
		
		public String getAantal(){return aantal;}
		public void setAantal(String aantal){this.aantal = aantal;}
    }
}

