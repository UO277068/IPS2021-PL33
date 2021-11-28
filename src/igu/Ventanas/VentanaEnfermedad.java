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
	private JScrollPane listDiagnosticos;
	private JList list;
	private List<DiagnosticoDto> lista;
	private JButton btSeguimento;
	private JPanel pnContenidos;
	private JButton btAtras;

	
	/**
	 * Create the frame.
	 */
	public VentanaEnfermedad(VentanaPrincipal vp) {
		this.vp=vp;
		setTitle("Seguimiento enfermedad");
		setBounds(100, 100, 611, 415);
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
					String i = d1.toString().split(" ")[0]+" "+getCbHora().getSelectedIndex()+":"+getCbMinutos().getSelectedIndex()+":00";
					Timestamp inicio = Timestamp.valueOf(i);
					if(rbActiva.isSelected())
						new AddDiagnosticoAction( vp.getCita().idPaciente, inicio,  (String)getList().getSelectedValue(), vp.getCita().id, "Si", getTxObservaciones().getText()).execute();
					if(rbFinalizo.isSelected())
						new AddDiagnosticoAction( vp.getCita().idPaciente, inicio,  (String)getList().getSelectedValue(), vp.getCita().id, "No", getTxObservaciones().getText()).execute();
				
				}
			});
		}
		return btAñadir;
	}
	private JComboBox<Integer> getCbHora() {
		if (cbHora == null) {
			cbHora = new JComboBox<Integer>();
			cbHora.setBounds(336, 104, 95, 21);
			Integer[] h= new Integer[24];
			for (int i = 0; i < h.length; i++) {
				h[i]=i;
			}
			cbHora.setModel(new DefaultComboBoxModel<Integer>(h));
		}
		return cbHora;
	}
	private JComboBox<Integer> getCbMinutos() {
		if (cbMinutos == null) {
			cbMinutos = new JComboBox<Integer>();
			cbMinutos.setBounds(454, 104, 95, 21);
			Integer[] m= new Integer[60];
			for (int j = 0; j < m.length; j++) {
				m[j]=j;
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
		}
		return pnSeguimiento;
	}
	private JPanel getPnElegirEnfermedad() {
		if (pnElegirEnfermedad == null) {
			pnElegirEnfermedad = new JPanel();
			pnElegirEnfermedad.setLayout(null);
			pnElegirEnfermedad.add(getListDiagnosticos());
			pnElegirEnfermedad.add(getBtSeguimento());
		}
		return pnElegirEnfermedad;
	}
	private JScrollPane getListDiagnosticos() {
		if (listDiagnosticos == null) {
			listDiagnosticos = new JScrollPane();
			listDiagnosticos.setBounds(40, 72, 258, 196);
			listDiagnosticos.setViewportView(getList());
		}
		return listDiagnosticos;
	}
	private JList getList() {
		if (list == null) {
			list = new JList();
			list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			lista = new ListDiagnosticoByPaciente(vp.getCita().idPaciente).execute();
			list.setModel(new DefaultComboBoxModel<String>(getDiagnostico(lista)));
		}
		return list;
	}
	
	private String[] getDiagnostico(List<DiagnosticoDto> lista) {
		String[] diagnosticos = new String[lista.size()];
		for (int i = 0; i < diagnosticos.length; i++) {
			String diagnostico = lista.get(i).diagnostico;
			diagnosticos[i]=diagnostico;
		}
		return diagnosticos;
	}
	private JButton getBtSeguimento() {
		if (btSeguimento == null) {
			btSeguimento = new JButton("Seguimiento");
			btSeguimento.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					mostrarPnSeguimiento();
				}
			});
			btSeguimento.setBounds(376, 297, 114, 21);
		}
		return btSeguimento;
	}
	
	private void mostrarPnSeguimiento() {
		CardLayout c = (CardLayout)getPnContenidos().getLayout();
		c.show(getPnContenidos(), "PnSeguimiento");	
		
	}
	
	private void mostrarPnEnfermedad() {
		CardLayout c = (CardLayout)getPnContenidos().getLayout();
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
}
