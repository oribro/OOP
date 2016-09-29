import java.awt.Image;

import oop.ex2.GameGUI;

/**
 * This class implements the aggressive type of spaceship.
 * This ship pursues other ships and tries to fire at them. It will
 *  always accelerate, and turn towards the nearest ship. When its angle
 *  to the nearest ship is less than 0.2 radians, it will try to fire.
 *  @author Ori Broda
 */
public class AggressiveSpaceShip extends SpaceShip{
	
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
		
		// Chase the target.
		this.getPhysics().move(true, shouldTurn(game));
		// Target is close, attempt to fire at them.
		if (Math.abs(this.angleToClosestShip(game)) < THREAT_ANGLE)
			this.fire(game);
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
