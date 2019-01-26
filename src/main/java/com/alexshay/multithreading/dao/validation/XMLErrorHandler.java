package com.alexshay.multithreading.dao.validation;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;

public class XMLErrorHandler extends DefaultHandler {
    private static final Logger LOGGER = LogManager.getLogger(XMLErrorHandler.class);

    private static final XMLErrorHandler ourInstance = new XMLErrorHandler();

    public static XMLErrorHandler getInstance() {
        return ourInstance;
    }

    private XMLErrorHandler() {
    }

    @Override
    public void warning(SAXParseException e)  {
        LOGGER.warn(e);
    }

    @Override
    public void error(SAXParseException e) throws SAXException {
        throw e;
    }

    @Override
    public void fatalError(SAXParseException e) throws SAXException {
        throw e;
    }
}
