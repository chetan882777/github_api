package com.example.github.network;

import com.example.github.models.Intermediate;
import io.reactivex.Flowable;
import retrofit2.http.GET;

public interface GithubApi{

    @GET("iranjith4/522d5b466560e91b8ebab54743f2d0fc/raw/" +
            "7b108e4aaac287c6c3fdf93c3343dd1c62d24faf/radius-mobile-intern")
    Flowable<Intermediate> getData();


}
