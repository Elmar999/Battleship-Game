package src;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.TimeUnit;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
public class BattleTest extends JFrame {
		
	JButton[][] user1 = new JButton[11][11];
	JButton[][] user2 = new JButton[11][11];
	JButton[][] bot2 = new JButton[11][11];
	private int confirm = 0;
	private int rival ;
	int sendMessage; static int count1 = 0 , count2 = 0;
	ServerSocket sersock ;  Socket sock;  BufferedReader keyRead;
	OutputStream ostream;	PrintWriter pwrite; InputStream istream;
	BufferedReader receiveRead;
	String receiveMessage ;

	

	
	public BattleTest(int rival) {
		this.rival = rival;
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
		btn.addActionListener(e -> { confirm = 1 ; panel2.setVisible(true); toolbar.setVisible(false);
			if(rival == 0)
				playGameBot(bot2);
			else if(rival == 1) {
				try {
					launch_user1_server(user2);
//					launch_user1_game();
					System.out.println("serveri qurdum");
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			else if(rival == 2)
				try {
					launch_user2_client(user1);
//					launch_user2_game();
					System.out.println("client quruldu");
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			
		});
		
		if(rival == 0) {
			fill_grid(user1, panel1);
			fill_grid(bot2, panel2);
		}
		else if(rival == 1) {
			fill_grid(user1, panel1);
			fill_grid(user2, panel2);
		}
		else if(rival == 2) {
			fill_grid(user2, panel1);
			fill_grid(user1, panel2); 
		}
//		add(panel);
		
		
		if (rival == 0) {
			for (int i = 0; i < user1.length; i++) {
				for (int j = 0; j < user1.length; j++) {
					user1[i][j].addMouseListener(new ButtonMouseListener(user1, i, j, confirm ));
					user1[i][j].setName(String.valueOf(0));
				}
			}
		}
		else if(rival == 1) {
			for (int i = 0; i < user1.length; i++) {
				for (int j = 0; j < user1.length; j++) {
					user1[i][j].addMouseListener(new ButtonMouseListener(user1, i, j, confirm ));
					user1[i][j].setName(String.valueOf(0));
				}
			}
		}
		else if(rival == 2) {
			for (int i = 0; i < user2.length; i++) {
				for (int j = 0; j < user2.length; j++) {
					user2[i][j].addMouseListener(new ButtonMouseListener(user2, i, j, confirm ));
					user2[i][j].setName(String.valueOf(0));
				}
			}
		}
//		print_matrix_values(button);
		
		
//		System.out.println("oldu");
		
		
	}
	
	private void launch_user1_game() throws IOException, InterruptedException {
		while(true)
	      {	    	  
	    	System.out.println("user1 gozduyur");
		    InputStream istream = sock.getInputStream();
	    	if(receiveRead == null)
	    		receiveRead = new BufferedReader(new InputStreamReader(istream));
	        if((receiveMessage = receiveRead.readLine()) != null)  
	        {
	           System.out.println(receiveMessage);         
	        }
	        for (int i = 0; i < user2.length; i++) {
				for (int j = 0; j < user2.length; j++) {
					user2[i][j].putClientProperty("row", i);
					user2[i][j].putClientProperty("column", j);
					user2[i][j].addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							JButton but = (JButton) e.getSource();
							int x = (int) but.getClientProperty("row");
							int y = (int) but.getClientProperty("column");
							sendMessage = x;
						    System.out.println("user1 sends a message: " + sendMessage);
						}
					});
				}
			}
//	        sendMessage = keyRead.readLine(); 
	       
	        pwrite.println(sendMessage);             
	        pwrite.flush();
	      }        
		
	}

	private void launch_user2_game() throws IOException {

	     String receiveMessage;

	     while(true){
	    	 System.out.println("user2 gozduyur");
	    	 for (int i = 0; i < user1.length; i++) {
					for (int j = 0; j < user1.length; j++) {
						user1[i][j].putClientProperty("row", i);
						user1[i][j].putClientProperty("column", j);
						user1[i][j].addActionListener(new ActionListener() {
							@Override
							public void actionPerformed(ActionEvent e) {
								JButton but = (JButton) e.getSource();
								int x = (int) but.getClientProperty("row");
								int y = (int) but.getClientProperty("column");
								sendMessage = x;
							}
						});
					 }
	    	 }
	//	        sendMessage = keyRead.readLine();  // keyboard reading
		    	 System.out.println("user2 gonder\n");
			     System.out.println("message sent: " + sendMessage);
		        pwrite.println(sendMessage);       // sending to server
		        pwrite.flush();                    // flush the data
		        if((receiveMessage = receiveRead.readLine()) != null) //receive from server
		        {
		            System.out.println(receiveMessage); // displaying at DOS prompt
		        }         
	      }    		
	}

	private void launch_user1_server(JButton[][] user2) throws IOException {
		ServerSocket sersock = new ServerSocket(3999);
	      System.out.println("Server  ready for chatting");
	      Socket sock = sersock.accept( );                          
	      // reading from keyboard (keyRead object)
	      BufferedReader keyRead = new BufferedReader(new InputStreamReader(System.in));
		  // sending to client (pwrite object)
	      OutputStream ostream = sock.getOutputStream(); 
	      PrintWriter pwrite = new PrintWriter(ostream, true);
	 
	      // receiving from server ( receiveRead  object)
	      InputStream istream = sock.getInputStream();
	      BufferedReader receiveRead = new BufferedReader(new InputStreamReader(istream));
//	      System.out.println("Buffer created");
	      String receiveMessage;
	      if(count1 == 0) {
		    if((receiveMessage = receiveRead.readLine()) != null) { //receive from server
			   System.out.println("server received a message: " + receiveMessage); // displaying at DOS prompt
			}   
		    count1++;
		  }
	   }   //end of function                 
	
	
	
	private void launch_user2_client(JButton[][] user1) throws IOException {
		Socket sock = new Socket("127.0.0.1", 3999);
	     // reading from keyboard (keyRead object)
	     BufferedReader keyRead = new BufferedReader(new InputStreamReader(System.in));
	     // sending to client (pwrite object)
	     OutputStream ostream = sock.getOutputStream(); 
	     PrintWriter pwrite = new PrintWriter(ostream, true);
	     
	     // receiving from server ( receiveRead  object)
	     InputStream istream = sock.getInputStream();
	     BufferedReader receiveRead = new BufferedReader(new InputStreamReader(istream));
	 
	     System.out.println("Client-Server connection is ready");
	 
	     String receiveMessage;
	     sendMessage = 10;
	     
	     
	     if(count2 == 0) {
	    	  pwrite.println(sendMessage);       // sending to server
		      pwrite.flush();                    // flush the data
	    	 count2++;
	     }
//	     }
	}
	

	private void playGameBot(JButton[][] btn) {
		// TODO Auto-generated method stub
		fill_bot(btn);	
//		
		for (int i = 0; i < btn.length; i++) {
			for (int j = 0; j < btn.length; j++) {
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
							playBot(user1);
					}
					
						private void playBot(JButton[][] user1) {
							int x = (int)(Math.random() * 10 + 1);
							int y = (int)(Math.random() * 10 + 1);
														
							if(user1[x][y].getName().equals("0")) {
								user1[x][y].setName("1");
								sleep(2);
								user1[x][y].setBackground(Color.white);
							}
							else if(!user1[x][y].getName().equals("0") && !user1[x][y].getName().equals("1")) {
								user1[x][y].setBackground(Color.red);
								sleep(2);
								playBot(user1);
							}
							else if(user1[x][y].getName().equals("1")) {
								sleep(2);
								playBot(user1);
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
