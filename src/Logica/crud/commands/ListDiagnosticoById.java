package Logica.crud.commands;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Logica.DataBaseManager;
import Logica.crud.dto.DiagnosticoDto;

public class ListDiagnosticoById {
	private String SQL = "select diagnostico, prescripcion, fecha, descripcion, activa from public.diagnostico where id_paciente=?";

	private String id;
	
	public ListDiagnosticoById(String id) {
		this.id=id;

	}
	
	public List<DiagnosticoDto> execute() {
		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		List<DiagnosticoDto> result=null;
		//String result = null;
		DiagnosticoDto diag = null;
		try {
			c = DataBaseManager.getConnection();
			
			pst = c.prepareStatement(SQL);
			pst.setString(1, id);
			rs = pst.executeQuery();
			result=new ArrayList<>();
			while(rs.next()) {
				diag = new DiagnosticoDto();
				diag.fecha=rs.getTimestamp("fecha");
				diag.diagnostico=rs.getString("diagnostico");
				diag.descripcion=rs.getString("descripcion");
				diag.prescripcion=rs.getString("prescripcion");
				diag.status=rs.getString("activa");
				result.add(diag);
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
