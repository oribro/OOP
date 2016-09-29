import java.awt.Image;

import oop.ex2.GameGUI;
import java.util.Random;

/**
 * This class implements the drunkard type of spaceship.
 * This ship wanders aimlessly around the space since the driver
 * is drunk. This ship performs random actions.
 * Assuming that the nextInt() method of the random object creates
 * a uniform distribution, we have:
 * 33% chance to turn left or right.
 * 50% chance to accelerate.
 * 20% chance for the shield to be turned on for j number of rounds, 
 * j between 0 and 4
 * 5% chance to fire a shot.
 *  @author Ori Broda
 */

public class DrunkardSpaceShip extends SpaceShip {
	// Random constants to make the ship look drunk.
	private static final int RANDOM_TURN_RANGE = 3;
	private static final int RANDOM_FIRE_RANGE = 20;
	private static final int RANDOM_SHIELD_ENABLE = 10;
	private static final int RANDOM_SHIELD_DISABLE = 5;
	private static final int RANDOM_FIRE_INDICATOR = 5;
	// The random object we will use in this class.
	private Random rand;
	
	/**
	* Constructor.
	* Used to initialize the random object.
	*/
	public DrunkardSpaceShip(){
		super();
		this.rand = new Random();
	}
	/**
	 * Implementation of the abstract method shouldTurn.
     * The ship has 33% chance to turn in either direction (see above).
     * @param game the game object to which this ship belongs.
     * @return the direction in which the spaceship will turn.
     */
	protected int shouldTurn(SpaceWars game){
		return this.rand.nextInt(RANDOM_TURN_RANGE) - 1;
	}
	
	
	//  The ship has 50% chance to accelerate.
	// @param game the game object to which this ship belongs.
	//  @return true to accelerate, false to move in constant speed, in
	//  a random fashion.
	 
	private boolean shouldAccelerate(SpaceWars game){
		return this.rand.nextBoolean();
	}
	
	 // Turns the shields on for 0 to 4 rounds if enough time passed.
	  //@param game the game object to which this ship belongs.
	 
	private void shouldShield(SpaceWars game){
		// The cooldown period is over.
		if (this.roundCounter > RANDOM_SHIELD_ENABLE){
			this.shieldOn();
			this.roundCounter = 0;
		}
	}
	
	 // Attempt to fire a shot with 5% chance.
	 //@param game the game object to which this ship belongs.
	 
	private void shouldFire(SpaceWars game){
		int fire = this.rand.nextInt(RANDOM_FIRE_RANGE);
		if (fire == RANDOM_FIRE_INDICATOR)
			this.fire(game);
	}
	
	  /**
     * Does the actions of this ship for this round. 
     * This is called once per round by the SpaceWars game driver.
     * 
     * @param game the game object to which this ship belongs.
     */
	public void doAction(SpaceWars game){
		// Turns the shield off for 6 to 10 rounds.
		if (this.roundCounter == this.rand.nextInt(RANDOM_SHIELD_DISABLE))
			this.isShieldOn = false;
		// Move the ship in a random fashion.
		this.getPhysics().move(this.shouldAccelerate(game),
				this.shouldTurn(game));
		this.shouldShield(game);
		this.shouldFire(game);
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
