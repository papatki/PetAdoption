package com.patrycjap.api;

import com.patrycjap.items.Pet;

import java.util.Map;


public interface Database {
    void addItemToBase(Map<String, String> map, String key, String value, Pet pet);
    void getAnAmountOfPetsInBase(Map<String, String> map);
    void printAList(Map<String, String> map);
}
