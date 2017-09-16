package com.islandcode.ckpd.chance.data;

/**
 * Created by charliederiggs on 30/08/2017.
 */

public class ListItem {
    private String dateAndTime;
    private String WinningNumbers;
    private String Title;
    private String FreeTicketLetter;
    private int colorResource;



    public ListItem(String dateAndTime, String winningNumbers, String title, String freeTicketLetter, int colorResource) {
        this.dateAndTime = dateAndTime;
        this.WinningNumbers = winningNumbers;
        this.FreeTicketLetter = freeTicketLetter;
        Title = title;
        this.colorResource = colorResource;
    }



    public String getDateAndTime() {
        return dateAndTime;
    }

    public void setDateAndTime(String dateAndTime) {
        this.dateAndTime = dateAndTime;
    }

    public String getWinningNumbers() {
        return WinningNumbers;
    }

    public void setWinningNumbers(String winningNumbers) {
        WinningNumbers = winningNumbers;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public int getColorResource() {
        return colorResource;
    }

    public void setColorResource(int colorResource) {
        this.colorResource = colorResource;
    }


    public String getFreeTicketLetter() {
        return FreeTicketLetter;
    }

    public void setFreeTicketLetter(String freeTicketLetter) {
        this.FreeTicketLetter = freeTicketLetter;
    }
}
