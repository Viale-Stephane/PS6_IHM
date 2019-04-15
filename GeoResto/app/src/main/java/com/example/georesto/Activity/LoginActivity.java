package com.example.georesto.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.EditText;

import com.example.georesto.Model.LoginModel;
import com.example.georesto.Profile;
import com.example.georesto.ProfileList;
import com.example.georesto.R;


public class LoginActivity extends Activity { // sans oublier l'implémentation de l'interface OnClickListener
    private ProfileList profileList = new ProfileList();
    private LoginModel model = new LoginModel();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        Button connect = findViewById(R.id.connect);
        TextView passwordForgotten = findViewById(R.id.passwordForgotten);
        final EditText username = findViewById(R.id.username);
        final EditText password = findViewById(R.id.password);
        final TextView wrongIdentifiers = findViewById(R.id.wrongIdentifiers);

        connect.setOnClickListener(new View.OnClickListener() {
            public void onClick(View connect) {
                if(model.connect(username.getText().toString(),password.getText().toString()) != null) {
                    startActivity(new Intent(LoginActivity.this, MapsActivityOnline.class));
                } else {
                    wrongIdentifiers.setText("Identifiants incorrects");
                }
            }

        });

        passwordForgotten.setOnClickListener(new View.OnClickListener() {
            public void onClick(View passwordForgotten) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            }
        });
    }


}