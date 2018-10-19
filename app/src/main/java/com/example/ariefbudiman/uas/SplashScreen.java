package com.example.ariefbudiman.uas;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;

import com.google.firebase.auth.FirebaseAuth;

/**
 * Created by arief budiman on 07/12/2017.
 */

public class SplashScreen extends AppCompatActivity {

    //firebase auth object
    private FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.splash_screen);

        //getting firebase auth object
        firebaseAuth = FirebaseAuth.getInstance();



        Thread myThread = new Thread(){

            @Override
            public void run() {
                super.run();
                try {
                    sleep(3000);

                    //if the objects getcurrentuser method is not null
                    //means user is already logged in
                    if(firebaseAuth.getCurrentUser() != null){
                        //close this activity
                        finish();
                        //opening profile activity
                        startActivity(new Intent(getApplicationContext(), Activity_Select.class));
                    }
                    else{
                        Intent intent = new Intent(getApplicationContext(), ActivityTampilanAwal.class);
                        startActivity(intent);
                        finish();
                    }
                }
                catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        };
        myThread.start();


    }
}
