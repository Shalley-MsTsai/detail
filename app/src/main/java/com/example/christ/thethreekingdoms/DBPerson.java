package com.example.christ.thethreekingdoms;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

/**
 * Created by christ on 2017/11/21.
 * 三国人物数据库
 */

public class DBPerson {
    private DBHelper dbHelper;

    public DBPerson(Context context){
        dbHelper = new DBHelper(context);

    }

    // 插入三国人物person，返回插入的id
    public int insert(Person person){
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        ContentValues cValues = new ContentValues();
//        cValues.put(Person.ID, person.getId());
        cValues.put(Person.NAME, person.getName());
        cValues.put(Person.SEX, person.getSex());
        cValues.put(Person.PIC, person.getPic());
        cValues.put(Person.BIRTH_DEATH, person.getBirth_death());
        cValues.put(Person.BIRTHPLACE, person.getBirthplace());
        cValues.put(Person.NATION, person.getNation());
        long Person_id = db.insert("person", null, cValues);
        db.close();
        person.setId((int)Person_id);
        return (int)Person_id;
    }

    // 更改数据库已有元组的信息，person为信息更新后的人物
    public void update(Person person){
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        ContentValues cValues = new ContentValues();
        cValues.put(Person.NAME, person.getName());
        cValues.put(Person.SEX, person.getSex());
        cValues.put(Person.PIC, person.getPic());
        cValues.put(Person.BIRTH_DEATH, person.getBirth_death());
        cValues.put(Person.BIRTHPLACE, person.getBirthplace());
        cValues.put(Person.NATION, person.getNation());
        db.update("person", cValues, Person.ID+"=?", new String[]{String.valueOf(person.getId())});
    }

    // 按照id删除三国人物
    public void delete(int Person_id){
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        db.delete("person", Person.ID+"=?", new String[]{String.valueOf(Person_id)});
        db.close();
    }

    // 按照属性值查找符合条件的元组
    // attributes: 条件语句中的属性名； values: 对应的属性取值
    public ArrayList<Person> getPersonList(String[] attributes, String[] values){
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        ArrayList<Person> persons = new ArrayList<Person>();
        Cursor cursor = null;
        String[] columns = new String[]{Person.ID, Person.PIC, Person.NAME, Person.SEX,
                Person.BIRTH_DEATH, Person.BIRTHPLACE, Person.NATION};
        String selection = attributes[0]+"=?";
        for(int i = 1; i < attributes.length; i++)
            selection += " and " + attributes[i] + "=?";
        cursor = db.query("person", columns, selection, values, null, null, Person.ID);

        while(cursor.moveToNext()){
            persons.add(new Person (cursor.getInt(cursor.getColumnIndex(Person.ID)),
                    cursor.getInt(cursor.getColumnIndex(Person.PIC)),
                    cursor.getString(cursor.getColumnIndex(Person.NAME)),
                    cursor.getString(cursor.getColumnIndex(Person.SEX)),
                    cursor.getString(cursor.getColumnIndex(Person.BIRTH_DEATH)),
                    cursor.getString(cursor.getColumnIndex(Person.BIRTHPLACE)),
                    cursor.getString(cursor.getColumnIndex(Person.NATION)) ));
        }
        cursor.close();
        db.close();

        return persons;
    }

    // 返回三国人物数据库中所有元组
    public ArrayList<Person> getPersonList(){
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        ArrayList<Person> persons = new ArrayList<Person>();
        Cursor cursor = null;
        String[] columns = new String[]{Person.ID, Person.PIC, Person.NAME, Person.SEX,
                Person.BIRTH_DEATH, Person.BIRTHPLACE, Person.NATION};
        cursor = db.query("person", columns, null, null, null, null, null);

        while(cursor.moveToNext()){
            persons.add(new Person (cursor.getInt(cursor.getColumnIndex(Person.ID)),
                    cursor.getInt(cursor.getColumnIndex(Person.PIC)),
                    cursor.getString(cursor.getColumnIndex(Person.NAME)),
                    cursor.getString(cursor.getColumnIndex(Person.SEX)),
                    cursor.getString(cursor.getColumnIndex(Person.BIRTH_DEATH)),
                    cursor.getString(cursor.getColumnIndex(Person.BIRTHPLACE)),
                    cursor.getString(cursor.getColumnIndex(Person.NATION)) ));
        }
        cursor.close();
        db.close();
        return persons;
    }

    // 恢复原始数据库
    public void restore(){
        dbHelper.restore(dbHelper.getReadableDatabase());
    }
}
