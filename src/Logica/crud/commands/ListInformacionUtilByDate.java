package Logica.crud.commands;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import Logica.DataBaseManager;
import Logica.crud.dto.CitaDto;

public class ListInformacionUtilByDate 
{
	private String SQL = "select * from public.informacion";
	
	public String[] execute(Timestamp dia) {
		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		List<String> lista = new ArrayList<String>();
		try {
			c = DataBaseManager.getConnection();
			
			pst = c.prepareStatement(SQL);
			rs = pst.executeQuery();
			while(rs.next()) {
				String texto = rs.getString("texto");
				Timestamp inicio = rs.getTimestamp("hora_inicio");
				Timestamp fin = rs.getTimestamp("hora_fin");
				 if(dia.after(inicio) && dia.before(fin)) {
					 
					 lista.add(texto);
				 }
				
			}
			pst.close();
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		finally {
			if (c!=null) try{c.close();;} catch (SQLException e) {}
		}
		return lista.toArray(new String[lista.size()]);
	}
}
