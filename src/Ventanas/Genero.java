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
	private JTextField textField;
	private JLabel lblNewLabel;
	private JButton btnAceptar;
	private JButton btnCancelar;
	private DefaultListModel<GeneroC> listModel;
	private JButton btnBorrar;
	private JButton btnEditar;
	private JButton btnAceptar_1;
	private JButton btnCancelar_1;
	private JScrollPane scrollPane;
	private JButton btnNuevo ;
	/**
	 * Create the application.
	 */
	public Genero() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	@SuppressWarnings("serial")
	private void initialize() {
		frmGnero = new JFrame();
		frmGnero.setTitle("G\u00E9nero");
		frmGnero.getContentPane().setBackground(SystemColor.textHighlight);
		frmGnero.getContentPane().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				list.clearSelection();
				btnEditar.setVisible(false);
				btnBorrar.setVisible(false);
				btnNuevo.setVisible(true);
				btnAceptar_1.setVisible(false);
				btnCancelar_1.setVisible(false);
				textField.setText("");
				textField.setVisible(false);
				list.setEnabled(true);
				lblNewLabel.setVisible(false);
				btnAceptar.setVisible(false);
				btnCancelar.setVisible(false);
				
			}
		});
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
						btnBorrar.setVisible(true);
						btnNuevo.setVisible(false);
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
				
				btnNuevo = new JButton("");
				btnNuevo.setHorizontalAlignment(SwingConstants.LEFT);
				btnNuevo.setIcon(new ImageIcon(Genero.class.getResource("/Imagenes/Add-icon.png")));
				btnNuevo.setToolTipText("Nuevo");
				btnNuevo.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						textField.setVisible(true);
						lblNewLabel.setVisible(true);
						btnAceptar.setVisible(true);
						btnCancelar.setVisible(true);
						list.setEnabled(false);
					}
				});
				btnNuevo.setBounds(10, 11, 80, 55);
				frmGnero.getContentPane().add(btnNuevo);
				
				textField = new JTextField();
				textField.setBounds(10, 94, Toolkit.getDefaultToolkit().getScreenSize().width-20, 20);
				frmGnero.getContentPane().add(textField);
				textField.setColumns(10);
				textField.setVisible(false);
				
				lblNewLabel = new JLabel("Nuevo:");
				lblNewLabel.setBounds(10, 69, 46, 14);
				frmGnero.getContentPane().add(lblNewLabel);
				
				btnAceptar = new JButton("");
				btnAceptar.setToolTipText("Aceptar");
				btnAceptar.setIcon(new ImageIcon(Genero.class.getResource("/Imagenes/Accept-icon.png")));
				btnAceptar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						textField.setVisible(false);
						lblNewLabel.setVisible(false);
						btnAceptar.setVisible(false);
						btnCancelar.setVisible(false);
						list.setEnabled(true);
						if (!textField.getText().equals(""))
						{
							GeneroC aux= new GeneroC();
							aux.setGenero(textField.getText());
							aux.Insert();
							listModel.addElement(aux);
						}
					}
				});
				btnAceptar.setBounds(100, 11, 80, 55);
				btnAceptar.setVisible(false);
				frmGnero.getContentPane().add(btnAceptar);
				
				btnCancelar = new JButton("");
				btnCancelar.setIcon(new ImageIcon(Genero.class.getResource("/Imagenes/Cancel-icon.png")));
				btnCancelar.setToolTipText("Cancelar");
				btnCancelar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						textField.setVisible(false);
						lblNewLabel.setVisible(false);
						btnAceptar.setVisible(false);
						btnCancelar.setVisible(false);
						textField.setText("");
						list.setEnabled(true);
					}
				});
				btnCancelar.setBounds(190, 11, 80, 55);
				btnCancelar.setVisible(false);
				frmGnero.getContentPane().add(btnCancelar);
				
				btnBorrar = new JButton("");
				btnBorrar.setToolTipText("Borrar");
				btnBorrar.setIcon(new ImageIcon(Genero.class.getResource("/Imagenes/Delete-icon.png")));
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
					}
				});
				btnBorrar.setBounds(100, 11, 80, 55);
				frmGnero.getContentPane().add(btnBorrar);
				
				btnEditar = new JButton("");
				btnEditar.setToolTipText("Editar");
				btnEditar.setIcon(new ImageIcon(Genero.class.getResource("/Imagenes/Pencil-icon.png")));
				btnEditar.setHorizontalAlignment(SwingConstants.LEFT);
				btnEditar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
						if (!list.isSelectionEmpty())
						{
						
							textField.setVisible(true);
							btnAceptar_1.setVisible(true);
							btnCancelar_1.setVisible(true);
							textField.setText(list.getSelectedValue().getGenero());
							list.setEnabled(false);
							btnBorrar.setVisible(false);
						}
						
					}
				});
				btnEditar.setBounds(10, 11, 80, 55);
				frmGnero.getContentPane().add(btnEditar);
				
				btnEditar.setVisible(false);
				btnBorrar.setVisible(false);
				
				btnAceptar_1 = new JButton("");
				btnAceptar_1.setIcon(new ImageIcon(Genero.class.getResource("/Imagenes/Accept-icon.png")));
				btnAceptar_1.setToolTipText("Aceptar");
				btnAceptar_1.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						textField.setVisible(false);
						btnAceptar_1.setVisible(false);
						btnCancelar_1.setVisible(false);
						list.setEnabled(true);
						if (!textField.getText().equals(""))
						{
							GeneroC aux= new GeneroC();
							aux.setGenero(textField.getText());
							aux.setId(list.getSelectedValue().getId());
							aux.Update();
							listModel.remove(list.getSelectedIndex());
							listModel.addElement(aux);
							list.setSelectedIndex(list.getLastVisibleIndex());
						}
						textField.setText("");
					}
				});
				btnAceptar_1.setBounds(100, 11, 80, 55);
				frmGnero.getContentPane().add(btnAceptar_1);
				
				btnCancelar_1 = new JButton("");
				btnCancelar_1.setToolTipText("Cancelar");
				btnCancelar_1.setIcon(new ImageIcon(Genero.class.getResource("/Imagenes/Cancel-icon.png")));
				btnCancelar_1.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
						textField.setVisible(false);
						btnAceptar_1.setVisible(false);
						btnCancelar_1.setVisible(false);
						textField.setText("");
						list.setEnabled(true);
						btnBorrar.setVisible(true);
					}
				});
				btnCancelar_1.setBounds(190, 11, 80, 55);
				frmGnero.getContentPane().add(btnCancelar_1);
				
				btnAceptar_1.setVisible(false);
				btnCancelar_1.setVisible(false);
				
				
				lblNewLabel.setVisible(false);
		
	
		frmGnero.setVisible(true);
	
		
	}
}
