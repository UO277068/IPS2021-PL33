package Logica.crud.commands;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Logica.DataBaseManager;
import Logica.crud.dto.PacienteDto;



public class ListPacienteById {
	
	private String SQL = "select nombre, apellidos from public.paciente where id=?";

	private String id;
	
	public ListPacienteById(String id) {
		this.id=id;

	}
	
	public PacienteDto execute() {
		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		PacienteDto result = null;
		try {
			c = DataBaseManager.getConnection();
			
			pst = c.prepareStatement(SQL);
			pst.setString(1, id);
			rs = pst.executeQuery();
			result= new PacienteDto();
			if(rs.next()) {
				result.id=id;
				result.name=rs.getString("nombre");
				result.surname=rs.getString("apellidos");
			}
			pst.close();
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		finally {
			if (c!=null) try{c.close();;} catch (SQLException e) {}
		}
		return result;
	}
}
