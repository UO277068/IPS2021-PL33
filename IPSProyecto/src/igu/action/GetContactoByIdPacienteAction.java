package igu.action;


import Logica.BusinessFactory;
import Logica.crud.CitaCrudService;

public class GetContactoByIdPacienteAction {
	
	private String id;

	private CitaCrudService service = BusinessFactory.forCitaCrudService();
	
	public GetContactoByIdPacienteAction(String idPaciente) {
		this.id=idPaciente;
	}

	public String execute(){

		String contacto =null;
		try {
			contacto = service.getContactoByIdPaciente(this.id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return contacto;
		
	}
}
