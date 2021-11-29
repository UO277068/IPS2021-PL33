package Logica.crud.commands;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Logica.DataBaseManager;
import Logica.crud.dto.VacunaDto;

public class ListVacunaById {
	private String SQL = "select vacuna, fecha from public.vacuna where id_paciente=?";

	private String id;
	
	public ListVacunaById(String id) {
		this.id=id;

	}
	
	public List<VacunaDto> execute() {
		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		//String result = null;
		List<VacunaDto> result = null;
		VacunaDto vacuna = null;
		try {
			c = DataBaseManager.getConnection();
			result = new ArrayList<>();
			pst = c.prepareStatement(SQL);
			pst.setString(1, id);
			rs = pst.executeQuery();
			//result="";
			while(rs.next()) {
				vacuna = new VacunaDto();
				vacuna.fecha= rs.getTimestamp("fecha");
				vacuna.vacuna=rs.getString("vacuna");
//				result+="Fecha de la visita: "+rs.getDate("fecha");
//				result+="\n\t-Vacuna: "+rs.getString("vacuna");
//				result+="\n";
				result.add(vacuna);
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
