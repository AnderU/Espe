package Ventanas;

import java.awt.Toolkit;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import BaseDatos.ConectorBD;
import Clases.ComboC;
import Clases.MovimientosC;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.border.TitledBorder;
import javax.swing.JTextPane;
import Tablas.TablaMovimientos;

import com.toedter.calendar.JDateChooser;
import javax.swing.border.MatteBorder;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Movimientos {

	private JFrame frmMovimientos;
	private TablaMovimientos modeloMov;
	private JPanel pnl_observaciones;
	private JTextPane textPane_observaciones;
	private JComboBox<ComboC> comboBox_grupo;
	private JComboBox<ComboC> comboBox_concepto;
	private JScrollPane scrollPane;
	private JTextField textField_importeTotal;
	private JTable table_mov;
	
	private JButton btnAceptar;
	private JButton btnAceptar_edit;
	private JButton btnCancelar_edit;
	private JButton btnCancelar;
	private JButton btnBorrar;
	private JButton btnEditar;
	private JButton btnNuevo ;
	private JPanel panel_grupo;
	private JPanel panel_concepto;
	private JPanel panel_importe;
	
	private JDateChooser dateChooser_fecha;
	private JDateChooser dateChooser_fechaPC;
	
	/**
	 * Launch the application.
	 */

	/**
	 * Create the application.
	 */
	public Movimientos() {
		initialize();
	}
	
	public void setEstadoInicial()
	{
		
		comboBox_concepto.setSelectedIndex(0);
		comboBox_grupo.setSelectedIndex(0);
		comboBox_grupo.setEnabled(false);
		comboBox_concepto.setEnabled(false);
		
		textPane_observaciones.setEditable(false);
		textPane_observaciones.setText("");
		
		btnNuevo.setVisible(true);
		btnNuevo.setEnabled(true);
		btnEditar.setVisible(true);
		btnEditar.setEnabled(false);
		btnBorrar.setVisible(true);
		btnBorrar.setEnabled(false);
		btnAceptar.setVisible(false);
		btnCancelar.setVisible(false);
		btnAceptar_edit.setVisible(false);
		btnCancelar_edit.setVisible(false);
				
		textField_importeTotal.setEditable(false);
		textField_importeTotal.setText("");
		
		dateChooser_fecha.setCalendar(null);
		dateChooser_fechaPC.setCalendar(null);
		dateChooser_fecha.setEnabled(false);
		dateChooser_fechaPC.setEnabled(false);
		
		table_mov.clearSelection();
		table_mov.setEnabled(true);
	}

	
	public void setEstadoNuevo()
	{
		btnNuevo.setEnabled(false);
		btnEditar.setEnabled(false);
		btnBorrar.setEnabled(false);
		btnAceptar.setVisible(true);
		btnAceptar.setEnabled(true);
		btnCancelar.setVisible(true);
		btnCancelar.setEnabled(true);
		btnAceptar_edit.setVisible(false);
		btnCancelar_edit.setVisible(false);
		
		textPane_observaciones.setEditable(true);
		textPane_observaciones.setText("");
			
		comboBox_concepto.setEnabled(true);
		comboBox_grupo.setEnabled(false);
		
		textField_importeTotal.setEditable(true);
		textField_importeTotal.setText("");
		
		dateChooser_fecha.setCalendar(Calendar.getInstance());
		dateChooser_fechaPC.setCalendar(null);
		dateChooser_fecha.setEnabled(true);
		dateChooser_fechaPC.setEnabled(true);
		
		table_mov.clearSelection();
		table_mov.setEnabled(false);
		
	}
	
	public void setEstadoSeleccion()
	{
		
		btnNuevo.setEnabled(false);
		btnEditar.setEnabled(true);
		btnBorrar.setEnabled(true);
		btnAceptar.setVisible(false);
		btnCancelar.setVisible(false);
		btnAceptar_edit.setVisible(false);
		btnCancelar_edit.setVisible(false);
		
		textPane_observaciones.setEditable(true);
		textPane_observaciones.setText("");
			
		comboBox_concepto.setEnabled(false);
		comboBox_grupo.setEnabled(false);
		comboBox_concepto.setSelectedIndex(0);
		comboBox_grupo.setSelectedIndex(0);
		
		textField_importeTotal.setEditable(false);
		textField_importeTotal.setText("");
		
		dateChooser_fecha.setCalendar(null);
		dateChooser_fechaPC.setCalendar(null);
		dateChooser_fecha.setEnabled(false);
		dateChooser_fechaPC.setEnabled(false);
		
	}
	
	public void setEstadoEditar()
	{		
		table_mov.setEnabled(false);
		
		btnNuevo.setEnabled(false);
		btnEditar.setEnabled(false);
		btnBorrar.setEnabled(false);
		btnAceptar.setVisible(false);
		btnCancelar.setVisible(false);
		btnAceptar_edit.setVisible(true);
		btnCancelar_edit.setVisible(true);
		btnAceptar_edit.setEnabled(true);
		btnCancelar_edit.setEnabled(true);
		
		textPane_observaciones.setEditable(true);
		textPane_observaciones.setText((String)modeloMov.getValueAt(table_mov.getSelectedRow(), 7));
			
		comboBox_concepto.setEnabled(true);
		comboBox_grupo.setEnabled(false);
		comboBox_concepto.setSelectedIndex(0);
		comboBox_grupo.setSelectedIndex(0);
		
		textField_importeTotal.setEditable(false);
		textField_importeTotal.setText(Double.toString((Double)modeloMov.getValueAt(table_mov.getSelectedRow(), 3)));

		dateChooser_fecha.setEnabled(true);
		dateChooser_fechaPC.setEnabled(true);
		SimpleDateFormat formatoFecha = new SimpleDateFormat("dd-MM-aaaa");
		Calendar miCalendario= new GregorianCalendar();
		try {
			miCalendario.setTime(formatoFecha.parse((String)modeloMov.getValueAt(table_mov.getSelectedRow(), 0)));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		dateChooser_fecha.setDate(miCalendario.getTime());
		if ((String)modeloMov.getValueAt(table_mov.getSelectedRow(), 1)=="NULL")
			dateChooser_fechaPC.setCalendar(null);
		else
		{
			try {
				miCalendario.setTime(formatoFecha.parse((String)modeloMov.getValueAt(table_mov.getSelectedRow(), 1)));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			dateChooser_fechaPC.setDate(miCalendario.getTime());
		}
	}
	
	private void initialize() {
		
//***************** VENTANA ********************************		
				frmMovimientos = new JFrame();
				frmMovimientos.setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\alumno\\workspace\\Repositorio\\Espe\\src\\Imagenes\\Animals-Fish-icon.png"));
				frmMovimientos.getContentPane().setBackground(SystemColor.textHighlight);
				frmMovimientos.setBounds(0, 0, Toolkit.getDefaultToolkit().getScreenSize().width, Toolkit.getDefaultToolkit().getScreenSize().height);
				frmMovimientos.setExtendedState(JFrame.MAXIMIZED_BOTH);
				frmMovimientos.getContentPane().setLayout(null);
				frmMovimientos.setVisible(true);
				frmMovimientos.setTitle("Movimientos");
				
//***************** TABLA ********************************
				Vector<String> columnNames= new Vector<String>();
				columnNames.add("Fecha");
				columnNames.add("Fecha Pago/Cobro");
				columnNames.add("Concepto");
				columnNames.add("Importe");
				columnNames.add("Grupo");
				columnNames.add("nº Factura");
				
				Vector<MovimientosC> vectorTabla= new Vector<MovimientosC>();

		        ResultSet rs=ConectorBD.bdMySQL.Select("movimientos", "*", "true");
		       
				
		        try {
					while (rs.next())
					{
						MovimientosC fila= new MovimientosC();
						fila.setIdMov(rs.getObject(1).toString());
						fila.setFecha(rs.getObject(2).toString());
						if (rs.getObject(3)!=null)
						fila.setFechaPC(rs.getObject(3).toString());
						fila.setIdConcepto(rs.getObject(4).toString());
						fila.setImporte(Double.parseDouble(rs.getObject(5).toString()));
						fila.setIdGrupo(rs.getObject(6).toString());
						fila.setnFactura(rs.getObject(7).toString());
						fila.setObservaciones(rs.getObject(8).toString());
						ResultSet rs1=ConectorBD.bdMySQL.SelectAux("conceptos", "Concepto", "Id="+rs.getObject(4).toString());
						rs1.next();
						fila.setTipo(rs1.getObject(1).toString());
						rs1=ConectorBD.bdMySQL.SelectAux("gruposconcepto", "Grupos", "Id="+rs.getObject(6).toString());
						rs1.next();
						fila.setGrupo(rs1.getObject(1).toString());
						vectorTabla.add(fila);
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		        
		        rs=ConectorBD.bdMySQL.Select("facturasproveedores", "*", "true");
		        try {
					while (rs.next())
					{
						MovimientosC fila= new MovimientosC();
						fila.setIdMov(rs.getObject(1).toString());
						fila.setFecha(rs.getObject(5).toString());
						if (rs.getObject(6)!=null)
						fila.setFechaPC(rs.getObject(6).toString());
						fila.setIdConcepto(rs.getObject(3).toString());
						fila.setImpuestos(rs.getObject(8).toString());
						fila.setIVA(rs.getObject(9).toString());
						ResultSet rs1=ConectorBD.bdMySQL.SelectAux("detalleCompras", "SUM(Precio*Cantidad)", "IdFactura="+rs.getObject(1).toString());
						rs1.next();
						fila.setImporte(Double.parseDouble(rs1.getObject(1).toString())*(Double.parseDouble(fila.getImpuestos())/100+1)*(Double.parseDouble(fila.getIVA())/100+1));
						fila.setIdGrupo("-1");
						fila.setnFactura(rs.getObject(2).toString());
						fila.setObservaciones(rs.getObject(7).toString());
						rs1=ConectorBD.bdMySQL.SelectAux("proveedores", "Proveedor", "Id="+rs.getObject(3).toString());
						rs1.next();
						fila.setTipo(rs1.getObject(1).toString());
						fila.setGrupo("Factura a Proveedor");
						vectorTabla.add(fila);
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		        
		        rs=ConectorBD.bdMySQL.Select("facturasclientes", "*", "true");
		        try {
					while (rs.next())
					{
						MovimientosC fila= new MovimientosC();
						fila.setIdMov(rs.getObject(1).toString());
						fila.setFecha(rs.getObject(5).toString());
						if (rs.getObject(6)!=null)
						fila.setFechaPC(rs.getObject(6).toString());
						fila.setIdConcepto(rs.getObject(4).toString());
						fila.setIVA(rs.getObject(7).toString());
						ResultSet rs1=ConectorBD.bdMySQL.SelectAux("detallefacturasclientes", "SUM(Precio*Cantidad)", "IdFactura="+rs.getObject(1).toString());
						rs1.next();
						fila.setImporte(Double.parseDouble(rs1.getObject(1).toString())*(Double.parseDouble(fila.getIVA())/100+1));
						fila.setIdGrupo("-2");
						fila.setnFactura(rs.getObject(2).toString());
						fila.setObservaciones(rs.getObject(8).toString());
						rs1=ConectorBD.bdMySQL.SelectAux("clientes", "Cliente", "Id="+rs.getObject(4).toString());
						rs1.next();
						fila.setTipo(rs1.getObject(1).toString());
						fila.setGrupo("Factura a Cliente");
						vectorTabla.add(fila);
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		        
				modeloMov= new TablaMovimientos(vectorTabla,columnNames);
				
			    scrollPane = new JScrollPane();
			    scrollPane.setBounds(21, 171, 1052, 523);
			    frmMovimientos.getContentPane().add(scrollPane);
			    			    
			    table_mov = new JTable(modeloMov);
			    table_mov.addMouseListener(new MouseAdapter() {
			    	@Override
			    	public void mouseClicked(MouseEvent arg0) {
			    		setEstadoSeleccion();
			    	}
			    });
			    scrollPane.setViewportView(table_mov);
			    

//****************** OBSERVACIONES ************************************

		pnl_observaciones = new JPanel();
		pnl_observaciones.setBounds(1083, 466, 251, 154);
		pnl_observaciones.setBorder(new TitledBorder(null, "Observaciones", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		frmMovimientos.getContentPane().add(pnl_observaciones);
		pnl_observaciones.setLayout(null);
		
		textPane_observaciones = new JTextPane();
		textPane_observaciones.setBounds(10, 22, 231, 121);
		pnl_observaciones.add(textPane_observaciones);
		    	
		
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
			frmMovimientos.getContentPane().add(btnNuevo);
			btnNuevo.setVisible(true);
			
//****************** EDITAR ************************************	
			btnEditar = new JButton("");
			btnEditar.setToolTipText("Editar");
			btnEditar.setIcon(new ImageIcon(Proveedores.class.getResource("/Imagenes/Pencil-icon.png")));
			btnEditar.setBounds(110, 11, 80, 55);
			frmMovimientos.getContentPane().add(btnEditar);
			btnEditar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) 
				{
					/*if ((Integer.parseInt((String) modeloMov.getValueAt(table_mov.getSelectedRow(), 6)))>0)
					{
						setEstadoEditar();
					}
					else
						{
						JOptionPane.showConfirmDialog(frmMovimientos, "Debido al tipo de movimiento, no es posible su edición.");
						setEstadoInicial();
						}*/
					setEstadoEditar();
				}
			});
			
//****************** BORRAR ************************************	
			btnBorrar = new JButton("");
			btnBorrar.setIcon(new ImageIcon(Proveedores.class.getResource("/Imagenes/Trash-icon.png")));
			btnBorrar.setToolTipText("Borrar");
			btnBorrar.setBounds(210, 11, 80, 55);
			frmMovimientos.getContentPane().add(btnBorrar);
			btnBorrar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) 
				{
					int result=JOptionPane.showConfirmDialog(frmMovimientos, "El movimiento seleccionado se borrará de la base de datos. ¿Estás segur@?", "¡Atención!",JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE);
					if (result==JOptionPane.OK_OPTION)
					{
						MovimientosC aux= new MovimientosC();
						aux.setIdMov((String)modeloMov.getValueAt(table_mov.getSelectedRow(),6));
						aux.Delete();
					}
					setEstadoInicial();
				}
			});
				
			
//****************** ACEPTAR ************************************			
			btnAceptar = new JButton("");
			btnAceptar.setBounds(310, 11, 80, 55);
			frmMovimientos.getContentPane().add(btnAceptar);
			btnAceptar.setIcon(new ImageIcon(Proveedores.class.getResource("/Imagenes/Accept-icon.png")));
			btnAceptar.setToolTipText("Aceptar");
			btnAceptar.setVisible(false);
			btnAceptar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) 
				{					
					/*if (!textField_importeTotal.getText().equals("") && comboBox_concepto.getSelectedIndex()!=0 && dateChooser_fecha.getCalendar()!=null)
					{				
						
						boolean x=false;
						for (int i=0; i<modeloMov.getRowCount(); i++)
						{
							if ((Boolean) modeloMov.getValueAt(i,6))
							{
								x=true;
								break;
							}
						}
						if (x)
						{	*/
							MovimientosC aux= new MovimientosC();
				
							SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
							aux.setFecha(formatoFecha.format(dateChooser_fecha.getCalendar().getTime()));
							if(dateChooser_fechaPC.getCalendar()!=null) 
								aux.setFechaPC(formatoFecha.format(dateChooser_fechaPC.getCalendar().getTime()));
													
							aux.setIdConcepto(((ComboC)comboBox_concepto.getSelectedItem()).getId());
							aux.setIdGrupo(((ComboC)comboBox_concepto.getSelectedItem()).getId());
							//aux.setIdMov(idMov)
							aux.setImporte(Double.parseDouble(textField_importeTotal.getText()));
							aux.setnFactura("sin factura");
							aux.setObservaciones(textPane_observaciones.getText());
							
							aux.Insert();
							modeloMov.insertRow(aux);
							setEstadoInicial();
							
						/*}	else
							JOptionPane.showMessageDialog(frmMovimientos, "Datos obligatorios no rellenados");
					}setEstadoInicial();*/
					
				} 
			});

			
//****************** ACEPTAR EDIT************************************	
			btnAceptar_edit = new JButton("");
			btnAceptar_edit.setBounds(310, 11, 80, 55);
			frmMovimientos.getContentPane().add(btnAceptar_edit);
			btnAceptar_edit.setIcon(new ImageIcon(Proveedores.class.getResource("/Imagenes/Accept-icon.png")));
			btnAceptar_edit.setToolTipText("Aceptar");
			btnAceptar_edit.setVisible(false);
			btnAceptar_edit.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					
					if (!textField_importeTotal.getText().equals("") && comboBox_concepto.getSelectedIndex()!=0 && dateChooser_fecha.getCalendar()!=null)
					{				
						MovimientosC aux= new MovimientosC();
						SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
						aux.setFecha(formatoFecha.format(dateChooser_fecha.getCalendar().getTime()));
						if(dateChooser_fechaPC.getCalendar()!=null) 
							aux.setFechaPC(formatoFecha.format(dateChooser_fechaPC.getCalendar().getTime()));
						
					
						aux.setIdConcepto(((ComboC)comboBox_concepto.getSelectedItem()).getId());
						aux.setIdGrupo(((ComboC)comboBox_concepto.getSelectedItem()).getId());
						aux.setIdMov((String)modeloMov.getValueAt(table_mov.getSelectedRow(),6));
						aux.setImporte(Double.parseDouble(textField_importeTotal.getText()));
						aux.setnFactura("sin factura");
						aux.setObservaciones(textPane_observaciones.getText());

						aux.Update();
						modeloMov.removeRow(table_mov.getSelectedRow());
						modeloMov.insertRow(aux);
					}	
					setEstadoInicial();
				}
			});

			
//****************** CANCELAR ************************************	
			btnCancelar = new JButton("");
			btnCancelar.setBounds(410, 11, 80, 55);
			frmMovimientos.getContentPane().add(btnCancelar);
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
		btnCancelar_edit.setBounds(410, 11, 80, 55);
		btnCancelar_edit.setToolTipText("Cancelar");
		frmMovimientos.getContentPane().add(btnCancelar_edit);
		btnCancelar_edit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setEstadoInicial();			
			}
		});
		btnCancelar_edit.setIcon(new ImageIcon(Proveedores.class.getResource("/Imagenes/Delete-icon.png")));
		btnCancelar_edit.setVisible(false);
		
		
//**************************************GRUPO***************************************
			
    	panel_grupo = new JPanel();
    	panel_grupo.setBorder(new TitledBorder(null, "Grupo", TitledBorder.LEADING, TitledBorder.TOP, null, null));
    	panel_grupo.setBounds(1083, 396, 251, 49);
    	frmMovimientos.getContentPane().add(panel_grupo);
		panel_grupo.setLayout(null);

		ComboC aux1= new ComboC(); 
 		rs=ConectorBD.bdMySQL.Select("gruposconcepto", "*", "true");
 		Vector<ComboC> elementos1= new Vector<ComboC>(); 
 		elementos1.addElement(aux1);
 		try {
 			while (rs.next())
 			{
 				ComboC a=new ComboC();
 				a.setId(rs.getObject(1).toString());
				a.setNombre(rs.getObject(2).toString());
 				elementos1.addElement(a);
 			}
 		} catch (SQLException e1) {
 			// TODO Auto-generated catch block
 			e1.printStackTrace();
 		}

 		comboBox_grupo = new JComboBox<ComboC>(elementos1);
		comboBox_grupo.setBounds(10, 18, 231, 20);
		panel_grupo.add(comboBox_grupo);
		comboBox_grupo.setEnabled(false);

		
//**************************************CONCEPTO***************************************

		panel_concepto = new JPanel();
		panel_concepto.setBorder(new TitledBorder(null, "Concepto", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_concepto.setBounds(1083, 325, 251, 49);
		frmMovimientos.getContentPane().add(panel_concepto);
 	    panel_concepto.setLayout(null);
	    
 	    aux1= new ComboC(); 
		rs=ConectorBD.bdMySQL.Select("conceptos", "*", "true");
		elementos1= new Vector<ComboC>(); 
		elementos1.addElement(aux1);
		try {
			while (rs.next())
			{
				ComboC a=new ComboC();
				
				a.setId(rs.getObject(1).toString());
				a.setNombre(rs.getObject(2).toString());
				elementos1.addElement(a);
				
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
 	    
 	    comboBox_concepto = new JComboBox<ComboC>(elementos1);
 	    comboBox_concepto.setBounds(10, 18, 230, 20);
 	    panel_concepto.add(comboBox_concepto);
 	    comboBox_concepto.setEnabled(false); 	     	    
 	     	    
//*************************************IMPORTE*************************************************
		panel_importe = new JPanel();
	    panel_importe.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
	    panel_importe.setBounds(1083, 638, 251, 56);
	    frmMovimientos.getContentPane().add(panel_importe);
	    panel_importe.setLayout(null);
	    
	    textField_importeTotal = new JTextField();
	    textField_importeTotal.setBounds(69, 16, 172, 24);
	    panel_importe.add(textField_importeTotal);
	    textField_importeTotal.setColumns(10);
	    
	    JLabel lblImporte = new JLabel("Importe");
	    lblImporte.setBounds(10, 21, 64, 14);
	    panel_importe.add(lblImporte);
	    
	    
//**************************************FECHA****************************************************	    
	    JPanel panel_fecha = new JPanel();
	    panel_fecha.setBorder(new TitledBorder(null, "Fecha", TitledBorder.LEADING, TitledBorder.TOP, null, null));
	    panel_fecha.setBounds(1083, 172, 251, 66);
	    frmMovimientos.getContentPane().add(panel_fecha);
	    panel_fecha.setLayout(null);
	    
	    dateChooser_fecha = new JDateChooser();
	    dateChooser_fecha.setBounds(10, 24, 231, 20);
	    dateChooser_fecha.setDateFormatString("dd-MM-yyyy");
	    dateChooser_fecha.setEnabled(false);
	    panel_fecha.add(dateChooser_fecha);
	    
	    JPanel panel_fechaPC = new JPanel();
	    panel_fechaPC.setBorder(new TitledBorder(null, "Fecha Pago/Cobro", TitledBorder.LEADING, TitledBorder.TOP, null, null));
	    panel_fechaPC.setBounds(1083, 238, 251, 66);
	    frmMovimientos.getContentPane().add(panel_fechaPC);
	    panel_fechaPC.setLayout(null);
	    
	    dateChooser_fechaPC = new JDateChooser();
	    dateChooser_fechaPC.setBounds(10, 24, 231, 20);
	    dateChooser_fechaPC.setDateFormatString("dd-MM-yyyy");
	    dateChooser_fechaPC.setEnabled(false);
	    panel_fechaPC.add(dateChooser_fechaPC);
		    	
	setEstadoInicial();	
	}
}