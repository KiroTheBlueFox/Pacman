package pacman.app;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.Line;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Clips {
	public static Clip
	munch1, munch2,
	move1,
	powerPellet;
	
	public static void initClips() {
		try {
			munch1 = (Clip) AudioSystem.getLine(new Line.Info(Clip.class));
			munch1.open(AudioSystem.getAudioInputStream(new File("assets/sounds/munch_1.wav")));
			
			munch2 = (Clip) AudioSystem.getLine(new Line.Info(Clip.class));
			munch2.open(AudioSystem.getAudioInputStream(new File("assets/sounds/munch_2.wav")));
			
			powerPellet = (Clip) AudioSystem.getLine(new Line.Info(Clip.class));
			powerPellet.open(AudioSystem.getAudioInputStream(new File("assets/sounds/power_pellet.wav")));
			
			move1 = (Clip) AudioSystem.getLine(new Line.Info(Clip.class));
			move1.open(AudioSystem.getAudioInputStream(new File("assets/sounds/siren_1.wav")));
		} catch (LineUnavailableException | IOException | UnsupportedAudioFileException e) {
			e.printStackTrace();
		}
	}
}
