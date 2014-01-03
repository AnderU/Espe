package Ventanas;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import BaseDatos.ConectorBD;
import Clases.ConfiguracionC;
import Clases.ConfiguracionConexion;
import Clases.TablaConfiguracion;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JTextField;
import javax.swing.JLabel;


public class Configuracion {

	private JFrame frame;
	private JTable table;
	private JTabbedPane Pestanas;
	private JPanel Parametros;
	private JPanel Conexion;
	private TablaConfiguracion model;
	private final ButtonGroup Local_Remota = new ButtonGroup();
	private JTextField textField_Servidor;
	private JTextField textField_Puerto;
	private JTextField textField_Nombre;
	private JTextField textField_User;
	private JTextField textField_Password;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the application.
	 */
	public Configuracion() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 670, 520);
		frame.getContentPane().setLayout(null);
		
		Pestanas = new JTabbedPane(JTabbedPane.TOP);
		Pestanas.setBounds(10, 11, 634, 460);
		frame.getContentPane().add(Pestanas);
		

		
		Parametros = new JPanel();
		Pestanas.addTab("Parámetros", null, Parametros, null);
		
		

		
		Conexion = new JPanel();
		Pestanas.addTab("Conexión", null, Conexion, null);
		Conexion.setLayout(null);
		
		JRadioButton rdbtnLocal = new JRadioButton("Local");
		Local_Remota.add(rdbtnLocal);
		rdbtnLocal.setBounds(6, 7, 109, 23);
		Conexion.add(rdbtnLocal);
		
		JRadioButton rdbtnRemota = new JRadioButton("Remota");
		rdbtnRemota.setSelected(true);
		Local_Remota.add(rdbtnRemota);
		rdbtnRemota.setBounds(117, 7, 109, 23);
		Conexion.add(rdbtnRemota);
		
		textField_Servidor = new JTextField();
		textField_Servidor.setToolTipText("");
		textField_Servidor.setBounds(117, 47, 216, 20);
		Conexion.add(textField_Servidor);
		textField_Servidor.setColumns(10);
		
		textField_Puerto = new JTextField();
		textField_Puerto.setColumns(10);
		textField_Puerto.setBounds(117, 78, 216, 20);
		Conexion.add(textField_Puerto);
		
		textField_Nombre = new JTextField();
		textField_Nombre.setColumns(10);
		textField_Nombre.setBounds(117, 109, 216, 20);
		Conexion.add(textField_Nombre);
		
		textField_User = new JTextField();
		textField_User.setColumns(10);
		textField_User.setBounds(117, 140, 216, 20);
		Conexion.add(textField_User);
		
		textField_Password = new JTextField();
		textField_Password.setColumns(10);
		textField_Password.setBounds(117, 173, 216, 20);
		Conexion.add(textField_Password);
		
		JLabel lblServidor = new JLabel("Servidor:");
		lblServidor.setBounds(10, 50, 101, 14);
		Conexion.add(lblServidor);
		
		JLabel lblPuerto = new JLabel("Puerto:");
		lblPuerto.setBounds(10, 78, 105, 14);
		Conexion.add(lblPuerto);
		
		JLabel lblNombreBaseDe = new JLabel("Nombre BD:");
		lblNombreBaseDe.setBounds(10, 112, 101, 14);
		Conexion.add(lblNombreBaseDe);
		
		JLabel lblUsuario = new JLabel("Usuario:");
		lblUsuario.setBounds(10, 143, 101, 14);
		Conexion.add(lblUsuario);
		
		JLabel lblContrasea = new JLabel("Contrase\u00F1a:");
		lblContrasea.setBounds(10, 176, 101, 14);
		Conexion.add(lblContrasea);
		
		JButton btnCancelar_1 = new JButton("Cancelar");
		btnCancelar_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				frame.dispose();
			}
		});
		btnCancelar_1.setBounds(530, 398, 89, 23);
		Conexion.add(btnCancelar_1);
		
		JButton btnAceptar_1 = new JButton("Aceptar");
		btnAceptar_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				ConfiguracionConexion conexionAux= new ConfiguracionConexion();
				conexionAux.setDbName(textField_Nombre.getText());
				conexionAux.setPassword(textField_Password.getText());
				conexionAux.setPuerto(textField_Puerto.getText());
				conexionAux.setServer(textField_Servidor.getText());
				conexionAux.setUser(textField_User.getText());
				FileOutputStream fic;
				try {
					fic = new FileOutputStream("cbd.conf");
					ObjectOutputStream escritor= new ObjectOutputStream(fic);
					escritor.writeObject(conexionAux);
					escritor.writeObject(null);
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
				
			}
		});
		btnAceptar_1.setBounds(431, 398, 89, 23);
		Conexion.add(btnAceptar_1);
		
				
		Vector <String> columnNames = new  Vector <String>(); 
		columnNames.add("Parámetro");
		columnNames.add("Valor");
		Vector<ConfiguracionC> vectorTabla= new Vector<ConfiguracionC>();
        ResultSet rs=ConectorBD.bdMySQL.Select("configuracion", "*", "true");
		
        try {
			while (rs.next())
			{
				ConfiguracionC fila= new ConfiguracionC();
				fila.setId(rs.getObject(1).toString());
				fila.setNombre(rs.getObject(2).toString());
				fila.setValor(rs.getObject(3).toString());			
				vectorTabla.add(fila);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        model = new TablaConfiguracion(vectorTabla, columnNames);
		Parametros.setLayout(null);
		table = new JTable(model);
		table.setBounds(10, 11, 609, 376);
		Parametros.add(table);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				frame.dispose();
			}
		});
		btnCancelar.setBounds(530, 398, 89, 23);
		Parametros.add(btnCancelar);
		
		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for (int i=0; i<model.getRowCount(); i++)
				{
					ConfiguracionC aux= new ConfiguracionC();
					aux.setId((String) model.getValueAt(i, 2));
					aux.setNombre((String) model.getValueAt(i,0));
					aux.setValor((String) model.getValueAt(i, 1));
					aux.Update();					
				}
				frame.dispose();
				
			}
		});
		btnAceptar.setBounds(431, 398, 89, 23);
		Parametros.add(btnAceptar);
		
		ConfiguracionConexion aux = null;
		try
		{
			FileInputStream fic= new FileInputStream("cbd.conf");
			ObjectInputStream lector= new ObjectInputStream(fic);
			
			while (true)
			{
				aux=(ConfiguracionConexion) lector.readObject();
			}
			
		}
		catch(FileNotFoundException e)
		{
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if (aux!=null)
		{
			textField_Nombre.setText(aux.getDbName());
			textField_Password.setText(aux.getPassword());
			textField_Puerto.setText(aux.getPuerto());
			textField_Servidor.setText(aux.getServer());
			textField_User.setText(aux.getUser());
			
		}
		else
		{
			textField_Nombre.setText("espedbaaa");
			textField_Password.setText("010190291290");
			textField_Puerto.setText(":3306");
			textField_Servidor.setText("db4free.net");
			textField_User.setText("anderyaitor");
		}


		
		
		
		frame.setVisible(true);
	}
}
