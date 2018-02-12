package com.patrycjap;

import com.patrycjap.core.CoreImplem;
import com.patrycjap.model.Cat;
import com.patrycjap.model.DataSource;
import com.patrycjap.model.Dog;
import com.patrycjap.ui.ConsoleUIImplem;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        DataSource dataSource = new DataSource();

        CoreImplem coreImplem = new CoreImplem();
        ConsoleUIImplem consoleUi = new ConsoleUIImplem();

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


        boolean isRunning = true;
        while (isRunning) {
            consoleUi.printMessage("\nPet Adoption\nWelcome! You are here to choose a PET for adoption.");
            consoleUi.printMessage("1. Adopt a pet.\n2. Give a pet up for an adoption.\n3. Exit.");
            int choice = Integer.parseInt(consoleUi.getInput());
            if (choice == 1) {
                consoleUi.printMessage("1. Adopt a dog.\n2. Adopt a cat.\n3. Exit.");
                int choice2 = Integer.parseInt(consoleUi.getInput());
                if (choice2 == 1) {
//                    dataSource.getCount(""); //liczba zwierzat w bazie
                    dataSource.printListOfDogs();
                    consoleUi.printMessage("\nTo choose a dog, please type dog's number: ");
                    dataSource.removeAnItem();

                } else if (choice2 == 2) {
//                    dataSource.getCount("");
                    dataSource.printListOfCats();
                    consoleUi.printMessage("To choose a cat, please type cat's number: ");
                    dataSource.removeAnItem();

                } else if (choice2 == 3) {
                    consoleUi.printMessage("Thank you! :) ");
                    isRunning = false;
                }
            } else if (choice == 2) {
                consoleUi.printMessage("If you want to give a pet for an adoption,\nplease follow the instructions below." +
                        "\nChoose a what kind of pet want to give for an adoption:\n");
                consoleUi.printMessage("1. Dog.\n2. Cat.");
                int choice3 = Integer.parseInt(consoleUi.getInput());
                if (choice3 == 1) {
                    coreImplem.addNewItem(baseOfDogs);
                    consoleUi.printMessage("From now this pet is waiting for an adoption.");

                } else if (choice3 == 2) {
                    coreImplem.addNewItem(baseOfCats);
                    consoleUi.printMessage("From now this pet is waiting for an adoption.");

                }
            } else if (choice == 3) {
                consoleUi.printMessage("Thank you! :) ");
                isRunning = false;
            }
        }
        coreImplem.exit();
    }
}
