package src;

import javax.swing.SwingUtilities;

public class Battle_main_Bot {
	public static void main(String[] args)
	{
		SwingUtilities.invokeLater(new Runnable(){
			public void run() {
				
				/*we will pass number 0 to our constructor to launch
				 'game with bot' part in that constructor			
				 */
				
				BattleTest setShip_user = new BattleTest(0);  
								
	
			}
		});
	}
}
