package igu.Ventanas;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Timestamp;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;

import com.toedter.calendar.JDateChooser;

import Logica.crud.commands.ListDiagnostico;
import Logica.crud.commands.ListDiagnosticoByPaciente;
import Logica.crud.dto.DiagnosticoDto;
import igu.action.AddDiagnosticoAction;
import igu.action.ListDiagnosticoByIdAction;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;

public class VentanaEnfermedad extends JDialog {

	private JPanel contentPane;
	private JRadioButton rbActiva;
	private JRadioButton rbFinalizo;
	private JDateChooser dateChooser;
	private Label lbFecha;
	private JLabel lbObservaciones;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JScrollPane scrollPane;
	private JTextArea txObservaciones;
	private JButton btAñadir;
	private VentanaPrincipal vp;
	private JComboBox<Integer> cbHora;
	private JComboBox<Integer> cbMinutos;
	private JPanel pnSeguimiento;
	private JPanel pnElegirEnfermedad;
	private JScrollPane scDiagnosticos;
	private JList listDiagnosticos;
	private List<DiagnosticoDto> lista;
	private JButton btSeguimento;
	private JPanel pnContenidos;
	private JButton btAtras;
	private JScrollPane scSeguimiento;
	private JTextArea txSeguimiento;
	private JLabel lbDiagnostico;
	private JLabel lbSeguimiento;
	private JLabel lbHora;
	private JLabel lbMinuto;

	/**
	 * Create the frame.
	 */
	public VentanaEnfermedad(VentanaPrincipal vp) {
		this.vp = vp;
		setTitle("Seguimiento enfermedad");
		setBounds(100, 100, 895, 481);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		contentPane.add(getPnContenidos());
	}

	private JRadioButton getRbActiva() {
		if (rbActiva == null) {
			rbActiva = new JRadioButton("Enfermedad Activa");
			rbActiva.setBounds(58, 54, 189, 21);
			buttonGroup.add(rbActiva);
			rbActiva.setSelected(true);
		}
		return rbActiva;
	}

	private JRadioButton getRbFinalizo() {
		if (rbFinalizo == null) {
			rbFinalizo = new JRadioButton("Enfermedad Finalizada");
			rbFinalizo.setBounds(58, 90, 189, 21);
			buttonGroup.add(rbFinalizo);
		}
		return rbFinalizo;
	}

	private JDateChooser getDateChooser() {
		if (dateChooser == null) {
			dateChooser = new JDateChooser();
			dateChooser.setBounds(371, 54, 183, 19);
		}
		return dateChooser;
	}

	private Label getLbFecha() {
		if (lbFecha == null) {
			lbFecha = new Label("Fecha:");
			lbFecha.setBounds(306, 54, 59, 21);
		}
		return lbFecha;
	}

	private JLabel getLbObservaciones() {
		if (lbObservaciones == null) {
			lbObservaciones = new JLabel("Observaciones");
			lbObservaciones.setBounds(69, 137, 127, 13);
		}
		return lbObservaciones;
	}

	private JScrollPane getScrollPane() {
		if (scrollPane == null) {
			scrollPane = new JScrollPane();
			scrollPane.setBounds(69, 180, 470, 142);
			scrollPane.setViewportView(getTxObservaciones());
		}
		return scrollPane;
	}

	private JTextArea getTxObservaciones() {
		if (txObservaciones == null) {
			txObservaciones = new JTextArea();
		}
		return txObservaciones;
	}

	private JButton getBtAñadir() {
		if (btAñadir == null) {
			btAñadir = new JButton("A\u00F1adir");
			btAñadir.setBounds(454, 347, 85, 21);
			btAñadir.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Timestamp d1 = new Timestamp(getDateChooser().getDate().getTime());
					String i = d1.toString().split(" ")[0] + " " + getCbHora().getSelectedIndex() + ":"
							+ getCbMinutos().getSelectedIndex() + ":00";
					Timestamp inicio = Timestamp.valueOf(i);
					if (rbActiva.isSelected())
						new AddDiagnosticoAction(vp.getCita().idPaciente, inicio,
								(String) getListDiagnosticos().getSelectedValue(), vp.getCita().id, "Si",
								getTxObservaciones().getText()).execute();
					if (rbFinalizo.isSelected())
						new AddDiagnosticoAction(vp.getCita().idPaciente, inicio,
								(String) getListDiagnosticos().getSelectedValue(), vp.getCita().id, "No",
								getTxObservaciones().getText()).execute();
					getSeguimiento();

				}
			});
		}
		return btAñadir;
	}

	private JComboBox<Integer> getCbHora() {
		if (cbHora == null) {
			cbHora = new JComboBox<Integer>();
			cbHora.setBounds(336, 116, 95, 21);
			Integer[] h = new Integer[24];
			for (int i = 0; i < h.length; i++) {
				h[i] = i;
			}
			cbHora.setModel(new DefaultComboBoxModel<Integer>(h));
		}
		return cbHora;
	}

	private JComboBox<Integer> getCbMinutos() {
		if (cbMinutos == null) {
			cbMinutos = new JComboBox<Integer>();
			cbMinutos.setBounds(459, 116, 95, 21);
			Integer[] m = new Integer[60];
			for (int j = 0; j < m.length; j++) {
				m[j] = j;
			}
			cbMinutos.setModel(new DefaultComboBoxModel<Integer>(m));
		}
		return cbMinutos;
	}

	private JPanel getPnSeguimiento() {
		if (pnSeguimiento == null) {
			pnSeguimiento = new JPanel();
			pnSeguimiento.setLayout(null);
			pnSeguimiento.add(getRbActiva());
			pnSeguimiento.add(getRbFinalizo());
			pnSeguimiento.add(getDateChooser());
			pnSeguimiento.add(getLbFecha());
			pnSeguimiento.add(getLbObservaciones());
			pnSeguimiento.add(getScrollPane());
			pnSeguimiento.add(getBtAñadir());
			pnSeguimiento.add(getCbHora());
			pnSeguimiento.add(getCbMinutos());
			pnSeguimiento.add(getBtAtras());
			pnSeguimiento.add(getLbHora());
			pnSeguimiento.add(getLbMinuto());
		}
		return pnSeguimiento;
	}

	private JPanel getPnElegirEnfermedad() {
		if (pnElegirEnfermedad == null) {
			pnElegirEnfermedad = new JPanel();
			pnElegirEnfermedad.setLayout(null);
			pnElegirEnfermedad.add(getScDiagnosticos());
			pnElegirEnfermedad.add(getBtSeguimento());
			pnElegirEnfermedad.add(getScSeguimiento());
			pnElegirEnfermedad.add(getLbDiagnostico());
			pnElegirEnfermedad.add(getLbSeguimiento());
		}
		return pnElegirEnfermedad;
	}

	private JScrollPane getScDiagnosticos() {
		if (scDiagnosticos == null) {
			scDiagnosticos = new JScrollPane();
			scDiagnosticos.setBounds(40, 72, 338, 270);
			scDiagnosticos.setViewportView(getListDiagnosticos());
		}
		return scDiagnosticos;
	}

	private JList getListDiagnosticos() {
		if (listDiagnosticos == null) {
			listDiagnosticos = new JList();
			listDiagnosticos.addListSelectionListener(new ListSelectionListener() {
				public void valueChanged(ListSelectionEvent e) {
					getSeguimiento();
				}
			});
			listDiagnosticos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			lista = new ListDiagnosticoByPaciente(vp.getCita().idPaciente).execute();
			listDiagnosticos.setModel(new DefaultComboBoxModel<String>(getDiagnostico(lista)));
		}
		return listDiagnosticos;
	}

	private String[] getDiagnostico(List<DiagnosticoDto> lista) {
		String[] diagnosticos = new String[lista.size()];
		for (int i = 0; i < diagnosticos.length; i++) {
			String diagnostico = lista.get(i).diagnostico;
			diagnosticos[i] = diagnostico;
		}
		return diagnosticos;
	}

	private JButton getBtSeguimento() {
		if (btSeguimento == null) {
			btSeguimento = new JButton("Actualizar estado");
			btSeguimento.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(getListDiagnosticos().getSelectedIndex()==-1)
						JOptionPane.showMessageDialog(null, "No has seleccionado ninguna enfermedad");
					else
						mostrarPnSeguimiento();
				}
			});
			btSeguimento.setBounds(343, 377, 169, 21);
		}
		return btSeguimento;
	}

	private void mostrarPnSeguimiento() {
		CardLayout c = (CardLayout) getPnContenidos().getLayout();
		c.show(getPnContenidos(), "PnSeguimiento");

	}

	private void mostrarPnEnfermedad() {
		CardLayout c = (CardLayout) getPnContenidos().getLayout();
		c.show(getPnContenidos(), "PnEnfermedad");

	}

	private JPanel getPnContenidos() {
		if (pnContenidos == null) {
			pnContenidos = new JPanel();
			pnContenidos.setLayout(new CardLayout(0, 0));
			pnContenidos.add(getPnElegirEnfermedad(), "PnEnfermedad");
			pnContenidos.add(getPnSeguimiento(), "PnSeguimiento");
		}
		return pnContenidos;
	}

	private JButton getBtAtras() {
		if (btAtras == null) {
			btAtras = new JButton("Atras");
			btAtras.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					mostrarPnEnfermedad();
				}
			});
			btAtras.setBounds(23, 347, 85, 21);
		}
		return btAtras;
	}

	private JScrollPane getScSeguimiento() {
		if (scSeguimiento == null) {
			scSeguimiento = new JScrollPane();
			scSeguimiento.setBounds(465, 72, 356, 270);
			scSeguimiento.setViewportView(getTxSeguimiento());
		}
		return scSeguimiento;
	}

	private JTextArea getTxSeguimiento() {
		if (txSeguimiento == null) {
			txSeguimiento = new JTextArea();
		}
		return txSeguimiento;
	}

	private void getSeguimiento() {
		List<DiagnosticoDto> diagnosticos = new ListDiagnosticoByIdAction(vp.getCita().idPaciente).execute();
		if (getListDiagnosticos().getSelectedValue() != null) {
			String d = "";
			for (DiagnosticoDto diag : diagnosticos) {
				if (diag.diagnostico.equals(getListDiagnosticos().getSelectedValue())) {
					d += "Fecha de la visita: " + diag.fecha.toString().substring(0, 16);
					if (diag.prescripcion != null)
						d += "\n\t-Prescripcion de la visita: " + diag.prescripcion;
					else
						d += "\n\t-Prescripcion de la visita: No hay prescripcion asociada";
					if(diag.descripcion.isEmpty())
						d += "\n\t-Observaciones: No hay ninguna observación";
					else
						d += "\n\t-Observaciones: " + diag.descripcion;
					if (diag.status.equals("Si"))
						d += "\n\t-Estado: Enfermedad activa\n";
					else if (diag.status.equals("No"))
						d += "\n\t-Estado: Enfermedad finalizada\n";
				}
			}
			getTxSeguimiento().setText(d);
		}
	}
	private JLabel getLbDiagnostico() {
		if (lbDiagnostico == null) {
			lbDiagnostico = new JLabel("Diagnosticos");
			lbDiagnostico.setBounds(40, 42, 143, 13);
		}
		return lbDiagnostico;
	}
	private JLabel getLbSeguimiento() {
		if (lbSeguimiento == null) {
			lbSeguimiento = new JLabel("Seguimiento");
			lbSeguimiento.setBounds(465, 42, 114, 13);
		}
		return lbSeguimiento;
	}
	private JLabel getLbHora() {
		if (lbHora == null) {
			lbHora = new JLabel("Hora");
			lbHora.setBounds(366, 94, 65, 13);
		}
		return lbHora;
	}
	private JLabel getLbMinuto() {
		if (lbMinuto == null) {
			lbMinuto = new JLabel("Minuto");
			lbMinuto.setBounds(494, 94, 45, 13);
		}
		return lbMinuto;
	}
}
