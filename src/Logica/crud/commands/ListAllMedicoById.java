package Logica.crud.commands;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Logica.DataBaseManager;
import Logica.crud.dto.CitaDto;
import Logica.crud.dto.MedicoDto;

public class ListAllMedicoById {

private String SQL = "select dni, nombre, apellidos, especialidad, correo from public.medico where id=?";

	
	private int id_medico;
	public ListAllMedicoById(int id_medico) {
		this.id_medico=id_medico;
	}
	
	public MedicoDto execute() {
		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		MedicoDto medico = null;
		try {
			c = DataBaseManager.getConnection();
			
			pst = c.prepareStatement(SQL);
			pst.setInt(1, id_medico);
			rs = pst.executeQuery();
			while(rs.next()) {
				medico = new MedicoDto();
				medico.dni= rs.getString("dni"); 
				medico.name= rs.getString("nombre"); 
				medico.surname= rs.getString("apellidos"); 
				medico.especialidad= rs.getString("especialidad"); 
				medico.correo= rs.getString("correo"); 
			}
			pst.close();
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		finally {
			if (c!=null) try{c.close();;} catch (SQLException e) {}
		}
		return medico;
	}
}
