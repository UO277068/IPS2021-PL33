package Logica.crud.commands;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import Logica.DataBaseManager;

public class AddPrescripcion {

	private String id;
	  private String prescripcion;
	  
	  private String SQL = "update cita set prescripcion=? where id=?";
	  
	  public AddPrescripcion(String id, String prescripcion) 
	  {
	   if(id==null) {throw new RuntimeException("Cita en capa logica es null");}
	   
	   this.prescripcion=prescripcion;
	   this.id=id;
	        
	  }
	  
	  public void execute() 
	   {
		   Connection c = null;
			PreparedStatement pst = null;
			try {
				c = DataBaseManager.getConnection();
				
				pst = c.prepareStatement(SQL);
				pst.setString(1, prescripcion);
				pst.setString(2, id);	
				
				pst.executeUpdate();

				pst.close();
				
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
			finally {
				if (c!=null) try{c.close();;} catch (SQLException e) {}
			}
	   }
	  
}
