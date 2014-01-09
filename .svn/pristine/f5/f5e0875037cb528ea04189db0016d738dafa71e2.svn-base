package org.bibly.logic.dao;

import org.bibly.logic.dao.interfaces.IUitleningDAO;
import org.bibly.logic.dao.interfaces.genericDAO.GenericHibernateDAO;
import org.bibly.logic.models.ItemExemplaar;
import org.bibly.logic.models.Lid;
import org.bibly.logic.models.Uitlening;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository("uitleningDAO")
@Transactional
public class UitleningDAO extends GenericHibernateDAO<Uitlening, Long> implements IUitleningDAO {
    public Uitlening save(Uitlening uitlening) {
        return super.makePersistent(uitlening);
    }

    public void saveBatch(List<Uitlening> uitleningen) {
        importBatch(uitleningen);
    }

    public Uitlening findById(long id) {
        return super.findById(id, false);
    }

    public List<Uitlening> FindByExample(Uitlening b) {
        return super.findByExample(b);
    }

    public List<Uitlening> findByExample(Uitlening u) {
        Session session = this.sessionFactory.openSession();
        try {
            Criteria cr = session.createCriteria(Uitlening.class);

            if (u.getUitleenID() > 0)
                cr.add(Restrictions.eq("uitleenID", u.getUitleenID()));
            if (u.getUitleenStartDatum() != null)
                cr.add(Restrictions.eq("uitleenStartDatum", u.getUitleenStartDatum()));
            if (u.getUitlener() != null)
                cr.add(Restrictions.eq("uitlener", u.getUitlener()));
            if (u.getUitgeleendDoor() != null)
                cr.add(Restrictions.eq("uitgeleendDoor", u.getUitgeleendDoor()));
            if (u.getDetails() != null)
                cr.add(Restrictions.eq("details", u.getDetails()));

            List<Uitlening> list = cr.list();
            return list;
        } catch (HibernateException e) {
            session.getTransaction().rollback();
            e.printStackTrace();
            return null;
        }
    }

    public Uitlening findLast(ItemExemplaar ex) throws Exception {
        try {
            Session session = sessionFactory.getCurrentSession();
            String hql = "FROM UitleningDetail ud , ItemExemplaar ex WHERE (ex = ud.exemplaar) AND ex.exemplaarID = :ExemplaarID ORDER BY ud.id desc";
            Query query = session.createQuery(hql);
            query.setParameter("ExemplaarID", 0);
            Uitlening result = (Uitlening) query.list();
            return result;
        } catch (Exception e) {
            throw e;
        }

    }


    public void delete(Uitlening uitlening) {
        super.makeTransient(uitlening);
    }

    public List<Uitlening> findByLid(Lid l) {
        try {
            Session session = sessionFactory.getCurrentSession();
            String hql = "FROM Uitlening WHERE uitlener = :lid";
            Query query = session.createQuery(hql);
            query.setParameter("lid", l);
            List<Uitlening> result = query.list();
            return result;

        } catch (Exception e) {

        }
        return null;
    }

}
