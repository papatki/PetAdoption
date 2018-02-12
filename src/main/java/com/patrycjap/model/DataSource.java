package com.patrycjap.model;

import com.patrycjap.api.Model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by patrioshka on 2/7/18.
 */
public class DataSource implements Model {
    public static final String DB_NAME = "pet_database.db";
    public static final String CONNECTION_STRING = "jdbc:sqlite:/home/patrioshka/IdeaProjects/PetAdoption/" + DB_NAME;

    public static final String TABLE_DOGS = "Dogs";
    public static final String COLUMN_DOGS_ID = "id";
    public static final String COLUMN_DOGS_NAME = "Name";
    public static final String COLUMN_DOGS_AGE = "Age";
    public static final String COLUMN_DOGS_BREED = "Breed";
    public static final int INDEX_DOGS_ID = 1;
    public static final int INDEX_DOGS_NAME = 2;
    public static final int INDEX_DOGS_AGE = 3;
    public static final int INDEX_DOGS_BREED = 4;

    public static final String TABLE_CATS = "Cats";
    public static final String COLUMN_CATS_ID = "id";
    public static final String COLUMN_CATS_NAME = "Name";
    public static final String COLUMN_CATS_AGE = "Age";
    public static final int INDEX_CATS_ID = 1;
    public static final int INDEX_CATS_NAME = 2;
    public static final int INDEX_CATS_AGE = 3;

    public static final int ORDER_BY_NONE = 1;
    public static final int ORDER_BY_ASC = 2;
    public static final int ORDER_BY_DESC = 3;


    private Connection connection;


    @Override
    public boolean open() {
        try {
            connection = DriverManager.getConnection(CONNECTION_STRING);
            return true;

        } catch (SQLException e) {
            System.out.println("Couldn't connect to database: " + e.getMessage());
            return false;

        }
    }

    @Override
    public void close() {
        try {
            if (connection != null)
                connection.close();

        } catch (SQLException e) {
            System.out.println("Couldn't close connection: " + e.getMessage());
        }

    }

    @Override
    public List<Dog> queryDogs(int sortOrder) {
        StringBuilder stringBuilder = new StringBuilder("SELECT * FROM ");
        stringBuilder.append(TABLE_DOGS);
        if (sortOrder != ORDER_BY_NONE) {
            stringBuilder.append(" ORDER BY ");
            stringBuilder.append(COLUMN_DOGS_NAME);
            stringBuilder.append(" COLLATE NOCASE ");
            if (sortOrder == ORDER_BY_DESC) {
                stringBuilder.append("DESC");
            } else {
                stringBuilder.append("ASC");
            }
        }

        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(stringBuilder.toString())) {
            List<Dog> dogs = new ArrayList<>();
            while (resultSet.next()) {
                Dog dog = new Dog();
                dog.setId(resultSet.getInt(INDEX_DOGS_ID));
                dog.setName(resultSet.getString(INDEX_DOGS_NAME));
                dog.setAge(resultSet.getString(INDEX_DOGS_AGE));
                dog.setBreed(resultSet.getString(INDEX_DOGS_BREED));
                dogs.add(dog);
            }
            return dogs;

        } catch (SQLException e) {
            System.out.println("Exception: " + e.getMessage());
            return null;
        }
    }

    @Override
    public List<Cat> queryCats(int sortOrder) {
        StringBuilder stringBuilder = new StringBuilder("SELECT * FROM ");
        stringBuilder.append(TABLE_CATS);
        if (sortOrder != ORDER_BY_NONE) {
            stringBuilder.append(" ORDER BY ");
            stringBuilder.append(COLUMN_CATS_NAME);
            stringBuilder.append(" COLLATE NOCASE ");
            if (sortOrder == ORDER_BY_DESC) {
                stringBuilder.append("DESC");
            } else {
                stringBuilder.append("ASC");
            }
        }

        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(stringBuilder.toString())) {
            List<Cat> cats = new ArrayList<>();
            while (resultSet.next()) {
                Cat cat = new Cat();
                cat.setId(resultSet.getInt(INDEX_CATS_ID));
                cat.setName(resultSet.getString(INDEX_CATS_NAME));
                cat.setAge(resultSet.getString(INDEX_CATS_AGE));
                cats.add(cat);
            }
            return cats;

        } catch (SQLException e) {
            System.out.println("Exception: " + e.getMessage());
            return null;
        }
    }

    @Override
    public void addNewRecordDog(Statement statement, int id) {
        System.out.println("Enter pet's name:\n ");
        Scanner scanner = new Scanner(System.in);
        String name = scanner.nextLine();
        System.out.println("Choose pet's age (PUPPY/JUNIOR/SENIOR):\n ");
        String age = scanner.nextLine();
        System.out.println("Enter pet's breed:\n ");
        String breed = scanner.nextLine();

        try {
            statement.execute("INSERT INTO " + TABLE_DOGS +
                    " (" + COLUMN_DOGS_ID + ", " +
                    COLUMN_DOGS_NAME + ", " +
                    COLUMN_DOGS_AGE + ", " +
                    COLUMN_DOGS_BREED +
                    " ) " +
                    "VALUES(" + id + ", '" + name + "','" + age + "',' " + breed + "')");
        }catch (SQLException e) {
            System.out.println("Something gone wrong.");
        }



    }

    @Override
    public void addNewRecordCat(Statement statement, int id) {
        System.out.println("Enter pet's name:\n ");
        Scanner scanner = new Scanner(System.in);
        String name = scanner.nextLine();
        System.out.println("Choose pet's age (KITTEN/JUNIOR/SENIOR):\n ");
        String age = scanner.nextLine();
        try{
            statement.execute("INSERT INTO " + TABLE_CATS +
                    " (" + COLUMN_CATS_ID + ", " +
                    COLUMN_CATS_NAME + ", " +
                    COLUMN_CATS_AGE +
                    " ) " +
                    "VALUES(" + id + ", '" + name + "','" + age +  "')");
        }catch (SQLException e){
            System.out.println("Something gone wrong.");
        }

    }

    @Override
    public void removeAnItem() {

    }















































}
