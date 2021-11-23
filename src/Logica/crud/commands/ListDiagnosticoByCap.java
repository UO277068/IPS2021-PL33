package Logica.crud.commands;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Logica.DataBaseManager;
import Logica.crud.dto.DiagnosticoDto;

public class ListDiagnosticoByCap {

	private String cap;
	private String SQLRange = "select * from public.cie10 where codigo=?";
	private String SQL = "select * from cie10 where codigo like '%-%'";
	public ListDiagnosticoByCap(String cap) {
		this.cap=cap;
	}
	
	public List<DiagnosticoDto> execute() {
		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		List<DiagnosticoDto> result = new ArrayList<DiagnosticoDto>();
		boolean start = false;
		String range = getRange(cap);
		try {
			DiagnosticoDto diagnostico = null;
			c = DataBaseManager.getConnection();
			pst = c.prepareStatement(SQL);
			rs = pst.executeQuery();
			while(rs.next()) {
				diagnostico= new DiagnosticoDto();
				diagnostico.id=rs.getString("id");
				diagnostico.codigo=rs.getString("codigo");
				diagnostico.descripcion=rs.getString("descripcion"); 
				if(diagnostico.codigo.contains(range.substring(0,3)))
					start = true;
				if(start==true)
					result.add(diagnostico); //A�ade a la lista la cita que tiene el metodo
				if(diagnostico.codigo.contains(range.substring(4,7)))
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
	
	private String getRange(String cap) {
		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		DiagnosticoDto diagnostico = null;
		String range="";
		try {
			c = DataBaseManager.getConnection();
			
			pst = c.prepareStatement(SQLRange);
			pst.setString(1, cap);
			rs = pst.executeQuery();
			diagnostico= new DiagnosticoDto();
			if(rs.next()) {
				diagnostico.id=rs.getString("id");
				diagnostico.codigo=rs.getString("codigo");
				diagnostico.descripcion=rs.getString("descripcion"); 
				
			}
			pst.close();
			range=diagnostico.descripcion.split("\\(")[1];
			range=range.substring(0,8);
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		finally {
			if (c!=null) try{c.close();;} catch (SQLException e) {}
		}
		return range;
	}

}
