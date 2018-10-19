package com.example.ariefbudiman.uas;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by arief budiman on 13/12/2017.
 */

public class ActivityDonasi extends AppCompatActivity implements View.OnClickListener {

    //firebase auth object
    private FirebaseAuth firebaseAuth;

    private DatabaseReference databaseReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donasi);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        Button btndonasi = (Button) findViewById(R.id.btndonasi);

        Intent in = getIntent();

        String msg = in.getStringExtra("datadarilist");
        TextView tvtujuandonasi = (TextView) findViewById(R.id.tvtujuandonasi);
        tvtujuandonasi.setText(msg);

        //initializing firebase authentication object
        firebaseAuth = FirebaseAuth.getInstance();


        databaseReference = FirebaseDatabase.getInstance().getReference("UserDonasi");
        btndonasi.setOnClickListener(this);

    }

    private void saveUserInformation(){

       // String name = editTextName.getText().toString().trim();
       // String add = editTextAddress.getText().toString().trim();
        FirebaseUser user = firebaseAuth.getCurrentUser();

        String donasi = "Rp.100,-";
        String email = user.getEmail();
       // String id = databaseReference.push().getKey();

        UserInformation userInformation = new UserInformation(email,donasi);

        databaseReference.child(user.getUid()).setValue(userInformation);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Donasi Anda berhasil..")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        Intent i = new Intent(ActivityDonasi.this, ActivityList.class);
                        startActivity(i);

                    }
                });

        AlertDialog alert = builder.create();
        alert.show();

    }

    @Override
    public void onClick(View v) {

        saveUserInformation();
    }

/*    public void actionDonasi(View v){

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Donasi berhasil, Terima kasih..")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        String donasi = "Rp.100,-";

                        Intent i = new Intent(getApplicationContext(), ActivityList.class);
                        i.putExtra("datadaridonasi",donasi);
                        startActivityForResult(i,99);
                        finish();
                    }
                });

        AlertDialog alert = builder.create();
        alert.show();

    }*/

}
