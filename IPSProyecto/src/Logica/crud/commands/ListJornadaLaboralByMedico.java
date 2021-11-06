package Logica.crud.commands;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Logica.DataBaseManager;
import Logica.crud.dto.JornadaDto;

public class ListJornadaLaboralByMedico 
{
	private String SQL = "select * from public.jornada_laboral where id_medico=?";

	private String id;
	
	
	public ListJornadaLaboralByMedico (String idMedico) 
	{
		this.id=idMedico;
	}
	
	public List<JornadaDto> execute() {
		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		List<JornadaDto> result = new ArrayList<JornadaDto>();
		try {
			c = DataBaseManager.getConnection();
			
			pst = c.prepareStatement(SQL);
			pst.setString(1, id);
			rs = pst.executeQuery();
			JornadaDto j=null;
			while(rs.next()) {
				j= new JornadaDto();
				j.diaEntrada=rs.getString("dia_entrada");
				j.diasalida=rs.getString("dia_salida");
				j.idMedico=rs.getString("id_medico");
				
				result.add(j); 
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
 