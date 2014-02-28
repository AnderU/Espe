
package Ventanas;


import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Vector;

import javax.swing.*;
import javax.swing.border.TitledBorder;

import BaseDatos.ConectorBD;
import Clases.GeneroC;
import Clases.ProveedorC;
import Clases.ComprasC;
import Clases.FacturasClientesC;
import Tablas.TablaFacturasClientesResumen;
import Tablas.TablaListadoCompras;
import Tablas.TablaListadoComprasResumen;

import com.toedter.calendar.JDateChooser;

public class PanelBusquedaCom extends JPanel {
	
	private JDateChooser dateChooser_Desde;
	private JDateChooser dateChooser_Hasta;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JTextField textField_ImporteD;
	private JTextField textField_ImporteH;
	private JTable table;
	private JComboBox cmbProv;
	private TablaListadoComprasResumen modeloComp;
	public TablaListadoComprasResumen getModeloComp() {
		return modeloComp;
	}
	private JTextPane textPaneObservaciones;
	private JRadioButton rdbtnNewRadioButton;
	private JRadioButton rdbtnSinIva;
	private boolean verPulsado;
	private JComboBox<GeneroC> comboBoxGenero;
	private JRadioButton rdbtnTodos;
	private JButton btnBuscar;
	private JButton button;
	/**
	 * Create the panel.
	 */

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
		
		int row=modeloComp.getRowCount();
		for (int i=0; i<row; i++)
			modeloComp.removeRow(0);
 		ResultSet rs=ConectorBD.bdMySQL.Select("compras", "*", criterio);
 		try {
 			while (rs.next())
 			{
 				ComprasC a=new ComprasC();
 				
 				a.setId(rs.getObject(1).toString());
 				a.setIdproveedor(rs.getObject(2).toString());
 				a.setObservaciones(rs.getObject(4).toString());
 				a.setImpuestos(rs.getObject(5).toString());
 				a.setIva(rs.getObject(6).toString());
 				if (rs.getObject(3)!=null)
 					a.setFecha((Date)rs.getObject(3));
 				
  				ResultSet rs1=ConectorBD.bdMySQL.SelectAux("proveedores", "Proveedor", "Id="+a.getIdproveedor());
 				rs1.next();
 				a.setProveedor(rs1.getObject(1).toString());
 				rs1=ConectorBD.bdMySQL.SelectAux("detallecompras", "SUM(Precio*Cantidad)", "IdCompra="+a.getId());
 				rs1.next();
 				String subtotal=rs1.getObject(1).toString();
 				a.setImporte(Double.toString(Double.parseDouble(subtotal)*(1+Double.parseDouble(a.getIva())/100)));
 				modeloComp.insertRow(a);
 				
 			}
 		} catch (SQLException e1) {
 			// TODO Auto-generated catch block
 			e1.printStackTrace();
 		}
	}
	public void setEstadoInicial()
	{
		int row=modeloComp.getRowCount();
		for (int i=0; i<row; i++)
			modeloComp.removeRow(0);
		table.clearSelection();
		textField_ImporteD.setText("");
		textField_ImporteH.setText("");
		textPaneObservaciones.setText("");
		cmbProv.setSelectedIndex(0);
		rdbtnSinIva.setSelected(false);
		dateChooser_Desde.setCalendar(null);
		
	}
	public PanelBusquedaCom() {
		
		//cmb Genero
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
		comboBoxGenero = new JComboBox<GeneroC>(elementos1);
		comboBoxGenero.setSelectedIndex(0);
		comboBoxGenero.setBounds(711, 118, 285, 20);
		add(comboBoxGenero);


		
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
		
		ProveedorC aux11= new ProveedorC(); 
 		ResultSet rs1=ConectorBD.bdMySQL.Select("proveedores", "*", "true");
 		Vector<ProveedorC> elementos11= new Vector<ProveedorC>(); 
 		elementos11.addElement(aux11);
 		try {
 			while (rs1.next())
 			{
 				ProveedorC a=new ProveedorC();
 				
 				a.setId(Integer.parseInt(rs1.getObject(1).toString()));
 				a.setProveedor(rs1.getObject(2).toString());
 				elementos11.addElement(a);
 				
 			}
 		} catch (SQLException e1) {
 			// TODO Auto-generated catch block
 			e1.printStackTrace();
 		}
 		
 		cmbProv = new JComboBox<ProveedorC>(elementos11);
		cmbProv.setBounds(433, 118, 268, 20);
		add(cmbProv);
		
		btnBuscar = new JButton("");
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
			if (comboBoxGenero.getSelectedIndex()!=0)
			{
				criterio+=" AND Id IN (SELECT IdCompra FROM detallecompras WHERE IdGenero="+((GeneroC)comboBoxGenero.getSelectedItem()).getId()+")";
			}
			
			cargaTabla(criterio);
			}
		});
		btnBuscar.setIcon(new ImageIcon(FacturasProveedores.class.getResource("/Imagenes/Search-icon.png")));
		btnBuscar.setToolTipText("Buscar");
		btnBuscar.setBounds(10, 5, 81, 57);
		add(btnBuscar);
		
		button = new JButton("");
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
		lblNewLabel.setBounds(10, 93, 81, 14);
		add(lblNewLabel);
		
		JLabel lblHasta = new JLabel("Hasta");
		lblHasta.setBounds(232, 93, 70, 14);
		add(lblHasta);
		
		JLabel lblProveedor = new JLabel("Proveedor");
		lblProveedor.setBounds(433, 93, 156, 14);
		add(lblProveedor);
		
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
		columnNames.add("Importe");
		columnNames.add("Iva");
		columnNames.add("Impuestos");
		columnNames.add("Total");
		
		Vector<ComprasC> vectorTabla= new Vector<ComprasC>();
		modeloComp= new TablaListadoComprasResumen(vectorTabla,columnNames);
		
		
		
		table = new JTable(modeloComp);
		scrollPane.setViewportView(table);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(SystemColor.textHighlight);
		panel_1.setBorder(new TitledBorder(null, "IVA", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(433, 162, 133, 124);
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
		rdbtnTodos.setSelected(true);
		buttonGroup.add(rdbtnTodos);
		rdbtnTodos.setBackground(SystemColor.textHighlight);
		rdbtnTodos.setBounds(62, 29, 65, 23);
		panel_1.add(rdbtnTodos);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(null, "Observaciones", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_2.setBackground(SystemColor.textHighlight);
		panel_2.setBounds(576, 162, 420, 124);
		add(panel_2);
		panel_2.setLayout(null);
		
		textPaneObservaciones = new JTextPane();
		textPaneObservaciones.setBounds(10, 22, 400, 91);
		panel_2.add(textPaneObservaciones);
		

		
		JLabel lblGnero = new JLabel("G\u00E9nero");
		lblGnero.setBounds(711, 93, 163, 14);
		add(lblGnero);
		
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
