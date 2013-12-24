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
import java.awt.SystemColor;
import javax.swing.ImageIcon;


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
		frame.getContentPane().setBackground(SystemColor.textHighlight);
		frame.setBounds(0, 0, Toolkit.getDefaultToolkit().getScreenSize().width, Toolkit.getDefaultToolkit().getScreenSize().height);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.getContentPane().setLayout(null);
		
	
		JButton btnNuevo = new JButton("");
		btnNuevo.setIcon(new ImageIcon(Proveedores.class.getResource("/Imagenes/Add-icon.png")));
		btnNuevo.setToolTipText("Nuevo");
		btnNuevo.setBounds(10, 11, 80, 55);
		frame.getContentPane().add(btnNuevo);
		
		JButton btnMostrar = new JButton("");
		btnMostrar.setIcon(new ImageIcon(Proveedores.class.getResource("/Imagenes/Search-icon.png")));
		btnMostrar.setToolTipText("Buscar");
		btnMostrar.setBounds(634, 91, 80, 55);
		frame.getContentPane().add(btnMostrar);
		
		textField = new JTextField();
		textField.setBounds(10, 91, 614, 55);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBounds(10, 351, 1330, 344);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblCodigo = new JLabel("C\u00F3digo");
		lblCodigo.setBounds(10, 10, 33, 14);
		lblCodigo.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		panel.add(lblCodigo);
		
		textField_codigo = new JTextField();
		textField_codigo.setBounds(53, 7, 46, 20);
		textField_codigo.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		textField_codigo.setAlignmentX(Component.RIGHT_ALIGNMENT);
		panel.add(textField_codigo);
		textField_codigo.setColumns(5);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(109, 10, 37, 14);
		lblNombre.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		panel.add(lblNombre);
		
		textField_nombre = new JTextField();
		textField_nombre.setBounds(156, 7, 406, 20);
		textField_nombre.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		textField_nombre.setAlignmentX(Component.LEFT_ALIGNMENT);
		panel.add(textField_nombre);
		textField_nombre.setColumns(50);
		
		JLabel lblPoblacion = new JLabel("Poblaci\u00F3n");
		lblPoblacion.setBounds(572, 10, 45, 14);
		panel.add(lblPoblacion);
		
		//String[] poblaciones={"Donostia-San Sebastián","Pasajes San Pedro","Ondarroa","Hondarribia","Pasajes San Juan"};
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(627, 7, 139, 20);
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
		
		JButton btnEditar = new JButton("");
		btnEditar.setToolTipText("Editar");
		btnEditar.setIcon(new ImageIcon(Proveedores.class.getResource("/Imagenes/Pencil-icon.png")));
		btnEditar.setBounds(1150, 6, 80, 55);
		panel.add(btnEditar);
		
		JButton btnBorrar = new JButton("");
		btnBorrar.setIcon(new ImageIcon(Proveedores.class.getResource("/Imagenes/Delete-icon.png")));
		btnBorrar.setToolTipText("Borrar");
		btnBorrar.setBounds(1240, 6, 80, 55);
		panel.add(btnBorrar);
		
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 164, 746, 174);
		frame.getContentPane().add(scrollPane);
		
		list = new JList();
		scrollPane.setViewportView(list);
		
		frame.setVisible(true);
		
	
	}
}
