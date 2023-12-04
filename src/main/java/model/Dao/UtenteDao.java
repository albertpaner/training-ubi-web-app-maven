package model.Dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Bean.UtenteBean;
import utils.DBConnection;

public class UtenteDao {

	public List<UtenteBean> findAll() throws ClassNotFoundException, SQLException {

		List<UtenteBean> listaUtenti = new ArrayList<>();

		Connection conn = DBConnection.createConnection();

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
			utenteBean.setResposabileId(rs.getInt("responsabile_id"));
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

	public UtenteBean findById(int utenteId) throws ClassNotFoundException, SQLException {

		UtenteBean utenteBean = new UtenteBean();

		Connection conn = DBConnection.createConnection();

		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM utente WHERE utente_id = " + utenteId);
		while (rs.next()) {
			utenteBean.setUtenteId(utenteId);
			utenteBean.setEmail(rs.getString("email"));
			utenteBean.setPassword(rs.getString("password"));
			utenteBean.setRuoloId(rs.getInt("ruolo_id"));
			utenteBean.setNome(rs.getString("nome"));
			utenteBean.setCognome(rs.getString("cognome"));
			utenteBean.setResposabileId(rs.getInt("responsabile_id"));
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

	public int create(String email, String password, int ruoloId, String nome, String cognome, int resposabileId,
			String societaOp, String mansione, String ambito, String jobFam, String subFam, String stdJob,
			String jobLevel) throws ClassNotFoundException, SQLException {

		Connection conn = DBConnection.createConnection();

		Statement stmt = conn.createStatement();
		int rs = stmt.executeUpdate(
				"INSERT INTO utente (email, password, ruolo_id, nome, cognome, responsabile_id, societa_op, mansione, ambito, job_fam, sub_fam, std_job, job_level) VALUES ('"
						+ email + "', '" + password + "', " + ruoloId + ", '" + nome + "', '" + cognome + "', "
						+ resposabileId + ", '" + societaOp + "', '" + mansione + "', '" + ambito + "', '" + jobFam
						+ "', '" + subFam + "', '" + stdJob + "', '" + jobLevel + "')");

		conn.close();
		return rs;
	}

	public int update(String email, String password, int ruoloId, String nome, String cognome, int resposabileId,
			String societaOp, String mansione, String ambito, String jobFam, String subFam, String stdJob,
			String jobLevel, int utenteId) throws ClassNotFoundException, SQLException {

		Connection conn = DBConnection.createConnection();

		Statement stmt = conn.createStatement();
		int rs = stmt.executeUpdate("UPDATE utente SET email = '" + email + "', password = '" + password
				+ "', ruolo_id = " + ruoloId + ", nome = '" + nome + "', cognome = '" + cognome
				+ "', responsabile_id = " + resposabileId + ", societa_op = '" + societaOp + "', mansione = '"
				+ mansione + "', ambito = '" + ambito + "', job_fam = '" + jobFam + "', sub_fam = '" + subFam
				+ "', std_job = '" + stdJob + "', job_level = '" + jobLevel + "' WHERE utente_id = " + utenteId);

		conn.close();
		return rs;
	}

	public int logicDelete(int utenteId) throws ClassNotFoundException, SQLException {

		Connection conn = DBConnection.createConnection();

		Statement stmt = conn.createStatement();
		int rs = stmt.executeUpdate("UPDATE utente SET flg_del = 1 WHERE utente_id = " + utenteId);

		conn.close();
		return rs;
	}

	public int delete(int utenteId) throws ClassNotFoundException, SQLException {

		Connection conn = DBConnection.createConnection();

		Statement stmt = conn.createStatement();
		int rs = stmt.executeUpdate("DELETE FROM utente WHERE utente_id = " + utenteId);

		conn.close();
		return rs;
	}

	public UtenteBean findByEmail(String email) throws ClassNotFoundException, SQLException {

		UtenteBean utenteFound = new UtenteBean();

		Connection conn = DBConnection.createConnection();

		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM utente WHERE email = '" + email + "'");
		while (rs.next()) {
			utenteFound.setUtenteId(rs.getInt("utente_id"));
			utenteFound.setEmail(email);
			utenteFound.setPassword(rs.getString("password"));
			utenteFound.setRuoloId(rs.getInt("ruolo_id"));
			utenteFound.setNome(rs.getString("nome"));
			utenteFound.setCognome(rs.getString("cognome"));
			utenteFound.setResposabileId(rs.getInt("responsabile_id"));
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

	public List<UtenteBean> findAllValutatori() throws ClassNotFoundException, SQLException {
		List<UtenteBean> listaValutatori = new ArrayList<>();

		Connection conn = DBConnection.createConnection();

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
			utenteBean.setResposabileId(rs.getInt("responsabile_id"));
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
			listaValutatori.add(utenteBean);
		}

		conn.close();
		return listaValutatori;

	}

}
