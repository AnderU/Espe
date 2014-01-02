package Ventanas;

import java.awt.Component;
import java.awt.Toolkit;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;

import BaseDatos.ConectorBD;
import Clases.GeneroC;
import javax.swing.JScrollBar;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.awt.SystemColor;


public class Genero {

	private JFrame frmGnero;
	private JList<GeneroC> list;
	private JTextField Genero;
	private JLabel lblNuevo;
	private JButton btnAceptar;
	private JButton btnCancelar;
	private DefaultListModel<GeneroC> listModel;
	private JButton btnBorrar;
	private JButton btnEditar;
	private JButton btnAceptar_edit;
	private JButton btnCancelar_edit;
	private JScrollPane scrollPane;
	private JButton btnNuevo ;
	/**
	 * Create the application.
	 */
	public Genero() {
		initialize();
		setEstadoInicial();
	}
	public void setEstadoInicial(){
		list.setEnabled(true);
		btnAceptar.setVisible(false);
		btnCancelar.setVisible(false);
		Genero.setVisible(false);
		btnAceptar_edit.setVisible(false);
		btnCancelar_edit.setVisible(false);
		list.clearSelection();
		lblNuevo.setVisible(false);
		btnNuevo.setEnabled(true);
		btnEditar.setVisible(true);
		btnBorrar.setVisible(true);
		btnEditar.setEnabled(false);
		btnBorrar.setEnabled(false);
	} 
	/**
	 * Initialize the contents of the frame.
	 */
	@SuppressWarnings("serial")
	private void initialize() {
		frmGnero = new JFrame();
		frmGnero.setIconImage(Toolkit.getDefaultToolkit().getImage(Genero.class.getResource("/Imagenes/Animals-Fish-icon.png")));
		frmGnero.setTitle("G\u00E9nero");
		frmGnero.getContentPane().setBackground(SystemColor.textHighlight);
		
		frmGnero.setBounds(0, 0, Toolkit.getDefaultToolkit().getScreenSize().width, Toolkit.getDefaultToolkit().getScreenSize().height);
		frmGnero.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frmGnero.getContentPane().setLayout(null);
		
		 listModel = new DefaultListModel<GeneroC>();
				
		ResultSet rs=ConectorBD.bdMySQL.Select("genero", "*", "true");
		try {
			while (rs.next())
			{
					GeneroC aux= new GeneroC();
					aux.setGenero(rs.getObject(2).toString());
					aux.setId(Integer.parseInt(rs.getObject(1).toString()));
					listModel.addElement(aux);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 125, Toolkit.getDefaultToolkit().getScreenSize().width-20, Toolkit.getDefaultToolkit().getScreenSize().height-200);
		frmGnero.getContentPane().add(scrollPane);
		
	
				list= new JList<GeneroC>(listModel);
				list.addListSelectionListener(new ListSelectionListener() {
					public void valueChanged(ListSelectionEvent e) {
						btnEditar.setVisible(true);
						btnEditar.setEnabled(true);
						btnBorrar.setEnabled(true);
						btnBorrar.setVisible(true);
						btnNuevo.setEnabled(true);
						btnAceptar.setVisible(false);
						btnCancelar.setVisible(false);
						list.setEnabled(true);
						Genero.setVisible(false);
						btnAceptar_edit.setVisible(false);
						btnCancelar_edit.setVisible(false);
						lblNuevo.setVisible(false);
					}
				});
				list.setCellRenderer(new DefaultListCellRenderer() {
					public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
		                Component renderer = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
		                if (renderer instanceof JLabel && value instanceof GeneroC) {
		                    ((JLabel) renderer).setText(((GeneroC) value).getGenero());
		                }
		                return renderer;
		            }
		        });
				scrollPane.setViewportView(list);
//------------------------------NUEVO------------------------------
		btnNuevo = new JButton("");
		btnNuevo.setHorizontalAlignment(SwingConstants.LEFT);
		btnNuevo.setIcon(new ImageIcon(Genero.class.getResource("/Imagenes/Add-icon.png")));
		btnNuevo.setToolTipText("Nuevo");
		btnNuevo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnNuevo.setEnabled(false);
				btnAceptar.setVisible(true);
				btnCancelar.setVisible(true);
				Genero.setVisible(true);
				Genero.setText("");
				list.setEnabled(false);
				btnEditar.setEnabled(false);
				btnBorrar.setEnabled(false);
				lblNuevo.setVisible(true);
			}
		});
		btnNuevo.setBounds(10, 11, 80, 55);
		frmGnero.getContentPane().add(btnNuevo);
			
//------------------------------GENERO------------------------------		
		Genero = new JTextField();
		Genero.setBounds(79, 90, 461, 20);
		frmGnero.getContentPane().add(Genero);
		Genero.setColumns(10);
		Genero.setVisible(false);
		
		lblNuevo = new JLabel("Nuevo");
		lblNuevo.setBounds(21, 93, 46, 14);
		frmGnero.getContentPane().add(lblNuevo);
		
//------------------------------ACEPTAR------------------------------
		btnAceptar = new JButton("");
		btnAceptar.setToolTipText("Aceptar");
		btnAceptar.setIcon(new ImageIcon(Genero.class.getResource("/Imagenes/Accept-icon.png")));
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Genero.setVisible(false);
				lblNuevo.setVisible(false);
				btnAceptar.setVisible(false);
				btnCancelar.setVisible(false);
				list.setEnabled(true);
				if (!Genero.getText().equals(""))
				{
					GeneroC aux= new GeneroC();
					aux.setGenero(Genero.getText().toUpperCase());
					aux.Insert();
					listModel.addElement(aux);
				}
				setEstadoInicial();
			}
		});
		btnAceptar.setBounds(355, 11, 80, 55);
		btnAceptar.setVisible(false);
		frmGnero.getContentPane().add(btnAceptar);
				
//------------------------------CANCELAR------------------------------
		btnCancelar = new JButton("");
		btnCancelar.setIcon(new ImageIcon(Genero.class.getResource("/Imagenes/Delete-icon.png")));
		btnCancelar.setToolTipText("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setEstadoInicial();
			}
		});
		btnCancelar.setBounds(460, 11, 80, 55);
		btnCancelar.setVisible(false);
		frmGnero.getContentPane().add(btnCancelar);
		
//------------------------------BORRAR------------------------------		
		btnBorrar = new JButton("");
		btnBorrar.setToolTipText("Borrar");
		btnBorrar.setIcon(new ImageIcon(Genero.class.getResource("/Imagenes/Trash-icon.png")));
		btnBorrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int result = JOptionPane.showConfirmDialog(frmGnero, "¿Quiere borrar este elemento?", "¡Atención!", JOptionPane.OK_CANCEL_OPTION,JOptionPane.WARNING_MESSAGE);
				if (result==JOptionPane.OK_OPTION)
				{
					GeneroC aux= new GeneroC();
					aux.setGenero(list.getSelectedValue().getGenero());
					aux.setId(list.getSelectedValue().getId());
					aux.Delete();
					listModel.remove(list.getSelectedIndex());
				}
				setEstadoInicial();
			}
		});
		btnBorrar.setBounds(214, 11, 80, 55);
		frmGnero.getContentPane().add(btnBorrar);
		
//------------------------------EDITAR------------------------------		
		btnEditar = new JButton("");
		btnEditar.setToolTipText("Editar");
		btnEditar.setIcon(new ImageIcon(Genero.class.getResource("/Imagenes/Pencil-icon.png")));
		btnEditar.setHorizontalAlignment(SwingConstants.LEFT);
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (!list.isSelectionEmpty())
				{
					Genero.setText(list.getSelectedValue().getGenero());
					Genero.setVisible(true);
					btnBorrar.setEnabled(false);
					btnEditar.setEnabled(false);
					btnNuevo.setEnabled(false);
					btnAceptar_edit.setVisible(true);
					btnCancelar_edit.setVisible(true);
					list.setEnabled(false);
					lblNuevo.setVisible(true);
				}
				
			}
		});
		btnEditar.setBounds(112, 11, 80, 55);
		frmGnero.getContentPane().add(btnEditar);
		
		btnEditar.setVisible(false);
		btnBorrar.setVisible(false);
				
//------------------------------ACEPTAR EDIT------------------------------
		btnAceptar_edit = new JButton("");
		btnAceptar_edit.setIcon(new ImageIcon(Genero.class.getResource("/Imagenes/Accept-icon.png")));
		btnAceptar_edit.setToolTipText("Aceptar");
		btnAceptar_edit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (!Genero.getText().equals(""))
				{
					GeneroC aux= new GeneroC();
					aux.setGenero(Genero.getText().toUpperCase());
					aux.setId(list.getSelectedValue().getId());
					aux.Update();
					listModel.remove(list.getSelectedIndex());
					listModel.addElement(aux);
					list.setSelectedIndex(list.getLastVisibleIndex());
				}
				setEstadoInicial();
			}
		});
		btnAceptar_edit.setBounds(355, 11, 80, 55);
		frmGnero.getContentPane().add(btnAceptar_edit);
				
//------------------------------CANCELAR EDIT------------------------------
		btnCancelar_edit = new JButton("");
		btnCancelar_edit.setToolTipText("Cancelar");
		btnCancelar_edit.setIcon(new ImageIcon(Genero.class.getResource("/Imagenes/Delete-icon.png")));
		btnCancelar_edit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				setEstadoInicial();
			}
		});
		btnCancelar_edit.setBounds(460, 11, 80, 55);
		frmGnero.getContentPane().add(btnCancelar_edit);
				
			
		
	
		frmGnero.setVisible(true);
	
		
	}
}
