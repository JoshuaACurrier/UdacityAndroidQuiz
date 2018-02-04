package tiken.demo1.com.udacityandroidquiz;

import android.nfc.Tag;
import android.util.Log;

import java.util.Random;

/**
 * Created by josh currier on 1/30/2018.
 * Program Purpose: This program holds the array of Questions and runs the logic for the quiz game's operations
 *
 */

public class Quiz {
    /*
        @questions: holds the 10 questions
        @answers: holds the 30 answers. Each answer corresponds to a three chunk value. 1-3 + (position)
        @shuffleOrder: holds an int list of the shuffled questions, used for answers and questions
        @currentPos: Holds the current question number in the shuffle array, gets incremented after a questions has been asked
        @countAnswers: a verify check that the quiz_question class doesn't exceed three questions in the question array
     */
    private String questions[];
    private String answers[];
    private int shuffleOrder[];
    private Question Q[];
    private int position;
    private int correctAnswers;


    public Quiz(){
        questions = new String[10];
        answers = new String[30];
        Q = new Question[10];
        shuffleOrder = new int[10];
        reset();
        fillQuestions();
        fillAnswers();
        setUp();
        position = 0;//set the first question position pointer
    }

    public boolean nextQ()
    {
        if(position > 8)//return false if the game is over
            return false;
        else
        {
            position++;//else increment the position counter
        }

        return true;
    }

    public int getResult()
    {
        return correctAnswers;
    }


    public void increaseAnswer(){
        correctAnswers++;
    }


    public int getAnswer(){
        return Q[shuffleOrder[position]].getRealAnswer();
    }


    /*
        This method creates all of the Question objects into the Q array
     */
    private void setUp(){
        Q[0] = new Question(questions[0],answers[0],answers[1],answers[2],2);
        Q[1] = new Question(questions[1],answers[3],answers[4],answers[5],1);
        Q[2] = new Question(questions[2],answers[6],answers[7],answers[8],3);
        Q[3] = new Question(questions[3],answers[9],answers[10],answers[11],3);
        Q[4] = new Question(questions[4],answers[12],answers[13],answers[14],2);
        Q[5] = new Question(questions[5],answers[15],answers[16],answers[17],2);
        Q[6] = new Question(questions[6],answers[18],answers[19],answers[20],1);
        Q[7] = new Question(questions[7],answers[21],answers[22],answers[23],3);
        Q[8] = new Question(questions[8],answers[24],answers[25],answers[26],2);
        Q[9] = new Question(questions[9],answers[27],answers[28],answers[29],1);

    }

    //Method sets the questions strings, This could be replaced in the future with a call to a flat file or database
    private void fillQuestions(){
        questions[0] = "What is Mario's last name?";
        questions[1] = "What does SNES stand for?";
        questions[2] = "Which Controller does NOT have an X button?";
        questions[3] = "Which character is NOT playable in Super Mario RPG?";
        questions[4] = "What is Master Chief's First Name?";
        questions[5] = "In the Devil May Cry series, who is Dante's brother?";
        questions[6] = "In Pokemon Red, which Pokemon is listed first in the Pokedex?";
        questions[7] = "Which character is NOT in a Super Smash Bros. game?";
        questions[8] = "How many Triforce pieces do you need to collect in Wind Waker?";
        questions[9] = "In the original Pacman, what were the names of the ghosts?";

    }

    //Method sets the answer strings
    private void fillAnswers(){
        //For Question 0
        answers[0] = "Bros.";
        answers[1] = "Mario";
        answers[2] = "Alto";


        //For Question 1
        answers[3] = "Super Nintendo Entertainment System";
        answers[4] = "Standard Nintendo Engine System";
        answers[5] = "Special Night Ending Stand";


        //For Question 2
        answers[6] = "Dreamcast";
        answers[7] = "Xbox One";
        answers[8] = "NES";


        //For Question 3
        answers[9] = "Geno";
        answers[10] = "Princess Peach";
        answers[11] = "Luigi";


        //For Question 4
        answers[12] = "Mario";
        answers[13] = "John";
        answers[14] = "Duke";


        //For Question 5
        answers[15] = "Link";
        answers[16] = "Vergil";
        answers[17] = "Inferno";


        //For Question 6
        answers[18] = "Bulbasaur";
        answers[19] = "Pikachu";
        answers[20] = "Charmander";


        //For Question 7
        answers[21] = "Snake (Metal Gear Solid)";
        answers[22] = "Robin (Fire Emblem)";
        answers[23] = "Rayman (Rayman Origins)";


        //For Question 8
        answers[24] = "7";
        answers[25] = "8";
        answers[26] = "9";


        //For Question 9
        answers[27] = "Blinky, Pinky, Inky, and Clyde";
        answers[28] = "Abra, Kadabra, Hocus, and Pocus";
        answers[29] = "Red, Pink, Blue, and Orange";


    }

    //This method uses the Fisher-Yates shuffle to shuffle the ints in the shuffle array
    private void shuffle(){
        Random rnd = new Random();

        for(int i=9;i>0;i--){
            int index = rnd.nextInt(i+1);
            int a = shuffleOrder[index];
            shuffleOrder[index] = shuffleOrder[i];
            shuffleOrder[i] = a;
        }

    }

    //returns the currently stored question in the shuffled order
    //sets a check if the array is out of bounds
    public String getNextQuestion(){
        if(position > 9)
        {
            Log.d("OOB", "getNextQuestion: Error, question is out of bounds");
            position = 9;
        }

        return (Q[shuffleOrder[position]].getQuestion());
    }

    /*
        Returns the answer string for button 1
        @param questionPosition: an indicator of the current question number (0-9)
     */
    public String getAnswer1()
    {
        return Q[shuffleOrder[position]].getAnswer1();
    }

    public String getAnswer2()
    {
        return Q[shuffleOrder[position]].getAnswer2();
    }

    public String getAnswer3()
    {
        return Q[shuffleOrder[position]].getAnswer3();
    }

    public void reset()
    {
        int i;
        correctAnswers = 0;
        //simply sets the ints in the shuffle order to 0-9
        for(i=0;i<10;i++)
        {
            shuffleOrder[i]=i;
        }
        shuffle();
        position = 0;//set the first question position pointer
    }

    public int getPosition()
    {
        return position;
    }



}
