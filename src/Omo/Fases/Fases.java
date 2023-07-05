package Omo.Fases;

import java.awt.Image;

public class Fases {

	private Image spriteFront;
	private Image spriteFront2;

	private Image spriteMed;
	private Image spriteMed2;

	private Image bandeira;

	// Variaveis que definem o x das 2 perspectivas
	private int posXFront;
	private int posXFront2;

	private int posXMed;
	private int posXMed2;

	private int posXBandeira;

	// Variaveis que definem a velocidade das 2 perspectivas;
	private int velFront;
	private int velMed;

	private int COLS;
	private int frameX;

	public Image getSpriteFront() {
		return spriteFront;
	}

	public void setSpriteFront(Image spriteFront) {
		this.spriteFront = spriteFront;
	}

	public Image getSpriteFront2() {
		return spriteFront2;
	}

	public void setSpriteFront2(Image spriteFront2) {
		this.spriteFront2 = spriteFront2;
	}

	public Image getSpriteMed() {
		return spriteMed;
	}

	public void setSpriteMed(Image spriteMed) {
		this.spriteMed = spriteMed;
	}

	public Image getSpriteMed2() {
		return spriteMed2;
	}

	public void setSpriteMed2(Image spriteMed2) {
		this.spriteMed2 = spriteMed2;
	}

	public Image getBandeira() {
		return bandeira;
	}

	public void setBandeira(Image bandeira) {
		this.bandeira = bandeira;
	}

	public int getPosXFront() {
		return posXFront;
	}

	public void setPosXFront(int posXFront) {
		this.posXFront = posXFront;
	}

	public int getPosXFront2() {
		return posXFront2;
	}

	public void setPosXFront2(int posXFront2) {
		this.posXFront2 = posXFront2;
	}

	public int getPosXMed() {
		return posXMed;
	}

	public void setPosXMed(int posXMed) {
		this.posXMed = posXMed;
	}

	public int getPosXMed2() {
		return posXMed2;
	}

	public void setPosXMed2(int posXMed2) {
		this.posXMed2 = posXMed2;
	}

	public int getPosXBandeira() {
		return posXBandeira;
	}

	public void setPosXBandeira(int posXBandeira) {
		this.posXBandeira = posXBandeira;
	}

	public int getVelFront() {
		return velFront;
	}

	public void setVelFront(int velFront) {
		this.velFront = velFront;
	}

	public int getVelMed() {
		return velMed;
	}

	public void setVelMed(int velMed) {
		this.velMed = velMed;
	}

	public int getCOLS() {
		return COLS;
	}

	public void setCOLS(int cOLS) {
		COLS = cOLS;
	}

	public int getFrameX() {
		return frameX;
	}

	public void setFrameX(int frameX) {
		this.frameX = frameX;
	}

}
