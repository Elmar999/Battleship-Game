package src;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Attack_frame extends JFrame {

	public int finish ;
	JPanel pnl ;
	JButton[][] btn_self = new JButton[11][11];
	JButton[][] btn ;
	
	public Attack_frame( JButton[][] button_rival ) {
		btn = button_rival;
		setVisible(true);  setSize(600, 400);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocation(700, 10);
		
		JPanel pnl = new JPanel();
		pnl.setLayout(new GridLayout(11,11,1,1));
		add(pnl);
		fill_grid(btn, pnl);

		
		
//		print_matrix_values(button);
//		button_rival[5][5].setName("5");
	}
	
	
	
	
	public void fill_jbutton(JButton[][] btn ) {
//		button_rival = new JButton[11][11];
		Attack_button_listener obj = null;
		
		for (int i = 0; i < btn.length; i++) {
			for (int j = 0; j < btn.length; j++) {
				obj = new Attack_button_listener(i , j , btn);
				btn[i][j].addMouseListener(obj);
				btn[i][j].setName(String.valueOf(0));
			}
		}
		finish = obj.finished;
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
	
	
	void fill_grid(JButton[][] button , JPanel pnl) {
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

		        pnl.add(button[i][j]);
		        
		     }
		}
		button[0][0].setBackground(Color.gray);
	}
	
	
}
