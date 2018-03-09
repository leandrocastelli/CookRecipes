package lcs.cookreceipts.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import lcs.cookreceipts.R;
import lcs.cookreceipts.data.Ingredient;

/**
 * Created by Leandro on 3/3/2018.
 */

public class IngredientsAdapter extends RecyclerView.Adapter<IngredientsAdapter.IngredientsHolder>{

    private List<Ingredient> ingredientsList;
    private Context context;
    public IngredientsAdapter(Context context, List<Ingredient> ingredientsList) {
        this.context = context;
        this.ingredientsList = ingredientsList;
    }

    @Override
    public IngredientsHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View v = layoutInflater.inflate(R.layout.ingredient_list_content, parent, false);
        return new IngredientsHolder(v);
    }

    @Override
    public void onBindViewHolder(IngredientsHolder holder, int position) {
        holder.ingredientName.setText(ingredientsList.get(position).getIngredient());
        holder.measure.setText(ingredientsList.get(position).getMeasure());
        holder.quantity.setText(ingredientsList.get(position).getQuantity());
    }

    @Override
    public int getItemCount() {
        if (ingredientsList == null)
        return 0;
        return  ingredientsList.size();
    }

    class IngredientsHolder extends RecyclerView.ViewHolder {
        TextView quantity;
        TextView measure;
        TextView ingredientName;
        public IngredientsHolder(View itemView) {
            super(itemView);
            quantity = (TextView)itemView.findViewById(R.id.tv_ingredient_quantity);
            measure = (TextView)itemView.findViewById(R.id.tv_ingredient_measure);
            ingredientName = (TextView)itemView.findViewById(R.id.tv_ingredient_name);

        }
    }
}
