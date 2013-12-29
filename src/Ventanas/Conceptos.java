package Ventanas;


import java.awt.Component;
import java.awt.Toolkit;

import javax.swing.JFrame;
import java.awt.SystemColor;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;

import BaseDatos.ConectorBD;
import Clases.ConceptosC;
import Clases.GrupoConcepto;
import Clases.TablaConceptos;

import Clases.TipoConcepto;


import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class Conceptos {

	private JFrame frmConceptos;
	private JTable table;
	private JTextField Concepto;
	private JTextField Patron;
	private TablaConceptos model;
	private JScrollPane scrollPane;
	private JButton btnNuevo;
	private JButton btnEditar;
	private JButton btnBorrar;
	private JButton btnAceptar;
	private JButton btnAceptar_1;
	private JButton btnCancelar;
	private JButton btnCancelar_1;
	private JComboBox<GrupoConcepto> cmbGrupo;
	private JComboBox cmbTipo;
	private DefaultComboBoxModel<TipoConcepto> cmbModel;
	private DefaultComboBoxModel<GrupoConcepto> cmbModel1;
	
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
	@SuppressWarnings("unchecked")
	private void initialize() {
		frmConceptos = new JFrame();
		frmConceptos.setIconImage(Toolkit.getDefaultToolkit().getImage(Conceptos.class.getResource("/Imagenes/Animals-Fish-icon.png")));
		frmConceptos.setTitle("Conceptos");
		frmConceptos.getContentPane().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				table.clearSelection();
				table.setEnabled(true);
				btnEditar.setVisible(false);
				btnBorrar.setVisible(false);
				btnNuevo.setVisible(true);
				btnAceptar_1.setVisible(false);
				btnCancelar_1.setVisible(false);
				Patron.setText("");
				Concepto.setText("");
				Patron.setVisible(false);
				Concepto.setVisible(false);
				cmbTipo.setSelectedIndex(0);
				cmbGrupo.setSelectedIndex(0);
				cmbTipo.setVisible(false);
				cmbGrupo.setVisible(false);
				btnAceptar.setVisible(false);
				btnCancelar.setVisible(false);
			}
		});
		frmConceptos.getContentPane().setBackground(SystemColor.textHighlight);
		frmConceptos.setBounds(0, 0, Toolkit.getDefaultToolkit().getScreenSize().width, Toolkit.getDefaultToolkit().getScreenSize().height);
		frmConceptos.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frmConceptos.getContentPane().setLayout(null);
		
	
		// Cargamos la tabla
		Vector <String> columnNames = new  Vector <String>(); 
		columnNames.add("Id");
		columnNames.add("Concepto");
		columnNames.add("Patrón");
		columnNames.add("Grupo");		
		columnNames.add("Tipo");
		Vector<ConceptosC> vectorTabla= new Vector<ConceptosC>();
        ResultSet rs=ConectorBD.bdMySQL.Select("conceptos", "*", "true");
        ResultSet rs1;
		
        try {
			while (rs.next())
			{
				ConceptosC fila= new ConceptosC();
				fila.setId(Integer.parseInt(rs.getObject(1).toString()));
				fila.setConcepto(rs.getObject(2).toString());
				fila.setPatron(rs.getObject(3).toString());
				fila.setIdTipo(Integer.parseInt(rs.getObject(4).toString()));
				fila.setIdGrupo(Integer.parseInt(rs.getObject(5).toString()));
				rs1=ConectorBD.bdMySQL.SelectAux("tiposconcepto", "Tipo", "Id="+rs.getObject(4).toString());
				rs1.next();
				fila.setTipo(rs1.getObject(1).toString());
				rs1=ConectorBD.bdMySQL.SelectAux("gruposconcepto", "Grupos", "Id="+rs.getObject(5).toString());
				rs1.next();
				fila.setGrupo(rs1.getObject(1).toString());
				vectorTabla.add(fila);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        model = new TablaConceptos(vectorTabla, columnNames);
        
		table = new JTable(model);		
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {	
				

				btnEditar.setVisible(true);
				btnBorrar.setVisible(true);
				btnNuevo.setVisible(false);

			}
		});
		
        //------------------------------
		
		btnNuevo = new JButton("");
		btnNuevo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnAceptar.setVisible(true);
				btnCancelar.setVisible(true);
				cmbTipo.setVisible(true);
				cmbGrupo.setVisible(true);
				Patron.setVisible(true);
				Concepto.setVisible(true);
			}
		});
		btnNuevo.setIcon(new ImageIcon(Conceptos.class.getResource("/Imagenes/Add-icon.png")));
		btnNuevo.setToolTipText("Nuevo");
		btnNuevo.setBounds(10, 11, 80, 55);
		frmConceptos.getContentPane().add(btnNuevo);
		
		btnEditar = new JButton("");
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				cmbTipo.setSelectedIndex((Integer) table.getModel().getValueAt(table.getSelectedRow(), 4));
				cmbGrupo.setSelectedIndex((Integer) table.getModel().getValueAt(table.getSelectedRow(), 5));
				Concepto.setText(table.getModel().getValueAt(table.getSelectedRow(), 0).toString());
				Patron.setText(table.getModel().getValueAt(table.getSelectedRow(), 1).toString());
				Concepto.setVisible(true);
				Patron.setVisible(true);
				cmbTipo.setVisible(true);
				cmbGrupo.setVisible(true);
				btnBorrar.setVisible(false);
				btnAceptar_1.setVisible(true);
				btnCancelar_1.setVisible(true);
				table.setEnabled(false);
			}
		});
		btnEditar.setIcon(new ImageIcon(Conceptos.class.getResource("/Imagenes/Pencil-icon.png")));
		btnEditar.setToolTipText("Editar");
		btnEditar.setBounds(10, 11, 80, 55);
		btnEditar.setVisible(false);
		frmConceptos.getContentPane().add(btnEditar);
		
		btnAceptar = new JButton("");
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (cmbTipo.getSelectedIndex()!=0 && cmbGrupo.getSelectedIndex()!=0)
				{
				btnAceptar.setVisible(false);
				btnCancelar.setVisible(false);
				cmbTipo.setVisible(false);
				cmbGrupo.setVisible(false);
				Patron.setVisible(false);
				Concepto.setVisible(false);
				ConceptosC a= new ConceptosC();
							
				a.setConcepto(Concepto.getText().toUpperCase());
				a.setPatron(Patron.getText().toUpperCase());
				a.setIdGrupo(((GrupoConcepto)cmbGrupo.getSelectedItem()).getId());
				a.setIdTipo(((TipoConcepto)cmbTipo.getSelectedItem()).getId());
				a.setGrupo(((GrupoConcepto)cmbGrupo.getSelectedItem()).getGrupo());
				a.setTipo(((TipoConcepto)cmbTipo.getSelectedItem()).getTipo());
				model.insertRow(a);
				a.Insert();
				}
				else
				{
					JOptionPane.showConfirmDialog(frmConceptos, "Datos necesarios no completados", "¡Atención!", JOptionPane.OK_OPTION,JOptionPane.WARNING_MESSAGE);
					
				}
				
			}
		});
		btnAceptar.setToolTipText("Aceptar");
		btnAceptar.setIcon(new ImageIcon(Conceptos.class.getResource("/Imagenes/Accept-icon.png")));
		btnAceptar.setBounds(100, 11, 80, 55);
		btnAceptar.setVisible(false);
		frmConceptos.getContentPane().add(btnAceptar);
		
		btnCancelar = new JButton("");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnAceptar.setVisible(false);
				btnCancelar.setVisible(false);
				cmbTipo.setVisible(false);
				cmbGrupo.setVisible(false);
				Patron.setVisible(false);
				Concepto.setVisible(false);
				Patron.setText("");
				Concepto.setText("");
			}
		});
		btnCancelar.setIcon(new ImageIcon(Conceptos.class.getResource("/Imagenes/Cancel-icon.png")));
		btnCancelar.setToolTipText("Cancelar");
		btnCancelar.setBounds(190, 11, 80, 55);
		btnCancelar.setVisible(false);
		frmConceptos.getContentPane().add(btnCancelar);
		
		btnAceptar_1 = new JButton("");
		btnAceptar_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (cmbTipo.getSelectedIndex()!=0 && cmbGrupo.getSelectedIndex()!=0)
				{
				btnAceptar_1.setVisible(false);
				btnCancelar_1.setVisible(false);
				btnBorrar.setVisible(true);
				cmbTipo.setVisible(false);
				cmbGrupo.setVisible(false);
				Patron.setVisible(false);
				Concepto.setVisible(false);
				ConceptosC a= new ConceptosC();
				
				a.setId((Integer)table.getModel().getValueAt(table.getSelectedRow(), 6));
				a.setConcepto(Concepto.getText().toUpperCase());
				a.setPatron(Patron.getText().toUpperCase());
				a.setIdGrupo(((GrupoConcepto)cmbGrupo.getSelectedItem()).getId());
				a.setIdTipo(((TipoConcepto)cmbTipo.getSelectedItem()).getId());
				a.setGrupo(((GrupoConcepto)cmbGrupo.getSelectedItem()).getGrupo());
				a.setTipo(((TipoConcepto)cmbTipo.getSelectedItem()).getTipo());
				table.getModel().setValueAt(a.getConcepto(), table.getSelectedRow(), 0);
				table.getModel().setValueAt(a.getPatron(), table.getSelectedRow(), 1);
				table.getModel().setValueAt(a.getTipo(), table.getSelectedRow(), 2);
				table.getModel().setValueAt(a.getGrupo(), table.getSelectedRow(), 3);
				a.Update();
				Patron.setText("");
				Concepto.setText("");
				table.setEnabled(true);
				}
				else
				{
					JOptionPane.showConfirmDialog(frmConceptos, "Datos necesarios no completados", "¡Atención!", JOptionPane.ERROR_MESSAGE,JOptionPane.WARNING_MESSAGE);
					
				}
			}
		});
		btnAceptar_1.setIcon(new ImageIcon(Conceptos.class.getResource("/Imagenes/Accept-icon.png")));
		btnAceptar_1.setToolTipText("Aceptar");
		btnAceptar_1.setBounds(100, 11, 80, 55);
		btnAceptar_1.setVisible(false);
		frmConceptos.getContentPane().add(btnAceptar_1);
		
		btnCancelar_1 = new JButton("");
		btnCancelar_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				btnAceptar_1.setVisible(false);
				btnCancelar_1.setVisible(false);
				btnBorrar.setVisible(true);
				cmbTipo.setVisible(false);
				cmbGrupo.setVisible(false);
				Patron.setVisible(false);
				Concepto.setVisible(false);
				Patron.setText("");
				Concepto.setText("");
				table.setEnabled(true);

			}
		});
		btnCancelar_1.setToolTipText("Cancelar");
		btnCancelar_1.setIcon(new ImageIcon(Conceptos.class.getResource("/Imagenes/Cancel-icon.png")));
		btnCancelar_1.setBounds(190, 11, 80, 55);
		btnCancelar_1.setVisible(false);
		frmConceptos.getContentPane().add(btnCancelar_1);
		
		btnBorrar = new JButton("");
		btnBorrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				int result = JOptionPane.showConfirmDialog(frmConceptos, "¿Quiere borrar este elemento?", "¡Atención!", JOptionPane.OK_CANCEL_OPTION,JOptionPane.WARNING_MESSAGE);
				if (result==JOptionPane.OK_OPTION)
				{
					
					ConceptosC a= new ConceptosC();
					a.setId((Integer)table.getModel().getValueAt(table.getSelectedRow(), 6));
					a.Delete();
					model.removeRow(table.getSelectedRow());
				}
				
			}
		});
		btnBorrar.setIcon(new ImageIcon(Conceptos.class.getResource("/Imagenes/Delete-icon.png")));
		btnBorrar.setToolTipText("Borrar");
		btnBorrar.setBounds(100, 11, 80, 55);
		btnBorrar.setVisible(false);
		frmConceptos.getContentPane().add(btnBorrar);
		
		Concepto = new JTextField();
		Concepto.setBounds(10, 90, 260, 20);
		Concepto.setVisible(false);
		frmConceptos.getContentPane().add(Concepto);
		Concepto.setColumns(10);
		
		Patron = new JTextField();
		Patron.setColumns(10);
		Patron.setBounds(280, 90, 260, 20);
		Patron.setVisible(false);
		frmConceptos.getContentPane().add(Patron);
		// cargamos el combobox grupo
		GrupoConcepto aux1= new GrupoConcepto(); 
		rs=ConectorBD.bdMySQL.Select("gruposconcepto", "*", "true");
		Vector<GrupoConcepto> elementos1= new Vector<GrupoConcepto>(); 
		elementos1.addElement(aux1);
		try {
			while (rs.next())
			{
				GrupoConcepto a=new GrupoConcepto();
				a.setId(Integer.parseInt(rs.getObject(1).toString()));
				a.setGrupo(rs.getObject(2).toString());
				elementos1.addElement(a);
				
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		cmbGrupo = new JComboBox<GrupoConcepto>(elementos1);
		cmbGrupo.setBounds(1017, 90, 170, 20);
		cmbGrupo.setVisible(false);
		frmConceptos.getContentPane().add(cmbGrupo);
		
		//------------------
		// cargamos combobox tipo
		TipoConcepto aux= new TipoConcepto(); 
		rs=ConectorBD.bdMySQL.Select("tiposconcepto", "*", "true");
		Vector<TipoConcepto> elementos= new Vector<TipoConcepto>();
		elementos.addElement(aux);
		try {
			while (rs.next())
			{
				TipoConcepto a= new TipoConcepto(); 
				a.setId(Integer.parseInt(rs.getObject(1).toString()));
				a.setTipo(rs.getObject(2).toString());
				elementos.addElement(a);
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		cmbTipo = new JComboBox<TipoConcepto>(elementos);
		cmbTipo.setBounds(682, 90, 170, 20);
		cmbTipo.setVisible(false);
		frmConceptos.getContentPane().add(cmbTipo);
		
		//-----------------------
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 120, Toolkit.getDefaultToolkit().getScreenSize().width-20, Toolkit.getDefaultToolkit().getScreenSize().height-195);
		frmConceptos.getContentPane().add(scrollPane);
		scrollPane.setViewportView(table);
		frmConceptos.setVisible(true);
	}
}
