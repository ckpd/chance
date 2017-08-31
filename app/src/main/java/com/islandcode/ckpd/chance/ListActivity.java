package com.islandcode.ckpd.chance;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.islandcode.ckpd.chance.data.ActualDataSource;
import com.islandcode.ckpd.chance.data.ListItem;
import com.islandcode.ckpd.chance.data.fakedatasource;
import com.islandcode.ckpd.chance.logic.Controller;

import java.util.List;

public class ListActivity extends AppCompatActivity implements ViewInterface {


    private static final String EXTRA_DATE_AND_TIME = "EXTRA_DATE_AND_TIME";
    private static final String EXTRA_WINNINGNUMBERS = "EXTRA_WINNINGNUMBERS";
    private static final String EXTRA_TITLE = "EXTRA_TITLE";
    private static final String EXTRA_COLOR = "EXTRA_COLOR";

    private List<ListItem> listOfData;
    private LayoutInflater layoutInflater;
    private RecyclerView recyclerView;
    private CustomAdapter adapter;

    private Controller controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        recyclerView = (RecyclerView) findViewById(R.id.rec_list_activity);

        layoutInflater = getLayoutInflater();

      //  controller = new Controller(this, new fakedatasource());
        controller = new Controller(this, new ActualDataSource(), getApplicationContext());


    }

    @Override
    public void startDetailActivity(String dateAndTime, String winningNumbers, String title, int colorResource) {
        Intent i = new Intent(this, DetailActivity.class);
        i.putExtra(EXTRA_DATE_AND_TIME, dateAndTime);
        i.putExtra(EXTRA_DATE_AND_TIME, winningNumbers);
        i.putExtra(EXTRA_DATE_AND_TIME, colorResource);

        startActivity(i);
    }

    @Override
    public void setUpAdapterAndView(List<ListItem> listOfData) {
        this.listOfData = listOfData;

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);

        recyclerView.setLayoutManager(layoutManager);

        adapter = new CustomAdapter();
        recyclerView.setAdapter(adapter);

    }

    private class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.CustomViewHolder>{
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

            holder.winningNumbers.setText(
                    currentItem.getWinningNumbers()
            );


            holder.dateAndTime.setText(
                    currentItem.getDateAndTime()
            );

        }

        @Override
        public int getItemCount() {
            //decide how many items to manage
            return listOfData.size();
        }

        class CustomViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

            private View box;
            private TextView dateAndTime;
            private TextView winningNumbers;
            private ViewGroup container;


            public CustomViewHolder(View itemView) {
                super(itemView);

                this.box = itemView.findViewById(R.id.image_list_item);
                this.dateAndTime = (TextView) itemView.findViewById(R.id.lbl_date_and_time);
                this.winningNumbers = (TextView) itemView.findViewById(R.id.lbl_message);
                this.container = (ViewGroup) itemView.findViewById(R.id.root_list_item);

                this.container.setOnClickListener(this);

            }

            @Override
            public void onClick(View v) {
                ListItem listItem = listOfData.get(
                        this.getAdapterPosition()
                );
                controller.onListItemClick(listItem);

            }
        }
    }
}
