package com.patrycjap.api;

import java.util.Map;


public interface CoreAPI {
    void exit();
    void addNewItem(Map<String, String> map);
    void removeAnItem(Map<String, String> map);

}
