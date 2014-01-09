package org.bibly.logic.dao.interfaces;

import org.bibly.logic.exceptions.PersoneelNotFoundException;
import org.bibly.logic.models.Personeel;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Dries on 11/21/13.
 */
@Transactional
public interface IPersoneelDAO
{
    public Personeel save(Personeel personeel);

    public void save(List<Personeel> personeel);

    public Personeel findById(long id);

    public List<Personeel> FindByExample(Personeel b);

    public List<Personeel> findAll();

    public void delete(Personeel lid);

    public Personeel login(String username, String password) throws IllegalArgumentException, PersoneelNotFoundException;

	public Personeel findByUsername(String string);
}
