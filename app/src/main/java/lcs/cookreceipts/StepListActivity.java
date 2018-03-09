package lcs.cookreceipts;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import java.util.List;

import lcs.cookreceipts.adapter.RecipesAdapter;
import lcs.cookreceipts.adapter.StepsAdapter;
import lcs.cookreceipts.data.Receipt;
import lcs.cookreceipts.data.ReceiptDatabase;
import lcs.cookreceipts.data.Step;
import lcs.cookreceipts.utils.Constants;

public class StepListActivity extends AppCompatActivity {
    private boolean mTwoPane;
    private RecyclerView recyclerView;
    private int recipeId;
    public static final String RECIPE_ID = "recipe_id";
    private String title;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        recipeId = getIntent().getIntExtra(RECIPE_ID, 0);
        title = getIntent().getStringExtra(Constants.ARG_TITLE);
        setContentView(R.layout.activity_step_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //toolbar.setTitle(title);


        recyclerView = findViewById(R.id.item_list);
        assert recyclerView != null;
        setupRecyclerView();



    }

    private void setupRecyclerView() {
        List<Step> list = ReceiptDatabase.getInstance(this).stepDao().getStepsFromReceipt(recipeId);
        recyclerView.setAdapter(new StepsAdapter(this, list, mTwoPane));
    }
}
