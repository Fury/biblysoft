package org.biblyfx.controllers.items;

import org.bibly.logic.models.ItemExemplaar;
import org.joda.time.DateTime;

import java.util.Date;

public class tempExemplaar
{
    public String itemID;
    public String titel;
    public String auteur;
    public String startDatum;
    public String eindDatum;

    tempExemplaar()
    {

    }

    public tempExemplaar(ItemExemplaar exemplaar)
    {
        itemID = String.valueOf(exemplaar.getItem().getItemID());
        titel = exemplaar.getItem().getTitel();
        auteur = exemplaar.getItem().getUitgever();
        startDatum = DateTime.now().toLocalDate().toString();
        eindDatum = new DateTime(startDatum).plusDays(30).toLocalDate().toString();
    }

    tempExemplaar(String itemID, String titel, String auteur, Date startDatum)
    {
        this.itemID = itemID;
        this.titel = titel;
        this.auteur = auteur;
        this.startDatum = new DateTime(startDatum).toString();
        this.eindDatum = new DateTime(startDatum).plusDays(30).toString();
    }

    public String getItemID()
    {
        return itemID;
    }

    public void setItemID(String itemID)
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

    public String getAuteur()
    {
        return auteur;
    }

    public void setAuteur(String auteur)
    {
        this.auteur = auteur;
    }

    public String getStartDatum()
    {
        return startDatum;
    }

    public void setStartDatum(Date startDatum)
    {
        this.startDatum = new DateTime(startDatum).toLocalDate().toString();
    }

    public String getEindDatum()
    {
        return eindDatum;
    }

    public void setEindDatum(Date eindDatum)
    {
        this.eindDatum = new DateTime(eindDatum).toLocalDate().toString();
    }
}
