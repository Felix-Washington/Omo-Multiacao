package inimigos;

import java.awt.Graphics;

import personagem.Omo;
import omo.Main;

public class Calca extends Inimigos {

	// Construtor.
	public Calca() {
		super();
		sprite_dirty = load_dirty_image("images/Inimigos/Enemy Pants Sprite sheet.png");
		sprite_clean = load_clean_image("images/Inimigos/Enemy Pants CLEAN Sprite Sheet.png");
		int sorteio = random.nextInt(280) + 20;
		this.x = getWidth();
		this.y = sorteio;
		this.enemy_width = 92;
		this.enemy_height = 140;
		this.velocityX = -3;
		this.velocityY = 10;
	}

	public void update(Omo omo, Boss boss) {
		finalFase(boss);
		if ((!this.alive || Main.isFimFase())) {
			this.y = getHeight() - this.velocityX;
		} else {
			this.x = getWidth() - this.velocityY;
		}

		// Leitura de aproximacao da camisa ate o omo.
		if (omo.getSprite().getHeight(null) + 80 >= this.x && this.y > 20 && this.y <= 340) {
			this.y -= this.velocityX;
		}

		// Controle da animacao.
		// Se colidiu avanca o frame apenas se for diferente de 23 (o ultimo
		// frame)
		// getTime() usado para controlara a velocidade da animacao
		if (this.time == 8 && !this.alive && this.frameX != 23) {
			this.frameX += 1;
			// Se NAO colidiu avanca o frame por 1
		} else if (this.time == 8 && this.alive) {
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

	public void finalFase(Boss boss) {
		if ((Main.isFimFase() && Main.isFaseTres() == 3) || boss.getLife() <= 0) {
			this.alive = false;
		}
	}

	@Override
	public void draw(Graphics g) {
		if (!this.alive) {
			g.drawImage(this.sprite_clean, this.x, this.y, this.enemy_width + this.x,
					this.enemy_height + this.y, (this.frameX * this.enemy_width), 0,
					(this.frameX * this.enemy_width) + this.enemy_width,
					this.enemy_height, null);
		} else {
			g.drawImage(this.sprite_dirty, this.x, getY(), this.enemy_width + this.x,
					this.enemy_height + this.y, (this.frameX * this.enemy_width), 0,
					(this.frameX * this.enemy_width) + this.enemy_width,
					this.enemy_height, null);
		}
	}

	public void colisaoOmo(Omo omo) {
		if (this.x <= omo.getX() + (omo.getSprite().getWidth(null) - 100) / 24
				&& this.y + (this.sprite_dirty.getHeight(null)) <= omo.getY() + omo.getSprite().getHeight(null)
				&& this.alive) {
			if (!omo.isInvencivel()) {
				omo.setLife(omo.getLife() - 1);
				omo.setInvencivel(true);
			}
			this.alive = false;
			omo.setColidiu(true);
		} else {
			omo.setColidiu(false);
		}

	}
	
	public int getEnemy_height() {
		return enemy_height;
	}

}
