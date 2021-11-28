package igu.Ventanas;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JDialog;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JTextField;


import Logica.Vacuna;
import Logica.crud.commands.ListDiagnostico;

import Logica.FileUtil;
import Logica.crud.commands.ListAllCitasById;
import Logica.crud.dto.*;
import igu.action.*;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JList;

public class VentanaPrescripcion extends JDialog {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JPanel pnPrescripcion;
	private JLabel lbPrescripcionExistentes;
	private JComboBox<String> cbPrescripciones;
	private JLabel lblCrearPrescripcion;
	private JTextField txtPrescripcion;
	private JButton btPrescripcionExistente;
	private JButton btCrearPrescripcion;
	private JLabel lbMensaje;
	private List<DiagnosticoDto> lista;
	
	List<CitaDto> prescripciones;
	

	static String id;
	private JScrollPane scEnfermedad;
	private JList<String> listEnfermedad;
	private JLabel lbEnfermedades;

	CitaDto cita;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaPrescripcion frame = new VentanaPrescripcion(id);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	
	public VentanaPrescripcion(String id) {
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.id=id;
		this.prescripciones = new ListAllPrescripcionAction().execute();
		getContentPane().add(getPnPrescripcion(), BorderLayout.CENTER);
	}
	

	private JPanel getPnPrescripcion() {
		if (pnPrescripcion == null) {
			pnPrescripcion = new JPanel();
			setBounds(100,100,797,470);
			pnPrescripcion.setLayout(null);
			pnPrescripcion.add(getLbPrescripcionExistentes());
			pnPrescripcion.add(getCbPrescripciones());
			pnPrescripcion.add(getLblCrearPrescripcion());
			pnPrescripcion.add(getTxtPrescripcion());
			pnPrescripcion.add(getBtPrescripcionExistente());
			pnPrescripcion.add(getBtCrearPrescripcion());
			pnPrescripcion.add(getLbMensaje());
			pnPrescripcion.add(getScEnfermedad());
			pnPrescripcion.add(getLbEnfermedades());
		}
		return pnPrescripcion;
	}
	private JLabel getLbPrescripcionExistentes() {
		if (lbPrescripcionExistentes == null) {
			lbPrescripcionExistentes = new JLabel("Prescripciones Existentes:");
			lbPrescripcionExistentes.setBounds(441, 28, 174, 30);
		}
		return lbPrescripcionExistentes;
	}
	private JComboBox<String> getCbPrescripciones() {
		if (cbPrescripciones == null) {
			cbPrescripciones = new JComboBox<String>();
			cbPrescripciones.setMaximumRowCount(100);
			cbPrescripciones.setBackground(Color.WHITE);
			String[] prescripcionstr = prescripcionToString(prescripciones);
			cbPrescripciones.setModel(new DefaultComboBoxModel<String>(prescripcionstr));
			cbPrescripciones.setBounds(453, 70, 226, 30);
		}
		return cbPrescripciones;
	}
	
	private String[] prescripcionToString(List<CitaDto> prescripcion) {
		String[] strPrescripcion = new String[prescripcion.size()];
		for(int i=0;i<prescripcion.size();i++) 
		{
				strPrescripcion[i] =prescripcion.get(i).preescripcion;
		}
		return strPrescripcion;
	}
	
	private JLabel getLblCrearPrescripcion() {
		if (lblCrearPrescripcion == null) {
			lblCrearPrescripcion = new JLabel("Crear Prescripcion:");
			lblCrearPrescripcion.setBounds(443, 152, 138, 30);
		}
		return lblCrearPrescripcion;
	}
	private JTextField getTxtPrescripcion() {
		if (txtPrescripcion == null) {
			txtPrescripcion = new JTextField();
			txtPrescripcion.setColumns(10);
			txtPrescripcion.setBounds(453, 191, 226, 30);
		}
		return txtPrescripcion;
	}
	private JButton getBtPrescripcionExistente() {
		if (btPrescripcionExistente == null) {
			btPrescripcionExistente = new JButton("Usar Prescripcion");
			btPrescripcionExistente.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(getCbPrescripciones().getSelectedItem()!=null) {
						CitaDto cita = prescripciones.get(getCbPrescripciones().getSelectedIndex());
						new AddPrescripcionAction(cita.idPaciente,cita.preescripcion).execute();
						new UpdatePrescripcionInHistorialAction(cita.idPaciente, cita.preescripcion).execute();
						FileUtil.escribirLog("MiLogger", "Medico ID: 1"+"null -> " + cita.preescripcion); 
					}
					else
						lbMensaje.setText("Selecciona una prescripci�n.");
				}
			});
			btPrescripcionExistente.setBounds(463, 111, 165, 30);
		}
		return btPrescripcionExistente;
	}
	private JButton getBtCrearPrescripcion() {
		if (btCrearPrescripcion == null) {
			btCrearPrescripcion = new JButton("Usar Prescripcion");
			btCrearPrescripcion.setBounds(463, 232, 165, 30);
			btCrearPrescripcion.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					prescripcion();
				}
			});
		}
		return btCrearPrescripcion;
	}
	
	private void prescripcion() {
		try {
			String txt = getTxtPrescripcion().getText();
			if(txt.equals("") || txt==null)
				JOptionPane.showMessageDialog(this, "Prescripcion vacia.");
			else {
				new AddPrescripcionAction(id, txt).execute();
				new UpdatePrescripcionInHistorialAction(lista.get(listEnfermedad.getSelectedIndex()).id,txt ).execute();
				lbMensaje.setText("Prescripcion a�adida con exito.");
				FileUtil.escribirLog("MiLogger", "Medico ID: 1"+"null -> " + txt); 
			}
		}catch(NullPointerException e) {
			JOptionPane.showMessageDialog(this, "Prescripcion vacia.");
		}
	}
	private JLabel getLbMensaje() {
		if (lbMensaje == null) {
			lbMensaje = new JLabel("");
			lbMensaje.setBounds(443, 230, 200, 22);
		}
		return lbMensaje;
	}
	private JScrollPane getScEnfermedad() {
		if (scEnfermedad == null) {
			scEnfermedad = new JScrollPane();
			scEnfermedad.setBounds(42, 70, 275, 209);
			scEnfermedad.setViewportView(getListEnfermedad());
		}
		return scEnfermedad;
	}
	private JList<String> getListEnfermedad() {
		if (listEnfermedad == null) {
			listEnfermedad = new JList<String>();
			lista = new ListDiagnostico(id).execute();
			listEnfermedad.setModel(new DefaultComboBoxModel<String>(getDiagnostico(lista)));

		}
		return listEnfermedad;
	}
	
	private String[] getDiagnostico(List<DiagnosticoDto> lista) {
		//List<CitaDto> lista = new ListAllCitasByIdAction(idMedico).execute();
		String[] diagnosticos = new String[lista.size()];
		for (int i = 0; i < diagnosticos.length; i++) {
			String diagnostico = lista.get(i).diagnostico;
			diagnosticos[i]=diagnostico;
		}
		return diagnosticos;
	}
	private JLabel getLbEnfermedades() {
		if (lbEnfermedades == null) {
			lbEnfermedades = new JLabel("Enfermedades:");
			lbEnfermedades.setBounds(42, 37, 144, 13);
		}
		return lbEnfermedades;
	}
}
