import java.util.Scanner;
/**
 * Four in a row: Two-player console, non-graphics
 * @author relkharboutly
 * @date 1/22/2020
 */
public class FIRConsole  {
                                                     
   public static Scanner in = new Scanner(System.in); // the input Scanner
 
   public static FourInARow FIRboard = new FourInARow();
  
   
   /** The entry main method (the program starts here) */
   public static void main(String[] args) {
      
	   int currentState = FourInARow.PLAYING;
	   String userInput ="";
	   //game loop
	   do {
		   FIRboard.printBoard();
         /** TODO implement the game loop 
          * 	1- accept user move
          *     2- call getComputerMove
          *     3- Check for winner
          *     4- Print game end messages in case of Win , Lose or Tie !
          * */
		   //1
		   System.out.println("Enter a location (0 - 35) you would like to move: ");
		   userInput = in.next();
		   int playerMove = Integer.parseInt(userInput);
		   FIRboard.setMove(1, playerMove);
		   //2
		   FIRboard.setMove(2 ,FIRboard.getComputerMove());
		   //3
		   int gameState = FIRboard.checkForWinner();
		   //4
		   switch(gameState) {
		       case 1:
		    	   System.out.println("Game Tied!");
		    	   currentState = IGame.TIE;
		    	   break;
		       case 2:
		    	   System.out.println("Blue Wins!");
		    	   currentState = IGame.BLUE_WON;
		    	   break;
		       case 3:
		    	   System.out.println("Red Wins!");
		    	   currentState = IGame.RED_WON;
		    	   break;
		   }
      } while ((currentState == IGame.PLAYING) && (!userInput.equals("q"))); // repeat if not game-over
	  //prints final board state
	  FIRboard.printBoard();
   }
     
}