package com.patrycjap.api;

import com.patrycjap.model.Cat;
import com.patrycjap.model.Dog;

import java.util.List;

/**
 * Created by patrioshka on 2/7/18.
 */
public interface Model {
    boolean open();
    void close();
    List<Dog> queryDogs(int sortOrder);
    List<Cat> queryCats(int sortOrder);
}
