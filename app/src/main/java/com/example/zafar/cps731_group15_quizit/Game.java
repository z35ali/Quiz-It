package com.example.zafar.cps731_group15_quizit;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Game extends AppCompatActivity {

    private TextView mTextMessage;
    private Button logout;
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    mTextMessage.setText("Game");
                    return true;
                case R.id.navigation_dashboard:
                    mTextMessage.setText("Custom Quizzes");
                    return true;
                case R.id.navigation_notifications:
                    mTextMessage.setText("Store and Report Bugs");
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game3);

        mTextMessage = (TextView) findViewById(R.id.message);
        logout = (Button)findViewById(R.id.logout);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);


        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(Game.this, MainActivity.class);
                startActivity(intent);

            }
        });

    }
    }




