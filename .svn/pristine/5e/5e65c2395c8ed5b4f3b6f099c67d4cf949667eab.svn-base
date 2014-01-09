package org.bibly.logic.dao.interfaces;

import org.bibly.logic.models.Gemeente;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Dries on 11/20/13.
 */
@Transactional
public interface IGemeenteDAO
{
    public Gemeente save(Gemeente gemeente);

    public Gemeente findById(long id);

    public List<Gemeente> FindByExample(Gemeente b);

    public void delete(Gemeente x);

    List<Gemeente> findAll();
}
