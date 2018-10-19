package com.example.ariefbudiman.uas;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;

/**
 * Created by arief budiman on 11/12/2017.
 */

public class Activity_Select extends AppCompatActivity implements View.OnClickListener{

    ViewFlipper ViewFlipper;
    ImageButton next;
    ImageButton prev;

    //firebase auth object
    private FirebaseAuth firebaseAuth;

    private DatabaseReference databaseReference;


    //Mendefinisikan variabel
    private Toolbar toolbar;
    private NavigationView navigationView;
    private DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select);

        ViewFlipper = (ViewFlipper) findViewById(R.id.ViewFlip);
        next = (ImageButton) findViewById(R.id.btnnext);
        prev = (ImageButton) findViewById(R.id.btnprev);

        //initializing firebase authentication object
        firebaseAuth = FirebaseAuth.getInstance();

        //getting current user
        FirebaseUser user = firebaseAuth.getCurrentUser();



        next.setOnClickListener(this);
        prev.setOnClickListener(this);


        // Menginisiasi Toolbar dan mensetting sebagai actionbar
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Menginisiasi  NavigationView
        navigationView = (NavigationView) findViewById(R.id.navigation_view);
        //Mengatur Navigasi View Item yang akan dipanggil untuk menangani item klik menu navigasi
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            // This method will trigger on item Click of navigation menu
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                //Memeriksa apakah item tersebut dalam keadaan dicek  atau tidak,
                if(menuItem.isChecked()) menuItem.setChecked(false);
                else menuItem.setChecked(true);
                //Menutup  drawer item klik
                drawerLayout.closeDrawers();
                //Memeriksa untuk melihat item yang akan dilklik dan melalukan aksi
                switch (menuItem.getItemId()){
                    // pilihan menu item navigasi akan menampilkan pesan toast klik kalian bisa menggantinya
                    //dengan intent activity
                    case R.id.navigation1:
                        return true;
                    case R.id.navigation2:
                        Intent i = new Intent(getApplicationContext(), ActivityList.class);
                        startActivity(i);
                        return true;

                    case R.id.navigation4:
                        Toast.makeText(getApplicationContext(),"Pengaturan telah dipilih",Toast.LENGTH_SHORT).show();
                        return true;
                    case R.id.navigation5:
                        Toast.makeText(getApplicationContext(),"Tentang telah dipilih",Toast.LENGTH_SHORT).show();
                        return true;
                    case R.id.navigation6:

                        AlertDialog.Builder builder = new AlertDialog.Builder(Activity_Select.this);
                        builder.setMessage("Apa Anda yakin ingin keluar?" )
                                .setIcon(R.drawable.icon_profile)
                                .setTitle("Keluar")
                                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                       firebaseAuth.signOut();
                                        //closing activity
                                        finish();
                                        //starting login activity
                                        startActivity(new Intent(getApplicationContext(), ActivityTampilanAwal.class));
                                    }
                                })
                                .setNegativeButton("Batal", new DialogInterface.OnClickListener(){
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {

                                    }
                                });
                        AlertDialog alert = builder.create();
                        alert.show();
                        return true;
                    default:
                        Toast.makeText(getApplicationContext(),"Kesalahan Terjadi ",Toast.LENGTH_SHORT).show();
                        return true;
                }
            }
        });
        // Menginisasi Drawer Layout dan ActionBarToggle
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer);
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.openDrawer, R.string.closeDrawer){
            @Override
            public void onDrawerClosed(View drawerView) {
                // Kode di sini akan merespons setelah drawer menutup disini kita biarkan kosong
                super.onDrawerClosed(drawerView);
            }
            @Override
            public void onDrawerOpened(View drawerView) {
                //  Kode di sini akan merespons setelah drawer terbuka disini kita biarkan kosong
                super.onDrawerOpened(drawerView);
            }
        };
        //Mensetting actionbarToggle untuk drawer layout
        drawerLayout.setDrawerListener(actionBarDrawerToggle);
        //memanggil synstate
        actionBarDrawerToggle.syncState();
    }

    @Override
    public void onClick(View v) {
        if (v==next){
            ViewFlipper.showNext();
        }
        else if (v==prev){
            ViewFlipper.showPrevious();
        }
    }

    public void actionMulai(View v){
        Intent in = new Intent(this, MainActivity.class);
        startActivity(in);
    }

    @Override
    public void onBackPressed() {

    }
}
