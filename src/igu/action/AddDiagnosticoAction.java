package igu.action;

import java.sql.Timestamp;

import Logica.BusinessFactory;
import Logica.crud.CitaCrudService;

public class AddDiagnosticoAction {

	private CitaCrudService service = BusinessFactory.forCitaCrudService();
	private String causa;
	private String idCita;
	private String idPaciente;
	private String descripcion;
	private Timestamp fecha;
	private String estado;
	
	public AddDiagnosticoAction(String idPaciente, Timestamp fecha, String causa, String idCita, String estado, String descripcion) {
		this.causa=causa;
		this.idCita=idCita;
		this.idPaciente=idPaciente;
		this.fecha=fecha;
		this.descripcion=descripcion;
		this.estado=estado;
	}

	public void execute() {
		try {
			service.addDiagnostico( idPaciente, fecha, causa, idCita, estado, descripcion);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		};
		
	}
}
