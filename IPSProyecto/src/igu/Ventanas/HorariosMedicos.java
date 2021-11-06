package igu.Ventanas;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JDialog;
import java.awt.GridLayout;

public class HorariosMedicos extends JDialog {

	private JPanel contentPane;
	private JPanel PanelPrincipal;
	private JPanel panelBotones;
	private JPanel panelInformacion;
	private JPanel panelMedicosSeleccionados;
	private JPanel panelAcciones;

	/**
	 * Create the frame.
	 */
	public HorariosMedicos() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 497, 371);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		contentPane.add(getPanelPrincipal());
		contentPane.add(getPanelBotones(), BorderLayout.SOUTH);
	
	}
	private JPanel getPanelPrincipal() {
		if (PanelPrincipal == null) {
			PanelPrincipal = new JPanel();
			PanelPrincipal.setLayout(new GridLayout(3, 0, 0, 0));
			PanelPrincipal.add(getPanelMedicosSeleccionados());
			PanelPrincipal.add(getPanelAcciones());
			PanelPrincipal.add(getPanelInformacion());
		}
		return PanelPrincipal;
	}
	private JPanel getPanelBotones() {
		if (panelBotones == null) {
			panelBotones = new JPanel();
		}
		return panelBotones;
	}
	private JPanel getPanelInformacion() {
		if (panelInformacion == null) {
			panelInformacion = new JPanel();
		}
		return panelInformacion;
	}
	private JPanel getPanelMedicosSeleccionados() {
		if (panelMedicosSeleccionados == null) {
			panelMedicosSeleccionados = new JPanel();
		}
		return panelMedicosSeleccionados;
	}
	private JPanel getPanelAcciones() {
		if (panelAcciones == null) {
			panelAcciones = new JPanel();
		}
		return panelAcciones;
	}
}
