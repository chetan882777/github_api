package com.example.github.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Trips {


    @SerializedName("from")
    @Expose()
    private String from;


    @SerializedName("to")
    @Expose()
    private String to;


    @SerializedName("from_time")
    @Expose()
    private String from_time;


    @SerializedName("to_time")
    @Expose()
    private String to_time;


    @SerializedName("cost")
    @Expose()
    private Credits cost;


    @SerializedName("trip_duration_in_mins")
    @Expose()
    private String trip_duration_in_mins;

    public Trips(String from, String to, String from_time, String to_time, Credits cost, String trip_duration_in_mins) {
        this.from = from;
        this.to = to;
        this.from_time = from_time;
        this.to_time = to_time;
        this.cost = cost;
        this.trip_duration_in_mins = trip_duration_in_mins;
    }

    public Trips() {
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getFrom_time() {
        return from_time;
    }

    public void setFrom_time(String from_time) {
        this.from_time = from_time;
    }

    public String getTo_time() {
        return to_time;
    }

    public void setTo_time(String to_time) {
        this.to_time = to_time;
    }

    public Credits getCost() {
        return cost;
    }

    public void setCost(Credits cost) {
        this.cost = cost;
    }

    public String getTrip_duration_in_mins() {
        return trip_duration_in_mins;
    }

    public void setTrip_duration_in_mins(String trip_duration_in_mins) {
        this.trip_duration_in_mins = trip_duration_in_mins;
    }

    @Override
    public String toString() {
        return "Trips{" +
                "from='" + from + '\'' +
                ", to='" + to + '\'' +
                ", from_time='" + from_time + '\'' +
                ", to_time='" + to_time + '\'' +
                ", cost=" + cost +
                ", trip_duration_in_mins='" + trip_duration_in_mins + '\'' +
                '}';
    }
}
