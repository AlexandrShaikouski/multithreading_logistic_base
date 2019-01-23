package com.alexshay.multithreading.entity;

import com.alexshay.multithreading.service.Activity;

import java.io.Serializable;
import java.util.concurrent.Semaphore;

public class Van implements Runnable, Serializable {
    private Semaphore territory;
    private Semaphore tetminal;
    private Activity activity;

    public void run() {

    }
}
