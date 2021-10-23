package Logica.crud.commands;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AddUbicacion {
	private String SQL = "update cita set ubicacion=? where id_cita=?";
	private String id;
	private String ubicacion;
	
	public AddUbicacion(String id, String ubicacion) {
		this.id=id;
		this.ubicacion=ubicacion;
	}
	
	public void execute() {
		Connection c = null;
		PreparedStatement pst = null;
		try {
			c = DriverManager.getConnection("");
			
			pst = c.prepareStatement(SQL);
			pst.setString(1, ubicacion);
			pst.setString(2, id);
			pst.executeUpdate();
			pst.close();
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		finally {
			if (c!=null) try{c.close();;} catch (SQLException e) {}
		}
	}
	
	
}
