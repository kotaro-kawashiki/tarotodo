package com.example.fabrikam.TodoDemo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.Time;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;


@Entity
public class TodoItem {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private String category;
    private String name;
    private boolean complete;
    //2018/8/8 added
//    private Date date;
//    private Time time;


    public TodoItem() {}

    public TodoItem(String category, String name) {
        this.category = category;
        this.name = name;
        this.complete = false;
        //2018/8/8 added
//        Calendar cl = Calendar.getInstance();
//        //SimpleDateFormatクラスでフォーマットパターンを設定する
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
//        this.date = sdf.parse(sdf.format(cl.getTime()));
//        this.time = time;
    }

    @Override
    public String toString() {
        return String.format(
                "TodoItem[id=%d, category='%s', name='%s', complete='%b']",
                id, category, name, complete);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        return;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
        return;
    }

     public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
        return;
    }

    public boolean isComplete() {
        return complete;
    }

    public void setComplete(boolean complete) {
        this.complete = complete;
        return;
    }
}