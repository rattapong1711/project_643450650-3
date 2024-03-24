package com.example.crudrealtimeadmin;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class User {
    private String name, phone, brand;

    public User(String name, String phone, String brand){
        this.brand = brand;
        this.phone = phone;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getBrand() {
        return brand;
    }
}
