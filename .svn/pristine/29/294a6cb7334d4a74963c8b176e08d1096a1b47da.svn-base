package org.bibly.logic.models;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

import static javax.persistence.GenerationType.IDENTITY;

@SuppressWarnings("serial")
@Entity
@Table(name = "UitleenDetails")
public class UitleningDetail implements Serializable
{

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "UITLEEN_ID")
    private Uitlening uitlening;

    @Column(name = "DATUMBINNENGEBRACHT")
    private Date datumBinnenGebracht;

    @Column(name = "AANTALVERLENGINGEN")
    private int aantalVerlengingen;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "EXEMPLAAR_ID", nullable = false)
    @PrimaryKeyJoinColumn
    private ItemExemplaar exemplaar;

    public UitleningDetail()
    {

    }


    @Fetch(FetchMode.SELECT)
    public Uitlening getUitlening()
    {
        return uitlening;
    }

    public void setUitlening(Uitlening uitlening)
    {
        this.uitlening = uitlening;
    }

    public Date getDatumBinnenGebracht()
    {
        return datumBinnenGebracht;
    }

    public void setDatumBinnenGebracht(Date datumBinnenGebracht)
    {
        this.datumBinnenGebracht = datumBinnenGebracht;
    }

    public int getAantalVerlengingen()
    {
        return aantalVerlengingen;
    }

    public void setAantalVerlengingen(int aantalVerlengingen)
    {
        this.aantalVerlengingen = aantalVerlengingen;
    }


    @Fetch(FetchMode.SELECT)
    public ItemExemplaar getExemplaar()
    {
        return exemplaar;
    }

    public void setExemplaar(ItemExemplaar exemplaar)
    {
        this.exemplaar = exemplaar;
    }


    public UitleningDetail(Uitlening uitlening, Date datumBinnenGebracht, int aantalVerlengingen, ItemExemplaar exemplaar)
    {
        this.uitlening = uitlening;
        this.datumBinnenGebracht = datumBinnenGebracht;
        this.aantalVerlengingen = aantalVerlengingen;
        this.exemplaar = exemplaar;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UitleningDetail that = (UitleningDetail) o;

        if (aantalVerlengingen != that.aantalVerlengingen) return false;
        if (id != that.id) return false;
        if (datumBinnenGebracht != null ? !datumBinnenGebracht.equals(that.datumBinnenGebracht) : that.datumBinnenGebracht != null)
            return false;
        if (exemplaar != null ? !exemplaar.equals(that.exemplaar) : that.exemplaar != null) return false;
        if (uitlening != null ? !uitlening.equals(that.uitlening) : that.uitlening != null) return false;

        return true;
    }

    @Override
    public int hashCode()
    {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (uitlening != null ? uitlening.hashCode() : 0);
        result = 31 * result + (datumBinnenGebracht != null ? datumBinnenGebracht.hashCode() : 0);
        result = 31 * result + aantalVerlengingen;
        result = 31 * result + (exemplaar != null ? exemplaar.hashCode() : 0);
        return result;
    }
}
