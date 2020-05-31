package fr.gostyle.gostyleApp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

public class PromoAdapter extends FirestoreRecyclerAdapter<Promotion, PromoAdapter.PromoHolder> {


    public PromoAdapter(@NonNull FirestoreRecyclerOptions<Promotion> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull PromoHolder holder, int position, @NonNull Promotion model) {
        holder.textview_code.setText(model.getCode());
        holder.textview_description.setText(model.getDescription());
        holder.textview_dateLimit.setText(String.valueOf(model.getDateLimit()));
        holder.textview_magasin.setText(model.getMagasin());

    }

    @NonNull
    @Override
    public PromoHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.promo_item,
                parent, false);
        return new PromoHolder(v);
    }

    class PromoHolder extends RecyclerView.ViewHolder{
        TextView textview_code;
        TextView textview_description;
        TextView textview_dateLimit;
        TextView textview_magasin;


        public PromoHolder(View itemView){
            super(itemView);
            textview_code = itemView.findViewById(R.id.textview_code);
            textview_description = itemView.findViewById(R.id.textview_description);
            textview_dateLimit = itemView.findViewById(R.id.textview_dateLimit);
            textview_magasin = itemView.findViewById(R.id.textview_magasin);
        }
    }
}
