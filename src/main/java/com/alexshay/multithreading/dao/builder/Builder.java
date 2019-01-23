package com.alexshay.multithreading.dao.builder;

import java.util.List;

public interface Builder<T> {
    List<T> build(String pathToFile);
}
