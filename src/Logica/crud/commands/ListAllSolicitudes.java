package Logica.crud.commands;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Logica.DataBaseManager;
import Logica.crud.dto.CitaDto;
import Logica.crud.dto.SolicitudDto;

public class ListAllSolicitudes {
	private String SQL = " select * from public.solicitud";

	public List<SolicitudDto> execute() {
		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		
		List<SolicitudDto> solicitudes= new ArrayList<SolicitudDto>();
		SolicitudDto solicitud = null;
		try {
			c = DataBaseManager.getConnection();
			
			pst = c.prepareStatement(SQL);
			rs = pst.executeQuery();
			while(rs.next()) {
				solicitud = new SolicitudDto();
				solicitud.id = rs.getString("id");
				solicitud.tipo = rs.getString("tipo");
				solicitud.cuerpo = rs.getString("cuerpo");
				solicitud.observaciones = rs.getString("observaciones");
				
				solicitudes.add(solicitud);
			}
			pst.close();
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		finally {
			if (c!=null) try{c.close();;} catch (SQLException e) {}
		}
		return solicitudes;
	}

}
