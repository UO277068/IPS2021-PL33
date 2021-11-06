package igu.Ventanas;

import javax.swing.JPanel;
import java.awt.BorderLayout;
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
import igu.action.ListAllMedicosAction;
import igu.action.ListAllPacientesAction;
import igu.action.ListCitasByMedicoAction;
import igu.action.ListJornadaLaboralByMedicoAction;

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

import Logica.crud.dto.*;
import javax.swing.JCheckBox;
import javax.swing.ListSelectionModel;

import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JTextFieldDateEditor;

import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.util.Locale;

public class VentanaCreaCitas extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel panelDatos;
	private JPanel panelBotones;
	private JPanel panelCitaEstandar;
	private JPanel panelMedicosPacientes;
	private JPanel panelHoraUrgencia;
	private JPanel panelAviso;
	private JPanel panelHora;
	private JLabel lblHoraInicio;
	private JLabel lblHoraFinCita;
	private JPanel panelUrgencia;
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
	
	//Atributos
	List<String> medicosselectModel;
	List<PacienteDto> pacientes;
	List<MedicoDto> medicos;
	List<MedicoDto> medicosSeleccionados;
	String[] especialidades;
	String SelectedEspecialidad;
	private JTextField txtEspecialidadSeleccionada;
	

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
		
		this.especialidades=obtenerEspecialidades();
		
		this.SelectedEspecialidad=null;
		
        this.setMinimumSize(new Dimension(800, 520));
        this.setBounds(100, 100, 648, 492);
        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        this.getContentPane().add(getPanelDatos(), BorderLayout.CENTER);
        this.getContentPane().add(getPanelBotones(), BorderLayout.SOUTH);

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
			panelBotones.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
			panelBotones.add(getBtnCrearCita());
		}
		return panelBotones;
	}

	private JPanel getPanelCitaEstandar() {
		if (panelCitaEstandar == null) {
			panelCitaEstandar = new JPanel();
			panelCitaEstandar.setLayout(new BorderLayout(0, 0));
			panelCitaEstandar.add(getPanelMedicosPacientes(), BorderLayout.CENTER);
			panelCitaEstandar.add(getPanelHoraUrgencia(), BorderLayout.SOUTH);
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

	private JPanel getPanelUrgencia() {
		if (panelUrgencia == null) {
			panelUrgencia = new JPanel();
			panelUrgencia.add(getChckbxUrgente());
		}
		return panelUrgencia;
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
						listMedicos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
						listMedicos.setModel(model);
						
						btnAñadirMedico.setText("Añadir especialidad");
						
						btnEliminarMedico.setText("Eliminar especialidad");
						
						if(SelectedEspecialidad==null) 
						{
						  btnEliminarMedico.setEnabled(false);
						}
						
						
					}else {
						getLblMedicos().setText("Medicos:");
						String[] medicosstr = medicosToString(medicos);
						ListModel<String> model = new DefaultComboBoxModel<String>(medicosstr);
						listMedicos.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
						listMedicos.setModel(model);
						
						btnAñadirMedico.setText("Añadir medicos");
						
						btnEliminarMedico.setText("Eliminar medico seleccionado");
						
						btnEliminarMedico.setEnabled(true);

						
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
			panelBotonesMedico.add(getBtnAñadirMedico());
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
					insertarEspecialidad(listMedicos.getSelectedValue());
				}
				}
			});
		}
		return btnAñadirMedico;
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
					 eliminarEspecialidad();
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
					}
					if (comboBoxHoraInicioCita.getSelectedItem().toString().length() >= 2) {
						comboBoxMinutoInicioCita.grabFocus();
						// requestFocus();
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
		if(checkValoresVentanaPrincipal()) //Comprueba que se han elegido los valores minimos para crear una cita
		{
		
		PacienteDto paciente =  obtenPacienteSeleccionado();
		Integer respuesta=JOptionPane.YES_OPTION;
		boolean urge = getChckbxUrgente().isSelected();
		for(MedicoDto medico:medicosSeleccionados) {
		
		//Comprueba que la cita se establece dentro de la jornada laboral
	    if(!compruebaJornada(medico.id)) {
		 respuesta=JOptionPane.showConfirmDialog(null,"El horario de la cita no forma parte de la jornada laboral del medico "+medico.name+" "+medico.surname+".","Advertencia al Crear la cita",JOptionPane.YES_NO_OPTION);
	    }
		//Comprobacion de que el medico no tiene citas a esa hora
		if(respuesta==JOptionPane.YES_OPTION && !compruebaHora(medico.id)) {
			 respuesta=JOptionPane.showConfirmDialog(null,"El medico "+medico.name+" "+medico.surname+" tiene otra cita a esa hora","Advertencia al Crear la cita",JOptionPane.YES_NO_OPTION);
		}
		
		if(respuesta==JOptionPane.YES_OPTION) {	
		CitaDto cita = new CitaDto();
		cita.idMedico=medico.id;
		cita.idPaciente=paciente.id; //elegir mas de un paciente
		cita.causa="";
		//
		Date inicio = getDcInicio().getDate();
		Date ultima = getDcFin().getDate();
		String horaInicio =getComboBoxHoraInicioCita().getSelectedItem().toString()+":"+getComboBoxMinutoInicioCita().getSelectedItem().toString();
		String horafin =getComboBoxHoraFinCita().getSelectedItem().toString()+":"+getComboBoxMinutoFinCita().getSelectedItem().toString();
		String fecha = formateaFecha(inicio)+" "+horaInicio+":00";;
		String fechafin=formateaFecha(ultima)+" "+horafin+":00";;
		
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
		cita.idSala="1";
		cita.preescripcion="";
		cita.contacto=paciente.contacto; //Por defecto F.E.R
		cita.acude="INDEFINIDO";
		if(this.SelectedEspecialidad==null) 
		{
			cita.Especialidad="NO DEFINIDA";
		}else {
			cita.Especialidad=this.SelectedEspecialidad.toUpperCase();
		}
                     
		int idcita = new InsertCitaAction(cita).execute();
		System.out.print(idcita);
		ventanaContacto(idcita-1, paciente);

		getTextFieldAvisoUsuario().setText("La cita se ha insertado correctamete");
		
		}}}
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

	// Metodos de checkeo/Comprobacion de los campos introducidos

	private boolean checkValoresVentanaPrincipal() {
		if (!checkCamposVacios()) {
			// getTextFieldAvisoUsuario().setText("Se deben rellenar todos los campos
			// correctamente para poder realizar una cita");
			JOptionPane.showInternalMessageDialog(null,
					"Se deben rellenar todos los campos correctamente para poder realizar una cita");
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
		if(getListSeleccionados().getModel().getSize()==0) 
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
		especialidades[37]="Oncología Medica";
		especialidades[38]="Oncología Radioterápica";
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
	
	private void insertarEspecialidad(String e) 
	{
		this.SelectedEspecialidad=e;
		getBtnEliminarMedico().setEnabled(true);
	    getTxtEspecialidadSeleccionada().setText("Especialidad: "+e);
	}
	
	private void eliminarEspecialidad() 
	{
		this.SelectedEspecialidad=null;
		getBtnEliminarMedico().setEnabled(false);
	    getTxtEspecialidadSeleccionada().setText("Especialidad: No determinada");

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
}
