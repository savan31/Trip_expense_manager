package codes.tuton.tripexpensemanager.Adaptor;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import codes.tuton.tripexpensemanager.Model.TrasactionModel;
import codes.tuton.tripexpensemanager.R;

public class AdaptorHistory extends RecyclerView.Adapter<AdaptorHistory.HistoryViewHolder> {

    List<TrasactionModel> trasactionModelList;
    Context context;

    public AdaptorHistory(List<TrasactionModel> trasactionModelList, Context context) {
        this.trasactionModelList = trasactionModelList;
        this.context = context;
    }

    public class HistoryViewHolder extends RecyclerView.ViewHolder{

        TextView description,payAmount,date,category;

        public HistoryViewHolder(@NonNull View itemView) {
            super(itemView);

            description = itemView.findViewById(R.id.description);
            payAmount = itemView.findViewById(R.id.payAmount);
            date = itemView.findViewById(R.id.date);
            category = itemView.findViewById(R.id.category);
        }
    }
    @NonNull
    @Override
    public HistoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_history_payment,parent,false);
        HistoryViewHolder historyViewHolder = new HistoryViewHolder(view);
        return historyViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryViewHolder holder, int position) {

        TrasactionModel trasactionModel = trasactionModelList.get(position);

        holder.description.setText(trasactionModel.getDescription());
        holder.payAmount.setText(String.valueOf(trasactionModel.getPayAmount()));
        holder.date.setText(trasactionModel.getDate());
        holder.category.setText(trasactionModel.getCategory());

    }

    @Override
    public int getItemCount() {
        return trasactionModelList.size();
    }


}
