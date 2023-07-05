package inimigos;

import java.awt.Graphics;
import java.awt.Image;

import omo.Constantes;
import personagem.Omo;
import omo.Personagens;

public class Boss extends Personagens {

	// Usado para a animacao.

	private Image spriteInvencivel;
	private Image spriteMorto;
	private boolean removeuInimigo;
	private int velocidadeY;
	private int vida;
	private boolean Invencivel;
	private int timer;

	// Construtor
	public Boss() {
		super();
		setSprite(Constantes.getInstance().loadImage(
				"images/Inimigos/Omo Boss Sprite Sheet.png"));
		spriteInvencivel = Constantes.getInstance().loadImage(
				"images/Inimigos/Omo Boss Invincible Sprite Sheet.png");
		spriteMorto = Constantes.getInstance().loadImage(
				"images/Inimigos/Omo Boss Vanish Sprite Sheet.png");
		// random = new Random();
		// int sorteio = random.nextInt(280) + 20;
		
		setLife(5);
		setX(Constantes.getInstance().getWidth());
		setY(100);
		setVelocidade(-5);
		setVelocidadeY(3);
	}

	// Atualizacao do jogo.
	public void update(Omo omo) {
		// Camisa subir quando morrer.
		if (getX() >= 700) {
			setX(getX() + getVelocidade());
		}

		if (getLife() > 0) {
			setY(getY() + getVelocidadeY());
		}

		if (isColidiu() && getLife() > 0) {
			omo.setColidiu(false);
			setLife(getLife() - 1);
			setInvencivel(true);
			setColidiu(false);
		} else if ((getY() <= 50 || getY() >= Constantes.getInstance()
				.getHeight() - 300) && getLife() > 0) {
			setVelocidadeY(getVelocidadeY() * -1);
		}

		// Controle da animacao.
		if (getTime() == 4 && getLife() <= 0 && getFrameX() != 23) {
			setFrameX(getFrameX() + 1);

		} else if (getTime() == 4 && getLife() > 0) {
			setTime(0);
			setFrameX(getFrameX() + 1);

			if (getFrameX() % getCOLS() == 0) {
				setFrameX(0);
			}

		} else {
			setTime(getTime() + 1);
		}

		invencivel();
	}

	public void invencivel() {
		if (isInvencivel()) {
			if (getTimer() >= 150) {
				setTimer(0);
				setInvencivel(false);
				setRemoveuInimigo(false);
			} else {
				setTimer(getTimer() + 1);
			}
		}

	}

	@Override
	public void draw(Graphics g) {
		if (isInvencivel()) {
			g.drawImage(spriteInvencivel, getX(), getY(),
					getSprite().getWidth(null) / getCOLS() + getX(),
					getSprite().getHeight(null) + getY(), (getFrameX()
							* getSprite().getWidth(null) / getCOLS()), 0,
					(getFrameX() * getSprite().getWidth(null) / getCOLS())
							+ getSprite().getWidth(null) / getCOLS(),
					getSprite().getHeight(null), null);

		} else if (getLife() <= 0) {
			g.drawImage(spriteMorto, getX(), getY(), getSprite().getWidth(null)
					/ getCOLS() + getX(), getSprite().getHeight(null) + getY(),
					(getFrameX() * getSprite().getWidth(null) / getCOLS()), 0,
					(getFrameX() * getSprite().getWidth(null) / getCOLS())
							+ getSprite().getWidth(null) / getCOLS(),
					getSprite().getHeight(null), null);
		} else {
			g.drawImage(getSprite(), getX(), getY(), getSprite().getWidth(null)
					/ getCOLS() + getX(), getSprite().getHeight(null) + getY(),
					(getFrameX() * getSprite().getWidth(null) / getCOLS()), 0,
					(getFrameX() * getSprite().getWidth(null) / getCOLS())
							+ getSprite().getWidth(null) / getCOLS(),
					getSprite().getHeight(null), null);
			// setContador(getContador() + 1);
		}
	}

	public int getVelocidadeY() {
		return velocidadeY;
	}

	public void setVelocidadeY(int velocidadeY) {
		this.velocidadeY = velocidadeY;
	}

	public boolean isInvencivel() {
		return Invencivel;
	}

	public void setInvencivel(boolean invencivel) {
		Invencivel = invencivel;
	}

	public int getTimer() {
		return timer;
	}

	public void setTimer(int timer) {
		this.timer = timer;
	}

	public boolean isRemoveuInimigo() {
		return removeuInimigo;
	}

	public void setRemoveuInimigo(boolean removeuInimigo) {
		this.removeuInimigo = removeuInimigo;
	}

}
