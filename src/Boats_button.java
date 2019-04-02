package src;

import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;;
public class Boats_button extends JPanel{


	
	public Boats_button() {
		JButton[] Boat = new JButton[5];
		JButton btn = new JButton("CONFIRM");
//		btn.setName("CONFIRM");
		create_boat_buttons(Boat);
		add(btn);  btn.addMouseListener(new ButtonMouseListener());
	}
	

	
	public void create_boat_buttons(JButton[] Boat ) {
			int boat_number = 2;
 		
		for (int i = Boat.length - 1; i > 0; i--) {		
			Boat[i] = new JButton(String.valueOf(boat_number)); 
			add(Boat[i]);  Boat[i].addMouseListener( new BoatMouseListener(boat_number));
			boat_number++;
		}
	}

	
}
