import java.awt.Image;

import oop.ex2.*;

public class HumanSpaceShip extends SpaceShip {
	

	
	public void doAction(SpaceWars game)
		{
			this.isShieldOn = false;
			if (game.getGUI().isTeleportPressed() == true)
				this.teleport();
			this.getPhysics().move(false, NO_TURN);
			if (game.getGUI().isUpPressed() == true)
				this.getPhysics().move(true, NO_TURN);
			if (game.getGUI().isLeftPressed() == true)
				this.getPhysics().move(false, LEFT_TURN);
			if (game.getGUI().isRightPressed() == true)
				this.getPhysics().move(false, RIGHT_TURN);
			if (game.getGUI().isShotPressed() == true)
				this.fire(game);
			if (game.getGUI().isShieldsPressed() == true)
				this.shieldOn();
			this.energyCharge();
			
	}
	public Image getImage(){
		if (this.isShieldOn == true)
			return GameGUI.SPACESHIP_IMAGE_SHIELD;
		else
			return GameGUI.SPACESHIP_IMAGE;
	}

}
