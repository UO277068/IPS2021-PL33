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
import javax.swing.JOptionPane;
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
import Logica.crud.dto.SolicitudDto;
import igu.action.AddHorarioAction;
import igu.action.AddSolicitudAction;
import igu.action.ListAllCitasAction;
import igu.action.ListAllCitasByIdAction;
import igu.action.ListAllMedicosAction;
import igu.action.ListAllPacientesAction;
import igu.action.ListDiagnosticoByIdAction;
import igu.action.ListVacunaByIdAction;
import igu.action.UpdateAcudioCitaAction;
import igu.action.UpdateHoraEntradaSalidaAction;
import javax.swing.JCheckBox;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;




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
	
	private JPanel panel;
	private JButton btSolicitarCrear;
	private JButton btSolicitarModificar;
	private JButton btSolicitarEliminar;
	private JPanel pnSolicitarEliminar;
	
	List<CitaDto> listaCitas;
	
	private JComboBox<String> cbMedicos_1;
	private JLabel lbMedico_1;
	private JLabel lbPaciente_1;
	private JComboBox<String> cbPacientes_1;
	private JButton btAtras;
	private JButton btSolicitarEliminarConfirmar;
	private JScrollPane scrollObservacion;
	private JTextArea txtObservacion;
	private JPanel pnSolicitarModificar;
	private JComboBox<String> cbMedicos_2;
	private JComboBox<String> cbPacientes_2;
	private JLabel lbMedico_1_1;
	private JLabel lbPaciente_1_1;


	

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
		this.listaCitas = new ListAllCitasAction().execute();
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
			pnContenidos.add(getPnSolicitarEliminar(), "PnSolicitarEliminar");
			pnContenidos.add(getPnSolicitarModificar(), "name_156011776791500");
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
			pnHorario.add(getChLunes());
			pnHorario.add(getChMartes());
			pnHorario.add(getChMiercoles());
			pnHorario.add(getChJueves());
			pnHorario.add(getChViernes());
			pnHorario.add(getChSabado());
			pnHorario.add(getChDomingo());
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
					new AddHorarioAction(d1, d2,diasSeleccionados, getList().getSelectedIndices()).execute();
				}
			});
			btAsignarHorario.setBounds(344, 306, 143, 29);
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
			dcDesde.addPropertyChangeListener(new PropertyChangeListener() {
				public void propertyChange(PropertyChangeEvent evt) {
					if(dcDesde.getDate()!=null) {
						if(diaDesde==1 && diaHasta!=1) {
							getChLunes().setSelected(false);
						}
						else if(diaDesde==2 && diaHasta!=2) {
							getChMartes().setSelected(false);
						}
						else if(diaDesde==3 && diaHasta!=3) {
							getChMiercoles().setSelected(false);
						}
						else if(diaDesde==4 && diaHasta!=4) {
							getChJueves().setSelected(false);
						}
						else if(diaDesde==5 && diaHasta!=5) {
							getChViernes().setSelected(false);
						}
						else if(diaDesde==6 && diaHasta!=6) {
							getChSabado().setSelected(false);
						}
						else if(diaDesde==0 && diaHasta!=0) {
							getChDomingo().setSelected(false);
						}
						diaDesde=dcDesde.getDate().getDay();
						if(diaDesde==1) {
							getChLunes().setSelected(true);
						}
						else if(diaDesde==2) {
							getChMartes().setSelected(true);
						}
						else if(diaDesde==3) {
							getChMiercoles().setSelected(true);
						}
						else if(diaDesde==4) {
							getChJueves().setSelected(true);
						}
						else if(diaDesde==5) {
							getChViernes().setSelected(true);
						}
						else if(diaDesde==6) {
							getChSabado().setSelected(true);
						}
						else if(diaDesde==0) {
							getChDomingo().setSelected(true);
						}
					}
				}
			});
			dcDesde.setBounds(149, 178, 165, 26);
		}
		return dcDesde;
	}
	private JDateChooser getDcHasta() {
		if (dcHasta == null) {
			dcHasta = new JDateChooser();
			dcHasta.addPropertyChangeListener(new PropertyChangeListener() {
				public void propertyChange(PropertyChangeEvent evt) {
					if(dcHasta.getDate()!=null) {
						
						if(diaHasta==1 && diaDesde!=1) {
							getChLunes().setSelected(false);
						}
						else if(diaHasta==2 && diaDesde!=2) {
							getChMartes().setSelected(false);
						}
						else if(diaHasta==3 && diaDesde!=3) {
							getChMiercoles().setSelected(false);
						}
						else if(diaHasta==4 && diaDesde!=4) {
							getChJueves().setSelected(false);
						}
						else if(diaHasta==5 && diaDesde!=5) {
							getChViernes().setSelected(false);
						}
						else if(diaHasta==6 && diaDesde!=6) {
							getChSabado().setSelected(false);
						}
						else if(diaHasta==0 && diaDesde!=0) {
							getChDomingo().setSelected(false);
						}
						diaHasta=dcHasta.getDate().getDay();
						if(diaHasta==1) {
							getChLunes().setSelected(true);
						}
						else if(diaHasta==2) {
							getChMartes().setSelected(true);
						}
						else if(diaHasta==3) {
							getChMiercoles().setSelected(true);
						}
						else if(diaHasta==4) {
							getChJueves().setSelected(true);
						}
						else if(diaHasta==5) {
							getChViernes().setSelected(true);
						}
						else if(diaHasta==6) {
							getChSabado().setSelected(true);
						}
						else if(diaHasta==0) {
							getChDomingo().setSelected(true);
						}
						
						if(getCbOpcion().getSelectedIndex()==0) {
							actualizarDias(diaDesde, diaHasta);
						}
					}
				}
			});
			dcHasta.setBounds(149, 227, 165, 26);
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
		d1.setDate(d1.getDate()+7);
		if(d2.after(d1)) {
			getChLunes().setSelected(true);
			getChMartes().setSelected(true);
			getChMiercoles().setSelected(true);
			getChJueves().setSelected(true);
			getChViernes().setSelected(true);
			getChSabado().setSelected(true);
			getChDomingo().setSelected(true);
		}
		else {
		
			for (int i = desde; i <= hasta+7; i++) {
				int c = i;
				if(i-7>=0)
					c=c-7;
				if(c==1) {
					getChLunes().setSelected(true);
				}
				else if(c==2) {
					getChMartes().setSelected(true);
				}
				else if(c==3) {
					getChMiercoles().setSelected(true);
				}
				else if(c==4) {
					getChJueves().setSelected(true);
				}
				else if(c==5) {
					getChViernes().setSelected(true);
				}
				else if(c==6) {
					getChSabado().setSelected(true);
				}
				else if(c==0) {
					getChDomingo().setSelected(true);
				}
				
				if(c==hasta)
					break;
			}
	
		}
	}
	private JLabel getLbDesde() {
		if (lbDesde == null) {
			lbDesde = new JLabel("Desde:");
			lbDesde.setBounds(90, 188, 61, 16);
		}
		return lbDesde;
	}
	private JLabel getLbHasta() {
		if (lbHasta == null) {
			lbHasta = new JLabel("Hasta:");
			lbHasta.setBounds(90, 237, 61, 16);
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
			cbHoraD.setBounds(342, 177, 72, 27);
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
			cbMinutosD.setBounds(415, 178, 72, 27);
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
			cbHoraH.setBounds(342, 227, 72, 27);
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
			cbMinutosH.setBounds(415, 226, 72, 27);
			cbMinutosH.setModel(new DefaultComboBoxModel<Integer>(m));
		}
		return cbMinutosH;
	}
	private JComboBox<String> getCbOpcion() {
		if (cbOpcion == null) {
			cbOpcion = new JComboBox<String>();
			cbOpcion.addItemListener(new ItemListener() {
				public void itemStateChanged(ItemEvent e) {
					if(cbOpcion.getSelectedIndex()==0) {
						actualizarDias(diaDesde, diaHasta);
					}
				}
			});
			cbOpcion.setModel(new DefaultComboBoxModel<String>(new String[] {"Todos los dias", "Personalizado"}));
			cbOpcion.setBounds(90, 307, 193, 26);
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
	private JCheckBox getChLunes() {
		if (chLunes == null) {
			chLunes = new JCheckBox("Lunes");
			chLunes.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					if((diaHasta==1 || diaDesde==1) && !chLunes.isSelected())
						chLunes.setSelected(true);
				}
			});
			chLunes.addItemListener(new ItemListener() {
				public void itemStateChanged(ItemEvent e) {
					if(chLunes.isSelected())
						diasSeleccionados.set(1, true);
					else
						diasSeleccionados.set(1, false);
				}
			});
			chLunes.setBounds(90, 120, 78, 23);
		}
		return chLunes;
	}
	private JCheckBox getChMartes() {
		if (chMartes == null) {
			chMartes = new JCheckBox("Martes");
			chMartes.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					if((diaHasta==2 || diaDesde==2) && !chMartes.isSelected())
						chMartes.setSelected(true);
				}
			});
			chMartes.addItemListener(new ItemListener() {
				public void itemStateChanged(ItemEvent e) {
					if(chMartes.isSelected())
						diasSeleccionados.set(2, true);
					else
						diasSeleccionados.set(2, false);
				}
			});
			chMartes.setBounds(173, 121, 81, 23);
		}
		return chMartes;
	}
	private JCheckBox getChMiercoles() {
		if (chMiercoles == null) {
			chMiercoles = new JCheckBox("Miercoles");
			chMiercoles.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					if((diaHasta==3 || diaDesde==3) && !chMiercoles.isSelected())
						chMiercoles.setSelected(true);
				}
			});
			chMiercoles.addItemListener(new ItemListener() {
				public void itemStateChanged(ItemEvent e) {
					if(chMiercoles.isSelected())
						diasSeleccionados.set(3, true);
					else
						diasSeleccionados.set(3, false);
				}
			});
			chMiercoles.setBounds(266, 121, 101, 23);
		}
		return chMiercoles;
	}
	private JCheckBox getChJueves() {
		if (chJueves == null) {
			chJueves = new JCheckBox("Jueves");
			chJueves.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					if((diaHasta==4 || diaDesde==4) && !chJueves.isSelected())
						chJueves.setSelected(true);
				}
			});
			chJueves.addItemListener(new ItemListener() {
				public void itemStateChanged(ItemEvent e) {
					if(chJueves.isSelected())
						diasSeleccionados.set(4, true);
					else
						diasSeleccionados.set(4, false);
				}
			});
			chJueves.setBounds(371, 121, 89, 23);
		}
		return chJueves;
	}
	private JCheckBox getChViernes() {
		if (chViernes == null) {
			chViernes = new JCheckBox("Viernes");
			chViernes.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					if((diaHasta==5 || diaDesde==5) && !chViernes.isSelected())
						chViernes.setSelected(true);
				}
			});
			chViernes.addItemListener(new ItemListener() {
				public void itemStateChanged(ItemEvent e) {
					if(chViernes.isSelected())
						diasSeleccionados.set(5, true);
					else
						diasSeleccionados.set(5, false);
				}
			});
			chViernes.setBounds(459, 121, 89, 23);
		}
		return chViernes;
	}
	private JCheckBox getChSabado() {
		if (chSabado == null) {
			chSabado = new JCheckBox("Sabado");
			chSabado.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					if((diaHasta==6 || diaDesde==6) && !chSabado.isSelected())
						chSabado.setSelected(true);
				}
			});
			chSabado.addItemListener(new ItemListener() {
				public void itemStateChanged(ItemEvent e) {
					if(chSabado.isSelected())
						diasSeleccionados.set(6, true);
					else
						diasSeleccionados.set(6, false);
				}
			});
			chSabado.setBounds(549, 121, 89, 23);
		}
		return chSabado;
	}
	private JCheckBox getChDomingo() {
		if (chDomingo == null) {
			chDomingo = new JCheckBox("Domingo");
			chDomingo.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					if((diaHasta==0 || diaDesde==0) && !chDomingo.isSelected())
						chDomingo.setSelected(true);
				}
			});
			chDomingo.addItemListener(new ItemListener() {
				public void itemStateChanged(ItemEvent e) {
					if(chDomingo.isSelected())
						diasSeleccionados.set(0, true);
					else
						diasSeleccionados.set(0, false);
				}
			});
			chDomingo.setBounds(641, 121, 120, 23);
		}
		return chDomingo;
	}
	private JPanel getPanel() {
		if (panel == null) {
			panel = new JPanel();
			panel.setBorder(new TitledBorder(null, "Solicitar", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			panel.setBounds(324, 316, 366, 62);
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
					ventanaSolicitarCrear();
				}
			});
		}
		return btSolicitarCrear;
	}
	
	private void ventanaSolicitarCrear() {
		VentanaSolicitarCrearCita solicitarCita = new VentanaSolicitarCrearCita(this);	
		solicitarCita.setLocationRelativeTo(this);
		solicitarCita.setModal(true);
		solicitarCita.setVisible(true);
	}
	
	private JButton getBtSolicitarModificar() {
		if (btSolicitarModificar == null) {
			btSolicitarModificar = new JButton("Modificar");
			btSolicitarModificar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					mostrarPnSolicitarModificar();
				}
			});
		}
		return btSolicitarModificar;
	}
	
	private void mostrarPnSolicitarModificar() {
		//TODO
	}
	
	private JButton getBtSolicitarEliminar() {
		if (btSolicitarEliminar == null) {
			btSolicitarEliminar = new JButton("Eliminar");
			btSolicitarEliminar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					mostrarPnSolicitarEliminar();
				}
			});
		}
		return btSolicitarEliminar;
	}
	
	private void mostrarPnSolicitarEliminar() {
			CardLayout c = (CardLayout)getPnContenidos().getLayout();
			c.show(getPnContenidos(), "PnSolicitarEliminar");		
	}
	
	private JPanel getPnSolicitarEliminar() {
		if (pnSolicitarEliminar == null) {
			pnSolicitarEliminar = new JPanel();
			pnSolicitarEliminar.setLayout(null);
			pnSolicitarEliminar.add(getCbMedicos_1());
			pnSolicitarEliminar.add(getLbMedico_1());
			pnSolicitarEliminar.add(getLbPaciente_1());
			pnSolicitarEliminar.add(getCbPacientes_1());
			pnSolicitarEliminar.add(getBtAtras());
			pnSolicitarEliminar.add(getBtSolicitarEliminarConfirmar());
			pnSolicitarEliminar.add(getScrollObservacion());
		}
		return pnSolicitarEliminar;
	}
	private JComboBox<String> getCbMedicos_1() {
		if (cbMedicos_1 == null) {
			String[] medicos = new String[listamedicos.size()];
			for (int i = 0; i < medicos.length; i++) {
				
				medicos[i]=listamedicos.get(i).name+" "+listamedicos.get(i).surname;
			}
			cbMedicos_1 = new JComboBox<String>();
			cbMedicos_1.addItemListener(new ItemListener() {
				public void itemStateChanged(ItemEvent e) {
					idMedico=cbMedicos_1.getSelectedIndex()+1;
					getCbPacientes_1().setModel(new DefaultComboBoxModel<String>(getPacientesCita(new ListAllCitasByIdAction(idMedico).execute())));
				}
			});
			idMedico=cbMedicos_1.getSelectedIndex()+1;
			cbMedicos_1.setBounds(149, 130, 225, 27);
			cbMedicos_1.setModel(new DefaultComboBoxModel<String>((medicos)));
		}
		return cbMedicos_1;
	}
	private JLabel getLbMedico_1() {
		if (lbMedico_1 == null) {
			lbMedico_1 = new JLabel("Medico:");
			lbMedico_1.setBounds(139, 107, 46, 14);
		}
		return lbMedico_1;
	}
	private JLabel getLbPaciente_1() {
		if (lbPaciente_1 == null) {
			lbPaciente_1 = new JLabel("Paciente:");
			lbPaciente_1.setBounds(508, 107, 46, 14);
		}
		return lbPaciente_1;
	}
	private JComboBox<String> getCbPacientes_1() {
		if (cbPacientes_1 == null) {
			cbPacientes_1 = new JComboBox<String>();
			cbPacientes_1.setBounds(518, 130, 225, 27);
			idMedico=cbMedicos_1.getSelectedIndex()+1;
			cbPacientes_1.setModel(new DefaultComboBoxModel<String>(getPacientesCita(new ListAllCitasByIdAction(idMedico).execute())));
		}
		return cbPacientes_1;
	}
	private JButton getBtAtras() {
		if (btAtras == null) {
			btAtras = new JButton("Atras");
			btAtras.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					mostrarPnMedico();
				}
			});
			btAtras.setBounds(776, 381, 89, 23);
		}
		return btAtras;
	}
	private JButton getBtSolicitarEliminarConfirmar() {
		if (btSolicitarEliminarConfirmar == null) {
			btSolicitarEliminarConfirmar = new JButton("Solicitar Eliminar");
			btSolicitarEliminarConfirmar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Integer res = JOptionPane.showConfirmDialog(null, "�Estas seguro de solicitar la eliminacion de esta cita?");
					if(res==JOptionPane.YES_OPTION) {
						String paciente = getCbPacientes_1().getSelectedItem().toString();
						String medico = getCbMedicos_1().getSelectedItem().toString();
						crearSolicitudEliminar(paciente, medico, getTxtObservacion().getText());
					}
				}
			});
			btSolicitarEliminarConfirmar.setBounds(546, 204, 178, 42);
		}
		return btSolicitarEliminarConfirmar;
	}

	private void crearSolicitudEliminar(String paciente, String medico, String observacion) {
		SolicitudDto sol = new SolicitudDto();
		sol.tipo = "ELIMINAR";
		sol.cuerpo = "Paciente: " + paciente + ", Medico: " + medico;
		sol.observaciones=observacion;
		new AddSolicitudAction(sol).execute();
		
	}
	private JScrollPane getScrollObservacion() {
		if (scrollObservacion == null) {
			scrollObservacion = new JScrollPane();
			scrollObservacion.setBounds(163, 204, 200, 107);
			scrollObservacion.setViewportView(getTxtObservacion());
		}
		return scrollObservacion;
	}
	private JTextArea getTxtObservacion() {
		if (txtObservacion == null) {
			txtObservacion = new JTextArea();
		}
		return txtObservacion;
	}
	private JPanel getPnSolicitarModificar() {
		if (pnSolicitarModificar == null) {
			pnSolicitarModificar = new JPanel();
			pnSolicitarModificar.setLayout(null);
			pnSolicitarModificar.add(getCbMedicos_2());
			pnSolicitarModificar.add(getCbPacientes_2());
			pnSolicitarModificar.add(getLbMedico_1_1());
			pnSolicitarModificar.add(getLbPaciente_1_1());
		}
		return pnSolicitarModificar;
	}
	private JComboBox<String> getCbMedicos_2() {
		if (cbMedicos_2 == null) {
			cbMedicos_2 = new JComboBox<String>();
			cbMedicos_2.setBounds(143, 134, 225, 27);
		}
		return cbMedicos_2;
	}
	private JComboBox<String> getCbPacientes_2() {
		if (cbPacientes_2 == null) {
			cbPacientes_2 = new JComboBox<String>();
			cbPacientes_2.setBounds(524, 136, 225, 27);
		}
		return cbPacientes_2;
	}
	private JLabel getLbMedico_1_1() {
		if (lbMedico_1_1 == null) {
			lbMedico_1_1 = new JLabel("Medico:");
			lbMedico_1_1.setBounds(121, 105, 46, 14);
		}
		return lbMedico_1_1;
	}
	private JLabel getLbPaciente_1_1() {
		if (lbPaciente_1_1 == null) {
			lbPaciente_1_1 = new JLabel("Paciente:");
			lbPaciente_1_1.setBounds(509, 111, 46, 14);
		}
		return lbPaciente_1_1;
	}
}
