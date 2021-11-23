package Logica.crud.commands;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Logica.DataBaseManager;
import Logica.crud.dto.CitaDto;

public class ListCitasBySala 
{
	private String SQL = "select * from public.cita where id_sala=?";

	private String sala;
	
	public ListCitasBySala(String s) 
	{
		this.sala=obtenSala(s);
		

	}
	
	private String obtenSala(String s) {
		// TODO Auto-generated method stub
		return new GetSalaByName().execute(s);
	}

	public List<CitaDto> execute() {
		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		List<CitaDto> result = new ArrayList<CitaDto>();
		try {
			c = DataBaseManager.getConnection();
			
			pst = c.prepareStatement(SQL);
			pst.setString(1, sala);
			rs = pst.executeQuery();
			CitaDto cita= new CitaDto();
			if(rs.next()) {
				cita.id=rs.getString("id");
				cita.horaInicio=rs.getString("hora_inicio");
				cita.horaFinal=rs.getString("hora_fin"); 
				
				result.add(cita); //Añade a la lista la cita que tiene el metodo
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
