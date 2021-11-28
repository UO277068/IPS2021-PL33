package igu.action;


import Logica.BusinessFactory;
import Logica.crud.CitaCrudService;
import Logica.crud.dto.MedicoDto;

public class ListAllMedicoByIdAction {

	private CitaCrudService service = BusinessFactory.forCitaCrudService();
	private int id_medico;
	public ListAllMedicoByIdAction(int id_medico) {
		this.id_medico=id_medico;
	}
	public MedicoDto execute(){

		MedicoDto medico=null;
		try {
			medico = service.listAllMedicoById(id_medico);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return medico;
		
	}
}
