package igu.action;

import Logica.BusinessFactory;
import Logica.crud.CitaCrudService;
import Logica.crud.dto.SolicitudDto;

public class AddSolicitudAction {
	 private CitaCrudService service = BusinessFactory.forCitaCrudService();
	 private SolicitudDto sol;
		
		public AddSolicitudAction(SolicitudDto dto) 
		{
	      this.sol=dto;
		}

		public void execute(){

			try {
				service.AddSolicitud(sol);
			} catch (Exception e) {
				e.printStackTrace();
			}	
		}
}
