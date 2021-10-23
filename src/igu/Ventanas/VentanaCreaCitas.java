package igu.Ventanas;



import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JTextField;
import javax.swing.ListModel;

import igu.action.EnviarEmailUrgenteAction;
import igu.action.InsertCitaAction;
import igu.action.ListAllMedicosAction;
import igu.action.ListAllPacientesAction;
import igu.action.ListCitasByMedicoAction;
import igu.action.ListJornadaLaboralByMedicoAction;


import javax.swing.JRadioButton;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Cursor;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.ComponentOrientation;
import java.awt.Rectangle;
import javax.swing.JScrollPane;
import javax.swing.border.BevelBorder;
import java.awt.FlowLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.border.SoftBevelBorder;

import Logica.crud.dto.*;

public class VentanaCreaCitas extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel panelDatos;
	private JPanel panelBotones;
	private JPanel panelDatosPrincipal;
	private JPanel panelMedicosPacientes;
	private JLabel lblMedicos;
	private JPanel panelHoraUrgencia;
	private JPanel panelAviso;
	private JPanel panelHora;
	private JLabel lblHoraInicio;
	private JLabel lblHoraFinCita;
	private JTextField textFieldHoraInicio;
	private JTextField textFieldHoraFin;
	private JPanel panelUrgencia;
	private JRadioButton rdbtnUrgente;
	private JButton btnCrearCita;
	
	//Atributos
	private JPanel panelPaciente;
	private JTextField textFieldAvisoUsuario;
	private JLabel lblFecha;
	private JTextField textFieldFecha;
	private JPanel panelComboPaciente;
	private JPanel panelFiltro;
	private JLabel lblPaciente;
	private JComboBox<String> comboBoxPaciente;
	private JTextField textFieldFiltroPaciente;
	private JPanel panelMedico;
	private JTextField textFieldFiltroMedico;
	private JScrollPane scrollPaneMedicos;
	
	List<PacienteDto> pacientes;
	List<MedicoDto> medicos;


	private VentanaPrincipal ventana;
	private JList<String> listMedicos;

	/**
	 * Create the application.
	 */
	public VentanaCreaCitas(VentanaPrincipal pedro) 
	{
		this.ventana=pedro;
		initialize();
	}
	
	public VentanaPrincipal getVentana() 
	{
		return this.ventana;
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		//Inicializa atributos
				this.medicos = new ListAllMedicosAction().execute();

				
				this.pacientes = new ListAllPacientesAction().execute();
		//
		this.setMaximumSize(new Dimension(350, 400));
		this.setMinimumSize(new Dimension(300, 320));
		this.setBounds(100, 100, 450, 300);
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.getContentPane().add(getPanelDatos(), BorderLayout.CENTER);
		this.getContentPane().add(getPanelBotones(), BorderLayout.SOUTH);
		
	}

	private JPanel getPanelDatos() {
		if (panelDatos == null) {
			panelDatos = new JPanel();
			panelDatos.setLayout(new BorderLayout(0, 0));
			panelDatos.add(getPanelDatosPrincipal(), BorderLayout.CENTER);
			panelDatos.add(getPanelAviso(), BorderLayout.SOUTH);
		}
		return panelDatos;
	}
	private JPanel getPanelBotones() {
		if (panelBotones == null) {
			panelBotones = new JPanel();
			panelBotones.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
			panelBotones.add(getBtnCrearCita());
		}
		return panelBotones;
	}
	private JPanel getPanelDatosPrincipal() {
		if (panelDatosPrincipal == null) {
			panelDatosPrincipal = new JPanel();
			panelDatosPrincipal.setLayout(new GridLayout(2, 1, 0, 0));
			panelDatosPrincipal.add(getPanelMedicosPacientes());
			panelDatosPrincipal.add(getPanelHoraUrgencia());
		}
		return panelDatosPrincipal;
	}
	private JPanel getPanelMedicosPacientes() {
		if (panelMedicosPacientes == null) {
			panelMedicosPacientes = new JPanel();
			panelMedicosPacientes.setLayout(new BorderLayout(0, 10));
			panelMedicosPacientes.add(getLblMedicos(), BorderLayout.WEST);
			panelMedicosPacientes.add(getPanelPaciente(), BorderLayout.NORTH);
			panelMedicosPacientes.add(getPanelMedico(), BorderLayout.CENTER);
		}
		return panelMedicosPacientes;
	}
	private JLabel getLblMedicos() {
		if (lblMedicos == null) {
			lblMedicos = new JLabel("Medicos:");
		}
		return lblMedicos;
	}
	private JPanel getPanelHoraUrgencia() {
		if (panelHoraUrgencia == null) {
			panelHoraUrgencia = new JPanel();
			panelHoraUrgencia.setLayout(new BorderLayout(0, 0));
			panelHoraUrgencia.add(getPanelHora(), BorderLayout.CENTER);
			panelHoraUrgencia.add(getPanelUrgencia(), BorderLayout.SOUTH);
		}
		return panelHoraUrgencia;
	}
	private JPanel getPanelAviso() {
		if (panelAviso == null) {
			panelAviso = new JPanel();
			panelAviso.setLayout(new GridLayout(0, 1, 0, 0));
			panelAviso.add(getTextFieldAvisoUsuario());
		}
		return panelAviso;
	}
	private JPanel getPanelHora() {
		if (panelHora == null) {
			panelHora = new JPanel();
			panelHora.setLayout(new GridLayout(3, 2, 0, 0));
			panelHora.add(getLblHoraInicio());
			panelHora.add(getTextFieldHoraInicio());
			panelHora.add(getLblHoraFinCita());
			panelHora.add(getTextFieldHoraFin());
			panelHora.add(getLblFecha());
			panelHora.add(getTextFieldFecha());
		}
		return panelHora;
	}
	private JLabel getLblHoraInicio() {
		if (lblHoraInicio == null) {
			lblHoraInicio = new JLabel("Hora de Inicio:");
			lblHoraInicio.setOpaque(true);
		}
		return lblHoraInicio;
	}
	private JLabel getLblHoraFinCita() {
		if (lblHoraFinCita == null) {
			lblHoraFinCita = new JLabel("Hora de fin de cita:");
		}
		return lblHoraFinCita;
	}
	private JTextField getTextFieldHoraInicio() {
		if (textFieldHoraInicio == null) {
			textFieldHoraInicio = new JTextField();
			textFieldHoraInicio.addFocusListener(new FocusAdapter() {
				@Override
				public void focusGained(FocusEvent e) 
				{
					textFieldHoraInicio.selectAll();
				}
			});
			textFieldHoraInicio.setText("Hora:Minutos");
			textFieldHoraInicio.setName("");
			textFieldHoraInicio.setToolTipText("");
			textFieldHoraInicio.setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
			textFieldHoraInicio.setColumns(10);
		}
		return textFieldHoraInicio;
	}
	private JTextField getTextFieldHoraFin() {
		if (textFieldHoraFin == null) {
			textFieldHoraFin = new JTextField();
			textFieldHoraFin.addFocusListener(new FocusAdapter() {
				@Override
				public void focusGained(FocusEvent e) 
				{
					textFieldHoraFin.selectAll();
				}
			});
			textFieldHoraFin.setText("Hora:Minutos");
			textFieldHoraFin.setColumns(10);
		}
		return textFieldHoraFin;
	}
	private JPanel getPanelUrgencia() {
		if (panelUrgencia == null) {
			panelUrgencia = new JPanel();
			panelUrgencia.add(getRdbtnUrgente());
		}
		return panelUrgencia;
	}
	private JRadioButton getRdbtnUrgente() {
		if (rdbtnUrgente == null) {
			rdbtnUrgente = new JRadioButton("Es Urgente");
			rdbtnUrgente.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		}
		return rdbtnUrgente;
	}
	private JButton getBtnCrearCita() {
		if (btnCrearCita == null) {
			btnCrearCita = new JButton("Crear cita");
			btnCrearCita.setBounds(new Rectangle(0, 0, 59, 0));
			btnCrearCita.setAlignmentY(Component.BOTTOM_ALIGNMENT);
			btnCrearCita.setAlignmentX(Component.RIGHT_ALIGNMENT);
			btnCrearCita.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
			btnCrearCita.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			btnCrearCita.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					getTextFieldAvisoUsuario().setVisible(true);
					crearCita();
				}
			});
			
		}
		return btnCrearCita;
	}
	
	private JPanel getPanelPaciente() {
		if (panelPaciente == null) {
			panelPaciente = new JPanel();
			panelPaciente.setLayout(new GridLayout(0, 2, 0, 0));
			panelPaciente.add(getPanelComboPaciente());
			panelPaciente.add(getPanelFiltro());
		}
		return panelPaciente;
	}
	
	private JTextField getTextFieldAvisoUsuario() {
		if (textFieldAvisoUsuario == null) {
			textFieldAvisoUsuario = new JTextField();
			textFieldAvisoUsuario.setMaximumSize(new Dimension(200, 100));
			textFieldAvisoUsuario.setEditable(false);
			textFieldAvisoUsuario.setColumns(10);
		}
		return textFieldAvisoUsuario;
	}
	
	private JLabel getLblFecha() {
		if (lblFecha == null) {
			lblFecha = new JLabel("Fecha cita:");
		}
		return lblFecha;
	}
	private JTextField getTextFieldFecha() {
		if (textFieldFecha == null) {
			textFieldFecha = new JTextField();
			textFieldFecha.addFocusListener(new FocusAdapter() {
				@Override
				public void focusGained(FocusEvent e) 
				{
					textFieldFecha.selectAll();
				}
			});
			textFieldFecha.setText("A\u00F1o-Mes-Dia");
			textFieldFecha.setColumns(10);
		}
		return textFieldFecha;
	}
	
	private JPanel getPanelComboPaciente() {
		if (panelComboPaciente == null) {
			panelComboPaciente = new JPanel();
			panelComboPaciente.setLayout(new BorderLayout(0, 0));
			panelComboPaciente.add(getLblPaciente(), BorderLayout.WEST);
			panelComboPaciente.add(getComboBoxPaciente());
		}
		return panelComboPaciente;
	}
	private JPanel getPanelFiltro() {
		if (panelFiltro == null) {
			panelFiltro = new JPanel();
			panelFiltro.setLayout(new BorderLayout(0, 0));
			panelFiltro.add(getTextFieldFiltroPaciente());
		}
		return panelFiltro;
	}
	private JLabel getLblPaciente() {
		if (lblPaciente == null) {
			lblPaciente = new JLabel("Paciente:");
			lblPaciente.setHorizontalAlignment(SwingConstants.LEFT);
		}
		return lblPaciente;
	}
	private JComboBox<String> getComboBoxPaciente() {
		if (comboBoxPaciente == null) {
			comboBoxPaciente = new JComboBox<String>();
			String[] pacientesstr = pacientesToString(this.pacientes);
			comboBoxPaciente.setModel(new DefaultComboBoxModel<String>(pacientesstr));
			comboBoxPaciente.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
			comboBoxPaciente.setBounds(new Rectangle(0, 0, 5, 5));
			comboBoxPaciente.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
			comboBoxPaciente.setAlignmentY(1.0f);
		}
		return comboBoxPaciente;
	}
	private JTextField getTextFieldFiltroPaciente() {
		if (textFieldFiltroPaciente == null) {
			textFieldFiltroPaciente = new JTextField();
			textFieldFiltroPaciente.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
			textFieldFiltroPaciente.addKeyListener(new KeyAdapter() {
				@Override
				public void keyReleased(KeyEvent e) 
				{
					String[] pacientesstr = pacientesToString(filtrarListaPacientes(pacientes,getTextFieldFiltroPaciente().getText()));
					getComboBoxPaciente().setModel(new DefaultComboBoxModel<String>(pacientesstr));
				}
			});
			textFieldFiltroPaciente.setColumns(10);
		}
		return textFieldFiltroPaciente;
	}
	

	private JPanel getPanelMedico() {
		if (panelMedico == null) {
			panelMedico = new JPanel();
			panelMedico.setLayout(new BorderLayout(0, 0));
			panelMedico.add(getTextFieldFiltroMedico(), BorderLayout.NORTH);
			panelMedico.add(getScrollPaneMedicos(), BorderLayout.CENTER);
		}
		return panelMedico;
	}
	private JTextField getTextFieldFiltroMedico() {
		if (textFieldFiltroMedico == null) {
			textFieldFiltroMedico = new JTextField();
			textFieldFiltroMedico.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
			textFieldFiltroMedico.addKeyListener(new KeyAdapter() {
				@Override
				public void keyReleased(KeyEvent e) 
				{
					String[] medicosstr = medicosToString(filtrarListaMedicos(medicos,getTextFieldFiltroMedico().getText()));
					ListModel<String> model = new DefaultComboBoxModel<String>(medicosstr);
					listMedicos.setModel(model);
				}

			});
			textFieldFiltroMedico.setColumns(10);
		}
		return textFieldFiltroMedico;
	}
	private JScrollPane getScrollPaneMedicos() {
		if (scrollPaneMedicos == null) {
			scrollPaneMedicos = new JScrollPane();
			scrollPaneMedicos.setViewportView(getListMedicos());
		}
		return scrollPaneMedicos;
	}
	private JList<String> getListMedicos() {
		if (listMedicos == null) {
			listMedicos = new JList<String>();
			String[] medicosstr = medicosToString(this.medicos);
			listMedicos = new JList<String>();
			listMedicos.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			ListModel<String> model = new DefaultComboBoxModel<String>(medicosstr);
			listMedicos.setModel(model);
		}
		return listMedicos;
	}
	
	//Metodos privados
	public void crearCita() 
	{
		if(checkValoresVentanaPrincipal()) //Comprueba que se han elegido los valores minimos para crear una cita
		{
		List<MedicoDto> medicosSelecionados = obtenMedicosSeleccionados();
		PacienteDto paciente =  pacientes.get(getComboBoxPaciente().getSelectedIndex());
		boolean urge = getRdbtnUrgente().isSelected();
		for(MedicoDto medico:medicosSelecionados) {
		
		//Comprueba que la cita se establece dentro de la jornada laboral
	    if(compruebaJornada(medico.id)) {
		//Comprobacion de que el medico no tiene citas a esa hora
		if(compruebaHora(medico.id)) {
			
		CitaDto cita = new CitaDto();
		cita.idMedico=medico.id;
		cita.idPaciente=paciente.id; //elegir mas de un paciente
		cita.causa="";
		cita.horaInicio=getTextFieldFecha().getText()+" "+getTextFieldHoraInicio().getText()+":00"; //fecha +hora inicio
		cita.horaFinal=getTextFieldFecha().getText()+" "+getTextFieldHoraFin().getText()+":00";
		
		if(urge) {
		cita.urgencia="Si";
		enviarGmail(medico.correo);
		}else {
		cita.urgencia="No";
		}
		cita.horaEntrada=null;
		cita.horaSalida=null;
		cita.idSala="1";
		cita.preescripcion="";
		cita.contacto=paciente.contacto; //Por defecto

		new InsertCitaAction(cita).execute(); //A�adir todos los atributos
		
    	getTextFieldAvisoUsuario().setText("La cita se ha insertado correctamete");

		
		}else {
			//getTextFieldAvisoUsuario().setText("El medico "+medico.name+" "+medico.surname+" tiene otra cita a esa hora");
			//Mostrar mensaje en panel aviso de medico determinado no disponible
			JOptionPane.showInternalMessageDialog(null,"El medico "+medico.name+" "+medico.surname+" tiene otra cita a esa hora");

		}
		}else {
			JOptionPane.showInternalMessageDialog(null,"El medico "+medico.name+" "+medico.surname+" no puede atenderle a esa hora(Jornada Laboral)");
		}
	}
  }
  }
	

	private void enviarGmail(String correo) 
	{
		new EnviarEmailUrgenteAction(correo).execute();
		
	}

	public boolean compruebaHora(String idMedico)  //Comprueba que los medicos no estan ocupados a esa hora y si estan en su jornada laboral
	{
		String fecha = getTextFieldFecha().getText();
		String horaInicio = getTextFieldHoraInicio().getText();
		String horafin =  getTextFieldHoraFin().getText();
		String horaInicioUser = fecha+" "+horaInicio+":00";
		String horafinUser =  fecha+" "+horafin+":00";
		Timestamp horainiciostamp = Timestamp.valueOf(horaInicioUser);
		Timestamp horafinstamp= Timestamp.valueOf(horafinUser);
		List<CitaDto> citasmedico = new ListCitasByMedicoAction(idMedico).execute(); //Obtiene las citas para el medico.
		
		for(CitaDto c: citasmedico) 
		{
        //comprueba la hora de las citas
		if(!(horainiciostamp.before(Timestamp.valueOf(c.horaInicio)) && horafinstamp.before(Timestamp.valueOf(c.horaFinal)) 
		   || horainiciostamp.after(Timestamp.valueOf(c.horaInicio)) && horafinstamp.after(Timestamp.valueOf(c.horaFinal))))  // comprobacion de hora
		 {
			return false;
		 }
		}
		return true;
		
	}
	
	private boolean compruebaJornada(String idMedico) 
	{
		String fecha = getTextFieldFecha().getText();
		String horaInicio = getTextFieldHoraInicio().getText();
		String horafin =  getTextFieldHoraFin().getText();
		String horaInicioUser = fecha+" "+horaInicio+":00";
		String horafinUser =  fecha+" "+horafin+":00";
		Timestamp horainiciostamp = Timestamp.valueOf(horaInicioUser);
		Timestamp horafinstamp= Timestamp.valueOf(horafinUser);
		List<JornadaDto> jornadas = new ListJornadaLaboralByMedicoAction(idMedico).execute();
		
		for(JornadaDto jornada:jornadas) 
		{
			Timestamp inicioMedico = Timestamp.valueOf(jornada.diaEntrada);
			Timestamp finMedico = Timestamp.valueOf(jornada.diasalida);
			if(horainiciostamp.after(inicioMedico) && horafinstamp.before(finMedico)) 
			{
				return true;
			}			 
		}
		return false;
	}
	
	private String[] pacientesToString(List<PacienteDto> paciente) {
		String[] strPacientes = new String[paciente.size()];
		for(int i=0;i<paciente.size();i++) 
		{
			strPacientes[i] =paciente.get(i).name+" "+paciente.get(i).surname;
		}
		return strPacientes;
	}
	
	private String[] medicosToString(List<MedicoDto> medico) {
		String[] strMedicos = new String[medico.size()];
		for(int i=0;i<medico.size();i++) 
		{
			strMedicos[i] =medico.get(i).name+" "+medico.get(i).surname;
		}
		return strMedicos;
	}
	
	private List<MedicoDto> obtenMedicosSeleccionados()
	{
		int[] index=getListMedicos().getSelectedIndices();
		if(index.length>0) {
		List<MedicoDto> listaSeleccionados = new ArrayList<MedicoDto>();
		for(int i=0;i<index.length;i++) 
		{
			listaSeleccionados.add(this.medicos.get(index[i]));
		}
		return listaSeleccionados;
		}
		return null;
	}
	
	//Metodos de checkeo/Comprobacion de los campos introducidos
	
	private boolean checkValoresVentanaPrincipal() 
	{
		if(!checkCamposVacios()) {
			//getTextFieldAvisoUsuario().setText("Se deben rellenar todos los campos correctamente para poder realizar una cita");
			JOptionPane.showInternalMessageDialog(null,"Se deben rellenar todos los campos correctamente para poder realizar una cita");
			return false;
		}
		if(!checkHoraFecha()) {
			//getTextFieldAvisoUsuario().setText("Formato Incorrecto en fecha y horas-> Formato: HH:MM (y) YYYY:MM:DD");
			JOptionPane.showInternalMessageDialog(null,"Formato Incorrecto en fecha y horas-> Formato: HH:MM (y) YYYY:MM:DD");
		}
		return true;
		
	   	
	}

	private boolean checkHoraFecha() {
		//Compruba que se ha seleccionado hora inicial y hora final;
		String horainicio = getTextFieldHoraInicio().getText();
		String fecha = getTextFieldFecha().getText();
		String horafin = getTextFieldHoraFin().getText();
		if(!checkIsHora(horainicio) || !checkIsHora(horafin) || !checkFecha(fecha)) {
			return false;
		}
		return true;
	}

	private boolean checkCamposVacios() {
		//Compruba que se han seleccionado medicos
		if(getListMedicos().isSelectionEmpty()) 
		{
			return false;
		}
		return true;
	}
	
	private boolean checkFecha(String fecha) 
	{
		String[] fechadividida=fecha.split("-");
		if(fechadividida.length!=3) 
		{
			return false;
		}
		
		return true;
	}

	private boolean checkIsHora(String hora) 
	{
		String[] horadividida=hora.split(":");
		if(horadividida.length!=2)
		{
			return false;
		}
	    if(horadividida[0].length()!=2) 
	    {
	    	return false;
	    }
	    if(horadividida[1].length()!=2) 
	    {
	    	return false;
	    }
	    return true;
	}
	
	private List<MedicoDto> filtrarListaMedicos(List<MedicoDto> medi, String start) 
	{
		List<MedicoDto> listaFiltrada = new ArrayList<MedicoDto>();
		for(MedicoDto m:medi) 
		{
			if(m.name.startsWith(start)) 
			{
				listaFiltrada.add(m);
			}
		}
		return listaFiltrada;
	}
	
	private List<PacienteDto> filtrarListaPacientes(List<PacienteDto> paci, String start) 
	{
		List<PacienteDto> listaFiltrada = new ArrayList<PacienteDto>();
		for(PacienteDto p:paci) 
		{
			if(p.name.startsWith(start)) 
			{
				listaFiltrada.add(p);
			}
		}
		return listaFiltrada;
	}
	

}
