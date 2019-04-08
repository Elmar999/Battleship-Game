package src;

import javax.swing.SwingUtilities;

public class Battle_main_Bot {
	public static void main(String[] args)
	{
		int bot = 0;
		SwingUtilities.invokeLater(new Runnable(){
			public void run() {
				
				BattleTest setShip_user = new BattleTest(bot);
								
	
			}
		});
	}
}
