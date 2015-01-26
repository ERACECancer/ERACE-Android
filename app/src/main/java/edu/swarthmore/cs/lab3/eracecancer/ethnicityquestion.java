package edu.swarthmore.cs.lab3.eracecancer;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

/**
 * Created by agewirt1 on 11/17/14.
 */
public class ethnicityquestion extends Activity {

    private RadioGroup radioFiveGroup;
    private static final String TAG = "ethnicityQuestion";
    private Button mNext;
    private Button mHome;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.question_ethnicity);

        mNext = (Button) findViewById(R.id.eNext);
        mHome = (Button) findViewById(R.id.eHome);

        RadioButton r1 = (RadioButton) findViewById(R.id.ebutton_1);
        RadioButton r2 = (RadioButton) findViewById(R.id.ebutton_2);
        RadioButton r3 = (RadioButton) findViewById(R.id.ebutton_3);
        RadioButton r4 = (RadioButton) findViewById(R.id.ebutton_4);
        RadioButton r5 = (RadioButton) findViewById(R.id.ebutton_5);
        RadioButton r6 = (RadioButton) findViewById(R.id.ebutton_6);
        RadioButton r7 = (RadioButton) findViewById(R.id.ebutton_7);
        RadioButton r8 = (RadioButton) findViewById(R.id.ebutton_8);
        RadioButton r9 = (RadioButton) findViewById(R.id.ebutton_9);

        String ans = SurveyStore.get(getApplicationContext()).getAnswer(2);
        if (ans != "0"){
            if (ans == "1"){
                r1.setChecked(true);
            } else if (ans =="2") {
                r2.setChecked(true);
            } else if (ans == "3"){
                r3.setChecked(true);
            } else if(ans == "4"){
                r4.setChecked(true);
            } else if (ans == "5"){
                r5.setChecked(true);
            } else if (ans == "6"){
                r6.setChecked(true);
            } else if (ans == "7"){
                r7.setChecked(true);
            } else if (ans == "8"){
                r8.setChecked(true);
            } else if (ans == "9"){
                r9.setChecked(true);
            }
        }
        mNext.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(SurveyStore.get(getApplicationContext()).getAnswer(2) == "0") {
                    Toast.makeText(getApplicationContext(), R.string.answer_q_toast, Toast.LENGTH_SHORT).show();
                }
                else {
                    setResult(2);
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
            case R.id.ebutton_1:
                if (checked) {
                    Log.d(TAG, "about to set answer to" + String.valueOf(1));
                    SurveyStore.get(getApplicationContext()).setSurveyAnswers(2, "1");
                    break;
                }
            case R.id.ebutton_2:
                if(checked) {
                    Log.d(TAG, "about to set answer to" + String.valueOf(2));
                    SurveyStore.get(getApplicationContext()).setSurveyAnswers(2, "2");
                    break;
                }
            case R.id.ebutton_3:
                if (checked) {
                    Log.d(TAG, "about to set answer to" + String.valueOf(3));
                    SurveyStore.get(getApplicationContext()).setSurveyAnswers(2, "3");
                    break;
                }
            case R.id.ebutton_4:
                if (checked) {
                    Log.d(TAG, "about to set answer to" + String.valueOf(4));
                    SurveyStore.get(getApplicationContext()).setSurveyAnswers(2, "4");
                    break;
                }
            case R.id.ebutton_5:
                Log.d(TAG, "about to set answer to" + String.valueOf(5));
                SurveyStore.get(getApplicationContext()).setSurveyAnswers(2, "5");
                break;
            case R.id.ebutton_6:
                if (checked) {
                    Log.d(TAG, "about to set answer to" + String.valueOf(6));
                    SurveyStore.get(getApplicationContext()).setSurveyAnswers(2, "6");
                    break;
                }
            case R.id.ebutton_7:
                if (checked) {
                    Log.d(TAG, "about to set answer to" + String.valueOf(7));
                    SurveyStore.get(getApplicationContext()).setSurveyAnswers(2, "7");
                    break;
                }
            case R.id.ebutton_8:
                if (checked) {
                    Log.d(TAG, "about to set answer to" + String.valueOf(8));
                    SurveyStore.get(getApplicationContext()).setSurveyAnswers(2, "8");
                    break;
                }
            case R.id.ebutton_9:
                if (checked) {
                    Log.d(TAG, "about to set answer to" + String.valueOf(9));
                    SurveyStore.get(getApplicationContext()).setSurveyAnswers(2, "9");
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