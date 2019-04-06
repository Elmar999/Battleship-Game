package src;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
public class BattleTest extends JFrame {
		
	JButton[][] button = new JButton[11][11];
	int confirm = 0;
	
	public BattleTest() {
		setVisible(true);  setSize(600, 400);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Boats_button toolbar = new Boats_button();
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(11,11,1,1));
		
		
		fill_grid(button, panel);
		add(toolbar, BorderLayout.SOUTH);
		add(panel);
		
		for (int i = 0; i < button.length; i++) {
			for (int j = 0; j < button.length; j++) {
				button[i][j].addMouseListener(new ButtonMouseListener(button, i, j, confirm ));
				button[i][j].setName(String.valueOf(0));
			}
		}	
//		print_matrix_values(button);
		
		
//		System.out.println("oldu");
		
		
	}
	
	public int getconfirm() {
		return confirm;
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
	
	public void print_matrix_values(JButton[][] btn) {
		for (int i = 0; i < btn.length; i++) {
			for (int j = 0; j < btn.length; j++) {
				System.out.print(btn[i][j].getName()+" ");
			}
			System.out.println();
		}
	}
	
	
	public void get_bomb(int x , int y) {
		if(!button[x][y].getName().equals("0")) {
			button[x][y].setBackground(Color.RED);
			System.out.println("hitted");
		}
	}


	
	
	
}
