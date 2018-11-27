package com.example.zafar.cps731_group15_quizit;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class fetchData extends AsyncTask<Void,Void,Void> {

    String data="";
  //public String questions="";
   public ArrayList <String> questions= new ArrayList<String>();
    public ArrayList <String> categories= new ArrayList<String>();
    public ArrayList <String> difficulties= new ArrayList<String>();
    public ArrayList <String> answers= new ArrayList<String>();
    @Override
    protected Void doInBackground(Void... voids) {


        try{
            URL url = new URL("https://raw.githubusercontent.com/z35ali/cps731-group15-quizit/master/app/src/main/java/com/example/zafar/cps731_group15_quizit/Quizzes.json?token=AXLoVuhWfP5EdvQ9MztrJQ5RhsyCEHFmks5cBhIUwA%3D%3D");
           HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();

            InputStream in = new BufferedInputStream(httpURLConnection.getInputStream());
            BufferedReader bufferedReader= new BufferedReader(new InputStreamReader(in));
            String line="";
            while(line != null){
                line = bufferedReader.readLine();
                data=data + line;
            }

            JSONArray ja= new JSONArray(data);
            for (int i=0; i<ja.length();i++){
                JSONObject jo= (JSONObject)ja.get(i);
                questions.add(""+jo.get("question"));
                categories.add(""+jo.get("category"));
                difficulties.add(""+jo.get("difficulty"));


            }


        } catch (MalformedURLException e) {
            //bad  URL, tell the user
        } catch (JSONException e) {
            //bad  URL, tell the user
        } catch (IOException e) {
            //network error/ tell the user
        } /*finally  {
            client.disconnect();
        }*/
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
    Play.question.setText(""+questions.get(1));
    Play.category.setText(""+categories.get(1));
    Play.difficulty.setText(""+difficulties.get(1));

    }
}
