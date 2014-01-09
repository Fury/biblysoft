package org.bibly.logic.dao;

import org.bibly.logic.dao.interfaces.IPersoneelDAO;
import org.bibly.logic.dao.interfaces.genericDAO.GenericHibernateDAO;
import org.bibly.logic.exceptions.PersoneelNotFoundException;
import org.bibly.logic.models.Personeel;
import org.bibly.logic.models.enums.PersoonType;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository("personeelDAO")
@Transactional
public class PersoneelDAO extends GenericHibernateDAO<Personeel, Long>
        implements IPersoneelDAO
{

    public Personeel save(Personeel personeel)
    {
        return super.makePersistent(personeel);
    }

    public void save(List<Personeel> personeel)
    {
        importBatch(personeel);
    }
    
    public List<Personeel> findAll() {
    	return super.findAll();
    }

    public Personeel findById(long id)
    {
        return super.findById(id, false);
    }

    public List<Personeel> FindByExample(Personeel b)
    {
        return super.findByExample(b);
    }

    public void delete(Personeel lid)
    {
        super.makeTransient(lid);
    }

    public Personeel findByUsername(String username)
    {

        Query query = sessionFactory.getCurrentSession().createQuery(
                "from Personeel where username = :username");
        query.setParameter("username", username);
        List list = query.list();
        if (list.size() == 0)
            return null;
        Personeel personeel = (Personeel) list.get(0);
        return personeel;

    }

    public Personeel login(String username, String password)
            throws IllegalArgumentException, PersoneelNotFoundException
    {
        if (username == null || password == null)
        {
            throw new IllegalArgumentException();
        }

        Query query = sessionFactory.getCurrentSession().createQuery(
                "from Personeel where username = :username");
        query.setParameter("username", username);
        List list = query.list();

        if (list.size() == 0 || list.size() > 1)
            throw new PersoneelNotFoundException();

        Personeel personeel = (Personeel) list.get(0);
        if (personeel.getPasswoord().equals(password)
                && personeel.getPermissie() != PersoonType.AFGESLOTEN)
            return personeel;
        else
            throw new PersoneelNotFoundException();
    }

}
