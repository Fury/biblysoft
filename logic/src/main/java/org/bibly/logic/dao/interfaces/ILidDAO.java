package org.bibly.logic.dao.interfaces;

import org.bibly.logic.models.Lid;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Dries on 11/21/13.
 */
@Transactional
public interface ILidDAO
{
    public Lid save(Lid lid);

    public void save(List<Lid> lid);

    public Lid findById(long id);

    public List<Lid> FindByExample(Lid b);

    public List<Lid> findAll();

    public void delete(Lid lid);
}
