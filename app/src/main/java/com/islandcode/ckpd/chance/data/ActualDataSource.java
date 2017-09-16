package com.islandcode.ckpd.chance.data;

import android.app.Application;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.android.volley.Cache;
import com.android.volley.Network;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.islandcode.ckpd.chance.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by charliederiggs on 30/08/2017.
 */

public class ActualDataSource implements DataSourceInterface {
    Random random;


    private Context context;
    private static final String TAG = "API";

    public ActualDataSource(Context context) {
        this.context = context;
    }




    public ActualDataSource() {
        random = new Random();
    }


//    @Override
//    public List<ListItem> getListOfData(Context context) {
//        ArrayList<ListItem> listOfData = new ArrayList<>();
//        Random random = new Random();
//        //make 12 semi-random items
//        for (int i = 0; i < 12; i++) {
//
//            listOfData.add(
//                    createNewListItem()
//            );
//        }
//
//        return listOfData;
//    }

    @Override
    public List<ListItem> getListOfData(Context context) {
        return null;
    }


    public ListItem createNewListItem() {



        //creates a semi-random ListItem
        ListItem listItem = new ListItem(
                "as","as","ad","", R.color.colorAccent

        );

        return listItem;
    }
}


