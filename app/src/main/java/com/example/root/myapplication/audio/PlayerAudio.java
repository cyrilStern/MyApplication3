package com.example.root.myapplication.audio;

import android.media.AudioTrack;

/**
 * Created by cyrilstern1 on 24/02/2017.
 */

public class PlayerAudio extends AudioTrack {
    public PlayerAudio(int streamType, int sampleRateInHz, int channelConfig, int audioFormat, int bufferSizeInBytes, int mode) throws IllegalArgumentException {
        super(streamType, sampleRateInHz, channelConfig, audioFormat, bufferSizeInBytes, mode);
    }
}
