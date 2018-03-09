package lcs.cookreceipts.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.List;

import lcs.cookreceipts.ItemDetailActivity;
import lcs.cookreceipts.ItemDetailFragment;
import lcs.cookreceipts.ItemListActivity;
import lcs.cookreceipts.R;
import lcs.cookreceipts.StepListActivity;
import lcs.cookreceipts.data.Receipt;
import lcs.cookreceipts.data.ReceiptDatabase;
import lcs.cookreceipts.data.Step;
import lcs.cookreceipts.dummy.DummyContent;
import lcs.cookreceipts.net.IDataReady;
import lcs.cookreceipts.utils.Constants;

/**
 * Created by Leandro on 3/1/2018.
 */

public class RecipesAdapter extends RecyclerView.Adapter<RecipesAdapter.RecipeHolder>
        implements IDataReady {

    private final AppCompatActivity mParentActivity;
    private List<Receipt> mValues;

    public RecipesAdapter(AppCompatActivity mParentActivity, List<Receipt> mValues) {
        this.mParentActivity = mParentActivity;
        this.mValues = mValues;
    }

    @Override
    public RecipeHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(mParentActivity);

        View v = inflater.inflate(R.layout.item_list_content, parent, false);

        return new RecipeHolder(v);
    }

    @Override
    public void onBindViewHolder(RecipeHolder holder, final int position) {
        TextView txtView = holder.textView;
        ImageView imageView = holder.foodImg;

        String imagePath = mValues.get(position).getImage().isEmpty()?null: mValues.get(position).getImage();
        Picasso.with(mParentActivity).load(imagePath).placeholder(R.drawable.food_default)
                .into(imageView);
        txtView.setText(mValues.get(position).getName());
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = view.getContext();
                Intent intent = new Intent(context, StepListActivity.class);
                intent.putExtra(StepListActivity.RECIPE_ID, mValues.get(position).getId());
                intent.putExtra(Constants.ARG_TITLE, mValues.get(position).getName());
                context.startActivity(intent);

            }
        });

    }

    @Override

    public int getItemCount() {
        if (mValues == null)
            return 0;
        return mValues.size();
    }

    @Override
    public void swapDataSet() {
        //reload database
        List<Receipt> list = ReceiptDatabase.getInstance(mParentActivity).receiptDao().getAllReceipts();
        if (!list.equals(mValues)) {
            mValues = list;
            notifyDataSetChanged();
        }

    }

    static class RecipeHolder extends RecyclerView.ViewHolder {
        ImageView foodImg;
        CardView cardView;
        TextView textView;
        public RecipeHolder(View itemView) {
            super(itemView);
            foodImg = (ImageView)itemView.findViewById(R.id.iv_food_img);
            cardView = (CardView)itemView.findViewById(R.id.cv_food_holder);
            textView = (TextView)itemView.findViewById(R.id.txt_recipe_title);

        }
    }
}
