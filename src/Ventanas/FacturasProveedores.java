package Ventanas;

import java.awt.Toolkit;
import javax.swing.JFrame;
import java.awt.SystemColor;
import javax.swing.ButtonGroup;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.border.TitledBorder;

import BaseDatos.ConectorBD;
import Clases.FacturasProveedoresC;
import Clases.FormaPago;
import Clases.ProveedorC;

import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Vector;

import javax.swing.JLabel;

import com.toedter.calendar.JDateChooser;
import javax.swing.JComboBox;

public class FacturasProveedores {

	private JFrame frmFactProv;
	private JTable tblFactProv;
	private JTextField textField_nFactura;
	private final ButtonGroup Con_Sin = new ButtonGroup();
	private JRadioButton rdbtnConIva;
	private JRadioButton rdbtnSinIva;
	private JLabel lblIva;
	private JPanel panel_observaciones;
	private JTextPane textPane_observaciones;
	private JComboBox<FormaPago> comboBox_formaPago;
	private JComboBox<ProveedorC> cmbProveedor;

	
	private JDateChooser dateChooser_fecha;
	private JDateChooser dateChooser_fechaPago;

	private JPanel pnl_importes;
	private JTextField textField_IPescado;
	private JTextField textField_IPIP;
	private JTextField textField_ICajas;
	private JTextField textField_Subtotal;
	private JTextField textField_IvaI;
	private JTextField textField_Total;
	private JTextField textField_IP;
	private JTextField textField_Iva;
	
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
		cmbProveedor.setEnabled(false);
		comboBox_formaPago.setEnabled(false);
		textPane_observaciones.setEditable(false);
		
		tblFactProv.clearSelection();
		
		textField_nFactura.setText("");
		cmbProveedor.setSelectedIndex(0);
		comboBox_formaPago.setSelectedIndex(0);
		
		textPane_observaciones.setText("");
		
		dateChooser_fecha.setCalendar(null);
		dateChooser_fechaPago.setCalendar(null);
		
		rdbtnConIva.setSelected(false);
		rdbtnSinIva.setSelected(false);
		
		textField_IvaI.setText("");
		textField_Iva.setText("");
		textField_Subtotal.setText("");
		textField_IPIP.setText("");
		textField_IP.setText("");
		textField_IPescado.setText("");
		textField_ICajas.setText("");
		textField_Total.setText("");
		
				
	}
	
	public void setEstadoNuevo()
	{
		btnNuevo.setEnabled(false);
		btnAceptar.setVisible(true);
		btnCancelar.setVisible(true);
		tblFactProv.setEnabled(false);
		
		textField_nFactura.setEditable(true);
		cmbProveedor.setEnabled(true);
		comboBox_formaPago.setEnabled(true);
		textPane_observaciones.setEditable(true);

		
		textField_nFactura.setText("");
		cmbProveedor.setSelectedIndex(0);
		comboBox_formaPago.setSelectedIndex(0);

		textPane_observaciones.setText("");

		btnEditar.setEnabled(false);
		btnBorrar.setEnabled(false);	

		dateChooser_fecha.setCalendar(Calendar.getInstance());
		dateChooser_fechaPago.setCalendar(null);
		
		rdbtnConIva.setSelected(true);
		rdbtnSinIva.setSelected(false);
		
		textField_Subtotal.setText("0.0");
		textField_ICajas.setText("0.0");
		textField_IPescado.setText("0.0");
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
//***************** VENTANA ********************************		
		frmFactProv = new JFrame();
		frmFactProv.setIconImage(Toolkit.getDefaultToolkit().getImage(FacturasProveedores.class.getResource("/Imagenes/Animals-Fish-icon.png")));
		frmFactProv.getContentPane().setBackground(SystemColor.textHighlight);
		frmFactProv.setBounds(0, 0, Toolkit.getDefaultToolkit().getScreenSize().width, Toolkit.getDefaultToolkit().getScreenSize().height);
		frmFactProv.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frmFactProv.getContentPane().setLayout(null);
		frmFactProv.setVisible(true);
		frmFactProv.setTitle("Facturas de Proveedores");
		
//***************** TABLA ********************************	
		tblFactProv = new JTable();
		tblFactProv.setBounds(10, 274, 1026, 415);
		frmFactProv.getContentPane().add(tblFactProv);
		
//***************** N FACTURA ********************************	
		JLabel lblNFactura = new JLabel("N\u00BA Factura");
		lblNFactura.setBounds(20, 118, 90, 14);
		frmFactProv.getContentPane().add(lblNFactura);
		
		textField_nFactura = new JTextField();
		textField_nFactura.setBounds(141, 115, 250, 20);
		frmFactProv.getContentPane().add(textField_nFactura);
		textField_nFactura.setColumns(10);
		
//***************** PROVEEDOR ********************************		
		JLabel lblProveedor = new JLabel("Proveedor");
		lblProveedor.setBounds(418, 118, 65, 14);
		frmFactProv.getContentPane().add(lblProveedor);
		
		ProveedorC aux1= new ProveedorC(); 
 		ResultSet rs=ConectorBD.bdMySQL.Select("proveedores", "*", "true");
 		Vector<ProveedorC> elementos1= new Vector<ProveedorC>(); 
 		elementos1.addElement(aux1);
 		try {
 			while (rs.next())
 			{
 				ProveedorC a=new ProveedorC();
 				
 				a.setId(Integer.parseInt(rs.getObject(1).toString()));
 				a.setProveedor(rs.getObject(2).toString());
 				elementos1.addElement(a);
 				
 			}
 		} catch (SQLException e1) {
 			// TODO Auto-generated catch block
 			e1.printStackTrace();
 		}
 		
 	    cmbProveedor = new JComboBox<ProveedorC>(elementos1);
 	    cmbProveedor.setEnabled(false);
 	    cmbProveedor.setBounds(487, 115, 549, 20);
 	    frmFactProv.getContentPane().add(cmbProveedor);
    
		
//***************** FORMA DE PAGO ********************************		
		JLabel lblFormaDePago = new JLabel("Forma de Pago");
		lblFormaDePago.setBounds(20, 158, 111, 14);
		frmFactProv.getContentPane().add(lblFormaDePago);
		
		FormaPago auxf= new FormaPago(); 
 		ResultSet rsf=ConectorBD.bdMySQL.Select("formapago", "*", "true");
 		Vector<FormaPago> elementosf= new Vector<FormaPago>(); 
 		elementosf.addElement(auxf);
 		try {
 			while (rsf.next())
 			{
 				FormaPago af=new FormaPago();
 				
 				af.setId(rsf.getObject(1).toString());
 				af.setForma(rsf.getObject(2).toString());
 				elementosf.addElement(af);
 				
 			}
 		} catch (SQLException ef) {
 			// TODO Auto-generated catch block
 			ef.printStackTrace();
 		}

		comboBox_formaPago = new JComboBox<FormaPago>(elementosf);
		comboBox_formaPago.setEnabled(false);
		comboBox_formaPago.setBounds(141, 155, 250, 20);
		frmFactProv.getContentPane().add(comboBox_formaPago);
		

//***************** IVA ********************************			
		rdbtnConIva = new JRadioButton("Con");
		rdbtnConIva.setBounds(487, 154, 54, 23);
		frmFactProv.getContentPane().add(rdbtnConIva);
		Con_Sin.add(rdbtnConIva);
		
		rdbtnSinIva = new JRadioButton("Sin");
		rdbtnSinIva.setBounds(538, 154, 54, 23);
		frmFactProv.getContentPane().add(rdbtnSinIva);
		Con_Sin.add(rdbtnSinIva);
		
		lblIva = new JLabel("IVA");
		lblIva.setBounds(418, 158, 34, 14);
		frmFactProv.getContentPane().add(lblIva);

//****************** OBSERVACIONES ************************************

		panel_observaciones = new JPanel();
		panel_observaciones.setBounds(1058, 104, 286, 158);
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
		btnEditar.setVisible(true);
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
		btnBorrar.setVisible(true);
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
					aux.setnFactura(textField_nFactura.getText());
					
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
					aux.setnFactura(textField_nFactura.getText());
					
					aux.setObservaciones(textPane_observaciones.getText());
				}		
				setEstadoInicial();
				
			}
		});


//****************** FECHAS ************************************	
		
		dateChooser_fecha = new JDateChooser();
		dateChooser_fecha.setDateFormatString("dd-MM-yyyy");
		dateChooser_fecha.setBounds(655, 155, 130, 20);
		frmFactProv.getContentPane().add(dateChooser_fecha);
		
		dateChooser_fechaPago = new JDateChooser();
		dateChooser_fechaPago.setDateFormatString("dd-MM-yyyy");
		dateChooser_fechaPago.setBounds(906, 155, 130, 20);
		frmFactProv.getContentPane().add(dateChooser_fechaPago);
		
		JLabel lblFecha = new JLabel("Fecha");
		lblFecha.setBounds(609, 158, 46, 14);
		frmFactProv.getContentPane().add(lblFecha);
		
		JLabel lblFechaDePago = new JLabel("Fecha de Pago");
		lblFechaDePago.setBounds(816, 158, 90, 14);
		frmFactProv.getContentPane().add(lblFechaDePago);
		
		
		
		
//****************** IMPORTES ************************************
		
		pnl_importes = new JPanel();
		pnl_importes.setBounds(1058, 274, 286, 415);
		frmFactProv.getContentPane().add(pnl_importes);
		pnl_importes.setLayout(null);
		
		textField_IPescado = new JTextField();

	    textField_IPescado.setBounds(203, 22, 73, 20);

	    pnl_importes.add(textField_IPescado);
	    textField_IPescado.setColumns(10);
	    
	    textField_IPIP = new JTextField();
	    textField_IPIP.setColumns(10);
	    textField_IPIP.setBounds(203, 47, 73, 20);    
	    pnl_importes.add(textField_IPIP);
	    
	    textField_ICajas = new JTextField();
	    textField_ICajas.setColumns(10);
	    textField_ICajas.setBounds(203, 72, 73, 20);
	    pnl_importes.add(textField_ICajas);
	    
	    textField_Subtotal = new JTextField();
	    textField_Subtotal.setColumns(10);
	    textField_Subtotal.setBounds(203, 97, 73, 20);
	    pnl_importes.add(textField_Subtotal);
	    
	    textField_IvaI = new JTextField();
	    textField_IvaI.setColumns(10);
	    textField_IvaI.setBounds(203, 122, 73, 20);
	    pnl_importes.add(textField_IvaI);
	    
	    textField_Total = new JTextField();
	    textField_Total.setColumns(10);
	    textField_Total.setBounds(203, 147, 73, 20);
	    pnl_importes.add(textField_Total);
	    
	    textField_IP = new JTextField();
	    textField_IP.setColumns(10);
	    textField_IP.setBounds(122, 47, 69, 20);

	    pnl_importes.add(textField_IP);
	    
	    textField_Iva = new JTextField();
	    textField_Iva.setColumns(10);
	    textField_Iva.setBounds(122, 122, 69, 20);
	    pnl_importes.add(textField_Iva);		
		
	
	    JLabel lblImportePescado = new JLabel("Importe Pescado:");
	    lblImportePescado.setBounds(10, 25, 102, 14);
	    pnl_importes.add(lblImportePescado);
	    
	    JLabel lblImpuestoPort = new JLabel("Impuesto Port:");
	    lblImpuestoPort.setBounds(10, 50, 102, 14);
	    pnl_importes.add(lblImpuestoPort);
	    
	    JLabel lblImpuestoCajas = new JLabel("Importe Cajas:");
	    lblImpuestoCajas.setBounds(10, 75, 102, 14);
	    pnl_importes.add(lblImpuestoCajas);
	    
	    JLabel lblSubtotal = new JLabel("Subtotal:");
	    lblSubtotal.setBounds(10, 100, 102, 14);
	    pnl_importes.add(lblSubtotal);
	    
	    JLabel lblIva = new JLabel("Iva:");
	    lblIva.setBounds(10, 125, 102, 14);
	    pnl_importes.add(lblIva);
	    
	    JLabel lblTotal = new JLabel("Total:");
	    lblTotal.setBounds(10, 150, 102, 14);
	    pnl_importes.add(lblTotal);
	    
	    
		
	setEstadoInicial();	
	}
}