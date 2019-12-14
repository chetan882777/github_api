package com.example.github.ui.main;

import com.example.github.models.Intermediate;
import com.example.github.repository.GithubRepository;
import com.example.github.util.Resource;

import javax.inject.Inject;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

public class MainViewModel extends ViewModel {

    private final GithubRepository repository;
    private LiveData<Resource<Intermediate>> resourceLiveData;

    @Inject
    public MainViewModel(GithubRepository repository){
        this.repository = repository;
    }

    public void loadData(){
        repository.loadData();
    }

    public LiveData<Resource<Intermediate>> observeData(){
        resourceLiveData = repository.observeData();
        return resourceLiveData;
    }

    public LiveData<Resource<Intermediate>> getData(){
        return resourceLiveData;
    }

}
