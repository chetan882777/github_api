package com.example.github.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Arrays;

public class Data {

    @SerializedName("profile")
    @Expose()
    private Profile profile;


    @SerializedName("stats")
    @Expose()
    private Stats stats;


    @SerializedName("trips")
    @Expose()
    private Trips[] trips;


    @SerializedName("theme")
    @Expose()
    private Theme theme;

    public Data(Profile profile, Stats stats, Trips[] trips, Theme theme) {
        this.profile = profile;
        this.stats = stats;
        this.trips = trips;
        this.theme = theme;
    }

    public Data() {
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    public Stats getStats() {
        return stats;
    }

    public void setStats(Stats stats) {
        this.stats = stats;
    }

    public Trips[] getTrips() {
        return trips;
    }

    public void setTrips(Trips[] trips) {
        this.trips = trips;
    }

    public Theme getTheme() {
        return theme;
    }

    public void setTheme(Theme theme) {
        this.theme = theme;
    }

    @Override
    public String toString() {
        return "Data{" +
                "profile=" + profile +
                ", stats=" + stats +
                ", trips=" + Arrays.toString(trips) +
                ", theme=" + theme +
                '}';
    }
}
