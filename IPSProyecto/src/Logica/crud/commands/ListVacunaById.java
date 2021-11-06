package Logica.crud.commands;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Logica.DataBaseManager;

public class ListVacunaById {
	private String SQL = "select vacuna, fecha from public.vacuna where id_paciente=?";

	private String id;
	
	public ListVacunaById(String id) {
		this.id=id;

	}
	
	public String execute() {
		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		String result = null;
		try {
			c = DataBaseManager.getConnection();
			
			pst = c.prepareStatement(SQL);
			pst.setString(1, id);
			rs = pst.executeQuery();
			result="";
			while(rs.next()) {
				result+="Fecha de la visita: "+rs.getDate("fecha");
				result+="\n\t-Vacuna: "+rs.getString("vacuna");
				result+="\n";
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
