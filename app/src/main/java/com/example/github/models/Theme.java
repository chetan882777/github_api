package com.example.github.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Theme {


    @SerializedName("dark_colour")
    @Expose()
    private String dark_colour;

    @SerializedName("light_colour")
    @Expose()
    private String light_colour;

    public Theme(String dark_colour, String light_colour) {
        this.dark_colour = dark_colour;
        this.light_colour = light_colour;
    }

    public Theme() {
    }

    public String getDark_colour() {
        return dark_colour;
    }

    public void setDark_colour(String dark_colour) {
        this.dark_colour = dark_colour;
    }

    public String getLight_colour() {
        return light_colour;
    }

    public void setLight_colour(String light_colour) {
        this.light_colour = light_colour;
    }

    @Override
    public String toString() {
        return "Theme{" +
                "dark_colour='" + dark_colour + '\'' +
                ", light_colour='" + light_colour + '\'' +
                '}';
    }
}
