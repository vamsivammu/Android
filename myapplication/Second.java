package com.example.a97053.myapplication;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import java.security.Signature;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Arrays.*;
import java.util.HashMap;
import java.util.Map;

import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.support.v4.view.GestureDetectorCompat;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class Second extends AppCompatActivity implements View.OnClickListener{
//    ArrayList<String> regpasswords;
//    ArrayList<String> regnames;
//    ArrayList<String> regemails;
    //    ArrayList<String> names = getName();
//    ArrayList<String> emails = getEmail();
//    ArrayList<String> passwords = getPassword();
    Button b3;
    ProgressDialog pd;
    public String regex_email = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
       // Bundle extras = getIntent().getExtras();

//        regnames = extras.getStringArrayList("names");
//
//        regemails = extras.getStringArrayList("emails");
//
//        regpasswords = extras.getStringArrayList("passwords");
        b3 = (Button)findViewById(R.id.login);
        b3.setOnClickListener(this);
        reg();

    }

    @Override
    public void onClick(View view) {
        if(view == b3){
            login();
        }
    }

public void login(){

                TextView t = (TextView)findViewById(R.id.textView3);
                EditText email = (EditText)findViewById(R.id.logemail);
                EditText pass = (EditText)findViewById(R.id.logpass);
                final String em = email.getText().toString();
                final String pa = pass.getText().toString();
                Boolean b = em.matches(regex_email);
                if(b){
                    t.setVisibility(View.INVISIBLE);
                    pd.setMessage("Logging in....");
                    pd.show();
                    StringRequest sr = new StringRequest(Request.Method.POST, "https://vammuvamsi64.000webhostapp.com/login/index.php", new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            pd.dismiss();
                            try {
                                JSONObject o = new JSONObject();
                                Toast.makeText(getApplicationContext(), o.getString("log"), Toast.LENGTH_LONG).show();
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(getApplicationContext(),error.getMessage(),Toast.LENGTH_LONG).show();
                        }
                    }){
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            Map <String,String> params = new HashMap<>();
                            params.put("logemail",em);
                            params.put("logpass",pa);
                            return params;
                        }
                    };

                    Singleton.getInstance(this).addToRequestQueue(sr);

                }else{

                    t.setVisibility(View.VISIBLE);
                    t.setText("Invalid Email");
                }



    }

//                if(regemails.contains(em)){
//                    int i = regemails.indexOf(em);
//                    boolean b = (regpasswords.get(i).equals(pa));
//                    if(b){
//                        t.setVisibility(View.VISIBLE);
//                         t.setText("login successful");
//                    }else{
//                        t.setVisibility(View.VISIBLE);
//                        t.setText("wrong  password");
//
//                    }
//
//                }else{
//                    t.setVisibility(View.VISIBLE);
//                    t.setText("not registered");
//                }






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
