package com.alexshay.multithreading.dao.builder.impl;

import com.alexshay.multithreading.dao.builder.Builder;
import com.alexshay.multithreading.dao.exception.DAOFileException;
import com.alexshay.multithreading.dao.exception.XMLParserDAOException;
import com.alexshay.multithreading.dao.validation.BaseValidator;
import com.alexshay.multithreading.dao.validation.impl.XMLValidatorByXSD;
import com.alexshay.multithreading.entity.Van;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class VanSAXBuilderTest {
    private BaseValidator validator;
    private Builder sAXGemBuilder;
    @Before
    public void setUp() {
        sAXGemBuilder = new VanSAXBuilder();
        validator = new XMLValidatorByXSD();
    }

    @Test
    public void buildFirstXML() throws XMLParserDAOException, DAOFileException {
        List<Van> vans = sAXGemBuilder.build();
        assertEquals(vans.size(),null);
    }
    @Test
    public void buildThirdXML() throws XMLParserDAOException, DAOFileException {
        List<Van> vans = sAXGemBuilder.build();
        assertEquals(vans.get(1).getName(),null);
    }
    @Test
    public void buildFourthXML() throws XMLParserDAOException, DAOFileException {
        sAXGemBuilder.build();
    }
    @Test
    public void buildFifthXML() throws XMLParserDAOException, DAOFileException {
        List<Van> vans = sAXGemBuilder.build();
        List<Van> expected = new ArrayList<>();
        assertEquals(vans.get(0), expected.get(0));
    }

    @After
    public void tearDown() {
        sAXGemBuilder = null;
        validator = null;
    }
}