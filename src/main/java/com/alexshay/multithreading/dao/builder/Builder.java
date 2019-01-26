package com.alexshay.multithreading.dao.builder;

import com.alexshay.multithreading.dao.exception.DAOFileException;
import com.alexshay.multithreading.dao.exception.XMLParserDAOException;

import java.util.List;

public interface Builder<T> {
    List<T> build() throws XMLParserDAOException, DAOFileException;
}
