package org.bibly.logic.dao;

import org.bibly.logic.dao.interfaces.ILidDAO;
import org.bibly.logic.dao.interfaces.genericDAO.GenericHibernateDAO;
import org.bibly.logic.models.Lid;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository("lidDAO")
@Transactional
public class LidDAO extends GenericHibernateDAO<Lid, Long> implements ILidDAO
{

    public Lid save(Lid lid)
    {
        return super.makePersistent(lid);
    }

    public void save(List<Lid> lid)
    {
        super.importBatch(lid);
    }

    public Lid findById(long id)
    {
        return super.findById(id, false);
    }

    public List<Lid> FindByExample(Lid b)
    {
        return super.findByExample(b);
    }

    public void delete(Lid lid)
    {
        super.makeTransient(lid);
    }

    public List<Lid> findAllHuurders()
    {
        Session session = this.sessionFactory.openSession();
        try
        {
            Criteria cr = session.createCriteria(Lid.class);

            cr.add(Restrictions.eq("isHuurder", false));

            List<Lid> list = cr.list();
            return list;
        } catch (HibernateException e)
        {
            session.getTransaction().rollback();
            e.printStackTrace();
            return null;
        }
    }

    public List<Lid> findHuurdersByExample(Lid l)
    {
        Session session = this.sessionFactory.openSession();
        try
        {
            Criteria cr = session.createCriteria(Lid.class);

            cr.add(Restrictions.eq("isHuurder", false));

            if (l.getVoornaam() != null)
                cr.add(Restrictions.eqOrIsNull("voornaam", l.getVoornaam()));
            if (l.getAchternaam() != null)
                cr.add(Restrictions.eqOrIsNull("achternaam", l.getAchternaam()));
            if (l.getTelefoon() != null)
                cr.add(Restrictions.eqOrIsNull("telefoon", l.getTelefoon()));
            if (l.getEmail() != null)
                cr.add(Restrictions.eqOrIsNull("email", l.getEmail()));
            if (l.getGsmNummer() != null)
                cr.add(Restrictions.eqOrIsNull("gsmNummer", l.getGsmNummer()));
            if (l.getVoornaam() != null)
                cr.add(Restrictions.eqOrIsNull("voornaam", l.getVoornaam()));

            List<Lid> list = cr.list();
            return list;
        } catch (HibernateException e)
        {
            session.getTransaction().rollback();
            e.printStackTrace();
            return null;
        }
    }
}
