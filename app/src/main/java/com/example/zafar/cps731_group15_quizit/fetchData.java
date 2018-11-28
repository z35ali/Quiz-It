package com.example.zafar.cps731_group15_quizit;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class FetchData {

    public ArrayList<Quiz> quizzes;


    public FetchData() {
        quizzes = new ArrayList<>();
    }

    public void fetchQuizzes() {

        String txt = "[{\n" +
                "  \"category\": \"test\",\n" +
                "  \"difficulty\": \"whatever\",\n" +
                "  \"author\": \"whomever\",\n" +
                "  \"name\": \"one of the best quizzes ever\",\n" +
                "\n" +
                "  \"questions\": [{\n" +
                "    \"question\": \"what?\",\n" +
                "    \"answer\": \"yes\",\n" +
                "    \"hint\": \":^)\"\n" +
                "  },\n" +
                "    {\n" +
                "      \"question\": \"what2?\",\n" +
                "      \"answer\": \"yes2\",\n" +
                "      \"hint\": \":^)2\"\n" +
                "    }\n" +
                "  ]\n" +
                "},\n" +
                "\n" +
                "  {\n" +
                "    \"category\": \"test\",\n" +
                "    \"difficulty\": \"whatever\",\n" +
                "    \"author\": \"whomever\",\n" +
                "    \"name\": \"one of the best quizzes ever\",\n" +
                "\n" +
                "    \"questions\": [{\n" +
                "      \"question\": \"what?\",\n" +
                "      \"answer\": \"yes\",\n" +
                "      \"hint\": \":^)\"\n" +
                "    },\n" +
                "      {\n" +
                "        \"question\": \"what2?\",\n" +
                "        \"answer\": \"yes2\",\n" +
                "        \"hint\": \":^)2\"\n" +
                "      }\n" +
                "    ]\n" +
                "  }\n" +
                "]";
        String line = null;

        Quiz current_quiz;

        String category;
        String difficulty;
        String author;
        String name;
        String questions;
        JSONArray jq;

        try {
            JSONArray ja = new JSONArray(txt);
            for (int i = 0; i < ja.length(); i++) {
                JSONObject jo = (JSONObject) ja.get(i); //jo is one quiz
                category = (String) jo.get("category");
                difficulty = (String) jo.get("difficulty");
                author = (String) jo.get("author");
                name = (String) jo.get("name");

                current_quiz = new Quiz(name, author, category, difficulty);
                jq = (JSONArray) jo.get("questions");

                //iterate through questions
                //public void addQuestion(String question_text, String hint_text, String answer_ text)
                for (int j = 0; j < jq.length(); j++) {
                    JSONObject jqq = (JSONObject) jq.get(i);
                    current_quiz.addQuestion((String) jqq.get("question"),
                            (String) jqq.get("hint"),
                            (String) jqq.get("answer"));
                }
                quizzes.add(current_quiz);


            }

        } catch (JSONException e) {
        }
    }
}