package com.training.interns.mza.whowroteitretrofit;

import android.os.AsyncTask;
import android.widget.TextView;

import com.training.interns.mza.whowroteitretrofit.retrofit.model.VolumeInfo;

import java.lang.ref.WeakReference;

public class FetchBookAsyncTask extends AsyncTask<String, Void, VolumeInfo> {
    private WeakReference<TextView> mTitleText;
    private WeakReference<TextView> mAuthorText;

    FetchBookAsyncTask(TextView titleText, TextView authorText) {
        this.mTitleText = new WeakReference<>(titleText);
        this.mAuthorText = new WeakReference<>(authorText);
    }

    @Override
    protected VolumeInfo doInBackground(String... strings) {
        return NetworkUtils.getBookInfo(strings[0]);
    }

    @Override
    protected void onPostExecute(VolumeInfo resultBook) {
        if (resultBook != null) {
            mTitleText.get().setText(resultBook.getTitle());
            mAuthorText.get().setText(resultBook.getAuthors().toString());
        } else {
            mTitleText.get().setText(R.string.no_results);
            mAuthorText.get().setText("");
        }
    }
}
