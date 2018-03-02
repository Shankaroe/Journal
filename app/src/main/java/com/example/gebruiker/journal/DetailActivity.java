package com.example.gebruiker.journal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.method.ScrollingMovementMethod;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Toolbar myToolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        Intent intent = getIntent();
        JournalEntry retrievedEntry = (JournalEntry) intent.getSerializableExtra("clicked_entry");
        String date = intent.getStringExtra("date");
        updateScreen(retrievedEntry, date);
        TextView description = findViewById(R.id.descriptionView);
        description.setMovementMethod(new ScrollingMovementMethod());
    }

    private void updateScreen(JournalEntry retrievedEntry, String dateString) {
        TextView title = findViewById(R.id.titleView);
        TextView date = findViewById(R.id.dateView);
        TextView description = findViewById(R.id.descriptionView);

        title.setText(retrievedEntry.getTitle());
        date.setText(dateString);
        description.setText(retrievedEntry.getDescription());
        imageResource(retrievedEntry.getRating());
    }

    private void imageResource(int rating) {
        ImageView mood = findViewById(R.id.moodView);
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
