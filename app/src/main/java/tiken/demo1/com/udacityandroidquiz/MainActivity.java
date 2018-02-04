package tiken.demo1.com.udacityandroidquiz;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity
{
    public boolean gameStart;//this tracks the start of the game, this is a check to disable hidden buttons
    public boolean gameNext;//this tracks when we are at the individual question display screen. Allows the next button to be visible
    private Quiz game;//the actual quiz object that runs the quiz game logic

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gameStart = false;
        gameNext = false;
        game = new Quiz();

    }

    /*
        The next methods are called from the appropriate on click methods
     */
    public void button1Click(View view)
    {
        if(!gameStart || gameNext) {//if the game hasn't started or we are next then do nothing
            //do nothing
        }
        else
        {
            setSolution(game.getAnswer(), 1);
            readyNext();
        }
    }

    public void button2Click(View view)
    {
        if(!gameStart || gameNext){
            //do nothing
        }
        else
        {
            setSolution(game.getAnswer(), 2);
            readyNext();
        }
    }

    public void button3Click(View view)
    {
        if(!gameStart || gameNext){
            //do nothing
        }
        else
        {
            setSolution(game.getAnswer(), 3);
            readyNext();
        }
    }


    //This method sets up the next button and makes it visible
    private void readyNext()
    {
        Button buttonNext = findViewById(R.id.startNextButton);

        buttonNext.setText("Next");

        buttonNext.setVisibility(View.VISIBLE);
    }

    //green color #ff42f445
    //red color #fff44242
    private void setSolution(int answer, int setNumber)
    {
        Button button1 = findViewById(R.id.Button1);
        Button button2 = findViewById(R.id.Button2);
        Button button3 = findViewById(R.id.Button3);
        TextView textView = findViewById(R.id.Question);
        ProgressBar pBar = findViewById(R.id.pBar);

        switch (setNumber)
        {
            case 1:
                if(setNumber == answer)
                    button1.setBackgroundColor(0xff42f445);
                else
                    button1.setBackgroundColor(0xfff44242);
                break;
            case 2:
                if(setNumber == answer)
                    button2.setBackgroundColor(0xff42f445);
                else
                    button2.setBackgroundColor(0xfff44242);
                break;
            case 3:
                if(setNumber == answer)
                    button3.setBackgroundColor(0xff42f445);
                else
                    button3.setBackgroundColor(0xfff44242);
                break;
            default:
                Log.d("setNumber switch", "setSolution: This is not a valid selection, how did you get here?");
                break;

        }

        if(answer != setNumber)
        {

            switch (answer)
            {
                case 1:
                    button1.setBackgroundColor(0xff42f445);
                    break;
                case 2:
                    button2.setBackgroundColor(0xff42f445);
                    break;
                case 3:
                    button3.setBackgroundColor(0xff42f445);
                    break;
                default:
                    Log.d("answer switch", "setSolution: This is not a valid selection, seriously how did you get here?!");
                    break;
            }

            textView.setText("Incorrect.");
        }
        else
        {
            textView.setText("Correct!");
            game.increaseAnswer();
        }

        gameNext = true;
        pBar.setProgress(10 * (game.getPosition()+1));

    }

    private void StartGame(View view)
    {
        Button button1 = findViewById(R.id.Button1);
        Button button2 = findViewById(R.id.Button2);
        Button button3 = findViewById(R.id.Button3);
        ProgressBar pBar = findViewById(R.id.pBar);
        Button startButton = findViewById(R.id.startNextButton);

        gameStart = true;
        //first we need to make the question buttons visible
        button1.setVisibility(View.VISIBLE);
        button2.setVisibility(View.VISIBLE);
        button3.setVisibility(View.VISIBLE);
        pBar.setVisibility(View.VISIBLE);

        //make the startButton invisible
        startButton.setVisibility(View.INVISIBLE);

        setTurn(view);//call the setQuestion function


    }


    public void startNext(View view)
    {
        ProgressBar pBar = findViewById(R.id.pBar);

        if(!gameStart)//we are starting the game
        {
            StartGame(view);
            pBar.setProgress(0);
        }
        else if(gameNext)//we are ready for the next question
        {
            if(!game.nextQ()) {
                finishGame();
                gameNext = false;
            }
            else
            {
                gameNext = false;
                StartGame(view);
            }
        }
        else
        {
            //do nothing
        }



    }

    /*
        This method sets up the turn for the next question. This does no error checking for question number, check before being called.
     */
    private void setTurn(View view)
    {
        Button button1 = findViewById(R.id.Button1);
        Button button2 = findViewById(R.id.Button2);
        Button button3 = findViewById(R.id.Button3);
        TextView tView = findViewById(R.id.Question);

        //reset all buttons to their default color
        button1.setBackgroundResource(android.R.drawable.btn_default);
        button2.setBackgroundResource(android.R.drawable.btn_default);
        button3.setBackgroundResource(android.R.drawable.btn_default);

        //set their text to the appropriate questions
        tView.setText(game.getNextQuestion());
        button1.setText(game.getAnswer1());
        button2.setText(game.getAnswer2());
        button3.setText(game.getAnswer3());

    }

    private void finishGame()
    {
        Button button1 = findViewById(R.id.Button1);
        Button button2 = findViewById(R.id.Button2);
        Button button3 = findViewById(R.id.Button3);
        Button startButton = findViewById(R.id.startNextButton);
        TextView tView = findViewById(R.id.Question);
        ProgressBar pBar = findViewById(R.id.pBar);

        int finalResult = game.getResult();

        button1.setVisibility(View.INVISIBLE);
        button2.setVisibility(View.INVISIBLE);
        button3.setVisibility(View.INVISIBLE);

        pBar.setProgress(100);


        startButton.setVisibility(View.VISIBLE);
        startButton.setText("Try Again?");

        if(finalResult == 10)
            tView.setText("Wow! You are an expert! \nWay to go champion!\n\n" + finalResult + "/10!");
        else if(finalResult > 7)
            tView.setText("You really know your stuff Player!\n" + finalResult + "/10!");
        else if(finalResult > 3)
            tView.setText("You are on your journey but not quite ready to finish the quest.\nStudy,play,learn!\n" + finalResult + "/10");
        else
            tView.setText("You might want to ask your grandson for answers.\nStart small, download Candy Crush.\n" + finalResult + "/10");


        gameStart = false;
        game.reset();

    }

}
