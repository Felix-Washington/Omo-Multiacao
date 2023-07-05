package inimigos;

import java.awt.Graphics;
import java.awt.Image;
import java.util.Random;
import omo.Constantes;

public class Inimigos {

	protected int x;
	protected int y;
	protected int velocityX;
	protected int velocityY;
	protected int frameX;
	protected final int COLS = 24;
	protected int time;
	protected int enemy_width;
	protected int enemy_height;
	protected Image sprite_dirty;
	protected Image sprite_clean;
	protected Random random;
	protected boolean alive;
	
	public Inimigos() {
		random = new Random();
	}
	
	public void draw(Graphics g) {
		
	}
	
	public Image load_dirty_image(String path) {
		sprite_dirty = Constantes.getInstance().loadImage(path);
		
		return sprite_dirty;
	}
	
	public Image load_clean_image(String path) {
		sprite_clean = Constantes.getInstance().loadImage(path);
		return sprite_clean;
	}
	
	public Image loadImage(String path) {
		Image image = Constantes.getInstance().loadImage(path);
		return image;
	}
	
	public int getWidth() {
		return Constantes.getInstance().getWidth();
	}

	public int getHeight() {
		return Constantes.getInstance().getHeight();
	}
	
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public Image getSprite_dirty() {
		return sprite_dirty;
	}

	public void setSprite_dirty(Image sprite_dirty) {
		this.sprite_dirty = sprite_dirty;
	}

	public Image getSprite_clean() {
		return sprite_clean;
	}

	public void setSprite_clean(Image sprite_clean) {
		this.sprite_clean = sprite_clean;
	}

	public Random getRandom() {
		return random;
	}

	public void setRandom(Random random) {
		this.random = random;
	}

	public int getVelocityX() {
		return velocityX;
	}

	public void setVelocityX(int velocityX) {
		this.velocityX = velocityX;
	}

	public int getVelocityY() {
		return velocityY;
	}

	public void setVelocityY(int velocityY) {
		this.velocityY = velocityY;
	}

	public boolean isAlive() {
		return alive;
	}

	public void setAlive(boolean alive) {
		this.alive = alive;
	}

	public int getFrameX() {
		return frameX;
	}

	public void setFrameX(int frameX) {
		this.frameX = frameX;
	}

	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}

	public int getEnemy_width() {
		return enemy_width;
	}

	public void setEnemy_width(int enemy_width) {
		this.enemy_width = enemy_width;
	}

	public int getEnemy_height() {
		return enemy_height;
	}

	public void setEnemy_height(int enemy_height) {
		this.enemy_height = enemy_height;
	}

	public int getCOLS() {
		return COLS;
	}
	
	
	
		
}
