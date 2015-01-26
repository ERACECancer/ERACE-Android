package edu.swarthmore.cs.lab3.eracecancer;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by agewirt1 on 11/17/14.
 */
public class fivequestions extends Activity {

    private RadioGroup radioFiveGroup;
    private RadioButton radioFiveButton;
    public static final String EXTRA_QNUM = "edu.swarthmore.cs.lab3.eracecancer.five_q_num";
    private int mQnum;
    private static final String TAG = "fiveQuestions";
    private Button mNext;
    private Button mHome;

    TextView mQuestion;
    RadioButton mAns1;
    RadioButton mAns2;
    RadioButton mAns3;
    RadioButton mAns4;
    RadioButton mAns5;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.questions_five);
        mQnum = getIntent().getIntExtra(EXTRA_QNUM, 100);

        mQuestion = (TextView) findViewById(R.id.five_questions_tv);
        mAns1 = (RadioButton) findViewById(R.id.button_1);
        mAns2 = (RadioButton) findViewById(R.id.button_2);
        mAns3 = (RadioButton) findViewById(R.id.button_3);
        mAns4 = (RadioButton) findViewById(R.id.button_4);
        mAns5 = (RadioButton) findViewById(R.id.button_5);
        mNext = (Button) findViewById(R.id.fNext);
        mHome = (Button) findViewById(R.id.fHome);

        if (mQnum == 3) {
            mQuestion.setText(R.string.educationQ);
            mQuestion.setTextSize(30);
            mAns1.setText(R.string.highs);
            mAns2.setText(R.string.somecollege);
            mAns3.setText(R.string.bachelors);
            mAns4.setText(R.string.masters);
            mAns5.setText(R.string.doctoral);
        } else if (mQnum == 12) {
            mQuestion.setText(R.string.cancerchanceQ);
            mQuestion.setTextSize(26);
            mAns1.setText(R.string.stronglyagree);
            mAns2.setText(R.string.agree);
            mAns3.setText(R.string.neutral);
            mAns4.setText(R.string.disagree);
            mAns5.setText(R.string.stronglydisagree);
        } else if (mQnum == 13) {
            mQuestion.setText(R.string.cancerchangesQ);
            mQuestion.setTextSize(26);
            mAns1.setText(R.string.stronglyagree);
            mAns2.setText(R.string.agree);
            mAns3.setText(R.string.neutral);
            mAns4.setText(R.string.disagree);
            mAns5.setText(R.string.stronglydisagree);
        }
        String ans = SurveyStore.get(getApplicationContext()).getAnswer(mQnum);
        if (ans != "0"){
            if (ans == "1"){
                mAns1.setChecked(true);
            } else if (ans =="2") {
                mAns2.setChecked(true);
            } else if (ans == "3"){
                mAns3.setChecked(true);
            } else if(ans == "4"){
                mAns4.setChecked(true);
            } else if (ans == "5"){
                mAns5.setChecked(true);
            }
        }
        mNext.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(SurveyStore.get(getApplicationContext()).getAnswer(mQnum) == "0") {
                    Toast.makeText(getApplicationContext(), R.string.answer_q_toast, Toast.LENGTH_SHORT).show();
                }
                else {
                    setResult(mQnum);
                    finish();
                }
            }

        });
        mHome.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                setResult(100);
                finish();
            }
        });
    }
    public void onRadioButtonClicked(View view){

        boolean checked = ((RadioButton) view).isChecked();

        switch (view.getId()){
            case R.id.button_1:
                if (checked) {
                    Log.d(TAG, "about to set answer to" + String.valueOf(1));
                    SurveyStore.get(getApplicationContext()).setSurveyAnswers(mQnum, "1");
                    break;
                }
            case R.id.button_2:
                if (checked) {
                    Log.d(TAG, "about to set answer to" + String.valueOf(2));
                    SurveyStore.get(getApplicationContext()).setSurveyAnswers(mQnum, "2");
                    break;
                }
            case R.id.button_3:
                if (checked) {
                    Log.d(TAG, "about to set answer to" + String.valueOf(3));
                    SurveyStore.get(getApplicationContext()).setSurveyAnswers(mQnum, "3");
                    break;
                }
            case R.id.button_4:
                if (checked) {
                    Log.d(TAG, "about to set answer to" + String.valueOf(4));
                    SurveyStore.get(getApplicationContext()).setSurveyAnswers(mQnum, "4");
                    break;
                }
            case R.id.button_5:
                if (checked) {
                    Log.d(TAG, "about to set answer to" + String.valueOf(5));
                    SurveyStore.get(getApplicationContext()).setSurveyAnswers(mQnum, "5");
                    break;
                }
        }
    }
    @Override
    public void onPause() {
        super.onPause();
        SurveyStore.get(getApplicationContext()).saveSurveyAnswers();
    }

    @Override
    public void onStop() {
        super.onStop();
        SurveyStore.get(getApplicationContext()).saveSurveyAnswers();
    }
    @Override
    public void onDestroy(){
        super.onDestroy();
        SurveyStore.get(getApplicationContext()).saveSurveyAnswers();
    }
}
