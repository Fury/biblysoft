package org.bibly.logic.models;

import org.bibly.logic.models.enums.PersoonType;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;

import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "PERSONEEL")
@PrimaryKeyJoinColumn(name = "PERSONEEL_ID", referencedColumnName = "PERSOON_ID")
public class Personeel extends Persoon {

    @Column(name = "USERNAME")
    private String username;

    @Column(name = "PASSWOORD")
    private String passwoord;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "uitgeleendDoor")
    private Set<Uitlening> uitleningen;

    public Personeel() {
        super();
    }

    /**
     * @param username     - Gebruikersnaam bij aanmaken
     * @param passwoord    - het passwoord
     * @param adres        - Adres van persoon
     * @param permissie    - Admin/Bibliothecaris/Lid
     * @param aanmaakDatum - aanmaakdatum
     */
    public Personeel(String username, String passwoord, Adres adres,
                     PersoonType permissie, Date aanmaakDatum) {
        super(adres, permissie, aanmaakDatum);
        this.username = username;
        this.passwoord = passwoord;
    }

    @Override
    public String toString() {
        String x = super.toString();
        return x + " - Personeel [username=" + username + ", passwoord=" + passwoord + "]";
    }

    public String getUsername() {
        return username;
    }

    public Personeel(Adres adres, PersoonType permissie, Date aanmaakDatum) {
        super(adres, permissie, aanmaakDatum);
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPasswoord() {
        return passwoord;
    }

    public void setPasswoord(String passwoord) {
        this.passwoord = passwoord;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result
                + ((username == null) ? 0 : username.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Personeel other = (Personeel) obj;
        if (username == null) {
            if (other.username != null)
                return false;
        } else if (!username.equals(other.username))
            return false;
        return true;
    }

	public Set<Uitlening> getUitleningen() {
		return uitleningen;
	}

	public void setUitleningen(Set<Uitlening> uitleningen) {
		this.uitleningen = uitleningen;
	}
    
    
}
