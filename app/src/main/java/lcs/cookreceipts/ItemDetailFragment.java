package lcs.cookreceipts;

import android.app.Activity;
import android.support.design.widget.CollapsingToolbarLayout;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.exoplayer2.ui.SimpleExoPlayerView;

import java.util.List;

import lcs.cookreceipts.data.Ingredient;
import lcs.cookreceipts.data.ReceiptDatabase;
import lcs.cookreceipts.data.Step;
import lcs.cookreceipts.dummy.DummyContent;

import static lcs.cookreceipts.StepListActivity.RECIPE_ID;
import static lcs.cookreceipts.utils.Constants.ARG_STEP;

/**
 * A fragment representing a single Item detail screen.
 * This fragment is either contained in a {@link ItemListActivity}
 * in two-pane mode (on tablets) or a {@link ItemDetailActivity}
 * on handsets.
 */
public class ItemDetailFragment extends Fragment {
    /**
     * The fragment argument representing the item ID that this fragment
     * represents.
     */
    public static final String ARG_ITEM_ID = "item_id";

    /**
     * The dummy content this fragment is presenting.
     */
    private DummyContent.DummyItem mItem;
    private int contentType;
    private int recipeId;
    private String stepArg;
    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public ItemDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments().containsKey(ARG_ITEM_ID)) {

            contentType = getArguments().getInt(ARG_ITEM_ID);
            recipeId = getArguments().getInt(RECIPE_ID);
            stepArg = getArguments().getString(ARG_STEP);

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = null;
        //Ingredients
        if (contentType == 0) {
            rootView = inflater.inflate(R.layout.item_detail, container, false);
            TextView textView = (TextView) rootView.findViewById(R.id.item_detail);
            List<Ingredient> ingredientList = ReceiptDatabase.getInstance(getActivity()).ingredientDao()
                    .getIngredientfromReceipt(recipeId);
            for (Ingredient ingredient : ingredientList) {
                textView.append(ingredient.toString());
            }
        } else {
            rootView = inflater.inflate(R.layout.step_detail, container, false);
            Step step = ReceiptDatabase.getInstance(getActivity()).stepDao().getStep(recipeId, stepArg);
            TextView textView = (TextView) rootView.findViewById(R.id.tv_description);
            textView.setText(step.getDescription());
            SimpleExoPlayerView exoPlayerView = (SimpleExoPlayerView)rootView.findViewById(R.id.exo_play);

        }

        return rootView;
    }
}
