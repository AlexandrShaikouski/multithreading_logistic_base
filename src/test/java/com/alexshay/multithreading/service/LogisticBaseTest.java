package com.alexshay.multithreading.service;

import com.alexshay.multithreading.entity.Van;
import com.alexshay.multithreading.service.impl.TerritoryAccess;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

import static org.junit.Assert.*;

public class LogisticBaseTest {
    private List<Van> vans;
    private Semaphore territory;
    private Semaphore terminal;
    private Van van;
    private ExecutorService service;

    @Before
    public void setParameters() {
        territory = new Semaphore(3);
        terminal = new Semaphore(2);
        vans = Arrays.asList(new Van("name", territory, terminal, false, true),
                new Van("name", territory, terminal, true, false),
                new Van("name", territory, terminal, false, true),
                new Van("name", territory, terminal, true, false),
                new Van("name", territory, terminal, true, true),
                new Van("name", territory, terminal, false, false),
                new Van("name", territory, terminal, false, true),
                new Van("name", territory, terminal, true, false),
                new Van("name", territory, terminal, true, true),
                new Van("name", territory, terminal, false, false));
        van = new Van("name", territory, terminal, true, false);
        service = Executors.newFixedThreadPool(1);
    }

    @Test
    public void checkVans() {
        vans.forEach(s -> service.submit(s));
        assertTrue(territory.tryAcquire());
    }

    @Test
    public void checkVanRunnable() {
        service.submit(new Van());
        assertFalse(service.isShutdown());
        service.shutdown();
        assertTrue(service.isShutdown());
    }

    @Test
    public void checkVanActivity() {
        Activity activity = van.getActivity();
        assertTrue(activity instanceof TerritoryAccess);
    }

    @After
    public void removeParameters() {
        vans = null;
        van = null;
        service = null;
    }

}