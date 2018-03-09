package lcs.cookreceipts.data;

import android.app.LoaderManager;
import android.content.AsyncTaskLoader;
import android.content.Context;
import android.content.Loader;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import lcs.cookreceipts.net.IDataReady;

/**
 * Created by Leandro on 2/25/2018.
 */

public class DataLoader extends AsyncTask<JSONArray, Void, Void> {

    Context context;
    IDataReady adapter;
    public DataLoader(Context context, IDataReady adapter) {
        this.adapter = adapter;
        this.context = context;
    }



    @Override
    protected Void doInBackground(JSONArray... jsonArrays) {
        for (int i = 0; i < jsonArrays[0].length(); i++) {
            try {
                JSONObject temp = (JSONObject) jsonArrays[0].get(i);
                String name = temp.getString("name");
                int id = temp.getInt("id");
                String servings = temp.getString("servings");
                String image = temp.getString("image");
                Receipt receipt = new Receipt(id, name, servings,image);
                ReceiptDatabase.getInstance(context).receiptDao().addReceipt(receipt);

                //get Ingredients list
                JSONArray ingredientsList = temp.getJSONArray("ingredients");
                for (int j = 0; j < ingredientsList.length(); j++) {
                    JSONObject ingredientHolder = (JSONObject)ingredientsList.get(j);
                    String quantity = ingredientHolder.getString("quantity");
                    String measure= ingredientHolder.getString("measure");
                    String ingredientName = ingredientHolder.getString("ingredient");
                    Ingredient ingredient = new Ingredient(id, quantity, measure, ingredientName);
                    ReceiptDatabase.getInstance(context).ingredientDao().insertIngredient(ingredient);

                }

                //get Steps
                JSONArray stepsList = temp.getJSONArray("steps");
                for (int k = 0; k < stepsList.length(); k++) {
                    JSONObject stepHolder = (JSONObject)stepsList.get(k);
                    String step = stepHolder.getString("id");
                    String shortDescription = stepHolder.getString("shortDescription");
                    String description = stepHolder.getString("description");
                    String videoURL = stepHolder.getString("videoURL");
                    String thumbnailURL = stepHolder.getString("thumbnailURL");
                    Step stepInstance = new Step(id, step, shortDescription, description, videoURL,thumbnailURL);
                    ReceiptDatabase.getInstance(context).stepDao().addStep(stepInstance);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            Log.i("Test","List parsed");
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        adapter.swapDataSet();
    }
}
