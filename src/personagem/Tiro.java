package personagem;

import java.awt.Graphics;
import java.awt.Image;
import java.util.List;

import inimigos.Boss;
import inimigos.Calca;
import inimigos.Camisa;
import omo.Constantes;
import omo.Personagens;

public class Tiro extends Personagens {

	//private Image sprite;
	private Image clean_sprite;

	private int xMouse; // Para aonde vai o tiro pos X.
	private int yMouse; // Para aonde vai o tiro pos Y.
	private boolean omo = true;

	public Tiro(Omo omo) {

		this.sprite = (Constantes.getInstance().loadImage(
				"images/Omo/Omo Shot animation.png"));
		clean_sprite = (Constantes.getInstance()
				.loadImage("images/Omo/Omo Shot Vanish animation.png"));

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
			g.drawImage(clean_sprite, getX(), getY(), getX()
					+ Constantes.TIRO_WIDTH, getY() + Constantes.TIRO_HEIGHT,
					(getFrameX() * Constantes.TIRO_WIDTH), 0,
					(getFrameX() * Constantes.TIRO_WIDTH)
							+ Constantes.TIRO_WIDTH, Constantes.TIRO_HEIGHT,
					null);
		} else {
			g.drawImage(this.sprite, getX(), getY(), getX()
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

			} else if (boss.getLife() <= 0) {
				boss.setColidiu(true);
				setColidiu(true);
				boss.setInvencivel(false);
			}

			for (int i = 0; i < camisa.size(); i++) {
				if ((getY()) >= camisa.get(i).getY()
						&&

						(getY() + 75) <= ((camisa.get(i).getY() + camisa.get(i)
								.getSprite_dirty().getHeight(null)))
						&&

						getX() >= (camisa.get(i).getX())
						&&

						(getX() + 75) <= ((camisa.get(i).getX() + camisa.get(i)
								.getSprite_dirty().getWidth(null)))
						&& camisa.get(i).isAlive()) {
					
					camisa.get(i).setAlive(false);;
					setColidiu(true);
					omo.setPontos(omo.getPontos() + 10);

				}
			}

			for (int i = 0; i < calca.size(); i++) {
				if (getY() >= calca.get(i).getY()
						&&

						(getY() + 75) <= ((calca.get(i).getY() + calca.get(i)
								.getSprite_dirty().getHeight(null)))
						&&

						getX()  >= (calca.get(i).getX())
						&&

						(getX() + 75) <= ((calca.get(i).getX() + calca.get(i)
								.getSprite_dirty().getWidth(null)))
						&& !calca.get(i).isAlive()) {
					calca.get(i).setAlive(false);
					setColidiu(true);
					omo.setPontos(omo.getPontos() + 15);

				}
			}

		}
	}
}
