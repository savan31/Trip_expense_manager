package codes.tuton.tripexpensemanager.Adaptor;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import codes.tuton.tripexpensemanager.Model.PersonModel;
import codes.tuton.tripexpensemanager.R;

public class AdaptorPersonDetails extends RecyclerView.Adapter<AdaptorPersonDetails.HomeViewHolder> {


    List<PersonModel> personModelList;
    Context context;

    public AdaptorPersonDetails(List<PersonModel> personModelList, Context context) {
        this.personModelList = personModelList;
        this.context = context;
    }

    public class HomeViewHolder extends RecyclerView.ViewHolder{

        TextView characterTV,personNameTV;
        ImageView menuIV;



        public HomeViewHolder(@NonNull View i) {
            super(i);

            characterTV = i.findViewById(R.id.characterTV);
            personNameTV = i.findViewById(R.id.personNameTV);
            menuIV = i.findViewById(R.id.menuIV);
        }
    }

    @NonNull
    @Override
    public HomeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_add_person,parent,false);
        HomeViewHolder homeViewHolder = new HomeViewHolder(view);
        return homeViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull HomeViewHolder holder, int position) {

        PersonModel personModel =personModelList.get(position);

        holder.characterTV.setText(String.valueOf(personModel.getName().charAt(0)));
        holder.personNameTV.setText(personModel.getName());



    }

    @Override
    public int getItemCount() {
        return personModelList.size();
    }


}
