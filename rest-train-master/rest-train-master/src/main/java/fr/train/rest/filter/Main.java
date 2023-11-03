package fr.train.rest.filter;

import com.zaxxer.hikari.HikariDataSource;
import fr.train.rest.filter.resource.*;
import io.ebean.DatabaseFactory;
import io.ebean.config.DatabaseConfig;
import org.restlet.Component;
import org.restlet.data.Protocol;


public class Main {
    public static void main(String[] args) throws Exception {
        String jdbcUrl = "jdbc:postgresql://ec2-52-215-68-14.eu-west-1.compute.amazonaws.com:5432/d54t6nioh254p5?sslmode=require";

        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setMaximumPoolSize(3);
        dataSource.setJdbcUrl(jdbcUrl);
        dataSource.setUsername("bixjncdjqdcpbq");
        dataSource.setPassword("eda6a6916ec158a03ef8e9f2da76b2359727b60f9aeb346dcff2094fc399e0d7");
        dataSource.setAutoCommit(false);
        dataSource.setDriverClassName("org.postgresql.Driver");

        var dbConfig = new DatabaseConfig();
        dbConfig.setDdlGenerate(true);
        dbConfig.setDdlRun(false);
        dbConfig.setDataSource(dataSource);

        DatabaseFactory.create(dbConfig);

        Component component = new Component();
        component.getServers().add(Protocol.HTTP, 8888);
        component.getDefaultHost().attach("/stations", StationsResource.class);
        component.getDefaultHost().attach("/train", TrainsResource.class);
        component.getDefaultHost().attach("/train/{id}", TrainResource.class);
        component.getDefaultHost().attach("/search", SearchResource.class);
        component.getDefaultHost().attach("/reservation", ReservationResource.class);
        component.getDefaultHost().attach("/docs", TrainsResource.class);
        component.start();
    }
}