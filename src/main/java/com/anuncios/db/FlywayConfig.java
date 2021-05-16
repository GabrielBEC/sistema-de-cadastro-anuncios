package com.anuncios.db;

import org.flywaydb.core.Flyway;

import static com.anuncios.db.Constants.*;

public class FlywayConfig {

    public static final void iniciarFlyway(){
        Flyway flyway = Flyway.configure().dataSource(HOST + DATABASE + "?useTimezone=true&serverTimezone=UTC&useSSL=false", USER, PASSWORD).load();

        flyway.migrate();
    }

}
