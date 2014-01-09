/**
 *
 */
package org.bibly.logic.models;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Dries
 */
@Entity
@Table(name = "ITEMEXEMPLAREN")
public class ItemExemplaar
{
    @Id
    @Column(name = "EXEMPLAAR_ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long exemplaarID;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ITEM_ID", nullable = false)
    private Item item;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "exemplaar", cascade = CascadeType.ALL)
    @Fetch(FetchMode.SELECT)
    private Set<UitleningDetail> detail = new HashSet<UitleningDetail>();

    private String PlaatsKenmerk;

    public ItemExemplaar(Item item)
    {
        super();
        this.item = item;
    }

    public ItemExemplaar(Item item, String plaatsKenmerk)
    {
        this.item = item;
        PlaatsKenmerk = plaatsKenmerk;
    }

    public long getExemplaarID()
    {
        return exemplaarID;
    }

    public void setExemplaarID(long exemplaarID)
    {
        this.exemplaarID = exemplaarID;
    }

    public Item getItem()
    {
        return item;
    }

    public void setItem(Item item)
    {
        this.item = item;
    }

    public Set<UitleningDetail> getDetail()
    {
        return detail;
    }

    public void setDetail(Set<UitleningDetail> detail)
    {
        this.detail = detail;
    }

    public String getPlaatsKenmerk()
    {
        return PlaatsKenmerk;
    }

    public void setPlaatsKenmerk(String plaatsKenmerk)
    {
        PlaatsKenmerk = plaatsKenmerk;
    }

    public ItemExemplaar(String plaatsKenmerk)
    {
        super();
        PlaatsKenmerk = plaatsKenmerk;
    }

    public ItemExemplaar()
    {
        // TODO Auto-generated constructor stub
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ItemExemplaar exemplaar = (ItemExemplaar) o;

        if (exemplaarID != exemplaar.exemplaarID) return false;
        if (PlaatsKenmerk != null ? !PlaatsKenmerk.equals(exemplaar.PlaatsKenmerk) : exemplaar.PlaatsKenmerk != null)
            return false;

        return true;
    }

    @Override
    public int hashCode()
    {
        int result = (int) (exemplaarID ^ (exemplaarID >>> 32));
        result = 31 * result + (PlaatsKenmerk != null ? PlaatsKenmerk.hashCode() : 0);
        return result;
    }
}
