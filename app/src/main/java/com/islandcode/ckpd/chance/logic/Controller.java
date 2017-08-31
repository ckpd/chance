package com.islandcode.ckpd.chance.logic;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.islandcode.ckpd.chance.R;
import com.islandcode.ckpd.chance.data.DataSourceInterface;
import com.islandcode.ckpd.chance.data.ListItem;
import com.islandcode.ckpd.chance.ViewInterface;
import com.islandcode.ckpd.chance.data.MySingleton;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by charliederiggs on 30/08/2017.
 */

public class Controller {

    private Context context;
    private ViewInterface view;
    private DataSourceInterface datasource;


    public Controller(ViewInterface view, DataSourceInterface dataSource, Context context) {
        this.view = view;
        this.datasource = dataSource;
        this.context = context;

        getListFromDataSource();
    }

    public void getListFromDataSource() {

        String url = "http://httpbin.org/get?site=code&network=tutsplus";

        JsonObjectRequest jsonRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        // the response is already constructed as a JSONObject!
                        ArrayList<ListItem> listOfData = new ArrayList<>();

                        //make 12 semi-random items
                        for (int i = 0; i < 12; i++) {

                            ListItem snap = new ListItem("d","d","d", R.color.colorPrimary);
                            listOfData.add(snap);
                        }


                        view.setUpAdapterAndView(listOfData);

                    }


                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                    }
                });
        MySingleton.getInstance(context).addToRequestque(jsonRequest);
    }


    public void onListItemClick(ListItem selectedItem){
        view.startDetailActivity(
                selectedItem.getDateAndTime(),
                selectedItem.getWinningNumbers(),
                selectedItem.getTitle(),
                selectedItem.getColorResource()
        );
    }

}
