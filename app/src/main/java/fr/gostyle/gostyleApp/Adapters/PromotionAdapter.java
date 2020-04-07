package fr.gostyle.gostyleApp.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.squareup.picasso.Picasso;

import fr.gostyle.gostyleApp.R;
import fr.gostyle.gostyleApp.models.Promotion;

public class PromotionAdapter extends FirestoreRecyclerAdapter<Promotion, PromotionAdapter.PromotionHolder> {
    /**
     * Create a new RecyclerView adapter that listens to a Firestore Query.  See {@link
     * FirestoreRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public PromotionAdapter(@NonNull FirestoreRecyclerOptions<Promotion> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull PromotionHolder holder, int position, @NonNull Promotion model) {
        holder.textDescription.setText(model.getDescription());
        Picasso.get().load(model.getImgUrl()).into(holder.imageUrl);
    }

    @NonNull
    @Override
    public PromotionHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.promotion_item, parent, false);
        return new PromotionHolder(view);
    }

    class PromotionHolder extends RecyclerView.ViewHolder {
        ImageView imageUrl;
        TextView textDescription;

        public PromotionHolder(@NonNull View itemView) {
            super(itemView);
            textDescription = itemView.findViewById(R.id.promotion_item_description);
            imageUrl = itemView.findViewById(R.id.promotion_item_image);
        }
    }

}
