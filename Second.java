package com.example.a97053.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Arrays.*;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.support.v4.view.GestureDetectorCompat;

public class Second extends AppCompatActivity{
    ArrayList<String> regpasswords;
    ArrayList<String> regnames;
    ArrayList<String> regemails;
    //    ArrayList<String> names = getName();
//    ArrayList<String> emails = getEmail();
//    ArrayList<String> passwords = getPassword();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        Bundle extras = getIntent().getExtras();

        regnames = extras.getStringArrayList("names");

        regemails = extras.getStringArrayList("emails");

        regpasswords = extras.getStringArrayList("passwords");

        reg();
    login();
    }

public void login(){

        Button b3 = (Button)findViewById(R.id.login);

        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView t = (TextView)findViewById(R.id.textView3);
                EditText email = (EditText)findViewById(R.id.logemail);
                EditText pass = (EditText)findViewById(R.id.logpass);
                String em = email.getText().toString();
                String pa = pass.getText().toString();


                if(regemails.contains(em)){
                    int i = regemails.indexOf(em);
                    boolean b = (regpasswords.get(i).equals(pa));
                    if(b){
                        t.setVisibility(View.VISIBLE);
                         t.setText("login successful");
                    }else{
                        t.setVisibility(View.VISIBLE);
                        t.setText("wrong  password");

                    }

                }else{
                    t.setVisibility(View.VISIBLE);
                    t.setText("not registered");
                }

            }
        });


}


public void reg(){
    Button b2 = (Button)findViewById(R.id.button5);
    b2.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent i3 = new Intent("com.example.a97053.reg");
            startActivity(i3);
        }
    });


}


}
