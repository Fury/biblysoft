package org.bibly.logic.models;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "Gemeentes")
public class Gemeente
{
    private long gemeenteID;
    private String gemeente;
    private int postcode;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "gemeentes")
    @Fetch(FetchMode.SELECT)
    private Set<Adres> adressen;

    public Gemeente()
    {

    }

    public Gemeente(Set<Adres> adressen)
    {
        this.adressen = adressen;
    }

    public Gemeente(String gemeente, int postcode)
    {
        super();
        this.gemeente = gemeente;
        this.postcode = postcode;
    }

    @Id
    @Column(name = "GEMEENTE_ID", nullable = false, precision = 5, scale = 0)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long getGemeenteID()
    {
        return gemeenteID;
    }

    public void setGemeenteID(long gemeenteID)
    {
        this.gemeenteID = gemeenteID;
    }

    @Column(name = "GEMEENTE", nullable = false)
    public String getGemeente()
    {
        return gemeente;
    }


    public void setGemeente(String gemeente)
    {
        this.gemeente = gemeente;
    }

    @Column(name = "POSTCODE", nullable = false)
    public int getPostcode()
    {
        return postcode;
    }

    public void setPostcode(int postcode)
    {
        this.postcode = postcode;
    }

    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result
                + ((gemeente == null) ? 0 : gemeente.hashCode());
        result = prime * result + postcode;
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
        Gemeente other = (Gemeente) obj;
        if (gemeente == null)
        {
            if (other.gemeente != null)
                return false;
        } else if (!gemeente.equals(other.gemeente))
            return false;
        if (postcode != other.postcode)
            return false;
        return true;
    }


}
