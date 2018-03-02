package com.example.gebruiker.journal;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    EntryDatabase db;
    EntryAdapter adapter;

    /** Create method with own toolbar and custom adapter set to a listView. */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar myToolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        db = EntryDatabase.getInstance(getApplicationContext());
        ListView entryList = findViewById(R.id.entryList);
        Cursor data = db.selectAll();
        adapter = new EntryAdapter(this, data);
        entryList.setAdapter(adapter);
        entryList.setOnItemClickListener(new listViewItemClicked());
        entryList.setOnItemLongClickListener(new listViewItemLongClicked());
    }

    /** Save position in list in saveInstanceState */
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        ListView entryList = findViewById(R.id.entryList);
        int index = entryList.getFirstVisiblePosition();
        View v = entryList.getChildAt(0);
        int top = (v == null) ? 0 : (v.getTop() - entryList.getPaddingTop());
        outState.putInt("index", index);
        outState.putInt("top", top);
    }

    /** Set checkboxes out of restoreInstanceState */
    @Override
    public void onRestoreInstanceState( Bundle inState) {
        super.onRestoreInstanceState(inState);
        int index = inState.getInt("index");
        int top = inState.getInt("top");
        ListView entryList = findViewById(R.id.entryList);
        entryList.setSelectionFromTop(index, top);
    }

    /** Update the screen if you return to the activity. */
    @Override
    public void onResume(){
        super.onResume();
        updateData();
    }

    /** Get send to the create entry page. */
    public void createEntry(View view) {
        Intent intent = new Intent(this, InputActivity.class);
        startActivity(intent);
    }

    /** If an item in the listView is clicked the information of that item is put in an intent and
     *  the detail page gets opened. */
    private class listViewItemClicked implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            Cursor data = (Cursor) adapterView.getItemAtPosition(i);
            int rating = data.getInt(data.getColumnIndex("rating"));
            String title = data.getString(data.getColumnIndex("title"));
            String description = data.getString(data.getColumnIndex("description"));
            String date = data.getString(data.getColumnIndex("timestamp"));
            JournalEntry clickedEntry = new JournalEntry(title, description, rating);
            Intent intent = new Intent(MainActivity.this, DetailActivity.class);
            intent.putExtra("clicked_entry", clickedEntry);
            intent.putExtra("date", date);
            startActivity(intent);
        }
    }

    /** If an item gets clicked on long it gets deleted. The screen is then updated. */
    private class listViewItemLongClicked implements AdapterView.OnItemLongClickListener {
        @Override
        public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
            db.delete(l);
            updateData();
            return true;
        }
    }

    /** Updates the screen. */
    private void updateData() {
        adapter.swapCursor(db.selectAll());
    }

}
