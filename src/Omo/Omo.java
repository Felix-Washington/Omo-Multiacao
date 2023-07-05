package Omo;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.util.List;

public class Omo extends Personagens {
	// Imagens usadas na classe garrafa.
	private Image spriteVida;
	private Image spriteVida1;
	private Image spriteVida2;
	private Image spriteVida3;
	private Image spriteInvencivel;
	private Image spritePontos;

	// Variaveis auxiliares.
	private boolean invencivel;
	private boolean removeuInimigo;
	private int pontos;
	private int time2;
	private boolean pulo;
	private int controlePulo;

	// Construtor
	public Omo() {
		// Inicializando as imagens.
		setSprite(Constantes.getInstance().loadImage(
				"Omo/imagens/Omo/omo sprite sheet.png"));
		spriteVida = Constantes.getInstance().loadImage(
				"Omo/imagens/Omo/Life Bar (Empty).png");
		spriteVida1 = Constantes.getInstance().loadImage(
				"Omo/imagens/Omo/Life Bar (1 Heart).png");
		spriteVida2 = Constantes.getInstance().loadImage(
				"Omo/imagens/Omo/Life Bar (2 Heart).png");
		spriteVida3 = Constantes.getInstance().loadImage(
				"Omo/imagens/Omo/Life Bar (Full).png");
		spriteInvencivel = Constantes.getInstance().loadImage(
				"Omo/imagens/Omo/Omo Man Invincible Sprite Sheet.png");
		spritePontos = Constantes.getInstance().loadImage(
				"Omo/imagens/Omo/Points Bar.png");

		setX(0);
		setY(getSprite().getHeight(null) + 30);
		setVida(3);
		setVelocidade(-8);
	}

	public void update(List<Garrafa> garrafa) {
		if (OmoMultiAcao.isFimFase() && getX() <= 400
				&& !OmoMultiAcao.isFaseTres()) {
			setX(getX() - getVelocidade());
		}
		if (getTime() == 3) {
			setTime(0);
			setFrameX(getFrameX() + 1);
			if (getFrameX() % getCOLS() == 0) {
				setFrameX(0);
			}
			if (pulo) {
				pular();
			}
		} else {
			setTime(getTime() + 1);
		}
		invencivel();
		colisaoGarrafa(garrafa);
	}

	@Override
	public void draw(Graphics g) {
		// Desenhar personagem.
		if (invencivel) {
			g.drawImage(spriteInvencivel, getX(), getY(),
					Constantes.OMO_VOANDO_WIDTH + getX(),
					Constantes.OMO_VOANDO_HEIGHT + getY(),
					(getFrameX() * Constantes.OMO_VOANDO_WIDTH), 0,
					(Constantes.OMO_VOANDO_WIDTH * getFrameX())
							+ Constantes.OMO_VOANDO_WIDTH,
					Constantes.OMO_VOANDO_HEIGHT, null);
		} else {
			g.drawImage(getSprite(), getX(), getY(),
					Constantes.OMO_VOANDO_WIDTH + getX(),
					Constantes.OMO_VOANDO_HEIGHT + getY(),
					(getFrameX() * Constantes.OMO_VOANDO_WIDTH), 0,
					(Constantes.OMO_VOANDO_WIDTH * getFrameX())
							+ Constantes.OMO_VOANDO_WIDTH,
					Constantes.OMO_VOANDO_HEIGHT, null);
		}

		// Desenhar barra de vidas.
		switch (getVida()) {
		case 0:
			g.drawImage(spriteVida, 0, 0, null);
			break;
		case 1:
			g.drawImage(spriteVida1, 0, 0, null);
			break;
		case 2:
			g.drawImage(spriteVida2, 0, 0, null);
			break;
		case 3:
			g.drawImage(spriteVida3, 0, 0, null);
			break;
		}

		g.drawImage(spritePontos, 213, 0, null);
		g.setColor(Color.WHITE);
		g.setFont(new Font("Arial", Font.BOLD, 38));
		g.drawString("Pontos: ", 225, 50);
		if (pontos >= 0 && pontos <= 9) {
			g.drawString(String.valueOf(pontos), 415, 50);
		} else if (pontos >= 10 && pontos <= 99) {
			g.drawString(String.valueOf(pontos), 405, 50);
		} else if (pontos >= 100 && pontos <= 999) {
			g.drawString(String.valueOf(pontos), 385, 50);
		} else {
			g.drawString(String.valueOf(pontos), 365, 50);
		}
	}

	public void invencivel() {
		if (isInvencivel()) {
			if (time2 >= 150) {
				time2 = 0;
				setInvencivel(false);
				setRemoveuInimigo(false);
			} else {
				time2++;
			}
		}

	}

	public void pular() {
		if (controlePulo < 7) {
			setY(getY() - 35);
		} else if (controlePulo > 7 && controlePulo < 15) {
			setY(getY() + 35);
		}
		controlePulo++;

		if (controlePulo == 15) {
			pulo = false;
			controlePulo = 0;
		}
	}
	
	public void colisaoGarrafa(List<Garrafa> garrafa) {
		for (int i = 0; i < garrafa.size(); i++) {
			if (getX() >= garrafa.get(i).getX() 
				&&
				(getY() + getSprite().getHeight(null) >= garrafa.get(i).getY() ))
				{
				garrafa.get(i).setColidiu(true);
				if (getVida() < 3) {
					setVida(getVida() + 1);
				} else {
					setPontos(getPontos() + 25);
				}
			}
		}
	}

	// Getters e Setters.
	public int getPontos() {
		return pontos;
	}

	public void setPontos(int pontos) {
		this.pontos = pontos;
	}

	public boolean isInvencivel() {
		return invencivel;
	}

	public void setInvencivel(boolean invencivel) {
		this.invencivel = invencivel;
	}

	public boolean isPulo() {
		return pulo;
	}

	public void setPulo(boolean pulo) {
		this.pulo = pulo;
	}

	public boolean isRemoveuInimigo() {
		return removeuInimigo;
	}

	public void setRemoveuInimigo(boolean removeuInimigo) {
		this.removeuInimigo = removeuInimigo;
	}

}
