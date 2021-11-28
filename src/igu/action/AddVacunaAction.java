
package igu.action;

import java.sql.Timestamp;
import java.util.List;

import Logica.BusinessFactory;
import Logica.Vacuna;
import Logica.crud.CitaCrudService;

public class AddVacunaAction {
	String idPaciente;
	private Timestamp fecha;
	private List<Vacuna> vacunas;
	private CitaCrudService service = BusinessFactory.forCitaCrudService();

	public AddVacunaAction(String idPaciente, Timestamp fecha, List<Vacuna> vacunas) {
		this.idPaciente=idPaciente;
		this.fecha=fecha;
		this.vacunas=vacunas;
	}
	
	public void execute() {
		try {
			service.addVacuna(idPaciente, fecha, vacunas);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

