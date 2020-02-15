package codes.tuton.tripexpensemanager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import java.util.List;

import codes.tuton.tripexpensemanager.Adaptor.AdaptorViewPerson;
import codes.tuton.tripexpensemanager.Model.PersonModel;
import codes.tuton.tripexpensemanager.Model.TripModel;

public class ActivityTripDetails extends AppCompatActivity {

    private final String TAG = this.getClass().getName();

    RecyclerView recyclerViewPersonDetails;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager layoutManager;
    private ImageView tripImageIV;
    int[] ids = new int[]{R.drawable.trip,R.drawable.trip1,R.drawable.trip2,R.drawable.trip3,R.drawable.trip4 };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trip_details);
        tripImageIV = findViewById(R.id.tripImageIV);

        int tripPosition = getIntent().getIntExtra("TRIP_POSITION", -1);
        TripModel currentTripModel = MainActivity.tripModelList.get(tripPosition);
        int idTrip = currentTripModel.getPrimaryId();
        if ((idTrip+1)%5 == 0) {
            tripImageIV.setImageResource(ids[4]);
        } else if ((idTrip+1)%4 == 0) {
            tripImageIV.setImageResource(ids[3]);
        } else if ((idTrip+1)%3 == 0) {
            tripImageIV.setImageResource(ids[2]);
        } else if ((idTrip+1)%2 == 0) {
           tripImageIV.setImageResource(ids[1]);
        } else {
            tripImageIV.setImageResource(ids[0]);
        }

        recyclerViewPersonDetails = findViewById(R.id.recyclerViewPersonDetails);
        adapter = new AdaptorViewPerson(currentTripModel.getPersonModelList(),this);
        layoutManager = new LinearLayoutManager(this);
        recyclerViewPersonDetails.setAdapter(adapter);
        recyclerViewPersonDetails.setLayoutManager(layoutManager);

    }

}
