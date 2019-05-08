package com.example.georesto.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.georesto.R;


public class MapsActivityOffline extends MapsActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        findViewById(R.id.accessProfile).setVisibility(View.GONE);
        findViewById(R.id.usernameProfile).setVisibility(View.GONE);
        findViewById(R.id.home_button).setVisibility(View.GONE);
        findViewById(R.id.mailProfile).setVisibility(View.GONE);

        Button connect = findViewById(R.id.accessToLogin);
        connect.setOnClickListener(v -> startActivity(new Intent(MapsActivityOffline.this, LoginActivity.class)));

        Button register = findViewById(R.id.accessToRegister);
        register.setOnClickListener(v -> startActivity(new Intent(MapsActivityOffline.this, RegisterActivity.class)));
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        return false;
    }


    // ----------------------
    //     CONFIGURATION
    // ----------------------

    @Override
    void configureSideViews() {
        // LeftView
        super.configureSideViews();

        // RightView
        profileView = findViewById(R.id.profileNav);
        profileView.setNavigationItemSelectedListener(this);
        profileView.removeHeaderView(profileView.getHeaderView(0));
        profileView.inflateHeaderView(R.layout.info);
    }
}
