import oop.ex2.*;

public class SpaceShipFactory {
    public static SpaceShip[] createSpaceShips(String[] args) {
    	final int numberOfSpaceships = args.length();
    	SpaceShip[] spaceShipArr = new SpaceShip[numberOfSpaceships];
        for (int i = 0; i < numberOfSpaceships; i++)
        	switch args[i] {
        		case 'h' :
        			spaceShipArr[i] = new HumanSpaceShip();
        			break;
        		case 'r' :
        			spaceShipArr[i] = new RunnerSpaceShip();
        			break;
        		case 'b' :
        			spaceShipArr[i] = new BasherSpaceShip();
        			break;
        		case 'a' :
        			spaceShipArr[i] = new AggressiveSpaceShip();
        			break;
        		case 'd' :
        			spaceShipArr[i] = new DrunkardSpaceShip();
        			break;
        		case 's' :
        			spaceShipArr[i] = new SpecialSpaceShip();
        			break;
        	}
        return spaceShipArr;
        
    }
}
