package com.zafar.quizit;

import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    public static EditText mTextUsername;
    public static EditText mTextPassword;
    Button mButtonLogin;
    TextView mTextViewRegister;
    DatabaseHelper db;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);



        db = new DatabaseHelper(this);
        mTextUsername = (EditText)findViewById(R.id.edittext_username);
        mTextPassword = (EditText)findViewById(R.id.edittext_password);
        mButtonLogin = (Button)findViewById(R.id.button_login);
        mTextViewRegister = (TextView)findViewById(R.id.textview_register);

        db.addUser("adminAdd","adminAdd");
        db.addUser("adminPlay","adminPlay");


        mTextViewRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent registerIntent = new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(registerIntent);
            }
        });

        mButtonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = mTextUsername.getText().toString().trim();
                String pwd = mTextPassword.getText().toString().trim();
                Boolean res = db.checkUser(user, pwd);
                if(res == true)
                {

                    if(user.equals("adminAdd") && (pwd.equals("adminAdd"))) {
                        Intent HomePage = new Intent(LoginActivity.this, AddQuestionActivity.class);
                        startActivity(HomePage);
                    }else if(user.equals("adminPlay") && (pwd.equals("adminPlay"))) {
                        Intent PlayPage = new Intent(LoginActivity.this, StartingScreenActivity.class);
                        PlayPage.putExtra("user","adminPlay");
                        startActivity(PlayPage);
                }else{
                        Intent HomePage = new Intent(LoginActivity.this,StartingScreenActivity.class);
                        HomePage.putExtra("user",user);
                        startActivity(HomePage);
                    }

                }
                else
                {
                    Toast.makeText(LoginActivity.this,"Login Error",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


}
