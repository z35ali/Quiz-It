package com.example.zafar.cps731_group15_quizit;

import java.util.ArrayList;

public class Quiz {
    public ArrayList<String> questions;
    public ArrayList<String> answers;
    public ArrayList<String> hints;

    public String name;
    public String author;
    public String category;
    public String difficulty;

    public Quiz (String name, String author, String category, String difficulty) {

        this.name = name;
        this.author = author;
        this.category = category;
        this.difficulty = difficulty;

        questions = new ArrayList<>();
        answers = new ArrayList<>();
        hints = new ArrayList<>();
    }

    public void addQuestion(String question_text, String hint_text, String answer_text) {
        questions.add(question_text);
        hints.add(hint_text);
        answers.add(answer_text);
    }
}