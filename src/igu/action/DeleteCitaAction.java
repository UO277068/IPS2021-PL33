package igu.action;

import Logica.BusinessFactory;
import Logica.crud.CitaCrudService;

public class DeleteCitaAction {

	private CitaCrudService service = BusinessFactory.forCitaCrudService();
	private String id;

	public DeleteCitaAction(String id) {
		this.id=id;
	}
	
	public void execute(){

		try {
			service.deleteCita(id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
