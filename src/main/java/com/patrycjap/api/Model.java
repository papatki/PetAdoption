package com.patrycjap.api;

import com.patrycjap.model.Cat;
import com.patrycjap.model.Dog;

import java.util.List;


public interface Model {
    boolean open();
    void close();
    List<Dog> queryDogs(int sortOrder);
    List<Cat> queryCats(int sortOrder);
    void printListOfDogs();
    void printListOfCats();
    void addNewRecordDog();
    void addNewRecordCat();
    void removeAnItemDog();
    void removeAnItemCat();
    void getCountDogs();
    void getCountCats();
}
