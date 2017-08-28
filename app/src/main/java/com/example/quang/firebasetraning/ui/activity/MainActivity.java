package com.example.quang.firebasetraning.ui.activity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.quang.firebasetraning.R;
import com.example.quang.firebasetraning.model.Lop;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button buttonThem, buttonXem, buttonQuanLy;
    private DatabaseReference mData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        buttonThem.setOnClickListener(this);
        buttonXem.setOnClickListener(this);
        buttonQuanLy.setOnClickListener(this);
        mData = FirebaseDatabase.getInstance().getReference();
    }

    private void initView() {
        buttonThem = (Button) findViewById(R.id.button_them);
        buttonXem = (Button) findViewById(R.id.button_xem);
        buttonQuanLy = (Button) findViewById(R.id.button_quanly);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.button_them:
                clickButtonThem(v);
                break;
            case R.id.button_xem:
                clickButtonXem(v);
                break;
            case R.id.button_quanly:
                clickButtonQuanLy(v);
                break;
        }

    }

    private void clickButtonThem(View v) {
        LayoutInflater xeChoHang = LayoutInflater.from(MainActivity.this);
        v = xeChoHang.inflate(R.layout.custom_dialog, null);
        Dialog hopThoaiCustom = new Dialog(MainActivity.this, R.style.Theme_Dialog);
        hopThoaiCustom.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        hopThoaiCustom.setContentView(v);
        hopThoaiCustom.show();
        final EditText editMaLop = (EditText) v.findViewById(R.id.edit_malop);
        final EditText editTenLop = (EditText) v.findViewById(R.id.edit_tenlop);
        Button buttonXoa = (Button) v.findViewById(R.id.button_xoa);
        Button buttonLuu = (Button) v.findViewById(R.id.button_luu);

        buttonXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editMaLop.setText("");
                editTenLop.setText("");
            }
        });
        buttonLuu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //lay ra thong tin cua lop ma nguoi dung nhap o edit text
                String maLop = editMaLop.getText().toString();
                String tenLop = editTenLop.getText().toString();
                Lop lopThem = new Lop(maLop, tenLop);
                mData.child("lop").push().setValue(lopThem, new DatabaseReference.CompletionListener() {
                    //neu thanh cong se goi den ham CompletionListener
                    @Override
                    public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                        Toast.makeText(MainActivity.this, "Completed!", Toast.LENGTH_SHORT).show();
                    }
                });
                editMaLop.setText("");
                editTenLop.setText("");
            }
        });

    }

    private void clickButtonQuanLy(View v) {
    }

    private void clickButtonXem(View v) {
        Intent intent = new Intent(MainActivity.this,DanhSachActivity.class);
        startActivity(intent);
    }
}
