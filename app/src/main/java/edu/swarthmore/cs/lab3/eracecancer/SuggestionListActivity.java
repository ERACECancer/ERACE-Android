package edu.swarthmore.cs.lab3.eracecancer;

import android.app.Activity;
import android.app.ListActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by sdavis2 on 12/2/14.
 */
public class SuggestionListActivity extends ListActivity{
    private ArrayList<SurveyAnswer> mSurveyAnswers;
    private ArrayList<String> mSuggestions;
    private static final String TAG = "SuggestionListFragment";

    ArrayList<String> getSuggestions(){
        Log.d(TAG, "in getSuggestions()");
        mSuggestions = new ArrayList<String>();
        int gender = Integer.parseInt(mSurveyAnswers.get(0).getAnswer());
        int age = Integer.parseInt(mSurveyAnswers.get(1).getAnswer());
        int col = Integer.parseInt(mSurveyAnswers.get(8).getAnswer());
        int oral = Integer.parseInt(mSurveyAnswers.get(9).getAnswer());
        int lung = Integer.parseInt(mSurveyAnswers.get(10).getAnswer());
        //maybe add stuff about specific date to get mammogram - add x years to year
        Log.d(TAG, "gender: "+String.valueOf(gender));
        Log.d(TAG, "age: "+String.valueOf(age));
        Log.d(TAG, "col"+ String.valueOf(col));
        if (gender == 1){
            //male
            if ((age >= 20) && (age < 30)){
                mSuggestions.add(getString(R.string.HPV_vaccine));
            } else if (age > 50){
                if((col == 1) || (col == 2)){
                    mSuggestions.add(getString(R.string.colorectal));
                }
                mSuggestions.add(getString(R.string.prostate));
            }
        }
        else if (gender == 2){
            //female
            if((age >= 20) && (age < 30)){
                mSuggestions.add(getString(R.string.HPV_vaccine));
                mSuggestions.add(getString(R.string.pap_3_year));
                mSuggestions.add(getString(R.string.breast_3_year));
                mSuggestions.add(getString(R.string.breast_self));
            } else if ((age >= 30) && (age < 40)) {
                mSuggestions.add(getString(R.string.hpv_5_year));
                mSuggestions.add(getString(R.string.pap_5_year));
                mSuggestions.add(getString(R.string.breast_3_year));
                mSuggestions.add(getString(R.string.breast_self));
            } else if ((age >=40) && (age < 65)) {
                mSuggestions.add(getString(R.string.hpv_5_year));
                mSuggestions.add(getString(R.string.pap_5_year));
                mSuggestions.add(getString(R.string.breast_1_year));
                mSuggestions.add(getString(R.string.mammogram));
            }
            if (age > 50){
                if((col == 1) || (col == 2)){
                    mSuggestions.add(getString(R.string.colorectal));
                }
            }
        }
        if (oral == 1){
            mSuggestions.add(getString(R.string.oral_screen));
        }
        if ((age > 54) && (age < 74) && (lung == 1)){
            mSuggestions.add(getString(R.string.lung_screen));
        }
        if(mSuggestions.size() == 0){
            mSuggestions.add(getString(R.string.no_screenings));
        }
        return mSuggestions;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate() called");
        setTitle(R.string.suggestions_title);
        mSurveyAnswers = SurveyStore.get(getApplicationContext()).getSurveyAnswers();
        ArrayList<String> prevention = getSuggestions();
        SuggestionsAdapter adapter = new SuggestionsAdapter(prevention);

        setListAdapter(adapter);
    }

    private class SuggestionsAdapter extends ArrayAdapter<String> {
        public SuggestionsAdapter(ArrayList<String> list){
            super(SuggestionListActivity.this, R.layout.list_item_suggestions, list);
            //super(getActivity(), 0, list);
        }
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (null == convertView){
                convertView = getLayoutInflater().inflate(R.layout.list_item_suggestions, null);
            }
            Log.d(TAG, "getView() called");
            String s = getItem(position);
            TextView titleTextView = (TextView)convertView.findViewById(R.id.suggestions_list_TextView);
            titleTextView.setText(s);
            return convertView;
        }
    }
    @Override
    public void onResume() {
        super.onResume();
        ((SuggestionsAdapter)getListAdapter()).notifyDataSetChanged();
    }
}

