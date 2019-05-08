package com.example.georesto.Activity;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;

import com.example.georesto.Model.RestaurantList;
import com.example.georesto.R;
import com.example.georesto.Model.Profile;
import com.example.georesto.Model.ProfileList;


public class RegisterActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);

        findViewById(R.id.accessProfile).setVisibility(View.GONE);
        findViewById(R.id.usernameProfile).setVisibility(View.GONE);
        findViewById(R.id.mailProfile).setVisibility(View.GONE);


        EditText mail = (EditText) findViewById(R.id.newMail);
        EditText username = (EditText) findViewById(R.id.newUsername);
        EditText password = (EditText) findViewById(R.id.newPassword);
        EditText passwordConfirmation = (EditText) findViewById(R.id.newPasswordConfirmation);
        EditText firstName = (EditText) findViewById(R.id.first_name);
        EditText lastName = (EditText) findViewById(R.id.last_name);
        TextView wrongNewProfile = (TextView) findViewById(R.id.wrongNewProfile);
        Button loginButton = (Button) findViewById(R.id.connect);
        Button registerButton = (Button) findViewById(R.id.register);
        CheckBox checkBox = (CheckBox) findViewById(R.id.checkBox);

        //hide password text
        password.setTransformationMethod(PasswordTransformationMethod.getInstance());
        passwordConfirmation.setTransformationMethod(PasswordTransformationMethod.getInstance());

        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
            TextView textMail = findViewById(R.id.textMail);
            TextView textPassword = findViewById(R.id.textPassword);
            TextView textPasswordConfirmation = findViewById(R.id.textConfirmPassword);
            TextView textUsername = findViewById(R.id.textUsername);
            TextView textFirstName = findViewById(R.id.textFirstName);
            TextView textLastName = findViewById(R.id.textLastName);
            //leftside
            textMail.setX(-500);
            textMail.setY(-50);
            mail.setX(-500);
            mail.setY(-50);
            textPassword.setX(-500);
            textPassword.setY(-50);
            password.setX(-500);
            password.setY(-50);
            checkBox.setX(-500);
            checkBox.setY(-50);
            textPasswordConfirmation.setX(-500);
            textPasswordConfirmation.setY(-50);
            passwordConfirmation.setX(-500);
            passwordConfirmation.setY(-50);
            //right side//
            textUsername.setX(500);
            textUsername.setY(-725);
            username.setX(500);
            username.setY(-725);
            textLastName.setX(500);
            textLastName.setY(-725);
            lastName.setX(500);
            lastName.setY(-725);
            textFirstName.setX(500);
            textFirstName.setY(-625);
            firstName.setX(500);
            firstName.setY(-675);


            //buttons//
            loginButton.setY(-675);
            registerButton.setY(-675);
        }

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
            }
        });

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                wrongNewProfile.setText(ProfileList.addProfile(new Profile(username.getText().toString(), password.getText().toString(), firstName.getText().toString(), lastName.getText().toString(), mail.getText().toString(), "@drawable/default_profile", null, null), passwordConfirmation.getText().toString()));
                if (wrongNewProfile.getText().toString().equals("Connexion en cours..")) {
                    startActivity(new Intent(RegisterActivity.this, MapsActivityOnline.class));
                }
            }
        });

        ImageButton home = (ImageButton) findViewById(R.id.home_button);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisterActivity.this, MapsActivityOffline.class));
            }
        });

        Button accessToRegister = (Button) findViewById(R.id.accessToRegister);
        accessToRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisterActivity.this, RegisterActivity.class));
            }
        });

        Button accessToLogin = (Button) findViewById(R.id.accessToLogin);
        accessToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
            }
        });

        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked) {
                    // show password
                    password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    passwordConfirmation.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                } else {
                    password.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    passwordConfirmation.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
            }
        });

    }
}
