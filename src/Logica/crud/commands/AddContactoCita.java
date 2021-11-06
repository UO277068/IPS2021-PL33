package Logica.crud.commands;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import Logica.DataBaseManager;

public class AddContactoCita {
	 private int id;
	  private String contacto;
	  
	  private String SQL = "update public.cita set contacto=? where id=?";
	  
	  public AddContactoCita(int id, String contacto) 
	  {
	   this.contacto=contacto;
	   this.id=id;
	        
	  }
	  
	  public void execute() 
	   {
		   Connection c = null;
			PreparedStatement pst = null;
			try {
				c = DataBaseManager.getConnection();
				
				pst = c.prepareStatement(SQL);
				pst.setString(1, contacto);
				pst.setInt(2,id);
				
				
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
