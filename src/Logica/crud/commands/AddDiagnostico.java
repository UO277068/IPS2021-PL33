package Logica.crud.commands;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import Logica.DataBaseManager;

public class AddDiagnostico {

	private String SQL = "insert into public.diagnostico values (?,?,?,?,?,?,?,?,?)";
	private String causa;
	private String idPaciente;
	private Timestamp fecha;
	private String idCita;
	private String descripcion;
	private String SQLID = "select count(*) from public.diagnostico";
	private String SQLE = "select max(id) from public.diagnostico";
	private String estado;
	
	public AddDiagnostico(String idPaciente, Timestamp fecha, String causa, String idCita, String estado, String descripcion) {
		this.idPaciente=idPaciente;
		this.causa = causa;
		this.fecha=fecha;
		this.idCita=idCita;
		this.estado=estado;
		this.descripcion=descripcion;
	}
	
	public void execute() {
		Connection c = null;
		PreparedStatement pst = null;
		try {
			c = DataBaseManager.getConnection();
			
			pst = c.prepareStatement(SQL);
			pst.setInt(1, calculateId()+1);
			pst.setString(2, idPaciente);
			pst.setTimestamp(3, fecha);
			pst.setString(4, causa);
			pst.setString(5, null);
			pst.setString(6, estado);
			pst.setInt(7, calculateIdEnfermedad());
			pst.setInt(8, Integer.parseInt(idCita));
			pst.setString(9, descripcion);
			pst.executeUpdate();
			pst.close();
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		finally {
			if (c!=null) try{c.close();;} catch (SQLException e) {}
		}
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
	public int calculateIdEnfermedad() {
		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			c = DataBaseManager.getConnection();
			int id = 1;
			pst = c.prepareStatement(SQLE);
			rs=pst.executeQuery();
			if(rs.next())
				id=rs.getInt(1);
			pst.close();
			if(id==0)
				return 1;
			return id;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		finally {
			if (c!=null) try{c.close();;} catch (SQLException e) {}
		}
	}
}
