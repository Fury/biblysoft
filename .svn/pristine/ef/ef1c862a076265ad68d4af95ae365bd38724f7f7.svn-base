package org.bibly.logic.dao.interfaces;

import org.bibly.logic.models.UitleningDetail;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * Created by Dries on 11/21/13.
 */
@Transactional
public interface IUitleningDetailsDAO
{
    public UitleningDetail save(UitleningDetail detail);

    public void save(List<UitleningDetail> details);

    public UitleningDetail findById(long id);

    public List<UitleningDetail> FindByExample(UitleningDetail b);

    public List<UitleningDetail> findAll();

    public UitleningDetail LaatsteUitleningExemplaar(long exemplaarID);

    public List<Date> findAllOccupied(long vergaderruimteID);
}
