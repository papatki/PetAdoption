package com.patrycjap;

import com.patrycjap.model.Cat;
import com.patrycjap.model.DataSource;
import com.patrycjap.model.Dog;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        DataSource dataSource = new DataSource();
        if (!dataSource.open()){
            System.out.println("Can't open data source.");
            return;
        }

        List<Dog> dogs = dataSource.queryDogs(DataSource.ORDER_BY_NONE);
        if (dogs == null) {
            System.out.println("No dogs");
            return;
        }
        for (Dog dog : dogs) {
            System.out.println(dog.getId() + ". Name: " + dog.getName() + ", age: "
                                    + dog.getAge() + ", breed: " + dog.getBreed());
        }

        System.out.println("*********************************");


        List<Cat> cats = dataSource.queryCats(DataSource.ORDER_BY_ASC);
        if (cats == null) {
            System.out.println("No cats");
            return;
        }
        for (Cat cat : cats) {
            System.out.println(cat.getId() + ". Name: " + cat.getName() + ", age: " + cat.getAge());
        }

        dataSource.close();





//        DatabaseImpl database = new DatabaseImpl();
//        CoreImplem coreImplem = new CoreImplem();
//        ConsoleUIImplem consoleUi = new ConsoleUIImplem();
//
////      Creating database of dogs for an adoption
//        Map<String, String> baseOfDogs = new LinkedHashMap<>();
//
//        Pet dog1 = new Pet("Rex", "PUPPY", "PUG");
//        Pet dog2 = new Pet("Pepe", "JUNIOR", "PUG");
//        Pet dog3 = new Pet("Elo", "SENIOR", "PUG");
//        Pet dog4 = new Pet("As", "JUNIOR", "LABRADOR");
//        Pet dog5 = new Pet("Avi", "PUPPY", "LABRADOR");
//
//        database.addItemToBase(baseOfDogs, dog1.getName(), dog1.getAge(), dog1);
//        database.addItemToBase(baseOfDogs, dog2.getName(), dog2.getAge(), dog2);
//        database.addItemToBase(baseOfDogs, dog3.getName(), dog3.getAge(), dog3);
//        database.addItemToBase(baseOfDogs, dog4.getName(), dog4.getAge(), dog4);
//        database.addItemToBase(baseOfDogs, dog5.getName(), dog5.getAge(), dog5);
//
////      Creating database of cats for an adoption
//        Map<String, String> baseOfCats = new LinkedHashMap<>();
//
//        Pet cat1 = new Pet("Snow", "KITTEN", "PERSIAN");
//        Pet cat2 = new Pet("Nero", "JUNIOR", "JUST CAT");
//        Pet cat3 = new Pet("Kitty", "SENIOR", "JUST CAT");
//        Pet cat4 = new Pet("Batman", "SENIOR", "JUST CAT");
//        Pet cat5 = new Pet("Pen", "JUNIOR", "MAINE COON");
//
//        database.addItemToBase(baseOfCats, cat1.getName(), cat1.getAge(), cat1);
//        database.addItemToBase(baseOfCats, cat2.getName(), cat2.getAge(), cat2);
//        database.addItemToBase(baseOfCats, cat3.getName(), cat3.getAge(), cat3);
//        database.addItemToBase(baseOfCats, cat4.getName(), cat4.getAge(), cat4);
//        database.addItemToBase(baseOfCats, cat5.getName(), cat5.getAge(), cat5);
//
//        boolean isRunning = true;
//        while (isRunning) {
//            consoleUi.printMessage("\nPet Adoption\nWelcome! You are here to choose a PET for adoption.");
//            consoleUi.printMessage("1. Adopt a pet.\n2. Give a pet up for an adoption.\n3. Exit.");
//            int choice = Integer.parseInt(consoleUi.getInput());
//            if (choice == 1) {
//                consoleUi.printMessage("1. Adopt a dog.\n2. Adopt a cat.\n3. Exit.");
//                int choice2 = Integer.parseInt(consoleUi.getInput());
//                if (choice2 == 1) {
//                    database.getAnAmountOfPetsInBase(baseOfDogs);
//                    database.printAList(baseOfDogs);
//                    consoleUi.printMessage("\nTo choose a dog, please type dog's name: ");
//                    coreImplem.removeAnItem(baseOfDogs);
//
//                } else if (choice2 == 2) {
//                    database.getAnAmountOfPetsInBase(baseOfCats);
//                    database.printAList(baseOfCats);
//                    consoleUi.printMessage("To choose a cat, please type cat's name: ");
//                    coreImplem.removeAnItem(baseOfCats);
//
//                } else if (choice2 == 3) {
//                    consoleUi.printMessage("Thank you! :) ");
//                    isRunning = false;
//                }
//            } else if (choice == 2) {
//                consoleUi.printMessage("If you want to give a pet for an adoption,\nplease follow the instructions below." +
//                        "\nChoose a what kind of pet want to give for an adoption:\n");
//                consoleUi.printMessage("1. Dog.\n2. Cat.");
//                int choice3 = Integer.parseInt(consoleUi.getInput());
//                if (choice3 == 1) {
//                    coreImplem.addNewItem(baseOfDogs);
//                    consoleUi.printMessage("From now this pet is waiting for an adoption.");
//
//                } else if (choice3 == 2) {
//                    coreImplem.addNewItem(baseOfCats);
//                    consoleUi.printMessage("From now this pet is waiting for an adoption.");
//
//                }
//            } else if (choice == 3) {
//                consoleUi.printMessage("Thank you! :) ");
//                isRunning = false;
//            }
//        }
//        coreImplem.exit();
    }
}
