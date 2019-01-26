package com.alexshay.multithreading.service;

import com.alexshay.multithreading.service.impl.TerritoryAccess;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ActivityTest {
    private Activity activity;

    @Before
    public void setParameters() {
        activity = new TerritoryAccess();
    }

    @Test
    public void changeActivity() {
        activity = Activity.changeActivity(activity);
        activity = Activity.changeActivity(activity);
        activity = Activity.changeActivity(activity);
        assertNull(activity);
    }
}