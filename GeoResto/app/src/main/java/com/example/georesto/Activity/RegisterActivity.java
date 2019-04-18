package com.example.georesto.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.georesto.Model.Profile;
import com.example.georesto.Model.ProfileList;
import com.example.georesto.R;


public class RegisterActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        EditText mail = (EditText) findViewById(R.id.newMail);
        EditText username = (EditText) findViewById(R.id.newUsername);
        EditText password = (EditText) findViewById(R.id.newPassword);
        EditText passwordConfirmation = (EditText) findViewById(R.id.newPasswordConfirmation);
        EditText firstName = (EditText) findViewById(R.id.first_name);
        EditText lastName = (EditText) findViewById(R.id.last_name);
        TextView wrongNewProfile = (TextView) findViewById(R.id.wrongNewProfile);
        Button loginButton = (Button) findViewById(R.id.connect);
        Button registerButton = (Button) findViewById(R.id.register);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
            }
        });

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                wrongNewProfile.setText(ProfileList.addProfile(new Profile(username.getText().toString(),password.getText().toString(),firstName.getText().toString(),lastName.getText().toString(),mail.getText().toString(), null), passwordConfirmation.getText().toString()));
                if(wrongNewProfile.getText().toString().equals("Connexion en cours..")) {
                    startActivity(new Intent(RegisterActivity.this, MapsActivityOnline.class));
                }
            }
        });
    }
}
