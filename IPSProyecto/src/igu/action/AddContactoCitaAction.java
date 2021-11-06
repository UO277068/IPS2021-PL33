package igu.action;

import Logica.BusinessFactory;
import Logica.crud.CitaCrudService;

public class AddContactoCitaAction {

	 private CitaCrudService service = BusinessFactory.forCitaCrudService();
	 private String id;
	 private String contacto;
		
	public AddContactoCitaAction( String id, String contacto) 
	{
	   this.contacto=contacto;
	   this.id=id;
	}

		public void execute(){

			try {
				service.AddContactoCita(id, contacto);
			} catch (Exception e) {
				e.printStackTrace();
			}	
		}
}
