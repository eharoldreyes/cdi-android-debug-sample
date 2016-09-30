package com.cdi.debugtest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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

                if("".equals(password))
                    throw new Error("Invalid input");


                for (int i = 0; i < 10; i++) {
                    Log.d("Loop", "Iteration: " + i);
                    if(i == 8){
                        String something = makeSomething();
                        Log.d("something", something);
                    }
                }


                Toast.makeText(SignIn.this, "Welcome " + email, Toast.LENGTH_LONG).show();
            }
        });
    }

    private String makeSomething() {
        throw new Error("What at Terrible Failure");
    }
}
