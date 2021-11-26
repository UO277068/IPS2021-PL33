package igu.Ventanas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Logica.FileUtil;
import Logica.crud.dto.CitaDto;
import igu.action.AddCausasAction;
import igu.action.ListAllCausasAction;
import igu.action.AddDiagnosticoAction;

public class VentanaCausas extends JDialog {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel panel;
	private JLabel lbCausas;
	private JTextField txtCausa;
	private JComboBox<String> cbCausasPredefinidas;
	private JLabel lbCausasPredefinidas;
	private JButton btCausa;
	private JButton btnAsignarCausaPredefinida;
	private JButton btSalir;
	
	List<CitaDto> causas;
	
	String id;
	String idCita;
	
	public VentanaCausas(String id, String idCita) {
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.id=id;
		this.idCita=idCita;
		this.causas = new ListAllCausasAction().execute();
		getContentPane().add(getPanel(), BorderLayout.CENTER);
	}

	private JPanel getPanel() {
		if (panel == null) {
			panel = new JPanel();
			panel.setLayout(null);
			setBounds(100,100,453,321);
			panel.add(getLbCausas());
			panel.add(getTxtCausa());
			panel.add(getCbCausasPredefinidas());
			panel.add(getLbCausasPredefinidas());
			panel.add(getBtCausa());
			panel.add(getBtnAsignarCausaPredefinida());
			panel.add(getBtSalir());
		}
		return panel;
	}
	private JLabel getLbCausas() {
		if (lbCausas == null) {
			lbCausas = new JLabel("Causas de la cita:");
			lbCausas.setBounds(27, 31, 172, 22);
		}
		return lbCausas;
	}
	private JTextField getTxtCausa() {
		if (txtCausa == null) {
			txtCausa = new JTextField();
			txtCausa.setColumns(10);
			txtCausa.setBounds(151, 29, 291, 26);
		}
		return txtCausa;
	}
	private JComboBox<String> getCbCausasPredefinidas() {
		if (cbCausasPredefinidas == null) {
			cbCausasPredefinidas = new JComboBox<String>();
			cbCausasPredefinidas.setMaximumRowCount(100);
			cbCausasPredefinidas.setBackground(Color.WHITE);
			String[] causastr = causasToString(this.causas);
			cbCausasPredefinidas.setModel(new DefaultComboBoxModel<String>(causastr));
			cbCausasPredefinidas.setBounds(37, 142, 360, 29);
		}
		return cbCausasPredefinidas;
	}
	
	private String[] causasToString(List<CitaDto> causa) {
		String[] strCausa = new String[causa.size()];
		for(int i=0;i<causa.size();i++) 
		{
			
			strCausa[i] =causa.get(i).causa;
		}
		return strCausa;
	}
	
	
	private JLabel getLbCausasPredefinidas() {
		if (lbCausasPredefinidas == null) {
			lbCausasPredefinidas = new JLabel("Causas Predefinidas:");
			lbCausasPredefinidas.setBounds(27, 118, 192, 22);
		}
		return lbCausasPredefinidas;
	}
	private JButton getBtCausa() {
		if (btCausa == null) {
			btCausa = new JButton("Asignar Causa");
			btCausa.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					new AddCausasAction(idCita,getTxtCausa().getText()).execute();
					new AddDiagnosticoAction(idCita, id,getTxtCausa().getText()).execute();
					FileUtil.escribirLog("MiLogger", "Medico ID: 1"+"null -> " + getTxtCausa().getText()); 
				}
			});
			btCausa.setBounds(129, 64, 117, 29);
		}
		return btCausa;
	}
	private JButton getBtnAsignarCausaPredefinida() {
		if (btnAsignarCausaPredefinida == null) {
			btnAsignarCausaPredefinida = new JButton("Asignar Causa Predefinida");
			btnAsignarCausaPredefinida.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					CitaDto citaa = causas.get(getCbCausasPredefinidas().getSelectedIndex());
					new AddCausasAction(idCita, citaa.causa).execute();
					FileUtil.escribirLog("MiLogger", "Medico ID: 1"+"null -> " + citaa.causa); 
					//new UpdateCausasInHistorialAction(idCita, id, citaa.causa).execute();
				}
			});
			btnAsignarCausaPredefinida.setBounds(47, 182, 172, 26);
		}
		return btnAsignarCausaPredefinida;
	}
	private JButton getBtSalir() {
		if (btSalir == null) {
			btSalir = new JButton("Salir");
			btSalir.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					dispose();
				}
			});
			btSalir.setBounds(325, 241, 117, 29);
		}
		return btSalir;
	}
}
