package Ventanas;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import org.freixas.jcalendar.*;

/**
 * This example shows various instances of the JCalendar class.
 * <hr>
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the Artistic License. You should have
 * received a copy of the Artistic License along with this program. If
 * not, a copy is available at
 * <a href="http://opensource.org/licenses/artistic-license.php">
 * opensource.org</a>.
 *
 * @author Antonio Freixas
 */

// Copyright © 2004 Antonio Freixas
// All Rights Reserved.

public class Compras 
{

	private JFrame frmCompras;
	private JButton btnNuevo;
	private JButton btnEditar;
	private JButton btnBorrar;
	private JButton btnAceptar;
	private JButton btnCancelar;
	private JButton btnAceptar_edit;
	private JButton btnCancelar_edit;
	
	
	
	
	public static Date fecha;
	private JTable table;
/**
 * Create various instances of a JCalendar.
 */
	
	public void setEstadoInicial(){
		//table.setEnabled(true);
		btnAceptar.setVisible(false);
		btnCancelar.setVisible(false);
		/*cmbGrupo.setVisible(false);
		cmbTipo.setVisible(false);
		Patron.setVisible(false);
		Concepto.setVisible(false);*/
		btnAceptar_edit.setVisible(false);
		btnCancelar_edit.setVisible(false);
		//table.clearSelection();
		/*lblConcepto.setVisible(false);
		lblGrupo.setVisible(false);
		lblTipo.setVisible(false);
		lblPatron.setVisible(false);*/
		btnNuevo.setEnabled(true);
		btnEditar.setVisible(true);
		btnBorrar.setVisible(true);
		btnEditar.setEnabled(false);
		btnBorrar.setEnabled(false);
	} 

	public void setEstadoNuevo()
	{
		btnNuevo.setEnabled(false);
		btnAceptar.setVisible(true);
		btnCancelar.setVisible(true);
		/*list.setEnabled(false);
		
		textField_nombre.setEditable(true);
		textField_correo.setEditable(true);
		textField_telefono2.setEditable(true);
		textField_telefono1.setEditable(true);
		textField_direccion.setEditable(true);
		textField_fax.setEditable(true);
		textField_web.setEditable(true);
		textField_cp.setEditable(true);
		textField_nif.setEditable(true);
		textField_cuentaCorriente.setEditable(true);
		textField_banco.setEditable(true);
		textPane_observaciones.setEditable(true);
		comboBoxPoblacion.setEnabled(true);
		comboBoxProvincia.setEnabled(true);
		
		textField_nombre.setText("");
		textField_correo.setText("");
		textField_telefono2.setText("");
		textField_telefono1.setText("");
		textField_direccion.setText("");
		textField_fax.setText("");
		textField_web.setText("");
		textField_cp.setText("");
		textField_nif.setText("");
		textField_cuentaCorriente.setText("");
		textField_banco.setText("");
		textPane_observaciones.setText("");
		comboBoxPoblacion.setSelectedIndex(0);
		comboBoxProvincia.setSelectedIndex(0);*/
		
		btnEditar.setEnabled(false);
		btnBorrar.setEnabled(false);	
	}
	
	public void setEstadoSeleccion()
	{
		btnAceptar.setVisible(false);
		btnCancelar.setVisible(false);
		btnAceptar_edit.setVisible(false);
		btnCancelar_edit.setVisible(false);
		btnEditar.setEnabled(true);
		btnBorrar.setEnabled(true);
	}
	
	public void setEstadoEditar()
	{
		/*textField_nombre.setEditable(true);
		textField_correo.setEditable(true);
		textField_telefono2.setEditable(true);
		textField_telefono1.setEditable(true);
		textField_direccion.setEditable(true);
		textField_fax.setEditable(true);
		textField_web.setEditable(true);
		textField_cp.setEditable(true);
		textField_nif.setEditable(true);
		textField_cuentaCorriente.setEditable(true);
		textField_banco.setEditable(true);
		textPane_observaciones.setEditable(true);
		comboBoxPoblacion.setEnabled(true);
		comboBoxProvincia.setEnabled(true);
				
		list.setEnabled(false);*/
		btnAceptar_edit.setVisible(true);
		btnCancelar_edit.setVisible(true);
		btnNuevo.setEnabled(false);
		btnBorrar.setEnabled(false);
		btnEditar.setEnabled(false);		
	}
	
public
Compras()
{
    // Set up the frame
	frmCompras = new JFrame();
    frmCompras.setTitle("Compras");
    frmCompras.setExtendedState(JFrame.MAXIMIZED_BOTH);
    frmCompras.getContentPane().setLayout(null);


    //Creamos el calendario
    MyDateListener listener = new MyDateListener();


    JCalendar calendar2 = new JCalendar(JCalendar.DISPLAY_DATE, false);
    calendar2.setLocation(10, 11);
    calendar2.setSize(375, 200);
    calendar2.addDateListener(listener);

   
    //++++++++++++++++++++++++++++++++++++
        
    frmCompras.getContentPane().add(calendar2);
    
    btnNuevo = new JButton("");
    btnNuevo.addActionListener(new ActionListener() {
    	public void actionPerformed(ActionEvent arg0) {
    		setEstadoNuevo();
    	}
    });
    btnNuevo.setIcon(new ImageIcon(Compras.class.getResource("/Imagenes/Add-icon.png")));
    btnNuevo.setToolTipText("Nuevo");
    btnNuevo.setEnabled(true);
    btnNuevo.setBounds(395, 11, 80, 55);
    frmCompras.getContentPane().add(btnNuevo);
    
    btnEditar = new JButton("");
    btnEditar.addActionListener(new ActionListener() {
    	public void actionPerformed(ActionEvent e) {
    		setEstadoEditar();
    	}
    });
    btnEditar.setIcon(new ImageIcon(Compras.class.getResource("/Imagenes/Pencil-icon.png")));
    btnEditar.setToolTipText("Editar");
    btnEditar.setEnabled(false);
    btnEditar.setBounds(485, 11, 80, 55);
    frmCompras.getContentPane().add(btnEditar);
    
    btnBorrar = new JButton("");
    btnBorrar.addActionListener(new ActionListener() {
    	public void actionPerformed(ActionEvent e) {
    		setEstadoInicial();
    	}
    });
    btnBorrar.setIcon(new ImageIcon(Compras.class.getResource("/Imagenes/Trash-icon.png")));
    btnBorrar.setToolTipText("Borrar");
    btnBorrar.setEnabled(false);
    btnBorrar.setBounds(575, 11, 80, 55);
    frmCompras.getContentPane().add(btnBorrar);
    
    btnAceptar = new JButton("");
    btnAceptar.addActionListener(new ActionListener() {
    	public void actionPerformed(ActionEvent e) {
    		setEstadoInicial();
    	}
    });
    btnAceptar.setIcon(new ImageIcon(Compras.class.getResource("/Imagenes/Accept-icon.png")));
    btnAceptar.setVisible(false);
    btnAceptar.setToolTipText("Aceptar");
    btnAceptar.setBounds(665, 11, 80, 55);
    frmCompras.getContentPane().add(btnAceptar);
    
    btnCancelar = new JButton("");
    btnCancelar.addActionListener(new ActionListener() {
    	public void actionPerformed(ActionEvent e) {
    		setEstadoInicial();
    	}
    });
    btnCancelar.setVisible(false);
    btnCancelar.setIcon(new ImageIcon(Compras.class.getResource("/Imagenes/Delete-icon.png")));
    btnCancelar.setToolTipText("Cancelar");
    btnCancelar.setBounds(755, 11, 80, 55);
    frmCompras.getContentPane().add(btnCancelar);
    
    btnAceptar_edit = new JButton("");
    btnAceptar_edit.addActionListener(new ActionListener() {
    	public void actionPerformed(ActionEvent e) {
    		setEstadoInicial();
    	}
    });
    btnAceptar_edit.setVisible(false);
    btnAceptar_edit.setToolTipText("Aceptar");
    btnAceptar_edit.setBounds(665, 11, 80, 55);
    frmCompras.getContentPane().add(btnAceptar_edit);
    
    btnCancelar_edit = new JButton("");
    btnCancelar_edit.addActionListener(new ActionListener() {
    	public void actionPerformed(ActionEvent e) {
    		setEstadoInicial();
    	}
    });
    btnCancelar_edit.setVisible(false);
    btnCancelar_edit.setToolTipText("Cancelar");
    btnCancelar_edit.setBounds(755, 11, 80, 55);
    frmCompras.getContentPane().add(btnCancelar_edit);
    
    JScrollPane scrollPane = new JScrollPane();
    scrollPane.setBounds(10, 235, 375, 460);
    frmCompras.getContentPane().add(scrollPane);
    
    table = new JTable();
    scrollPane.setViewportView(table);


    frmCompras.setVisible(true);
    setEstadoInicial();
}






//**********************************************************************
// Inner Classes
//**********************************************************************

private class MyDateListener
      implements DateListener
{

public void
dateChanged(DateEvent e)
{

    Calendar c = e.getSelectedDate();
    if (c != null) {
	Compras.fecha=c.getTime();
    }
    else {
	System.out.println("No time selected.");
    }
}

}
}