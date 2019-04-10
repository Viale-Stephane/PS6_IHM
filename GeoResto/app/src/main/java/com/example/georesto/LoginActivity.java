package com.example.georesto;

import android.app.Activity;
import android.os.Bundle;


public class LoginActivity extends Activity { // sans oublier l'implémentation de l'interface OnClickListener

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
    }

}