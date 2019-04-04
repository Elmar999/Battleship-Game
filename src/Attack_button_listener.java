package src;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;

public class Attack_button_listener implements MouseListener {

	
	private int x = 0 , y = 0 ;
	private JButton[][] btn_rival;
	private JButton[][] btn_self;
	public Attack_button_listener(JButton[][] btn_rival ,int  x , int y , JButton[][] btn_self ) {
		this.x = x;
		this.y = y;
		this.btn_self = btn_self;
		this.btn_rival = btn_rival;
		
	}
	
	
	public int get_X(){
		return this.x;
	}
	
	public int get_Y() {
		return this.y;
	}
	
	
	public void mouseClicked(MouseEvent e) {
		System.out.println(this.x + " " + this.y);
		if(!btn_rival[x][y].getName().equals("0")) {
			btn_rival[x][y].setBackground(Color.red);
		}
		
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
