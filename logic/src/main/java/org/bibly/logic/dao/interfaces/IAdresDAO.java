package org.bibly.logic.dao.interfaces;

import org.bibly.logic.models.Adres;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Dries on 11/21/13.
 */
@Transactional
public interface IAdresDAO
{
    public Adres save(Adres adres);

    public void save(List<Adres> adressen);

    public Adres findById(long id);

    public List<Adres> FindByExample(Adres b);

    public List<Adres> findAll();
}
