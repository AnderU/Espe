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
import javax.swing.table.DefaultTableModel;

import BaseDatos.ConectorBD;
import Clases.GrupoConcepto;
import Clases.TipoConcepto;

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
	private JComboBox cmbGrupo;
	private JComboBox<TipoConcepto> cmbTipo;
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
		frame = new JFrame();
		frame.getContentPane().setBackground(SystemColor.textHighlight);
		frame.setBounds(0, 0, Toolkit.getDefaultToolkit().getScreenSize().width, Toolkit.getDefaultToolkit().getScreenSize().height);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.getContentPane().setLayout(null);
		
		// Cargamos la tabla
		
		Vector <String> columnNames = new  Vector <String>(); //= {"Concepto", "Grupo","Patrón","Tipo"};
		columnNames.add("Id");
		columnNames.add("Concepto");
		columnNames.add("Patrón");
		columnNames.add("Grupo");		
		columnNames.add("Tipo");
		Vector<Vector <String>> vectorTabla= new Vector<Vector <String>>();
        ResultSet rs=ConectorBD.bdMySQL.Select("conceptos", "*", "true");
        ResultSet rs1;
		
        try {
			while (rs.next())
			{
				Vector <String> vectorfila= new	Vector <String>();
				vectorfila.add(rs.getObject(1).toString());
				vectorfila.add(rs.getObject(2).toString());
				vectorfila.add(rs.getObject(3).toString());
				rs1=ConectorBD.bdMySQL.SelectAux("gruposconcepto", "Grupos", "Id="+rs.getObject(4).toString());
				rs1.next();
				vectorfila.add(rs1.getObject(1).toString());
				rs1=ConectorBD.bdMySQL.SelectAux("tiposconcepto", "Tipo", "Id="+rs.getObject(5).toString());
				rs1.next();
				vectorfila.add(rs1.getObject(1).toString());
				vectorTabla.add(vectorfila);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        model = new DefaultTableModel(vectorTabla,columnNames);
        
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
        
        //------------------------------
		
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
		// cargamos el combobox grupo
		cmbModel1 =new DefaultComboBoxModel<GrupoConcepto>();
		GrupoConcepto aux1= new GrupoConcepto(); 
		rs=ConectorBD.bdMySQL.Select("gruposconcepto", "*", "true");
		cmbModel1.addElement(aux1);
		try {
			while (rs.next())
			{
				aux1.setId(Integer.parseInt(rs.getObject(1).toString()));
				aux1.setGrupo(rs.getObject(2).toString());
				cmbModel1.addElement(aux1);
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		cmbGrupo = new JComboBox<GrupoConcepto>(cmbModel1);
		cmbGrupo.setBounds(588, 90, 170, 20);
		
		
		cmbGrupo.setRenderer(new DefaultListCellRenderer() {
		    @Override
		    public Component getListCellRendererComponent(JList<?> list,
		                                               Object value,
		                                               int index,
		                                               boolean isSelected,
		                                               boolean cellHasFocus) {
		    	GrupoConcepto employee = (GrupoConcepto)value;
		        value = employee.getGrupo();
		        return super.getListCellRendererComponent(list, value,
		                index, isSelected, false);
		    }
		});
		
		
		frame.getContentPane().add(cmbGrupo);
		
		//------------------
		// cargamos combobox tipo
		cmbModel =new DefaultComboBoxModel<TipoConcepto>();
		TipoConcepto aux= new TipoConcepto(); 
		rs=ConectorBD.bdMySQL.Select("tiposconcepto", "*", "true");
		cmbModel.addElement(aux);
		try {
			while (rs.next())
			{
				aux.setId(Integer.parseInt(rs.getObject(1).toString()));
				aux.setTipo(rs.getObject(2).toString());
				cmbModel.addElement(aux);
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		cmbTipo = new JComboBox<TipoConcepto>(cmbModel);
		cmbTipo.setBounds(779, 90, 170, 20);
		
		cmbTipo.setRenderer(new DefaultListCellRenderer() {
		    @Override
		    public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
		    	TipoConcepto employee = (TipoConcepto)value;
		        value = employee.getTipo();
		        return super.getListCellRendererComponent(list, value,
		                index, isSelected, false);
		        
		        
		        
		        
		    }
		});
		
		frame.getContentPane().add(cmbTipo);
		
		//-----------------------
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 120, Toolkit.getDefaultToolkit().getScreenSize().width-20, Toolkit.getDefaultToolkit().getScreenSize().height-195);
		frame.getContentPane().add(scrollPane);
		
		

		scrollPane.setViewportView(table);
		frame.setVisible(true);
	}
}
