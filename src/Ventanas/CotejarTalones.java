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
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;



import java.io.File;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import BaseDatos.ConectorBD;



import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Insets;
import javax.swing.ImageIcon;

public class CotejarTalones {

	private JFrame frame;
	private String ficheroExcel;
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
		
		JButton button = new JButton("");
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
	            while( rows.hasNext() ) {   
	                XSSFRow row = (XSSFRow) rows.next();
	                System.out.println("\n");
	                Iterator<Cell> cells = row.cellIterator();
	                while( cells.hasNext() ) {
	                     
	                    XSSFCell cell = (XSSFCell) cells.next();
	                    if(XSSFCell.CELL_TYPE_NUMERIC==cell.getCellType())
	                    System.out.print( cell.getNumericCellValue()+"     " );
	                    else
	                    if(XSSFCell.CELL_TYPE_STRING==cell.getCellType())
	                        System.out.print( cell.getStringCellValue()+"     " );
	                    else
	                        if(XSSFCell.CELL_TYPE_BOOLEAN==cell.getCellType())
	                        System.out.print( cell.getBooleanCellValue()+"     " );
	                        else
	                            if(XSSFCell.CELL_TYPE_BLANK==cell.getCellType())
	                                System.out.print( "BLANK     " );
	                                else
	                            System.out.print("Unknown cell type");
	                   
	                }
	                 
	                 
	            }
			}
		});
		button.setToolTipText("Cotejo Talones");
		button.setMargin(new Insets(0, 14, 4, 14));
		button.setBounds(10, 11, 80, 55);
		frame.getContentPane().add(button);
		frame.setVisible(true);
		frame.setTitle("Cotejar Talones");
	}

}
