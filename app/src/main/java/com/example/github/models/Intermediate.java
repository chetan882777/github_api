package com.example.github.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Intermediate {


    @SerializedName("message")
    @Expose()
    private String message;

    @SerializedName("success")
    @Expose()
    private String success;

    @SerializedName("data")
    @Expose()
    private Data data;

    public Intermediate(String message, String success, Data data) {
        this.message = message;
        this.success = success;
        this.data = data;
    }

    public Intermediate() {
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    @Override
    public String toString() {
        return "Intermediate{" +
                "message='" + message + '\'' +
                ", success='" + success + '\'' +
                ", data=" + data +
                '}';
    }
}
