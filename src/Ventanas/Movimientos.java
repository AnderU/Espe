package Ventanas;

import java.awt.Toolkit;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.ButtonGroup;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import BaseDatos.ConectorBD;
import Clases.FormaPago;
import Clases.MovimientosC;
import Clases.ProveedorC;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.SystemColor;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.border.TitledBorder;
import javax.swing.JTextPane;
import Tablas.TablaMovimientos;

import com.toedter.calendar.JDateChooser;

public class Movimientos {

	private JFrame frmMovimientos;
	private TablaMovimientos modeloFact;
	private JTextField textField_nFactura;
	private ButtonGroup Ver = new ButtonGroup();
	private JRadioButton rdbtnCadaMov;
	private JRadioButton rdbtnTotalesGrupo;

	private JPanel pnl_observaciones;
	private JTextPane textPane_observaciones;
	private JComboBox<FormaPago> comboBox_grupo;
	private JComboBox<ProveedorC> cmbConcepto;

	
	private JDateChooser dateChooser_fechaDesde;
	private JDateChooser dateChooser_fechaHasta;
	private JDateChooser dateChooser_fechaPCDesde;
//	private JTable table_mov;
	private JScrollPane scrollPane;
	private JPanel pnl_fechaPC;
	private JLabel lblFechaPCHasta;
	private JPanel pnl_ver;
	private JRadioButton rdbtnTotalesConcepto;
	private JPanel pnl_importe;
	private JTextField textField_importeDesde;
	private JTextField textField_importeHasta;
	private JLabel lblImporteTotal;
	private JTextField textField_importeTotal;
	private JTable table_mov;
	
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
		
		textField_nFactura.setEditable(false);
		cmbConcepto.setEnabled(false);
		comboBox_grupo.setEnabled(false);
		textPane_observaciones.setEditable(false);
		
		textField_nFactura.setText("");
		cmbConcepto.setSelectedIndex(0);
		comboBox_grupo.setSelectedIndex(0);
		
		textPane_observaciones.setText("");
		
		dateChooser_fechaDesde.setCalendar(null);
		dateChooser_fechaPCDesde.setCalendar(null);
		
		rdbtnCadaMov.setSelected(false);
		rdbtnTotalesGrupo.setSelected(false);
		
				
	}

	/**
	 * Initialize the contents of the frame.
	 */
	
	
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
				columnNames.add("Tipo Movimiento");
				columnNames.add("Observaciones");
				
				Vector<MovimientosC> vectorTabla= new Vector<MovimientosC>();
				modeloFact= new TablaMovimientos(vectorTabla,columnNames);


			    scrollPane = new JScrollPane();
			    scrollPane.setBounds(21, 171, 1052, 523);
			    frmMovimientos.getContentPane().add(scrollPane);
			    
			    table_mov = new JTable(modeloFact);
			    scrollPane.setColumnHeaderView(table_mov);		
		

		
//***************** N FACTURA ********************************	
		JLabel lblNFactura = new JLabel("N\u00BA Factura");
		lblNFactura.setBounds(20, 80, 90, 14);
		frmMovimientos.getContentPane().add(lblNFactura);
		
		textField_nFactura = new JTextField();
		textField_nFactura.setBounds(141, 77, 250, 20);
		frmMovimientos.getContentPane().add(textField_nFactura);
		textField_nFactura.setColumns(10);
		
//***************** CONCEPTO ********************************		
		JLabel lblConcepto = new JLabel("Concepto");
		lblConcepto.setBounds(418, 80, 65, 14);
		frmMovimientos.getContentPane().add(lblConcepto);
		
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
 		
 	    cmbConcepto = new JComboBox<ProveedorC>(elementos1);
 	    cmbConcepto.addItemListener(new ItemListener() {
 	    	public void itemStateChanged(ItemEvent arg0) {
 	    		if (arg0.getStateChange() == ItemEvent.SELECTED) 
 	    		{
 	 	    		
 	    		}

 	    	}
 	    });


 	    cmbConcepto.setEnabled(false);
 	    cmbConcepto.setBounds(487, 77, 549, 20);
 	    frmMovimientos.getContentPane().add(cmbConcepto);
    
		
//***************** FORMA DE PAGO ********************************		
		JLabel lblGrupo = new JLabel("Grupo");
		lblGrupo.setBounds(20, 110, 111, 14);
		frmMovimientos.getContentPane().add(lblGrupo);
		
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

		comboBox_grupo = new JComboBox<FormaPago>(elementosf);
		comboBox_grupo.setEnabled(false);
		comboBox_grupo.setBounds(141, 107, 250, 20);
		frmMovimientos.getContentPane().add(comboBox_grupo);
		

//***************** VER ********************************			
    	pnl_ver = new JPanel();
    	pnl_ver.setBorder(new TitledBorder(null, "Ver", TitledBorder.LEADING, TitledBorder.TOP, null, null));
    	pnl_ver.setBounds(1083, 369, 251, 87);
    	frmMovimientos.getContentPane().add(pnl_ver);
    	
    	pnl_ver.add(rdbtnCadaMov);
    	pnl_ver.add(rdbtnTotalesGrupo);
    	pnl_ver.setLayout(null);
    	
		rdbtnCadaMov = new JRadioButton("Cada Movimiento");
		rdbtnCadaMov.setBounds(41, 7, 137, 23);
		pnl_ver.add(rdbtnCadaMov);
		rdbtnCadaMov.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				if (rdbtnCadaMov.isSelected())
				{
					
				}	
				else
				{
			
					
				}	
				
			}
		});
		Ver.add(rdbtnCadaMov);
		
		rdbtnTotalesGrupo = new JRadioButton("Totales por Grupo");
		rdbtnTotalesGrupo.setBounds(41, 59, 137, 23);
		pnl_ver.add(rdbtnTotalesGrupo);
		Ver.add(rdbtnTotalesGrupo);
		
		rdbtnTotalesConcepto = new JRadioButton("Totales por Concepto");
		rdbtnTotalesConcepto.setBounds(41, 33, 137, 23);
		pnl_ver.add(rdbtnTotalesConcepto);
		Ver.add(rdbtnTotalesConcepto);

//****************** OBSERVACIONES ************************************

		pnl_observaciones = new JPanel();
		pnl_observaciones.setBounds(1083, 478, 251, 180);
		pnl_observaciones.setBorder(new TitledBorder(null, "Observaciones", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		frmMovimientos.getContentPane().add(pnl_observaciones);
		pnl_observaciones.setLayout(null);
		
		textPane_observaciones = new JTextPane();
		textPane_observaciones.setBounds(10, 22, 231, 147);
		pnl_observaciones.add(textPane_observaciones);


//****************** FECHAS ************************************	
		//panel Fecha 
		JPanel pnl_fecha = new JPanel();
		pnl_fecha.setBorder(new TitledBorder(null, "Fecha", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnl_fecha.setBounds(1083, 75, 251, 76);
		frmMovimientos.getContentPane().add(pnl_fecha);
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
		    frmMovimientos.getContentPane().add(pnl_fechaPC);
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
		    	frmMovimientos.getContentPane().add(pnl_importe);
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
		    	frmMovimientos.getContentPane().add(lblIva);
		    	
		    	JComboBox comboBox_iva = new JComboBox();
		    	comboBox_iva.setBounds(487, 107, 250, 20);
		    	frmMovimientos.getContentPane().add(comboBox_iva);
		    	
		    	lblImporteTotal = new JLabel("Importe Total");
		    	lblImporteTotal.setBounds(1083, 678, 80, 14);
		    	frmMovimientos.getContentPane().add(lblImporteTotal);
		    	
		    	textField_importeTotal = new JTextField();
		    	textField_importeTotal.setBounds(1170, 675, 164, 20);
		    	frmMovimientos.getContentPane().add(textField_importeTotal);
		    	textField_importeTotal.setColumns(10);
		    	
		
	setEstadoInicial();	
	}
}