package niveis;

import java.awt.Graphics;
import java.awt.Image;
import java.util.Random;

import omo.Main;

public class Nivel_dois extends Nivel_pai{

	// Imagens usadas para a fase2;
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
	private int velMed2;
	private int velBack;

	private boolean ativoBandeira;
	private int time;

	private boolean ativoNuvem;

	// Construtor;
	public Nivel_dois() {

		// Inicializa as imagens;
		this.spriteFront = loadImage("images/niveis/Fase2/Foreground (Stage 2).png");		
		this.spriteFront2 = loadImage("images/niveis/Fase2/Foreground (Stage 2).png");
		ceu = loadImage("images/niveis/Fase2/Scene (Stage 2).png");
		spriteFlor = loadImage("images/niveis/Fase2/Flower (Stage 2).png");
		spriteNuvemPequena = loadImage("images/niveis/Fase2/Clouds 2 (Small).png");
		spriteNuvemGrande = loadImage("images/niveis/Fase2/Clouds 1 (big).png");
		spriteArvore = loadImage("images/niveis/Fase2/Tree (Small).png");
		this.bandeira = loadImage("images/niveis/Flag Animation.png");

		// Velocidades dos cenarios.
		this.velFront = -15;
		this.velMed = -3;
		velMed2 = -5;
		velBack = -1;

		// Posicoes das variaveis que movimentam o chao.
		this.posXFront = 0;
		this.posXFront2 = getWidth();

		this.posXFlor = getWidth();
		this.posXArvore = getWidth();
		this.posXNuvemGrande = getWidth();
		this.posXNuvemPequena = getWidth();
		this.posXBandeira = getWidth();
		Main.setFimFase(false);
		this.COLS = 23;
		this.score_to_win = 200;

		this.random = new Random();

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

		posXFront += this.velFront;
		posXFront2 += this.velFront;

		posXArvore += this.velMed;
		posXFlor += velMed2;
		posXNuvemGrande += velBack;
		posXNuvemPequena += velBack;

		if (this.posXFront + getWidth() - 11 <= 0) {

			posXFront = getWidth();
		} else if (this.posXFront2 + getWidth() - 11 <= 0) {

			posXFront2 = getWidth();
		}

		if (this.posXFlor + getWidth() <= 0) {
			posXFlor = getWidth()
					+ (this.random.nextInt(70) + 10);
			// ativo = false;
		}

		if (this.posXArvore + getWidth() <= 0) {
			posXArvore = getWidth()
					+ (this.random.nextInt(100) + 10);
			// ativo = false;
		}

		if (this.posXNuvemGrande + getWidth() <= 0) {
			posXNuvemGrande = getWidth()
					+ (this.random.nextInt(150) + 100);
			// ativo2 = false;
		}

		if (this.posXNuvemPequena + getWidth() <= 0) {
			posXNuvemPequena = getWidth();
			// ativo2 = false;
		}

		if (posXNuvemGrande + spriteNuvemGrande.getWidth(null) <= 0
				|| posXNuvemPequena + spriteNuvemPequena.getWidth(null) <= 0) {
			ativoNuvem = false;
		} else {
			int sorteio = this.random.nextInt(1);

			if (sorteio == 0) {
				ativoNuvem = true;
			}
		}

	}

	public void bandeira() {
		if (Main.isFimFase()) {

			if (this.posXBandeira >= 496) {
				ativoBandeira = true;
				this.posXBandeira += this.velFront;
			} else if (this.posXBandeira <= 512) {
				this.velFront = 0;
				this.velMed = 0;
				velBack = 0;
				velMed2 = 0;
			}
		}
	}

	// Metodo para desenhar o cenario.
	public void draw(Graphics g) {

		g.drawImage(ceu, 0, 0, null);

		if (ativoBandeira) {
			g.drawImage(bandeira, this.posXBandeira,
					573 - getFlagH(), this.posXBandeira
							+ getFlagW(), 573
							- getFlagH()
							+ getFlagH(),
					(this.frameX * getFlagW()), 0,
					(this.frameX * getFlagW())
							+ getFlagW(),
							getFlagH(), null);
		}

		if (ativoNuvem) {
			int sorteio = this.random.nextInt(1);

			if (sorteio == 0 && ativoNuvem) {

				g.drawImage(spriteNuvemGrande, posXNuvemGrande, 20, null);
				ativoNuvem = true;
			} else if (sorteio == 1 && ativoNuvem) {

				g.drawImage(spriteNuvemPequena, posXNuvemPequena, 20, null);
				ativoNuvem = true;
			}
		}

		g.drawImage(spriteArvore, posXArvore, getHeight() - spriteArvore.getHeight(null), null);
		g.drawImage(spriteFlor, posXFlor, getHeight()
				- spriteFlor.getHeight(null) - 35, null);

		g.drawImage(spriteFront, posXFront, getHeight() - spriteFront.getHeight(null), null);

		g.drawImage(spriteFront2, posXFront2, getHeight() - spriteFront.getHeight(null), null);

	}

}
