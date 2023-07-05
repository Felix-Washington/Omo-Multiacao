package niveis;

import java.awt.Graphics;
import java.awt.Image;
import java.util.Random;

import omo.Main;
//import omo.Constantes;

public final class Nivel_um extends Nivel_pai{

	// Imagens usadas para a fase1;
	private Image quadro;

	// Variaveis que definem o x das 2 perspectivas
	private int posXQuadro;

	// Variaveis auxiliares.
	private boolean ativoQuadro;
	private boolean ativoBandeira;

	private int time;

	// Construtor;
	public Nivel_um() {
		// Inicializa as imagens;
		this.spriteFront = loadImage("images/niveis/Fase1/Floor (Stage 1).png");		
		this.spriteFront2 = loadImage("images/niveis/Fase1/Floor (Stage 1).png");
		this.spriteMed = loadImage("images/niveis/Fase1/Wall (Stage 1) (2).png");
		this.spriteMed2 = loadImage("images/niveis/Fase1/Wall (Stage 1) (2).png");
		this.bandeira = loadImage("images/niveis/Fase1/Flag Animation.png");
		quadro = loadImage("images/niveis/Fase1/Picture1 (Stage 1).png");

		this.velFront = -16;
		this.velMed = -5;

		this.posXFront = 0;
		this.posXFront2 = getWidth();

		this.posXMed = 0;
		this.posXMed2 = getWidth();

		this.posXQuadro = getWidth();
		ativoQuadro = true;

		posXBandeira = getWidth();
		this.COLS = 23;
		Main.setFimFase(false);
		
		this.score_to_win = 150;
	}

	public void quadro() {
		this.posXQuadro = getWidth();
		ativoQuadro = true;
	}

	// Update das imagens.
	public void update() {

		bandeira();

		if (ativoBandeira) {
			if (time == 2) {
				time = 0;
				this.frameX++;
				if (this.frameX % this.COLS == 0) {
					this.frameX = 0;
				}

			} else {
				time++;
			}

		}

		this.posXFront += this.velFront;
		this.posXFront2 += this.velFront;

		this.posXMed += this.velMed;
		this.posXMed2 += this.velMed;

		posXQuadro += this.velMed;

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

		if (this.posXQuadro + quadro.getWidth(null) < 0) {

			ativoQuadro = false;
			int sorteio = random.nextInt(200);

			if (sorteio == 0) {
				quadro();
			}
		}

	}

	public void bandeira() {
		if (Main.isFimFase()) {
			if (posXBandeira >= 496) {
				ativoBandeira = true;
				posXBandeira += this.velFront;
			} else {
				this.velFront = 0;
				this.velMed = 0;
			}
		}
	}

	// Metodo para desenhar as imagens.
	public void draw(Graphics g) {

		g.drawImage(this.spriteMed, this.posXMed - 8, 0, null);

		g.drawImage(this.spriteMed2, this.posXMed2 - 8, 0, null);

		g.drawImage(this.spriteFront, this.posXFront, this.getHeight() - this.spriteFront.getHeight(null), null);

		g.drawImage(this.spriteFront2, this.posXFront2, this.getHeight() - this.spriteFront.getHeight(null), null);

		if (ativoQuadro) {
			g.drawImage(quadro, posXQuadro, 130, null);
		}

		if (ativoBandeira) {
			g.drawImage(this.bandeira, this.posXBandeira,
					573 - this.getFlagH(), this.posXBandeira
							+ this.getFlagW(), 573
							- this.getFlagH()
							+ this.getFlagH(),
					(this.frameX * this.getFlagW()), 0,
					(this.frameX * this.getFlagW())
							+ this.getFlagW(),
							this.getFlagH(), null);
		}

	}
}
