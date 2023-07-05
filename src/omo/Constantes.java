package omo;
import java.awt.Image;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

public class Constantes  {

	private int width = 1024;
	private int height = 600;

	public static final int OMO_VOANDO_WIDTH = 247;
	public static final int OMO_VOANDO_HEIGHT = 274;

	public static final int CELEIRO_WIDTH = 679;
	public static final int CELEITO_HEIGHT = 450;
	
	public static final int BANDEIRA_WIDTH = 175;
	public static final int BANDEIRA_HEIGHT = 340;

	public static final int TIRO_WIDTH = 75;
	public static final int TIRO_HEIGHT = 75;

	public static int clickX;
	public static int clickY;

	private static Constantes instance;

	public static Constantes getInstance() {
		if (instance == null) {
			instance = new Constantes();
		}

		return instance;
	}

	// metodo para carregar imagens
	public Image loadImage(String fileName) {
		URL imgUrl = getClass().getClassLoader().getResource(fileName);
		//URL imgUrl = getClass().getResource(fileName);
		//System.out.println("Image url" + imgUrl);
		if (imgUrl == null) {
			System.out.println("Erro ao carregar a " + fileName);
		} else {
			try {
				return ImageIO.read(imgUrl);
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
		return null;
	} 


	public static void setInstance(Constantes instance) {
		Constantes.instance = instance;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

}
