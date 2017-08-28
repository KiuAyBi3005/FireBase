package com.example.quang.firebasetraning.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.quang.firebasetraning.R;
import com.example.quang.firebasetraning.model.Lop;

import java.util.ArrayList;

/**
 * Created by quang on 8/23/2017.
 */

public class LopAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<Lop>  data;

    public LopAdapter(Context context, ArrayList<Lop> data) {
        this.context = context;
        this.data = data;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(this.context);
        convertView = inflater.inflate(R.layout.custom_list_view,parent,false);
        TextView textStt = (TextView) convertView.findViewById(R.id.text_stt);
        TextView textMaLop = (TextView) convertView.findViewById(R.id.text_malop);
        TextView textTenLop = (TextView) convertView.findViewById(R.id.text_tenlop);
        textStt.setText(String.valueOf(position + 1));
        textMaLop.setText(data.get(position).getMaLop());
        textTenLop.setText(data.get(position).getTenLop());
        return convertView;
    }
}
