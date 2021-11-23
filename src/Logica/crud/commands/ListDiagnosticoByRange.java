package Logica.crud.commands;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Logica.DataBaseManager;
import Logica.crud.dto.DiagnosticoDto;

public class ListDiagnosticoByRange {
	private String cap;
	private String SQLCode = "select * from public.cie10 where codigo=?";
	private String SQL = "select * from cie10 where codigo not like '%-%' "
			+ "and codigo not like 'Cap%' and codigo not like '%.%' and "
			+ "codigo like ?";
	public ListDiagnosticoByRange(String cap) {
		this.cap=cap;
	}
	
	public List<DiagnosticoDto> execute() {
		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		boolean start = false;
		List<DiagnosticoDto> result = new ArrayList<DiagnosticoDto>();
		try {
			DiagnosticoDto diagnostico = null;
			c = DataBaseManager.getConnection();
			pst = c.prepareStatement(SQL);
			pst.setString(1, cap.substring(0,1)+"%");
			rs = pst.executeQuery();
			while(rs.next()) {
				diagnostico= new DiagnosticoDto();
				diagnostico.id=rs.getString("id");
				diagnostico.codigo=rs.getString("codigo");
				diagnostico.descripcion=rs.getString("descripcion"); 
				if(diagnostico.codigo.contains(cap.substring(0,3)))
					start = true;
				if(start==true)
					result.add(diagnostico); //A�ade a la lista la cita que tiene el metodo
				if(cap.length()>=7 && cap.substring(4,7).equals(diagnostico.codigo))
					break;
				else if(cap.length()<7&& diagnostico.codigo.contains(cap.substring(0,3)))
					break;
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
