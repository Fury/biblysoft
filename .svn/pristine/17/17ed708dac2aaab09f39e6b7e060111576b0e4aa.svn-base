package org.bibly.logic.dao;

import org.bibly.logic.dao.interfaces.IItemDAO;
import org.bibly.logic.dao.interfaces.genericDAO.GenericHibernateDAO;
import org.bibly.logic.models.Item;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository("itemDAO")
@Transactional
public class ItemDAO extends GenericHibernateDAO<Item, Long> implements IItemDAO
{
    public List<Item> FindByExample(Item i)
    {
        Session session = sessionFactory.getCurrentSession();
        try
        {
            Criteria cr = session.createCriteria(Item.class);

            if (!i.getTitel().equals(""))
                cr.add(Restrictions.like("titel", "%" + i.getTitel() + "%"));
            if (!i.getGenre().equals(""))
                cr.add(Restrictions.like("genre", "%" + i.getGenre() + "%"));
            if (!i.getUitgever().equals(""))
                cr.add(Restrictions.like("uitgever", "%" + i.getUitgever() + "%"));
            if (!i.getTaal().equals(""))
                cr.add(Restrictions.eq("taal", i.getTaal()));
            System.out.println(cr.toString());
            List<Item> items = cr.list();
            return items;
        } catch (Exception e)
        {
            e.printStackTrace();
        }
       /* catch (HibernateException e)
        {
            session.getTransaction().rollback();
            e.printStackTrace();
            return null;
        }*/
        return null;
    }

    public List<Item> findAll()
    {
        return super.findAll();
    }

    public Item findById(long id)
    {
        return super.findById(id, false);
    }

    public Item save(Item item)
    {
        return super.makePersistent(item);
    }

    public void save(List<Item> items)
    {
        importBatch(items);
    }
}
