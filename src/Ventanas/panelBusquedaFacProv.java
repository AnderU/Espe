package Ventanas;


import java.awt.Desktop;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Vector;

import javax.swing.*;
import javax.swing.border.TitledBorder;

import BaseDatos.ConectorBD;
import Clases.FacturasProveedoresC;
import Clases.ProveedorC;
import Tablas.TablaFacturasProveedoresResumen;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.toedter.calendar.JDateChooser;

public class panelBusquedaFacProv extends JPanel {
	
	private JDateChooser dateChooser_Desde;
	private JDateChooser dateChooser_Hasta;
	private JTextField textField_nFactura;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JTextField textField_ImporteD;
	private JTextField textField_ImporteH;
	private JTable table;
	private JComboBox cmbProv;
	private TablaFacturasProveedoresResumen modeloFactProvRe;
	public TablaFacturasProveedoresResumen getModeloFactProvRe() {
		return modeloFactProvRe;
	}
	private JTextPane textPaneObservaciones;
	private JRadioButton rdbtnNewRadioButton;
	private JRadioButton rdbtnSinIva;
	private boolean verPulsado;
	private JTextField textField_nTalon;
	private JRadioButton rdbtnTodos;
	/**
	 * Create the panel.
	 */
	
	 public PdfPTable createTable1() throws DocumentException {
	        PdfPTable table = new PdfPTable(7);
	
	        table.setWidthPercentage(288 / 2.8f);
	        table.setWidths(new int[]{2, 2, 2, 2 , 2 ,2 , 2});
	        
	        PdfPCell cell;
	        //cabecera
	        cell = new PdfPCell(new Phrase("Facturas de proveedores"));
	        cell.setColspan(7);   
	        table.addCell(cell);
	        table.addCell("Fecha");
	        table.addCell("Proveedores");
	        table.addCell("Factura");
	        table.addCell("Fecha Pago");
	        table.addCell("Iva");
	        table.addCell("Impuestos");
	        table.addCell("Total");
	        //datos
	        int row=modeloFactProvRe.getRowCount();
	        for (int i=0; i<row; i++)
	        {
		      for (int j=0; j<modeloFactProvRe.getColumnCount(); j++)
	        	table.addCell(modeloFactProvRe.getValueAt(i, j).toString());
		    	  
	        }
	        return table;
	 }
	 
	public void Imprimir()
	{
		FileOutputStream archivo = null;
		

		try {

			archivo = new FileOutputStream("c:\\Users\\alumno\\Desktop\\PescaderiaEspeApp\\hola.pdf");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	     Document documento = new Document();
	      try {
			PdfWriter.getInstance(documento, archivo);
			documento.open();
			PdfPTable table = createTable1();
		    documento.add(table);
		    documento.close();
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void abreFactura()
	{
		if (table.getSelectedRow()!=-1)
		{
			setVerPulsado(true);
			Window win = SwingUtilities.getWindowAncestor(this);
		      if (win != null) {
		         win.dispose();
		      }
		}
	}
	public void cargaTabla(String criterio)
	{
		
		int row=modeloFactProvRe.getRowCount();
		for (int i=0; i<row; i++)
			modeloFactProvRe.removeRow(0);
 		ResultSet rs=ConectorBD.bdMySQL.Select("facturasproveedores", "*", criterio);
 		try {
 			while (rs.next())
 			{
 				FacturasProveedoresC a=new FacturasProveedoresC();
 				
 				a.setIdFactProv(rs.getObject(1).toString());
 				a.setnFactura(rs.getObject(2).toString());
 				a.setIdProveedor(rs.getObject(3).toString());
 				a.setIdFormaPago(rs.getObject(4).toString());
 				a.setFecha(rs.getObject(5).toString());
 				if (rs.getObject(6)!=null)
 					a.setFechaPago(rs.getObject(6).toString());
 				a.setObservaciones(rs.getObject(7).toString());
 				a.setImpuestos(rs.getObject(8).toString());
 				a.setIva(rs.getObject(9).toString());
 				ResultSet rs1=ConectorBD.bdMySQL.SelectAux("proveedores", "Proveedor", "Id="+a.getIdProveedor());
 				rs1.next();
 				a.setProveedor(rs1.getObject(1).toString());
 				rs1=ConectorBD.bdMySQL.SelectAux("detallecompras", "SUM(Precio*cantidad)", "IdFactura="+a.getIdFactProv());
 				rs1.next();
 				String subtotal=rs1.getObject(1).toString();
 				a.setTotal(Double.toString(Double.parseDouble(subtotal)*(1+Double.parseDouble(a.getIva())/100)*(1+Double.parseDouble(a.getImpuestos())/100)));
 				int i=0;
 				if (!textField_ImporteD.getText().equals(""))
 				{
 				
 					Double aux=Double.parseDouble(textField_ImporteD.getText());
 					if (Double.parseDouble(a.getTotal())<aux)
 						i++;
 				}
 				if (!textField_ImporteH.getText().equals(""))	
 				{
 	 				
 					Double aux=Double.parseDouble(textField_ImporteH.getText());
 					if (Double.parseDouble(a.getTotal())>aux)
 						i++;
 				}
 				if (i==0)
 					modeloFactProvRe.insertRow(a);
 				
 			}
 		} catch (SQLException e1) {
 			// TODO Auto-generated catch block
 			e1.printStackTrace();
 		}
	}
	public void setEstadoInicial()
	{
		int row=modeloFactProvRe.getRowCount();
		for (int i=0; i<row; i++)
			modeloFactProvRe.removeRow(0);
		table.clearSelection();
		textField_ImporteD.setText("");
		textField_ImporteH.setText("");
		textField_nFactura.setText("");
		textPaneObservaciones.setText("");
		cmbProv.setSelectedIndex(0);
		rdbtnSinIva.setSelected(false);
		dateChooser_Desde.setCalendar(null);
		
	}
	public panelBusquedaFacProv() {
		
		setVerPulsado(false);
		setBackground(SystemColor.textHighlight);
		
		
		setBounds(0, 0, Toolkit.getDefaultToolkit().getScreenSize().width, Toolkit.getDefaultToolkit().getScreenSize().height);
	
		setVisible(true);
		setLayout(null);
		
		
		dateChooser_Desde = new JDateChooser();
		dateChooser_Desde.setDateFormatString("dd-MM-yyyy");
		dateChooser_Desde.setBounds(10, 118, 212, 20);
		add(dateChooser_Desde);
		
		dateChooser_Hasta = new JDateChooser();
		dateChooser_Hasta.setDateFormatString("dd-MM-yyyy");
		dateChooser_Hasta.setCalendar(Calendar.getInstance());
		dateChooser_Hasta.setBounds(232, 118, 191, 20);
		add(dateChooser_Hasta);
		// cmb Porveedor
		
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
 		
 		cmbProv = new JComboBox<ProveedorC>(elementos1);
		cmbProv.setBounds(607, 118, 219, 20);
		add(cmbProv);
		
		textField_nFactura = new JTextField();
		textField_nFactura.setBounds(433, 118, 164, 20);
		add(textField_nFactura);
		textField_nFactura.setColumns(10);
		
		JButton btnBuscar = new JButton("");
		btnBuscar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				String criterio="true";
				SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
				// fechas
				if (dateChooser_Desde.getCalendar()!=null)
				{
					criterio+=" AND Fecha>='"+formatoFecha.format(dateChooser_Desde.getCalendar().getTime())+"'";
				}
				if (dateChooser_Hasta.getCalendar()!=null)
				{
					criterio+=" AND Fecha<='"+formatoFecha.format(dateChooser_Hasta.getCalendar().getTime())+"'";
				}
			if (!textField_nFactura.equals(""))
				criterio+=" AND nFactProv LIKE '%"+textField_nFactura.getText()+"%'";
			if(!textPaneObservaciones.getText().equals(""))
				criterio+=" AND Observaciones LIKE '%"+textPaneObservaciones.getText()+"%'";
			if (cmbProv.getSelectedIndex()!=0)
				criterio+=" AND IdProveedor="+((ProveedorC)cmbProv.getSelectedItem()).getId();
			if (rdbtnSinIva.isSelected())
				criterio+=" AND Iva=0.0";
			else
			{
				if (rdbtnNewRadioButton.isSelected())
					criterio+=" AND Iva<>0.0";
			}
				
			if (!textField_nTalon.getText().equals(""))
				criterio+=" AND nTalon LIKE '%"+textField_nTalon.getText()+"%'";
		
			
			cargaTabla(criterio);
			}
		});
		btnBuscar.setIcon(new ImageIcon(FacturasProveedores.class.getResource("/Imagenes/Search-icon.png")));
		btnBuscar.setToolTipText("Buscar");
		btnBuscar.setBounds(10, 5, 81, 57);
		add(btnBuscar);
		
		JButton button = new JButton("");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
					abreFactura();
			}
		});
		button.setIcon(new ImageIcon(FacturasProveedores.class.getResource("/Imagenes/16-Quick-Look-icon.png")));
		button.setToolTipText("Abrir");
		button.setBounds(103, 5, 81, 57);
		add(button);
		
		JLabel lblNewLabel = new JLabel("Desde");
		lblNewLabel.setBounds(10, 93, 30, 14);
		add(lblNewLabel);
		
		JLabel lblHasta = new JLabel("Hasta");
		lblHasta.setBounds(232, 93, 28, 14);
		add(lblHasta);
		
		JLabel lblProveedor = new JLabel("Proveedor");
		lblProveedor.setBounds(607, 93, 50, 14);
		add(lblProveedor);
		
		JLabel lblNmeroFactura = new JLabel("N\u00FAmero Factura");
		lblNmeroFactura.setBounds(433, 93, 77, 14);
		add(lblNmeroFactura);
		
		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.textHighlight);
		panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Importe", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(10, 162, 413, 124);
		add(panel);
		panel.setLayout(null);
		
		JLabel lblImporteDesde = new JLabel("Desde");
		lblImporteDesde.setBounds(10, 29, 111, 14);
		panel.add(lblImporteDesde);
		
		textField_ImporteD = new JTextField();
		textField_ImporteD.setBounds(10, 58, 187, 20);
		panel.add(textField_ImporteD);
		textField_ImporteD.setColumns(10);
		
		textField_ImporteH = new JTextField();
		textField_ImporteH.setBounds(207, 58, 187, 20);
		panel.add(textField_ImporteH);
		textField_ImporteH.setColumns(10);
		
		JLabel lblImporteHasta = new JLabel("Hasta");
		lblImporteHasta.setBounds(207, 29, 197, 14);
		panel.add(lblImporteHasta);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 297, 1342, 434);
		add(scrollPane);
		//-------TABLA--------------
		
		
		
		Vector<String> columnNames= new Vector<String>();
		columnNames.add("Fecha");
		columnNames.add("Proveedor");
		columnNames.add("Factura");
		columnNames.add("Fecha Pago");
		columnNames.add("Iva");
		columnNames.add("Impuestos");
		columnNames.add("Total");
		
		Vector<FacturasProveedoresC> vectorTabla= new Vector<FacturasProveedoresC>();
		modeloFactProvRe= new TablaFacturasProveedoresResumen(vectorTabla,columnNames);
		
		
		
		table = new JTable(modeloFactProvRe);
		scrollPane.setViewportView(table);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(SystemColor.textHighlight);
		panel_1.setBorder(new TitledBorder(null, "IVA", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(433, 162, 164, 124);
		add(panel_1);
		panel_1.setLayout(null);
		
		rdbtnNewRadioButton = new JRadioButton("Con");
		rdbtnNewRadioButton.setBackground(SystemColor.textHighlight);
		rdbtnNewRadioButton.setBounds(6, 29, 54, 23);
		panel_1.add(rdbtnNewRadioButton);
		buttonGroup.add(rdbtnNewRadioButton);
		
		rdbtnSinIva = new JRadioButton("Sin");
		rdbtnSinIva.setBackground(SystemColor.textHighlight);
		rdbtnSinIva.setBounds(6, 71, 54, 23);
		panel_1.add(rdbtnSinIva);
		buttonGroup.add(rdbtnSinIva);
		
		rdbtnTodos = new JRadioButton("Todos");
		buttonGroup.add(rdbtnTodos);
		rdbtnTodos.setSelected(true);
		rdbtnTodos.setBackground(SystemColor.textHighlight);
		rdbtnTodos.setBounds(62, 29, 82, 23);
		panel_1.add(rdbtnTodos);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(null, "Observaciones", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_2.setBackground(SystemColor.textHighlight);
		panel_2.setBounds(607, 162, 413, 124);
		add(panel_2);
		panel_2.setLayout(null);
		
		textPaneObservaciones = new JTextPane();
		textPaneObservaciones.setBounds(10, 22, 384, 91);
		panel_2.add(textPaneObservaciones);
		
		JButton button_1 = new JButton("");
		button_1.setIcon(new ImageIcon(panelBusquedaFacProv.class.getResource("/Imagenes/Office-Stuff-Printer-icon.png")));
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Imprimir();
			}
		});
		button_1.setToolTipText("Abrir");
		button_1.setBounds(194, 5, 81, 57);
		add(button_1);
		
		JLabel lbltalon = new JLabel("N\u00FAmero Tal\u00F3n");
		lbltalon.setBounds(836, 93, 77, 14);
		add(lbltalon);
		
		textField_nTalon = new JTextField();
		textField_nTalon.setText("");
		textField_nTalon.setColumns(10);
		textField_nTalon.setBounds(836, 118, 184, 20);
		add(textField_nTalon);
		
		
		setEstadoInicial();
	}
	public JTable getTable() {
		return table;
	}
	public boolean getVerPulsado() {
		return verPulsado;
	}
	public void setVerPulsado(boolean verPulsado) {
		this.verPulsado = verPulsado;
	}
}
