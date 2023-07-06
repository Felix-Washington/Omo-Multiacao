package Omo;

import java.io.File;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;

public class Musica extends Thread {

	private File wav;
	private boolean ativo;
	private AudioInputStream stream;
	private AudioFormat format;
	private DataLine.Info info;
	private Clip clip;

	public void carregar(String caminho) {
		wav = new File("Omo Theme.wav");
	}

	public File getWav() {
		return wav;
	}

	public void setWav(File wav) {
		this.wav = wav;
	}

	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

	public void run() {
		try {
			ativo = true;

			stream = AudioSystem.getAudioInputStream(Musica.class.getResource("/resources/"+ wav));;
			format = stream.getFormat();
			info = new DataLine.Info(Clip.class, format);
			clip = (Clip) AudioSystem.getLine(info);

			clip.open(stream);
			clip.loop(10000);
			clip.drain();
		} catch (Exception e) {
			System.out.println("Erro rodanado a musica " + wav);
		}
	}

	public AudioInputStream getStream() {
		return stream;
	}

	public void setStream(AudioInputStream stream) {
		this.stream = stream;
	}

	public AudioFormat getFormat() {
		return format;
	}

	public void setFormat(AudioFormat format) {
		this.format = format;
	}

	public DataLine.Info getInfo() {
		return info;
	}

	public void setInfo(DataLine.Info info) {
		this.info = info;
	}

	public Clip getClip() {
		return clip;
	}

	public void setClip(Clip clip) {
		this.clip = clip;
	}

}
