package com.example.github.ui.main;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.github.R;
import com.example.github.models.Data;
import com.example.github.models.Trips;

import java.text.SimpleDateFormat;
import java.util.Date;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class TripsAdapter extends RecyclerView.Adapter<TripsAdapter.TripsViewHolder> {

    private Trips[] trips;

    public TripsAdapter(Trips[] trips){
        this.trips = trips;
    }

    @NonNull
    @Override
    public TripsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new TripsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TripsViewHolder holder, int position) {
        holder.fromTextView.setText(trips[position].getFrom());
        holder.toTextView.setText(trips[position].getTo());
        holder.currencySymbolTextView.setText(trips[position].getCost().getCurrency_symbol());
        holder.currencyTextView.setText(trips[position].getCost().getValue());


        String timeInMin = "Travel time: ";
        int hours = 0,min = 0;
        int time = Integer.parseInt(trips[position].getTrip_duration_in_mins());
        hours = time/60;
        min = time % 60;

        if(hours > 0){
            timeInMin += hours + "h";
        }
        if(min > 0){
            timeInMin += " " + min + "min";
        }

        holder.timeInMinTextView.setText(timeInMin);

        Date fromDate = new Date(Long.parseLong(trips[position].getFrom_time()));
        Date toDate = new Date(Long.parseLong(trips[position].getTo_time()));

        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

        String fromDateString = formatter.format(fromDate);
        String toDateString = formatter.format(toDate);

        holder.fromTimeTextView.setText(fromDateString);

        holder.toTimeTextView.setText(toDateString);

    }

    @Override
    public int getItemCount() {
        return trips.length;
    }


    class TripsViewHolder extends RecyclerView.ViewHolder{

        final TextView fromTextView;
        final TextView fromTimeTextView;
        final TextView toTextView;
        final TextView toTimeTextView;
        final TextView currencySymbolTextView;
        final TextView currencyTextView;
        final TextView timeInMinTextView;

        public TripsViewHolder(@NonNull View itemView) {
            super(itemView);

            fromTextView = itemView.findViewById(R.id.textView_from);
            fromTimeTextView = itemView.findViewById(R.id.textView_fromTime);
            toTextView = itemView.findViewById(R.id.textView_to);
            toTimeTextView = itemView.findViewById(R.id.textView_toTime);
            currencySymbolTextView = itemView.findViewById(R.id.textView_currencySymbol);
            currencyTextView = itemView.findViewById(R.id.textView_currency);
            timeInMinTextView = itemView.findViewById(R.id.textView_timeInMin);
        }
    }

    public void setTrips(Trips[] trips){
        this.trips = trips;
    }
}
