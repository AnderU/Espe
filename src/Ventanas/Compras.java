package Ventanas;

import javax.swing.JFrame;
import java.awt.Toolkit;


public class Compras {

	private JFrame frmCompras;

	/**
	 * Launch the application.
	 */


	/**
	 * Create the application.
	 */
	public Compras() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmCompras = new JFrame();
		frmCompras.setIconImage(Toolkit.getDefaultToolkit().getImage(Compras.class.getResource("/Imagenes/Animals-Fish-icon.png")));
		frmCompras.setTitle("Compras");
		frmCompras.setBounds(100, 100, 450, 300);
		frmCompras.setExtendedState(JFrame.MAXIMIZED_BOTH);
		
		
		
		frmCompras.setVisible(true);
	}

}
