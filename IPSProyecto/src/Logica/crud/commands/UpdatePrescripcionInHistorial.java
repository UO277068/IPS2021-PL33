package Logica.crud.commands;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import Logica.DataBaseManager;

public class UpdatePrescripcionInHistorial {

	private String SQL = "update public.diagnostico set prescripcion=? where id_cita=?";
	private String diagnostico;
	private String id;
	
	public UpdatePrescripcionInHistorial(String id, String diagnostico) {
		this.id=id;
		this.diagnostico = diagnostico;
	}
	
	public void execute() {
		Connection c = null;
		PreparedStatement pst = null;
		try {
			c = DataBaseManager.getConnection();
			
			pst = c.prepareStatement(SQL);
			pst.setString(1, diagnostico);
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
