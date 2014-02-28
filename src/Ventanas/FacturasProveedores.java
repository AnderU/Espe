package Ventanas;

import java.awt.Dialog.ModalityType;
import java.awt.Toolkit;
import java.awt.Window;

import javax.swing.JFrame;
import java.awt.SystemColor;
import javax.swing.ButtonGroup;
import javax.swing.DefaultCellEditor;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SwingUtilities;
import javax.swing.border.TitledBorder;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableColumn;

import BaseDatos.ConectorBD;
import Clases.DetalleComprasC;
import Clases.FacturasProveedoresC;
import Clases.FacturasProveedoresTC;
import Clases.FormaPago;
import Clases.ProveedorC;

import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Vector;

import javax.swing.JLabel;

import Tablas.TablaDetalleCompra;
import Tablas.TablaFacturasProveedores;

import com.toedter.calendar.JDateChooser;
import javax.swing.JComboBox;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.JScrollPane;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;

public class FacturasProveedores extends JFrame{

	private JFrame frmFactProv;
	private TablaFacturasProveedores modeloFactProv;
	private JTextField textField_nFactura;
	private final ButtonGroup Con_Sin = new ButtonGroup();
	private JPanel panel_observaciones;
	private JTextPane textPane_observaciones;
	private JComboBox<FormaPago> comboBox_formaPago;
	private JComboBox<ProveedorC> cmbProveedor;

	
	private JDateChooser dateChooser_fecha;
	private JDateChooser dateChooser_fechaPago;

	private JPanel pnl_importes;
	private JTextField textField_IPescado;
	private JTextField textField_IPIP;
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
	private JTable table_1;
	private JScrollPane scrollPane;
	private String FacturaSeleccionada;
	
	private JDialog busquedaFacturas;
	private JTextField txtNtalon;
	private JLabel lblNTaln;
	private JDateChooser dateChooser_talon;
	private JLabel label;
	
	/**
	 * Launch the application.
	 */

	/**
	 * Create the application.
	 */
	public FacturasProveedores() {
		initialize();
	}
	public void abreDialogo()
	{
		panelBusquedaFacProv miPanelBusqueda= new panelBusquedaFacProv();

		Window win = SwingUtilities.getWindowAncestor(this);
		  busquedaFacturas = new JDialog(win, "Búsqueda Facturas",
	      ModalityType.APPLICATION_MODAL);
		  
		  busquedaFacturas.getContentPane().add(miPanelBusqueda);
		  busquedaFacturas.setBounds(0, 0, Toolkit.getDefaultToolkit().getScreenSize().width, Toolkit.getDefaultToolkit().getScreenSize().height);
		  busquedaFacturas.setSize( Toolkit.getDefaultToolkit().getScreenSize().width, Toolkit.getDefaultToolkit().getScreenSize().height);
		  busquedaFacturas.setResizable(false);
		  busquedaFacturas.setIconImage(Toolkit.getDefaultToolkit().getImage(FacturasProveedores.class.getResource("/Imagenes/Animals-Fish-icon.png")));
		  busquedaFacturas.setLocationRelativeTo(null);
		  busquedaFacturas.setVisible(true);
		
		if (miPanelBusqueda.getTable().getSelectedRow()!=-1 && miPanelBusqueda.getVerPulsado())
		{
			String id=(String) miPanelBusqueda.getModeloFactProvRe().getValueAt(miPanelBusqueda.getTable().getSelectedRow(), 7);
			FacturaSeleccionada=id;
			String IP=(String) miPanelBusqueda.getModeloFactProvRe().getValueAt(miPanelBusqueda.getTable().getSelectedRow(), 5);
			String iva=(String) miPanelBusqueda.getModeloFactProvRe().getValueAt(miPanelBusqueda.getTable().getSelectedRow(), 4);
			String nFactura=(String) miPanelBusqueda.getModeloFactProvRe().getValueAt(miPanelBusqueda.getTable().getSelectedRow(), 2);
			textField_nFactura.setText(nFactura);
			
			String IdProveedor=(String) miPanelBusqueda.getModeloFactProvRe().getValueAt(miPanelBusqueda.getTable().getSelectedRow(), 8);
			for (int i=0; i<cmbProveedor.getItemCount(); i++)
			{
				String idAux=Integer.toString(cmbProveedor.getItemAt(i).getId());
				if (idAux.equals(IdProveedor))
				{
					cmbProveedor.setSelectedIndex(i);
				}
			}	
			
			String IdFormaPago=(String) miPanelBusqueda.getModeloFactProvRe().getValueAt(miPanelBusqueda.getTable().getSelectedRow(), 9);
			for (int i=0; i<comboBox_formaPago.getItemCount(); i++)
			{
				if (comboBox_formaPago.getItemAt(i).getId().equals(IdFormaPago))
				{
					comboBox_formaPago.setSelectedIndex(i);
				}
			}	
			
			String fecha=(String) miPanelBusqueda.getModeloFactProvRe().getValueAt(miPanelBusqueda.getTable().getSelectedRow(), 0);
			DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
			Calendar miCalendario=new GregorianCalendar();
			try {
				miCalendario.setTime(df.parse(fecha));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			dateChooser_fecha.setCalendar(miCalendario.getInstance());
			
			CargaTablaFact(id);

			String observaciones=(String) miPanelBusqueda.getModeloFactProvRe().getValueAt(miPanelBusqueda.getTable().getSelectedRow(), 2);
			textPane_observaciones.setText(observaciones);
			textField_IP.setText(IP);
			textField_Iva.setText(iva);
			Recalcula();
			setEstadoSeleccion();
			
		}
	}
	public void muestraTalon(boolean a)
	{
		txtNtalon.setVisible(a);
		dateChooser_talon.setVisible(a);
		label.setVisible(a);
		lblNTaln.setVisible(a);
	}
	public void setEstadoEditar()
	{
		btnAceptar_edit.setVisible(true);
		btnCancelar_edit.setVisible(true);
		btnAceptar.setVisible(false);
		btnCancelar.setVisible(false);
		btnNuevo.setEnabled(false);
		btnEditar.setEnabled(false);
		btnBorrar.setEnabled(false);
		dateChooser_fecha.setEnabled(true);
		dateChooser_fechaPago.setEnabled(true);
		
		textField_nFactura.setEditable(true);
		cmbProveedor.setEnabled(true);
		comboBox_formaPago.setEnabled(true);
		textPane_observaciones.setEditable(true);

		

	}
	
	public void setEstadoSeleccion()
	{
		btnEditar.setEnabled(true);
		btnBorrar.setEnabled(true);
		textField_nFactura.setEditable(false);
		cmbProveedor.setEnabled(false);
		comboBox_formaPago.setEnabled(false);
		textPane_observaciones.setEditable(false);
		dateChooser_fecha.setEnabled(false);
		dateChooser_fechaPago.setEnabled(false);
		
		
	}
	
	public void setEstadoInicial()
	{
		int row=modeloFactProv.getRowCount();
		for (int i=row-1; i>=0; i--)
			modeloFactProv.removeRow(0);
		
		FacturaSeleccionada="0";
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
		
		textField_nFactura.setEditable(false);
		cmbProveedor.setEnabled(false);
		comboBox_formaPago.setEnabled(false);
		textPane_observaciones.setEditable(false);
		
		textField_nFactura.setText("");
		cmbProveedor.setSelectedIndex(0);
		comboBox_formaPago.setSelectedIndex(0);
		
		textPane_observaciones.setText("");
		
		dateChooser_fecha.setCalendar(null);
		dateChooser_fechaPago.setCalendar(null);
		
		dateChooser_fecha.setEnabled(false);
		dateChooser_fechaPago.setEnabled(false);
		
		textField_IvaI.setText("");
		textField_Iva.setText("");
		textField_Subtotal.setText("");
		textField_IPIP.setText("");
		textField_IP.setText("");
		textField_IPescado.setText("");
		textField_Total.setText("");
		textField_IvaI.setEditable(false);
		textField_Iva.setEditable(false);
		textField_Subtotal.setEditable(false);
		textField_IPIP.setEditable(false);
		textField_IP.setEditable(false);
		textField_IPescado.setEditable(false);
		textField_Total.setEditable(false);

		
		txtNtalon.setVisible(false);
		dateChooser_talon.setVisible(false);
		label.setVisible(false);
		lblNTaln.setVisible(false);
				
	}
	
	public void setEstadoNuevo()
	{
		int row=modeloFactProv.getRowCount();
		for (int i=row-1; i>=0; i--)
			modeloFactProv.removeRow(0);
		
		btnNuevo.setEnabled(false);
		btnAceptar.setVisible(true);
		btnCancelar.setVisible(true);
		
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
		dateChooser_fecha.setEnabled(true);
		dateChooser_fechaPago.setEnabled(true);
		
			
		ResultSet rs1=ConectorBD.bdMySQL.SelectAux("configuracion","*","Id=1");
		try {
			rs1.next();
			textField_IP.setText(rs1.getObject(3).toString());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		textField_Subtotal.setText("0.0");
		textField_IPescado.setText("0.0");
	}

	/**
	 * Initialize the contents of the frame.
	 */
	public void CargaTablaFact(String idFactura)
	{
		
		int row=modeloFactProv.getRowCount();
		for (int i=row-1; i>=0; i--)
			modeloFactProv.removeRow(0);
		
		ResultSet rs=ConectorBD.bdMySQL.Select("detallecompras","*","IdFactura="+idFactura);
		try {
			while (rs.next())
			{
				ResultSet rs1=ConectorBD.bdMySQL.SelectAux("compras","IdProveedor ,Fecha , Iva","Id="+rs.getObject(5).toString());
				rs1.next();
				FacturasProveedoresTC aux = new FacturasProveedoresTC();
				aux.setIdGenero(rs.getObject(2).toString());
				ResultSet rs2=ConectorBD.bdMySQL.SelectAux1("genero","Genero","Id="+aux.getIdGenero());
				rs2.next();
				aux.setGenero(rs2.getObject(1).toString());
				aux.setCantidad(rs.getObject(3).toString());
				aux.setPrecio(rs.getObject(4).toString());
				aux.setFacturada(Boolean.parseBoolean(rs.getObject(6).toString()));
				SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
				aux.setFecha(formatoFecha.parse(rs1.getObject(2).toString()));
				aux.setIva(rs1.getObject(3).toString());
				aux.setId(rs.getObject(1).toString());
				aux.setIdCompra(rs.getObject(5).toString());
				modeloFactProv.insertRow(aux);				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	public void CargaTabla(String idProveedor)
	{
		
		int row=modeloFactProv.getRowCount();
		for (int i=row-1; i>=0; i--)
			modeloFactProv.removeRow(0);
		
		ResultSet rs=ConectorBD.bdMySQL.Select("detallecompras","*","Facturada=0");
		try {
			while (rs.next())
			{
				
				ResultSet rs1=ConectorBD.bdMySQL.SelectAux("compras","IdProveedor ,Fecha , Iva","Id="+rs.getObject(5).toString());
				rs1.next();
				if (rs1.getObject(1).toString().equals(idProveedor))
				{
					FacturasProveedoresTC aux = new FacturasProveedoresTC();
					aux.setIdGenero(rs.getObject(2).toString());
					ResultSet rs2=ConectorBD.bdMySQL.SelectAux1("genero","Genero","Id="+aux.getIdGenero());
					rs2.next();
					aux.setGenero(rs2.getObject(1).toString());
					aux.setCantidad(rs.getObject(3).toString());
					aux.setPrecio(rs.getObject(4).toString());
					aux.setFacturada(Boolean.parseBoolean(rs.getObject(6).toString()));
					SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
					aux.setFecha(formatoFecha.parse(rs1.getObject(2).toString()));
					aux.setIva(rs1.getObject(3).toString());
					aux.setId(rs.getObject(1).toString());
					aux.setIdCompra(rs.getObject(5).toString());
					modeloFactProv.insertRow(aux);	
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void Recalcula()
	{
	    if (!textField_IP.getText().equals(""))
	    {
	    	DecimalFormat df = new DecimalFormat("0.00##");
	    	Double importe=(Double.parseDouble(textField_IP.getText())/100)*Double.parseDouble(textField_IPescado.getText());
	    	textField_IPIP.setText(df.format(importe));
	    	
	    	double importeP=Double.parseDouble(textField_IPescado.getText())+Double.parseDouble(textField_IPIP.getText().replace(',', '.'));

	    	
	    	textField_Subtotal.setText(Double.toString(importeP));	 
	    	if (!textField_Iva.getText().equals(""))
	    		importe=(Double.parseDouble(textField_Iva.getText())/100)*(importeP);
	    	else
	    		importe=0.0;
	    	textField_IvaI.setText(df.format(importe));	
	    	
	    	textField_Total.setText(Double.toString(Double.parseDouble(textField_IvaI.getText().replace(',', '.'))+Double.parseDouble(textField_Subtotal.getText().replace(',','.'))));

	    	
	    }
	}
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
				Vector<String> columnNames= new Vector<String>();
				columnNames.add("Fecha");
				columnNames.add("Genero");
				columnNames.add("Cantidad");
				columnNames.add("Precio");
				columnNames.add("Subtotal");
				columnNames.add("Iva");
				columnNames.add("Incluido");
				
				Vector<FacturasProveedoresTC> vectorTabla= new Vector<FacturasProveedoresTC>();
				modeloFactProv= new TablaFacturasProveedores(vectorTabla,columnNames);

			    table_1 = new JTable(modeloFactProv);
			    scrollPane = new JScrollPane();
			    scrollPane.setBounds(10, 228, 1026, 467);
			    frmFactProv.getContentPane().add(scrollPane);
			    scrollPane.setViewportView(table_1);		
			    table_1.getModel().addTableModelListener(new TableModelListener() {


					@Override
					public void tableChanged(TableModelEvent arg0) {
						// TODO Auto-generated method stub
						Double total=0.0;
						String iva="";
						int seleccionado=0;
						boolean error=false;
						for (int i=0; i<modeloFactProv.getRowCount(); i++)
						{
							if ((Boolean) modeloFactProv.getValueAt(i, 6) )
							{
								String ivaAux=(String) modeloFactProv.getValueAt(i, 5);
								if (iva.equals(ivaAux) || seleccionado==0)
								{
									total+= Double.parseDouble((String) modeloFactProv.getValueAt(i, 4));
									iva=(String) modeloFactProv.getValueAt(i, 5);
									seleccionado=1;
								}
								else
								{
									error=true;
									i=modeloFactProv.getRowCount();
								}
							}
						}
						
						if (error)
						{
							total=0.0;							
							JOptionPane.showMessageDialog(frmFactProv, "Por favor seleccione compras con el mismo iva");
							btnAceptar.setEnabled(false);
						}
						else
							btnAceptar.setEnabled(true);
							
						textField_Iva.setText(iva);
						textField_IPescado.setText(total.toString());
						Recalcula();
					}
			      });
		

		
//***************** N FACTURA ********************************	
		JLabel lblNFactura = new JLabel("N\u00BA Factura");
		lblNFactura.setBounds(10, 90, 90, 14);
		frmFactProv.getContentPane().add(lblNFactura);
		
		textField_nFactura = new JTextField();
		textField_nFactura.setBounds(10, 115, 250, 20);
		frmFactProv.getContentPane().add(textField_nFactura);
		textField_nFactura.setColumns(10);
		
//***************** PROVEEDOR ********************************		
		JLabel lblProveedor = new JLabel("Proveedor");
		lblProveedor.setBounds(487, 90, 65, 14);
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
 	    cmbProveedor.addItemListener(new ItemListener() {
 	    	public void itemStateChanged(ItemEvent arg0) {
 	    		if (arg0.getStateChange() == ItemEvent.SELECTED) 
 	    		{
 	 	    		String id=Integer.toString(((ProveedorC)cmbProveedor.getSelectedItem()).getId());
 		    		CargaTabla(id);
 	    		}

 	    	}
 	    });


 	    cmbProveedor.setEnabled(false);
 	    cmbProveedor.setBounds(487, 115, 549, 20);
 	    frmFactProv.getContentPane().add(cmbProveedor);
    
		
//***************** FORMA DE PAGO ********************************		
		JLabel lblFormaDePago = new JLabel("Forma de Pago");
		lblFormaDePago.setBounds(9, 157, 111, 14);
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
		comboBox_formaPago.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent event) {
				if (event.getStateChange() == ItemEvent.SELECTED) {
					if (((FormaPago)event.getItem()).getId().equals("1"))
					{
						muestraTalon(true);
					}
					else
						muestraTalon(false);
			       }
			}
		});
		comboBox_formaPago.setEnabled(false);
		comboBox_formaPago.setBounds(110, 155, 285, 20);
		frmFactProv.getContentPane().add(comboBox_formaPago);

//****************** OBSERVACIONES ************************************

		panel_observaciones = new JPanel();
		panel_observaciones.setBounds(487, 144, 548, 77);
		panel_observaciones.setBorder(new TitledBorder(null, "Observaciones", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		frmFactProv.getContentPane().add(panel_observaciones);
		panel_observaciones.setLayout(null);
		
		textPane_observaciones = new JTextPane();
		textPane_observaciones.setBounds(10, 21, 529, 46);
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
				
				setEstadoEditar();
				
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
					aux.setIdFactProv(FacturaSeleccionada);
					aux.Delete();
					setEstadoInicial();
				}
				
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
				
				if (!textField_nFactura.getText().equals("") && comboBox_formaPago.getSelectedIndex()!=0 && cmbProveedor.getSelectedIndex()!=0)
				{				
					
					boolean x=false;
					for (int i=0; i<modeloFactProv.getRowCount(); i++)
					{
						if ((Boolean) modeloFactProv.getValueAt(i,6))
						{
							x=true;
							break;
						}
					}
					if (x)
					{	
						FacturasProveedoresC aux= new FacturasProveedoresC();
			
						aux.setIdFormaPago(((FormaPago)comboBox_formaPago.getSelectedItem()).getId());
						aux.setIdProveedor(Integer.toString(((ProveedorC)cmbProveedor.getSelectedItem()).getId()));
						SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
						
						aux.setFecha(formatoFecha.format(dateChooser_fecha.getCalendar().getTime()));
						if(dateChooser_fechaPago.getCalendar()!=null) 
							aux.setFechaPago(formatoFecha.format(dateChooser_fechaPago.getCalendar().getTime()));
						aux.setnFactura(textField_nFactura.getText());
						aux.setObservaciones(textPane_observaciones.getText());
						aux.setIva(textField_Iva.getText());
						aux.setImpuestos(textField_IP.getText());
						
						aux.Update();
						
						setEstadoInicial();
					}
					else
						JOptionPane.showMessageDialog(frmFactProv, "Seleccione al menos una compra");	
				}	
				else
					JOptionPane.showMessageDialog(frmFactProv, "Datos obligatorios no rellenados");
				
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
				
				if (!textField_nFactura.getText().equals("") && comboBox_formaPago.getSelectedIndex()!=0 && cmbProveedor.getSelectedIndex()!=0)
				{				
					
					boolean x=false;
					for (int i=0; i<modeloFactProv.getRowCount(); i++)
					{
						if ((Boolean) modeloFactProv.getValueAt(i,6))
						{
							x=true;
							break;
						}
					}
					if (x)
					{	
						FacturasProveedoresC aux= new FacturasProveedoresC();
			
						aux.setIdFormaPago(((FormaPago)comboBox_formaPago.getSelectedItem()).getId());
						aux.setIdProveedor(Integer.toString(((ProveedorC)cmbProveedor.getSelectedItem()).getId()));
						SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
						
						aux.setFecha(formatoFecha.format(dateChooser_fecha.getCalendar().getTime()));
						if(dateChooser_fechaPago.getCalendar()!=null) 
							aux.setFechaPago(formatoFecha.format(dateChooser_fechaPago.getCalendar().getTime()));
						if(dateChooser_fechaPago.getCalendar()!=null) 
							aux.setFechaEmision(formatoFecha.format(dateChooser_talon.getCalendar().getTime()));
						aux.setnTalon(txtNtalon.getText());
						aux.setnFactura(textField_nFactura.getText());
						aux.setObservaciones(textPane_observaciones.getText());
						aux.setIva(textField_Iva.getText());
						aux.setImpuestos(textField_IP.getText());
						
						int id=aux.Insert();
						for (int i=0; i<modeloFactProv.getRowCount(); i++)
						{
							if ((Boolean)modeloFactProv.getValueAt(i, 6))
							{
								DetalleComprasC auxC= new DetalleComprasC();
								auxC.setIdGenero((String)modeloFactProv.getValueAt(i, 8));
								auxC.setCantidad((String)modeloFactProv.getValueAt(i, 2));
								auxC.setPrecio((String)modeloFactProv.getValueAt(i, 3));
								auxC.setIdcompra((String)modeloFactProv.getValueAt(i, 9));
								auxC.setFacturada(true);
								auxC.setIdfactura(Integer.toString(id));
								auxC.setId((String)modeloFactProv.getValueAt(i, 7));
								auxC.Update();						
								
							}
						}
						setEstadoInicial();
					}
					else
						JOptionPane.showMessageDialog(frmFactProv, "Seleccione al menos una compra");	
				}	
				else
					JOptionPane.showMessageDialog(frmFactProv, "Datos obligatorios no rellenados");
				
			}
		});


//****************** FECHAS ************************************	
		
		dateChooser_fecha = new JDateChooser();
		dateChooser_fecha.setDateFormatString("dd-MM-yyyy");
		dateChooser_fecha.setBounds(1202, 112, 130, 20);
		frmFactProv.getContentPane().add(dateChooser_fecha);
		
		dateChooser_fechaPago = new JDateChooser();
		dateChooser_fechaPago.setDateFormatString("dd-MM-yyyy");
		dateChooser_fechaPago.setBounds(1202, 155, 130, 20);
		frmFactProv.getContentPane().add(dateChooser_fechaPago);
		
		JLabel lblFecha = new JLabel("Fecha");
		lblFecha.setBounds(1102, 118, 46, 14);
		frmFactProv.getContentPane().add(lblFecha);
		
		JLabel lblFechaDePago = new JLabel("Fecha de Pago");
		lblFechaDePago.setBounds(1102, 158, 90, 14);
		frmFactProv.getContentPane().add(lblFechaDePago);
		
		
		
		
//****************** IMPORTES ************************************
		
		pnl_importes = new JPanel();
		pnl_importes.setBounds(1046, 274, 286, 415);
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
	    
	    JLabel lblSubtotal = new JLabel("Subtotal:");
	    lblSubtotal.setBounds(10, 100, 102, 14);
	    pnl_importes.add(lblSubtotal);
	    
	    JLabel lblIva = new JLabel("Iva:");
	    lblIva.setBounds(10, 125, 102, 14);
	    pnl_importes.add(lblIva);
	    
	    JLabel lblTotal = new JLabel("Total:");
	    lblTotal.setBounds(10, 150, 102, 14);
	    pnl_importes.add(lblTotal);
	    
	    JButton btnBuscar = new JButton("");
	    btnBuscar.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent arg0) {
	    		
	    	     abreDialogo();
	    		
	    		//FacturasProveedoresBusqueda ventanaBusqueda= new FacturasProveedoresBusqueda(frmFactProv.hashCode());
	    	}
	    });
	    btnBuscar.setIcon(new ImageIcon(FacturasProveedores.class.getResource("/Imagenes/Search-icon.png")));
	    btnBuscar.setToolTipText("Buscar");
	    btnBuscar.setBounds(502, 11, 80, 55);
	    frmFactProv.getContentPane().add(btnBuscar);
	    
	    txtNtalon = new JTextField();
	    txtNtalon.setText("");
	    txtNtalon.setColumns(10);
	    txtNtalon.setBounds(110, 183, 90, 20);
	    frmFactProv.getContentPane().add(txtNtalon);
	    
	    lblNTaln = new JLabel("N\u00BA Tal\u00F3n");
	    lblNTaln.setBounds(8, 186, 111, 14);
	    frmFactProv.getContentPane().add(lblNTaln);
	    
	    label = new JLabel("Fecha");
	    label.setBounds(210, 186, 46, 14);
	    frmFactProv.getContentPane().add(label);
	    
	    dateChooser_talon = new JDateChooser();
	    dateChooser_talon.setDateFormatString("dd-MM-yyyy");
	    dateChooser_talon.setBounds(265, 186, 130, 20);
	    frmFactProv.getContentPane().add(dateChooser_talon);
	    

	    

	    
	    
		
	setEstadoInicial();	
	}
}