package com.training.interns.mza.broadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ScreenReceiver extends BroadcastReceiver {
    private SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss.mmm");

    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "Hora de encendido: " + sdf.format(new Date()) ,Toast.LENGTH_LONG).show();
    }
}
