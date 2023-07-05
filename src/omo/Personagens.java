package omo;
import java.awt.Graphics;
import java.awt.Image;

public abstract class Personagens {

	// Esta é a classe personagens. Ele é uma super classe que serve apenas para
	// realizar a herança.

	protected Image sprite;
	private Image sprite2;

	protected int life;
	protected int velocidade;
	//protected int ve
	protected int x;
	protected int y;
	protected int entity_width;
	protected int entity_height;
	private boolean colidiu;

	private final int COLS = 24;
	protected int frameX;
	private int time;


	public Image loadImage(String path) {
		Image image = null;
		image = Constantes.getInstance().loadImage(path);		
		return image;
	}
	
	public abstract void draw(Graphics g);

	public Image getSprite() {
		return sprite;
	}

	public void setSprite(Image sprite) {
		this.sprite = sprite;
	}

	public Image getSprite2() {
		return sprite2;
	}

	public void setSprite2(Image sprite2) {
		this.sprite2 = sprite2;
	}

	public int getVelocidade() {
		return velocidade;
	}

	public void setVelocidade(int velocidade) {
		this.velocidade = velocidade;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public boolean isColidiu() {
		return colidiu;
	}

	public void setColidiu(boolean colidiu) {
		this.colidiu = colidiu;
	}

	public int getFrameX() {
		return frameX;
	}

	public void setFrameX(int frameX) {
		this.frameX = frameX;
	}

	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}

	public int getCOLS() {
		return COLS;
	}

	public int getLife() {
		return life;
	}

	public void setLife(int life) {
		this.life = life;
	}
	
	

}
