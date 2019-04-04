package src;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Attack_frame extends JFrame {

	
	JButton[][] button_rival = new JButton[11][11];
	JButton[][] btn_self = new JButton[11][11];
	
	public Attack_frame(JButton[][] btn) {
		this.button_rival = btn;
		setVisible(true);  setSize(600, 400);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocation(700, 10);
		
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(11,11,1,1));
		fill_grid(btn_self, panel);
		add(panel);
		
		for (int i = 0; i < btn_self.length; i++) {
			for (int j = 0; j < btn_self.length; j++) {
				btn_self[i][j].addMouseListener(new Attack_button_listener(button_rival, i , j , btn_self));
				btn_self[i][j].setName(String.valueOf(0));
			}
		}	
//		print_matrix_values(button);
		
	}
	
	

	void fill_with_letter(JButton[][] button ,int row , int col , int n) {
		String letter ;
		char[] ch = new char[2];
		
		if( n == 10) {
			button[row][col] = new JButton(String.valueOf(10));
			button[row][col].setBackground(Color.cyan);
		}
		else {
			ch[0] = (char)n;
			letter = new String(ch);
			button[row][col] = new JButton(letter);
			button[row][col].setBackground(Color.cyan);
		}
	}
	
	
	void fill_grid(JButton[][] button , JPanel panel) {
		int ascii = 65; int number = '1';
		
		for(int i=0;i<11;i++){
		     for(int j=0;j<11;j++){
		    	if(i == 0 && j != 0 ) {
		    		fill_with_letter(button, i, j, ascii);
		    		ascii++;
		    	}
		    	else if(j == 0 && i != 0 && i!=10){
		    		fill_with_letter(button, i, j, number);
		    		number++;
		    	}
		    	else if(i== 10 && j==0) {
		    		fill_with_letter(button, i, j, 10);
		    	}
		    	else
		    		button[i][j] = new JButton();

		        panel.add(button[i][j]);
		        
		     }
		}
		button[0][0].setBackground(Color.gray);
	}
	
	
}
