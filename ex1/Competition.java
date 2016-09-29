import java.util.Scanner;

/**
 * The Competition class represents a Nim competition between two players, consisting of a given number of rounds. 
 * It also keeps track of the number of victories of each player.
 */
public class Competition {
	
	/* You need to implement this class */
	
	
	/*
	 * Returns the integer representing the type of the player; returns -1 on bad
	 * input.
	 */
	private Player firstPlayer;
	private Player secondPlayer;
	boolean message;
	private int firstPlayerVictories = 0;
	private int secondPlayerVictories = 0;
	
	public Competition (Player player1, Player player2, boolean displayMessage){
		firstPlayer = player1;
		secondPlayer = player2;
		message = displayMessage;
	}
	private static int parsePlayerType(String[] args,int index){
		try{
			return Integer.parseInt(args[index]);
		} catch (Exception E){
			return -1;
		}
	}
	
	/*
	 * Returns the integer representing the type of player 2; returns -1 on bad
	 * input.
	 */
	private static int parseNumberOfGames(String[] args){
		try{
			return Integer.parseInt(args[2]);
		} catch (Exception E){
			return -1;
		}
	}
	
	public int getPlayerScore(int playerPosition){
		if (playerPosition == 1)
			return firstPlayerVictories;
		if (playerPosition == 2)
			return secondPlayerVictories;
		return -1;
	}
	

	public void playMultipleRounds(int numberOfRounds){
		for (int i=0 ; i < numberOfRounds ; i++){
			if (message == true)
				System.out.println("Welcome to the sticks game!");
			Board board = new Board();
			Player currentPlayer = firstPlayer;
			while (board.getNumberOfUnmarkedSticks() != 0){
				if (message == true)
					System.out.println("Player "+currentPlayer.getPlayerId()+ " , it is now your turn!");
				Move currentMove = currentPlayer.produceMove(board);
				while (board.markStickSequence(currentMove) <= 0){
					System.out.println("Invalid move. Enter another:");
					currentMove = currentPlayer.produceMove(board);
				}
				if (message == true)
					System.out.println(("Player " +currentPlayer.getPlayerId()+ " made the move: " +currentMove.toString()));
				if (currentPlayer == firstPlayer)
					currentPlayer = secondPlayer;
				else
					currentPlayer = firstPlayer;	
			}
					
			if (currentPlayer == firstPlayer)
				firstPlayerVictories++;
			else
				secondPlayerVictories++;
			
			if (message == true){
				if (firstPlayerVictories > secondPlayerVictories)	
					System.out.println("Player "+firstPlayer.getPlayerId()+" won!");
				else
					System.out.println("Player "+secondPlayer.getPlayerId()+" won!");
			}
		}	
	}
	
	
	/**
	 * The method runs a Nim competition between two players according to the three user-specified arguments. 
	 * (1) The type of the first player, which is a positive integer between 1 and 4: 1 for a Random computer
	 *     player, 2 for a Heuristic computer player, 3 for a Smart computer player and 4 for a human player.
	 * (2) The type of the second player, which is a positive integer between 1 and 4.
	 * (3) The number of rounds to be played in the competition.
	 * @param args an array of string representations of the three input arguments, as detailed above.
	 */
	public static void main(String[] args) {
		
		int p1Type = parsePlayerType(args,0);
		int p2Type = parsePlayerType(args,1);
		int numGames = parseNumberOfGames(args);
		boolean message;
		Scanner scanner = new Scanner(System.in);
		
		Player firstPlayer = new Player(p1Type, 1, scanner);
		Player secondPlayer = new Player(p2Type, 2, scanner);
		
		if ((p1Type == 4 || p2Type == 4))
			message = true;
		else
			message = false;
		Competition competition = new Competition(firstPlayer, secondPlayer, message);
		
		System.out.println("Starting a Nim competition of " +numGames+ " rounds between a " +competition.firstPlayer.getTypeName()+ " player and a " +competition.secondPlayer.getTypeName()+ " player.");
		competition.playMultipleRounds(numGames);
		System.out.println("The results are " +competition.firstPlayerVictories+ ":" +competition.secondPlayerVictories);

		}	
	}

	
