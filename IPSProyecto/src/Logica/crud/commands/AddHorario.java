package Logica.crud.commands;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import Logica.DataBaseManager;

public class AddHorario {
	private Timestamp d1;
	private Timestamp d2;
	private int opcion;
	private int[] ids_medicos;
	private String SQL = "INSERT INTO public.jornada_laboral VALUES(?,?,?,?)";
	private String SQLID = "select count(*) from public.jornada_laboral";
	public AddHorario(Timestamp d1, Timestamp d2, int opcion, int[] ids_medicos) {
		this.d1=d1;
		this.d2=d2;
		this.opcion=opcion;
		this.ids_medicos=ids_medicos;
	}
	
	@SuppressWarnings("deprecation")
	public void execute() {
		Connection c = null;
		PreparedStatement pst = null;
		try {
			c = DataBaseManager.getConnection();
			int id = calculateId();
				while(d1.before(d2)) {
					for (int i = 0; i < ids_medicos.length; i++) {
						pst = c.prepareStatement(SQL);
						Timestamp d1s= new Timestamp(d1.getTime());
						d1s.setHours(d2.getHours());
						d1s.setMinutes(d2.getMinutes());
						pst.setInt(1,id);
						pst.setTimestamp(2, d1);
						pst.setTimestamp(3, d1s);
						System.out.println(d1.toLocalDateTime().toLocalDate().getDayOfWeek().toString());
						pst.setInt(4, ids_medicos[i]+1);
						pst.executeUpdate();
						id++;
					}
					if(opcion==1)
						d1.setDate(d1.getDate()+1);
					else if(opcion==2)
						d1.setDate(d1.getDate()+7);
					else break;
					pst.close();
				}
			
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
