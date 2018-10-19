package com.example.ariefbudiman.uas;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by arief budiman on 05/12/2017.
 */

public class DataAdapter extends ArrayAdapter<DataList> {

    ArrayList<DataList> arrayListData;

    public DataAdapter(Context context, int resource, ArrayList<DataList> objects) {
        super(context, resource, objects);
        this.arrayListData = objects;
    }

    public View getView(int pos, View convertView, ViewGroup parent){
        View v = convertView;
        if(v == null){
            LayoutInflater li = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = li.inflate(R.layout.row, null);
        }

        DataList dl = arrayListData.get(pos);
        if(dl != null){
            TextView tvjudul = (TextView) v.findViewById(R.id.tvjudul);
            TextView tvdeskripsi = (TextView) v.findViewById(R.id.tvdeskripsi);
           // TextView tvdonasi = (TextView) v.findViewById(R.id.tvdonasi);

            tvjudul.setText(dl.judul);
            tvdeskripsi.setText(dl.deskripsi);
           // tvdonasi.setText(dl.donasi);


        }
        return v;

    }


}
