package com.training.interns.mza.sqliteexample.tasks;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;

import com.training.interns.mza.sqliteexample.database.PersonSQLiteOpenHelper;
import com.training.interns.mza.sqliteexample.database.PersonTableHelper;

public class PersonSQLiteAsyncTask extends AsyncTask<Void, Void, Cursor> {

    public interface Callback {
        void callback(Cursor cursor);
    }

    private Callback callback;
    private PersonSQLiteOpenHelper personSQLiteOpenHelper;

    public PersonSQLiteAsyncTask(Callback callback, PersonSQLiteOpenHelper personSQLiteOpenHelper) {
        this.callback = callback;
        this.personSQLiteOpenHelper = personSQLiteOpenHelper;
    }

    @Override
    protected Cursor doInBackground(Void... params) {
        // Search Person List in the DB
        return getPersonListFromDB();
    }

    @Override
    protected void onPostExecute(Cursor result) {
        callback.callback(result);
    }

    private Cursor getPersonListFromDB() {
        SQLiteDatabase personDb = personSQLiteOpenHelper.getReadableDatabase();
        String query = "SELECT * from " + PersonTableHelper.TABLE_NAME + " " +
                " ORDER BY " + PersonTableHelper.COLUMN_LASTNAME + "," + PersonTableHelper.COLUMN_NAME;
        return personDb.rawQuery(query, null);
    }
}