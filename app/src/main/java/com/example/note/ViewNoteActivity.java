package com.example.note;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ViewNoteActivity extends AppCompatActivity {

    private TextView tvTieuDe,tvNgayTao,tvNgayChinhSua,tvTag;
    private EditText tvNoiDung;
    private Button btnCapNhat,btnXoa;

    public static final String Noidungcapnhat="nội dung mới";
    public static final String ThayDoi="Kiểm tra thay đổi";
    public static final int Capnhat=1;
    public static final int Xoa=-1;
    public static final int pin = 1001;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_note);

        tvTieuDe = findViewById(R.id.NoiDungTieuDe);
        tvNgayTao = findViewById(R.id.ThoiGianTao);
        tvNgayChinhSua = findViewById(R.id.NgaySua);
        tvTag = findViewById(R.id.Tag);
        tvNoiDung = findViewById(R.id.NoiDung);

        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra(MainActivity.Nen);

        tvTieuDe.setText(bundle.getString(MainActivity.Tieude));
        tvNgayTao.setText(bundle.getString(MainActivity.Ngaytao));
        tvNgayChinhSua.setText(bundle.getString(MainActivity.Ngaysua));
        tvTag.setText(bundle.getString(MainActivity.tag));
        tvNoiDung.setText(bundle.getString(MainActivity.Noidung));


        btnCapNhat = findViewById(R.id.capnhat);
        btnXoa = findViewById(R.id.xoa);

        btnCapNhat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra(ThayDoi,Capnhat);
                intent.putExtra(Noidungcapnhat,tvNoiDung.getText().toString());
                setResult(pin,intent);
                finish();
            }
        });


        btnXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra(ThayDoi,Xoa);
                setResult(pin,intent);
                finish();
            }
        });

    }



}
