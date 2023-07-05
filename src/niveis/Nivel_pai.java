package niveis;

import java.awt.Image;
import java.util.Random;

import omo.Constantes;

public class Nivel_pai {

	protected Image spriteFront;
	protected Image spriteFront2;

	protected Image spriteMed;
	protected Image spriteMed2;

	protected Image bandeira;

	// Variaveis que definem o x das 2 perspectivas
	protected int posXFront;
	protected int posXFront2;

	protected int posXMed;
	protected int posXMed2;

	protected int posXBandeira;

	// Variaveis que definem a velocidade das 2 perspectivas;
	protected int velFront;
	protected int velMed;

	protected int COLS;
	protected int frameX;
	
	protected int score_to_win;
	
	protected Random random;
	
	public Image loadImage(String path) {
		Image image = Constantes.getInstance().loadImage(path);
		
		return image;
	}
	
	public int getWidth() {
		return Constantes.getInstance().getWidth();
	}
	
	public int getHeight() {
		return Constantes.getInstance().getHeight();
	}
	
	public int getFlagH() {
		return Constantes.BANDEIRA_HEIGHT;
	}
	
	public int getFlagW() {
		return Constantes.BANDEIRA_WIDTH;
	}

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

	public int getScore_to_win() {
		return score_to_win;
	}

	public void setScore_to_win(int score_to_win) {
		this.score_to_win = score_to_win;
	}


}
