package src;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.util.concurrent.TimeUnit;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
public class BattleTest extends JFrame {
		
	JButton[][] button1 = new JButton[11][11];
	JButton[][] button2 = new JButton[11][11];
	int confirm = 0;
	private int x;
	private int y;
	int i = 0 ;
	int j = 0;
	
	public BattleTest() {
		setVisible(true);  setSize(600, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		JPanel panel = new JPanel();
		setContentPane(panel);
		
//		setLayout(new ());
		panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
		
		JPanel panel1 = new JPanel(); 
		panel1.setLayout(new GridLayout(11,11,1,1));
				
		JPanel panel2 = new JPanel();
		panel2.setLayout(new GridLayout(11,11,1,1));
		
		panel.add(panel1);
		panel.add(panel2);
		panel2.setVisible(false);
		
		
		JButton btn = new JButton("CONFIRM");
		Boats_button toolbar = new Boats_button(btn);
		panel.add(toolbar, BorderLayout.SOUTH);
		btn.addActionListener(e -> { confirm = 1 ; panel2.setVisible(true); toolbar.setVisible(false); playGame(button2); });
		

		fill_grid(button1, panel1);
		fill_grid(button2, panel2);
		
//		add(panel);
		
		for (int i = 0; i < button1.length; i++) {
			for (int j = 0; j < button1.length; j++) {
				button1[i][j].addMouseListener(new ButtonMouseListener(button1, i, j, confirm ));
				button1[i][j].setName(String.valueOf(0));
			}
		}	
//		print_matrix_values(button);
		
		
//		System.out.println("oldu");
		
		
	}
	
	private void playGame(JButton[][] btn) {
		// TODO Auto-generated method stub
		fill_bot(btn);	
//		
		for ( i = 0; i < btn.length; i++) {
			for (j = 0; j < btn.length; j++) {
				btn[i][j].putClientProperty("row", i);
				btn[i][j].putClientProperty("column", j);
				btn[i][j].addActionListener(new actionlistener() {
					public void actionPerformed(ActionEvent e) {
						JButton but = (JButton) e.getSource();
	//					System.out.println(but.getClientProperty("row") + " " + but.getClientProperty("column")); 
						int x = (int) but.getClientProperty("row");
						int y = (int) but.getClientProperty("column");
						System.out.println(x + " " + y);
						if(!btn[x][y].getName().equals("0")) btn[x][y].setBackground(Color.red);
						else 
							playBot(button1);
					}
					
					
						private void playBot(JButton[][] button1) {
							int x = (int)(Math.random() * 10 + 1);
							int y = (int)(Math.random() * 10 + 1);
														
							if(button1[x][y].getName().equals("0")) {
								button1[x][y].setName("1");
								sleep(2);
								button1[x][y].setBackground(Color.white);
							}
							else if(!button1[x][y].getName().equals("0") && !button1[x][y].getName().equals("1")) {
								button1[x][y].setBackground(Color.red);
								sleep(2);
								playBot(button1);
							}
							else if(button1[x][y].getName().equals("1")) {
								sleep(2);
								playBot(button1);
							}
							
						}
						
						private void sleep(int i) {
							// TODO Auto-generated method stub
							try {
								TimeUnit.SECONDS.sleep(2);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
			
					}
				);
			}
		}
		
	}

	private void fill_bot(JButton[][] bot_ship) {
		for (int i = 0; i < bot_ship.length; i++) 
			for (int j = 0; j < bot_ship.length; j++) 
				bot_ship[i][j].setName("0");
			
		//ship 5 
		for(int i = 3 ; i < 8; i++)
			bot_ship[i][3].setName("5");
		//ship 4
		for (int i = 1; i < 5; i++) {
			bot_ship[1][i].setName("4");
		}
		
		
		
		
		
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
	
//	public void 	
	
}
