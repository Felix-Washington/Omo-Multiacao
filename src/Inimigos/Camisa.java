package Inimigos;

import java.awt.Graphics;
import java.awt.Image;
import java.util.Random;

import Omo.Constantes;
import Omo.Omo;
import Omo.OmoMultiAcao;
import Omo.Personagens;

public class Camisa extends Personagens {

	// Usado para a animacao.

	private Random random;
	private Image spriteLimpo;
	private boolean removeuInimigo;
	private int velocidadeY;

	// Construtor
	public Camisa() {
		super();
		setSprite(Constantes.getInstance().loadImage(
				"Omo/imagens/Inimigos/Shirt sprite sheet.png"));
		spriteLimpo = Constantes.getInstance().loadImage(
				"Omo/imagens/Inimigos/Enemy Shirt CLEAN Sprite Sheet.png");
		random = new Random();
		int sorteio = random.nextInt(280) + 20;
		setX(Constantes.getInstance().getWidth());
		setY(sorteio);
		setVelocidade(-5);
		this.velocidadeY = 10;
	}

	// Atualizacao do jogo.
	public void update(Omo omo, Boss boss) {
		finalFase(boss);

		// Camisa subir quando morrer.
		if ((isColidiu() || OmoMultiAcao.isFimFase())) {
			omo.setColidiu(false);
			setY(getY() - velocidadeY);
		} else {
			setX(getX() + getVelocidade());
		}

		// Leitura de aproximacao da camisa ate o omo.
		if (omo.getSprite().getHeight(null) + 80 >= getX() && getY() > 20
				&& getY() <= 340) {
			setY(getY() - getVelocidade());
		}

		// Controle da animacao.
		// Se colidiu avanca o frame apenas se for diferente de 23 (o ultimo
		// frame)
		// getTime() usado para controlar a velocidade da animacao
		if (getTime() == 8 && isColidiu() && getFrameX() != 23) {
			setFrameX(getFrameX() + 1);
			// Se NAO colidiu avanca o frame por 1
		} else if (getTime() == 8 && !isColidiu()) {
			setFrameX(getFrameX() + 1);
			// se esta no ultimo frame set o frama para 0 para reiniciar a
			// animacao
			if (getFrameX() % getCOLS() == 0) {
				setFrameX(0);
			}
			// Se o time for diferente de 8 adiciona 1
		} else {
			setTime(getTime() + 1);
		}
		colisaoOmo(omo);
	}

	// Colisao da camiseta com o omo.
	public void colisaoOmo(Omo omo) {
		if (getX() <= omo.getX() + (omo.getSprite().getWidth(null) / 24) - 100
				&& getY() + (getSprite().getHeight(null)) <= omo.getY()
						+ omo.getSprite().getHeight(null) && !isColidiu()) {
			omo.setColidiu(true);
			if (!omo.isInvencivel() && omo.isColidiu() && !isColidiu()) {
				omo.setVida(omo.getVida() - 1);
				omo.setInvencivel(true);
			}

			setColidiu(true);

		}

	}

	public void finalFase(Boss boss) {
		if ((OmoMultiAcao.isFimFase() && !OmoMultiAcao.isFaseTres()) || boss.getVida() <= 0) {
			setColidiu(true);
		}
	}

	@Override
	public void draw(Graphics g) {
		if (isColidiu() || OmoMultiAcao.isFimFase()) {
			g.drawImage(spriteLimpo, getX(), getY(),
					Constantes.CAMISA_VOANDO_WIDTH + getX(),
					Constantes.CAMISA_VOANDO_HEIGHT + getY(),
					(getFrameX() * Constantes.CAMISA_VOANDO_WIDTH), 0,
					(getFrameX() * Constantes.CAMISA_VOANDO_WIDTH)
							+ Constantes.CAMISA_VOANDO_WIDTH,
					Constantes.CAMISA_VOANDO_HEIGHT, null);

		} else {
			g.drawImage(getSprite(), getX(), getY(),
					Constantes.CAMISA_VOANDO_WIDTH + getX(),
					Constantes.CAMISA_VOANDO_HEIGHT + getY(),
					(getFrameX() * Constantes.CAMISA_VOANDO_WIDTH), 0,
					(getFrameX() * Constantes.CAMISA_VOANDO_WIDTH)
							+ Constantes.CAMISA_VOANDO_WIDTH,
					Constantes.CAMISA_VOANDO_HEIGHT, null);
		}
	}

	public boolean isRemoveuInimigo() {
		return removeuInimigo;
	}

	public void setRemoveuInimigo(boolean removeuInimigo) {
		this.removeuInimigo = removeuInimigo;
	}

}
