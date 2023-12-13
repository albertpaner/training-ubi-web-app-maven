package model.dao;

import model.bean.UtenteBean;
import utils.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UtenteDao implements Crud<UtenteBean> {

    private Connection conn;

    public UtenteDao(Connection conn) {
        this.conn = conn;
    }

    public UtenteDao() throws SQLException, ClassNotFoundException {
    }

    /**
     * This method corresponds to READ operation on the database.
     * It returns a list of all the users in the database.
     *
     * @return A list of all the users in the database.
     * @throws SQLException If a database access error occurs or this method is called on a closed connection.
     */
    public List<UtenteBean> findAll() throws SQLException, ClassNotFoundException {

        this.conn = DBConnection.createConnection();
        List<UtenteBean> listaUtenti = new ArrayList<>();

        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM utente");
        while (rs.next()) {
            UtenteBean utenteBean = new UtenteBean();
            utenteBean.setUtenteId(rs.getInt("utente_id"));
            utenteBean.setEmail(rs.getString("email"));
            utenteBean.setPassword(rs.getString("password"));
            utenteBean.setRuoloId(rs.getInt("ruolo_id"));
            utenteBean.setNome(rs.getString("nome"));
            utenteBean.setCognome(rs.getString("cognome"));
            utenteBean.setValutatoreId(rs.getInt("valutatore_id"));
            utenteBean.setDataNascita(rs.getDate("data_nascita"));
            utenteBean.setDataUltAcc(rs.getDate("data_ult_acc"));
            utenteBean.setDataUltMod(rs.getDate("data_ult_mod"));
            utenteBean.setDataCreaz(rs.getDate("data_creaz"));
            utenteBean.setFlgDel(rs.getBoolean("flg_del"));


            // ci popoliamo tutto l'oggetto
            listaUtenti.add(utenteBean);
        }

        conn.close();
        return listaUtenti;

    }

    /**
     * This method finds a user in the database by its ID.
     *
     * @param utenteId The ID of the user to be found.
     * @return The user with the specified ID.
     * @throws SQLException If a database access error occurs or this method is called on a closed connection.
     */
    public UtenteBean findById(int utenteId) throws SQLException, ClassNotFoundException {

        this.conn = DBConnection.createConnection();
        UtenteBean utenteBean = new UtenteBean();

        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM utente WHERE utente_id = " + utenteId);
        while (rs.next()) {
            utenteBean.setUtenteId(utenteId);
            utenteBean.setEmail(rs.getString("email"));
            utenteBean.setPassword(rs.getString("password"));
            utenteBean.setRuoloId(rs.getInt("ruolo_id"));
            utenteBean.setNome(rs.getString("nome"));
            utenteBean.setCognome(rs.getString("cognome"));
            utenteBean.setValutatoreId(rs.getInt("valutatore_id"));
            utenteBean.setDataNascita(rs.getDate("data_nascita"));
            utenteBean.setDataUltAcc(rs.getDate("data_ult_acc"));
            utenteBean.setDataUltMod(rs.getDate("data_ult_mod"));
            utenteBean.setDataCreaz(rs.getDate("data_creaz"));
            utenteBean.setFlgDel(rs.getBoolean("flg_del"));
            
        }

        conn.close();
        return utenteBean;

    }

    /**
     * this method corresponds to CREATE operation on the database.
     * It creates a new user in the database.
     *
     * @param userParams A list of parameters to be inserted in the database.
     * @return The number of rows affected by the insert operation.
     * @throws SQLException If a database access error occurs or this method is called on a closed connection.
     */
    public int create(List<Object> userParams) throws SQLException, ClassNotFoundException {

        this.conn = DBConnection.createConnection();

        String email = (String) userParams.get(0);
        String password = (String) userParams.get(1);
        int ruoloId = (Integer) userParams.get(2);
        String nome = (String) userParams.get(3);
        String cognome = (String) userParams.get(4);
        int valutatoreId = (Integer) userParams.get(5);
        Date dataNascita = (Date) userParams.get(6);

        Statement stmt = conn.createStatement();
        int rs = stmt.executeUpdate(
                "INSERT INTO utente (email, password, ruolo_id, nome, cognome, valutatore_id, data_nascita) VALUES ('"
                        + email + "', '" + password + "', " + ruoloId + ", '" + nome + "', '" + cognome + "', "
                        + valutatoreId + ", '" + dataNascita + "')");

        conn.close();
        return rs;
    }

    /**
     * this method corresponds to UPDATE operation on the database.
     * It updates an existing user in the database.
     *
     * @param userParams A list of user parameters to be updated in the database.
     * @return The number of rows affected by the update operation.
     * @throws SQLException If a database access error occurs or this method is called on a closed connection.
     */
    public int update(List<Object> userParams) throws SQLException, ClassNotFoundException {

        this.conn = DBConnection.createConnection();
        String email = (String) userParams.get(0);
        String password = (String) userParams.get(1);
        int ruoloId = (Integer) userParams.get(2);
        String nome = (String) userParams.get(3);
        String cognome = (String) userParams.get(4);
        int valutatoreId = (Integer) userParams.get(5);
        Date dataNascita = (Date) userParams.get(6);
        int utenteId = (Integer) userParams.get(7);

        Statement stmt = conn.createStatement();
        int rs = stmt.executeUpdate("UPDATE utente SET email = '" + email + "', password = '" + password
                + "', ruolo_id = " + ruoloId + ", nome = '" + nome + "', cognome = '" + cognome
                + "', valutatore_id = " + valutatoreId + ", data_nascita = '" + dataNascita + "' WHERE utente_id = " + utenteId);

        conn.close();
        return rs;
    }

    /**
     * this method corresponds to DELETE operation on the database.
     * It logically deletes an existing user in the database.
     *
     * @param utenteId The ID of the user to be deleted.
     * @return The number of rows affected by the update operation.
     * @throws SQLException If a database access error occurs or this method is called on a closed connection.
     */
    public int logicalDelete(int utenteId) throws SQLException, ClassNotFoundException {

        this.conn = DBConnection.createConnection();
        Statement stmt = conn.createStatement();
        int rs = stmt.executeUpdate("UPDATE utente SET flg_del = 1 WHERE utente_id = " + utenteId);

        conn.close();
        return rs;
    }

    /**
     * this method corresponds to DELETE operation on the database.
     * It physically deletes an existing user in the database.
     *
     * @param utenteId The ID of the user to be deleted.
     * @return The number of rows affected by the delete operation.
     * @throws SQLException If a database access error occurs or this method is called on a closed connection.
     */
    public int delete(int utenteId) throws SQLException, ClassNotFoundException {

        this.conn = DBConnection.createConnection();
        Statement stmt = conn.createStatement();
        int rs = stmt.executeUpdate("DELETE FROM utente WHERE utente_id = " + utenteId);

        conn.close();
        return rs;
    }

    /**
     * This method updates the last access time of a user in the database.
     * The last access time is set to the current time.
     *
     * @param utenteId The ID of the user whose last access time is to be updated.
     * @return The number of rows affected by the update operation.
     * @throws SQLException If a database access error occurs or this method is called on a closed connection.
     */
    public int updateLastAccess(int utenteId) throws SQLException, ClassNotFoundException {

        this.conn = DBConnection.createConnection();
        Statement stmt = conn.createStatement();
        int rs = stmt.executeUpdate("UPDATE utente SET data_ult_acc = NOW() WHERE utente_id = " + utenteId);

        conn.close();
        return rs;
    }


}
