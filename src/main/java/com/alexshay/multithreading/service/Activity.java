package com.alexshay.multithreading.service;

import com.alexshay.multithreading.service.impl.Terminal;
import com.alexshay.multithreading.service.impl.TerminalAccess;
import com.alexshay.multithreading.service.impl.TerritoryAccess;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public interface Activity {
    Logger LOGGER = LogManager.getLogger(Activity.class);
    Lock LOCK = new ReentrantLock();

    void doActivity(String name);

    static Activity changeActivity(Activity activity) {

        try {
            LOCK.lock();
            if (activity instanceof TerritoryAccess) {
                return new TerminalAccess();
            } else if (activity instanceof TerminalAccess) {
                return new Terminal();
            } else {
                return null;
            }
        } finally {
            LOCK.unlock();
        }

    }
}
