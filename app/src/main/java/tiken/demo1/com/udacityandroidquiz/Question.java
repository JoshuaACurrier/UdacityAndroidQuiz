package tiken.demo1.com.udacityandroidquiz;

/**
 * Created by Josh currier on 1/30/2018.
 * Program Desc: This holds and returns the data for the questions and answers
 */

public class Question {

    private String question;
    private String answer1;
    private String answer2;
    private String answer3;
    private int realAnswer;

    public Question(){
        realAnswer = 0;
    }

    public Question(String toQuestion, String toAnswer1, String toAnswer2, String toAnswer3, int toRealAnswer){
        question = toQuestion;
        answer1 = toAnswer1;
        answer2 = toAnswer2;
        answer3 = toAnswer3;
        realAnswer = toRealAnswer;

    }

    public String getQuestion() {
        return question;
    }


    public String getAnswer1() {
        return answer1;
    }

    public String getAnswer2() {
        return answer2;
    }

    public String getAnswer3() {
        return answer3;
    }

    public int getRealAnswer() {
        return realAnswer;
    }

}
