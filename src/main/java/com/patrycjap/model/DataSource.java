package com.patrycjap.model;

import com.patrycjap.api.Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by patrioshka on 2/7/18.
 */
public class DataSource implements Model {
    public static final String DB_NAME = "pet_adoption_database.db";
    public static final String CONNECTION_STRING = "jdbc:sqlite:/home/patrioshka/IdeaProjects/PetAdoption/" + DB_NAME;
    private Connection connection;


    @Override
    public boolean open() {
        try{
            connection = DriverManager.getConnection(CONNECTION_STRING);
            return true;

        }catch (SQLException e ){
            System.out.println("Couldn't connect to database: " + e.getMessage());
            return false;

        }
    }

    @Override
    public void close() {
        try {
            if (connection != null)
                connection.close();

        }catch (SQLException e){
            System.out.println("Couldn't close connection: " + e.getMessage());
        }

    }
}
