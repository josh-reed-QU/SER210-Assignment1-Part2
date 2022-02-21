/*
    Assignment 1 - Part 2 - Android Four In a Row Game
    Author - Josh Reed
    Due Date: 2/20
 */
package edu.quinnipiac.ser210.assignment1_part2;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import edu.quinnipiac.ser210.assignment1_part2.IGame;

public class GameActivity extends AppCompatActivity {

    private String playerName;
    private int currentState = IGame.PLAYING;
    private boolean playerMoved = false;
    private static final int ROWS = 6, COLS = 6; // number of rows and columns
    private int[][] board = new int[ROWS][COLS]; // game board in 2D array

    private List<ImageButton> buttons;
    private static final int[] BUTTON_IDS = {
            R.id.buttonOne,
            R.id.buttonTwo,
            R.id.buttonThree,
            R.id.buttonFour,
            R.id.buttonFive,
            R.id.buttonSix,
            R.id.buttonSeven,
            R.id.buttonEight,
            R.id.buttonNine,
            R.id.buttonTen,
            R.id.buttonEleven,
            R.id.buttonTwelve,
            R.id.buttonThirteen,
            R.id.buttonFourteen,
            R.id.buttonFifteen,
            R.id.buttonSixteen,
            R.id.buttonSeventeen,
            R.id.buttonEighteen,
            R.id.buttonNineteen,
            R.id.buttonTwenty,
            R.id.buttonTwentyOne,
            R.id.buttonTwentyTwo,
            R.id.buttonTwentyThree,
            R.id.buttonTwentyFour,
            R.id.buttonTwentyFive,
            R.id.buttonTwentySix,
            R.id.buttonTwentySeven,
            R.id.buttonTwentyEight,
            R.id.buttonTwentyNine,
            R.id.buttonThirty,
            R.id.buttonThirtyOne,
            R.id.buttonThirtyTwo,
            R.id.buttonThirtyThree,
            R.id.buttonThirtyFour,
            R.id.buttonThirtyFive,
            R.id.buttonThirtySix
    };

    IGame fourInARowGame = new IGame() {
        @Override
        public void clearBoard() {
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
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_screen);

        //creates arrayList to access the grid buttons
        buttons = new ArrayList<ImageButton>(BUTTON_IDS.length);
        for(int id : BUTTON_IDS) {
            ImageButton button = (ImageButton) findViewById(id);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(currentState == IGame.PLAYING) {
                        button.setImageResource(R.drawable.ic_o_icon_foreground);
                        fourInARowGame.setMove(1, buttons.indexOf(button));
                        playerMoved = true;
                    }
                }
            });
            buttons.add(button);
        }
        //gets username to display from splash screen using intent
        playerName = getIntent().getStringExtra("playerUsername");
        TextView usernameDisplay = (TextView) findViewById(R.id.username);
        //sets the text in the view to the inputted username
        usernameDisplay.setText("Player: " + playerName);
        //turnTracker.setText("Turn: " + playerName);

        Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                runGame();
                handler.post(this);
            }
        });
    }

    protected void runGame() {
        //game loop
        while(playerMoved && currentState == IGame.PLAYING) {
            //check for winner
            int gameState = fourInARowGame.checkForWinner();
            switch (gameState) {
                case 1:
                    currentState = IGame.TIE;
                    Toast.makeText(getBaseContext(), "Tie!", Toast.LENGTH_LONG).show();
                    playerMoved = false;
                    break;
                case 2:
                    currentState = IGame.BLUE_WON;
                    Toast.makeText(getBaseContext(), "You Win!", Toast.LENGTH_LONG).show();
                    playerMoved = false;
                    break;
                case 3:
                    currentState = IGame.RED_WON;
                    Toast.makeText(getBaseContext(), "You Lose", Toast.LENGTH_LONG).show();
                    playerMoved = false;
                    break;
            }
            //computer move
            //turnTracker.setText("Turn: Computer");
            int temp = fourInARowGame.getComputerMove();
            fourInARowGame.setMove(2, temp);
            buttons.get(temp).setImageResource(R.drawable.ic_x_icon_foreground);

            //turnTracker.setText("Turn: " + playerName);
            playerMoved = false;
        }
    }

    //if game is over and reset is pressed, the board is cleared and the icons and game state are reset
    protected void onResetPress(View view) {
        if(currentState != IGame.PLAYING) {
            fourInARowGame.clearBoard();
            for(ImageButton button : buttons) {
                button.setImageResource(R.drawable.ic_o_icon_background);
            }
            currentState = IGame.PLAYING;
            playerMoved = true;
        }
    }

}
