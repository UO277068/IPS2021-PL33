package igu.Ventanas;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.toedter.calendar.JDateChooser;

import Logica.crud.dto.CitaDto;
import Logica.crud.dto.MedicoDto;
import Logica.crud.dto.PacienteDto;
import igu.action.AddHorarioAction;
import igu.action.ListAllCitasByIdAction;
import igu.action.ListAllMedicosAction;
import igu.action.ListAllPacientesAction;
import igu.action.ListDiagnosticoByIdAction;
import igu.action.ListVacunaByIdAction;
import igu.action.UpdateAcudioCitaAction;
import igu.action.UpdateHoraEntradaSalidaAction;




public class VentanaPrincipal extends JFrame {

	List<PacienteDto> listapacientes;
	List<MedicoDto> listamedicos;
	private int changeWindow=1;
	private String acude = "INDEFINIDO";
	
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel pnMedico;
	private JButton btContinuar;
	private JPanel pnCita;
	private JLabel lbEntrada;
	private JTextField txEntrada;
	private JLabel lbSalida;
	private JTextField txSalida;
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
	private JComboBox<String> cbMedicos;
	private JLabel lbMedicos;
	private JLabel lbPacientes;
	private JComboBox<String> cbPacientes;
	private JButton btHistorialA;
	private JButton btAtrasA;
	private JButton btContacto;
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
	public VentanaPrincipal() 
	{
		//Atributos
		this.listamedicos=new ListAllMedicosAction().execute();
		this.listapacientes=new ListAllPacientesAction().execute();
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
		}
		return pnMedico;
	}
	private JButton getBtContinuar() {
		if (btContinuar == null) {
			btContinuar = new JButton("Continuar");
			btContinuar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					idPaciente=listaFiltrada.get(getListCitas().getSelectedIndex()).idPaciente;
					idCita=listaFiltrada.get(getListCitas().getSelectedIndex()).id;

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
			pnCita.add(getTxEntrada());
			pnCita.add(getLbSalida());
			pnCita.add(getTxSalida());
			pnCita.add(getBtAsignar());
			pnCita.add(getBtnAtrasM());
			pnCita.add(getBtCausa());
			pnCita.add(getBtPrescripcion());
			
			JPanel pnAcudeCita = new JPanel();
			pnAcudeCita.setBorder(new TitledBorder(null, "Acude el paciente a la cita", TitledBorder.LEADING, TitledBorder.TOP, null, null));
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
		}
		return pnCita;
	}
	private void mostrarPnCita() {
		CardLayout c = (CardLayout)getPnContenidos().getLayout();
		c.show(getPnContenidos(), "PnCita");		
	}
	
	private void mostrarPnHorario() {
		CardLayout c = (CardLayout)getPnContenidos().getLayout();
		c.show(getPnContenidos(), "PnHorario");	
		
	}
	
	private void mostrarPnHistorial(){
		CardLayout c = (CardLayout)getPnContenidos().getLayout();
		c.show(getPnContenidos(), "PnHistorial");
		String d = new ListDiagnosticoByIdAction(idPaciente).execute();
		getTxHistorial().setText(d);
		String v = new ListVacunaByIdAction(idPaciente).execute();
		getTxVacuna().setText(v);
	}
	private JLabel getLbEntrada() {
		if (lbEntrada == null) {
			lbEntrada = new JLabel("Hora de entrada");
			lbEntrada.setBounds(41, 57, 106, 16);
		}
		return lbEntrada;
	}
	private JTextField getTxEntrada() {
		if (txEntrada == null) {
			txEntrada = new JTextField();
			txEntrada.setBounds(146, 52, 130, 26);
			txEntrada.setColumns(10);
		}
		return txEntrada;
	}
	private JLabel getLbSalida() {
		if (lbSalida == null) {
			lbSalida = new JLabel("Hora de salida");
			lbSalida.setBounds(41, 100, 106, 16);
		}
		return lbSalida;
	}
	private JTextField getTxSalida() {
		if (txSalida == null) {
			txSalida = new JTextField();
			txSalida.setBounds(146, 100, 130, 26);
			txSalida.setColumns(10);
		}
		return txSalida;
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
		}
		return pnContenidos;
	}
	
	private void mostrarPnMedico() {
		CardLayout c = (CardLayout)getPnContenidos().getLayout();
		c.show(getPnContenidos(), "PnMedico");
	}
	private JButton getBtHistorial() {
		if (btHistorial == null) {
			btHistorial = new JButton("Ver historial");
			btHistorial.setBounds(19, 397, 117, 29);
			btHistorial.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						changeWindow=1;
						idPaciente=listaFiltrada.get(getListCitas().getSelectedIndex()).idPaciente;
						idCita=listaFiltrada.get(getListCitas().getSelectedIndex()).id;
						if(getListCitas().getSelectedValue()!=null)
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
		}
		return pnHistorial;
	}
	private JScrollPane getScHistorial() {
		if (scHistorial == null) {
			scHistorial = new JScrollPane();
			scHistorial.setViewportBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "Diagnosticos", TitledBorder.LEFT, TitledBorder.TOP, null, new Color(0, 0, 0)));
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
					//List<CitaDto> lista = new ListAllCitasByIdAction(idMedico).execute();
					String horaInicio=listaFiltrada.get(getListCitas().getSelectedIndex()).horaInicio;
					new UpdateHoraEntradaSalidaAction(idCita, getTxEntrada().getText(),getTxSalida().getText(),horaInicio).execute();
				}
			});
			btAsignar.setBounds(30, 140, 117, 29);
		}
		return btAsignar;
	}
	private JScrollPane getScVacuna() {
		if (scVacuna == null) {
			scVacuna = new JScrollPane();
			scVacuna.setViewportBorder(new TitledBorder(null, "Vacuna", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			scVacuna.setViewportView(getTxVacuna());
		}
		return scVacuna;
	}
	private JTextArea getTxVacuna() {
		if (txVacuna == null) {
			txVacuna = new JTextArea();
		}
		return txVacuna;
	}
	private JPanel getPnEleccion() {
		if (pnEleccion == null) {
			pnEleccion = new JPanel();
			pnEleccion.setLayout(null);
			pnEleccion.add(getBtAdministrador());
			pnEleccion.add(getBtMedico());
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
			btAdministrador.setBounds(239, 144, 117, 29);
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
			btMedico.setBounds(402, 144, 117, 29);
		}
		return btMedico;
	}
	private JPanel getPnHorario() {
		if (pnHorario == null) {
			pnHorario = new JPanel();
			pnHorario.setLayout(null);
			pnHorario.add(getBtAsignarHorario());
			pnHorario.add(getScrollPane());
			pnHorario.add(getDcDesde());
			pnHorario.add(getDcHasta());
			pnHorario.add(getLbDesde());
			pnHorario.add(getLbHasta());
			pnHorario.add(getCbHoraD());
			pnHorario.add(getCbMinutosD());
			pnHorario.add(getCbHoraH());
			pnHorario.add(getCbMinutosH());
			pnHorario.add(getCbOpcion());
			pnHorario.add(getBtnAtras());
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
//					d1.setHours((int)getCbHoraD().getSelectedItem());
//					d1.setMinutes((int)getCbMinutosD().getSelectedItem());
//					d2.setHours((int)getCbHoraH().getSelectedItem());
//					d2.setMinutes((int)getCbMinutosH().getSelectedItem());
					new AddHorarioAction(d1, d2,getCbOpcion().getSelectedIndex(), getList().getSelectedIndices()).execute();
				}
			});
			btAsignarHorario.setBounds(344, 276, 143, 29);
		}
		return btAsignarHorario;
	}
	private JScrollPane getScrollPane() {
		if (scrollPane == null) {
			scrollPane = new JScrollPane();
			scrollPane.setBounds(90, 35, 304, 73);
			scrollPane.setViewportView(getList());
		}
		return scrollPane;
	}
	private JList<String> getList() {
		if (list == null) {
			List<MedicoDto> lista = new ListAllMedicosAction().execute();
			medicos = new DefaultListModel<String>();
			for (int i = 0; i < lista.size(); i++) {
				medicos.addElement(lista.get(i).name+" "+lista.get(i).surname);
			}
			list = new JList<String>(medicos);
			list.setModel(medicos);
		}
		return list;
	}
	private JButton getBtHAtras() {
		if (btHAtras == null) {
			btHAtras = new JButton("Atras");
			btHAtras.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) 
				{
					if(changeWindow==1) {
					mostrarPnMedico();
					}else {
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
		}
		return pnBt;
	}
	private JDateChooser getDcDesde() {
		if (dcDesde == null) {
			dcDesde = new JDateChooser();
			dcDesde.setBounds(149, 148, 165, 26);
		}
		return dcDesde;
	}
	private JDateChooser getDcHasta() {
		if (dcHasta == null) {
			dcHasta = new JDateChooser();
			dcHasta.setBounds(149, 197, 165, 26);
		}
		return dcHasta;
	}
	private JLabel getLbDesde() {
		if (lbDesde == null) {
			lbDesde = new JLabel("Desde:");
			lbDesde.setBounds(90, 158, 61, 16);
		}
		return lbDesde;
	}
	private JLabel getLbHasta() {
		if (lbHasta == null) {
			lbHasta = new JLabel("Hasta:");
			lbHasta.setBounds(90, 207, 61, 16);
		}
		return lbHasta;
	}
	private JComboBox<Integer> getCbHoraD() {
		if (cbHoraD == null) {
			Integer[] h= new Integer[24];
			for (int i = 0; i < h.length; i++) {
				h[i]=i;
			}
			cbHoraD = new JComboBox<Integer>();
			cbHoraD.setBounds(342, 147, 72, 27);
			cbHoraD.setModel(new DefaultComboBoxModel<Integer>(h));
		}
		return cbHoraD;
	}
	private JComboBox<Integer> getCbMinutosD() {
		if (cbMinutosD == null) {
			Integer[] m= new Integer[60];
			for (int j = 0; j < m.length; j++) {
				m[j]=j;
			}
			cbMinutosD = new JComboBox<Integer>();
			cbMinutosD.setBounds(415, 148, 72, 27);
			cbMinutosD.setModel(new DefaultComboBoxModel<Integer>(m));
		}
		return cbMinutosD;
	}
	private JComboBox<Integer> getCbHoraH() {
		if (cbHoraH == null) {
			Integer[] h= new Integer[24];
			for (int i = 0; i < h.length; i++) {
				h[i]=i;
			}
			cbHoraH = new JComboBox<Integer>();
			cbHoraH.setBounds(342, 197, 72, 27);
			cbHoraH.setModel(new DefaultComboBoxModel<Integer>(h));
		}
		return cbHoraH;
	}
	private JComboBox<Integer> getCbMinutosH() {
		if (cbMinutosH == null) {
			Integer[] m= new Integer[60];
			for (int j = 0; j < m.length; j++) {
				m[j]=j;
			}
			cbMinutosH = new JComboBox<Integer>();
			cbMinutosH.setBounds(415, 196, 72, 27);
			cbMinutosH.setModel(new DefaultComboBoxModel<Integer>(m));
		}
		return cbMinutosH;
	}
	private JComboBox<String> getCbOpcion() {
		if (cbOpcion == null) {
			cbOpcion = new JComboBox<String>();
			cbOpcion.setModel(new DefaultComboBoxModel<String>(new String[] {"Seleccionar opcion", "Todos los dias", "Cada 7 dias"}));
			cbOpcion.setBounds(90, 277, 193, 26);
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
		}
		return panelBotonesPrincipal;
	}
	private JButton getBtnHistorial() {
		if (btnHistorial == null) {
			btnHistorial = new JButton("Ver historial de pacientes");
			btnHistorial.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) 
				{
					changeWindow=2;
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
				public void actionPerformed(ActionEvent e) 
				{
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
				public void actionPerformed(ActionEvent e) 
				{
					
					mostrarVentanaCita();
				}
			});
		}
		return btnCrearCita;
	}
	
	//Metodos
	private void mostrarVentanaCita() 
	{
		VentanaCreaCitas citas = new VentanaCreaCitas(this);
		citas.setLocationRelativeTo(this);
		citas.setModal(true);
		citas.setVisible(true);
	}
	

	private void mostrarPanelAdministrador() 
	{
		CardLayout c = (CardLayout)getPnContenidos().getLayout();
		c.show(getPnContenidos(), "PnAdministrador");
	}
	
	private void mostrarPnPrincipal() 
	{
		CardLayout c = (CardLayout)getPnContenidos().getLayout();
		c.show(getPnContenidos(), "PnEleccion");
		
	}
	
	private JButton getBtnAtras() {
		if (btnAtras == null) {
			btnAtras = new JButton("Atras");
			btnAtras.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) 
				{
					mostrarPanelAdministrador();
				}
			});
			btnAtras.setBounds(45, 399, 89, 23);
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
				public void actionPerformed(ActionEvent e) 
				{
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
				public void actionPerformed(ActionEvent e) 
				{
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
				public void actionPerformed(ActionEvent e) 
				{
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
			pnVerCitas.setLayout(null);
			pnVerCitas.add(getCbMedicos());
			pnVerCitas.add(getLbMedicos());
			pnVerCitas.add(getLbPacientes());
			pnVerCitas.add(getCbPacientes());
			pnVerCitas.add(getBtHistorialA());
			pnVerCitas.add(getBtAtrasA());
			pnVerCitas.add(getBtContacto());
		}
		return pnVerCitas;
	}
	private JComboBox<String> getCbMedicos() {
		if (cbMedicos == null) {
			String[] medicos = new String[listamedicos.size()];
			for (int i = 0; i < medicos.length; i++) {
				
				medicos[i]=listamedicos.get(i).name+" "+listamedicos.get(i).surname;
			}
			cbMedicos = new JComboBox<String>();
			cbMedicos.addItemListener(new ItemListener() {
				public void itemStateChanged(ItemEvent e) {
					idMedico=cbMedicos.getSelectedIndex()+1;
					getCbPacientes().setModel(new DefaultComboBoxModel<String>(getPacientesCita(new ListAllCitasByIdAction(idMedico).execute())));
				}
			});
			idMedico=cbMedicos.getSelectedIndex()+1;
			cbMedicos.setBounds(139, 113, 225, 27);
			cbMedicos.setModel(new DefaultComboBoxModel<String>((medicos)));
		}
		return cbMedicos;
	}
	private JLabel getLbMedicos() {
		if (lbMedicos == null) {
			lbMedicos = new JLabel("Medicos:");
			lbMedicos.setBounds(68, 117, 61, 16);
		}
		return lbMedicos;
	}
	private JLabel getLbPacientes() {
		if (lbPacientes == null) {
			lbPacientes = new JLabel("Pacientes:");
			lbPacientes.setBounds(408, 117, 77, 16);
		}
		return lbPacientes;
	}
	public JComboBox<String> getCbPacientes() {
		if (cbPacientes == null) {
			cbPacientes = new JComboBox<String>();
			cbPacientes.setBounds(497, 113, 225, 27);
			idMedico=cbMedicos.getSelectedIndex()+1;
			cbPacientes.setModel(new DefaultComboBoxModel<String>(getPacientesCita(new ListAllCitasByIdAction(idMedico).execute())));
		}
		return cbPacientes;
	}
	
	private void mostrarPnVerCitas() {
		CardLayout c = (CardLayout)getPnContenidos().getLayout();
		c.show(getPnContenidos(), "PnVerCitas");	
	}
	private JButton getBtHistorialA() {
		if (btHistorialA == null) {
			btHistorialA = new JButton("Ver Historial");
			btHistorialA.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					idPaciente=idsPaciente[getCbPacientes().getSelectedIndex()];
					if(getCbPacientes().getSelectedItem()!=null)
						mostrarPnHistorial();
				}
			});
			btHistorialA.setBounds(325, 179, 117, 29);
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
		//List<CitaDto> lista = new ListAllCitasByIdAction(idMedico).execute();
		String[] pacientes = new String[lista.size()];
		idsCita = new String[lista.size()];
		idsPaciente = new String[lista.size()];
		for (int i = 0; i < pacientes.length; i++) {
			pacientes[i]=lista.get(i).namePaciente+" "+lista.get(i).surnamePaciente+" "+lista.get(i).horaInicio.split(":00.000")[0];
			idsCita[i]=lista.get(i).id;
			idsPaciente[i]=lista.get(i).idPaciente;
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
			btAtrasA.setBounds(124, 380, 117, 29);
		}
		return btAtrasA;
	}
	private JButton getBtContacto() {
		if (btContacto == null) {
			btContacto = new JButton("Asignar contacto");
			btContacto.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					idPaciente=idsPaciente[getCbPacientes().getSelectedIndex()];
					idCita=idsCita[getCbPacientes().getSelectedIndex()];
					ventanaContacto();
				}
			});
			btContacto.setBounds(469, 179, 166, 29);
		}
		return btContacto;
	}
	
	private void ventanaContacto() {
		VentanaContacto v = new VentanaContacto(this, idCita, new ListAllCitasByIdAction(idMedico).execute().get(getCbPacientes().getSelectedIndex()).idPaciente);
		v.setLocationRelativeTo(this);
		v.setModal(true);
		v.setVisible(true);
	}
	
	private void ventanaCausas() {
		VentanaCausas v = new VentanaCausas(new ListAllCitasByIdAction(idMedico).execute().get(getCbPacientes().getSelectedIndex()).idPaciente, idCita);
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
					//List<CitaDto> lista = new ListAllCitasByIdAction(idMedico).execute();
					listaFiltrada = new ArrayList<CitaDto>();
					for (CitaDto cita : lista) {
						if(getDateChooser().getDate()!=null) {
						Timestamp m = Timestamp.from(getDateChooser().getDate().toInstant());
						String string = m.toString().split(" ")[0];
						if(Date.valueOf(string).compareTo(Date.valueOf(cita.horaInicio.split(" ")[0]))==0)
								listaFiltrada.add(cita);
						}
					}
					String[] pacientes= getPacientesCita(listaFiltrada);
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
			listCitas.addListSelectionListener(new ListSelectionListener() {
				public void valueChanged(ListSelectionEvent e) {
					if(getListCitas().getSelectedIndex()!=-1) {
						CitaDto cita = listaFiltrada.get(getListCitas().getSelectedIndex());
						String sala=cita.idSala;
						String inicio = cita.horaInicio.split(" ")[1].substring(0,5);
						String salida = cita.horaFinal.split(" ")[1].substring(0, 5);
						String motivo = cita.causa;
						getTxInfo().setText("Sala de la cita: "+sala+"\n"+"Hora de inicio: "+inicio+"\n"+"Hora de salida: "+salida+"\n"+"Motivo de la cita: "+motivo );
					}
					else
						getTxInfo().setText("");
				}
			});
			idMedico=1;
			lista = new ListAllCitasByIdAction(idMedico).execute();
			String[] pacientes= getPacientesCita(lista);
			if(idsPaciente.length==0) {
				getBtContinuar().setEnabled(false);
				getBtHistorial().setEnabled(false);
			}
			listCitas.setBorder(new TitledBorder(null, "Citas", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			listCitas.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			ListModel<String> model = new DefaultComboBoxModel<String>(pacientes);
			listCitas.setModel(model);
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
			txInfo.setBorder(new TitledBorder(null, "Informacion cita:", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		}
		return txInfo;
	}
}
