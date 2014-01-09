package org.bibly.logic.dao;

import org.bibly.logic.dao.interfaces.IBoekDAO;
import org.bibly.logic.dao.interfaces.genericDAO.GenericDAO;
import org.bibly.logic.dao.interfaces.genericDAO.GenericHibernateDAO;
import org.bibly.logic.models.Boek;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository("boekDAO")
@Transactional
public class BoekDAO extends GenericHibernateDAO<Boek, Long> implements IBoekDAO, GenericDAO<Boek, Long>
{
    public Boek save(Boek boek)
    {
        return super.makePersistent(boek);
    }

    public Boek findById(long id)
    {
        return super.findById(id, false);
    }

    public List<Boek> FindByExample(Boek boek)
    {

        Session session = sessionFactory.getCurrentSession();
        try
        {
            Criteria cr = session.createCriteria(Boek.class);

            if (!boek.getAuteur().equals(""))
                cr.add(Restrictions.like("auteur", "%" + boek.getAuteur() + "%"));

            if (boek.getIsbn() != 0)
                cr.add(Restrictions.eq("isbn", boek.getIsbn()));

            if (boek.getDruk() != 0)
                cr.add(Restrictions.eq("druk", boek.getDruk()));

            if (!boek.getTitel().equals(""))
                cr.add(Restrictions.like("titel", "%" + boek.getTitel() + "%"));

            if (!boek.getGenre().equals(""))
                cr.add(Restrictions.like("genre", "%" + boek.getGenre() + "%"));

            if (!boek.getUitgever().equals(""))
                cr.add(Restrictions.like("uitgever", "%" + boek.getUitgever() + "%"));


            List<Boek> list = cr.list();
            return list;
        } catch (HibernateException e)
        {
            e.printStackTrace();
            return null;
        }

    }

    public List<Boek> findAll()
    {
        return super.findAll();
    }

    public Boek getBoekByISBN(long isbn)
    {
        Session session = sessionFactory.getCurrentSession();
        try
        {
            Criteria cr = session.createCriteria(Boek.class);

            if (isbn != 0 && isbn > 999999999)
                cr.add(Restrictions.eq("isbn", isbn));
            else
                return null;

            Boek boeken = (Boek) cr.list().get(0);
            return boeken;

        } catch (HibernateException e)
        {
            session.getTransaction().rollback();
            e.printStackTrace();
            return null;
        }
    }

    public List<Boek> findByTitel(String titel)
    {
        Query query = sessionFactory.getCurrentSession().createQuery("from Boek where titel = :titel");
        query.setParameter("titel", titel);
        return query.list();
    }

    public void delete(Boek boek)
    {
        super.makeTransient(boek);
    }

    public void save(List<Boek> boek)
    {
        importBatch(boek);
    }


}
