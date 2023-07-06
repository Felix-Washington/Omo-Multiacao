package Omo;

import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

public class Constantes {

	private int width = 1024;
	private int height = 600;

	public static final int CAMISA_VOANDO_WIDTH = 153;
	public static final int CAMISA_VOANDO_HEIGHT = 132;

	public static final int CALCA_VOANDO_WIDTH = 92;
	public static final int CALCA_VOANDO_HEIGHT = 140;

	public static final int OMO_VOANDO_WIDTH = 247;
	public static final int OMO_VOANDO_HEIGHT = 274;

	public static final int ROUPA_CHAO_WIDTH = 200;
	public static final int ROUPA_CHAO_HEIGHT = 80;

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

	public Image loadImage(String fileName) {
		URL imgUrl = getClass().getClassLoader().getResource(fileName);

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
	} // metodo para carregar imagens

	public void drawVida(Graphics g) {

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
