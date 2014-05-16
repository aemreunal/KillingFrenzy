package clientSide.sounds;

import global.Settings;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

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
			e.printStackTrace();
		}

	}

	public void playSound() {
		clip.start();
	}
}
