package niveis;

import java.awt.Graphics;
import java.awt.Image;

import omo.Constantes;
import omo.Main;

public class Nivel_tres extends Nivel_pai {

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
	private int velBack;
	private int velBack2;
	private int time2;

	// Variaveis usadas na animacao do celeiro.
	private int time;
	private int posXCeleiro;
	private boolean ativoCeleiro;

	// Construtor;
	public Nivel_tres() {
		// Inicializa as imagens;
		ceu = loadImage("images/niveis/nivel_tres/Sky (fase 3).png");
		spriteChao = loadImage("images/niveis/nivel_dois/Ground (Stage 3).png");
		spriteChao2 = loadImage("images/niveis/nivel_dois/Ground (Stage 3).png");
		spriteMorros = loadImage("images/niveis/nivel_dois/Hills (Stage 3).png");
		spriteMorros2 = loadImage("images/niveis/nivel_dois/Hills (Stage 3).png");
		spriteBack = loadImage("images/niveis/nivel_dois/Mountains (Fase 3).png");
		spriteBack2 = loadImage("images/niveis/nivel_dois/Barn Animation.png");


		this.velFront = -15;
		this.velMed = -4;
		this.velBack = -1;
		this.velBack2 = -4;

		this.posXFront = 0;
		this.posXFront2 = Constantes.getInstance().getWidth();

		this.posXMed = 0;
		this.posXMed2 = Constantes.getInstance().getWidth();

		this.posXBack = 600;
		this.COLS = 24;
		this.score_to_win = 300;
		posXCeleiro = Constantes.getInstance().getWidth() + 100;
		Main.setFimFase(false);
	}

	// Update das imagens.
	public void update() {

		posXFront += this.velFront;
		posXFront2 += this.velFront;

		posXMed += this.velMed;
		posXMed2 += this.velMed;

		celeiro();

		if (ativoCeleiro && this.frameX != 23) {
			if (time == 8) {
				time = 0;
				this.frameX++;
				if (this.frameX % this.COLS == 0) {
					this.frameX = 0;
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
		if (Main.isFaseTres() == 3
				&& (Main.getTimer().equals("00:00:01")
				|| Main.getTimer().equals("00:00:00"))) {
			if (posXCeleiro >= 499) {
				ativoCeleiro = true;
				posXCeleiro += velBack2;
			} else {
				velBack = 0;
				velBack2 = 0;
				this.velFront = 0;
				this.velMed = 0;
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
					(this.frameX * Constantes.CELEIRO_WIDTH), 0,
					(this.frameX * Constantes.CELEIRO_WIDTH)
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
