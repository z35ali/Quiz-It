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

        db.addUser("adminQuest","adminQuest");
        db.addUser("adminCat","adminCat");
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

                    if(user.equals("adminQuest") && (pwd.equals("adminQuest"))) {
                        Intent HomePage = new Intent(LoginActivity.this, AddQuestionActivity.class);
                        startActivity(HomePage);
                    }else if(user.equals("adminCat") && (pwd.equals("adminCat"))) {
                        Intent CatPage = new Intent(LoginActivity.this, AddCategoryActivity.class);
                        startActivity(CatPage);
                }else if(user.equals("adminPlay") && (pwd.equals("adminPlay"))) {
                        Intent PlayPage = new Intent(LoginActivity.this, StartingScreenActivity.class);
                        PlayPage.putExtra("user", "adminPlay");
                        startActivity(PlayPage);
                    }else{
                        Intent HomePage = new Intent(LoginActivity.this,StartingScreenActivity.class);
                        HomePage.putExtra("user",user);
                        startActivity(HomePage);
                    }

                }
                else
                {
                    if(user.equals("")||(pwd.equals(""))){
                        Toast.makeText(LoginActivity.this,"Please Fill In All The Fields",Toast.LENGTH_SHORT).show();

                    }else{
                        Toast.makeText(LoginActivity.this,"Invalid Username or Password",Toast.LENGTH_SHORT).show();

                    }

                }
            }
        });
    }


}
