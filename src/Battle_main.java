package src;

import java.util.concurrent.TimeUnit;

import javax.swing.SwingUtilities;

public class Battle_main {
	public static void main(String[] args)
	{
		int confirm = 0;
		
		SwingUtilities.invokeLater(new Runnable(){
			public void run() {
				BattleTest setShip_Server = new BattleTest();
				
				while(setShip_Server.getconfirm() != 1) {
					System.out.println("olmadi");
				}
				
//				BattleTest Attack = new BattleTest();
				
				
				
				
	
			}
		});
	}
}
	
	
	
	
	
	
	
	