package Logica.crud.commands;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Logica.DataBaseManager;

public class GetContactoByIdPaciente {

	private String SQL = " select * from public.paciente where id=?";

	private String id;

	public GetContactoByIdPaciente(String id) {
		this.id=id;
	}
	
	public String execute() {
		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		String str = "";
		
		try {
			c = DataBaseManager.getConnection();
			
			pst = c.prepareStatement(SQL);
			pst.setString(1, id);
			rs = pst.executeQuery();
			while(rs.next()){
			str =rs.getString("contacto");
			}
			pst.close();
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		finally {
			if (c!=null) try{c.close();;} catch (SQLException e) {}
		}
		return str;
	}
}
