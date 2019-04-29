package src;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;

public class BoatMouseListener implements MouseListener {

	private int boat_number = 0;
	
	private static int  btn = 0;
	private static int count = 0;
	private JButton[][] button;
	

	
	
	BoatMouseListener(int boat_number) {
		this.boat_number = boat_number;
	}
	
	
	public BoatMouseListener() {
		// TODO Auto-generated constructor stub
	}


	@Override
	public void mouseClicked(MouseEvent e) {
		/* mouse click used for selecting a exact number of ship */
		
		
		set_boat_number(boat_number);
		System.out.print(boat_number + " is selected\n");
	}
	
	public void set_boat_number(int boat_number) {
		this.btn = boat_number;
	}
	
	public int get_boat_number() {
		return this.btn;
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
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
