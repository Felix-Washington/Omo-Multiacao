package Inimigos;

import java.awt.Graphics;
import java.awt.Image;

import Omo.Constantes;
import Omo.Omo;
import Omo.Personagens;

public class roupaChao extends Personagens {

	private Image sprite2;

	private int xSprite2;
	private int ySprite2;

	// Construtor.
	public roupaChao() {
		setSprite(Constantes.getInstance().loadImage(
				"Omo/imagens/Inimigos/Pile of Clothes.png"));
		sprite2 = (Constantes.getInstance()
				.loadImage("Omo/imagens/Inimigos/Stink Lines Sprite Sheet.png"));
		setX(Constantes.getInstance().getWidth());
		setVelocidade(-15);
		setY(500);
		xSprite2 = getX();
		ySprite2 = getY() - (sprite2.getHeight(null) - 20);
		setContador(getContador() +  1);
	}

	public void update(Omo omo) {
		setX(getX() + getVelocidade());
		xSprite2 += getVelocidade();

		if (getTime() == 2) {
			setTime(0);
			setFrameX(getFrameX() + 1);

			if (getFrameX() % getCOLS() == 0) {
				setFrameX(0);
			}
		} else {
			setTime(getTime() + 1);
		}

		if (omo.isColidiu()) {
			if (omo.isPulo()) {
				omo.setPulo(false);
			}
		}

		colisaoOmo(omo);
	}

	// Colisao das roupas no chão com o omo.
	public void colisaoOmo(Omo omo) {
		if (getX()+10 <= omo.getX() + (omo.getSprite().getWidth(null)) / 24
				&& getX()+10 + getSprite().getWidth(null) <= omo.getX() + (omo.getSprite().getWidth(null)) / 24
				&& getY() + getSprite().getHeight(null) <= omo.getY()
						+ omo.getSprite().getHeight(null)
				&& omo.isInvencivel() == false) {
			if (omo.isInvencivel()) {
				omo.setColidiu(true);
			} else {
				omo.setVida(omo.getVida() - 1);
				omo.setColidiu(true);
				omo.setInvencivel(true);
			}
		}

	}

	@Override
	public void draw(Graphics g) {
		g.drawImage(getSprite(), getX(), getY(), null);
		g.drawImage(sprite2, xSprite2, ySprite2, Constantes.ROUPA_CHAO_WIDTH
				+ xSprite2, Constantes.ROUPA_CHAO_HEIGHT + ySprite2,
				(getFrameX() * Constantes.ROUPA_CHAO_WIDTH), 0,
				(getFrameX() * Constantes.ROUPA_CHAO_WIDTH)
						+ Constantes.ROUPA_CHAO_WIDTH,
				Constantes.ROUPA_CHAO_HEIGHT, null);
		setContador(getContador() + 1);
	}

}
