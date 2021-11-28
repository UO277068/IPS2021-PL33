package Logica.crud.commands;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Logica.DataBaseManager;
import Logica.crud.dto.CitaDto;

public class ListCitaById {

	private String SQL = "select * from public.cita where id=?";

	private int id;
	
	public ListCitaById(int id) {
		this.id=id;

	}
	
	public CitaDto execute() {
		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		CitaDto cita = new CitaDto();
		try {
			c = DataBaseManager.getConnection();
			
			pst = c.prepareStatement(SQL);
			pst.setInt(1, id);
			rs = pst.executeQuery();

			if(rs.next()) {
				cita.id=rs.getString("id");
				cita.idMedico = rs.getString("id_medico");
				cita.idPaciente=rs.getString("id_paciente");
				cita.horaInicio=rs.getString("hora_inicio");
				cita.horaFinal=rs.getString("hora_fin");
				cita.idSala = rs.getString("id_sala");
				
			}
			pst.close();
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		finally {
			if (c!=null) try{c.close();;} catch (SQLException e) {}
		}
		return cita;
	}
}
