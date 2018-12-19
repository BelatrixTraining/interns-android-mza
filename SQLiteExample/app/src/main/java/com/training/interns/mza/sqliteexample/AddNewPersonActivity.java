package com.training.interns.mza.sqliteexample;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.training.interns.mza.sqliteexample.database.PersonSQLiteOpenHelper;
import com.training.interns.mza.sqliteexample.model.Person;
import com.training.interns.mza.sqliteexample.tasks.SavePersonAsyncTask;

public class AddNewPersonActivity extends AppCompatActivity implements SavePersonAsyncTask.Callback {
    private EditText nameEditText;
    private EditText lastNameEditText;
    private EditText phoneNumberEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_person);
        nameEditText = findViewById(R.id.nameEdiText);
        lastNameEditText = findViewById(R.id.lastNameEditText);
        phoneNumberEditText = findViewById(R.id.phoneNumberEditText);
    }

    public void savePerson(View view) {
        String name = nameEditText.getText().toString();
        String lastName = lastNameEditText.getText().toString();
        String phoneNumber = phoneNumberEditText.getText().toString();
        if (!name.isEmpty() && !lastName.isEmpty() && !phoneNumber.isEmpty()) {
            Person person = new Person(0L, name, lastName, phoneNumber);
            SavePersonAsyncTask savePersonAsyncTask = new SavePersonAsyncTask(this, new PersonSQLiteOpenHelper(this),  person);
            savePersonAsyncTask.execute();
        } else {
            Toast.makeText(this, "Ingrese los datos solicitados", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void callback() {
        Intent listIntent = new Intent(this, MainActivity.class);
        startActivity(listIntent);
    }
}
