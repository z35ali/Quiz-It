package com.zafar.quizit;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

public class QuizActivity extends AppCompatActivity {
    public static final String EXTRA_SCORE = "scorePassed";
    private static final long COUNTDOWN_IN_MILLIS = 31000; //31 seconds since timer starts at 29 if put to 30

    //Variables to handle landscape mode orientation
    private static final String KEY_SCORE = "keyScore";
    private static final String KEY_QUESTION_COUNT = "keyQuestionCount";
    private static final String KEY_MILLIS_LEFT = "keyMillisLeft";
    private static final String KEY_ANSWERED = "keyAnswered";
    private static final String KEY_QUESTION_LIST = "keyQuestionList";


    private TextView textViewQuestion;
    private TextView textViewScore;
    private TextView textViewQuestionCount;
    private TextView textViewDifficulty;
    private TextView textViewCategory;
    private TextView textViewCountdown;
    private RadioGroup rbGroup;
    private RadioButton rb1;
    private RadioButton rb2;
    private RadioButton rb3;
    private Button buttonConfirmNext;
    private ArrayList<Question> questionList;

    private ColorStateList textColorDefaultRb;
    private ColorStateList textColorDefaultCd;

    private CountDownTimer countDownTimer;
    private long timeLeftInMillis;


    private int questionCounter;
    private int questionCountTotal;
    private Question currentQuestion;

    private int score;
    private boolean answered;

    private long backPressedTime;
    String difficulty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        textViewQuestion = findViewById(R.id.text_view_question);
        textViewScore = findViewById(R.id.text_view_score);
        textViewQuestionCount= findViewById(R.id.text_view_question_count);
        textViewCountdown = findViewById(R.id.text_view_countdown);
        textViewDifficulty = findViewById(R.id.text_view_difficulty);
        textViewCategory = findViewById(R.id.text_view_category);
        rbGroup = findViewById(R.id.radio_group);
        rb1 = findViewById(R.id.radio_button1);
        rb2 = findViewById(R.id.radio_button2);
        rb3 = findViewById(R.id.radio_button3);
        buttonConfirmNext = findViewById(R.id.button_confirm_next);

        textColorDefaultRb = rb1.getTextColors();
        textColorDefaultCd = textViewCountdown.getTextColors();

        Intent intent = getIntent();
        int categoryID = intent.getIntExtra(StartingScreenActivity.EXTRA_CATEGORY_ID, 0);
        String categoryName = intent.getStringExtra(StartingScreenActivity.EXTRA_CATEGORY_NAME);

        difficulty = intent.getStringExtra(StartingScreenActivity.EXTRA_DIFFICULTY);
        textViewDifficulty.setText("Difficulty: " + difficulty);
        textViewDifficulty.setText("Category: " + categoryName);


        if(savedInstanceState == null) {
            QuizDbHelper dbHelper = QuizDbHelper.getInstance(this);
            questionList = dbHelper.getQuestions(categoryID,difficulty);
            questionCountTotal = questionList.size();
            Collections.shuffle(questionList);
            showNextQuestion();
        }else{
            questionList = savedInstanceState.getParcelableArrayList(KEY_QUESTION_LIST);

            if(questionList == null){
                finish();
            }

            questionCountTotal = questionList.size();
            questionCounter = savedInstanceState.getInt(KEY_QUESTION_COUNT);
            currentQuestion = questionList.get(questionCounter - 1);
            score = savedInstanceState.getInt(KEY_SCORE);
            timeLeftInMillis = savedInstanceState.getLong(KEY_MILLIS_LEFT);
            answered = savedInstanceState.getBoolean(KEY_ANSWERED);

            if(!answered){
                startCountDownTimer();

            }else{
                updateCountDownText();
                showSolution();
            }

        }

        buttonConfirmNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!answered) {
                    if (rb1.isChecked() || rb2.isChecked() || rb3.isChecked()) {
                        checkAnswer();
                    } else {
                        Toast.makeText(QuizActivity.this, "Please Select An Answer", Toast.LENGTH_SHORT);

                    }
                } else{
                        showNextQuestion();
                    }
                }

        });


    }

    private void showNextQuestion(){
        rb1.setTextColor(textColorDefaultRb);
        rb2.setTextColor(textColorDefaultRb);
        rb3.setTextColor(textColorDefaultRb);
        rbGroup.clearCheck();
        rb1.setEnabled(true);
        rb2.setEnabled(true);
        rb3.setEnabled(true);

        if(questionCounter < questionCountTotal){
          currentQuestion = questionList.get(questionCounter);

          textViewQuestion.setText(currentQuestion.getQuestion());
          rb1.setText(currentQuestion.getOption1());
          rb2.setText(currentQuestion.getOption2());
          rb3.setText(currentQuestion.getOption3());
          questionCounter++;
          textViewQuestionCount.setText("Question: " + questionCounter + "/" + questionCountTotal);
          answered = false;
          buttonConfirmNext.setText("Confirm");

          timeLeftInMillis = COUNTDOWN_IN_MILLIS;
          startCountDownTimer();
        }else{
            finishQuiz();
        }


    }

    private void startCountDownTimer(){
        countDownTimer = new CountDownTimer(timeLeftInMillis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeLeftInMillis = millisUntilFinished;
                updateCountDownText();
            }

            @Override
            public void onFinish() {

                //fixes bug where countdown timer ends at 1 not 0
                timeLeftInMillis = 0;
                updateCountDownText();
                checkAnswer();
            }
        }.start();
    }

    private void updateCountDownText(){
        int minutes = (int)(timeLeftInMillis / 1000) / 60;
        int seconds = (int) (timeLeftInMillis / 1000) % 60;

        String timeFormatted = String.format(Locale.getDefault(), "%02d:%02d", minutes,seconds);

        textViewCountdown.setText(timeFormatted);

        if(timeLeftInMillis < 10000){
            textViewCountdown.setTextColor(Color.RED);

        }else{
            textViewCountdown.setTextColor(textColorDefaultCd);
        }
    }
    private void checkAnswer(){
        answered = true;

        countDownTimer.cancel();
        RadioButton rbSelected = findViewById(rbGroup.getCheckedRadioButtonId());
        int answerNr = rbGroup.indexOfChild(rbSelected) + 1;

        if(answerNr == currentQuestion.getAnswerNr()){
            if(difficulty.equals("Easy")) {
                score++;
            }else if (difficulty.equals("Medium")){
                score+=2;

            }else{
                score+=3;
            }
            textViewScore.setText("Score: "+ score);
        }
        showSolution();
    }


    private void showSolution(){
        rb1.setTextColor(Color.RED);
        rb2.setTextColor(Color.RED);
        rb3.setTextColor(Color.RED);
        rb1.setEnabled(false);
        rb2.setEnabled(false);
        rb3.setEnabled(false);
        switch(currentQuestion.getAnswerNr()){
            case 1:
                rb1.setTextColor(Color.parseColor("#1177c9"));
                textViewQuestion.setText("Answer 1 is correct");
                break;
            case 2:
                rb2.setTextColor(Color.parseColor("#1177c9"));
                textViewQuestion.setText("Answer 2 is correct");
                break;
            case 3:
                rb3.setTextColor(Color.parseColor("#1177c9"));
                textViewQuestion.setText("Answer 3 is correct");
                break;

        }

        if (questionCounter < questionCountTotal){
            buttonConfirmNext.setText("Next");

        }else{
            buttonConfirmNext.setText("Finish");
        }
    }
    private void finishQuiz(){
        Intent resultIntent = new Intent();
        resultIntent.putExtra(EXTRA_SCORE, score);
        setResult(RESULT_OK, resultIntent);
        finish();

    }


    @Override
    public void onBackPressed() {
        if(backPressedTime + 2000 > System.currentTimeMillis()){
            finishQuiz();
        }else{
            Toast.makeText(this, "Press Back Again To Finish", Toast.LENGTH_SHORT).show();
        }
        backPressedTime = System.currentTimeMillis();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(countDownTimer !=null){
            countDownTimer.cancel();
        }
    }

    //Handler to save state when orienting screen
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(KEY_SCORE,score);
        outState.putInt(KEY_QUESTION_COUNT, questionCounter);
        outState.putLong(KEY_MILLIS_LEFT, timeLeftInMillis);
        outState.putBoolean(KEY_ANSWERED, answered);
        outState.putParcelableArrayList(KEY_QUESTION_LIST, questionList);
    }
}
