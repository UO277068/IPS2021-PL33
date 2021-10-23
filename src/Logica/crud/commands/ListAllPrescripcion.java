package Logica.crud.commands;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import Logica.DataBaseManager;
import Logica.crud.dto.CitaDto;

public class ListAllPrescripcion {
	
	private String SQL = " select distinct prescripcion from public.diagnostico where prescripcion is not null";

	public List<CitaDto> execute() {
		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		
		List<CitaDto> prescripciones= new ArrayList<CitaDto>();
		CitaDto prescripcion = null;
		try {
			c = DataBaseManager.getConnection();
			
			pst = c.prepareStatement(SQL);
			rs = pst.executeQuery();
			while(rs.next()) {
				prescripcion = new CitaDto();
				prescripcion.preescripcion=rs.getString("prescripcion");
				
				prescripciones.add(prescripcion);
			}
			pst.close();
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		finally {
			if (c!=null) try{c.close();;} catch (SQLException e) {}
		}
		return prescripciones;
	}
}
