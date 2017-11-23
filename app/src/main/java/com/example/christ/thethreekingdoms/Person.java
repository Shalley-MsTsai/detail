package com.example.christ.thethreekingdoms;

import java.io.Serializable;

/**
 * Created by christ on 2017/11/21.
 * 三国人物
 */

public class Person implements Serializable{
    final static String ID = "id";
    final static String PIC = "pic";
    final static String NAME = "name";
    final static String SEX = "sex";
    final static String BIRTH_DEATH = "birth_death";
    final static String BIRTHPLACE = "birthplace";
    final static String NATION = "nation";

    private int id;  // 作为主键而附加的
    private int pic;  // 图片id
    private String name;  // 名字
    private String sex;  // 性别
    private String birth_death;  // 生卒年月
    private String birthplace;  // 籍贯
    private String nation;  // 所属势力

    Person(int id, int pic, String name, String sex, String birth_death, String birthplace,
           String nation){
        this.id = id;
        this.pic = pic;
        this.name = name;
        this.sex = sex;
        this.birth_death = birth_death;
        this.birthplace = birthplace;
        this.nation = nation;
    }
    Person(int pic, String name, String sex, String birth_death, String birthplace,
           String nation){
        this.pic = pic;
        this.name = name;
        this.sex = sex;
        this.birth_death = birth_death;
        this.birthplace = birthplace;
        this.nation = nation;
    }
    public int getId(){
        return id;
    }
    public int getPic(){
        return pic;
    }
    public String getName(){
        return name;
    }
    public String getSex(){
        return sex;
    }
    public String getBirth_death(){
        return birth_death;
    }
    public String getBirthplace(){
        return birthplace;
    }
    public String getNation(){
        return nation;
    }

    public void setId(int id) {
        this.id = id;
    }
    public void setPic(int pic){
        this.pic = pic;
    }
    public void setName(String name){
        this.name = name;
    }
    public void setSex(String sex){
        this.sex = sex;
    }
    public void setBirthDeath(String birth_death){
        this.birth_death = birth_death;
    }
    public void setBirthplace(String birthplace){
        this.birthplace = birthplace;
    }
    public void setNation(String nation){
        this.nation = nation;
    }

}
