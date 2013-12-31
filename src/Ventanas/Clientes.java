package Ventanas;
import java.awt.SystemColor;
import java.awt.Toolkit;

import javax.swing.JFrame;


public class Clientes {

	private JFrame frmClnt;

	/**
	 * Launch the application.
	 */


	/**
	 * Create the application.
	 */
	public Clientes() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmClnt = new JFrame();
		frmClnt.setBounds(0, 0, Toolkit.getDefaultToolkit().getScreenSize().width, Toolkit.getDefaultToolkit().getScreenSize().height);
		frmClnt.setVisible(true);
		frmClnt.getContentPane().setBackground(SystemColor.textHighlight);
	
		frmClnt.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frmClnt.getContentPane().setLayout(null);
		frmClnt.setTitle("Clientes");
	}

}
