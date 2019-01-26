package com.alexshay.multithreading.service;

import com.alexshay.multithreading.dao.builder.Builder;
import com.alexshay.multithreading.dao.builder.impl.VanSAXBuilder;
import com.alexshay.multithreading.dao.exception.DAOFileException;
import com.alexshay.multithreading.dao.exception.XMLParserDAOException;
import com.alexshay.multithreading.entity.Van;
import com.alexshay.multithreading.service.exception.ServiceFileException;
import com.alexshay.multithreading.service.exception.ServiceParserException;

import java.util.List;

public class VanFactory {
    private final static VanFactory INSTANCE = new VanFactory();
    private Builder<Van> vanBuilder = new VanSAXBuilder();


    public static VanFactory getInstance(){
        return INSTANCE;
    }

    public List<Van> getVans() throws ServiceFileException, ServiceParserException {
        try {
            return vanBuilder.build();
        } catch (XMLParserDAOException  e) {
           throw new ServiceParserException(e);
        }catch (DAOFileException e){
            throw new ServiceFileException(e);
        }
    }

    private VanFactory(){}
}
