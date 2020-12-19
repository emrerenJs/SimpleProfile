package com.example.simpleprofile.Models;

import java.io.Serializable;
import java.util.ArrayList;

public class Profile implements Serializable {
    private String firstname;
    private String lastname;
    private boolean gender;
    private ArrayList<String> hobbies;
    private String city;
    private int age;

    public Profile(){}

    public Profile(String firstname, String lastname, boolean gender, ArrayList<String> hobbies, String city,int age) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.gender = gender;
        this.hobbies = hobbies;
        this.city = city;
        this.age = age;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public ArrayList<String> getHobbies() {
        return hobbies;
    }

    public void setHobbies(ArrayList<String> hobbies) {
        this.hobbies = hobbies;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
