package codes.tuton.tripexpensemanager.Adaptor;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import codes.tuton.tripexpensemanager.Model.PersonModel;
import codes.tuton.tripexpensemanager.R;

public class AdaptorViewPerson extends RecyclerView.Adapter<AdaptorViewPerson.PersonViewHolder> {

    List<PersonModel> personModelList;
    Context context;

    public AdaptorViewPerson(List<PersonModel> personModelList, Context context) {
        this.personModelList = personModelList;
        this.context = context;
    }

    public class PersonViewHolder extends RecyclerView.ViewHolder{

        TextView charcterImage,personName,debitRs,creditRs;
        ImageView useMoney;

        public PersonViewHolder(@NonNull View itemView) {
            super(itemView);

            charcterImage = itemView.findViewById(R.id.characterTV);
            personName = itemView.findViewById(R.id.personName);
            debitRs = itemView.findViewById(R.id.debitRs);
            creditRs = itemView.findViewById(R.id.creditRs);
            useMoney = itemView.findViewById(R.id.useMoney);

        }
    }

    @NonNull
    @Override
    public PersonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_trip_person_details,parent,false);
        PersonViewHolder personViewHolder = new PersonViewHolder(view);
        return personViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull PersonViewHolder holder, int position) {

        final PersonModel personModel = personModelList.get(position);
        holder.charcterImage.setText(String.valueOf(personModel.getName().toUpperCase().charAt(0)));
        holder.personName.setText(personModel.getName());
        holder.debitRs.setText(String.valueOf(personModel.getAmountDebit()));
        holder.creditRs.setText(String.valueOf(personModel.getAmountCredit()));


    }

    @Override
    public int getItemCount() {
        return personModelList.size();
    }


}
