package org.bibly.logic.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "CDS")
@Inheritance(strategy = InheritanceType.JOINED)
public class Cd extends Item
{

    @Column(name = "ARTIEST")
    private String artiest;

    @Column(name = "PRIJS")
    private double prijs;

    @ElementCollection
    @CollectionTable(name = "NUMMERS", joinColumns = @JoinColumn(name = "CDS_NUMMERS"))
    @Column(name = "NUMMERS")
    private List<String> nummers;


    public Cd()
    {
    }

    public Cd(String titel, String uitgever, String uitgaveJaar, String genre)
    {
        super(titel, uitgever, uitgaveJaar, genre);
    }

    public Cd(String titel, String uitgever, String uitgaveJaar)
    {
        super(titel, uitgever, uitgaveJaar);
    }

    public Cd(String artiest, List<String> nummers, double prijs, String titel, String uitgever, String uitgaveJaar, String genre)
    {
        super(titel, uitgever, uitgaveJaar, genre);
        this.artiest = artiest;
        this.nummers = nummers;
        this.prijs = prijs;
    }

    public Cd(String artiest, List<String> nummers, double prijs, String titel, String uitgever, String uitgaveJaar)
    {
        super(titel, uitgever, uitgaveJaar);
        this.artiest = artiest;
        this.nummers = nummers;
        this.prijs = prijs;
    }

    public Cd(String artiest, List<String> nummers, double prijs)
    {
        super();
        this.artiest = artiest;
        this.nummers = nummers;
        this.prijs = prijs;
    }

    public Cd(String artiest, double prijs)
    {
        super();
        this.artiest = artiest;
        this.prijs = prijs;
    }


    public String getArtiest()
    {
        return artiest;
    }

    public void setArtiest(String artiest)
    {
        this.artiest = artiest;
    }

    public List<String> getNummers()
    {
        return nummers;
    }

    public void setNummers(List<String> nummers)
    {
        this.nummers = nummers;
    }

    public double getPrijs()
    {
        return prijs;
    }

    public void setPrijs(double prijs)
    {
        this.prijs = prijs;
    }


}
