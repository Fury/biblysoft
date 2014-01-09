package org.bibly.logic.dao;

import org.bibly.logic.dao.interfaces.IPersoonDAO;
import org.bibly.logic.dao.interfaces.genericDAO.GenericHibernateDAO;
import org.bibly.logic.models.Persoon;
import org.bibly.logic.models.enums.PersoonType;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository("persoonDAO")
@Transactional
public class PersoonDAO extends GenericHibernateDAO<Persoon, Long> implements IPersoonDAO
{

    public Persoon save(Persoon persoon)
    {
        return super.makePersistent(persoon);
    }

    public void save(List<Persoon> persoon)
    {
        importBatch(persoon);
    }

    public Persoon findById(long id)
    {
        return super.findById(id, false);
    }

    public List<Persoon> FindByExample(Persoon b)
    {
        return super.findByExample(b);
    }

    public List<Persoon> findByExample(Persoon p)
    {
        Session session = this.sessionFactory.openSession();
        try
        {
            Criteria cr = session.createCriteria(Persoon.class);


            if (p.getPersoonID() > 0)
                cr.add(Restrictions.eq("persoon_ID", p.getPersoonID()));

            if (p.getAdres() != null)
                cr.add(Restrictions.eq("adres", p.getAdres()));

            if (p.getPermissie() != null)
                cr.add(Restrictions.eq("permissie", p.getPermissie()));

            if (p.getAanmaakDatum() != null)
                cr.add(Restrictions.eqOrIsNull("aanmaakDatum", p.getAanmaakDatum()));

            if (p.getGeboorteDatum() != null)
                cr.add(Restrictions.eqOrIsNull("geboorteDatum", p.getGeboorteDatum()));

            List<Persoon> list = cr.list();
            session.getTransaction().commit();
            return list;
        } catch (HibernateException e)
        {
            session.getTransaction().rollback();
            e.printStackTrace();
            return null;
        }
    }

    public List<Persoon> findByPermissie(PersoonType permissie)
    {
        Session session = this.sessionFactory.openSession();
        try
        {
            session.beginTransaction();
            Criteria cr = session.createCriteria(Persoon.class);

            if (permissie != null)
                cr.add(Restrictions.eq("permissie", permissie));

            List<Persoon> list = cr.list();
            session.getTransaction().commit();
            return list;
        } catch (HibernateException e)
        {
            session.getTransaction().rollback();
            e.printStackTrace();
            return null;
        }
    }
}