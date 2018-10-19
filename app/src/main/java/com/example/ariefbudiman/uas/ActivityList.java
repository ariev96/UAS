package com.example.ariefbudiman.uas;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

/*import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;*/
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;

/**
 * Created by arief budiman on 05/12/2017.
 */

public class ActivityList extends AppCompatActivity {

    ArrayList<DataList> arrayList = new ArrayList<DataList>();

//    Firebase mRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        final TextView tvdonasi = (TextView) findViewById(R.id.tvdonasi);

        /*Firebase.setAndroidContext(this);

        mRef = new Firebase("https://projectuas-5ace8.firebaseio.com/UserDonasi");

        mRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                String value = dataSnapshot.getValue(String.class);
                tvdonasi.setText(value);

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });*/
         Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
         setSupportActionBar(toolbar);

         getSupportActionBar().setDisplayHomeAsUpEnabled(true);
         getSupportActionBar().setDisplayShowHomeEnabled(true);


        final ListView listView = (ListView) findViewById(R.id.listMedia);

        listView.setClickable(true);
        DataAdapter adapter = new DataAdapter(this, R.layout.row, arrayList);

        listView.setAdapter(adapter);
        final String judul = "Dukung Indonesia untuk R80";
        String deskripsi = "Kitabisa.com";
        String donasi = "Rp.0,-";
        final String judul1 = "Save Palestina";
        String deskripsi1 = "Kitabisa.com";
        String donasi1 = "Rp.0,-";
        final String judul2 = "Renovasi Sekolah di Perbatasan";
        String deskripsi2 = "Kitabisa.com";
        String donasi2 = "Rp.0,-";
        final String judul3 = "Beasiswa Yatim di Pedesaan";
        String deskripsi3 = "Kitabisa.com";
        String donasi3 = "Rp.0,-";
        final String judul4 = "Peduli Suriah";
        String deskripsi4 = "Kitabisa.com";
        String donasi4 = "Rp.0,-";
        final String judul5 = "Selamatkan Anak Indonesia";
        String deskripsi5 = "Kitabisa.com";
        String donasi5 = "Rp.0,-";

        arrayList.add(new DataList(judul,deskripsi,donasi));
        arrayList.add(new DataList(judul1,deskripsi1,donasi1));
        arrayList.add(new DataList(judul2,deskripsi2,donasi2));
        arrayList.add(new DataList(judul3,deskripsi3,donasi3));
        arrayList.add(new DataList(judul4,deskripsi4,donasi4));
        arrayList.add(new DataList(judul5,deskripsi5,donasi5));


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                view.setSelected(true);
                if (position == 0) {
                    Intent myIntent = new Intent(view.getContext(), ActivityDonasi.class);
                    myIntent.putExtra("datadarilist",judul);
                    startActivityForResult(myIntent, 0);

                }
                if (position == 1) {
                    Intent myIntent = new Intent(view.getContext(), ActivityDonasi.class);
                    myIntent.putExtra("datadarilist",judul1);
                    startActivityForResult(myIntent, 0);

                }
                if (position == 2) {
                    Intent myIntent = new Intent(view.getContext(), ActivityDonasi.class);
                    myIntent.putExtra("datadarilist",judul2);
                    startActivityForResult(myIntent, 0);

                }
                if (position == 3) {
                    Intent myIntent = new Intent(view.getContext(), ActivityDonasi.class);
                    myIntent.putExtra("datadarilist",judul3);
                    startActivityForResult(myIntent, 0);

                }
                if (position == 4) {
                    Intent myIntent = new Intent(view.getContext(), ActivityDonasi.class);
                    myIntent.putExtra("datadarilist",judul4);
                    startActivityForResult(myIntent, 0);

                }
                if (position == 5) {
                    Intent myIntent = new Intent(view.getContext(), ActivityDonasi.class);
                    myIntent.putExtra("datadarilist",judul5);
                    startActivityForResult(myIntent, 0);

                }




            }
        });

    }

/*    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        Intent in = getIntent();

        String msg = in.getStringExtra("datadaridonasi");
        String judul = "Save Cancer";
        String deskripsi = "Kitabisa.com";

        arrayList.add(new DataList(judul,deskripsi,msg));


    }*/

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i = new Intent(this, Activity_Select.class);
        startActivity(i);
    }

    /*@Override
    protected void onStart() {
        super.onStart();

        Intent in = getIntent();

        String msg = in.getStringExtra("donasi");
        TextView tvdonasi = (TextView) findViewById(R.id.tvdonasi);
        tvdonasi.setText(msg);
    }*/
}
