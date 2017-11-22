package com.example.christ.thethreekingdoms;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by christ on 2017/11/21.
 * 帮助管理三国人物数据库
 */

public class DBHelper extends SQLiteOpenHelper {

    private final static String name = "person";
    private final static int version = 1;

    public DBHelper(Context context){
        // name: name of database, not including path; factory: null for default
        super(context, name, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        // 创建三国人物表
        String sql = "CREATE TABLE person("
                + Person.ID + " INTEGER PRIMARY KEY,"
                + Person.PIC + " INTEGER,"
                +  Person.NAME + " TEXT,"
                + Person.SEX + " TEXT,"
                + Person.BIRTH_DEATH +" TEXT,"
                + Person.BIRTHPLACE + " TEXT,"
                + Person.NATION + " TEXT)";
        db.execSQL(sql);

//        String[] names = new String[]{"曹操", "刘备", "孙权", "郭嘉", "诸葛亮", "周瑜",
//                "张辽", "赵云", "吕蒙", "荀彧"};

        // 初始加入几个三国人物信息
        String[] insert = new String[]{
                "INSERT INTO person VALUES(1," + String.valueOf(R.mipmap.ic_launcher)
                        +", '曹操','男','155 - 220','豫州沛国谯（安徽亳州市亳县）','魏')",
                "INSERT INTO person VALUES(2," + String.valueOf(R.mipmap.ic_launcher)
                        +", '刘备','男','161 - 223','幽州涿郡涿（河北保定市涿州）','蜀')",
                "INSERT INTO person VALUES(3," + String.valueOf(R.mipmap.ic_launcher)
                        +", '孙权','男','182 - 252','扬州吴郡富春（浙江杭州市富阳）','吴')"};

        for(int i = 0; i < 3; i++){
            db.execSQL(insert[i]);
        }

    }

    // 版本更新时调用
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){

    }

    public void restore(SQLiteDatabase db){
        db.execSQL("DROP TABLE person;");
        onCreate(db);
    }
}
