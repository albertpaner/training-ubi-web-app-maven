package model.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Bean.RuoloBean;
import utils.DBConnection;



public class RuoloDao {

    public List<RuoloBean> findAll() throws ClassNotFoundException, SQLException{

        List<RuoloBean> listaRuolo = new ArrayList();

        DBConnection dBConn = new DBConnection();
		Connection conn = dBConn.createConnection();
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM ruolo");

        while(rs.next()){

            RuoloBean ruoloBean = new RuoloBean();

            ruoloBean.setRuoloId(rs.getInt("ruolo_id"));
            ruoloBean.setDescrRl(rs.getString("descr_rl"));
            ruoloBean.setFlgDel(rs.getBoolean("flg_del"));

            listaRuolo.add(ruoloBean);
        
        }

        conn.close();
        return listaRuolo;


    }

    public  RuoloBean findById(int id) throws ClassNotFoundException, SQLException{

        RuoloBean ruoloBean = new RuoloBean();

        DBConnection dBConn = new DBConnection();
		Connection conn = dBConn.createConnection();
		PreparedStatement ps = conn.prepareStatement("SELECT * FROM ruolo WHERE ruolo_id = ?");
        ps.setFloat(1, id);
        ResultSet rs = ps.executeQuery();

        while (rs.next()){

            ruoloBean.setRuoloId(id);
            ruoloBean.setDescrRl(rs.getString("descr_rl"));
            ruoloBean.setFlgDel(rs.getBoolean("flg_del"));
        }

        conn.close();
        return ruoloBean;


    }

        public int create(int ruoloIdId, String descrRl,  Boolean flgDel) throws ClassNotFoundException, SQLException {

		Connection conn = DBConnection.createConnection();
		PreparedStatement ps = conn.prepareStatement("INSERT INTO ruolo (ruolo_id, descr_rl, flg_del) VALUES (?, ?, ?)");


		ps.setInt(1, ruoloIdId);
        ps.setString(2, descrRl);
		ps.setBoolean(3, flgDel);
		

		int execute_success = ps.executeUpdate();
		conn.close();

		return execute_success;
	}



	public int update(int ruoloIdId, String descrRl,  Boolean flgDel) throws ClassNotFoundException, SQLException {

		Connection conn = DBConnection.createConnection();


		PreparedStatement ps = conn.prepareStatement("UPDATE ruolo SET ruolo_id = ?,  descr_rl = ?,  flg_del = ?  WHERE ruolo_id = ?");
        ps.setInt(1, ruoloIdId);
        ps.setString(2, descrRl);
		ps.setBoolean(3, flgDel);
		
	
		int execute_success = ps.executeUpdate();
		conn.close();

		return execute_success;
	}

	// logic delete
	public int logicDeleteBook(int ruoloId) throws ClassNotFoundException, SQLException {

		Connection conn = DBConnection.createConnection();

		PreparedStatement ps = conn.prepareStatement("UPDATE ruolo SET flg_Del = true WHERE sezione_id = ?");
		ps.setInt(1, ruoloId);

		int execute_success = ps.executeUpdate();
		conn.close();

		return execute_success;
	}

	// physical delete
	public int deleteBook(int ruoloId) throws ClassNotFoundException, SQLException {

		Connection conn = DBConnection.createConnection();
		PreparedStatement ps = conn.prepareStatement("DELETE FROM ruolo WHERE ruolo_id = ?");
		ps.setInt(1, ruoloId);

		int execute_success = ps.executeUpdate();
		conn.close();

		return execute_success;
	}
    
}
