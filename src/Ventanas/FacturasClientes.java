package Ventanas;

import java.awt.Toolkit;
import javax.swing.JFrame;
import java.awt.SystemColor;

import javax.swing.DefaultCellEditor;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.border.TitledBorder;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableColumn;

import BaseDatos.ConectorBD;
import Clases.ClienteC;
import Clases.DetalleFacturasCliente;
import Clases.FacturasClientesC;
import Clases.FormaCobro;
import Clases.GeneroC;

import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Vector;

import javax.swing.JLabel;

import Tablas.ComboRenderer;
import Tablas.TablaFacturasClientes;
import Clases.ClienteC;

import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JDateChooserCellEditor;

import javax.swing.JComboBox;
import javax.swing.JScrollPane;

public class FacturasClientes {

	private JFrame frmFactProv;
	private TablaFacturasClientes modeloTFactCli;
	private JTextField textField_nFactura;
	private JPanel panel_observaciones;
	private JTextPane textPane_observaciones;
	private JComboBox<FormaCobro> comboBox_formaCobro;
	private JComboBox<ClienteC> cmbCliente;

	
	private JDateChooser dateChooser_fecha;
	private JDateChooser dateChooser_fechaCobro;

	private JPanel pnl_importes;
	private JTextField textField_Subtotal;
	private JTextField textField_IvaI;
	private JTextField textField_Total;
	private JTextField textField_Iva;
	
	private JButton btnAceptar;
	private JButton btnCancelar;
	private JButton btnCancelar_edit;
	private JButton btnAceptar_edit;
	private JButton btnBorrar;
	private JButton btnEditar;
	private JButton btnNuevo ;
	private JPanel pnl_cliente;
	private JTable table;
	private JScrollPane scrollPane;
	
	/**
	 * Launch the application.
	 */

	/**
	 * Create the application.
	 */
	public FacturasClientes() {
		initialize();
	}

	
	public void setEstadoInicial()
	{
		int row= modeloTFactCli.getRowCount();
		
		for (int i=0; i<row; i++)
			modeloTFactCli.removeRow(0);
		
		btnNuevo.setVisible(true);
		btnNuevo.setEnabled(true);
		btnEditar.setEnabled(false);
		btnBorrar.setEnabled(false);
		btnEditar.setVisible(true);
		btnBorrar.setVisible(true);
		
		btnAceptar.setVisible(false);
		btnCancelar.setVisible(false);
		btnAceptar_edit.setVisible(false);
		btnCancelar_edit.setVisible(false);
		
		textField_Subtotal.setEditable(false);
		textField_IvaI.setEditable(false);
		textField_Total.setEditable(false);
		textField_nFactura.setEditable(false);
		cmbCliente.setEnabled(false);
		comboBox_formaCobro.setEnabled(false);
		textPane_observaciones.setEditable(false);
		
		textField_Iva.setEditable(false);
		
		textField_nFactura.setText("");
		cmbCliente.setSelectedIndex(0);
		comboBox_formaCobro.setSelectedIndex(0);
		
		textPane_observaciones.setText("");
		
		dateChooser_fecha.setCalendar(null);
		dateChooser_fechaCobro.setCalendar(null);
		dateChooser_fecha.setEnabled(false);
		dateChooser_fechaCobro.setEnabled(false);
		
		textField_IvaI.setText("");
		textField_Iva.setText("");
		textField_Subtotal.setText("");
		textField_Total.setText("");
		
		table.clearSelection();
		table.setEnabled(true);
				
	}
	
	public void setEstadoNuevo()
	{
		btnNuevo.setEnabled(false);
		btnAceptar.setVisible(true);
		btnCancelar.setVisible(true);
		table.setEnabled(true);
		
		textField_nFactura.setEditable(true);
		cmbCliente.setEnabled(true);
		comboBox_formaCobro.setEnabled(true);
		textPane_observaciones.setEditable(true);
		
		textField_Iva.setEditable(true);
		
		textField_nFactura.setText("");
		cmbCliente.setSelectedIndex(0);
		comboBox_formaCobro.setSelectedIndex(0);

		textPane_observaciones.setText("");

		btnEditar.setEnabled(false);
		btnBorrar.setEnabled(false);	

		dateChooser_fecha.setCalendar(Calendar.getInstance());
		dateChooser_fechaCobro.setCalendar(null);
		dateChooser_fecha.setEnabled(true);
		dateChooser_fechaCobro.setEnabled(true);
			
		textField_Subtotal.setText("0.0");
		
		ResultSet rs=ConectorBD.bdMySQL.Select("configuracion","Valor","Id=2");
		try {
			rs.next();
			textField_Iva.setText(rs.getObject(1).toString());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		rs=ConectorBD.bdMySQL.Select("facturasclientes","MAX(Id)","true");
		
		try {
			if (rs.getFetchSize()!=0)
			{
					
					rs.next();
					Integer aux=Integer.parseInt(rs.getObject(1).toString());
					aux++;
					textField_nFactura.setText(aux.toString());

			}
			else
			{
				textField_nFactura.setText("1");
			}
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		DetalleFacturasCliente aux=new DetalleFacturasCliente();
		modeloTFactCli.insertRow(aux);

		
	}
    public void setUpSportColumn(JTable table,  TableColumn columnaGenero) {
  	   
    	   JDateChooser fecha = new JDateChooser();
    	   fecha.setDateFormatString("dd-MM-yyyy");
    	   columnaGenero.setCellEditor(new JDateChooserCellEditor());
}
    
    public void setUpSportColumn1(JTable table,  TableColumn columnaGenero) {
   	   
    	GeneroC aux1= new GeneroC(); 
		ResultSet rs=ConectorBD.bdMySQL.Select("genero", "*", "true");
		Vector<GeneroC> elementos1= new Vector<GeneroC>(); 
		elementos1.addElement(aux1);
		try {
			while (rs.next())
			{
				GeneroC a=new GeneroC();
				
				a.setId(Integer.parseInt(rs.getObject(1).toString()));
				a.setGenero(rs.getObject(2).toString());
				elementos1.addElement(a);
				
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	    JComboBox<GeneroC> cmbGenero = new JComboBox<GeneroC>(elementos1);
	    cmbGenero.setBounds(10, 11, 342, 20);
	    cmbGenero.setRenderer(new ComboRenderer());
	   columnaGenero.setCellEditor(new DefaultCellEditor(cmbGenero));
}
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
//***************** VENTANA ********************************		
		frmFactProv = new JFrame();
		frmFactProv.setIconImage(Toolkit.getDefaultToolkit().getImage(FacturasClientes.class.getResource("/Imagenes/Animals-Fish-icon.png")));
		frmFactProv.getContentPane().setBackground(SystemColor.textHighlight);
		frmFactProv.setBounds(0, 0, Toolkit.getDefaultToolkit().getScreenSize().width, Toolkit.getDefaultToolkit().getScreenSize().height);
		frmFactProv.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frmFactProv.getContentPane().setLayout(null);
		frmFactProv.setVisible(true);
		frmFactProv.setTitle("Facturas de Clientes");
		
//***************** TABLA ********************************	
		
		Vector <String> columnNames = new  Vector <String>(); 
	    columnNames.add("Albaran");
	    columnNames.add("Fecha");
	    columnNames.add("Género");
	    columnNames.add("Cantidad");
	    columnNames.add("Precio");
	    columnNames.add("Total");
	    Vector<DetalleFacturasCliente> vectorTabla= new Vector<DetalleFacturasCliente>();
		modeloTFactCli= new TablaFacturasClientes(vectorTabla,columnNames);
		
	    scrollPane = new JScrollPane();
	    scrollPane.setBounds(10, 77, 1026, 612);
	    frmFactProv.getContentPane().add(scrollPane);
	    
	    table = new JTable(modeloTFactCli);
	    scrollPane.setViewportView(table);
	    setUpSportColumn(table, table.getColumnModel().getColumn(1));
	    setUpSportColumn1(table, table.getColumnModel().getColumn(2));	  
	    table.getModel().addTableModelListener(new TableModelListener() {

			@Override
			public void tableChanged(TableModelEvent arg0) {
				// TODO Auto-generated method stub
			if (table.getRowCount()>0)
			{
				if (!modeloTFactCli.getValueAt(table.getRowCount()-1,1).equals("") && 
					!modeloTFactCli.getValueAt(table.getRowCount()-1,2).equals("") && 
					!modeloTFactCli.getValueAt(table.getRowCount()-1,3).equals("0.0") &&
					!modeloTFactCli.getValueAt(table.getRowCount()-1,4).equals("0.0") &&
					!modeloTFactCli.getValueAt(table.getRowCount()-1,5).equals("0.0"))
				{
					DetalleFacturasCliente aux = new DetalleFacturasCliente();
					modeloTFactCli.insertRow(aux);
					
				}
				Double op1= new Double(0);
				double op2;
				
				for (int i=0; i<table.getRowCount(); i++)
				{
					op2=0;
					if (!modeloTFactCli.getValueAt(i,5).equals("0.0"))
					{
						op2=Double.parseDouble(modeloTFactCli.getValueAt(i,5).toString());
					}
					op1+=op2;				

				}
				textField_Subtotal.setText(op1.toString());
				double iva = Double.parseDouble(textField_Iva.getText());
				double ivaI=(iva/100)*op1;
				textField_IvaI.setText(Double.toString(ivaI));	
				double total= op1+ivaI;
				textField_Total.setText(Double.toString(total));					
				
			}	
				
			}
	      });
		
//***************** N FACTURA ********************************	
		JLabel lblNFactura = new JLabel("N\u00BA Factura");
		lblNFactura.setBounds(1046, 118, 90, 14);
		frmFactProv.getContentPane().add(lblNFactura);
		
		textField_nFactura = new JTextField();
		textField_nFactura.setBounds(1109, 115, 223, 20);
		frmFactProv.getContentPane().add(textField_nFactura);
		textField_nFactura.setColumns(10);
		
		ClienteC aux1= new ClienteC(); 
 		ResultSet rs=ConectorBD.bdMySQL.Select("clientes", "*", "true");
 		Vector<ClienteC> elementos1= new Vector<ClienteC>(); 
 		elementos1.addElement(aux1);
 		try {
 			while (rs.next())
 			{
 				ClienteC a=new ClienteC();
 				
 				a.setId(Integer.parseInt(rs.getObject(1).toString()));
 				a.setCliente(rs.getObject(2).toString());
 				elementos1.addElement(a);
 				
 			}
 		} catch (SQLException e1) {
 			// TODO Auto-generated catch block
 			e1.printStackTrace();
 		}
 		
 	    cmbCliente = new JComboBox<ClienteC>(elementos1);
 	    cmbCliente.setEnabled(false);
 	    cmbCliente.setBounds(10, 21, 266, 20);
 	    
	    pnl_cliente = new JPanel();
	    pnl_cliente.setBorder(new TitledBorder(null, "Cliente", TitledBorder.LEADING, TitledBorder.TOP, null, null));
	    pnl_cliente.setBounds(1046, 152, 286, 60);
	    frmFactProv.getContentPane().add(pnl_cliente);
	    pnl_cliente.setLayout(null);
	    pnl_cliente.add(cmbCliente);
 	    
    
		
//***************** FORMA DE PAGO ********************************		
		JLabel lblFormaDeCobro = new JLabel("Forma de Cobro");
		lblFormaDeCobro.setBounds(1046, 233, 90, 14);
		frmFactProv.getContentPane().add(lblFormaDeCobro);
		
		FormaCobro auxf= new FormaCobro(); 
 		ResultSet rsf=ConectorBD.bdMySQL.Select("formapago", "*", "true");
 		Vector<FormaCobro> elementosf= new Vector<FormaCobro>(); 
 		elementosf.addElement(auxf);
 		try {
 			while (rsf.next())
 			{
 				FormaCobro af=new FormaCobro();
 				
 				af.setId(rsf.getObject(1).toString());
 				af.setForma(rsf.getObject(2).toString());
 				elementosf.addElement(af);
 				
 			}
 		} catch (SQLException ef) {
 			// TODO Auto-generated catch block
 			ef.printStackTrace();
 		}

		comboBox_formaCobro = new JComboBox<FormaCobro>(elementosf);
		comboBox_formaCobro.setEnabled(false);
		comboBox_formaCobro.setBounds(1174, 230, 158, 20);
		frmFactProv.getContentPane().add(comboBox_formaCobro);

//****************** OBSERVACIONES ************************************

		panel_observaciones = new JPanel();
		panel_observaciones.setBounds(1046, 352, 286, 206);
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
		btnNuevo.setIcon(new ImageIcon(Clientes.class.getResource("/Imagenes/Add-icon.png")));
		btnNuevo.setToolTipText("Nuevo");
		btnNuevo.setBounds(10, 11, 80, 55);
		frmFactProv.getContentPane().add(btnNuevo);
		btnNuevo.setVisible(true);
		
//****************** EDITAR ************************************	
		btnEditar = new JButton("");
		btnEditar.setToolTipText("Editar");
		btnEditar.setIcon(new ImageIcon(Clientes.class.getResource("/Imagenes/Pencil-icon.png")));
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
		btnBorrar.setIcon(new ImageIcon(Clientes.class.getResource("/Imagenes/Trash-icon.png")));
		btnBorrar.setToolTipText("Borrar");
		btnBorrar.setBounds(210, 11, 80, 55);
		frmFactProv.getContentPane().add(btnBorrar);
		btnBorrar.setVisible(true);
		btnBorrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				int result=JOptionPane.showConfirmDialog(frmFactProv, "La factura de cliente seleccionada se borrará de la base de datos. ¿Estás segur@?", "¡Atención!",JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE);
				if (result==JOptionPane.OK_OPTION)
				{
					FacturasClientesC aux= new FacturasClientesC();
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
		btnAceptar_edit.setIcon(new ImageIcon(Clientes.class.getResource("/Imagenes/Accept-icon.png")));
		btnAceptar_edit.setToolTipText("Aceptar");
		btnAceptar_edit.setVisible(false);
		btnAceptar_edit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if (!textField_nFactura.getText().equals(""))
				{				
					FacturasClientesC aux= new FacturasClientesC();
					aux.setnFactura(textField_nFactura.getText());
					
					aux.setObservaciones(textPane_observaciones.getText());
				}				
				setEstadoInicial();
			}
		});
		

//****************** CANCELAR ************************************	
		btnCancelar = new JButton("");
		btnCancelar.setBounds(401, 11, 80, 55);
		frmFactProv.getContentPane().add(btnCancelar);
		btnCancelar.setToolTipText("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setEstadoInicial();
			}
		});
		btnCancelar.setIcon(new ImageIcon(Clientes.class.getResource("/Imagenes/Delete-icon.png")));
		btnCancelar.setVisible(false);

//****************** CANCELAR EDIT ************************************	
		btnCancelar_edit = new JButton("");
		btnCancelar_edit.setBounds(401, 11, 80, 55);
		frmFactProv.getContentPane().add(btnCancelar_edit);
		btnCancelar_edit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setEstadoInicial();			
			}
		});
		btnCancelar_edit.setIcon(new ImageIcon(Clientes.class.getResource("/Imagenes/Delete-icon.png")));
		btnCancelar_edit.setToolTipText("Cancelar");
		btnCancelar_edit.setVisible(false);
		
//****************** ACEPTAR ************************************			
		btnAceptar = new JButton("");
		frmFactProv.getContentPane().add(btnAceptar);
		btnAceptar.setBounds(311, 11, 80, 55);
		btnAceptar.setIcon(new ImageIcon(Clientes.class.getResource("/Imagenes/Accept-icon.png")));
		btnAceptar.setToolTipText("Aceptar");

		btnAceptar.setVisible(false);
		
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				
				if (!textField_nFactura.getText().equals("") && cmbCliente.getSelectedIndex()!=0  && comboBox_formaCobro.getSelectedIndex()!=0 && dateChooser_fecha.getCalendar()!=null)
				{					
					FacturasClientesC aux= new FacturasClientesC();
					aux.setnFactura(textField_nFactura.getText());
					SimpleDateFormat formatoFecha = new SimpleDateFormat("dd-MM-yyyy");
					aux.setFecha(formatoFecha.format(dateChooser_fecha.getCalendar().getTime()));
					if (dateChooser_fechaCobro.getCalendar()!=null)
						aux.setFechaCobro(formatoFecha.format(dateChooser_fechaCobro.getCalendar().getTime()));
					//aux.setFormaCobro();
					aux.setIdFormaCobro(((FormaCobro)comboBox_formaCobro.getSelectedItem()).getId());
					aux.setIdCliente(Integer.toString(((ClienteC) cmbCliente.getSelectedItem()).getId()));
					aux.setObservaciones(textPane_observaciones.getText());
					aux.setIva(textField_Iva.getText());
					Integer id=aux.Insert();
					for (int i=0; i<modeloTFactCli.getRowCount()-1; i++)
					{
						DetalleFacturasCliente aux1= new DetalleFacturasCliente();
						aux1.setAlbaran((String) modeloTFactCli.getValueAt(table.getSelectedRow(), 0));
						aux1.setFecha((String) modeloTFactCli.getValueAt(table.getSelectedRow(), 1));
						aux1.setIdgenero((String) modeloTFactCli.getValueAt(table.getSelectedRow(), 6));
						aux1.setCantidad((String) modeloTFactCli.getValueAt(table.getSelectedRow(), 3));
						aux1.setPrecio((String) modeloTFactCli.getValueAt(table.getSelectedRow(), 4));
						aux1.setIdfactura(id.toString());						
						aux1.Insert();
					}
				}		
				setEstadoInicial();
				
			}
		});


//****************** FECHAS ************************************	
		
		dateChooser_fecha = new JDateChooser();
		dateChooser_fecha.setDateFormatString("dd-MM-yyyy");
		dateChooser_fecha.setBounds(1174, 271, 158, 20);
		frmFactProv.getContentPane().add(dateChooser_fecha);
		
		dateChooser_fechaCobro = new JDateChooser();
		dateChooser_fechaCobro.setDateFormatString("dd-MM-yyyy");
		dateChooser_fechaCobro.setBounds(1172, 307, 160, 20);
		frmFactProv.getContentPane().add(dateChooser_fechaCobro);
		
		JLabel lblFecha = new JLabel("Fecha");
		lblFecha.setBounds(1046, 274, 46, 14);
		frmFactProv.getContentPane().add(lblFecha);
		
		JLabel lblFechaDeCobro = new JLabel("Fecha de Cobro");
		lblFechaDeCobro.setBounds(1046, 310, 90, 14);
		frmFactProv.getContentPane().add(lblFechaDeCobro);
		
		
		
		
//****************** IMPORTES ************************************
		
		pnl_importes = new JPanel();
		pnl_importes.setBorder(new TitledBorder(null, "Importes", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnl_importes.setBounds(1046, 580, 286, 109);
		frmFactProv.getContentPane().add(pnl_importes);
		pnl_importes.setLayout(null);
	    
	    textField_Subtotal = new JTextField();
	    textField_Subtotal.setColumns(10);
	    textField_Subtotal.setBounds(203, 16, 73, 20);
	    pnl_importes.add(textField_Subtotal);
	    
	    textField_IvaI = new JTextField();
	    textField_IvaI.setColumns(10);
	    textField_IvaI.setBounds(203, 47, 73, 20);
	    pnl_importes.add(textField_IvaI);
	    
	    textField_Total = new JTextField();
	    textField_Total.setColumns(10);
	    textField_Total.setBounds(203, 75, 73, 20);
	    pnl_importes.add(textField_Total);
	    
	    textField_Iva = new JTextField();
	    textField_Iva.setColumns(10);
	    textField_Iva.setBounds(91, 47, 69, 20);
	    pnl_importes.add(textField_Iva);
	    
	    JLabel lblSubtotal = new JLabel("Subtotal:");
	    lblSubtotal.setBounds(10, 19, 102, 14);
	    pnl_importes.add(lblSubtotal);
	    
	    JLabel lblIva = new JLabel("Iva:");
	    lblIva.setBounds(10, 50, 102, 14);
	    pnl_importes.add(lblIva);
	    
	    JLabel lblTotal = new JLabel("Total:");
	    lblTotal.setBounds(10, 78, 102, 14);
	    pnl_importes.add(lblTotal);
	    
	    JButton btnNewButton = new JButton("");
	    btnNewButton.setIcon(new ImageIcon(FacturasClientes.class.getResource("/Imagenes/Search-icon.png")));
	    btnNewButton.setToolTipText("Buscar");
	    btnNewButton.setBounds(502, 11, 80, 55);
	    frmFactProv.getContentPane().add(btnNewButton);
	    

	    


	    
		
	setEstadoInicial();	
	}
}