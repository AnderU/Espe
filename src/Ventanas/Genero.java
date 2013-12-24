package Ventanas;

import javax.swing.JFrame;


public class Genero {

	private JFrame frame;


	/**
	 * Create the application.
	 */
	public Genero() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setVisible(true);
	}

}
