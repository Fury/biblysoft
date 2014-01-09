package org.bibly.logic.models;

import javax.persistence.*;


@Entity
@Table(name = "VERGADERRUIMTES")
@Inheritance(strategy = InheritanceType.JOINED)
public class Vergaderruimte extends Item
{

    @Column(name = "MAXPERSONEN", nullable = false)
    private int maxPersonen;


    @Column(name = "PRIJS", nullable = false)
    private double prijs;

    public Vergaderruimte()
    {
        super();
    }

    public Vergaderruimte(String naam, int maxPersonen, Adres adres, double prijs)
    {
        super(naam);
        this.maxPersonen = maxPersonen;
        this.adres = adres;
        this.prijs = prijs;
    }

    public Vergaderruimte(int maxPersonen, Adres adres, double prijs)
    {
        this.maxPersonen = maxPersonen;
        this.adres = adres;
        this.prijs = prijs;
    }

    public int getMaxPersonen()
    {
        return maxPersonen;
    }

    public void setMaxPersonen(int maxPersonen)
    {
        this.maxPersonen = maxPersonen;
    }

    public Adres getAdres()
    {
        return adres;
    }

    public void setAdres(Adres adres)
    {
        this.adres = adres;
    }

    public double getPrijs()
    {
        return prijs;
    }

    public void setPrijs(double prijs)
    {
        this.prijs = prijs;
    }


    @ManyToOne(optional = false)
    private Adres adres;

    public Adres getAdreses()
    {
        return adres;
    }

    public void setAdreses(Adres adreses)
    {
        this.adres = adreses;
    }
}
