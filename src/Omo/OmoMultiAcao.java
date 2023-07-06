package Omo;

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

import Inimigos.Boss;
import Inimigos.Calca;
import Inimigos.Camisa;
import Inimigos.roupaChao;
import Omo.Fases.Fase1;
import Omo.Fases.Fase2;
import Omo.Fases.Fase3;

@SuppressWarnings("serial")
public class OmoMultiAcao extends Canvas {

	private BufferStrategy strategy;
	private boolean gameRunning = true;

	// Imagens da classe Game.
	private Image imageMenu;
	private Image imageTelaNivel;
	private Image imagePause;
	private Image imageTutorial;
	private Image imageVencerOkay;
	private Image imageVencer;
	private Image imageCreditos;
	private Image imageTexto;
	private Image imageFinal;
	private Image imageHistoria1;
	private Image imageHistoria2;
	private Image imageHistoria3;

	private Image imageGameOver;

	private Image imageFaseUmUnlocked;

	private Image imageFaseDoisLocked;
	private Image imageFaseDoisUnlocked;

	private Image imageFaseTresLocked;
	private Image imageFaseTresUnlocked;

	// Variaveis de controle para as tela
	private boolean menu;
	private boolean telaNivel;
	private boolean pause;
	private boolean tutorial;
	private boolean vencerOkay;
	private boolean vencer;
	private boolean gameOver;
	private boolean credits;
	private boolean jogoFinal;
	private boolean historia1;
	private boolean historia2;
	private boolean historia3;

	// Variaveis para entrar nas fases.
	private boolean faseUm, faseDois;
	private static boolean faseTres;

	// Variaveis para indicar as fases que estão desbloqueadas.
	private boolean faseDoisUnlocked, faseTresUnlocked;
	private boolean mouseOverFaseUm, mouseOverFaseDois, mouseOverFaseTres;

	// Variveis de relacionamento entre classes
	private Fase1 fase1;
	private Fase2 fase2;
	private Fase3 fase3;
	private Omo omo;
	private Boss boss;

	// Listas.
	private List<Tiro> tiro;
	private List<Camisa> camisa;
	private List<Calca> calca;
	private List<roupaChao> roupaChao;
	private List<Garrafa> garrafa;

	// Variaveis contadoras.
	private int contadorCamisa;
	private int contadorCalca;
	private int contadorRoupa;
	private int contadorTiro;
	private int contadorGarrafa;

	// Variaveis do Timer.
	private Timer cronometro;
	private DateFormat formato = new SimpleDateFormat("HH:mm:ss");
	private Calendar calendario = Calendar.getInstance();
	private static String timer;

	// Variaveis auxiliares.
	private Random random;
	private int pontosFase;
	private static boolean fimFase;
	private int pontosFinal;
	private Musica musica;
	private int creditsY = Constantes.getInstance().getHeight();

	// Construtor
	public OmoMultiAcao() {
		JFrame container = new JFrame();

		container.setUndecorated(true);

		JPanel panel = (JPanel) container.getContentPane();

		panel.setPreferredSize(new Dimension(Constantes.getInstance()
				.getWidth(), Constantes.getInstance().getHeight()));

		panel.setLayout(null);

		setBounds(0, 0, Constantes.getInstance().getWidth(), Constantes
				.getInstance().getHeight());

		panel.add(this);

		setIgnoreRepaint(true);

		container.pack();

		container.setResizable(false);

		addMouseListener(new MouseInputHandler());
		addMouseMotionListener(new MouseInputHandler());
		addKeyListener(new KeyInputHandler());

		init();

		container.setLocationRelativeTo(null);

		container.setVisible(true);

		requestFocus();

		createBufferStrategy(2);

		strategy = getBufferStrategy();

	}

	// Inicializa as imagens
	public void init() {
		imageMenu = Constantes.getInstance().loadImage(
				"Omo/imagens/Title Screen.png");
		imageTelaNivel = Constantes.getInstance().loadImage(
				"Omo/imagens/Level Select Base.png");
		imagePause = Constantes.getInstance().loadImage(
				"Omo/imagens/Pause Menu.png");
		imageTutorial = Constantes.getInstance().loadImage(
				"Omo/imagens/Tutorial.png");
		imageVencerOkay = Constantes.getInstance().loadImage(
				"Omo/imagens/Victory Okay.png");
		imageVencer = Constantes.getInstance().loadImage(
				"Omo/imagens/Victory Happy.png");
		imageGameOver = Constantes.getInstance().loadImage(
				"Omo/imagens/Victory Sad.png");
		imageCreditos = Constantes.getInstance().loadImage(
				"Omo/imagens/Credits Background.png");
		imageTexto = Constantes.getInstance().loadImage(
				"Omo/imagens/Credits Text.png");

		imageFaseUmUnlocked = Constantes.getInstance().loadImage(
				"Omo/imagens/SelecaoNivel/level Casa (Unlocked).png");

		imageFaseDoisLocked = Constantes.getInstance().loadImage(
				"Omo/imagens/SelecaoNivel/level Planicie (Locked).png");
		imageFaseDoisUnlocked = Constantes.getInstance().loadImage(
				"Omo/imagens/SelecaoNivel/level Planicie (Unlocked).png");

		imageFaseTresLocked = Constantes.getInstance().loadImage(
				"Omo/imagens/SelecaoNivel/level Celeiro (Locked).png");
		imageFaseTresUnlocked = Constantes.getInstance().loadImage(
				"Omo/imagens/SelecaoNivel/level Celeiro (Unlocked).png");
		imageFinal = Constantes.getInstance().loadImage(
				"Omo/imagens/Boss Victory.png");
		imageHistoria1 = Constantes.getInstance().loadImage(
				"Omo/imagens/Story 1.png");
		imageHistoria2 = Constantes.getInstance().loadImage(
				"Omo/imagens/Story 2.png");
		imageHistoria3 = Constantes.getInstance().loadImage(
				"Omo/imagens/Story 3.png");

		random = new Random();
		camisa = new ArrayList<Camisa>();
		calca = new ArrayList<Calca>();
		roupaChao = new ArrayList<roupaChao>();
		boss = new Boss();
		tiro = new ArrayList<Tiro>();
		fase1 = new Fase1();
		fase2 = new Fase2();
		fase3 = new Fase3();
		omo = new Omo();
		garrafa = new ArrayList<Garrafa>();
		menu = true;
		tutorial = true;
		// Inicializa as variaveis do cronometro;

		cronometro = new Timer();

		TimerTask tarefa = new TimerTask() {

			@Override
			public void run() {
				timer = formato.format(calendario.getTime());
				if (!timer.equals("00:00:00") && !pause) {
					calendario.add(Calendar.SECOND, -1);
				}

			}
		};
		cronometro.scheduleAtFixedRate(tarefa, 0, 1000);
	}

	// GameLoop, aonde funciona a logica toda do jogo.
	public void GameLoop() {
		while (gameRunning) {

			Graphics2D g = (Graphics2D) strategy.getDrawGraphics();
			if (!gameOver) {
				if (menu) {
					g.drawImage(imageMenu, 0, 0, null);
				} else if (historia1) {
					g.drawImage(imageHistoria1, 0, 0, null);
				} else if (historia2) {
					g.drawImage(imageHistoria2, 0, 0, null);
				} else if (historia3) {
					g.drawImage(imageHistoria3, 0, 0, null);
				} else if (telaNivel) {
					g.drawImage(imageTelaNivel, 0, 0, null);

					if (musica == null || !musica.isAtivo() && !pause) {
						musica = new Musica();
						musica.carregar("src/resources/Omo Theme.wav");
						musica.start();
					}

					if (tutorial) {
						g.drawImage(imageTutorial, 0, 0, null);

					} else {
						drawFases(g);
					}
					// Pausar o jogo.
				} else if (pause) {
					draw(g);
					g.drawImage(imagePause, 0, 0, null);
					desenharPontos(g);

					if (musica != null || musica.isAtivo()) {
						musica.getClip().stop();
					}

				} else if (faseUm || faseDois || faseTres) {
					update();
					draw(g);
				} else if (vencerOkay || vencer) {
					draw(g);
				} else if (credits) {
					credits(g);
					creditsUpdate();
				} else if (jogoFinal) {
					g.drawImage(imageFinal, 0, 0, null);
				}
			} else {
				g.drawImage(imageGameOver, 0, 0, null);
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

	private void creditsUpdate() {
		if (creditsY > -1155) {
			creditsY -= 2;
		}
	}

	private void credits(Graphics g) {
		g.drawImage(imageCreditos, 0, 0, null);
		g.drawImage(imageTexto, 45, creditsY, null);
	}

	public static void main(String[] args) {
		OmoMultiAcao game = new OmoMultiAcao();
		game.GameLoop();
	}

	class MouseInputHandler extends MouseAdapter implements MouseMotionListener {

		@Override
		public void mouseClicked(MouseEvent event) {
			System.out.println("X " + event.getX() + " Y: " + event.getY());
			// Tela menu.
			if (menu) {

				desativarMenus();
				menu = true;
				creditsY = Constantes.getInstance().getHeight();

				pause = false;

				desativarFases();

				if (event.getX() >= 392 && event.getX() <= 631
						&& event.getY() >= 334 && event.getY() <= 407) {
					// Historia.
					desativarMenus();
					historia1 = true;
					pause = false;

					desativarFases();

				} else if (event.getX() >= 391 && event.getX() <= 630
						&& event.getY() >= 429 && event.getY() <= 500) {
					// Creditos.

					desativarFases();
					desativarMenus();
					credits = true;
					pause = false;

				} else if (event.getX() >= 391 && event.getX() <= 632
						&& event.getY() >= 522 && event.getY() <= 592) {
					// Exit.
					gameRunning = false;
					System.exit(0);
				}
			} else if (historia1) {
				if (event.getX() >= 0 && event.getX() <= 162
						&& event.getY() >= 552 && event.getY() <= 600) {
					historia1 = false;
					menu = true;
				} else if (event.getX() >= 859 && event.getX() <= 1024
						&& event.getY() >= 552 && event.getY() <= 600) {
					historia1 = false;
					historia2 = true;
				} else if (event.getX() >= 839 && event.getX() <= 1024
						&& event.getY() >= 0 && event.getY() <= 47) {
					desativarHistoria();
					telaNivel = true;
				}
			} else if (historia2) {
				if (event.getX() >= 0 && event.getX() <= 162
						&& event.getY() >= 552 && event.getY() <= 600) {
					historia2 = false;
					historia1 = true;
				} else if (event.getX() >= 859 && event.getX() <= 1024
						&& event.getY() >= 552 && event.getY() <= 600) {
					historia2 = false;
					historia3 = true;
				} else if (event.getX() >= 839 && event.getX() <= 1024
						&& event.getY() >= 0 && event.getY() <= 47) {
					desativarHistoria();
					telaNivel = true;
				}
			} else if (historia3) {
				if (event.getX() >= 0 && event.getX() <= 162
						&& event.getY() >= 552 && event.getY() <= 600) {
					historia3 = false;
					historia2 = true;
				} else if (event.getX() >= 859 && event.getX() <= 1024
						&& event.getY() >= 552 && event.getY() <= 600) {
					historia3 = false;
					telaNivel = true;
				} else if (event.getX() >= 839 && event.getX() <= 1024
						&& event.getY() >= 0 && event.getY() <= 47) {
					desativarHistoria();
					telaNivel = true;
				}
			}

			else if (telaNivel) {

				if (tutorial) {
					if (event.getX() >= 0
							&& event.getX() <= Constantes.getInstance()
									.getWidth()
							&& event.getY() >= 0
							&& event.getY() <= Constantes.getInstance()
									.getHeight()) {
						tutorial = false;
					}
				} else if (event.getX() >= 0 && event.getX() <= 163
						&& event.getY() >= 551 && event.getY() <= 600) {
					// Tutorial
					tutorial = true;

					// else if () {

				} else {

					if (event.getX() >= 131 && event.getX() <= 253
							&& event.getY() >= 232 && event.getY() <= 318) {
						// Fase 1.
						desativarMenus();

						calendario.set(0, 0, 0, 0, 0, 30);

						desativarFases();
						faseUm = true;

						pause = false;

						pontosFase = 150;

					} else if (event.getX() >= 397 && event.getX() <= 569
							&& event.getY() >= 292 && event.getY() <= 414
							&& faseDoisUnlocked) {
						// Fase 2.
						desativarMenus();

						calendario.set(0, 0, 0, 0, 0, 45);

						desativarFases();
						faseDois = true;

						pause = false;

						pontosFase = 200;

					} else if (event.getX() >= 786 && event.getX() <= 981
							&& event.getY() >= 382 && event.getY() <= 521
							&& faseTresUnlocked) {
						// Fase 3.
						desativarMenus();
						calendario.set(0, 0, 0, 0, 0, 50);

						desativarFases();
						faseTres = true;

						pause = false;

					} else if (event.getX() >= 859
							&& event.getX() <= Constantes.getInstance()
									.getWidth()
							&& event.getY() >= 553
							&& event.getY() <= Constantes.getInstance()
									.getHeight()) {
						// Sair da tela de niveis.
						desativarMenus();
						menu = true;

						desativarFases();

						pause = false;

					}

				}

			} else if ((faseUm || faseDois || faseTres) && !pause) {
				// Tiro.
				if (event.getX() >= 184
						&& event.getX() <= Constantes.getInstance().getWidth()
						&& event.getY() >= 0
						&& event.getY() <= Constantes.getInstance().getHeight()) {
					if (!fimFase || faseTres) {
						Constantes.clickX = event.getX();
						Constantes.clickY = event.getY();

						if (contadorTiro < 3) {
							tiro.add(new Tiro(omo));
							contadorTiro++;
						} else {

						}
					}

				}

			} else if (event.getX() >= 612 && event.getX() <= 730
					&& event.getY() >= 432 && event.getY() <= 467 && pause) {
				// Sair da fase.
				mouseOverFaseUm = false;
				mouseOverFaseDois = false;
				mouseOverFaseTres = false;
				desativarMenus();
				if (musica != null || musica.isAtivo()) {
					musica.getClip().loop(1000);
				}

				vencer = false;
				vencerOkay = false;
				desativarFases();
				telaNivel = true;

				resetarCenario();
			} else if (event.getX() >= 862
					&& event.getX() <= Constantes.getInstance().getWidth()
					&& event.getY() >= 550
					&& event.getY() <= Constantes.getInstance().getHeight()) {
				// Sair da tela de creditos.
				desativarMenus();
				desativarFases();
				menu = true;

			} else if (gameOver && event.getX() >= 0
					&& event.getX() <= Constantes.getInstance().getWidth()
					&& event.getY() >= 0
					&& event.getY() <= Constantes.getInstance().getHeight()) {
				// Sair da tela de game over.
				pontosFinal = omo.getPontos();
				gameOver = false;
				resetarCenario();
				telaNivel = true;

			} else if ((vencer || vencerOkay || jogoFinal) && event.getX() >= 0
					&& event.getX() <= Constantes.getInstance().getWidth()
					&& event.getY() >= 0
					&& event.getY() <= Constantes.getInstance().getHeight()) {
				// Sair da tela de vitoria.
				if (jogoFinal) {
					desativarMenus();
					desativarFases();
					resetarCenario();
					credits = true;
				} else {
					vencer = false;
					vencerOkay = false;
					desativarMenus();
					desativarFases();
					resetarCenario();
					telaNivel = true;
				}
			}
		}

		@Override
		public void mouseMoved(MouseEvent e) {
			if (telaNivel) {
				if (e.getX() >= 131 && e.getX() <= 253 && e.getY() >= 232
						&& e.getY() <= 318) {

					mouseOverFaseUm = true;
					mouseOverFaseDois = false;
					mouseOverFaseTres = false;
				} else if (e.getX() >= 394 && e.getX() <= 570
						&& e.getY() >= 293 && e.getY() <= 406) {

					mouseOverFaseUm = false;
					mouseOverFaseDois = true;
					mouseOverFaseTres = false;
				} else if (e.getX() >= 783 && e.getX() <= 998
						&& e.getY() >= 373 && e.getY() <= 513) {

					mouseOverFaseUm = false;
					mouseOverFaseDois = false;
					mouseOverFaseTres = true;
				} else {
					mouseOverFaseUm = false;
					mouseOverFaseDois = false;
					mouseOverFaseTres = false;
				}
			}
		}
	}

	private class KeyInputHandler extends KeyAdapter {

		@Override
		public void keyPressed(KeyEvent event) {
			if (historia1 || historia2 || historia3) {
				if (event.getKeyCode() == KeyEvent.VK_ESCAPE) {
					desativarMenus();
					desativarHistoria();
					telaNivel = true;
				} else if (event.getKeyCode() == KeyEvent.VK_LEFT) {
					if (historia1) {
						desativarHistoria();
						menu = true;
					} else if (historia2) {
						desativarHistoria();
						historia1 = true;
					} else if (historia3) {
						desativarHistoria();
						historia2 = true;
					}

				} else if (event.getKeyCode() == KeyEvent.VK_RIGHT) {
					if (historia1) {
						desativarHistoria();
						historia2 = true;
					} else if (historia2) {
						desativarHistoria();
						historia3 = true;
					} else if (historia3) {
						desativarHistoria();
						telaNivel = true;
					}
				}
			} else if (telaNivel && event.getKeyCode() == KeyEvent.VK_ESCAPE) {
				desativarMenus();
				menu = true;
			}

			if (faseUm || faseDois || faseTres) {
				if (event.getKeyCode() == KeyEvent.VK_P
						|| event.getKeyCode() == KeyEvent.VK_ESCAPE) {

					if (musica != null || musica.isAtivo() && !pause) {
						musica.getClip().loop(1000);
					}
					pause = !pause;

				} else if (event.getKeyCode() == KeyEvent.VK_W
						|| event.getKeyCode() == KeyEvent.VK_SPACE) {

					omo.setPulo(true);
				}
			}
			if (credits && event.getKeyCode() == KeyEvent.VK_ESCAPE) {
				desativarMenus();
				menu = true;
			}
		}
	}

	// Método para atualizar os objetos.
	public void update() {
		pontosFinal = omo.getPontos();
		verificarFinal();
		criarRoupas();
		morrer();

		if (faseUm) {
			fase1.update();
			faseDoisUnlocked = true;
		} else if (faseDois && faseDoisUnlocked) {
			fase2.update();
			faseTresUnlocked = true;
			for (roupaChao roupa : roupaChao) {
				roupa.update(omo);
			}
			for (Calca calca : calca) {
				calca.update(omo, boss);
			}
		} else if (faseTres && faseTresUnlocked) {
			fase3.update();
			for (roupaChao roupa : roupaChao) {
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
			camisa.update(omo, boss);
		}
		for (Tiro tiro : tiro) {
			tiro.update(camisa, calca, omo, boss);
		}
		for (Garrafa garrafa : garrafa) {
			garrafa.update();
		}

		omo.update(garrafa);
		deletarObjetos();

		victory();
	}

	// Método para desenhar as imagens.
	public void draw(Graphics g) {

		if (faseUm) {
			fase1.draw(g);
		} else if (faseDois) {
			fase2.draw(g);
			for (Calca calca : calca) {
				calca.draw(g);
			}
			for (roupaChao roupa : roupaChao) {
				roupa.draw(g);
			}
		} else if (faseTres) {

			fase3.draw(g);
			for (Calca calca : calca) {
				calca.draw(g);
			}
			for (roupaChao roupa : roupaChao) {
				roupa.draw(g);
			}
			if (fase3.getPosXCeleiro() <= 500) {
				boss.draw(g);
			}
		}

		if (vencerOkay) {
			g.drawImage(imageVencerOkay, 0, 0, null);
			desenharPontos(g);
		} else if (vencer) {
			g.drawImage(imageVencer, 0, 0, null);
			desenharPontos(g);
		}

		if (faseUm || faseDois || faseTres) {
			omo.draw(g);
			for (Camisa camisa : camisa) {
				camisa.draw(g);
			}
			for (Tiro tiro : tiro) {
				tiro.draw(g);
			}
			for (Garrafa garrafa : garrafa) {
				garrafa.draw(g);
			}
		}

	}

	// Método usado para mostrar se as fases estão travadas ou não.
	public void drawFases(Graphics g) {
		if (mouseOverFaseUm) {
			g.drawImage(imageFaseUmUnlocked, (Constantes.getInstance()
					.getWidth() / 2)
					- (imageFaseDoisUnlocked.getWidth(null) / 2), 0, null);
		} else if (!faseDoisUnlocked && mouseOverFaseDois) {
			g.drawImage(imageFaseDoisLocked,
					(Constantes.getInstance().getWidth() / 2)
							- (imageFaseDoisLocked.getWidth(null) / 2), 0, null);
		} else if (!faseTresUnlocked && mouseOverFaseTres) {
			g.drawImage(imageFaseTresLocked,
					(Constantes.getInstance().getWidth() / 2)
							- (imageFaseDoisLocked.getWidth(null) / 2), 0, null);

		} else if (faseDoisUnlocked && mouseOverFaseDois) {
			g.drawImage(imageFaseDoisUnlocked, (Constantes.getInstance()
					.getWidth() / 2)
					- (imageFaseDoisUnlocked.getWidth(null) / 2), 0, null);
		} else if (faseTresUnlocked && mouseOverFaseTres) {
			g.drawImage(imageFaseTresUnlocked, (Constantes.getInstance()
					.getWidth() / 2)
					- (imageFaseTresUnlocked.getWidth(null) / 2), 0, null);
		}
	}

	// Método para criar as roupas.
	public void criarRoupas() {

		if (!fimFase) {
			int sorteio;
			if (random.nextInt(60) == 0 && faseUm) {
				if (contadorCamisa <= 3) {
					camisa.add(new Camisa());
					contadorCamisa++;
				}

			} else if (random.nextInt(55) == 0 && faseDois) {
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
						roupaChao.add(new roupaChao());
					}
				}
			} else if (random.nextInt(50) == 0 && faseTres
					&& boss.getVida() >= 0) {
				sorteio = random.nextInt(3);

				if (sorteio == 0 && contadorCamisa <= 6) {
					camisa.add(new Camisa());
					contadorCamisa++;
				} else if (sorteio == 1 && contadorCalca <= 5) {
					calca.add(new Calca());
					contadorCalca++;
				} else if (sorteio == 2 && contadorRoupa <= 1
						&& !fase3.isAtivoCeleiro()) {
					roupaChao.add(new roupaChao());
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
				} else if (camisa.get(i).getY()
						+ Constantes.CAMISA_VOANDO_HEIGHT <= 0) {
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
				} else if (calca.get(i).getY() + Constantes.CALCA_VOANDO_HEIGHT <= 0) {
					calca.remove(i);
					contadorCalca--;
				}
				continue;
			}
		}

		for (int i = 0; i < roupaChao.size(); i++) {
			// Deletar roupas no chão.
			if (roupaChao != null) {
				if (roupaChao.get(i).getX() + Constantes.ROUPA_CHAO_WIDTH <= 0) {
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
				} else if (tiro.get(i).getX() + Constantes.TIRO_WIDTH >= Constantes
						.getInstance().getWidth()
						|| tiro.get(i).getY() >= Constantes.getInstance()
								.getHeight()
						&& tiro.get(i).getY() + Constantes.TIRO_HEIGHT <= 0) {
					System.out.println("Removeu tiro");
					tiro.remove(i);
					contadorTiro--;
				}
				continue;
			}
		}

		for (int i = 0; i < garrafa.size(); i++) {
			if (garrafa != null && garrafa.get(i).isColidiu()) {
				garrafa.remove(i);
				contadorGarrafa--;
			} else if (garrafa.get(i).getX() +
					garrafa.get(i).getSprite().getWidth(null) <= 0) {
				System.out.println("Removeu tiro");
				garrafa.remove(i);
				contadorTiro--;
			}
		}
	}

	public void verificarFinal() {
		// String[] split = timer.split(":");

		if (timer.equals("00:00:03") && !faseTres) {
			fimFase = true;
		} else if (timer.equals("00:00:03") && faseTres) {
			fimFase = false;
		}
	}

	public void desenharPontos(Graphics g) {
		g.setColor(Color.WHITE);
		pontosFinal = omo.getPontos();
		g.setFont(new Font("Arial", Font.BOLD, 100));
		if (pause) {
			if (faseTres) {
				g.setFont(new Font("Arial", Font.BOLD, 45));
				g.drawString("Derrote o Quortonn", 302, 380);
			} else if (pontosFinal >= pontosFase) {
				g.setFont(new Font("Arial", Font.BOLD, 45));
				g.drawString("Pontos suficientes ", 320, 360);
				g.drawString("alcançados!", 390, 410);
			} else if (pontosFinal >= 0 && pontosFinal <= 9) {
				g.setFont(new Font("Arial", Font.BOLD, 100));
				g.drawString(
						String.valueOf(Math.abs(pontosFinal - pontosFase)),
						445, 400);
			} else if (pontosFinal >= 10 && pontosFinal <= 99) {
				g.drawString(
						String.valueOf(Math.abs(pontosFinal - pontosFase)),
						430, 400);

			} else if (pontosFinal >= 100 && pontosFinal <= 999) {
				g.drawString(
						String.valueOf(Math.abs(pontosFinal - pontosFase)),
						400, 400);
			}

		} else if (vencer || vencerOkay || gameOver) {
			if (pontosFinal >= 0 && pontosFinal <= 9) {
				g.drawString(String.valueOf(Math.abs(pontosFinal)), 490, 320);
			} else if (pontosFinal >= 10 && pontosFinal <= 99) {
				g.drawString(String.valueOf(Math.abs(pontosFinal)), 450, 320);
			} else if (pontosFinal >= 100 && pontosFinal <= 999) {
				g.drawString(String.valueOf(Math.abs(pontosFinal)), 420, 320);
			} else {
				g.drawString(String.valueOf(Math.abs(pontosFinal)), 400, 320);
			}
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

		fase1 = new Fase1();
		fase2 = new Fase2();
		fase3 = new Fase3();
		omo = new Omo();
		boss = new Boss();
	}

	// Método usado para verificar se você passou de fase ou não.
	public void victory() {
		if (faseTres) {
			if (boss.getVida() <= 0) {
				resetarCenario();
				desativarFases();
				desativarMenus();
				jogoFinal = true;
				System.out.println("jogo fim");
			}
		} else if (timer.equals("00:00:01") && !omo.isInvencivel()) {
			if (faseUm) {
				if (pontosFinal >= pontosFase) {
					vencer = true;
					faseDoisUnlocked = true;
				} else {
					vencerOkay = true;
				}
			} else if (faseDois) {
				if (pontosFinal >= pontosFase) {
					vencer = true;
					faseTresUnlocked = true;
				} else {
					vencerOkay = true;
				}
			}
			desativarFases();
		}
	}

	// Método usado para verificar se você perdeu o jogo.
	public void morrer() {
		if (omo.getVida() < 1) {
			desativarFases();
			gameOver = true;
		}
	}

	// Desativa todas as fases.
	public void desativarFases() {
		faseUm = false;
		faseDois = false;
		faseTres = false;
	}

	// Desativa alguns menus.
	public void desativarMenus() {
		jogoFinal = false;
		credits = false;
		menu = false;
		telaNivel = false;
		tutorial = false;
	}

	public void desativarHistoria() {
		historia1 = false;
		historia2 = false;
		historia3 = false;
	}

	// Getters e Setters usados.
	public static boolean isFimFase() {
		return fimFase;
	}

	public static void setFimFase(boolean fimFase) {
		OmoMultiAcao.fimFase = fimFase;
	}

	public static boolean isFaseTres() {
		return faseTres;
	}

	public static String getTimer() {
		return timer;
	}
}
