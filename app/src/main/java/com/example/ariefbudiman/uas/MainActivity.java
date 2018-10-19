package com.example.ariefbudiman.uas;


import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;

import android.os.Build;
import android.os.SystemClock;
import android.provider.MediaStore;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.StringDef;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    //private TextView textview;
    private LocationManager locationManager;
    private LocationListener locationListener;

    double count = 0.00;
    Thread t;

    private long lastpause;

    Button btnSelesai;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_running);

        final TextView textview = (TextView) findViewById(R.id.tvKilometer);

         t = new Thread(){
            @Override
            public void run() {
                while(!isInterrupted()){
                    try{
                        Thread.sleep(5000);

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                count+=0.01;

                                textview.setText(String.valueOf(count));
                            }
                        });
                    }
                    catch (InterruptedException e){
                        e.printStackTrace();
                    }
                }

            }
        };
        final Chronometer simpleChronometer = (Chronometer) findViewById(R.id.stopwatch);
        simpleChronometer.start();

        t.start();


    }

    public void actionKamera(View v){
       Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent,0);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //Bitmap bitmap = (Bitmap)data.getExtras().get("data");
        //ImageButton imageButton = (ImageButton) findViewById(R.id.btnKamera);
       // imageButton.setImageBitmap(bitmap);
    }

    public void actionSelesai(View v){

        final Chronometer simpleChronometer = (Chronometer) findViewById(R.id.stopwatch);
        lastpause = SystemClock.elapsedRealtime();
        simpleChronometer.stop();


        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("               Poin yang anda raih : \n           " +
                "              Rp.100,-.\n\n" +
                "        Apa Anda yakin ingin selesai \n" +
                "                        sekarang?")
                .setIcon(R.drawable.icon_profile)
                .setTitle("     Selesai Aktivitas")
                .setPositiveButton("Selesai", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                })
        .setNegativeButton("Lanjutkan", new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if(lastpause != 0){
                            simpleChronometer.setBase(simpleChronometer.getBase() + SystemClock.elapsedRealtime() - lastpause);
                        }
                        simpleChronometer.start();
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();
    }

    @Override
    public void onBackPressed() {

    }

    public void actionChrono(View v){
        final Chronometer simpleChronometer = (Chronometer) findViewById(R.id.stopwatch);
        simpleChronometer.start();

       t.start();

       long elapsedMillis = SystemClock.elapsedRealtime() - simpleChronometer.getBase();
       int hours = (int) (elapsedMillis / 3600000);
       int minutes = (int) (elapsedMillis - hours * 3600000) / 60000;
       int seconds = (int) (elapsedMillis - hours * 3600000 - minutes * 60000) / 1000;



    }
}
