package com.patrycjap.model;

import com.patrycjap.api.Model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DataSource implements Model {
    public static final String DB_NAME = "pet_database.db";
    public static final String CONNECTION_STRING = "jdbc:sqlite:/home/patrioshka/IdeaProjects/PetAdoption/" + DB_NAME;

    public static final String TABLE_DOGS = "Dogs";
    public static final String COLUMN_DOGS_NAME = "Name";
    public static final String COLUMN_DOGS_AGE = "Age";
    public static final String COLUMN_DOGS_BREED = "Breed";
    public static final int INDEX_DOGS_NAME = 1;
    public static final int INDEX_DOGS_AGE = 2;
    public static final int INDEX_DOGS_BREED = 3;

    public static final String TABLE_CATS = "Cats";
    public static final String COLUMN_CATS_NAME = "Name";
    public static final String COLUMN_CATS_AGE = "Age";
    public static final int INDEX_CATS_NAME = 1;
    public static final int INDEX_CATS_AGE = 2;

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
    public void printListOfDogs() {
        List<Dog> dogs = queryDogs(ORDER_BY_NONE);
        if (dogs == null) {
            System.out.println("No dogs in base.");
            return;
        }
        for (Dog dog : dogs) {
            System.out.println("Name: " + dog.getName() + ", age: "
                    + dog.getAge() + ", breed: " + dog.getBreed() + ".");
        }
    }

    @Override
    public void printListOfCats() {
        List<Cat> cats = queryCats(ORDER_BY_NONE);
        if (cats == null) {
            System.out.println("No cats");
            return;
        }
        for (Cat cat : cats) {
            System.out.println("Name: " + cat.getName() + ", age: " + cat.getAge() + ".");
        }
    }

    @Override
    public void addNewRecordDog() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter pet's name:\n ");
        String name = scanner.nextLine();
        System.out.println("Choose pet's age (PUPPY/JUNIOR/SENIOR):\n ");
        String age = scanner.nextLine();
        System.out.println("Enter pet's breed:\n ");
        String breed = scanner.nextLine();

        try (Statement statement = connection.createStatement()) {
            statement.execute("INSERT INTO " + TABLE_DOGS +
                    " (" + COLUMN_DOGS_NAME + ", " +
                    COLUMN_DOGS_AGE + ", " +
                    COLUMN_DOGS_BREED +
                    " ) " +
                    "VALUES( '" + name + "','" + age + "',' " + breed + "')");
        } catch (SQLException e) {
            System.out.println("Something gone wrong.");
        }
    }

    @Override
    public void addNewRecordCat() {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter pet's name:\n ");
        String name = scanner.nextLine();
        System.out.println("Choose pet's age (KITTEN/JUNIOR/SENIOR):\n ");
        String age = scanner.nextLine();
        try (Statement statement = connection.createStatement()) {
            statement.execute("INSERT INTO " + TABLE_CATS +
                    " ("+ COLUMN_CATS_NAME + ", " +
                    COLUMN_CATS_AGE +
                    " ) " +
                    "VALUES( '"  + name + "','" + age + "')");
        } catch (SQLException e) {
            System.out.println("Something gone wrong.");
        }
    }

    @Override
    public void removeAnItemDog() {
        Scanner scanner = new Scanner(System.in);
        String itemToRemove = scanner.nextLine();

        try (Statement statement = connection.createStatement()) {
            statement.execute("DELETE FROM " + TABLE_DOGS +
                    " WHERE " + COLUMN_DOGS_NAME + " = '" + itemToRemove + "'");
            System.out.println("From now this is your pet!");

        } catch (SQLException e) {
            System.out.println("Sorry, we do not have a pet with that name :( ");
        }
    }

    @Override
    public void removeAnItemCat() {

        Scanner scanner = new Scanner(System.in);
        String itemToRemove = scanner.nextLine();

        try (Statement statement = connection.createStatement()) {
            statement.execute("DELETE FROM " + TABLE_CATS +
                    " WHERE " + COLUMN_CATS_NAME + " = " + itemToRemove);
            System.out.println("From now this is your pet!");

        } catch (SQLException e) {
            System.out.println("Sorry, we do not have a pet with that name :( ");
        }
    }

    @Override
    public void getCountDogs() {

        try(Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT count(" + COLUMN_DOGS_NAME + ") FROM " +
                    TABLE_DOGS)){
            resultSet.next();
            int count = resultSet.getInt(1);
            System.out.println("In our base we have " + count + " pets waiting for an adoption.\n");

        }catch (SQLException e){
            System.out.println("Something gone wrong." + e.getMessage());
        }
    }

    @Override
    public void getCountCats() {
        try(Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT count(" + COLUMN_CATS_NAME + ") FROM " +
                    TABLE_CATS)){
            resultSet.next();
            int count = resultSet.getInt(1);
            System.out.println("In our base we have " + count + " pets waiting for an adoption.\n");

        }catch (SQLException e){
            System.out.println("Something gone wrong." + e.getMessage());
        }

    }
}
