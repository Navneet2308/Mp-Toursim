package com.example.madhyapradeshtourism;

import java.io.Serializable;

public class CategoryModel implements Serializable {
    String name;
    String img;


    public CategoryModel() {
    }

    public CategoryModel(String name, String img,String id) {
        this.name = name;
        this.img = img;

    }
    //getter


    public String getName() {
        return name;
    }

    public String getImg() {
        return img;
    }




    public void setName(String name) {
        this.name = name;
    }

    public void setImg(String img) {
        this.img = img;
    }


}
