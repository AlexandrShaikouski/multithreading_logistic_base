package com.alexshay.multithreading.service;

import com.alexshay.multithreading.entity.Van;
import com.alexshay.multithreading.service.exception.ServiceFileException;
import com.alexshay.multithreading.service.exception.ServiceParserException;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class LogisticBase {
    private VanFactory vanFactory = VanFactory.getInstance();

    public void createLogisticBase() throws ServiceFileException, ServiceParserException {

        List<Van> vans = vanFactory.getVans();
        ExecutorService service = Executors.newFixedThreadPool(vans.size());
        vans.forEach(service::submit);
        service.shutdown();
    }
}
