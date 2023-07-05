package personagem;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;
import java.util.List;

import omo.Constantes;
import omo.Personagens;

public class Omo extends Personagens {
	// Variaveis auxiliares.
	private boolean invencivel;
	private boolean removeuInimigo;
	private int pontos;
	private int time2;
	private boolean pulo;
	private int controlePulo;
	
	private List<Image> omo_images;

	// Construtor
	public Omo() {
		// Inicializando as imagens.
		omo_images = new ArrayList<Image>();
		for (int i = 0; i < 7; i++) {			
			Image image = image_path(i);			
			omo_images.add(image);
		}

		this.x = 0;
		this.y = omo_images.get(0).getHeight(null) + 30;
		this.entity_width = 247;
		this.entity_height = 274; 
		
		this.life = 3;
		this.velocidade = -8;
	}
	
	public Image image_path(int image_chosen) {
		String path = "";
		switch (image_chosen) {
		case 0:
			path = "images/Omo/omo sprite sheet.png";
			break;
		case 1:
			path = "images/Omo/Omo Man Invincible Sprite Sheet.png";
			break;
		case 2:
			path = "images/Omo/Points Bar.png";
			break;
		case 3:
			path = "images/Omo/Life Bar (Empty).png)";
			break;
		case 4:
			path = "images/Omo/Life Bar (1 Heart).png";
			break;
		case 5:
			path = "images/Omo/Life Bar (2 Heart).png";
			break;
		case 6:
			path = "images/Omo/Life Bar (Full).png";
			break;			
		default:
			break;
		}
		Image image = Constantes.getInstance().loadImage(path);
		return image;
	}
	
	public void update() {
		if (getTime() == 3) {
			setTime(0);
			this.frameX ++;
			if (this.frameX % getCOLS() == 0) {
				setFrameX(0);
			}
			if (pulo) {
				pular();
			}
		} else {
			setTime(getTime() + 1);
		}
		invencivel();
	}

	@Override
	public void draw(Graphics g) {
		
		// Desenhar personagem.
		Image omo_image;
		if (invencivel) {
			omo_image = this.omo_images.get(1);
		} else {
			omo_image = this.omo_images.get(0);
		}
		
		g.drawImage(omo_image, this.x, this.y,
				this.entity_width + this.x,
				this.entity_height + this.y,
				(this.frameX * this.entity_width), 0,
				(this.entity_width * this.frameX)
						+ this.entity_width,
				this.entity_height, null);		

		// Desenhar barra de vidas.
		switch (this.life) {
		case 0:
			g.drawImage(this.omo_images.get(3), 0, 0, null);
			break;
		case 1:
			g.drawImage(this.omo_images.get(4), 0, 0, null);
			break;
		case 2:
			g.drawImage(this.omo_images.get(5), 0, 0, null);
			break;
		case 3:
			g.drawImage(this.omo_images.get(6), 0, 0, null);
			break;
		}

		g.drawImage(omo_images.get(2), 213, 0, null);
		g.setColor(Color.WHITE);
		g.setFont(new Font("Arial", Font.BOLD, 38));
		g.drawString("Pontos: ", 225, 50);
		int draw_x;
		if (pontos >= 0 && pontos <= 9) {
			draw_x = 415;
		} else if (pontos >= 10 && pontos <= 99) {
			draw_x = 405;
		} else if (pontos >= 100 && pontos <= 999) {
			draw_x = 385;
		} else {
			draw_x = 365;
		}
		
		g.drawString(String.valueOf(pontos), draw_x, 50);
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
			this.y -= 35;
		} else if (controlePulo > 7 && controlePulo < 15) {
			this.y += 35;
		}
		controlePulo++;

		if (controlePulo == 15) {
			pulo = false;
			controlePulo = 0;
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

	public List<Image> getOmo_images() {
		return omo_images;
	}

	public void setOmo_images(List<Image> omo_images) {
		this.omo_images = omo_images;
	}

	

}
