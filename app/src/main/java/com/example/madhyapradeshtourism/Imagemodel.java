package com.example.madhyapradeshtourism;

import android.content.ServiceConnection;

import java.io.Serializable;

public class Imagemodel implements Serializable {

    String photo;
    String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
}
