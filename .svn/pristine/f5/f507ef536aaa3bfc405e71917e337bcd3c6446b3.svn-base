package org.bibly.logic.dao.interfaces;

import org.bibly.logic.models.Lid;
import org.bibly.logic.models.Uitlening;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Dries on 11/21/13.
 */
@Transactional
public interface IUitleningDAO
{
    public Uitlening save(Uitlening uitlening);

    public void saveBatch(List<Uitlening> uitleningen);

    public Uitlening findById(long id);

    public List<Uitlening> FindByExample(Uitlening b);

    public List<Uitlening> findAll();

    public void delete(Uitlening uitlening);

    public List<Uitlening> findByLid(Lid l);
}
