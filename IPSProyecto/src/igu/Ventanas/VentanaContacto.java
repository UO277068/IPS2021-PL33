package igu.Ventanas;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

import Logica.crud.dto.MedicoDto;
import Logica.crud.dto.PacienteDto;
import igu.action.AddContactoCitaAction;
import igu.action.GetContactoByIdPacienteAction;

public class VentanaContacto extends JDialog {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JLabel lbPaciente;
	private JTextField txtPaciente;
	private JLabel lbContacto;
	private JTextField txtContacto;
	private JLabel lbNuevoContacto;
	private JTextField txtNuevoContacto;
	private JButton btAceptarCambio;
	private JButton btAceptar;
	private JButton btSalir;
	
	List<PacienteDto> pacientes;
	List<MedicoDto> medicos;
	
	String contacto;
	String numCita;
	String idPaciente;
	
	private VentanaPrincipal ventanaPrincipal;
	
	public VentanaContacto(VentanaPrincipal vRober, String numCita, String idPaciente) {
		this.ventanaPrincipal = vRober;
		this.idPaciente = idPaciente;
		this.numCita=numCita;
		
		this.medicos = getVentanaPrincipal().listamedicos;

		this.pacientes = getVentanaPrincipal().listapacientes;
		
		getContentPane().setLayout(null);
		setBounds(100,100,450,320);
		getContentPane().add(getLbPaciente());
		getContentPane().add(getTxtPaciente());
		getContentPane().add(getLbContacto());
		getContentPane().add(getTxtContacto());
		getContentPane().add(getLbNuevoContacto());
		getContentPane().add(getTxtNuevoContacto());
		getContentPane().add(getBtAceptarCambio());
		getContentPane().add(getBtAceptar());
		getContentPane().add(getBtSalir());
		
		
	}
	private JLabel getLbPaciente() {
		if (lbPaciente == null) {
			lbPaciente = new JLabel("Paciente:");
			lbPaciente.setBounds(34, 45, 86, 21);
		}
		return lbPaciente;
	}
	
	public VentanaPrincipal getVentanaPrincipal() {
		return ventanaPrincipal;
	}
	
	private JTextField getTxtPaciente() {
		if (txtPaciente == null) {
			txtPaciente = new JTextField();
			txtPaciente.setText((String) getVentanaPrincipal().getCbPacientes().getSelectedItem());
			txtPaciente.setBackground(Color.WHITE);
			txtPaciente.setEditable(false);
			txtPaciente.setBounds(158, 39, 255, 27);
			txtPaciente.setColumns(10);
		}
		return txtPaciente;
	}
	private JLabel getLbContacto() {
		if (lbContacto == null) {
			lbContacto = new JLabel("Contacto:");
			lbContacto.setBounds(34, 112, 86, 21);
		}
		return lbContacto;
	}
	private JTextField getTxtContacto() {
		if (txtContacto == null) {
			txtContacto = new JTextField();
			String contacto = new GetContactoByIdPacienteAction(idPaciente).execute();
			txtContacto.setText(contacto);
			txtContacto.setBackground(Color.WHITE);
			txtContacto.setEditable(false);
			txtContacto.setBounds(158, 106, 86, 27);
			txtContacto.setColumns(10);
		}
		return txtContacto;
	}
	private JLabel getLbNuevoContacto() {
		if (lbNuevoContacto == null) {
			lbNuevoContacto = new JLabel("Cambiar contacto cita:");
			lbNuevoContacto.setBounds(34, 159, 124, 27);
		}
		return lbNuevoContacto;
	}
	private JTextField getTxtNuevoContacto() {
		if (txtNuevoContacto == null) {
			txtNuevoContacto = new JTextField();
			txtNuevoContacto.setColumns(10);
			txtNuevoContacto.setBackground(Color.WHITE);
			txtNuevoContacto.setBounds(158, 159, 97, 27);
		}
		return txtNuevoContacto;
	}
	private JButton getBtAceptarCambio() {
		if (btAceptarCambio == null) {
			btAceptarCambio = new JButton("Aceptar cambio");
			btAceptarCambio.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					new AddContactoCitaAction(numCita, getTxtNuevoContacto().getText()).execute();
					dispose();
				}
			});
			btAceptarCambio.setBounds(277, 158, 147, 27);
		}
		return btAceptarCambio;
	}
	private JButton getBtAceptar() {
		if (btAceptar == null) {
			btAceptar = new JButton("Aceptar");
			btAceptar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					new AddContactoCitaAction(numCita, getTxtContacto().getText());
					dispose();
				}
			});
			btAceptar.setBounds(277, 105, 147, 27);
		}
		return btAceptar;
	}
	private JButton getBtSalir() {
		if (btSalir == null) {
			btSalir = new JButton("Salir");
			btSalir.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					dispose();
				}
			});
			btSalir.setBounds(315, 228, 97, 27);
		}
		return btSalir;
	}
}
