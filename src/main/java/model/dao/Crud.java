package model.dao;

import java.sql.SQLException;
import java.util.List;

/**
 * This interface is used to implement the CRUD operations
 * */
public interface Crud<T> {

    List<T> findAll() throws SQLException, ClassNotFoundException;
    T findById(int id) throws SQLException, ClassNotFoundException;
    int create(List<Object> params) throws SQLException, ClassNotFoundException;
    int update(List<Object> params) throws SQLException, ClassNotFoundException;
    int delete(int id) throws SQLException, ClassNotFoundException;
}
