package com.alexshay.multithreading.dao.validation.impl;

import com.alexshay.multithreading.dao.Constants;
import com.alexshay.multithreading.dao.exception.DAOFileException;
import com.alexshay.multithreading.dao.exception.XMLParserDAOException;
import com.alexshay.multithreading.dao.validation.BaseValidator;
import com.alexshay.multithreading.dao.validation.XMLErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.XMLConstants;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.IOException;

public class XMLValidatorByXSD implements BaseValidator {
    private static final String LANGUAGE = XMLConstants.W3C_XML_SCHEMA_NS_URI;
    private final SchemaFactory factory = SchemaFactory.newInstance(LANGUAGE);

    public XMLValidatorByXSD() {
    }

    @Override
    public void validate() throws XMLParserDAOException, DAOFileException {
        Source source = new StreamSource(Constants.PATH_TO_XML);

        File schemaLocation = new File(Constants.PATH_TO_XSD);
        try{

            Schema schema = factory.newSchema(schemaLocation);
            Validator validator = schema.newValidator();

            DefaultHandler errorHandler = XMLErrorHandler.getInstance();
            validator.setErrorHandler(errorHandler);
            validator.validate(source);
        } catch (IOException e){
            throw new DAOFileException(e);
        } catch (SAXException e){
            throw  new XMLParserDAOException(e);
        }
    }
}
