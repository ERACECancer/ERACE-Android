package edu.swarthmore.cs.lab3.eracecancer;

import android.app.ListActivity;
import android.content.Context;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by agewirt1 on 11/19/14.
 */
public class SurveyStore {
    private static SurveyStore sSurveyStore;
    private Context mAppContext;
    private ArrayList <SurveyAnswer> mSurveyAnswers;
    private SurveyAnswerIntentJSONSerializer mSerializer;
    private static final String FILENAME = "survey_results.json";
    private static final String TAG = "SurveyStore";
    int[] mQuestions = new int[] {
      R.string.sexQ,
      R.string.ageQ,
      R.string.ethnicityQ,
      R.string.educationQ,
      R.string.medinsuranceQ,
      R.string.mammogramQ,
      R.string.breastQ,
      R.string.papQ,
      R.string.colorectalQ,
      R.string.tabaco_alcohol_q,
      R.string.smoking_q,
      R.string.zipcodeQ,
      R.string.cancerchanceQ,
      R.string.cancerchangesQ
    };

    private SurveyStore(Context appContext){
        mAppContext = appContext;
        Log.d(TAG, "in Constructor");
        mSerializer = new SurveyAnswerIntentJSONSerializer(mAppContext, FILENAME);
        try {
            mSurveyAnswers = mSerializer.loadSurveyAnswers();
            if (mSurveyAnswers.size() != 14){
                for (int i=0; i<mQuestions.length; i++){
                    SurveyAnswer s = new SurveyAnswer();
                    int question = mQuestions[i];
                    s.setQuestion(question);
                    s.setQNumber(i);
                    s.setAnswer("0");
                    mSurveyAnswers.add(s);
                }
            }
            Log.d(TAG, "in try part");
        } catch (Exception e){
            Log.d(TAG, "in catch part");
            mSurveyAnswers = new ArrayList<SurveyAnswer>();
            for (int i=0; i<mQuestions.length; i++){
                SurveyAnswer s = new SurveyAnswer();
                int question = mQuestions[i];
                s.setQuestion(question);
                s.setQNumber(i);
                s.setAnswer("0");
                mSurveyAnswers.add(s);
            }
        }
    }

    public ArrayList <SurveyAnswer> getSurveyAnswers() {return mSurveyAnswers;}

    public SurveyAnswer getSurveyAnswer(int qNum){
        for (SurveyAnswer s : mSurveyAnswers){
            if (s.getQNumber()==qNum){
                return s;
            }
        }
        return null;
    }

    public String getAnswer(int qNum) {
        for (SurveyAnswer s : mSurveyAnswers) {
            if (s.getQNumber() == qNum) {
                return s.getAnswer();
            }
        }
        return "";
    }

    public static SurveyStore get(Context c){

        if (sSurveyStore == null){
                sSurveyStore = new SurveyStore(c);
        }
        return sSurveyStore;
    }

    public void addSurveyAnswer(SurveyAnswer se) {mSurveyAnswers.add(se);}

    public void setSurveyAnswers(int Q, String A){
        Log.d(TAG, "called setSurveyAnswers");
        for (SurveyAnswer s : mSurveyAnswers){
            if (s.getQNumber() == Q){
                Log.d(TAG, "Setting Q "+String.valueOf(Q)+" to "+String.valueOf(A));
                s.setAnswer(A);
                break;
            }
        }
    }

    public void clearSurveyAnswers() {
        Log.d(TAG, "clearSurveyAnswers() called");
        for (SurveyAnswer s : mSurveyAnswers) {
            s.setAnswer("0");
        }
    }

    public boolean saveSurveyAnswers() {
        try {
            mSerializer.saveSurveyAnswers(mSurveyAnswers);
            return true;
        } catch (Exception e){
            return false;
        }
    }
}
