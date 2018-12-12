package com.training.interns.mza.broadcastreceiver;

import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    private ScreenReceiver screenReceiver = new ScreenReceiver();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Screen Receiver va a detectar cada vez que la pantalla se enciende y va a mostrar una
        //alerta con la hora de encendido.
        this.registerReceiver(screenReceiver, new IntentFilter(Intent.ACTION_SCREEN_ON));
    }
}
