package Logica.crud.commands;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import Logica.DataBaseManager;

public class DeleteCita {

	private String SQL = "delete from public.cita where id=?";
	private String id;

	public DeleteCita(String id) {
		this.id = id;

	}

	public void execute() {
		Connection c = null;
		PreparedStatement pst = null;

		try {
			c = DataBaseManager.getConnection();

			pst = c.prepareStatement(SQL);
			pst.setString(1, id);
			pst.execute();
			pst.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			if (c != null)
				try {
					c.close();
					;
				} catch (SQLException e) {
				}
		}
	}
}
