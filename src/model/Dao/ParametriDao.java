package model.Dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Bean.ParametriBean;
import utils.DBConnection;

public class ParametriDao {
	public ParametriDao() {
	};

	public List<ParametriBean> findAll() throws ClassNotFoundException, SQLException {
		List<ParametriBean> listaParametri = new ArrayList<>();

		Connection conn = DBConnection.createConnection();

		Statement stmt = conn.createStatement();

		ResultSet rs = stmt.executeQuery("SELECT * FROM parametri");

		while (rs.next()) {
			ParametriBean parametriBean = new ParametriBean();

			parametriBean.setParamId(rs.getInt("param_id"));
			parametriBean.setParagrafoId(rs.getInt("paragrafo_id"));
			parametriBean.setDescrParam(rs.getString("descr_param"));
			parametriBean.setDataUltMod(rs.getDate("data_ult_mod"));
			parametriBean.setDataCreazione(rs.getDate("data_creazione"));
			parametriBean.setValutId(rs.getInt("valut_id"));
			parametriBean.setFlgDel(rs.getBoolean("flg_del"));

			listaParametri.add(parametriBean);
		}
		conn.close();

		return listaParametri;

	}

	public ParametriBean findById(int id) throws ClassNotFoundException, SQLException {
		ParametriBean parametriBean = new ParametriBean();
		Connection conn = DBConnection.createConnection();
		PreparedStatement ps = conn.prepareStatement("SELECT * FROM parametri WHERE param_id=?");
		ResultSet rs = ps.executeQuery();
		ps.setInt(1, id);

		while (rs.next()) {
			parametriBean.setParamId(id);
			parametriBean.setParagrafoId(rs.getInt("param_id"));
			parametriBean.setValutId(rs.getInt("valut_id"));
			parametriBean.setDescrParam(rs.getString("descr_param"));
			parametriBean.setDataCreazione(rs.getDate("data_creazione"));
			parametriBean.setDataUltMod(rs.getDate("data_ult_mod"));
			parametriBean.setFlgDel(rs.getBoolean("flg_del"));
		}
		return parametriBean;
	}

	public int create(int paragrafoId, int valutId, String descrParam) throws ClassNotFoundException, SQLException {

		Connection conn = DBConnection.createConnection();
		PreparedStatement ps = conn.prepareStatement(
				"INSERT INTO parametri (paragrafo_id, valut_id, descr_param) VALUES (?, ?, ?)");

		ps.setInt(1, paragrafoId);
		ps.setInt(2, valutId);
		ps.setString(3, descrParam);

		int execute_success = ps.executeUpdate();
		conn.close();

		return execute_success;

	}

	public int update(int valutId, int paragrafoId, String descrParam, int paramId)
			throws ClassNotFoundException, SQLException {

		Connection conn = DBConnection.createConnection();
		PreparedStatement ps = conn.prepareStatement(
				"UPDATE parametri SET valut_id = ?,  paragrafo_id= ?, descr_param = ? WHERE param_id = ?");

		ps.setInt(1, valutId);
		ps.setInt(2, paragrafoId);
		ps.setString(3, descrParam);
		ps.setInt(4, paramId);

		int execute_success = ps.executeUpdate();
		conn.close();

		return execute_success;

	}

	public int logicDeleteParametri(int paramId) throws ClassNotFoundException, SQLException {
		Connection conn = DBConnection.createConnection();
		PreparedStatement ps = conn.prepareStatement("UPDATE parametri SET flg_del = true WHERE param_id = ?");
		ps.setInt(1, paramId);

		int execute_success = ps.executeUpdate();
		conn.close();

		return execute_success;
	}

	public int deleteParametri(int paramId) throws ClassNotFoundException, SQLException {

		Connection conn = DBConnection.createConnection();
		PreparedStatement ps = conn.prepareStatement("DELETE FROM parametri WHERE param_id = ?");
		ps.setInt(1, paramId);
		int execute_success = ps.executeUpdate();
		conn.close();

		return execute_success;
	}

}
