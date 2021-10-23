package Logica.crud.commands;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

import Logica.DataBaseManager;


public class UpdateHoraEntradaSalida {
	private String SQL = "update public.cita set hora_entrada=?, hora_salida=? where id=?";
	private Timestamp entrada;
	private Timestamp salida;
	private String id;
	
	public UpdateHoraEntradaSalida(String id, Timestamp entrada, Timestamp salida ) {
		this.id=id;
		this.entrada=entrada;
		this.salida=salida;
	}
	
	public void execute() {
		Connection c = null;
		PreparedStatement pst = null;
		try {
			c = DataBaseManager.getConnection();
			pst = c.prepareStatement(SQL);
			pst.setTimestamp(1, entrada);
			pst.setTimestamp(2, salida);
			pst.setString(3, id);
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
