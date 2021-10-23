package igu.action;

import Logica.BusinessFactory;
import Logica.crud.CitaCrudService;

public class ListVacunaByIdAction {
	private CitaCrudService service = BusinessFactory.forCitaCrudService();
	String id;
	public ListVacunaByIdAction(String id) {
		this.id=id;
	}
	public String execute(){

		String nombre=null;
		try {
			nombre = service.listVacunaById(id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return nombre;
		
	}
}
