package com.islandcode.ckpd.chance.data;

import android.content.Context;

import com.islandcode.ckpd.chance.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * test double used for fake data
 * Created by charliederiggs on 30/08/2017.
 */

public class fakedatasource implements DataSourceInterface {

    private static final int SIZE_OF_COLLECTION = 12;
    private Random random;

    private final String[] datesAndTimes = {
            "6:30AM 06/01/2017",
            "9:26PM 04/22/2013",
            "2:01PM 12/02/2015",
            "2:43AM 09/7/2018",
    };

    private final int[] colors = {
            R.color.colorAccent,
            R.color.colorPrimaryDark,
            R.color.colorAccent,
            R.color.colorPrimary
    };

    private final String[] title = {
            "Super Six",
            "Pick three",
            "Play way",
            "buss hed"
    };

    private final String[] winningNumbers = {
            "Winning numbers: 2 3 49 12 39",
            "Winning numbers: 2 3 49 12 39",
            "Winning numbers: 2 3 49 12 39",
            "Winning numbers: 21 32 45 13 3"
    };



    public fakedatasource() {
        random = new Random();
    }



    @Override
    public List<ListItem> getListOfData(Context context) {
        ArrayList<ListItem> listOfData = new ArrayList<>();
        Random random = new Random();
        //make 12 semi-random items
        for (int i = 0; i < 12; i++) {

            listOfData.add(
                    createNewListItem()
            );
        }

        return listOfData;
    }


    public ListItem createNewListItem() {

        //these will be 0, 1, 2, or 3
        int randOne = random.nextInt(4);
        int randTwo = random.nextInt(4);
        int randThree = random.nextInt(4);
        int randFour = random.nextInt(4);

        //creates a semi-random ListItem
        ListItem listItem = new ListItem(
                datesAndTimes[randOne],
                winningNumbers[randTwo],
                title[randThree],
                title[randThree],
                colors[randFour]
        );

        return listItem;
    }


}
