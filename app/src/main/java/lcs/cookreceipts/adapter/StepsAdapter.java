package lcs.cookreceipts.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import lcs.cookreceipts.IngredientsActivity;
import lcs.cookreceipts.ItemDetailActivity;
import lcs.cookreceipts.ItemDetailFragment;
import lcs.cookreceipts.R;
import lcs.cookreceipts.StepActivity;
import lcs.cookreceipts.StepListActivity;
import lcs.cookreceipts.data.Receipt;
import lcs.cookreceipts.data.Step;
import lcs.cookreceipts.utils.Constants;

/**
 * Created by Leandro on 3/3/2018.
 */

public class StepsAdapter extends RecyclerView.Adapter<RecipesAdapter.RecipeHolder> {

    private final AppCompatActivity mParentActivity;
    private final List<Step> mValues;
    private final boolean mTwoPane;

    public StepsAdapter(AppCompatActivity mParentActivity, List<Step> mValues, boolean mTwoPane) {
        this.mParentActivity = mParentActivity;
        this.mValues = mValues;
        this.mTwoPane = mTwoPane;
    }
    @Override
    public RecipesAdapter.RecipeHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(mParentActivity);

        View v = inflater.inflate(R.layout.steps_list_content, parent, false);

        return new RecipesAdapter.RecipeHolder(v);
    }

    @Override
    public void onBindViewHolder(RecipesAdapter.RecipeHolder holder,final int position) {
        TextView txtView = holder.textView;
        ImageView imageView = holder.foodImg;
        //It will be always Ingredients on the first position
        if (position == 0) {
            txtView.setText(R.string.ingredients_string);
            imageView.setImageResource(R.drawable.food_default);
        } else {
            String imagePath = mValues.get(position -1).getThumbnailURL().isEmpty() ? null : mValues.get(position-1).getThumbnailURL();
            Picasso.with(mParentActivity).load(imagePath).placeholder(R.drawable.food_default)
                    .into(imageView);
            txtView.setText(mValues.get(position -1).getShortDescription());
        }
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mParentActivity.getResources().getBoolean(R.bool.isTablet)) {
                    Bundle arguments = new Bundle();
                    if (position == 0) {
                        arguments.putInt(ItemDetailFragment.ARG_ITEM_ID, 0);

                    } else {
                        arguments.putInt(ItemDetailFragment.ARG_ITEM_ID, 1);
                        arguments.putString(Constants.ARG_STEP, mValues.get(position-1).getStep());
                    }

                    arguments.putInt(StepListActivity.RECIPE_ID, mValues.get(0).getReceipt_id());
                    ItemDetailFragment fragment = new ItemDetailFragment();
                    fragment.setArguments(arguments);
                    mParentActivity.getSupportFragmentManager().beginTransaction()
                            .replace(R.id.step_holder, fragment)
                            .commit();
                } else {
                    Context context = view.getContext();
                    Intent intent;
                    if (position == 0) {
                        intent = new Intent(context, IngredientsActivity.class);
                        intent.putExtra(Constants.ARG_TITLE, mValues.get(0).getShortDescription());
                    } else {
                        intent = new Intent(context, StepActivity.class);
                        intent.putExtra(Constants.ARG_TITLE, mValues.get(position-1).getShortDescription());
                        intent.putExtra(Constants.ARG_STEP, mValues.get(position-1).getStep());
                    }
                    intent.putExtra(StepListActivity.RECIPE_ID, mValues.get(0).getReceipt_id());

                    context.startActivity(intent);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        if (mValues == null) {
            return 0;
        }
        return mValues.size() + 1; //Considering Ingredients
    }
}
