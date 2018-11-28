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
  public String questions1="";
   private ArrayList <String> questions= new ArrayList<String>();
    private ArrayList <String> categories= new ArrayList<String>();
    private ArrayList <String> difficulties= new ArrayList<String>();
    private ArrayList <String> answers= new ArrayList<String>();
    public ArrayList <Integer> block = new ArrayList <Integer>();
    private ArrayList <String> hints= new ArrayList<String>();
    @Override
    protected Void doInBackground(Void... voids) {


        try{
            URL url = new URL("https://raw.githubusercontent.com/z35ali/cps731-group15-quizit/master/app/src/main/java/com/example/zafar/cps731_group15_quizit/Quizzes.json?token=AXLoVmX-WnvTjAmIbhYfCQPOhCWGF7XNks5cBuQPwA%3D%3D");
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
                hints.add(""+jo.get("hint"));


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

    //Play.category.setText(""+categories.get(1));
    //Play.difficulty.setText(""+difficulties.get(1));

    }


    public ArrayList<String> getQuestions(){
        return questions;
    }

  public ArrayList <String> getAnswers(){
        return answers;
  }

    public ArrayList <String> getHints(){
        return hints;
    }



    public void setBlock(String category, String difficulty) {
        block.add(0);
        Play.category.setText(""+category);
        for (int i = 0; i <  categories.size(); i++) {
            System.out.println(i);

            if(categories.get(i).equals(category)){
                if(difficulties.get(i).equals(difficulty)){
                    block.add(i);
                }
        }
    }

}
}
