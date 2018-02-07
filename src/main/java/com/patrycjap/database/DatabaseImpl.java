package com.patrycjap.database;

import com.patrycjap.api.Database;
import com.patrycjap.items.Pet;

import java.util.Map;


public class DatabaseImpl implements Database {

    public void addItemToBase(Map<String, String> map, String key, String value, Pet pet) {
        map.put(key, value);
    }

    public void getAnAmountOfPetsInBase(Map<String, String> map) {
        System.out.println("In our base we have " + map.size() + " pets waiting for an adoption.\n");
    }

    public void printAList(Map<String, String> map ){
            for (Map.Entry entry : map.entrySet()) {
                System.out.println("Name: " + entry.getKey() + " ~ Age: " + entry.getValue());
            }
    }
}
