package Logica.crud.commands;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Logica.DataBaseManager;
import Logica.crud.dto.RequisitoDto;

public class InsertRequisitos {
 
	 private String SQL = "insert into public.requisitos values (?,?,?,?)";
	 private String SQLID = "select count(*) from public.requisitos";
	 
	 private RequisitoDto requisito;
	 public InsertRequisitos(RequisitoDto req) {
		 this.requisito=req;
	 }
	
	public void execute() {
		
		Connection c = null;
		PreparedStatement pst = null;
		try {
			c = DataBaseManager.getConnection();
			
			pst = c.prepareStatement(SQL);
			//Atributos
			pst.setInt(1,calculateId()+1);
			pst.setString(2,requisito.especialidad);
			pst.setString(3,requisito.numeroMedicos);
			pst.setString(4,requisito.cita);
			
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
