package clientSide.sounds;

import global.Settings;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class Gun {
    private File soundFile;
    private AudioInputStream stream;
    private AudioFormat format;
    private DataLine.Info info;
    private Clip clip;

    public Gun() {
        try {
            soundFile = new File(Settings.GUN_SOUND_FILE_PATH);
            stream = AudioSystem.getAudioInputStream(soundFile);
            format = stream.getFormat();
            info = new DataLine.Info(Clip.class, format);
            clip = (Clip) AudioSystem.getLine(info);
            clip.open(stream);
        } catch (IOException e) {
            System.out.println("Unable to read the sound file!");
//			  e.printStackTrace();
        } catch (UnsupportedAudioFileException e) {
            System.out.println("Unsupported audio type!");
//            e.printStackTrace();
        } catch (LineUnavailableException e) {
            System.out.println("Unable to get audio line!");
//            e.printStackTrace();
        }
    }

    public void playSound() {
        clip.start();
    }
}
