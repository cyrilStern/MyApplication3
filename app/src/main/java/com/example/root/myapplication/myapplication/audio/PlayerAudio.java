package com.example.root.myapplication.myapplication.audio;

import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.AudioTrack;
import android.media.MediaPlayer;
import android.net.Uri;
import android.util.Log;

import java.io.IOException;

/**
 * Created by cyrilstern1 on 24/02/2017.
 */

public class PlayerAudio extends MediaPlayer {
    private static PlayerAudio INSTANCE = null;
    private String path = "";
    private Context ctx;
    private Boolean stop = false;

    public PlayerAudio() {
    }

    public static PlayerAudio getInstance() throws IOException {
        if (INSTANCE == null) {
            INSTANCE = new PlayerAudio();
        }
        return INSTANCE;
    }

    public static void stopPlaying() {

        if (INSTANCE != null && INSTANCE.isPlaying()) {
            INSTANCE.stop();
            INSTANCE.reset();
            Log.i("music", "stopmusic");
        }
    }

    public String getRessourcePlaying() {
        return this.path;
    }

    public void setRadio(Context ctx, String path) throws IOException {
        this.path = path;
        if (INSTANCE.isPlaying()) {
            INSTANCE.stop();
            INSTANCE.reset();
            try {
                INSTANCE.reset();

                INSTANCE.setAudioStreamType(AudioManager.STREAM_MUSIC);
                INSTANCE.setDataSource(path);
                INSTANCE.prepareAsync();
                INSTANCE.setOnPreparedListener(new OnPreparedListener() {
                    @Override
                    public void onPrepared(MediaPlayer mp) {
                        INSTANCE.start();
                        Log.i("music", "startmusic");

                    }
                });

            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } catch (SecurityException e) {
                e.printStackTrace();
            } catch (IllegalStateException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        } else {
            //this.stop = false;
            INSTANCE.path = path;
            INSTANCE.ctx = ctx;
            try {
                INSTANCE.reset();

                INSTANCE.setAudioStreamType(AudioManager.STREAM_MUSIC);
                INSTANCE.setDataSource(path);
                INSTANCE.prepareAsync();
                INSTANCE.setOnPreparedListener(new OnPreparedListener() {
                    @Override
                    public void onPrepared(MediaPlayer mp) {
                        INSTANCE.start();
                    }
                });

            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } catch (SecurityException e) {
                e.printStackTrace();
            } catch (IllegalStateException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }


    }

    @Override
    public void setOnCompletionListener(OnCompletionListener listener) {
        super.setOnCompletionListener(listener);
    }

    @Override
    public void setOnBufferingUpdateListener(OnBufferingUpdateListener listener) {
        super.setOnBufferingUpdateListener(listener);
    }


    @Override
    public void setOnPreparedListener(OnPreparedListener listener) {
        super.setOnPreparedListener(listener);
    }
}
