package com.alexshay.multithreading.dao.builder.impl;

import com.alexshay.multithreading.dao.Constants;
import com.alexshay.multithreading.dao.builder.Builder;
import com.alexshay.multithreading.dao.exception.DAOFileException;
import com.alexshay.multithreading.dao.exception.XMLParserDAOException;
import com.alexshay.multithreading.dao.validation.BaseValidator;
import com.alexshay.multithreading.dao.validation.impl.XMLValidatorByXSD;
import com.alexshay.multithreading.entity.Van;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.util.List;

public class VanSAXBuilder implements Builder<Van> {
    private BaseValidator validator = new XMLValidatorByXSD();

    public VanSAXBuilder() {
    }


    public List<Van> build() throws XMLParserDAOException, DAOFileException {
        VanHandler gemHandler = new VanHandler();
        validator.validate();
        try {
            SAXParserFactory parserFactory = SAXParserFactory.newInstance();
            SAXParser parser = parserFactory.newSAXParser();
            XMLReader reader = parser.getXMLReader();
            reader.setContentHandler(gemHandler);
            reader.parse(Constants.PATH_TO_XML);
        } catch (SAXException | ParserConfigurationException e ){
            throw new XMLParserDAOException(e);
        }catch (IOException e){
            throw  new DAOFileException(e);
        }
        return gemHandler.getVans();
    }

}
