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

//hola aitor

public class Portada {

	private JFrame frame;
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
	private JButton btnBalances;
	
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
					window.frame.setVisible(true);
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

		
		frame = new JFrame();
		frame.getContentPane().setBackground(SystemColor.textHighlight);
		frame.setBounds(0, 0, Toolkit.getDefaultToolkit().getScreenSize().width, Toolkit.getDefaultToolkit().getScreenSize().height);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		btnGenero = new JButton("G\u00E9nero");
		btnGenero.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				vGenero=new Genero();
			}
		});
		btnGenero.setBounds(10, 11, anchoP/nBotones, 23);
		frame.getContentPane().add(btnGenero);
		
		btnConcepto = new JButton("Conceptos");
		btnConcepto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				vConceptos=new Conceptos();
			}
		});
		btnConcepto.setBounds(144, 11,anchoP/nBotones , 23);
		frame.getContentPane().add(btnConcepto);
		
		btnProveedores = new JButton("Proveedores");
		btnProveedores.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				vProveedores=new Proveedores();
			}
		});
		btnProveedores.setBounds(278, 11, anchoP/nBotones, 23);
		frame.getContentPane().add(btnProveedores);
		
		btnClientes = new JButton("Clientes");
		btnClientes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				vClientes= new Clientes();
			}
		});
		btnClientes.setBounds(412, 11, anchoP/nBotones, 23);
		frame.getContentPane().add(btnClientes);
		
		btnCompras = new JButton("Compras");
		btnCompras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				vCompras= new Compras();
			}
		});
		btnCompras.setBounds(546, 11, anchoP/nBotones, 23);
		frame.getContentPane().add(btnCompras);
		
		btnFacturasDeProveedores = new JButton("Facturas de Proveedores");
		btnFacturasDeProveedores.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				vFacturasProveedores=new FacturasProveedores();
			}
		});
		btnFacturasDeProveedores.setToolTipText("Facturas de Proveedores");
		btnFacturasDeProveedores.setBounds(680, 11, anchoP/nBotones, 23);
		frame.getContentPane().add(btnFacturasDeProveedores);
		
		btnFacturasAClientes = new JButton("Facturas a Clientes");
		btnFacturasAClientes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				vFacturasClientes = new FacturasClientes();
			}
		});
		btnFacturasAClientes.setToolTipText("Facturas a Clientes");
		btnFacturasAClientes.setBounds(814, 11, anchoP/nBotones, 23);
		frame.getContentPane().add(btnFacturasAClientes);
		
		btnConfiguracion = new JButton("Configuracion");
		btnConfiguracion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				vConfiguracion=new Configuracion();
			}
		});
		btnConfiguracion.setToolTipText("Configuracion");
		btnConfiguracion.setBounds(1216, 11, anchoP/nBotones, 23);
		frame.getContentPane().add(btnConfiguracion);
		
		btnMovimientos = new JButton("Movimientos");
		btnMovimientos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				vMovimientos=new Movimientos();
			}
		});
		btnMovimientos.setToolTipText("Movimientos");
		btnMovimientos.setBounds(948, 11, anchoP/nBotones, 23);
		frame.getContentPane().add(btnMovimientos);
		
		btnBalances = new JButton("Balances");
		btnBalances.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				vBalances= new Balances();
			}
		});
		btnBalances.setToolTipText("Balances");
		btnBalances.setBounds(1082, 11, anchoP/nBotones, 23);
		frame.getContentPane().add(btnBalances);
		
		JLabel lblTitulo1 = new JLabel("PESCADER\u00CDA");
		lblTitulo1.setForeground(Color.WHITE);
		lblTitulo1.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblTitulo1.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 45));
		lblTitulo1.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo1.setBounds(0, 0, 400, 200);
		lblTitulo1.setLocation((frame.getSize().width/2)-(lblTitulo1.getSize().width/2), 100);
		frame.getContentPane().add(lblTitulo1);
		
		JLabel lblTitulo2 = new JLabel("ESPE");
		lblTitulo2.setForeground(Color.WHITE);
		lblTitulo2.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo2.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 80));
		lblTitulo2.setAlignmentX(0.5f);
		lblTitulo2.setBounds(0, 200, 400, 200);
		lblTitulo2.setLocation((frame.getSize().width/2)-(lblTitulo2.getSize().width/2), lblTitulo1.getLocation().y+100);
		frame.getContentPane().add(lblTitulo2);
		
		JLabel lblTitulo3 = new JLabel("ARRAINDEGIA");
		lblTitulo3.setForeground(Color.WHITE);
		lblTitulo3.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo3.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 45));
		lblTitulo3.setAlignmentX(0.5f);
		lblTitulo3.setBounds(0, 400, 400, 200);
		lblTitulo3.setLocation((frame.getSize().width/2)-(lblTitulo3.getSize().width/2), lblTitulo1.getLocation().y+200);
		frame.getContentPane().add(lblTitulo3);
	}
}
