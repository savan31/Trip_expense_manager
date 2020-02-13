package codes.tuton.tripexpensemanager.Adaptor;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import codes.tuton.tripexpensemanager.Model.TripModel;
import codes.tuton.tripexpensemanager.R;

public class AdaptorTripDetails extends RecyclerView.Adapter<AdaptorTripDetails.MainViewHolder> {
    

    public class MainViewHolder extends RecyclerView.ViewHolder{

        ImageView tripImageIV;
        TextView tripNameTV,dateTV,rsTV;

        public MainViewHolder(@NonNull View i) {
            super(i);

            tripImageIV = i.findViewById(R.id.tripImageIV);
            tripNameTV = i.findViewById(R.id.tripNameTV);
            dateTV = i.findViewById(R.id.dateTV);
            rsTV = i.findViewById(R.id.rsTV);

        }
    }
    @NonNull
    @Override
    public MainViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_trip,parent,false);
        MainViewHolder mainViewHolder = new MainViewHolder(view);
        return mainViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MainViewHolder holder, int position) {
        holder.tripImageIV.setImageResource(R.drawable.trip);
        holder.tripNameTV.setText("Rajasthan");
        holder.dateTV.setText("18 Feb 2020");
        holder.rsTV.setText("500 ₹");
    }

    @Override
    public int getItemCount() {
        return 2;
    }


}
