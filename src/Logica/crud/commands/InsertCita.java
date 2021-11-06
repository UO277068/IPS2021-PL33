package Logica.crud.commands;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Logica.DataBaseManager;
import Logica.crud.dto.CitaDto;

public class InsertCita 
{

  //SQL
  private String SQL = "insert into public.cita values (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
  private CitaDto cita;
  private String SQLID = "select count(*) from public.cita";
  public InsertCita(CitaDto dto) 
  {
    if(dto==null) {throw new RuntimeException("Cita en capa logica es null");}
    this.cita=dto;
        
  }
   
  public int execute() 
   {
	   Connection c = null;
		PreparedStatement pst = null;
		try {
			c = DataBaseManager.getConnection();
			
			pst = c.prepareStatement(SQL);
			//Atributos
			pst.setInt(1,calculateId()+1);
			pst.setString(2,cita.causa);
			pst.setString(3,cita.preescripcion);
			pst.setString(4,cita.urgencia);
			pst.setString(5,cita.horaInicio);
			pst.setString(6,cita.horaFinal);
			pst.setString(7,cita.horaEntrada);
			pst.setString(8,cita.horaSalida);
			pst.setString(9,cita.acude);
			pst.setString(10,cita.contacto);
			pst.setString(11,cita.Especialidad);
			pst.setString(12,cita.idMedico);
			pst.setString(13,cita.idPaciente);
			pst.setString(14,cita.idSala);
			
			pst.executeUpdate();

			pst.close();
			
			return calculateId()+1;
			
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
