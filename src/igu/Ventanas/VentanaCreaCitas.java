package igu.Ventanas;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JTextField;
import javax.swing.ListModel;

import igu.action.EnviarEmailUrgenteAction;
import igu.action.InsertCitaAction;
import igu.action.InsertRequisitosCitaAction;
import igu.action.ListAllMedicosAction;
import igu.action.ListAllPacientesAction;
import igu.action.ListCitasByMedicoAction;
import igu.action.ListJornadaLaboralByMedicoAction;

import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Cursor;
import java.awt.Rectangle;
import javax.swing.JScrollPane;
import javax.swing.border.BevelBorder;
import java.awt.FlowLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.border.SoftBevelBorder;
import javax.swing.event.ListDataListener;

import Logica.crud.commands.GetSalaByName;
import Logica.crud.commands.ListCitasBySala;
import Logica.crud.dto.*;
import javax.swing.JCheckBox;
import javax.swing.ListSelectionModel;

import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JTextFieldDateEditor;

import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.util.Locale;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.JSpinner;

public class VentanaCreaCitas extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel panelDatos;
	private JPanel panelBotones;
	private JPanel panelCitaEstandar;
	private JPanel panelMedicosPacientes;
	private JPanel panelHoraSalaUrgencia;
	private JPanel panelAviso;
	private JPanel panelHora;
	private JLabel lblHoraInicio;
	private JLabel lblHoraFinCita;
	private JPanel panelUrgenciaSala;
	private JButton btnCrearCita;
	private JPanel panelPaciente;
	private JTextField textFieldAvisoUsuario;
	private JLabel lblFechaInicio;
	private JPanel panelComboPaciente;
	private JPanel panelFiltro;
	private JLabel lblPaciente;
	private JTextField textFieldFiltroPaciente;
	private JPanel panelMedicos;
	private VentanaPrincipal ventana;
	private JCheckBox chckbxUrgente;
	private JPanel panelListado;
	private JScrollPane scrollPaneMedicos;
	private JScrollPane scrollPaneMedicosSeleccionados;
	private JList<String> listMedicos;
	private JList<String> listSeleccionados;
	private JPanel panelBotonesMedico;
	private JButton btnAñadirMedico;
	private JButton btnEliminarMedico;
	private JButton btnLimpiarListaSeleccionados;
	private JLabel lblNewLabel;
	private JPanel panelHoraInicioCita;
	private JPanel panelHoraFinCita;
	private JPanel panelFechaInicioCita;
	private JPanel panelFechaFinCita;
	private JComboBox<Integer> comboBoxHoraInicioCita;
	private JLabel lblSeparacionEstilo1;
	private JComboBox<Integer> comboBoxMinutoInicioCita;
	private JComboBox<Integer> comboBoxHoraFinCita;
	private JLabel lblSeparacionEstilo2;
	private JComboBox<Integer> comboBoxMinutoFinCita;
	private JDateChooser dcInicio;
	private JDateChooser dcFin;
	private JLabel lblMedicos;
	private JButton btnHorariosMedicos;
	private JScrollPane scrollPanePacientes;
	private JList<String> listPaciente;
	private JPanel panelFiltroEspecialidad;
	private JTextField textFieldFiltroMedico;
	private JCheckBox chckbxEspecialidad;
	private JTextField txtEspecialidadSeleccionada;
	private JPanel panelCreaCita;
	private JPanel panelDisponibilidad;
	private JPanel panelContenidos;
	private JPanel panelCentralDisponibilidad;
	private JPanel panelBotonesDisponibilidad;
	private JButton btnAtras;
	private JPanel panelMedicosDisponibilidad;
	private JPanel panelListaSeleccionadosDisponibilidad;
	private JPanel panelHoraDisponibilidad;
	private JPanel panelInformacionDisponibilidad;
	private JScrollPane scrollPaneMedicosDisponibilidad;
	private JList<String> listMedicosDisponibilidad;
	private JLabel lblHorariosDisponibles;
	private JScrollPane scrollPaneHorarios;
	private JList<String> listHorariosDisponibles;
	private JPanel panelFiltroMedicosDisponibilidad;
	private JCheckBox chckbxFiltroMedicosDisponibilidad;
	private JTextField textFieldFiltroMedicosDisponibilidad;
	private JLabel lblHoraInicioDisponibilidad;
	private JLabel lblHoraFinalDisponibilidad;
	private JLabel lblFechaFinalDisponibilidad;
	private JDateChooser dcInicioDisponibilidad;
	private JDateChooser dcFinalDisponibilidad;
	private JPanel panelHoraInicioDisponibilidad;
	private JPanel panelHoraFinalDisponibilidad;
	private JLabel lblFechaInicioDisponibilidad;
	private JComboBox<Integer> comboBoxHoraInicioDisponibilidad;
	private JLabel lblSeparacionInicioDisponibilidad;
	private JComboBox<Integer> comboBoxMinutoInicioDisponibilidad;
	private JComboBox<Integer> comboBoxHoraFinalDisponibilidad;
	private JLabel lblSeparacionFinalDisponibilidad;
	private JComboBox<Integer> comboBoxMinutoFinalDisponibilidad;
	private JPanel panelContenidoCita;
	private JPanel panelContenidoDisponibilidad;
	private JCheckBox chckbxFiltrarPorHora;
	private JPanel panelOpcionesDisponibilidad;
	private JButton btnFiltrarPorHoraDisponibilidad;
	private JButton btnSeleccionarHorario;
	
	//Atributos
	List<String> medicosselectModel;
	List<PacienteDto> pacientes;
	List<MedicoDto> medicos;
	List<MedicoDto> medicosSeleccionados;
	List<String> especialidadesSeleccionadas;
	String[] especialidades;
	String SelectedEspecialidad;
	private JPanel panelSala;
	private JLabel lblSala;
	private JComboBox<String> comboBoxSala;
	private JPanel panelMotivos;
	private JLabel lblMotivos;
	private JTextField textFieldMotivos;
	private JPanel panelAñadir;
	private JSpinner spinnerMedicoEspecialidad;
	

	/**
	 * Create the application.
	 */
	public VentanaCreaCitas(VentanaPrincipal principal) 
	{ 
		//setIconImage(Toolkit.getDefaultToolkit().getImage(VentanaCreaCitas.class.getResource("/Multimedia/Logo.jpg")));
		setTitle("Hospital:Crear Una cita");
		setModal(true);
		this.ventana=principal;
		initialize();
	}

	public VentanaPrincipal getVentana() {
		return this.ventana;
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		//Inicializa atributos
		this.medicos = new ListAllMedicosAction().execute();

		this.pacientes = new ListAllPacientesAction().execute();
		
		this.medicosselectModel= new ArrayList<String>();
		
		this.medicosSeleccionados = new ArrayList<MedicoDto>();
		
		this.especialidadesSeleccionadas= new ArrayList<String>();
		
		this.especialidades=obtenerEspecialidades();
		
		this.SelectedEspecialidad=null;
		
        this.setMinimumSize(new Dimension(800, 520));
        this.setBounds(100, 100, 648, 492);
        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        getContentPane().add(getPanelContenidos(), BorderLayout.CENTER);
	}
	
	private JPanel getPanelContenidos() {
		if (panelContenidos == null) {
			panelContenidos = new JPanel();
			panelContenidos.setLayout(new CardLayout(0, 0));
			panelContenidos.add(getPanelContenidoCita(), "PanelCreaCitas");
			panelContenidos.add(getPanelContenidoDisponibilidad(), "PanelDisponibilidad");
		}
		return panelContenidos;
	}
	
	private JPanel getPanelDisponibilidad() {
		if (panelDisponibilidad == null) {
			panelDisponibilidad = new JPanel();
			panelDisponibilidad.setLayout(new BorderLayout(0, 0));
			panelDisponibilidad.add(getPanelCentralDisponibilidad(), BorderLayout.CENTER);
			panelDisponibilidad.add(getPanelBotonesDisponibilidad(), BorderLayout.SOUTH);
		}
		return panelDisponibilidad;
	}
	
	private JPanel getPanelCreaCita() {
		if (panelCreaCita == null) {
			panelCreaCita = new JPanel();
			panelCreaCita.setLayout(new BorderLayout(0, 0));
			panelCreaCita.add(getPanelDatos(), BorderLayout.CENTER);
			panelCreaCita.add(getPanelBotones(), BorderLayout.SOUTH);

		}
		return panelCreaCita;
	}

	private JPanel getPanelDatos() {
		if (panelDatos == null) {
			panelDatos = new JPanel();
			panelDatos.setLayout(new BorderLayout(0, 0));
			panelDatos.add(getPanelCitaEstandar(), BorderLayout.CENTER);
			panelDatos.add(getPanelAviso(), BorderLayout.SOUTH);
		}
		return panelDatos;
	}

	private JPanel getPanelBotones() {
		if (panelBotones == null) {
			panelBotones = new JPanel();
			panelBotones.setLayout(new GridLayout(0, 1, 0, 0));
			panelBotones.add(getBtnCrearCita());
		}
		return panelBotones;
	}

	private JPanel getPanelCitaEstandar() {
		if (panelCitaEstandar == null) {
			panelCitaEstandar = new JPanel();
			panelCitaEstandar.setLayout(new BorderLayout(0, 0));
			panelCitaEstandar.add(getPanelMedicosPacientes(), BorderLayout.CENTER);
			panelCitaEstandar.add(getPanelHoraSalaUrgencia(), BorderLayout.SOUTH);
		}
		return panelCitaEstandar;
	}

	private JPanel getPanelMedicosPacientes() {
		if (panelMedicosPacientes == null) {
			panelMedicosPacientes = new JPanel();
			panelMedicosPacientes.setLayout(new GridLayout(0, 1, 0, 0));
			panelMedicosPacientes.add(getPanelPaciente());
			panelMedicosPacientes.add(getPanelMedicos());
		}
		return panelMedicosPacientes;
	}

	private JPanel getPanelHoraSalaUrgencia() {
		if (panelHoraSalaUrgencia == null) {
			panelHoraSalaUrgencia = new JPanel();
			panelHoraSalaUrgencia.setLayout(new BorderLayout(0, 0));
			panelHoraSalaUrgencia.add(getPanelHora(), BorderLayout.CENTER);
			panelHoraSalaUrgencia.add(getPanelUrgenciaSala(), BorderLayout.SOUTH);
		}
		return panelHoraSalaUrgencia;
	}

	private JPanel getPanelAviso() {
		if (panelAviso == null) {
			panelAviso = new JPanel();
			panelAviso.setLayout(new BorderLayout(0, 0));
			panelAviso.add(getTextFieldAvisoUsuario());
		}
		return panelAviso;
	}

	private JPanel getPanelHora() {
		if (panelHora == null) {
			panelHora = new JPanel();
			panelHora.setLayout(new GridLayout(0, 2, 0, 0));
			panelHora.add(getLblHoraInicio());
			panelHora.add(getPanelHoraInicioCita());
			panelHora.add(getLblHoraFinCita());
			panelHora.add(getPanelHoraFinCita());
			panelHora.add(getLblFechaInicio());
			panelHora.add(getPanelFechaInicioCita());
			panelHora.add(getLblNewLabel());
			panelHora.add(getPanelFechaFinCita());
		}
		return panelHora;
	}

	private JLabel getLblHoraInicio() {
		if (lblHoraInicio == null) {
			lblHoraInicio = new JLabel("Hora de Inicio de cita:");
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

	private JPanel getPanelUrgenciaSala() {
		if (panelUrgenciaSala == null) {
			panelUrgenciaSala = new JPanel();
			panelUrgenciaSala.setLayout(new GridLayout(0, 2, 0, 0));
			panelUrgenciaSala.add(getChckbxUrgente());
			panelUrgenciaSala.add(getChckbxFiltrarPorHora());
			panelUrgenciaSala.add(getPanelSala());
			panelUrgenciaSala.add(getPanelMotivos());
		}
		return panelUrgenciaSala;
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
			panelPaciente.setLayout(new BorderLayout(0, 0));
			panelPaciente.add(getPanelComboPaciente());
			panelPaciente.add(getPanelFiltro(), BorderLayout.NORTH);
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

	private JLabel getLblFechaInicio() {
		if (lblFechaInicio == null) {
			lblFechaInicio = new JLabel("Fecha inicio cita:");
		}
		return lblFechaInicio;
	}

	private JPanel getPanelComboPaciente() {
		if (panelComboPaciente == null) {
			panelComboPaciente = new JPanel();
			panelComboPaciente.setLayout(new BorderLayout(0, 0));
			panelComboPaciente.add(getLblPaciente(), BorderLayout.WEST);
			panelComboPaciente.add(getScrollPanePacientes(), BorderLayout.CENTER);
		}
		return panelComboPaciente;
	}

	private JPanel getPanelFiltro() {
		if (panelFiltro == null) {
			panelFiltro = new JPanel();
			panelFiltro.setLayout(new BorderLayout(0, 0));
			panelFiltro.add(getTextFieldFiltroPaciente(), BorderLayout.NORTH);
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

	private JTextField getTextFieldFiltroPaciente() {
		if (textFieldFiltroPaciente == null) {
			textFieldFiltroPaciente = new JTextField();
			textFieldFiltroPaciente.setToolTipText("Busqueda por nombre o dni");
			textFieldFiltroPaciente.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
			textFieldFiltroPaciente.addKeyListener(new KeyAdapter() {
				@Override
				public void keyReleased(KeyEvent e) {
					String[] pacientesstr = pacientesToString(
							filtrarListaPacientes(pacientes, getTextFieldFiltroPaciente().getText()));
					ListModel<String> model = new DefaultComboBoxModel<String>(pacientesstr);
					listPaciente.setModel(model);
				}
			});
			textFieldFiltroPaciente.setColumns(10);
		}
		return textFieldFiltroPaciente;
	}

	private JPanel getPanelMedicos() {
		if (panelMedicos == null) {
			panelMedicos = new JPanel();
			panelMedicos.setLayout(new BorderLayout(0, 0));
			panelMedicos.add(getPanelListado(), BorderLayout.CENTER);
			panelMedicos.add(getPanelBotonesMedico(), BorderLayout.SOUTH);
			panelMedicos.add(getLblMedicos(), BorderLayout.WEST);
			panelMedicos.add(getPanelFiltroEspecialidad(), BorderLayout.NORTH);
		}
		return panelMedicos;
	}
	private JCheckBox getChckbxUrgente() {
		if (chckbxUrgente == null) {
			chckbxUrgente = new JCheckBox("Es Urgente");
		}
		return chckbxUrgente;
	}

	private JPanel getPanelListado() {
		if (panelListado == null) {
			panelListado = new JPanel();
			panelListado.setLayout(new GridLayout(0, 2, 0, 0));
			panelListado.add(getScrollPaneMedicos());
			panelListado.add(getScrollPaneMedicosSeleccionados());
		}
		return panelListado;
	}

	private JScrollPane getScrollPaneMedicos() {
		if (scrollPaneMedicos == null) {
			scrollPaneMedicos = new JScrollPane();
			scrollPaneMedicos.setViewportView(getListMedicos());
		}
		return scrollPaneMedicos;
	}

	private JScrollPane getScrollPaneMedicosSeleccionados() {
		if (scrollPaneMedicosSeleccionados == null) {
			scrollPaneMedicosSeleccionados = new JScrollPane();
			scrollPaneMedicosSeleccionados.setViewportView(getListSeleccionados());
			scrollPaneMedicosSeleccionados.setColumnHeaderView(getTxtEspecialidadSeleccionada());
		}
		return scrollPaneMedicosSeleccionados;
	}
	
	private JCheckBox getChckbxEspecialidad() {
		if (chckbxEspecialidad == null) {
			chckbxEspecialidad = new JCheckBox("Especialidades");
			chckbxEspecialidad.addItemListener(new ItemListener() {
				public void itemStateChanged(ItemEvent e) 
				{
					getTextFieldFiltroMedico().setText("");
					if(chckbxEspecialidad.isSelected()) {
						getLblMedicos().setText("Especialidades:");
						ListModel<String> model = new DefaultComboBoxModel<String>(especialidades);
						listMedicos.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
						listMedicos.setModel(model);
						spinnerMedicoEspecialidad.setVisible(true);
						//getTxtEspecialidadSeleccionada().setVisible(false);

						
						btnAñadirMedico.setText("Añadir especialidad");
						
						btnEliminarMedico.setText("Eliminar especialidad");
						
						if(SelectedEspecialidad==null) 
						{
						  btnEliminarMedico.setEnabled(false);
						}
						
						//Actualiza la lista de especialidades seleccionadas
						String[] mste = especialidadesToString(especialidadesSeleccionadas);
						getListSeleccionados().setModel(new DefaultComboBoxModel<String>(mste));
						
						
					}else {
						getLblMedicos().setText("Medicos:");
						String[] medicosstr = medicosToString(medicos);
						ListModel<String> model = new DefaultComboBoxModel<String>(medicosstr);
						listMedicos.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
						listMedicos.setModel(model);
						
						btnAñadirMedico.setText("Añadir medicos");
						
						btnEliminarMedico.setText("Eliminar medico seleccionado");
						
						btnEliminarMedico.setEnabled(true);
						
						//getTxtEspecialidadSeleccionada().setVisible(true);
						
						spinnerMedicoEspecialidad.setVisible(false);
						
						String[] mstr = medicosToString(medicosSeleccionados) ;
						getListSeleccionados().setModel(new DefaultComboBoxModel<String>(mstr));

						
					}
				}
			});
		}
		return chckbxEspecialidad;
	}

	private JList<String> getListMedicos() {
		if (listMedicos == null) {
			listMedicos = new JList<String>();
			listMedicos.setSelectedIndices(new int[] { -1 });
			listMedicos.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
			listMedicos.setToolTipText("Lista de Medicos");
			String[] medicosstr = medicosToString(this.medicos);
			listMedicos.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			ListModel<String> model = new DefaultComboBoxModel<String>(medicosstr);
			listMedicos.setModel(model);
		}
		return listMedicos;
	}

	private JList<String> getListSeleccionados() {
		if (listSeleccionados == null) {
			listSeleccionados = new JList<String>();
			listSeleccionados.setToolTipText("Medicos Seleccionados");
			listSeleccionados.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			listSeleccionados.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		}
		return listSeleccionados;
	}

	private JPanel getPanelBotonesMedico() {
		if (panelBotonesMedico == null) {
			panelBotonesMedico = new JPanel();
			panelBotonesMedico.setLayout(new GridLayout(0, 4, 0, 0));
			panelBotonesMedico.add(getPanelAñadir());
			panelBotonesMedico.add(getBtnEliminarMedico());
			panelBotonesMedico.add(getBtnHorariosMedicos());
			panelBotonesMedico.add(getBtnLimpiarListaSeleccionados());
		}
		return panelBotonesMedico;
	}

	private JButton getBtnAñadirMedico() {
		if (btnAñadirMedico == null) {
			btnAñadirMedico = new JButton("A\u00F1adir medicos");
			btnAñadirMedico.setToolTipText("Seleccionar medicos");
			btnAñadirMedico.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) 
				{
				  if(!chckbxEspecialidad.isSelected()) {
				  
				   List<MedicoDto> l = obtenMedicosSeleccionados();
				   for(MedicoDto medico : l) 
				   {
					   if(!medicosSeleccionados.contains(medico)) 
					   {
					   medicosSeleccionados.add(medico);	
					   }
				   }
				   //Actualiza la lista de medicos seleccionados
				   String[] mstr = medicosToString(medicosSeleccionados) ;
				   getListSeleccionados().setModel(new DefaultComboBoxModel<String>(mstr));
				   //Actualiza la lista de medicos normal
				   //for(Medico)
				}else {
					List<String> esp = obtenEspecialidadesSeleccionadas();
					int resp = JOptionPane.YES_OPTION;
					for(String especialidad : esp)
					{
						if(!checkMedicosSuficientes(esp)) {
							resp = JOptionPane.showConfirmDialog(null,"No hay medicos suficentes de esa especialidade. ¿Desea añadir?","Advertencia al añadir Especialidades",JOptionPane.YES_NO_OPTION);
						}
						
						if(!cont(especialidad) && resp==JOptionPane.YES_OPTION) {
							especialidadesSeleccionadas.add(getSpinnerMedicoEspecialidad().getValue()+":"+especialidad);
							insertarEspecialidad();
						}
					}
					//Actualiza la lista de especialidades seleccionadas
					String[] mste = especialidadesToString(especialidadesSeleccionadas);
					getListSeleccionados().setModel(new DefaultComboBoxModel<String>(mste));
				}
				}

			});
		}
		return btnAñadirMedico;
	}
	
	private boolean checkMedicosSuficientes(List<String> especialidades) {
		int t=0;
		int pedido = (int) getSpinnerMedicoEspecialidad().getValue();
	    for(String especialidad : especialidades) {
		for (MedicoDto m : medicos) {
			if(m.especialidad.toUpperCase().startsWith(especialidad.toUpperCase())) {
			    t=t+1;
			}}
			if(t<pedido) {
				return false;
			}
		}
	    return true;
		
	}
	
	private boolean cont(String e) {
		for(String esp : especialidadesSeleccionadas) {
			if(esp.split(":")[1].equals(e)) {
				return true;
			}
		}
		return false;
	}

	private JButton getBtnEliminarMedico() {
		if (btnEliminarMedico == null) {
			btnEliminarMedico = new JButton("Eliminar medico seleccionado");
			btnEliminarMedico.setToolTipText("Eliminar medico seleccionado");
			btnEliminarMedico.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) 
				{
					if(!chckbxEspecialidad.isSelected()) {
					int i=getListSeleccionados().getSelectedIndex();
					if(i>=0) {
					medicosSeleccionados.remove(i);
					String[] mstr = medicosToString(medicosSeleccionados)  ;
					getListSeleccionados().setModel(new DefaultComboBoxModel<String>(mstr));
					}
				 }else {
					 int i=getListSeleccionados().getSelectedIndex();
						if(i>=0) {
						especialidadesSeleccionadas.remove(i);
						String[] mstr = especialidadesToString(especialidadesSeleccionadas)  ;
						getListSeleccionados().setModel(new DefaultComboBoxModel<String>(mstr));
						eliminarEspecialidad();
						}
					 
				 }
				}
			});
			
		}
		return btnEliminarMedico;
	}

	private JButton getBtnLimpiarListaSeleccionados() {
		if (btnLimpiarListaSeleccionados == null) {
			btnLimpiarListaSeleccionados = new JButton("Limpiar lista Seleccionados");
			btnLimpiarListaSeleccionados.setToolTipText("Borrar todos los medicos seleccionados");
			btnLimpiarListaSeleccionados.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					medicosSeleccionados.clear();
					String[] mstr = medicosToString(medicosSeleccionados);
					getListSeleccionados().setModel(new DefaultComboBoxModel<String>(mstr));
					especialidadesSeleccionadas.clear();
					eliminarEspecialidad();
					
				}
			});
		}
		return btnLimpiarListaSeleccionados;
	}

	private JLabel getLblNewLabel() {
		if (lblNewLabel == null) {
			lblNewLabel = new JLabel("Fecha fin cita:");
		}
		return lblNewLabel;
	}

	private JPanel getPanelHoraInicioCita() {
		if (panelHoraInicioCita == null) {
			panelHoraInicioCita = new JPanel();
			panelHoraInicioCita.add(getComboBoxHoraInicioCita());
			panelHoraInicioCita.add(getLblSeparacionEstilo1());
			panelHoraInicioCita.add(getComboBoxMinutoInicioCita());
		}
		return panelHoraInicioCita;
	}

	private JPanel getPanelHoraFinCita() {
		if (panelHoraFinCita == null) {
			panelHoraFinCita = new JPanel();
			panelHoraFinCita.add(getComboBoxHoraFinCita());
			panelHoraFinCita.add(getLblSeparacionEstilo2());
			panelHoraFinCita.add(getComboBoxMinutoFinCita());
		}
		return panelHoraFinCita;
	}

	private JPanel getPanelFechaInicioCita() {
		if (panelFechaInicioCita == null) {
			panelFechaInicioCita = new JPanel();
			panelFechaInicioCita.setLayout(new BorderLayout(0, 0));
			panelFechaInicioCita.add(getDcInicio());
		}
		return panelFechaInicioCita;
	}

	private JPanel getPanelFechaFinCita() {
		if (panelFechaFinCita == null) {
			panelFechaFinCita = new JPanel();
			panelFechaFinCita.setLayout(new BorderLayout(0, 0));
			panelFechaFinCita.add(getDcFin());
		}
		return panelFechaFinCita;
	}

	private JComboBox<Integer> getComboBoxHoraInicioCita() {
		if (comboBoxHoraInicioCita == null) {
			comboBoxHoraInicioCita = new JComboBox<Integer>();
			comboBoxHoraInicioCita.addItemListener(new ItemListener() {
				public void itemStateChanged(ItemEvent e) {
					if (comboBoxHoraInicioCita.getSelectedIndex() < 0) {
						comboBoxHoraInicioCita.setSelectedIndex(0);
					}else 
					{
						getComboBoxHoraInicioDisponibilidad().setSelectedIndex(comboBoxHoraInicioCita.getSelectedIndex());
					}
					if (comboBoxHoraInicioCita.getSelectedItem().toString().length() >= 2) {
						comboBoxMinutoInicioCita.grabFocus();
					}
				}
			});
			comboBoxHoraInicioCita.setEditable(true);
			Integer[] h = new Integer[24];
			for (int i = 0; i < h.length; i++) {
				h[i] = i;
			}
			comboBoxHoraInicioCita.setBounds(342, 147, 72, 27);
			comboBoxHoraInicioCita.setModel(new DefaultComboBoxModel<Integer>(h));

		}
		return comboBoxHoraInicioCita;
	}

	private JLabel getLblSeparacionEstilo1() {
		if (lblSeparacionEstilo1 == null) {
			lblSeparacionEstilo1 = new JLabel(":");
		}
		return lblSeparacionEstilo1;
	}

	private JComboBox<Integer> getComboBoxMinutoInicioCita() {
		if (comboBoxMinutoInicioCita == null) {
			comboBoxMinutoInicioCita = new JComboBox<Integer>();
			comboBoxMinutoInicioCita.addItemListener(new ItemListener() {
				public void itemStateChanged(ItemEvent e) 
				{
					if (comboBoxMinutoInicioCita.getSelectedIndex() < 0) {
						comboBoxMinutoInicioCita.setSelectedIndex(0);
					}
				}
			});
			comboBoxMinutoInicioCita.setEditable(true);
			Integer[] h = new Integer[60];
			for (int i = 0; i < h.length; i++) {
				h[i] = i;
			}
			comboBoxMinutoInicioCita.setBounds(342, 147, 72, 27);
			comboBoxMinutoInicioCita.setModel(new DefaultComboBoxModel<Integer>(h));
		}

		return comboBoxMinutoInicioCita;
	}

	private JComboBox<Integer> getComboBoxHoraFinCita() {
		if (comboBoxHoraFinCita == null) {
			comboBoxHoraFinCita = new JComboBox<Integer>();
			comboBoxHoraFinCita.addItemListener(new ItemListener() {
				public void itemStateChanged(ItemEvent e) 
				{
					if (comboBoxHoraFinCita.getSelectedIndex() < 0) {
						comboBoxHoraFinCita.setSelectedIndex(0);
					}
				}
			});
			comboBoxHoraFinCita.setEditable(true);
			Integer[] h = new Integer[24];
			for (int i = 0; i < h.length; i++) {
				h[i] = i;
			}
			comboBoxHoraFinCita.setBounds(342, 147, 72, 27);
			comboBoxHoraFinCita.setModel(new DefaultComboBoxModel<Integer>(h));
		}
		return comboBoxHoraFinCita;
	}

	private JLabel getLblSeparacionEstilo2() {
		if (lblSeparacionEstilo2 == null) {
			lblSeparacionEstilo2 = new JLabel(":");
		}
		return lblSeparacionEstilo2;
	}

	private JComboBox<Integer> getComboBoxMinutoFinCita() {
		if (comboBoxMinutoFinCita == null) {
			comboBoxMinutoFinCita = new JComboBox<Integer>();
			comboBoxMinutoFinCita.addItemListener(new ItemListener() {
				public void itemStateChanged(ItemEvent e) 
				{
					if (comboBoxMinutoFinCita.getSelectedIndex() < 0) {
						comboBoxMinutoFinCita.setSelectedIndex(0);
					}
				}
			});
			comboBoxMinutoFinCita.setEditable(true);
			Integer[] h = new Integer[60];
			for (int i = 0; i < h.length; i++) {
				h[i] = i;
			}
			comboBoxMinutoFinCita.setBounds(342, 147, 72, 27);
			comboBoxMinutoFinCita.setModel(new DefaultComboBoxModel<Integer>(h));
		}
		return comboBoxMinutoFinCita;
	}

	//
	private JDateChooser getDcInicio() {
		if (dcInicio == null) {
			dcInicio = new JDateChooser();
			dcInicio.getCalendarButton().addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Date date = getDcFin().getDate();
					if (date != null) {
						dcInicio.setMaxSelectableDate(date);
					}
					if(date != null && dcInicio.getDate()!=null) {
						getChckbxFiltrarPorHora().setEnabled(true);
					}
				}
			});
			JTextFieldDateEditor editor = (JTextFieldDateEditor) dcInicio.getDateEditor();
			editor.setEditable(false);
		}
		return dcInicio;

	}

	private JDateChooser getDcFin() {
		if (dcFin == null) {
			dcFin = new JDateChooser();
			dcFin.getCalendarButton().addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Date date = getDcInicio().getDate();
					if (date != null) {
						dcFin.setMinSelectableDate(date);
						dcFin.setDate(date);
					}
					if(date != null && dcFin.getDate()!=null) {
						getChckbxFiltrarPorHora().setEnabled(true);
					}
				}
			});
			JTextFieldDateEditor editor = (JTextFieldDateEditor) dcFin.getDateEditor();
			editor.setEditable(false);
		}
		return dcFin;
	}

	//

	private JLabel getLblMedicos() {
		if (lblMedicos == null) {
			lblMedicos = new JLabel("Medicos:");
		}
		return lblMedicos;
	}

	private JButton getBtnHorariosMedicos() {
		if (btnHorariosMedicos == null) {
			btnHorariosMedicos = new JButton("DisponibilidadMedicos");
			btnHorariosMedicos.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) 
				{
					//Tratamiento de datos
					  if(getListSeleccionados().getModel().getSize()==0) 
					  {
						  String[] medicosstr = medicosToString(medicos);
						  ListModel<String> model = new DefaultComboBoxModel<String>(medicosstr);
						  getListMedicosDisponibilidad().setModel(model);

						  getChckbxFiltroMedicosDisponibilidad().setSelected(false);
						  getChckbxFiltroMedicosDisponibilidad().setEnabled(false);  
					  }else {
						  getListMedicosDisponibilidad().setModel(getListSeleccionados().getModel());
						  int[] indices= new int[getListSeleccionados().getModel().getSize()];
						     for(int i=0;i<getListSeleccionados().getModel().getSize();i++) 
						     {
						    	indices[i]=i;
						     }
						  getListMedicosDisponibilidad().setSelectedIndices(indices);
						  getChckbxFiltroMedicosDisponibilidad().setSelected(true);
						  getChckbxFiltroMedicosDisponibilidad().setEnabled(true);
					  }
					
					  if(getDcInicio().getDate()!=null) 
					  {
						  getDcInicioDisponibilidad().setDate(getDcInicio().getDate());
					  }
					  if(getDcFin().getDate()!=null) 
					  {
						 getDcFinalDisponibilidad().setDate(getDcFin().getDate()); 
					  }
					  getComboBoxHoraInicioDisponibilidad().setSelectedIndex(getComboBoxHoraInicioCita().getSelectedIndex());
					  getComboBoxMinutoInicioDisponibilidad().setSelectedIndex(getComboBoxMinutoInicioCita().getSelectedIndex());
					  
					  getComboBoxHoraFinalDisponibilidad().setSelectedIndex(getComboBoxHoraFinCita().getSelectedIndex());
					  getComboBoxMinutoFinalDisponibilidad().setSelectedIndex(getComboBoxMinutoFinCita().getSelectedIndex());
					//
					CardLayout c = (CardLayout)getPanelContenidos().getLayout();
					c.show(getPanelContenidos(),"PanelDisponibilidad");
				}
			});
		}
		return btnHorariosMedicos;
	}

	private JScrollPane getScrollPanePacientes() {
		if (scrollPanePacientes == null) {
			scrollPanePacientes = new JScrollPane();
			scrollPanePacientes.setViewportView(getListPaciente());
		}
		return scrollPanePacientes;
	}

	private JList<String> getListPaciente() {
		if (listPaciente == null) {
			listPaciente = new JList<String>();
			String[] pacientesstr = pacientesToString(this.pacientes);
			ListModel<String> model = new DefaultComboBoxModel<String>(pacientesstr);
			listPaciente.setModel(model);
			listPaciente.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			listPaciente.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		}
		return listPaciente;
	}

	
	private JPanel getPanelFiltroEspecialidad() {
		if (panelFiltroEspecialidad == null) {
			panelFiltroEspecialidad = new JPanel();
			panelFiltroEspecialidad.setLayout(new BorderLayout(0, 0));
			panelFiltroEspecialidad.add(getTextFieldFiltroMedico(), BorderLayout.CENTER);
			panelFiltroEspecialidad.add(getChckbxEspecialidad(), BorderLayout.WEST);
		}
		return panelFiltroEspecialidad;
	}
	private JTextField getTextFieldFiltroMedico() {
		if (textFieldFiltroMedico == null) {
			textFieldFiltroMedico = new JTextField();
			textFieldFiltroMedico.setName("FiltroMedico");
			textFieldFiltroMedico.setLocale(new Locale("es", "ES"));
			textFieldFiltroMedico.setActionCommand("");
			textFieldFiltroMedico.setToolTipText("Busqueda por nombre o dni");
			textFieldFiltroMedico.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
			textFieldFiltroMedico.addKeyListener(new KeyAdapter() {
				@Override
				public void keyReleased(KeyEvent e) 
				{
					if(!chckbxEspecialidad.isSelected()) {
					  String[] medicosstr = medicosToString(filtrarListaMedicos(medicos,getTextFieldFiltroMedico().getText()));
					  ListModel<String> model = new DefaultComboBoxModel<String>(medicosstr);
					  listMedicos.setModel(model);
					}else {
					  String[] especialidadesFiltradas = filtrarEspecialidades(especialidades,getTextFieldFiltroMedico().getText());
					  ListModel<String> model = new DefaultComboBoxModel<String>(especialidadesFiltradas);
					  listMedicos.setModel(model);
					}
				}

			});
			textFieldFiltroMedico.setColumns(10);
		}
		return textFieldFiltroMedico;
	}
	
	//Metodos privados
	public void crearCita() 
	{
		PacienteDto paciente =  obtenPacienteSeleccionado();
		if(paciente!=null) {
		if(checkValoresVentanaPrincipal()) //Comprueba que se han elegido los valores minimos para crear una cita
		{	
		Integer respuesta=JOptionPane.YES_OPTION;
		boolean urge = getChckbxUrgente().isSelected();
		if(getListSeleccionados().getModel().getSize()!=0) { //Medicos seleccionados?
		for(MedicoDto medico:medicosSeleccionados) 
		{	
		 GeneraCita(paciente, respuesta, urge, medico);
		}
		}else {
			 respuesta=JOptionPane.showConfirmDialog(null,"¿Quiere crear una cita sin medicos definidos?","Advertencia al Crear la cita",JOptionPane.YES_NO_OPTION);
			if(respuesta==JOptionPane.YES_OPTION) { //Preguntar si desea generar una cita sin medicos
		       GeneraCita(paciente, respuesta, urge, null);
			}
		}
	  }
		}else {
			JOptionPane.showMessageDialog(null, "Elija un paciente para asignarle la cita");
		}
	}

	private Integer GeneraCita(PacienteDto paciente, Integer respuesta, boolean urge, MedicoDto medico) {
		CitaDto cita = new CitaDto();
		cita.idMedico=null;
		if(medico!=null) {
		//Comprueba que la cita se establece dentro de la jornada laboral
	    if(!compruebaJornada(medico.id)) {
		 respuesta=JOptionPane.showConfirmDialog(null,"El horario de la cita no forma parte de la jornada laboral del medico "+medico.name+" "+medico.surname+".","Advertencia al Crear la cita",JOptionPane.YES_NO_OPTION);
	    }
		//Comprobacion de que el medico no tiene citas a esa hora
		if(respuesta==JOptionPane.YES_OPTION && !compruebaHora(medico.id)) {
			 respuesta=JOptionPane.showConfirmDialog(null,"El medico "+medico.name+" "+medico.surname+" tiene otra cita a esa hora","Advertencia al Crear la cita",JOptionPane.YES_NO_OPTION);
		}
		 cita.idMedico=medico.id;
		}
		if(respuesta==JOptionPane.YES_OPTION) {	
		cita.idPaciente=paciente.id;
		cita.causa="No determinandas"; //Añadir causas una vez el medico las determine
		cita.motivo=getTextFieldMotivos().getText();
		//
		Date inicio = getDcInicio().getDate();
		Date ultima = getDcFin().getDate();
		String horaInicio =getComboBoxHoraInicioCita().getSelectedItem().toString()+":"+getComboBoxMinutoInicioCita().getSelectedItem().toString();
		String horafin =getComboBoxHoraFinCita().getSelectedItem().toString()+":"+getComboBoxMinutoFinCita().getSelectedItem().toString();
		String fecha = formateaFecha(inicio)+" "+horaInicio+":00";
		String fechafin=formateaFecha(ultima)+" "+horafin+":00";
		
		cita.horaInicio=fecha; //fecha +hora inicio
		cita.horaFinal=fechafin;
		//
		
		if(urge) {
		cita.urgencia="Si";
		enviarGmail(medico.correo,paciente,cita);
		}else {
		cita.urgencia="No";
		}
		cita.horaEntrada=null;
		cita.horaSalida=null;
		cita.idSala=getComboBoxSala().getEditor().getItem().toString();
		boolean act=true;
		if(getComboBoxSala().getSelectedIndex()!=0) 
		{
			act =compruebaHoraSala(cita); //Comprobacion hora sala.
		}else {
			cita.idSala="Sala NO Determinada";
		}
		
		if(act==true) {
		 String Sala = new GetSalaByName().execute(cita.idSala);
		 cita.idSala=Sala;
		 
		cita.preescripcion="";
		cita.contacto=paciente.contacto; //Por defecto contacto de la cita = contacto del paciente
		cita.acude="INDEFINIDO";
		if(this.SelectedEspecialidad==null) 
		{
			cita.Especialidad=null;
		}else {
			cita.Especialidad=this.SelectedEspecialidad.toUpperCase();
			
		}
                     
		int idcita = new InsertCitaAction(cita).execute();
		cita.id=idcita-1+"";
		introduceRequisitos(cita.id,especialidadesSeleccionadas);
		ventanaContacto(idcita-1, paciente);

		getTextFieldAvisoUsuario().setText("La cita se ha insertado correctamete");
		}
		
		}
		return respuesta;
	}

	
	private void introduceRequisitos(String id, List<String> especialidadesSeleccionadas2) {
		InsertRequisitosCitaAction insertaRequisitos = new InsertRequisitosCitaAction();
		for(String esp : especialidadesSeleccionadas2) {
			String[] requisito = esp.split(":");
			RequisitoDto dto = new RequisitoDto();
			dto.cita=id;
			dto.numeroMedicos=requisito[0];
			dto.especialidad=requisito[1];
			insertaRequisitos.setReqDto(dto);
			insertaRequisitos.execute();
		}

	}

	private boolean compruebaHoraSala(CitaDto cita) 
	{
		List<CitaDto> listaDeCitas = new ListCitasBySala(cita.idSala).execute();
		for(CitaDto c : listaDeCitas) 
		{
			if(Timestamp.valueOf(c.horaInicio).after(Timestamp.valueOf(cita.horaInicio)) || (Timestamp.valueOf(c.horaInicio).before(Timestamp.valueOf(cita.horaInicio)) && Timestamp.valueOf(c.horaFinal).after(Timestamp.valueOf(cita.horaFinal))) ) 
			{
				int respuesta = JOptionPane.showConfirmDialog(null,"La sala "+cita.idSala+"esta ocupada en ese momento.¿Desea crear la cita?","Advertencia al Crear la cita",JOptionPane.YES_NO_OPTION);
				if(respuesta==JOptionPane.YES_OPTION) 
				{
					return true;
				}else {
					return false;
				}
			}
		}
		return true;
		
	}

	private PacienteDto obtenPacienteSeleccionado() {
		int index = getListPaciente().getSelectedIndex();
		if (index >= 0) {
			String p = getListPaciente().getModel().getElementAt(index);
			String[] paciente = p.split("-");
			String nombre = paciente[0];
			String apellido = paciente[1];
			String dni = paciente[2];
			for (PacienteDto dto : this.pacientes) {
				if (nombre.equals(dto.name) && apellido.equals(dto.surname) && dni.equals(dto.dni)) {
					return dto;
				}
			}
		}
		return null;
	}

	private void enviarGmail(String correo, PacienteDto paciente, CitaDto cita) {
		new EnviarEmailUrgenteAction(correo, paciente, cita).execute();

	}

	public boolean compruebaHora(String idMedico) // Comprueba que los medicos no estan ocupados a esa hora y si estan
													// en su jornada laboral
	{
		Date inicio = getDcInicio().getDate();
		Date ultima = getDcFin().getDate();
		String fecha = formateaFecha(inicio);
		String fechafin = formateaFecha(ultima);
		String horaInicio = getComboBoxHoraInicioCita().getSelectedItem().toString() + ":"
				+ getComboBoxMinutoInicioCita().getSelectedItem().toString();
		String horafin = getComboBoxHoraFinCita().getSelectedItem().toString() + ":"
				+ getComboBoxMinutoFinCita().getSelectedItem().toString();
		String horaInicioUser = fecha + " " + horaInicio + ":00";
		String horafinUser = fechafin + " " + horafin + ":00";
		Timestamp horainiciostamp = Timestamp.valueOf(horaInicioUser);
		Timestamp horafinstamp = Timestamp.valueOf(horafinUser);
		List<CitaDto> citasmedico = new ListCitasByMedicoAction(idMedico).execute(); // Obtiene las citas para el
																						// medico.

		for (CitaDto c : citasmedico) {
			// comprueba la hora de las citas
			if (!(horainiciostamp.before(Timestamp.valueOf(c.horaInicio))
					&& horafinstamp.before(Timestamp.valueOf(c.horaFinal))
					|| horainiciostamp.after(Timestamp.valueOf(c.horaInicio))
							&& horafinstamp.after(Timestamp.valueOf(c.horaFinal)))) // comprobacion de hora
			{
				return false;
			}
		}
		return true;

	}

	private boolean compruebaJornada(String idMedico) {
		Date inicio = getDcInicio().getDate();
		Date ultima = getDcFin().getDate();
		String fecha = formateaFecha(inicio);
		String fechafin = formateaFecha(ultima);
		String horaInicio = getComboBoxHoraInicioCita().getSelectedItem().toString() + ":"
				+ getComboBoxMinutoInicioCita().getSelectedItem().toString();
		String horafin = getComboBoxHoraFinCita().getSelectedItem().toString() + ":"
				+ getComboBoxMinutoFinCita().getSelectedItem().toString();
		String horaInicioUser = fecha + " " + horaInicio + ":00";
		String horafinUser = fechafin + " " + horafin + ":00";
		Timestamp horainiciostamp = Timestamp.valueOf(horaInicioUser);
		Timestamp horafinstamp = Timestamp.valueOf(horafinUser);
		List<JornadaDto> jornadas = new ListJornadaLaboralByMedicoAction(idMedico).execute();

		for (JornadaDto jornada : jornadas) {
			Timestamp inicioMedico = Timestamp.valueOf(jornada.diaEntrada);
			Timestamp finMedico = Timestamp.valueOf(jornada.diasalida);
			if (horainiciostamp.after(inicioMedico) && horafinstamp.before(finMedico)) {
				return true;
			}
		}
		return false;
	}

	private String formateaFecha(Date fecha) {
		String[] fechaS = fecha.toString().split(" ");
		String mes = fechaS[1];
		String año = fechaS[5];
		String dia = fechaS[2];
		return año + "-" + seleccionaMes(mes) + "-" + dia;
	}

	private String seleccionaMes(String mes) {
		switch (mes) {
		case "Jan":
			return "01";
		case "Feb":
			return "02";
		case "Mar":
			return "03";
		case "Apr":
			return "04";
		case "May":
			return "05";
		case "Jun":
			return "06";
		case "Jul":
			return "07";
		case "Aug":
			return "08";
		case "Sep":
			return "09";
		case "Oct":
			return "10";
		case "Nov":
			return "11";
		case "Dec":
			return "12";
		default:
			return null;

		}
	}

	private String[] pacientesToString(List<PacienteDto> paciente) {
		String[] strPacientes = new String[paciente.size()];
		for (int i = 0; i < paciente.size(); i++) {
			strPacientes[i] = paciente.get(i).name + "-" + paciente.get(i).surname + "-" + paciente.get(i).dni;
		}
		return strPacientes;
	}

	private String[] medicosToString(List<MedicoDto> medico) {
		String[] strMedicos = new String[medico.size()];
		for (int i = 0; i < medico.size(); i++) {
			strMedicos[i] = medico.get(i).name + "-" + medico.get(i).surname + "-" + medico.get(i).dni;
		}
		return strMedicos;
	}
	
	private String[] especialidadesToString(List<String> especialidades) {
		String[] strEspecialidad = new String[especialidades.size()];
		for (int i = 0; i < especialidades.size(); i++) {
			String[] tot = especialidades.get(i).split(":");
			String med=tot[0];
			String esp= tot[1];
			if(Integer.valueOf(med)==0) {
				strEspecialidad[i] = esp;
			}else if(Integer.valueOf(med)==1){
				strEspecialidad[i] = med+" medico de "+esp;
			}else {
				strEspecialidad[i] = med+" medicos de "+esp;
			}
		}
		return strEspecialidad;
	}

	private List<MedicoDto> obtenMedicosSeleccionados() {
		int[] index = getListMedicos().getSelectedIndices();
		List<MedicoDto> listaSeleccionados = new ArrayList<MedicoDto>();
		if (index.length > 0) {
			for (int i = 0; i < index.length; i++) {
				String medicot = getListMedicos().getModel().getElementAt(index[i]);
				String[] medico = medicot.split("-");
				String nombre = medico[0];
				String apellido = medico[1];
				String dni = medico[2];

				for (MedicoDto dto : this.medicos) {
					if (nombre.equals(dto.name) && apellido.equals(dto.surname) && dni.equals(dto.dni)) {
						listaSeleccionados.add(dto);
					}
				}
			}
			return listaSeleccionados;
		}
		return listaSeleccionados;
	}
	
	private List<String> obtenEspecialidadesSeleccionadas(){
		int[] index = getListMedicos().getSelectedIndices();
		List<String> lista = new ArrayList<String>();
		if (index.length > 0) {
			for (int i = 0; i < index.length; i++) {
			  String select = getListMedicos().getModel().getElementAt(index[i]);
			  
			  for (String e : this.especialidades) {
					if (e.equals(select)) {
						lista.add(e);
					}
				}
			}
		}
		return lista;
	}

	// Metodos de checkeo/Comprobacion de los campos introducidos

	private boolean checkValoresVentanaPrincipal() {
		if (!checkCamposVacios()) {
			// getTextFieldAvisoUsuario().setText("Se deben rellenar todos los campos
			// correctamente para poder realizar una cita");
			JOptionPane.showInternalMessageDialog(null,
					"Escoja una fecha para establecer la cita");
			return false;
		}
//		if(!checkHoraFecha()) {
//			//getTextFieldAvisoUsuario().setText("Formato Incorrecto en fecha y horas-> Formato: HH:MM (y) YYYY:MM:DD");
//			JOptionPane.showInternalMessageDialog(null,"Formato Incorrecto en fecha y horas-> Formato: HH:MM (y) YYYY:MM:DD");
//		}
		return true;

	}

	private boolean checkHoraFecha() {
		// Compruba que se ha seleccionado hora inicial y hora final;
		String fecha = getDcInicio().getDate().toString();
		String fechafin = getDcFin().getDate().toString();
		String horaInicio = getComboBoxHoraInicioCita().getSelectedItem().toString() + ":"
				+ getComboBoxMinutoInicioCita().getSelectedItem().toString();
		String horafin = getComboBoxHoraFinCita().getSelectedItem().toString() + ":"
				+ getComboBoxMinutoFinCita().getSelectedItem().toString();
		if (!checkIsHora(horaInicio) || !checkIsHora(horafin) || !checkFecha(fecha) || !checkFecha(fechafin)) {
			return false;
		}
		return true;
	}

	private boolean checkCamposVacios() {
		//Compruba que se han seleccionado medicos
		if(getDcInicio().getDate()==null || getDcFin().getDate()==null ) 
		{
			return false;
		}
		return true;
	}

	private boolean checkFecha(String fecha) {
		String[] fechadividida = fecha.split("-");
		if (fechadividida.length != 3) {
			return false;
		}

		return true;
	}

	private boolean checkIsHora(String hora) {
		String[] horadividida = hora.split(":");
		if (horadividida.length != 2) {
			return false;
		}
		if (horadividida[0].length() != 2) {
			return false;
		}
		if (horadividida[1].length() != 2) {
			return false;
		}
		return true;
	}

	private List<MedicoDto> filtrarListaMedicos(List<MedicoDto> medi, String start) {
		List<MedicoDto> listaFiltrada = new ArrayList<MedicoDto>();
		for (MedicoDto m : medi) {
			// Filtro por nombre
			if (m.name.toUpperCase().startsWith(start.toUpperCase())) {
				listaFiltrada.add(m);
			}
			//Filtro por documento de identficacion
			else if(m.dni.toUpperCase().startsWith(start.toUpperCase())) 
			{
			    listaFiltrada.add(m);
			}
			else if(m.especialidad.toUpperCase().startsWith(start.toUpperCase())) {
			    listaFiltrada.add(m);
			}
		}
		return listaFiltrada;
	}	
	private String[] filtrarEspecialidades(String[] especialidades,String start) 
	{
	   String[] filtrostr;
	   List<String> especialidad =  new ArrayList<String>(Arrays.asList(especialidades));
	   List<String> filtro =  new ArrayList<String>();
	   for(String ep :especialidad) 
	   {
		   if(ep.toUpperCase().startsWith(start.toUpperCase())) 
		   {
			  filtro.add(ep); 
		   }
	   }
	   filtrostr = new String[filtro.size()];
	   return filtro.toArray(filtrostr);
	}
	
	private List<PacienteDto> filtrarListaPacientes(List<PacienteDto> paci, String start) 
	{
		List<PacienteDto> listaFiltrada = new ArrayList<PacienteDto>();
		for (PacienteDto p : paci) {
			if (p.name.toUpperCase().startsWith(start.toUpperCase())) {
				listaFiltrada.add(p);
			}

			else if (p.dni.toUpperCase().startsWith(start.toUpperCase())) {
				listaFiltrada.add(p);
			}
		}
		return listaFiltrada;
	}
	
	private String[] obtenerEspecialidades() 
	{
		String[] especialidades=new String[48];
		especialidades[0]="Alergologia";
		especialidades[1]="Anestesiologia";
		especialidades[2]="Angiologia";
		especialidades[3]="Analisis clinico";
		especialidades[4]="Anatomia patologica";
		especialidades[5]="Bioquimica clinica";
		especialidades[6]="Cardiologia";
		especialidades[7]="Cirugia Cardiaca";
		especialidades[8]="Cirugia general";
		especialidades[9]="Cirugia oral y maxilofacial";
		especialidades[10]="Cirugia ortopedica";
		especialidades[11]="Cirugia pediatrica";
		especialidades[12]="Cirugia plastica";
		especialidades[13]="Cirugia toracica";
		especialidades[14]="Cirugia vascular";
		especialidades[15]="Dermatologia";
		especialidades[16]="Endocrinologia";
		especialidades[17]="Estomatologia";
		especialidades[18]="Farmacologia";
		especialidades[19]="Gastroenterologia";
		especialidades[20]="Genetica";
		especialidades[21]="Geriatria";
		especialidades[22]="Ginecologia";
		especialidades[23]="Hematologia";
		especialidades[24]="Hepatologia";
		especialidades[25]="Infectologia";
		especialidades[26]="Inmunologia";
		especialidades[27]="Nefrologia";
		especialidades[28]="Neumologia";
		especialidades[29]="Neurologia";
		especialidades[30]="Neurocirugia";
		especialidades[31]="Nutriologia";
		especialidades[32]="Medicina de emergencia";
		especialidades[33]="Medicina familiar";
		especialidades[34]="Medicina fisica";
		especialidades[35]="Medicina intensiva";
		especialidades[36]="Microbilogia";
		especialidades[37]="Oncologia Medica";
		especialidades[38]="Oncologia Radioterapica";
		especialidades[39]="Oftalmologia";
		especialidades[40]="Otorriolaringologia";
		especialidades[41]="Pediatria";
		especialidades[42]="Psiquiatria";
		especialidades[43]="Reumatologia";
		especialidades[44]="Toxicologia";
		especialidades[45]="Traumatologia";
		especialidades[46]="Urologia";
		especialidades[47]="Radiologia";
		
		return especialidades;
	}
	
	private void insertarEspecialidad() 
	{
		this.SelectedEspecialidad="Seleccionadas";
		getBtnEliminarMedico().setEnabled(true);
	    getTxtEspecialidadSeleccionada().setText("Especialidades: "+"Determinadas");
	}
	
	private void eliminarEspecialidad() 
	{
		if(this.especialidadesSeleccionadas.isEmpty()) {
		this.SelectedEspecialidad=null;
		getBtnEliminarMedico().setEnabled(false);
	    getTxtEspecialidadSeleccionada().setText("Especialidad: No determinada");
		}

 	}

	private JTextField getTxtEspecialidadSeleccionada() {
		if (txtEspecialidadSeleccionada == null) {
			txtEspecialidadSeleccionada = new JTextField();
			txtEspecialidadSeleccionada.setText("Especialidad: No determinada");
			txtEspecialidadSeleccionada.setColumns(10);
		}
		return txtEspecialidadSeleccionada;
	}

	private void ventanaContacto(int idCita, PacienteDto paciente) {
		VentanaContacto v = new VentanaContacto(this, idCita, paciente);
		v.setLocationRelativeTo(this);
		v.setModal(true);
		v.setVisible(true);
	}

	private JPanel getPanelCentralDisponibilidad() {
		if (panelCentralDisponibilidad == null) {
			panelCentralDisponibilidad = new JPanel();
			panelCentralDisponibilidad.setLayout(new GridLayout(2, 0, 0, 0));
			panelCentralDisponibilidad.add(getPanelMedicosDisponibilidad());
			panelCentralDisponibilidad.add(getPanelInformacionDisponibilidad());
		}
		return panelCentralDisponibilidad;
	}
	private JPanel getPanelBotonesDisponibilidad() {
		if (panelBotonesDisponibilidad == null) {
			panelBotonesDisponibilidad = new JPanel();
			panelBotonesDisponibilidad.add(getBtnAtras());
		}
		return panelBotonesDisponibilidad;
	}
	private JButton getBtnAtras() {
		if (btnAtras == null) {
			btnAtras = new JButton("Atras");
			btnAtras.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) 
				{
					CardLayout c = (CardLayout)getPanelContenidos().getLayout();
					c.show(getPanelContenidos(),"PanelCreaCitas");
				}
			});
		}
		return btnAtras;
	}
	private JPanel getPanelMedicosDisponibilidad() {
		if (panelMedicosDisponibilidad == null) {
			panelMedicosDisponibilidad = new JPanel();
			panelMedicosDisponibilidad.setLayout(new GridLayout(0, 2, 0, 0));
			panelMedicosDisponibilidad.add(getPanelListaSeleccionadosDisponibilidad());
			panelMedicosDisponibilidad.add(getPanelHoraDisponibilidad());
		}
		return panelMedicosDisponibilidad;
	}
	private JPanel getPanelListaSeleccionadosDisponibilidad() {
		if (panelListaSeleccionadosDisponibilidad == null) {
			panelListaSeleccionadosDisponibilidad = new JPanel();
			panelListaSeleccionadosDisponibilidad.setLayout(new BorderLayout(0, 0));
			panelListaSeleccionadosDisponibilidad.add(getScrollPaneMedicosDisponibilidad());
		}
		return panelListaSeleccionadosDisponibilidad;
	}
	private JPanel getPanelHoraDisponibilidad() {
		if (panelHoraDisponibilidad == null) {
			panelHoraDisponibilidad = new JPanel();
			panelHoraDisponibilidad.setLayout(new GridLayout(0, 2, 0, 0));
			panelHoraDisponibilidad.add(getLblHoraInicioDisponibilidad());
			panelHoraDisponibilidad.add(getPanelHoraInicioDisponibilidad());
			panelHoraDisponibilidad.add(getLblHoraFinalDisponibilidad());
			panelHoraDisponibilidad.add(getPanelHoraFinalDisponibilidad());
			panelHoraDisponibilidad.add(getLblFechaInicioDisponibilidad());
			panelHoraDisponibilidad.add(getDcInicioDisponibilidad());
			panelHoraDisponibilidad.add(getLblFechaFinalDisponibilidad());
			panelHoraDisponibilidad.add(getDcFinalDisponibilidad());
		}
		return panelHoraDisponibilidad;
	}
	private JPanel getPanelInformacionDisponibilidad() {
		if (panelInformacionDisponibilidad == null) {
			panelInformacionDisponibilidad = new JPanel();
			panelInformacionDisponibilidad.setLayout(new BorderLayout(0, 0));
			panelInformacionDisponibilidad.add(getLblHorariosDisponibles(), BorderLayout.NORTH);
			panelInformacionDisponibilidad.add(getScrollPaneHorarios(), BorderLayout.CENTER);
		}
		return panelInformacionDisponibilidad;
	}
	private JScrollPane getScrollPaneMedicosDisponibilidad() {
		if (scrollPaneMedicosDisponibilidad == null) {
			scrollPaneMedicosDisponibilidad = new JScrollPane();
			scrollPaneMedicosDisponibilidad.setViewportView(getListMedicosDisponibilidad());
			scrollPaneMedicosDisponibilidad.setColumnHeaderView(getPanelFiltroMedicosDisponibilidad());
		}
		return scrollPaneMedicosDisponibilidad;
	}
	private JList<String> getListMedicosDisponibilidad() {
		if (listMedicosDisponibilidad == null) {
			listMedicosDisponibilidad = new JList<String>();
		}
		return listMedicosDisponibilidad;
	}
	private JLabel getLblHorariosDisponibles() {
		if (lblHorariosDisponibles == null) {
			lblHorariosDisponibles = new JLabel("Horarios disponibles mas cercanos:\r\n");
		}
		return lblHorariosDisponibles;
	}
	private JScrollPane getScrollPaneHorarios() {
		if (scrollPaneHorarios == null) {
			scrollPaneHorarios = new JScrollPane();
			scrollPaneHorarios.setViewportView(getListHorariosDisponibles());
			scrollPaneHorarios.setColumnHeaderView(getPanelOpcionesDisponibilidad());
		}
		return scrollPaneHorarios;
	}
	private JList<String> getListHorariosDisponibles() {
		if (listHorariosDisponibles == null) {
			listHorariosDisponibles = new JList<String>();
			listHorariosDisponibles.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		}
		return listHorariosDisponibles;
	}
	private JPanel getPanelFiltroMedicosDisponibilidad() {
		if (panelFiltroMedicosDisponibilidad == null) {
			panelFiltroMedicosDisponibilidad = new JPanel();
			panelFiltroMedicosDisponibilidad.setLayout(new BorderLayout(0, 0));
			panelFiltroMedicosDisponibilidad.add(getChckbxFiltroMedicosDisponibilidad(), BorderLayout.WEST);
			panelFiltroMedicosDisponibilidad.add(getTextFieldFiltroMedicosDisponibilidad(), BorderLayout.CENTER);
		}
		return panelFiltroMedicosDisponibilidad;
	}
	private JCheckBox getChckbxFiltroMedicosDisponibilidad() {
		if (chckbxFiltroMedicosDisponibilidad == null) {
			chckbxFiltroMedicosDisponibilidad = new JCheckBox("Medicos Seleccionados");
			chckbxFiltroMedicosDisponibilidad.addItemListener(new ItemListener() {
				public void itemStateChanged(ItemEvent e) 
				{
					if(chckbxFiltroMedicosDisponibilidad.isSelected())
					{
						 String[] medicosst = medicosToString(medicosSeleccionados);
						 ListModel<String> model1 = new DefaultComboBoxModel<String>(medicosst);
						 getListMedicosDisponibilidad().setModel(model1);
						 int[] indices= new int[getListSeleccionados().getModel().getSize()];
					     for(int i=0;i<getListSeleccionados().getModel().getSize();i++) 
					     {
					    	indices[i]=i;
					     }
					     getListMedicosDisponibilidad().setSelectedIndices(indices);
					}else {
						String[] medicosstr = medicosToString(medicos);
						ListModel<String> model = new DefaultComboBoxModel<String>(medicosstr);
						getListMedicosDisponibilidad().setModel(model);
					} 
				}
			});
			chckbxFiltroMedicosDisponibilidad.setSelected(true);
		}
		return chckbxFiltroMedicosDisponibilidad;
	}
	private JTextField getTextFieldFiltroMedicosDisponibilidad() {
		if (textFieldFiltroMedicosDisponibilidad == null) {
			textFieldFiltroMedicosDisponibilidad = new JTextField();
			textFieldFiltroMedicosDisponibilidad.addKeyListener(new KeyAdapter() {
				@Override
				public void keyReleased(KeyEvent e) 
				{
					if(!chckbxFiltroMedicosDisponibilidad.isSelected()) {
						  String[] medicosstr = medicosToString(filtrarListaMedicos(medicos,textFieldFiltroMedicosDisponibilidad.getText()));
						  ListModel<String> model = new DefaultComboBoxModel<String>(medicosstr);
						  listMedicos.setModel(model);
						}else {
						  String[] seleccionadosFiltrados =  medicosToString(filtrarListaMedicos(medicosSeleccionados,textFieldFiltroMedicosDisponibilidad.getText()));
						  ListModel<String> model = new DefaultComboBoxModel<String>(seleccionadosFiltrados);
						  listMedicos.setModel(model);
						}
				}
			});
			textFieldFiltroMedicosDisponibilidad.setColumns(10);
		}
		return textFieldFiltroMedicosDisponibilidad;
	}
	private JLabel getLblHoraInicioDisponibilidad() {
		if (lblHoraInicioDisponibilidad == null) {
			lblHoraInicioDisponibilidad = new JLabel("Hora de inicio:");
		}
		return lblHoraInicioDisponibilidad;
	}
	private JLabel getLblHoraFinalDisponibilidad() {
		if (lblHoraFinalDisponibilidad == null) {
			lblHoraFinalDisponibilidad = new JLabel("Hora final:");
		}
		return lblHoraFinalDisponibilidad;
	}
	private JLabel getLblFechaFinalDisponibilidad() {
		if (lblFechaFinalDisponibilidad == null) {
			lblFechaFinalDisponibilidad = new JLabel("Fecha final:");
		}
		return lblFechaFinalDisponibilidad;
	}
	private JDateChooser getDcInicioDisponibilidad() {
		if (dcInicioDisponibilidad == null) {
			dcInicioDisponibilidad = new JDateChooser();
			dcInicioDisponibilidad.getDateEditor().setEnabled(false);
		}
		return dcInicioDisponibilidad;
	}
	private JDateChooser getDcFinalDisponibilidad() {
		if (dcFinalDisponibilidad == null) {
			dcFinalDisponibilidad = new JDateChooser();
			dcFinalDisponibilidad.getDateEditor().setEnabled(false);
		}
		return dcFinalDisponibilidad;
	}
	private JPanel getPanelHoraInicioDisponibilidad() {
		if (panelHoraInicioDisponibilidad == null) {
			panelHoraInicioDisponibilidad = new JPanel();
			panelHoraInicioDisponibilidad.add(getComboBoxHoraInicioDisponibilidad());
			panelHoraInicioDisponibilidad.add(getLblSeparacionInicioDisponibilidad());
			panelHoraInicioDisponibilidad.add(getComboBoxMinutoInicioDisponibilidad());
		}
		return panelHoraInicioDisponibilidad;
	}
	private JPanel getPanelHoraFinalDisponibilidad() {
		if (panelHoraFinalDisponibilidad == null) {
			panelHoraFinalDisponibilidad = new JPanel();
			panelHoraFinalDisponibilidad.add(getComboBoxHoraFinalDisponibilidad());
			panelHoraFinalDisponibilidad.add(getLblSeparacionFinalDisponibilidad());
			panelHoraFinalDisponibilidad.add(getComboBoxMinutoFinalDisponibilidad());
		}
		return panelHoraFinalDisponibilidad;
	}
	private JLabel getLblFechaInicioDisponibilidad() {
		if (lblFechaInicioDisponibilidad == null) {
			lblFechaInicioDisponibilidad = new JLabel("Fecha Inicio:");
		}
		return lblFechaInicioDisponibilidad;
	}
	private JComboBox<Integer> getComboBoxHoraInicioDisponibilidad() {
		if (comboBoxHoraInicioDisponibilidad == null) {
			comboBoxHoraInicioDisponibilidad = new JComboBox<Integer>();
			Integer[] h = new Integer[24];
			for (int i = 0; i < h.length; i++) {
				h[i] = i;
			}
			comboBoxHoraInicioDisponibilidad.setBounds(342, 147, 72, 27);
			comboBoxHoraInicioDisponibilidad.setModel(new DefaultComboBoxModel<Integer>(h));
		}
		return comboBoxHoraInicioDisponibilidad;
	}
	private JLabel getLblSeparacionInicioDisponibilidad() {
		if (lblSeparacionInicioDisponibilidad == null) {
			lblSeparacionInicioDisponibilidad = new JLabel(":");
		}
		return lblSeparacionInicioDisponibilidad;
	}
	private JComboBox<Integer> getComboBoxMinutoInicioDisponibilidad() {
		if (comboBoxMinutoInicioDisponibilidad == null) {
			comboBoxMinutoInicioDisponibilidad = new JComboBox<Integer>();
			Integer[] h = new Integer[60];
			for (int i = 0; i < h.length; i++) {
				h[i] = i;
			}
			comboBoxMinutoInicioDisponibilidad.setBounds(342, 147, 72, 27);
			comboBoxMinutoInicioDisponibilidad.setModel(new DefaultComboBoxModel<Integer>(h));
			
		}
		return comboBoxMinutoInicioDisponibilidad;
	}
	private JComboBox<Integer> getComboBoxHoraFinalDisponibilidad() {
		if (comboBoxHoraFinalDisponibilidad == null) {
			comboBoxHoraFinalDisponibilidad = new JComboBox<Integer>();
			Integer[] h = new Integer[24];
			for (int i = 0; i < h.length; i++) {
				h[i] = i;
			}
			comboBoxHoraFinalDisponibilidad.setBounds(342, 147, 72, 27);
			comboBoxHoraFinalDisponibilidad.setModel(new DefaultComboBoxModel<Integer>(h));
			
		}
		return comboBoxHoraFinalDisponibilidad;
	}
	private JLabel getLblSeparacionFinalDisponibilidad() {
		if (lblSeparacionFinalDisponibilidad == null) {
			lblSeparacionFinalDisponibilidad = new JLabel(":");
		}
		return lblSeparacionFinalDisponibilidad;
	}
	private JComboBox<Integer> getComboBoxMinutoFinalDisponibilidad() {
		if (comboBoxMinutoFinalDisponibilidad == null) {
			comboBoxMinutoFinalDisponibilidad = new JComboBox<Integer>();
			Integer[] h = new Integer[60];
			for (int i = 0; i < h.length; i++) {
				h[i] = i;
			}
			comboBoxMinutoFinalDisponibilidad.setBounds(342, 147, 72, 27);
			comboBoxMinutoFinalDisponibilidad.setModel(new DefaultComboBoxModel<Integer>(h));
		}
		return comboBoxMinutoFinalDisponibilidad;
	}
	private JPanel getPanelContenidoCita() {
		if (panelContenidoCita == null) {
			panelContenidoCita = new JPanel();
			panelContenidoCita.setLayout(new CardLayout(0, 0));
			panelContenidoCita.add(getPanelCreaCita(),"CreaCitas");

		}
		return panelContenidoCita;
	}
	private JPanel getPanelContenidoDisponibilidad() {
		if (panelContenidoDisponibilidad == null) {
			panelContenidoDisponibilidad = new JPanel();
			panelContenidoDisponibilidad.setLayout(new CardLayout(0, 0));
			panelContenidoDisponibilidad.add(getPanelDisponibilidad(), "name_1120899890498800");

		}
		return panelContenidoDisponibilidad;
	}
	private JCheckBox getChckbxFiltrarPorHora() {
		if (chckbxFiltrarPorHora == null) {
			chckbxFiltrarPorHora = new JCheckBox("Filtrar medicos por hora de la cita");
			chckbxFiltrarPorHora.setEnabled(false);
			chckbxFiltrarPorHora.addItemListener(new ItemListener() {
				public void itemStateChanged(ItemEvent e) 
				{
					getTextFieldFiltroMedico().setText("");
					if(chckbxFiltrarPorHora.isSelected()) {
					   chckbxEspecialidad.setSelected(false);
					   chckbxEspecialidad.setEnabled(false);
					   filtraListaMedicos();
					}else {
						chckbxEspecialidad.setEnabled(true);
						String[] medicosstr = medicosToString(medicos);
						listMedicos.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
						ListModel<String> model = new DefaultComboBoxModel<String>(medicosstr);
						listMedicos.setModel(model);
					}
				}
			});
		}
		return chckbxFiltrarPorHora;
	}
	
	private void filtraListaMedicos() 
	{
		List<MedicoDto> filtro = new ArrayList<MedicoDto>();
		for(MedicoDto medico: this.medicos) 
		{
			if(compruebaJornada(medico.id) && compruebaHora(medico.id)) 
			{
				filtro.add(medico);
			}
		}
		String[] medicosstr = medicosToString(filtro);
		ListModel<String> model = new DefaultComboBoxModel<String>(medicosstr);
		listMedicos.setModel(model);
	}
	private JPanel getPanelOpcionesDisponibilidad() {
		if (panelOpcionesDisponibilidad == null) {
			panelOpcionesDisponibilidad = new JPanel();
			panelOpcionesDisponibilidad.setLayout(new GridLayout(0, 2, 0, 0));
			panelOpcionesDisponibilidad.add(getBtnFiltrarPorHoraDisponibilidad());
			panelOpcionesDisponibilidad.add(getBtnSeleccionarHorario());
		}
		return panelOpcionesDisponibilidad;
	}
	private JButton getBtnFiltrarPorHoraDisponibilidad() {
		if (btnFiltrarPorHoraDisponibilidad == null) {
			btnFiltrarPorHoraDisponibilidad = new JButton("Buscar Horarios");
			btnFiltrarPorHoraDisponibilidad.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) 
				{
					if(checkCamposVaciosDisponibilidad()) 
					{
						List<String> horarios =obtenHorariosMedicosDisponibilidad();
						if(horarios.size()==0)
						{
							horarios.add("No hay horarios disponibles");
							getBtnSeleccionarHorario().setEnabled(false);
						}else {
							getBtnSeleccionarHorario().setEnabled(true);
	
						}
						String[] s=new String[horarios.size()];
						s = horarios.toArray(s);
						ListModel<String> model = new DefaultComboBoxModel<String>(s);
						listHorariosDisponibles.setModel(model);
					}else {
						JOptionPane.showInternalMessageDialog(null,"Añada la fecha y hora de entrada y de salida por la que desea buscar horarios");
					}
					
				}
			});
		}
		return btnFiltrarPorHoraDisponibilidad;
	}
	
	private List<MedicoDto> obtenMedicosSeleccionadosDisponibilidad() {
		int[] index = getListMedicosDisponibilidad().getSelectedIndices();
		List<MedicoDto> listaSeleccionados = new ArrayList<MedicoDto>();
		if (index.length > 0) {
			for (int i = 0; i < index.length; i++) {
				String medicot = getListMedicosDisponibilidad().getModel().getElementAt(index[i]);
				String[] medico = medicot.split("-");
				String nombre = medico[0];
				String apellido = medico[1];
				String dni = medico[2];

				for (MedicoDto dto : this.medicos) {
					if (nombre.equals(dto.name) && apellido.equals(dto.surname) && dni.equals(dto.dni)) {
						listaSeleccionados.add(dto);
					}
				}
			}
			return listaSeleccionados;
		}
		return listaSeleccionados;
	}
	
	private List<String> obtenHorariosMedicosDisponibilidad() 
	{
	List<String> resultado = new ArrayList<String>();
		if(getListMedicosDisponibilidad().getSelectedIndex()!=-1) 
		{
			List<MedicoDto> medicosSeleccionadosDisponibilidad = obtenMedicosSeleccionadosDisponibilidad();
			List<JornadaDto> jornadas = new ListJornadaLaboralByMedicoAction(medicosSeleccionadosDisponibilidad.get(0).id).execute();
			List<JornadaDto> copia = new ArrayList<JornadaDto>(jornadas);
			for(MedicoDto medico : medicosSeleccionadosDisponibilidad) 
			{
				if(medico.id!=(medicosSeleccionadosDisponibilidad.get(0).id))
				{
				 List<JornadaDto> jornadasC = new ListJornadaLaboralByMedicoAction(medico.id).execute();
				 for(JornadaDto j : jornadasC) 
				 {
				    for(JornadaDto jornada : copia) 
				    {
				    	Timestamp inicioFecha = Timestamp.valueOf(jornada.diaEntrada);
				    	Timestamp finFecha = Timestamp.valueOf(jornada.diasalida);
				    	
				    	Timestamp inicioj= Timestamp.valueOf(j.diaEntrada);
				    	Timestamp finj = Timestamp.valueOf(j.diasalida);
				    	
				    	boolean igualdad1=inicioFecha.equals(inicioj);
				    	boolean igualdad2=finFecha.equals(finj);
				    	if((inicioFecha.before(inicioj)||igualdad1) && (finFecha.after(finj)||igualdad2)) 
				    	{
				    		JornadaDto jor = new JornadaDto();
				    		jor.diaEntrada=inicioj.toString();
				    		jor.diasalida=finj.toString();
				    		if(!jornadas.contains(jor)) {
				    		jornadas.add(jor);
				    		jornadas.remove(jornada);
				    		}

				    	}else if((inicioFecha.before(inicioj)||igualdad1) &&( finFecha.before(finj)||igualdad2)) {
				    		
				    		JornadaDto jor = new JornadaDto();
				    		jor.diaEntrada=inicioj.toString();
				    		jor.diasalida=finFecha.toString();
				    		if(!jornadas.contains(jor)) {
				    		jornadas.add(jor);
				    		jornadas.remove(jornada);
				    		}
				    	}else if((inicioFecha.after(inicioj)||igualdad1) && (finFecha.after(finj)||igualdad2)) {
				    		
				    		JornadaDto jor = new JornadaDto();
				    		jor.diaEntrada=inicioFecha.toString();
				    		jor.diasalida=finj.toString();
				    		if(!jornadas.contains(jor)) {
				    			jornadas.add(jor);
					    		jornadas.remove(jornada);
				    		}
				    	}else if((inicioFecha.after(inicioj)||igualdad1) &&( finFecha.before(finj)||igualdad2)) {
				    		//No hace nada
				    	}else {
				    		jornadas.remove(jornada);
				    	}	
				    }
				 }
				}
			}
			
			for(JornadaDto dto: jornadas) 
			{
			    
				resultado.add(dto.diasalida+"-"+dto.diaEntrada);
			}
		}
		return resultado;
		
   
//		for (JornadaDto jornada : jornadas) {
//			Timestamp inicioMedico = Timestamp.valueOf(jornada.diaEntrada);
//			Timestamp finMedico = Timestamp.valueOf(jornada.diasalida);
//			if (horainiciostamp.after(inicioMedico) && horafinstamp.before(finMedico)) {
//				return true;
//			}
//		
//	        }
	}
	
	private boolean checkCamposVaciosDisponibilidad() {
		if(getComboBoxHoraFinalDisponibilidad().getSelectedIndex()==-1 && getComboBoxHoraInicioDisponibilidad().getSelectedIndex()==-1 &&
				getComboBoxMinutoFinalDisponibilidad().getSelectedIndex()==-1 && getComboBoxMinutoInicioDisponibilidad().getSelectedIndex()==-1
				 && getDcFinalDisponibilidad().getDate()==null && getDcInicioDisponibilidad().getDate()==null) 
		{
			return false;
		}
		return true;
	}
	
	private JButton getBtnSeleccionarHorario() {
		if (btnSeleccionarHorario == null) {
			btnSeleccionarHorario = new JButton("SeleccionarHorario");
			btnSeleccionarHorario.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) 
				{
					if(listHorariosDisponibles.getSelectedIndex()!=-1) 
					{
					  String h = listHorariosDisponibles.getSelectedValue();
					  String[] horarios = h.split("-");
					  Timestamp inicio = Timestamp.valueOf(horarios[0]+"-"+horarios[1]+"-"+horarios[2]);
					  Timestamp fina = Timestamp.valueOf(horarios[3]+"-"+horarios[4]+"-"+horarios[5]);
//					    Timestamp m = Timestamp.from(getDateChooser().getDate().toInstant());
//                      String string = inicio.toString().split(" ")[0];
                     
					  Date fechaInicio= new Date(inicio.getTime());
					  Date fechaFin= new Date(fina.getTime());
					  
					  getDcInicio().setDate(fechaInicio);
					  getDcFin().setDate(fechaFin);
					  
					  
					  CardLayout c = (CardLayout)getPanelContenidos().getLayout();
					  c.show(getPanelContenidos(),"PanelCreaCitas");
					}
				}
			});
			btnSeleccionarHorario.setEnabled(false);
		}
		return btnSeleccionarHorario;
	}
	private JPanel getPanelSala() {
		if (panelSala == null) {
			panelSala = new JPanel();
			panelSala.setLayout(new BorderLayout(0, 0));
			panelSala.add(getLblSala(), BorderLayout.WEST);
			panelSala.add(getComboBoxSala(), BorderLayout.CENTER);
		}
		return panelSala;
	}
	private JLabel getLblSala() {
		if (lblSala == null) {
			lblSala = new JLabel("Sala:");
		}
		return lblSala;
	}
	private JComboBox<String> getComboBoxSala() {
		if (comboBoxSala == null) {
			comboBoxSala = new JComboBox<String>();
			comboBoxSala.setEditable(true);
			String[] salas = new String[20];
			salas[0]="No determinada";
			salas[1]="1-1";
			salas[2]="1-2";
			salas[3]="1-3";
			salas[4]="1-4";
			salas[5]="2-1";
			salas[6]="2-2";
			salas[7]="2-3";
			salas[8]="2-4";
			salas[9]="3-1";
			salas[10]="3-2";
			salas[11]="3-3";
			salas[12]="3-4";
			salas[13]="4-1";
			salas[14]="4-2";
			salas[15]="4-3";
			salas[16]="4-4";
			
			comboBoxSala.setModel(new DefaultComboBoxModel<String>(salas));
		}
		return comboBoxSala;
	}
	private JPanel getPanelMotivos() {
		if (panelMotivos == null) {
			panelMotivos = new JPanel();
			panelMotivos.setLayout(new BorderLayout(0, 0));
			panelMotivos.add(getLblMotivos(), BorderLayout.NORTH);
			panelMotivos.add(getTextFieldMotivos(), BorderLayout.SOUTH);
		}
		return panelMotivos;
	}
	private JLabel getLblMotivos() {
		if (lblMotivos == null) {
			lblMotivos = new JLabel("Motivos de la cita:");
		}
		return lblMotivos;
	}
	private JTextField getTextFieldMotivos() {
		if (textFieldMotivos == null) {
			textFieldMotivos = new JTextField();
			textFieldMotivos.addFocusListener(new FocusAdapter() {
				@Override
				public void focusGained(FocusEvent e) 
				{
					textFieldMotivos.selectAll();
				}
			});
			textFieldMotivos.setText("No determinados");
			textFieldMotivos.setHorizontalAlignment(SwingConstants.LEFT);
			textFieldMotivos.setColumns(10);
		}
		return textFieldMotivos;
	}
	private JPanel getPanelAñadir() {
		if (panelAñadir == null) {
			panelAñadir = new JPanel();
			panelAñadir.setLayout(new BorderLayout(0, 0));
			panelAñadir.add(getBtnAñadirMedico());
			panelAñadir.add(getSpinnerMedicoEspecialidad(), BorderLayout.EAST);
		}
		return panelAñadir;
	}
	private JSpinner getSpinnerMedicoEspecialidad() {
		if (spinnerMedicoEspecialidad == null) {
			spinnerMedicoEspecialidad = new JSpinner();
			spinnerMedicoEspecialidad.setVisible(false);
		}
		return spinnerMedicoEspecialidad;
	}
}
