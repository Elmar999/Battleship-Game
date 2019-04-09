package src;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class Welcome_page_user2 extends JFrame{
	static JFrame fr2;
	
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				try {
					fr2 = new Welcome_page_user2();
					fr2.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public Welcome_page_user2(){
	
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.NORTH);
		
		JButton btnNewButton = new JButton("Game with Computer");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				Battle_main_Bot.main(new String[]{});
//				Battle_main_user1.main(new String[]{});
				Battle_main_user2.main(new String[]{});
				fr2.dispose();
			}
		});
		getContentPane().add(btnNewButton, BorderLayout.CENTER);
		
	
	}

}
