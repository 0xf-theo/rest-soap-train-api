package fr.train.rest.filter.repository;

import io.ebean.DB;
import io.ebean.Database;
import io.ebean.Finder;

public abstract class BeanFinder<T> extends Finder<Integer, T> {

    protected final Database server;

    public BeanFinder(Class<T> type) {
        super(type);
        this.server = DB.getDefault();
    }

    @Override
    public Database db() {
        return server;
    }
}