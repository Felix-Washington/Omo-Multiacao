package inimigos;

import java.awt.Graphics;
import java.awt.Image;

import personagem.Omo;

public class RoupaChao extends Inimigos {

	private Image sprite2;

	private int xSprite2;
	private int ySprite2;
	// Construtor.
	public RoupaChao() {
		this.sprite_dirty = load_dirty_image("images/Inimigos/Pile of Clothes.png");
		sprite2 = load_dirty_image("images/Inimigos/Stink Lines Sprite Sheet.png");
		this.x = getWidth();
		this.y = 500;
		this.enemy_width = 200;
		this.enemy_height = 80;
		this.velocityX = -15;

		xSprite2 = getX();
		ySprite2 = getY() - (sprite2.getHeight(null) - 20);
	}

	public void update(Omo omo) {
		this.x += this.velocityX;
		xSprite2 += this.velocityX;

		if (this.time == 2) {
			this.time = 0;
			this.frameX += 1;

			if (this.frameX % this.COLS == 0) {
				this.frameX = 0;
			}
		} else {
			this.time += 1;
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
				&& getX()+10 + this.sprite_dirty.getWidth(null) <= omo.getX() + (omo.getSprite().getWidth(null)) / 24
				&& getY() + this.sprite_dirty.getHeight(null) <= omo.getY()
						+ omo.getSprite().getHeight(null)
				&& omo.isInvencivel() == false) {
			if (omo.isInvencivel()) {
				omo.setColidiu(true);
			} else {
				omo.setLife(omo.getLife() - 1);
				omo.setColidiu(true);
				omo.setInvencivel(true);
			}
		}

	}

	@Override
	public void draw(Graphics g) {
		g.drawImage(this.sprite_dirty, this.x, this.y, null);
		g.drawImage(sprite2, xSprite2, ySprite2, this.enemy_width
				+ xSprite2, this.enemy_height + ySprite2,
				(this.frameX * this.enemy_width), 0,
				(this.frameX * this.enemy_width)
						+ this.enemy_width,
						this.enemy_height, null);
	}

}
