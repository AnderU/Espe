package Ventanas;

import java.awt.Component;
import java.awt.Toolkit;

import javax.swing.JFrame;
import java.awt.SystemColor;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.Timer;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import BaseDatos.ConectorBD;
import Clases.ConceptosC;
import Clases.FacturasProveedoresC;
import Clases.GrupoConcepto;

import Clases.TipoConcepto;


import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTabbedPane;
import javax.swing.JLabel;

import com.toedter.calendar.JDateChooser;

public class FacturasProveedores {

	private JFrame frmFactProv;
	private JTable tblFactProv;
	private JTextField textField_nFactura;
	private JTextField textField_proveedor;
	private JTextField textField_formaDePago;
	private final ButtonGroup Con_Sin = new ButtonGroup();
	private JRadioButton rdbtnConIva;
	private JRadioButton rdbtnSinIva;
	private JLabel lblIva;
	private JPanel panel_observaciones;
	private JTextPane textPane_observaciones;
	

	
	private JDateChooser dateChooser_fecha;
	private JDateChooser dateChooser_fechaCobro;


	private JButton btnAceptar;
	private JButton btnCancelar;
	private JButton btnCancelar_edit;
	private JButton btnAceptar_edit;
	private JButton btnBorrar;
	private JButton btnEditar;
	private JButton btnNuevo ;
	
	/**
	 * Launch the application.
	 */

	/**
	 * Create the application.
	 */
	public FacturasProveedores() {
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
		tblFactProv.setEnabled(true);
		
		btnAceptar.setVisible(false);
		btnCancelar.setVisible(false);
		btnAceptar_edit.setVisible(false);
		btnCancelar_edit.setVisible(false);
		
		textField_nFactura.setEditable(false);
		textField_proveedor.setEditable(false);
		textField_formaDePago.setEditable(false);
		textPane_observaciones.setEditable(false);
		
		
		tblFactProv.clearSelection();
		
		textField_nFactura.setText("");
		textField_proveedor.setText("");
		textField_formaDePago.setText("");
		
		textPane_observaciones.setText("");
				
	}
	
	public void setEstadoNuevo()
	{
		btnNuevo.setEnabled(false);
		btnAceptar.setVisible(true);
		btnCancelar.setVisible(true);
		tblFactProv.setEnabled(false);
		
		textField_nFactura.setEditable(true);
		textField_proveedor.setEditable(true);
		textField_formaDePago.setEditable(false);
		textPane_observaciones.setEditable(true);

		
		textField_nFactura.setText("");
		textField_proveedor.setText("");
		textField_formaDePago.setText("");

		textPane_observaciones.setText("");

		btnEditar.setEnabled(false);
		btnBorrar.setEnabled(false);	
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
//***************** VENTANA ********************************		
		frmFactProv = new JFrame();
		frmFactProv.setIconImage(Toolkit.getDefaultToolkit().getImage(FacturasProveedores.class.getResource("/Imagenes/fp.png")));
		frmFactProv.getContentPane().setBackground(SystemColor.textHighlight);
		frmFactProv.setBounds(0, 0, Toolkit.getDefaultToolkit().getScreenSize().width, Toolkit.getDefaultToolkit().getScreenSize().height);
		frmFactProv.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frmFactProv.getContentPane().setLayout(null);
		frmFactProv.setVisible(true);
		frmFactProv.setTitle("Facturas de Proveedores");
		
//***************** TABLA ********************************	
		tblFactProv = new JTable();
		tblFactProv.setBounds(10, 274, 1324, 415);
		frmFactProv.getContentPane().add(tblFactProv);
		
//***************** N FACTURA ********************************	
		JLabel lblNFactura = new JLabel("N\u00BA Factura");
		lblNFactura.setBounds(20, 118, 90, 14);
		frmFactProv.getContentPane().add(lblNFactura);
		
		textField_nFactura = new JTextField();
		textField_nFactura.setBounds(110, 115, 260, 20);
		frmFactProv.getContentPane().add(textField_nFactura);
		textField_nFactura.setColumns(10);
		
//***************** PROVEEDOR ********************************		
		JLabel lblProveedor = new JLabel("Proveedor");
		lblProveedor.setBounds(400, 118, 90, 14);
		frmFactProv.getContentPane().add(lblProveedor);
		
		textField_proveedor = new JTextField();
		textField_proveedor.setBounds(480, 115, 539, 20);
		frmFactProv.getContentPane().add(textField_proveedor);
		textField_proveedor.setColumns(10);
		
//***************** FORMA DE PAGO ********************************		
		JLabel lblFormaDePago = new JLabel("Forma de Pago");
		lblFormaDePago.setBounds(20, 158, 79, 14);
		frmFactProv.getContentPane().add(lblFormaDePago);
		
		textField_formaDePago = new JTextField();
		textField_formaDePago.setBounds(110, 155, 260, 20);
		frmFactProv.getContentPane().add(textField_formaDePago);
		textField_formaDePago.setColumns(10);

//***************** IVA ********************************			
		rdbtnConIva = new JRadioButton("Con");
		rdbtnConIva.setBounds(440, 154, 50, 23);
		frmFactProv.getContentPane().add(rdbtnConIva);
		Con_Sin.add(rdbtnConIva);
		
		rdbtnSinIva = new JRadioButton("Sin");
		rdbtnSinIva.setBounds(490, 154, 50, 23);
		frmFactProv.getContentPane().add(rdbtnSinIva);
		Con_Sin.add(rdbtnSinIva);
		
		lblIva = new JLabel("IVA");
		lblIva.setBounds(400, 158, 34, 14);
		frmFactProv.getContentPane().add(lblIva);

//****************** OBSERVACIONES ************************************

		panel_observaciones = new JPanel();
		panel_observaciones.setBounds(1048, 105, 286, 158);
		panel_observaciones.setBorder(new TitledBorder(null, "Observaciones", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		frmFactProv.getContentPane().add(panel_observaciones);
		panel_observaciones.setLayout(null);
		
		textPane_observaciones = new JTextPane();
		textPane_observaciones.setBounds(10, 21, 266, 126);
		panel_observaciones.add(textPane_observaciones);

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
		frmFactProv.getContentPane().add(btnNuevo);
		btnNuevo.setVisible(true);
		
//****************** EDITAR ************************************	
		btnEditar = new JButton("");
		btnEditar.setToolTipText("Editar");
		btnEditar.setIcon(new ImageIcon(Proveedores.class.getResource("/Imagenes/Pencil-icon.png")));
		btnEditar.setBounds(110, 11, 80, 55);
		frmFactProv.getContentPane().add(btnEditar);
		btnEditar.setVisible(false);
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				
				
				
			}
		});

//****************** BORRAR ************************************	
		btnBorrar = new JButton("");
		btnBorrar.setIcon(new ImageIcon(Proveedores.class.getResource("/Imagenes/Trash-icon.png")));
		btnBorrar.setToolTipText("Borrar");
		btnBorrar.setBounds(210, 11, 80, 55);
		frmFactProv.getContentPane().add(btnBorrar);
		btnBorrar.setVisible(false);
		btnBorrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				int result=JOptionPane.showConfirmDialog(frmFactProv, "La factura de proveedor seleccionada se borrará de la base de datos. ¿Estás segur@?", "¡Atención!",JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE);
				if (result==JOptionPane.OK_OPTION)
				{
					FacturasProveedoresC aux= new FacturasProveedoresC();
					//aux.setNFactura(tblFactProv.getSelectedRow().getNFactura());
					aux.Delete();
					
				}
				setEstadoInicial();
			}
		});
		

//****************** ACEPTAR EDIT************************************	
		btnAceptar_edit = new JButton("");
		btnAceptar_edit.setBounds(311, 11, 80, 55);
		frmFactProv.getContentPane().add(btnAceptar_edit);
		btnAceptar_edit.setIcon(new ImageIcon(Proveedores.class.getResource("/Imagenes/Accept-icon.png")));
		btnAceptar_edit.setToolTipText("Aceptar");
		btnAceptar_edit.setVisible(false);
		btnAceptar_edit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if (!textField_nFactura.getText().equals(""))
				{				
					FacturasProveedoresC aux= new FacturasProveedoresC();
					aux.setnFactura(Integer.parseInt(textField_nFactura.getText()));
					
					aux.setObservaciones(textPane_observaciones.getText());
				}				
				setEstadoInicial();
			}
		});
		

//****************** CANCELAR ************************************	
		btnCancelar = new JButton("");
		btnCancelar.setBounds(412, 11, 80, 55);
		frmFactProv.getContentPane().add(btnCancelar);
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
		btnCancelar_edit.setBounds(412, 11, 80, 55);
		frmFactProv.getContentPane().add(btnCancelar_edit);
		btnCancelar_edit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setEstadoInicial();			
			}
		});
		btnCancelar_edit.setIcon(new ImageIcon(Proveedores.class.getResource("/Imagenes/Delete-icon.png")));
		btnCancelar_edit.setToolTipText("Cancelar");
		btnCancelar_edit.setVisible(false);
		
//****************** ACEPTAR ************************************			
		btnAceptar = new JButton("");
		frmFactProv.getContentPane().add(btnAceptar);
		btnAceptar.setBounds(311, 11, 80, 55);
		btnAceptar.setIcon(new ImageIcon(Proveedores.class.getResource("/Imagenes/Accept-icon.png")));
		btnAceptar.setToolTipText("Aceptar");

		btnAceptar.setVisible(false);
		
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				
				if (!textField_nFactura.getText().equals(""))
				{					
					FacturasProveedoresC aux= new FacturasProveedoresC();
					aux.setnFactura(Integer.parseInt(textField_nFactura.getText()));
					
					aux.setObservaciones(textPane_observaciones.getText());
				}		
				setEstadoInicial();
				
			}
		});


	
		
		dateChooser_fecha = new JDateChooser();
		dateChooser_fecha.setBounds(615, 155, 130, 20);
		frmFactProv.getContentPane().add(dateChooser_fecha);
		
		dateChooser_fechaCobro = new JDateChooser();
		dateChooser_fechaCobro.setBounds(889, 155, 130, 20);
		frmFactProv.getContentPane().add(dateChooser_fechaCobro);
		
		JLabel lblFecha = new JLabel("Fecha");
		lblFecha.setBounds(574, 158, 46, 14);
		frmFactProv.getContentPane().add(lblFecha);
		
		JLabel lblFechaDeCobro = new JLabel("Fecha de Cobro");
		lblFechaDeCobro.setBounds(789, 158, 90, 14);
		frmFactProv.getContentPane().add(lblFechaDeCobro);
		
		
		
	}
}