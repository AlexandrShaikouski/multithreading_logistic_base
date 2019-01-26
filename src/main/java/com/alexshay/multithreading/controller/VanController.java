package com.alexshay.multithreading.controller;

import com.alexshay.multithreading.service.LogisticBase;
import com.alexshay.multithreading.service.exception.ServiceFileException;
import com.alexshay.multithreading.service.exception.ServiceParserException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class VanController {
    private static final Logger LOGGER = LogManager.getLogger(VanController.class);
    public static void main(String[] args) {
        LogisticBase logisticBase = new LogisticBase();
        try {
            logisticBase.createLogisticBase();
        } catch (ServiceFileException e) {
            LOGGER.error("Problem with XML file", e);
        } catch (ServiceParserException e) {
            LOGGER.error("Problem with Parser", e);
        }

    }
}
