package com.example.a97053.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
//   public ArrayList <String> names = new ArrayList<String>();
//   public  ArrayList <String> emails = new ArrayList<String>();
//    public ArrayList <String> passwords = new ArrayList<String>();
//    public ArrayList<String> getEmails() {
//        return emails;
//    }
//
//    public ArrayList<String> getNames() {
//        return names;
//    }
//    public ArrayList<String> getPasswords() {
//        return passwords;
//    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gotologinpage();
        gotosignuppage();

    }



    //   public void onclick(){
//
//       Button alert = (Button) findViewById(R.id.button);
//    alert.setOnClickListener(new View.OnClickListener() {
//        @Override
//        public void onClick(View v) {
//            AlertDialog.Builder ab = new AlertDialog.Builder(MainActivity.this);
//            ab.setMessage("do u want to close").setCancelable(false).setPositiveButton("yes", new DialogInterface.OnClickListener() {
//                @Override
//                public void onClick(DialogInterface dialog, int which) {
//                    finish();
//                }
//            }).setNegativeButton("no", new DialogInterface.OnClickListener() {
//                @Override
//                public void onClick(DialogInterface dialog, int which) {
//                    dialog.cancel();
//                }
//            });
//            AlertDialog a = ab.create();
//            a.setTitle("Close");
//            a.show();
//        }
//    });
//
//
//    }

    public void gotologinpage(){

        Button act = (Button)findViewById(R.id.button);
        act.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent("com.example.a97053.log");
                startActivity(i);
            }
        });

    }

    public void gotosignuppage(){

        Button reg = (Button)findViewById(R.id.button2);
        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i1 = new Intent("com.example.a97053.reg");
                startActivity(i1);
            }
        });

    }

}
