package com.example.note;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class NoteAdapter extends BaseAdapter {

    Context myContext;
    int myLayout;
    List<Note> arrNote;

    public NoteAdapter(Context myContext, int myLayout, List<Note> myNote) {
        this.myContext = myContext;
        this.myLayout = myLayout;
        this.arrNote = myNote;
    }

    @Override
    public int getCount() {
        return arrNote.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) myContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        convertView = inflater.inflate(myLayout,null);

        TextView tvTieuDe = (TextView) convertView.findViewById(R.id.TieuDe);
        tvTieuDe.setText(arrNote.get(position).TieuDe);

        TextView tvNgayTao = (TextView) convertView.findViewById(R.id.NgayTao);
        tvNgayTao.setText(arrNote.get(position).NgayTao.toString());

        TextView tvNgayChinhSua = (TextView) convertView.findViewById(R.id.NgayChinhSua);
        tvNgayChinhSua.setText(arrNote.get(position).ChinhSua.toString());

        TextView tvTag = (TextView) convertView.findViewById(R.id.Tag);
        tvTag.setText(arrNote.get(position).Tag.toString());

        return convertView;
    }
}
