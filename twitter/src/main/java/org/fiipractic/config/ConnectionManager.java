package org.fiipractic.config;

import org.hibernate.annotations.Cache;
import org.springframework.stereotype.Component;

import java.sql.Connection;

@Component
public class ConnectionManager {

    private ConnectionManager(){

    }

    private static final Connection connection;
    public static Connection getConnection(){
        if (connection == null){
            connection =  new Connection()

        }
        return connection;
    }
}
