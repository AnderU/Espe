package Ventanas;


import java.awt.Color;
import java.awt.Component;
import java.awt.ComponentOrientation;
import java.awt.Toolkit;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.ComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import BaseDatos.ConectorBD;
import Clases.ProveedorC;

import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import java.awt.SystemColor;


public class Proveedores {

	private JFrame frame;
	private JList<ProveedorC> list;
	private JTextField textField;
	private JTextField textField_nombre;
	private JTextField textField_codigo;
	
	private JPanel panel;
	private JButton btnMostrar;
	//private JButton btnAceptar;
	//private JButton btnCancelar;
	private DefaultListModel<ProveedorC> listModel;
	private JButton btnBorrar;
	private JButton btnEditar;
	private JScrollPane scrollPane;
	private JButton btnNuevo ;
	private JLabel lblCodigo;
	private JLabel lblNombre;
	private JLabel lblPoblacion;
	private JComboBox<String> comboBox;
	
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
		
		
		
		
		frame.getContentPane().addMouseListener(new MouseAdapter()
		{
			public void mouseClicked(MouseEvent e)
			{
				list.clearSelection();
				btnEditar.setVisible(false);
				btnBorrar.setVisible(false);
				btnNuevo.setVisible(true);
			}
		});
		
		//listModel = new DefaultListModel<ProveedorC>();
		
		ResultSet rs=ConectorBD.bdMySQL.Select("proveedor", "*", "true");
		try {
			while (rs.next())
			{
					ProveedorC aux= new ProveedorC();
					aux.setProveedor(rs.getObject(2).toString());
					aux.setId(Integer.parseInt(rs.getObject(1).toString()));
					aux.setPoblacion(rs.getObject(3).toString());
					listModel.addElement(aux);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	
		
//****************** NUEVO ************************************		
		btnNuevo = new JButton("");
		btnNuevo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnNuevo.setIcon(new ImageIcon(Proveedores.class.getResource("/Imagenes/Add-icon.png")));
		btnNuevo.setToolTipText("Nuevo");
		btnNuevo.setBounds(10, 11, 80, 55);
		frame.getContentPane().add(btnNuevo);
		
//****************** MOSTRAR ************************************
		btnMostrar = new JButton("");
		btnMostrar.setIcon(new ImageIcon(Proveedores.class.getResource("/Imagenes/Search-icon.png")));
		btnMostrar.setToolTipText("Buscar");
		btnMostrar.setBounds(634, 91, 80, 55);
		frame.getContentPane().add(btnMostrar);
		
		textField = new JTextField();
		textField.setBounds(10, 91, 614, 55);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBounds(10, 351, 1330, 344);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
//****************** CODIGO ************************************
		lblCodigo = new JLabel("C\u00F3digo");
		lblCodigo.setBounds(10, 10, 33, 14);
		lblCodigo.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		panel.add(lblCodigo);
		
		textField_codigo = new JTextField();
		textField_codigo.setBounds(53, 7, 46, 20);
		textField_codigo.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		textField_codigo.setAlignmentX(Component.RIGHT_ALIGNMENT);
		panel.add(textField_codigo);
		textField_codigo.setColumns(5);
		
//****************** NOMBRE ************************************		
		lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(109, 10, 37, 14);
		lblNombre.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		panel.add(lblNombre);
		
		textField_nombre = new JTextField();
		textField_nombre.setBounds(156, 7, 406, 20);
		textField_nombre.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		textField_nombre.setAlignmentX(Component.LEFT_ALIGNMENT);
		panel.add(textField_nombre);
		textField_nombre.setColumns(50);
		
//****************** POBLACION ************************************
		lblPoblacion = new JLabel("Poblaci\u00F3n");
		lblPoblacion.setBounds(572, 10, 45, 14);
		panel.add(lblPoblacion);
		
		//String[] poblaciones={"Donostia-San Sebastián","Pasajes San Pedro","Ondarroa","Hondarribia","Pasajes San Juan"};
		

		comboBox = new JComboBox<String>();
		comboBox.setBounds(627, 7, 139, 20);
		comboBox.setAutoscrolls(true);
		comboBox.setEditable(true);
		comboBox.setMaximumRowCount(5);
		comboBox.setAlignmentX(Component.LEFT_ALIGNMENT);
		panel.add(comboBox);
		//comboBox.addItem("Donostia-San Sebastián");
		//comboBox.addItem("Pasajes San Pedro");
		//comboBox.addItem("Hondarribia");
		//comboBox.addItem("Ondarroa");
		//comboBox.addItem("Hendaya");
		
//****************** EDITAR ************************************	
		btnEditar = new JButton("");
		btnEditar.setToolTipText("Editar");
		btnEditar.setIcon(new ImageIcon(Proveedores.class.getResource("/Imagenes/Pencil-icon.png")));
		btnEditar.setBounds(1150, 6, 80, 55);
		panel.add(btnEditar);

//****************** BORRAR ************************************	
		btnBorrar = new JButton("");
		btnBorrar.setIcon(new ImageIcon(Proveedores.class.getResource("/Imagenes/Delete-icon.png")));
		btnBorrar.setToolTipText("Borrar");
		btnBorrar.setBounds(1240, 6, 80, 55);
		panel.add(btnBorrar);
		
//****************** LISTA ************************************	
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 164, 746, 174);
		frame.getContentPane().add(scrollPane);
		
		list = new JList<ProveedorC>(listModel);
		scrollPane.setViewportView(list);
		list.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				btnEditar.setVisible(true);
				btnBorrar.setVisible(true);
				btnNuevo.setVisible(false);
			}
		});
		
		frame.setVisible(true);
		
	
	}
}
