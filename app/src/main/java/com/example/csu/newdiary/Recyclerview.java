package com.example.csu.newdiary;

//This class just houses the get/set methods for the recyclerview class

public class Recyclerview {
    private String title;
    private String date;
    private String content;

    public Recyclerview (){

    }

    public Recyclerview (String title ,String date, String content){
        this.title=title;
        this.date=date;
        this.content=content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }


}
