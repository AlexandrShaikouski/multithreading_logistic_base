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
                new Van("Alex", territory, terminal, false, false),
                new Van("Mike", territory, terminal, false, false),
                new Van("Felix", territory, terminal, true, false),
                new Van("Zeus", territory, terminal, false, true),
                new Van("Din", territory, terminal, true, true),
                new Van("Gem", territory, terminal, false, false),
                new Van("Max", territory, terminal, false, false),
                new Van("Odin", territory, terminal, true, false),
                new Van("Dionis", territory, terminal, true, false),
                new Van("Lex", territory, terminal, true, false),
                new Van("alex", territory, terminal, false, false),
                new Van("mike", territory, terminal, false, false),
                new Van("felix", territory, terminal, true, false),
                new Van("zeus", territory, terminal, false, true),
                new Van("din", territory, terminal, true, true),
                new Van("gem", territory, terminal, false, false),
                new Van("max", territory, terminal, false, false),
                new Van("odin", territory, terminal, true, false),
                new Van("dionis", territory, terminal, true, false),
                new Van("lex", territory, terminal, true, false));

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
        territory = null;
        terminal = null;
    }

}