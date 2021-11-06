package Logica.crud;

import java.sql.Timestamp;
import java.util.List;

import Logica.crud.commands.*;
import Logica.crud.dto.*;


public class CitaCrudServiceImpl implements CitaCrudService {

	@Override
	public void updateHoraEntradaSalida(String id, Timestamp entrada, Timestamp salida) throws Exception {
		new UpdateHoraEntradaSalida(id, entrada, salida).execute();
	}

	@Override
	public void addUbicacion(String id, String ubicacion) throws Exception {
		new AddUbicacion(id, ubicacion).execute();
		
	}

	@Override
	public PacienteDto listPacienteById(String id) throws Exception {
		return new ListPacienteById(id).execute();
		
	}

	@Override
	public List<PacienteDto> listAllPacientes() throws Exception {
		// TODO Auto-generated method stub
		return new ListAllPacientes().execute();
	}
//nuevo
	@Override
	public List<CitaDto> listAllCitasById(int id_medico) throws Exception {
		// TODO Auto-generated method stub
		return new ListAllCitasById(id_medico).execute();
	}

	@Override
	public String listDiagnosticoById(String id) throws Exception {
		// TODO Auto-generated method stub
		return new ListDiagnosticoById(id).execute();
	}

	@Override
	public String listVacunaById(String id) throws Exception {
		// TODO Auto-generated method stub
		return new ListVacunaById(id).execute();
	}

	@Override
	public List<MedicoDto> listAllMedicos() throws Exception {
		// TODO Auto-generated method stub
		return new ListAllMedicos().execute();
	}

	@Override
	public void addHorario(Timestamp d1, Timestamp d2, int opcion, int[] ids_medicos) throws Exception {
		new AddHorario(d1, d2, opcion, ids_medicos).execute();
		
	}
    //Rober
	
	@Override
	public List<JornadaDto> listJornadaLaboralByMedico(String id) {
		return new ListJornadaLaboralByMedico(id).execute();
	}

	@Override
	public List<CitaDto> ListCitasByMedico(String id) {
		return new ListCitasByMedico(id).execute();
	}

	@Override
	public int InsertCita(CitaDto cita) {
		return new InsertCita(cita).execute();
		
	}

	@Override
	public List<CitaDto> listAllCitas() {
		return new ListAllCitas().execute();
	}

	@Override
	public void EnviarEmail(String correo,PacienteDto paciente,CitaDto cita) {
		new EnviarGmailUrgente(correo,paciente,cita).execute();
		
	}
	
	//Fer
	@Override
	public void AddCausa(String id, String causa) {
		new AddCausa(id, causa).execute();
	}

	@Override
	public List<CitaDto> listAllCausas() {
		return new ListAllCausas().execute();
	}

	@Override
	public void AddPrescripcion(String id, String prescripcion) {
		new AddPrescripcion(id,prescripcion).execute();
		
	}

	@Override
	public List<CitaDto> listAllPrescripcion() {
		return new ListAllPrescripcion().execute();
	}

	@Override
	public void AddContactoCita(int id, String contacto) {
		new AddContactoCita(id,contacto).execute();
		
	}

	@Override
	public String getContactoByIdPaciente(String id) {
		return new GetContactoByIdPaciente(id).execute();
	}

	@Override
	public void updateCausasInHistorial(String id, String idPaciente, String causa) {
		new UpdateCausasInHistorial(id,idPaciente,causa).execute();;
		
	}

	@Override
	public void updatePrescripcionInHistorial(String id, String prescripcion) {
		new UpdatePrescripcionInHistorial( id,  prescripcion).execute();
		
	}

	@Override
	public void updateAcudioCita(String id, String acude) {
		new UpdateAcudioCita(id, acude).execute();
		
	}
	
	

}
