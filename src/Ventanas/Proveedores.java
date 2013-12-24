package Ventanas;

import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JSplitPane;
import javax.swing.JList;
import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JScrollBar;
import javax.swing.JTextField;
import javax.swing.JTable;
import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import javax.swing.JLabel;
import java.awt.Component;
import java.awt.ComponentOrientation;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;


public class Proveedores {

	private JFrame frame;
	private JList list;
	private JTextField textField;
	private JTextField textField_nombre;
	private JTextField textField_codigo;
	/**
	 * Launch the application.
	 */

	/**
	 * Create the application.
	 */
	public Proveedores() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(0, 0, Toolkit.getDefaultToolkit().getScreenSize().width, Toolkit.getDefaultToolkit().getScreenSize().height);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.getContentPane().setLayout(null);
		
	
		JButton btnNuevo = new JButton("Nuevo");
		btnNuevo.setBounds(46, 11, 122, 69);
		frame.getContentPane().add(btnNuevo);
		
		JButton btnEditar = new JButton("Editar");
		btnEditar.setBounds(1146, 351, 89, 23);
		frame.getContentPane().add(btnEditar);
		
		JButton btnBorrar = new JButton("Borrar");
		btnBorrar.setBounds(1245, 351, 89, 23);
		frame.getContentPane().add(btnBorrar);
		
		JButton btnMostrar = new JButton("Mostrar");
		btnMostrar.setBounds(847, 91, 122, 39);
		frame.getContentPane().add(btnMostrar);
		
		textField = new JTextField();
		textField.setBounds(194, 91, 614, 39);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBounds(46, 385, 1288, 314);
		frame.getContentPane().add(panel);
		
		JLabel lblCodigo = new JLabel("C\u00F3digo");
		lblCodigo.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		panel.add(lblCodigo);
		
		textField_codigo = new JTextField();
		textField_codigo.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		textField_codigo.setAlignmentX(Component.RIGHT_ALIGNMENT);
		panel.add(textField_codigo);
		textField_codigo.setColumns(5);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		panel.add(lblNombre);
		
		textField_nombre = new JTextField();
		textField_nombre.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		textField_nombre.setAlignmentX(Component.LEFT_ALIGNMENT);
		panel.add(textField_nombre);
		textField_nombre.setColumns(50);
		
		JLabel lblPoblacion = new JLabel("Poblaci\u00F3n");
		panel.add(lblPoblacion);
		
		//String[] poblaciones={"Donostia-San Sebastián","Pasajes San Pedro","Ondarroa","Hondarribia","Pasajes San Juan"};
		
		JComboBox comboBox = new JComboBox();
		comboBox.setAutoscrolls(true);
		comboBox.setEditable(true);
		comboBox.setMaximumRowCount(5);
		comboBox.setAlignmentX(Component.LEFT_ALIGNMENT);
		panel.add(comboBox);
		comboBox.addItem("Donostia-San Sebastián");
		comboBox.addItem("Pasajes San Pedro");
		comboBox.addItem("Hondarribia");
		comboBox.addItem("Ondarroa");
		comboBox.addItem("Hendaya");
		
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(166, 166, 824, 174);
		frame.getContentPane().add(scrollPane);
		
		list = new JList();
		scrollPane.setViewportView(list);
		
		frame.setVisible(true);
		
	
	}
}
