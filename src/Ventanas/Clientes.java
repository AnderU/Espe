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
import Clases.ClienteC;

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


public class Clientes {

	private JFrame frmProvdor;
	private JList<ClienteC> list;
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
	private DefaultListModel<ClienteC> listModel;
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
	private JButton btnCancelar_edit;
	private JButton btnAceptar_edit;
	private JLabel lblCp;
	private JTextField textField_cp;
	private JTextField textField_fax;
	private JTextField textField_web;
	private JTextField textField_nif;
	private JTextField textField_cuentaCorriente;
	private JTextField textField_Banco;
	private JPanel panel_observaciones;
	private JTextField textField_observaciones;
	
	/**
	 * Launch the application.
	 */

	/**
	 * Create the application.
	 */
	public Clientes() {
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
		comboBoxPoblacion.setEnabled(false);
		btnMostrar.setEnabled(true);
		list.clearSelection();
		textField_nombre.setText("");
		textField_correo.setText("");
		textField_telefono2.setText("");
		textField_telefono1.setText("");
		textField_direccion.setText("");
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
		frmProvdor.setIconImage(Toolkit.getDefaultToolkit().getImage(Clientes.class.getResource("/Imagenes/Animals-Fish-icon.png")));
		frmProvdor.getContentPane().setBackground(SystemColor.textHighlight);
		frmProvdor.setBounds(0, 0, Toolkit.getDefaultToolkit().getScreenSize().width, Toolkit.getDefaultToolkit().getScreenSize().height);
		frmProvdor.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frmProvdor.getContentPane().setLayout(null);
		frmProvdor.setVisible(true);
		frmProvdor.setTitle("Clientes");
		
		
//****************** NUEVO ************************************		
		btnNuevo = new JButton("");
		btnNuevo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setEstadoNuevo();
			}
		});
		btnNuevo.setIcon(new ImageIcon(Clientes.class.getResource("/Imagenes/Add-icon.png")));
		btnNuevo.setToolTipText("Nuevo");
		btnNuevo.setBounds(10, 11, 80, 55);
		frmProvdor.getContentPane().add(btnNuevo);
		btnNuevo.setVisible(true);
		
	
		
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

//****************** EDITAR ************************************	
	btnEditar = new JButton("");
	btnEditar.setToolTipText("Editar");
	btnEditar.setIcon(new ImageIcon(Clientes.class.getResource("/Imagenes/Pencil-icon.png")));
	btnEditar.setBounds(1092, 20, 80, 55);
	panel_datos.add(btnEditar);
	btnEditar.setVisible(false);
	btnEditar.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) 
		{
			
			if (!list.isSelectionEmpty())
			{
				textField_nombre.setText(list.getSelectedValue().getCliente());	
				setEstadoEditar();
			}
			
		}
	});
	
	//****************** BORRAR ************************************	
		btnBorrar = new JButton("");
		btnBorrar.setIcon(new ImageIcon(Clientes.class.getResource("/Imagenes/Trash-icon.png")));
		btnBorrar.setToolTipText("Borrar");
		btnBorrar.setBounds(1199, 20, 80, 55);
		panel_datos.add(btnBorrar);
		
	
//****************** ACEPTAR ************************************			
	btnAceptar = new JButton("");
	btnAceptar.setBounds(1092, 94, 80, 55);
	panel_datos.add(btnAceptar);
	btnAceptar.setIcon(new ImageIcon(Clientes.class.getResource("/Imagenes/Accept-icon.png")));
	
	btnAceptar_edit = new JButton("");
	btnAceptar_edit.setBounds(1092, 94, 80, 55);
	panel_datos.add(btnAceptar_edit);
	btnAceptar_edit.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			
			if (!textField_nombre.getText().equals(""))
			{				
				ClienteC aux= new ClienteC();
				aux.setCliente(textField_nombre.getText());
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
	btnAceptar_edit.setIcon(new ImageIcon(Clientes.class.getResource("/Imagenes/Accept-icon.png")));
	btnAceptar_edit.setToolTipText("Aceptar");
	
	
//****************** CANCELAR ************************************	
	btnCancelar = new JButton("");
	btnCancelar.setBounds(1199, 94, 80, 55);
	panel_datos.add(btnCancelar);
	btnCancelar.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			setEstadoInicial();
		}
	});
	btnCancelar.setIcon(new ImageIcon(Clientes.class.getResource("/Imagenes/Delete-icon.png")));
	
	btnCancelar_edit = new JButton("");
	btnCancelar_edit.setBounds(1199, 94, 80, 55);
	panel_datos.add(btnCancelar_edit);
	btnCancelar_edit.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			setEstadoInicial();			
			
		}
	});
	btnCancelar_edit.setIcon(new ImageIcon(Clientes.class.getResource("/Imagenes/Delete-icon.png")));
	btnCancelar_edit.setToolTipText("Cancelar");
	
	JPanel panel_direccion = new JPanel();
	panel_direccion.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Direcci\u00F3n", TitledBorder.LEADING, TitledBorder.TOP, null, Color.BLACK));
	panel_direccion.setToolTipText("");
	panel_direccion.setBounds(10, 165, 1001, 88);
	panel_datos.add(panel_direccion);
					panel_direccion.setLayout(null);
			
			
			//****************** DIRECCION ************************************		
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
			comboBoxPoblacion = new JComboBox<Poblacion>(elementos);
			comboBoxPoblacion.setBounds(440, 53, 205, 20);
			panel_direccion.add(comboBoxPoblacion);
			comboBoxPoblacion.setVisible(true);
			comboBoxPoblacion.setAutoscrolls(true);
			comboBoxPoblacion.setMaximumRowCount(5);
			comboBoxPoblacion.setAlignmentX(Component.LEFT_ALIGNMENT);
			
			JLabel lblProvincia = new JLabel("Provincia");
			lblProvincia.setBounds(680, 56, 90, 14);
			panel_direccion.add(lblProvincia);
			
			JComboBox comboBox = new JComboBox();
			comboBox.setBounds(786, 53, 205, 20);
			panel_direccion.add(comboBox);
			
			JLabel lblFax = new JLabel("Fax");
			lblFax.setBounds(20, 135, 90, 14);
			panel_datos.add(lblFax);
			
			textField_fax = new JTextField();
			textField_fax.setBounds(117, 132, 205, 20);
			panel_datos.add(textField_fax);
			textField_fax.setColumns(10);
			
			JLabel lblWeb = new JLabel("Web");
			lblWeb.setBounds(357, 135, 90, 14);
			panel_datos.add(lblWeb);
			
			textField_web = new JTextField();
			textField_web.setBounds(450, 132, 205, 20);
			panel_datos.add(textField_web);
			textField_web.setColumns(10);
			
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
			
			textField_Banco = new JTextField();
			textField_Banco.setBounds(440, 22, 551, 20);
			panel_banco.add(textField_Banco);
			textField_Banco.setColumns(10);
			
			panel_observaciones = new JPanel();
			panel_observaciones.setBorder(new TitledBorder(null, "Observaciones", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			panel_observaciones.setBounds(1031, 165, 289, 195);
			panel_datos.add(panel_observaciones);
			panel_observaciones.setLayout(null);
			
			textField_observaciones = new JTextField();
			textField_observaciones.setBounds(10, 22, 269, 162);
			textField_observaciones.setText("");
			panel_observaciones.add(textField_observaciones);
			textField_observaciones.setColumns(10);
	btnCancelar_edit.setVisible(false);
	btnCancelar.setVisible(false);
	btnAceptar_edit.setVisible(false);
	btnAceptar.setVisible(false);
	
	btnAceptar.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) 
		{
			
			if (!textField_nombre.getText().equals(""))
			{
				ClienteC aux= new ClienteC();
				aux.setCliente(textField_nombre.getText());
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
			int result=JOptionPane.showConfirmDialog(frmProvdor, "El cliente seleccionado se borrar� de la base de datos. �Est�s segur@?", "�Atenci�n!",JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE);
			if (result==JOptionPane.OK_OPTION)
			{
				ClienteC aux= new ClienteC();
				aux.setId(list.getSelectedValue().getId());
				aux.Delete();
				
			}
			setEstadoInicial();
		}
	});
		
		
		
//****************** LISTA ************************************	
	listModel = new DefaultListModel<ClienteC>();
			
	ResultSet rs1=ConectorBD.bdMySQL.Select("clientes", "*", "true");
	try {
		while (rs1.next())
		{
			ClienteC aux1= new ClienteC();
			aux1.setCliente(rs1.getObject(2).toString());
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
	
	
	list = new JList<ClienteC>(listModel);
	scrollPane.setViewportView(list);
	list.addListSelectionListener(new ListSelectionListener() {
		public void valueChanged(ListSelectionEvent e) {
			
			if (!list.isSelectionEmpty())
			{
				textField_nombre.setText(list.getSelectedValue().getCliente());
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
        if (renderer instanceof JLabel && value instanceof ClienteC)
        	{
        		((JLabel) renderer).setText(((ClienteC) value).getCliente());
        	}
        return renderer;
        }
    });
	
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
	
		
			
		
	//****************** MOSTRAR ************************************
			btnMostrar = new JButton("");
			btnMostrar.setBounds(10, 156, 80, 55);
			panel_buscar.add(btnMostrar);
			btnMostrar.setIcon(new ImageIcon(Clientes.class.getResource("/Imagenes/Search-icon.png")));
			btnMostrar.setToolTipText("Buscar");
			btnMostrar.setVisible(true);
			
			btnMostrar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) 
				{
					listModel.removeAllElements();
					ResultSet rs=ConectorBD.bdMySQL.Select("clientes", "*", "Cliente LIKE '%"+textField_mostrar.getText()+"%'");
					try {
						while (rs.next())
							{
								ClienteC aux= new ClienteC();
								aux.setCliente(rs.getObject(2).toString());
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
