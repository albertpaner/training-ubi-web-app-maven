package model.dao;

import model.bean.UtenteBean;
import utils.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UtenteDao {

    private static UtenteDao instance;
    private static Connection conn;


    public static UtenteDao getInstance() throws SQLException, ClassNotFoundException {
        if (instance == null) {
            instance = new UtenteDao();
        }
        return instance;
    }


    public UtenteDao() throws SQLException, ClassNotFoundException {
    }

    public List<UtenteBean> findAll() throws SQLException, ClassNotFoundException {

        conn = DBConnection.createConnection();
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
            utenteBean.setMansione(rs.getString("mansione"));
            utenteBean.setJobLevel(rs.getString("job_level"));
            utenteBean.setSocietàOperativa(rs.getString("società_operativa"));
            utenteBean.setDataNascita(rs.getDate("data_nascita"));
            utenteBean.setInSospeso(rs.getBoolean("in_sospeso"));
            utenteBean.setDataUltAcc(rs.getDate("data_ult_acc"));
            utenteBean.setDataUltMod(rs.getDate("data_ult_mod"));
            utenteBean.setDataCreaz(rs.getDate("data_creaz"));
            utenteBean.setFlgDel(rs.getBoolean("flg_del"));

            listaUtenti.add(utenteBean);
        }

        conn.close();
        return listaUtenti;

    }


    public UtenteBean findById(int utenteId) throws SQLException, ClassNotFoundException {

        conn = DBConnection.createConnection();
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
            utenteBean.setMansione(rs.getString("mansione"));
            utenteBean.setJobLevel(rs.getString("job_level"));
            utenteBean.setSocietàOperativa(rs.getString("società_operativa"));
            utenteBean.setDataNascita(rs.getDate("data_nascita"));
            utenteBean.setInSospeso(rs.getBoolean("in_sospeso"));
            utenteBean.setDataUltAcc(rs.getDate("data_ult_acc"));
            utenteBean.setDataUltMod(rs.getDate("data_ult_mod"));
            utenteBean.setDataCreaz(rs.getDate("data_creaz"));
            utenteBean.setFlgDel(rs.getBoolean("flg_del"));
        }

        conn.close();
        return utenteBean;

    }

    public int create(String email, String password, int ruoloId, String nome, String cognome, int valutatoreId, String mansione, String jobLevel, String societàOperativa, Date dataNascita) throws SQLException, ClassNotFoundException {

        conn = DBConnection.createConnection();
        String sql = "INSERT INTO utente (email, password, ruolo_id, nome, cognome, valutatore_id, mansione, job_level, società_operativa, data_nascita) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, email);
        pstmt.setString(2, password);
        pstmt.setInt(3, ruoloId);
        pstmt.setString(4, nome);
        pstmt.setString(5, cognome);
        pstmt.setInt(6, valutatoreId);
        pstmt.setString(7, mansione);
        pstmt.setString(8, jobLevel);
        pstmt.setString(9, societàOperativa);
        pstmt.setDate(10, dataNascita);

        int rowsAffected = pstmt.executeUpdate();

        conn.close();
        return rowsAffected;
    }

    public int update(int utenteId, String email, String password, int ruoloId, String nome, String cognome, int valutatoreId, String mansione, String jobLevel, String societàOperativa, Date dataNascita, boolean inSospeso, Date dataUltAcc, Date dataUltMod, Date dataCreaz, boolean flgDel) throws SQLException, ClassNotFoundException {

        conn = DBConnection.createConnection();
        String sql = "UPDATE utente SET email = ?, password = ?, ruolo_id = ?, nome = ?, cognome = ?, valutatore_id = ?, mansione = ?, job_level = ?, società_operativa = ?, data_nascita = ?, in_sospeso = ?, data_ult_acc = ?, data_ult_mod = ?, data_creaz = ?, flg_del = ? WHERE utente_id = ?";
        PreparedStatement pstmt = conn.prepareStatement(sql);

        pstmt.setString(1, email);
        pstmt.setString(2, password);
        pstmt.setInt(3, ruoloId);
        pstmt.setString(4, nome);
        pstmt.setString(5, cognome);
        pstmt.setInt(6, valutatoreId);
        pstmt.setString(7, mansione);
        pstmt.setString(8, jobLevel);
        pstmt.setString(9, societàOperativa);
        pstmt.setDate(10, dataNascita);
        pstmt.setBoolean(11, inSospeso);
        pstmt.setDate(12, dataUltAcc);
        pstmt.setDate(13, dataUltMod);
        pstmt.setDate(14, dataCreaz);
        pstmt.setBoolean(15, flgDel);
        pstmt.setInt(16, utenteId);

        int rowsAffected = pstmt.executeUpdate();

        conn.close();
        return rowsAffected;
    }


    public int logicalDelete(int utenteId) throws SQLException, ClassNotFoundException {

        conn = DBConnection.createConnection();
        Statement stmt = conn.createStatement();
        int rs = stmt.executeUpdate("UPDATE utente SET flg_del = 1 WHERE utente_id = " + utenteId);

        conn.close();
        return rs;
    }


    public int delete(int utenteId) throws SQLException, ClassNotFoundException {

        conn = DBConnection.createConnection();
        Statement stmt = conn.createStatement();
        int rs = stmt.executeUpdate("DELETE FROM utente WHERE utente_id = " + utenteId);

        conn.close();
        return rs;
    }



}
