package Omo.Fases;

import java.awt.Graphics;
import java.awt.Image;
import java.util.Random;

import Omo.Constantes;
import Omo.OmoMultiAcao;

public class Fase2 {

	// Imagens usadas para a fase2;
	private Image spriteFront;
	private Image spriteFront2;

	private Image spriteNuvemGrande;
	private Image spriteNuvemPequena;
	private Image spriteFlor;
	private Image spriteArvore;
	private Image ceu;

	// Variaveis que definem o x das 2 perspectivas
	private int posXFront;
	private int posXFront2;
	private int posXFlor;
	private int posXArvore;
	private int posXNuvemGrande;
	private int posXNuvemPequena;

	// Variaveis que definem a velocidade das 2 perspectivas;
	private int velFront;
	private int velMed;
	private int velMed2;
	private int velBack;

	private int posXBandeira;

	private Image bandeira;
	private int COLS;
	private int frameX;
	private boolean ativoBandeira;
	private int time;
	private Random random;

	private boolean ativoNuvem;

	// Construtor;
	public Fase2() {

		// Inicializa as imagens;
		spriteFront = Constantes.getInstance().loadImage(
				"Omo/Fases/imagens/Fase2/Foreground (Stage 2).png");
		spriteFront2 = Constantes.getInstance().loadImage(
				"Omo/Fases/imagens/Fase2/Foreground (Stage 2).png");

		ceu = Constantes.getInstance().loadImage(
				"Omo/Fases/imagens/Fase2/Scene (Stage 2).png");

		spriteFlor = Constantes.getInstance().loadImage(
				"Omo/Fases/imagens/Fase2/Flower (Stage 2).png");

		spriteNuvemPequena = Constantes.getInstance().loadImage(
				"Omo/Fases/imagens/Fase2/Clouds 2 (Small).png");

		spriteNuvemGrande = Constantes.getInstance().loadImage(
				"Omo/Fases/imagens/Fase2/Clouds 1 (big).png");

		spriteArvore = Constantes.getInstance().loadImage(
				"Omo/Fases/imagens/Fase2/Tree (Small).png");

		bandeira = Constantes.getInstance().loadImage(
				"Omo/Fases/imagens/Flag Animation.png");

		// Velocidades dos cenarios.
		velFront = -15;
		velMed = -3;
		velMed2 = -5;
		velBack = -1;

		// Posicoes das variaveis que movimentam o chao.
		this.posXFront = 0;
		this.posXFront2 = Constantes.getInstance().getWidth();

		this.posXFlor = Constantes.getInstance().getWidth();
		this.posXArvore = Constantes.getInstance().getWidth();
		this.posXNuvemGrande = Constantes.getInstance().getWidth();
		this.posXNuvemPequena = Constantes.getInstance().getWidth();
		posXBandeira = Constantes.getInstance().getWidth();
		OmoMultiAcao.setFimFase(false);
		COLS = 23;

		random = new Random();

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

		posXArvore += velMed;
		posXFlor += velMed2;
		posXNuvemGrande += velBack;
		posXNuvemPequena += velBack;

		if (this.posXFront + Constantes.getInstance().getWidth() - 11 <= 0) {

			posXFront = Constantes.getInstance().getWidth();
		} else if (this.posXFront2 + Constantes.getInstance().getWidth() - 11 <= 0) {

			posXFront2 = Constantes.getInstance().getWidth();
		}

		if (this.posXFlor + Constantes.getInstance().getWidth() <= 0) {
			posXFlor = Constantes.getInstance().getWidth()
					+ (random.nextInt(70) + 10);
			// ativo = false;
		}

		if (this.posXArvore + Constantes.getInstance().getWidth() <= 0) {
			posXArvore = Constantes.getInstance().getWidth()
					+ (random.nextInt(100) + 10);
			// ativo = false;
		}

		if (this.posXNuvemGrande + Constantes.getInstance().getWidth() <= 0) {
			posXNuvemGrande = Constantes.getInstance().getWidth()
					+ (random.nextInt(150) + 100);
			// ativo2 = false;
		}

		if (this.posXNuvemPequena + Constantes.getInstance().getWidth() <= 0) {
			posXNuvemPequena = Constantes.getInstance().getWidth();
			// ativo2 = false;
		}

		if (posXNuvemGrande + spriteNuvemGrande.getWidth(null) <= 0
				|| posXNuvemPequena + spriteNuvemPequena.getWidth(null) <= 0) {
			ativoNuvem = false;
		} else {
			int sorteio = random.nextInt(1);

			if (sorteio == 0) {
				ativoNuvem = true;
			}
		}

	}

	public void bandeira() {
		if (OmoMultiAcao.isFimFase()) {

			if (posXBandeira >= 496) {
				ativoBandeira = true;
				posXBandeira += velFront;
			} else if (posXBandeira <= 512) {
				velFront = 0;
				velMed = 0;
				velBack = 0;
				velMed2 = 0;
			}
		}
	}

	// Metodo para desenhar o cenario.
	public void draw(Graphics g) {

		g.drawImage(ceu, 0, 0, null);

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

		if (ativoNuvem) {
			int sorteio = random.nextInt(1);

			if (sorteio == 0 && ativoNuvem) {

				g.drawImage(spriteNuvemGrande, posXNuvemGrande, 20, null);
				ativoNuvem = true;
			} else if (sorteio == 1 && ativoNuvem) {

				g.drawImage(spriteNuvemPequena, posXNuvemPequena, 20, null);
				ativoNuvem = true;
			}
		}

		g.drawImage(spriteArvore, posXArvore, Constantes.getInstance()
				.getHeight() - spriteArvore.getHeight(null), null);
		g.drawImage(spriteFlor, posXFlor, Constantes.getInstance().getHeight()
				- spriteFlor.getHeight(null) - 35, null);

		g.drawImage(spriteFront, posXFront, Constantes.getInstance()
				.getHeight() - spriteFront.getHeight(null), null);

		g.drawImage(spriteFront2, posXFront2, Constantes.getInstance()
				.getHeight() - spriteFront.getHeight(null), null);

	}

}
