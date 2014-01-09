package org.bibly.logic.models;

import javax.persistence.*;
import java.util.Set;


@Entity
@Table(name = "ADRESSEN")
public class Adres
{

	public String toString() {
		return ", straatNaam=" + straatNaam
				+ ", bus=" + bus + ", huisNummer=" + huisNummer + ", gemeente="
				+ gemeente;
	}

	@Id
    @Column(name = "ADRES_ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long adresID;
    @Column(name = "STRAATNAAM", nullable = false)
    private String straatNaam;
    @Column(name = "BUS", nullable = false)
    private String bus;
    @Column(name = "HUISNUMMER", nullable = false)
    private int huisNummer;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "gemeente_id")
    private Gemeente gemeente;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Persoon> personen;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "adres")
    private Set<Vergaderruimte> vergaderruimtes;

    public Adres()
    {

    }


    public Adres(String straatNaam, String bus, int huisNummer,
                 Gemeente gemeente)
    {
        super();
        this.straatNaam = straatNaam;
        this.bus = bus;
        this.huisNummer = huisNummer;
        this.gemeente = gemeente;
    }


    public String getStraatNaam()
    {
        return straatNaam;
    }

    public Long getAdresID()
    {
        return adresID;
    }

    public void setAdresID(Long adresID)
    {
        this.adresID = adresID;
    }

    public void setStraatNaam(String straatNaam)
    {
        this.straatNaam = straatNaam;
    }

    public String getBus()
    {
        return bus;
    }

    public void setBus(String bus)
    {
        this.bus = bus;
    }

    public int getHuisNummer()
    {
        return huisNummer;
    }

    public void setHuisNummer(int huisNummer)
    {
        this.huisNummer = huisNummer;
    }

    public Gemeente getGemeente()
    {
        return gemeente;
    }

    public void setGemeente(Gemeente gemeente)
    {
        this.gemeente = gemeente;
    }

}
