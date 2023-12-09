package model.dao;

import java.sql.SQLException;
import java.util.List;

/**
 * This interface is used to implement the CRUD operations
 * */
public interface Crud<T> {

    List<T> findAll() throws SQLException;
    T findById(int id) throws SQLException;
    int create(List<Object> params) throws SQLException;
    int update(List<Object> params) throws SQLException;
    int delete(int id) throws SQLException;
}
