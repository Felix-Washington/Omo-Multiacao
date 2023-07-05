package omo;

import java.awt.Graphics;
import java.util.Random;

import niveis.Nivel_pai;

public class LevelTest extends Nivel_pai{

	public LevelTest() {
		this.spriteFront = (Constantes.getInstance().loadImage(
				"images/niveis/Fase1/Floor (Stage 1).png"));
		this.spriteFront2 = loadImage("images/niveis/Fase1/Floor (Stage 1).png");
		this.spriteMed = loadImage("images/niveis/Fase1/Floor (Stage 1).png");
		this.spriteMed2 = loadImage("images/niveis/Fase1/Floor (Stage 1).png");
		this.bandeira = loadImage("images/niveis/Fase1/Floor (Stage 1).png");
	}
	
	public void draw(Graphics g) {

		g.drawImage(this.spriteMed, this.posXMed - 8, 0, null);

		g.drawImage(this.spriteMed2, this.posXMed2 - 8, 0, null);

		g.drawImage(this.spriteFront, this.posXFront, this.getWidth() - this.spriteFront.getHeight(null), null);

		g.drawImage(this.spriteFront2, this.posXFront2, this.getWidth() - this.spriteFront.getHeight(null), null);
		
	}
	
	public void update() {

		this.posXFront += this.velFront;
		this.posXFront2 += this.velFront;

		this.posXMed += this.velMed;
		this.posXMed2 += this.velMed;
		if (this.posXMed + this.getWidth() - 10 <= 0) {
			this.posXMed = this.getWidth();
		} else if (this.posXMed2 + this.getWidth() - 10 <= 0) {
			this.posXMed2 = this.getWidth();
		}

		if (this.posXFront + this.getWidth() - 11 <= 0) {
			this.posXFront = this.getWidth();
		} else if (this.posXFront2 + this.getWidth() - 11 <= 0) {
			this.posXFront2 = this.getWidth();
		}

		Random random = new Random();


	}
	
}
