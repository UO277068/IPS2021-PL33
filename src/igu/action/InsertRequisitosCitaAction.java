package igu.action;

import Logica.BusinessFactory;
import Logica.crud.CitaCrudService;
import Logica.crud.dto.CitaDto;
import Logica.crud.dto.RequisitoDto;

public class InsertRequisitosCitaAction {
	
	private CitaCrudService service = BusinessFactory.forCitaCrudService();
	
	private RequisitoDto req;
	public InsertRequisitosCitaAction() 
	{
	}
	
	public void setReqDto(RequisitoDto r) {
		this.req=r;
	}
	
	public void execute() {
		try {
			service.insertRequisitos(req);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
