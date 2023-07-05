package omo;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferStrategy;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JFrame;
import javax.swing.JPanel;

import inimigos.Boss;
import inimigos.Calca;
import inimigos.Camisa;
import inimigos.Enemies;
import inimigos.RoupaChao;
import niveis.Nivel_dois;
import niveis.Nivel_tres;
import niveis.Nivel_um;
import personagem.Garrafa;
import personagem.Omo;
import personagem.Tiro;

@SuppressWarnings("serial")
public class Main extends Canvas {

	private BufferStrategy strategy;
	private boolean gameRunning = true;

	// Controllers
	private int screen_controler;
	private int screen_controller_2; // 0 - Idle, 1 - tutorial, 2 - pause
	private int game_controller; // 0 - Idle, 1 - Victory, 2 - Victory +-, 3 - Game Over, 4 - Jogo Final, 5 -
									// Tutorial, 6 - Pause
	private static int level_controller; // 0 - No level/ 1 - Level 1 / 2 - Level 2 / 3 - Level 3
	private int mouse_over_counter;

	// Variveis de relacionamento entre classes 
	// Relationship variables between classes
	private Nivel_um fase1;
	private Nivel_dois fase2;
	private Nivel_tres fase3;
	private Omo omo;
	private Boss boss;

	private Camisa shirt;

	// Lists.
	private List<Tiro> tiro;
	private List<Camisa> camisa;
	private List<Calca> calca;
	private List<RoupaChao> roupaChao;
	private List<Garrafa> garrafa;
	private List<Image> menu_images;
	private List<Boolean> locked_levels;
	private List<Object> enemies;

	// Counters
	private int contadorTiro;
	private int contadorGarrafa;
	private int contadorCamisa;
	private int contadorCalca;
	private int contadorRoupa;

	// Timer
	private Timer cronometro;
	private DateFormat formato = new SimpleDateFormat("HH:mm:ss");
	private Calendar calendario = Calendar.getInstance();
	private static String timer;

	// Aux
	private Random random;
	private static boolean fimFase;
	// private int pontosFinal;
	private Musica musica;
	private int creditsY = Constantes.getInstance().getHeight();

	// Main
	public static void main(String[] args) {
		Main game = new Main();
		game.GameLoop();
	}

	// ((Inimigo)listaInimigos[0]).getForça() < !!! SIM!!!
	// class cast

	// Constructor
	public Main() {
		JFrame container = new JFrame();

		container.setUndecorated(true);

		JPanel panel = (JPanel) container.getContentPane();

		panel.setPreferredSize(
				new Dimension(Constantes.getInstance().getWidth(), Constantes.getInstance().getHeight()));

		panel.setLayout(null);

		setBounds(0, 0, Constantes.getInstance().getWidth(), Constantes.getInstance().getHeight());

		panel.add(this);

		setIgnoreRepaint(true);

		container.pack();

		container.setResizable(false);

		addMouseListener(new MouseInputHandler());
		addMouseMotionListener(new MouseInputHandler());
		addKeyListener(new KeyInputHandler());
		System.out.println("teste");
		init();

		container.setLocationRelativeTo(null);

		container.setVisible(true);

		requestFocus();

		createBufferStrategy(2);

		strategy = getBufferStrategy();

	}

	// Main init
	public void init() {
		variables_attribuition();
	}

	// GameLoop, aonde funciona a logica toda do jogo.
	public void GameLoop() {
		
		
		//strategy.getDrawGraphics();
		
		while (gameRunning) {
			Graphics2D g = (Graphics2D) strategy.getDrawGraphics();
			g.drawImage(history(), 0, 0, null);
			
			if (screen_controler == -1) {
				g.drawImage(menu_images.get(16), 45, creditsY, null);
				creditsUpdate();
			} else {
				creditsY = Constantes.getInstance().getHeight();
				;
			}

			System.out.println(g);
			if (screen_controler == 4) {
				//verify_music("sc4");

				if (screen_controller_2 == 1) {
					g.drawImage(menu_images.get(15), 0, 0, null);
				} else {
					drawFases(g);
				}

			}

			if (screen_controler == 5) {
				draw(g);
				if (screen_controller_2 == 2) {
					g.drawImage(menu_images.get(14), 0, 0, null);
					desenharPontos(g);
					verify_music("pause");
				} else {
					update();

				}

			}
			if (screen_controler > 5) {
				draw(g);
				g.drawImage(end_of_level(), 0, 0, null);
				desenharPontos(g);
			}

			g.dispose();

			strategy.show();
			try {
				Thread.sleep(20);
			} catch (Exception e) {
			}
		}
	}

	public void verify_music(String option) {
		switch (option) {
			case "pause":
				//if (musica != null || musica.isAtivo()) {
					// musica.getClip().stop();
				//}
				break;
	
			case "sc4":
				if (musica == null || !musica.isAtivo() && screen_controller_2 == 0) {
					musica = new Musica();
					musica.carregar("resources/Omo Theme.wav");
					musica.start();
				}
				break;
	
			}
	}

	// Método para atualizar os objetos.
	public void update() {
		verificarFinal();
		criarRoupas();
		create_enemies();
		//colisions();
		morrer();

		if (level_controller == 1) {
			fase1.update();
		} else if (level_controller == 2 && !locked_levels.get(0)) {
			fase2.update();
			for (RoupaChao roupa : roupaChao) {
				roupa.update(omo);
			}
			for (Calca calca : calca) {
				calca.update(omo, boss);
			}
		} else if (level_controller == 3 && !locked_levels.get(1)) {
			fase3.update();
			for (RoupaChao roupa : roupaChao) {
				roupa.update(omo);
			}
			for (Calca calca : calca) {
				calca.update(omo, boss);
			}
			if (fase3.getPosXCeleiro() <= 500) {
				boss.update(omo);
			}
		}

		for (Camisa camisa : camisa) {
			if (!camisa.isAlive() || isFimFase()) {
				omo.setColidiu(true);
				camisa.setY(camisa.getY() - camisa.getVelocityY());
			} else {
				camisa.setX(camisa.getX() + camisa.getVelocityX());
			}
			camisa.update(omo, boss);
		}
		for (Tiro tiro : tiro) {
			tiro.update(camisa, calca, omo, boss);
		}
		for (Garrafa garrafa : garrafa) {
			garrafa.update();
		}
		if (isFimFase() && omo.x <= 400 && isFaseTres() == 3) {
			omo.x -= omo.velocidade;
		}
		omo.update();
		deletarObjetos();

		victory();
	}

	public void enemies_verification() {

	}

	// Método para desenhar as imagens.
	public void draw(Graphics g) {

		switch (level_controller) {
		case 1:
			fase1.draw(g);
			break;

		case 2:
			fase2.draw(g);
			for (Calca calca : calca) {
				calca.draw(g);
			}
			for (RoupaChao roupa : roupaChao) {
				roupa.draw(g);
			}
			break;

		case 3:
			fase3.draw(g);
			for (Calca calca : calca) {
				calca.draw(g);
			}
			for (RoupaChao roupa : roupaChao) {
				roupa.draw(g);
			}

			if (fase3.getPosXCeleiro() <= 500) {
				boss.draw(g);
			}
			break;
		}

		for (Camisa camisa : camisa) {
			camisa.draw(g);
		}
		for (Tiro tiro : tiro) {
			tiro.draw(g);
		}
		for (Garrafa garrafa : garrafa) {
			garrafa.draw(g);
		}
		desenharPontos(g);
		omo.draw(g);
		g.drawImage(end_of_level(), 0, 0, null);
	}

	// Método usado para mostrar se as fases estão travadas ou não.
	public void drawFases(Graphics g) {
		if (mouse_over_counter > 0 && mouse_over_counter < 4) {
			g.drawImage(mouse_over_image(),
					(Constantes.getInstance().getWidth() / 2) - (mouse_over_image().getWidth(null) / 2), 0, null);
		}
	}

	// Método para criar as roupas.
	public void criarRoupas() {
		if (!fimFase) {
			int sorteio;
			if (random.nextInt(60) == 0 && level_controller == 1) {
				if (contadorCamisa <= 3) {
					camisa.add(new Camisa());
					contadorCamisa++;
				}

			} else if (random.nextInt(55) == 0 && level_controller == 2) {
				sorteio = random.nextInt(3);

				if (sorteio == 0 && contadorCamisa <= 4) {
					camisa.add(new Camisa());
					contadorCamisa++;
				} else if (sorteio == 1 && contadorCalca <= 4) {
					calca.add(new Calca());
					contadorCalca++;
				} else if (sorteio == 2) {
					contadorRoupa++;
					if (contadorRoupa <= 1) {
						roupaChao.add(new RoupaChao());
					}
				}
			} else if (random.nextInt(50) == 0 && level_controller == 3 && boss.getLife() >= 0) {
				sorteio = random.nextInt(3);

				if (sorteio == 0 && contadorCamisa <= 6) {
					camisa.add(new Camisa());
					contadorCamisa++;
				} else if (sorteio == 1 && contadorCalca <= 5) {
					calca.add(new Calca());
					contadorCalca++;
				} else if (sorteio == 2 && contadorRoupa <= 1 && !fase3.isAtivoCeleiro()) {
					roupaChao.add(new RoupaChao());
					contadorRoupa++;
				}
			}
			if (random.nextInt(500) == 0 && contadorGarrafa <= 1) {
				garrafa.add(new Garrafa());

				contadorGarrafa++;
			}

		}
	}

	// Método usado para deletar os objetos criados.
	public void deletarObjetos() {
		// Deletar camisas.
		for (int i = 0; i < camisa.size(); i++) {
			if (camisa != null) {
				if ((omo.isColidiu()) && !omo.isRemoveuInimigo()) {
					omo.setRemoveuInimigo(true);
					omo.setColidiu(false);
					omo.setPontos(omo.getPontos() + 1);
				} else if (camisa.get(i).getY() + camisa.get(i).getHeight() <= 0) {
					camisa.remove(i);
					contadorCamisa--;
				}
				continue;
			}
		}

		for (int i = 0; i < calca.size(); i++) {
			// Deletar calça.
			if (calca != null) {
				if ((omo.isColidiu()) && !omo.isRemoveuInimigo()) {
					omo.setRemoveuInimigo(true);
					omo.setColidiu(false);
					omo.setPontos(omo.getPontos() + 1);
				} else if (calca.get(i).getY() + calca.get(i).getEnemy_height() <= 0) {
					calca.remove(i);
					contadorCalca--;
				}
				continue;
			}
		}

		for (int i = 0; i < roupaChao.size(); i++) {
			// Deletar roupas no chão.
			if (roupaChao != null) {
				if (roupaChao.get(i).getX() + roupaChao.get(i).getEnemy_width() <= 0) {
					contadorRoupa--;
					roupaChao.remove(i);
				} else if (omo.isColidiu()) {
					contadorRoupa--;
					roupaChao.remove(i);
					omo.setColidiu(false);
				}
				continue;
			}
		}

		for (int i = 0; i < tiro.size(); i++) {
			// Deletar tiro.
			if (tiro != null) {
				if (tiro.get(i).isColidiu()) {
					tiro.remove(i);
					contadorTiro--;
				} else if (tiro.get(i).getX() + Constantes.TIRO_WIDTH >= Constantes.getInstance().getWidth()
						|| tiro.get(i).getY() >= Constantes.getInstance().getHeight()
								&& tiro.get(i).getY() + Constantes.TIRO_HEIGHT <= 0) {
					System.out.println("Removeu tiro");
					tiro.remove(i);
					contadorTiro--;
				}
				continue;
			}
		}
		/*
		 * for (int i = 0; i < garrafa.size(); i++) { if (garrafa != null &&
		 * garrafa.get(i).isColidiu()) { garrafa.remove(i); contadorGarrafa --; } else
		 * if (garrafa.get(i).getX() + garrafa.get(i).getSprite().getWidth(null) <= 0) {
		 * System.out.println("Removeu tiro"); garrafa.remove(i); contadorTiro--; } }
		 */
	}

	public void verificarFinal() {
		// String[] split = timer.split(":");

		if (timer.equals("00:00:03") && level_controller != 3) {
			fimFase = true;
		} else if (timer.equals("00:00:03") && level_controller == 3) {
			fimFase = false;
		}
	}

	public void create_enemies() {

		if (random.nextInt(60) == 0 && enemies.size() <= 5) {
			if (random.nextInt(2) == 0) {

				Object enemy = new Camisa();
				enemies.add(enemy);

			} else if (random.nextInt(2) == 1) {
				Object object = new Calca();
				// enemies.add(object);
			} else if (random.nextInt(2) == 2) {
				// enemies.add(roupaChao);
			}
		}

		for (Object enemy : enemies) {

			//Camisa teste = (Camisa) enemies.getClass().cast(enemy);

			//System.out.println(((Camisa) teste).getEnemy_height());

		}

	}

	public void colisions() {
		// Bottle colision
		for (int i = 0; i < garrafa.size(); i++) {
			if (omo.getX() >= garrafa.get(i).getX()
					&& (omo.getY() + omo.getOmo_images().get(0).getHeight(null) >= garrafa.get(i).getY())) {
				garrafa.get(i).setColidiu(true);
				if (omo.getLife() < 3) {
					omo.setLife(omo.getLife() + 1);
				} else {

					omo.setPontos(omo.getPontos() + 25);
				}
			}
		}

	}

	public void draw_enemies(Graphics g) {
		for (int i = 0; i < enemies.size(); i++) {
			// enemies.get(i).draw(g);
		}
	}

	public void desenharPontos(Graphics g) {
		g.setColor(Color.WHITE);
		g.setFont(new Font("Arial", Font.BOLD, 100));
		int x = 0;
		if (screen_controller_2 == 2) {
			if (level_controller == 3) {
				g.setFont(new Font("Arial", Font.BOLD, 45));
				g.drawString("Derrote o Quortonn", 302, 380);
			} else if (omo.getPontos() >= fase3.getScore_to_win()) {
				g.setFont(new Font("Arial", Font.BOLD, 45));
				g.drawString("Pontos suficientes ", 320, 360);
				g.drawString("alcançados!", 390, 410);
			}

		} else if (game_controller == 1 || game_controller == 2 || game_controller == 3) {
			if (omo.getPontos() >= 0 && omo.getPontos() <= 9) {
				x = 490;
			} else if (omo.getPontos() >= 10 && omo.getPontos() <= 99) {
				x = 450;
			} else if (omo.getPontos() >= 100 && omo.getPontos() <= 999) {
				x = 420;
			} else {
				x = 400;
			}

			g.drawString(String.valueOf(Math.abs(omo.getPontos())), x, 320);
		}
	}

	// Método usado pra resetar o cenário,
	public void resetarCenario() {
		fimFase = false;
		camisa.clear();
		calca.clear();
		roupaChao.clear();
		tiro.clear();
		garrafa.clear();

		contadorCamisa = 0;
		contadorCalca = 0;
		contadorRoupa = 0;
		contadorTiro = 0;

		fase1 = new Nivel_um();
		fase2 = new Nivel_dois();
		fase3 = new Nivel_tres();
		omo = new Omo();
		boss = new Boss();
	}

	// Método usado para verificar se você passou de fase ou não.
	public void victory() {
		if (level_controller == 3) {
			if (boss.getLife() <= 0) {
				resetarCenario();
				level_controller = 0;
				game_controller = 4;
			}
		} else if (timer.equals("00:00:01") && !omo.isInvencivel()) {
			if (level_controller == 1 && omo.getPontos() >= fase1.getScore_to_win()) {
				game_controller = 1;
				screen_controler = 6;
				locked_levels.set(0, true);

			} else if (level_controller == 2 && omo.getPontos() >= fase2.getScore_to_win()) {
				game_controller = 1;
				locked_levels.set(1, false);
			} else {
				game_controller = 2;
			}
			level_controller = 0;
		}
	}

	// Método usado para verificar se você perdeu o jogo.
	public void morrer() {
		if (omo.getLife() < 1) {
			level_controller = 0;
			game_controller = 3;
		}
	}

	// Getters e Setters usados.
	public static boolean isFimFase() {
		return fimFase;
	}

	public static void setFimFase(boolean fimFase) {
		Main.fimFase = fimFase;
	}

	public static Integer isFaseTres() {
		return level_controller;
	}

	public static String getTimer() {
		return timer;
	}

	public void variables_attribuition() {
		random = new Random();
		camisa = new ArrayList<Camisa>();
		calca = new ArrayList<Calca>();
		roupaChao = new ArrayList<RoupaChao>();
		garrafa = new ArrayList<Garrafa>();
		menu_images = new ArrayList<Image>(4);
		locked_levels = new ArrayList<Boolean>();
		enemies = new ArrayList<Object>();

		boss = new Boss();
		tiro = new ArrayList<Tiro>();
		fase1 = new Nivel_um();
		fase2 = new Nivel_dois();
		fase3 = new Nivel_tres();
		omo = new Omo();
		screen_controler = 0;
		screen_controller_2 = 0;
		game_controller = -1;
		mouse_over_counter = 0;

		creditsY = Constantes.getInstance().getHeight();

		for (int i = 0; i < 18; i++) {
			Image image = new Menu_images(i).loadImage();
			menu_images.add(image);
		}

		for (int i = 2; i < 4; i++) {
			Boolean level = true;
			locked_levels.add(level);
		}

		// Inicializa as variaveis do cronometro;
		cronometro = new Timer();

		TimerTask tarefa = new TimerTask() {

			@Override
			public void run() {
				timer = formato.format(calendario.getTime());
				if (!timer.equals("00:00:00") && screen_controller_2 == 0) {
					calendario.add(Calendar.SECOND, -1);
				}

			}
		};
		cronometro.scheduleAtFixedRate(tarefa, 0, 1000);
	}

	public Image history() {
		switch (screen_controler) {
		case -1:
			return menu_images.get(17);
		case 0:
			return menu_images.get(0);
		case 1:
			return menu_images.get(1);
		case 2:
			return menu_images.get(2);
		case 3:
			return menu_images.get(3);
		case 4:
			return menu_images.get(4);

		}
		return null;
	}

	public Image mouse_over_image() {
		switch (mouse_over_counter) {
		case 1:
			return menu_images.get(8);
		case 2:
			if (locked_levels.get(0)) {
				return menu_images.get(9);
			} else {
				return menu_images.get(10);
			}

		case 3:
			if (locked_levels.get(1)) {
				return menu_images.get(11);
			} else {
				return menu_images.get(12);
			}

		}
		return null;
	}

	public Image end_of_level() {
		switch (game_controller) {
		case 1:
			return menu_images.get(5);
		case 2:
			return menu_images.get(6);
		case 3:
			return menu_images.get(7);
		case 4:
			return menu_images.get(13);
		case 5:
			return menu_images.get(15);
		}
		return null;

	}

	private void creditsUpdate() {
		if (creditsY > -1155) {
			creditsY -= 2;
		}
	}

	class MouseInputHandler extends MouseAdapter implements MouseMotionListener {
		private int mouseX;
		private int mouseY;

		@Override
		public void mouseClicked(MouseEvent event) {
			mouseX = event.getX();
			mouseY = event.getY();

			switch (verify_coordinate()) {
			case "history":
				screen_controler = 1;
				break;

			case "credits":
				level_controller = 0;
				screen_controler = -1;
				break;

			case "back_from_credits":
				screen_controler = 0;
				break;

			case "history_advance":
				screen_controler--;
				break;
			case "history_back":
				screen_controler++;
				break;

			case "go_to_levels":
				screen_controler = 4;
				break;

			case "open_tutorial":
				screen_controller_2 = 1;
				break;
			case "close_tutorial":
				screen_controller_2 = 0;
				break;
			case "enter_level_1":
				calendario.set(0, 0, 0, 0, 0, 30);
				level_controller = 1;
				screen_controler = 5;
				break;

			case "enter_level_2":
				calendario.set(0, 0, 0, 0, 0, 45);
				level_controller = 2;
				screen_controler = 5;
				break;

			case "enter_level_3":
				calendario.set(0, 0, 0, 0, 0, 50);
				level_controller = 3;
				screen_controler = 5;
				break;

			case "leave_from_level_screen":
				screen_controler = 0;
				break;

			case "shoot":
				if (!fimFase || level_controller == 3) {
					Constantes.clickX = mouseX;
					Constantes.clickY = mouseY;

					if (contadorTiro < 3) {
						tiro.add(new Tiro(omo));
						contadorTiro++;
					}
				}
				break;

			case "back_to_levels":

				if (musica != null || musica.isAtivo()) {
					// musica.getClip().loop(1000);
				}
				mouse_over_counter = 0;
				level_controller = 0;
				screen_controler = 4;
				screen_controller_2 = 0;
				resetarCenario();
				break;

			case "leave_from_gamecontroller_screen":
				// Sair da tela de vitoria.
				if (game_controller == 4) {
					screen_controler = -1;
				} else if (game_controller == 3) {
					level_controller = 0;

				} else {
					screen_controler = 4;
				}
				game_controller = 0;
				resetarCenario();
				break;
			}
		}

		public String verify_coordinate() {
			if (screen_controler == -1) {
				if (mouseX >= 862 && mouseX <= Constantes.getInstance().getWidth() && mouseY >= 550
						&& mouseY <= Constantes.getInstance().getHeight()) {
					return "back_from_credits";
				}
			}

			if (screen_controler == 0) {
				if (mouseX >= 392 && mouseX <= 631 && mouseY >= 334 && mouseY <= 407) {
					return "history";
				} else if (mouseX >= 391 && mouseX <= 630 && mouseY >= 429 && mouseY <= 500) {
					return "credits";
				} else if (mouseX >= 391 && mouseX <= 632 && mouseY >= 522 && mouseY <= 592) {
					// Exit.
					gameRunning = false;
					System.exit(0);
				}
			}

			if (screen_controler > 0 && screen_controler <= 3) {
				if (mouseX >= 0 && mouseX <= 162 && mouseY >= 552 && mouseY <= 600) {
					return "history_advance";

				} else if (mouseX >= 859 && mouseX <= 1024 && mouseY >= 552 && mouseY <= 600) {
					return "history_back";

				} else if (mouseX >= 839 && mouseX <= 1024 && mouseY >= 0 && mouseY <= 47) {
					return "go_to_levels";
				}
			}

			if (screen_controller_2 == 0 && screen_controler == 4) {
				if (mouseX >= 131 && mouseX <= 253 && mouseY >= 232 && mouseY <= 318) {
					return "enter_level_1";
				} else if (mouseX >= 397 && mouseX <= 569 && mouseY >= 292 && mouseY <= 414 && !locked_levels.get(0)) {
					return "enter_level_2";
				} else if (mouseX >= 786 && mouseX <= 981 && mouseY >= 382 && mouseY <= 521 && !locked_levels.get(1)) {
					return "enter_level_3";
				} else if (mouseX >= 859 && mouseX <= 1024 && mouseY >= 553 && mouseY <= 600) {
					return "leave_from_level_screen";
				}
			}

			if (screen_controler == 4) {
				if (mouseX >= 0 && mouseX <= 163 && mouseY >= 551 && mouseY <= 600) {
					return "open_tutorial";
				} else if (mouseX >= 0 && mouseX <= 1024 && mouseY >= 0 && mouseY <= 600) {
					return "close_tutorial";
				}
			}

			if ((level_controller >= 1 && level_controller <= 3) && screen_controller_2 == 0) {
				if (mouseX >= 0 && mouseX <= 1024 && mouseY >= 0 && mouseY <= 600) {
					return "shoot";
				}
			} else if (mouseX >= 612 && mouseX <= 730 && mouseY >= 432 && mouseY <= 467 && screen_controller_2 == 2) {
				return "back_to_levels";
			}

			if (game_controller > 0) {
				return "leave_from_gamecontroller_screen";
			}

			return "";
		}

		@Override
		public void mouseMoved(MouseEvent e) {
			if (screen_controler == 4) {
				if (e.getX() >= 131 && e.getX() <= 253 && e.getY() >= 232 && e.getY() <= 318) {
					mouse_over_counter = 1;
				} else if (e.getX() >= 394 && e.getX() <= 570 && e.getY() >= 293 && e.getY() <= 406) {
					mouse_over_counter = 2;
				} else if (e.getX() >= 783 && e.getX() <= 998 && e.getY() >= 373 && e.getY() <= 513) {
					mouse_over_counter = 3;
				} else {
					mouse_over_counter = 0;
				}
			}

		}
	}

	private class KeyInputHandler extends KeyAdapter {
		@Override
		public void keyPressed(KeyEvent event) {
			if (event.getKeyCode() == KeyEvent.VK_ESCAPE) {
				if (screen_controler > 0 && screen_controler < 4) {
					screen_controler = 4;
				} else if (screen_controler == 4) {
					screen_controler = 0;
				}

				if (level_controller >= 1 && level_controller <= 3) {
					//if (musica != null || musica.isAtivo() && screen_controller_2 == 0) {
						// musica.getClip().loop(1000);
					//}
					if (screen_controller_2 == 0) {
						screen_controller_2 = 2;
					} else {
						screen_controller_2 = 0;
					}
				}
			} else if (event.getKeyCode() == KeyEvent.VK_LEFT) {
				if (screen_controler > 0 && screen_controler < 4) {
					screen_controler--;
				}
			} else if (event.getKeyCode() == KeyEvent.VK_RIGHT) {
				if (screen_controler > 0 && screen_controler < 4) {
					screen_controler++;
				}
			} else if (event.getKeyCode() == KeyEvent.VK_P) {
				if (level_controller >= 1 && level_controller <= 3) {
					if (musica != null || musica.isAtivo() && screen_controller_2 == 0) {
						musica.getClip().loop(1000);
					}
					if (screen_controller_2 == 0) {
						screen_controller_2 = 2;
					} else {
						screen_controller_2 = 0;
					}
				}
			} else if (event.getKeyCode() == KeyEvent.VK_SPACE) {
				if (level_controller > 0 && level_controller < 4) {
					omo.setPulo(true);
				}

			}
		}

	}

}