package Logica.crud.commands;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Logica.DataBaseManager;
import Logica.crud.dto.DiagnosticoDto;

public class ListDiagnosticoByPaciente {
	private String SQL = "select distinct diagnostico from public.diagnostico where id_paciente=?";

	private String id;
	
	public ListDiagnosticoByPaciente(String id) {
		this.id=id;

	}
	
	public List<DiagnosticoDto> execute() {
		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		List<DiagnosticoDto> lista = new ArrayList<>();
		DiagnosticoDto result = null;
		try {
			c = DataBaseManager.getConnection();
			
			pst = c.prepareStatement(SQL);
			pst.setString(1, id);
			rs = pst.executeQuery();
			while(rs.next()) {
				result= new DiagnosticoDto();
				result.diagnostico=rs.getString(1);
				lista.add(result);
			}
			pst.close();
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		finally {
			if (c!=null) try{c.close();;} catch (SQLException e) {}
		}
		return lista;
	}
}
