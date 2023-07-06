package Omo;

import java.awt.Graphics;
import java.awt.Image;
import java.util.List;

import Inimigos.Boss;
import Inimigos.Calca;
import Inimigos.Camisa;

public class Tiro extends Personagens {

	private Image spriteLimpo;

	private int xMouse; // Para aonde vai o tiro pos X.
	private int yMouse; // Para aonde vai o tiro pos Y.
	private boolean omo = true;

	public Tiro(Omo omo) {

		setSprite(Constantes.getInstance().loadImage(
				"Omo/imagens/Omo/Omo Shot animation.png"));
		spriteLimpo = (Constantes.getInstance()
				.loadImage("Omo/imagens/Omo/Omo Shot Vanish animation.png"));

		xMouse = Constantes.clickX + 37;
		yMouse = Constantes.clickY - 37;
		setX(omo.getX() + 100);
		setY(omo.getY() + 115);
	}

	public void update(List<Camisa> camisa, List<Calca> calca, Omo omo,
			Boss boss) {

		if (getTime() == 8 && isColidiu() && getFrameX() != 23) {
			setFrameX(getFrameX() + 1);
		} else if (getTime() == 8 && !isColidiu()) {
			setTime(0);
			setFrameX(getFrameX() + 1);

			if (getFrameX() % getCOLS() == 0) {
				setFrameX(0);
			}

		} else {
			setTime(getTime() + 1);
		}

		movimentacao();
		colisaoRoupas(camisa, calca, omo, boss);
	}

	public void movimentacao() {
		if (omo) {
			int novoX = (this.xMouse - getX());
			int novoY = (getY() - this.yMouse);
			double inteiro = novoX + novoY;
			if (inteiro == 0) {
				inteiro = 0.00001;
			}
			double refX = (novoX * 100) / inteiro;
			double porY = 100 - refX;
			int velX = (int) (100 - porY) / 5;
			int velY = (int) (100 - refX) / 5;
			this.xMouse += velX;
			this.yMouse -= velY;
			setX(getX() + velX);
			setY(getY() - velY);
		}
	}

	@Override
	public void draw(Graphics g) {
		if (isColidiu()) {
			g.drawImage(spriteLimpo, getX(), getY(), getX()
					+ Constantes.TIRO_WIDTH, getY() + Constantes.TIRO_HEIGHT,
					(getFrameX() * Constantes.TIRO_WIDTH), 0,
					(getFrameX() * Constantes.TIRO_WIDTH)
							+ Constantes.TIRO_WIDTH, Constantes.TIRO_HEIGHT,
					null);
		} else {
			g.drawImage(getSprite(), getX(), getY(), getX()
					+ Constantes.TIRO_WIDTH, getY() + Constantes.TIRO_HEIGHT,
					(getFrameX() * Constantes.TIRO_WIDTH), 0,
					(getFrameX() * Constantes.TIRO_WIDTH)
							+ Constantes.TIRO_WIDTH, Constantes.TIRO_HEIGHT,
					null);
		}
	}

	public void colisaoRoupas(List<Camisa> camisa, List<Calca> calca, Omo omo,
			Boss boss) {
		if (getX() < Constantes.getInstance().getWidth()
				&& getY() + 75 < Constantes.getInstance().getHeight()) {
			if (!boss.isInvencivel()
					&& getY() >= boss.getY()
					&&

					(getY() + 75) <= ((boss.getY() + boss.getSprite()
							.getHeight(null)))
					&&

					getX() >= (boss.getX())
					&&

					(getX() + 75) <= ((boss.getX() + boss.getSprite().getWidth(
							null)))) {
				boss.setColidiu(true);
				setColidiu(true);
				omo.setPontos(omo.getPontos() + 20);

			} else if (boss.getVida() <= 0) {
				boss.setColidiu(true);
				setColidiu(true);
				boss.setInvencivel(false);
			}

			for (int i = 0; i < camisa.size(); i++) {
				if ((getY()) >= camisa.get(i).getY()
						&&

						(getY() + 75) <= ((camisa.get(i).getY() + camisa.get(i)
								.getSprite().getHeight(null)))
						&&

						getX() >= (camisa.get(i).getX())
						&&

						(getX() + 75) <= ((camisa.get(i).getX() + camisa.get(i)
								.getSprite().getWidth(null)))
						&& !camisa.get(i).isColidiu()) {
					camisa.get(i).setColidiu(true);
					setColidiu(true);
					omo.setPontos(omo.getPontos() + 10);

				}
			}

			for (int i = 0; i < calca.size(); i++) {
				if (getY() >= calca.get(i).getY()
						&&

						(getY() + 75) <= ((calca.get(i).getY() + calca.get(i)
								.getSprite().getHeight(null)))
						&&

						getX()  >= (calca.get(i).getX())
						&&

						(getX() + 75) <= ((calca.get(i).getX() + calca.get(i)
								.getSprite().getWidth(null)))
						&& !calca.get(i).isColidiu()) {
					calca.get(i).setColidiu(true);
					setColidiu(true);
					omo.setPontos(omo.getPontos() + 15);

				}
			}

		}
	}
}
