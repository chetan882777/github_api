package com.example.github.di;

import com.example.github.di.main.MainModule;
import com.example.github.di.main.MainViewModelModule;
import com.example.github.ui.main.MainActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBuildersModule {

    @ContributesAndroidInjector(
            modules = {
                    MainViewModelModule.class,
                    MainModule.class
            }
    )
    abstract MainActivity contributeMainActivity();
}
