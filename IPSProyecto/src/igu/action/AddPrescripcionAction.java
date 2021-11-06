package igu.action;

import Logica.BusinessFactory;
import Logica.crud.CitaCrudService;

public class AddPrescripcionAction {

	private CitaCrudService service = BusinessFactory.forCitaCrudService();
	 private String id;
	 private String prescripcion;
		
	public AddPrescripcionAction(String id, String prescripcion) 
	{
	   this.prescripcion=prescripcion;
	   this.id=id;
	}

		public void execute(){

			try {
				service.AddPrescripcion(id,prescripcion);
			} catch (Exception e) {
				e.printStackTrace();
			}	
		}
	  
}
