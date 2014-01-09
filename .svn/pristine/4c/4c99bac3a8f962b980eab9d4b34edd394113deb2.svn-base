package org.bibly.logic.dao.interfaces;

import org.bibly.logic.models.Cd;
import org.bibly.logic.models.Dvd;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Dries on 22/11/13.
 */
@Transactional
public interface ICdDAO
{
    List<Cd> findByArtiest(String artiest);

    List<Cd> findByNummers(List<String> nummers);

    public Cd save(Cd Cd);

    public void save(List<Cd> Cd);

    public Cd findById(long id);

    public List<Cd> FindByExample(Cd b);

    public List<Cd> findAll();

    public List<Cd> findByTitel(String titel);
    
    void delete(Cd d);
}
