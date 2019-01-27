package com.alexshay.multithreading.dao.builder.impl;

import com.alexshay.multithreading.dao.Constants;
import com.alexshay.multithreading.dao.builder.Builder;
import com.alexshay.multithreading.dao.exception.DAOFileException;
import com.alexshay.multithreading.dao.exception.XMLParserDAOException;
import com.alexshay.multithreading.entity.Van;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Semaphore;

import static org.junit.Assert.*;

public class VanSAXBuilderTest {
    private Builder sAXGemBuilder;
    private Semaphore territory;
    private Semaphore terminal;

    @Before
    public void setUp() {
        sAXGemBuilder = new VanSAXBuilder();
        territory = Constants.TERRITORY;
        terminal = Constants.TERMINAL;
    }

    @Test
    public void buildFirstXML() throws XMLParserDAOException, DAOFileException {
        List<Van> vans = sAXGemBuilder.build();
        assertEquals(20, vans.size());
    }

    @Test
    public void buildSecondXML() throws XMLParserDAOException, DAOFileException {
        List<Van> vans = sAXGemBuilder.build();
        assertEquals(vans.get(1).getName(), "Mike");
    }

    @Test
    public void buildThirdFourthXML() throws XMLParserDAOException, DAOFileException {
        sAXGemBuilder.build();
    }

    @Test
    public void buildFourthFourthXML() throws XMLParserDAOException, DAOFileException {
        List<Van> actual = sAXGemBuilder.build();
        List<Van> vans = new ArrayList<>();
        vans.add(new Van("Alex", territory, terminal, false, false));
        vans.add(new Van("Mike", territory, terminal, false, false));
        vans.add(new Van("Felix", territory, terminal, true, false));
        vans.add(new Van("Zeus", territory, terminal, false, true));
        vans.add(new Van("Din", territory, terminal, true, true));
        vans.add(new Van("Gem", territory, terminal, false, false));
        vans.add(new Van("Max", territory, terminal, false, false));
        vans.add(new Van("Odin", territory, terminal, true, false));
        vans.add(new Van("Dionis", territory, terminal, true, false));
        vans.add(new Van("Lex", territory, terminal, true, false));
        vans.add(new Van("alex", territory, terminal, false, false));
        vans.add(new Van("mike", territory, terminal, false, false));
        vans.add(new Van("felix", territory, terminal, true, false));
        vans.add(new Van("zeus", territory, terminal, false, true));
        vans.add(new Van("din", territory, terminal, true, true));
        vans.add(new Van("gem", territory, terminal, false, false));
        vans.add(new Van("max", territory, terminal, false, false));
        vans.add(new Van("odin", territory, terminal, true, false));
        vans.add(new Van("dionis", territory, terminal, true, false));
        vans.add(new Van("lex", territory, terminal, true, false));
        assertEquals(vans, actual);
    }

    @After
    public void tearDown() {
        sAXGemBuilder = null;
    }
}