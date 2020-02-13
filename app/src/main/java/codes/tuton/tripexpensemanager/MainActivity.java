package codes.tuton.tripexpensemanager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import codes.tuton.tripexpensemanager.Adaptor.AdaptorTripDetails;

public class MainActivity extends AppCompatActivity {

    FloatingActionButton floatingActionButton;
    RecyclerView recyclerViewTrip;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        floatingActionButton = findViewById(R.id.floatingActionButton);
        recyclerViewTrip = findViewById(R.id.recyclerViewTrip);

        adapter = new  AdaptorTripDetails();
        layoutManager = new GridLayoutManager(this,2);

        recyclerViewTrip.setAdapter(adapter);
        recyclerViewTrip.setLayoutManager(layoutManager);


        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,TripDataAdd.class));
            }
        });



    }
}
