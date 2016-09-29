import java.awt.Image;

import oop.ex2.GameGUI;


public class BasherSpaceShip extends SpaceShip {
	
	public void doAction(SpaceWars game){
		this.isShieldOn = false;
		this.getPhysics().move(true, NO_TURN);
		if (this.getPhysics().angleTo(game.getClosestShipTo(this).getPhysics()) < 0)
			this.getPhysics().move(true, RIGHT_TURN);
		if (this.getPhysics().angleTo(game.getClosestShipTo(this).getPhysics()) > 0)
			this.getPhysics().move(true, LEFT_TURN);
		if (this.getPhysics().distanceFrom(game.getClosestShipTo(this).getPhysics()) <= THREAT_DISTANCE)
			this.shieldOn();
		this.energyCharge();
		System.out.println(this.currentHealth);
	}
	
	public Image getImage(){
		if (this.isShieldOn == true)
			return GameGUI.ENEMY_SPACESHIP_IMAGE_SHIELD;
		else
			return GameGUI.ENEMY_SPACESHIP_IMAGE;
	}

}
