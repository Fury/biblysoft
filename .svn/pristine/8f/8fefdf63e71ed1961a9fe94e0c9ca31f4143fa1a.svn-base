package org.bibly.logic.dao;

import org.bibly.logic.dao.interfaces.IExemplaarDAO;
import org.bibly.logic.dao.interfaces.IUitleningDetailsDAO;
import org.bibly.logic.dao.interfaces.genericDAO.GenericHibernateDAO;
import org.bibly.logic.models.ItemExemplaar;
import org.bibly.logic.models.UitleningDetail;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Repository("uitleningdetailsDAO")
@Transactional
public class UitleningDetailsDAO extends GenericHibernateDAO<UitleningDetail, Long> implements IUitleningDetailsDAO
{

    @Qualifier("exemplaarDAO")
    @Autowired
    private IExemplaarDAO exemplaarDAO = new ExemplaarDAO();

    public UitleningDetail LaatsteUitleningExemplaar(long exemplaarID)
    {
        Session session = sessionFactory.getCurrentSession();
        try
        {

            Criteria cr = session.createCriteria(UitleningDetail.class);
            cr.addOrder(Order.asc("datumBinnenGebracht"));
            cr.createAlias("exemplaar", String.valueOf(exemplaarID));
            cr.setMaxResults(1);
            List<UitleningDetail> details = cr.list();
            return details.size() == 0 ? null : details.get(0);

        } catch (HibernateException e)
        {
            session.getTransaction().rollback();
            e.printStackTrace();
            return null;
        }

    }

    public UitleningDetail IsExemplaarBeschikbaar(long exemplaarID)
    {
        Session session = sessionFactory.getCurrentSession();
        try
        {
            UitleningDetail detail = null;

            Criteria cr = session.createCriteria(UitleningDetail.class);
            cr.createAlias("uitlening", "uit");
            cr.add(Restrictions.le("uitleenStartDatum", new Date()));

            session.getTransaction().commit();
            return detail;
        } catch (HibernateException e)
        {
            session.getTransaction().rollback();
            e.printStackTrace();
            return null;
        }

    }

    public UitleningDetail save(UitleningDetail detail)
    {
        return super.makePersistent(detail);
    }

    public void save(List<UitleningDetail> details)
    {
        importBatch(details);
    }

    public UitleningDetail findById(long id)
    {
        return super.findById(id, false);
    }

    public List<UitleningDetail> FindByExample(UitleningDetail b)
    {
        return super.findByExample(b);
    }

    public List<UitleningDetail> findAll()
    {
        return super.findAll();
    }

    public List<Date> findAllOccupied(long id)
    {
        Session session = sessionFactory.getCurrentSession();
        try
        {

            Criteria cr = session.createCriteria(UitleningDetail.class);
            ItemExemplaar e = exemplaarDAO.findById(id);
            cr.add(Restrictions.eq("exemplaar", e));

            List<UitleningDetail> reservaties = cr.list();
            List<Date> dates = new ArrayList<Date>();
            for (UitleningDetail u : reservaties)
            {
                if (u.getUitlening().getUitleenStartDatum().after(new Date()) || u.getUitlening().getUitleenStartDatum() == new Date())
                {
                    dates.add(u.getUitlening().getUitleenStartDatum());
                }
            }
            return dates;
        } catch (HibernateException e)
        {
            session.getTransaction().rollback();
            e.printStackTrace();
            return null;
        }
    }

}
