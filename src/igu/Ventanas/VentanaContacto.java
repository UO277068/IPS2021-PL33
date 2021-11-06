package igu.Ventanas;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import Logica.crud.dto.MedicoDto;
import Logica.crud.dto.PacienteDto;
import igu.action.AddContactoCitaAction;
import igu.action.GetContactoByIdPacienteAction;
import javax.swing.JComboBox;

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
	
	PacienteDto paciente;
	int idCita;
	
	String[] listPrefijos;

	
	private VentanaCreaCitas ventanaPrincipal;
	private JComboBox<String> cbPrefijos;
	
	public VentanaContacto(VentanaCreaCitas vRober,int idCita, PacienteDto paciente) {
		this.ventanaPrincipal = vRober;
		this.paciente = paciente;
		this.idCita=idCita;
		
		listPrefijos = inicializaPrefijos();
		
		getContentPane().setLayout(null);
		setBounds(100,100,501,340);
		getContentPane().add(getLbPaciente());
		getContentPane().add(getTxtPaciente());
		getContentPane().add(getLbContacto());
		getContentPane().add(getTxtContacto());
		getContentPane().add(getLbNuevoContacto());
		getContentPane().add(getTxtNuevoContacto());
		getContentPane().add(getBtAceptarCambio());
		getContentPane().add(getBtAceptar());
		getContentPane().add(getBtSalir());
		getContentPane().add(getCbPrefijos());
		
		
		
		
	}
	private String[] inicializaPrefijos() {
		String[] ret = new String[10];
		ret[0]="+34"; ret[1]="";
		ret[2]= "+1"; ret[3]= "+49";
		ret[4]= "+39"; ret[5]= "+44";
		ret[6]="+7";   ret[7]="+351";
		
		return ret;
	}
	private JLabel getLbPaciente() {
		if (lbPaciente == null) {
			lbPaciente = new JLabel("Paciente:");
			lbPaciente.setBounds(34, 45, 86, 21);
		}
		return lbPaciente;
	}
	
	public VentanaCreaCitas getVentanaPrincipal() {
		return ventanaPrincipal;
	}
	
	private JTextField getTxtPaciente() {
		if (txtPaciente == null) {
			txtPaciente = new JTextField();
			txtPaciente.setText("" + paciente.name + " " + paciente.surname);
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
			String contacto = paciente.contacto;
			txtContacto.setText(contacto);
			txtContacto.setBackground(Color.WHITE);
			txtContacto.setEditable(false);
			txtContacto.setBounds(158, 106, 147, 27);
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
			txtNuevoContacto.setBounds(158, 211, 147, 27);
		}
		return txtNuevoContacto;
	}
	private JButton getBtAceptarCambio() {
		if (btAceptarCambio == null) {
			btAceptarCambio = new JButton("Aceptar cambio");
			btAceptarCambio.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String prefijo = getCbPrefijos().getSelectedItem().toString();
					if(!prefijo.matches("[+-]?\\d{1,5}"))
						JOptionPane.showMessageDialog(null, "El prefijo seleccionado no es valido.");
					else if(!getTxtNuevoContacto().getText().matches("\\d*") ||getTxtNuevoContacto().getText().equals("")) 
						JOptionPane.showMessageDialog(null, "El número de teléfono no es valido.");
					else {
						prefijo+= getTxtNuevoContacto().getText();
						new AddContactoCitaAction(idCita, prefijo).execute();
						dispose();
					}
				}
			});
			btAceptarCambio.setBounds(328, 159, 147, 27);
		}
		return btAceptarCambio;
	}
	private JButton getBtAceptar() {
		if (btAceptar == null) {
			btAceptar = new JButton("Aceptar");
			btAceptar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(!getTxtContacto().getText().matches("[+-]?\\d*") || getTxtContacto().getText().equals("")) 
						JOptionPane.showMessageDialog(null, "El número de teléfono del paciente no es valido.");
					else{
						new AddContactoCitaAction(idCita, getTxtContacto().getText()).execute();;
						dispose();
					}
				}
			});
			btAceptar.setBounds(328, 106, 147, 27);
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
			btSalir.setBounds(378, 251, 97, 27);
		}
		return btSalir;
	}
	private JComboBox<String> getCbPrefijos() {
		if (cbPrefijos == null) {
			cbPrefijos = new JComboBox();
			cbPrefijos.setEditable(true);
			cbPrefijos.setBounds(91, 213, 57, 22);
			cbPrefijos.setModel(new DefaultComboBoxModel<String>((listPrefijos)));
		}
		return cbPrefijos;
	}
}
