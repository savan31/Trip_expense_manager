package codes.tuton.tripexpensemanager.Adaptor;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import codes.tuton.tripexpensemanager.ActivityAddExpense;
import codes.tuton.tripexpensemanager.DataBaseHelper;
import codes.tuton.tripexpensemanager.MainActivity;
import codes.tuton.tripexpensemanager.Model.PersonModel;
import codes.tuton.tripexpensemanager.Model.TrasactionModel;
import codes.tuton.tripexpensemanager.R;

public class AdaptorViewPerson extends RecyclerView.Adapter<AdaptorViewPerson.PersonViewHolder> {

    List<PersonModel> personModelList;
    int totleAmount;
    Context context;

    public AdaptorViewPerson(List<PersonModel> personModelList, int totleAmount, Context context) {
        this.personModelList = personModelList;
        this.totleAmount = totleAmount;
        this.context = context;
    }

    public class PersonViewHolder extends RecyclerView.ViewHolder {

        TextView charcterImage, personName, debitRs, creditRs,fundRs;
        ImageView useMoney;

        public PersonViewHolder(@NonNull View itemView) {
            super(itemView);

            charcterImage = itemView.findViewById(R.id.characterTV);
            personName = itemView.findViewById(R.id.personName);
            debitRs = itemView.findViewById(R.id.debitRs);
            creditRs = itemView.findViewById(R.id.creditRs);
            fundRs = itemView.findViewById(R.id.fundRs);
            useMoney = itemView.findViewById(R.id.useMoney);

        }
    }

    @NonNull
    @Override
    public PersonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_trip_person_details, parent, false);
        PersonViewHolder personViewHolder = new PersonViewHolder(view);
        return personViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull PersonViewHolder holder, int position) {

        final PersonModel personModel = personModelList.get(position);

        holder.charcterImage.setText(String.valueOf(personModel.getName().toUpperCase().charAt(0)));
        if (personModel.getAdmin().matches("admin")) {
            holder.personName.setText(personModel.getName() + "(Admin)");
            holder.fundRs.setText("( +"+String.valueOf(totleAmount)+"₹)");
            holder.fundRs.setTextColor(Color.parseColor("#e5b84c"));
            holder.useMoney.setVisibility(View.VISIBLE);
        } else {

            holder.personName.setText(personModel.getName());

        }
        holder.creditRs.setText("("+String.valueOf(personModel.getAmountCredit())+"₹)");
        holder.creditRs.setTextColor(Color.parseColor("#007F00"));
        if (personModel.getAmountDebit() > personModel.getAmountCredit()){

            final Dialog dialog = new Dialog(context);
            dialog.setContentView(R.layout.custom_dialog_money_finish);
            dialog.show();

            TextView cancetTV,addTV;
            final EditText addMoneyET;

            cancetTV = dialog.findViewById(R.id.cancelTV);
            addTV = dialog.findViewById(R.id.addTV);
            addMoneyET = dialog.findViewById(R.id.addMoneyET);

            cancetTV.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                }
            });

            addTV.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String addMoney = addMoneyET.getText().toString();

                    if (addMoney.isEmpty()){
                        addMoneyET.setError("Please enter amount");
                    }else {
                        Intent intent = new Intent(context, MainActivity.class);
                        intent.putExtra("ADD_MONEY",addMoney);
                        context.startActivity(intent);

                        DataBaseHelper dataBaseHelper = new DataBaseHelper(context);
                        dataBaseHelper.updateCreditAmountPerson(personModel.getTripId(),personModel.getAmountCredit()+Integer.valueOf(addMoney));
                    }


                }
            });
        }else {
            holder.debitRs.setText(String.valueOf(personModel.getAmountDebit())+"₹");
            holder.debitRs.setTextColor(Color.parseColor("#FF0000"));
        }




        holder.useMoney.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ActivityAddExpense.class);
                intent.putExtra("PERSON_NAME",personModel.getName());
                context.startActivity(intent);
            }
        });
    }



    @Override
    public int getItemCount() {
        return personModelList.size();
    }

}
