package model.dao;

import utils.DBConnection;

import java.sql.Connection;
import java.sql.SQLException;

public abstract class Dao {
    protected final Connection conn;
    public Dao() throws SQLException, ClassNotFoundException {
        this.conn = DBConnection.createConnection();
    }

    public Connection getConn() {
        return conn;
    }
}
