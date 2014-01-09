package org.bibly.logic;

//import com.sun.javaws.exceptions.InvalidArgumentException;

import org.bibly.logic.dao.interfaces.IUitleningDAO;
import org.bibly.logic.dao.interfaces.IUitleningDetailsDAO;
import org.bibly.logic.exceptions.ItemException;
import org.bibly.logic.exceptions.UitleningException;
import org.bibly.logic.models.UitleningDetail;

import java.util.Date;

public class CheckinBuilder
{
    private IUitleningDetailsDAO uitleningdetailsDAO;
    private IUitleningDAO uitleningDAO;

    public CheckinBuilder()
    {
        super();
    }

    public IUitleningDetailsDAO getUitleningdetailsDAO()
    {
        return uitleningdetailsDAO;
    }

    public void setUitleningdetailsDAO(IUitleningDetailsDAO uitleningdetailsDAO)
    {
        this.uitleningdetailsDAO = uitleningdetailsDAO;
    }

    public IUitleningDAO getUitleningDAO()
    {
        return uitleningDAO;
    }

    public void setUitleningDAO(IUitleningDAO uitleningDAO)
    {
        this.uitleningDAO = uitleningDAO;
    }

    public boolean checkin(long exemplaarId, Date date) throws ItemException, UitleningException/*, InvalidArgumentException*/
    {
        Date currDate = new Date();
        if (exemplaarId < 0)
            return false;
        if (date == null)
            date = new Date();
        /*
        if (currDate.compareTo(date) == 1)
            throw new InvalidArgumentException(new String[]{"1", "Datum kan niet in de toekomst zijn"});
		*/
        if (uitleningDAO == null || uitleningdetailsDAO == null) return false;

        UitleningDetail uitleningDetail = uitleningdetailsDAO.LaatsteUitleningExemplaar(exemplaarId);
        if (uitleningDetail == null)
            throw new ItemException("Item niet gevonden");

        if (uitleningDetail.getDatumBinnenGebracht() != null)
            throw new UitleningException("Uitlening reeds binnengebracht");

        uitleningDetail.setDatumBinnenGebracht(date);
        UitleningDetail dx = uitleningdetailsDAO.save(uitleningDetail);


        return true;
    }


}
