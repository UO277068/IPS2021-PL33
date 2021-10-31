package Logica.crud.commands;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import Logica.DataBaseManager;
import Logica.crud.dto.CitaDto;

public class UpdateAcudioCita {

	private String SQL = "update public.cita set acudio_cita=? where id=?";
	private String acude;
	private String id;
	
	public UpdateAcudioCita(String id, String acude) {
		this.id=id;
		this.acude = acude;
	}
	
	public void execute() {
		Connection c = null;
		PreparedStatement pst = null;
		try {
			c = DataBaseManager.getConnection();
			
			pst = c.prepareStatement(SQL);
			pst.setString(1, acude);
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
