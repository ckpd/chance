package com.islandcode.ckpd.chance.data;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;

import java.util.List;

/**
 * Created by charliederiggs on 30/08/2017.
 * this is a contract between classes that dictate how they can talk to each other without given
 * implementation details
 */

public interface DataSourceInterface {
    List<ListItem> getListOfData(Context context);
    ListItem createNewListItem();


}
