package com.patrycjap.api;

import com.patrycjap.model.Cat;
import com.patrycjap.model.Dog;

import java.sql.Statement;
import java.util.List;


/**
 * Created by patrioshka on 2/7/18.
 */
public interface Model {
    boolean open();
    void close();
    List<Dog> queryDogs(int sortOrder);
    List<Cat> queryCats(int sortOrder);
    void addNewRecordDog(Statement statement, int id);
    void addNewRecordCat(Statement statement, int id);
    void removeAnItem();
}
