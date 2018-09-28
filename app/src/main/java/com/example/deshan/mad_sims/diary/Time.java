package com.example.deshan.mad_sims.diary;

import java.io.Serializable;

public class Time implements Serializable{

    private int hours;
    private int minutes;

    public Time(){
        this.hours = 14;
        this.minutes = 42;
    }

    public Time(int hours, int minutes) {
        this.hours = hours;
        this.minutes = minutes;
    }

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    public int getMinutes() {
        return minutes;
    }

    public void setMinutes(int minutes) {
        this.minutes = minutes;
    }

    public static Time valueOf(String time){
        Time t = new Time();
        String[] values = time.split(":");

        if(values.length == 2){
            t.setHours(Integer.valueOf(values[0]));
            t.setMinutes(Integer.valueOf(values[1]));
            //sd
            return t;
        }

        return null;
    }

    @Override
    public String toString() {
        return hours + ":" + minutes ;
    }
}
