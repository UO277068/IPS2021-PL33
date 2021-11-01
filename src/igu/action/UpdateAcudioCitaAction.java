package igu.action;

import Logica.BusinessFactory;
import Logica.crud.CitaCrudService;

public class UpdateAcudioCitaAction {
	private CitaCrudService service = BusinessFactory.forCitaCrudService();
	private String acude;
	private String id;
	
	public UpdateAcudioCitaAction(String id, String acude) {
		this.acude=acude;
		this.id=id;
	}

	public void execute() {
		try {
			service.updateAcudioCita(id, acude);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		};
		
	}
}
