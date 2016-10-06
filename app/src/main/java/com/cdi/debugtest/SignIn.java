package com.cdi.debugtest;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.cdi.debugtest.models.User;

import org.json.JSONException;
import org.json.JSONObject;

public class SignIn extends AppCompatActivity {

    private EditText etEmail;
    private EditText etPassword;
    private Button bSignIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        getViews();

        listenViews();
    }

    private void getViews(){
        etEmail = (EditText) findViewById(R.id.etEmail);
        etPassword = (EditText) findViewById(R.id.etPassword);
        bSignIn = (Button) findViewById(R.id.bSignIn);
    }

    private void listenViews() {
        bSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = etEmail.getText().toString();
                String password = etPassword.getText().toString();

                User user = new User();
                try {
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("email", email);
                    jsonObject.put("password", password);
                    jsonObject.put("age", 21);
                    jsonObject.put("isActive", true);
                    jsonObject.put("lat", 14.188244);
                    jsonObject.put("lng", 121.238638);
                    user = new User(jsonObject);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                user.setRoles(new String[]{"Admin", "User"});

                Intent intent = new Intent(SignIn.this, MainActivity.class);
                intent.putExtra("Key", user);
                Log.d("User", intent.getParcelableExtra("Key").toString());

                startActivity(intent);

                Toast.makeText(SignIn.this, "Welcome " + user.getEmail(), Toast.LENGTH_LONG).show();
                finish();
            }
        });
    }

    private String makeSomething() {

//                Log.d("tag", password);
//                if("".equals(password))
//                    throw new Error("Invalid input");
//
//                for (int i = 0; i < 10; i++) {
//                    Log.d("Loop", "Iteration: " + i);
//                    if(i == 8){
//                        String something = makeSomething();
//                        Log.d("something", something);
//                    }
//                }

        throw new Error("What at Terrible Failure");
    }
}
