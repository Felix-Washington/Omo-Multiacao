package Omo;

import java.awt.Graphics;
import java.awt.Image;

public abstract class Personagens {

	// Esta é a classe personagens. Ele é uma super classe que serve apenas para
	// realizar a herança.

	private Image sprite;
	private Image sprite2;

	private int vida;
	private int velocidade;
	private int x;
	private int y;
	private int contador;
	private boolean colidiu;

	private final int COLS = 24;
	private int frameX;
	private int time;

	public Personagens() {

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

	public int getVida() {
		return vida;
	}

	public void setVida(int vida) {
		this.vida = vida;
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

	public int getContador() {
		return contador;
	}

	public void setContador(int contador) {
		this.contador = contador;
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

}
