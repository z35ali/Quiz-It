package com.example.zafar.cps731_group15_quizit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

public class ReportAdmin extends AppCompatActivity {

    private TextView reports;
    private Button logoutbtn;

    Report userReports = new Report();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_admin);


        logoutbtn = (Button) findViewById(R.id.logoutbtn);


        reports= (TextView)findViewById(R.id.reportsTV);

        for (int i=0; i<userReports.reportName.size();i++){
            reports.append("\n Name: " + userReports.reportName.get(i)+"\n");
            reports.append("\n Description: " + userReports.reportDetails.get(i) + "\n \n");

        }






        //onclick listener for logout button
        logoutbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //changes activity from current to Login
                Intent intent= new Intent(ReportAdmin.this, Login.class);
                startActivity(intent);

            }
        });
    }
}
