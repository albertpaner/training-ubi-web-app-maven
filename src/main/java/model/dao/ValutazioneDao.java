package model.dao;

import model.bean.ValutazioneBean;
import utils.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ValutazioneDao extends Dao{

    public ValutazioneDao() throws SQLException, ClassNotFoundException {
        super();
    }

    public List<ValutazioneBean> findAll() throws ClassNotFoundException, SQLException {

        DBConnection dBConn = new DBConnection();
        Connection conn = dBConn.createConnection();

        List<ValutazioneBean> listaValutazione = new ArrayList<>();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM valutazione");
        ValutazioneBean valutazioneBean = new ValutazioneBean();

        while (rs.next()) {
            valutazioneBean = new ValutazioneBean();
            valutazioneBean.setValutId(rs.getInt("valut_id"));
            valutazioneBean.setValoreVal(rs.getInt("valore_val"));
            valutazioneBean.setDescrVal(rs.getString("descr_val"));
            valutazioneBean.setDescrVal(rs.getString("descr_val"));
            valutazioneBean.setDataUltMod(rs.getDate("data_ult_mod"));
            valutazioneBean.setDataCreaz(rs.getDate("data_creaz"));
            valutazioneBean.setFlgDel(rs.getBoolean("flg_del"));
            listaValutazione.add(valutazioneBean);
        }
        conn.close();

        return listaValutazione;

    }

    public ValutazioneBean findById(int id) throws ClassNotFoundException, SQLException {

        DBConnection dBConn = new DBConnection();
        Connection conn = dBConn.createConnection();

        PreparedStatement ps = conn.prepareStatement("SELECT * FROM valutazione WHERE valut_id = ?");
        ps.setFloat(1, id);
        ResultSet rs = ps.executeQuery();
        ValutazioneBean valutazioneBean = new ValutazioneBean();
        ;

        if (rs.next()) {
            valutazioneBean.setValutId(rs.getInt("valut_id"));
            valutazioneBean.setValoreVal(rs.getInt("valore_val"));
            valutazioneBean.setDescrVal(rs.getString("descr_val"));
            valutazioneBean.setDescrVal(rs.getString("descr_val"));
            valutazioneBean.setDataUltMod(rs.getDate("data_ult_mod"));
            valutazioneBean.setDataCreaz(rs.getDate("data_creaz"));
            valutazioneBean.setFlgDel(rs.getBoolean("flg_del"));
        }
        conn.close();

        return valutazioneBean;

    }

    public int create(int valoreVal, String descrVal, int utenteId) throws ClassNotFoundException, SQLException {

        DBConnection dBConn = new DBConnection();
        Connection conn = dBConn.createConnection();
        PreparedStatement ps = conn.prepareStatement(
                "INSERT INTO valutazione (valore_val, descr_val, utente_id) values (?, ?, ?)");

        ps.setInt(1, valoreVal);
        ps.setString(2, descrVal);
        ps.setInt(3, utenteId);

        int result = ps.executeUpdate();
        conn.close();
        return result;

    }

    public int update(int valoreVal, String descrVal, int utenteId, int valut_id)
            throws ClassNotFoundException, SQLException {

        Connection conn = DBConnection.createConnection();
        PreparedStatement ps = conn.prepareStatement(
                "UPDATE valutazione SET valore_val = ?, descr_val = ?, utente_id = ? WHERE valut_id = ?");

        ps.setInt(1, valoreVal);
        ps.setString(2, descrVal);
        ps.setInt(3, utenteId);
        ps.setInt(4, valut_id);

        int execute_success = ps.executeUpdate();
        conn.close();
        return execute_success;
    }

    public int logicDelete(int valutId) throws ClassNotFoundException, SQLException {

        Connection conn = DBConnection.createConnection();
        PreparedStatement ps = conn.prepareStatement("UPDATE valutazione SET flg_del = true WHERE valut_id = ?");
        ps.setInt(1, valutId);

        int execute_success = ps.executeUpdate();
        conn.close();

        return execute_success;

    }

    public int delete(int valutId) throws ClassNotFoundException, SQLException {
        Connection conn = DBConnection.createConnection();
        PreparedStatement ps = conn.prepareStatement("DELETE FROM valutazione WHERE valut_id = ?");
        ps.setInt(1, valutId);

        int execute_success = ps.executeUpdate();
        conn.close();

        return execute_success;
    }

}
