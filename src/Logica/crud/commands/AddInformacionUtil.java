package Logica.crud.commands;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Logica.DataBaseManager;
import Logica.crud.dto.InformacionDto;

public class AddInformacionUtil 
{
	private String SQL = "insert into public.informacion values (?,?,?,?)";
	private String SQLID = "select count(*) from public.informacion";

	
	 public void execute(InformacionDto info) 
	   {
		   Connection c = null;
			PreparedStatement pst = null;
			try {
				c = DataBaseManager.getConnection();
				
				pst = c.prepareStatement(SQL);
				pst.setInt(1,calculateId()+1);
				pst.setString(2, info.texto);
				pst.setTimestamp(3, info.inicio);
				pst.setTimestamp(4, info.fin);
				
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
