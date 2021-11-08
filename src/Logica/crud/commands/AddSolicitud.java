package Logica.crud.commands;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Logica.DataBaseManager;
import Logica.crud.dto.SolicitudDto;

public class AddSolicitud {

	//SQL
	  private String SQL = "insert into public.solicitud values (?,?,?,?)";
	  private SolicitudDto solicitud;
	  private String SQLID = "select count(*) from public.solicitud";
	  
	  public AddSolicitud(SolicitudDto dto) 
	  {
	    if(dto==null) {throw new RuntimeException("Cita en capa logica es null");}
	    this.solicitud=dto;
	        
	  }
	   
	  public void execute() 
	   {
		   Connection c = null;
			PreparedStatement pst = null;
			try {
				c = DataBaseManager.getConnection();
				
				pst = c.prepareStatement(SQL);
				//Atributos
				pst.setInt(1,calculateId()+1);
				pst.setString(2,solicitud.tipo);
				pst.setString(3, solicitud.cuerpo);
				pst.setString(4, solicitud.observaciones);
				
				pst.executeUpdate();

				pst.close();
				
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
			finally {
				if (c!=null) try{c.close();;} catch (SQLException e) {}
			}
			
	   }
	  
	  public int calculateId() {
			Connection c = null;
			PreparedStatement pst = null;
			ResultSet rs = null;
			try {
				c = DataBaseManager.getConnection();
				int id = 1;
				pst = c.prepareStatement(SQLID);
				rs=pst.executeQuery();
				if(rs.next())
					id=rs.getInt(1);
				pst.close();
				
				return id;
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
			finally {
				if (c!=null) try{c.close();;} catch (SQLException e) {}
			}
		}
}
