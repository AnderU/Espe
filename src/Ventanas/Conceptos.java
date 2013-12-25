package Ventanas;


import java.awt.Toolkit;

import javax.swing.JFrame;
import java.awt.SystemColor;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

import BaseDatos.ConectorBD;
import Clases.ConceptosC;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class Conceptos {

	private JFrame frame;
	private JTable table;
	private JTextField Concepto;
	private JTextField Patron;
	private DefaultTableModel model;
	private JScrollPane scrollPane;
	private JButton btnNuevo;
	private JButton btnEditar;
	private JButton btnBorrar;
	private JButton btnAceptar;
	private JButton btnAceptar_1;
	private JButton btnCancelar;
	private JButton btnCancelar_1;
	
	/**
	 * Launch the application.
	 */

	/**
	 * Create the application.
	 */
	public Conceptos() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(SystemColor.textHighlight);
		frame.setBounds(0, 0, Toolkit.getDefaultToolkit().getScreenSize().width, Toolkit.getDefaultToolkit().getScreenSize().height);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.getContentPane().setLayout(null);
		
		Vector <String> columnNames = new  Vector <String>(); //= {"Concepto", "Grupo","Patrón","Tipo"};
		columnNames.add("Id");
		columnNames.add("Concepto");
		columnNames.add("Patrón");
		columnNames.add("Grupo");		
		columnNames.add("Tipo");
		Vector<Vector <String>> vectorTabla= new Vector<Vector <String>>();
        ResultSet rs=ConectorBD.bdMySQL.Select("conceptos", "*", "true");
		
        try {
			while (rs.next())
			{
				Vector <String> vectorfila= new	Vector <String>();
				vectorfila.add(rs.getObject(1).toString());
				vectorfila.add(rs.getObject(2).toString());
				vectorfila.add(rs.getObject(3).toString());
				vectorfila.add(rs.getObject(4).toString());
				vectorfila.add(rs.getObject(5).toString());
				vectorTabla.add(vectorfila);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        model = new DefaultTableModel(vectorTabla,columnNames);
        
		btnNuevo = new JButton("");
		btnNuevo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnAceptar.setVisible(true);
				btnCancelar.setVisible(true);
			}
		});
		btnNuevo.setIcon(new ImageIcon(Conceptos.class.getResource("/Imagenes/Add-icon.png")));
		btnNuevo.setToolTipText("Nuevo");
		btnNuevo.setBounds(10, 11, 80, 55);
		frame.getContentPane().add(btnNuevo);
		
		btnEditar = new JButton("");
		btnEditar.setIcon(new ImageIcon(Conceptos.class.getResource("/Imagenes/Pencil-icon.png")));
		btnEditar.setToolTipText("Editar");
		btnEditar.setBounds(10, 11, 80, 55);
		btnEditar.setVisible(false);
		frame.getContentPane().add(btnEditar);
		
		btnAceptar = new JButton("");
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnAceptar.setVisible(false);
				btnCancelar.setVisible(false);
			}
		});
		btnAceptar.setToolTipText("Aceptar");
		btnAceptar.setIcon(new ImageIcon(Conceptos.class.getResource("/Imagenes/Accept-icon.png")));
		btnAceptar.setBounds(100, 11, 80, 55);
		btnAceptar.setVisible(false);
		frame.getContentPane().add(btnAceptar);
		
		btnCancelar = new JButton("");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnAceptar.setVisible(false);
				btnCancelar.setVisible(false);
			}
		});
		btnCancelar.setIcon(new ImageIcon(Conceptos.class.getResource("/Imagenes/Cancel-icon.png")));
		btnCancelar.setToolTipText("Cancelar");
		btnCancelar.setBounds(190, 11, 80, 55);
		btnCancelar.setVisible(false);
		frame.getContentPane().add(btnCancelar);
		
		btnAceptar_1 = new JButton("");
		btnAceptar_1.setIcon(new ImageIcon(Conceptos.class.getResource("/Imagenes/Accept-icon.png")));
		btnAceptar_1.setToolTipText("Aceptar");
		btnAceptar_1.setBounds(100, 11, 80, 55);
		btnAceptar_1.setVisible(false);
		frame.getContentPane().add(btnAceptar_1);
		
		btnCancelar_1 = new JButton("");
		btnCancelar_1.setToolTipText("Cancelar");
		btnCancelar_1.setIcon(new ImageIcon(Conceptos.class.getResource("/Imagenes/Cancel-icon.png")));
		btnCancelar_1.setBounds(190, 11, 80, 55);
		btnCancelar_1.setVisible(false);
		frame.getContentPane().add(btnCancelar_1);
		
		btnBorrar = new JButton("");
		btnBorrar.setIcon(new ImageIcon(Conceptos.class.getResource("/Imagenes/Delete-icon.png")));
		btnBorrar.setToolTipText("Borrar");
		btnBorrar.setBounds(100, 11, 80, 55);
		btnBorrar.setVisible(false);
		frame.getContentPane().add(btnBorrar);
		
		Concepto = new JTextField();
		Concepto.setBounds(293, 90, 260, 20);
		frame.getContentPane().add(Concepto);
		Concepto.setColumns(10);
		
		Patron = new JTextField();
		Patron.setColumns(10);
		Patron.setBounds(10, 90, 260, 20);
		frame.getContentPane().add(Patron);
		
		JComboBox cmbGrupo = new JComboBox();
		cmbGrupo.setBounds(588, 90, 170, 20);
		frame.getContentPane().add(cmbGrupo);
		
		JComboBox cmbTipo = new JComboBox();
		cmbTipo.setBounds(779, 90, 170, 20);
		frame.getContentPane().add(cmbTipo);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 120, Toolkit.getDefaultToolkit().getScreenSize().width-20, Toolkit.getDefaultToolkit().getScreenSize().height-195);
		frame.getContentPane().add(scrollPane);
		
		
		table = new JTable(model);		
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {				
				Concepto.setText(table.getModel().getValueAt(table.getSelectedRow(), 1).toString());
				Patron.setText(table.getModel().getValueAt(table.getSelectedRow(), 2).toString());
				table.getModel().getValueAt(table.getSelectedRow(), 3);
				table.getModel().getValueAt(table.getSelectedRow(), 4);
			}
		});
		scrollPane.setViewportView(table);
		frame.setVisible(true);
	}
}
