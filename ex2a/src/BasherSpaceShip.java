import java.awt.Image;

import oop.ex2.GameGUI;

/**
 * This class implements the basher type of spaceship.
 * This ship attempts to collide with other ships. It will always accelerate,
 *  and will constantly turn towards the closest ship. If it gets within a
 *  distance of 0.2 units from another ship, it will attempt to turn on
 *  its shields.
 *  @author Ori Broda
 */
public class BasherSpaceShip extends SpaceShip {
	/**
	 * Implementation of the abstract method shouldTurn.
     * Indicates left turn if the closest ship is to the left,
     * indicates right turn if the closest ship is to the right,
     * or don't turn if it's up ahead. 
     * @param game the game object to which this ship belongs.
     * @return the direction in which the spaceship will turn.
     */
	protected int shouldTurn(SpaceWars game){
		if (this.angleToClosestShip(game) < 0)
			return RIGHT_TURN;
		if (this.angleToClosestShip(game) > 0)
			return LEFT_TURN;
		else
			return NO_TURN;
	}
	   /**
     * Does the actions of this ship for this round. 
     * This is called once per round by the SpaceWars game driver.
     * 
     * @param game the game object to which this ship belongs.
     */
	public void doAction(SpaceWars game){
		this.isShieldOn = false;
		// Attempt to collide with the closest enemy ship.
		this.getPhysics().move(true, this.shouldTurn(game));
		// We are close enough. Prepare to bash.
		if (this.distanceToClosestShip(game) <= THREAT_DISTANCE)
			this.shieldOn();
		this.energyCharge();
		this.roundCounter++;
	}
	
	 /**
     * Gets the image of this ship. This method should return the image of the
     * ship with or without the shield. This will be displayed on the GUI at
     * the end of the round.
     * 
     * @return the image of this ship.
     */
	public Image getImage(){
		if (this.isShieldOn == true)
			return GameGUI.ENEMY_SPACESHIP_IMAGE_SHIELD;
		else
			return GameGUI.ENEMY_SPACESHIP_IMAGE;
	}

}
