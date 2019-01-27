package com.alexshay.multithreading.service;

import com.alexshay.multithreading.dao.Constants;
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
        territory = Constants.TERRITORY;
        terminal = Constants.TERMINAL;
        vans = Arrays.asList(
                new Van("Alex", false, false),
                new Van("Mike", false, false),
                new Van("Felix", true, false),
                new Van("Zeus", false, true),
                new Van("Din", true, true),
                new Van("Gem", false, false),
                new Van("Max", false, false),
                new Van("Odin", true, false),
                new Van("Dionis", true, false),
                new Van("Lex", true, false),
                new Van("alex", false, false),
                new Van("mike", false, false),
                new Van("felix", true, false),
                new Van("zeus", false, true),
                new Van("din", true, true),
                new Van("gem", false, false),
                new Van("max", false, false),
                new Van("odin", true, false),
                new Van("dionis", true, false),
                new Van("lex", true, false));

        van = new Van("name", true, false);
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
        territory = null;
        terminal = null;
    }

}