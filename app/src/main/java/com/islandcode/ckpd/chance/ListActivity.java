package com.islandcode.ckpd.chance;

import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.islandcode.ckpd.chance.data.ActualDataSource;
import com.islandcode.ckpd.chance.data.ListItem;
import com.islandcode.ckpd.chance.data.fakedatasource;
import com.islandcode.ckpd.chance.logic.Controller;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

public class ListActivity extends AppCompatActivity implements ViewInterface, SwipeRefreshLayout.OnRefreshListener {

    private static final String TAG = " ";
    private Intent intent;

    private List<ListItem> listOfData;
    private LayoutInflater layoutInflater;
    private RecyclerView recyclerView;
    private CustomAdapter adapter;
    private SwipeRefreshLayout swipeLayout;
    private Controller controller;
    private AdView mAdView;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        MobileAds.initialize(getApplicationContext(), "ca-app-pub-1729247271373475~3143119734");





        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        recyclerView = (RecyclerView) findViewById(R.id.rec_list_activity);

        layoutInflater = getLayoutInflater();

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);

        controller = new Controller(this, getApplicationContext());

    }


    @Override
    public void setUpAdapterAndView(List<ListItem> listOfData) {
        this.listOfData = listOfData;

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);

        recyclerView.setLayoutManager(layoutManager);

        adapter = new CustomAdapter();
        recyclerView.setAdapter(adapter);



        mAdView = (AdView) findViewById(R.id.adView);

        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();

        mAdView.loadAd(adRequest);


//        swipeLayout = (SwipeRefreshLayout) findViewById(R.id.root_list_activity);
//        swipeLayout.setColorSchemeResources(R.color.colorComp, R.color.colorComp1, R.color.colorComp2, R.color.colorComp3, R.color.colorComp4);

//        swipeLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
//            @Override
//            public void onRefresh() {
//
//                Date currentTime = Calendar.getInstance().getTime();
//
//                Context context = getApplicationContext();
//                CharSequence text = "Updated "+ currentTime;
//                int duration = Toast.LENGTH_SHORT;
//
//                Toast toast = Toast.makeText(context, text, duration);
//                toast.show();
//
//                swipeLayout.setRefreshing(false);
//            }
//        });


    }

    @Override
    public void onRefresh() {
        Log.d(TAG, "onRefresh: ckeck");
    }


    private class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.CustomViewHolder> {
        @Override
        public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = layoutInflater.inflate(R.layout.item_data, parent, false);

            return new CustomViewHolder(v);
        }


        @Override
        public void onBindViewHolder(CustomViewHolder holder, int position) {
            ListItem currentItem = listOfData.get(position);

            holder.winningNumbers.setText(
                    currentItem.getWinningNumbers()
            );

            holder.box.setBackgroundResource(
                    currentItem.getColorResource()
            );

            holder.dateAndTime.setText(
                    currentItem.getDateAndTime()
            );
            holder.freeTicketNumber.setText(
                    currentItem.getFreeTicketLetter()
            );

            holder.title.setText(
                    currentItem.getTitle()
            );

        }

        @Override
        public int getItemCount() {
            //decide how many items to manage
            return listOfData.size();
        }

        class CustomViewHolder extends RecyclerView.ViewHolder {

            private View box;
            private TextView title;
            private TextView dateAndTime;
            private TextView winningNumbers;
            private TextView freeTicketNumber;
            private ViewGroup container;


            public CustomViewHolder(View itemView) {
                super(itemView);

                this.box = itemView.findViewById(R.id.root_list_item);
                this.dateAndTime = (TextView) itemView.findViewById(R.id.lbl_date_and_time);
                this.winningNumbers = (TextView) itemView.findViewById(R.id.lbl_message);
                this.title = (TextView) itemView.findViewById(R.id.lbl_title);
                this.freeTicketNumber = (TextView) itemView.findViewById(R.id.lbl_Free_Ticket_Number);

//                this.container.setOnClickListener(this);

            }

//            @Override
//            public void onClick(View v) {
//                ListItem listItem = listOfData.get(
//                        this.getAdapterPosition()
//                );
//                controller.onListItemClick(listItem);
//
//            }

        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_about:
                intent = new Intent(this, AboutActivity.class);
                startActivity(intent);
                return true;

            case R.id.action_refresh:
                intent = new Intent(this, ListActivity.class);
                startActivity(intent);

                Date currentTime = Calendar.getInstance().getTime();

                Context context = getApplicationContext();
                CharSequence text = "Updated "+ currentTime;
                int duration = Toast.LENGTH_SHORT;

                Toast toast = Toast.makeText(context, text, duration);
                toast.show();




                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }


    /** Called when leaving the activity */
    @Override
    public void onPause() {
        if (mAdView != null) {
            mAdView.pause();
        }
        super.onPause();
    }

    /** Called when returning to the activity */
    @Override
    public void onResume() {
        super.onResume();
        if (mAdView != null) {
            mAdView.resume();
        }
    }

    /** Called before the activity is destroyed */
    @Override
    public void onDestroy() {
        if (mAdView != null) {
            mAdView.destroy();
        }
        super.onDestroy();
    }
}


