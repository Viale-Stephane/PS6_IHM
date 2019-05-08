package com.example.georesto.Activity;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.georesto.R;
import com.example.georesto.Model.LoginModel;
import com.example.georesto.Model.ProfileList;

public class LoginActivity extends Activity { // sans oublier l'implémentation de l'interface OnClickListener
    private ProfileList profileList = new ProfileList();
    private LoginModel model = new LoginModel();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        findViewById(R.id.accessProfile).setVisibility(View.GONE);
        findViewById(R.id.usernameProfile).setVisibility(View.GONE);
        findViewById(R.id.mailProfile).setVisibility(View.GONE);

        Button connect = findViewById(R.id.connect);
        Button register = findViewById(R.id.register);
        TextView passwordForgotten = findViewById(R.id.passwordForgotten);
        final EditText username = findViewById(R.id.username);
        final EditText password = findViewById(R.id.password);
        final TextView wrongIdentifiers = findViewById(R.id.wrongIdentifiers);


        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
            connect.setX(-400);
            register.setX(400);
            register.setY(-175);
            passwordForgotten.setY(-175);
            passwordForgotten.setX(-400);
            wrongIdentifiers.setY(-250);
            wrongIdentifiers.setX(275);
        }

        connect.setOnClickListener(new View.OnClickListener() {
            public void onClick(View connect) {
                if (model.connect(username.getText().toString(), password.getText().toString()) != null) {
                    startActivity(new Intent(LoginActivity.this, MapsActivityOnline.class));
                } else {
                    wrongIdentifiers.setText("Identifiants incorrects");
                }
            }

        });


        register.setOnClickListener(new View.OnClickListener() {
            public void onClick(View passwordForgotten) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            }
        });

        ImageButton home = (ImageButton) findViewById(R.id.home_button);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, MapsActivityOffline.class));
            }
        });

        Button accessToRegister = (Button) findViewById(R.id.accessToRegister);
        accessToRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            }
        });

        Button accessToLogin = (Button) findViewById(R.id.accessToLogin);
        accessToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, LoginActivity.class));
            }
        });
    }
}