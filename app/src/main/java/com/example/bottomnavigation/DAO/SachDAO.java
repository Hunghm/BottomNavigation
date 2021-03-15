package com.example.bottomnavigation.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.bottomnavigation.Database.DBHelper;
import com.example.bottomnavigation.Model.Sach;
import com.example.bottomnavigation.Model.ThanhVien;

import java.util.ArrayList;
import java.util.List;

public class SachDAO {

    private SQLiteDatabase db;
    private Context context;

    public SachDAO(Context context) {
        this.context = context;
        DBHelper dbHelper = new DBHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public long insert(Sach obj) {
        ContentValues values = new ContentValues();
        values.put("maSach" , obj.getMaSach());
        values.put("tenSach" , obj.getTenSach());
        values.put("giaThue" , obj.getGiaThue());
        values.put("maLoai" , obj.getMaLoai());
        return db.insert("Sach",null,values);
    }

    public int update(Sach obj) {
        ContentValues values = new ContentValues();
        values.put("maSach" , obj.getMaSach());
        values.put("tenSach" , obj.getTenSach());
        values.put("giaThue" , obj.getGiaThue());
        values.put("maLoai" , obj.getMaLoai());
        return db.update("Sach",values,"maSach=?", new String[]{String.valueOf(obj.getMaSach())});
    }
    public int delete(String id){
        return db.delete("Sach","maSach",new String[]{id});
    }

    public List<Sach> getAll(){
        String sql = "SELECT * FROM Sach";
        return getData(sql);
    }

    public Sach getID (String id){
        String sql = "SELECT * FROM Sach WHERE maSach=?";
        List<Sach> list = getData(sql, id);
        return list.get(0);
    }

    private List<Sach> getData(String sql, String...selectionArgs){
        List<Sach> list = new ArrayList<>();
        Cursor c = db.rawQuery(sql,selectionArgs);
        while (c.moveToNext()){
            Sach obj = new Sach();
            obj.setMaSach(c.getInt(c.getColumnIndex("maSach")));
            obj.setTenSach(c.getString(c.getColumnIndex("tenSach")));
            obj.setGiaThue(c.getInt(c.getColumnIndex("giaThue")));
            obj.setMaLoai(c.getInt(c.getColumnIndex("maLoai")));
            list.add(obj);
        }
        return list;
    }

}
