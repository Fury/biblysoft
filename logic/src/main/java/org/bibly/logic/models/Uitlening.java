package org.bibly.logic.models;

import javax.persistence.*;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import static javax.persistence.GenerationType.IDENTITY;


@Entity
@Table(name = "Uitleningen")
public class Uitlening
{

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "UITLEEN_ID", unique = true, nullable = false)
    private long uitleenID;

    @Column(name = "UITLEENSTARTDATUM")
    private Date uitleenStartDatum;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "PERSOON_ID", nullable = false)
    private Lid uitlener;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "PERSONEEL_ID", nullable = false)
    private Personeel uitgeleendDoor;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "uitlening", nullable = false)
    private Set<UitleningDetail> details = new HashSet<UitleningDetail>();

    public Uitlening(Date uitleenStartDatum, Lid uitlener, Personeel uitgeleendDoor, Set<UitleningDetail> details)
    {
        this.uitleenStartDatum = uitleenStartDatum;
        this.uitlener = uitlener;
        this.uitgeleendDoor = uitgeleendDoor;
        this.details = details;
    }

    public Uitlening(Date uitleenStartDatum, Lid uitlener, Personeel uitgeleendDoor)
    {
        this.uitleenStartDatum = uitleenStartDatum;
        this.uitlener = uitlener;
        this.uitgeleendDoor = uitgeleendDoor;
    }

    public Uitlening()
    {

    }

    public long getUitleenID()
    {
        return uitleenID;
    }

    public void setUitleenID(long uitleenID)
    {
        this.uitleenID = uitleenID;
    }

    public Date getUitleenStartDatum()
    {
        return uitleenStartDatum;
    }

    public void setUitleenStartDatum(Date uitleenStartDatum)
    {
        this.uitleenStartDatum = uitleenStartDatum;
    }

    public Lid getUitlener()
    {
        return uitlener;
    }

    public void setUitlener(Lid uitlener)
    {
        this.uitlener = uitlener;
    }

    public Personeel getUitgeleendDoor()
    {
        return uitgeleendDoor;
    }

    public void setUitgeleendDoor(Personeel uitgeleendDoor)
    {
        this.uitgeleendDoor = uitgeleendDoor;
    }

    public Set<UitleningDetail> getDetails()
    {
        return details;
    }

    public void setDetails(Set<UitleningDetail> details)
    {
        this.details = details;
    }

    public void addUitleningDetail(UitleningDetail detail)
    {
        if (detail == null)
            return;

        details.add(detail);
    }

    public UitleningDetail getDetailByExemplaar(ItemExemplaar ex)
    {
        for (UitleningDetail d : this.getDetails())
        {
            if (d.getExemplaar().equals(ex))
            {
                return d;
            }
        }
        return null;
    }


}
