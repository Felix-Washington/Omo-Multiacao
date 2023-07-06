package Omo.Fases;

import java.awt.Graphics;
import java.awt.Image;

import Omo.Constantes;
import Omo.OmoMultiAcao;

public class Fase3 extends Fases {

	// Imagens usadas para a fase1;
	private Image spriteChao;
	private Image spriteChao2;

	private Image spriteMorros;
	private Image spriteMorros2;

	private Image spriteBack;
	private Image spriteBack2;

	private Image ceu;

	// Variaveis que definem o x das 2 perspectivas
	private int posXFront;
	private int posXFront2;

	private int posXMed;
	private int posXMed2;

	private int posXBack;

	// Variaveis que definem a velocidade das 2 perspectivas;
	private int velFront;
	private int velMed;
	private int velBack;
	private int velBack2;
	private int time2;

	// Variaveis usadas na animacao do celeiro.
	private int COLS;
	private int frameX;
	private int time;
	private int posXCeleiro;
	private boolean ativoCeleiro;

	// Construtor;
	public Fase3() {
		// Inicializa as imagens;
		ceu = Constantes.getInstance().loadImage(
				"Omo/Fases/imagens/Fase3/Sky (fase 3).png");
		
		spriteChao = Constantes.getInstance().loadImage(
				"Omo/Fases/imagens/Fase3/Ground (Stage 3).png");
		spriteChao2 = Constantes.getInstance().loadImage(
				"Omo/Fases/imagens/Fase3/Ground (Stage 3).png");

		spriteMorros = Constantes.getInstance().loadImage(
				"Omo/Fases/imagens/Fase3/Hills (Stage 3).png");
		spriteMorros2 = Constantes.getInstance().loadImage(
				"Omo/Fases/imagens/Fase3/Hills (Stage 3).png");

		spriteBack = Constantes.getInstance().loadImage(
				"Omo/Fases/imagens/Fase3/Mountains (Fase 3).png");
		spriteBack2 = Constantes.getInstance().loadImage(
				"Omo/Fases/imagens/Fase3/Barn Animation.png");


		this.velFront = -15;
		this.velMed = -4;
		this.velBack = -1;
		this.velBack2 = -4;

		this.posXFront = 0;
		this.posXFront2 = Constantes.getInstance().getWidth();

		this.posXMed = 0;
		this.posXMed2 = Constantes.getInstance().getWidth();

		this.posXBack = 600;
		COLS = 24;
		posXCeleiro = Constantes.getInstance().getWidth() + 100;
		OmoMultiAcao.setFimFase(false);
	}

	// Update das imagens.
	public void update() {

		posXFront += velFront;
		posXFront2 += velFront;

		posXMed += velMed;
		posXMed2 += velMed;

		celeiro();

		if (ativoCeleiro && frameX != 23) {
			if (time == 8) {
				time = 0;
				frameX++;
				if (frameX % COLS == 0) {
					frameX = 0;
				}

			} else {
				time++;
			}
		}

		if (this.posXMed + Constantes.getInstance().getWidth() <= 0) {
			posXMed = Constantes.getInstance().getWidth();
		} else if (this.posXMed2 + Constantes.getInstance().getWidth() <= 0) {
			posXMed2 = Constantes.getInstance().getWidth();
		}

		if (this.posXFront + Constantes.getInstance().getWidth() - 10 <= 0) {
			posXFront = Constantes.getInstance().getWidth();
		} else if (this.posXFront2 + Constantes.getInstance().getWidth() - 10 <= 0) {
			posXFront2 = Constantes.getInstance().getWidth();
		}

		if (time2 == 10) {
			posXBack += velBack;
			time2 = 0;
		} else {
			time2++;
		}

	}

	public void celeiro() {
		if (OmoMultiAcao.isFaseTres()
				&& (OmoMultiAcao.getTimer().equals("00:00:01")
				|| OmoMultiAcao.getTimer().equals("00:00:00"))) {
			if (posXCeleiro >= 499) {
				ativoCeleiro = true;
				posXCeleiro += velBack2;
			} else {
				velBack = 0;
				velBack2 = 0;
				velFront = 0;
				velMed = 0;
			}
		}

	}

	// Metodo para desenhar o cenario.
	public void draw(Graphics g) {

		g.drawImage(ceu, 0, 0, null);
		g.drawImage(ceu, 0, 0, null);
		if (posXBack + spriteBack.getWidth(null) >= 0) {
			g.drawImage(spriteBack, posXBack, 120, null);
		}

		g.drawImage(spriteMorros, posXMed, 310, null);

		g.drawImage(spriteMorros2, posXMed2, 310, null);

		if (ativoCeleiro) {
			g.drawImage(spriteBack2, posXCeleiro, 120, posXCeleiro
					+ Constantes.CELEIRO_WIDTH, Constantes.CELEITO_HEIGHT,
					(frameX * Constantes.CELEIRO_WIDTH), 0,
					(frameX * Constantes.CELEIRO_WIDTH)
							+ Constantes.CELEIRO_WIDTH,
					Constantes.CELEITO_HEIGHT, null);
		}

		g.drawImage(spriteChao, posXFront, Constantes.getInstance().getHeight()
				- spriteChao.getHeight(null), null);

		g.drawImage(spriteChao2, posXFront2, Constantes.getInstance()
				.getHeight() - spriteChao.getHeight(null), null);

	}

	public int getPosXCeleiro() {
		return posXCeleiro;
	}

	public boolean isAtivoCeleiro() {
		return ativoCeleiro;
	}

}
