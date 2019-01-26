package com.alexshay.multithreading.service.impl;

import com.alexshay.multithreading.service.Activity;

public class Terminal implements Activity {
    @Override
    public void doActivity(String name) {
        LOGGER.info("Unloading/loading of goods " + name);
    }
}
