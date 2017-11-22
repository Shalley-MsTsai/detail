package com.example.christ.thethreekingdoms;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.concurrent.TimeoutException;

/**
 * Created by christ on 2017/11/21.
 * 用于主界面中人物列表的加载
 */

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.MyViewHolder> {

    private Context context;
    private ArrayList<Person> persons;
    private OnItemClickListener onItemClickListener;

    public MainAdapter(Context context, ArrayList<Person> persons){
        this.context = context;
        this.persons = persons;
    }
    // 创建ViewHolder，用于回收不用的view来重复利用
    @Override
    public MyViewHolder onCreateViewHolder(final ViewGroup parent, int viewType){
        View view = LayoutInflater.from(context).inflate(R.layout.main_item, parent, false);
        MyViewHolder viewHolder = new MyViewHolder(view);
        return viewHolder;
    }

    // 更改ViewHolder的view的内容
    @Override
    public void onBindViewHolder(final MyViewHolder viewHolder, int position){
        // view内容的更改
        viewHolder.name.setText(persons.get(position).getName());
        viewHolder.sex.setText(persons.get(position).getSex());
        viewHolder.birth_death.setText(persons.get(position).getBirth_death());
        viewHolder.birthplace.setText(persons.get(position).getBirthplace());
        viewHolder.nation.setText(persons.get(position).getNation());
        viewHolder.pic.setImageResource(persons.get(position).getPic());

        // 点击事件
        if(onItemClickListener != null) {
            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onItemClickListener.onClick(viewHolder.getAdapterPosition());
                }
            });
            viewHolder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    onItemClickListener.onLongClick(viewHolder.getAdapterPosition());
                    return false;
                }
            });
        }
    }

    // 返回人物列表个数
    @Override
    public int getItemCount(){
        if(persons == null)
            return 0;
        return persons.size();
    }

    // 设置点击监听器
    public interface OnItemClickListener {
        void onClick(int position);
        void onLongClick(int position);
    }
    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView name;
        private TextView sex;
        private TextView birth_death;
        private TextView birthplace;
        private TextView nation;
        private ImageView pic;
        public MyViewHolder(View view){
            super(view);
            name = (TextView)view.findViewById(R.id.name);
            sex = (TextView)view.findViewById(R.id.sex);
            birth_death = (TextView)view.findViewById(R.id.birth_death);
            birthplace = (TextView)view.findViewById(R.id.birthplace);
            nation = (TextView)view.findViewById(R.id.nation);
            pic = (ImageView)view.findViewById(R.id.pic);
        }
    }
}
