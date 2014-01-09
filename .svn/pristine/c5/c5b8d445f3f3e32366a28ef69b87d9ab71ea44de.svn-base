package org.bibly.logic.dao;

import org.bibly.logic.dao.interfaces.IAdresDAO;
import org.bibly.logic.dao.interfaces.genericDAO.GenericHibernateDAO;
import org.bibly.logic.models.Adres;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository("adresDAO")
@Transactional
public class AdresDAO extends GenericHibernateDAO<Adres, Long> implements IAdresDAO
{
    public List<Adres> FindByExample(Adres adres)
    {
        Session session = sessionFactory.getCurrentSession();
        try
        {
            Criteria cr = session.createCriteria(Adres.class);

            if (!adres.getStraatNaam().equals(""))
                cr.add(Restrictions.eq("straatNaam", adres.getStraatNaam()));
            if (!adres.getBus().equals(""))
                cr.add(Restrictions.eqOrIsNull("bus", adres.getBus()));
            if (adres.getHuisNummer() != 0)
                cr.add(Restrictions.eq("huisNummer", adres.getHuisNummer()));
            if (adres.getGemeente() != null)
                cr.add(Restrictions.eq("gemeente", adres.getGemeente()));

            List<Adres> adressen = cr.list();
            session.getTransaction().commit();
            return adressen;
        } catch (HibernateException e)
        {
            session.getTransaction().rollback();
            e.printStackTrace();
            return null;
        }

    }

    public Adres save(Adres adres)
    {
        return super.makePersistent(adres);
    }

    public void save(List<Adres> adressen)
    {
        importBatch(adressen);
    }

    public Adres findById(long id)
    {
        return super.findById(id, false);
    }
}