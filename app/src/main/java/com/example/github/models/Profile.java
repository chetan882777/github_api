package com.example.github.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Profile {

    @SerializedName("first_name")
    @Expose()
    private String first_name;


    @SerializedName("middle_name")
    @Expose()
    private String middle_name;


    @SerializedName("last_name")
    @Expose()
    private String last_name;


    @SerializedName("profile_image")
    @Expose()
    private String profile_image;


    @SerializedName("city")
    @Expose()
    private String city;


    @SerializedName("Country")
    @Expose()
    private String country;

    public Profile(String first_name, String middle_name, String last_name, String profile_image, String city, String country) {
        this.first_name = first_name;
        this.middle_name = middle_name;
        this.last_name = last_name;
        this.profile_image = profile_image;
        this.city = city;
        this.country = country;
    }


    public Profile() {
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getMiddle_name() {
        return middle_name;
    }

    public void setMiddle_name(String middle_name) {
        this.middle_name = middle_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getProfile_image() {
        return profile_image;
    }

    public void setProfile_image(String profile_image) {
        this.profile_image = profile_image;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public String toString() {
        return "Profile{" +
                "first_name='" + first_name + '\'' +
                ", middle_name='" + middle_name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", profile_image='" + profile_image + '\'' +
                ", city='" + city + '\'' +
                ", country='" + country + '\'' +
                '}';
    }
}
