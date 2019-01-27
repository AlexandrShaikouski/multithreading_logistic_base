package com.alexshay.multithreading.dao.builder.impl;

import com.alexshay.multithreading.dao.builder.Builder;
import com.alexshay.multithreading.dao.exception.DAOFileException;
import com.alexshay.multithreading.dao.exception.XMLParserDAOException;
import com.alexshay.multithreading.entity.Van;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class VanSAXBuilderTest {
    private Builder sAXGemBuilder;

    @Before
    public void setUp() {
        sAXGemBuilder = new VanSAXBuilder();
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
        vans.add(new Van("Alex", false, false));
        vans.add(new Van("Mike", false, false));
        vans.add(new Van("Felix", true, false));
        vans.add(new Van("Zeus", false, true));
        vans.add(new Van("Din", true, true));
        vans.add(new Van("Gem", false, false));
        vans.add(new Van("Max", false, false));
        vans.add(new Van("Odin", true, false));
        vans.add(new Van("Dionis", true, false));
        vans.add(new Van("Lex", true, false));
        vans.add(new Van("alex", false, false));
        vans.add(new Van("mike", false, false));
        vans.add(new Van("felix", true, false));
        vans.add(new Van("zeus", false, true));
        vans.add(new Van("din", true, true));
        vans.add(new Van("gem", false, false));
        vans.add(new Van("max", false, false));
        vans.add(new Van("odin", true, false));
        vans.add(new Van("dionis", true, false));
        vans.add(new Van("lex", true, false));
        assertEquals(vans, actual);
    }

    @After
    public void tearDown() {
        sAXGemBuilder = null;
    }
}