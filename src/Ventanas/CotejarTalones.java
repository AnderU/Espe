package Ventanas;

import java.awt.EventQueue;
import java.awt.SystemColor;
import java.awt.Toolkit;

import javax.swing.JFileChooser;
import javax.swing.JFrame;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.Vector;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;



import java.io.File;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import Tablas.TablaConceptos;
import Tablas.TablaDetalleCompra;
import Tablas.TablaExcel;

import BaseDatos.ConectorBD;
import Clases.ConceptosC;
import Clases.DetalleComprasC;
import Clases.ExcelC;



import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Insets;
import javax.swing.ImageIcon;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class CotejarTalones {

	private JFrame frame;
	private String ficheroExcel;
	private JTable table;
	private JScrollPane scrollPane;
	private TablaExcel model;
	private JButton button;
	/**
	 * Create the application.
	 */
	public CotejarTalones() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		frame = new JFrame();
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(FacturasProveedores.class.getResource("/Imagenes/Animals-Fish-icon.png")));
		frame.getContentPane().setBackground(SystemColor.textHighlight);
		frame.setBounds(0, 0, Toolkit.getDefaultToolkit().getScreenSize().width, Toolkit.getDefaultToolkit().getScreenSize().height);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.getContentPane().setLayout(null);
		
		
		Vector <String> columnNames = new  Vector <String>(); 
		
		columnNames.add("Fecha");
		columnNames.add("Concepto");
		columnNames.add("Importe");
		columnNames.add("Importe");
		Vector<ExcelC> vectorTabla= new Vector<ExcelC>();

        
        model = new TablaExcel(vectorTabla, columnNames);


        
		button = new JButton("");
		button.setIcon(new ImageIcon(CotejarTalones.class.getResource("/Imagenes/MS-Office-Excel-icon.png")));
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				JFileChooser chooser = new JFileChooser();
				chooser.setDialogTitle("Selecciona Destino");
				
				ResultSet rs=ConectorBD.bdMySQL.Select("configuracion", "Valor", "Parametro='DIRECTORIO DE EXCEL'");
				String directorio=null;
				try {
					rs.next();
					directorio=rs.getObject(1).toString().trim();
					chooser.setCurrentDirectory(new File(directorio));
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
							
				chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
                if (chooser.showOpenDialog(frame) == JFileChooser.APPROVE_OPTION) {
                	ficheroExcel=chooser.getSelectedFile().toString();
                	
                }
				
				InputStream input;
				XSSFSheet sheet=null;
				try {
					
					FileInputStream file = new FileInputStream(new File(ficheroExcel));
		             
					//Get the workbook instance for XLS file 
					XSSFWorkbook workbook = new XSSFWorkbook (file);
					 
					//Get first sheet from the workbook
					sheet = workbook.getSheetAt(0);
				    
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				Iterator<Row> rows = sheet.rowIterator(); 
				int i=0;
	            while( rows.hasNext() ) 
	            {   
	            	ExcelC fila= new ExcelC();
	                XSSFRow row = (XSSFRow) rows.next();
	                Iterator<Cell> cells = row.cellIterator();
	                while( cells.hasNext() )
	                {
	                	SimpleDateFormat a= new SimpleDateFormat("yyyy-MM-dd");
	               
	                    XSSFCell cell = (XSSFCell) cells.next();
	                   if (i==2)
	                   {
	                	   fila.setImporte(Double.toString(cell.getNumericCellValue()));
	                	   i=0;
	                   }
	                   else
	                   {
		                   if (i==1)
		                   {
		                	   fila.setConcepto(cell.getStringCellValue());
		                	   i++;
		                   }
		                   else
		                   {
			                   if (i==0)
			                   {
			                	   fila.setFecha(a.format(cell.getDateCellValue()));
			                	   i++;
			                   }
		                   }
	                   }
	                }
	                fila.toStringa();
	                model.insertRow(fila);
	            }
			}
		});
		button.setToolTipText("Cotejo Talones");
		button.setMargin(new Insets(0, 14, 4, 14));
		button.setBounds(10, 11, 80, 55);
		frame.getContentPane().add(button);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 77, 1340, 623);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable(model);
		scrollPane.setViewportView(table);
		frame.setVisible(true);
		frame.setTitle("Cotejar Talones");
	}
}
