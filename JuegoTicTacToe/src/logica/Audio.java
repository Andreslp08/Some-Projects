package logica;

import Gui.GuiDialog;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import sun.audio.AudioStream;

public class Audio {
//	private AudioInputStream inputStream;
//	private AudioStream seleccion;

	private String[] mouseSonidos = {"/sonidos/mouseAdentro.wav"};
	private Clip clip;
	private AudioInputStream audioInputStream;

	public Audio() {

	}

	public void reproducirSonido(String url, boolean loop) {
		try {
			audioInputStream = AudioSystem.getAudioInputStream(new File(getClass().getResource(url).getFile().replace("Andr%c3%a9s", "Andrés")));
			clip = AudioSystem.getClip();
			clip.open(audioInputStream);
			clip.start();
			if (loop == true) {
				clip.loop(Clip.LOOP_CONTINUOUSLY);
			}

		} catch (Exception ex) {
			System.err.println("No se pudo reproducir el audio: " + ex);

		}

	}

	public void detenerSonido() {
		clip.stop();
	}

	public Clip obtenerSonido() {
		return clip;
	}

	public AudioInputStream getAudioInputStream() {
		return audioInputStream;
	}

	public void cambiarVolumen(float volumen) {
		FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
		gainControl.setValue(volumen); // Reduce volume by 10 decibels.
	}
}
