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

import Clases.TipoConcepto;


import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTabbedPane;
import javax.swing.JLabel;

import Tablas.TablaConceptos;


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
	private JButton btnAceptar_edit;
	private JButton btnCancelar;
	private JButton btnCancelar_edit;
	private JComboBox<GrupoConcepto> cmbGrupo;
	private JComboBox<TipoConcepto> cmbTipo;
	private JLabel lblConcepto;
	private JLabel lblGrupo;
	private JLabel lblTipo;
	private JLabel lblPatron;
	//private DefaultComboBoxModel<TipoConcepto> cmbModel;
	//private DefaultComboBoxModel<GrupoConcepto> cmbModel1;
	
	/**
	 * Launch the application.
	 */

	/**
	 * Create the application.
	 */
	public Conceptos() {
		initialize();
		setEstadoInicial();
	}

	public void setEstadoInicial(){
		table.setEnabled(true);
		btnAceptar.setVisible(false);
		btnCancelar.setVisible(false);
		cmbGrupo.setVisible(false);
		cmbTipo.setVisible(false);
		Patron.setVisible(false);
		Concepto.setVisible(false);
		btnAceptar_edit.setVisible(false);
		btnCancelar_edit.setVisible(false);
		table.clearSelection();
		lblConcepto.setVisible(false);
		lblGrupo.setVisible(false);
		lblTipo.setVisible(false);
		lblPatron.setVisible(false);
		btnNuevo.setEnabled(true);
		btnEditar.setVisible(true);
		btnBorrar.setVisible(true);
		btnEditar.setEnabled(false);
		btnBorrar.setEnabled(false);
	} 
	/**
	 * Initialize the contents of the frame.
	 */
	@SuppressWarnings("unchecked")
	private void initialize() {
		frmConceptos = new JFrame();
		frmConceptos.setIconImage(Toolkit.getDefaultToolkit().getImage(Conceptos.class.getResource("/Imagenes/Animals-Fish-icon.png")));
		frmConceptos.setTitle("Conceptos");
		
		frmConceptos.getContentPane().setBackground(SystemColor.textHighlight);
		frmConceptos.setBounds(0, 0, Toolkit.getDefaultToolkit().getScreenSize().width, Toolkit.getDefaultToolkit().getScreenSize().height);
		frmConceptos.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frmConceptos.getContentPane().setLayout(null);
		
		
	
		// Cargamos la tabla
		Vector <String> columnNames = new  Vector <String>(); 
		columnNames.add("Concepto");
		columnNames.add("Patrón");
		columnNames.add("Tipo");
		columnNames.add("Grupo");
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
		
//------------------------------NUEVO------------------------------
		
		btnNuevo = new JButton("");
		btnNuevo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnNuevo.setEnabled(false);
				btnAceptar.setVisible(true);
				btnCancelar.setVisible(true);
				cmbTipo.setVisible(true);
				cmbTipo.setSelectedIndex(0);
				cmbGrupo.setVisible(true);
				cmbGrupo.setSelectedIndex(0);
				Patron.setVisible(true);
				Patron.setText("");
				Concepto.setVisible(true);
				Concepto.setText("");
				table.setEnabled(false);
				btnEditar.setEnabled(false);
				btnBorrar.setEnabled(false);
				lblConcepto.setVisible(true);
				lblGrupo.setVisible(true);
				lblTipo.setVisible(true);
				lblPatron.setVisible(true);
				
			}
		});
		btnNuevo.setIcon(new ImageIcon(Conceptos.class.getResource("/Imagenes/Add-icon.png")));
		btnNuevo.setToolTipText("Nuevo");
		btnNuevo.setBounds(10, 11, 80, 55);
		frmConceptos.getContentPane().add(btnNuevo);
		
//------------------------------EDITAR------------------------------		
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
				btnBorrar.setEnabled(false);
				btnEditar.setEnabled(false);
				btnNuevo.setEnabled(false);
				btnAceptar_edit.setVisible(true);
				btnCancelar_edit.setVisible(true);
				table.setEnabled(false);
				lblConcepto.setVisible(true);
				lblGrupo.setVisible(true);
				lblTipo.setVisible(true);
				lblPatron.setVisible(true);
			}
		});
		btnEditar.setIcon(new ImageIcon(Conceptos.class.getResource("/Imagenes/Pencil-icon.png")));
		btnEditar.setToolTipText("Editar");
		btnEditar.setBounds(112, 11, 80, 55);
		btnEditar.setVisible(false);
		frmConceptos.getContentPane().add(btnEditar);
		
//------------------------------ACEPTAR------------------------------
		btnAceptar = new JButton("");
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (cmbTipo.getSelectedIndex()!=0 && cmbGrupo.getSelectedIndex()!=0)
				{
				ConceptosC a= new ConceptosC();
							
				a.setConcepto(Concepto.getText().toUpperCase());
				a.setPatron(Patron.getText().toUpperCase());
				a.setIdGrupo(((GrupoConcepto)cmbGrupo.getSelectedItem()).getId());
				a.setIdTipo(((TipoConcepto)cmbTipo.getSelectedItem()).getId());
				a.setGrupo(((GrupoConcepto)cmbGrupo.getSelectedItem()).getGrupo());
				a.setTipo(((TipoConcepto)cmbTipo.getSelectedItem()).getTipo());
				
				int id=a.Insert();
				a.setId(id);
				model.insertRow(a);
				setEstadoInicial();
				}
				else
				{
					JOptionPane.showMessageDialog(frmConceptos, "Datos necesarios no completados");
				}
				
			}
			
		});
		btnAceptar.setToolTipText("Aceptar");
		btnAceptar.setIcon(new ImageIcon(Conceptos.class.getResource("/Imagenes/Accept-icon.png")));
		btnAceptar.setBounds(355, 11, 80, 55);
		btnAceptar.setVisible(false);
		frmConceptos.getContentPane().add(btnAceptar);
		
//------------------------------CANCELAR------------------------------	
		btnCancelar = new JButton("");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setEstadoInicial();
			}
		});
		btnCancelar.setIcon(new ImageIcon(Conceptos.class.getResource("/Imagenes/Cancel-icon.png")));
		btnCancelar.setToolTipText("Cancelar");
		btnCancelar.setBounds(460, 11, 80, 55);
		btnCancelar.setVisible(false);
		frmConceptos.getContentPane().add(btnCancelar);
		
//------------------------------ACEPTAR EDIT------------------------------
		btnAceptar_edit = new JButton("");
		btnAceptar_edit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (cmbTipo.getSelectedIndex()!=0 && cmbGrupo.getSelectedIndex()!=0)
				{
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
				setEstadoInicial();
				}
				else
				{
					JOptionPane.showMessageDialog(frmConceptos, "Datos necesarios no completados");
					
				}
				
			}
		});
		btnAceptar_edit.setIcon(new ImageIcon(Conceptos.class.getResource("/Imagenes/Accept-icon.png")));
		btnAceptar_edit.setToolTipText("Aceptar");
		btnAceptar_edit.setBounds(355, 11, 80, 55);
		btnAceptar_edit.setVisible(false);
		frmConceptos.getContentPane().add(btnAceptar_edit);
		
//------------------------------CANCELAR EDIT------------------------------
		btnCancelar_edit = new JButton("");
		btnCancelar_edit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setEstadoInicial();
			}
		});
		btnCancelar_edit.setToolTipText("Cancelar");
		btnCancelar_edit.setIcon(new ImageIcon(Conceptos.class.getResource("/Imagenes/Cancel-icon.png")));
		btnCancelar_edit.setBounds(460, 11, 80, 55);
		btnCancelar_edit.setVisible(false);
		frmConceptos.getContentPane().add(btnCancelar_edit);
		
//------------------------------BORRAR------------------------------		
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
				setEstadoInicial();
			}
		});
		btnBorrar.setIcon(new ImageIcon(Conceptos.class.getResource("/Imagenes/Trash-icon.png")));
		btnBorrar.setToolTipText("Borrar");
		btnBorrar.setBounds(214, 11, 80, 55);
		btnBorrar.setVisible(false);
		frmConceptos.getContentPane().add(btnBorrar);

//------------------------------CONCEPTO------------------------------
		Concepto = new JTextField();
		Concepto.setBounds(79, 90, 378, 20);
		Concepto.setVisible(false);
		frmConceptos.getContentPane().add(Concepto);
		Concepto.setColumns(10);
		
//------------------------------PATRON------------------------------		
		Patron = new JTextField();
		Patron.setColumns(10);
		Patron.setBounds(536, 90, 260, 20);
		Patron.setVisible(false);
		frmConceptos.getContentPane().add(Patron);
		
//------------------------------COMBOBOX------------------------------
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
		cmbGrupo.setBounds(1128, 90, 206, 20);
		cmbGrupo.setVisible(false);
		frmConceptos.getContentPane().add(cmbGrupo);

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
		cmbTipo.setBounds(858, 90, 206, 20);
		cmbTipo.setVisible(false);
		frmConceptos.getContentPane().add(cmbTipo);
		
//------------------------------TABLA------------------------------
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 120, Toolkit.getDefaultToolkit().getScreenSize().width-20, Toolkit.getDefaultToolkit().getScreenSize().height-195);
		frmConceptos.getContentPane().add(scrollPane);
		
		table = new JTable(model);
		scrollPane.setViewportView(table);
		
		lblConcepto = new JLabel("Concepto");
		lblConcepto.setBounds(21, 93, 57, 14);
		frmConceptos.getContentPane().add(lblConcepto);
		
		lblPatron = new JLabel("Patr\u00F3n");
		lblPatron.setBounds(487, 93, 39, 14);
		frmConceptos.getContentPane().add(lblPatron);
		
		lblGrupo = new JLabel("Grupo");
		lblGrupo.setBounds(1084, 93, 46, 14);
		frmConceptos.getContentPane().add(lblGrupo);
		
		lblTipo = new JLabel("Tipo");
		lblTipo.setBounds(822, 93, 32, 14);
		frmConceptos.getContentPane().add(lblTipo);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {	
				if (table.isEnabled())
				{
					btnEditar.setVisible(true);
					btnEditar.setEnabled(true);
					btnBorrar.setEnabled(true);
					btnBorrar.setVisible(true);
					btnNuevo.setEnabled(true);
					btnAceptar.setVisible(false);
					btnCancelar.setVisible(false);
					table.setEnabled(true);
					cmbGrupo.setVisible(false);
					cmbTipo.setVisible(false);
					Patron.setVisible(false);
					Concepto.setVisible(false);
					btnAceptar_edit.setVisible(false);
					btnCancelar_edit.setVisible(false);
					lblTipo.setVisible(false);
					lblGrupo.setVisible(false);
					lblPatron.setVisible(false);
					lblConcepto.setVisible(false);
				}
	

			}
		});
		frmConceptos.setVisible(true);
	}
}
