package Logica.crud;

import java.sql.Timestamp;
import java.util.List;

import Logica.Vacuna;
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
	public List<DiagnosticoDto> listDiagnosticoById(String id) throws Exception {
		// TODO Auto-generated method stub
		return new ListDiagnosticoById(id).execute();
	}

	@Override
	public List<VacunaDto> listVacunaById(String id) throws Exception {
		// TODO Auto-generated method stub
		return new ListVacunaById(id).execute();
	}

	@Override
	public List<MedicoDto> listAllMedicos() throws Exception {
		// TODO Auto-generated method stub
		return new ListAllMedicos().execute();
	}

	@Override
	public void addHorario(Timestamp d1, Timestamp d2, List<Boolean> dias, int[] ids_medicos) throws Exception {
		new AddHorario(d1, d2, dias, ids_medicos).execute();
		
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
	public void addDiagnostico(String idPaciente, Timestamp fecha, String causa,String idCita, String estado, String descripcion) {
		new AddDiagnostico(idPaciente, fecha, causa, idCita, estado, descripcion).execute();;
		

	}

	@Override
	public void updatePrescripcionInHistorial(String id, String prescripcion) {
		new UpdatePrescripcionInHistorial( id,  prescripcion).execute();
		
	}

	@Override
	public void updateAcudioCita(String id, String acude) {
		new UpdateAcudioCita(id, acude).execute();
		
	}

	@Override
	public void updateCita(String idMedico, Timestamp horaInicio, Timestamp horaFin, String idSala, String idCita) {
		new UpdateCita(idMedico, horaInicio, horaFin, idSala, idCita).execute();
		
	}

	@Override
	public void AddSolicitud(SolicitudDto sol) {
		new AddSolicitud(sol).execute();
		
	}

	@Override
	public void addVacuna(String idPaciente, Timestamp fecha, List<Vacuna> vacunas) {
		new AddVacuna(idPaciente, fecha, vacunas).execute();
		
	}

	@Override
	public List<DiagnosticoDto> listDiagnosticoByCap(String cap) {
		return new ListDiagnosticoByCap(cap).execute();
	}

	@Override
	public List<DiagnosticoDto> listDiagnosticoByRange(String cap) {
		return new ListDiagnosticoByRange(cap).execute();
	}

	@Override
	public List<DiagnosticoDto> listDiagnosticoByCode(String cap) {
		return new ListDiagnosticoByCode(cap).execute();
	}

	@Override
	public List<DiagnosticoDto> listDiagnostico(String id) {
		return new ListDiagnostico(id).execute();
	}

	@Override
	public List<DiagnosticoDto> listDiagnosticoByPaciente(String id) {
		return new ListDiagnosticoByPaciente(id).execute();
	}
	
	@Override
	public List<SolicitudDto> listAllSolicitudes() {
		return new ListAllSolicitudes().execute();
	}

	@Override
	public void deleteSolicitud(String id) {
		new DeleteSolicitud(id).execute();
		
	}

	@Override
	public void deleteCita(String id) {
		new DeleteCita(id).execute();
		
	}

	@Override
	public void insertRequisitos(RequisitoDto dto) {
		new InsertRequisitos(dto).execute();
	}

	public MedicoDto listAllMedicoById(int id_medico) {
		return new ListAllMedicoById(id_medico).execute();
	}

	@Override
	public CitaDto listCitaById(int id) {
		return new ListCitaById(id).execute();
	}

	@Override
	public List<DiagnosticoDto> listAllDiagnosticos() {
		return new ListAllDiagnosticos().execute();
	}
	

}
