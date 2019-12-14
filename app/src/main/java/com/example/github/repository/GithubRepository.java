package com.example.github.repository;

import android.util.Log;

import com.example.github.models.Intermediate;
import com.example.github.network.GithubApi;
import com.example.github.util.Resource;

import org.reactivestreams.Publisher;

import javax.inject.Inject;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.LiveDataReactiveStreams;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.Observer;
import io.reactivex.Flowable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class GithubRepository {

    private static final String TAG = "GithubRepository";

    private GithubApi githubApi;

    private LiveData<Resource<Intermediate>> data;
    private MediatorLiveData<Resource<Intermediate>> linkData;

    @Inject
    public GithubRepository(GithubApi githubApi){
        this.githubApi = githubApi;
    }

    public void loadData(){
        Log.d(TAG, "searchUser: method call");


        linkData = new MediatorLiveData<>();

        data = LiveDataReactiveStreams.fromPublisher(githubApi.getData()
                .onErrorReturn(new Function<Throwable, Intermediate>() {
                    @Override
                    public Intermediate apply(Throwable throwable) throws Exception {
                        Log.d(TAG, "apply: Error in searching user");
                        return null;
                    }
                }).map(new Function<Intermediate, Resource<Intermediate>>() {
                    @Override
                    public Resource<Intermediate> apply(Intermediate intermediate) throws Exception {
                        Log.d(TAG, "apply: " + intermediate);
                        if(intermediate == null){
                            return Resource.error("error", intermediate);
                        }
                        return Resource.success(intermediate);
                    }
                }).subscribeOn(Schedulers.io()));

        linkData.addSource(data, new Observer<Resource<Intermediate>>() {
            @Override
            public void onChanged(Resource<Intermediate> intermediateResource) {
                linkData.setValue(intermediateResource);
                linkData.removeSource(data);
            }
        });
    }

    public LiveData<Resource<Intermediate>> observeData() {
        return linkData;
    }
}
