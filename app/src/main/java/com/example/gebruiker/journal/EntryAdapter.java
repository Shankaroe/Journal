package com.example.gebruiker.journal;

import android.content.Context;
import android.database.Cursor;
import android.view.View;
import android.widget.ImageView;
import android.widget.ResourceCursorAdapter;
import android.widget.TextView;

import java.sql.Timestamp;

/**
 * Created by Shankara on 27-2-2018.
 */

public class EntryAdapter extends ResourceCursorAdapter {

    /** Constructor. */
    public EntryAdapter(Context context, Cursor cursor){
        super(context, R.layout.entry_row, cursor);
    }

    /** Gets the information from the database and displays it as an item in the list. */
    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView name = view.findViewById(R.id.textName);
        TextView date = view.findViewById(R.id.textDate);
        ImageView mood = view.findViewById(R.id.imageRating);
        int rating = cursor.getInt(cursor.getColumnIndex("rating"));
        name.setText(cursor.getString(cursor.getColumnIndex("title")) );
        date.setText(cursor.getString(cursor.getColumnIndex("timestamp")));
        int imageID = R.drawable.ic_sentiment_neutral_black_24dp;
        switch (rating) {
            case 1:
                imageID = R.drawable.ic_sentiment_very_dissatisfied_black_24dp;
                break;
            case 2:
                imageID = R.drawable.ic_sentiment_dissatisfied_black_24dp;
                break;
            case 3:
                imageID = R.drawable.ic_sentiment_neutral_black_24dp;
                break;
            case 4:
                imageID = R.drawable.ic_sentiment_satisfied_black_24dp;
                break;
            case 5:
                imageID = R.drawable.ic_sentiment_very_satisfied_black_24dp;
                break;
        }
        mood.setImageResource(imageID);
    }

}
