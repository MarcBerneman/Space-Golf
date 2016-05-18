package gui_componenten;

//overgenomen van ProjectTemplate <-- Beerend

import javax.sound.sampled.AudioInputStream;   
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class MusicPlayer implements Runnable {
	final static String MUSIC_FOLDER = "music/";

	private String fileName;
	private boolean loopContinuously;
	
	private Clip clip;

	public MusicPlayer(String fileName, boolean loopContinuously) {
		this.fileName = fileName;
		this.loopContinuously = loopContinuously;
	}

	public void run() {
		try {
			AudioInputStream as = AudioSystem.getAudioInputStream(getClass()
					.getResource(MUSIC_FOLDER + fileName).toURI().toURL());

			clip = AudioSystem.getClip();

			clip.open(as);
			if (loopContinuously)
				clip.loop(Clip.LOOP_CONTINUOUSLY);
			clip.start();

		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("De requested music file " + fileName
					+ " could not be found or loaded.");
		}
	}
	
	public void stop(){
		clip.stop();
	}
}
