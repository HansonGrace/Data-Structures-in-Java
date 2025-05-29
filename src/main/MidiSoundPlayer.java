package main;

import javax.sound.midi.*;

public class MidiSoundPlayer {
    private Synthesizer synth;
    private MidiChannel channel;

    public MidiSoundPlayer() {
        try {
            synth = MidiSystem.getSynthesizer();
            synth.open();
            channel = synth.getChannels()[0];
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void playNote(int note, int velocity, int durationMs) {
        channel.noteOn(note, velocity);
        try {
            Thread.sleep(durationMs);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        channel.noteOff(note);
    }

    public void close() {
        if (synth != null && synth.isOpen()) {
            synth.close();
        }
    }
}
