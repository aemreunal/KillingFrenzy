package clientSide.sounds;

import global.Settings;

import java.io.File;
import java.io.IOException;
import javax.sound.sampled.*;

/*
 * This code belongs to:
 * Ahmet Emre Unal
 * Eren Sezener
 * Deniz Sokmen
 * Erdi Gultekin
 */

public class Death {
    private static File soundFile;
    private static AudioInputStream stream;
    private static AudioFormat format;
    private static DataLine.Info info;
    private static Clip clip;

    private static void initDeathSoundFile() {
        try {
            soundFile = new File(Settings.DEATH_SOUND_FILE_PATH);
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
            initDeathSoundFile();
        }
        clip.setFramePosition(0);
        clip.start();
    }
}
