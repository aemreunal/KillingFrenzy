package clientSide.sounds;

import global.Settings;
import java.io.File;
import java.io.IOException;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Gun {
	File soundFile;
	AudioInputStream stream;
	AudioFormat format;
	DataLine.Info info;
	Clip clip;

	public Gun() {

		try {
			soundFile = new File(Settings.GUN_SOUND_FILE_PATH);
			stream = AudioSystem.getAudioInputStream(soundFile);

			format = stream.getFormat();
			info = new DataLine.Info(Clip.class, format);
			clip = (Clip) AudioSystem.getLine(info);
			clip.open(stream);
		} catch (IOException | UnsupportedAudioFileException
				| LineUnavailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void playSound() {
		clip.start();
	}
}
