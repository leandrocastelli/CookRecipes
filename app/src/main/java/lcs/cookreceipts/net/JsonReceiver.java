package lcs.cookreceipts.net;

import android.app.Activity;
import android.content.AsyncTaskLoader;
import android.content.Context;
import android.support.v4.app.LoaderManager;
import android.util.Log;

import com.android.volley.Response;
import com.android.volley.VolleyError;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import lcs.cookreceipts.ItemDetailActivity;
import lcs.cookreceipts.data.DataLoader;
import lcs.cookreceipts.data.Ingredient;
import lcs.cookreceipts.data.Receipt;
import lcs.cookreceipts.data.ReceiptDatabase;
import lcs.cookreceipts.data.Step;

/**
 * Created by Leandro on 2/24/2018.
 */

public class JsonReceiver implements Response.Listener<JSONArray>, Response.ErrorListener {
    private final Context context;
    private final IDataReady adapter;
    public JsonReceiver(Context context, IDataReady adapter) {
        this.adapter = adapter;
        this.context = context;
    }
    @Override
    public void onResponse(JSONArray  response) {

        Log.i("Test","Received");
       DataLoader dataLoader = new DataLoader(context, adapter);
       dataLoader.execute(response);

    }

    @Override
    public void onErrorResponse(VolleyError error) {

    }
}
