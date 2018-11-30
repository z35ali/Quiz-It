package com.example.zafar.cps731_group15_quizit;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.os.CountDownTimer;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;


public class Play extends AppCompatActivity {

    //Layout variables
    private TextView timer;
    public static TextView question;
    public static TextView category;
    public static TextView difficulty;
    public static TextView pointsTV;
    public static TextView scoreTV;
    private TextView answerET;
    private Button logoutbtn;
    private Button submit;
    private Button hintsSubmit;
    private TextView hintsTV;
    public int index;
    public boolean found=false;
    public String username;
    public String password;

    CountDownTimer t;

    int quizCount;
    int questionCount;
    int customQuestionCount;
    int score=0;
   public int points;
    long millis;
    boolean timerChoice;
    String categoryChoice;
    String difficultyChoice;
    Quiz currentQuiz;
    int customQuizCount;

    FetchData fetch;
    CustomQuiz cq;


    public static ArrayList <Quiz> customQuizzes = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);


        fetch= new FetchData();
        fetch.fetchQuizzes();

        username=(getIntent().getExtras().getString("username"));
        points=(getIntent().getExtras().getInt("points"));
        password=(getIntent().getExtras().getString("password"));
        //sets Layout variables to layout items
        logoutbtn = (Button) findViewById(R.id.logoutbtn);
        timer=(TextView)findViewById(R.id.timeTV);
        category=(TextView)findViewById(R.id.categoryTV);
        question=(TextView)findViewById(R.id.questionTV);
        difficulty=(TextView)findViewById(R.id.difficultyTV);
        answerET = (TextView)findViewById(R.id.answerET);

        scoreTV=(TextView)findViewById(R.id.scoreTV);


        pointsTV=(TextView)findViewById(R.id.pointsTV);
        submit=(Button) findViewById(R.id.submitAnswerBtn);
        hintsSubmit= (Button)findViewById(R.id.hintBtn);
        hintsTV=(TextView)findViewById(R.id.hintTV);
        pointsTV.setText("Points: "+points);
        //Gets the variables passed from the QuizOptions activity
        timerChoice= getIntent().getExtras().getBoolean("timerChoice");
        categoryChoice= getIntent().getExtras().getString("category");
        difficultyChoice= getIntent().getExtras().getString("difficulty");

        fetchQuiz(categoryChoice, difficultyChoice);

        //Checker that the variables were passed
       // category.setText("Your Category is: " + categoryChoice);




        //onclick listener for logout button
        logoutbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //changes activity from current to Login
                Intent intent= new Intent(Play.this, Login.class);
                intent.putExtra("usernameFromPlay",username);
                intent.putExtra("pointsFromPlay",points);
                intent.putExtra("loggedOut",true);
                intent.putExtra("passwordFromPlay",password);
                startActivity(intent);

            }
        });



        //onclick listener for submit button
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (difficultyChoice.equals("Custom")) {
                    String answer = answerET.getText().toString().toLowerCase();
                    String quizAnswer = customQuizzes.get(customQuizCount).answers.get(customQuestionCount).toLowerCase();

                    if (answer.equals(quizAnswer)) {
                        score++;
                        points = points + 5;
                    } else {
                       // score--;
                        points = points - 5;
                    }

                    customQuestionCount++;
                    millis = 10000;
                    answerET.setText("");

                    changeQuestion();
                } else {
                    String answer = answerET.getText().toString().toLowerCase();
                    String quizAnswer = fetch.quizzes.get(quizCount).answers.get(questionCount).toLowerCase();
                    if (answer.equals(quizAnswer)) {
                        score++;
                        points = points + 5;
                    } else {
                       // score--;
                        points = points - 5;
                    }

                    questionCount++;
                    millis = 10000;
                    answerET.setText("");
                    //  t.cancel();
                    changeQuestion();
                }
            }
        });

        //onclick listener for hints button
        hintsSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (difficultyChoice.equals("Custom")) {
                    Toast.makeText(Play.this, "CUSTOM QUIZZES DO NOT HAVE HINTS", Toast.LENGTH_SHORT).show();
                } else {
                    if (points > 0) {
                        points -= 5;
                        hintsTV.setText(fetch.quizzes.get(quizCount).hints.get(questionCount));
                        pointsTV.setText("Points: " + points);
                    } else {
                        Toast.makeText(Play.this, "YOU HAVE NO POINTS", Toast.LENGTH_SHORT).show();
                        pointsTV.setText("Points: " + points);
                    }

                }
            }

        });

    }


    public void fetchQuiz(String cat, String diff){

        for (Quiz q: fetch.quizzes) {
            if(q.category.equals(cat) && q.difficulty.equals(diff)){
                quizCount=fetch.quizzes.indexOf(q);
                //    question.setText("This quiz number is:"+ fetch.quizzes.size());

                //quizCount= fetch.quizzes.indexOf(q);
                changeQuestion();
            }
        }

        ArrayList<Integer> indexes = new ArrayList<Integer>();
        for (Quiz p: customQuizzes) {
            if(p.category.equals(cat)) {


                found=true;

                indexes.add(customQuizzes.indexOf(p));
            }
            }
            if(found&&difficultyChoice.equals("Custom")){
            Random rand = new Random();
            Integer randomInt = indexes.get(rand.nextInt(indexes.size()));

                customQuizCount= randomInt;
                changeQuestion();
        }
            if(!found&&difficultyChoice.equals("Custom")){
            customQuizCount=-1;
                Intent intent= new Intent(Play.this, QuizOptions.class);
                startActivity(intent);
                finish();
            }





        }


    public void changeQuestion(){

        if(!(customQuestionCount==3||questionCount==3)) {


            if (difficultyChoice.equals("Custom")) {
                if (customQuestionCount < customQuizzes.get(customQuizCount).questions.size()) {
                    cq = new CustomQuiz();
                    question.setText(customQuizzes.get(customQuizCount).questions.get(customQuestionCount));

                    category.setText("Category: " + customQuizzes.get(customQuizCount).category);
                    difficulty.setText("Difficulties: " + customQuizzes.get(customQuizCount).difficulty);
                    scoreTV.setText("Score: " + score);
                    pointsTV.setText("Points: " + points);

                    if (timerChoice) {
                        millis = 10000;

                        if (customQuestionCount == 0) {
                            startTimer(millis, 1000);
                        } else if (customQuestionCount > 0) {
                            t.cancel();
                            millis = 10000;
                            startTimer(millis, 1000);
                        }

                    }


                }
            } else if (questionCount < fetch.quizzes.get(quizCount).questions.size()) {
                question.setText(fetch.quizzes.get(quizCount).questions.get(questionCount));

                category.setText("Category: " + fetch.quizzes.get(quizCount).category);
                difficulty.setText("Difficulties: " + fetch.quizzes.get(quizCount).difficulty);
                scoreTV.setText("Score: " + score);
                pointsTV.setText("Points: " + points);
                // timer.setText("Time Left: " + millis);// manage it according to you
                if (timerChoice) {
                    millis = 10000;

                    if (questionCount == 0) {
                        startTimer(millis, 1000);
                    } else if (questionCount > 0) {
                        t.cancel();
                        millis = 10000;
                        startTimer(millis, 1000);
                    }

                }
            }

        }else{


                question.setText("");
                hintsTV.setText("Your Score is: " + score+"/3");
            hintsTV.append(" \n Your Points is: " + points);
                difficulty.setText(" ");
                category.setText(" ");
                scoreTV.setText(" ");
                pointsTV.setText(" ");
                timer.setText(" ");
                if(timerChoice) {
                    t.cancel();
                }



                submit.setVisibility(View.GONE);
                hintsSubmit.setVisibility(View.GONE);
                answerET.setVisibility(View.GONE);


            }
            }





    private void startTimer(final long finish, long tick){

    t = new CountDownTimer(finish, tick) {


        public void onTick(long millisUntilFinished) {
            long remainedSecs = millisUntilFinished / 1000;
            timer.setText("Time Left: " + (remainedSecs / 60) + ":" + (remainedSecs % 60));// manage it according to you
        }

        public void onFinish() {
            timer.setText("00:00:00");
            Toast.makeText(Play.this, "Finish", Toast.LENGTH_SHORT).show();

                millis = 10000;
                score=score-1;
                points=points-5;

                if(difficultyChoice.equals("Custom")){
                    customQuestionCount++;
                    changeQuestion();
                }else {
                    questionCount++;
                    changeQuestion();
                }


            cancel();
        }
    }.start();

}

}


