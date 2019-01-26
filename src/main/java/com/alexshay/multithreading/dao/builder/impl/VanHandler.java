package com.alexshay.multithreading.dao.builder.impl;

import com.alexshay.multithreading.dao.Constants;
import com.alexshay.multithreading.entity.Van;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;

public class VanHandler extends DefaultHandler {
    private static final Logger LOGGER = LogManager.getLogger(VanHandler.class);
    private List<Van> vans;
    private Van current;
    private String qName;
    private final String[] fields = new String[]{"name", "is_full", "is_perishable_foods"};

    public List<Van> getVans() {
        return vans;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {
        if ("vans".equals(qName)) {
            vans = new ArrayList<>();
        } else if ("van".equals(qName)) {
            current = new Van();
            current.setTerritory(Constants.TERRITORY);
            current.setTerminal(Constants.TERMINAL);
        } else {
            this.qName = qName;
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) {
        if ("van".equals(qName)) {
            vans.add(current);
        }
        this.qName = null;
    }

    @Override
    public void characters(char[] ch, int start, int length) {
        String s = new String(ch, start, length).trim();
        if (qName != null) {
            switch (qName){
                case "name":current.setName(s);break;
                case "is_full":current.setFull(Boolean.parseBoolean(s));break;
                case "is_perishable_foods": current.setPerishableFoods(Boolean.parseBoolean(s));break;
                default:LOGGER.info("Not find such element");
            }

        }

    }
}
