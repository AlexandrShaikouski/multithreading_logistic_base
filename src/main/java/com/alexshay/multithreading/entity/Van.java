package com.alexshay.multithreading.entity;

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
    private String name;
    private Semaphore territory;
    private Semaphore terminal;
    private Activity activity;
    private boolean isFull;
    private boolean isPerishableFoods;

    public Van() {
        activity = new TerritoryAccess();
    }

    public Van(String name, Semaphore territory, Semaphore terminal, boolean isFull, boolean isPerishableFoods) {
        this.name = name;
        this.territory = territory;
        this.terminal = terminal;
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
                    doActivity(atomicIntegerTerritory);
                    if (isPerishableFoods) {
                        atomicIntegerTerritory.decrementAndGet();
                        atomicIntegerTerminal.incrementAndGet();
                    }

                } else if (activity instanceof TerminalAccess) {
                    doActivity(atomicIntegerTerminal);
                    if (isPerishableFoods) {
                        atomicIntegerTerminal.decrementAndGet();
                    }
                } else {
                    activity.doActivity(name);
                    TimeUnit.SECONDS.sleep(1);
                    isFull = !isFull;
                }
                activity = Activity.changeActivity(activity);
            } catch (InterruptedException e) {
                LOGGER.error(e);
            }
        }

    }

    private void doActivity(AtomicInteger atomicInteger) throws InterruptedException {
        if (atomicInteger.get() != 0 && !isPerishableFoods) {
            TimeUnit.MILLISECONDS.sleep(10);
        }
        territory.acquire();
        activity.doActivity(name);
        TimeUnit.SECONDS.sleep(1);
        territory.release();
    }

    public void setTerritory(Semaphore territory) {
        this.territory = territory;
    }

    public void setTerminal(Semaphore terminal) {
        this.terminal = terminal;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
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
                Objects.equals(name, van.name) &&
                Objects.equals(territory, van.territory) &&
                Objects.equals(terminal, van.terminal);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, territory, terminal, isFull, isPerishableFoods);
    }

    @Override
    public String toString() {
        return "Van{" +
                "name='" + name + '\'' +
                '}';
    }
}
