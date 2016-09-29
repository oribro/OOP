import java.awt.Image;

import oop.ex2.GameGUI;


public class AggressiveSpaceShip extends SpaceShip{
	
	public void doAction(SpaceWars game){
		this.getPhysics().move(true, NO_TURN);
		if (this.getPhysics().angleTo(game.getClosestShipTo(this).getPhysics()) < 0)
			this.getPhysics().move(false, RIGHT_TURN);
		if (this.getPhysics().angleTo(game.getClosestShipTo(this).getPhysics()) > 0)
			this.getPhysics().move(false, LEFT_TURN);
		if (Math.abs(this.getPhysics().angleTo(game.getClosestShipTo(this).getPhysics())) < THREAT_ANGLE)
			this.fire(game);
		this.energyCharge();
			
	}
	
	public Image getImage(){
		return GameGUI.ENEMY_SPACESHIP_IMAGE;
	}

}
