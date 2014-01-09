package org.bibly.logic.models;

import javax.persistence.*;

@Entity
@Table(name = "BOEKEN")
@Inheritance(strategy = InheritanceType.JOINED)
public class Boek extends Item
{
    @Column(name = "ISBN")
    private long isbn;

    @Column(name = "DRUK")
    private int druk;

    @Column(name = "AUTEUR")
    private String auteur;

    @Column(name = "PRIJS")
    private double prijs;

    public Boek()
    {
    }

    public Boek(long isbn, String titel, String auteur, String uitgever, String genre, String uitgavedatum, int druk)
    {
        super(titel, uitgever, uitgavedatum, genre);
        this.isbn = isbn;
        this.druk = druk;
        this.auteur = auteur;
    }

    public Boek(String titel, String auteur, String uitgever, String uitgavedatum, int druk)
    {
        super(titel, uitgever, uitgavedatum);
        this.druk = druk;
        this.auteur = auteur;
    }

    @Override
    public String toString()
    {
        return "Boek [isbn=" + isbn + ", druk=" + druk + ", auteur=" + auteur
                + "]";
    }

    public long getIsbn()
    {
        return isbn;
    }

    public void setIsbn(long isbn)
    {
        this.isbn = isbn;
    }

    public int getDruk()
    {
        return druk;
    }

    public void setDruk(int druk)
    {
        this.druk = druk;
    }

    public String getAuteur()
    {
        return auteur;
    }

    public void setAuteur(String auteur)
    {
        this.auteur = auteur;
    }

    public double getPrijs()
    {
        return prijs;
    }

    public void setPrijs(double prijs)
    {
        this.prijs = prijs;
    }

    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + (int) (isbn ^ (isbn >>> 32));
        return result;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (this == obj)
            return true;
        if (!super.equals(obj))
            return false;
        if (getClass() != obj.getClass())
            return false;
        Boek other = (Boek) obj;
        if (isbn != other.isbn)
            return false;
        return true;
    }
}