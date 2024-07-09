import javax.sound.sampled.*;
import java.io.File;


// satria
public class SoundPlayer {

    private Clip clip;
    public SoundPlayer(String filePath) {
        try {
            File soundFile = new File(filePath);
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(soundFile);

            clip = AudioSystem.getClip();

            clip.open(audioIn);

            // close clip once it stop playing
            clip.addLineListener(event -> {
                if (event.getType() == LineEvent.Type.STOP) {
                    clip.close();
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void playContinuously() {
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public void playClip() {
        clip.start();
    }

    public void stopClip() {
        clip.close();
        clip.setFramePosition(0);
        clip.stop();
    }
}