package src;

import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;;
public class Boats_button extends JPanel{


	
	public Boats_button() {
		JButton[] Boat = new JButton[5];
		create_boat_buttons(Boat);
	}
	

	
	public void create_boat_buttons(JButton[] Boat) {
		String bt_name = " ";	int boat_number = 5;
 		
		for (int i = 1; i < Boat.length; i++) {		
			Boat[i] = new JButton(bt_name.valueOf(boat_number)); 
			add(Boat[i]);  Boat[i].addMouseListener( new BoatMouseListener(boat_number));
			boat_number--;
		}
	}

	
}
