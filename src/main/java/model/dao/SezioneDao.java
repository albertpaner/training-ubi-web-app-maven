package model.dao;

import model.bean.SezioneBean;
import utils.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SezioneDao {

	public SezioneDao() throws SQLException, ClassNotFoundException {
		super();
	}
    public List<SezioneBean> findAll() throws ClassNotFoundException, SQLException {

	List<SezioneBean> listaSez = new ArrayList<>();

	DBConnection dBConn = new DBConnection();
	Connection conn = dBConn.createConnection();
	Statement stmt = conn.createStatement();
	ResultSet rs = stmt.executeQuery("SELECT * FROM sezione");

	while (rs.next()) {

	    SezioneBean sezioneBean = new SezioneBean();

	    sezioneBean.setSezioneId(rs.getInt("sezione_id"));
	    sezioneBean.setTitoloSez(rs.getString("titolo_sez"));
	    sezioneBean.setDescrSez(rs.getString("descr_sez"));
	    sezioneBean.setDataUltAcc(rs.getDate("data_ult_acc"));
	    sezioneBean.setDataUltMod(rs.getDate("data_ult_mod"));
	    sezioneBean.setFlgDel(rs.getBoolean("flg_del"));

	    listaSez.add(sezioneBean);

	}

	conn.close();
	return listaSez;

    }

    public SezioneBean findById(int id) throws ClassNotFoundException, SQLException {

	SezioneBean sezioneBean = new SezioneBean();
	DBConnection dBConn = new DBConnection();
	Connection conn = dBConn.createConnection();
	PreparedStatement ps = conn.prepareStatement("SELECT * FROM sezione WHERE sezione_id = ?");
	ps.setFloat(1, id);
	ResultSet rs = ps.executeQuery();

	while (rs.next()) {

	    sezioneBean.setSezioneId(id);
	    sezioneBean.setTitoloSez(rs.getString("titolo_sezione"));
	    sezioneBean.setDescrSez(rs.getString("descr_sez"));
	    sezioneBean.setDataUltAcc(rs.getDate("data_ult_acc"));
	    sezioneBean.setDataUltMod(rs.getDate("data_ult_mod"));
	    sezioneBean.setFlgDel(rs.getBoolean("flg_del"));

	}

	conn.close();
	return sezioneBean;
    }

    public int create(int sezioneId, String titoloSez, String descrSez, Date dataUltAcc, Date dataUltMod,
	    Boolean flgDel) throws ClassNotFoundException, SQLException {
	// TEST
	Connection conn = DBConnection.createConnection();
	PreparedStatement ps = conn.prepareStatement(
		"INSERT INTO sezione (sezione_id, titolo_sez, descr_sez, data_ult_acc, data_ult_mod, flg_del) VALUES (?, ?, ?, ?, ?, ?)");

	ps.setInt(1, sezioneId);
	ps.setString(2, titoloSez);
	ps.setString(3, descrSez);
	ps.setDate(4, dataUltAcc);
	ps.setDate(5, dataUltMod);
	ps.setBoolean(6, flgDel);

	int execute_success = ps.executeUpdate();
	conn.close();

	return execute_success;
    }

    public int update(int sezioneId, String titoloSez, String descrSez, Date dataUltAcc, Date dataUltMod,
	    Boolean flgDel) throws ClassNotFoundException, SQLException {

	Connection conn = DBConnection.createConnection();
	PreparedStatement ps = conn.prepareStatement(
		"UPDATE sezione SET sezione_id = ?, titolo_sez = ?, descr_sez = ?, data_ult_acc = ?, data_ult_mod = ?, flg_del = ?  WHERE sezione_id = ?");

	ps.setInt(1, sezioneId);
	ps.setString(2, titoloSez);
	ps.setString(3, descrSez);
	ps.setDate(4, dataUltAcc);
	ps.setDate(5, dataUltMod);
	ps.setBoolean(6, flgDel);

	int execute_success = ps.executeUpdate();
	conn.close();

	return execute_success;
    }

    // logic delete
    public int logicDeleteBook(int sezioneId) throws ClassNotFoundException, SQLException {

	Connection conn = DBConnection.createConnection();
	PreparedStatement ps = conn.prepareStatement("UPDATE sezione SET flg_Del = true WHERE sezione_id = ?");
	ps.setInt(1, sezioneId);

	int execute_success = ps.executeUpdate();
	conn.close();

	return execute_success;
    }

    // physical delete
    public int deleteBook(int sezioneId) throws ClassNotFoundException, SQLException {

	Connection conn = DBConnection.createConnection();
	PreparedStatement ps = conn.prepareStatement("DELETE FROM sezione WHERE sezione_id = ?");
	ps.setInt(1, sezioneId);

	int execute_success = ps.executeUpdate();
	conn.close();

	return execute_success;
    }

}
