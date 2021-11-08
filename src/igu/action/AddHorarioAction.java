package igu.action;

import java.sql.Timestamp;
import java.util.List;

import Logica.BusinessFactory;
import Logica.crud.CitaCrudService;

public class AddHorarioAction {
	private Timestamp d1;
	private Timestamp d2;
	private List<Boolean> dias;
	private int[] ids_medicos;
	private CitaCrudService service = BusinessFactory.forCitaCrudService();
	public AddHorarioAction(Timestamp d1, Timestamp d2, List<Boolean> dias, int ids_medicos[]) {
		this.d1=d1;
		this.d2=d2;
		this.dias=dias;
		this.ids_medicos=ids_medicos;
	}
	
	public void execute() {
		try {
			service.addHorario(d1, d2,dias, ids_medicos);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
