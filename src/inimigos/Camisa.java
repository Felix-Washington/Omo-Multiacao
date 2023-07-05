package inimigos;

import java.awt.Graphics;
import personagem.Omo;

public class Camisa extends Inimigos {

	// Construtor
	public Camisa() {
		super();
		this.sprite_dirty = loadImage("images/Inimigos/Shirt sprite sheet.png");
		this.sprite_clean = loadImage("images/Inimigos/Enemy Shirt CLEAN Sprite Sheet.png");
		int sorteio = random.nextInt(280) + 20;
		this.x = getWidth();
		this.y = sorteio;
		this.velocityX = -5;
		this.velocityY = 10;
		this.enemy_width = 153;
		this.enemy_height = 132;
		this.alive = true;
	}

	// Atualizacao do jogo.
	public void update(Omo omo, Boss boss) {
		// Leitura de aproximacao da camisa ate o omo.
		if (omo.getOmo_images().get(0).getHeight(null) + 80 >= this.x && this.y > 20
				&& this.y <= 340) {
			this.y -= this.velocityX;
		}

		// Controle da animacao.
		// Se colidiu avanca o frame apenas se for diferente de 23 (o ultimo
		// frame)
		// getTime() usado para controlar a velocidade da animacao
		if (this.time == 8 && this.alive && this.frameX != 23) {
			this.frameX += 1;
			// Se NAO colidiu avanca o frame por 1
		} else if (this.time == 8 && !this.alive) {
			this.frameX += 1;
			// se esta no ultimo frame set o frama para 0 para reiniciar a
			// animacao
			if (this.frameX % this.COLS == 0) {
				this.frameX = 0;
			}
			// Se o time for diferente de 8 adiciona 1
		} else {
			this.time += 1;
		}
		colisaoOmo(omo);
	}

	// Colisao da camiseta com o omo.
	public void colisaoOmo(Omo omo) {
		if (this.x <= omo.getX() + (omo.getOmo_images().get(0).getWidth(null) / 24) - 100
				&& this.y + (this.sprite_dirty.getHeight(null)) <= omo.getY()
						+ omo.getOmo_images().get(0).getHeight(null) && !this.alive) {
			omo.setColidiu(true);
			if (!omo.isInvencivel() && omo.isColidiu() && !this.alive) {
				omo.setLife(omo.getLife() - 1);
				omo.setInvencivel(true);
			}

			this.alive = false;

		}

	}

	@Override
	public void draw(Graphics g) {
		if (this.alive) {
			g.drawImage(this.sprite_dirty, this.x, this.y,
					this.enemy_width + this.x,
					this.enemy_height + this.y,
					(this.frameX * this.enemy_width), 0,
					(this.frameX * this.enemy_width)
							+ this.enemy_width,
							this.enemy_height, null);
		} else {
			g.drawImage(this.sprite_clean, this.x, this.y,
					this.enemy_width + this.x,
					this.enemy_height + this.y,
					(this.frameX * this.enemy_width), 0,
					(this.frameX * this.enemy_width)
							+ this.enemy_width,
							this.enemy_height, null);

		} 
	}


}
