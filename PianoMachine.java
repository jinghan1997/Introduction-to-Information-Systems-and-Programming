package piano;

//wut
import javax.sound.midi.MidiUnavailableException;

import java.util.ArrayList;
import java.util.List;

import midi.Midi;
import midi.Instrument;
import music.Pitch;
import music.NoteEvent;

public class PianoMachine {

    private Midi midi;
    private List<Pitch> PitchList = new ArrayList<Pitch>();
    private List<NoteEvent> recordList = new ArrayList<NoteEvent>();
    private Instrument ins = Instrument.PIANO;
    private Pitch newPitch;
    private int octToShift = 0;
    private boolean record = false;

    /**
     * constructor for PianoMachine.
     *
     * initialize midi device and any other state that we're storing.
     */
    public PianoMachine() {
        try {
            midi = Midi.getInstance();
        } catch (MidiUnavailableException e1) {
            System.err.println("Could not initialize midi device");
            e1.printStackTrace();
            return;
        }
    }

    public void beginNote(Pitch rawPitch) {
        newPitch = rawPitch.transpose(octToShift);
        if (!PitchList.contains(newPitch)) {
            midi.beginNote(newPitch.toMidiFrequency(), ins);
            PitchList.add(newPitch);
            if (record){
                recordList.add(new NoteEvent(newPitch,System.currentTimeMillis(),ins,NoteEvent.Kind.start));
            }
        }
    }

    public void endNote(Pitch rawPitch) {
        newPitch = rawPitch.transpose(octToShift);
        if (PitchList.contains(newPitch)) {
            midi.endNote(newPitch.toMidiFrequency(), ins);
            PitchList.remove(newPitch);
            if (record){
                recordList.add(new NoteEvent(newPitch,System.currentTimeMillis(),ins,NoteEvent.Kind.stop));
            }
        }
    }

    public void changeInstrument() {
        ins = ins.next();
    }


    public void shiftUp() {
        if(octToShift<Pitch.OCTAVE*2) {
            octToShift+=Pitch.OCTAVE;
        }
    }

    public void shiftDown() {
        if(octToShift>Pitch.OCTAVE*(-2)) {
            octToShift-=Pitch.OCTAVE;
        }
    }


    public boolean toggleRecording() {
        if (record) {
            record = false;
        } else {
            recordList.clear();
            record = true;
        }
        return record;
    }


    public void playback()  {
        try {
            Midi m = Midi.getInstance();

            for (int i = 0; i < recordList.size(); i++) {

                NoteEvent note = recordList.get(i);

                if (note.getKind() == NoteEvent.Kind.start) {
                    m.beginNote(note.getPitch().toMidiFrequency(), note.getInstr());
                } else {
                    m.endNote(note.getPitch().toMidiFrequency(), note.getInstr());
                }

                if (i< recordList.size()-1){
                    //find time between the ins
                    int time =(int) (recordList.get(i+1).getTime() - note.getTime()) ;
                    //prof norman say dont use rest, use thread sleep
                    Thread.sleep(time);
                    }
                }
        
        } catch (MidiUnavailableException | InterruptedException e1) {
            System.err.println("Could not initialize midi device");
            e1.printStackTrace();           
        }
    }

}
