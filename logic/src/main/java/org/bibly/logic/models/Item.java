package org.bibly.logic.models;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "Items")
@Inheritance(strategy = InheritanceType.JOINED)
public class Item
{

    @Id
    @Column(name = "ITEM_ID", nullable = false)
    @GeneratedValue(strategy = IDENTITY)
    private long itemID;

    @Column(name = "TITEL")
    private String titel;

    @Column(name = "GENRE")
    private String genre;

    @Column(name = "UITGEVER")
    private String uitgever;

    @Column(name = "TAAL")
    private String taal;

    @Column(name = "UITGAVEJAAR")
    private String uitgaveJaar;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "item", cascade = CascadeType.ALL)
    @Fetch(FetchMode.SELECT)
    private Set<ItemExemplaar> exemplaars;

    public Item()
    {
        exemplaars = new HashSet<ItemExemplaar>();
    }
    
    public Item(String titel) {
    	this.titel = titel;
    }

    public Item(String titel, String uitgever, String uitgaveJaar)
    {
        super();
        this.uitgaveJaar = uitgaveJaar;
        this.titel = titel;
        this.uitgever = uitgever;
        exemplaars = new HashSet<ItemExemplaar>();
    }

    public Item(String titel, String uitgever, String uitgaveJaar, String genre)
    {
        super();
        this.uitgaveJaar = uitgaveJaar;
        this.titel = titel;
        this.uitgever = uitgever;
        this.genre = genre;
        exemplaars = new HashSet<ItemExemplaar>();
    }

    public String getUitgaveJaar()
    {
        return uitgaveJaar;
    }

    public void setUitgaveJaar(String uitgaveJaar)
    {
        this.uitgaveJaar = uitgaveJaar;
    }

    public long getItemID()
    {
        return itemID;
    }

    public Set<ItemExemplaar> getExemplaars()
    {
        return exemplaars;
    }

    public void setExemplaars(Set<ItemExemplaar> exemplaars)
    {
        this.exemplaars = exemplaars;
    }

    public void setItemID(long itemID)
    {
        this.itemID = itemID;
    }

    public String getTitel()
    {
        return titel;
    }

    public void setTitel(String titel)
    {
        this.titel = titel;
    }

    public String getGenre()
    {
        if (genre == null)
            genre = "";
        return genre;
    }

    public void setGenre(String genre)
    {
        this.genre = genre;
    }

    public String getUitgever()
    {
        return uitgever;
    }

    public void setUitgever(String uitgever)
    {
        this.uitgever = uitgever;
    }

    public String getTaal()
    {
        if (taal == null)
            taal = "";
        return taal;
    }

    public void setTaal(String taal)
    {
        this.taal = taal;
    }

    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((genre == null) ? 0 : genre.hashCode());
        result = prime * result + (int) (itemID ^ (itemID >>> 32));
        result = prime * result + ((taal == null) ? 0 : taal.hashCode());
        result = prime * result + ((titel == null) ? 0 : titel.hashCode());
        result = prime * result
                + ((uitgever == null) ? 0 : uitgever.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Item other = (Item) obj;
        if (genre == null)
        {
            if (other.genre != null)
                return false;
        } else if (!genre.equals(other.genre))
            return false;
        if (itemID != other.itemID)
            return false;
        if (taal == null)
        {
            if (other.taal != null)
                return false;
        } else if (!taal.equals(other.taal))
            return false;
        if (titel == null)
        {
            if (other.titel != null)
                return false;
        } else if (!titel.equals(other.titel))
            return false;
        if (uitgever == null)
        {
            if (other.uitgever != null)
                return false;
        } else if (!uitgever.equals(other.uitgever))
            return false;
        return true;
    }

    public void addExemplaar(ItemExemplaar exemplaar)
    {
        exemplaars.add(exemplaar);
    }

    private String type;

    public String getType()
    {
        return type;
    }

    public void setType(String type)
    {
        this.type = type;
    }
}
