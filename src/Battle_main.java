package src;

import javax.swing.SwingUtilities;

public class Battle_main {
	public static void main(String[] args)
	{
		
		SwingUtilities.invokeLater(new Runnable(){
			public void run() {
				BattleTest setShip = new BattleTest();
//				BattleTest Attack = new BattleTest();
				
				if(setShip.finshed() != 1) {
					System.out.println(setShip.finshed()+ "finished ");
				}
				
				System.out.println("siuuuuuuuuuuuuuuuuuuu");
				
				
				for (int i = 1; i < 11; i++) {
					for (int j = 1; j < 11; j++) {
						setShip.get_bomb(i, j);
					}
				}
	
			}
		});
	}
}
	
	
	
	
	
	
	
	