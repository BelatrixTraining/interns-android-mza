package com.training.interns.mza.service;

import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.training.interns.mza.service.constants.ImageDownloaderConstants;
import com.training.interns.mza.service.services.ImageDownloaderService;

public class MainActivity extends AppCompatActivity {
    private static final String LOG_TAG = MainActivity.class.getName();

    private ProgressBar progressBar;

    private EditText downloadUrl;

    // Create the image receiver.
    private BroadcastReceiver imageReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String imagePath = intent.getStringExtra(ImageDownloaderConstants.IMAGE_PATH);
            ImageView image = findViewById(R.id.downloadImage);
            Log.i(LOG_TAG, "Image path: " + image);
            if (imagePath != null && !imagePath.isEmpty()) {
                Bitmap imageBitmap = BitmapFactory.decodeFile(imagePath);
                if (imageBitmap != null) {
                    image.setImageBitmap(imageBitmap);
                } else {
                    image.setImageResource(R.mipmap.ic_broken_image_black_48dp);
                }
            } else {
                image.setImageResource(R.mipmap.ic_broken_image_black_48dp);
            }
            hideLoadingIndicator();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        downloadUrl = findViewById(R.id.downloadUrl);
        progressBar = findViewById(R.id.progressbar);

        // Suscribe to download image custom event.
        subscribeToDownloadImageEvent();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        LocalBroadcastManager.getInstance(this).unregisterReceiver(imageReceiver);
    }

    public void downloadImageOnClickListener(View v) {
        String url = downloadUrl.getText().toString();
        if (url != null && !url.isEmpty()) {
            Intent imageDownloaderIntent = new Intent(MainActivity.this, ImageDownloaderService.class);
            imageDownloaderIntent.putExtra(ImageDownloaderConstants.IMAGE_URL, url);
            showLoadingIndicator();
            MainActivity.this.startService(imageDownloaderIntent);
        }
    }

    private void subscribeToDownloadImageEvent() {
        LocalBroadcastManager.getInstance(this).registerReceiver(imageReceiver, new IntentFilter(ImageDownloaderConstants.INTENT_FILTER_DOWNLOAD_IMAGE));
    }

    private void showLoadingIndicator() {
        progressBar.setVisibility(View.VISIBLE);
    }

    private void hideLoadingIndicator() {
        progressBar.setVisibility(View.GONE);
    }
}
