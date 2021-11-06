package igu.action;

import Logica.BusinessFactory;
import Logica.crud.CitaCrudService;

public class ListDiagnosticoByIdAction {
	private CitaCrudService service = BusinessFactory.forCitaCrudService();
	String id;
	public ListDiagnosticoByIdAction(String id) {
		this.id=id;
	}
	public String execute(){

		String nombre=null;
		try {
			nombre = service.listDiagnosticoById(id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return nombre;
		
	}
}
