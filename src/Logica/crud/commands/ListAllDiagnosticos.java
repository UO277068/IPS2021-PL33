package Logica.crud.commands;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Logica.DataBaseManager;
import Logica.crud.dto.DiagnosticoDto;

public class ListAllDiagnosticos {
	private String SQL = "select * from public.cie10 where codigo like '%.%' and codigo not like 'Cap%'";
	public ListAllDiagnosticos() {
	}
	
	public List<DiagnosticoDto> execute() {
		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		List<DiagnosticoDto> result = new ArrayList<DiagnosticoDto>();
		try {
			DiagnosticoDto diagnostico = null;
			c = DataBaseManager.getConnection();
			pst = c.prepareStatement(SQL);
			rs = pst.executeQuery();
			while(rs.next()) {
				diagnostico= new DiagnosticoDto();
				diagnostico.id=rs.getString("id");
				diagnostico.codigo=rs.getString("codigo");
				diagnostico.diagnostico=rs.getString("descripcion"); 
				result.add(diagnostico);
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
