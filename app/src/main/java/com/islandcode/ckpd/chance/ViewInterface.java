package com.islandcode.ckpd.chance;

import com.islandcode.ckpd.chance.data.ListItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by charliederiggs on 30/08/2017.
 */

public interface ViewInterface {

    void startDetailActivity(String dateAndTime, String winningNumbers, String title, int colorResource);

    void setUpAdapterAndView(List<ListItem> listOfData);

}
