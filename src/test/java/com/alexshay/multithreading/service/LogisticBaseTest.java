package com.alexshay.multithreading.service;

import com.alexshay.multithreading.entity.Van;
import com.alexshay.multithreading.service.exception.ServiceFileException;
import com.alexshay.multithreading.service.exception.ServiceParserException;
import com.alexshay.multithreading.service.impl.TerritoryAccess;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;

public class LogisticBaseTest {
    private List<Van> vans;
    private Semaphore territory;
    private Semaphore terminal;
    private Van van;
    private ExecutorService service;

    @Before
    public void setParameters() throws ServiceFileException, ServiceParserException {
        vans = Arrays.asList(new Van("name",territory,terminal),
                new Van("name",territory,terminal),
                new Van("name",territory,terminal),
                new Van("name",territory,terminal),
                new Van("name",territory,terminal),
                new Van("name",territory,terminal),
                new Van("name",territory,terminal),
                new Van("name",territory,terminal),
                new Van("name",territory,terminal),
                new Van("name",territory,terminal));
        van = new Van("name",territory,terminal);
        service = Executors.newFixedThreadPool(1);
    }
    @Test
    public void checkVans(){
        vans.stream().forEach(s->service.submit(s));
        assertTrue(territory.tryAcquire());
    }
    @Test
    public void checkVanRunnable(){
        service.submit(new Van());
        assertFalse(service.isShutdown());
        service.shutdown();
        assertTrue(service.isShutdown());
    }
    @Test
    public void checkVanActivity(){
        Activity activity= van.getActivity();
        assertTrue(activity instanceof TerritoryAccess);
    }
    @After
    public void removeParameters(){
        vans = null;
        van = null;
        service = null;
    }

}