package igu.action;

import Logica.BusinessFactory;
import Logica.crud.CitaCrudService;

public class EnviarEmailUrgenteAction 
{
	private CitaCrudService service = BusinessFactory.forCitaCrudService();
	private String correo;
	
	public EnviarEmailUrgenteAction(String c) 
	{
		this.correo=c;
	}
	public void execute(){

		try {
			service.EnviarEmail(correo);
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}
}
