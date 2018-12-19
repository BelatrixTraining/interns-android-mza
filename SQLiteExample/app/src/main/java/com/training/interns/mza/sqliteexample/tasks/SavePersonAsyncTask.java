package com.training.interns.mza.sqliteexample.tasks;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;

import com.training.interns.mza.sqliteexample.database.PersonSQLiteOpenHelper;
import com.training.interns.mza.sqliteexample.database.PersonTableHelper;
import com.training.interns.mza.sqliteexample.model.Person;

public class SavePersonAsyncTask extends AsyncTask<Void, Void, Void> {

    public interface Callback {
        void callback();
    }

    private Callback callback;
    private Person person;
    private PersonSQLiteOpenHelper personSQLiteOpenHelper;

    public SavePersonAsyncTask(Callback callback, PersonSQLiteOpenHelper personSQLiteOpenHelper, Person person) {
        this.callback = callback;
        this.person = person;
        this.personSQLiteOpenHelper = personSQLiteOpenHelper;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        savePersonInDB(person);
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        callback.callback();
    }

    private void savePersonInDB(Person person) {
        SQLiteDatabase db = personSQLiteOpenHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(PersonTableHelper.COLUMN_NAME, person.getName());
        values.put(PersonTableHelper.COLUMN_LASTNAME, person.getLastname());
        values.put(PersonTableHelper.COLUMN_PHONE_NUMBER, person.getPhoneNumber());
        db.insert(PersonTableHelper.TABLE_NAME, null, values);
    }
}