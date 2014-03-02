package Ventanas;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JOptionPane;

import java.awt.SystemColor;
import javax.swing.JLabel;
import java.awt.Component;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


import BaseDatos.*;
import Clases.GeneroC;
import java.awt.Insets;
import javax.swing.ImageIcon;


import java.io.FileNotFoundException;
import java.io.FileOutputStream;
 
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class Portada {

	private JFrame frmPortada;
	private JButton btnGenero;
	private JButton btnConcepto;	
	private JButton btnProveedores;
	private JButton btnClientes;
	private JButton btnCompras;
	private JButton btnFacturasDeProveedores;
	private JButton btnFacturasAClientes;
	private JButton btnConfiguracion;
	private JButton btnMovimientos;
	
	// Ventanas
	
	private Genero vGenero;
	private Conceptos vConceptos;
	private Proveedores vProveedores;
	private Clientes vClientes;
	private FacturasProveedores vFacturasProveedores;
	private FacturasClientes vFacturasClientes;
	private Movimientos vMovimientos;
	private Balances vBalances;
	private Configuracion vConfiguracion;
	private Compras vCompras;
	private CotejarTalones vTalones;
	

	
// genero
	
	private GeneroC migenero;
	private JButton btnCotejoTalones;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Portada window = new Portada();
					window.frmPortada.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */

	 
	public Portada() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		ConectorBD.bdMySQL= new ConectorBD();
		ConectorBD.bdMySQL.ConectarMysql("espedb");
		
		
		frmPortada = new JFrame();
		frmPortada.setIconImage(Toolkit.getDefaultToolkit().getImage(Portada.class.getResource("/Imagenes/Animals-Fish-icon.png")));
		frmPortada.setTitle("Pescader\u00EDa ESPE Arraindegia");
		frmPortada.getContentPane().setBackground(SystemColor.textHighlight);
		frmPortada.setBounds(0, 0, Toolkit.getDefaultToolkit().getScreenSize().width, Toolkit.getDefaultToolkit().getScreenSize().height);
		frmPortada.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frmPortada.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmPortada.getContentPane().setLayout(null);
		
		btnGenero = new JButton("");
		btnGenero.setIcon(new ImageIcon(Portada.class.getResource("/Imagenes/Animals-Fish-icon48.png")));
		btnGenero.setToolTipText("G\u00E9nero");
		btnGenero.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				vGenero=new Genero();
			}
		});
		btnGenero.setBounds(10, 11, 80, 55);
		frmPortada.getContentPane().add(btnGenero);
		
		btnConcepto = new JButton("");
		btnConcepto.setIcon(new ImageIcon(Portada.class.getResource("/Imagenes/Kitchen-List-ingredients-icon.png")));
		btnConcepto.setToolTipText("Conceptos");
		btnConcepto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				vConceptos=new Conceptos();
			}
		});
		btnConcepto.setBounds(100, 11,80 , 55);
		frmPortada.getContentPane().add(btnConcepto);
		
		btnProveedores = new JButton("");
		btnProveedores.setMargin(new Insets(0, 14, 2, 14));
		btnProveedores.setIcon(new ImageIcon(Portada.class.getResource("/Imagenes/Transport-Cargo-ship-icon.png")));
		btnProveedores.setToolTipText("Proveedores");
		btnProveedores.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				vProveedores=new Proveedores();
			}
		});
		btnProveedores.setBounds(190, 11, 80, 55);
		frmPortada.getContentPane().add(btnProveedores);
		
		btnClientes = new JButton("");
		btnClientes.setIcon(new ImageIcon(Portada.class.getResource("/Imagenes/Users-icon.png")));
		btnClientes.setToolTipText("Clientes");
		btnClientes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				vClientes= new Clientes();
			}
		});
		
		btnClientes.setBounds(280, 11, 80, 55);
		frmPortada.getContentPane().add(btnClientes);
		
		btnCompras = new JButton("");
		btnCompras.setIcon(new ImageIcon(Portada.class.getResource("/Imagenes/Ecommerce-Buy-icon.png")));
		btnCompras.setToolTipText("Compras");
		btnCompras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				vCompras= new Compras();
				
			}
		});
		btnCompras.setBounds(370, 11, 80, 55);
		frmPortada.getContentPane().add(btnCompras);
		
		btnFacturasDeProveedores = new JButton("");
		btnFacturasDeProveedores.setMargin(new Insets(0, 14, 4, 14));
		btnFacturasDeProveedores.setIcon(new ImageIcon(Portada.class.getResource("/Imagenes/fp.png")));
		btnFacturasDeProveedores.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				vFacturasProveedores=new FacturasProveedores();
			}
		});
		btnFacturasDeProveedores.setToolTipText("Facturas de Proveedores");
		btnFacturasDeProveedores.setBounds(550, 11, 80, 55);
		frmPortada.getContentPane().add(btnFacturasDeProveedores);
		
		btnFacturasAClientes = new JButton("");
		btnFacturasAClientes.setIcon(new ImageIcon(Portada.class.getResource("/Imagenes/fc.png")));
		btnFacturasAClientes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				vFacturasClientes = new FacturasClientes();
			}
		});
		btnFacturasAClientes.setToolTipText("Facturas a Clientes");
		btnFacturasAClientes.setBounds(640, 11, 80, 55);
		frmPortada.getContentPane().add(btnFacturasAClientes);
		
		btnConfiguracion = new JButton("");
		btnConfiguracion.setIcon(new ImageIcon(Portada.class.getResource("/Imagenes/Sections-of-Website-Services-icon.png")));
		btnConfiguracion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				vConfiguracion=new Configuracion();
			}
		});
		btnConfiguracion.setToolTipText("Configuracion");
		btnConfiguracion.setBounds(910, 11, 80, 55);
		frmPortada.getContentPane().add(btnConfiguracion);
		
		btnMovimientos = new JButton("");
		btnMovimientos.setIcon(new ImageIcon(Portada.class.getResource("/Imagenes/Accounting-Purchase-order-icon.png")));
		btnMovimientos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				vMovimientos=new Movimientos();
			}
		});
		btnMovimientos.setToolTipText("Movimientos");
		btnMovimientos.setBounds(730, 11, 80, 55);
		frmPortada.getContentPane().add(btnMovimientos);
		
		JLabel lblTitulo1 = new JLabel("PESCADER\u00CDA");
		lblTitulo1.setForeground(Color.WHITE);
		lblTitulo1.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblTitulo1.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 45));
		lblTitulo1.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo1.setBounds(0, 0, 400, 200);
		lblTitulo1.setLocation((frmPortada.getSize().width/2)-(lblTitulo1.getSize().width/2), 100);
		frmPortada.getContentPane().add(lblTitulo1);
		
		JLabel lblTitulo2 = new JLabel("ESPE");
		lblTitulo2.setForeground(Color.WHITE);
		lblTitulo2.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo2.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 80));
		lblTitulo2.setAlignmentX(0.5f);
		lblTitulo2.setBounds(0, 200, 400, 200);
		lblTitulo2.setLocation((frmPortada.getSize().width/2)-(lblTitulo2.getSize().width/2), lblTitulo1.getLocation().y+100);
		frmPortada.getContentPane().add(lblTitulo2);
		
		JLabel lblTitulo3 = new JLabel("ARRAINDEGIA");
		lblTitulo3.setForeground(Color.WHITE);
		lblTitulo3.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo3.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 45));
		lblTitulo3.setAlignmentX(0.5f);
		lblTitulo3.setBounds(0, 400, 400, 200);
		lblTitulo3.setLocation((frmPortada.getSize().width/2)-(lblTitulo3.getSize().width/2), lblTitulo1.getLocation().y+200);
		frmPortada.getContentPane().add(lblTitulo3);
		
		btnCotejoTalones = new JButton("");
		btnCotejoTalones.setIcon(new ImageIcon(Portada.class.getResource("/Imagenes/Payment-Methods-Card-in-use-icon.png")));
		btnCotejoTalones.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				vTalones=new CotejarTalones();
			}
		});
		btnCotejoTalones.setToolTipText("Cotejo Talones");
		btnCotejoTalones.setMargin(new Insets(0, 14, 4, 14));
		btnCotejoTalones.setBounds(460, 11, 80, 55);
		frmPortada.getContentPane().add(btnCotejoTalones);
		
		JButton button = new JButton("");
		button.setIcon(new ImageIcon(Portada.class.getResource("/Imagenes/Management-Statistics-icon.png")));
		button.setToolTipText("Configuracion");
		button.setBounds(820, 11, 80, 55);
		frmPortada.getContentPane().add(button);
		

	}
}
