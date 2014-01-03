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
import javax.swing.table.TableColumn;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Vector;

import org.freixas.jcalendar.*;

import Tablas.ComboRenderer;
import Tablas.TablaDetalleCompra;

import BaseDatos.ConectorBD;
import Clases.DetalleComprasC;
import Clases.GeneroC;
import Clases.ProveedorC;

/**
 * This example shows various instances of the JCalendar class.
 * <hr>
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the Artistic License. You should have
 * received a copy of the Artistic License along with this program. If
 * not, a copy is available at
 * <a href="http://opensource.org/licenses/artistic-license.php">
 * opensource.org</a>.
 *
 * @author Antonio Freixas
 */

// Copyright © 2004 Antonio Freixas
// All Rights Reserved.

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
	private JScrollPane scrollPane_1;
	private JTable tPescado;
	private JPanel Observaciones;
	private JTable tOtros;
	private JTextField textField_IPescado;
	private JTextField textField_IPIP;
	private JTextField textField_ICajas;
	private JTextField textField_Subtotal;
	private JTextField textField_IvaI;
	private JTextField textField_Total;
	private JTextField textField_IP;
	private JTextField textField_Iva;
	private TablaDetalleCompra modeloTDetalle;
	
	
	
	public static Date fecha;


/**
 * Create various instances of a JCalendar.
 */
	
	public void setEstadoInicial(){
		tListado.setEnabled(false);
		btnAceptar.setVisible(false);
		btnCancelar.setVisible(false);
		/*cmbGrupo.setVisible(false);
		cmbTipo.setVisible(false);
		Patron.setVisible(false);
		Concepto.setVisible(false);*/
		btnAceptar_edit.setVisible(false);
		btnCancelar_edit.setVisible(false);
		//table.clearSelection();
		/*lblConcepto.setVisible(false);
		lblGrupo.setVisible(false);
		lblTipo.setVisible(false);
		lblPatron.setVisible(false);*/
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
		textField_ICajas.setText("");
		textField_Total.setText("");
		
		int rows = this.modeloTDetalle.getRowCount(); 
		for(int i =0  ; i<rows ; i++)
		{
			modeloTDetalle.removeRow(0); 
		}
		
	} 

	public void setEstadoNuevo()
	{
		btnNuevo.setEnabled(false);
		btnAceptar.setVisible(true);
		btnCancelar.setVisible(true);
		tListado.setEnabled(false);
		cmbProveedor.setEnabled(true);
		tPescado.setEnabled(true);
		
		DetalleComprasC aux = new DetalleComprasC();
		modeloTDetalle.insertRow(aux);
		/*textField_nombre.setEditable(true);
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
		comboBoxProvincia.setSelectedIndex(0);*/
		
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
	    textField_ICajas.setText("0.0");
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
	}
	
	public void setEstadoEditar()
	{
		/*textField_nombre.setEditable(true);
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
				
		list.setEnabled(false);*/
		btnAceptar_edit.setVisible(true);
		btnCancelar_edit.setVisible(true);
		btnNuevo.setEnabled(false);
		btnBorrar.setEnabled(false);
		btnEditar.setEnabled(false);		
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
	
public
Compras()
{
    // Set up the frame
	frmCompras = new JFrame();
	frmCompras.setIconImage(Toolkit.getDefaultToolkit().getImage(Compras.class.getResource("/Imagenes/Animals-Fish-icon.png")));
    frmCompras.setTitle("Compras");
    frmCompras.setExtendedState(JFrame.MAXIMIZED_BOTH);
    frmCompras.getContentPane().setLayout(null);


    //Creamos el calendario
    MyDateListener listener = new MyDateListener();


    JCalendar calendario = new JCalendar(JCalendar.DISPLAY_DATE, false);
    calendario.setLocation(10, 11);
    calendario.setSize(375, 200);
    calendario.addDateListener(listener);

   
    //++++++++++++++++++++++++++++++++++++
        
    frmCompras.getContentPane().add(calendario);
    
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
    			
    			setEstadoInicial();
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
    btnAceptar_edit.addActionListener(new ActionListener() {
    	public void actionPerformed(ActionEvent e) {
    		setEstadoInicial();
    	}
    });
    btnAceptar_edit.setVisible(false);
    btnAceptar_edit.setToolTipText("Aceptar");
    btnAceptar_edit.setBounds(665, 11, 80, 55);
    frmCompras.getContentPane().add(btnAceptar_edit);
    
    btnCancelar_edit = new JButton("");
    btnCancelar_edit.addActionListener(new ActionListener() {
    	public void actionPerformed(ActionEvent e) {
    		setEstadoInicial();
    	}
    });
    btnCancelar_edit.setVisible(false);
    btnCancelar_edit.setToolTipText("Cancelar");
    btnCancelar_edit.setBounds(755, 11, 80, 55);
    frmCompras.getContentPane().add(btnCancelar_edit);
    
    JScrollPane scrollPane = new JScrollPane();
    scrollPane.setBounds(10, 235, 375, 460);
    frmCompras.getContentPane().add(scrollPane);
    
    tListado = new JTable();
    scrollPane.setViewportView(tListado);
    
    Desglose = new JPanel();
    Desglose.setBorder(new LineBorder(new Color(0, 0, 0)));
    Desglose.setBounds(395, 77, 957, 618);
    frmCompras.getContentPane().add(Desglose);
    Desglose.setLayout(null);
    // combobox proveedores

    
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
    
    scrollPane_1 = new JScrollPane();

    scrollPane_1.setBounds(10, 47, 937, 202);
    Desglose.add(scrollPane_1);
    //----------------------------------------- TABLA DETALLE COMPRAS
    
    
	// Cargamos la tabla
	Vector <String> columnNames = new  Vector <String>(); 
	Vector<DetalleComprasC> vectorTabla= new Vector<DetalleComprasC>();
    
    
    modeloTDetalle= new TablaDetalleCompra(vectorTabla,columnNames);
    
    
    
    tPescado = new JTable(modeloTDetalle);
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
		    if (!textField_IP.getText().equals(""))
		    {
		    	DecimalFormat df = new DecimalFormat("0.00##");
		    	Double importe=(Double.parseDouble(textField_IP.getText())/100)*op3;
		    	textField_IPIP.setText(df.format(importe));
		    	
		    	double importeP=Double.parseDouble(textField_IPescado.getText())+Double.parseDouble(textField_IPIP.getText().replace(',', '.'));
		    	double importeC=Double.parseDouble(textField_ICajas.getText().replace(',','.'));
		    	
		    	textField_Subtotal.setText(Double.toString(importeP+importeC));	 
		    	
		    	importe=(Double.parseDouble(textField_Iva.getText())/100)*(importeP+importeC);
		    	textField_IvaI.setText(df.format(importe));	
		    	
		    	textField_Total.setText(Double.toString(Double.parseDouble(textField_IvaI.getText().replace(',', '.'))+Double.parseDouble(textField_Subtotal.getText().replace(',','.'))));

		    	
		    }
		}	
			
		}
      });
    tPescado.setEnabled(false);
    scrollPane_1.setViewportView(tPescado);
    //------------------------------------------
    Observaciones = new JPanel();
    Observaciones.setBorder(new TitledBorder(null, "Observaciones", TitledBorder.LEADING, TitledBorder.TOP, null, null));
    Observaciones.setBounds(10, 405, 547, 202);
    Desglose.add(Observaciones);
    Observaciones.setLayout(null);
    
    JTextPane txtPObservaciones = new JTextPane();
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
    
    JLabel lblImpuestoCajas = new JLabel("Importe Cajas:");
    lblImpuestoCajas.setBounds(10, 75, 102, 14);
    Importes.add(lblImpuestoCajas);
    
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
    
    textField_ICajas = new JTextField();
    textField_ICajas.setColumns(10);
    textField_ICajas.setBounds(203, 72, 167, 20);
    Importes.add(textField_ICajas);
    
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
    textField_IP.setColumns(10);
    textField_IP.setBounds(122, 47, 69, 20);

    Importes.add(textField_IP);
    
    textField_Iva = new JTextField();
    textField_Iva.setColumns(10);
    textField_Iva.setBounds(122, 122, 69, 20);
    Importes.add(textField_Iva);
    
    JScrollPane scrollPane_2 = new JScrollPane();
    scrollPane_2.setBounds(10, 260, 937, 134);
    Desglose.add(scrollPane_2);
    
    tOtros = new JTable();
    scrollPane_2.setViewportView(tOtros);


    frmCompras.setVisible(true);
    setEstadoInicial();
}






//**********************************************************************
// Inner Classes
//**********************************************************************

private class MyDateListener
      implements DateListener
{

public void
dateChanged(DateEvent e)
{

    Calendar c = e.getSelectedDate();
    if (c != null) {
	Compras.fecha=c.getTime();
    }
    else {
	System.out.println("No time selected.");
    }
}

}
}