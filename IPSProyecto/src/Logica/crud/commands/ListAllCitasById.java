package Logica.crud.commands;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import Logica.DataBaseManager;
import Logica.crud.dto.CitaDto;


public class ListAllCitasById {
private String SQL = "select id, id_paciente, nombre, apellidos, hora_inicio, hora_fin, dni from public.cita c, public.paciente p where c.id_paciente=p.id and c.id_medico=?";

	
	private int id_medico;
	public ListAllCitasById(int id_medico) {
		this.id_medico=id_medico;
	}
	
	public List<CitaDto> execute() {
		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		List<CitaDto> result= new ArrayList<CitaDto>();
		CitaDto cita = null;
		try {
			c = DataBaseManager.getConnection();
			
			pst = c.prepareStatement(SQL);
			pst.setInt(1, id_medico);
			rs = pst.executeQuery();
			while(rs.next()) {
				cita = new CitaDto();
				cita.id=rs.getString("id");
				cita.idPaciente=rs.getString("id_paciente");
				cita.namePaciente=rs.getString("nombre");
				cita.surnamePaciente=rs.getString("apellidos");
				cita.horaInicio=rs.getString("hora_inicio");
				cita.horaFinal=rs.getString("hora_fin");
				result.add(cita);
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
