import java.awt.Image;

import oop.ex2.GameGUI;
import java.util.Random;


public class DrunkardSpaceShip extends SpaceShip {
	
	private static final int RANDOM_MOVE_RANGE = 3;
	private static final int RANDOM_FIRE_RANGE = 20;
	private static final int RANDOM_FIRE_INDICATOR = 5;
	
	public void doAction(SpaceWars game){
		Random rand = new Random();
		boolean accelerate = rand.nextBoolean();
		int move = rand.nextInt(RANDOM_MOVE_RANGE) - 1;
		int shouldFire = rand.nextInt(RANDOM_FIRE_RANGE);
		this.getPhysics().move(accelerate, move);
		if (shouldFire == RANDOM_FIRE_INDICATOR)
			this.fire(game);
		this.energyCharge();
	}
	
	public Image getImage(){
		return GameGUI.ENEMY_SPACESHIP_IMAGE;
	}
}
