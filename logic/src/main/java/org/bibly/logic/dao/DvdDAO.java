package org.bibly.logic.dao;

import org.bibly.logic.dao.interfaces.IDvdDAO;
import org.bibly.logic.dao.interfaces.genericDAO.GenericHibernateDAO;
import org.bibly.logic.models.Dvd;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository("dvdDAO")
@Transactional
public class DvdDAO extends GenericHibernateDAO<Dvd, Long> implements IDvdDAO
{

    public List<Dvd> findByExample(Dvd dvd)
    {
        Session session = this.sessionFactory.openSession();
        try
        {
            session.beginTransaction();
            Criteria cr = session.createCriteria(Dvd.class);

            if (dvd.getSpeelduur() > 0)
                cr.add(Restrictions.eqOrIsNull("speelduur", dvd.getSpeelduur()));

            if (dvd.getMinleeftijd() > 0)
                cr.add(Restrictions.eqOrIsNull("minleeftijd", dvd.getMinleeftijd()));

            if (dvd.getActeurs() != null)
                cr.add(Restrictions.eqOrIsNull("acteurs", dvd.getActeurs()));

            List<Dvd> list = cr.list();
            return list;
        } catch (HibernateException e)
        {
            session.getTransaction().rollback();
            e.printStackTrace();
            return null;
        }
    }

    public List<Dvd> findAll()
    {
        return super.findAll();
    }

    public List<Dvd> findByTitel(String titel)
    {
        return null;
    }

    public void delete(Dvd d)
    {
        super.makeTransient(d);
    }

    public List<Dvd> findByActeurs(List<String> acteurs)
    {
        Session session = this.sessionFactory.openSession();
        try
        {
            Criteria cr = session.createCriteria(Dvd.class);

            if (acteurs != null && acteurs.size() > 0)
                cr.add(Restrictions.in("acteurs", acteurs));

            List<Dvd> list = cr.list();
            return list;
        } catch (HibernateException e)
        {
            session.getTransaction().rollback();
            e.printStackTrace();
            return null;
        }
    }

    public Dvd save(Dvd dvd)
    {
        return super.makePersistent(dvd);
    }

    public void save(List<Dvd> dvd)
    {
        importBatch(dvd);
    }

    public Dvd findById(long id)
    {
        return super.findById(id, false);
    }

    public List<Dvd> FindByExample(Dvd b)
    {
        return super.findByExample(b);
    }
}
