package com.alexshay.multithreading.dao.builder.impl;

import com.alexshay.multithreading.dao.builder.Builder;
import com.alexshay.multithreading.dao.exception.DAOFileException;
import com.alexshay.multithreading.dao.exception.XMLParserDAOException;
import com.alexshay.multithreading.entity.Van;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

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
        assertEquals(20,vans.size());
    }
    @Test
    public void buildSecondXML() throws XMLParserDAOException, DAOFileException {
        List<Van> vans = sAXGemBuilder.build();
        assertEquals(vans.get(1).getName(),"Mike");
    }
    @Test
    public void buildThirdFourthXML() throws XMLParserDAOException, DAOFileException {
        sAXGemBuilder.build();
    }

    @After
    public void tearDown() {
        sAXGemBuilder = null;
    }
}