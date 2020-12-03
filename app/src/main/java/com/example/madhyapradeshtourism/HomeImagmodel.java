package com.example.madhyapradeshtourism;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.ColorSpace;
import android.os.Bundle;

import java.io.Serializable;

public class HomeImagmodel implements Serializable
{
    String category;

    String name;
    String id;
    String cityid;
    String placename;
    String hinoverview;
    String timings;
    String timerequired;
    String photo;
    String entryfees;
    String address;
    String buses;
    String ratings;
    String enoverview;
    String placecategory;
    String placeid;

    public String getPlaceid() {
        return placeid;
    }

    public void setPlaceid(String placeid) {
        this.placeid = placeid;
    }


    public String getPlacecategory() {
        return placecategory;
    }

    public void setPlacecategory(String placecategory) {
        this.placecategory = placecategory;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCityid() {
        return cityid;
    }

    public void setCityid(String cityid) {
        this.cityid = cityid;
    }

    public String getPlacename() {
        return placename;
    }

    public void setPlacename(String placename) {
        this.placename = placename;
    }

    public String getHinoverview() {
        return hinoverview;
    }

    public void setHinoverview(String hinoverview) {
        this.hinoverview = hinoverview;
    }

    public String getTimings() {
        return timings;
    }

    public void setTimings(String timings) {
        this.timings = timings;
    }

    public String getTimerequired() {
        return timerequired;
    }

    public void setTimerequired(String timerequired) {
        this.timerequired = timerequired;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getEntryfees() {
        return entryfees;
    }

    public void setEntryfees(String entryfees) {
        this.entryfees = entryfees;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getBuses() {
        return buses;
    }

    public void setBuses(String buses) {
        this.buses = buses;
    }

    public String getRatings() {
        return ratings;
    }

    public void setRatings(String ratings) {
        this.ratings = ratings;
    }

    public String getEnoverview() {
        return enoverview;
    }

    public void setEnoverview(String enoverview) {
        this.enoverview = enoverview;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}