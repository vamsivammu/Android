package com.example.a97053.myapplication;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.provider.SyncStateContract;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;


public class Registration extends MainActivity implements View.OnClickListener{
    //protected String[] names = getNames();
    //protected String[] emails = getEmails();
    //protected String[] passwords = getPasswords();
//    ArrayList <String> names = getNames();
//    ArrayList <String> emails = getEmails();
//    ArrayList <String> passwords = getPasswords();
//
//        public ArrayList<String> getName() {
//        return names;
//    }
//
//
//    public ArrayList<String> getPassword() {
//        return passwords;
//    }
//
//
//    public ArrayList<String> getEmail() {
//        return emails;
//    }
    boolean registered = false;
    DatabaseHelper db;
    ProgressDialog pd ;

    Button b;
    public String regex_email = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$";
//    public Pattern pattern = Pattern.compile(regex_email,Pattern.CASE_INSENSITIVE);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_registration);
        db = new DatabaseHelper(this);
        pd = new ProgressDialog(this);
        b = (Button)findViewById(R.id.signup);
        signinpage();
        b.setOnClickListener(this);
    //onchange();
    }

//    public void onchange(){
//        final EditText ema = (EditText)findViewById(R.id.logemail);
//        final TextView error_ema = (TextView)findViewById(R.id.textView);
//
//        ema.setOnKeyListener(new View.OnKeyListener() {
//        String e = ema.getText().toString();
//        @Override
//        public boolean onKey(View v, int keyCode, KeyEvent event) {
//
//            if(event.getAction() == KeyEvent.ACTION_DOWN || event.getAction() == KeyEvent.ACTION_UP ){
//                if (!e.matches(regex_email)) {
//                    error_ema.setVisibility(View.VISIBLE);
//                } else {
//                    error_ema.setVisibility(View.INVISIBLE);
//
//                }
//                return true;
//            }
//            return false;
//        }
//    });
//
//    }
    public void register(){

                final EditText name = (EditText)findViewById(R.id.name);
                final EditText email = (EditText)findViewById(R.id.logemail);
                final EditText pass = (EditText)findViewById(R.id.regpass);
                final EditText confpass = (EditText)findViewById(R.id.confpass);
                TextView error_email = (TextView)findViewById(R.id.textView);
                TextView error_pass = (TextView)findViewById(R.id.errpass);
                TextView success = (TextView)findViewById(R.id.textView2);

                final String em = email.getText().toString();
                final String n = name.getText().toString();
                final String p = pass.getText().toString();
                final String cp = confpass.getText().toString();
                boolean b = em.matches(regex_email);
                if(b) {
                    error_email.setVisibility(View.INVISIBLE);
                    if(p.equals(cp) && p.length()>6){
                        registered = true;
                        pd.setMessage("Creating a new account....");
                        pd.show();
                        StringRequest sr = new StringRequest(Request.Method.POST, "https://vammuvamsi64.000webhostapp.com", new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                pd.dismiss();
                                name.setText("");
                                email.setText("");
                                pass.setText("");
                                confpass.setText("");

                                try{
                                    JSONObject j  = new JSONObject(response);
                                    if(!j.getBoolean("err")){
                                        Toast.makeText(getApplicationContext(),j.getString("message"),Toast.LENGTH_LONG).show();
                                    }else{
                                        Toast.makeText(getApplicationContext(),j.getString("message"),Toast.LENGTH_LONG).show();
                                    }

                                }catch (JSONException e){
                                    e.printStackTrace();
                                }
                                //Toast.makeText(getApplicationContext(),response,Toast.LENGTH_LONG).show();
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
                                params.put("name",n);
                                params.put("email",em);
                                params.put("password",p);
                                return params;
                            }
                        };

                        Singleton.getInstance(this).addToRequestQueue(sr);

                        error_pass.setVisibility(View.INVISIBLE);
//                        names.add(n);
//                        emails.add(em);
//                        passwords.add(p);

//                       names[-1] = n;
//                       emails[-1] = em;
//                       passwords[-1] = p;
//                       name.setText("");
//                       email.setText("");
//                       confpass.setText("");
//                       pass.setText("");
//                       success.setVisibility(View.VISIBLE);

                    }else if(p.equals(cp) && p.length()<=6){
                        success.setVisibility(View.INVISIBLE);
                        error_pass.setVisibility(View.VISIBLE);
                        error_pass.setText("Password length should be more than 6");
                    }else{
                        success.setVisibility(View.INVISIBLE);
                        error_pass.setVisibility(View.VISIBLE);
                        error_pass.setText("Passwords donot match");
                    }

                }else{
                    error_email.setVisibility(View.VISIBLE);
                    if(p.equals(cp) && p.length()<=6){
                        success.setVisibility(View.INVISIBLE);
                        error_pass.setVisibility(View.VISIBLE);
                        error_pass.setText("Password length should be more than 6");

                    }else if(p.equals(cp) && p.length()>6){

                        error_pass.setVisibility(View.INVISIBLE);
                    }
                    else{
                        success.setVisibility(View.INVISIBLE);
                        error_pass.setVisibility(View.VISIBLE);
                        error_pass.setText("Passwords donot match");
                    }
            }


    }
    public void signinpage(){

        Button b = (Button)findViewById(R.id.button3);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i2 = new Intent("com.example.a97053.log");
                startActivity(i2);
            }
        });

    }

    @Override
    public void onClick(View view) {

        if(view == b){
            register();

        }

    }
}
