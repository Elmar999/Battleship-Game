package src;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.concurrent.TimeUnit;

import javax.swing.JButton;


public class ButtonMouseListener implements MouseListener{
	private static int ass = 0;
	private static JButton[][] button;
	private int x , y;
	private int boat_number;
	private static int horizontal = 0;
	private static int vertical = 0;
	private static int counter = 0;
	private int confirmed = 0;
	static int finished = 0;
	static int count = 0;
	int confirm;
	
	public int check_if_finished(JButton[][] btn) {
		int count=0;
		for (int i = 0; i < btn.length; i++) {
			for (int j = 0; j < btn[i].length; j++) {
				if(!btn[i][j].getName().equals("0"))
					count++;
			}
		}
		return count;
	}
	
	ButtonMouseListener(JButton[][] btn , int x , int y , int confirm ){
		button = btn;
		this.x = x;
		this.y = y;
		confirm = 0;
	}
	
	
	public JButton[][] get_jbutton() {
		return button;
	}
	

	public ButtonMouseListener() {
		confirmed = 1;
	}

	public void print_matrix_values(JButton[][] btn) {
		for (int i = 0; i < btn.length; i++) {
			for (int j = 0; j < btn[i].length; j++) {
				System.out.print(btn[i][j].getName()+" ");
			}
			System.out.println();
		}
	}
	

	void show_hitted(JButton[][] button , int x , int y , int boat_number) {	
		if(boat_number != 5 && boat_number != 4 && boat_number != 3 && boat_number != 2) 
			return ;
		
		int key = 0;
		if( x == 0)  key = 1;
		else if( y == 0) key = 1;		
		
		//check_cell
		if(!button[x][y].getName().equals("0")) {
			System.out.println("cell is not empty"); return ; }
			
		if (key == 0) {  	
			if (check_boat_size(button, boat_number) == 1) {
				add_boat_to_cell(button,x ,y ,boat_number);
			}
//			print_matrix_values(button);

		}
	}
	
	
	public int check_boat_size(JButton[][] button , int boat_number) {
		int count = 0;
		for (int i = 0; i < button.length; i++) {
			for (int j = 0; j < button.length; j++) {
				if(button[i][j].getName().equals(String.valueOf(boat_number)))
					count++;
			}
		}
		
//		System.out.println(count);

		
		if(count%boat_number == 0) {
			counter = 0;
			horizontal = 0 ; vertical = 0;
		}
		
		
		if(count == boat_number*get_number_of_boat(boat_number)) 
			return 0;
		
		return 1;
		
	}
	
	
	public int get_number_of_boat(int boat_number) {
		if(boat_number == 5) 
			return 1; 
		else if(boat_number == 4)
			return 1;
		else if(boat_number == 3)
			return 2;
		else if(boat_number == 2)
			return 1;
	
		return -1;
		
	}
	
	
	public void add_boat_to_cell(JButton[][] btn , int x , int y , int boat_number) {
		if(counter == 0) {
				btn[x][y].setName(String.valueOf(boat_number));
				btn[x][y].setBackground(Color.BLACK);		
				counter++;
		}
		else if(counter == 1) {
			btn[x][y].setName(String.valueOf(boat_number));
			btn[x][y].setBackground(Color.BLACK);	counter++;
			//check direction of boat : horizontal or vertical
			if(check_H_or_V(btn ,x ,y) == 0) 		horizontal = 1;
			else if(check_H_or_V(btn ,x ,y) == 1)	vertical = 1;	
			counter++;
			return ;
		}
//		System.out.println("horizontal: " + horizontal + "\n" + "vertical: " + vertical);
		
		if(horizontal == 1) {
			if(check_H_or_V(btn ,x ,y) == 0) { 	
				btn[x][y].setName(String.valueOf(boat_number));
				btn[x][y].setBackground(Color.BLACK);
//				System.out.println("horizontal");
			}
		}
		else if(vertical == 1) {
			if(check_H_or_V(btn ,x ,y) == 1) {
				btn[x][y].setName(String.valueOf(boat_number));
				btn[x][y].setBackground(Color.BLACK);
//				System.out.println("vertical");
			}
		}
		
	}
	
		
	public int check_H_or_V(JButton[][] btn , int x, int y) {
		//check for vertical
		if( !(btn[x][y+1].getName().equals("0")) || !(btn[x][y-1].getName().equals("0"))  )
			return 1; 
		//check for horizontal
		if( !(btn[x+1][y].getName().equals("0")) || !(btn[x-1][y].getName().equals("0")) )
			return 0;
		
		return -1;
	}

	private int check_neighbours(JButton[][] btn ,int x , int y) {
		int diff = 0;
		if( (btn[x][y+1].getName().equals("0")) && (btn[x][y-1].getName().equals("0")) && (btn[x+1][y].getName().equals("0")) 	&& (btn[x-1][y].getName().equals("0")) )
				return 1;
		//if neighbours are not free( 0 )
		return 0;
	}
	


	@Override
	public void mouseClicked(MouseEvent e ) {
		int nmb = 6;
		JButton[][] button_rival = new JButton[11][11];
		BoatMouseListener btn = new BoatMouseListener();
		int bt = btn.get_boat_number();
			if(confirmed == 1) {
				System.out.println("kecek bota?");
			}
		else {
			System.out.println(bt);
			show_hitted(button, x, y , bt);
		}
	}
	
	
	
	public void Launch_boat() {
		button[5][5].setName("5");
	}
	
	public void set_boat_number(int boat_number) {
		this.boat_number = boat_number;
	}
	
	public int get_boat_number() {
		return boat_number;
	}

	
	
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}



	
	
	

}
