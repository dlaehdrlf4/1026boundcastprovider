package com.example.a503_25.a1026boundcastprovider;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.provider.MediaStore;

public class AlarmReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        //리시버가 호출되면 수행되는 메소드
        MediaPlayer player = MediaPlayer.create(context.getApplicationContext(),R.raw.abc);

        player.start();
    }
}
