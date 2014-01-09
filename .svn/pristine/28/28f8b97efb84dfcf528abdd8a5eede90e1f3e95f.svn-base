package org.bibly.logic.dao.interfaces;

import org.bibly.logic.models.Boek;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Dries on 11/21/13.
 */
@Transactional
public interface IBoekDAO
{
    public Boek save(Boek Boek);

    public void save(List<Boek> itemExemplaar);

    public Boek findById(long id);

    public List<Boek> FindByExample(Boek b);

    public List<Boek> findAll();

    public Boek getBoekByISBN(long isbn);

    public List<Boek> findByTitel(String titel);

    public void delete(Boek boek);
}
