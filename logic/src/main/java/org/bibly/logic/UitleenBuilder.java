package org.bibly.logic;

import org.bibly.logic.dao.interfaces.IExemplaarDAO;
import org.bibly.logic.dao.interfaces.IUitleningDAO;
import org.bibly.logic.dao.interfaces.IUitleningDetailsDAO;
import org.bibly.logic.exceptions.*;
import org.bibly.logic.models.*;
import org.bibly.logic.models.enums.PersoonType;
import org.springframework.dao.DataIntegrityViolationException;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class UitleenBuilder {

    private Personeel bibliothecaris;
    private Lid persoon;
    private Set<ItemExemplaar> exemplaren = new HashSet<ItemExemplaar>();

    private Date dateCreated = new Date();
    private Date inGeleverd;

    public Date getInGeleverd() {
        return inGeleverd;
    }

    public void setInGeleverd(Date inGeleverd) {
        this.inGeleverd = inGeleverd;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    @Resource
    private IUitleningDAO uitleningDAO;
    @Resource
    private IUitleningDetailsDAO uitleningdetailsDAO;
    @Resource
    private IExemplaarDAO exemplaarDAO;

    public IUitleningDAO getUitleningDAO() {
        return uitleningDAO;
    }

    public void setUitleningDAO(IUitleningDAO uitleningDAO) {
        this.uitleningDAO = uitleningDAO;
    }

    public IUitleningDetailsDAO getUitleningdetailsDAO() {
        return uitleningdetailsDAO;
    }

    public void setUitleningdetailsDAO(IUitleningDetailsDAO uitleningdetailsDAO) {
        this.uitleningdetailsDAO = uitleningdetailsDAO;
    }

    public IExemplaarDAO getExemplaarDAO() {
        return exemplaarDAO;
    }

    public void setExemplaarDAO(IExemplaarDAO exemplaarDAO) {
        this.exemplaarDAO = exemplaarDAO;
    }

    /**
     * @param bibliothecaris , persoon waarvoor de uitlening zal aangemaakt worden
     * @param uitlener       , persoon die de uitlening heeft verzorgt
     */
    public UitleenBuilder(Personeel bibliothecaris, Lid uitlener) {
        this.persoon = uitlener;
        this.bibliothecaris = bibliothecaris;
    }

    /**
     * @param p , persoon waarvoor de uitlening zal aangemaakt worden
     */
    public UitleenBuilder(Personeel p) {
        this.bibliothecaris = p;
    }

    public Persoon getBibliothecaris() {
        return bibliothecaris;
    }

    public void setBibliothecaris(Personeel b) {
        this.bibliothecaris = b;
    }

    public Persoon getPersoon() {
        return persoon;
    }

    public void setPersoon(Lid pers) {
        this.persoon = pers;
    }

    public Set<ItemExemplaar> getExemplaren() {
        return exemplaren;
    }

    public void setExemplaren(Set<ItemExemplaar> exemplaars) {
        this.exemplaren = exemplaars;
    }

    public void addExemplaar(ItemExemplaar exemplaar) throws ItemException {
        if (exemplaar != null) {
            if (exemplaarDAO.isBeschikbaar(exemplaar.getExemplaarID())) {
                this.exemplaren.add(exemplaar);
            } else {
                throw new ItemException("Item is niet beschikbaar");
            }
        } else {
            throw new ItemException("Interne error");
        }
    }

    /**
     * @return
     * @throws LidException
     * @throws BoekenException
     * @throws LidPermissionException
     */
    public Uitlening commitUitlening() throws LidException, ItemException, LidPermissionException, BibliothecarisException {
        if (persoon == null)
            throw new LidException();
        if (bibliothecaris == null)
            throw new BibliothecarisException();

        if (persoon.getPermissie() != PersoonType.LID)
            throw new LidPermissionException(persoon.getPermissie(), PersoonType.LID);

        if (exemplaren.size() == 0 && exemplaren.size() < StaticData.MaxAantalUitleenbaar)
            throw new ItemException();

        Uitlening uitlening = new Uitlening();
        uitlening.setUitleenStartDatum(dateCreated);
        uitlening.setUitgeleendDoor(bibliothecaris);
        uitlening.setUitlener(persoon);

        for (ItemExemplaar exemplaar : exemplaren) {
            UitleningDetail uitleenDetail = new UitleningDetail();
            uitleenDetail.setExemplaar(exemplaar);
            uitleenDetail.setUitlening(uitlening);
            if (inGeleverd != null)
                uitleenDetail.setDatumBinnenGebracht(inGeleverd);
            uitlening.addUitleningDetail(uitleenDetail);
        }

        try {
            uitleningDAO.save(uitlening);
        } catch (DataIntegrityViolationException ex) {
            ex.printStackTrace();
        }
        return uitlening;

    }

}
