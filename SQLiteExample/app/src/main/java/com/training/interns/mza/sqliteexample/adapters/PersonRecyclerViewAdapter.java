package com.training.interns.mza.sqliteexample.adapters;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;
import android.view.View;

import com.training.interns.mza.sqliteexample.R;
import com.training.interns.mza.sqliteexample.database.PersonTableHelper;
import com.training.interns.mza.sqliteexample.model.Person;

import java.util.ArrayList;
import java.util.List;

public class PersonRecyclerViewAdapter extends RecyclerView.Adapter<PersonRecyclerViewAdapter.PersonViewHolder> {
    private final LayoutInflater layoutInflater;
    private List<Person> personList = new ArrayList<>();

    class PersonViewHolder extends RecyclerView.ViewHolder {
        private final TextView personNameTextView;
        private final TextView phoneNumberTextView;

        public PersonViewHolder(View itemView) {
            super(itemView);
            personNameTextView = itemView.findViewById(R.id.personName);
            phoneNumberTextView = itemView.findViewById(R.id.phoneNumber);
        }
    }

    public PersonRecyclerViewAdapter(Context context, Cursor cursor) {
        layoutInflater = LayoutInflater.from(context);
        if (cursor.getCount() > 0) {
            personList = new ArrayList<>();
            while(!cursor.isLast()) {
                cursor.moveToNext();
                Long id = cursor.getLong(cursor.getColumnIndexOrThrow(PersonTableHelper.COLUMN_ID));
                String name = cursor.getString(cursor.getColumnIndexOrThrow(PersonTableHelper.COLUMN_NAME));
                String lastName = cursor.getString(cursor.getColumnIndexOrThrow(PersonTableHelper.COLUMN_LASTNAME));
                String phoneNumber = cursor.getString(cursor.getColumnIndexOrThrow(PersonTableHelper.COLUMN_PHONE_NUMBER));
                Person person = new Person(id, name, lastName, phoneNumber);
                personList.add(person);
            }
            cursor.close();
        }
    }

    @Override
    public PersonRecyclerViewAdapter.PersonViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // Inflate an item view.
        View itemView = layoutInflater.inflate(R.layout.person_item, parent, false);
        return new PersonViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(PersonRecyclerViewAdapter.PersonViewHolder holder,
                                 int position) {
        // Retrieve the data for that position.
        Person person = personList.get(position);
//         Add the data to the view holder.
        holder.personNameTextView.setText(person.getLastname() + ", " + person.getName());
        holder.phoneNumberTextView.setText(person.getPhoneNumber());
    }

    @Override
    public int getItemCount() {
        return personList.size();
    }
}
