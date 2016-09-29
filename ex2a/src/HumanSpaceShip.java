import java.awt.Image;

import oop.ex2.*;

/**
 * This class implements the human type of spaceship.
 * The spaceship is controlled by the user. The keys are: left-arrow and
 *  right-arrow to turn, up-arrow to accelerate, 'd' to fire a shot, 's'
 *  to turn on the shield, 'a' to teleport.  We assume there will be
 *  at most one human controlled ship in a game
 * @author Ori Broda
 */

public class HumanSpaceShip extends SpaceShip {
	/**
	 * Implementation of the abstract method shouldTurn.
     * Indicates left turn if the left arrow in the keyboard is pressed,
     * indicates right if the right arrow is pressed, or neither direction.
     * @param game the game object to which this ship belongs.
     * @return the direction in which the spaceship will turn.
     */
	protected int shouldTurn(SpaceWars game){
		if (game.getGUI().isLeftPressed() == true)
			return LEFT_TURN;
		if (game.getGUI().isRightPressed() == true)
			return RIGHT_TURN;
		else
			return NO_TURN;
	}
	
     // Indicates to accelerate if up arrow is pressed, otherwise
     // indicates to move in a constant speed.
     // @param game the game object to which this ship belongs.
     // @return true if the order is to accelerate, false otherwise.
     
	private boolean shouldAccelerate(SpaceWars game){
		if (game.getGUI().isUpPressed())
			return true;
		else
			return false;
	}
	  /**
     * Does the actions of this ship for this round. 
     * This is called once per round by the SpaceWars game driver.
     * 
     * @param game the game object to which this ship belongs.
     */
	public void doAction(SpaceWars game)
		{
			// Assures that the shield is off at the beginning of the round.
			this.isShieldOn = false;
			// Performs the actions according to the user's input, see
			// class description above for details.
			if (game.getGUI().isTeleportPressed() == true)
				this.teleport();
			this.getPhysics().move(this.shouldAccelerate(game),
					this.shouldTurn(game));
			if (game.getGUI().isShieldsPressed() == true)
				this.shieldOn();
			if (game.getGUI().isShotPressed() == true)
				this.fire(game);
			// Ends the round with the needed updates.
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
			return GameGUI.SPACESHIP_IMAGE_SHIELD;
		else
			return GameGUI.SPACESHIP_IMAGE;
	}
	

}
