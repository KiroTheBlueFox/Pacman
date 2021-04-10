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
	eat_fruit("assets/sounds/eat_fruit.wav"),
	eat_ghost("assets/sounds/eat_ghost.wav"),
	move1("assets/sounds/siren_1.wav"),
	move2("assets/sounds/siren_2.wav"),
	move3("assets/sounds/siren_3.wav"),
	move4("assets/sounds/siren_4.wav"),
	move5("assets/sounds/siren_5.wav"),
	powerPellet("assets/sounds/power_pellet.wav"),
	death1("assets/sounds/death_1.wav"),
	death2("assets/sounds/death_2.wav"),
	credit("assets/sounds/credit.wav"),
	retreating("assets/sounds/retreating.wav"),
	intermission("assets/sounds/intermission.wav"),
	extend("assets/sounds/extend.wav"),
	game_start("assets/sounds/game_start.wav");
	private Clip clip;
	private boolean alreadyPlayed = false;
	
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
	
	public void play(int times) {
		if (!alreadyPlayed) {
			clip.start();
			alreadyPlayed = true;
			times--;
		}
		if (times > 0)
			clip.loop(times);
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
		return new Clips[] {move1, move2, move3, move4, move5};
	}
	
	public boolean hasAlreadyBeenPlayed() {
		return alreadyPlayed;
	}
	
	public void setAlreadyPlayed(boolean alreadyPlayed) {
		this.alreadyPlayed = alreadyPlayed;
	}
}
