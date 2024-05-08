package com.example.listviewdemo.model;

public class Technology {
    private int img;
    private String name, sub, derscribe;

    public Technology(int img, String name, String sub, String derscribe) {
        this.img = img;
        this.name = name;
        this.sub = sub;
        this.derscribe = derscribe;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSub() {
        return sub;
    }

    public void setSub(String sub) {
        this.sub = sub;
    }

    public String getDerscribe() {
        return derscribe;
    }

    public void setDerscribe(String derscribe) {
        this.derscribe = derscribe;
    }
}
