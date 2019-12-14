package com.example.github.di;

import com.bumptech.glide.request.RequestOptions;
import com.example.github.R;
import com.example.github.network.GithubApi;
import com.example.github.repository.GithubRepository;
import com.example.github.util.Constants;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import androidx.room.PrimaryKey;
import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.github.util.Constants.CONNECTION_TIMEOUT;
import static com.example.github.util.Constants.READ_TIMEOUT;
import static com.example.github.util.Constants.WRITE_TIMEOUT;

@Module
public class AppModule {

    @Singleton
    @Provides
    static Retrofit provideRetrofitInstance(OkHttpClient client){
        return new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .client(client)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    @Singleton
    @Provides
    static OkHttpClient providesOkHttpClient(){
        return new OkHttpClient.Builder()
                .connectTimeout(CONNECTION_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
                .build();
    }

    @Singleton
    @Provides
    static GithubApi providesGithubApi(Retrofit retrofit){
        return retrofit.create(GithubApi.class);
    }

    @Singleton
    @Provides
    static GithubRepository providesGithubRepository( GithubApi githubApi){
        return new GithubRepository(githubApi);
    }

    @Singleton
    @Provides
    static RequestOptions providesRequestOptions(){
        return new RequestOptions()
                .centerCrop()
                .placeholder(R.mipmap.ic_launcher_round)
                .error(R.mipmap.ic_launcher_round);
    }
}
