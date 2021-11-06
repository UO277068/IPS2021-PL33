package Logica.crud.commands;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import Logica.DataBaseManager;
import Logica.crud.dto.CitaDto;

public class ListAllCausas {

private String SQL = " select distinct causa from public.cita where causa is not null";
	
	public List<CitaDto> execute() {
		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		
		List<CitaDto> citas= new ArrayList<CitaDto>();
		CitaDto cita = null;
		try {
			c = DataBaseManager.getConnection();
			
			pst = c.prepareStatement(SQL);
			rs = pst.executeQuery();
			while(rs.next()) {
				cita = new CitaDto();
				cita.causa=rs.getString("causa");
				
				citas.add(cita);
			}
			pst.close();
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		finally {
			if (c!=null) try{c.close();;} catch (SQLException e) {}
		}
		return citas;
	}
}
