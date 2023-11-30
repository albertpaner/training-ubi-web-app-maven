package model.Dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Bean.ParagrafoBean;
import utils.DBConnection;

public class ParagrafoDao {

	public List<ParagrafoBean> findAll() throws ClassNotFoundException, SQLException {

		List<ParagrafoBean> listaPar = new ArrayList<>();

		DBConnection dBConn = new DBConnection();
		Connection conn = dBConn.createConnection();

		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM paragrafo");
		while (rs.next()) {
			ParagrafoBean parBean = new ParagrafoBean();
			parBean.setParagrafoId(rs.getInt("paragrafo_id"));
			parBean.setSezioneId(rs.getInt("sezione_id"));
			parBean.setTitoloPar(rs.getString("titolo_par"));
			parBean.setDescrPar(rs.getString("descr_par"));
			parBean.setDataUltMod(rs.getDate("data_ult_mod"));
			parBean.setDataCreaz(rs.getDate("data_creaz"));
			parBean.setFlgDel(rs.getBoolean("flg_del"));
			
			// ci popoliamo tutto l'oggetto
			listaPar.add(parBean);
		}

		conn.close();
		return listaPar;

	}

	public ParagrafoBean findById(int id) throws ClassNotFoundException, SQLException {

		ParagrafoBean parBean = new ParagrafoBean();
		DBConnection dBConn = new DBConnection();
		Connection conn = dBConn.createConnection();
		PreparedStatement ps = conn.prepareStatement("SELECT * FROM paragrafo WHERE paragrafo_id = ?");
		ps.setFloat(1, id);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			parBean.setParagrafoId(id);
			parBean.setSezioneId(rs.getInt("sezione_id"));
			parBean.setTitoloPar(rs.getString("titolo_par"));
			parBean.setDescrPar(rs.getString("descr_par"));
			parBean.setDataUltMod(rs.getDate("data_ult_mod"));
			parBean.setDataCreaz(rs.getDate("data_creaz"));
			parBean.setFlgDel(rs.getBoolean("flg_del"));

		}
		conn.close();
		return parBean;
	}
	
	public int create(int sezioneId, String titoloPar, String descrPar, Date dataUltMod, Date dataCreaz, boolean flgDel) throws ClassNotFoundException, SQLException {
		
		/*AtomicInteger count = new AtomicInteger(100);
		int userId = count.incrementAndGet();*/
		
		DBConnection dBConn = new DBConnection();
		Connection conn = dBConn.createConnection();
		PreparedStatement ps = conn.prepareStatement("INSERT INTO paragrafo (sezione_id, titolo_par, descr_par) values (?, ?, ?)");
		
		/*ps.setInt(1, userId);*/
		ps.setInt(1, sezioneId);
		ps.setString(2, titoloPar);
		ps.setString(3, descrPar);

		
		int result = ps.executeUpdate();
		conn.close();
		
		return result;
	}
	
	public int update(int sezioneId, String titoloPar, String descrPar, int paragrafoId)
			throws ClassNotFoundException, SQLException {
		Connection conn = DBConnection.createConnection();
		// Prepare a SQL statement to update a user by their ID
		PreparedStatement ps = conn.prepareStatement(
				"UPDATE paragrafo SET sezione_id = ?, titolo_par = ?, descr_par = ? WHERE paragrafo_id = ?");
		ps.setInt(1, sezioneId);
		ps.setString(2, titoloPar);
		ps.setString(3, descrPar);
		ps.setInt(4, paragrafoId);

		int execute_success = ps.executeUpdate();
		conn.close();

		return execute_success;
	}
	
	public int logicDeletePar(int paragrafoId) throws ClassNotFoundException, SQLException {

		Connection conn = DBConnection.createConnection();
		PreparedStatement ps = conn.prepareStatement("UPDATE paragrafo SET flg_del = true WHERE paragrafo_id = ?");
		ps.setInt(1, paragrafoId);

		int execute_success = ps.executeUpdate();
		conn.close();

		return execute_success;
	}
	
	public int deletePar(int paragrafoId) throws ClassNotFoundException, SQLException {

		Connection conn = DBConnection.createConnection();
		PreparedStatement ps = conn.prepareStatement("DELETE FROM paragrafo WHERE paragrafo_id = ?");
		ps.setInt(1, paragrafoId);

		int execute_success = ps.executeUpdate();
		conn.close();

		return execute_success;
	}
}