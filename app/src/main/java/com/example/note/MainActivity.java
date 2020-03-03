package com.example.note;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.SearchView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.security.Key;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;



public class MainActivity extends AppCompatActivity {

    ListView myList;
    ArrayList<Note> searchList,listNote;
    RadioButton searchTag,searchNoiDung;

    protected static MenuItem item;
    public static final String Tieude="Tiêu đề";
    public static final String Ngaytao="Ngày tạo";
    public static final String Ngaysua="Ngày chỉnh sửa";
    public static final String tag="Tag";
    public static final String Noidung="Nội dung";
    public static final String Nen="bundle";
    public static final int Code1=1000;
    public static final int Code2=1002;
    public static int thaydoi=0;
    private int now;

    SharedPreferences sharedPreferences;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myList = (ListView) findViewById(R.id.listview);


        listNote = new ArrayList<Note>();

        sharedPreferences  = getSharedPreferences("listNote",MODE_PRIVATE);

        load();

        ADAPTER();

        searchList = new  ArrayList<Note>();

        final EditText search = findViewById(R.id.search);


        myList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {



                if(!search.getText().toString().equals(""))
                {
                    now = listNote.indexOf(searchList.get(position));
                }
                else
                {
                    now = position;
                }

                Intent intent = new Intent(MainActivity.this,ViewNoteActivity.class);

                Bundle bundle = new Bundle();
                bundle.putString(Tieude,listNote.get(now).TieuDe);
                bundle.putString(Ngaytao,listNote.get(now).NgayTao.toString());
                bundle.putString(Ngaysua,listNote.get(now).ChinhSua.toString());
                bundle.putString(tag,listNote.get(now).Tag);
                bundle.putString(Noidung,listNote.get(now).NoiDung);

                intent.putExtra(Nen,bundle);

                startActivityForResult(intent,Code1);
            }
        });





        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if(s.toString().equals(""))
                {
                    setSearchList();
                    ADAPTER();
                }
                else {

                    setSearchList();
                    searchItem(search.getText().toString());
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });




    }

    public void searchItem(String s)
    {

        searchTag = findViewById(R.id.SearchTag);
        searchNoiDung = findViewById(R.id.SearchString);

        if(searchTag.isChecked())
        {
          for(int i=0;i<searchList.size();i++)
           {
              if(searchList.get(i).Tag.contains(s)!=true)
                {
                    searchList.remove(i);
                    i=i-1;
                }
           }
        }

        if(searchNoiDung.isChecked())
        {
            for(int i=0;i<searchList.size();i++)
            {
                if(searchList.get(i).NoiDung.contains(s)!=true)
                {
                    searchList.remove(i);
                    i=i-1;
                }
            }
        }

        searchADAPTER();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.putInt("SoPhanTu:",listNote.size());

        for(int i = 0 ; i<listNote.size();i++)
        {
            String index = String.valueOf(i);
            editor.putString("TieuDe"+index,listNote.get(i).TieuDe);
            editor.putString("NgayTao"+index,listNote.get(i).NgayTao.toString());
            editor.putString("NgayChinhSua"+index,listNote.get(i).ChinhSua.toString());
            editor.putString("Tag"+index,listNote.get(i).Tag);
            editor.putString("NoiDung"+index,listNote.get(i).NoiDung);
            editor.commit();
        }

        finish();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_note,menu);
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if(item.getItemId()== R.id.themghichu)
        {
            Intent intent = new Intent(MainActivity.this,AddNoteActivity.class);
            startActivityForResult(intent,Code2);
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == Code1)
        {
            if(resultCode==ViewNoteActivity.pin)
            {
                thaydoi=data.getIntExtra(ViewNoteActivity.ThayDoi,0);
                if(thaydoi==1)
                {
                    String noidungmoi=data.getStringExtra(ViewNoteActivity.Noidungcapnhat);

                    if(listNote.get(now).NoiDung.compareTo(noidungmoi)!= 0 && noidungmoi!="")
                    {
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm:ss dd-MM-yyyy");
                        listNote.get(now).NoiDung=noidungmoi;
                        listNote.get(now).ChinhSua=java.time.LocalDateTime.now().format(formatter);
                        ADAPTER();
                    }
                }

                if(thaydoi==-1)
                {
                    listNote.remove(now);
                    ADAPTER();
                    Toast.makeText(MainActivity.this,"Đã xóa",Toast.LENGTH_SHORT).show();
                }

            }
        }

        if(requestCode == Code2)
        {
            if(resultCode==AddNoteActivity.pin)
            {
                Bundle bundle = data.getBundleExtra(AddNoteActivity.Nen);

                if(bundle.getString(AddNoteActivity.Tieude).isEmpty()&&bundle.getString(AddNoteActivity.tag).isEmpty()&&bundle.getString(AddNoteActivity.Noidung).isEmpty())
                {
                    return;
                }

                Note note = new Note(bundle.getString(AddNoteActivity.Tieude),bundle.getString(AddNoteActivity.tag),bundle.getString(AddNoteActivity.Noidung));

                listNote.add(note);

                ADAPTER();

                Toast.makeText(MainActivity.this,"Thêm thành công",Toast.LENGTH_SHORT).show();

            }
        }

    }

    protected void setSearchList()
    {
        searchList.clear();
        for(int i=0;i<listNote.size();i++)
        {
            searchList.add(listNote.get(i));
        }
    }


    protected void searchADAPTER()
    {
        NoteAdapter SearchAdapter = new NoteAdapter(
          MainActivity.this,
           R.layout.note_screen,
           searchList
        );

        myList.setAdapter(SearchAdapter);
    }

    protected void ADAPTER()
    {
        if(listNote.isEmpty()==true)
        {
            Toast.makeText(MainActivity.this,"Danh sách trống",Toast.LENGTH_LONG).show();
        }

        NoteAdapter adapter = new NoteAdapter(
                MainActivity.this,
                R.layout.note_screen,
                listNote
        );

        myList.setAdapter(adapter);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    protected void load()
    {
        int sophantu=sharedPreferences.getInt("SoPhanTu:",-1);

        if(sophantu == 0 && sophantu != -1)
        {
            return;
        }
        else
        {
            for(int i = 0;i<sophantu;i++)
            {
                String index = String.valueOf(i);
                String td,ngt,ngs,tag,nd;
                td = sharedPreferences.getString("TieuDe"+index,"");
                ngt = sharedPreferences.getString("NgayTao"+index,"");
                ngs = sharedPreferences.getString("NgayChinhSua"+index,"");
                tag = sharedPreferences.getString("Tag"+index,"");
                nd = sharedPreferences.getString("NoiDung"+index,"");

                Note note = new Note(td,ngt,ngs,tag,nd);

                listNote.add(note);


            }
        }

    }

}
