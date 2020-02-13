package codes.tuton.tripexpensemanager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import codes.tuton.tripexpensemanager.Adaptor.AdaptorPersonDetails;
import codes.tuton.tripexpensemanager.Model.PersonModel;
import codes.tuton.tripexpensemanager.Model.TripModel;

public class TripDataAdd extends AppCompatActivity {
    //Tag
    private final String TAG = this.getClass().getName();

    //Bind View
    private EditText nameTripET;
    private EditText descriptionET;
    private TextView dateTV;
    private ImageView addPersonImageView;
    private ImageView calenderDialoImageView;
    private Button saveTripBTN;

    //Recycler View
    private RecyclerView recyclerViewPerson; //Recycler View
    private RecyclerView.LayoutManager layoutManager; //Layout Manager
    private RecyclerView.Adapter adapter; //Adapter
    private List<PersonModel> personModelList = new ArrayList<>(); //Recycler View Data

    //Dialog View
    EditText nameET;
    EditText amountET;
    TextView cancelTV;
    TextView okTV;

    //TripData
    private String tripNameString, tripDescriptionString, tripDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trip_data_add);

        bindView();//Bind View
        onClickEventHandler();//Click event handler
        setRecyclerViewPerson(); //Recycler View Set
    }

    void bindView(){//Bind View
        Log.i(TAG, "Bind view");
        nameTripET = findViewById(R.id.nameTripET);
        descriptionET = findViewById(R.id.descriptionET);
        dateTV = findViewById(R.id.dateTV);
        recyclerViewPerson = findViewById(R.id.recyclerViewPerson);
        saveTripBTN = findViewById(R.id.saveTripBTN);
        addPersonImageView = findViewById(R.id.addPersonImageView);
        calenderDialoImageView = findViewById(R.id.calenderDialoImageView);

        //TodayDefualtDate
        String date = new SimpleDateFormat("dd-MMM-yyyy ", Locale.getDefault()).format(new Date());
        dateTV.setText(date);

        layoutManager = new LinearLayoutManager(this); //Layout Manager
        adapter = new AdaptorPersonDetails(personModelList , this);//Adapter
    }

    void onClickEventHandler(){ //On Click handler
        Log.i(TAG, "OnClick handler");

        calenderDialoImageView.setOnClickListener(new View.OnClickListener() { //Date Picker
            @Override
            public void onClick(View v) {
                Log.i(TAG, "DatePicker Dialog");
                Calendar newCalendar = Calendar.getInstance();
                DatePickerDialog datePickerDialog = new DatePickerDialog(TripDataAdd.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        Date date = new Date();
                        date.setMonth(month);
                        date.setDate(dayOfMonth);

                        dateTV.setText((new SimpleDateFormat("dd-MMM", Locale.getDefault()).format(date)) +"-"+year);
                    }
                },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));
                datePickerDialog.show();
            }
        });

        addPersonImageView.setOnClickListener(new View.OnClickListener() { //AddPerson Click
            @Override
            public void onClick(View v) {
                Log.i(TAG, "Add Person Click");
                addNewPerson(); //Add New Person
            }
        });

        saveTripBTN.setOnClickListener(new View.OnClickListener() {//Save Trip Button
            @Override
            public void onClick(View v) {
                Log.i(TAG, "Save Trip Button Clicked");

                if (isValidTripData()){
                    TripModel tripModel =  new TripModel();
                    tripModel.setTripName(tripNameString);
                    tripModel.setDescription(tripDescriptionString);
                    tripModel.setDate(dateTV.getText().toString().trim());
                }
            }
        });
    }

    //TODO : AddPerson Dialog And RecyclerView
    void setRecyclerViewPerson(){ //RecyclerView Set
        Log.i(TAG, "Recycler View Set");
        recyclerViewPerson.setLayoutManager(layoutManager);
        recyclerViewPerson.setAdapter(adapter);
    }

    void addNewPerson() { //Add New Person
        Log.i(TAG, "Add Person");
        final Dialog addPersonDialog = new Dialog(this); //Add Person Dialog
        addPersonDialog.setContentView(R.layout.custom_dialog_add_person);
        addPersonDialog.show();
        bindDialogView(addPersonDialog); //Link Dialog View (Bind View)

        okTV.setOnClickListener(new View.OnClickListener() { //Ad new Person
            @Override
            public void onClick(View v) {
                Log.i(TAG, "Add Person Start ");
                if (isValidDialogData()) {
                    String personName = nameET.getText().toString().trim();
                    String depositeAmount = amountET.getText().toString().trim();

                    if (depositeAmount.isEmpty()) { //Deposite is 0
                        Log.i(TAG, "deposite Amount is 0");
                        depositeAmount = "0";
                    }

                    PersonModel personModel = new PersonModel(); //New Person Model redy
                    personModel.setName(personName);
                    personModel.setAmountDebit(Integer.valueOf(depositeAmount));
                    personModel.setAmountCredit(0);

                   savePersonData(personModel);

                   addPersonDialog.dismiss();
                }
            }
        });

        cancelTV.setOnClickListener(new View.OnClickListener() { //Cansel Dialog
            @Override
            public void onClick(View v) {
                Log.i(TAG,"Add Person Dialog Cancel");
                addPersonDialog.dismiss();
            }
        });
    }

    void bindDialogView(Dialog addPersonDialog) {
        Log.i(TAG, "Add Person Dialog Bind View");
        nameET = addPersonDialog.findViewById(R.id.nameET);
        amountET = addPersonDialog.findViewById(R.id.amountET);
        cancelTV = addPersonDialog.findViewById(R.id.cancelTV);
        okTV = addPersonDialog.findViewById(R.id.okTV);
    }

    boolean isValidDialogData(){ //Dialog  Data Validation
        Log.i(TAG, "Validation of Add Person Dialog Data");
        String personName = nameET.getText().toString().trim();

        if (personName.isEmpty()) {
            Log.w(TAG,"Person Name is empty");
            nameET.setError("Enter person name");
            nameET.requestFocus();
            return  false;
        }

        return  true;
    }

    void savePersonData(PersonModel personModel) { //Add Person to List of RecycelrView
        Log.i(TAG, "Add New person in List");
        personModelList.add(personModel);
        adapter.notifyDataSetChanged();
    }

    //TODO: Trip Data
    boolean isValidTripData(){  //Trip Data Validation
        tripNameString = nameTripET.getText().toString().trim();
        tripDescriptionString = descriptionET.getText().toString().trim();

        if (tripNameString.isEmpty()) {
            Log.w(TAG,"Trip Name is empty");
            nameTripET.setError("Enter trip name");
            nameTripET.requestFocus();
            return  false;
        }
        return true;
    }
}
