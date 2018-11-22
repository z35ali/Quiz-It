package com.example.zafar.cps731_group15_quizit;

public class Question {
    public int correct_index;
    public String question_text;
    public String hint_text;
    public String[] answer_texts;

    public Question (String question_text, String hint_text, String[] answer_texts, int correct_index) {

        this.question_text = question_text;
        this.hint_text = hint_text;
        this.answer_texts = answer_texts;
        this.correct_index = correct_index;
    }

    public String toString() { //just for testing
        String out = ">> " + question_text + '\n';
        for (String s : answer_texts) {
            out += "> " + s + '\n';
        }
        out += '\n' + "Hint: " + hint_text + '\n';

        return out;
    }
}
