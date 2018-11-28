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

        String txt = "[\n" +
                "\t{\n" +
                "        \"category\": \"Math\",\n" +
                "        \"difficulty\": \"Easy\",\n" +
                "        \"author\": \"admin\",\n" +
                "        \"name\": \"Easy Math Quiz\",\n" +
                "        \"questions\":\n" +
                "\t\t[\n" +
                "\t\t\t{\n" +
                "                \"question\": \"What is 9-5?\",\n" +
                "                \"answer\": \"4\",\n" +
                "                \"hint\": \"Between 2 and 5.\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"question\": \"What is 2+9?\",\n" +
                "                \"answer\": \"11\",\n" +
                "                \"hint\": \"Between 10 and 15.\"\n" +
                "            }, \n" +
                "\t\t\t{\n" +
                "                \"question\": \"What is 30-12?\",\n" +
                "                \"answer\": \"18\",\n" +
                "                \"hint\": \"Between 15 and 20.\"\n" +
                "            }\n" +
                "        ]\n" +
                "    },\n" +
                "    {\n" +
                "        \"category\": \"Math\",\n" +
                "        \"difficulty\": \"Medium\",\n" +
                "        \"author\": \"admin\",\n" +
                "        \"name\": \"Medium Math Quiz\",\n" +
                "        \"questions\": \n" +
                "\t\t[\n" +
                "\t\t\t{\n" +
                "                \"question\": \"What is 8*6?\",\n" +
                "                \"answer\": \"54\",\n" +
                "                \"hint\": \"Between 50 and 60.\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"question\": \"What is 9*7?\",\n" +
                "                \"answer\": \"63\",\n" +
                "                \"hint\": \"Between 60 and 70.\"\n" +
                "            },\n" +
                "\t\t\t{\n" +
                "                \"question\": \"What is 20/5?\",\n" +
                "                \"answer\": \"4\",\n" +
                "                \"hint\": \"An even number.\"\n" +
                "            }\n" +
                "        ]\n" +
                "    },\n" +
                "\t{\n" +
                "        \"category\": \"Math\",\n" +
                "        \"difficulty\": \"Hard\",\n" +
                "        \"author\": \"admin\",\n" +
                "        \"name\": \"Hard Math Quiz\",\n" +
                "        \"questions\":\n" +
                "\t\t[\n" +
                "\t\t\t{\n" +
                "                \"question\": \"What is 9^9?\",\n" +
                "                \"answer\": \"81\",\n" +
                "                \"hint\": \"Between 80 and 100.\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"question\": \"What is 11^9\",\n" +
                "                \"answer\": \"99\",\n" +
                "                \"hint\": \"Between 90 and 100.\"\n" +
                "            },\n" +
                "\t\t\t{\n" +
                "                \"question\": \"What is 5 * 5^2\",\n" +
                "                \"answer\": \"125\",\n" +
                "                \"hint\": \"Around 125...\"\n" +
                "            }\n" +
                "        ]\n" +
                "    },\n" +
                "\t{\n" +
                "        \"category\": \"Geography\",\n" +
                "        \"difficulty\": \"Easy\",\n" +
                "        \"author\": \"admin\",\n" +
                "        \"name\": \"Easy Geography Quiz\",\n" +
                "        \"questions\":\n" +
                "\t\t[\n" +
                "\t\t\t{\n" +
                "                \"question\": \"How many continents are there?\",\n" +
                "                \"answer\": \"7\",\n" +
                "                \"hint\": \"Bigger than 5.\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"question\": \"What is bigger Canada or Mexico?\",\n" +
                "                \"answer\": \"Canada\",\n" +
                "                \"hint\": \"Not the southern country.\"\n" +
                "            }, \n" +
                "\t\t\t{\n" +
                "                \"question\": \"What continent is Australia in?\",\n" +
                "                \"answer\": \"Oceania\",\n" +
                "                \"hint\": \"Ocean Start\"\n" +
                "            }\n" +
                "        ]\n" +
                "    },\n" +
                "    {\n" +
                "        \"category\": \"Geography\",\n" +
                "        \"difficulty\": \"Medium\",\n" +
                "        \"author\": \"admin\",\n" +
                "        \"name\": \"Medium Geography Quiz\",\n" +
                "        \"questions\": \n" +
                "\t\t[\n" +
                "\t\t\t{\n" +
                "                \"question\": \"What is the capital of Canada?\",\n" +
                "                \"answer\": \"Ottawa\",\n" +
                "                \"hint\": \"Starts with O\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"question\": \"Where is Madrid located?\",\n" +
                "                \"answer\": \"Spain\",\n" +
                "                \"hint\": \"_ + Pain\"\n" +
                "            },\n" +
                "\t\t\t{\n" +
                "                \"question\": \"What is the biggest country in the world?\",\n" +
                "                \"answer\": \"Russia\",\n" +
                "                \"hint\": \"People have bears as pets.\"\n" +
                "            }\n" +
                "        ]\n" +
                "    },\n" +
                "\t{\n" +
                "        \"category\": \"Geography\",\n" +
                "        \"difficulty\": \"Hard\",\n" +
                "        \"author\": \"Admin\",\n" +
                "        \"name\": \"Hard Geography Quiz\",\n" +
                "        \"questions\":\n" +
                "\t\t[\n" +
                "\t\t\t{\n" +
                "                \"question\": \"What is the smallest country in the world?\",\n" +
                "                \"answer\": \"Vatican City\",\n" +
                "                \"hint\": \"It is a city-state.\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"question\": \"What city-state is in the south of France?\",\n" +
                "                \"answer\": \"Monaco\",\n" +
                "                \"hint\": \"Has a Formula 1 race that is popular held here.\"\n" +
                "            },\n" +
                "\t\t\t{\n" +
                "                \"question\": \"What country is to the right of Norway?\",\n" +
                "                \"answer\": \"Sweden\",\n" +
                "                \"hint\": \"Flag colors are blue and yellow.\"\n" +
                "            }\n" +
                "        ]\n" +
                "    },\n" +
                "\t{\n" +
                "        \"category\": \"Science\",\n" +
                "        \"difficulty\": \"Easy\",\n" +
                "        \"author\": \"admin\",\n" +
                "        \"name\": \"Easy Science Quiz\",\n" +
                "        \"questions\":\n" +
                "\t\t[\n" +
                "\t\t\t{\n" +
                "                \"question\": \"What is the chemical symbol for the element oxygen?\",\n" +
                "                \"answer\": \"O\",\n" +
                "                \"hint\": \"More circle than a 0.\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"question\": \"Is the Earth flat or round?\",\n" +
                "                \"answer\": \"Round\",\n" +
                "                \"hint\": \"not __\"\n" +
                "            }, \n" +
                "\t\t\t{\n" +
                "                \"question\": \"How many colours are there in a rainbow?\",\n" +
                "                \"answer\": \"7\",\n" +
                "                \"hint\": \"4 + Primarys\"\n" +
                "            }\n" +
                "        ]\n" +
                "    },\n" +
                "    {\n" +
                "        \"category\": \"Science\",\n" +
                "        \"difficulty\": \"Medium\",\n" +
                "        \"author\": \"admin\",\n" +
                "        \"name\": \"Medium Science Quiz\",\n" +
                "        \"questions\": \n" +
                "\t\t[\n" +
                "\t\t\t{\n" +
                "                \"question\": \"What is the hardest substance on earth?\",\n" +
                "                \"answer\": \"Diamond\",\n" +
                "                \"hint\": \"Pressure makes...\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"question\": \"What planet is refered to as the 'Red Planet'?\",\n" +
                "                \"answer\": \"Mars\",\n" +
                "                \"hint\": \"4th Planet\"\n" +
                "            },\n" +
                "\t\t\t{\n" +
                "                \"question\": \"How many billions of years old (to 1 decimal) is the earth?\",\n" +
                "                \"answer\": \"4.5 Billion\",\n" +
                "                \"hint\": \"Between 4 and 6 billion.\"\n" +
                "            }\n" +
                "        ]\n" +
                "    },\n" +
                "\t{\n" +
                "        \"category\": \"Science\",\n" +
                "        \"difficulty\": \"Hard\",\n" +
                "        \"author\": \"Admin\",\n" +
                "        \"name\": \"Hard Science Quiz\",\n" +
                "        \"questions\":\n" +
                "\t\t[\n" +
                "\t\t\t{\n" +
                "                \"question\": \"What percentage of the Earth is made up on water?\",\n" +
                "                \"answer\": \"71\",\n" +
                "                \"hint\": \"A little less than 3/4s.\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"question\": \"What is the heaviest planet?\",\n" +
                "                \"answer\": \"Jupiter\",\n" +
                "                \"hint\": \"Earth + 2\"\n" +
                "            },\n" +
                "\t\t\t{\n" +
                "                \"question\": \"What was Thomas Edison's most famous invention?\",\n" +
                "                \"answer\": \"Light Bulb\",\n" +
                "                \"hint\": \"Glows\"\n" +
                "            }\n" +
                "        ]\n" +
                "    }\n" +
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