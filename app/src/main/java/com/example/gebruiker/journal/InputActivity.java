package com.example.gebruiker.journal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.RatingBar;

import org.w3c.dom.Text;

public class InputActivity extends AppCompatActivity {

    /** Create method with own toolbar. */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);
        Toolbar myToolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
    }

    /** If the 'add' button gets clicked a Journal Entry object is created and put in the database.
     *  Afterwards all the activities get closed and you are returned to the home screen. */
    public void addEntry(View view) {
        EntryDatabase db = EntryDatabase.getInstance(getApplicationContext());
        EditText titleText = findViewById(R.id.editTitle);
        String title = titleText.getText().toString();
        EditText descriptionText = findViewById(R.id.editDescription);
        String description = descriptionText.getText().toString();
        RatingBar ratingBar = findViewById(R.id.ratingBar);
        int mood = Math.round(ratingBar.getRating());
        JournalEntry je = new JournalEntry(title, description, mood);
        db.insert(je);
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
    }
}
