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

        // 初始加入20个三国人物信息
        String[] insert = new String[]{
                "INSERT INTO person VALUES(1," + String.valueOf(R.mipmap.caocao)
                        +", '曹操','男','155 - 220','豫州沛国谯（安徽亳州市亳县）','魏')",
                "INSERT INTO person VALUES(2," + String.valueOf(R.mipmap.ic_launcher)
                        +", '刘备','男','161 - 223','幽州涿郡涿（河北保定市涿州）','蜀')",
                "INSERT INTO person VALUES(3," + String.valueOf(R.mipmap.ic_launcher)
                        +", '孙权','男','182 - 252','扬州吴郡富春（浙江杭州市富阳）','吴')",
                "INSERT INTO person VALUES(4," + String.valueOf(R.mipmap.ic_launcher)
                        +", '张辽','男','169 - 222','并州雁门郡马邑（山西朔州市朔城区）','魏')",
                "INSERT INTO person VALUES(5," + String.valueOf(R.mipmap.ic_launcher)
                        +", '诸葛亮','男','181 - 234','徐州琅邪国阳都(山东临沂市沂南县南)','蜀')",
                "INSERT INTO person VALUES(6," + String.valueOf(R.mipmap.ic_launcher)
                        +", '张飞','男','? - 221','豫州沛国谯(安徽亳州市亳县)','蜀')",
                "INSERT INTO person VALUES(7," + String.valueOf(R.mipmap.ic_launcher)
                        +", '关羽','男','? - 219','司隶河东郡解(山西运城市临猗县西南)','蜀')",
                "INSERT INTO person VALUES(8," + String.valueOf(R.mipmap.ic_launcher)
                        +", '赵云','男','? - 229','冀州常山国真定(河北石家庄市正定县南)','蜀')",
                "INSERT INTO person VALUES(9," + String.valueOf(R.mipmap.ic_launcher)
                        +", '黄忠','男','? - 220','荆州南阳郡(河南南阳市)','蜀')",
                "INSERT INTO person VALUES(10," + String.valueOf(R.mipmap.ic_launcher)
                        +", '司马懿','男','179 - 251','荆州南阳郡(河南南阳市)','魏')",
                "INSERT INTO person VALUES(11," + String.valueOf(R.mipmap.ic_launcher)
                        +", '曹丕','男','187 - 226','豫州沛国谯(安徽亳州市亳县)','魏')",
                "INSERT INTO person VALUES(12," + String.valueOf(R.mipmap.ic_launcher)
                        +", '郭嘉','男','170 - 207','豫州颍川郡阳翟(河南许昌市禹州)','魏')",
                "INSERT INTO person VALUES(13," + String.valueOf(R.mipmap.ic_launcher)
                        +", '夏侯渊','男','? - 219','豫州沛国谯(安徽亳州市亳县)','魏')",
                "INSERT INTO person VALUES(14," + String.valueOf(R.mipmap.ic_launcher)
                        +", '荀彧','男','163 - 212','豫州颍川郡颍阴(河南许昌市)','魏')",
                "INSERT INTO person VALUES(15," + String.valueOf(R.mipmap.ic_launcher)
                        +", '周瑜','男','175 - 210','扬州庐江郡舒(安徽合肥市庐江县西南)','吴')",
                "INSERT INTO person VALUES(16," + String.valueOf(R.mipmap.ic_launcher)
                        +", '大乔','女','? - ?','扬州庐江郡皖(安徽安庆市潜山县)','吴')",
                "INSERT INTO person VALUES(17," + String.valueOf(R.mipmap.ic_launcher)
                        +", '小乔','女','? - ?','扬州庐江郡皖(安徽安庆市潜山县)','吴')",
                "INSERT INTO person VALUES(18," + String.valueOf(R.mipmap.ic_launcher)
                        +", '鲁肃','男','172 - 217','徐州下邳国东城(安徽滁州市定远县东南二十五公里)','吴')",
                "INSERT INTO person VALUES(19," + String.valueOf(R.mipmap.ic_launcher)
                        +", '吕蒙','男','178 - 219','豫州汝南郡富波(安徽阜阳市阜南县东南)','吴')",
                "INSERT INTO person VALUES(20," + String.valueOf(R.mipmap.ic_launcher)
                        +", '黄盖','男','? - ?','荆州零陵郡泉陵(湖南永州市零陵区)','吴')"};

        for(String i :insert){
            db.execSQL(i);
        }

    }

    // 版本更新时调用
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){

    }

    // 恢复原始数据库
    public void restore(SQLiteDatabase db){
        db.execSQL("DROP TABLE person;");
        onCreate(db);
    }
}
