package edu.swarthmore.cs.lab3.eracecancer;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;


public class MySurvey extends Activity {

    private boolean mIsFemale;
    private ArrayList <SurveyAnswer> mSurveyAnswers;
    private static final String TAG = "MySurvey";
    private Button mViewList;
    private Button mViewSurvey;
    private Button mReset;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate() called");
        mSurveyAnswers = SurveyStore.get(getApplicationContext()).getSurveyAnswers();
        int Size = mSurveyAnswers.size();
        String s = String.valueOf(Size);
        Log.d(TAG, "size of mSurveyAnswers: "+s);
        findNextQuestion();

        setContentView(R.layout.activity_my_survey);
        mViewList = (Button) findViewById(R.id.list);
        mViewSurvey = (Button) findViewById(R.id.survey);
        mReset = (Button) findViewById(R.id.reset);

        mViewList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent j = new Intent(MySurvey.this, SuggestionListActivity.class);
                startActivity(j);
            }
        });
        mViewSurvey.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean load = findNextQuestion();
                if (load == false) {
                    Intent i = new Intent(MySurvey.this, sexquestion.class);
                    i.putExtra(sexquestion.EXTRA_QNUM, 0);
                    startActivityForResult(i, 0);
                }
            }
        });
        mReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SurveyStore.get(getApplicationContext()).clearSurveyAnswers();
                mSurveyAnswers = SurveyStore.get(getApplicationContext()).getSurveyAnswers();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        Log.d(TAG, String.valueOf(requestCode));
        switch (resultCode){
            case 0:
                Log.d(TAG, "case 0");
                int gender = data.getIntExtra("GENDER", 0);
                if (gender == 1){
                    mIsFemale = false;
                }
                else if (gender == 2){
                    mIsFemale = true;
                }
                Intent i1 = new Intent(MySurvey.this, AgeQuestion.class);
                i1.putExtra(AgeQuestion.EXTRA_QNUM, 1);
                startActivityForResult(i1, 1);
                break;
            case 1:
                Log.d(TAG, "case 1");
                Intent i2 = new Intent(MySurvey.this, ethnicityquestion.class);
                startActivityForResult(i2, 2);
                break;
            case 2:
                Log.d(TAG, "case 2");
                Intent i3 = new Intent(MySurvey.this, fivequestions.class);
                i3.putExtra(fivequestions.EXTRA_QNUM, 3);
                startActivityForResult(i3, 3);
                break;
            case 3:
                Log.d(TAG, "case 3");
                Intent i4 = new Intent(MySurvey.this, sexquestion.class);
                i4.putExtra(sexquestion.EXTRA_QNUM, 4);
                startActivityForResult(i4, 4);
                break;
            case 4:
                if(mIsFemale) {
                    Log.d(TAG, "case 4");
                    Intent i5 = new Intent(MySurvey.this, datequestions.class);
                    i5.putExtra(datequestions.EXTRA_QNUM, 5);
                    startActivityForResult(i5, 5);
                }
                else {
                    Log.d(TAG, "case 4");
                    Intent i = new Intent(MySurvey.this, datequestions.class);
                    i.putExtra(datequestions.EXTRA_QNUM, 8);
                    startActivityForResult(i, 8);
                }
                break;
            case 5:
                Log.d(TAG, "case 5");
                Intent i6 = new Intent(MySurvey.this, datequestions.class);
                i6.putExtra(datequestions.EXTRA_QNUM, 6);
                startActivityForResult(i6, 6);
                break;
            case 6:
                Log.d(TAG, "case 6");
                Intent i7 = new Intent(MySurvey.this, datequestions.class);
                i7.putExtra(datequestions.EXTRA_QNUM, 7);
                startActivityForResult(i7, 7);
                break;
            case 7:
                Log.d(TAG, "case 7");
                Intent i8 = new Intent(MySurvey.this, datequestions.class);
                i8.putExtra(datequestions.EXTRA_QNUM, 8);
                startActivityForResult(i8, 8);
                break;
            case 8:
                Intent i9 = new Intent(MySurvey.this, sexquestion.class);
                i9.putExtra(sexquestion.EXTRA_QNUM, 9);
                startActivityForResult(i9, 9);
                break;
            case 9:
                Intent i0 = new Intent(MySurvey.this, sexquestion.class);
                i0.putExtra(sexquestion.EXTRA_QNUM, 10);
                startActivityForResult(i0, 10);
                break;
            case 10:
                Log.d(TAG, "case 8");
                Intent i11 = new Intent(MySurvey.this, AgeQuestion.class);
                i11.putExtra(AgeQuestion.EXTRA_QNUM, 11);
                startActivityForResult(i11, 11);
                break;
            case 11:
                Log.d(TAG, "case 9");
                Intent i12 = new Intent(MySurvey.this, fivequestions.class);
                i12.putExtra(fivequestions.EXTRA_QNUM, 12);
                startActivityForResult(i12, 12);
                break;
            case 12:
                Log.d(TAG, "case 10");
                Intent i13 = new Intent(MySurvey.this, fivequestions.class);
                i13.putExtra(fivequestions.EXTRA_QNUM, 13);
                startActivityForResult(i13, 13);
                break;
            case 13:
                Log.d(TAG, "case 11");
                Intent j = new Intent(MySurvey.this, SuggestionListActivity.class);
                startActivity(j);
                break;
            case 100:
                break;
        }

    }

    private boolean findNextQuestion(){
        mSurveyAnswers = SurveyStore.get(getApplicationContext()).getSurveyAnswers();
        if (mSurveyAnswers.get(0).getAnswer() == "0") {
            Log.d(TAG, "about to call Q0");
            Intent i = new Intent(MySurvey.this, sexquestion.class);
            i.putExtra(sexquestion.EXTRA_QNUM, 0);
            startActivityForResult(i, 0);
            return true;
        } else if (mSurveyAnswers.get(1).getAnswer() == "0") {
            Log.d(TAG, "about to call Q1 - if");
            Intent i = new Intent(MySurvey.this, AgeQuestion.class);
            i.putExtra(AgeQuestion.EXTRA_QNUM, 1);
            startActivityForResult(i, 1);
            return true;
        } else if (mSurveyAnswers.get(2).getAnswer() == "0") {
            Log.d(TAG, "about to call Q2 - if");
            Intent i = new Intent(MySurvey.this, ethnicityquestion.class);
            startActivityForResult(i, 2);
            return true;
        } else if (mSurveyAnswers.get(3).getAnswer() == "0") {
            Log.d(TAG, "about to call Q3 - if");
            Intent i = new Intent(MySurvey.this, fivequestions.class);
            i.putExtra(fivequestions.EXTRA_QNUM, 3);
            startActivityForResult(i, 3);
            return true;
        } else if (mSurveyAnswers.get(4).getAnswer() == "0") {
            Log.d(TAG, "about to call Q4 - if");
            Intent i = new Intent(MySurvey.this, sexquestion.class);
            i.putExtra(sexquestion.EXTRA_QNUM, 4);
            startActivityForResult(i, 4);
            return true;
        } else if (mSurveyAnswers.get(5).getAnswer() == "0") {
            if(mIsFemale == true) {
                Log.d(TAG, "about to call Q5 - if");
                Intent i = new Intent(MySurvey.this, datequestions.class);
                i.putExtra(datequestions.EXTRA_QNUM, 5);
                startActivityForResult(i, 5);
                return true;
            }
        } else if (mSurveyAnswers.get(6).getAnswer() == "0") {
            if(mIsFemale == true) {
                Log.d(TAG, "about to call Q6 - if");
                Intent i = new Intent(MySurvey.this, datequestions.class);
                i.putExtra(datequestions.EXTRA_QNUM, 6);
                startActivityForResult(i, 6);
                return true;
            }
        } else if (mSurveyAnswers.get(7).getAnswer() == "0") {
            if (mIsFemale == true) {
                Log.d(TAG, "about to call Q7 - if");
                Intent i = new Intent(MySurvey.this, datequestions.class);
                i.putExtra(datequestions.EXTRA_QNUM, 7);
                startActivityForResult(i, 7);
                return true;
            }
        } else if (mSurveyAnswers.get(8).getAnswer() == "0") {
            Log.d(TAG, "about to call Q8 - if");
            Intent i = new Intent(MySurvey.this, datequestions.class);
            i.putExtra(datequestions.EXTRA_QNUM, 8);
            startActivityForResult(i, 8);
            return true;
        } else if (mSurveyAnswers.get(9).getAnswer() == "0") {
            Log.d(TAG, "about to call Q9 - if");
            Intent i = new Intent(MySurvey.this, sexquestion.class);
            i.putExtra(AgeQuestion.EXTRA_QNUM, 9);
            startActivityForResult(i, 9);
            return true;
        } else if (mSurveyAnswers.get(10).getAnswer() == "0") {
            Log.d(TAG, "about to call Q10 - if");
            Intent i = new Intent(MySurvey.this, sexquestion.class);
            i.putExtra(fivequestions.EXTRA_QNUM, 10);
            startActivityForResult(i, 10);
            return true;
        } else if (mSurveyAnswers.get(11).getAnswer() == "0") {
            Log.d(TAG, "about to call Q11 - if");
            Intent i = new Intent(MySurvey.this, AgeQuestion.class);
            i.putExtra(fivequestions.EXTRA_QNUM, 11);
            startActivityForResult(i, 11);
            return true;
        } else if (mSurveyAnswers.get(12).getAnswer() == "0") {
            Log.d(TAG, "about to call Q11 - if");
            Intent i = new Intent(MySurvey.this, fivequestions.class);
            i.putExtra(fivequestions.EXTRA_QNUM, 12);
            startActivityForResult(i, 12);
            return true;
        } else if (mSurveyAnswers.get(13).getAnswer() == "0") {
            Log.d(TAG, "about to call Q11 - if");
            Intent i = new Intent(MySurvey.this, fivequestions.class);
            i.putExtra(fivequestions.EXTRA_QNUM, 13);
            startActivityForResult(i, 13);
            return true;
        }
        return false;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.my_survey, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
