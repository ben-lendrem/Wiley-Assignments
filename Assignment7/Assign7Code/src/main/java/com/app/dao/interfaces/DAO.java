package com.app.dao.interfaces;

import java.util.List;

public interface DAO<T> {
    List<T> GetAll();
    T GetByID(int id);
    boolean Add(T objIn);
    boolean Update(T objIn);
    boolean DeleteByID(int id);
}
