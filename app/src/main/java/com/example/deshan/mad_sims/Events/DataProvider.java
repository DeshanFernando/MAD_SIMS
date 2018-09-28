package com.example.deshan.mad_sims.Events;


public class DataProvider {

    String id,name,date,type,venue;

    public DataProvider(String id,String name,String date,String type,String venue){

        this.id = id;
        this.name = name;
        this.date = date;
        this.type = type;
        this.venue = venue;

    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getVenue() {
        return venue;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }
}
