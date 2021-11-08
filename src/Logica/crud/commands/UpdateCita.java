package Logica.crud.commands;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

import Logica.DataBaseManager;

public class UpdateCita {
	private String idMedico;
	private Timestamp horaInicio;
	private Timestamp horaFin;
	private String idSala;
	private String idCita;
	private String SQL = "update public.cita set id_medico=?, hora_inicio=?, hora_fin=?, id_sala=? where id=?";
	
	public UpdateCita(String idMedico, Timestamp horaInicio, Timestamp horaFin, String idSala, String idCita) {
		this.idMedico=idMedico;
		this.horaInicio=horaInicio;
		this.horaFin=horaFin;
		this.idSala=idSala;
		this.idCita=idCita;
	}
	
	public void execute() {
		Connection c = null;
		PreparedStatement pst = null;
		try {
			c = DataBaseManager.getConnection();
			
			pst = c.prepareStatement(SQL);
			pst.setString(1, idMedico);
			pst.setTimestamp(2, horaInicio);
			pst.setTimestamp(3, horaFin);
			pst.setString(4, idSala);
			pst.setString(5, idCita);
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
