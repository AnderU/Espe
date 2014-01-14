package Ventanas;

import java.awt.Toolkit;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import BaseDatos.ConectorBD;
import Clases.ConceptosC;
import Clases.FormaPago;
import Clases.MovimientosC;
import Clases.ProveedorC;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.border.TitledBorder;
import javax.swing.JTextPane;
import Tablas.TablaMovimientos;

import com.toedter.calendar.JDateChooser;
import javax.swing.UIManager;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.MatteBorder;
import java.awt.Color;

public class Movimientos {

	private JFrame frmMovimientos;
	private TablaMovimientos modeloFact;
	private JTextField textField_nFactura;
	private ButtonGroup Ver = new ButtonGroup();
	private JPanel pnl_ver;
	private JRadioButton rdbtnTotalesConcepto;
	private JRadioButton rdbtnCadaMov;
	private JRadioButton rdbtnTotalesGrupo;

	private JPanel pnl_observaciones;
	private JTextPane textPane_observaciones;
	private JComboBox<FormaPago> comboBox_grupo;
	private JComboBox<ConceptosC> cmbConcepto;
	
	private JDateChooser dateChooser_fechaDesde;
	private JDateChooser dateChooser_fechaHasta;
	private JDateChooser dateChooser_fechaPCDesde;
	private JScrollPane scrollPane;
	private JPanel pnl_fechaPC;
	private JLabel lblFechaPCHasta;

	private JPanel pnl_importe;
	private JTextField textField_importeDesde;
	private JTextField textField_importeHasta;
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
	private JPanel panel_2;
	
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
		cmbConcepto.setEnabled(false);
		comboBox_grupo.setEnabled(false);
		textPane_observaciones.setEditable(false);
		
		textField_nFactura.setText("");
		//cmbConcepto.setSelectedIndex(0);
		comboBox_grupo.setSelectedIndex(0);
		
		textPane_observaciones.setText("");
		
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
	}

	/**
	 * Initialize the contents of the frame.
	 */
	
	public void setEstadoNuevo()
	{
		btnNuevo.setEnabled(false);
		btnAceptar.setVisible(true);
		btnCancelar.setVisible(true);
		
		textPane_observaciones.setEditable(true);
		textPane_observaciones.setText("");
		btnEditar.setEnabled(false);
		btnBorrar.setEnabled(false);
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
		textPane_observaciones.setEditable(true);
				
		btnAceptar_edit.setVisible(true);
		btnCancelar_edit.setVisible(true);
		btnNuevo.setEnabled(false);
		btnBorrar.setEnabled(false);
		btnEditar.setEnabled(false);
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
				/*Vector<String> columnNames= new Vector<String>();
				columnNames.add("Fecha");
				columnNames.add("Fecha Pago/Cobro");
				columnNames.add("Concepto");
				columnNames.add("Importe");
				columnNames.add("Tipo Movimiento");
				columnNames.add("Observaciones");
				
				Vector<MovimientosC> vectorTabla= new Vector<MovimientosC>();
				modeloFact= new TablaMovimientos(vectorTabla,columnNames);*/


			    scrollPane = new JScrollPane();
			    scrollPane.setBounds(21, 171, 1052, 523);
			    frmMovimientos.getContentPane().add(scrollPane);
			    
			    table_mov = new JTable(modeloFact);
			    scrollPane.setColumnHeaderView(table_mov);
		
//***************** N FACTURA ********************************	
		JLabel lblNFactura = new JLabel("N\u00BA Factura");
		lblNFactura.setBounds(20, 80, 90, 14);
		
		textField_nFactura = new JTextField();
		textField_nFactura.setBounds(141, 77, 250, 20);
		textField_nFactura.setColumns(10);
		
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
		

//***************** VER ********************************			
		
		pnl_ver = new JPanel();
    	pnl_ver.setBorder(new TitledBorder(null, "Ver", TitledBorder.LEADING, TitledBorder.TOP, null, null));
    	pnl_ver.setBounds(1083, 369, 251, 87);
    	pnl_ver.setVisible(true);
    	pnl_ver.setLayout(null);
    	
		rdbtnCadaMov = new JRadioButton("Cada Movimiento");
		rdbtnCadaMov.setBounds(41, 7, 137, 23);
		pnl_ver.add(rdbtnCadaMov);
		rdbtnCadaMov.setVisible(true);
		/*rdbtnCadaMov.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				if (rdbtnCadaMov.isSelected())
				{
					
				}	
				else
				{
			
					
				}	
				
			}
		});*/
		Ver.add(rdbtnCadaMov);
		
		rdbtnTotalesGrupo = new JRadioButton("Totales por Grupo");
		rdbtnTotalesGrupo.setBounds(41, 59, 137, 23);
		pnl_ver.add(rdbtnTotalesGrupo);
		Ver.add(rdbtnTotalesGrupo);
		rdbtnTotalesGrupo.setVisible(true);
		
		rdbtnTotalesConcepto = new JRadioButton("Totales por Concepto");
		rdbtnTotalesConcepto.setBounds(41, 33, 137, 23);
		rdbtnTotalesConcepto.setVisible(true);
		pnl_ver.add(rdbtnTotalesConcepto);
		Ver.add(rdbtnTotalesConcepto);

//****************** OBSERVACIONES ************************************

		pnl_observaciones = new JPanel();
		pnl_observaciones.setBounds(1083, 391, 251, 225);
		pnl_observaciones.setBorder(new TitledBorder(null, "Observaciones", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		frmMovimientos.getContentPane().add(pnl_observaciones);
		pnl_observaciones.setLayout(null);
		
		textPane_observaciones = new JTextPane();
		textPane_observaciones.setBounds(10, 22, 231, 192);
		pnl_observaciones.add(textPane_observaciones);


//****************** FECHAS ************************************	
		//panel Fecha 
		JPanel pnl_fecha = new JPanel();
		pnl_fecha.setBorder(new TitledBorder(null, "Fecha", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnl_fecha.setBounds(1083, 75, 251, 76);
		pnl_fecha.setLayout(null);
		
			//fecha
			dateChooser_fechaDesde = new JDateChooser();
			dateChooser_fechaDesde.setDateFormatString("dd-MM-yyyy");
			dateChooser_fechaDesde.setBounds(108, 18, 133, 20);
			pnl_fecha.add(dateChooser_fechaDesde);
			
			JLabel lblFechaDesde = new JLabel("Desde");
			lblFechaDesde.setBounds(30, 21, 46, 14);
			pnl_fecha.add(lblFechaDesde);
			
			JLabel lblFechaHasta = new JLabel("Hasta");
		    lblFechaHasta.setBounds(30, 48, 50, 14);
		    pnl_fecha.add(lblFechaHasta);
		    
		    dateChooser_fechaHasta = new JDateChooser();
			dateChooser_fechaHasta.setDateFormatString("dd-MM-yyyy");
		    dateChooser_fechaHasta.setBounds(108, 45, 133, 20);
		    pnl_fecha.add(dateChooser_fechaHasta);
		
		    
		 //panel FechaPC
		    
		    pnl_fechaPC = new JPanel();
		    pnl_fechaPC.setBorder(new TitledBorder(null, "Fecha Pago/Cobro", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		    pnl_fechaPC.setBounds(1083, 172, 251, 76);
		    pnl_fechaPC.setLayout(null);
		    
		    	JLabel lblFechaPCDesde = new JLabel("Desde");
		    	lblFechaPCDesde.setBounds(30, 21, 47, 14);
		    	pnl_fechaPC.add(lblFechaPCDesde);
		    
		    	dateChooser_fechaPCDesde = new JDateChooser();
		    	dateChooser_fechaPCDesde.setBounds(108, 18, 133, 20);
		    	pnl_fechaPC.add(dateChooser_fechaPCDesde);
		    	dateChooser_fechaPCDesde.setDateFormatString("dd-MM-yyyy");
		    	
		    	lblFechaPCHasta = new JLabel("Hasta");
		    	lblFechaPCHasta.setBounds(30, 48, 50, 14);
		    	pnl_fechaPC.add(lblFechaPCHasta);
		    	 
		    	JDateChooser dateChooser_fechaPCHasta = new JDateChooser();
		    	dateChooser_fechaPCHasta.setBounds(108, 45, 133, 20);
		    	dateChooser_fechaPCHasta.setDateFormatString("dd-MM-yyyy");
		    	pnl_fechaPC.add(dateChooser_fechaPCHasta);
		    	
		    	pnl_importe = new JPanel();
		    	pnl_importe.setBorder(new TitledBorder(null, "Importe", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		    	pnl_importe.setBounds(1083, 270, 251, 76);
		    	pnl_importe.setLayout(null);
		    	
		    	textField_importeDesde = new JTextField();
		    	textField_importeDesde.setBounds(108, 18, 133, 20);
		    	pnl_importe.add(textField_importeDesde);
		    	textField_importeDesde.setColumns(10);
		    	
		    	textField_importeHasta = new JTextField();
		    	textField_importeHasta.setBounds(108, 45, 133, 20);
		    	pnl_importe.add(textField_importeHasta);
		    	textField_importeHasta.setColumns(10);
		    	
		    	JLabel lblImporteDesde = new JLabel("Desde");
		    	lblImporteDesde.setBounds(30, 21, 46, 14);
		    	pnl_importe.add(lblImporteDesde);
		    	
		    	JLabel lblImporteHasta = new JLabel("Hasta");
		    	lblImporteHasta.setBounds(30, 48, 46, 14);
		    	pnl_importe.add(lblImporteHasta);
		    	
		    	JLabel lblIva = new JLabel("IVA");
		    	lblIva.setBounds(418, 110, 46, 14);
		    	
		    	JComboBox comboBox_iva = new JComboBox();
		    	comboBox_iva.setBounds(487, 107, 250, 20);
		    	
		
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
			btnEditar.setVisible(false);
			btnEditar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) 
				{
					
					if (true)
					{
						
						setEstadoEditar();
					}
					
				}
			});
			
//****************** BORRAR ************************************	
			btnBorrar = new JButton("");
			btnBorrar.setIcon(new ImageIcon(Proveedores.class.getResource("/Imagenes/Trash-icon.png")));
			btnBorrar.setToolTipText("Borrar");
			btnBorrar.setBounds(210, 11, 80, 55);
			frmMovimientos.getContentPane().add(btnBorrar);
			btnBorrar.setVisible(false);
			btnBorrar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) 
				{
					int result=JOptionPane.showConfirmDialog(frmMovimientos, "El movimiento seleccionado se borrará de la base de datos. ¿Estás segur@?", "¡Atención!",JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE);
					/*if (result==JOptionPane.OK_OPTION)
					{
						MovimientosC aux= new MovimientosC();
						aux.setIdFact(modeloFact.getValueAt(table_mov.getSelectedRow(),6);
						aux.Delete();
					}*/
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
					
					/*if (cmbConcepto.getText().equals(""))
					{
						MovimientosC aux= new MovimientosC();
						aux.setFecha(null);
						aux.setFechaPC(null);
						aux.setImporte(900);
						aux.setIdProvCli(null);
						aux.setObservaciones(textPane_observaciones.getText());
						aux.Insert();
						table_mov.addElement(aux);
					}*/		
					setEstadoInicial();
					
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
					
					/*if (.getText().equals(""))
					{				
						MovimientosC aux= new MovimientosC();
						
						aux.setObservaciones(textPane_observaciones.getText());
						
						aux.Update();
						table_mov.remove(.getSelectedIndex());
						table_mov.addElement(aux);
					}*/				
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
			frmMovimientos.getContentPane().add(btnCancelar_edit);
			btnCancelar_edit.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					setEstadoInicial();			
				}
			});
			btnCancelar_edit.setIcon(new ImageIcon(Proveedores.class.getResource("/Imagenes/Delete-icon.png")));
			btnCancelar_edit.setToolTipText("Cancelar");
			btnCancelar_edit.setVisible(false);
			
//********************************panel oculto****************************************************************************************		    	
		    	
		    	JPanel pnl_oculto = new JPanel();
		    	pnl_oculto.setBounds(-27, 138, 49, 49);
		    	frmMovimientos.getContentPane().add(pnl_oculto);
		    	
		    	pnl_oculto.add(pnl_fecha);
		    	pnl_oculto.add(pnl_fechaPC);
		    	pnl_oculto.add(pnl_importe);
		    	pnl_oculto.add(comboBox_iva);
		    	pnl_oculto.add(lblIva);

		    	pnl_oculto.add(pnl_ver);
		    	pnl_oculto.add(textField_nFactura);
		    	pnl_oculto.add(lblNFactura);
		    	
		    	panel_grupo = new JPanel();
		    	panel_grupo.setBorder(new TitledBorder(null, "Grupo", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		    	panel_grupo.setBounds(1083, 315, 251, 49);
		    	frmMovimientos.getContentPane().add(panel_grupo);
		    			panel_grupo.setLayout(null);
		    	
		    			comboBox_grupo = new JComboBox<FormaPago>(elementosf);
		    			comboBox_grupo.setBounds(10, 18, 231, 20);
		    			panel_grupo.add(comboBox_grupo);
		    			comboBox_grupo.setEnabled(false);
		    			
		    			panel_concepto = new JPanel();
		    			panel_concepto.setBorder(new TitledBorder(null, "Concepto", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		    			panel_concepto.setBounds(1083, 243, 251, 49);
		    			frmMovimientos.getContentPane().add(panel_concepto);
 	    panel_concepto.setLayout(null);
		    			
 	    cmbConcepto = new JComboBox<ConceptosC>(/*elementos1*/);
 	    cmbConcepto.setBounds(10, 18, 230, 20);
 	    panel_concepto.add(cmbConcepto);
 	    cmbConcepto.addItemListener(new ItemListener() {
 	    	public void itemStateChanged(ItemEvent arg0) {
 	    		if (arg0.getStateChange() == ItemEvent.SELECTED) 
 	    		{
 	 	    		
 	    		}

 	    	}
 	    });
 	    
 	    
 	     	    cmbConcepto.setEnabled(false);
 	     	    
 	     	    panel_2 = new JPanel();
 	     	    panel_2.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
 	     	    panel_2.setBounds(1083, 638, 251, 56);
 	     	    frmMovimientos.getContentPane().add(panel_2);
 	     	    panel_2.setLayout(null);
 	     	    
 	     	    textField_importeTotal = new JTextField();
 	     	    textField_importeTotal.setBounds(69, 16, 172, 24);
 	     	    panel_2.add(textField_importeTotal);
 	     	    textField_importeTotal.setColumns(10);
 	     	    
 	     	    JLabel lblImporte = new JLabel("Importe");
 	     	    lblImporte.setBounds(10, 21, 64, 14);
 	     	    panel_2.add(lblImporte);
 	     	    
 	     	    JPanel panel_fecha = new JPanel();
 	     	    panel_fecha.setBorder(new TitledBorder(null, "Fecha", TitledBorder.LEADING, TitledBorder.TOP, null, null));
 	     	    panel_fecha.setBounds(1083, 171, 251, 49);
 	     	    frmMovimientos.getContentPane().add(panel_fecha);
 	     	    panel_fecha.setLayout(null);
 	     	    
 	     	    JDateChooser dateChooser_fecha = new JDateChooser();
 	     	    dateChooser_fecha.setBounds(10, 18, 231, 20);
 	     	    panel_fecha.add(dateChooser_fecha);
		    	
		    	
		    	pnl_oculto.setVisible(false);
		    	
	setEstadoInicial();	
	}
}