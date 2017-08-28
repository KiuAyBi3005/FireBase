package com.example.quang.firebasetraning.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.example.quang.firebasetraning.R;
import com.example.quang.firebasetraning.model.Lop;
import com.example.quang.firebasetraning.ui.adapter.LopAdapter;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class DanhSachActivity extends AppCompatActivity {
    private ListView listDanhSach;
    private DatabaseReference mData;
    private ArrayList<Lop> duLieu;
    private LopAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danh_sach);
        listDanhSach = (ListView) findViewById(R.id.list_danhsach);
        final ArrayList<Lop> duLieu = new ArrayList<>();
        adapter = new LopAdapter(this, duLieu);
        listDanhSach.setAdapter(adapter);
        mData = FirebaseDatabase.getInstance().getReference();
        mData.child("lop").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Lop lopHoc = dataSnapshot.getValue(Lop.class);
                duLieu.add(lopHoc);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }
}
