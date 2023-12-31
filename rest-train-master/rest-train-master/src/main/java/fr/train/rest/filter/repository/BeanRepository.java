package fr.train.rest.filter.repository;

import io.ebean.Query;
import io.ebean.bean.EntityBean;

public abstract class BeanRepository<T> extends BeanFinder<T> {

    private Class<T> type;

    public BeanRepository(Class<T> type) {
        super(type);
        this.type = type;
    }

    public void markAsDirty(T bean) {
        db().markAsDirty(bean);
    }

    public void markPropertyUnset(T bean, String propertyName) {
        ((EntityBean) bean)._ebean_getIntercept().setPropertyLoaded(propertyName, false);
    }

    public void save(T bean) {
        db().save(bean);
    }

    public void update(T bean) {
        db().update(bean);
    }

    public void insert(T bean) {
        db().insert(bean);
    }

    public boolean delete(T bean) {
        return db().delete(bean);
    }

    public boolean deletePermanent(T bean) {
        return db().deletePermanent(bean);
    }

    public void refresh(T bean) {
        db().refresh(bean);
    }

    public Query<T> find() {
        return db().find(this.type);
    }
}