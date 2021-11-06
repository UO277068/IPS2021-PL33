package Logica.crud.commands;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import Logica.DataBaseManager;

public class UpdateCausasInHistorial {

	private String SQL = "insert into public.diagnostico values (?,?,?,?,?,?)";
	private String SQLHora = "select hora_fin from public.cita where id=?";
	private String causa;
	private String id;
	private String idPaciente;
	private String SQLID = "select count(*) from public.diagnostico";
	
	public UpdateCausasInHistorial(String id, String idPaciente, String causa) {
		this.id=id;
		this.idPaciente=idPaciente;
		this.causa = causa;
	}
	
	public void execute() {
		Connection c = null;
		PreparedStatement pst = null;
		try {
			c = DataBaseManager.getConnection();
			
			pst = c.prepareStatement(SQL);
			pst.setInt(1, calculateId()+1);
			pst.setString(2, idPaciente);
			pst.setTimestamp(3, getHora());
			pst.setString(4, causa);
			pst.setString(5, null);
			pst.setString(6, id);
			pst.executeUpdate();
			pst.close();
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		finally {
			if (c!=null) try{c.close();;} catch (SQLException e) {}
		}
	}
	
	private Timestamp getHora() {
		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		Timestamp t = null;
		try {
			c = DataBaseManager.getConnection();
			
			pst = c.prepareStatement(SQLHora);
			pst.setString(1, id);
			rs = pst.executeQuery();
			
			if(rs.next()) {
				t=rs.getTimestamp(1);
			}
			pst.close();
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		finally {
			if (c!=null) try{c.close();;} catch (SQLException e) {}
		}
		return t;
	}
	
	public int calculateId() {
		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			c = DataBaseManager.getConnection();
			int id = 1;
			pst = c.prepareStatement(SQLID);
			rs=pst.executeQuery();
			if(rs.next())
				id=rs.getInt(1);
			pst.close();
			
			return id;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		finally {
			if (c!=null) try{c.close();;} catch (SQLException e) {}
		}
	}
}
