package codes.tuton.tripexpensemanager;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import codes.tuton.tripexpensemanager.Model.PersonModel;
import codes.tuton.tripexpensemanager.Model.TripModel;

public class DataBaseHelper extends SQLiteOpenHelper {

    public  static String DATABASE_NAME = "Trip";

    String TABLE_NAME_TRIP = "TRIP";
    String COL_PRIMARY_ID = "PRIMARY_ID";
    String COL_TRIP_NAME = "TRIP_NAME";
    String COL_DESCRIPTION = "DESCRIPTION";
    String COL_DATE = "DATE";
    String COLUMN_TIMESTAMP_TRIP = "TIMESTAMP_TRIP";


    String TABLE_NAME_PERSON = "PERSON";
    String COL_PERSON_ID = "PRIMARY_ID";
    String COL_NAME = "NAME";
    String COL_AMOUNT_DEBIT = "AMOUNT_DEBIT";
    String COL_AMOUNT_CREADIT = "AMOUNT_CREADIT";
    String COLUMN_TIMESTAMP_PERSON = "TIMESTAMP_PERSON";


    String CREATE_TABLE_TRIP =
            "CREATE TABLE " + TABLE_NAME_TRIP + "("
                    + COL_PRIMARY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + COL_DESCRIPTION + " TEXT,"
                    + COL_DESCRIPTION + " TEXT,"
                    + COL_DATE + " STRING,"
                    + COLUMN_TIMESTAMP_TRIP + " DATETIME DEFAULT CURRENT_TIMESTAMP"
                    + ")";

    String CREATE_TABLE_PERSON =
            "CREATE TABLE " + TABLE_NAME_PERSON + "("
                    + COL_PERSON_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + COL_NAME + " TEXT,"
                    + COL_AMOUNT_DEBIT + " INTEGER,"
                    + COL_AMOUNT_CREADIT + " INTEGER,"
                    + COLUMN_TIMESTAMP_PERSON + " DATETIME DEFAULT CURRENT_TIMESTAMP"
                    + ")";


    public DataBaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(CREATE_TABLE_TRIP);
        db.execSQL(CREATE_TABLE_PERSON);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_TRIP);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_PERSON);
        onCreate(db);

    }

    public long insertDataTrip(String tripname,String description, String date) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_TRIP_NAME,tripname);
        contentValues.put(COL_DESCRIPTION,description);
        contentValues.put(COL_DATE, date);
        long id = db.insert(TABLE_NAME_TRIP, null, contentValues);
        db.close();
        return id;
    }
    public long insertDataPerson(String name, int amountDebit,int amountCredit) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_NAME,name);
        contentValues.put(COL_AMOUNT_DEBIT,amountDebit);
        contentValues.put(COL_AMOUNT_CREADIT, amountCredit);
        long id = db.insert(TABLE_NAME_PERSON, null, contentValues);
        db.close();
        return id;
    }

    public void updateDataPerson(PersonModel personModel) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_AMOUNT_DEBIT, personModel.getAmountDebit());
        contentValues.put(COL_AMOUNT_CREADIT, personModel.getAmountCredit());
        db.update(TABLE_NAME_PERSON, contentValues, COL_PERSON_ID + " = ?", new String[]{String.valueOf(personModel.getPersonId())});
    }

    public Integer deleteDataTrip(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME_TRIP, "ID=?", new String[]{id});
    }

    public Integer deleteDataPerson(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME_PERSON, "ID=?", new String[]{id});
    }

    public TripModel getDataTrip(long id) {

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME_TRIP + " WHERE ID='" + id + "'", null);

        if (cursor != null) {
            cursor.moveToFirst();
        }

        TripModel tripModel = new TripModel();

        tripModel.setPrimaryId(cursor.getInt(cursor.getColumnIndex(COL_PRIMARY_ID)));
        tripModel.setDescription(cursor.getString(cursor.getColumnIndex(COL_DESCRIPTION)));
        tripModel.setTripName(cursor.getString(cursor.getColumnIndex(COL_TRIP_NAME)));
        tripModel.setDate(cursor.getString(cursor.getColumnIndex(COL_DATE)));
        tripModel.setTimeStampTrip(cursor.getString(cursor.getColumnIndex(COLUMN_TIMESTAMP_TRIP)));

        return tripModel;

    }

    public PersonModel getDataPerson(long id) {

        SQLiteDatabase db = this.getWritableDatabase();
        Log.e("TEST", String.valueOf(id));

        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME_PERSON + " WHERE ID='" + id + "'", null);

        if (cursor != null) {
            cursor.moveToFirst();
        }

        PersonModel personModel = new PersonModel();

        personModel.setPersonId(cursor.getInt(cursor.getColumnIndex(COLUMN_TIMESTAMP_PERSON)));
        personModel.setName(cursor.getString(cursor.getColumnIndex(COL_NAME)));
        personModel.setAmountDebit(cursor.getInt(cursor.getColumnIndex(COL_AMOUNT_DEBIT)));
        personModel.setAmountCredit(cursor.getInt(cursor.getColumnIndex(COL_AMOUNT_CREADIT)));
        personModel.setTimeStampTrip(cursor.getString(cursor.getColumnIndex(COLUMN_TIMESTAMP_TRIP)));

        return personModel;

    }

    public List<TripModel> getAllGoalTrip() {
        List<TripModel> tripModelList = new ArrayList<>();

        // Select All Query


        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT  * FROM " + TABLE_NAME_TRIP + " ORDER BY " + COLUMN_TIMESTAMP_TRIP + " DESC", null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                TripModel tripModel = new TripModel();
                tripModel.setPrimaryId(cursor.getInt(cursor.getColumnIndex(COL_PRIMARY_ID)));
                tripModel.setTripName(cursor.getString(cursor.getColumnIndex(COL_TRIP_NAME)));
                tripModel.setDescription(cursor.getString(cursor.getColumnIndex(COL_DESCRIPTION)));
                tripModel.setDate(cursor.getString(cursor.getColumnIndex(COL_DATE)));
                tripModel.setTimeStampTrip(cursor.getString(cursor.getColumnIndex(COLUMN_TIMESTAMP_TRIP)));

                tripModelList.add(tripModel);
            } while (cursor.moveToNext());
        }

        // close db connection
        db.close();

        // return notes list
        return tripModelList;
    }

    public List<PersonModel> getAllGoalPerson() {
        List<PersonModel> personModelList = new ArrayList<>();

        // Select All Query


        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT  * FROM " + TABLE_NAME_PERSON + " ORDER BY " + COLUMN_TIMESTAMP_PERSON + " DESC", null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                PersonModel personModel = new PersonModel();
                personModel.setPersonId(cursor.getInt(cursor.getColumnIndex(COLUMN_TIMESTAMP_PERSON)));
                personModel.setName(cursor.getString(cursor.getColumnIndex(COL_NAME)));
                personModel.setAmountDebit(cursor.getInt(cursor.getColumnIndex(COL_AMOUNT_DEBIT)));
                personModel.setAmountCredit(cursor.getInt(cursor.getColumnIndex(COL_AMOUNT_CREADIT)));
              //  personModel.setTimeStampTrip(cursor.getString(cursor.getColumnIndex(COLUMN_TIMESTAMP_TRIP)));

                personModelList.add(personModel);
            } while (cursor.moveToNext());
        }

        // close db connection
        db.close();

        // return notes list
        return personModelList;
    }

}
