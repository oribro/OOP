import java.awt.Image;

import oop.ex2.GameGUI;


public class RunnerSpaceShip extends SpaceShip{
	
	
	public void doAction(SpaceWars game){
		/**
		this.getPhysics().move(true, NO_TURN);
		//Dodge the closest enemy ship
		if (this.getPhysics().angleTo(game.getClosestShipTo(this).getPhysics()) < 0)
			this.getPhysics().move(false, LEFT_TURN);
		if (this.getPhysics().angleTo(game.getClosestShipTo(this).getPhysics()) > 0)
			this.getPhysics().move(false, RIGHT_TURN);
		
		// The runner feels threatened, enemy ship too close.
		if (this.getPhysics().distanceFrom(game.getClosestShipTo(this).getPhysics()) < THREAT_DISTANCE || 
				Math.abs(this.getPhysics().angleTo(game.getClosestShipTo(this).getPhysics())) < THREAT_ANGLE)
			this.teleport();
		this.energyCharge();
		*/
		System.out.println(this.currentHealth);
		this.shieldOn();
	}
	
	public Image getImage(){
		return GameGUI.ENEMY_SPACESHIP_IMAGE;
	}

}
