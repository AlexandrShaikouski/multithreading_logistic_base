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
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Van implements Runnable, Serializable {
    private static final Logger LOGGER = LogManager.getLogger(Van.class);
    private static Lock lock = new ReentrantLock();
    private static Condition condition = lock.newCondition();
    private static AtomicInteger atomicInteger = new AtomicInteger(0);
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
        if(isPerishableFoods){
            atomicInteger.incrementAndGet();
        }
    }

    public void run() {
        while (activity != null) {

            try {
                if(atomicInteger.get() != 0 && !isPerishableFoods){
                    TimeUnit.MILLISECONDS.sleep(10);
                }
                if (activity instanceof TerritoryAccess) {
                    territory.acquire();
                    TimeUnit.SECONDS.sleep(1);
                    activity.doActivity(name);
                    territory.release();
                } else if (activity instanceof TerminalAccess) {
                    terminal.acquire();
                    TimeUnit.SECONDS.sleep(1);
                    activity.doActivity(name);
                    terminal.release();
                } else {
                    TimeUnit.SECONDS.sleep(1);
                    activity.doActivity(name);
                    isFull = !isFull;
                }
                activity = Activity.changeActivity(activity);
            } catch (InterruptedException e) {
                LOGGER.error(e);
            }
        }

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
        if(perishableFoods){
            atomicInteger.incrementAndGet();
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
                Objects.equals(terminal, van.terminal) &&
                Objects.equals(activity, van.activity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, territory, terminal, activity, isFull, isPerishableFoods);
    }

    @Override
    public String toString() {
        return "Van{" +
                "name='" + name + '\'' +
                '}';
    }
}
