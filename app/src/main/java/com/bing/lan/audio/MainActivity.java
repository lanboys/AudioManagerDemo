package com.bing.lan.audio;

import android.content.Context;
import android.media.AudioManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private AudioManager mAudioManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mAudioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //http://blog.csdn.net/wds1181977/article/details/40649511

                Log.v("Audio", "响铃模式: " + mAudioManager.getRingerMode());
                Toast.makeText(MainActivity.this, "RingerMode: " + mAudioManager.getRingerMode(), Toast.LENGTH_SHORT).show();
                //系统音量
                int max = mAudioManager.getStreamMaxVolume(AudioManager.STREAM_SYSTEM);
                int current = mAudioManager.getStreamVolume(AudioManager.STREAM_SYSTEM);
                Log.v("Audio", "系统音量 max: " + max + ", current: " + current);

                //铃声音量
                max = mAudioManager.getStreamMaxVolume(AudioManager.STREAM_RING);
                current = mAudioManager.getStreamVolume(AudioManager.STREAM_RING);
                Log.v("Audio", "铃声音量 max: " + max + ", current: " + current);

                //提示声音音量
                max = mAudioManager.getStreamMaxVolume(AudioManager.STREAM_ALARM);
                current = mAudioManager.getStreamVolume(AudioManager.STREAM_ALARM);
                Log.v("Audio", "提示声音音量 max: " + max + ", current: " + current);

                //音乐音量
                max = mAudioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
                current = mAudioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
                Log.v("Audio", "音乐音量 max: " + max + ", current: " + current);

                //通话音量
                max = mAudioManager.getStreamMaxVolume(AudioManager.STREAM_VOICE_CALL);
                current = mAudioManager.getStreamVolume(AudioManager.STREAM_VOICE_CALL);
                Log.v("Audio", "通话音量 max: " + max + ", current: " + current);
            }
        });
    }

    //ringerMode为手机的系统声音设置的状态值，0位静音，1为震动，2为响铃
    //am.setRingerMode(RINGER_MODE_NORMAL);//  正常模式   响铃 振动 (响铃模式震动 无用？)
    //am.setRingerMode(RINGER_MODE_SILENT);//  静音模式   静音 不振动
    //am.setRingerMode(RINGER_MODE_VIBRATE);// 震动模式   静音 振动

    public void onSilent(View view) {//0
        mAudioManager.setRingerMode(AudioManager.RINGER_MODE_SILENT);
    }

    public void onVibrate(View view) {//1
        mAudioManager.setRingerMode(AudioManager.RINGER_MODE_VIBRATE);
    }

    public void onNormal(View view) {//2
        mAudioManager.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
    }

    public void onRaise(View view) {
        //增加音量，调出系统音量控制
        mAudioManager.adjustStreamVolume(AudioManager.STREAM_SYSTEM, AudioManager.ADJUST_RAISE,
                AudioManager.FX_FOCUS_NAVIGATION_UP);
    }

    public void onLower(View view) {
        //降低音量，调出系统音量控制
        mAudioManager.adjustStreamVolume(AudioManager.STREAM_SYSTEM, AudioManager.ADJUST_LOWER,
                AudioManager.FX_FOCUS_NAVIGATION_UP);
    }
}
