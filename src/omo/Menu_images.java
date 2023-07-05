package omo;
import java.awt.Image;

public class Menu_images {
	private String path;

	
	public Menu_images(int screen) {
		switch (screen) {
		case 0:
			path = "images/telas/Title Screen.png";
			break;
		case 1:
			path = "images/telas/Story 1.png";
			break;
		case 2:
			path = "images/telas/Story 2.png";
			break;
		case 3:
			path = "images/telas/Story 3.png";
			break;
		case 4:
			path = "images/telas/Level Select Base.png";
			break;
		case 5:
			path = "images/telas/Victory Happy.png";
			break;			
		case 6:
			path = "images/telas/Victory Okay.png";
			break;			
		case 7:
			path = "images/telas/Victory Sad.png";
			break;			
		case 8:
			path = "images/telas/SelecaoNivel/level Casa (Unlocked).png";
			break;			
		case 9:
			path = "images/telas/SelecaoNivel/level Planicie (Locked).png";
			break;			
		case 10:
			path = "images/telas/SelecaoNivel/level Planicie (Unlocked).png";
			break;
		case 11:
			path = "images/telas/SelecaoNivel/level Celeiro (Locked).png";
			break;
		case 12:
			path = "images/telas/SelecaoNivel/level Celeiro (Unlocked).png";
			break;
		case 13:			
			path = "images/telas/Boss Victory.png";
			break;
		case 14:
			path = "images/telas/Pause Menu.png";
			break;			
		case 15:
			path = "images/telas/Tutorial.png";
			break;
		case 16:
			path = "images/telas/Credits Text.png";
			break;
		case 17:
			path = "images/telas/Credits Background.png";
			break;
		default:
			break;
		}
		
	}
	
	
	public Image loadImage() {
		Image image = null;
		image = Constantes.getInstance().loadImage(path);		
		return image;
	}


	public String getPath() {
		return path;
	}


	public void setPath(String path) {
		this.path = path;
	}
}
