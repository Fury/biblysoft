package org.bibly.logic.dao;

import org.bibly.logic.dao.interfaces.ICdDAO;
import org.bibly.logic.dao.interfaces.genericDAO.GenericHibernateDAO;
import org.bibly.logic.models.Cd;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository("cdDAO")
@Transactional
public class CdDAO extends GenericHibernateDAO<Cd, Long> implements ICdDAO
{

    public List<Cd> findByExample(Cd cd)
    {
        Session session = this.sessionFactory.openSession();
        try
        {
            Criteria cr = session.createCriteria(Cd.class);

            if (cd.getArtiest() != "")
                cr.add(Restrictions.eqOrIsNull("artiest", cd.getArtiest()));
            if (cd.getNummers() != null && cd.getNummers().size() > 0)
                cr.add(Restrictions.eqOrIsNull("nummers", cd.getNummers()));

            List<Cd> list = cr.list();
            return list;
        } catch (HibernateException e)
        {
            session.getTransaction().rollback();
            e.printStackTrace();
            return null;
        }
    }

    public List<Cd> findAll()
    {
        Session session = this.sessionFactory.openSession();
        try
        {
            Criteria cr = session.createCriteria(Cd.class);
            List<Cd> list = cr.list();
            return list;
        } catch (HibernateException e)
        {
            session.getTransaction().rollback();
            e.printStackTrace();
            return null;
        }
    }

    public List<Cd> findByTitel(String titel)
    {
        return null;
    }

    public List<Cd> findByArtiest(String artiest)
    {
        Session session = this.sessionFactory.openSession();
        try
        {
            Criteria cr = session.createCriteria(Cd.class);

            if (artiest != "")
                cr.add(Restrictions.like("artiest", "%" + artiest + "%"));

            List<Cd> list = cr.list();
            session.getTransaction().commit();
            return list;
        } catch (HibernateException e)
        {
            session.getTransaction().rollback();
            e.printStackTrace();
            return null;
        }
    }

    public List<Cd> findByNummers(List<String> nummers)
    {
        Session session = this.sessionFactory.openSession();
        try
        {
            Criteria cr = session.createCriteria(Cd.class);

            if (nummers != null && nummers.size() > 0)
                cr.add(Restrictions.in("nummers", nummers));

            List<Cd> list = cr.list();
            session.getTransaction().commit();
            return list;
        } catch (HibernateException e)
        {
            session.getTransaction().rollback();
            e.printStackTrace();
            return null;
        }
    }

    public Cd save(Cd cd)
    {
        return super.makePersistent(cd);
    }

    public void save(List<Cd> cd)
    {
        importBatch(cd);
    }

    public Cd findById(long id)
    {
        return super.findById(id, false);
    }

    public List<Cd> FindByExample(Cd b)
    {
        return null;
    }

	public void delete(Cd d) {
		super.makeTransient(d);
		
	}
}