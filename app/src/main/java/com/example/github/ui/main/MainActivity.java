package com.example.github.ui.main;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.github.R;
import com.example.github.models.Intermediate;
import com.example.github.models.Trips;
import com.example.github.util.Resource;
import com.google.android.material.tabs.TabLayout;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import dagger.android.support.DaggerAppCompatActivity;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;

import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import javax.inject.Inject;

public class MainActivity extends DaggerAppCompatActivity {

    private static final String TAG = "MainActivity";

    @Inject
    ViewModelProviderFactory providerFactory;

    @Inject
    RequestOptions requestOptions;

    private MainViewModel viewModel;
    private ImageView profileImageView;
    private TextView nameTextView;
    private TextView addressTextView;
    private TextView ridesTextView;
    private TextView freeRidesTextView;
    private TextView creditsTextView;
    private RecyclerView recyclerView;
    private TripsAdapter adapter;
    private Trips[] trips;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("My Profile");
        getSupportActionBar().setElevation(0f);

        profileImageView = findViewById(R.id.profile_image);
        nameTextView = findViewById(R.id.profile_name);
        addressTextView = findViewById(R.id.profile_address);
        ridesTextView = findViewById(R.id.textView_rides);
        freeRidesTextView = findViewById(R.id.textView_freeRides);
        creditsTextView = findViewById(R.id.textView_credits);

        progressBar = findViewById(R.id.progressBar);
        progressBar.setVisibility(View.VISIBLE);


        viewModel = ViewModelProviders.of(this, providerFactory).get(MainViewModel.class);
        viewModel.loadData();


        setAdapter();
        if(savedInstanceState != null){
            progressBar.setVisibility(View.GONE);
            LiveData<Resource<Intermediate>> data = viewModel.getData();
            setData(data.getValue());
        }else {
            if (isConnectedToNetwork(this)) {
                subscribeObservers();
            } else {
                progressBar.setVisibility(View.GONE);
                Toast.makeText(MainActivity.this,
                        "Something went wrong! try again later", Toast.LENGTH_LONG)
                        .show();

            }
        }
    }


    private void subscribeObservers() {

        progressBar.setVisibility(View.VISIBLE);
        viewModel.observeData().observe(this, new Observer<Resource<Intermediate>>() {
            @Override
            public void onChanged(Resource<Intermediate> intermediateResource) {
                if (intermediateResource != null) {
                    switch (intermediateResource.status) {
                        case LOADING: {
                            progressBar.setVisibility(View.VISIBLE);
                            break;
                        }

                        case ERROR: {
                            Log.e(TAG, "onChanged: ERROR ");
                            Toast.makeText(MainActivity.this,
                                    "Something went wrong! try again later", Toast.LENGTH_LONG)
                                    .show();
                            progressBar.setVisibility(View.GONE);
                            break;
                        }

                        case SUCCESS: {
                            progressBar.setVisibility(View.GONE);
                            Log.d(TAG, "onChanged: Intermediate " + intermediateResource.data);
                            setData(intermediateResource);
                            break;
                        }
                    }
                } else {
                    Log.e(TAG, "onChanged: ERROR - RESOURCE NULL ...");
                }
            }
        });


    }

    private void setData(Resource<Intermediate> intermediateResource) {

        if(intermediateResource != null) {
            Intermediate data = intermediateResource.data;


            Glide.with(this)
                    .load(data.getData().getProfile().getMiddle_name())
                    .apply(requestOptions)
                    .into(profileImageView);

            String name = data.getData().getProfile().getFirst_name() + " " + data.getData().getProfile().getLast_name();
            nameTextView.setText(name);

            String address = data.getData().getProfile().getCity() + ", " + data.getData().getProfile().getCountry();
            addressTextView.setText(address);

            ridesTextView.setText(data.getData().getStats().getRides());
            freeRidesTextView.setText(data.getData().getStats().getFree_rides());

            String credits = data.getData().getStats().getCredits().getCurrency_symbol() + "" + data.getData().getStats().getCredits().getValue();
            creditsTextView.setText(credits);

            trips = data.getData().getTrips();

            adapter.setTrips(trips);

            adapter.notifyDataSetChanged();
        }
        else{
            Toast.makeText(MainActivity.this,
                    "Something went wrong! try again later", Toast.LENGTH_LONG)
                    .show();

        }
    }


    private void setAdapter() {

        recyclerView = findViewById(R.id.trips_recycler_view);
        adapter = new TripsAdapter(new Trips[0]);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);

        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setAdapter(adapter);
    }

    public boolean isConnectedToNetwork(Context context) {
        ConnectivityManager connectivityManager =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        boolean isConnected = false;
        if (connectivityManager != null) {
            NetworkInfo activeNetwork = connectivityManager.getActiveNetworkInfo();
            isConnected = (activeNetwork != null) && (activeNetwork.isConnectedOrConnecting());
        }

        return isConnected;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.action_refresh){
            subscribeObservers();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}