package com.github.mcdevteam.purple.voice;

import com.github.mcdevteam.purple.PurpleCarpetExtension;

import javax.sound.sampled.*;

public class Microphone implements Runnable {
    public static final int SAMPLE_RATE = 48000;
    public static final int FRAME_SIZE = (SAMPLE_RATE / 1000) * 20;
    public static double LAST_PEAK = 0;
    public final AudioFormat AUDIO_FORMAT = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED, SAMPLE_RATE, 16, 1, 2, SAMPLE_RATE, false);
    private TargetDataLine microphone = null;
    private AudioInputStream is = null;

    @Override
    public void run() {
        try {
            if (microphone == null) {
                microphone = getLine();
                if (microphone == null) throw new Exception("Microphone is null");
                microphone.open(AUDIO_FORMAT, FRAME_SIZE);
                microphone.start();
                is = new AudioInputStream(microphone);
            }

            double peak = 0f;
            double rms  = 0f;
            int l = is.available();
            if (l > 0) {
                byte[] buf = is.readNBytes(l);
                for (int i = 0; i < l-1; ) {
                    int sample = buf[i++] & 0xFF;
                    sample |= buf[i++] << 8;
                    peak = Math.max(Math.abs(sample / 32768.0), peak);
                    rms += sample * sample;
                }
                rms = Math.sqrt(rms / l);
            }
            if(LAST_PEAK > peak) peak = LAST_PEAK * 0.875;
            LAST_PEAK = peak;
        } catch (Exception e) {
            PurpleCarpetExtension.LOGGER.error(e.getMessage(), e);
            try {
                if (microphone != null) microphone.close();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
            microphone = null;
        }
    }

    private TargetDataLine getLine() {
        DataLine.Info info = new DataLine.Info(TargetDataLine.class, AUDIO_FORMAT);
        try {
            return (TargetDataLine) AudioSystem.getLine(info);
        } catch (Exception e) {
            return null;
        }
    }
}
