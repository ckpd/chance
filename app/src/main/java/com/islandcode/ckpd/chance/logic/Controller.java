package com.islandcode.ckpd.chance.logic;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.View;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.islandcode.ckpd.chance.R;
import com.islandcode.ckpd.chance.data.DataSourceInterface;
import com.islandcode.ckpd.chance.data.ListItem;
import com.islandcode.ckpd.chance.ViewInterface;
import com.islandcode.ckpd.chance.data.MySingleton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by charliederiggs on 30/08/2017.
 */

public class Controller {

    private Context context;
    private ViewInterface view;
    public int j = 0;
    private SwipeRefreshLayout sp;

    private final int[] colors = {
            R.color.colorComp,
            R.color.colorComp1,
            R.color.colorComp2,
            R.color.colorComp3,
            R.color.colorComp4
    };





    public Controller(ViewInterface view, Context context) {
        this.view = view;
        this.context = context;

        getListFromDataSource();
    }

    public void getListFromDataSource() {

//        String url = "https://enigmatic-island-75306.herokuapp.com/";
        String url = "http://18.220.235.100:8080/";

        JsonArrayRequest jsonRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                ArrayList<ListItem> listOfData = new ArrayList<>();
                for (int i = 0; i < response.length(); i++) {
                    try{
                        JSONObject item = response.getJSONObject(i);
                        JSONObject metadata = item.getJSONObject("metadata");
                        String mtype = metadata.getString("DrawId");
                        String firstDraw = "";
                        String btype = "";
                        if (mtype.equals("Daily Pick 3 results") || mtype.equals("Daily Cash 4 results") || mtype.equals("Play way results")) {
                            firstDraw = "Midday Draw : "+ metadata.getString("winningNumbers");
                            btype = "Evening Draw : "+ metadata.getString("FTL").replaceAll(",","  ");

                        }else{
                            btype = "Free Ticket # : "+ metadata.getString("FTL").replaceAll(",","  ");
                            firstDraw = metadata.getString("winningNumbers");
                        }

                        ListItem snap = new ListItem(metadata.getString("Date"),firstDraw.replaceAll(",","  "),metadata.getString("DrawId").toUpperCase(),btype, colors[j]);
                        j++;

                        System.out.println(btype);
                        listOfData.add(snap);

                        view.setUpAdapterAndView(listOfData);

                    }catch (Exception e){
                        Log.e("Timeline get error","Help me!!!");
                        e.printStackTrace();

                    }
                }

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
//        view.startDetailActivity(
//                selectedItem.getDateAndTime(),
//                selectedItem.getWinningNumbers(),
//                selectedItem.getTitle(),
//                selectedItem.getFreeTicketLetter(),
//                selectedItem.getColorResource()
//        );
    }

}
