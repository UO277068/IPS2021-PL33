package Logica.crud.commands;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import Logica.DataBaseManager;
import Logica.Vacuna;

public class AddVacuna {

	private String SQL = "INSERT INTO public.vacuna VALUES(?,?,?,?,?)";
	private String SQLID = "select count(*) from public.vacuna";
	private String id;
	private Timestamp fecha;
	private List<Vacuna> vacunas;
	private String idPaciente;
	
	public AddVacuna(String id, String idPaciente, Timestamp fecha, List<Vacuna> vacunas) {
		this.id=id;
		this.idPaciente=idPaciente;
		this.fecha=fecha;
		this.vacunas=vacunas;
	}
	
	public void execute() {
		Connection c = null;
		PreparedStatement pst = null;
		try {
			c = DataBaseManager.getConnection();
			int id = calculateId();
				for (Vacuna vacuna : vacunas) {
						pst = c.prepareStatement(SQL);
						pst.setInt(1,id);
						pst.setString(2, idPaciente);
						pst.setTimestamp(3, fecha);
						pst.setString(4, vacuna.getNombre());
						pst.setString(5, vacuna.getComponente());
						pst.executeUpdate();
						id++;
						pst.close();
				}
			
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
			if(id==0)
				id=1;
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
