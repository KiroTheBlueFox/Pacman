package pacman.app;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.Line;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public enum Clips {
	munch1("assets/sounds/munch_1.wav"),
	munch2("assets/sounds/munch_2.wav"),
	move1("assets/sounds/siren_1.wav"),
	move2("assets/sounds/siren_2.wav"),
	move3("assets/sounds/siren_3.wav"),
	powerPellet("assets/sounds/power_pellet.wav");
	private Clip clip;
	
	private Clips(String file) {
		try {
			clip = (Clip) AudioSystem.getLine(new Line.Info(Clip.class));
			clip.open(AudioSystem.getAudioInputStream(new File(file)));
			FloatControl gainControl = ((FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN));
			float range = gainControl.getMaximum() - gainControl.getMinimum();
			float gain = (range * Application.DEFAULT_VOLUME) + gainControl.getMinimum();
			gainControl.setValue(gain);
		} catch (LineUnavailableException | IOException | UnsupportedAudioFileException e) {
			e.printStackTrace();
		}
	}

	public static void closeClips() {
		for (Clips clip : values()) {
			Application.stopSound(clip);
			clip.getClip().close();
		}
	}

	public Clip getClip() {
		return clip;
	}

	public static Clips[] getMoveClips() {
		return new Clips[] {move1, move2, move3};
	}
}
