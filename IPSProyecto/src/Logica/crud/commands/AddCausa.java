package Logica.crud.commands;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import Logica.DataBaseManager;

public class AddCausa {

	  private String id;
	  private String causa;
	  
	  private String SQL = "update public.cita set causa=? where id=?";
	  
	  public AddCausa(String id, String causa) 
	  {
	    if(id==null) {throw new RuntimeException("Cita en capa logica es null");}
	   this.causa=causa;
	   this.id=id;
	        
	  }
	  
	  public void execute() 
	   {
		   Connection c = null;
			PreparedStatement pst = null;
			try {
				c = DataBaseManager.getConnection();
				
				pst = c.prepareStatement(SQL);
				pst.setString(1, causa);
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
