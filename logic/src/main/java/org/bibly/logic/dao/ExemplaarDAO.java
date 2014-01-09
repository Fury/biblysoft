package org.bibly.logic.dao;

import org.bibly.logic.dao.interfaces.IExemplaarDAO;
import org.bibly.logic.dao.interfaces.IUitleningDetailsDAO;
import org.bibly.logic.dao.interfaces.genericDAO.GenericHibernateDAO;
import org.bibly.logic.models.ItemExemplaar;
import org.bibly.logic.models.UitleningDetail;
import org.hibernate.CacheMode;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;

@Repository("exemplaarDAO")
@Transactional
public class ExemplaarDAO extends GenericHibernateDAO<ItemExemplaar, Long> implements IExemplaarDAO
{
    @Resource
    private IUitleningDetailsDAO uitleningdetailsDAO;

    public ItemExemplaar save(ItemExemplaar itemExemplaar)
    {
        return super.makePersistent(itemExemplaar);
    }

    public void save(List<ItemExemplaar> itemExemplaar)
    {
        importBatch(itemExemplaar);
    }

    public boolean isBeschikbaar(long itemID)
    {
        UitleningDetail detail = uitleningdetailsDAO.LaatsteUitleningExemplaar(itemID);
        if (detail != null)
        {
            if (detail.getDatumBinnenGebracht() == null)
            {
                return false;
            }

        }
        return true;
    }

    public void importBatch(Collection objs)
    {

        try
        {
            Session session = this.sessionFactory.openSession();
            session.setCacheMode(CacheMode.IGNORE);

            for (Object o : objs)
            {
                session.save(o);
                if (objs.size() == 0)
                {
                    session.getTransaction().commit();
                    session.clear();
                }
            }

            session.close();
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public List<ItemExemplaar> findByExamplaar(ItemExemplaar ex)
    {
        Session session = this.sessionFactory.openSession();
        try
        {
            Criteria cr = session.createCriteria(ItemExemplaar.class);

            if (ex.getExemplaarID() > 0)
                cr.add(Restrictions.eq("exemplaar_ID", ex.getExemplaarID()));

            if (ex.getItem() != null)
                cr.add(Restrictions.eq("item", ex.getItem()));

            //needs completion
            //do not merge

            List<ItemExemplaar> list = cr.list();
            return list;
        } catch (HibernateException e)
        {
            session.getTransaction().rollback();
            e.printStackTrace();
            return null;
        }

    }

    public ItemExemplaar findById(long artikelNr)
    {
        return super.findById(artikelNr, false);
    }

    public void delete(ItemExemplaar exemplaar)
    {
        super.makeTransient(exemplaar);
    }
}
