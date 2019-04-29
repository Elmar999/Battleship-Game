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
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.StringTokenizer;
import java.util.concurrent.TimeUnit;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
public class BattleTest extends JFrame {
		
	JButton[][] user1 = new JButton[11][11];
	JButton[][] user2 = new JButton[11][11];
	JButton[][] bot2 = new JButton[11][11];
	private int confirm = 0; // used for confirmation button
	private int rival ; // to see if it is bot , or user1 or user2
	static int first_step = 0; //used to send first message via socket
	static int tmp = 0; static byte[][] array_user1; //used to convert jbutton array to byte array 
	
	public BattleTest(int rival) {
		this.rival = rival;
		setVisible(true);  setSize(600, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
				
		
		JPanel panel = new JPanel();
		setContentPane(panel);
		
		panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
		
		JPanel panel1 = new JPanel(); 
		panel1.setLayout(new GridLayout(11,11,1,1));
				
		JPanel panel2 = new JPanel();
		panel2.setLayout(new GridLayout(11,11,1,1));
		JButton btn = new JButton("CONFIRM");
		Boats_button toolbar = new Boats_button(btn);
		
		panel.add(panel1);
		panel.add(panel2);
		panel2.setVisible(true);
		panel.add(toolbar, BorderLayout.SOUTH);
		
		
		
		btn.addActionListener(e -> {  toolbar.setVisible(false);
			if(rival == 0)
				playGameBot(bot2);
			else if(rival == 1) {
				try {
					launch_user1_server(user2);
					launch_user1_game();
					System.out.println("serveri established");
				} catch (IOException | InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			else if(rival == 2)
				try {
					launch_user2_client(user1);
					launch_user2_game();
					System.out.println("client created");
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
	}
	
	private void launch_user1_game() throws IOException, InterruptedException {
		
		 	 System.out.println("user1_game launched\n");
		     Socket sock = new Socket("127.0.0.1", 3999);
		     System.out.println("game runs\n");
		     BufferedReader keyRead = new BufferedReader(new InputStreamReader(System.in));
		     OutputStream ostream = sock.getOutputStream(); 
		     ObjectOutputStream os = new ObjectOutputStream(sock.getOutputStream());
		     ObjectInputStream is = new ObjectInputStream(sock.getInputStream());
		     PrintWriter pwrite = new PrintWriter(ostream, true);
		     InputStream istream = sock.getInputStream();
		     BufferedReader receiveRead = new BufferedReader(new InputStreamReader(istream));	

		 String sendMessage = " ";
	     int count = 0;
	     if(tmp == 0) {
	    	 byte[][] array = new byte[11][11];
	    	 for (int i = 1; i < array.length; i++) {
				for (int j = 0; j < array.length; j++) {
					array[i][j] = (byte)Integer.parseInt(user1[i][j].getName());
				}
			}
	    	 os.writeObject(array);
	    	 tmp = 1;
	     }
	     
	     try {
			array_user1 = (byte[][])is.readObject();
		} catch (ClassNotFoundException e2) {
			e2.printStackTrace();
		}
	     
		while(true)
	      {	    	
			 
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
							
						    StringTokenizer st ;
							String receiveMessage;
							int index_x = 0 , index_y = 0;
							
							
						    
						    try {
						    				if (first_step == 0) {
						    					send_message(x , y);
						    					first_step = 1;
						    				}
						    				if(array_user1[x][y] != 0) {
												user2[x][y].setBackground(Color.red);
											}
						    				else if(array_user1[x][y] == 0) {
						    					user2[x][y].setBackground(Color.black);
						    				}
										    if((receiveMessage = receiveRead.readLine()) != null) {							
													
													System.out.println("user1 is received a msg: " + receiveMessage); 
													st = new StringTokenizer(receiveMessage," "); 
												    index_x = Integer.parseInt(st.nextToken());  
													index_y = Integer.parseInt(st.nextToken());  
													if(!user1[index_x][index_y].getName().equals("0")) {
												        user1[index_x][index_y].setBackground(Color.red);
													}
													  send_message(x , y);
												}
											} catch (IOException e1) {
												// TODO Auto-generated catch block
												e1.printStackTrace();
											}
								 
						}

						private void send_message(int x, int y) {
							int sendMessageX = x;
							int sendMessageY = y;
							String sendMessage = " ";
							
							sendMessage = Integer.toString(sendMessageX);
							sendMessage+= " ";
							sendMessage += Integer.toString(sendMessageY);

						    System.out.println("user1 sends a message: " + sendMessage);
							pwrite.println(sendMessage);             
							pwrite.flush();
							
						}
					});
				}
			}      
	       
	        count++;
	        if(count == 1) {
	        	
	        	break;
	        }
	      }        
		
	}

	private void launch_user2_game() throws IOException {

	   
	     ServerSocket sersock = new ServerSocket(3999);
		 Socket sock = sersock.accept( );                          
		 BufferedReader keyRead = new BufferedReader(new InputStreamReader(System.in));
	     OutputStream ostream = sock.getOutputStream(); 
	     ObjectInputStream is = new ObjectInputStream(sock.getInputStream());
	     ObjectOutputStream os = new ObjectOutputStream(sock.getOutputStream());
	     PrintWriter pwrite = new PrintWriter(ostream, true);
	     InputStream istream = sock.getInputStream();
	     BufferedReader receiveRead = new BufferedReader(new InputStreamReader(istream));

	     try {
			array_user1 = (byte[][])is.readObject();
		} catch (ClassNotFoundException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
	     
	     if(tmp == 0) {
	    	 byte[][] array = new byte[11][11];
	    	 for (int i = 1; i < array.length; i++) {
				for (int j = 0; j < array.length; j++) {
					array[i][j] = (byte)Integer.parseInt(user2[i][j].getName());
				}
			}
	    	 os.writeObject(array);
	    	 tmp = 1;
	     }
	     
	     while(true){
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
								String sendMessage = " ";
								String receiveMessage = " ";
							
								 StringTokenizer st ;
								 int index_x = 0 , index_y = 0;
								
										
											try {
												if(array_user1[x][y] != 0) {
													user1[x][y].setBackground(Color.red);
												}
												else user1[x][y].setBackground(Color.black);
												
												if((receiveMessage = receiveRead.readLine()) != null) 
												{
													System.out.println("user2 is received a msg: " + receiveMessage); 
													st = new StringTokenizer(receiveMessage," "); 
													index_x = Integer.parseInt(st.nextToken());  
													index_y = Integer.parseInt(st.nextToken());  
												}  
												  if(!user2[index_x][index_y].getName().equals("0")) {
													  user2[index_x][index_y].setBackground(Color.red);
												  }
												  send_message(x, y);
												
												
											} catch (IOException e1) {
												// TODO Auto-generated catch block
												e1.printStackTrace();
											}
							}

							private void send_message(int x, int y) {
								int sendMessageX = x;
								int sendMessageY = y;
								String sendMessage = " ";
								
								sendMessage = Integer.toString(sendMessageX);
								sendMessage+= " ";
								sendMessage += Integer.toString(sendMessageY);
							    System.out.println("user2 sends a message: " + sendMessage);
//								
								pwrite.println(sendMessage);             
								pwrite.flush();
								
							}
						});
					 }
	    	 }
	    	 
	    	 int count=0;
		     count++;
		     if(count == 1)
		    	 break;
	      }    		
	}

	private void launch_user1_server(JButton[][] user2) throws IOException {
		ServerSocket sersock = new ServerSocket(3999);
	      System.out.println("Server  ready for chatting");
	      Socket sock = sersock.accept();                          
	      BufferedReader keyRead = new BufferedReader(new InputStreamReader(System.in));
	      OutputStream ostream = sock.getOutputStream(); 
	      PrintWriter pwrite = new PrintWriter(ostream, true);
	      InputStream istream = sock.getInputStream();
	      BufferedReader receiveRead = new BufferedReader(new InputStreamReader(istream));
	      String receiveMessage;
	      System.out.println("waiting for response in user1\n");
	      
	      if((receiveMessage = receiveRead.readLine()) != null) { //receive from server
			 System.out.println("server received a message: " + receiveMessage); // displaying at DOS prompt
		  }   
	      
	      System.out.println("response arrived in user1\n");
		  
	      
	      sersock.close();
	      sock.close();
	      
	   }   //end of function                 
	
	
	
	private void launch_user2_client(JButton[][] user1) throws IOException {
		 Socket sock = new Socket("127.0.0.1", 3999);
	     BufferedReader keyRead = new BufferedReader(new InputStreamReader(System.in));
	     OutputStream ostream = sock.getOutputStream(); 
	     PrintWriter pwrite = new PrintWriter(ostream, true);
	     
	     InputStream istream = sock.getInputStream();
	     BufferedReader receiveRead = new BufferedReader(new InputStreamReader(istream));
	 
	     System.out.println("Client-Server connection is ready");
	 
	     String receiveMessage;
	     int sendMessage = 5;
    	 pwrite.println(sendMessage);       // sending to server
         pwrite.flush();                   // flush the data
         sock.close();
		

	}
	

	private void playGameBot(JButton[][] btn) {
		fill_bot(btn);			
		for (int i = 0; i < btn.length; i++) {
			for (int j = 0; j < btn.length; j++) {
				btn[i][j].putClientProperty("row", i);
				btn[i][j].putClientProperty("column", j);
				btn[i][j].addActionListener(new actionlistener() {
					public void actionPerformed(ActionEvent e) {
						JButton but = (JButton) e.getSource();
						int x = (int) but.getClientProperty("row");
						int y = (int) but.getClientProperty("column");
						System.out.println(x + " " + y);
						if(!btn[x][y].getName().equals("0")) btn[x][y].setBackground(Color.red);
						else {
							playBot(user1);
							btn[x][y].setBackground(Color.black);
						}
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
