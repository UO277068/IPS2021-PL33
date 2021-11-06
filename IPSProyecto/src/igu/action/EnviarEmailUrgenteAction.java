package igu.action;

import Logica.BusinessFactory;
import Logica.crud.CitaCrudService;
import Logica.crud.dto.CitaDto;
import Logica.crud.dto.PacienteDto;

public class EnviarEmailUrgenteAction 
{
	private CitaCrudService service = BusinessFactory.forCitaCrudService();
	private String correo;
	private PacienteDto paciente;
	private CitaDto cita;
	
	public EnviarEmailUrgenteAction(String c,PacienteDto p,CitaDto ci) 
	{
		this.correo=c;
		this.paciente=p;
		this.cita=ci;
		
	}
	public void execute(){

		try {
			service.EnviarEmail(correo,paciente,cita);
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}
}
