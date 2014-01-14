package Ventanas;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Vector;

import Tablas.ComboRenderer;
import Tablas.TablaDetalleCompra;
import Tablas.TablaListadoCompras;

import BaseDatos.ConectorBD;
import Clases.ComprasC;
import Clases.DetalleComprasC;
import Clases.GeneroC;
import Clases.ProveedorC;
import com.toedter.calendar.JCalendar;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;


public class Compras 
{

	private JFrame frmCompras;
	private JButton btnNuevo;
	private JButton btnEditar;
	private JButton btnBorrar;
	private JButton btnAceptar;
	private JButton btnCancelar;
	private JButton btnAceptar_edit;
	private JButton btnCancelar_edit;
	private JPanel Desglose;
	private JComboBox cmbProveedor;
	private JTable tListado;
	private JTextField textField;
	private JScrollPane sPGenero;
	private JTable tPescado;
	private JPanel Observaciones;
	private JTextField textField_IPescado;
	private JTextField textField_IPIP;
	private JTextField textField_Subtotal;
	private JTextField textField_IvaI;
	private JTextField textField_Total;
	private JTextField textField_IP;
	private JTextField textField_Iva;
	private TablaDetalleCompra modeloTDetalle;
	private com.toedter.calendar.JCalendar calendar1;
	private  JTextPane txtPObservaciones;
	private TablaListadoCompras modeloListado;
	private JButton btnBorrarDetalle;
	
	public static Date fecha;


/**
 * Create various instances of a JCalendar.
 */
	
	public void setEstadoInicial(){
	
		btnBorrarDetalle.setVisible(false);
		
		cmbProveedor.setSelectedIndex(0);
		
		tListado.setEnabled(true);
		tListado.clearSelection();
		
		btnAceptar.setVisible(false);
		btnCancelar.setVisible(false);
		btnAceptar_edit.setVisible(false);
		btnCancelar_edit.setVisible(false);
		btnNuevo.setEnabled(true);
		btnEditar.setVisible(true);
		btnBorrar.setVisible(true);
		btnEditar.setEnabled(false);
		btnBorrar.setEnabled(false);
		tPescado.setEnabled(false);
		cmbProveedor.setEnabled(false);
		cmbProveedor.setSelectedIndex(0);
		
		textField_IvaI.setText("");
		textField_Iva.setText("");
		textField_Subtotal.setText("");
		textField_IPIP.setText("");
		textField_IP.setText("");
		textField_IPescado.setText("");
		textField_Total.setText("");
		txtPObservaciones.setText("");
		
		textField_IvaI.setEditable(false);
		textField_Iva.setEditable(false);
		textField_Subtotal.setEditable(false);
		textField_IPIP.setEditable(false);
		textField_IP.setEditable(false);
		textField_IPescado.setEditable(false);
		textField_Total.setEditable(false);
		txtPObservaciones.setEditable(false);
		
		
		int rows = this.modeloTDetalle.getRowCount(); 
		for(int i =0  ; i<rows ; i++)
		{
			modeloTDetalle.removeRow(0); 
		}
		
	} 

	public void setEstadoNuevo()
	{
		textField_Iva.setEditable(true);
		textField_IP.setEditable(true);
		txtPObservaciones.setEditable(true);
		tListado.clearSelection();
		btnNuevo.setEnabled(false);
		btnAceptar.setVisible(true);
		btnCancelar.setVisible(true);
		tListado.setEnabled(false);
		cmbProveedor.setEnabled(true);
		tPescado.setEnabled(true);
		
		
		cmbProveedor.setSelectedIndex(0);
		
		int rows = this.modeloTDetalle.getRowCount(); 
		for(int i =0  ; i<rows ; i++)
		{
			modeloTDetalle.removeRow(0); 
		}		
		DetalleComprasC aux = new DetalleComprasC();
		modeloTDetalle.insertRow(aux);
		
		btnEditar.setEnabled(false);
		btnBorrar.setEnabled(false);	
	    ResultSet rs1=ConectorBD.bdMySQL.Select("configuracion", "*", "Id=1");
	    try {
			while (rs1.next())
				textField_IP.setText(rs1.getObject(3).toString());
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	    rs1=ConectorBD.bdMySQL.Select("configuracion", "*", "Id=2");
	    try {
			while (rs1.next())
				textField_Iva.setText(rs1.getObject(3).toString());
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	    textField_Subtotal.setText("0.0");
	    textField_IPescado.setText("0.0");
	    

	}
	
	public void setEstadoSeleccion()
	{
		
		btnAceptar.setVisible(false);
		btnCancelar.setVisible(false);
		btnAceptar_edit.setVisible(false);
		btnCancelar_edit.setVisible(false);
		btnEditar.setEnabled(true);
		btnBorrar.setEnabled(true);
		int rows = this.modeloTDetalle.getRowCount(); 
		for(int i =0  ; i<rows ; i++)
		{
			modeloTDetalle.removeRow(0); 
		}
	}
	
	public void setEstadoEditar()
	{
		textField_Iva.setEditable(true);
		textField_IP.setEditable(true);
		txtPObservaciones.setEditable(true);
		tListado.setEnabled(false);
		btnAceptar_edit.setVisible(true);
		btnCancelar_edit.setVisible(true);
		btnNuevo.setEnabled(false);
		btnBorrar.setEnabled(false);
		btnEditar.setEnabled(false);
		tPescado.setEnabled(true);
		cmbProveedor.setEnabled(true);


	}
    public void setUpSportColumn(JTable table,  TableColumn columnaGenero) {
     	   
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
	
   public void Recalcula()
   {
	    if (!textField_IP.getText().equals(""))
	    {
	    	DecimalFormat df = new DecimalFormat("0.00##");
	    	Double importe=(Double.parseDouble(textField_IP.getText())/100)*Double.parseDouble(textField_IPescado.getText());
	    	textField_IPIP.setText(df.format(importe));
	    	
	    	double importeP=Double.parseDouble(textField_IPescado.getText())+Double.parseDouble(textField_IPIP.getText().replace(',', '.'));

	    	
	    	textField_Subtotal.setText(Double.toString(importeP));	 
	    	
	    	importe=(Double.parseDouble(textField_Iva.getText())/100)*(importeP);
	    	textField_IvaI.setText(df.format(importe));	
	    	
	    	textField_Total.setText(Double.toString(Double.parseDouble(textField_IvaI.getText().replace(',', '.'))+Double.parseDouble(textField_Subtotal.getText().replace(',','.'))));

	    	
	    }
	   
   }
   
   public void RecargaListado()
   {
	   //Borramos todo
	   setEstadoInicial();
	   int nRows=modeloListado.getRowCount();
	   for (int i=0; i<nRows; i++)
		   modeloListado.removeRow(0);
	   //Recargamos
	   Vector <String> columnNames = new  Vector <String>(); 
		Vector<ComprasC> vectorTabla0= new Vector<ComprasC>();
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		ResultSet rs=ConectorBD.bdMySQL.Select("compras","*","Fecha ='"+df.format(fecha)+"'");
			try {
	 			while (rs.next())
	 			{
	 				ComprasC a=new ComprasC();
	 				a.setId(rs.getObject(1).toString());
	 				a.setIdproveedor(rs.getObject(2).toString());
	 				a.setIva(rs.getObject(6).toString());
	 				a.setImpuestos(rs.getObject(5).toString());
	 				ResultSet rs1=ConectorBD.bdMySQL.SelectAux("proveedores","Proveedor","Id="+a.getIdproveedor());
	 				rs1.next();
	 				a.setProveedor(rs1.getObject(1).toString());
	 				rs1=ConectorBD.bdMySQL.SelectAux("detalleCompras","SUM(Precio*Cantidad)","IdCompra="+a.getId()+"");
	 				rs1.next();
	 				double iva=Double.parseDouble(a.getIva());
	 				double impuestos=Double.parseDouble(a.getImpuestos());
	 				double importeSinIva=Double.parseDouble(rs1.getObject(1).toString());
	 				a.setImporte(Double.toString(importeSinIva*(1+impuestos/100)*(1+iva/100)));
	 				modeloListado.insertRow(a);	 				
	 			}
	 		} catch (SQLException e1) {
	 			// TODO Auto-generated catch block
	 			e1.printStackTrace();
	 		}
	   
   }
    
public
Compras()
{
	fecha=new Date();
    // Set up the frame
	frmCompras = new JFrame();
	frmCompras.getContentPane().setBackground(SystemColor.textHighlight);
	frmCompras.setIconImage(Toolkit.getDefaultToolkit().getImage(Compras.class.getResource("/Imagenes/Animals-Fish-icon.png")));
    frmCompras.setTitle("Compras");
    frmCompras.setExtendedState(JFrame.MAXIMIZED_BOTH);
    frmCompras.getContentPane().setLayout(null);
    
    btnNuevo = new JButton("");
    btnNuevo.addActionListener(new ActionListener() {
    	public void actionPerformed(ActionEvent arg0) {
    		setEstadoNuevo();
    	}
    });
    btnNuevo.setIcon(new ImageIcon(Compras.class.getResource("/Imagenes/Add-icon.png")));
    btnNuevo.setToolTipText("Nuevo");
    btnNuevo.setEnabled(true);
    btnNuevo.setBounds(395, 11, 80, 55);
    frmCompras.getContentPane().add(btnNuevo);
    
    btnEditar = new JButton("");
    btnEditar.addActionListener(new ActionListener() {
    	public void actionPerformed(ActionEvent e) {
    		setEstadoEditar();
    	}
    });
    btnEditar.setIcon(new ImageIcon(Compras.class.getResource("/Imagenes/Pencil-icon.png")));
    btnEditar.setToolTipText("Editar");
    btnEditar.setEnabled(false);
    btnEditar.setBounds(485, 11, 80, 55);
    frmCompras.getContentPane().add(btnEditar);
    
    btnBorrar = new JButton("");
    btnBorrar.addActionListener(new ActionListener() {
    	public void actionPerformed(ActionEvent e) {
    		int result=JOptionPane.showConfirmDialog(frmCompras, "¿Está seguro de que desea borrar este elemento?", "¡Atención!", JOptionPane.OK_CANCEL_OPTION,JOptionPane.WARNING_MESSAGE);
    		if (result==JOptionPane.OK_OPTION)
    		{
    			ComprasC aux = new ComprasC();
    			aux.setId((String) modeloListado.getValueAt(tListado.getSelectedRow(),3));
    			aux.Delete();
    			RecargaListado();
    		}
    		setEstadoInicial();
    	}
    });
    btnBorrar.setIcon(new ImageIcon(Compras.class.getResource("/Imagenes/Trash-icon.png")));
    btnBorrar.setToolTipText("Borrar");
    btnBorrar.setEnabled(false);
    btnBorrar.setBounds(575, 11, 80, 55);
    frmCompras.getContentPane().add(btnBorrar);
    
    btnAceptar = new JButton("");
    btnAceptar.addActionListener(new ActionListener() {
    	public void actionPerformed(ActionEvent e) {
    		if (cmbProveedor.getSelectedIndex()!=0 &&
    				(modeloTDetalle.getRowCount()>1 || 
    						(!modeloTDetalle.getValueAt(0,0).equals("") && !modeloTDetalle.getValueAt(0,1).equals("") && !modeloTDetalle.getValueAt(0,2).equals(""))
    				)
    		)
    		{
    			ComprasC auxC= new ComprasC();

    			auxC.setIdproveedor(Integer.toString(((ProveedorC)cmbProveedor.getSelectedItem()).getId()));
    			auxC.setObservaciones(txtPObservaciones.getText());
    			auxC.setImpuestos(textField_IP.getText());
    			auxC.setIva(textField_Iva.getText());
    			auxC.setFecha(fecha);
    			int id=auxC.Insert();
    			
    			DetalleComprasC auxDC= new DetalleComprasC();
    			for (int i=0; i<tPescado.getRowCount()-1; i++)
    			{
    				auxDC.setIdGenero((String) modeloTDetalle.getValueAt(i, 4));
    				auxDC.setCantidad((String) modeloTDetalle.getValueAt(i, 1));
    				auxDC.setPrecio((String) modeloTDetalle.getValueAt(i, 2));
    				auxDC.setIdcompra(Integer.toString(id));
    				auxDC.Insert();
    			}
    			
    			
    			RecargaListado();
    		}
    		else
    			JOptionPane.showMessageDialog(frmCompras, "Datos obligatorios no rellenados");
    	}
    });
    btnAceptar.setIcon(new ImageIcon(Compras.class.getResource("/Imagenes/Accept-icon.png")));
    btnAceptar.setVisible(false);
    btnAceptar.setToolTipText("Aceptar");
    btnAceptar.setBounds(665, 11, 80, 55);
    frmCompras.getContentPane().add(btnAceptar);
    
    btnCancelar = new JButton("");
    btnCancelar.addActionListener(new ActionListener() {
    	public void actionPerformed(ActionEvent e) {
    		
    		setEstadoInicial();
    	}
    });
    btnCancelar.setVisible(false);
    btnCancelar.setIcon(new ImageIcon(Compras.class.getResource("/Imagenes/Delete-icon.png")));
    btnCancelar.setToolTipText("Cancelar");
    btnCancelar.setBounds(755, 11, 80, 55);
    frmCompras.getContentPane().add(btnCancelar);
    
    btnAceptar_edit = new JButton("");
    btnAceptar_edit.setIcon(new ImageIcon(Compras.class.getResource("/Imagenes/Accept-icon.png")));
    btnAceptar_edit.addActionListener(new ActionListener() {
    	public void actionPerformed(ActionEvent e) {
    		if (cmbProveedor.getSelectedIndex()!=0 &&
    				(modeloTDetalle.getRowCount()>1 || 
    						(!modeloTDetalle.getValueAt(0,0).equals("") && !modeloTDetalle.getValueAt(0,1).equals("") && !modeloTDetalle.getValueAt(0,2).equals(""))
    				)
    		)
    		{
    			ComprasC auxC= new ComprasC();

    			auxC.setIdproveedor(Integer.toString(((ProveedorC)cmbProveedor.getSelectedItem()).getId()));
    			auxC.setObservaciones(txtPObservaciones.getText());
    			auxC.setImpuestos(textField_IP.getText());
    			auxC.setIva(textField_Iva.getText());
    			auxC.setFecha(fecha);
    			auxC.setId((String) modeloListado.getValueAt(tListado.getSelectedRow(),3));
    			auxC.Update();
    			
    			DetalleComprasC auxDC= new DetalleComprasC();
    			for (int i=0; i<tPescado.getRowCount()-1; i++)
    			{
    				auxDC.setIdGenero((String) modeloTDetalle.getValueAt(i, 4));
    				auxDC.setCantidad((String) modeloTDetalle.getValueAt(i, 1));
    				auxDC.setPrecio((String) modeloTDetalle.getValueAt(i, 2));
    				auxDC.setIdcompra(auxC.getId());
    				auxDC.setId((String) modeloTDetalle.getValueAt(i, 5));
    				if (auxDC.getId().equals(""))
    					auxDC.Insert();
    				else
    					auxDC.Update();
    			}

    			RecargaListado();
    		}
    		else
    			JOptionPane.showMessageDialog(frmCompras, "Datos obligatorios no rellenados");
    	}
    });
    btnAceptar_edit.setVisible(false);
    btnAceptar_edit.setToolTipText("Aceptar");
    btnAceptar_edit.setBounds(665, 11, 80, 55);
    frmCompras.getContentPane().add(btnAceptar_edit);
    
    btnCancelar_edit = new JButton("");
    btnCancelar_edit.setIcon(new ImageIcon(Compras.class.getResource("/Imagenes/Delete-icon.png")));
    btnCancelar_edit.addActionListener(new ActionListener() {
    	public void actionPerformed(ActionEvent e) {
    		setEstadoInicial();
    	}
    });
    btnCancelar_edit.setVisible(false);
    btnCancelar_edit.setToolTipText("Cancelar");
    btnCancelar_edit.setBounds(755, 11, 80, 55);
    frmCompras.getContentPane().add(btnCancelar_edit);
    
    JScrollPane sPListado = new JScrollPane();
    sPListado.setBounds(10, 235, 375, 460);
    frmCompras.getContentPane().add(sPListado);
    //---------- Cargamos listado derecho de compras
    Vector <String> columnNames = new  Vector <String>(); 
	Vector<ComprasC> vectorTabla0= new Vector<ComprasC>();
	DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	columnNames.add("Proveedor");
	columnNames.add("Iva");
	columnNames.add("Importe");
    ResultSet rs=ConectorBD.bdMySQL.Select("compras","*","Fecha ='"+df.format(new Date())+"'");
    
		try {
 			while (rs.next())
 			{
 				ComprasC a=new ComprasC();
 				a.setId(rs.getObject(1).toString());
 				a.setIdproveedor(rs.getObject(2).toString());
 				a.setIva(rs.getObject(6).toString());
 				a.setImpuestos(rs.getObject(5).toString());
 				ResultSet rs1=ConectorBD.bdMySQL.SelectAux("proveedores","Proveedor","Id="+a.getIdproveedor());
 				rs1.next();
 				a.setProveedor(rs1.getObject(1).toString());
 				rs1=ConectorBD.bdMySQL.SelectAux("detalleCompras","SUM(Precio*Cantidad)","IdCompra="+a.getId()+"");
 				double iva=Double.parseDouble(a.getIva());
 				double impuestos=Double.parseDouble(a.getImpuestos());
 				double importeSinIva=0.0;
 				while (rs1.next())
 				{
	 				importeSinIva=Double.parseDouble(rs1.getObject(1).toString());
 				}
 				a.setImporte(Double.toString(importeSinIva*(1+impuestos/100)*(1+iva/100)));
 				vectorTabla0.addElement(a);
 				
 			}
 		} catch (SQLException e1) {
 			// TODO Auto-generated catch block
 			e1.printStackTrace();
 		}
    modeloListado = new TablaListadoCompras(vectorTabla0,columnNames);
	
    tListado = new JTable(modeloListado);
    tListado.addMouseListener(new MouseAdapter() {
    	@Override
    	public void mouseClicked(MouseEvent arg0) {
    		
    		if (tListado.isEnabled())
    		{
	    		setEstadoSeleccion();
	    		String id=(String) modeloListado.getValueAt(tListado.getSelectedRow(), 3);
	    		String impuestos=(String) modeloListado.getValueAt(tListado.getSelectedRow(), 4);
	    		String iva=(String) modeloListado.getValueAt(tListado.getSelectedRow(), 1);
	    		String idProveedor= (String) modeloListado.getValueAt(tListado.getSelectedRow(), 5);
	    		ResultSet rs=ConectorBD.bdMySQL.Select("detalleCompras","*","IdCompra="+id);
	    		try {
					while (rs.next())
					{
						DetalleComprasC a = new DetalleComprasC();
						a.setId(rs.getObject(1).toString());
						a.setIdGenero(rs.getObject(2).toString());
						a.setCantidad(rs.getObject(3).toString());
						a.setPrecio(rs.getObject(4).toString());
						a.setIdcompra(rs.getObject(5).toString());
						a.setFacturada(Boolean.parseBoolean(rs.getObject(6).toString()));
						ResultSet rs1=ConectorBD.bdMySQL.SelectAux("genero","Genero","Id="+a.getIdGenero());
						rs1.next();
						a.setGenero(rs1.getObject(1).toString());
						modeloTDetalle.insertRow(a);
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	    		textField_IP.setText(impuestos);
	    		textField_Iva.setText(iva);
	    		
	    		for (int i=0; i<cmbProveedor.getComponentCount(); i++)
	    		{
	    			if (Integer.parseInt(idProveedor)==((ProveedorC)cmbProveedor.getItemAt(i)).getId())
	    			{
	    				cmbProveedor.setSelectedIndex(i);
	    			}
	    		}
	    		// PEQUEÑO ARREGLO
	    		int row= modeloTDetalle.getRowCount();
	    		for (int i=row-1; i>=0; i--)
	    		{
	    			if (modeloTDetalle.getValueAt(i, 1).equals(""))
	    			{
	    				modeloTDetalle.removeRow(i);
	    			}
	    		}
	    		
	    	}
    	}
    });
    sPListado.setViewportView(tListado);
    //----------------------------------------
    Desglose = new JPanel();
    Desglose.setBorder(new LineBorder(new Color(0, 0, 0)));
    Desglose.setBounds(395, 77, 957, 618);
    frmCompras.getContentPane().add(Desglose);
    Desglose.setLayout(null);
    // combobox proveedores

    
    ProveedorC aux1= new ProveedorC(); 
    rs=ConectorBD.bdMySQL.Select("proveedores", "*", "true");
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
 	    cmbProveedor.setBounds(10, 11, 342, 20);
 	    Desglose.add(cmbProveedor);
    
    //-----------------------------------
    
    
    JLabel lblPesoDelPescado = new JLabel("Peso del pescado:");
    lblPesoDelPescado.setBounds(381, 14, 111, 14);
    Desglose.add(lblPesoDelPescado);
    
    textField = new JTextField();
    textField.setEditable(false);
    textField.setBounds(499, 11, 86, 20);
    Desglose.add(textField);
    textField.setColumns(10);
    
    sPGenero = new JScrollPane();

    sPGenero.setBounds(10, 47, 937, 347);
    Desglose.add(sPGenero);
    //----------------------------------------- TABLA DETALLE COMPRAS
    
    
	// Cargamos la tabla
    columnNames = new  Vector <String>(); 
    columnNames.add("Genero");
    columnNames.add("Cantidad (Kg)");
    columnNames.add("Precio (€/Kg)");
    columnNames.add("Importe");
    Vector<DetalleComprasC> vectorTabla= new Vector<DetalleComprasC>();
    
    
    modeloTDetalle= new TablaDetalleCompra(vectorTabla,columnNames);
    
    
    
    tPescado = new JTable(modeloTDetalle);
    tPescado.addMouseListener(new MouseAdapter() {
    	@Override
    	public void mouseClicked(MouseEvent arg0) {
    		if (tPescado.isEnabled())
    			btnBorrarDetalle.setVisible(true);
    		
    		
    	}
    });
    setUpSportColumn(tPescado, tPescado.getColumnModel().getColumn(0));
    tPescado.getModel().addTableModelListener(new TableModelListener() {

		@Override
		public void tableChanged(TableModelEvent arg0) {
			// TODO Auto-generated method stub
		if (tPescado.getRowCount()>0)
		{
			if (!modeloTDetalle.getValueAt(tPescado.getRowCount()-1,0).equals("") && 
				!modeloTDetalle.getValueAt(tPescado.getRowCount()-1,1).equals("") &&
				!modeloTDetalle.getValueAt(tPescado.getRowCount()-1,2).equals(""))
			{
				DetalleComprasC aux = new DetalleComprasC();
				modeloTDetalle.insertRow(aux);
				
			}
			Double op1= new Double(0);
			double op2;
			
			Double op3= new Double(0);
			double op4;
			for (int i=0; i<tPescado.getRowCount(); i++)
			{
				op2=0;
				if (!modeloTDetalle.getValueAt(i,1).equals(""))
				{
					op2=Double.parseDouble(modeloTDetalle.getValueAt(i,1).toString());
				}
				op1+=op2;
				
				op4=0;
				if (!modeloTDetalle.getValueAt(i,3).equals(""))
				{
					op4=Double.parseDouble(modeloTDetalle.getValueAt(i,3).toString());
				}
				op3+=op4;
				

			}
			
			textField.setText(op1.toString());
			textField_IPescado.setText(op3.toString());
			Recalcula();
		}	
			
		}
      });
    tPescado.setEnabled(false);
    sPGenero.setViewportView(tPescado);
    //------------------------------------------
    Observaciones = new JPanel();
    Observaciones.setBorder(new TitledBorder(null, "Observaciones", TitledBorder.LEADING, TitledBorder.TOP, null, null));
    Observaciones.setBounds(10, 405, 547, 202);
    Desglose.add(Observaciones);
    Observaciones.setLayout(null);
    
    txtPObservaciones = new JTextPane();
    txtPObservaciones.setMaximumSize(new Dimension(527, 196));
    txtPObservaciones.setBounds(10, 23, 527, 196);
    Observaciones.add(txtPObservaciones);
    
    JPanel Importes = new JPanel();
    Importes.setBorder(new TitledBorder(null, "Importes", TitledBorder.LEADING, TitledBorder.TOP, null, null));
    Importes.setBounds(567, 405, 380, 202);
    Desglose.add(Importes);
    Importes.setLayout(null);
    
    JLabel lblImportePescado = new JLabel("Importe Pescado:");
    lblImportePescado.setBounds(10, 25, 102, 14);
    Importes.add(lblImportePescado);
    
    JLabel lblImpuestoPort = new JLabel("Impuesto Port:");
    lblImpuestoPort.setBounds(10, 50, 102, 14);
    Importes.add(lblImpuestoPort);
    
    JLabel lblSubtotal = new JLabel("Subtotal:");
    lblSubtotal.setBounds(10, 100, 102, 14);
    Importes.add(lblSubtotal);
    
    JLabel lblIva = new JLabel("Iva:");
    lblIva.setBounds(10, 125, 102, 14);
    Importes.add(lblIva);
    
    JLabel lblTotal = new JLabel("Total:");
    lblTotal.setBounds(10, 150, 102, 14);
    Importes.add(lblTotal);
    
    textField_IPescado = new JTextField();

    textField_IPescado.setBounds(203, 22, 167, 20);

    Importes.add(textField_IPescado);
    textField_IPescado.setColumns(10);
    
    textField_IPIP = new JTextField();
    textField_IPIP.setColumns(10);
    textField_IPIP.setBounds(203, 47, 167, 20);    
    Importes.add(textField_IPIP);
    
    textField_Subtotal = new JTextField();
    textField_Subtotal.setColumns(10);
    textField_Subtotal.setBounds(203, 97, 167, 20);
    Importes.add(textField_Subtotal);
    
    textField_IvaI = new JTextField();
    textField_IvaI.setColumns(10);
    textField_IvaI.setBounds(203, 122, 167, 20);
    Importes.add(textField_IvaI);
    
    textField_Total = new JTextField();
    textField_Total.setColumns(10);
    textField_Total.setBounds(203, 147, 167, 20);
    Importes.add(textField_Total);
    
    textField_IP = new JTextField();
    textField_IP.addFocusListener(new FocusAdapter() {
    	@Override
    	public void focusLost(FocusEvent arg0) {
    		
    		Recalcula();
    	}
    });
    textField_IP.setColumns(10);
    textField_IP.setBounds(122, 47, 69, 20);

    Importes.add(textField_IP);
    
    textField_Iva = new JTextField();
    textField_Iva.addFocusListener(new FocusAdapter() {
    	@Override
    	public void focusLost(FocusEvent e) {
    		Recalcula();
    	}
    });
    textField_Iva.setColumns(10);
    textField_Iva.setBounds(122, 122, 69, 20);
    Importes.add(textField_Iva);
    
    // tabla cajas

    
    
	rs=ConectorBD.bdMySQL.Select("configuracion", "*", "Parametro LIKE '%CAJA%'");
	Vector<Vector<String>> elementos2= new Vector<Vector<String>>(); 
	try {
		while (rs.next())
		{
			Vector<String> aux = new Vector<String>();
			aux.add(rs.getObject(2).toString());
			aux.add("0");
			elementos2.add(aux);
			
		}
	} catch (SQLException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
	Vector<String> titulo = new Vector<String>();
	titulo.add("Tipo");
	titulo.add("Cantidad");
	DefaultTableModel modeloCajas = new DefaultTableModel(elementos2,titulo);
       


    //----------------------------
    
    calendar1 = new com.toedter.calendar.JCalendar();
    calendar1.addPropertyChangeListener(new PropertyChangeListener() {

		@Override
		public void propertyChange(PropertyChangeEvent arg0) {
			// TODO Auto-generated method stub
			fecha=calendar1.getCalendar().getTime();
			RecargaListado();
		}
    });
    calendar1.setBounds(10, 11, 375, 213);
    frmCompras.getContentPane().add(calendar1);
    
    btnBorrarDetalle = new JButton("");
    btnBorrarDetalle.addActionListener(new ActionListener() {
    	public void actionPerformed(ActionEvent arg0) {
    		
    		
    		int result= JOptionPane.showConfirmDialog(frmCompras, "", "¡Atención!",JOptionPane.OK_CANCEL_OPTION ,JOptionPane.WARNING_MESSAGE);
    		if (result==JOptionPane.OK_OPTION)
    		{
    			if (modeloTDetalle.getRowCount()!=2)
    			{
	    			String id= (String) modeloTDetalle.getValueAt(tPescado.getSelectedRow(), 5);
	    			DetalleComprasC aux= new DetalleComprasC();
	    			aux.setId(id);
	    			aux.Delete();
	    			
    			}
    			else
    			{
    				String idCompra= (String) modeloTDetalle.getValueAt(tPescado.getSelectedRow(), 6);
    				ComprasC aux = new ComprasC();
    				aux.setId(idCompra);
    				aux.Delete();
    			}
    			RecargaListado();
    		}
    	}
    });
    btnBorrarDetalle.setIcon(new ImageIcon(Compras.class.getResource("/Imagenes/Trash-icon.png")));
    btnBorrarDetalle.setToolTipText("Borrar Detalle");
    btnBorrarDetalle.setBounds(1272, 11, 80, 55);
    frmCompras.getContentPane().add(btnBorrarDetalle);

 
    


    frmCompras.setVisible(true);
    setEstadoInicial();
}
}
