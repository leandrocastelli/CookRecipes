package lcs.cookreceipts;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import java.util.List;

import lcs.cookreceipts.adapter.IngredientsAdapter;
import lcs.cookreceipts.adapter.StepsAdapter;
import lcs.cookreceipts.data.Ingredient;
import lcs.cookreceipts.data.ReceiptDatabase;
import lcs.cookreceipts.data.Step;

import static lcs.cookreceipts.StepListActivity.RECIPE_ID;
import static lcs.cookreceipts.utils.Constants.ARG_TITLE;

public class IngredientsActivity extends AppCompatActivity {

    private int recipeId;
    private RecyclerView recyclerView;
    private String title;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        recipeId = getIntent().getIntExtra(RECIPE_ID, 0);
        title = getIntent().getStringExtra(ARG_TITLE);
        setContentView(R.layout.activity_ingredients);
        Toolbar toolbar = (Toolbar) findViewById(R.id.detail_toolbar);
        setSupportActionBar(toolbar);
        //toolbar.setTitle(title);
        recyclerView = findViewById(R.id.item_list);
        assert recyclerView != null;
        setupRecyclerView();

    }

    private void setupRecyclerView() {
        List<Ingredient> list = ReceiptDatabase.getInstance(this).ingredientDao().getIngredientfromReceipt(recipeId);
        recyclerView.setAdapter(new IngredientsAdapter(this, list));
    }

}
