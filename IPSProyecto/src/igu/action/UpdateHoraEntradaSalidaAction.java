package igu.action;

import java.sql.Timestamp;

import Logica.BusinessFactory;
import Logica.crud.CitaCrudService;

public class UpdateHoraEntradaSalidaAction {

	private CitaCrudService service = BusinessFactory.forCitaCrudService();
	private String entrada;
	private String salida;
	private String id;
	private String horaInicio;
	public UpdateHoraEntradaSalidaAction(String id, String entrada, String salida, String horaInicio) {
		this.id=id;
		this.entrada=entrada;
		this.salida=salida;
		this.horaInicio=horaInicio;
	}

	public void execute() {
		// Get info
		// Process
		try {
			String[] eh = entrada.split(":");
			String[] sh = salida.split(":");
			String[] h = horaInicio.split(" ");
			String es = h[0]+" "+entrada+":00";
			String ss = h[0]+" "+salida+":00";
			Timestamp e = Timestamp.valueOf(es);
			Timestamp s = Timestamp.valueOf(ss);
			service.updateHoraEntradaSalida(id, e, s);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		};
		
	}
}
