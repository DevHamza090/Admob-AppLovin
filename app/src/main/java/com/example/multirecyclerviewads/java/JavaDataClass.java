package com.example.multirecyclerviewads.java;

import java.util.Date;

public class JavaDataClass {
    private String name;
    private Date date;

    public JavaDataClass() {
    }

    public JavaDataClass(String name, Date date) {
        this.name = name;
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}

