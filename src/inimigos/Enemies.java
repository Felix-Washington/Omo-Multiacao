package inimigos;

import java.awt.Graphics;
import java.awt.Image;
import java.util.Random;
import omo.Constantes;
import inimigos.Camisa;

public class Enemies {

	private Camisa shirt;
	private Object type;
	
	public Enemies(Object object) {
		if (object.getClass().getName() == "inimigos.camisa") {
				this.type = object;
		} else if (this.type == "calca") {
			
		}
	}
	
	public void attribuition() {
		type = shirt;
		//type.getClass().get

	}
	
	public void draw(Graphics g) {
		
	}
	
	
	
		
}
