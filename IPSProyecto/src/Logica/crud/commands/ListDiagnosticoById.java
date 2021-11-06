package Logica.crud.commands;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Logica.DataBaseManager;

public class ListDiagnosticoById {
	private String SQL = "select causa, prescripcion, fecha from public.diagnostico where id_paciente=?";

	private String id;
	
	public ListDiagnosticoById(String id) {
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
				result+="\n\t-Causa de la visita: "+rs.getString("causa");
				result+="\n\t-Prescripcion de la visita: "+rs.getString("prescripcion");
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
