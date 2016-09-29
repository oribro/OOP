import java.awt.Image;

import oop.ex2.GameGUI;

/**
 * This class implements the runner type of spaceship.
 * This spaceship attempts to run away from the fight. It will always
 *  accelerate, and will constantly turn away from the closest ship.
 *   If the nearest ship is closer than 0.2 units, and if its angle
 *  to the Runner is less than 0.2 radians, the Runner feels threatened 
 *  and will attempt to teleport.
 * @author Ori Broda
 */
public class RunnerSpaceShip extends SpaceShip{
	/**
	 * Implementation of the abstract method shouldTurn.
     * Indicates left turn if the closest ship is to the right,
     * indicates right turn if the closest ship is to the left. 
     * @param game the game object to which this ship belongs.
     * @return the direction in which the spaceship will turn.
     */
	protected int shouldTurn(SpaceWars game){
		if (this.angleToClosestShip(game) < 0)
			return LEFT_TURN;
		else
			return RIGHT_TURN;
	}
	  /**
     * Does the actions of this ship for this round. 
     * This is called once per round by the SpaceWars game driver.
     * 
     * @param game the game object to which this ship belongs.
     */
	public void doAction(SpaceWars game){
		
		// The runner feels threatened, enemy ship too close.
		if (this.distanceToClosestShip(game) < THREAT_DISTANCE &&  
			Math.abs(this.angleToClosestShip(game)) < THREAT_ANGLE)
			this.teleport();
		// Dodge enemy closest ship.
		this.getPhysics().move(true, this.shouldTurn(game));
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
		return GameGUI.ENEMY_SPACESHIP_IMAGE;
	}

}
