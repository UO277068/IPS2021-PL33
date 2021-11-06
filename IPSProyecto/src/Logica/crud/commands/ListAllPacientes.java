package Logica.crud.commands;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Logica.DataBaseManager;
import Logica.crud.dto.PacienteDto;


public class ListAllPacientes {
	private String SQL = "select * from public.paciente";
	

	public ListAllPacientes() {}
	
	public List<PacienteDto> execute() {
		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		List<PacienteDto> nombre= new ArrayList<PacienteDto>();
		PacienteDto paciente = null;
		try {
			c = DataBaseManager.getConnection();
			
			pst = c.prepareStatement(SQL);
			rs = pst.executeQuery();
			while(rs.next()) {
				paciente = new PacienteDto();
				paciente.id=rs.getString("id");
				paciente.name=rs.getString("nombre");
				paciente.dni=rs.getString("dni");
				paciente.surname=rs.getString("apellidos");
				paciente.contacto=rs.getString("contacto");
				nombre.add(paciente);
			}
			pst.close();
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		finally {
			if (c!=null) try{c.close();;} catch (SQLException e) {}
		}
		return nombre;
	}
}
