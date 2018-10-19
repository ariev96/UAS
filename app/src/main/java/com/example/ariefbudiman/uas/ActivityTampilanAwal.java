package com.example.ariefbudiman.uas;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by arief budiman on 04/11/2017.
 */

public class ActivityTampilanAwal extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tampilanawal);
    }

    public void actionMasuk(View v){
        Intent in = new Intent(this, ActivityLogin.class);
        startActivity(in);
    }

    public void actionDaftar(View v){
        Intent in = new Intent(this, ActivityDaftar.class);
        startActivity(in);
    }
}
