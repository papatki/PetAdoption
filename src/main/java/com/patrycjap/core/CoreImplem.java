package com.patrycjap.core;

import com.patrycjap.api.CoreAPI;
import com.patrycjap.items.Pet;

import java.util.Map;
import java.util.Scanner;

public class CoreImplem implements CoreAPI {
    @Override
    public void exit() {
    }

    @Override
    public void addNewItem(Map<String, String> map) {
        System.out.println("Enter pet's name:\n ");
        Scanner scanner = new Scanner(System.in);
        String name = scanner.nextLine();
        System.out.println("Choose pet's age (PUPPY/KITTEN/JUNIOR/SENIOR):\n ");
        String age = scanner.nextLine();
        System.out.println("Enter pet's breed:\n ");
        String breed = scanner.nextLine();

        Pet pet = new Pet(name, age, breed);
        map.put(pet.getName(), pet.getAge());

    }

    @Override
    public void removeAnItem(Map<String, String> map) {
        Scanner scanner = new Scanner(System.in);
        String itemToRemove = scanner.nextLine();

        if (map.containsKey(itemToRemove)) {
            map.remove(itemToRemove);
            System.out.println("From now " + itemToRemove + " is your pet!");
        } else {
            System.out.println("Sorry, we do not have a pet with that name :( ");
        }

    }

}
