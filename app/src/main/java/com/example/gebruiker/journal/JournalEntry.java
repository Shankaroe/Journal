package com.example.gebruiker.journal;

import java.io.Serializable;

/**
 * Created by Shankara on 27-2-2018.
 */

public class JournalEntry implements Serializable{
    private String title;
    private String description;
    private int rating;

    public JournalEntry(String newTitle, String newDescription, int newRating) {
        title = newTitle;
        description = newDescription;
        rating = newRating;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getRating() {
        return rating;
    }

}
