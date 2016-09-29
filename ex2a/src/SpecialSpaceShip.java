import java.awt.Image;

import oop.ex2.GameGUI;

/**
 * This class implements the special type of spaceship.
 * This ship is a demigod: it will chase and fire at you, and if close enough
 * it will try to bash you with its shields.
 * But the most interesting part: If you face it directly and look it in
 * the eye, it will empower, meaning it will teleport away and will regenerate
 *  all of its attributes. So, in order to beat this ship you will have to
 *  find a way to confront it without looking at it.
 *  @author Ori Broda
 */
public class SpecialSpaceShip extends SpaceShip {
	
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
		// Chase the closest ship.
		this.getPhysics().move(true, this.shouldTurn(game));
		// Prepare to bash, for the target is being approached.
		if (this.distanceToClosestShip(game) <= THREAT_DISTANCE)
			this.shieldOn();
		// Fire if the target is on sight.
		if (this.angleToClosestShip(game) < THREAT_ANGLE)
			this.fire(game);
		// Closest enemy ship is aiming at us. Empower.	
		if (Math.abs(game.getClosestShipTo(this).getPhysics().angleTo(this
				.getPhysics())) < THREAT_ANGLE){
			this.reset();
		}
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
