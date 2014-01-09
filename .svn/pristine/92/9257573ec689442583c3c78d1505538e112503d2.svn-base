package org.bibly.logic.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "DVDS")
@Inheritance(strategy = InheritanceType.JOINED)
public class Dvd extends Item
{

    @Column(name = "SPEELDUUR")
    private int speelduur;

    @Column(name = "MINLEEFTIJD")
    private int minleeftijd;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "ACTEURS", joinColumns = @JoinColumn(name = "DVDS_ACTEURS"))
    @Column(name = "ACTEURS")
    private List<String> acteurs;

    public Dvd()
    {
    }

    public Dvd(String titel, String uitgever, String uitgaveJaar, String genre)
    {
        super(titel, uitgever, uitgaveJaar, genre);
    }

    public Dvd(String titel, String uitgever, String uitgaveJaar)
    {
        super(titel, uitgever, uitgaveJaar);
    }

    public Dvd(int speelduur, int minleeftijd, List<String> acteurs, String titel, String uitgever, String uitgaveJaar, String genre)
    {
        super(titel, uitgever, uitgaveJaar, genre);
        this.speelduur = speelduur;
        this.minleeftijd = minleeftijd;
        this.acteurs = acteurs;
    }

    public Dvd(int speelduur, int minleeftijd, List<String> acteurs, String titel, String uitgever, String uitgaveJaar)
    {
        super(titel, uitgever, uitgaveJaar);
        this.speelduur = speelduur;
        this.minleeftijd = minleeftijd;
        this.acteurs = acteurs;
    }

    public Dvd(int speelduur, int minleeftijd, List<String> acteurs)
    {
        super();
        this.speelduur = speelduur;
        this.minleeftijd = minleeftijd;
        this.acteurs = acteurs;
    }

    public int getSpeelduur()
    {
        return speelduur;
    }

    public void setSpeelduur(int speelduur)
    {
        this.speelduur = speelduur;
    }

    public int getMinleeftijd()
    {
        return minleeftijd;
    }

    public void setMinleeftijd(int minleeftijd)
    {
        this.minleeftijd = minleeftijd;
    }

    public List<String> getActeurs()
    {
        return acteurs;
    }

    public void setActeurs(List<String> acteurs)
    {
        this.acteurs = acteurs;
    }


}
