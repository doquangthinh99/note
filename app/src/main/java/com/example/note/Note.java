package com.example.note;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.zip.DataFormatException;

public class Note {
    public String TieuDe;
    public String NgayTao;
    public String ChinhSua;
    public String Tag;
    public String NoiDung;

    public Note(String tieuDe, String tag, String noidung) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm:ss dd-MM-yyyy");
        Tag = tag;
        TieuDe = tieuDe;
        NoiDung = noidung;
        LocalDateTime nt = java.time.LocalDateTime.now();
        NgayTao = nt.format(formatter);
        ChinhSua=nt.format(formatter);
    }

    public Note(String tieuDe, String ngayTao, String chinhSua, String tag, String noiDung) {
        TieuDe = tieuDe;
        NgayTao = ngayTao;
        ChinhSua = chinhSua;
        Tag = tag;
        NoiDung = noiDung;
    }
}
