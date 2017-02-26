package com.example.root.myapplication.DAO;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by cyrilstern1 on 25/02/2017.
 */

public interface IDAO<T, ID> {
    void open() throws SQLException;

    void close();

    void create(T t) throws Exception;

    T retrieve(ID id) throws Exception;

    List<T> findAll() throws Exception;

    void update(T t) throws Exception;

    void delete(ID id) throws Exception;
}
