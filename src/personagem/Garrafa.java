package personagem;

import java.awt.Graphics;
import java.util.Random;

import omo.Constantes;
import omo.Personagens;

public class Garrafa extends Personagens {

	private Random random;
	private int velY;

	public Garrafa() {
		super();
		setSprite(Constantes.getInstance().loadImage(
				"images/Omo/Omo power.png"));

		random = new Random();
		
		setY(random.nextInt(200)+ 50);
		setVelocidade(-6);
		setVelY(3);
		setX(Constantes.getInstance().getWidth());
	}

	public void update() {
		setX(getX() + getVelocidade());
		setY(getY() + getVelY());
		if ((getY() <= 50 || getY() >= Constantes.getInstance()
				.getHeight() - 300)) {
			setVelY(getVelY() * -1);
		}
	}

	@Override
	public void draw(Graphics g) {
		g.drawImage(getSprite(), getX(), getY(), null);

	}

	public int getVelY() {
		return velY;
	}

	public void setVelY(int velY) {
		this.velY = velY;
	}
	
	

}
