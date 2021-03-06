package igu.Ventanas;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.HierarchyEvent;
import java.awt.event.HierarchyListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.FileHandler;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.ListModel;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

import com.toedter.calendar.JDateChooser;

import Logica.Carta;
import Logica.FileUtil;
import Logica.Vacuna;
import Logica.crud.commands.AddInformacionUtil;
import Logica.crud.commands.DeleteCita;
import Logica.crud.commands.DeleteSolicitud;
import Logica.crud.commands.ListAllMedicoById;
import Logica.crud.commands.ListAllSolicitudes;
import Logica.crud.commands.ListInformacionUtilByDate;
import Logica.crud.commands.ListPacienteById;
import Logica.crud.dto.CitaDto;
import Logica.crud.dto.DiagnosticoDto;
import Logica.crud.dto.InformacionDto;
import Logica.crud.dto.JornadaDto;
import Logica.crud.dto.MedicoDto;
import Logica.crud.dto.PacienteDto;
import Logica.crud.dto.SolicitudDto;
import Logica.crud.dto.VacunaDto;
import igu.action.AddDiagnosticoAction;
import igu.action.AddHorarioAction;
import igu.action.AddSolicitudAction;
import igu.action.DeleteSolicitudAction;
import igu.action.InsertCitaAction;
import igu.action.ListAllCitasAction;
import igu.action.ListAllCitasByIdAction;
import igu.action.ListAllDiagnosticosAction;
import igu.action.ListAllMedicosAction;
import igu.action.ListAllPacientesAction;
import igu.action.ListCitaByIdAction;
import igu.action.ListCitasByMedicoAction;
import igu.action.ListDiagnosticoByCapAction;
import igu.action.ListDiagnosticoByCodeAction;
import igu.action.ListDiagnosticoByIdAction;
import igu.action.ListDiagnosticoByRangeAction;
import igu.action.ListJornadaLaboralByMedicoAction;
import igu.action.ListVacunaByIdAction;
import igu.action.UpdateAcudioCitaAction;
import igu.action.UpdateCitaAction;
import igu.action.UpdateHoraEntradaSalidaAction;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class VentanaPrincipal extends JFrame {

	List<PacienteDto> listapacientes;
	List<String> listaDiagnosticos;

	public List<PacienteDto> getListapacientes() {
		return listapacientes;
	}

	List<MedicoDto> listamedicos;
	List<DiagnosticoDto> listaDiagnosticosCode;
	private int changeWindow = 1;
	private String acude = "INDEFINIDO";
	private String[] informacion;
	private Carta carta;
	private List<Vacuna> vacunas;

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel pnMedico;
	private JButton btContinuar;
	private JPanel pnCita;
	private JLabel lbEntrada;
	private JLabel lbSalida;
	private JPanel pnContenidos;
	private JButton btHistorial;
	private JPanel pnHistorial;
	private JScrollPane scHistorial;
	private JTextArea txHistorial;
	private JButton btAsignar;
	private String idCita;
	private String idPaciente;
	private int idMedico;
	private String[] idsCita;
	private String[] idsPaciente;
	private JScrollPane scVacuna;
	private JTextArea txVacuna;
	private JPanel pnEleccion;
	private JButton btAdministrador;
	private JButton btMedico;
	private JPanel pnHorario;
	private JButton btAsignarHorario;
	private JScrollPane scrollPane;
	private JList<String> list;
	private DefaultListModel<String> medicos;
	private JButton btHAtras;
	private JPanel pnHInfo;
	private JPanel pnBt;
	private JDateChooser dcDesde;
	private JDateChooser dcHasta;
	private JLabel lbDesde;
	private JLabel lbHasta;
	private JComboBox<Integer> cbHoraD;
	private JComboBox<Integer> cbMinutosD;
	private JComboBox<Integer> cbHoraH;
	private JComboBox<Integer> cbMinutosH;
	private JComboBox<String> cbOpcion;
	private JPanel pnAdministrador;
	private JPanel panelBotonesPrincipal;
	private JButton btnHistorial;
	private JButton btnJornadaLaboral;
	private JButton btnCrearCita;
	private JButton btnAtras;
	private JPanel panelBotones;
	private JButton btnAtrasP;
	private JButton btnAtrasM;
	private JButton btnAtrasE;
	private JPanel pnVerCitas;
	private JButton btHistorialA;
	private JButton btAtrasA;
	private JButton btCausa;
	private JButton btPrescripcion;

	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JButton btAsignarAcude;

	private JDateChooser dateChooser;
	private JLabel lbFechaCita;
	private JScrollPane scCitas;

	private JList<String> listCitas;
	List<CitaDto> lista;
	private JScrollPane scInfo;
	private JTextArea txInfo;
	List<CitaDto> listaFiltrada;
	private JCheckBox chLunes;
	private JCheckBox chMartes;
	private JCheckBox chMiercoles;
	private JCheckBox chJueves;
	private JCheckBox chViernes;
	private JCheckBox chSabado;
	private JCheckBox chDomingo;

	private int diaDesde;
	private int diaHasta;

	List<Boolean> diasSeleccionados = new ArrayList<>();
	private JButton btnModificarCita;
	private JPanel pnElegirMCita;
	private JButton btModificar;
	private JButton btnAtrasMC;
	private JDateChooser dcCitaModifcar;
	private JLabel lbFechaCitaModifcar;
	private JScrollPane scCitasModifcar;
	private JScrollPane scInfoModificar;
	private JList<String> listModificarCita;
	private JTextArea txInfoModificar;
	private JPanel pnModificarCita;
	private JScrollPane scInfoCita;
	private JTextArea txInfoCita;
	private JScrollPane scElegirMedico;
	private JList<String> listaElegirMedico;
	private JButton btElegirMedico;
	private JLabel lbMHoraInicio;
	private JLabel lbMHoraFin;
	private JComboBox<Integer> cbMHoraInicio;
	private JComboBox<Integer> cbMMinutosInicio;
	private JComboBox<Integer> cbMHoraFin;
	private JComboBox<Integer> cbMMinutosFin;
	private JLabel lbMSala;
	private JComboBox<Integer> cbMSala;
	private JDateChooser dcModificarFecha;

	private JComboBox<Integer> cbHoraEntrada;
	private JComboBox<Integer> cbMinutosEntrada;
	private JComboBox<Integer> cbHoraSalida;
	private JComboBox<Integer> cbMinutosSalida;
	private CitaDto cita;
	private JTextField textField;

	private JPanel panel;
	private JButton btSolicitarCrear;
	private JButton btSolicitarModificar;
	private JButton btSolicitarEliminar;

	private boolean derechos;
	private JButton btSolicitudMod;
	List<MedicoDto> medicosSeleccionados = null;
	private JTextField txMedicos;
	private JButton btMAtras;
	private JPanel pnEMCNorte;
	private JPanel pnEMCModificar;
	private JPanel pnEMCFecha;
	private JPanel pnEMCCentro;
	private JPanel pnEMCSur;
	private JScrollPane scVCitaMedico;
	private JList<String> listaVCitaMedico;
	private JScrollPane scVCitaPaciente;
	private JList<String> listaVCitaPaciente;
	private JPanel pnVCCentro;
	private JPanel pnVCSur;
	private JPanel pnVCAtras;
	private JPanel pnVCVer;
	private JPanel pnVCNorte;
	private JDateChooser dcFechaCita;
	private JLabel lbVCFechaCita;
	private JPanel pnHNorte;
	private JPanel pnHTexto;
	private JPanel pnHCheck;
	private JPanel pnHCFechas;
	private JLabel lbPuntos;
	private JPanel pnCHoras;
	private JLabel pbPuntos2;
	private JPanel pnFecha;
	private JPanel pnHCBotones;
	private JPanel pnHCentro;
	private JPanel panel_1;
	private JScrollPane scllSolicitudMod;
	private JTextArea txtSolicitudMod;
	private JLabel lbObservacionesMod;
	private JPanel panelPrincipal;
	private JPanel panelInformacionDeInteres;
	private JPanel panelInformacionBotones;
	private JButton btnSiguienteInformacion;
	private JButton btnInformacionInteres;
	private JPanel panelInformacionUtil;
	private JPanel panelBotonesInformacion;
	private JButton btnAtrasInformacion;
	private JButton btnA??adirInformacion;
	private JPanel panelCentralInformacion;
	private JTextField textAvisoUsuarioInformacion;
	private JPanel panelPrincipalInformacion;
	private JPanel panelTextoInformacion;
	private JLabel lblInformacion;
	private JTextField textFieldInformacionPrincipal;
	private JPanel panelFechaInformacion;
	private JPanel panelModificadoresFecha;
	private JCheckBox chckbxDiaInformacion;
	private JCheckBox chckbxMesInformacion;
	private JCheckBox chckbxAoInformacion;
	private JTextField textFieldInformacionUtil;
	private JPanel panelFecha;
	private JDateChooser dcHorarioInformacionInicio;
	private JDateChooser dcHorarioInformacionFin;
	private JButton btAsignarVacuna;
	private JButton btAsignarVacunaH;

	private JPanel pnDiagnosticos;
	private JButton btDiagnostico;
	private JScrollPane scTree;
	private JTree tree;
	private String capitulo = "";
	private boolean isExpandedRange = false;
	private boolean isExpandedCode = false;
	private boolean isExpandedCapitulo = false;
	private JScrollPane scDiagnostico;
	private JList<String> listDiagnosticos;
	private JButton btEliminar;
	private JButton btAsignarDiagnostico;
	private JLabel lbCie10;
	private JLabel lbDiagnostico;
	private JButton btAtrasDiagnostico;
	private JScrollPane scObservaciones;
	private JLabel lbObservaciones;
	private JTextArea txObservaciones;
	private JDateChooser dcFechaDiagnostico;
	private JLabel lbFechaDiagnostico;
	private JComboBox<Integer> cbHoraDiagnostico;
	private JComboBox<Integer> cbMinutosDiagnostico;
	private int indexFiltrado;
	private JButton btSeguimiento;
	private JPanel pnBotonesH;
	private JCheckBox chDetalles;
	private JDateChooser dcHistorial;
	private JPanel pnNorte;
	private JPanel pnBotonesV;
	private JDateChooser dcVacuna;
	private JCheckBox chFecha;
	private JCheckBox chFechaV;
	private JButton btVerSolicitudes;
	private JPanel pnVerSolicitudes;
	private JPanel pnBotonesSolicitudes;
	private JScrollPane scllPnSolicitudes;
	private JButton btAceptarSolicitud;
	private JButton btDenegarSolicitud;
	private JList listSolicitudes;
	private List<SolicitudDto> solicitudes;
	private JButton btSalir;
	private JButton btCancelarMod;

	private CitaDto citaModificar;

	private static final String logger = "MiLogger";
	private JButton btSalirSolMod;
	private JPanel pnCDiag;
	private JPanel pnTree;
	private JPanel pnBuscar;
	private JTextField txDiagnosticos;
	private JScrollPane scrollPane_1;
	private JList<String> listAllDiagnosticos;
	private JButton btA??adirDiagnostico;
	private JLabel lbAvisoDiagnosticos;
	private JLabel lbBuscar;
	private JLabel lbMedicos;
	private JLabel lbInfoMod;
	private JLabel lbFechaMod;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaPrincipal frame = new VentanaPrincipal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */

	public VentanaPrincipal() {

		// Atributos
		this.informacion = recuperaInformacionUtil(); // Recupera la informacion util de la ventana de inicio
		this.listamedicos = new ListAllMedicosAction().execute();
		this.listapacientes = new ListAllPacientesAction().execute();
		this.listaDiagnosticosCode = new ListAllDiagnosticosAction().execute();
		for (int i = 0; i < 7; i++) {
			diasSeleccionados.add(false);
		}
		//
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 918, 482);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		contentPane.add(getPnContenidos(), BorderLayout.CENTER);

	}

	private JPanel getPnMedico() {
		if (pnMedico == null) {
			pnMedico = new JPanel();
			pnMedico.setLayout(null);
			pnMedico.add(getBtContinuar());
			pnMedico.add(getBtHistorial());
			pnMedico.add(getBtnAtrasE());
			pnMedico.add(getDateChooser());
			pnMedico.add(getLbFechaCita());
			pnMedico.add(getScCitas());
			pnMedico.add(getScInfo());
			pnMedico.add(getPanel());
		}
		return pnMedico;
	}

	private JButton getBtContinuar() {
		if (btContinuar == null) {
			btContinuar = new JButton("Continuar");
			btContinuar.setEnabled(false);
			btContinuar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (getListCitas().getSelectedIndex() != -1) {
						idPaciente = listaFiltrada.get(getListCitas().getSelectedIndex()).idPaciente;
						idCita = listaFiltrada.get(getListCitas().getSelectedIndex()).id;
					}
					acude = "INDEFINIDO";

					mostrarPnCita();
				}
			});
			btContinuar.setBounds(393, 50, 117, 29);
		}
		return btContinuar;
	}

	private JPanel getPnCita() {
		if (pnCita == null) {
			pnCita = new JPanel();
			pnCita.setLayout(null);
			pnCita.add(getLbEntrada());
			pnCita.add(getLbSalida());
			pnCita.add(getBtAsignar());
			pnCita.add(getBtnAtrasM());
			pnCita.add(getBtCausa());
			pnCita.add(getBtPrescripcion());

			JPanel pnAcudeCita = new JPanel();
			pnAcudeCita.setBorder(new TitledBorder(null, "Acude el paciente a la cita", TitledBorder.LEADING,
					TitledBorder.TOP, null, null));
			pnAcudeCita.setBounds(344, 52, 164, 117);
			pnCita.add(pnAcudeCita);
			pnAcudeCita.setLayout(null);

			JRadioButton rbSiAcude = new JRadioButton("Si");
			rbSiAcude.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					acude = "ACUDIO";
				}
			});
			buttonGroup.add(rbSiAcude);
			rbSiAcude.setBounds(25, 38, 63, 23);
			rbSiAcude.setHorizontalAlignment(SwingConstants.LEFT);
			pnAcudeCita.add(rbSiAcude);

			JRadioButton rbNoAcude = new JRadioButton("No");
			rbNoAcude.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					acude = "NO ACUDIO";
				}
			});
			buttonGroup.add(rbNoAcude);
			rbNoAcude.setBounds(90, 38, 63, 23);
			pnAcudeCita.add(rbNoAcude);
			pnAcudeCita.add(getBtAsignarAcude());
			pnCita.add(getCbHoraEntrada());
			pnCita.add(getCbMinutosEntrada());
			pnCita.add(getCbHoraSalida());
			pnCita.add(getCbMinutosSalida());
			pnCita.add(getBtAsignarVacuna());
			pnCita.add(getBtDiagnostico());
			pnCita.add(getBtSeguimiento());
		}
		return pnCita;
	}

	private void mostrarPnCita() {
		CardLayout c = (CardLayout) getPnContenidos().getLayout();
		c.show(getPnContenidos(), "PnCita");
	}

	private void mostrarPnHorario() {
		CardLayout c = (CardLayout) getPnContenidos().getLayout();
		c.show(getPnContenidos(), "PnHorario");

	}

	private void mostrarPnModificarCita() {
		CardLayout c = (CardLayout) getPnContenidos().getLayout();
		c.show(getPnContenidos(), "PnModificarCita");

	}

	private void mostrarPnElegirMCita() {
		CardLayout c = (CardLayout) getPnContenidos().getLayout();
		c.show(getPnContenidos(), "PnElegirMCita");

	}

	private void mostrarPnDiagnosticos() {
		CardLayout c = (CardLayout) getPnContenidos().getLayout();
		c.show(getPnContenidos(), "PnDiagnosticos");

	}

	private void mostrarPnBuscar() {
		CardLayout c = (CardLayout) getPnCDiag().getLayout();
		c.show(getPnCDiag(), "PnBuscar");

	}

	private void mostrarPnTree() {
		CardLayout c = (CardLayout) getPnCDiag().getLayout();
		c.show(getPnCDiag(), "PnTree");

	}

	private void mostrarPnHistorial() {
		CardLayout c = (CardLayout) getPnContenidos().getLayout();
		c.show(getPnContenidos(), "PnHistorial");
		filtrarHistorial();
		mostrarVacunas();
	}

	private void mostrarVacunas() {
		List<VacunaDto> vacunas = new ListVacunaByIdAction(idPaciente).execute();
		String v = "";
		String fecha = "";
		if (getChFechaV().isSelected() && getDcVacuna().getDate() != null) {
			for (VacunaDto vacuna : vacunas) {
				if (getChFechaV().isSelected()) {
					Timestamp m = Timestamp.from(getDcVacuna().getDate().toInstant());
					String string = m.toString().split(" ")[0];
					if (Date.valueOf(string).compareTo(Date.valueOf(vacuna.fecha.toString().split(" ")[0])) == 0) {
						if (fecha.isBlank() || !fecha.equals(vacuna.fecha.toString().substring(0, 16))) {
							fecha = vacuna.fecha.toString().substring(0, 16);
							v += "Fecha de la visita: " + vacuna.fecha.toString().substring(0, 16) + "\n";
						}
						v += "\t-Vacuna: " + vacuna.vacuna + "\n";
					}
				}
			}
		} else {
			for (VacunaDto vacuna : vacunas) {
				if (fecha.isBlank() || !fecha.equals(vacuna.fecha.toString().substring(0, 16))) {
					fecha = vacuna.fecha.toString().substring(0, 16);
					v += "Fecha de la visita: " + vacuna.fecha.toString().substring(0, 16) + "\n";
				}
				v += "\t-Vacuna: " + vacuna.vacuna + "\n";

			}
		}

		getTxVacuna().setText(v);
	}

	private JLabel getLbEntrada() {
		if (lbEntrada == null) {
			lbEntrada = new JLabel("Hora de entrada");
			lbEntrada.setBounds(41, 57, 106, 16);
		}
		return lbEntrada;
	}

	private JLabel getLbSalida() {
		if (lbSalida == null) {
			lbSalida = new JLabel("Hora de salida");
			lbSalida.setBounds(41, 100, 106, 16);
		}
		return lbSalida;
	}

	private JPanel getPnContenidos() {
		if (pnContenidos == null) {
			pnContenidos = new JPanel();
			pnContenidos.setLayout(new CardLayout(0, 0));
			pnContenidos.add(getPnEleccion(), "PnEleccion");
			pnContenidos.add(getPnMedico(), "PnMedico");
			pnContenidos.add(getPnCita(), "PnCita");
			pnContenidos.add(getPnHistorial(), "PnHistorial");
			pnContenidos.add(getPnHorario(), "PnHorario");
			pnContenidos.add(getPnAdministrador(), "PnAdministrador");
			pnContenidos.add(getPnVerCitas(), "PnVerCitas");
			pnContenidos.add(getPnElegirMCita(), "PnElegirMCita");
			pnContenidos.add(getPnModificarCita(), "PnModificarCita");
			pnContenidos.add(getPanelInformacionUtil(), "PnInformacion");
			pnContenidos.add(getPnDiagnosticos(), "PnDiagnosticos");
			pnContenidos.add(getPnVerSolicitudes(), "PnVerSolicitudes");

		}
		return pnContenidos;
	}

	private void mostrarPnMedico() {
		CardLayout c = (CardLayout) getPnContenidos().getLayout();
		c.show(getPnContenidos(), "PnMedico");
	}

	private JButton getBtHistorial() {
		if (btHistorial == null) {
			btHistorial = new JButton("Ver historial");
			btHistorial.setEnabled(false);
			btHistorial.setBounds(19, 397, 117, 29);
			btHistorial.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						changeWindow = 1;
						idPaciente = listaFiltrada.get(indexFiltrado).idPaciente;
						idCita = listaFiltrada.get(indexFiltrado).id;

						mostrarPnHistorial();
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
			});
		}
		return btHistorial;
	}

	private JPanel getPnHistorial() {
		if (pnHistorial == null) {
			pnHistorial = new JPanel();
			pnHistorial.setLayout(new BorderLayout(0, 0));
			pnHistorial.add(getPnHInfo(), BorderLayout.CENTER);
			pnHistorial.add(getPnBt(), BorderLayout.SOUTH);
			pnHistorial.add(getPnNorte(), BorderLayout.NORTH);
		}
		return pnHistorial;
	}

	private JScrollPane getScHistorial() {
		if (scHistorial == null) {
			scHistorial = new JScrollPane();
			scHistorial.setViewportBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null),
					"Diagnosticos", TitledBorder.LEFT, TitledBorder.TOP, null, new Color(0, 0, 0)));
			scHistorial.setViewportView(getTxHistorial());
		}
		return scHistorial;
	}

	private JTextArea getTxHistorial() {
		if (txHistorial == null) {
			txHistorial = new JTextArea();
			txHistorial.setEditable(false);
		}
		return txHistorial;
	}

	private JButton getBtAsignar() {
		if (btAsignar == null) {
			btAsignar = new JButton("Asignar hora");
			btAsignar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					// List<CitaDto> lista = new ListAllCitasByIdAction(idMedico).execute();
					// String
					// horaInicio=listaFiltrada.get(getListCitas().getSelectedIndex()).horaInicio;
					String horaInicio = cita.horaInicio;
//					new UpdateHoraEntradaSalidaAction(idCita, getTxEntrada().getText(),getTxSalida().getText(),horaInicio).execute();
					new UpdateHoraEntradaSalidaAction(idCita,
							getCbHoraEntrada().getSelectedIndex() + ":" + getCbMinutosEntrada().getSelectedIndex(),
							getCbHoraSalida().getSelectedIndex() + ":" + getCbMinutosSalida().getSelectedIndex(),
							horaInicio).execute();

				}
			});
			btAsignar.setBounds(30, 140, 117, 29);
		}
		return btAsignar;
	}

	private JScrollPane getScVacuna() {
		if (scVacuna == null) {
			scVacuna = new JScrollPane();
			scVacuna.setViewportBorder(
					new TitledBorder(null, "Vacuna", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			scVacuna.setViewportView(getTxVacuna());
		}
		return scVacuna;
	}

	private JTextArea getTxVacuna() {
		if (txVacuna == null) {
			txVacuna = new JTextArea();
			txVacuna.setEditable(false);
		}
		return txVacuna;
	}

	private JPanel getPnEleccion() {
		if (pnEleccion == null) {
			pnEleccion = new JPanel();
			pnEleccion.setLayout(new BorderLayout(0, 0));
			pnEleccion.add(getPanelPrincipal(), BorderLayout.CENTER);
			pnEleccion.add(getPanelInformacionDeInteres(), BorderLayout.SOUTH);
		}
		return pnEleccion;
	}

	private JButton getBtAdministrador() {
		if (btAdministrador == null) {
			btAdministrador = new JButton("Administrador");
			btAdministrador.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					mostrarPanelAdministrador();
				}
			});
		}
		return btAdministrador;
	}

	private JButton getBtMedico() {
		if (btMedico == null) {
			btMedico = new JButton("Medico");
			btMedico.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					mostrarPnMedico();
				}
			});
		}
		return btMedico;
	}

	private JPanel getPnHorario() {
		if (pnHorario == null) {
			pnHorario = new JPanel();
			pnHorario.setLayout(new GridLayout(3, 1, 0, 0));
			pnHorario.add(getPnHNorte());
			pnHorario.add(getPnHCentro());
			pnHorario.add(getPanel_1());
		}
		return pnHorario;
	}

	private JButton getBtAsignarHorario() {
		if (btAsignarHorario == null) {
			btAsignarHorario = new JButton("Asignar Horario");
			btAsignarHorario.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Timestamp d1 = new Timestamp(getDcDesde().getDate().getTime());
					Timestamp d2 = new Timestamp(getDcHasta().getDate().getTime());
					String i = d1.toString().split(" ")[0] + " " + getCbHoraD().getSelectedIndex() + ":"
							+ getCbMinutosD().getSelectedIndex() + ":00";
					String f = d2.toString().split(" ")[0] + " " + getCbHoraH().getSelectedIndex() + ":"
							+ getCbMinutosH().getSelectedIndex() + ":00";
					Timestamp inicio = Timestamp.valueOf(i);
					Timestamp fin = Timestamp.valueOf(f);
					int[] idsmedicos = new int[getList().getSelectedValuesList().size()];
					int[] medicos = getList().getSelectedIndices();
					if (medicosSeleccionados != null) {
						for (int j = 0; j < medicos.length; j++) {
							idsmedicos[j] = Integer.parseInt(medicosSeleccionados.get(medicos[j]).id);
						}
					} else {
						for (int j = 0; j < medicos.length; j++) {
							idsmedicos[j] = medicos[j] + 1;
						}
					}

					new AddHorarioAction(inicio, fin, diasSeleccionados, idsmedicos).execute();
				}
			});
		}
		return btAsignarHorario;
	}

	private JScrollPane getScrollPane() {
		if (scrollPane == null) {
			scrollPane = new JScrollPane();
			scrollPane.setViewportView(getList());
		}
		return scrollPane;
	}

	private JList<String> getList() {
		if (list == null) {
			List<MedicoDto> lista = new ListAllMedicosAction().execute();
			medicos = new DefaultListModel<String>();
			for (int i = 0; i < lista.size(); i++) {
				medicos.addElement(lista.get(i).name + " " + lista.get(i).surname);
			}
			String[] medicosstr = medicosToString(lista);
			ListModel<String> model = new DefaultComboBoxModel<String>(medicosstr);
			list = new JList<String>(medicos);
			list.setModel(model);
			// list.setModel(medicos);
		}
		return list;
	}

	private JButton getBtHAtras() {
		if (btHAtras == null) {
			btHAtras = new JButton("Atras");
			btHAtras.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (changeWindow == 1) {
						mostrarPnMedico();
					} else {
						mostrarPnVerCitas();
					}
				}
			});
		}
		return btHAtras;
	}

	private JPanel getPnHInfo() {
		if (pnHInfo == null) {
			pnHInfo = new JPanel();
			pnHInfo.setLayout(new GridLayout(0, 2, 0, 0));
			pnHInfo.add(getScHistorial());
			pnHInfo.add(getScVacuna());
		}
		return pnHInfo;
	}

	private JPanel getPnBt() {
		if (pnBt == null) {
			pnBt = new JPanel();
			FlowLayout flowLayout = (FlowLayout) pnBt.getLayout();
			flowLayout.setAlignment(FlowLayout.LEFT);
			pnBt.add(getBtHAtras());
			pnBt.add(getBtAsignarVacunaH());
		}
		return pnBt;
	}

	private JDateChooser getDcDesde() {
		if (dcDesde == null) {
			dcDesde = new JDateChooser();
			dcDesde.addPropertyChangeListener(new PropertyChangeListener() {
				public void propertyChange(PropertyChangeEvent evt) {
					if (dcDesde.getDate() != null) {
						if (diaDesde == 1 && diaHasta != 1) {
							getChLunes().setSelected(false);
						} else if (diaDesde == 2 && diaHasta != 2) {
							getChMartes().setSelected(false);
						} else if (diaDesde == 3 && diaHasta != 3) {
							getChMiercoles().setSelected(false);
						} else if (diaDesde == 4 && diaHasta != 4) {
							getChJueves().setSelected(false);
						} else if (diaDesde == 5 && diaHasta != 5) {
							getChViernes().setSelected(false);
						} else if (diaDesde == 6 && diaHasta != 6) {
							getChSabado().setSelected(false);
						} else if (diaDesde == 0 && diaHasta != 0) {
							getChDomingo().setSelected(false);
						}
						diaDesde = dcDesde.getDate().getDay();
						if (diaDesde == 1) {
							getChLunes().setSelected(true);
						} else if (diaDesde == 2) {
							getChMartes().setSelected(true);
						} else if (diaDesde == 3) {
							getChMiercoles().setSelected(true);
						} else if (diaDesde == 4) {
							getChJueves().setSelected(true);
						} else if (diaDesde == 5) {
							getChViernes().setSelected(true);
						} else if (diaDesde == 6) {
							getChSabado().setSelected(true);
						} else if (diaDesde == 0) {
							getChDomingo().setSelected(true);
						}
					}
				}
			});
		}
		return dcDesde;
	}

	private JDateChooser getDcHasta() {
		if (dcHasta == null) {
			dcHasta = new JDateChooser();
			dcHasta.addPropertyChangeListener(new PropertyChangeListener() {
				public void propertyChange(PropertyChangeEvent evt) {
					if (dcHasta.getDate() != null) {

						if (diaHasta == 1 && diaDesde != 1) {
							getChLunes().setSelected(false);
						} else if (diaHasta == 2 && diaDesde != 2) {
							getChMartes().setSelected(false);
						} else if (diaHasta == 3 && diaDesde != 3) {
							getChMiercoles().setSelected(false);
						} else if (diaHasta == 4 && diaDesde != 4) {
							getChJueves().setSelected(false);
						} else if (diaHasta == 5 && diaDesde != 5) {
							getChViernes().setSelected(false);
						} else if (diaHasta == 6 && diaDesde != 6) {
							getChSabado().setSelected(false);
						} else if (diaHasta == 0 && diaDesde != 0) {
							getChDomingo().setSelected(false);
						}
						diaHasta = dcHasta.getDate().getDay();
						if (diaHasta == 1) {
							getChLunes().setSelected(true);
						} else if (diaHasta == 2) {
							getChMartes().setSelected(true);
						} else if (diaHasta == 3) {
							getChMiercoles().setSelected(true);
						} else if (diaHasta == 4) {
							getChJueves().setSelected(true);
						} else if (diaHasta == 5) {
							getChViernes().setSelected(true);
						} else if (diaHasta == 6) {
							getChSabado().setSelected(true);
						} else if (diaHasta == 0) {
							getChDomingo().setSelected(true);
						}

						if (getCbOpcion().getSelectedIndex() == 0) {
							actualizarDias(diaDesde, diaHasta);
						}
					}
				}
			});
		}
		return dcHasta;
	}

	void actualizarDias(int desde, int hasta) {

		getChLunes().setSelected(false);
		getChMartes().setSelected(false);
		getChMiercoles().setSelected(false);
		getChJueves().setSelected(false);
		getChViernes().setSelected(false);
		getChSabado().setSelected(false);
		getChDomingo().setSelected(false);
		Timestamp d1 = new Timestamp(getDcDesde().getDate().getTime());
		Timestamp d2 = new Timestamp(getDcHasta().getDate().getTime());
		d1.setDate(d1.getDate() + 7);
		if (d2.after(d1)) {
			getChLunes().setSelected(true);
			getChMartes().setSelected(true);
			getChMiercoles().setSelected(true);
			getChJueves().setSelected(true);
			getChViernes().setSelected(true);
			getChSabado().setSelected(true);
			getChDomingo().setSelected(true);
		} else {

			for (int i = desde; i <= hasta + 7; i++) {
				int c = i;
				if (i - 7 >= 0)
					c = c - 7;
				if (c == 1) {
					getChLunes().setSelected(true);
				} else if (c == 2) {
					getChMartes().setSelected(true);
				} else if (c == 3) {
					getChMiercoles().setSelected(true);
				} else if (c == 4) {
					getChJueves().setSelected(true);
				} else if (c == 5) {
					getChViernes().setSelected(true);
				} else if (c == 6) {
					getChSabado().setSelected(true);
				} else if (c == 0) {
					getChDomingo().setSelected(true);
				}

				if (c == hasta)
					break;
			}

		}
	}

	private JLabel getLbDesde() {
		if (lbDesde == null) {
			lbDesde = new JLabel("Desde:");
		}
		return lbDesde;
	}

	private JLabel getLbHasta() {
		if (lbHasta == null) {
			lbHasta = new JLabel("Hasta:");
		}
		return lbHasta;
	}

	private JComboBox<Integer> getCbHoraD() {
		if (cbHoraD == null) {
			Integer[] h = new Integer[24];
			for (int i = 0; i < h.length; i++) {
				h[i] = i;
			}
			cbHoraD = new JComboBox<Integer>();
			cbHoraD.setModel(new DefaultComboBoxModel<Integer>(h));
		}
		return cbHoraD;
	}

	private JComboBox<Integer> getCbMinutosD() {
		if (cbMinutosD == null) {
			Integer[] m = new Integer[60];
			for (int j = 0; j < m.length; j++) {
				m[j] = j;
			}
			cbMinutosD = new JComboBox<Integer>();
			cbMinutosD.setModel(new DefaultComboBoxModel<Integer>(m));
		}
		return cbMinutosD;
	}

	private JComboBox<Integer> getCbHoraH() {
		if (cbHoraH == null) {
			Integer[] h = new Integer[24];
			for (int i = 0; i < h.length; i++) {
				h[i] = i;
			}
			cbHoraH = new JComboBox<Integer>();
			cbHoraH.setModel(new DefaultComboBoxModel<Integer>(h));
		}
		return cbHoraH;
	}

	private JComboBox<Integer> getCbMinutosH() {
		if (cbMinutosH == null) {
			Integer[] m = new Integer[60];
			for (int j = 0; j < m.length; j++) {
				m[j] = j;
			}
			cbMinutosH = new JComboBox<Integer>();
			cbMinutosH.setModel(new DefaultComboBoxModel<Integer>(m));
		}
		return cbMinutosH;
	}

	private JComboBox<String> getCbOpcion() {
		if (cbOpcion == null) {
			cbOpcion = new JComboBox<String>();
			cbOpcion.addItemListener(new ItemListener() {
				public void itemStateChanged(ItemEvent e) {
					if (cbOpcion.getSelectedIndex() == 0) {
						actualizarDias(diaDesde, diaHasta);
					}
				}
			});
			cbOpcion.setModel(new DefaultComboBoxModel<String>(new String[] { "Todos los dias", "Personalizado" }));
		}
		return cbOpcion;
	}

	private JPanel getPnAdministrador() {
		if (pnAdministrador == null) {
			pnAdministrador = new JPanel();
			pnAdministrador.setLayout(new BorderLayout(0, 0));
			pnAdministrador.add(getPanelBotonesPrincipal(), BorderLayout.CENTER);
			pnAdministrador.add(getPanelBotones(), BorderLayout.SOUTH);
		}
		return pnAdministrador;
	}

	private JPanel getPanelBotonesPrincipal() {
		if (panelBotonesPrincipal == null) {
			panelBotonesPrincipal = new JPanel();
			panelBotonesPrincipal.setLayout(new GridLayout(0, 3, 0, 0));
			panelBotonesPrincipal.add(getBtnHistorial());
			panelBotonesPrincipal.add(getBtnCrearCita());
			panelBotonesPrincipal.add(getBtnJornadaLaboral());
			panelBotonesPrincipal.add(getBtnModificarCita());
			panelBotonesPrincipal.add(getBtnInformacionInteres());
			panelBotonesPrincipal.add(getBtVerSolicitudes());
		}
		return panelBotonesPrincipal;
	}

	private JButton getBtnHistorial() {
		if (btnHistorial == null) {
			btnHistorial = new JButton("Ver historial de pacientes");
			btnHistorial.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					changeWindow = 2;
					mostrarPnVerCitas();
				}
			});
		}
		return btnHistorial;
	}

	private JButton getBtnJornadaLaboral() {
		if (btnJornadaLaboral == null) {
			btnJornadaLaboral = new JButton("Asignar jornada laboral");
			btnJornadaLaboral.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					mostrarPnHorario();
				}
			});
		}
		return btnJornadaLaboral;
	}

	private JButton getBtnCrearCita() {
		if (btnCrearCita == null) {
			btnCrearCita = new JButton("Crear cita nueva");
			btnCrearCita.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {

					mostrarVentanaCita();
				}
			});
		}
		return btnCrearCita;
	}

	// Metodos
	private void mostrarVentanaCita() {
		VentanaCreaCitas citas = new VentanaCreaCitas(this);
		citas.setLocationRelativeTo(this);
		citas.setModal(true);
		citas.setVisible(true);
	}

	private void mostrarVentanaSeguimiento() {
		VentanaEnfermedad citas = new VentanaEnfermedad(this);
		citas.setLocationRelativeTo(this);
		citas.setModal(true);
		citas.setVisible(true);
	}

	private void mostrarPanelAdministrador() {
		CardLayout c = (CardLayout) getPnContenidos().getLayout();
		c.show(getPnContenidos(), "PnAdministrador");
	}

	private void mostrarPnPrincipal() {
		CardLayout c = (CardLayout) getPnContenidos().getLayout();
		c.show(getPnContenidos(), "PnEleccion");
		cita = null;

	}

	private JButton getBtnAtras() {
		if (btnAtras == null) {
			btnAtras = new JButton("Atras");
			btnAtras.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					mostrarPanelAdministrador();
				}
			});
		}
		return btnAtras;
	}

	private JPanel getPanelBotones() {
		if (panelBotones == null) {
			panelBotones = new JPanel();
			panelBotones.add(getBtnAtrasP());
		}
		return panelBotones;
	}

	private JButton getBtnAtrasP() {
		if (btnAtrasP == null) {
			btnAtrasP = new JButton("Atras");
			btnAtrasP.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					mostrarPnPrincipal();
				}
			});
		}
		return btnAtrasP;
	}

	private JButton getBtnAtrasM() {
		if (btnAtrasM == null) {
			btnAtrasM = new JButton("Atras");
			btnAtrasM.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					mostrarPnMedico();
				}
			});
			btnAtrasM.setBounds(41, 373, 89, 23);
		}
		return btnAtrasM;
	}

	private JButton getBtnAtrasE() {
		if (btnAtrasE == null) {
			btnAtrasE = new JButton("Atras");
			btnAtrasE.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					mostrarPnPrincipal();
				}
			});
			btnAtrasE.setBounds(155, 400, 89, 23);
		}
		return btnAtrasE;
	}

	private JPanel getPnVerCitas() {
		if (pnVerCitas == null) {
			pnVerCitas = new JPanel();
			pnVerCitas.setLayout(new BorderLayout(0, 0));
			pnVerCitas.add(getPnVCCentro());
			pnVerCitas.add(getPnVCSur(), BorderLayout.SOUTH);
			pnVerCitas.add(getPnVCNorte(), BorderLayout.NORTH);
		}
		return pnVerCitas;
	}

	private void mostrarPnVerCitas() {
		CardLayout c = (CardLayout) getPnContenidos().getLayout();
		c.show(getPnContenidos(), "PnVerCitas");
	}

	private JButton getBtHistorialA() {
		if (btHistorialA == null) {
			btHistorialA = new JButton("Ver Historial");
			btHistorialA.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					idPaciente = idsPaciente[getListaVCitaPaciente().getSelectedIndex()];
					cita = lista.get(getListaVCitaPaciente().getSelectedIndex());
					if (getListaVCitaPaciente().getSelectedIndex() != -1)
						mostrarPnHistorial();
				}
			});
		}
		return btHistorialA;
	}

//	private String[] getPacientesCita(int idMedico) {
//		List<CitaDto> lista = new ListAllCitasByIdAction(idMedico).execute();
//		String[] pacientes = new String[lista.size()];
//		idsCita = new String[lista.size()];
//		idsPaciente = new String[lista.size()];
//		for (int i = 0; i < pacientes.length; i++) {
//			pacientes[i]=lista.get(i).namePaciente+" "+lista.get(i).surnamePaciente+" "+lista.get(i).horaInicio.split(":00.000")[0];
//			idsCita[i]=lista.get(i).id;
//			idsPaciente[i]=lista.get(i).idPaciente;
//		}
//		return pacientes;
//	}

	private String[] getPacientesCita(List<CitaDto> lista) {
		// List<CitaDto> lista = new ListAllCitasByIdAction(idMedico).execute();
		String[] pacientes = new String[lista.size()];
		idsCita = new String[lista.size()];
		idsPaciente = new String[lista.size()];
		for (int i = 0; i < pacientes.length; i++) {
			PacienteDto paciente = new ListPacienteById(lista.get(i).idPaciente).execute();
			pacientes[i] = paciente.name + " " + paciente.surname + " " + lista.get(i).horaInicio.split(":00.000")[0];
			idsCita[i] = lista.get(i).id;
			idsPaciente[i] = lista.get(i).idPaciente;
		}
		return pacientes;
	}

	private JButton getBtAtrasA() {
		if (btAtrasA == null) {
			btAtrasA = new JButton("Atras");
			btAtrasA.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					mostrarPanelAdministrador();
				}
			});
		}
		return btAtrasA;
	}

	private void ventanaCausas() {
		VentanaCausas v = new VentanaCausas(new ListAllCitasByIdAction(idMedico).execute()
				.get(getListaVCitaPaciente().getSelectedIndex()).idPaciente, idCita);
		v.setLocationRelativeTo(this);
		v.setModal(true);
		v.setVisible(true);
	}

	private JButton getBtCausa() {
		if (btCausa == null) {
			btCausa = new JButton("Asignar causas");
			btCausa.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					ventanaCausas();
				}
			});
			btCausa.setBounds(30, 204, 159, 29);
		}
		return btCausa;
	}

	private void ventanaPrescripcion() {
		VentanaPrescripcion v = new VentanaPrescripcion(idCita);
		v.setLocationRelativeTo(this);
		v.setModal(true);
		v.setVisible(true);
	}

	private JButton getBtPrescripcion() {
		if (btPrescripcion == null) {
			btPrescripcion = new JButton("Asignar prescripcion");
			btPrescripcion.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					ventanaPrescripcion();
				}
			});
			btPrescripcion.setBounds(205, 204, 159, 29);
		}
		return btPrescripcion;
	}

	private JButton getBtAsignarAcude() {
		if (btAsignarAcude == null) {
			btAsignarAcude = new JButton("Asignar");
			btAsignarAcude.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					new UpdateAcudioCitaAction(idCita, acude).execute();
				}
			});
			btAsignarAcude.setBounds(25, 68, 117, 29);
		}
		return btAsignarAcude;
	}

	private JDateChooser getDateChooser() {
		if (dateChooser == null) {
			dateChooser = new JDateChooser();
			dateChooser.addPropertyChangeListener(new PropertyChangeListener() {
				public void propertyChange(PropertyChangeEvent evt) {
					// idMedico=1;
					// lista = new ListAllCitasByIdAction(idMedico).execute();
					// lista = new ListAllCitasAction().execute();
					listaFiltrada = new ArrayList<CitaDto>();
					for (CitaDto cita : lista) {
						if (getDateChooser().getDate() != null) {
							Timestamp m = Timestamp.from(getDateChooser().getDate().toInstant());
							String string = m.toString().split(" ")[0];
							if (Date.valueOf(string).compareTo(Date.valueOf(cita.horaInicio.split(" ")[0])) == 0)
								listaFiltrada.add(cita);
						}
					}
					String[] pacientes = getPacientesCita(listaFiltrada);
					ListModel<String> model = new DefaultComboBoxModel<String>(pacientes);
					getListCitas().setModel(model);
				}
			});
			dateChooser.setBounds(133, 50, 224, 26);
		}
		return dateChooser;
	}

	private JLabel getLbFechaCita() {
		if (lbFechaCita == null) {
			lbFechaCita = new JLabel("Fecha de la cita:");
			lbFechaCita.setBounds(29, 50, 140, 16);
		}
		return lbFechaCita;
	}

	private JScrollPane getScCitas() {
		if (scCitas == null) {
			scCitas = new JScrollPane();
			scCitas.setBounds(29, 109, 292, 110);
			scCitas.setViewportView(getListCitas());
		}
		return scCitas;
	}

	private JList<String> getListCitas() {
		if (listCitas == null) {
			listCitas = new JList<String>();
			idMedico = 1;
			listCitas.addListSelectionListener(new ListSelectionListener() {
				public void valueChanged(ListSelectionEvent e) {
					if (getListCitas().getSelectedIndex() != -1) {
						idMedico = 1;
						indexFiltrado = getListCitas().getSelectedIndex();
						cita = listaFiltrada.get(getListCitas().getSelectedIndex());

						citaModificar = new CitaDto();
						citaModificar.id = cita.id;
						String sala = cita.idSala;
						String inicio = cita.horaInicio.split(" ")[1].substring(0, 5);
						String salida = cita.horaFinal.split(" ")[1].substring(0, 5);
						String motivo = cita.motivo;
						String medico = listamedicos.get(idMedico - 1).name + " "
								+ listamedicos.get(idMedico - 1).surname;
						getTxInfo().setText("Sala de la cita: " + sala + "\n" + "Hora de inicio: " + inicio + "\n"
								+ "Hora de salida: " + salida + "\n" + "Motivo de la cita: " + motivo);
						getTxInfoCita().setText("Sala de la cita: " + sala + "\n" + "Medico de la cita: " + medico
								+ "\n" + "Hora de inicio: " + inicio + "\n" + "Hora de salida: " + salida + "\n"
								+ "Motivo de la cita: " + motivo);

						getListaElegirMedico().setSelectedIndex(Integer.parseInt(cita.idMedico) - 1);
						getCbMHoraInicio().setSelectedIndex(Integer.parseInt(inicio.substring(0, 2)));
						getCbHoraEntrada().setSelectedIndex(Integer.parseInt(inicio.substring(0, 2)));
						getCbHoraDiagnostico().setSelectedIndex(Integer.parseInt(inicio.substring(0, 2)));
						getCbMHoraFin().setSelectedIndex(Integer.parseInt(salida.substring(0, 2)));
						getCbHoraSalida().setSelectedIndex(Integer.parseInt(salida.substring(0, 2)));
						getCbMMinutosInicio().setSelectedIndex(Integer.parseInt(inicio.substring(3, 5)));
						getCbMinutosDiagnostico().setSelectedIndex(Integer.parseInt(inicio.substring(3, 5)));
						getCbMinutosEntrada().setSelectedIndex(Integer.parseInt(inicio.substring(3, 5)));
						getCbMMinutosFin().setSelectedIndex(Integer.parseInt(salida.substring(3, 5)));
						getCbMinutosSalida().setSelectedIndex(Integer.parseInt(salida.substring(3, 5)));
						getCbMSala().setSelectedIndex(Integer.parseInt(cita.idSala) - 1);
						getDcModificarFecha().setDate(getDateChooser().getDate());
						idCita = cita.id;
						if (idsPaciente.length != 0) {
							getBtContinuar().setEnabled(true);
							getBtHistorial().setEnabled(true);
							getBtSolicitarModificar().setEnabled(true);
							getBtSolicitarEliminar().setEnabled(true);
						}
					} else if (getListCitas().getSelectedIndex() == -1 && cita == null)
						getTxInfo().setText("");
				}
			});
			lista = new ListAllCitasByIdAction(idMedico).execute();
			String[] pacientes = getPacientesCita(lista);
			if (idsPaciente.length == 0) {
				getBtContinuar().setEnabled(false);
				getBtHistorial().setEnabled(false);
				getBtSolicitarModificar().setEnabled(false);
				getBtSolicitarEliminar().setEnabled(false);
			}
			getBtContinuar().repaint();
			getBtHistorial().repaint();
			listCitas.setBorder(new TitledBorder(null, "Citas", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			listCitas.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			ListModel<String> model = new DefaultComboBoxModel<String>(pacientes);
			listCitas.setModel(model);

			listCitas.addListSelectionListener(new ListSelectionListener() {
				public void valueChanged(ListSelectionEvent e) {
					if (getListModificarCita().getSelectedIndex() != -1) {
						CitaDto cita = listaFiltrada.get(getListModificarCita().getSelectedIndex());
						String sala = cita.idSala;
						int idMedico = Integer.parseInt(cita.idMedico);
						String medico = listamedicos.get(idMedico - 1).name + " "
								+ listamedicos.get(idMedico - 1).surname;
						String inicio = cita.horaInicio.split(" ")[1].substring(0, 5);
						String salida = cita.horaFinal.split(" ")[1].substring(0, 5);
						String motivo = cita.causa;
						getTxInfoModificar().setText("Sala de la cita: " + sala + "\n" + "Medico de la cita: " + medico
								+ "\n" + "Hora de inicio: " + inicio + "\n" + "Hora de salida: " + salida + "\n"
								+ "Motivo de la cita: " + motivo);
						getTxInfoCita().setText("Sala de la cita: " + sala + "\n" + "Medico de la cita: " + medico
								+ "\n" + "Hora de inicio: " + inicio + "\n" + "Hora de salida: " + salida + "\n"
								+ "Motivo de la cita: " + motivo);
					}
				}
			});
		}
		return listCitas;
	}

	private JScrollPane getScInfo() {
		if (scInfo == null) {
			scInfo = new JScrollPane();
			scInfo.setBounds(333, 109, 273, 110);
			scInfo.setViewportView(getTxInfo());
		}
		return scInfo;
	}

	private JTextArea getTxInfo() {
		if (txInfo == null) {
			txInfo = new JTextArea();
			txInfo.setBorder(
					new TitledBorder(null, "Informacion cita:", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		}
		return txInfo;
	}

	private JCheckBox getChLunes() {
		if (chLunes == null) {
			chLunes = new JCheckBox("Lunes");
			chLunes.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					if ((diaHasta == 1 || diaDesde == 1) && !chLunes.isSelected())
						chLunes.setSelected(true);
				}
			});
			chLunes.addItemListener(new ItemListener() {
				public void itemStateChanged(ItemEvent e) {
					if (chLunes.isSelected())
						diasSeleccionados.set(1, true);
					else
						diasSeleccionados.set(1, false);
				}
			});
		}
		return chLunes;
	}

	private JCheckBox getChMartes() {
		if (chMartes == null) {
			chMartes = new JCheckBox("Martes");
			chMartes.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					if ((diaHasta == 2 || diaDesde == 2) && !chMartes.isSelected())
						chMartes.setSelected(true);
				}
			});
			chMartes.addItemListener(new ItemListener() {
				public void itemStateChanged(ItemEvent e) {
					if (chMartes.isSelected())
						diasSeleccionados.set(2, true);
					else
						diasSeleccionados.set(2, false);
				}
			});
		}
		return chMartes;
	}

	private JCheckBox getChMiercoles() {
		if (chMiercoles == null) {
			chMiercoles = new JCheckBox("Miercoles");
			chMiercoles.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					if ((diaHasta == 3 || diaDesde == 3) && !chMiercoles.isSelected())
						chMiercoles.setSelected(true);
				}
			});
			chMiercoles.addItemListener(new ItemListener() {
				public void itemStateChanged(ItemEvent e) {
					if (chMiercoles.isSelected())
						diasSeleccionados.set(3, true);
					else
						diasSeleccionados.set(3, false);
				}
			});
		}
		return chMiercoles;
	}

	private JCheckBox getChJueves() {
		if (chJueves == null) {
			chJueves = new JCheckBox("Jueves");
			chJueves.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					if ((diaHasta == 4 || diaDesde == 4) && !chJueves.isSelected())
						chJueves.setSelected(true);
				}
			});
			chJueves.addItemListener(new ItemListener() {
				public void itemStateChanged(ItemEvent e) {
					if (chJueves.isSelected())
						diasSeleccionados.set(4, true);
					else
						diasSeleccionados.set(4, false);
				}
			});
		}
		return chJueves;
	}

	private JCheckBox getChViernes() {
		if (chViernes == null) {
			chViernes = new JCheckBox("Viernes");
			chViernes.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					if ((diaHasta == 5 || diaDesde == 5) && !chViernes.isSelected())
						chViernes.setSelected(true);
				}
			});
			chViernes.addItemListener(new ItemListener() {
				public void itemStateChanged(ItemEvent e) {
					if (chViernes.isSelected())
						diasSeleccionados.set(5, true);
					else
						diasSeleccionados.set(5, false);
				}
			});
		}
		return chViernes;
	}

	private JCheckBox getChSabado() {
		if (chSabado == null) {
			chSabado = new JCheckBox("Sabado");
			chSabado.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					if ((diaHasta == 6 || diaDesde == 6) && !chSabado.isSelected())
						chSabado.setSelected(true);
				}
			});
			chSabado.addItemListener(new ItemListener() {
				public void itemStateChanged(ItemEvent e) {
					if (chSabado.isSelected())
						diasSeleccionados.set(6, true);
					else
						diasSeleccionados.set(6, false);
				}
			});
		}
		return chSabado;
	}

	private JCheckBox getChDomingo() {
		if (chDomingo == null) {
			chDomingo = new JCheckBox("Domingo");
			chDomingo.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					if ((diaHasta == 0 || diaDesde == 0) && !chDomingo.isSelected())
						chDomingo.setSelected(true);
				}
			});
			chDomingo.addItemListener(new ItemListener() {
				public void itemStateChanged(ItemEvent e) {
					if (chDomingo.isSelected())
						diasSeleccionados.set(0, true);
					else
						diasSeleccionados.set(0, false);
				}
			});
		}
		return chDomingo;
	}

	private JButton getBtnModificarCita() {
		if (btnModificarCita == null) {
			btnModificarCita = new JButton("Modificar Cita");
			this.derechos = true;
			btnModificarCita.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					mostrarPnElegirMCita();
				}
			});
		}
		return btnModificarCita;
	}

	private JPanel getPnElegirMCita() {
		if (pnElegirMCita == null) {
			pnElegirMCita = new JPanel();
			pnElegirMCita.setLayout(new BorderLayout(0, 0));
			pnElegirMCita.add(getPnEMCNorte(), BorderLayout.NORTH);
			pnElegirMCita.add(getPnEMCCentro(), BorderLayout.CENTER);
			pnElegirMCita.add(getPnEMCSur(), BorderLayout.SOUTH);
		}
		return pnElegirMCita;
	}

	private JButton getBtModificar() {
		if (btModificar == null) {
			btModificar = new JButton("Modificar");
			btModificar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					getBtSolicitudMod().setEnabled(false);
					getBtSolicitudMod().setVisible(false);
					getScllSolicitudMod().setEnabled(false);
					getScllSolicitudMod().setVisible(false);
					filtrarListaMedicos(listamedicos, "");

					getBtSalirSolMod().setVisible(false);
					getBtSalirSolMod().setEnabled(false);
					getBtCancelarMod().setEnabled(false);
					getBtCancelarMod().setVisible(false);
					mostrarPnModificarCita();
				}
			});
		}
		return btModificar;
	}

	private JButton getBtnAtrasMC() {
		if (btnAtrasMC == null) {
			btnAtrasMC = new JButton("Atras");
			btnAtrasMC.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					mostrarPanelAdministrador();
				}
			});
		}
		return btnAtrasMC;
	}

	private JDateChooser getDcCitaModifcar() {
		if (dcCitaModifcar == null) {
			dcCitaModifcar = new JDateChooser();
			dcCitaModifcar.addPropertyChangeListener(new PropertyChangeListener() {
				public void propertyChange(PropertyChangeEvent evt) {
					// List<CitaDto> lista = new ListAllCitasByIdAction(idMedico).execute();
					lista = new ListAllCitasAction().execute();
					listaFiltrada = new ArrayList<CitaDto>();
					for (CitaDto cita : lista) {
						if (getDcCitaModifcar().getDate() != null) {
							Timestamp m = Timestamp.from(getDcCitaModifcar().getDate().toInstant());
							String string = m.toString().split(" ")[0];
							if (Date.valueOf(string).compareTo(Date.valueOf(cita.horaInicio.split(" ")[0])) == 0)
								listaFiltrada.add(cita);
						}
					}
					String[] pacientes = getPacientesCita(listaFiltrada);
					ListModel<String> model = new DefaultComboBoxModel<String>(pacientes);
					getListModificarCita().setModel(model);
				}
			});
		}
		return dcCitaModifcar;
	}

	private JLabel getLbFechaCitaModifcar() {
		if (lbFechaCitaModifcar == null) {
			lbFechaCitaModifcar = new JLabel("Fecha de la cita:");
		}
		return lbFechaCitaModifcar;
	}

	private JScrollPane getScCitasModifcar() {
		if (scCitasModifcar == null) {
			scCitasModifcar = new JScrollPane();
			scCitasModifcar.setViewportView(getListModificarCita());
		}
		return scCitasModifcar;
	}

	private JScrollPane getScInfoModificar() {
		if (scInfoModificar == null) {
			scInfoModificar = new JScrollPane();
			scInfoModificar.setViewportView(getTxInfoModificar());
		}
		return scInfoModificar;
	}

	private JList<String> getListModificarCita() {
		if (listModificarCita == null) {
			listModificarCita = new JList<String>();
			listModificarCita.addHierarchyListener(new HierarchyListener() {
				public void hierarchyChanged(HierarchyEvent e) {
					if (getListModificarCita().getSelectedIndex() != -1) {
						// CitaDto cita = listaFiltrada.get(getListModificarCita().getSelectedIndex());
						getListaElegirMedico().setSelectedIndex(Integer.parseInt(cita.idMedico) - 1);
						String inicio = cita.horaInicio.split(" ")[1].substring(0, 5);
						String salida = cita.horaFinal.split(" ")[1].substring(0, 5);
						getCbMHoraInicio().setSelectedIndex(Integer.parseInt(inicio.substring(0, 2)));
						getCbMHoraFin().setSelectedIndex(Integer.parseInt(salida.substring(0, 2)));
						getCbMMinutosInicio().setSelectedIndex(Integer.parseInt(inicio.substring(3, 5)));
						getCbMMinutosFin().setSelectedIndex(Integer.parseInt(salida.substring(3, 5)));
						getCbMSala().setSelectedIndex(Integer.parseInt(cita.idSala) - 1);
						getDcModificarFecha().setDate(getDcCitaModifcar().getDate());
						idCita = cita.id;

					}
				}
			});
			listModificarCita.addListSelectionListener(new ListSelectionListener() {
				public void valueChanged(ListSelectionEvent e) {
					if (getListModificarCita().getSelectedIndex() != -1) {
						cita = listaFiltrada.get(getListModificarCita().getSelectedIndex());
						String sala = cita.idSala;
						int idMedico = Integer.parseInt(cita.idMedico);
						String medico = listamedicos.get(idMedico - 1).name + " "
								+ listamedicos.get(idMedico - 1).surname;
						String inicio = cita.horaInicio.split(" ")[1].substring(0, 5);
						String salida = cita.horaFinal.split(" ")[1].substring(0, 5);
						String motivo = cita.causa;
						getTxInfoModificar().setText("Sala de la cita: " + sala + "\n" + "Medico de la cita: " + medico
								+ "\n" + "Hora de inicio: " + inicio + "\n" + "Hora de salida: " + salida + "\n"
								+ "Motivo de la cita: " + motivo);
						getTxInfoCita().setText("Sala de la cita: " + sala + "\n" + "Medico de la cita: " + medico
								+ "\n" + "Hora de inicio: " + inicio + "\n" + "Hora de salida: " + salida + "\n"
								+ "Motivo de la cita: " + motivo);
						// cita=listaFiltrada.get(getListCitas().getSelectedIndex());
					} else if (getListModificarCita().getSelectedIndex() == -1 && cita == null) {
						getTxInfoModificar().setText("");
						getTxInfoCita().setText("");

					}
				}
			});
			lista = new ListAllCitasAction().execute();
			String[] pacientes = getPacientesCita(lista);
			if (idsPaciente.length == 0) {
				getBtContinuar().setEnabled(false);
				getBtHistorial().setEnabled(false);
			}
			listModificarCita
					.setBorder(new TitledBorder(null, "Citas", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			listModificarCita.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			ListModel<String> model = new DefaultComboBoxModel<String>(pacientes);
			listModificarCita.setModel(model);
		}
		return listModificarCita;
	}

	private JTextArea getTxInfoModificar() {
		if (txInfoModificar == null) {
			txInfoModificar = new JTextArea();
			txInfoModificar
					.setBorder(new TitledBorder(null, "Info:", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		}
		return txInfoModificar;
	}

	private JPanel getPnModificarCita() {
		if (pnModificarCita == null) {
			pnModificarCita = new JPanel();
			pnModificarCita.setLayout(null);
			pnModificarCita.add(getScInfoCita());
			pnModificarCita.add(getScElegirMedico());
			pnModificarCita.add(getBtElegirMedico());
			pnModificarCita.add(getLbMHoraInicio());
			pnModificarCita.add(getLbMHoraFin());
			pnModificarCita.add(getCbMHoraInicio());
			pnModificarCita.add(getCbMMinutosInicio());
			pnModificarCita.add(getCbMHoraFin());
			pnModificarCita.add(getCbMMinutosFin());
			pnModificarCita.add(getLbMSala());
			pnModificarCita.add(getCbMSala());
			pnModificarCita.add(getDcModificarFecha());
			pnModificarCita.add(getBtSolicitudMod());
			pnModificarCita.add(getTxMedicos());
			pnModificarCita.add(getBtMAtras());
			pnModificarCita.add(getScllSolicitudMod());
			pnModificarCita.add(getBtCancelarMod());
			pnModificarCita.add(getBtSalirSolMod());
			pnModificarCita.add(getLbMedicos());
			pnModificarCita.add(getLbInfoMod());
			pnModificarCita.add(getLbFechaMod());
		}
		return pnModificarCita;
	}

	private JScrollPane getScInfoCita() {
		if (scInfoCita == null) {
			scInfoCita = new JScrollPane();
			scInfoCita.setBounds(49, 42, 295, 95);
			scInfoCita.setViewportView(getTxInfoCita());
		}
		return scInfoCita;
	}

	private JTextArea getTxInfoCita() {
		if (txInfoCita == null) {
			txInfoCita = new JTextArea();
			txInfoCita.setEditable(false);
		}
		return txInfoCita;
	}

	private JScrollPane getScElegirMedico() {
		if (scElegirMedico == null) {
			scElegirMedico = new JScrollPane();
			scElegirMedico.setBounds(49, 197, 295, 95);
			scElegirMedico.setViewportView(getListaElegirMedico());
		}
		return scElegirMedico;
	}

	private JList<String> getListaElegirMedico() {
		if (listaElegirMedico == null) {
			// List<MedicoDto> lista = new ListAllMedicosAction().execute();
			medicos = new DefaultListModel<String>();
			for (int i = 0; i < listamedicos.size(); i++) {
				medicos.addElement(listamedicos.get(i).name + " " + listamedicos.get(i).surname);
			}
			String[] medicosstr = medicosToString(listamedicos);
			ListModel<String> model = new DefaultComboBoxModel<String>(medicosstr);
			listaElegirMedico = new JList<String>();
			listaElegirMedico.setModel(model);
			// listaElegirMedico.setModel(medicos);
		}
		return listaElegirMedico;
	}

	private JButton getBtElegirMedico() {
		if (btElegirMedico == null) {
			btElegirMedico = new JButton("Modificar");
			if (!derechos) {
				btElegirMedico.setVisible(false);
				btElegirMedico.setEnabled(false);
			}
			btElegirMedico.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
					// String id = String.valueOf(getListaElegirMedico().getSelectedIndex()+1);
					Integer respuesta = JOptionPane.YES_OPTION;
					// MedicoDto medicodto = listamedicos.get(Integer.parseInt(id)-1);
					int medicoid = getListaElegirMedico().getSelectedIndex();
//					int[] idsmedicos = new int[getList().getSelectedValuesList().size()];
//					for (int j = 0; j < medicos.length; j++) {
//							idsmedicos[j]=Integer.parseInt(medicosSeleccionados.get(medicos[j]).id);
//					}
					MedicoDto medicodto = new ListAllMedicoById(medicoid).execute();

					// Comprueba que la cita se establece dentro de la jornada laboral
					if (!compruebaJornada(medicodto.id)) {
						respuesta = JOptionPane.showConfirmDialog(null, "El medico " + medicodto.name + " "
								+ medicodto.surname + " no puede atenderle a esa hora(Jornada Laboral)");
					}
					// Comprobacion de que el medico no tiene citas a esa hora
					if (respuesta == JOptionPane.YES_OPTION && !compruebaHora(medicodto.id)) {
						respuesta = JOptionPane.showConfirmDialog(null, "El medico " + medicodto.name + " "
								+ medicodto.surname + " tiene otra cita a esa hora");
					}

					if (respuesta == JOptionPane.YES_OPTION) {
//							String id = String.valueOf(getListaElegirMedico().getSelectedIndex()+1);

						Timestamp fecha = new Timestamp(getDcModificarFecha().getDate().getTime());
						Timestamp fechaInicio = Timestamp
								.valueOf(fecha.toString().split(" ")[0] + " " + getCbMHoraInicio().getSelectedIndex()
										+ ":" + getCbMMinutosInicio().getSelectedIndex() + ":00");
						Timestamp fechaFin = Timestamp
								.valueOf(fecha.toString().split(" ")[0] + " " + getCbMHoraFin().getSelectedIndex() + ":"
										+ getCbMMinutosFin().getSelectedIndex() + ":00");
						String sala = String.valueOf(getCbMSala().getSelectedItem());
						new UpdateCitaAction(medicodto.id, fechaInicio, fechaFin, sala, idCita).execute();
						cita.horaInicio = fechaInicio.toString();
						cita.horaFinal = fechaFin.toString();
						cita.idSala = sala;
						String inicio = cita.horaInicio.split(" ")[1].substring(0, 5);
						String salida = cita.horaFinal.split(" ")[1].substring(0, 5);
						String motivo = cita.causa;
						// String medico = listamedicos.get(Integer.parseInt(id)-1).name + " "
						// +listamedicos.get(Integer.parseInt(id)-1).surname;
						String medico = medicodto.name + " " + medicodto.surname;
						getTxInfoCita().setText("Sala de la cita: " + sala + "\n" + "Medico de la cita: " + medico
								+ "\n" + "Hora de inicio: " + inicio + "\n" + "Hora de salida: " + salida + "\n"
								+ "Motivo de la cita: " + motivo);
					}
				}
			});
			btElegirMedico.setBounds(59, 307, 117, 29);
		}
		return btElegirMedico;
	}

	private JLabel getLbMHoraInicio() {
		if (lbMHoraInicio == null) {
			lbMHoraInicio = new JLabel("Hora inicio:");
			lbMHoraInicio.setBounds(425, 44, 79, 16);
		}
		return lbMHoraInicio;
	}

	private JLabel getLbMHoraFin() {
		if (lbMHoraFin == null) {
			lbMHoraFin = new JLabel("Hora fin:");
			lbMHoraFin.setBounds(425, 84, 61, 16);
		}
		return lbMHoraFin;
	}

	private JComboBox<Integer> getCbMHoraInicio() {
		if (cbMHoraInicio == null) {
			cbMHoraInicio = new JComboBox<Integer>();
			Integer[] h = new Integer[24];
			for (int i = 0; i < h.length; i++) {
				h[i] = i;
			}
			cbMHoraInicio.setBounds(519, 40, 72, 27);
			cbMHoraInicio.setModel(new DefaultComboBoxModel<Integer>(h));
		}
		return cbMHoraInicio;
	}

	private JComboBox<Integer> getCbMMinutosInicio() {
		if (cbMMinutosInicio == null) {
			Integer[] m = new Integer[60];
			for (int j = 0; j < m.length; j++) {
				m[j] = j;
			}
			cbMMinutosInicio = new JComboBox<Integer>();
			cbMMinutosInicio.setBounds(591, 40, 72, 27);
			cbMMinutosInicio.setModel(new DefaultComboBoxModel<Integer>(m));

		}
		return cbMMinutosInicio;
	}

	private JComboBox<Integer> getCbMHoraFin() {
		if (cbMHoraFin == null) {
			Integer[] h = new Integer[24];
			for (int i = 0; i < h.length; i++) {
				h[i] = i;
			}
			cbMHoraFin = new JComboBox<Integer>();
			cbMHoraFin.setBounds(519, 80, 72, 27);
			cbMHoraFin.setModel(new DefaultComboBoxModel<Integer>(h));

		}
		return cbMHoraFin;
	}

	private JComboBox<Integer> getCbMMinutosFin() {
		if (cbMMinutosFin == null) {
			Integer[] m = new Integer[60];
			for (int j = 0; j < m.length; j++) {
				m[j] = j;
			}
			cbMMinutosFin = new JComboBox<Integer>();
			cbMMinutosFin.setBounds(591, 80, 72, 27);
			cbMMinutosFin.setModel(new DefaultComboBoxModel<Integer>(m));

		}
		return cbMMinutosFin;
	}

	private JLabel getLbMSala() {
		if (lbMSala == null) {
			lbMSala = new JLabel("Sala:");
			lbMSala.setBounds(425, 121, 61, 16);
		}
		return lbMSala;
	}

	private JComboBox<Integer> getCbMSala() {
		if (cbMSala == null) {
			Integer[] m = new Integer[20];
			for (int j = 0; j < m.length; j++) {
				m[j] = j + 1;
			}
			cbMSala = new JComboBox<Integer>();
			cbMSala.setBounds(519, 117, 72, 27);
			cbMSala.setModel(new DefaultComboBoxModel<Integer>(m));
		}
		return cbMSala;
	}

	private JDateChooser getDcModificarFecha() {
		if (dcModificarFecha == null) {
			dcModificarFecha = new JDateChooser();
			dcModificarFecha.setBounds(519, 166, 166, 26);
		}
		return dcModificarFecha;
	}

	public boolean compruebaHora(String idMedico) // Comprueba que los medicos no estan ocupados a esa hora y si estan
	// en su jornada laboral
	{
//		//		Date inicio = getDcModificarFecha().getDate();
//		Date ultima = getDcModificarFecha().getDate();
		Timestamp m = Timestamp.from(getDcModificarFecha().getDate().toInstant());
		String fecha = m.toString().split(" ")[0];
//		Date inicio = Date.valueOf(string);
		Timestamp m2 = Timestamp.from(getDcModificarFecha().getDate().toInstant());
		String fechafin = m2.toString().split(" ")[0];
//		Date ultima = Date.valueOf(string2);
//		String fecha = formateaFecha(inicio);
//		String fechafin = formateaFecha(ultima);
		String horaInicio = getCbMHoraInicio().getSelectedItem().toString() + ":"
				+ getCbMMinutosInicio().getSelectedItem().toString();
		String horafin = getCbMHoraFin().getSelectedItem().toString() + ":"
				+ getCbMMinutosFin().getSelectedItem().toString();
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
//		Date inicio = getDcModificarFecha().getDate();
//		Date ultima = getDcModificarFecha().getDate();
		Timestamp m = Timestamp.from(getDcModificarFecha().getDate().toInstant());
		String fecha = m.toString().split(" ")[0];
//		Date inicio = Date.valueOf(string);
		Timestamp m2 = Timestamp.from(getDcModificarFecha().getDate().toInstant());
		String fechafin = m2.toString().split(" ")[0];
//		Date ultima = Date.valueOf(string2);
//		String fecha = formateaFecha(inicio);
//		String fechafin = formateaFecha(ultima);
		String horaInicio = getCbMHoraInicio().getSelectedItem().toString() + ":"
				+ getCbMMinutosInicio().getSelectedItem().toString();
		String horafin = getCbMHoraFin().getSelectedItem().toString() + ":"
				+ getCbMMinutosFin().getSelectedItem().toString();
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

	private JPanel getPanel() {
		if (panel == null) {
			panel = new JPanel();
			panel.setBorder(new TitledBorder(null, "Solicitudes", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			panel.setBounds(382, 306, 308, 53);
			panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
			panel.add(getBtSolicitarCrear());
			panel.add(getBtSolicitarModificar());
			panel.add(getBtSolicitarEliminar());
		}
		return panel;
	}

	private JButton getBtSolicitarCrear() {
		if (btSolicitarCrear == null) {
			btSolicitarCrear = new JButton("Crear");
			btSolicitarCrear.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					mostrarVentanaSolicitarCrear();
				}
			});
		}
		return btSolicitarCrear;
	}

	private void mostrarVentanaSolicitarCrear() {
		VentanaSolicitarCrearCita vSolicitarCrear = new VentanaSolicitarCrearCita(this);
		vSolicitarCrear.setLocationRelativeTo(this);
		vSolicitarCrear.setModal(true);
		vSolicitarCrear.setVisible(true);
	}

	private JComboBox<Integer> getCbHoraEntrada() {
		if (cbHoraEntrada == null) {
			cbHoraEntrada = new JComboBox<Integer>();
			Integer[] h = new Integer[24];
			for (int i = 0; i < h.length; i++) {
				h[i] = i;
			}
			cbHoraEntrada.setBounds(159, 57, 76, 26);
			cbHoraEntrada.setModel(new DefaultComboBoxModel<Integer>(h));
		}
		return cbHoraEntrada;
	}

	private JComboBox<Integer> getCbMinutosEntrada() {
		if (cbMinutosEntrada == null) {
			cbMinutosEntrada = new JComboBox<Integer>();
			Integer[] m = new Integer[60];
			for (int j = 0; j < m.length; j++) {
				m[j] = j;
			}
			cbMinutosEntrada.setBounds(234, 57, 76, 27);
			cbMinutosEntrada.setModel(new DefaultComboBoxModel<Integer>(m));
		}
		return cbMinutosEntrada;
	}

	private JComboBox<Integer> getCbHoraSalida() {
		if (cbHoraSalida == null) {
			cbHoraSalida = new JComboBox<Integer>();
			Integer[] h = new Integer[24];
			for (int i = 0; i < h.length; i++) {
				h[i] = i;
			}
			cbHoraSalida.setBounds(159, 96, 76, 27);
			cbHoraSalida.setModel(new DefaultComboBoxModel<Integer>(h));
		}
		return cbHoraSalida;
	}

	private JComboBox<Integer> getCbMinutosSalida() {
		if (cbMinutosSalida == null) {
			cbMinutosSalida = new JComboBox<Integer>();
			Integer[] m = new Integer[60];
			for (int j = 0; j < m.length; j++) {
				m[j] = j;
			}
			cbMinutosSalida.setBounds(234, 95, 76, 29);
			cbMinutosSalida.setModel(new DefaultComboBoxModel<Integer>(m));
		}
		return cbMinutosSalida;
	}

	private JTextField getTextField() {
		if (textField == null) {
			textField = new JTextField();
			textField.addKeyListener(new KeyAdapter() {
				@Override
				public void keyReleased(KeyEvent e) {
					String[] medicosstr = medicosToString(filtrarListaMedicos(listamedicos, getTextField().getText()));
					ListModel<String> model = new DefaultComboBoxModel<String>(medicosstr);
					list.setModel(model);
				}
			});
			textField.setColumns(10);
		}
		return textField;
	}

	private List<MedicoDto> filtrarListaMedicos(List<MedicoDto> medi, String start) {
		List<MedicoDto> listaFiltrada = new ArrayList<MedicoDto>();
		medicosSeleccionados = new ArrayList<MedicoDto>();
		for (MedicoDto m : medi) {
			// Filtro por nombre
			if (m.name.toUpperCase().startsWith(start.toUpperCase())) {
				listaFiltrada.add(m);
				medicosSeleccionados.add(m);
			}
			// Filtro por documento de identficacion
			else if (m.dni.toUpperCase().startsWith(start.toUpperCase())) {
				listaFiltrada.add(m);
			} else if (m.especialidad.toUpperCase().startsWith(start.toUpperCase())) {
				listaFiltrada.add(m);
			}
		}
		return listaFiltrada;
	}

	private String[] medicosToString(List<MedicoDto> medico) {
		String[] strMedicos = new String[medico.size()];
		for (int i = 0; i < medico.size(); i++) {
			strMedicos[i] = medico.get(i).name + "-" + medico.get(i).surname + "-" + medico.get(i).dni;
		}
		return strMedicos;
	}

	private JButton getBtSolicitarModificar() {
		if (btSolicitarModificar == null) {
			btSolicitarModificar = new JButton("Modificar");
			btSolicitarModificar.setEnabled(false);
			btSolicitarModificar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					derechos = false;
					if (getListCitas().getSelectedIndex() != -1) {
						getBtElegirMedico().setEnabled(false);
						getBtElegirMedico().setVisible(false);
						getBtSalirSolMod().setVisible(false);
						getBtSalirSolMod().setEnabled(false);
						getBtMAtras().setEnabled(false);
						getBtMAtras().setVisible(false);
						// citaModificar = listaFiltrada.get(getListCitas().getSelectedIndex());
						mostrarPnModificarCita();
					}
				}
			});
		}
		return btSolicitarModificar;
	}

	private JButton getBtSolicitarEliminar() {
		if (btSolicitarEliminar == null) {
			btSolicitarEliminar = new JButton("Eliminar");
			btSolicitarEliminar.setEnabled(false);
			btSolicitarEliminar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (getListCitas().getSelectedIndex() == -1)
						JOptionPane.showMessageDialog(null, "Selecciona una cita primero para borrar.");
					else {
						CitaDto cita = listaFiltrada.get(getListCitas().getSelectedIndex());

						Integer respuesta = JOptionPane.showConfirmDialog(null,
								"???Estas seguro de solicitar eliminar la cita seleccionada?");
						if (respuesta == JOptionPane.YES_OPTION) {

							SolicitudDto sol = new SolicitudDto();
							sol.tipo = "ELIMINAR";
							sol.cuerpo = cita.id;
							new AddSolicitudAction(sol).execute();
						}
					}
				}
			});
		}
		return btSolicitarEliminar;
	}

	private JButton getBtSolicitudMod() {
		if (btSolicitudMod == null) {
			btSolicitudMod = new JButton("Solicitar Modificar");
			btSolicitudMod.setBounds(227, 310, 117, 29);
			btSolicitudMod.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {

					String id = String.valueOf(getListaElegirMedico().getSelectedIndex() + 1);

					Integer respuesta = JOptionPane.YES_OPTION;
					MedicoDto medicodto = listamedicos.get(Integer.parseInt(id) - 1);

					if (!compruebaJornada(medicodto.id)) {
						respuesta = JOptionPane.showConfirmDialog(null, "El medico " + medicodto.name + " "
								+ medicodto.surname + " no puede atenderle a esa hora(Jornada Laboral)");
					}
					// Comprobacion de que el medico no tiene citas a esa hora
					if (respuesta == JOptionPane.YES_OPTION && !compruebaHora(medicodto.id)) {
						respuesta = JOptionPane.showConfirmDialog(null, "El medico " + medicodto.name + " "
								+ medicodto.surname + " tiene otra cita a esa hora");
					}

					if (respuesta == JOptionPane.YES_OPTION) {
						String idM = String.valueOf(getListaElegirMedico().getSelectedIndex() + 1);
						SolicitudDto sol = new SolicitudDto();

						String[] fecha = getDcModificarFecha().getDate().toGMTString().split(" ");
						String fechaInicio = getCbMHoraInicio().getSelectedIndex() + ":"
								+ getCbMMinutosInicio().getSelectedIndex() + ":00";
						String fechaFin = getCbMHoraFin().getSelectedIndex() + ":"
								+ getCbMMinutosFin().getSelectedIndex() + ":00";
						String sala = String.valueOf(getCbMSala().getSelectedItem());

						sol.tipo = "MODIFICAR";
						String mes = formateaMes(fecha[1]);
						System.out.println(fecha[1]);
						sol.cuerpo = idM + "#" + (Integer.parseInt(fecha[0]) + 1) + "-" + mes + "-" + fecha[2] + "#"
								+ fechaInicio + "#" + fechaFin + "#" + sala + "#2";
						sol.observaciones = getTxtSolicitudMod().getText();
						new AddSolicitudAction(sol).execute();
						mostrarPnMedico();
					}

				}
			});
		}
		return btSolicitudMod;
	}

	private String formateaMes(String mes) {
		if (mes.equals("Jan"))
			return "1";
		if (mes.equals("Feb"))
			return "2";
		if (mes.equals("Mar"))
			return "3";
		if (mes.equals("Apr"))
			return "4";
		if (mes.equals("May"))
			return "5";
		if (mes.equals("Jun"))
			return "6";
		if (mes.equals("Jul"))
			return "7";
		if (mes.equals("Aug"))
			return "8";
		if (mes.equals("Sep"))
			return "9";
		if (mes.equals("Oct"))
			return "10";
		if (mes.equals("Nov"))
			return "11";
		else
			return "12";
	}

	private JTextField getTxMedicos() {
		if (txMedicos == null) {
			txMedicos = new JTextField();
			txMedicos.addKeyListener(new KeyAdapter() {
				@Override
				public void keyReleased(KeyEvent e) {
					String[] medicosstr = medicosToString(filtrarListaMedicos(listamedicos, getTxMedicos().getText()));
					ListModel<String> model = new DefaultComboBoxModel<String>(medicosstr);
					listaElegirMedico.setModel(model);
				}
			});
			txMedicos.setBounds(49, 166, 295, 26);
			txMedicos.setColumns(10);
		}
		return txMedicos;
	}

	private JButton getBtMAtras() {
		if (btMAtras == null) {
			btMAtras = new JButton("Atras");
			btMAtras.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					mostrarPnElegirMCita();
				}
			});
			btMAtras.setBounds(28, 398, 117, 29);
		}
		return btMAtras;
	}

	private JPanel getPnEMCNorte() {
		if (pnEMCNorte == null) {
			pnEMCNorte = new JPanel();
			pnEMCNorte.setLayout(new GridLayout(0, 2, 0, 0));
			pnEMCNorte.add(getPnEMCFecha());
			pnEMCNorte.add(getPnEMCModificar());
		}
		return pnEMCNorte;
	}

	private JPanel getPnEMCModificar() {
		if (pnEMCModificar == null) {
			pnEMCModificar = new JPanel();
			FlowLayout flowLayout = (FlowLayout) pnEMCModificar.getLayout();
			flowLayout.setVgap(10);
			pnEMCModificar.add(getBtModificar());
		}
		return pnEMCModificar;
	}

	private JPanel getPnEMCFecha() {
		if (pnEMCFecha == null) {
			pnEMCFecha = new JPanel();
			pnEMCFecha.setLayout(new BorderLayout(0, 0));
			pnEMCFecha.add(getLbFechaCitaModifcar(), BorderLayout.WEST);
			pnEMCFecha.add(getDcCitaModifcar());
		}
		return pnEMCFecha;
	}

	private JPanel getPnEMCCentro() {
		if (pnEMCCentro == null) {
			pnEMCCentro = new JPanel();
			pnEMCCentro.setLayout(new GridLayout(0, 2, 0, 0));
			pnEMCCentro.add(getScCitasModifcar());
			pnEMCCentro.add(getScInfoModificar());
		}
		return pnEMCCentro;
	}

	private JPanel getPnEMCSur() {
		if (pnEMCSur == null) {
			pnEMCSur = new JPanel();
			FlowLayout flowLayout = (FlowLayout) pnEMCSur.getLayout();
			flowLayout.setAlignment(FlowLayout.LEFT);
			pnEMCSur.add(getBtnAtrasMC());
		}
		return pnEMCSur;
	}

	private JScrollPane getScVCitaMedico() {
		if (scVCitaMedico == null) {
			scVCitaMedico = new JScrollPane();
			scVCitaMedico.setViewportView(getListaVCitaMedico());
		}
		return scVCitaMedico;
	}

	private JList<String> getListaVCitaMedico() {
		if (listaVCitaMedico == null) {
			listaVCitaMedico = new JList<>();
			listaVCitaMedico
					.setBorder(new TitledBorder(null, "Medicos", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			String[] medicos = new String[listamedicos.size()];
			for (int i = 0; i < medicos.length; i++) {

				medicos[i] = listamedicos.get(i).name + " " + listamedicos.get(i).surname;
			}
			listaVCitaMedico.addListSelectionListener(new ListSelectionListener() {
				public void valueChanged(ListSelectionEvent e) {
					idMedico = listaVCitaMedico.getSelectedIndex() + 1;
					lista = new ListAllCitasByIdAction(idMedico).execute();
					getListaVCitaPaciente().setModel(new DefaultComboBoxModel<String>(getPacientesCita(lista)));

				}
			});
			idMedico = listaVCitaMedico.getSelectedIndex() + 1;
			listaVCitaMedico.setModel(new DefaultComboBoxModel<String>((medicos)));
		}
		return listaVCitaMedico;
	}

	private JScrollPane getScrollPane_1_1() {
		if (scVCitaPaciente == null) {
			scVCitaPaciente = new JScrollPane();
			scVCitaPaciente.setViewportView(getListaVCitaPaciente());
		}
		return scVCitaPaciente;
	}

	private JList<String> getListaVCitaPaciente() {
		if (listaVCitaPaciente == null) {
			listaVCitaPaciente = new JList<String>();
			listaVCitaPaciente
					.setBorder(new TitledBorder(null, "Pacientes", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		}
		return listaVCitaPaciente;
	}

	private JPanel getPnVCCentro() {
		if (pnVCCentro == null) {
			pnVCCentro = new JPanel();
			pnVCCentro.setLayout(new GridLayout(0, 2, 0, 0));
			pnVCCentro.add(getScVCitaMedico());
			pnVCCentro.add(getScrollPane_1_1());
		}
		return pnVCCentro;
	}

	private JPanel getPnVCSur() {
		if (pnVCSur == null) {
			pnVCSur = new JPanel();
			pnVCSur.setLayout(new GridLayout(0, 2, 0, 0));
			pnVCSur.add(getPnVCAtras());
			pnVCSur.add(getPnVCVer());
		}
		return pnVCSur;
	}

	private JPanel getPnVCAtras() {
		if (pnVCAtras == null) {
			pnVCAtras = new JPanel();
			FlowLayout flowLayout = (FlowLayout) pnVCAtras.getLayout();
			flowLayout.setAlignment(FlowLayout.LEFT);
			pnVCAtras.add(getBtAtrasA());
		}
		return pnVCAtras;
	}

	private JPanel getPnVCVer() {
		if (pnVCVer == null) {
			pnVCVer = new JPanel();
			FlowLayout flowLayout = (FlowLayout) pnVCVer.getLayout();
			flowLayout.setAlignment(FlowLayout.RIGHT);
			pnVCVer.add(getBtHistorialA());
		}
		return pnVCVer;
	}

	private JPanel getPnVCNorte() {
		if (pnVCNorte == null) {
			pnVCNorte = new JPanel();
			pnVCNorte.setLayout(new BorderLayout(0, 0));
			pnVCNorte.add(getLbVCFechaCita(), BorderLayout.WEST);
			pnVCNorte.add(getDcFechaCita(), BorderLayout.CENTER);
		}
		return pnVCNorte;
	}

	private JDateChooser getDcFechaCita() {
		if (dcFechaCita == null) {
			dcFechaCita = new JDateChooser();
			dcFechaCita.addPropertyChangeListener(new PropertyChangeListener() {
				public void propertyChange(PropertyChangeEvent evt) {
					if (dcFechaCita.getDate() != null) {
						listaFiltrada = new ArrayList<CitaDto>();
						for (CitaDto cita : lista) {
							Timestamp m = Timestamp.from(getDcFechaCita().getDate().toInstant());
							String string = m.toString().split(" ")[0];
							if (Date.valueOf(string).compareTo(Date.valueOf(cita.horaInicio.split(" ")[0])) == 0)
								listaFiltrada.add(cita);

						}
						listaVCitaPaciente
								.setModel((new DefaultComboBoxModel<String>(getPacientesCita(listaFiltrada))));
					}

				}
			});
		}
		return dcFechaCita;
	}

	private JLabel getLbVCFechaCita() {
		if (lbVCFechaCita == null) {
			lbVCFechaCita = new JLabel("Elige fecha de la cita:");
		}
		return lbVCFechaCita;
	}

	private JPanel getPnHNorte() {
		if (pnHNorte == null) {
			pnHNorte = new JPanel();
			pnHNorte.setLayout(new BorderLayout(0, 0));
			pnHNorte.add(getPnHTexto());
			pnHNorte.add(getPanel_1_1(), BorderLayout.SOUTH);
		}
		return pnHNorte;
	}

	private JPanel getPnHTexto() {
		if (pnHTexto == null) {
			pnHTexto = new JPanel();
			pnHTexto.setLayout(new BorderLayout(0, 0));
			pnHTexto.add(getScrollPane());
			pnHTexto.add(getTextField(), BorderLayout.NORTH);
		}
		return pnHTexto;
	}

	private JPanel getPanel_1_1() {
		if (pnHCheck == null) {
			pnHCheck = new JPanel();
			pnHCheck.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
			pnHCheck.add(getChLunes());
			pnHCheck.add(getChMartes());
			pnHCheck.add(getChMiercoles());
			pnHCheck.add(getChJueves());
			pnHCheck.add(getChViernes());
			pnHCheck.add(getChSabado());
			pnHCheck.add(getChDomingo());
		}
		return pnHCheck;
	}

	private JPanel getPnHCFechas() {
		if (pnHCFechas == null) {
			pnHCFechas = new JPanel();
			pnHCFechas.setAlignmentX(Component.LEFT_ALIGNMENT);
			pnHCFechas.setLayout(new GridLayout(0, 2, 0, 0));
			pnHCFechas.add(getPnFecha());
			pnHCFechas.add(getPnCHoras());
		}
		return pnHCFechas;
	}

	private JLabel getLbPuntos() {
		if (lbPuntos == null) {
			lbPuntos = new JLabel("             :");
		}
		return lbPuntos;
	}

	private JPanel getPnCHoras() {
		if (pnCHoras == null) {
			pnCHoras = new JPanel();
			pnCHoras.setLayout(new GridLayout(0, 3, 0, 0));
			pnCHoras.add(getCbHoraD());
			pnCHoras.add(getLbPuntos());
			pnCHoras.add(getCbMinutosD());
			pnCHoras.add(getCbHoraH());
			pnCHoras.add(getPbPuntos2());
			pnCHoras.add(getCbMinutosH());
		}
		return pnCHoras;
	}

	private JLabel getPbPuntos2() {
		if (pbPuntos2 == null) {
			pbPuntos2 = new JLabel("             :");
		}
		return pbPuntos2;
	}

	private JPanel getPnFecha() {
		if (pnFecha == null) {
			pnFecha = new JPanel();
			pnFecha.setLayout(new GridLayout(2, 2, 0, 0));
			pnFecha.add(getLbDesde());
			pnFecha.add(getDcDesde());
			pnFecha.add(getLbHasta());
			pnFecha.add(getDcHasta());
		}
		return pnFecha;
	}

	private JPanel getPnHCBotones() {
		if (pnHCBotones == null) {
			pnHCBotones = new JPanel();
			pnHCBotones.add(getCbOpcion());
			pnHCBotones.add(getBtAsignarHorario());
		}
		return pnHCBotones;
	}

	private JPanel getPnHCentro() {
		if (pnHCentro == null) {
			pnHCentro = new JPanel();
			pnHCentro.setLayout(new BorderLayout(0, 0));
			pnHCentro.add(getPnHCFechas());
			pnHCentro.add(getPnHCBotones(), BorderLayout.SOUTH);
		}
		return pnHCentro;
	}

	private JPanel getPanel_1() {
		if (panel_1 == null) {
			panel_1 = new JPanel();
			FlowLayout flowLayout = (FlowLayout) panel_1.getLayout();
			flowLayout.setAlignment(FlowLayout.LEFT);
			flowLayout.setVgap(100);
			panel_1.add(getBtnAtras());
		}
		return panel_1;
	}

	private JScrollPane getScllSolicitudMod() {
		if (scllSolicitudMod == null) {
			scllSolicitudMod = new JScrollPane();
			scllSolicitudMod.setBounds(423, 243, 202, 96);
			scllSolicitudMod.setViewportView(getTxtSolicitudMod());
			scllSolicitudMod.setColumnHeaderView(getLbObservacionesMod());
		}
		return scllSolicitudMod;
	}

	private JTextArea getTxtSolicitudMod() {
		if (txtSolicitudMod == null) {
			txtSolicitudMod = new JTextArea();
		}
		return txtSolicitudMod;
	}

	private JLabel getLbObservacionesMod() {
		if (lbObservacionesMod == null) {
			lbObservacionesMod = new JLabel("Observaciones:");
			lbObservacionesMod.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return lbObservacionesMod;
	}

	private JPanel getPanelPrincipal() {
		if (panelPrincipal == null) {
			panelPrincipal = new JPanel();
			panelPrincipal.setLayout(new GridLayout(0, 2, 0, 0));
			panelPrincipal.add(getBtAdministrador());
			panelPrincipal.add(getBtMedico());
		}
		return panelPrincipal;
	}

	private JPanel getPanelInformacionDeInteres() {
		if (panelInformacionDeInteres == null) {
			panelInformacionDeInteres = new JPanel();
			panelInformacionDeInteres.setLayout(new BorderLayout(0, 0));
			panelInformacionDeInteres.add(getPanelInformacionBotones(), BorderLayout.EAST);
			panelInformacionDeInteres.add(getTextFieldInformacionUtil(), BorderLayout.CENTER);
		}
		return panelInformacionDeInteres;
	}

	private JPanel getPanelInformacionBotones() {
		if (panelInformacionBotones == null) {
			panelInformacionBotones = new JPanel();
			panelInformacionBotones.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
			panelInformacionBotones.add(getBtnSiguienteInformacion());
		}
		return panelInformacionBotones;
	}

	private JButton getBtnSiguienteInformacion() {
		if (btnSiguienteInformacion == null) {
			btnSiguienteInformacion = new JButton("\u25BA");
			btnSiguienteInformacion.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (informacion.length != 0) {
						String nuevaInfo = chargeInformacion();
						getTextFieldInformacionUtil().setText(nuevaInfo);
					}

				}
			});
		}
		return btnSiguienteInformacion;
	}

	private JButton getBtnInformacionInteres() {
		if (btnInformacionInteres == null) {
			btnInformacionInteres = new JButton("Modificar informacion");
			btnInformacionInteres.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					mostrarPnInformacion();
				}

			});
		}
		return btnInformacionInteres;
	}

	private void mostrarPnInformacion() {
		CardLayout c = (CardLayout) getPnContenidos().getLayout();
		c.show(getPnContenidos(), "PnInformacion");

	}

	private JPanel getPanelInformacionUtil() {
		if (panelInformacionUtil == null) {
			panelInformacionUtil = new JPanel();
			panelInformacionUtil.setLayout(new BorderLayout(0, 0));
			panelInformacionUtil.add(getPanelBotonesInformacion(), BorderLayout.SOUTH);
			panelInformacionUtil.add(getPanelCentralInformacion(), BorderLayout.CENTER);
		}
		return panelInformacionUtil;
	}

	private JPanel getPanelBotonesInformacion() {
		if (panelBotonesInformacion == null) {
			panelBotonesInformacion = new JPanel();
			panelBotonesInformacion.add(getBtnAtrasInformacion());
			panelBotonesInformacion.add(getBtnA??adirInformacion());
		}
		return panelBotonesInformacion;
	}

	private JButton getBtnAtrasInformacion() {
		if (btnAtrasInformacion == null) {
			btnAtrasInformacion = new JButton("Atras\r\n");
			btnAtrasInformacion.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					CardLayout c = (CardLayout) getPnContenidos().getLayout();
					c.show(getPnContenidos(), "PnAdministrador");
				}
			});

		}
		return btnAtrasInformacion;
	}

	private JButton getBtnA??adirInformacion() {
		if (btnA??adirInformacion == null) {
			btnA??adirInformacion = new JButton("A\u00F1adir Informacion");
			btnA??adirInformacion.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					boolean a??adido = a??adeInformacion();
					if (a??adido == true) {
						getTextAvisoUsuarioInformacion().setText("La informacion se ha a???adido correctamente");
						getTextFieldInformacionPrincipal().setText("");
					}
				}
			});
			btnA??adirInformacion.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		}
		return btnA??adirInformacion;
	}

	@SuppressWarnings("deprecation")
	private boolean a??adeInformacion() {
		String texto = getTextFieldInformacionPrincipal().getText();
		if (texto == null || texto.isEmpty()) {
			return false;
		} else {
			int resp = JOptionPane.YES_OPTION;
			if (getDcHorarioInformacionFin().getDate() == null) {
				resp = JOptionPane.showConfirmDialog(null,
						"Si no selecciona fecha de fin se pondra por defecto el a???o.???Desea continuar?");
			}

			if (resp != JOptionPane.YES_OPTION) {
				return false;
			} else {
				InformacionDto info = new InformacionDto();
				if (chckbxAoInformacion.isSelected() && chckbxDiaInformacion.isSelected()
						&& chckbxMesInformacion.isSelected()) {
					java.util.Date inicio = getDcHorarioInformacionInicio().getDate();
					java.util.Date ultima = getDcHorarioInformacionFin().getDate();
					String fechaInicioInformacion = formateaFecha(inicio) + " " + "00:00" + ":00";
					String fechafinInformacion = formateaFecha(ultima) + " " + "00:00" + ":00";
					info.inicio = Timestamp.valueOf(fechaInicioInformacion);
					info.fin = Timestamp.valueOf(fechafinInformacion);
					info.texto = texto;
					new AddInformacionUtil().execute(info);
					this.informacion = recuperaInformacionUtil();
					return true;
				} else {
					String[] fechaI = dcHorarioInformacionInicio.getDate().toString().split(" ");
					String[] fechaF = dcHorarioInformacionFin.getDate().toString().split(" ");

					String yearI = "";
					String monthI = "";
					String dayI = "";
					String yearF = "";
					String monthF = "";
					String dayF = "";
					if (chckbxAoInformacion.isSelected()) {
						yearI = fechaI[5];
						yearF = fechaF[5];
					} else {
						yearI = "1999";
						yearF = "3999";
					}
					if (chckbxMesInformacion.isSelected()) {
						monthI = fechaI[1];
						monthF = fechaF[1];
					} else {
						monthI = "Jan";
						monthF = "Dec";
					}
					if (chckbxDiaInformacion.isSelected()) {
						dayI = fechaI[2];
						dayF = fechaF[2];
					} else {
						dayI = "01";
						dayF = "28";
					}

					String fechaInicioInformacion2 = yearI + "-" + seleccionaMes(monthI) + "-" + dayI + " " + "00:00"
							+ ":00";
					String fechafinInformacion2 = yearF + "-" + seleccionaMes(monthF) + "-" + dayF + " " + "00:00"
							+ ":00";

					info.inicio = Timestamp.valueOf(fechaInicioInformacion2);
					info.fin = Timestamp.valueOf(fechafinInformacion2);
					info.texto = texto;
					new AddInformacionUtil().execute(info);
					this.informacion = recuperaInformacionUtil();
					return true;
				}
			}
		}
	}

	private String formateaFecha(java.util.Date ultima) {
		String[] fechaS = ultima.toString().split(" ");
		String mes = fechaS[1];
		String a??o = fechaS[5];
		String dia = fechaS[2];
		return a??o + "-" + seleccionaMes(mes) + "-" + dia;
	}

	private JPanel getPanelCentralInformacion() {
		if (panelCentralInformacion == null) {
			panelCentralInformacion = new JPanel();
			panelCentralInformacion.setLayout(new BorderLayout(0, 0));
			panelCentralInformacion.add(getTextAvisoUsuarioInformacion(), BorderLayout.SOUTH);
			panelCentralInformacion.add(getPanelPrincipalInformacion(), BorderLayout.CENTER);
			panelCentralInformacion.add(getLblInformacion(), BorderLayout.NORTH);
		}
		return panelCentralInformacion;
	}

	private JTextField getTextAvisoUsuarioInformacion() {
		if (textAvisoUsuarioInformacion == null) {
			textAvisoUsuarioInformacion = new JTextField();
			textAvisoUsuarioInformacion.setEditable(false);
			textAvisoUsuarioInformacion.setColumns(10);
		}
		return textAvisoUsuarioInformacion;
	}

	private JPanel getPanelPrincipalInformacion() {
		if (panelPrincipalInformacion == null) {
			panelPrincipalInformacion = new JPanel();
			panelPrincipalInformacion.setLayout(new GridLayout(1, 0, 0, 0));
			panelPrincipalInformacion.add(getPanelTextoInformacion());
		}
		return panelPrincipalInformacion;
	}

	private JPanel getPanelTextoInformacion() {
		if (panelTextoInformacion == null) {
			panelTextoInformacion = new JPanel();
			panelTextoInformacion.setLayout(new GridLayout(0, 2, 0, 0));
			panelTextoInformacion.add(getTextFieldInformacionPrincipal());
			panelTextoInformacion.add(getPanelFechaInformacion());
		}
		return panelTextoInformacion;
	}

	private JLabel getLblInformacion() {
		if (lblInformacion == null) {
			lblInformacion = new JLabel("A\u00F1ada la informacion y el tiempo que se desea mostrar:");
		}
		return lblInformacion;
	}

	private JTextField getTextFieldInformacionPrincipal() {
		if (textFieldInformacionPrincipal == null) {
			textFieldInformacionPrincipal = new JTextField();
			textFieldInformacionPrincipal.setHorizontalAlignment(SwingConstants.LEFT);
			textFieldInformacionPrincipal.setColumns(10);
		}
		return textFieldInformacionPrincipal;
	}

	private JPanel getPanelFechaInformacion() {
		if (panelFechaInformacion == null) {
			panelFechaInformacion = new JPanel();
			panelFechaInformacion.setLayout(new BorderLayout(0, 0));
			panelFechaInformacion.add(getPanel_2_1(), BorderLayout.NORTH);
			panelFechaInformacion.add(getPanelFecha(), BorderLayout.CENTER);
		}
		return panelFechaInformacion;
	}

	private JPanel getPanel_2_1() {
		if (panelModificadoresFecha == null) {
			panelModificadoresFecha = new JPanel();
			panelModificadoresFecha.add(getChckbxDiaInformacion());
			panelModificadoresFecha.add(getChckbxMesInformacion());
			panelModificadoresFecha.add(getChckbxAoInformacion());
		}
		return panelModificadoresFecha;
	}

	private JCheckBox getChckbxDiaInformacion() {
		if (chckbxDiaInformacion == null) {
			chckbxDiaInformacion = new JCheckBox("Dia\r\n");
			chckbxDiaInformacion.setSelected(true);
		}
		return chckbxDiaInformacion;
	}

	private JCheckBox getChckbxMesInformacion() {
		if (chckbxMesInformacion == null) {
			chckbxMesInformacion = new JCheckBox("Mes\r\n");
			chckbxMesInformacion.setSelected(true);
		}
		return chckbxMesInformacion;
	}

	private JCheckBox getChckbxAoInformacion() {
		if (chckbxAoInformacion == null) {
			chckbxAoInformacion = new JCheckBox("A\u00F1o\r\n");
			chckbxAoInformacion.setSelected(true);
		}
		return chckbxAoInformacion;
	}

	private String[] recuperaInformacionUtil() // Carga la informacion a una lista o un array desde uno o varios
												// ficheros locales.
	{
		LocalDateTime ahora = LocalDateTime.now();
		String[] util = new ListInformacionUtilByDate().execute(Timestamp.valueOf(ahora));
		this.informacion = util;
		return util;
	}

	private String chargeInformacion() // Escoje informacion al azar
	{
		Random random = new Random();
		int aleatorio = random.nextInt(this.informacion.length - 1);
		String text = this.informacion[aleatorio];
		if (!getTextFieldInformacionUtil().getText().equals(text)) {
			return text;
		} else {
			return this.informacion[aleatorio + 1];
		}
	}

	private JTextField getTextFieldInformacionUtil() {
		if (textFieldInformacionUtil == null) {
			textFieldInformacionUtil = new JTextField();
			textFieldInformacionUtil.setEditable(false);
			textFieldInformacionUtil.setColumns(10);
			if (this.informacion.length != 0) {
				textFieldInformacionUtil.setText(this.informacion[0]);
			} else {
				textFieldInformacionUtil.setText("Informacion Relevante por a???adir");
			}

		}
		return textFieldInformacionUtil;
	}

	private JPanel getPanelFecha() {
		if (panelFecha == null) {
			panelFecha = new JPanel();
			panelFecha.setLayout(new GridLayout(2, 0, 0, 0));
			panelFecha.add(getDcHorarioInformacionInicio());
			panelFecha.add(getDcHorarioInformacionFin());
		}
		return panelFecha;
	}

	private JDateChooser getDcHorarioInformacionInicio() {
		if (dcHorarioInformacionInicio == null) {
			dcHorarioInformacionInicio = new JDateChooser();
		}
		return dcHorarioInformacionInicio;
	}

	private JDateChooser getDcHorarioInformacionFin() {
		if (dcHorarioInformacionFin == null) {
			dcHorarioInformacionFin = new JDateChooser();
		}
		return dcHorarioInformacionFin;
	}

	private JButton getBtAsignarVacuna() {
		if (btAsignarVacuna == null) {
			btAsignarVacuna = new JButton("Asignar Vacuna");
			btAsignarVacuna.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					mostrarVentanaVacuna();
				}
			});
			btAsignarVacuna.setBounds(374, 204, 151, 29);
		}
		return btAsignarVacuna;
	}

	private void mostrarVentanaVacuna() {
		carta = new Carta();
		VentanaVacuna v = new VentanaVacuna(carta, this);
		v.setLocationRelativeTo(this);
		v.setModal(true);
		v.setVisible(true);

	}

	public CitaDto getCita() {
		return cita;
	}

	public String getIdPaciente() {
		return idPaciente;
	}

	private JButton getBtAsignarVacunaH() {
		if (btAsignarVacunaH == null) {
			btAsignarVacunaH = new JButton("Asignar Vacuna");
			btAsignarVacunaH.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					mostrarVentanaVacuna();
					mostrarVacunas();

				}
			});
		}
		return btAsignarVacunaH;
	}

	private JPanel getPnDiagnosticos() {
		if (pnDiagnosticos == null) {
			pnDiagnosticos = new JPanel();
			pnDiagnosticos.setLayout(null);
			pnDiagnosticos.add(getScDiagnostico());
			pnDiagnosticos.add(getBtEliminar());
			pnDiagnosticos.add(getBtAsignarDiagnostico());
			pnDiagnosticos.add(getLbCie10());
			pnDiagnosticos.add(getLbDiagnostico());
			pnDiagnosticos.add(getBtAtrasDiagnostico());
			pnDiagnosticos.add(getScObservaciones());
			pnDiagnosticos.add(getLbObservaciones());
			pnDiagnosticos.add(getDcFechaDiagnostico());
			pnDiagnosticos.add(getLbFechaDiagnostico());
			pnDiagnosticos.add(getCbHoraDiagnostico());
			pnDiagnosticos.add(getCbMinutosDiagnostico());
			pnDiagnosticos.add(getPnCDiag());
			pnDiagnosticos.add(getTxDiagnosticos());
			pnDiagnosticos.add(getLbAvisoDiagnosticos());
			pnDiagnosticos.add(getLbBuscar());
		}
		return pnDiagnosticos;
	}

	private JButton getBtDiagnostico() {
		if (btDiagnostico == null) {
			btDiagnostico = new JButton("Asignar Diagnostico");
			btDiagnostico.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					getDcFechaDiagnostico().setDate(getDateChooser().getDate());
					mostrarPnDiagnosticos();
				}
			});
			btDiagnostico.setBounds(535, 204, 179, 29);
		}
		return btDiagnostico;
	}

	private JScrollPane getScTree() {
		if (scTree == null) {
			scTree = new JScrollPane();
			scTree.setViewportView(getTree_1());
		}
		return scTree;
	}

	private JTree getTree_1() {
		if (tree == null) {
			DefaultMutableTreeNode root = new DefaultMutableTreeNode("Tab.D");
			DefaultMutableTreeNode node = null;
			for (int i = 1; i < 22; i++) {
				if (i < 10)
					node = new DefaultMutableTreeNode("Cap.0" + i);
				else
					node = new DefaultMutableTreeNode("Cap." + i);
				root.add(node);
			}
			tree = new JTree(root);
			tree.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					if (tree.getSelectionPath() != null) {
						DefaultMutableTreeNode selected = (DefaultMutableTreeNode) tree.getSelectionPath()
								.getLastPathComponent();
						if (selected != null && selected.getUserObject().toString().contains("Cap")) {
							isExpandedCapitulo = true;
							expandirCap();
							capitulo = selected.getUserObject().toString();
						} else if (selected.getUserObject().toString().contains("-")
								&& selected.getUserObject().toString().contains("(")) {
							isExpandedRange = true;
							expandirRange();
						} else if (selected.getUserObject().toString().contains(" - ")
								&& !selected.getUserObject().toString().contains(".")) {
							isExpandedCode = true;
							expandirCode();
						} else if (selected.getUserObject().toString().contains(".")
								&& selected.getUserObject().toString().contains("-")) {
							if (!listaDiagnosticos.contains(selected.getUserObject().toString())) {
								listaDiagnosticos.add(selected.getUserObject().toString());
								String[] diag = new String[listaDiagnosticos.size()];
								for (int i = 0; i < diag.length; i++) {
									diag[i] = listaDiagnosticos.get(i);

								}
								getListDiagnosticos().setModel(new DefaultComboBoxModel<String>(diag));
							}
						}
						//if (!isExpandedCapitulo)
							//expandirTab();
					}
				}
			});
			//tree.setModel(new DefaultTreeModel(crearTree()));
			tree.setRootVisible(false);
			tree.setShowsRootHandles(true);
			listaDiagnosticos = new ArrayList<>();
		}
		return tree;
	}

	private DefaultMutableTreeNode crearTree() {
		return new DefaultMutableTreeNode("Tab.D") {
			{
			}
		};
	}

	private void expandirTab() {
		DefaultMutableTreeNode selected = (DefaultMutableTreeNode) getTree_1().getSelectionPath()
				.getLastPathComponent();
		DefaultMutableTreeNode node = null;
		for (int i = 1; i < 22; i++) {
			if (i < 10)
				node = new DefaultMutableTreeNode("Cap.0" + i);
			else
				node = new DefaultMutableTreeNode("Cap." + i);
			selected.add(node);
		}
		DefaultTreeModel model = (DefaultTreeModel) getTree_1().getModel();
		model.reload();
	}

	private void expandirCap() {
		DefaultMutableTreeNode node = null;
		if (isExpandedCapitulo) {
			DefaultMutableTreeNode selected = (DefaultMutableTreeNode) getTree_1().getSelectionPath()
					.getLastPathComponent();
			List<DiagnosticoDto> capitulos = new ListDiagnosticoByCapAction(selected.getUserObject().toString())
					.execute();
			for (DiagnosticoDto diag : capitulos) {
				// node=expandirRange(diag.descripcion);
				node = new DefaultMutableTreeNode(diag.descripcion);
				selected.add(node);
			}
		} else {
			node = new DefaultMutableTreeNode("");
		}
		DefaultTreeModel model = (DefaultTreeModel) getTree_1().getModel();
		model.reload();
	}

	private void expandirRange() {
		DefaultMutableTreeNode node = null;
		DefaultMutableTreeNode selected = (DefaultMutableTreeNode) getTree_1().getSelectionPath()
				.getLastPathComponent();
		String range = selected.getUserObject().toString().split("\\(")[1].substring(0, 7);
		List<DiagnosticoDto> capitulos = new ListDiagnosticoByRangeAction(range).execute();
		for (DiagnosticoDto diag : capitulos) {
			node = new DefaultMutableTreeNode(diag.codigo + " - " + diag.descripcion);
			selected.add(node);
		}
		DefaultTreeModel model = (DefaultTreeModel) getTree_1().getModel();
		model.reload();
	}

	private void expandirCode() {
		DefaultMutableTreeNode node = null;
		DefaultMutableTreeNode selected = (DefaultMutableTreeNode) getTree_1().getSelectionPath()
				.getLastPathComponent();
		if (isExpandedCode) {
			List<DiagnosticoDto> capitulos = new ListDiagnosticoByCodeAction(
					selected.getUserObject().toString().substring(0, 3)).execute();
			for (DiagnosticoDto diag : capitulos) {
				node = new DefaultMutableTreeNode(diag.codigo + " - " + diag.descripcion);
				selected.add(node);
			}
		}
		DefaultTreeModel model = (DefaultTreeModel) getTree_1().getModel();
		model.reload();
	}

	private JScrollPane getScDiagnostico() {
		if (scDiagnostico == null) {
			scDiagnostico = new JScrollPane();
			scDiagnostico.setBounds(477, 64, 259, 111);
			scDiagnostico.setViewportView(getListDiagnosticos());
		}
		return scDiagnostico;
	}

	private JList<String> getListDiagnosticos() {
		if (listDiagnosticos == null) {
			listDiagnosticos = new JList<String>();
		}
		return listDiagnosticos;
	}

	private JButton getBtEliminar() {
		if (btEliminar == null) {
			btEliminar = new JButton("Eliminar");
			btEliminar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					for (int i : getListDiagnosticos().getSelectedIndices()) {
						listaDiagnosticos.remove(i);
					}
					String[] diag = new String[listaDiagnosticos.size()];
					for (int i = 0; i < diag.length; i++) {
						diag[i] = listaDiagnosticos.get(i);

					}
					getListDiagnosticos().setModel(new DefaultComboBoxModel<String>(diag));
				}
			});
			btEliminar.setBounds(503, 404, 85, 21);
		}
		return btEliminar;
	}

	private JButton getBtAsignarDiagnostico() {
		if (btAsignarDiagnostico == null) {
			btAsignarDiagnostico = new JButton("Asignar");
			btAsignarDiagnostico.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					for (String diag : listaDiagnosticos) {

						Timestamp d1 = new Timestamp(getDcFechaDiagnostico().getDate().getTime());
						String i = d1.toString().split(" ")[0] + " " + getCbHoraDiagnostico().getSelectedIndex() + ":"
								+ getCbMinutosDiagnostico().getSelectedIndex() + ":00";
						Timestamp inicio = Timestamp.valueOf(i);
						new AddDiagnosticoAction(cita.idPaciente, inicio, diag, idCita, "Si",
								getTxObservaciones().getText()).execute();
						FileUtil.escribirLog("MiLogger", "Medico ID: 1" + "null -> " + diag);
						getLbAvisoDiagnosticos().setText("Diagnosticos asigandos correctamente");

					}
				}
			});
			btAsignarDiagnostico.setBounds(651, 404, 85, 21);
		}
		return btAsignarDiagnostico;
	}

	private JLabel getLbCie10() {
		if (lbCie10 == null) {
			lbCie10 = new JLabel("Cie10 - Diagnosticos");
			lbCie10.setBounds(22, 89, 154, 13);
		}
		return lbCie10;
	}

	private JLabel getLbDiagnostico() {
		if (lbDiagnostico == null) {
			lbDiagnostico = new JLabel("Diagnostico:");
			lbDiagnostico.setBounds(477, 41, 111, 13);
		}
		return lbDiagnostico;
	}

	private JButton getBtAtrasDiagnostico() {
		if (btAtrasDiagnostico == null) {
			btAtrasDiagnostico = new JButton("Atras");
			btAtrasDiagnostico.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					mostrarPnCita();
					getLbAvisoDiagnosticos().setText("");
				}
			});
			btAtrasDiagnostico.setBounds(47, 404, 85, 21);
		}
		return btAtrasDiagnostico;
	}

	private JScrollPane getScObservaciones() {
		if (scObservaciones == null) {
			scObservaciones = new JScrollPane();
			scObservaciones.setBounds(477, 233, 316, 111);
			scObservaciones.setViewportView(getTxObservaciones());
		}
		return scObservaciones;
	}

	private JLabel getLbObservaciones() {
		if (lbObservaciones == null) {
			lbObservaciones = new JLabel("Observaciones");
			lbObservaciones.setBounds(477, 210, 139, 13);
		}
		return lbObservaciones;
	}

	private JTextArea getTxObservaciones() {
		if (txObservaciones == null) {
			txObservaciones = new JTextArea();
		}
		return txObservaciones;
	}

	private JDateChooser getDcFechaDiagnostico() {
		if (dcFechaDiagnostico == null) {
			dcFechaDiagnostico = new JDateChooser();
			dcFechaDiagnostico.setBounds(775, 64, 109, 26);
		}
		return dcFechaDiagnostico;
	}

	private JLabel getLbFechaDiagnostico() {
		if (lbFechaDiagnostico == null) {
			lbFechaDiagnostico = new JLabel("Fecha:");
			lbFechaDiagnostico.setBounds(773, 41, 45, 13);
		}
		return lbFechaDiagnostico;
	}

	private JComboBox<Integer> getCbHoraDiagnostico() {
		if (cbHoraDiagnostico == null) {
			cbHoraDiagnostico = new JComboBox<Integer>();
			Integer[] h = new Integer[24];
			for (int i = 0; i < h.length; i++) {
				h[i] = i;
			}
			cbHoraDiagnostico.setBounds(775, 112, 85, 21);
			cbHoraDiagnostico.setModel(new DefaultComboBoxModel<Integer>(h));
		}
		return cbHoraDiagnostico;
	}

	private JComboBox<Integer> getCbMinutosDiagnostico() {
		if (cbMinutosDiagnostico == null) {
			cbMinutosDiagnostico = new JComboBox();
			Integer[] m = new Integer[60];
			for (int j = 0; j < m.length; j++) {
				m[j] = j;
			}
			cbMinutosDiagnostico.setBounds(775, 143, 85, 21);
			cbMinutosDiagnostico.setModel(new DefaultComboBoxModel<Integer>(m));
		}
		return cbMinutosDiagnostico;
	}

	private JButton getBtSeguimiento() {
		if (btSeguimiento == null) {
			btSeguimiento = new JButton("Seguimiento enfermedad");
			btSeguimiento.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					mostrarVentanaSeguimiento();
				}
			});
			btSeguimiento.setBounds(30, 254, 205, 29);
		}
		return btSeguimiento;
	}

	private JPanel getPnBotonesH() {
		if (pnBotonesH == null) {
			pnBotonesH = new JPanel();
			pnBotonesH.setLayout(new GridLayout(0, 3, 0, 0));
			pnBotonesH.add(getChDetalles());
			pnBotonesH.add(getChFecha());
			pnBotonesH.add(getDcHistorial());
		}
		return pnBotonesH;
	}

	private JCheckBox getChDetalles() {
		if (chDetalles == null) {
			chDetalles = new JCheckBox("Mas detalles");
			chDetalles.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					filtrarHistorial();
				}
			});
		}
		return chDetalles;
	}

	private void filtrarHistorial() {
		List<DiagnosticoDto> diagnosticos = new ListDiagnosticoByIdAction(idPaciente).execute();
		if (getChFecha().isSelected() && getDcHistorial().getDate() != null) {
			if (getChDetalles().isSelected()) {
				String d = "";
				String fecha = "";
				for (DiagnosticoDto diag : diagnosticos) {
					Timestamp m = Timestamp.from(getDcHistorial().getDate().toInstant());
					String string = m.toString().split(" ")[0];
					if (Date.valueOf(string).compareTo(Date.valueOf(diag.fecha.toString().split(" ")[0])) == 0) {
						if (fecha.isBlank() || !fecha.equals(diag.fecha.toString().substring(0, 16))) {
							fecha = diag.fecha.toString().substring(0, 16);
							d += "Fecha de la visita: " + diag.fecha.toString().substring(0, 16) + "\n";
						}
						d += "\t-Diagnostico: " + diag.diagnostico;
						if (diag.prescripcion != null)
							d += "\n\t\t-Prescripcion de la visita: " + diag.prescripcion;
						else
							d += "\n\t\t-Prescripcion de la visita: No hay prescripcion asociada";
						if(diag.descripcion.isEmpty())
							d += "\n\t\t-Observaciones: No hay ninguna observaci??n";
						else
							d += "\n\t\t-Observaciones: " + diag.descripcion;
						if (diag.status.equals("Si"))
							d += "\n\t\t-Estado: Enfermedad activa\n";
						else if (diag.status.equals("No"))
							d += "\n\t\t-Estado: Enfermedad finalizada\n";
						getTxHistorial().setText(d);
					}
				}
			} else {
				String d = "";
				String fecha = "";
				for (DiagnosticoDto diag : diagnosticos) {
					Timestamp m = Timestamp.from(getDcHistorial().getDate().toInstant());
					String string = m.toString().split(" ")[0];
					if (Date.valueOf(string).compareTo(Date.valueOf(diag.fecha.toString().split(" ")[0])) == 0) {
						if (fecha.isBlank() || !fecha.equals(diag.fecha.toString().substring(0, 16))) {
							fecha = diag.fecha.toString().substring(0, 16);
							d += "Fecha de la visita: " + diag.fecha.toString().substring(0, 16) + "\n";
						}
						d += "\t-Diagnostico: " + diag.diagnostico + "\n";
					}
				}
				getTxHistorial().setText(d);
			}
		} else {

			if (getChDetalles().isSelected()) {
				String d = "";
				String fecha = "";
				for (DiagnosticoDto diag : diagnosticos) {
					if (fecha.isBlank() || !fecha.equals(diag.fecha.toString().substring(0, 16))) {
						fecha = diag.fecha.toString().substring(0, 16);
						d += "Fecha de la visita: " + diag.fecha.toString().substring(0, 16) + "\n";
					}
					d += "\t-Diagnostico: " + diag.diagnostico;
					if (diag.prescripcion != null)
						d += "\n\t\t-Prescripcion de la visita: " + diag.prescripcion;
					else
						d += "\n\t\t-Prescripcion de la visita: No hay prescripcion asociada";
					if(diag.descripcion.isEmpty())
						d += "\n\t\t-Observaciones: No hay ninguna observaci??n";
					else
						d += "\n\t\t-Observaciones: " + diag.descripcion;
					if (diag.status.equals("Si"))
						d += "\n\t\t-Estado: Enfermedad activa\n";
					else if (diag.status.equals("No"))
						d += "\n\t\t-Estado: Enfermedad finalizada\n";
					getTxHistorial().setText(d);
				}
			} else {
				String d = "";
				String fecha = "";
				for (DiagnosticoDto diag : diagnosticos) {
					if (fecha.isBlank() || !fecha.equals(diag.fecha.toString().substring(0, 16))) {
						fecha = diag.fecha.toString().substring(0, 16);
						d += "Fecha de la visita: " + diag.fecha.toString().substring(0, 16) + "\n";
					}
					d += "\t-Diagnostico: " + diag.diagnostico + "\n";
				}
				getTxHistorial().setText(d);
			}

		}

	}

	private JDateChooser getDcHistorial() {
		if (dcHistorial == null) {
			dcHistorial = new JDateChooser();
			dcHistorial.addPropertyChangeListener(new PropertyChangeListener() {
				public void propertyChange(PropertyChangeEvent evt) {
					filtrarHistorial();
				}
			});
		}
		return dcHistorial;
	}

	private JPanel getPnNorte() {
		if (pnNorte == null) {
			pnNorte = new JPanel();
			pnNorte.setLayout(new GridLayout(0, 2, 0, 0));
			pnNorte.add(getPnBotonesH());
			pnNorte.add(getPnBotonesV());
		}
		return pnNorte;
	}

	private JPanel getPnBotonesV() {
		if (pnBotonesV == null) {
			pnBotonesV = new JPanel();
			pnBotonesV.setLayout(new GridLayout(0, 2, 0, 0));
			pnBotonesV.add(getChFechaV());
			pnBotonesV.add(getDcVacuna());
		}
		return pnBotonesV;
	}

	private JDateChooser getDcVacuna() {
		if (dcVacuna == null) {
			dcVacuna = new JDateChooser();
			dcVacuna.addPropertyChangeListener(new PropertyChangeListener() {
				public void propertyChange(PropertyChangeEvent evt) {
					mostrarVacunas();
				}
			});
		}
		return dcVacuna;
	}

	private JCheckBox getChFecha() {
		if (chFecha == null) {
			chFecha = new JCheckBox("Filtrar por fecha");
			chFecha.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					filtrarHistorial();
				}
			});
		}
		return chFecha;
	}

	private JCheckBox getChFechaV() {
		if (chFechaV == null) {
			chFechaV = new JCheckBox("Filtrar por fecha");
			chFechaV.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					mostrarVacunas();
				}
			});
		}
		return chFechaV;
	}

	private JButton getBtVerSolicitudes() {
		if (btVerSolicitudes == null) {
			btVerSolicitudes = new JButton("Ver Solicitudes");
			btVerSolicitudes.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					actualizarSolicitudes();
					mostrarPnVerSolicitudes();
				}
			});
		}
		return btVerSolicitudes;
	}

	private void mostrarPnVerSolicitudes() {
		CardLayout c = (CardLayout) getPnContenidos().getLayout();
		c.show(getPnContenidos(), "PnVerSolicitudes");

	}

	private JPanel getPnVerSolicitudes() {
		if (pnVerSolicitudes == null) {
			pnVerSolicitudes = new JPanel();
			pnVerSolicitudes.setLayout(new BorderLayout(0, 0));
			pnVerSolicitudes.add(getPnBotonesSolicitudes(), BorderLayout.SOUTH);
			pnVerSolicitudes.add(getScllPnSolicitudes(), BorderLayout.CENTER);

		}
		return pnVerSolicitudes;
	}

	private JPanel getPnBotonesSolicitudes() {
		if (pnBotonesSolicitudes == null) {
			pnBotonesSolicitudes = new JPanel();
			pnBotonesSolicitudes.add(getBtAceptarSolicitud());
			pnBotonesSolicitudes.add(getBtDenegarSolicitud());
			pnBotonesSolicitudes.add(getBtSalir());
		}
		return pnBotonesSolicitudes;
	}

	private JScrollPane getScllPnSolicitudes() {
		if (scllPnSolicitudes == null) {
			scllPnSolicitudes = new JScrollPane();
			scllPnSolicitudes.setViewportView(getListSolicitudes());
		}
		return scllPnSolicitudes;
	}

	// aceptar
	private JButton getBtAceptarSolicitud() {
		if (btAceptarSolicitud == null) {
			btAceptarSolicitud = new JButton("Aceptar");
			btAceptarSolicitud.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					// actualizarSolicitudes();
					SolicitudDto solicitud = solicitudes.get(getListSolicitudes().getSelectedIndex());
					if (solicitud.tipo.equals("CREAR")) {
						aceptarSolicitudCrear(solicitud);
					} else if (solicitud.tipo.equals("MODIFICAR")) {
						aceptarSolicitudModificar(solicitud);
					} else if (solicitud.tipo.equals("ELIMINAR")) {
						Integer respuesta = JOptionPane.showConfirmDialog(null,
								"???Estas seguro de borrar la cita " + solicitud.cuerpo + "?");
						if (respuesta == JOptionPane.YES_OPTION) {
							new DeleteSolicitud(solicitud.id).execute();
							new DeleteCita(solicitud.cuerpo).execute();
							borrarResiduos(solicitud.cuerpo);
						}
					}
					actualizarSolicitudes();
				}
			});
			btAceptarSolicitud.setEnabled(false);
		}
		return btAceptarSolicitud;
	}

	public void aceptarSolicitudCrear(SolicitudDto solicitud) {
		String cuerpo = solicitud.cuerpo;
		String[] partes = cuerpo.split("#");
		mostrarVentanaCrearSolicitud(partes, solicitud.id);
	}

	private void mostrarVentanaCrearSolicitud(String[] partes, String idSol) {
		VentanaCrearSolicitud citas = new VentanaCrearSolicitud(this, partes, idSol);
		citas.setLocationRelativeTo(this);
		citas.setModal(true);
		citas.setVisible(true);
	}

	private void aceptarSolicitudModificar(SolicitudDto solicitud) {
		getBtMAtras().setEnabled(false);
		getBtMAtras().setVisible(false);
		getBtCancelarMod().setVisible(false);
		getBtCancelarMod().setEnabled(false);
		getBtSolicitudMod().setEnabled(false);
		getBtSolicitudMod().setVisible(false);
		getBtElegirMedico().setEnabled(true);
		getBtElegirMedico().setVisible(true);

		new DeleteSolicitud(solicitud.id).execute();

		String cuerpo = solicitud.cuerpo;
		String[] partes = cuerpo.split("#");
		MedicoDto med = new ListAllMedicoById(Integer.parseInt(partes[0])).execute();

		String[] horasE = partes[2].split(":");
		String[] horasS = partes[3].split(":");

		String infoCita = "Sala de la cita: " + partes[0] + "\n Medico de la cita: " + med.name + " " + med.surname
				+ "\n Hora de inicio: " + partes[2] + "\n Hora de salida: " + partes[3]
				+ "\n Modtivo de la cita: No determinados";
		getTxInfoCita().setText(infoCita); // informacion cita
		getListaElegirMedico().setSelectedIndex(Integer.parseInt(partes[0])); // medico

		getCbMHoraInicio().setSelectedIndex(Integer.parseInt(horasE[0])); // hora inicio
		getCbMMinutosInicio().setSelectedIndex(Integer.parseInt(horasE[1]));

		getCbMHoraFin().setSelectedIndex(Integer.parseInt(horasS[0])); // hora fin
		getCbMMinutosFin().setSelectedIndex(Integer.parseInt(horasS[1]));

		getCbMSala().setSelectedIndex(Integer.parseInt(partes[4]) - 1);

		String[] partesFecha = partes[1].split("-");
		int[] fecha = new int[3];
		fecha[0] = Integer.parseInt(partesFecha[0]);// dia
		fecha[1] = Integer.parseInt(partesFecha[1]);// mes
		fecha[2] = Integer.parseInt(partesFecha[2]);// a???o

		Calendar cal = Calendar.getInstance();
		cal.set(fecha[2], fecha[1] - 1, fecha[0]);

		getDcModificarFecha().setCalendar(cal); // fecha

		getTxtSolicitudMod().setText(solicitud.observaciones);
		cita = new ListCitaByIdAction(Integer.parseInt(partes[5])).execute();
		mostrarPnModificarCita();

	}

	// denegar
	private JButton getBtDenegarSolicitud() {
		if (btDenegarSolicitud == null) {
			btDenegarSolicitud = new JButton("Denegar");
			btDenegarSolicitud.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {

					SolicitudDto solicitud = solicitudes.get(getListSolicitudes().getSelectedIndex());
					Integer respuesta = JOptionPane.showConfirmDialog(null,
							"???Estas seguro de denegar la solictud " + solicitud.id + "?");
					if (respuesta == JOptionPane.YES_OPTION) {
						new DeleteSolicitud(solicitud.id).execute();
						actualizarSolicitudes();
						btDenegarSolicitud.setEnabled(false);
						getBtAceptarSolicitud().setEnabled(false);
					}
				}
			});
			btDenegarSolicitud.setEnabled(false);
		}
		return btDenegarSolicitud;
	}

	private JList<String> getListSolicitudes() {
		if (listSolicitudes == null) {
			listSolicitudes = new JList<String>();

			listSolicitudes.setBorder(
					new TitledBorder(null, "Solicitudes", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			solicitudes = new ListAllSolicitudes().execute();
			actualizarSolicitudes();

			listSolicitudes.addListSelectionListener(new ListSelectionListener() {
				public void valueChanged(ListSelectionEvent e) {
					getBtAceptarSolicitud().setEnabled(true);
					getBtDenegarSolicitud().setEnabled(true);
				}
			});
		}
		return listSolicitudes;
	}

	private void actualizarSolicitudes() {
		solicitudes = new ListAllSolicitudes().execute();
		String[] sols = new String[solicitudes.size()];
		for (int i = 0; i < solicitudes.size(); i++) {
			SolicitudDto sol = solicitudes.get(i);
			sols[i] = sol.id + " - " + sol.tipo + " - " + sol.cuerpo + " - " + sol.observaciones;
		}
		ListModel<String> model = new DefaultComboBoxModel<String>(sols);
		getListSolicitudes().setModel(model);
	}

	private void borrarResiduos(String idCita) {
		for (SolicitudDto solicitud : solicitudes) {
			if (solicitud.cuerpo.equals(idCita))
				new DeleteSolicitud(solicitud.id).execute();
		}
	}

	private JButton getBtSalir() {
		if (btSalir == null) {
			btSalir = new JButton("Salir");
			btSalir.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					mostrarPanelAdministrador();
				}
			});
		}
		return btSalir;
	}

	private JButton getBtCancelarMod() {
		if (btCancelarMod == null) {
			btCancelarMod = new JButton("Cancelar");
			btCancelarMod.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					mostrarPnMedico();
				}
			});
			btCancelarMod.setBounds(28, 398, 117, 29);
		}
		return btCancelarMod;
	}

	private JButton getBtSalirSolMod() {
		if (btSalirSolMod == null) {
			btSalirSolMod = new JButton("Salir");
			btSalirSolMod.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					mostrarPnVerSolicitudes();
				}
			});
			btSalirSolMod.setBounds(160, 401, 89, 23);
		}
		return btSalirSolMod;
	}

	private JPanel getPnCDiag() {
		if (pnCDiag == null) {
			pnCDiag = new JPanel();
			pnCDiag.setBounds(22, 112, 418, 277);
			pnCDiag.setLayout(new CardLayout(0, 0));
			pnCDiag.add(getPanel_2_2(), "PnTree");
			pnCDiag.add(getPanel_2_3(), "PnBuscar");
		}
		return pnCDiag;
	}

	private JPanel getPanel_2_2() {
		if (pnTree == null) {
			pnTree = new JPanel();
			pnTree.setLayout(new GridLayout(0, 1, 0, 0));
			pnTree.add(getScTree());
		}
		return pnTree;
	}

	private JPanel getPanel_2_3() {
		if (pnBuscar == null) {
			pnBuscar = new JPanel();
			pnBuscar.setLayout(new BorderLayout(0, 0));
			pnBuscar.add(getScrollPane_1());
			pnBuscar.add(getBtA??adirDiagnostico(), BorderLayout.SOUTH);
		}
		return pnBuscar;
	}

	private JTextField getTxDiagnosticos() {
		if (txDiagnosticos == null) {
			txDiagnosticos = new JTextField();
			txDiagnosticos.addKeyListener(new KeyAdapter() {
				@Override
				public void keyReleased(KeyEvent e) {
					if (!getTxDiagnosticos().getText().isBlank()) {
						mostrarPnBuscar();
						String[] diag = diagnosticosToString(
								filtrarListaDiagnosticos(listaDiagnosticosCode, getTxDiagnosticos().getText()));
						ListModel<String> model = new DefaultComboBoxModel<String>(diag);
						getListAllDiagnosticos().setModel(model);
					} else
						mostrarPnTree();
				}
			});
			txDiagnosticos.setBounds(22, 44, 418, 35);
			txDiagnosticos.setColumns(10);
		}
		return txDiagnosticos;
	}

	private JScrollPane getScrollPane_1() {
		if (scrollPane_1 == null) {
			scrollPane_1 = new JScrollPane();
			scrollPane_1.setViewportView(getListAllDiagnosticos());
		}
		return scrollPane_1;
	}

	private JList<String> getListAllDiagnosticos() {
		if (listAllDiagnosticos == null) {
			listAllDiagnosticos = new JList<>();
		}
		return listAllDiagnosticos;
	}

	private List<DiagnosticoDto> filtrarListaDiagnosticos(List<DiagnosticoDto> medi, String start) {
		List<DiagnosticoDto> listaFiltrada = new ArrayList<DiagnosticoDto>();
		for (DiagnosticoDto m : medi) {
			// Filtro por codigo
			if (m.codigo.toUpperCase().startsWith(start.toUpperCase())) {
				listaFiltrada.add(m);
			}
			// Filtro por Diagnostico
			else if (m.diagnostico.toUpperCase().startsWith(start.toUpperCase())) {
				listaFiltrada.add(m);
			}
		}
		return listaFiltrada;

	}

	private String[] diagnosticosToString(List<DiagnosticoDto> medico) {
		String[] strMedicos = new String[medico.size()];
		for (int i = 0; i < medico.size(); i++) {
			strMedicos[i] = medico.get(i).codigo + " - " + medico.get(i).diagnostico;
		}
		return strMedicos;
	}

	private JButton getBtA??adirDiagnostico() {
		if (btA??adirDiagnostico == null) {
			btA??adirDiagnostico = new JButton("A??adir");
			btA??adirDiagnostico.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					for (String vacuna : getListAllDiagnosticos().getSelectedValuesList()) {
						if (!listaDiagnosticos.contains(vacuna))
							listaDiagnosticos.add(vacuna);
					}
					String[] strMedicos = new String[listaDiagnosticos.size()];
					for (int i = 0; i < listaDiagnosticos.size(); i++) {
						strMedicos[i] = listaDiagnosticos.get(i);
					}
					getListDiagnosticos().setModel(new DefaultComboBoxModel<String>(strMedicos));
				}
			});
		}
		return btA??adirDiagnostico;
	}
	private JLabel getLbAvisoDiagnosticos() {
		if (lbAvisoDiagnosticos == null) {
			lbAvisoDiagnosticos = new JLabel("");
			lbAvisoDiagnosticos.setBounds(487, 356, 306, 21);
		}
		return lbAvisoDiagnosticos;
	}
	private JLabel getLbBuscar() {
		if (lbBuscar == null) {
			lbBuscar = new JLabel("Buscar");
			lbBuscar.setBounds(22, 21, 97, 13);
		}
		return lbBuscar;
	}
	private JLabel getLbMedicos() {
		if (lbMedicos == null) {
			lbMedicos = new JLabel("Medicos");
			lbMedicos.setBounds(49, 147, 79, 13);
		}
		return lbMedicos;
	}
	private JLabel getLbInfoMod() {
		if (lbInfoMod == null) {
			lbInfoMod = new JLabel("Informaci??n");
			lbInfoMod.setBounds(49, 20, 151, 13);
		}
		return lbInfoMod;
	}
	private JLabel getLbFechaMod() {
		if (lbFechaMod == null) {
			lbFechaMod = new JLabel("Fecha:");
			lbFechaMod.setBounds(425, 172, 61, 13);
		}
		return lbFechaMod;
	}
}
