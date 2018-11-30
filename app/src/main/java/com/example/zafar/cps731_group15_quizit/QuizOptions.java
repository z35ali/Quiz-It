package com.example.zafar.cps731_group15_quizit;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class QuizOptions extends AppCompatActivity {

    public static String[] categories [];





    //category array that will be later replaced by SQL fetch
    private String[] dummyArray = {"Math", "Science", "Geography"};
    private String[] difficultyArray = {"Easy", "Medium", "Hard", "Custom"};

   //timer selection
    private boolean timerChoice = false;

    //Layout items
    private Button logoutbtn;
    private Button playbtn;
    private CheckBox timer;
    private Spinner dropdown;
    private Spinner difficulty;
    private TextView usernameText;
    private TextView pointsText;




    //chosen category from spinner gets saved in this variable
    private String categoryChoice = "";
    private String difficultyChoice="";

    public String username;
    public int points;
    public String password;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_options);


        //Sets layout items to variables
        timer = (CheckBox) findViewById(R.id.timerOption);
        logoutbtn = (Button) findViewById(R.id.logoutbtn);
        playbtn = (Button) findViewById(R.id.playbtn);
        usernameText=(TextView)findViewById(R.id.usernameTV) ;
        pointsText= (TextView)findViewById(R.id.pointsTV);


        username=(getIntent().getExtras().getString("username"));
        points=(getIntent().getExtras().getInt("points"));
        password=(getIntent().getExtras().getString("password"));

        pointsText.setText(""+points);
        usernameText.setText(username);
        //onclick for logout button
        logoutbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //changes activity from current to Login page
                Intent intent = new Intent(QuizOptions.this, Login.class);
                startActivity(intent);

            }
        });

        //onclick for play button
        playbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Play play = new Play();

                if (play.customQuizzes.isEmpty()&&categoryChoice.equals("Custom")) {
                    Toast.makeText(QuizOptions.this, "There Are No Custom Quizzes Created", Toast.LENGTH_SHORT).show();


                } else {
                    if (timerChoice) {

                        //Changes the activity from current to Play page and passes the category and timer true
                        Intent intent = new Intent(QuizOptions.this, Play.class);
                        intent.putExtra("timerChoice", true);
                        intent.putExtra("category", categoryChoice);
                        intent.putExtra("difficulty", difficultyChoice);
                        intent.putExtra("username", username);
                        intent.putExtra("password", password);
                        intent.putExtra("points", points);
                        startActivity(intent);


                    } else {

                        //Changes the activity from current to Play page and passes the category and timer false
                        Intent intent = new Intent(QuizOptions.this, Play.class);
                        intent.putExtra("timerChoice", false);
                        intent.putExtra("category", categoryChoice);
                        intent.putExtra("difficulty", difficultyChoice);
                        intent.putExtra("username", username);
                        intent.putExtra("password", password);
                        intent.putExtra("points", points);
                        startActivity(intent);
                    }


                }
            }
        });

        //onclick for timer selection checkbox
        timer.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (v == timer) {
                    timerChoice = true;
                }else{
                    timerChoice=false;



                }
            }



        });

        //sets spinner to the array
        dropdown = findViewById(R.id.categorySpinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, dummyArray);
        dropdown.setAdapter(adapter);
        dropdown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {


            //Called when a spinner item is selected and stores the selection in categoryChoice
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                //use position value

                switch (position)

                {

                    case 0:

                        categoryChoice=dummyArray[0];

                        break;

                    case 1:

                        categoryChoice = dummyArray[1];

                        break;

                    case 2:

                        categoryChoice = dummyArray[2];

                        break;

                    case 3:

                        categoryChoice = dummyArray[3];

                        break;




                }

            }

            @Override

            public void onNothingSelected(AdapterView<?> parent) {

            }

        });



        //sets spinner to the array
        difficulty= findViewById(R.id.difficultySpinner);
        ArrayAdapter<String> adapter1 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, difficultyArray);
        difficulty.setAdapter(adapter1);
        difficulty.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {


            //Called when a spinner item is selected and stores the selection in difficultyChoice
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                //use position value

                switch (position)

                {

                    case 0:

                        difficultyChoice=difficultyArray[0];

                        break;

                    case 1:

                        difficultyChoice = difficultyArray[1];

                        break;

                    case 2:

                        difficultyChoice = difficultyArray[2];

                        break;

                    case 3:

                        difficultyChoice = difficultyArray[3];

                        break;



                }

            }

            @Override

            public void onNothingSelected(AdapterView<?> parent) {

            }

        });




    }
    }
