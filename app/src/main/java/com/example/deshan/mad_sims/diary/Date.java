package com.example.deshan.mad_sims.diary;

import java.io.Serializable;

public class Date implements Serializable{

    private int year;
    private int month;
    private int day;

    public Date(){
        this.year = 2018;
        this.month = 9;
        this.day = 28;
    }

    public Date(int year, int month, int day) {
        this.year = year;
        this.month = month;
        this.day = day;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public static Date valueOf(String date){
        Date d = new Date();
        String[] values = date.split("-");

        if(values.length == 3){
            d.setYear(Integer.valueOf(values[0]));
            d.setMonth(Integer.valueOf(values[1]));
            d.setDay(Integer.valueOf(values[2]));

            return d;
        }

        return null;
    }

    @Override
    public String toString() {
        return year + "-" + month + "-" + day;
    }
}
