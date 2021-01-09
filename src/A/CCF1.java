package A;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JDesktopPane;
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CCF1 extends JInternalFrame {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CCF1 frame = new CCF1();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public CCF1() {
		setBounds(100, 100, 839, 486);
		
		JDesktopPane desktopPane = new JDesktopPane();
		desktopPane.setBackground(Color.WHITE);
		getContentPane().add(desktopPane, BorderLayout.CENTER);
		
		JButton btnNewButton = new JButton("New button");
		
		btnNewButton.setBounds(282, 133, 123, 29);
		desktopPane.add(btnNewButton);

	}

}
