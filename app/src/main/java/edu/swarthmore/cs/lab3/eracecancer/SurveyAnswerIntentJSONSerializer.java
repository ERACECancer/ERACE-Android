package edu.swarthmore.cs.lab3.eracecancer;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONTokener;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;

/**
 * Created by agewirt1 on 11/19/14.
 */
public class SurveyAnswerIntentJSONSerializer {

    private Context mContext;
    private String mFilename;

    public SurveyAnswerIntentJSONSerializer(Context c, String f){
        mContext = c;
        mFilename = f;
    }

    public ArrayList <SurveyAnswer> loadSurveyAnswers() throws IOException, JSONException {
        ArrayList <SurveyAnswer> surveyAnswers = new ArrayList <SurveyAnswer>();
        BufferedReader reader = null;
        try {
            InputStream in = mContext.openFileInput(mFilename);
            reader = new BufferedReader(new InputStreamReader(in));
            StringBuilder jsonString = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null) {
                jsonString.append(line);
            }
            JSONArray array = (JSONArray) new JSONTokener(jsonString.toString()).nextValue();
            for (int i = 0; i < array.length(); i++) {
                surveyAnswers.add(new SurveyAnswer(array.getJSONObject(i)));
            }
        }
        catch(FileNotFoundException e) {

        }finally {
            if (reader != null) {
                reader.close();
            }
        }
      return surveyAnswers;
    }

    public void saveSurveyAnswers(ArrayList<SurveyAnswer> surveyAnswers) //create JSONArray object
            throws JSONException, IOException {
        //build an array in JSON
        JSONArray array = new JSONArray();
        for (SurveyAnswer sa : surveyAnswers) {
            array.put(sa.toJSON());
        }

        // Write the file to disk
        Writer writer = null;
        try {
            OutputStream out = mContext.openFileOutput(mFilename, Context.MODE_PRIVATE); //open file and write to it
            writer = new OutputStreamWriter(out);
            writer.write(array.toString());
        } finally {
            if (writer != null) {
                writer.close();
            }
        }
    }
}
