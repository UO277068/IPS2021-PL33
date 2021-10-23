package igu.action;

import Logica.BusinessFactory;
import Logica.crud.CitaCrudService;

public class AddCausasAction {

	 private CitaCrudService service = BusinessFactory.forCitaCrudService();
	 private String id;
	 private String causa;
		
	public AddCausasAction( String id, String causa) 
	{
		this.id=id;
	    this.causa=causa;
	}

		public void execute(){

			try {
				service.AddCausa(id, causa);
			} catch (Exception e) {
				e.printStackTrace();
			}	
		}
}
