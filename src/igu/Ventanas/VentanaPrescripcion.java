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

import Logica.crud.dto.*;
import igu.action.*;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;

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
	
	List<CitaDto> prescripciones;
	
	static String id;
	
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
			setBounds(100,100,450,320);
			pnPrescripcion.setLayout(null);
			pnPrescripcion.add(getLbPrescripcionExistentes());
			pnPrescripcion.add(getCbPrescripciones());
			pnPrescripcion.add(getLblCrearPrescripcion());
			pnPrescripcion.add(getTxtPrescripcion());
			pnPrescripcion.add(getBtPrescripcionExistente());
			pnPrescripcion.add(getBtCrearPrescripcion());
			pnPrescripcion.add(getLbMensaje());
		}
		return pnPrescripcion;
	}
	private JLabel getLbPrescripcionExistentes() {
		if (lbPrescripcionExistentes == null) {
			lbPrescripcionExistentes = new JLabel("Prescripciones Existentes:");
			lbPrescripcionExistentes.setBounds(29, 11, 174, 30);
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
			cbPrescripciones.setBounds(41, 53, 226, 30);
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
			lblCrearPrescripcion.setBounds(31, 135, 138, 30);
		}
		return lblCrearPrescripcion;
	}
	private JTextField getTxtPrescripcion() {
		if (txtPrescripcion == null) {
			txtPrescripcion = new JTextField();
			txtPrescripcion.setColumns(10);
			txtPrescripcion.setBounds(41, 174, 226, 30);
		}
		return txtPrescripcion;
	}
	private JButton getBtPrescripcionExistente() {
		if (btPrescripcionExistente == null) {
			btPrescripcionExistente = new JButton("Usar Prescripcion");
			btPrescripcionExistente.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(getCbPrescripciones().getSelectedItem()!=null) {
						CitaDto presc = prescripciones.get(getCbPrescripciones().getSelectedIndex());
						new AddPrescripcionAction(id,presc.preescripcion).execute();
						new UpdatePrescripcionInHistorialAction( id, presc.preescripcion).execute();
					}
					else
						lbMensaje.setText("Selecciona una prescripci�n.");
				}
			});
			btPrescripcionExistente.setBounds(51, 94, 165, 30);
		}
		return btPrescripcionExistente;
	}
	private JButton getBtCrearPrescripcion() {
		if (btCrearPrescripcion == null) {
			btCrearPrescripcion = new JButton("Usar Prescripcion");
			btCrearPrescripcion.setBounds(51, 215, 165, 30);
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
				new UpdatePrescripcionInHistorialAction(id,txt ).execute();
				lbMensaje.setText("Prescripcion a�adida con exito.");
			}
		}catch(NullPointerException e) {
			JOptionPane.showMessageDialog(this, "Prescripcion vacia.");
		}
	}
	private JLabel getLbMensaje() {
		if (lbMensaje == null) {
			lbMensaje = new JLabel("");
			lbMensaje.setBounds(31, 213, 200, 22);
		}
		return lbMensaje;
	}
	
}
