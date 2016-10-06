package com.cdi.debugtest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.cdi.debugtest.models.User;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        User user = getIntent().getParcelableExtra("Key");

        if(user != null){
            Log.d("User", user.toString());

            Log.d("Roles", Arrays.asList(user.getRoles()).toString());

        }
    }
}
