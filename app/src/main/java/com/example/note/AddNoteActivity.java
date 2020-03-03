package com.example.note;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;



public class AddNoteActivity extends AppCompatActivity {

    public static final String Tieude="Tiêu đề";
    public static final String tag="Tag";
    public static final String Noidung="Nội dung";
    public static final String Nen="bundle";
    public static final int pin = 1003;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);

        final EditText etThemTieuDe,etThemTag,etThemNoiDung;

        etThemTieuDe = findViewById(R.id.themTieuDe);
        etThemTag = findViewById(R.id.themTag);
        etThemNoiDung = findViewById(R.id.themnoidung);

        Button btnThem = findViewById(R.id.Themmoi);

        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent();

                Bundle bundle =new Bundle();
                bundle.putString(Tieude,etThemTieuDe.getText().toString());
                bundle.putString(tag,etThemTag.getText().toString());
                bundle.putString(Noidung,etThemNoiDung.getText().toString());

                intent.putExtra(Nen,bundle);

                setResult(pin,intent);

                finish();

            }
        });



    }
}
