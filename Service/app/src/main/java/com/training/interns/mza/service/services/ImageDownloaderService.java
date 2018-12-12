package com.training.interns.mza.service.services;

import android.app.IntentService;
import android.content.Intent;
import android.os.Environment;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import com.training.interns.mza.service.constants.ImageDownloaderConstants;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ImageDownloaderService extends IntentService {
    private static final String LOG_TAG = ImageDownloaderService.class.getName();

    public ImageDownloaderService() {

        super("ImageDownloaderService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        String imagePath = downloadAndSaveImage(intent.getStringExtra(ImageDownloaderConstants.IMAGE_URL));
        sendImage(imagePath);
    }

    private String downloadAndSaveImage (String url) {
        String imagePath = null;
        try {
            //Uses timestamp to make the file name unique.
            String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            String imageFileName = "image_" + timeStamp + "_";

            File storageDir = Environment.getExternalStoragePublicDirectory(
                    Environment.DIRECTORY_DOWNLOADS);
            File imageFile = File.createTempFile(
                    imageFileName,  /* prefix */
                    ".jpg",         /* suffix */
                    storageDir      /* directory */
            );

            InputStream imageInputStream = new URL(url).openStream();

            FileOutputStream imageOutputStream = new FileOutputStream(imageFile);

            int bytesRead = 0;
            //TODO: Determine lenght of buffer.
            byte[] buffer = new byte[8192];
            while((bytesRead = imageInputStream.read(buffer)) != -1) {
                imageOutputStream.write(buffer, 0, bytesRead);
            }

            imagePath = imageFile.getAbsolutePath();
        } catch (Exception e) {
            Log.d(LOG_TAG,"Error downloading image");
            e.printStackTrace();
        }

        return imagePath;
    }

    //Send an Intent using LocalBroadcastManager to return the downloaded image.
    private void sendImage(String imagePath) {
        Intent intent = new Intent(ImageDownloaderConstants.INTENT_FILTER_DOWNLOAD_IMAGE);
        intent.putExtra(ImageDownloaderConstants.IMAGE_PATH, imagePath);
        LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
    }
}
