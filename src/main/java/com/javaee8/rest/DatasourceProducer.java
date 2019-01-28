package com.javaee8.rest;

import javax.annotation.Resource;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.sql.DataSource;

@ApplicationScoped
public class DatasourceProducer {

    @Resource(lookup = "java:/jdbc/market/bookDs")
    private DataSource bookDatasource;

    @Produces
    public DataSource createDatasource(){
        return bookDatasource;
    }
}
