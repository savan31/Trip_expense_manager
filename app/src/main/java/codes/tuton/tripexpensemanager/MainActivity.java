package codes.tuton.tripexpensemanager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import codes.tuton.tripexpensemanager.Adaptor.AdaptorPersonDetails;
import codes.tuton.tripexpensemanager.Adaptor.AdaptorTripDetails;
import codes.tuton.tripexpensemanager.Model.PersonModel;
import codes.tuton.tripexpensemanager.Model.TripModel;

public class MainActivity extends AppCompatActivity {

    private final String TAG = this.getClass().getName();
    //Bind View
    FloatingActionButton floatingActionButton;

    //Recycler View
    RecyclerView recyclerViewTrip;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager layoutManager;
    List<TripModel> tripModelList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bindView();//Bind View
        onClickEventHandler();//Click event handler
        setRecyclerViewPerson(); //Recycler View Set



    }

    void onClickEventHandler(){ //On Click handler
        Log.i(TAG, "OnClick handler");
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,TripDataAdd.class));
            }
        });

    }

    void bindView(){//Bind View
        Log.i(TAG, "Bind view");
        floatingActionButton = findViewById(R.id.floatingActionButton);
        recyclerViewTrip = findViewById(R.id.recyclerViewTrip);

        adapter = new  AdaptorTripDetails(tripModelList,this);
        layoutManager = new GridLayoutManager(this,2);
    }

    //TODO : AddTrip RecyclerView
    void setRecyclerViewPerson(){ //RecyclerView Set
        Log.i(TAG, "Recycler View Set");
        recyclerViewTrip.setAdapter(adapter);
        recyclerViewTrip.setLayoutManager(layoutManager);
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        if (tripModelList.size() > 0) {
            tripModelList.clear();
        }
        addAllTripData(); //Add Trip Data in tripModelList
    }

    //TODO : Add Trip Data in tripModelList
    void addAllTripData(){
        DataBaseHelper dataBaseHelper = new DataBaseHelper(this);
        tripModelList.addAll(dataBaseHelper.getAllGoalTrip());

        for (int i = 0; i < tripModelList.size(); i++) {
            tripModelList.get(i).setPersonModelList(dataBaseHelper.getAllGoalPerson(tripModelList.get(i).getPrimaryId()));
            int totalGetMoney = 0;
            int totalUsedmoney = 0;
            for(PersonModel personModel : tripModelList.get(i).getPersonModelList()) {
                totalGetMoney += personModel.getAmountCredit();
                totalUsedmoney += personModel.getAmountDebit();
            }

            tripModelList.get(i).setDebitMoney(totalGetMoney - totalUsedmoney);
            adapter.notifyItemChanged(i);
        }
        Log.i(TAG,"Data Add In tripModelList");
    }

}
