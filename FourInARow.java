import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * TicTacToe class implements the interface
 * @author relkharboutly
 * @date 2/12/2022
 */
public class FourInARow implements IGame {
		 
	   // The game board and the game status
	   private static final int ROWS = 6, COLS = 6; // number of rows and columns
	   private int[][] board = new int[ROWS][COLS]; // game board in 2D array
	  
	/**
	 * clear board and set current player   
	 */
	public FourInARow(){
		
	}
	@Override
	public void clearBoard() {
		// TODO Auto-generated method stub
		for(int i = 0; i < 6; i++) {
			for(int j = 0; j < 6; j++) {
				board[i][j] = 0;
			}
		}
	}

	@Override
	public void setMove(int player, int location) {
		//location input 0-35, calc row#, col#
		int row = location/6 ;
		int col = location % 6;
		
		board[row][col] = player;
	}

	@Override
	public int getComputerMove() {
		boolean valid = false;
		int temp = 0;
		int row = 0;
		int col = 0;
		//computer picks a random location, and moves there if it's a valid move
		while(!valid) {
			//picks random location
			temp = (int) (Math.random() * 36);
			row = temp / 6;
			col = temp % 6;
			//if spot isn't empty, it is a valid move
			if(board[row][col] == 0)
				valid = true;
		}
		//returns location
		return temp;
		
		/* Work in Progress - Commputer tries to win, doesn't just play
		int temp = 0;
		boolean prevMoves = false;
		
		for(int r = 0; r < 6; r++) {
			for(int c = 0; c < 6; c++) {
				//checks for an empty spot on the board
				if(board[r][c] == 2) {
					prevMoves = true;
				}
			}
		}
		//if there are no previous moves, pick a random open spot
		if(!prevMoves) {
			boolean valid = false;
			//testing
			int row = 0;
			int col = 0;
			
			while(!valid) {
				temp = (int) (Math.random() * 36);
				row = temp / 6;
				col = temp % 6;
				//if spot isn't empty, it is a valid move
				if(board[row][col] == 0)
					valid = true;
			}
			return temp;
		}
		//if there are previous moves, track location
		else {
			for(int r = 0; r < 6; r++) {
				for(int c = 0; c < 6; c++) {
					if(board[r][c] == 2) {
						
					}

				}
			}
		}
		*/
		//default return statement
		//return -1;
	}

	@Override
	public int checkForWinner() {
		int temp = -1;
		
		//horizontal
		for(int r = 0; r < 6; r++) {
			for(int c = 0; c < 3; c++) {
				//if spot is filled, check next 3 spots to the right
				if(board[r][c] != 0) {
					temp = board[r][c];
					if(board[r][c + 1] == temp && board[r][c + 2] == temp && board[r][c + 3] == temp) {
						return temp + 1;
					}
				}
			}
		}
		//vertical
		for(int r = 0; r < 3; r++) {
			for(int c = 0; c < 6; c++) {
				//if spot is filled, checks the next 4 spots down
				if(board[r][c] != 0) {
					temp = board[r][c];
					if(board[r + 1][c] == temp && board[r + 2][c] == temp && board[r + 3][c] == temp) {
						return temp + 1;
					}
				}
			}
		}
		//diag up L -> R
		for(int r = 0; r < 6; r++) {
			for(int c = 0; c < 3; c++) {
				if(board[r][c] != 0) {
					boolean repeat = true;
					int count = 1;
					int curr = board[r][c];
					int row = r - 1;
					int col = c + 1;
					//if valid spot, continues to check the next spots up to the right until a player wins, or the spot is invalid
					while(col < 6 && row >= 0 && repeat) {
						if(board[row][col] == curr) {
							count++;
						}
						else {
							repeat = false;
						}
						row--;
						col++;
					}
					//if there were 4 in a row, this player has won
					if(count == 4) {
						return curr + 1;
					}
				}
			}
		}
		//diag down L -> R
		for(int r = 0; r < 6; r++) {
			for(int c = 0; c < 3; c++) {
				if(board[r][c] != 0) {
					boolean repeat = true;
					int count = 1;
					int curr = board[r][c];
					int row = r + 1;
					int col = c + 1;
					//if valid spot, continues to check the next spot down to the right until aplayer wins, or spot is invalid
					while(col < 6 && row < 6 && repeat) {
						if(board[row][col] == curr) {
							count++;
						}
						else {
							repeat = false;
						}
						row++;
						col++;
					}
					//if there were four valid spots in a row, returns the player who won
					if(count == 4) {
						return curr + 1;
					}
				}
			}
		}
		
		//ties
		boolean openSpots = false;
		
		for(int r = 0; r < 6; r++) {
			for(int c = 0; c < 6; c++) {
				//checks for an empty spot on the board
				if(board[r][c] == 0) {
					openSpots = true;
				}
			}
		}
		//if there are no empty cells, return tie
		if(!openSpots)
			return 1;
		//default return statement
		return -1;
	}
	
	  /**
	   *  Print the game board 
	   */
	   public  void printBoard() {
		  
	      for (int row = 0; row < ROWS; ++row) {
	         for (int col = 0; col < COLS; ++col) {
	            printCell(board[row][col]); // print each of the cells
	            if (col != COLS - 1) {
	               System.out.print("|");   // print vertical partition
	            }
	         }
	         System.out.println();
	         if (row != ROWS - 1) {
	            System.out.print("-----------"); // print horizontal partition
	            System.out.println("-----------"); // print horizontal partition
	         }
	      }
	      System.out.println(); 
	   }
	 
	   /**
	    * Print a cell with the specified "content" 
	    * @param content either BLUE, RED or EMPTY
	    */
	   public  void printCell(int content) {
	      switch (content) {
	         case EMPTY:  System.out.print("   "); break;
	         case BLUE: System.out.print(" B "); break;
	         case RED:  System.out.print(" R "); break;
	      }
	   }

}
