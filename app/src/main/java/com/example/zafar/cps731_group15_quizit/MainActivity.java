package com.example.zafar.cps731_group15_quizit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {
    private EditText name;
    private EditText password;
    private TextView info;
    private Button login;
    private int count=5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name=(EditText)findViewById(R.id.etName);
        password=(EditText)findViewById(R.id.etPassword);
        info=(TextView)findViewById(R.id.tvInfo);
        login = (Button)findViewById(R.id.btnLogin);

        info.setText("No of attempts remaining: 5");

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validate(name.getText().toString(),password.getText().toString());

            }
        });

    }

    private void validate(String userName, String userPassword){
        if((userName.equals("admin")) && (userPassword.equals("1234"))){
            Intent intent= new Intent(MainActivity.this, Activity2.class);
            startActivity(intent);
        }else{
            count--;
            info.setText("No of attempts remaining:" + String.valueOf(count));

            if(count==0){
                login.setEnabled(false);
            }
        }

    }
}
