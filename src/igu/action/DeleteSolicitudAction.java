package igu.action;


import Logica.BusinessFactory;
import Logica.crud.CitaCrudService;

public class DeleteSolicitudAction {

	private CitaCrudService service = BusinessFactory.forCitaCrudService();
	private String id;

	public DeleteSolicitudAction(String id) {
		this.id=id;
	}
	
	public void execute(){

		try {
			service.deleteSolicitud(id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
