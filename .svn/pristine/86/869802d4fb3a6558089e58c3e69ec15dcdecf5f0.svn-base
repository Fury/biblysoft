package org.bibly.logic.models;

import org.bibly.logic.models.enums.PersoonType;

import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name = "PERSONEN")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Persoon
{

    @Id
    @Column(name = "PERSOON_ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long persoonID;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ADRES_ID")
    private Adres adres;

    @Column(name = "PERMISSIE")
    @Enumerated(EnumType.STRING)
    private PersoonType permissie;

    @Column(name = "AANMAAKDATUM")
    private Date aanmaakDatum;

    @Column(name = "GEBOORTEDATUM")
    private Date geboorteDatum;

    public Persoon()
    {
        super();
    }

    public Persoon(Adres adres, PersoonType permissie, Date aanmaakDatum)
    {
        super();
        this.adres = adres;
        this.permissie = permissie;
        this.aanmaakDatum = aanmaakDatum;
    }

    public Persoon(Adres adres, PersoonType permissie, Date aanmaakDatum, Date geboorteDatum)
    {
        super();
        this.adres = adres;
        this.permissie = permissie;
        this.aanmaakDatum = aanmaakDatum;
        this.geboorteDatum = geboorteDatum;
    }

    public long getPersoonID()
    {
        return persoonID;
    }


    public void setPersoonID(int persoonID)
    {
        this.persoonID = persoonID;
    }


    public Adres getAdres()
    {
        return adres;
    }

    @Override
    public String toString()
    {
        return "Persoon [persoonID=" + persoonID + ", adres=" + adres
                + ", permissie=" + permissie + ", aanmaakDatum=" + aanmaakDatum
                + "]";
    }

    public void setAanmaakDatum(Date aanmaakDatum)
    {
        this.aanmaakDatum = aanmaakDatum;
    }


    public void setAdres(Adres adres)
    {
        this.adres = adres;
    }

    public PersoonType getPermissie()
    {
        return permissie;
    }

    public void setPermissie(PersoonType permissie)
    {
        this.permissie = permissie;
    }

    public Date getAanmaakDatum()
    {
        return aanmaakDatum;
    }

    public Date getGeboorteDatum()
    {
        return geboorteDatum;
    }

    public void setGeboorteDatum(Date geboorteDatum)
    {
        this.geboorteDatum = geboorteDatum;
    }

    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int) (persoonID ^ (persoonID >>> 32));
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
        Persoon other = (Persoon) obj;
        if (persoonID != other.persoonID)
            return false;
        return true;
    }
}
