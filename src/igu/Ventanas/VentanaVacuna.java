package igu.Ventanas;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Logica.Carta;
import Logica.FileUtil;
import Logica.Vacuna;
import Logica.crud.commands.ListPacienteById;
import Logica.crud.dto.CitaDto;
import Logica.crud.dto.MedicoDto;
import Logica.crud.dto.PacienteDto;
import igu.action.AddVacunaAction;

import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.ListModel;
import javax.swing.JButton;
import javax.swing.JDialog;

import java.awt.event.ActionListener;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class VentanaVacuna extends JDialog {

	private JPanel contentPane;
	private JScrollPane scVacuna;
	private JScrollPane scVacunaElegida;
	private JList<Vacuna> listVacuna;
	private JList<Vacuna> listVacunaElegida;
	private JLabel lbVacuna;
	private JLabel lbVacunasElegidas;
	private JLabel lbPaciente;
	private JTextField txPaciente;
	private Carta carta;
	private JButton btAñadir;
	private List<Vacuna> vacunas;
	private List<Vacuna> vacunasElegidas;
	private JButton btEliminar;
	private JButton btAsignar;
	private VentanaPrincipal vp;
	private JTextField txVacuna;

	/**
	 * Create the frame.
	 */
	public VentanaVacuna(Carta carta, VentanaPrincipal vp) {
		this.carta=carta;
		this.vp=vp;
		vacunas=carta.getVacunas();
		vacunasElegidas=new ArrayList<>();
		setBounds(100, 100, 774, 536);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getScVacuna());
		contentPane.add(getScVacunaElegida());
		contentPane.add(getLbVacuna());
		contentPane.add(getLbVacunasElegidas());
		contentPane.add(getLbPaciente());
		contentPane.add(getTxPaciente());
		contentPane.add(getBtAñadir());
		contentPane.add(getBtEliminar());
		contentPane.add(getBtAsignar());
		contentPane.add(getTxVacuna());
	}
	private JScrollPane getScVacuna() {
		if (scVacuna == null) {
			scVacuna = new JScrollPane();
			scVacuna.setBounds(60, 185, 313, 172);
			scVacuna.setViewportView(getListVacuna());
		}
		return scVacuna;
	}
	private JScrollPane getScVacunaElegida() {
		if (scVacunaElegida == null) {
			scVacunaElegida = new JScrollPane();
			scVacunaElegida.setBounds(452, 185, 298, 172);
			scVacunaElegida.setViewportView(getListVacunaElegida());
		}
		return scVacunaElegida;
	}
	private JList<Vacuna> getListVacuna() {
		if (listVacuna == null) {
			listVacuna = new JList<>();
			listVacuna.setModel(new DefaultComboBoxModel<Vacuna>(getVacunas(vacunas)));
		}
		return listVacuna;
	}
	private JList<Vacuna> getListVacunaElegida() {
		if (listVacunaElegida == null) {
			listVacunaElegida = new JList<>();
		}
		return listVacunaElegida;
	}
	private JLabel getLbVacuna() {
		if (lbVacuna == null) {
			lbVacuna = new JLabel("Vacuna");
			lbVacuna.setBounds(60, 144, 73, 13);
		}
		return lbVacuna;
	}
	private JLabel getLbVacunasElegidas() {
		if (lbVacunasElegidas == null) {
			lbVacunasElegidas = new JLabel("Vacunas Elegidas");
			lbVacunasElegidas.setBounds(452, 144, 110, 13);
		}
		return lbVacunasElegidas;
	}
	private JLabel getLbPaciente() {
		if (lbPaciente == null) {
			lbPaciente = new JLabel("Paciente");
			lbPaciente.setBounds(60, 48, 73, 13);
		}
		return lbPaciente;
	}
	private JTextField getTxPaciente() {
		if (txPaciente == null) {
			txPaciente = new JTextField();
			txPaciente.setEditable(false);
			txPaciente.setBounds(127, 45, 162, 19);
			txPaciente.setColumns(10);
			String paciente = vp.getListapacientes().get(Integer.valueOf(vp.getCita().idPaciente)-1).name +" "+vp.getListapacientes().get(Integer.valueOf(vp.getCita().idPaciente)-1).surname;
			txPaciente.setText(paciente);
		}
		return txPaciente;
	}
	private JButton getBtAñadir() {
		if (btAñadir == null) {
			btAñadir = new JButton("A\u00F1adir");
			btAñadir.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					for (Vacuna vacuna : getListVacuna().getSelectedValuesList()) {
						if(!vacunasElegidas.contains(vacuna))
							vacunasElegidas.add(vacuna);
					}
					getListVacunaElegida().setModel(new DefaultComboBoxModel<Vacuna>(getVacunas(vacunasElegidas)));

				}
			});
			btAñadir.setBounds(159, 385, 85, 21);
		}
		return btAñadir;
	}
	
	private Vacuna[] getVacunas(List<Vacuna> lista) {
		//List<CitaDto> lista = new ListAllCitasByIdAction(idMedico).execute();
		Vacuna[] vacunas = new Vacuna[lista.size()];
		for (int i = 0; i < vacunas.length; i++) {
			Vacuna vacuna = new Vacuna(lista.get(i));
			vacunas[i]=vacuna;
		}
		return vacunas;
	}
	private JButton getBtEliminar() {
		if (btEliminar == null) {
			btEliminar = new JButton("Eliminar");
			btEliminar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					for (Vacuna vacuna : getListVacunaElegida().getSelectedValuesList()) {
						if(vacunasElegidas.contains(vacuna))
							vacunasElegidas.remove(vacuna);
					}
					getListVacunaElegida().setModel(new DefaultComboBoxModel<Vacuna>(getVacunas(vacunasElegidas)));

				}
			});
			btEliminar.setBounds(565, 385, 85, 21);
		}
		return btEliminar;
	}

	public List<Vacuna> getVacunasElegidas() {
		return vacunasElegidas;
	}
	
	
	private JButton getBtAsignar() {
		if (btAsignar == null) {
			btAsignar = new JButton("Asignar");
			btAsignar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					CitaDto cita = vp.getCita();
					Timestamp fecha=null;
					if(cita.horaEntrada!=null)
						fecha = Timestamp.valueOf(cita.horaEntrada);
					else
						fecha = Timestamp.valueOf(LocalDateTime.now().toString().replace('T', ' '));
					new AddVacunaAction(vp.getIdPaciente(), cita.idPaciente,fecha, vacunasElegidas).execute();
					FileUtil.escribirLog("MiLogger", "Medico ID: 1"+"null -> " + vacunasElegidas);
				}
			});
			btAsignar.setBounds(352, 457, 85, 21);
		}
		return btAsignar;
	}
	private JTextField getTxVacuna() {
		if (txVacuna == null) {
			txVacuna = new JTextField();
			txVacuna.addKeyListener(new KeyAdapter() {
				@Override
				public void keyReleased(KeyEvent e) {
					Vacuna[] vacunasstr = getVacunas(filtrarListaVacunas(vacunas,txVacuna.getText()));
					ListModel<Vacuna> model = new DefaultComboBoxModel<Vacuna>(vacunasstr);
					getListVacuna().setModel(model);
				}
			});
			txVacuna.setBounds(60, 167, 313, 19);
			txVacuna.setColumns(10);
		}
		return txVacuna;
	}
	
	private List<Vacuna> filtrarListaVacunas(List<Vacuna> medi, String start) {
		List<Vacuna> listaFiltrada = new ArrayList<Vacuna>();
		for (Vacuna m : medi) {
			// Filtro por nombre
			if (m.getNombre().toUpperCase().startsWith(start.toUpperCase())) {
				listaFiltrada.add(m);
			}
			//Filtro por documento de identficacion
			else if(m.getComponente().toUpperCase().startsWith(start.toUpperCase())) 
			{
			    listaFiltrada.add(m);
			}
		}
		return listaFiltrada;
	}
}
