package com.example.zafar.cps731_group15_quizit;

import java.util.ArrayList;

public class Quiz {
    public ArrayList<Question> questions;
    public String name;
    public String author;
    public String category;

    public Quiz (String name, String author, String category) {

        this.name = name;
        this.author = author;
        this.category = category;

        questions = new ArrayList<>();
    }

    public void addQuestion(String question_text, String hint_text, String[] answer_texts, int correct_index) {
        questions.add(new Question(question_text, hint_text, answer_texts, correct_index));
    }
}
