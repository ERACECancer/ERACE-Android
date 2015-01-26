package edu.swarthmore.cs.lab3.eracecancer;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by agewirt1 on 11/19/14.
 */
public class SurveyAnswer {
    private int mQNumber;
    private int mQuestion;
    private String mAnswer;
    private static final String JSON_QNUMBER = "qNumber";
    private static final String JSON_QUESTION = "question";
    private static final String JSON_ANSWER = "answer";
    private boolean mCompleted;


    public SurveyAnswer(){
        mCompleted=false;
    }

    public SurveyAnswer(JSONObject json) throws JSONException {
        mAnswer = json.getString(JSON_ANSWER);
        mQNumber = json.getInt(JSON_QNUMBER);
        mQuestion = json.getInt(JSON_QUESTION);
    }

    public JSONObject toJSON() throws JSONException {
        JSONObject json = new JSONObject();
        json.put(JSON_QNUMBER, mQNumber);
        json.put(JSON_QUESTION,mQuestion);
        json.put(JSON_ANSWER, mAnswer);
        return json;
    }



//getter and setter methods
    public int getQNumber() {
        return mQNumber;
    }

    public void setQNumber(int QNumber) {
        mQNumber = QNumber;
    }

    public int getQuestion() {
        return mQuestion;
    }

    public void setQuestion(int question) {
        mQuestion = question;
    }

    public String getAnswer() {
        return mAnswer;
    }

    public void setAnswer(String answer) {
        mAnswer = answer;
    }

    public boolean isCompleted() {
        return mCompleted;
    }

    public void setCompleted(boolean completed) {
        mCompleted = completed;
    }
}
