package org.bibly.logic.models;

import org.bibly.logic.models.enums.PersoonType;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "LEDEN")
@PrimaryKeyJoinColumn(name = "LID_ID", referencedColumnName = "PERSOON_ID")
public class Lid extends Persoon
{

    @Column(name = "VOORNAAM")
    private String voornaam;

    @Column(name = "ACHTERNAAM")
    private String achternaam;

    @Column(name = "TELEFOON")
    private String telefoon;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "GSMNUMMER")
    private String gsmNummer;

    @Column(name = "SUBSCRIPTIEEINDDATUM")
    private Date subscriptieEindDatum;

    @Column(name = "HUURDER")
    private boolean isHuurder = false;

    @OneToMany(mappedBy = "uitlener")
    private List<Uitlening> uitleningen;

    public Lid(Adres adres, PersoonType permissie, Date aanmaakDatum)
    {
        super(adres, permissie, aanmaakDatum);
    }
    
    public Lid(Adres adres, Date geboorteDatum, PersoonType permissie, Date aanmaakDatum,
            String voornaam, String achternaam, String telefoon, String email,
            String gsmNummer, boolean isHuurder)
    {
    	super(adres, permissie, aanmaakDatum, geboorteDatum);
        this.voornaam = voornaam;
        this.achternaam = achternaam;
        this.telefoon = telefoon;
        this.email = email;
        this.gsmNummer = gsmNummer;
        this.isHuurder = isHuurder;
    }
    
    public Lid(Adres adres, PersoonType permissie, Date aanmaakDatum,
            String voornaam, String achternaam, String telefoon, String email,
            String gsmNummer, boolean isHuurder) 
    {
    	super(adres, permissie, aanmaakDatum);
        this.voornaam = voornaam;
        this.achternaam = achternaam;
        this.telefoon = telefoon;
        this.email = email;
        this.gsmNummer = gsmNummer;
        this.isHuurder = isHuurder;
    }

    public Lid(Adres adres, Date geboorteDatum, PersoonType permissie, Date aanmaakDatum,
               String voornaam, String achternaam, String telefoon, String email,
               String gsmNummer, Date subscriptieEindDatum, boolean isHuurder)
    {
        super(adres, permissie, aanmaakDatum, geboorteDatum);
        this.voornaam = voornaam;
        this.achternaam = achternaam;
        this.telefoon = telefoon;
        this.email = email;
        this.gsmNummer = gsmNummer;
        this.subscriptieEindDatum = subscriptieEindDatum;
        this.isHuurder = isHuurder;
    }

    public List<Uitlening> getUitleningen()
    {
        return uitleningen;
    }

    public Lid(Adres adres, PersoonType permissie, Date aanmaakDatum,
               String voornaam, String achternaam, String telefoon, String email,
               String gsmNummer, Date subscriptieEindDatum)
    {
        super(adres, permissie, aanmaakDatum);
        this.voornaam = voornaam;
        this.achternaam = achternaam;
        this.telefoon = telefoon;
        this.email = email;
        this.gsmNummer = gsmNummer;
        this.subscriptieEindDatum = subscriptieEindDatum;
    }

    public Lid()
    {
    }

    public void setUitleningen(List<Uitlening> uitleningen)
    {
        this.uitleningen = uitleningen;
    }

    public String getVoornaam()
    {
        return voornaam;
    }

    public void setVoornaam(String voornaam)
    {
        this.voornaam = voornaam;
    }

    public String getAchternaam()
    {
        return achternaam;
    }

    public void setAchternaam(String achternaam)
    {
        this.achternaam = achternaam;
    }

    public String getTelefoon()
    {
        return telefoon;
    }

    public void setTelefoon(String telefoon)
    {
        this.telefoon = telefoon;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getGsmNummer()
    {
        return gsmNummer;
    }

    public void setGsmNummer(String gsmNummer)
    {
        this.gsmNummer = gsmNummer;
    }

    public Date getSubscriptieEindDatum()
    {
        return subscriptieEindDatum;
    }

    public void setSubscriptieEindDatum(Date subscriptieEindDatum)
    {
        this.subscriptieEindDatum = subscriptieEindDatum;
    }

    public boolean isHuurder()
    {
        return isHuurder;
    }

    public void setHuurder(boolean isHuurder)
    {
        this.isHuurder = isHuurder;
    }

    @Override
    public String toString()
    {
        return "Lid{" +
                "voornaam='" + voornaam + '\'' +
                ", achternaam='" + achternaam + '\'' +
                ", telefoon='" + telefoon + '\'' +
                ", email='" + email + '\'' +
                ", gsmNummer='" + gsmNummer + '\'' +
                ", subscriptieEindDatum=" + subscriptieEindDatum +
                ", isHuurder=" + isHuurder +
                ", uitleningen=" + uitleningen +
                '}';
    }
}
