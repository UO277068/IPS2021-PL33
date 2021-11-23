package Logica.crud;

import java.sql.Timestamp;
import java.util.List;

import Logica.Vacuna;
import Logica.crud.dto.*;

/**
 * This service is intended to be used by the Manager
 * It follows the ISP principle (@see SOLID principles from RC Martin)
 */
public interface CitaCrudService {

	void updateHoraEntradaSalida(String id, Timestamp entrada, Timestamp salida) throws Exception;
	void addUbicacion(String id, String ubicacion) throws Exception;
	PacienteDto listPacienteById(String id) throws Exception;
	List<PacienteDto> listAllPacientes() throws Exception;
	//Nuevo
	List<CitaDto> listAllCitasById(int id_medico) throws Exception;
	String listDiagnosticoById(String id) throws Exception;
	String listVacunaById(String id) throws Exception;
	List<MedicoDto> listAllMedicos() throws Exception;
	void addHorario(Timestamp d1, Timestamp d2, List<Boolean> dias, int[] ids_medicos) throws Exception;
	void updateCita(String idMedico, Timestamp horaInicio, Timestamp horaFin, String idSala, String idCita);
	void addVacuna(String id, String idPaciente, Timestamp fecha, List<Vacuna> vacunas);
	List<DiagnosticoDto> listDiagnosticoByCap(String cap);
	List<DiagnosticoDto> listDiagnosticoByRange(String cap);
	List<DiagnosticoDto> listDiagnosticoByCode(String cap);
	//Rober
	List<JornadaDto> listJornadaLaboralByMedico(String id);
	List<CitaDto> ListCitasByMedico(String id);
	int InsertCita(CitaDto cita);
	List<CitaDto> listAllCitas();
	void EnviarEmail(String correo,PacienteDto paciente,CitaDto cita);
	//fer
	void AddCausa(String id, String causa);
	List<CitaDto> listAllCausas();
	void AddPrescripcion(String id, String prescripcion);
	List<CitaDto> listAllPrescripcion();
	void AddContactoCita(int id, String contacto);
	String getContactoByIdPaciente(String id);
	void addDiagnostico(String id, String idPaciente, String causa);
	void updatePrescripcionInHistorial(String id, String prescripcion);
	void updateAcudioCita(String id,String acude);
	void AddSolicitud(SolicitudDto sol);
	List<SolicitudDto> listAllSolicitudes();
	void deleteSolicitud(String id);
	void deleteCita(String id);
}
