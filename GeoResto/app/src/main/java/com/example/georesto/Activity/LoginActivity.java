package com.example.georesto.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.georesto.R;
import com.example.georesto.model.LoginModel;
import com.example.georesto.model.ProfileList;

public class LoginActivity extends Activity { // sans oublier l'implémentation de l'interface OnClickListener
    private ProfileList profileList = new ProfileList();
    private LoginModel model = new LoginModel();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        findViewById(R.id.accessProfile).setVisibility(View.GONE);
        findViewById(R.id.usernameProfile).setVisibility(View.GONE);

        Button connect = findViewById(R.id.connect);
        Button register = findViewById(R.id.register);
        TextView passwordForgotten = findViewById(R.id.passwordForgotten);
        final EditText username = findViewById(R.id.username);
        final EditText password = findViewById(R.id.password);
        final TextView wrongIdentifiers = findViewById(R.id.wrongIdentifiers);

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
                startActivity(new Intent(LoginActivity.this, MapsActivity.class));
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