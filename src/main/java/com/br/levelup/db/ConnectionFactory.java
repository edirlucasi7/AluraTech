package com.br.levelup.db;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionFactory {

    public DataSource dataSource;

    public ConnectionFactory() {
        ComboPooledDataSource comboPooledDataSource = new ComboPooledDataSource();
        comboPooledDataSource.setJdbcUrl("jdbc:mysql://localhost/aluratech?useTimezone=true&serverTimezone=UTC");
        comboPooledDataSource.setUser("root");
        comboPooledDataSource.setPassword("123456");

        comboPooledDataSource.setMaxPoolSize(20);

        this.dataSource = comboPooledDataSource;
    }

    public Connection recoverConnection() throws SQLException {
            return dataSource.getConnection();
    }

}
