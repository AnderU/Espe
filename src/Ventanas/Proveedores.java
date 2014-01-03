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


public class Proveedores {

	private JFrame frmProvdor;
	private JList<ProveedorC> list;
	private JTextField textField_mostrar;
	private JTextField textField_nombre;
	private JTextField textField_codigo;
	private JTextField textField_correo;
	private JTextField textField_telefono2;
	private JTextField textField_telefono1;
	private JTextField textField_direccion;
	
	private JPanel panel;
	private JButton btnMostrar;
	private JButton btnAceptar;
	private JButton btnCancelar;
	private DefaultListModel<ProveedorC> listModel;
	private JButton btnBorrar;
	private JButton btnEditar;
	private JScrollPane scrollPane;
	private JButton btnNuevo ;
	private JLabel lblCodigo;
	private JLabel lblNombre;
	private JLabel lblCorreo;
	private JLabel lblDireccion;
	private JLabel lblTelefono1;
	private JLabel lblTelefono2;
	private JLabel lblPoblacion;
	private JComboBox<Poblacion> comboBoxPoblacion;
	private JButton btnCancelar_edit;
	private JButton btnAceptar_edit;
	
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
		textField_codigo.setEditable(false);
		textField_correo.setEditable(false);
		textField_telefono2.setEditable(false);
		textField_telefono1.setEditable(false);
		textField_direccion.setEditable(false);
		comboBoxPoblacion.setEnabled(false);
		btnMostrar.setEnabled(true);
		list.clearSelection();
		textField_nombre.setText("");
		textField_correo.setText("");
		textField_telefono2.setText("");
		textField_telefono1.setText("");
		textField_direccion.setText("");
		textField_codigo.setText("");
		comboBoxPoblacion.setSelectedIndex(0);
		System.out.println("eI");
		
		
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
		comboBoxPoblacion.setEnabled(true);
		textField_nombre.setText("");
		textField_correo.setText("");
		textField_telefono2.setText("");
		textField_telefono1.setText("");
		textField_direccion.setText("");
		textField_codigo.setText("");
		comboBoxPoblacion.setSelectedIndex(0);
		
		btnEditar.setEnabled(false);
		btnBorrar.setEnabled(false);
		btnMostrar.setEnabled(false);
		System.out.println("en");
		
	}
	
	public void setEstadoSeleccion()
	{

		btnAceptar.setVisible(false);
		btnCancelar.setVisible(false);
		btnAceptar_edit.setVisible(false);
		btnCancelar_edit.setVisible(false);
		btnEditar.setEnabled(true);
		btnBorrar.setEnabled(true);
		btnMostrar.setEnabled(false);
		System.out.println("eS");
		
		
	}
	
	public void setEstadoEditar()
	{
		textField_nombre.setEditable(true);
		textField_correo.setEditable(true);
		textField_telefono2.setEditable(true);
		textField_telefono1.setEditable(true);
		textField_direccion.setEditable(true);
		comboBoxPoblacion.setEnabled(true);
		list.setEnabled(false);
		btnAceptar_edit.setVisible(true);
		btnCancelar_edit.setVisible(true);
		btnNuevo.setEnabled(false);
		btnBorrar.setEnabled(false);
		btnEditar.setEnabled(false);
		btnMostrar.setEnabled(false);
		System.out.println("eE");
		
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
		
	
		
		panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBounds(10, 310, 1330, 385);
		frmProvdor.getContentPane().add(panel);
		panel.setLayout(null);
		
//****************** CODIGO ************************************
		lblCodigo = new JLabel("C\u00F3digo");
		lblCodigo.setBounds(10, 10, 46, 14);
		lblCodigo.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		panel.add(lblCodigo);
		
		textField_codigo = new JTextField();
		textField_codigo.setBounds(70, 7, 46, 20);
		textField_codigo.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		textField_codigo.setAlignmentX(Component.RIGHT_ALIGNMENT);
		panel.add(textField_codigo);
		textField_codigo.setColumns(5);
		textField_codigo.setEditable(false);
		
//****************** NOMBRE ************************************		
		lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(139, 10, 57, 14);
		lblNombre.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		panel.add(lblNombre);
		
		textField_nombre = new JTextField();
		textField_nombre.setBounds(206, 7, 406, 20);
		textField_nombre.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		textField_nombre.setAlignmentX(Component.LEFT_ALIGNMENT);
		panel.add(textField_nombre);
		textField_nombre.setColumns(50);

		
//****************** CORREO ************************************		
		lblCorreo = new JLabel("Correo");
		lblCorreo.setBounds(574, 86, 57, 14);
		lblCorreo.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		panel.add(lblCorreo);
		
		textField_correo = new JTextField();
		textField_correo.setBounds(620, 83, 246, 20);
		textField_correo.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		textField_correo.setAlignmentX(Component.LEFT_ALIGNMENT);
		panel.add(textField_correo);
		textField_correo.setColumns(50);


//****************** DIRECCION ************************************		
		lblDireccion = new JLabel("Direccion");
		lblDireccion.setBounds(10, 49, 57, 14);
		lblDireccion.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		panel.add(lblDireccion);
		
		textField_direccion = new JTextField();
		textField_direccion.setBounds(70, 47, 542, 20);
		textField_direccion.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		textField_direccion.setAlignmentX(Component.LEFT_ALIGNMENT);
		panel.add(textField_direccion);
		textField_direccion.setColumns(50);

		
//****************** TELEFONOS ************************************		
		lblTelefono1 = new JLabel("Telefono1");
		lblTelefono1.setBounds(10, 86, 57, 14);
		lblTelefono1.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		panel.add(lblTelefono1);
		
		textField_telefono1 = new JTextField();
		textField_telefono1.setBounds(70, 83, 187, 20);
		textField_telefono1.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		textField_telefono1.setAlignmentX(Component.LEFT_ALIGNMENT);
		panel.add(textField_telefono1);
		textField_telefono1.setColumns(50);


		lblTelefono2 = new JLabel("Telefono2");
		lblTelefono2.setBounds(290, 86, 57, 14);
		lblTelefono2.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		panel.add(lblTelefono2);
		
		textField_telefono2 = new JTextField();
		textField_telefono2.setBounds(357, 83, 187, 20);
		textField_telefono2.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		textField_telefono2.setAlignmentX(Component.LEFT_ALIGNMENT);
		panel.add(textField_telefono2);
		textField_telefono2.setColumns(50);

		
//****************** POBLACION ************************************
		lblPoblacion = new JLabel("Poblaci\u00F3n");
		lblPoblacion.setBounds(639, 10, 89, 14);
		panel.add(lblPoblacion);
	
		Poblacion aux= new Poblacion(); 
		ResultSet rs=ConectorBD.bdMySQL.Select("poblaciones", "*", "true");
		Vector<Poblacion> elementos= new Vector<Poblacion>();
		elementos.addElement(aux);
		try {
			while (rs.next())
			{
				Poblacion a= new Poblacion(); 
				a.setId(rs.getObject(1).toString());
				a.setPoblacion(rs.getObject(2).toString());
				elementos.addElement(a);
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		comboBoxPoblacion = new JComboBox<Poblacion>(elementos);
		comboBoxPoblacion.setBounds(696, 7, 170, 20);
		comboBoxPoblacion.setVisible(true);
		comboBoxPoblacion.setAutoscrolls(true);
		comboBoxPoblacion.setMaximumRowCount(5);
		comboBoxPoblacion.setAlignmentX(Component.LEFT_ALIGNMENT);
		panel.add(comboBoxPoblacion);	

//****************** EDITAR ************************************	
	btnEditar = new JButton("");
	btnEditar.setToolTipText("Editar");
	btnEditar.setIcon(new ImageIcon(Proveedores.class.getResource("/Imagenes/Pencil-icon.png")));
	btnEditar.setBounds(1150, 6, 80, 55);
	panel.add(btnEditar);
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
		btnBorrar.setBounds(1240, 6, 80, 55);
		panel.add(btnBorrar);
		
	
//****************** ACEPTAR ************************************			
	btnAceptar = new JButton("");
	btnAceptar.setBounds(1150, 75, 80, 55);
	panel.add(btnAceptar);
	btnAceptar.setIcon(new ImageIcon(Proveedores.class.getResource("/Imagenes/Accept-icon.png")));
	
	btnAceptar_edit = new JButton("");
	btnAceptar_edit.setBounds(1150, 75, 80, 55);
	panel.add(btnAceptar_edit);
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
	btnAceptar_edit.setIcon(new ImageIcon(Proveedores.class.getResource("/Imagenes/Accept-icon.png")));
	btnAceptar_edit.setToolTipText("Aceptar");
	
	
//****************** CANCELAR ************************************	
	btnCancelar = new JButton("");
	btnCancelar.setBounds(1240, 75, 80, 55);
	panel.add(btnCancelar);
	btnCancelar.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			setEstadoInicial();
		}
	});
	btnCancelar.setIcon(new ImageIcon(Proveedores.class.getResource("/Imagenes/Cancel-icon.png")));
	
	btnCancelar_edit = new JButton("");
	btnCancelar_edit.setBounds(1240, 75, 80, 55);
	panel.add(btnCancelar_edit);
	btnCancelar_edit.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			setEstadoInicial();			
			
		}
	});
	btnCancelar_edit.setIcon(new ImageIcon(Proveedores.class.getResource("/Imagenes/Cancel-icon.png")));
	btnCancelar_edit.setToolTipText("Cancelar");
	btnCancelar_edit.setVisible(false);
	btnCancelar.setVisible(false);
	btnAceptar_edit.setVisible(false);
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
				if (comboBoxPoblacion.getSelectedIndex()!=0)
					aux.setIdPoblacion(Integer.toString(comboBoxPoblacion.getSelectedIndex()));
				aux.Insert();
				listModel.addElement(aux);
			}		
			setEstadoInicial();
			
		}
	});
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
		
		
		
//****************** LISTA ************************************	
	listModel = new DefaultListModel<ProveedorC>();
			
	ResultSet rs1=ConectorBD.bdMySQL.Select("proveedores", "*", "true");
	try {
		while (rs1.next())
		{
			ProveedorC aux1= new ProveedorC();
			aux1.setProveedor(rs1.getObject(2).toString());
			aux1.setId(Integer.parseInt(rs1.getObject(1).toString()));
			aux1.setIdPoblacion(rs1.getObject(3).toString());
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
				setEstadoSeleccion();
				textField_codigo.setText(Integer.toString(list.getSelectedValue().getId()));
				textField_nombre.setText(list.getSelectedValue().getProveedor());
				comboBoxPoblacion.setSelectedIndex(Integer.parseInt(list.getSelectedValue().getIdPoblacion()));
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
	
	JPanel panel_1 = new JPanel();
	panel_1.setName("Filtros");
	panel_1.setBorder(new LineBorder(new Color(0, 0, 0)));
	panel_1.setBounds(856, 77, 484, 222);
	frmProvdor.getContentPane().add(panel_1);
	panel_1.setLayout(null);
	
	textField_mostrar = new JTextField();
	textField_mostrar.addMouseListener(new MouseAdapter() {
		@Override
		public void mouseReleased(MouseEvent e) {
			setEstadoInicial();
		}
	});
	textField_mostrar.setBounds(10, 27, 337, 20);
	panel_1.add(textField_mostrar);
	textField_mostrar.setColumns(10);
	textField_mostrar.setEditable(true);
	
		
			
		
	//****************** MOSTRAR ************************************
			btnMostrar = new JButton("");
			btnMostrar.setBounds(10, 156, 80, 55);
			panel_1.add(btnMostrar);
			btnMostrar.setIcon(new ImageIcon(Proveedores.class.getResource("/Imagenes/Search-icon.png")));
			btnMostrar.setToolTipText("Buscar");
			
			JLabel lblFiltros = new JLabel("Filtros");
			lblFiltros.setBounds(10, 11, 46, 14);
			panel_1.add(lblFiltros);
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
								aux.setProveedor(rs.getObject(2).toString());
								aux.setId(Integer.parseInt(rs.getObject(1).toString()));
								aux.setIdPoblacion(rs.getObject(3).toString());
								listModel.addElement(aux);
							}
						}
					catch (SQLException e1)
						{
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
				}
			});
	
	
//------------------------------------------
	
	this.setEstadoInicial();
	
	
//-------------------------------------------	
	}
}
