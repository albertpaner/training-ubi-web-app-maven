package model.Dao;

import model.Bean.UtenteBean;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UtenteDao {
    private Connection conn;

    public UtenteDao() {
    }

    public UtenteDao(Connection conn) throws ClassNotFoundException, SQLException {
        this.conn = conn;
    }

    /**
     * This method corresponds to READ operation on the database.
     * It returns a list of all the users in the database.
     *
     * @return A list of all the users in the database.
     * @throws SQLException If a database access error occurs or this method is called on a closed connection.
     * */
    public List<UtenteBean> findAll() throws SQLException {

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
			/* 
			utenteBean.setSocietaOp(rs.getString("societa_op"));
			utenteBean.setMansione(rs.getString("mansione"));
			utenteBean.setAmbito(rs.getString("ambito"));
			utenteBean.setJobFam(rs.getString("job_fam"));
			utenteBean.setSubFam(rs.getString("sub_fam"));
			utenteBean.setStdJob(rs.getString("std_job"));
			utenteBean.setJobLevel(rs.getString("job_level")); */
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
     * */
    public UtenteBean findById(int utenteId) throws SQLException {

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
			/* 
			utenteBean.setSocietaOp(rs.getString("societa_op"));
			utenteBean.setMansione(rs.getString("mansione"));
			utenteBean.setAmbito(rs.getString("ambito"));
			utenteBean.setJobFam(rs.getString("job_fam"));
			utenteBean.setSubFam(rs.getString("sub_fam"));
			utenteBean.setStdJob(rs.getString("std_job"));
			utenteBean.setJobLevel(rs.getString("job_level")); */
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
     * @param email The email of the user to be created.
     * @param password The password of the user to be created.
     * @param ruoloId The role ID of the user to be created.
     * @param nome The name of the user to be created.
     * @param cognome The surname of the user to be created.
     * @param valutatoreId The ID of the user's evaluator.
     * @param dataNascita The date of birth of the user to be created.
     *
     * @return The number of rows affected by the insert operation.
     * @throws SQLException If a database access error occurs or this method is called on a closed connection.
     * */
    public int create(String email, String password, int ruoloId, String nome, String cognome, int valutatoreId, Date dataNascita) throws SQLException {

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
     * It updates an existing user or inserts a new user in the database.
     *
     * @param email The email of the user to be updated.
     * @param password The password of the user to be updated.
     * @param ruoloId The role ID of the user to be updated.
     * @param nome The name of the user to be updated.
     * @param cognome The surname of the user to be updated
     * @param valutatoreId The ID of the user's evaluator.
     * @param dataNascita The date of birth of the user to be updated.
     *
     * @return The number of rows affected by the update operation.
     * @throws SQLException If a database access error occurs or this method is called on a closed connection.
     * */
    public int update(String email, String password, int ruoloId, String nome, String cognome, int valutatoreId,
                      Date dataNascita, int utenteId) throws SQLException {

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
     * */
    public int logicDelete(int utenteId) throws SQLException {

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
     * */
    public int delete(int utenteId) throws SQLException {

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
    public int updateLastAccess(int utenteId) throws SQLException {

        Statement stmt = conn.createStatement();
        int rs = stmt.executeUpdate("UPDATE utente SET data_ult_acc = NOW() WHERE utente_id = " + utenteId);

        conn.close();
        return rs;
    }

    /**
     * This method updates the last modification time of a user in the database.
     * The last modification time is set to the current time.
     * @param utenteId The ID of the user whose last modification time is to be updated.
     * @return The number of rows affected by the update operation.
     * @throws SQLException If a database access error occurs or this method is called on a closed connection.
     */
    public int updateValutatoreId(int utenteId, int valutatoreId) throws SQLException {

        Statement stmt = conn.createStatement();
        int rs = stmt.executeUpdate("UPDATE utente SET valutatore_id = " + valutatoreId + " WHERE utente_id = " + utenteId);

        return rs;
    }

    /*
    public HashMap<String, List<UtenteBean>> splitEvaluators(int soglia) throws SQLException {

        HashMap<String, List<UtenteBean>> resultMap = new HashMap<>();
        List<UtenteBean> valutatoriPiu = new ArrayList<>();
        List<UtenteBean> valutatoriMeno = new ArrayList<>();

        Statement stmt = conn.createStatement();

        ResultSet rs = stmt.executeQuery(
                "SELECT user_id, nome, cognome, email FROM utente WHERE utente_id IN (" +
                        "SELECT valutatore_id" +
                        "FROM utente WHERE ruolo_id = 1" +
                        "GROUP BY valutatore_id" +
                        "HAVING COUNT(valutatore_id) >= " + soglia + ");");

        while (rs.next()) {
            UtenteBean utenteBean = new UtenteBean();
            utenteBean.setUtenteId(rs.getInt("valutatore_id"));
            utenteBean.setEmail(rs.getString("email"));
            utenteBean.setNome(rs.getString("nome"));
            utenteBean.setCognome(rs.getString("cognome"));
            utenteBean.setDataNascita(rs.getDate("data_nascita"));
            valutatoriPiu.add(utenteBean);
        }

        resultMap.put("valutatori_occupati", valutatoriPiu);

        rs = stmt.executeQuery(
                "SELECT user_id, nome, cognome, email FROM utente WHERE utente_id IN (" +
                        "SELECT valutatore_id" +
                        "FROM utente WHERE ruolo_id = 1" +
                        "GROUP BY valutatore_id" +
                        "HAVING COUNT(valutatore_id) < " + soglia + ");");

        while (rs.next()) {
            UtenteBean utenteBean = new UtenteBean();
            utenteBean.setUtenteId(rs.getInt("valutatore_id"));
            utenteBean.setEmail(rs.getString("email"));
            utenteBean.setNome(rs.getString("nome"));
            utenteBean.setCognome(rs.getString("cognome"));
            utenteBean.setDataNascita(rs.getDate("data_nascita"));
            valutatoriMeno.add(utenteBean);
        }

        resultMap.put("valutatori_disponibili", valutatoriMeno);

        conn.close();

        return resultMap;
    }

    public int countValuedByEvaluator(int evalId) throws ClassNotFoundException, SQLException {
        int count = 0;

        PreparedStatement ps = conn.prepareStatement("SELECT COUNT(*) FROM utente" +
                "WHERE ruolo_id = 2 AND valutatore_id = ?");
        ps.setFloat(1, evalId);
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            count = rs.getInt(1);
        }

        conn.close();
        return count;
    }


    public List<UtenteBean> findValuedByEvaluator(int evatorId) throws SQLException {
        List<UtenteBean> utentiValutatiDa = new ArrayList<>();

        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM utente WHERE ruolo_id = 2 AND valutatore_id = " + evatorId);
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
            utentiValutatiDa.add(utenteBean);
        }

        return utentiValutatiDa;
    }
    */

}
