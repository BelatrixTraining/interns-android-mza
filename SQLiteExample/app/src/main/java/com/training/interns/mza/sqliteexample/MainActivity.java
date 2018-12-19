package com.training.interns.mza.sqliteexample;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.widget.ProgressBar;
import android.view.View;
import android.support.v7.widget.RecyclerView;

import com.training.interns.mza.sqliteexample.adapters.PersonRecyclerViewAdapter;
import com.training.interns.mza.sqliteexample.database.PersonSQLiteOpenHelper;
import com.training.interns.mza.sqliteexample.tasks.PersonSQLiteAsyncTask;

public class MainActivity extends AppCompatActivity implements PersonSQLiteAsyncTask.Callback {
    private ProgressBar progressBar;
    private RecyclerView personRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // RecyclerView
        personRecyclerView = findViewById(R.id.personRecyclerView);
        personRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Init DB Open Helper.
        PersonSQLiteOpenHelper personSQLiteOpenHelper = new PersonSQLiteOpenHelper(this);

        // Init Progress Bar
        progressBar = findViewById(R.id.progressBar);
        progressBar.setVisibility(View.VISIBLE);

        PersonSQLiteAsyncTask personSQLiteAsyncTask = new PersonSQLiteAsyncTask(this, personSQLiteOpenHelper);
        personSQLiteAsyncTask.execute();
    }

    @Override
    public void callback(Cursor cursor) {
        PersonRecyclerViewAdapter personRecyclerViewAdapter = new PersonRecyclerViewAdapter(this, cursor);
        personRecyclerView.setAdapter(personRecyclerViewAdapter);
        progressBar.setVisibility(View.INVISIBLE);
    }

    public void addNewPerson(View view) {
        Intent addPersonIntent = new Intent(this, AddNewPersonActivity.class);
        startActivity(addPersonIntent);
    }
}
