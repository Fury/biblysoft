package org.bibly.logic.dao.interfaces;

import org.bibly.logic.models.Persoon;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Dries on 11/21/13.
 */
@Transactional
public interface IPersoonDAO
{
    public Persoon save(Persoon persoon);

    public void save(List<Persoon> persoon);

    public Persoon findById(long id);

    public List<Persoon> FindByExample(Persoon b);

    public List<Persoon> findAll();
}
