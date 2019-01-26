package com.alexshay.multithreading.service.impl;

import com.alexshay.multithreading.service.Activity;

public class TerminalAccess implements Activity {
    @Override
    public void doActivity(String name) {
        LOGGER.info("Waiting near terminal " + name);
    }
}
