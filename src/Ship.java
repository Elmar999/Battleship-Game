package src;


public class Ship {
	
	private int[] cells;
	private int ship_size ;
	private int start_x , start_y;
	
	Ship(int size){
		this.ship_size = size;
		cells = new int[ship_size];
	}
	
	
	public void setInit_x_y(int x , int y) {
		this.start_x = x ;
		this.start_y = y;
	}
	
	

}
