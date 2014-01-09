package org.bibly.logic.dao.interfaces;

import org.bibly.logic.models.Dvd;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Dries on 22/11/13.
 */
@Transactional
public interface IDvdDAO
{

    public List<Dvd> findByActeurs(List<String> acteurs);

    public Dvd save(Dvd Boek);

    public void save(List<Dvd> itemExemplaar);

    public Dvd findById(long id);

    public List<Dvd> FindByExample(Dvd b);

    public List<Dvd> findAll();

    public List<Dvd> findByTitel(String titel);

    void delete(Dvd d);
}
