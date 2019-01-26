package com.alexshay.multithreading.dao.validation;

import com.alexshay.multithreading.dao.exception.DAOFileException;
import com.alexshay.multithreading.dao.exception.XMLParserDAOException;

public interface BaseValidator {
    void validate() throws DAOFileException, XMLParserDAOException;
}
