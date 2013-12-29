package Ventanas;


import java.awt.Color;
import java.awt.Component;
import java.awt.ComponentOrientation;
import java.awt.Toolkit;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.ComboBoxModel;
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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import java.awt.SystemColor;


public class Proveedores {

	private JFrame frmProvdor;
	private JList<ProveedorC> list;
	private JTextField textField_mostrar;
	private JTextField textField_nombre;
	private JTextField textField_codigo;
	
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
	private JLabel lblPoblacion;
	private JComboBox<Poblacion> comboBoxPoblacion;
	
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
		frmProvdor = new JFrame();
		frmProvdor.getContentPane().setBackground(SystemColor.textHighlight);
		frmProvdor.setBounds(0, 0, Toolkit.getDefaultToolkit().getScreenSize().width, Toolkit.getDefaultToolkit().getScreenSize().height);
		frmProvdor.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frmProvdor.getContentPane().setLayout(null);
		frmProvdor.setVisible(true);
		frmProvdor.setTitle("Proveedores");
		
		
		
		frmProvdor.getContentPane().addMouseListener(new MouseAdapter()
		{
			public void mouseClicked(MouseEvent e)
			{
				list.clearSelection();
				btnEditar.setVisible(false);
				btnBorrar.setVisible(false);
				btnNuevo.setVisible(true);
			}
		});	
	
		
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
		btnNuevo.setIcon(new ImageIcon(Proveedores.class.getResource("/Imagenes/Add-icon.png")));
		btnNuevo.setToolTipText("Nuevo");
		btnNuevo.setBounds(10, 11, 80, 55);
		frmProvdor.getContentPane().add(btnNuevo);
		btnNuevo.setVisible(true);

	
		
	
//****************** MOSTRAR ************************************
		btnMostrar = new JButton("");
		btnMostrar.setIcon(new ImageIcon(Proveedores.class.getResource("/Imagenes/Search-icon.png")));
		btnMostrar.setToolTipText("Buscar");
		btnMostrar.setBounds(766, 11, 80, 55);
		frmProvdor.getContentPane().add(btnMostrar);
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
				ResultSet rs=ConectorBD.bdMySQL.Select("proveedores", "*", "Proveedor LIKE '%"+textField_mostrar.getText()+"%'");
				try {
					while (rs.next())
						{
							ProveedorC aux= new ProveedorC();
							aux.setProveedor(rs.getObject(2).toString());
							aux.setId(Integer.parseInt(rs.getObject(1).toString()));
							aux.setPoblacion(rs.getObject(3).toString());
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
		frmProvdor.getContentPane().add(textField_mostrar);
		textField_mostrar.setColumns(10);
		textField_mostrar.setEditable(true);
		
	
		
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
		textField_nombre.setEditable(false);
		
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
					btnAceptar.setVisible(true);
					btnCancelar.setVisible(true);
					list.setEnabled(false);
					btnEditar.setVisible(false);
					btnBorrar.setVisible(false);
					btnMostrar.setEnabled(false);
					textField_mostrar.setEditable(false);
					textField_nombre.setEditable(true);
					comboBoxPoblacion.setEditable(true);
					list.setEnabled(false);
					textField_nombre.setText(list.getSelectedValue().getProveedor());	
				}
				
			}
		});

//****************** BORRAR ************************************	
		btnBorrar = new JButton("");
		btnBorrar.setIcon(new ImageIcon(Proveedores.class.getResource("/Imagenes/Delete-icon.png")));
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
				JOptionPane.showMessageDialog(btnAceptar, "El proveedor seleccionado se borrará de la base de datos. ¿Estás segur@?"); 
			}
		});
		
	
//****************** ACEPTAR ************************************			
		btnAceptar = new JButton("");
		btnAceptar.setBounds(1161, 244, 80, 55);
		frmProvdor.getContentPane().add(btnAceptar);
		btnAceptar.setIcon(new ImageIcon(Proveedores.class.getResource("/Imagenes/Accept-icon.png")));
		btnAceptar.setVisible(false);
		
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				if (!textField_nombre.getText().equals(""))
				{
					ProveedorC aux= new ProveedorC();
					aux.setProveedor(textField_nombre.getText());
					aux.Insert();
					listModel.addElement(aux);
				}
				/*if (!textField_nombre.getText().equals(""))
				{
					ProveedorC aux= new ProveedorC();
					aux.setProveedor(textField_nombre.getText());
					aux.setId(list.getSelectedValue().getId());
					aux.Update();
					listModel.remove(list.getSelectedIndex());
					listModel.addElement(aux);
					list.setSelectedIndex(list.getLastVisibleIndex());
				}
				textField_nombre.setText("");
				*/
				
				textField_mostrar.setEditable(true);
				
			}
		});
		
		
//****************** CANCELAR ************************************	
		btnCancelar = new JButton("");
		btnCancelar.setIcon(new ImageIcon(Proveedores.class.getResource("/Imagenes/Cancel-icon.png")));
		btnCancelar.setBounds(1251, 244, 80, 55);
		frmProvdor.getContentPane().add(btnCancelar);
		btnCancelar.setVisible(false);
		
		
		
//****************** LISTA ************************************	
			listModel = new DefaultListModel<ProveedorC>();
			
			ResultSet rs1=ConectorBD.bdMySQL.Select("proveedores", "*", "true");
			try {
				while (rs1.next())
					{
						ProveedorC aux1= new ProveedorC();
						aux1.setProveedor(rs1.getObject(2).toString());
						aux1.setId(Integer.parseInt(rs1.getObject(1).toString()));
						aux1.setPoblacion(rs1.getObject(3).toString());
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
	//frmProvdor.getContentPane().add(list);
	//list.setEnabled(true);
	scrollPane.setEnabled(true);
	
	
	list = new JList<ProveedorC>(listModel);
	scrollPane.setViewportView(list);
	list.addListSelectionListener(new ListSelectionListener() {
		public void valueChanged(ListSelectionEvent e) {
			btnEditar.setVisible(true);
			btnBorrar.setVisible(true);
			btnNuevo.setEnabled(false);
			btnAceptar.setVisible(false);
			btnCancelar.setVisible(false);
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
	
	
//------------------------------------------
	
	

	
	
	
//-------------------------------------------	
	}
}
