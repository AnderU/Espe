package Ventanas;

import java.awt.event.*;
import java.awt.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import BaseDatos.ConectorBD;
import Clases.ClienteC;
import Clases.Poblacion;



public class Clientes {

	private JFrame frmClnt;
	private JList<ClienteC> list;
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
	private DefaultListModel<ClienteC> listModel;
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
	
	public Clientes() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmClnt = new JFrame();
		frmClnt.setBounds(0, 0, Toolkit.getDefaultToolkit().getScreenSize().width, Toolkit.getDefaultToolkit().getScreenSize().height);
		frmClnt.setVisible(true);
		frmClnt.getContentPane().setBackground(SystemColor.textHighlight);
	
		frmClnt.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frmClnt.getContentPane().setLayout(null);
		frmClnt.setTitle("Clientes");
	
	
		//****************** NUEVO ************************************		
				btnNuevo = new JButton("");
				btnNuevo.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) 
					{
						btnAceptar.setVisible(true);
						btnCancelar.setVisible(true);
						list.setEnabled(false);
						btnEditar.setVisible(false);
						btnBorrar.setVisible(false);
						btnMostrar.setEnabled(false);
						textField_mostrar.setEditable(false);
						list.setEnabled(false);
						textField_nombre.setEditable(true);
						comboBoxPoblacion.setEditable(true);
						textField_codigo.setEditable(false);
					}
				});
				btnNuevo.setIcon(new ImageIcon(Clientes.class.getResource("/Imagenes/Add-icon.png")));
				btnNuevo.setToolTipText("Nuevo");
				btnNuevo.setBounds(10, 11, 80, 55);
				frmClnt.getContentPane().add(btnNuevo);
				btnNuevo.setVisible(true);

			
				
			
		//****************** MOSTRAR ************************************
				btnMostrar = new JButton("");
				btnMostrar.setIcon(new ImageIcon(Clientes.class.getResource("/Imagenes/Search-icon.png")));
				btnMostrar.setToolTipText("Buscar");
				btnMostrar.setBounds(766, 11, 80, 55);
				frmClnt.getContentPane().add(btnMostrar);
				btnMostrar.setVisible(true);
				
				btnMostrar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) 
					{
						btnEditar.setVisible(false);
						btnBorrar.setVisible(false);
						btnAceptar.setVisible(false);
						btnCancelar.setVisible(false);
						list.setEnabled(true);
						listModel.removeAllElements();
						ResultSet rs=ConectorBD.bdMySQL.Select("clientes", "*", "Cliente LIKE '%"+textField_mostrar.getText()+"%'");
						try {
							while (rs.next())
								{
									ClienteC aux= new ClienteC();
									aux.setCliente(rs.getObject(2).toString());
									aux.setId(Integer.parseInt(rs.getObject(1).toString()));
									aux.setIdPoblacion(Integer.parseInt(rs.getObject(3).toString()));
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
				
				textField_mostrar = new JTextField();
				textField_mostrar.setBounds(100, 24, 656, 20);
				frmClnt.getContentPane().add(textField_mostrar);
				textField_mostrar.setColumns(10);
				textField_mostrar.setEditable(true);
				
			
				
				panel = new JPanel();
				panel.setBorder(new LineBorder(new Color(0, 0, 0)));
				panel.setBounds(10, 310, 1330, 385);
				frmClnt.getContentPane().add(panel);
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
			textField_nombre.setEditable(false);
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
			textField_correo.setEditable(false);

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
			textField_direccion.setEditable(false);
			
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
			textField_telefono1.setEditable(false);

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
			textField_telefono2.setEditable(false);
				
				
//****************** POBLACION ************************************
			lblPoblacion = new JLabel("Poblaci\u00F3n");
			lblPoblacion.setBounds(638, 10, 57, 14);
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
			comboBoxPoblacion.setEnabled(false);
			panel.add(comboBoxPoblacion);	

//****************** EDITAR ************************************	
				btnEditar = new JButton("");
				btnEditar.setToolTipText("Editar");
				btnEditar.setIcon(new ImageIcon(Clientes.class.getResource("/Imagenes/Pencil-icon.png")));
				btnEditar.setBounds(1150, 6, 80, 55);
				panel.add(btnEditar);
				btnEditar.setVisible(false);
				btnEditar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) 
					{
						
						if (!list.isSelectionEmpty())
						{
							btnAceptar.setVisible(true);
							btnCancelar.setVisible(true);
							list.setEnabled(false);
							btnEditar.setVisible(false);
							btnBorrar.setVisible(false);
							btnMostrar.setEnabled(false);
							textField_mostrar.setEditable(false);
							textField_nombre.setEnabled(true);
							textField_nombre.setEditable(true);
							comboBoxPoblacion.setEnabled(true);					
							list.setEnabled(false);
							textField_nombre.setText(list.getSelectedValue().getCliente());	
						}
						
					}
				});

		//****************** BORRAR ************************************	
				btnBorrar = new JButton("");
				btnBorrar.setIcon(new ImageIcon(Clientes.class.getResource("/Imagenes/Delete-icon.png")));
				btnBorrar.setToolTipText("Borrar");
				btnBorrar.setBounds(1240, 6, 80, 55);
				panel.add(btnBorrar);
				btnBorrar.setVisible(false);
				btnBorrar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) 
					{
						btnAceptar.setEnabled(false);
						btnCancelar.setEnabled(false);
						list.setEnabled(false);
						btnEditar.setEnabled(false);
						btnBorrar.setEnabled(false);
						btnMostrar.setEnabled(false);
						textField_mostrar.setEditable(false);
						btnNuevo.setEnabled(false);
						list.setEnabled(false);
						JOptionPane.showMessageDialog(btnAceptar, "El cliente seleccionado se borrará de la base de datos. ¿Estás segur@?"); 
					}
				});
				
			
		//****************** ACEPTAR ************************************			
				btnAceptar = new JButton("");
				btnAceptar.setBounds(1161, 244, 80, 55);
				frmClnt.getContentPane().add(btnAceptar);
				btnAceptar.setIcon(new ImageIcon(Clientes.class.getResource("/Imagenes/Accept-icon.png")));
				btnAceptar.setVisible(false);
				
				btnAceptar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) 
					{
						if (!textField_nombre.getText().equals(""))
						{
							ClienteC aux= new ClienteC();
							aux.setCliente(textField_nombre.getText());
							if (comboBoxPoblacion.getSelectedIndex()!=0)
								//aux.setIdPoblacion(Integer.toString(comboBoxPoblacion.getSelectedIndex()));
								aux.setIdPoblacion(comboBoxPoblacion.getSelectedIndex());
							aux.Insert();
							listModel.addElement(aux);
						}				
						textField_mostrar.setEditable(true);
						
					}
				});
				
				
		//****************** CANCELAR ************************************	
				btnCancelar = new JButton("");
				btnCancelar.setIcon(new ImageIcon(Clientes.class.getResource("/Imagenes/Cancel-icon.png")));
				btnCancelar.setBounds(1251, 244, 80, 55);
				frmClnt.getContentPane().add(btnCancelar);
				btnCancelar.setVisible(false);
				
				
				
		//****************** LISTA ************************************	
					listModel = new DefaultListModel<ClienteC>();
					
					ResultSet rs1=ConectorBD.bdMySQL.Select("clientes", "*", "true");
					try {
						while (rs1.next())
							{
								ClienteC aux1= new ClienteC();
								aux1.setCliente(rs1.getObject(2).toString());
								aux1.setId(Integer.parseInt(rs1.getObject(1).toString()));
								aux1.setIdPoblacion(Integer.parseInt(rs1.getObject(3).toString()));
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
					frmClnt.getContentPane().add(scrollPane);	
			//frmClnt.getContentPane().add(list);
			//list.setEnabled(true);
			scrollPane.setEnabled(true);
			
			
			
			list = new JList<ClienteC>(listModel);
			scrollPane.setViewportView(list);
			list.addListSelectionListener(new ListSelectionListener() {
				public void valueChanged(ListSelectionEvent e) {
					btnEditar.setVisible(true);
					btnBorrar.setVisible(true);
					btnNuevo.setEnabled(false);
					btnAceptar.setVisible(false);
					textField_codigo.setText(Integer.toString(list.getSelectedValue().getId()));
					textField_nombre.setText(list.getSelectedValue().getCliente());
					comboBoxPoblacion.setSelectedItem(Integer.toString(list.getSelectedValue().getIdPoblacion()));
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
			
			
		//------------------------------------------	
	}

}
