package model.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import model.Bean.UtenteBean;
import utils.DBConnection;

public class UtenteDao {
	private Connection conn;

	public UtenteDao() {
	}

	public UtenteDao(Connection conn) throws ClassNotFoundException, SQLException {
		this.conn = conn;
	}

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
			utenteBean.setSocietaOp(rs.getString("societa_op"));
			utenteBean.setMansione(rs.getString("mansione"));
			utenteBean.setAmbito(rs.getString("ambito"));
			utenteBean.setJobFam(rs.getString("job_fam"));
			utenteBean.setSubFam(rs.getString("sub_fam"));
			utenteBean.setStdJob(rs.getString("std_job"));
			utenteBean.setJobLevel(rs.getString("job_level"));
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
			utenteBean.setSocietaOp(rs.getString("societa_op"));
			utenteBean.setMansione(rs.getString("mansione"));
			utenteBean.setAmbito(rs.getString("ambito"));
			utenteBean.setJobFam(rs.getString("job_fam"));
			utenteBean.setSubFam(rs.getString("sub_fam"));
			utenteBean.setStdJob(rs.getString("std_job"));
			utenteBean.setJobLevel(rs.getString("job_level"));
			utenteBean.setDataUltAcc(rs.getDate("data_ult_acc"));
			utenteBean.setDataUltMod(rs.getDate("data_ult_mod"));
			utenteBean.setDataCreaz(rs.getDate("data_creaz"));
			utenteBean.setFlgDel(rs.getBoolean("flg_del"));
		}

		conn.close();
		return utenteBean;

	}

	public int create(String email, String password, int ruoloId, String nome, String cognome, int valutatoreId,
			String societaOp, String mansione, String ambito, String jobFam, String subFam, String stdJob,
			String jobLevel) throws SQLException {

		Statement stmt = conn.createStatement();
		int rs = stmt.executeUpdate(
				"INSERT INTO utente (email, password, ruolo_id, nome, cognome, valutatore_id, societa_op, mansione, ambito, job_fam, sub_fam, std_job, job_level) VALUES ('"
						+ email + "', '" + password + "', " + ruoloId + ", '" + nome + "', '" + cognome + "', "
						+ valutatoreId + ", '" + societaOp + "', '" + mansione + "', '" + ambito + "', '" + jobFam
						+ "', '" + subFam + "', '" + stdJob + "', '" + jobLevel + "')");

		conn.close();
		return rs;
	}

	public int update(String email, String password, int ruoloId, String nome, String cognome, int valutatoreId,
			String societaOp, String mansione, String ambito, String jobFam, String subFam, String stdJob,
			String jobLevel, int utenteId) throws SQLException {

		Statement stmt = conn.createStatement();
		int rs = stmt.executeUpdate("UPDATE utente SET email = '" + email + "', password = '" + password
				+ "', ruolo_id = " + ruoloId + ", nome = '" + nome + "', cognome = '" + cognome
				+ "', valutatore_id = " + valutatoreId + ", societa_op = '" + societaOp + "', mansione = '"
				+ mansione + "', ambito = '" + ambito + "', job_fam = '" + jobFam + "', sub_fam = '" + subFam
				+ "', std_job = '" + stdJob + "', job_level = '" + jobLevel + "' WHERE utente_id = " + utenteId);

		conn.close();
		return rs;
	}

	public int logicDelete(int utenteId) throws SQLException {

		Statement stmt = conn.createStatement();
		int rs = stmt.executeUpdate("UPDATE utente SET flg_del = 1 WHERE utente_id = " + utenteId);

		conn.close();
		return rs;
	}

	public int delete(int utenteId) throws SQLException {

		Statement stmt = conn.createStatement();
		int rs = stmt.executeUpdate("DELETE FROM utente WHERE utente_id = " + utenteId);

		conn.close();
		return rs;
	}

	public UtenteBean findByEmail(String email) throws SQLException {

		UtenteBean utenteFound = new UtenteBean();

		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM utente WHERE email = '" + email + "'");
		while (rs.next()) {
			utenteFound.setUtenteId(rs.getInt("utente_id"));
			utenteFound.setEmail(email);
			utenteFound.setPassword(rs.getString("password"));
			utenteFound.setRuoloId(rs.getInt("ruolo_id"));
			utenteFound.setNome(rs.getString("nome"));
			utenteFound.setCognome(rs.getString("cognome"));
			utenteFound.setValutatoreId(rs.getInt("valutatore_id"));
			utenteFound.setSocietaOp(rs.getString("societa_op"));
			utenteFound.setMansione(rs.getString("mansione"));
			utenteFound.setAmbito(rs.getString("ambito"));
			utenteFound.setJobFam(rs.getString("job_fam"));
			utenteFound.setSubFam(rs.getString("sub_fam"));
			utenteFound.setStdJob(rs.getString("std_job"));
			utenteFound.setJobLevel(rs.getString("job_level"));
			utenteFound.setDataUltAcc(rs.getDate("data_ult_acc"));
			utenteFound.setDataUltMod(rs.getDate("data_ult_mod"));
			utenteFound.setDataCreaz(rs.getDate("data_creaz"));
			utenteFound.setFlgDel(rs.getBoolean("flg_del"));
		}

		conn.close();
		return utenteFound;
	}

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

}
