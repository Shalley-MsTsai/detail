package com.example.christ.thethreekingdoms;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Person person = (Person) getIntent().getSerializableExtra("person");
        loadPerson(person);

    }
    private void loadPerson(Person person){
        ImageView pic = (ImageView)findViewById(R.id.detail_pic);
        TextView name = (TextView)findViewById(R.id.detail_name);
        TextView sex = (TextView)findViewById(R.id.detail_sex);

        pic.setImageResource(person.getPic());
        name.setText(person.getName());
        sex.setText(person.getSex());
    }
}
