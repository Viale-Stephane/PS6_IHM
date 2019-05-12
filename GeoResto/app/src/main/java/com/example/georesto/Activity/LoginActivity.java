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

import com.example.georesto.Model.Profile;
import com.example.georesto.R;
import com.example.georesto.Model.LoginModel;
import com.example.georesto.Model.ProfileList;

import org.w3c.dom.Text;

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
            register.setY(-145);
            passwordForgotten.setY(-175);
            passwordForgotten.setX(-400);
            wrongIdentifiers.setY(-245);
            wrongIdentifiers.setX(390);
        }

        connect.setOnClickListener(v -> {
                if (model.connect(username.getText().toString(), password.getText().toString()) != null) {
                    startActivity(new Intent(LoginActivity.this, MapsActivityOnline.class));
                } else {
                    wrongIdentifiers.setText("Identifiants non reconnus.");
                }
        });


        register.setOnClickListener(v -> startActivity(new Intent(LoginActivity.this, RegisterActivity.class)));

        ImageButton home =  findViewById(R.id.home_button);
        home.setOnClickListener(v -> startActivity(new Intent(LoginActivity.this, MapsActivityOffline.class)));

        passwordForgotten.setOnClickListener(v -> {
            Profile profile = ProfileList.findUserByUsername(username.getText().toString());
            if(profile != null)
            passwordForgotten.setText(getString(R.string.passwordForgotten) + " " + ProfileList.findUserByUsername(username.getText().toString()).getPassword());
            else
                passwordForgotten.setText(getString(R.string.passwordForgotten) + " compte inexistant");
        });

        Button accessToRegister =  findViewById(R.id.accessToRegister);
        accessToRegister.setOnClickListener(v -> startActivity(new Intent(LoginActivity.this, RegisterActivity.class)));

        Button accessToLogin = findViewById(R.id.accessToLogin);
        accessToLogin.setOnClickListener(v -> startActivity(new Intent(LoginActivity.this, LoginActivity.class)));
    }
}