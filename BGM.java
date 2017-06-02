import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class BGM {
	
	private static Clip clip;
	
	public void play(String title) {
		
		try {
			AudioInputStream input = AudioSystem.getAudioInputStream(this.getClass().getResource(title));
			clip = AudioSystem.getClip();
			clip.open(input);
			
			if(title.equals("8_City_of_Funk.wav")) {
				clip.loop(Clip.LOOP_CONTINUOUSLY);
			} else {
				clip.loop(0);
			}
		} catch(Exception ex) { }
	}
	
	public void stop() {
		
		if(clip.isRunning()) {
			clip.stop();
		}
	}
}
