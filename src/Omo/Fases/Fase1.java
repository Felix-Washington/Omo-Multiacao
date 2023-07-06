package Omo.Fases;

import java.awt.Graphics;
import java.awt.Image;
import java.util.Random;

import Omo.Constantes;
import Omo.OmoMultiAcao;

public class Fase1 {

	// Imagens usadas para a fase1;
	private Image spriteFront;
	private Image spriteFront2;

	private Image spriteMed;
	private Image spriteMed2;

	private Image quadro;

	private Image bandeira;

	// Variaveis que definem o x das 2 perspectivas
	private int posXFront;
	private int posXFront2;

	private int posXMed;
	private int posXMed2;

	private int posXQuadro;

	private int posXBandeira;

	// Variaveis que definem a velocidade das 2 perspectivas;
	private int velFront;
	private int velMed;

	// Variaveis auxiliares.
	private boolean ativoQuadro;
	private boolean ativoBandeira;

	private int frameX;
	private int time;
	private int COLS;

	// Construtor;
	public Fase1() {

		// Inicializa as imagens;
		spriteFront = Constantes.getInstance().loadImage(
				"Omo/Fases/imagens/Fase1/Floor (Stage 1).png");
		spriteFront2 = Constantes.getInstance().loadImage(
				"Omo/Fases/imagens/Fase1/Floor (Stage 1).png");

		spriteMed = Constantes.getInstance().loadImage(
				"Omo/Fases/imagens/Fase1/Wall (Stage 1) (2).png");
		spriteMed2 = Constantes.getInstance().loadImage(
				"Omo/Fases/imagens/Fase1/Wall (Stage 1) (2).png");

		bandeira = Constantes.getInstance().loadImage(
				"Omo/Fases/imagens/Flag Animation.png");
		quadro = Constantes.getInstance().loadImage(
				"Omo/Fases/imagens/Fase1/Picture1 (Stage 1).png");

		this.velFront = -16;
		this.velMed = -5;

		this.posXFront = 0;
		this.posXFront2 = Constantes.getInstance().getWidth();

		this.posXMed = 0;
		this.posXMed2 = Constantes.getInstance().getWidth();

		this.posXQuadro = Constantes.getInstance().getWidth();
		ativoQuadro = true;

		posXBandeira = Constantes.getInstance().getWidth();
		COLS = 23;
		OmoMultiAcao.setFimFase(false);
	}

	public void quadro() {
		this.posXQuadro = Constantes.getInstance().getWidth();
		ativoQuadro = true;
	}

	// Update das imagens.
	public void update() {

		bandeira();

		if (ativoBandeira) {
			if (time == 2) {
				time = 0;
				frameX++;
				if (frameX % COLS == 0) {
					frameX = 0;
				}

			} else {
				time++;
			}

		}

		posXFront += velFront;
		posXFront2 += velFront;

		posXMed += velMed;
		posXMed2 += velMed;

		posXQuadro += velMed;

		if (this.posXMed + Constantes.getInstance().getWidth() - 10 <= 0) {
			posXMed = Constantes.getInstance().getWidth();
		} else if (this.posXMed2 + Constantes.getInstance().getWidth() - 10 <= 0) {
			posXMed2 = Constantes.getInstance().getWidth();
		}

		if (this.posXFront + Constantes.getInstance().getWidth() - 11 <= 0) {
			posXFront = Constantes.getInstance().getWidth();
		} else if (this.posXFront2 + Constantes.getInstance().getWidth() - 11 <= 0) {
			posXFront2 = Constantes.getInstance().getWidth();
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
		if (OmoMultiAcao.isFimFase()) {
			if (posXBandeira >= 496) {
				ativoBandeira = true;
				posXBandeira += velFront;
			} else {
				velFront = 0;
				velMed = 0;
			}
		}
	}

	// Metodo para desenhar as imagens.
	public void draw(Graphics g) {

		g.drawImage(spriteMed, posXMed - 8, 0, null);

		g.drawImage(spriteMed2, posXMed2 - 8, 0, null);

		g.drawImage(spriteFront, posXFront, Constantes.getInstance()
				.getHeight() - spriteFront.getHeight(null), null);

		g.drawImage(spriteFront2, posXFront2, Constantes.getInstance()
				.getHeight() - spriteFront.getHeight(null), null);

		if (ativoQuadro) {
			g.drawImage(quadro, posXQuadro, 130, null);
		}

		if (ativoBandeira) {
			g.drawImage(bandeira, posXBandeira,
					573 - Constantes.BANDEIRA_HEIGHT, posXBandeira
							+ Constantes.BANDEIRA_WIDTH, 573
							- Constantes.BANDEIRA_HEIGHT
							+ Constantes.BANDEIRA_HEIGHT,
					(frameX * Constantes.BANDEIRA_WIDTH), 0,
					(frameX * Constantes.BANDEIRA_WIDTH)
							+ Constantes.BANDEIRA_WIDTH,
					Constantes.BANDEIRA_HEIGHT, null);
		}

	}
}
