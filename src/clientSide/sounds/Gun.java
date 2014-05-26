package clientSide.sounds;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.*;

import global.Settings;

public class Gun {
    private static File soundFile;
    private static AudioInputStream stream;
    private static AudioFormat format;
    private static DataLine.Info info;
    private static Clip clip;

    private static void initGunSoundFile() {
        try {
            soundFile = new File(Settings.GUN_SOUND_FILE_PATH);
            stream = AudioSystem.getAudioInputStream(soundFile);
            format = stream.getFormat();
            info = new DataLine.Info(Clip.class, format);
            clip = (Clip) AudioSystem.getLine(info);
            clip.open(stream);
        } catch (IOException e) {
            System.out.println("Unable to read the sound file!");
        } catch (UnsupportedAudioFileException e) {
            System.out.println("Unsupported audio type!");
        } catch (LineUnavailableException e) {
            System.out.println("Unable to get audio line!");
        }
    }

    public static void playSound() {
        if (clip == null) {
            initGunSoundFile();
        }
        clip.setFramePosition(0);
        clip.start();
    }
}
