package igu.action;

import Logica.BusinessFactory;
import Logica.crud.CitaCrudService;

public class AddContactoCitaAction {

	 private CitaCrudService service = BusinessFactory.forCitaCrudService();
	 private int id;
	 private String contacto;
		
	public AddContactoCitaAction( int idCita, String contacto) 
	{
	   this.contacto=contacto;
	   this.id=idCita;
	}

		public void execute(){

			try {
				service.AddContactoCita(id, contacto);
			} catch (Exception e) {
				e.printStackTrace();
			}	
		}
}
