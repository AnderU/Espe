package Ventanas;


import java.awt.Color;
import java.awt.Component;
import java.awt.ComponentOrientation;
import java.awt.Toolkit;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import BaseDatos.ConectorBD;
import Clases.Poblacion;
import Clases.ProveedorC;
import Clases.Provincia;

import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.ImageIcon;
import java.awt.SystemColor;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.InputMethodListener;
import java.awt.event.InputMethodEvent;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;
import javax.swing.UIManager;
import javax.swing.JTextPane;
import java.awt.Dimension;
import javax.swing.JCheckBox;


public class Proveedores {

	private JFrame frmProvdor;
	private JList<ProveedorC> list;
	private JTextField textField_mostrar;
	private JTextField textField_nombre;
	private JTextField textField_correo;
	private JTextField textField_telefono2;
	private JTextField textField_telefono1;
	private JTextField textField_direccion;
	
	private JPanel panel_datos;
	private JButton btnMostrar;
	private JButton btnAceptar;
	private JButton btnCancelar;
	private DefaultListModel<ProveedorC> listModel;
	private JButton btnBorrar;
	private JButton btnEditar;
	private JScrollPane scrollPane;
	private JButton btnNuevo ;
	private JLabel lblNombre;
	private JLabel lblCorreo;
	private JLabel lblDireccion;
	private JLabel lblTelefono1;
	private JLabel lblTelefono2;
	private JLabel lblPoblacion;
	private JComboBox<Poblacion> comboBoxPoblacion;
	private JComboBox<Provincia> comboBoxProvincia; 
	private JButton btnCancelar_edit;
	private JButton btnAceptar_edit;
	private JLabel lblCp;
	private JTextField textField_cp;
	private JTextField textField_fax;
	private JTextField textField_web;
	private JTextField textField_nif;
	private JTextField textField_cuentaCorriente;
	private JTextField textField_banco;
	private JPanel panel_observaciones;
	private JTextPane textPane_observaciones;
	private JCheckBox chckbxUsaCajas;

	
	/**
	 * Launch the application.
	 */

	/**
	 * Create the application.
	 */
	public Proveedores() {
		initialize();
	}
	
	public void setEstadoInicial()
	{
		btnNuevo.setVisible(true);
		btnNuevo.setEnabled(true);
		btnEditar.setEnabled(false);
		btnBorrar.setEnabled(false);
		btnEditar.setVisible(true);
		btnBorrar.setVisible(true);
		list.setEnabled(true);
		
		btnAceptar.setVisible(false);
		btnCancelar.setVisible(false);
		btnAceptar_edit.setVisible(false);
		btnCancelar_edit.setVisible(false);
		
		textField_nombre.setEditable(false);
		textField_correo.setEditable(false);
		textField_telefono2.setEditable(false);
		textField_telefono1.setEditable(false);
		textField_direccion.setEditable(false);
		textField_fax.setEditable(false);
		textField_web.setEditable(false);
		textField_cp.setEditable(false);
		textField_nif.setEditable(false);
		textField_cuentaCorriente.setEditable(false);
		textField_banco.setEditable(false);
		textPane_observaciones.setEditable(false);
		comboBoxPoblacion.setEnabled(false);
		comboBoxProvincia.setEnabled(false);
		
		list.clearSelection();
		
		textField_nombre.setText("");
		textField_correo.setText("");
		textField_telefono2.setText("");
		textField_telefono1.setText("");
		textField_direccion.setText("");
		textField_fax.setText("");
		textField_web.setText("");
		textField_cp.setText("");
		textField_nif.setText("");
		textField_cuentaCorriente.setText("");
		textField_banco.setText("");
		textPane_observaciones.setText("");
		comboBoxPoblacion.setSelectedIndex(0);
		comboBoxProvincia.setSelectedIndex(0);	
		chckbxUsaCajas.setEnabled(false);
	}
	
	public void setEstadoNuevo()
	{
		btnNuevo.setEnabled(false);
		btnAceptar.setVisible(true);
		btnCancelar.setVisible(true);
		list.setEnabled(false);
		
		textField_nombre.setEditable(true);
		textField_correo.setEditable(true);
		textField_telefono2.setEditable(true);
		textField_telefono1.setEditable(true);
		textField_direccion.setEditable(true);
		textField_fax.setEditable(true);
		textField_web.setEditable(true);
		textField_cp.setEditable(true);
		textField_nif.setEditable(true);
		textField_cuentaCorriente.setEditable(true);
		textField_banco.setEditable(true);
		textPane_observaciones.setEditable(true);
		comboBoxPoblacion.setEnabled(true);
		comboBoxProvincia.setEnabled(true);
		
		textField_nombre.setText("");
		textField_correo.setText("");
		textField_telefono2.setText("");
		textField_telefono1.setText("");
		textField_direccion.setText("");
		textField_fax.setText("");
		textField_web.setText("");
		textField_cp.setText("");
		textField_nif.setText("");
		textField_cuentaCorriente.setText("");
		textField_banco.setText("");
		textPane_observaciones.setText("");
		comboBoxPoblacion.setSelectedIndex(0);
		comboBoxProvincia.setSelectedIndex(0);
		
		btnEditar.setEnabled(false);
		btnBorrar.setEnabled(false);
		chckbxUsaCajas.setEnabled(true);
	}
	
	public void setEstadoSeleccion()
	{
		btnAceptar.setVisible(false);
		btnCancelar.setVisible(false);
		btnAceptar_edit.setVisible(false);
		btnCancelar_edit.setVisible(false);
		btnEditar.setEnabled(true);
		btnBorrar.setEnabled(true);
	}
	
	public void setEstadoEditar()
	{
		textField_nombre.setEditable(true);
		textField_correo.setEditable(true);
		textField_telefono2.setEditable(true);
		textField_telefono1.setEditable(true);
		textField_direccion.setEditable(true);
		textField_fax.setEditable(true);
		textField_web.setEditable(true);
		textField_cp.setEditable(true);
		textField_nif.setEditable(true);
		textField_cuentaCorriente.setEditable(true);
		textField_banco.setEditable(true);
		textPane_observaciones.setEditable(true);
		comboBoxPoblacion.setEnabled(true);
		comboBoxProvincia.setEnabled(true);
				
		list.setEnabled(false);
		btnAceptar_edit.setVisible(true);
		btnCancelar_edit.setVisible(true);
		btnNuevo.setEnabled(false);
		btnBorrar.setEnabled(false);
		btnEditar.setEnabled(false);
		chckbxUsaCajas.setEnabled(true);
	}
	
	/**
	 * Initialize the contents of the frame.
	 */
	@SuppressWarnings("serial")
	private void initialize() {
		frmProvdor = new JFrame();
		frmProvdor.setIconImage(Toolkit.getDefaultToolkit().getImage(Proveedores.class.getResource("/Imagenes/Animals-Fish-icon.png")));
		frmProvdor.getContentPane().setBackground(SystemColor.textHighlight);
		frmProvdor.setBounds(0, 0, Toolkit.getDefaultToolkit().getScreenSize().width, Toolkit.getDefaultToolkit().getScreenSize().height);
		frmProvdor.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frmProvdor.getContentPane().setLayout(null);
		frmProvdor.setVisible(true);
		frmProvdor.setTitle("Proveedores");
		
		
//****************** NUEVO ************************************		
		btnNuevo = new JButton("");
		btnNuevo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setEstadoNuevo();
			}
		});
		btnNuevo.setIcon(new ImageIcon(Proveedores.class.getResource("/Imagenes/Add-icon.png")));
		btnNuevo.setToolTipText("Nuevo");
		btnNuevo.setBounds(10, 11, 80, 55);
		frmProvdor.getContentPane().add(btnNuevo);
		btnNuevo.setVisible(true);
		
//****************** PANEL DATOS ************************************	
		panel_datos = new JPanel();
		panel_datos.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_datos.setBounds(10, 310, 1330, 385);
		frmProvdor.getContentPane().add(panel_datos);
		panel_datos.setLayout(null);
		
//****************** NOMBRE ************************************		
		lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(20, 23, 57, 14);
		lblNombre.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		panel_datos.add(lblNombre);
		
		textField_nombre = new JTextField();
		textField_nombre.setBounds(117, 20, 894, 20);
		textField_nombre.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		textField_nombre.setAlignmentX(Component.LEFT_ALIGNMENT);
		panel_datos.add(textField_nombre);
		textField_nombre.setColumns(50);

		
//****************** CORREO ************************************		
		lblCorreo = new JLabel("Correo");
		lblCorreo.setBounds(20, 60, 57, 14);
		lblCorreo.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		panel_datos.add(lblCorreo);
		
		textField_correo = new JTextField();
		textField_correo.setBounds(117, 57, 894, 20);
		textField_correo.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		textField_correo.setAlignmentX(Component.LEFT_ALIGNMENT);
		panel_datos.add(textField_correo);
		textField_correo.setColumns(50);

		
//****************** TELEFONOS ************************************		
		lblTelefono1 = new JLabel("Telefono1");
		lblTelefono1.setBounds(20, 100, 57, 14);
		lblTelefono1.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		panel_datos.add(lblTelefono1);
		
		textField_telefono1 = new JTextField();
		textField_telefono1.setBounds(117, 97, 205, 20);
		textField_telefono1.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		textField_telefono1.setAlignmentX(Component.LEFT_ALIGNMENT);
		panel_datos.add(textField_telefono1);
		textField_telefono1.setColumns(50);


		lblTelefono2 = new JLabel("Telefono2");
		lblTelefono2.setBounds(357, 100, 90, 14);
		lblTelefono2.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		panel_datos.add(lblTelefono2);
		
		textField_telefono2 = new JTextField();
		textField_telefono2.setBounds(450, 97, 205, 20);
		textField_telefono2.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		textField_telefono2.setAlignmentX(Component.LEFT_ALIGNMENT);
		panel_datos.add(textField_telefono2);
		textField_telefono2.setColumns(50);
		
//******************* POBLACION Y PROVINCIA***********************************	
		Poblacion auxPobl= new Poblacion(); 
		ResultSet rsPobl=ConectorBD.bdMySQL.Select("poblaciones", "*", "true");
		Vector<Poblacion> elementosPobl= new Vector<Poblacion>();
		elementosPobl.addElement(auxPobl);
		try {
			while (rsPobl.next())
			{
				Poblacion aPobl= new Poblacion(); 
				aPobl.setId(rsPobl.getObject(1).toString());
				aPobl.setPoblacion(rsPobl.getObject(2).toString());
				elementosPobl.addElement(aPobl);
			}
		} catch (SQLException e1Pobl) {
			// TODO Auto-generated catch block
			e1Pobl.printStackTrace();
		}
		
		Provincia auxProv= new Provincia(); 
		ResultSet rsProv=ConectorBD.bdMySQL.Select("provincias", "*", "true");
		Vector<Provincia> elementosProv= new Vector<Provincia>();
		elementosProv.addElement(auxProv);
		try {
			while (rsProv.next())
			{
				Provincia aProv= new Provincia(); 
				aProv.setId(rsProv.getObject(1).toString());
				aProv.setProvincia(rsProv.getObject(2).toString());
				elementosProv.addElement(aProv);
			}
		} catch (SQLException e1Prov) {
			// TODO Auto-generated catch block
			e1Prov.printStackTrace();
		}

//****************** EDITAR ************************************	
	btnEditar = new JButton("");
	btnEditar.setToolTipText("Editar");
	btnEditar.setIcon(new ImageIcon(Proveedores.class.getResource("/Imagenes/Pencil-icon.png")));
	btnEditar.setBounds(1092, 20, 80, 55);
	panel_datos.add(btnEditar);
	btnEditar.setVisible(false);
	btnEditar.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) 
		{
			
			if (!list.isSelectionEmpty())
			{
				textField_nombre.setText(list.getSelectedValue().getProveedor());	
				setEstadoEditar();
			}
			
		}
	});
	
//****************** BORRAR ************************************	
	btnBorrar = new JButton("");
	btnBorrar.setIcon(new ImageIcon(Proveedores.class.getResource("/Imagenes/Trash-icon.png")));
	btnBorrar.setToolTipText("Borrar");
	btnBorrar.setBounds(1199, 20, 80, 55);
	panel_datos.add(btnBorrar);
	btnBorrar.setVisible(false);
	btnBorrar.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) 
		{
			int result=JOptionPane.showConfirmDialog(frmProvdor, "El proveedor seleccionado se borrará de la base de datos. ¿Estás segur@?", "¡Atención!",JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE);
			if (result==JOptionPane.OK_OPTION)
			{
				ProveedorC aux= new ProveedorC();
				aux.setId(list.getSelectedValue().getId());
				aux.Delete();
				
			}
			setEstadoInicial();
		}
	});
		
	
//****************** ACEPTAR ************************************			
	btnAceptar = new JButton("");
	btnAceptar.setBounds(1092, 94, 80, 55);
	panel_datos.add(btnAceptar);
	btnAceptar.setIcon(new ImageIcon(Proveedores.class.getResource("/Imagenes/Accept-icon.png")));
	btnAceptar.setToolTipText("Aceptar");
	btnAceptar.setVisible(false);
	
	btnAceptar.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) 
		{
			
			if (!textField_nombre.getText().equals(""))
			{
				ProveedorC aux= new ProveedorC();
				aux.setProveedor(textField_nombre.getText());
				aux.setDireccion(textField_direccion.getText());
				aux.setCorreo(textField_correo.getText());
				aux.setTelefono1(textField_telefono1.getText());
				aux.setTelefono2(textField_telefono2.getText());
				aux.setFax(textField_fax.getText());
				aux.setWeb(textField_web.getText());
				aux.setCp(textField_cp.getText());
				aux.setNif(textField_nif.getText());
				aux.setCuentaCorriente(textField_cuentaCorriente.getText());
				aux.setBanco(textField_banco.getText());
				aux.setObservaciones(textPane_observaciones.getText());
				if (chckbxUsaCajas.isSelected())
					aux.setUsaCajas("1");
				else
					aux.setUsaCajas("0");
				if (comboBoxProvincia.getSelectedIndex()!=0)
					aux.setIdProvincia(Integer.toString(comboBoxProvincia.getSelectedIndex()));
				if (comboBoxPoblacion.getSelectedIndex()!=0)
					aux.setIdPoblacion(Integer.toString(comboBoxPoblacion.getSelectedIndex()));
				aux.Insert();
				listModel.addElement(aux);
			}		
			setEstadoInicial();
			
		}
	});

	
//****************** ACEPTAR EDIT************************************	
	btnAceptar_edit = new JButton("");
	btnAceptar_edit.setBounds(1092, 94, 80, 55);
	panel_datos.add(btnAceptar_edit);
	btnAceptar_edit.setIcon(new ImageIcon(Proveedores.class.getResource("/Imagenes/Accept-icon.png")));
	btnAceptar_edit.setToolTipText("Aceptar");
	btnAceptar_edit.setVisible(false);
	btnAceptar_edit.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			
			if (!textField_nombre.getText().equals(""))
			{				
				ProveedorC aux= new ProveedorC();
				aux.setProveedor(textField_nombre.getText());
				aux.setDireccion(textField_direccion.getText());
				aux.setCorreo(textField_correo.getText());
				aux.setTelefono1(textField_telefono1.getText());
				aux.setTelefono2(textField_telefono2.getText());
				aux.setFax(textField_fax.getText());
				aux.setWeb(textField_web.getText());
				aux.setCp(textField_cp.getText());
				aux.setNif(textField_nif.getText());
				aux.setCuentaCorriente(textField_cuentaCorriente.getText());
				aux.setBanco(textField_banco.getText());
				aux.setObservaciones(textPane_observaciones.getText());
				if (chckbxUsaCajas.isSelected())
					aux.setUsaCajas("1");
				else
					aux.setUsaCajas("0");
				if (comboBoxProvincia.getSelectedIndex()!=0)
					aux.setIdProvincia(Integer.toString(comboBoxProvincia.getSelectedIndex()));
				aux.setId(list.getSelectedValue().getId());
				if (comboBoxPoblacion.getSelectedIndex()!=0)
					aux.setIdPoblacion(Integer.toString(comboBoxPoblacion.getSelectedIndex()));
				aux.Update();
				listModel.remove(list.getSelectedIndex());
				listModel.addElement(aux);
			}				
			setEstadoInicial();
		}
	});

	
//****************** CANCELAR ************************************	
	btnCancelar = new JButton("");
	btnCancelar.setBounds(1199, 94, 80, 55);
	panel_datos.add(btnCancelar);
	btnCancelar.setToolTipText("Cancelar");
	btnCancelar.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			setEstadoInicial();
		}
	});
	btnCancelar.setIcon(new ImageIcon(Proveedores.class.getResource("/Imagenes/Delete-icon.png")));
	btnCancelar.setVisible(false);
	
//****************** CANCELAR EDIT ************************************	
	btnCancelar_edit = new JButton("");
	btnCancelar_edit.setBounds(1199, 94, 80, 55);
	panel_datos.add(btnCancelar_edit);
	btnCancelar_edit.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			setEstadoInicial();			
		}
	});
	btnCancelar_edit.setIcon(new ImageIcon(Proveedores.class.getResource("/Imagenes/Delete-icon.png")));
	btnCancelar_edit.setToolTipText("Cancelar");
	btnCancelar_edit.setVisible(false);
	

//****************** DIRECCION ************************************	
	JPanel panel_direccion = new JPanel();
	panel_direccion.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Direcci\u00F3n", TitledBorder.LEADING, TitledBorder.TOP, null, Color.BLACK));
	panel_direccion.setToolTipText("");
	panel_direccion.setBounds(10, 165, 1001, 88);
	panel_datos.add(panel_direccion);
	panel_direccion.setLayout(null);
			
	lblDireccion = new JLabel("Domicilio");
	lblDireccion.setBounds(10, 25, 90, 14);
	panel_direccion.add(lblDireccion);
	lblDireccion.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
	
	lblCp = new JLabel("C.P.");
	lblCp.setBounds(10, 56, 90, 14);
	lblCp.setHorizontalAlignment(SwingConstants.LEFT);
	panel_direccion.add(lblCp);
	
	textField_direccion = new JTextField();
	textField_direccion.setBounds(107, 22, 884, 20);
	panel_direccion.add(textField_direccion);
	textField_direccion.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
	textField_direccion.setAlignmentX(Component.LEFT_ALIGNMENT);
	textField_direccion.setColumns(50);
	
	textField_cp = new JTextField();
	textField_cp.setBounds(107, 53, 205, 20);
	panel_direccion.add(textField_cp);
	textField_cp.setColumns(10);
	
			
//****************** POBLACION ************************************
		lblPoblacion = new JLabel("Poblaci\u00F3n");
		lblPoblacion.setBounds(347, 56, 90, 14);
		panel_direccion.add(lblPoblacion);
		comboBoxPoblacion = new JComboBox<Poblacion>(elementosPobl);
		comboBoxPoblacion.setBounds(440, 53, 205, 20);
		panel_direccion.add(comboBoxPoblacion);
		comboBoxPoblacion.setVisible(true);
		comboBoxPoblacion.setAutoscrolls(true);
		comboBoxPoblacion.setMaximumRowCount(5);
		comboBoxPoblacion.setAlignmentX(Component.LEFT_ALIGNMENT);
		
//****************** PROVINCIA ************************************
		JLabel lblProvincia = new JLabel("Provincia");
		lblProvincia.setBounds(680, 56, 90, 14);
		panel_direccion.add(lblProvincia);
		comboBoxProvincia = new JComboBox<Provincia>(elementosProv);
		comboBoxProvincia.setBounds(786, 53, 205, 20);
		panel_direccion.add(comboBoxProvincia);
		comboBoxProvincia.setVisible(true);
		comboBoxProvincia.setAutoscrolls(true);
		comboBoxProvincia.setMaximumRowCount(5);
		comboBoxProvincia.setAlignmentX(Component.LEFT_ALIGNMENT);
		
//****************** FAX ************************************
		JLabel lblFax = new JLabel("Fax");
		lblFax.setBounds(20, 135, 90, 14);
		panel_datos.add(lblFax);
		
		textField_fax = new JTextField();
		textField_fax.setBounds(117, 132, 205, 20);
		panel_datos.add(textField_fax);
		textField_fax.setColumns(10);
		
//****************** WEB ************************************		
		JLabel lblWeb = new JLabel("Web");
		lblWeb.setBounds(357, 135, 90, 14);
		panel_datos.add(lblWeb);
		
		textField_web = new JTextField();
		textField_web.setBounds(450, 132, 205, 20);
		panel_datos.add(textField_web);
		textField_web.setColumns(10);

		
//****************** DATOS FINANCIEROS ************************************		
		JPanel panel_banco = new JPanel();
		panel_banco.setBorder(new TitledBorder(null, "Datos Bancarios", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_banco.setBounds(10, 272, 1001, 88);
		panel_datos.add(panel_banco);
		panel_banco.setLayout(null);
		
		JLabel lblNif = new JLabel("NIF");
		lblNif.setBounds(10, 25, 90, 14);
		panel_banco.add(lblNif);
		
		textField_nif = new JTextField();
		textField_nif.setBounds(107, 22, 205, 20);
		panel_banco.add(textField_nif);
		textField_nif.setColumns(10);
		
		JLabel lblCuentaCorriente = new JLabel("CC");
		lblCuentaCorriente.setBounds(10, 56, 90, 14);
		panel_banco.add(lblCuentaCorriente);
		
		textField_cuentaCorriente = new JTextField();
		textField_cuentaCorriente.setBounds(107, 53, 884, 20);
		panel_banco.add(textField_cuentaCorriente);
		textField_cuentaCorriente.setColumns(10);
		
		JLabel lblBanco = new JLabel("Banco");
		lblBanco.setBounds(347, 25, 90, 14);
		panel_banco.add(lblBanco);
		
		textField_banco = new JTextField();
		textField_banco.setBounds(440, 22, 551, 20);
		panel_banco.add(textField_banco);
		textField_banco.setColumns(10);
	
		
//****************** OBSERVACIONES ************************************
		panel_observaciones = new JPanel();
		panel_observaciones.setBorder(new TitledBorder(null, "Observaciones", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_observaciones.setBounds(1031, 165, 289, 195);
		panel_datos.add(panel_observaciones);
		panel_observaciones.setLayout(null);
		
		textPane_observaciones = new JTextPane();
		textPane_observaciones.setMaximumSize(new Dimension(269, 162));
		//textPane_observaciones.setHorizontalAlignment(SwingConstants.LEFT);
		textPane_observaciones.setBounds(10, 22, 269, 162);
		panel_observaciones.add(textPane_observaciones);
		
		chckbxUsaCajas = new JCheckBox("Usa Cajas");
		chckbxUsaCajas.setBounds(701, 96, 97, 23);
		panel_datos.add(chckbxUsaCajas);
		
		
//****************** LISTA ************************************	
	listModel = new DefaultListModel<ProveedorC>();
			
	ResultSet rs1=ConectorBD.bdMySQL.Select("proveedores", "*", "true");
	try {
		while (rs1.next())
		{
			ProveedorC aux1= new ProveedorC();
			aux1.setId(Integer.parseInt(rs1.getObject(1).toString()));
			aux1.setProveedor(rs1.getObject(2).toString());
			aux1.setIdPoblacion(rs1.getObject(3).toString());
			aux1.setDireccion(rs1.getObject(4).toString());
			aux1.setTelefono1(rs1.getObject(5).toString());
			aux1.setTelefono2(rs1.getObject(6).toString());
			aux1.setCorreo(rs1.getObject(7).toString());
			aux1.setFax(rs1.getObject(8).toString());
			aux1.setWeb(rs1.getObject(9).toString());
			aux1.setCp(rs1.getObject(10).toString());
			aux1.setIdProvincia(rs1.getObject(11).toString());
			aux1.setNif(rs1.getObject(12).toString());
			aux1.setCuentaCorriente(rs1.getObject(13).toString());
			aux1.setBanco(rs1.getObject(14).toString());
			aux1.setObservaciones(rs1.getObject(15).toString());
			aux1.setUsaCajas(rs1.getObject(16).toString());
			listModel.addElement(aux1);
		}
	}
	catch (SQLException e)
	{
					// TODO Auto-generated catch block
					e.printStackTrace();
	}
			
			
	scrollPane = new JScrollPane();
	scrollPane.setBounds(10, 77,836,222);
	frmProvdor.getContentPane().add(scrollPane);	
	scrollPane.setEnabled(true);
	
	
	list = new JList<ProveedorC>(listModel);
	scrollPane.setViewportView(list);
	list.addListSelectionListener(new ListSelectionListener() {
		public void valueChanged(ListSelectionEvent e) {
			
			if (!list.isSelectionEmpty())
			{
				textField_nombre.setText(list.getSelectedValue().getProveedor());
				textField_direccion.setText(list.getSelectedValue().getDireccion());
				textField_fax.setText(list.getSelectedValue().getFax());
				textField_correo.setText(list.getSelectedValue().getCorreo());
				textField_telefono1.setText(list.getSelectedValue().getTelefono1());
				textField_telefono2.setText(list.getSelectedValue().getTelefono2());
				textField_web.setText(list.getSelectedValue().getWeb());
				textField_cp.setText(list.getSelectedValue().getCp());
				textField_nif.setText(list.getSelectedValue().getNif());
				textField_cuentaCorriente.setText(list.getSelectedValue().getCuentaCorriente());
				textField_banco.setText(list.getSelectedValue().getBanco());
				textPane_observaciones.setText(list.getSelectedValue().getObservaciones());
				if (list.getSelectedValue().getUsaCajas().equals("true"))
					chckbxUsaCajas.setSelected(true);
				else
					chckbxUsaCajas.setSelected(false);
				comboBoxProvincia.setSelectedIndex(Integer.parseInt(list.getSelectedValue().getIdProvincia()));
				comboBoxPoblacion.setSelectedIndex(Integer.parseInt(list.getSelectedValue().getIdPoblacion()));
				setEstadoSeleccion();
			}
		}
	});		
	
	list.setCellRenderer(new DefaultListCellRenderer()
	{
	public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus)
		{
		Component renderer = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
        if (renderer instanceof JLabel && value instanceof ProveedorC)
        	{
        		((JLabel) renderer).setText(((ProveedorC) value).getProveedor());
        	}
        return renderer;
        }
    });
	
//****************** PANEL BUSCAR ************************************	
	JPanel panel_buscar = new JPanel();
	panel_buscar.setName("Filtros");
	panel_buscar.setBorder(new TitledBorder(null, "Filtros", TitledBorder.LEADING, TitledBorder.TOP, null, null));
	panel_buscar.setBounds(856, 77, 484, 222);
	frmProvdor.getContentPane().add(panel_buscar);
	panel_buscar.setLayout(null);
	
	textField_mostrar = new JTextField();
	textField_mostrar.addMouseListener(new MouseAdapter() {
		@Override
		public void mouseReleased(MouseEvent e) {
			setEstadoInicial();
		}
	});
	textField_mostrar.setBounds(10, 22, 464, 20);
	panel_buscar.add(textField_mostrar);
	textField_mostrar.setColumns(10);
	textField_mostrar.setEditable(true);
	
	btnMostrar = new JButton("");
	btnMostrar.setBounds(10, 156, 80, 55);
	panel_buscar.add(btnMostrar);
	btnMostrar.setIcon(new ImageIcon(Proveedores.class.getResource("/Imagenes/Search-icon.png")));
	btnMostrar.setToolTipText("Buscar");
	btnMostrar.setVisible(true);
	
	btnMostrar.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) 
		{
			listModel.removeAllElements();
			ResultSet rs=ConectorBD.bdMySQL.Select("proveedores", "*", "Proveedor LIKE '%"+textField_mostrar.getText()+"%'");
			try {
				while (rs.next())
					{
						ProveedorC aux= new ProveedorC();
						aux.setId(Integer.parseInt(rs.getObject(1).toString()));
						aux.setProveedor(rs.getObject(2).toString());
						aux.setIdPoblacion(rs.getObject(3).toString());
						aux.setDireccion(rs.getObject(4).toString());
						aux.setTelefono1(rs.getObject(5).toString());
						aux.setTelefono2(rs.getObject(6).toString());
						aux.setCorreo(rs.getObject(7).toString());
						aux.setFax(rs.getObject(8).toString());
						aux.setWeb(rs.getObject(9).toString());
						aux.setCp(rs.getObject(10).toString());
						aux.setIdProvincia(rs.getObject(11).toString());
						aux.setNif(rs.getObject(12).toString());
						aux.setCuentaCorriente(rs.getObject(13).toString());
						aux.setBanco(rs.getObject(14).toString());
						aux.setObservaciones(rs.getObject(15).toString());
						listModel.addElement(aux);
					}
				}
			catch (SQLException e1)
				{
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			setEstadoInicial();
		}
	});
	
	

	
	this.setEstadoInicial();
	
	
	}
}
