import java.awt.Image;
import oop.ex2.*;

/**
 * The API spaceships need to implement for the SpaceWars game. 
 * This class is an abstract class of spaceships. It defines a general
 * spaceship with general properties. each of the 6 different types
 * of spaceships will inherit from this class.
 * @author Ori Broda
 */
public abstract class SpaceShip{
	// The amount of energy each spaceship starts with.
	protected final static int INITIAL_ENERGY_LEVEL = 200;
	// The energy cost of teleportation.
	protected final static int ENERGY_TELEPORTATION_REDUCTION = 150;
	// The amount of health each spaceship starts with.
	protected final static int INITIAL_HEALTH_LEVEL = 20;
	// The amount of energy that will be added to the current energy
	// level upon colliding with another ship while the shields are on.
	protected final static int ENERGY_BASHING_INCREASE = 20;
	// The energy cost of firing a shot with the spaceship.
	protected final static int ENERGY_FIRE_REDUCTION = 20;
	// The amount of energy that will be reduced from the maximal energy
	// upon colliding with another ship while the shields are off.
	protected final static int HIT_REDUCTION = 10;
	// The energy cost of enabling the shields.
	protected final static int ENERGY_SHIELD_REDUCTION = 3;
	// The amount of health that is lost upon getting hit while the 
	// shields are off.
	protected final static int HEALTH_REDUCTION = 1;
	protected final static int LOWEST_ENERGY_LEVEL = 0;
	protected final static int LOWEST_HEALTH_LEVEL = 0;
	// Spaceship turn indicators.
	protected static final int NO_TURN = 0;
	protected static final int RIGHT_TURN = -1;
	protected static final int LEFT_TURN = 1;
	// Hostile range for the spaceship.
	protected static final double THREAT_DISTANCE = 0.2;
	protected static final double THREAT_ANGLE = 0.2;
	// The amount of energy the spaceship gains per round.
	protected static final int ENERGY_ROUND_INCREASE = 1;
	// The number of rounds we wait until the guns can be used again.
	protected static final int ROUND_FIRE_COOLDOWN = 8;
	//  The physics object that controls this ship.
	protected SpaceShipPhysics shipPhysics;
	// Ship attributes.
	protected int maximalEnergy;
	protected int currentEnergy;
	protected int currentHealth;
	// A flag to indicate if the shield is enabled or disabled.
	protected boolean isShieldOn;
	// Counts the number of rounds passed = number of calls to doAction(game).
	protected int roundCounter;
	
	/**
	*
	* SpaceShip Constructor.
	*/
	
	public SpaceShip(){
		this.shipPhysics = new SpaceShipPhysics(); 
		this.maximalEnergy = INITIAL_ENERGY_LEVEL; 
		this.currentEnergy = INITIAL_ENERGY_LEVEL;
		this.currentHealth = INITIAL_HEALTH_LEVEL;
		this.isShieldOn = false;
		this.roundCounter = 0;
	}
    /**
     * Does the actions of this ship for this round. 
     * This is called once per round by the SpaceWars game driver.
     * 
     * @param game the game object to which this ship belongs.
     */
    public abstract void doAction(SpaceWars game);


    /**
     * This method is called every time a collision with this ship occurs 
     */
    public void collidedWithAnotherShip(){
    	//The ship got hit.
    	this.gotHit();
    	//The case of bashing.
    	if (this.isShieldOn == true){
    		this.maximalEnergy += ENERGY_BASHING_INCREASE;
    		this.currentEnergy += ENERGY_BASHING_INCREASE;
    	}
    }

    /** 
     * This method is called whenever a ship has died. It resets the ship's 
     * attributes, and starts it at a new random position.
     */
    public void reset(){
    	this.maximalEnergy = INITIAL_ENERGY_LEVEL;
    	this.currentEnergy = INITIAL_ENERGY_LEVEL;
    	this.currentHealth = INITIAL_HEALTH_LEVEL;
    	this.shipPhysics = new SpaceShipPhysics();
    }

    /**
     * Checks if this ship is dead.
     * 
     * @return true if the ship is dead. false otherwise.
     */
    public boolean isDead() {
    	if (this.currentHealth == LOWEST_HEALTH_LEVEL)
    		return true;
    	else
    		return false;
    }

    /**
     * Gets the physics object that controls this ship.
     * 
     * @return the physics object that controls the ship.
     */
    public SpaceShipPhysics getPhysics() {
        return this.shipPhysics;
    }

    /**
     * This method is called by the SpaceWars game object when ever this ship
     * gets hit by a shot or collides with another ship when the shields are down.
     */
    public void gotHit() {
    	if (this.isShieldOn == false){
    		// Reduce health.
    		if (this.currentHealth >= HEALTH_REDUCTION)
    			this.currentHealth -= HEALTH_REDUCTION;
    		// Reduce energy.
    		if (this.maximalEnergy >= HIT_REDUCTION)
    			this.maximalEnergy -= HIT_REDUCTION;
    		if (this.maximalEnergy < HIT_REDUCTION)
    			this.maximalEnergy = LOWEST_ENERGY_LEVEL;
    		if (this.currentEnergy > this.maximalEnergy)
    			this.currentEnergy = this.maximalEnergy;
    	}
    }
   
    /**
     * Gets the image of this ship. This method should return the image of the
     * ship with or without the shield. This will be displayed on the GUI at
     * the end of the round.
     * 
     * @return the image of this ship.
     */
    public abstract Image getImage();


    /**
     * Attempts to fire a shot.
     * 
     * @param game the game object.
     */
    public void fire(SpaceWars game) {
    	// Attempt to fire.
       if (this.currentEnergy >= ENERGY_FIRE_REDUCTION && this.roundCounter >
       ROUND_FIRE_COOLDOWN){
    	   this.currentEnergy -= ENERGY_FIRE_REDUCTION;
    	   game.addShot(this.shipPhysics);
    	   // We just fired, apply 8 round cooldown.
    	   this.roundCounter = 0;
    	   
       }
    }
   

    /**
     * Attempts to turn on the shield.
     */
    public void shieldOn() {
    	if (this.currentEnergy >= ENERGY_SHIELD_REDUCTION)
    	{
    		this.currentEnergy -= ENERGY_SHIELD_REDUCTION;
    		this.isShieldOn = true;
    	}
    }

    /**
     * Attempts to teleport.
     */
    public void teleport() {
       if (this.currentEnergy >= ENERGY_TELEPORTATION_REDUCTION){
    	   this.currentEnergy -= ENERGY_TELEPORTATION_REDUCTION;
    	   // Reposition the ship.
           this.shipPhysics = new SpaceShipPhysics();
       }	
    }
    /**
     * Method to enable constant energy increase per round.
     */
    protected void energyCharge(){
    	if (this.currentEnergy < this.maximalEnergy)
    		this.currentEnergy += ENERGY_ROUND_INCREASE;
    }
    /**
     * Turns the spaceship in some direction. It will be implemented
     * differently according to the type of the spaceship.
     * @param game the game object to which this ship belongs.
     * @return the direction in which the spaceship will turn.
     */
    protected abstract int shouldTurn(SpaceWars game);
    
    /**
     * Returns the angle to the closest ship to our spaceship.
     * @param game the game object to which this ship belongs.
     * @return the angle to the closest ship.
     */
    protected double angleToClosestShip(SpaceWars game){
    	return this.getPhysics().angleTo(game.getClosestShipTo(this).
    			getPhysics());
    }
    /**
     * Returns the distance to the closest ship to our spaceship.
     * @param game the game object to which this ship belongs.
     * @return the distance to the closest ship.
     */
    protected double distanceToClosestShip(SpaceWars game){
    	return this.getPhysics().distanceFrom(game.getClosestShipTo(this).
    			getPhysics());
    }
}
