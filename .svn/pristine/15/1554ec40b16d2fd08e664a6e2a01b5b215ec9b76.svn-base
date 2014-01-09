package org.bibly.logic.dao;

import org.bibly.logic.dao.interfaces.IVergaderruimteDAO;
import org.bibly.logic.dao.interfaces.genericDAO.GenericHibernateDAO;
import org.bibly.logic.models.Vergaderruimte;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository("vergaderruimteDAO")
@Transactional
public class VergaderruimteDAO extends GenericHibernateDAO<Vergaderruimte, Long> implements IVergaderruimteDAO
{

    public List<Vergaderruimte> findByExample(Vergaderruimte ruimte)
    {
        Session session = this.sessionFactory.openSession();
        try
        {
            Criteria cr = session.createCriteria(Vergaderruimte.class);

            if (ruimte.getMaxPersonen() > 0)
                cr.add(Restrictions.eqOrIsNull("maxPersonen", ruimte.getMaxPersonen()));

            if (ruimte.getAdres() != null)
                cr.add(Restrictions.eq("adres", ruimte.getAdres()));

            if (ruimte.getPrijs() > 0)
                cr.add(Restrictions.eq("prijs", ruimte.getPrijs()));

            List<Vergaderruimte> list = cr.list();
            return list;
        } catch (HibernateException e)
        {
            session.getTransaction().rollback();
            e.printStackTrace();
            return null;
        }
    }

    public List<Vergaderruimte> findByPrijs(double price)
    {
        Session session = this.sessionFactory.openSession();
        try
        {
            Criteria cr = session.createCriteria(Vergaderruimte.class);

            if (price > 0)
                cr.add(Restrictions.le("PRIJS", price));

            List<Vergaderruimte> list = cr.list();
            return list;
        } catch (HibernateException e)
        {
            session.getTransaction().rollback();
            e.printStackTrace();
            return null;
        }
    }

    public List<Vergaderruimte> findByMaxPersonen(int max)
    {
        Session session = this.sessionFactory.openSession();
        try
        {
            Criteria cr = session.createCriteria(Vergaderruimte.class);

            cr.add(Restrictions.le("maxPersonen", max));

            List<Vergaderruimte> list = cr.list();
            return list;
        } catch (HibernateException e)
        {
            session.getTransaction().rollback();
            e.printStackTrace();
            return null;
        }
    }


    public void delete(Vergaderruimte vergaderruimte)
    {
        super.makeTransient(vergaderruimte);
    }

    public Vergaderruimte findById(long id)
    {
        return super.findById(id, false);
    }

    public Vergaderruimte save(Vergaderruimte ruimte)
    {
        return super.makePersistent(ruimte);
    }

    public List<Vergaderruimte> save(List<Vergaderruimte> ruimte)
    {
        super.importBatch(ruimte);
        return ruimte;
    }

    public List<Vergaderruimte> findAll()
    {
        return super.findAll();
    }

}
