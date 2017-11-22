package com.example.christ.thethreekingdoms;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

import jp.wasabeef.recyclerview.adapters.ScaleInAnimationAdapter;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // -------------------测试数据库的显示---------------------
        // 获取数据库的所有元组
        final DBPerson dbPerson = new DBPerson(MainActivity.this);
        final ArrayList<Person> persons = dbPerson.getPersonList();

        // 人物列表
        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.recyclerview);
        final MainAdapter mainAdapter = new MainAdapter(MainActivity.this, persons);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(mainAdapter);

        mainAdapter.setOnItemClickListener(new MainAdapter.OnItemClickListener() {
            // ---------------测试数据库的元组更新---------------------
            // 点击后更改
            @Override
            public void onClick(int position) {
                persons.get(position).setPic(R.mipmap.ic_launcher_round);
                dbPerson.update(persons.get(position));
                mainAdapter.notifyDataSetChanged();
            }
            // ---------------测试数据库的元组删除--------------------
            // 长按删除
            @Override
            public void onLongClick(int position) {
                Toast.makeText(MainActivity.this,"移除第"+String.valueOf(position+1)+"个人物",
                        Toast.LENGTH_SHORT).show();
                // 删除数据库相关项
                dbPerson.delete(persons.get(position).getId());
                persons.remove(position);
                mainAdapter.notifyDataSetChanged();
            }
        });

        // ------------------------测试数据库的添加------------------------
        // 点击add_icon后，人物列表中添加张辽人物
        FloatingActionButton button = (FloatingActionButton)findViewById(R.id.add_icon);
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Person person = new Person(R.mipmap.ic_launcher, "张辽", "男", "169 - 222",
                        "并州雁门郡马邑（山西朔州市朔城区）", "魏");
                int i = dbPerson.insert(person);
                Toast.makeText(MainActivity.this, String.valueOf(i),
                        Toast.LENGTH_SHORT).show();
                persons.add(person);
                mainAdapter.notifyDataSetChanged();
            }
        });

        // -----------------------测试数据库的恢复------------------------
        // 点击restore后，恢复原始数据库
        FloatingActionButton restore = (FloatingActionButton)findViewById(R.id.restore);
        restore.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                dbPerson.restore();
                persons.clear();
                ArrayList<Person> p = dbPerson.getPersonList();
                for(int i = 0; i < p.size(); i++)
                    persons.add(p.get(i));
                mainAdapter.notifyDataSetChanged();
            }
        });
    }
}
