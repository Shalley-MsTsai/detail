package com.example.christ.thethreekingdoms;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

import jp.wasabeef.recyclerview.adapters.ScaleInAnimationAdapter;

public class MainActivity extends AppCompatActivity {

    final int REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // -------------------测试数据库的显示---------------------
        // 获取数据库的所有元组
        final DBPerson dbPerson = new DBPerson(MainActivity.this);
        //final ArrayList<Person> persons = dbPerson.getPersonList();

        // ------------------测试数据库的条件查询语句--------------
        // 查询吴国的女性人物
        String[] attributes = new String[]{"nation", "sex"};
        String[] values = new String[]{"吴", "女"};
        final ArrayList<Person> persons = dbPerson.getPersonList(attributes, values);

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
                Intent intent = new Intent(MainActivity.this, DetailActivity.class);
                intent.putExtra("person", persons.get(position));
                startActivityForResult(intent, REQUEST_CODE);
            }
            // ---------------测试数据库的元组删除--------------------
            // 长按删除
            @Override
            public void onLongClick(final int position) {
                final AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity.this);
                alertDialog.setTitle("删除人物").setMessage("从列表中移除"
                        +persons.get(position).getName()+"?").setPositiveButton("确认",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int which){
                                Toast.makeText(MainActivity.this,"移除"+persons.get(position).getName(),
                                        Toast.LENGTH_SHORT).show();
                                // 删除数据库相关项
                                dbPerson.delete(persons.get(position).getId());
                                persons.remove(position);
                                mainAdapter.notifyDataSetChanged();
                            }
                        }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int which) {
                                /*Toast.makeText(MainActivity.this,
                                        "您选择了取消", Toast.LENGTH_SHORT).show();*/
                            }
                        }).create().show();;
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

    }
}
