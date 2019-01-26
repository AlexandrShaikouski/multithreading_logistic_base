package com.alexshay.multithreading.dao;

import java.util.concurrent.Semaphore;

public interface Constants {
    Semaphore TERRITORY = new Semaphore(3);
    Semaphore TERMINAL = new Semaphore(2);
    String PATH_TO_XML = "resource\\xml\\vans.xml";
    String PATH_TO_XSD = "resource\\xsd\\van.xsd";
}
