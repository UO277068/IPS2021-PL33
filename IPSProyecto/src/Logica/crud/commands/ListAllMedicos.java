package Logica.crud.commands;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Logica.DataBaseManager;
import Logica.crud.dto.MedicoDto;

public class ListAllMedicos 
{
	private String SQL = "select * from public.medico";
	
	public List<MedicoDto> execute() {
		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		List<MedicoDto> medicos= new ArrayList<MedicoDto>();
		MedicoDto medico = null;
		try {
			c = DataBaseManager.getConnection();
			
			pst = c.prepareStatement(SQL);
			rs = pst.executeQuery();
			while(rs.next()) {
				medico = new MedicoDto();
				medico.id=rs.getString("id");
				medico.dni=rs.getString("dni");
				medico.name=rs.getString("nombre");
				medico.surname=rs.getString("apellidos");
				medico.correo=rs.getString("correo");
				medicos.add(medico);
			}
			pst.close();
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		finally {
			if (c!=null) try{c.close();;} catch (SQLException e) {}
		}
		return medicos;
	}
}
