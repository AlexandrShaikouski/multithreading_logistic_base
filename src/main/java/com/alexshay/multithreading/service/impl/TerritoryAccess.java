package com.alexshay.multithreading.service.impl;

import com.alexshay.multithreading.service.Activity;

public class TerritoryAccess implements Activity {
    @Override
    public void doActivity(String name) {
        LOGGER.info("Waiting near enterance " + name);
    }
}
