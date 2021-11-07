package igu.action;

import java.sql.Timestamp;

import Logica.BusinessFactory;
import Logica.crud.CitaCrudService;

public class UpdateCitaAction {

	private CitaCrudService service = BusinessFactory.forCitaCrudService();
	private String idMedico;
	private Timestamp horaInicio;
	private Timestamp horaFin;
	private String idSala;
	private String idCita;
	public UpdateCitaAction(String idMedico, Timestamp horaInicio, Timestamp horaFin, String idSala, String idCita) {
		this.idMedico=idMedico;
		this.horaInicio=horaInicio;
		this.horaFin=horaFin;
		this.idSala=idSala;
		this.idCita=idCita;
	}
	
	public void execute() {
		try {
			service.updateCita(idMedico, horaInicio, horaFin, idSala, idCita);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		};
	}

}
