package org.bibly.logic.dao.interfaces.genericDAO;

import org.hibernate.Criteria;
import org.hibernate.NonUniqueObjectException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Example;

import javax.annotation.Resource;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.Collection;
import java.util.List;

public abstract class GenericHibernateDAO<T, ID extends Serializable> implements GenericDAO<T, ID> {

    private Class<T> persistentClass;

    @Resource(name = "sessionFactory")
    protected SessionFactory sessionFactory;

    public GenericHibernateDAO() {
        this.persistentClass = (Class<T>) ((ParameterizedType) getClass()
                .getGenericSuperclass()).getActualTypeArguments()[0];
    }

    public Session session() {
        if (sessionFactory.getCurrentSession().isOpen() == false)
            return sessionFactory.openSession();
        return sessionFactory.getCurrentSession();
    }

    public void setSession(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Class<T> getPersistentClass() {
        return persistentClass;
    }

    public T findById(ID id, boolean lock) {
        return (T) sessionFactory.getCurrentSession().get(getPersistentClass(), id);
    }

    @SuppressWarnings("unchecked")
    public List<T> findAll() {
        return findByCriteria();
    }

    @SuppressWarnings("unchecked")
    public List<T> findByExample(T exampleInstance) {
        return sessionFactory.getCurrentSession().createCriteria(getPersistentClass()).add(Example.create(exampleInstance)).list();
    }

    @SuppressWarnings("unchecked")
    public List<T> findByExample(T exampleInstance, String[] excludeProperty) {
        Session session = sessionFactory.getCurrentSession();
        Criteria crit = session.createCriteria(getPersistentClass());
        Example example = Example.create(exampleInstance);
        for (String exclude : excludeProperty) {
            example.excludeProperty(exclude);
        }
        crit.add(example);
        List<T> list = crit.list();
        return list;
    }

    @SuppressWarnings("unchecked")
    public T makePersistent(T entity) {
        try {
            if (sessionFactory.getCurrentSession().contains(entity))
                sessionFactory.getCurrentSession().merge(entity);
            else
                sessionFactory.getCurrentSession().saveOrUpdate(entity);
        } catch (NonUniqueObjectException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return entity;
        }
    }

    public void makeTransient(T entity) {
        sessionFactory.getCurrentSession().delete(entity);
    }

    public void flush() {
        sessionFactory.getCurrentSession().flush();
    }

    public void clear() {
        sessionFactory.getCurrentSession().clear();
    }

    /**
     * Use this inside subclasses as a convenience method.
     */
    @SuppressWarnings("unchecked")
    protected List<T> findByCriteria(Criterion... criterion) {
        Session session = session();
        Criteria crit = session.createCriteria(getPersistentClass());
        for (Criterion c : criterion) {
            crit.add(c);
        }
        List<T> list = crit.list();
        return list;
    }

    public void importBatch(Collection objs) {
        for (Object o : objs)
            makePersistent((T) o);
    }

}