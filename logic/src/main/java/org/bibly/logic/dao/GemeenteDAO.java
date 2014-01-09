package org.bibly.logic.dao;

import org.bibly.logic.dao.interfaces.IGemeenteDAO;
import org.bibly.logic.dao.interfaces.genericDAO.GenericHibernateDAO;
import org.bibly.logic.models.Gemeente;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository("gemeenteDAO")
@Transactional
public class GemeenteDAO extends GenericHibernateDAO<Gemeente, Long> implements IGemeenteDAO
{
    public Gemeente save(Gemeente gemeente)
    {
        return super.makePersistent(gemeente);
    }

    public Gemeente findById(long id)
    {
        return super.findById(id, false);
    }

    public List<Gemeente> FindByExample(Gemeente b)
    {
        Session session = sessionFactory.getCurrentSession();
        try
        {
            Criteria cr = session.createCriteria(Gemeente.class);

            if (!b.getGemeente().equals(""))
                cr.add(Restrictions.like("gemeente", "%" + b.getGemeente() + "%"));

            if (b.getPostcode() != 0)
                cr.add(Restrictions.eq("postcode", b.getPostcode()));


            List<Gemeente> list = cr.list();
            return list;
        } catch (HibernateException e)
        {
            e.printStackTrace();
            return null;
        }
    }

    public void delete(Gemeente gemeente)
    {
        super.makeTransient(gemeente);
    }

    public void delete(long id)
    {
    }

}
