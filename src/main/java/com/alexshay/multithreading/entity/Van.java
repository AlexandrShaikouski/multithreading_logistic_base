package com.alexshay.multithreading.entity;

import com.alexshay.multithreading.dao.Constants;
import com.alexshay.multithreading.service.Activity;
import com.alexshay.multithreading.service.impl.TerminalAccess;
import com.alexshay.multithreading.service.impl.TerritoryAccess;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.Serializable;
import java.util.Objects;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class Van implements Runnable, Serializable {
    private static final Logger LOGGER = LogManager.getLogger(Van.class);
    private static AtomicInteger atomicIntegerTerritory = new AtomicInteger(0);
    private static AtomicInteger atomicIntegerTerminal = new AtomicInteger(0);
    private static final Semaphore territory = Constants.TERRITORY;
    private static final Semaphore terminal = Constants.TERMINAL;
    private String name;
    private boolean isFull;
    private boolean isPerishableFoods;
    private transient Activity activity;

    public Van() {
        activity = new TerritoryAccess();
    }

    public Van(String name, boolean isFull, boolean isPerishableFoods) {
        this.name = name;
        this.isFull = isFull;
        this.isPerishableFoods = isPerishableFoods;
        activity = new TerritoryAccess();
        if (isPerishableFoods) {
            atomicIntegerTerritory.incrementAndGet();
        }
    }

    public void run() {
        while (activity != null) {

            try {
                if (activity instanceof TerritoryAccess) {
                    doActivity(atomicIntegerTerritory, territory);
                    if (isPerishableFoods) {
                        atomicIntegerTerritory.decrementAndGet();
                        atomicIntegerTerminal.incrementAndGet();
                    }

                } else if (activity instanceof TerminalAccess) {
                    doActivity(atomicIntegerTerminal, terminal);
                    if (isPerishableFoods) {
                        atomicIntegerTerminal.decrementAndGet();
                    }
                } else {
                    activity.doActivity(name);
                    TimeUnit.SECONDS.sleep(1);
                    isFull = !isFull;
                }

            } catch (InterruptedException e) {
                LOGGER.error(e);
            } finally {
                activity = Activity.changeActivity(activity);
            }
        }

    }

    private void doActivity(AtomicInteger atomicInteger, Semaphore semaphore) throws InterruptedException {
        if (atomicInteger.get() != 0 && !isPerishableFoods) {
            TimeUnit.MILLISECONDS.sleep(10);
        }
        semaphore.acquire();
        activity.doActivity(name);
        TimeUnit.SECONDS.sleep(1);
        semaphore.release();
    }


    public void setName(String name) {
        this.name = name;
    }

    public void setFull(boolean full) {
        isFull = full;
    }

    public String getName() {
        return name;
    }

    public boolean isFull() {
        return isFull;
    }

    public Activity getActivity() {
        return activity;
    }

    public boolean isPerishableFoods() {
        return isPerishableFoods;
    }

    public void setPerishableFoods(boolean perishableFoods) {
        if (perishableFoods) {
            atomicIntegerTerritory.incrementAndGet();
        }
        isPerishableFoods = perishableFoods;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Van van = (Van) o;
        return isFull == van.isFull &&
                isPerishableFoods == van.isPerishableFoods &&
                Objects.equals(name, van.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, isFull, isPerishableFoods);
    }

    @Override
    public String toString() {
        return "Van{" +
                "name='" + name + '\'' +
                '}';
    }
}
