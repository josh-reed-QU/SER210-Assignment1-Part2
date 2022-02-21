/*
    Assignment 1 - Part 2 - Android Four In a Row Game
    Author - Josh Reed
    Due Date: 2/20
 */
package edu.quinnipiac.ser210.assignment1_part2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    /*
    //TODO: implement method
    //saves game state
    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
    }
    */
    //Method called when the start button is clicked
    public void onStart(View view) {
        EditText userInput = (EditText) findViewById(R.id.usernameInput);
        String name = userInput.getText().toString();

        Intent intent = new Intent(this, GameActivity.class);
        intent.putExtra("playerUsername", name);
        startActivity(intent);
    }
}