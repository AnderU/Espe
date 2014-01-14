package Ventanas;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.SystemColor;
import javax.swing.JLabel;
import java.awt.Component;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


import BaseDatos.*;
import Clases.GeneroC;
import java.awt.Window.Type;
import javax.swing.ImageIcon;
import java.awt.Insets;


public class Portada {

	private JFrame frmPortada;
	private JButton btnGenero;
	private JButton btnConcepto;	
	private int anchoP, nBotones;
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
	

	
// genero
	
	private GeneroC migenero;
	
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
			

				
		//this.altoP=Toolkit.getDefaultToolkit().getScreenSize().height;
		this.anchoP=Toolkit.getDefaultToolkit().getScreenSize().width;
		this.nBotones=11;

		
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
		btnConcepto.setIcon(new ImageIcon(Portada.class.getResource("/Imagenes/Accounting-Bill-icon.png")));
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
		btnFacturasDeProveedores.setBounds(460, 11, 80, 55);
		frmPortada.getContentPane().add(btnFacturasDeProveedores);
		
		btnFacturasAClientes = new JButton("");
		btnFacturasAClientes.setIcon(new ImageIcon(Portada.class.getResource("/Imagenes/fc.png")));
		btnFacturasAClientes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				vFacturasClientes = new FacturasClientes();
			}
		});
		btnFacturasAClientes.setToolTipText("Facturas a Clientes");
		btnFacturasAClientes.setBounds(550, 11, 80, 55);
		frmPortada.getContentPane().add(btnFacturasAClientes);
		
		btnConfiguracion = new JButton("");
		btnConfiguracion.setIcon(new ImageIcon(Portada.class.getResource("/Imagenes/Settings-icon.png")));
		btnConfiguracion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				vConfiguracion=new Configuracion();
			}
		});
		btnConfiguracion.setToolTipText("Configuracion");
		btnConfiguracion.setBounds(730, 11, 80, 55);
		frmPortada.getContentPane().add(btnConfiguracion);
		
		btnMovimientos = new JButton("");
		btnMovimientos.setIcon(new ImageIcon(Portada.class.getResource("/Imagenes/Accounting-Purchase-order-icon.png")));
		btnMovimientos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				vMovimientos=new Movimientos();
			}
		});
		btnMovimientos.setToolTipText("Movimientos");
		btnMovimientos.setBounds(640, 11, 80, 55);
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
	}
}
