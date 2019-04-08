package src;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import com.sun.javafx.event.EventQueue;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Welcome_page extends JFrame {
	static JFrame fr;
	
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				try {
					fr =  new Welcome_page();
					fr.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public Welcome_page(){
	
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.NORTH);
		
		JButton btnNewButton = new JButton("Game with Computer");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Battle_main_Bot.main(new String[]{});
//				Battle_main_Server.main(new String[]{});
				fr.dispose();
			}
		});
		getContentPane().add(btnNewButton, BorderLayout.CENTER);
		
	
	}

}
