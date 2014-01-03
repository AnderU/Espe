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
	
	public static Date fecha;
/**
 * Create various instances of a JCalendar.
 */

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


    frmCompras.setVisible(true);
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

//**********************************************************************
// End Inner Classes
//**********************************************************************

}