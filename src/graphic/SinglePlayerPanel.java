package graphic;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Formatter;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

import resources.Resources;

public class SinglePlayerPanel extends JPanel implements ActionListener, MouseListener {

  private static final long serialVersionUID = 1L;
  private JButton[] btnC;
  private JButton[] btnB;
  private JButton[] btnA;
  private JButton[] btnD;
  private JButton boardbtns[][];
  private JPanel boardpanel;
  private JButton[] rotates;
  private JLabel text;
  private String phraseLists[];
  private int scoreTeam1;
  private int scoreTeam2;
  private String user1;
  private String user2;
  private JLabel teamscore;
  private JButton sound;

  // mainScoreUser1 & mainScoreUser2 is the score that shown in game panel
  // upside right ne
  private int mainScoreUser1;
  private JLabel user1Label;
  private int attentionRivalLocations[];
  private int[] middleRivalStrategy;
  private int[] doubleRivalMarbles;
  private int[] attentionSelfLocations;
  private int[] middleSelfStrategies;
  private int[] doubleSelfMarbles;
  private static int planNumb;
  private static int[][] planLocations;

	public SinglePlayerPanel(String[] phrase, String username1, JButton sound1) {
		// enable user to mute the music
		sound = sound1;
		sound.setBounds(130, 620, 30, 30);
		this.add(sound);
		// The name of users that is entered in the menuPanel class
		user1 = username1;
		user2 = "PENTABALL";
		scoreTeam1 = 0;

		mainScoreUser1 = showUsersScoreInGame(username1);

		teamscore = new JLabel(username1 + "  " + scoreTeam1 + "  _  " + scoreTeam2 + "  " + user2);
		teamscore.setBounds(100, 10, 400, 30);
		teamscore.setForeground(new Color(62, 87, 141));
		this.add(teamscore);
		//
		phraseLists = new String[24];
		phraseLists = phrase;
		//
		this.setBounds(0, 0, HEIGHT, WIDTH);
		this.setBackground(new Color(186, 205, 234));
		this.setLayout(null);
		//
		user1Label = new JLabel(username1 + " " + phraseLists[14] + " " + mainScoreUser1);
		user1Label.setBounds(650, 10, 200, 30);
		user1Label.setForeground(Color.BLUE);
		this.add(user1Label);
		//
		boardpanel = new JPanel();
		boardpanel.setBounds(220, 130, 450, 430);
		boardpanel.setLayout(new GridLayout(2, 2, 15, 15));
		boardpanel.setBackground(new Color(62, 87, 141));
		//
		JPanel zoneA = new JPanel();
		zoneA.setBackground(new Color(62, 87, 141));
		zoneA.setLayout(new GridLayout(3, 3, 2, 2));
		btnA = new JButton[9];
		for (int i = 0; i < 9; i++) {
			btnA[i] = new JButton("");
			btnA[i].setBackground(Color.WHITE);
			btnA[i].addActionListener(this);
			btnA[i].addMouseListener(this);
			zoneA.add(btnA[i]);
		}
		//
		JPanel zoneB = new JPanel();
		zoneB.setBackground(new Color(62, 87, 141));
		zoneB.setPreferredSize(new Dimension(150, 150));
		zoneB.setLayout(new GridLayout(3, 3, 2, 2));
		btnB = new JButton[9];
		for (int i = 0; i < 9; i++) {
			btnB[i] = new JButton("");
			btnB[i].addActionListener(this);
			btnB[i].addMouseListener(this);
			btnB[i].setBackground(Color.WHITE);
			zoneB.add(btnB[i]);
		}
		//
		JPanel zoneC = new JPanel();
		zoneC.setBackground(new Color(62, 87, 141));
		zoneC.setPreferredSize(new Dimension(150, 150));
		zoneC.setLayout(new GridLayout(3, 3, 2, 2));
		btnC = new JButton[9];
		for (int i = 0; i < 9; i++) {
			btnC[i] = new JButton("");
			btnC[i].addActionListener(this);
			btnC[i].addMouseListener(this);
			btnC[i].setBackground(Color.WHITE);
			zoneC.add(btnC[i]);
		}
		//
		JPanel zoneD = new JPanel();
		zoneD.setBackground(new Color(62, 87, 141));
		zoneD.setPreferredSize(new Dimension(150, 150));
		zoneD.setLayout(new GridLayout(3, 3, 2, 2));
		btnD = new JButton[9];
		for (int i = 0; i < 9; i++) {
			btnD[i] = new JButton("");
			btnD[i].addActionListener(this);
			btnD[i].addMouseListener(this);
			btnD[i].setBackground(Color.WHITE);
			zoneD.add(btnD[i]);
		}
		//
		JButton exit = new JButton("");
		exit.setBounds(90, 620, 30, 30);
		exit.setBorderPainted(false);
		exit.setBorderPainted(false);
		exit.setBackground(new Color(182, 209, 252));
		exit.setIcon(new ImageIcon(Resources.getImage("close.png")));
		exit.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				exit.setIcon(new ImageIcon(Resources.getImage("open.png")));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				exit.setIcon(new ImageIcon(Resources.getImage("close.png")));
			}
		});
		exit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				String[] options = { phrase[22], phrase[23] };
				int reply = JOptionPane.showOptionDialog(null, phrase[9], phrase[3], 0, JOptionPane.INFORMATION_MESSAGE,
						null, options, null);
				if (reply == JOptionPane.YES_OPTION) {
					removeAll();
					add(getPaneExit(phraseLists));
					revalidate();
					repaint();
				}
			}
		});
		this.add(exit);
		//
		// attach all the zones and create the main board
		boardbtns = boardButtonConstructor(btnA, btnB, btnC, btnD);

		//
		boardpanel.add(zoneA);
		boardpanel.add(zoneB);
		boardpanel.add(zoneC);
		boardpanel.add(zoneD);
		this.add(boardpanel);
		rotates = rotateBtns();
		this.add(rotates[0]);
		this.add(rotates[1]);
		this.add(rotates[2]);
		this.add(rotates[3]);
		this.add(rotates[4]);
		this.add(rotates[5]);
		this.add(rotates[6]);
		this.add(rotates[7]);
		text = text();
		this.add(text);

	}

	public JButton getClockwisebtn(JButton[] btnZone, JButton[][] boardbtns) {
		ClockwiseRotate clockwise = new ClockwiseRotate(btnZone, boardbtns);
		clockwise.setSize(new Dimension(10, 10));
		// create the rotate buttons use the default characteristics defined in
		// clock
		if (btnZone == btnA) {
			clockwise.setBounds(155, 130, 70, 70);
			clockwise.setIcon(new ImageIcon(Resources.getImage("A2.png")));
		}
		if (btnZone == btnB) {
			clockwise.setBounds(610, 65, 70, 70);
			clockwise.setIcon(new ImageIcon(Resources.getImage("B2.png")));
		}
		if (btnZone == btnC) {
			clockwise.setBounds(210, 555, 70, 70);
			clockwise.setIcon(new ImageIcon(Resources.getImage("C2.png")));
		}
		if (btnZone == btnD) {
			clockwise.setBounds(665, 490, 70, 70);
			clockwise.setIcon(new ImageIcon(Resources.getImage("D2.png")));
		}
		return clockwise;
	}

	public JButton getCounterClockwisebtn(JButton[] btnZone, JButton[][] boardbtns) {
		CounterClockwiseRotate CounterClockwise = new CounterClockwiseRotate(btnZone, boardbtns);
		CounterClockwise.setSize(new Dimension(10, 10));
		if (btnZone == btnA) {
			CounterClockwise.setBounds(210, 65, 70, 70);
			CounterClockwise.setIcon(new ImageIcon(Resources.getImage("A1.png")));
		}
		if (btnZone == btnB) {
			CounterClockwise.setBounds(665, 130, 70, 70);
			CounterClockwise.setIcon(new ImageIcon(Resources.getImage("B1.png")));
		}
		if (btnZone == btnC) {
			CounterClockwise.setBounds(155, 490, 70, 70);
			CounterClockwise.setIcon(new ImageIcon(Resources.getImage("C1.png")));
		}
		if (btnZone == btnD) {
			CounterClockwise.setBounds(610, 555, 70, 70);
			CounterClockwise.setIcon(new ImageIcon(Resources.getImage("D1.png")));
		}
		return CounterClockwise;

	}

	//
	private JButton[] rotateBtns() {
		JButton[] rotate = new JButton[8];
		rotate[0] = getClockwisebtn(btnA, boardbtns);
		rotate[1] = getClockwisebtn(btnB, boardbtns);
		rotate[2] = getClockwisebtn(btnC, boardbtns);
		rotate[3] = getClockwisebtn(btnD, boardbtns);
		rotate[4] = getCounterClockwisebtn(btnA, boardbtns);
		rotate[5] = getCounterClockwisebtn(btnB, boardbtns);
		rotate[6] = getCounterClockwisebtn(btnC, boardbtns);
		rotate[7] = getCounterClockwisebtn(btnD, boardbtns);
		for (int i = 0; i < 8; i++) {
			rotate[i].setVisible(false);
			rotate[i].addMouseListener(new MouseAdapter() {
				@Override
				public void mouseReleased(MouseEvent e) {
					// when you click and release the mouse button all the
					// buttons will disappear!
					for (int j = 0; j < 8; j++) {
						rotate[j].setVisible(false);
						text.setText(phraseLists[4]);
						revalidate();
						repaint();
					}
					// after every rotate check victory situation
					int[] victory = victorySituation(boardbtns);
					if (victory[0] != 9) {
						hasBeenWon(0);
					}
					// after every rotate check if there is any white button or
					// not
					nowinner();
				}
			});

			rotate[i].addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {

					final Timer t = new Timer(100, new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent evt) {
							revalidate();
							repaint();
							attentionRivalLocations = new int[] { 10, 10, 10, 10 };
							middleRivalStrategy = new int[] { 10, 10, 10, 10 };
							doubleRivalMarbles = new int[] { 10, 10, 10, 10 };
							attentionSelfLocations = new int[] { 10, 10, 10, 10 };
							middleSelfStrategies = new int[] { 10, 10, 10, 10 };
							doubleSelfMarbles = new int[] { 10, 10, 10, 10 };
							planNumb = 0;
							planLocations = putPlan(planMaker(), planNumb);
							revalidate();
							JButton[][] latestboard = boardbtns;
							boardbtns = RobotAction(latestboard, btnA, btnB, btnC, btnD);
							revalidate();
							repaint();
							int[] victory = victorySituation(boardbtns);
							if (victory[0] != 9) {
								hasBeenWon(0);
							}
						}
					});
					t.setRepeats(false);
					t.start();

				}
			});
		}
		return rotate;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		boolean rotate = true;
		// check victory situation
		int[] victory = victorySituation(boardbtns);
		if (victory[0] != 9) {
			rotate = hasBeenWon(1);
			for (int i = 0; i < 6; i++) {
				for (int j = 0; j < 6; j++) {
					// if the user wants to play again set all the button colors
					// white
					boardbtns[i][j].setBackground(Color.WHITE);
					boardbtns[i][j].setEnabled(true);
					repaint();
				}
			}
		}

		if (rotate == true) {
			// if rotate is not needed don't show it again. (else when you wants
			// to play again it show the rotate buttons wrongly)
			onlyRotate(boardbtns);
		}
		this.repaint();
		for (int j = 0; j < 6; j++) {
			for (int i = 0; i < victory.length; i++) {
				// put the marble in board
				if (boardbtns[j][i] == e.getSource()) {
					boardbtns[j][i].setBackground(Color.BLUE);
					boardbtns[j][i].setEnabled(false);

					break;
				}
			}
		}

		if (victory[0] != 9) {
			rotate = hasBeenWon(1);
			for (int i = 0; i < 6; i++) {
				for (int k = 0; k < 6; k++) {
					boardbtns[i][k].setBackground(Color.WHITE);
					boardbtns[i][k].setEnabled(true);
					repaint();
				}
			}
		}

	}

	public void mouseClicked(MouseEvent arg0) {

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// when you enter the button shows the marble
		for (int j = 0; j < 6; j++) {
			for (int i = 0; i < 6; i++) {
				if (boardbtns[j][i] == e.getSource()) {
					if (boardbtns[j][i].isEnabled() == true) {
						boardbtns[j][i].setBackground(Color.BLUE);
					}
				}
			}
		}
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// when you exit the button it will be disappeared
		for (int j = 0; j < 6; j++) {
			for (int i = 0; i < 6; i++) {
				if (boardbtns[j][i] == e.getSource()) {
					if (boardbtns[j][i].isEnabled() == true) {
						boardbtns[j][i].setBackground(Color.WHITE);
					}
				}
			}
		}
	}

	@Override
	public void mousePressed(MouseEvent arg0) {

	}

	@Override
	public void mouseReleased(MouseEvent e) {

	}

	//
	private JButton[][] boardButtonConstructor(JButton[] zoneA, JButton[] zoneB, JButton[] zoneC, JButton[] zoneD) {
		JButton[][] boardbtns = new JButton[6][6];
		// attach all the zones together
		int i = 0;
		for (int j = 0; j <= 2; j++) {
			boardbtns[0][j] = zoneA[i];
			i++;
		}
		i = 0;
		for (int j = 0; j <= 2; j++) {
			boardbtns[3][j] = zoneC[i];
			i++;
		}
		i = 0;
		for (int j = 3; j <= 5; j++) {
			boardbtns[0][j] = zoneB[i];
			i++;
		}
		i = 0;
		for (int j = 3; j <= 5; j++) {
			boardbtns[3][j] = zoneD[i];
			i++;
		}
		i = 3;
		for (int j = 0; j <= 2; j++) {
			boardbtns[1][j] = zoneA[i];
			i++;
		}
		i = 3;
		for (int j = 0; j <= 2; j++) {
			boardbtns[4][j] = zoneC[i];
			i++;
		}
		i = 3;
		for (int j = 3; j <= 5; j++) {
			boardbtns[1][j] = zoneB[i];
			i++;
		}
		i = 3;
		for (int j = 3; j <= 5; j++) {
			boardbtns[4][j] = zoneD[i];
			i++;
		}
		i = 6;
		for (int j = 0; j <= 2; j++) {
			boardbtns[2][j] = zoneA[i];
			i++;
		}
		i = 6;
		for (int j = 0; j <= 2; j++) {
			boardbtns[5][j] = zoneC[i];
			i++;
		}
		i = 6;
		for (int j = 3; j <= 5; j++) {
			boardbtns[2][j] = zoneB[i];
			i++;
		}
		i = 6;
		for (int j = 3; j <= 5; j++) {
			boardbtns[5][j] = zoneD[i];
			i++;
		}
		return boardbtns;
	}

	//
	public JLabel text() {

		JLabel nowRotate = new JLabel(phraseLists[4], JLabel.CENTER);
		// the main text that shows what you should do
		Font font = new Font("Courier", Font.BOLD, 35);
		nowRotate.setFont(font);
		nowRotate.setLayout(null);
		nowRotate.setBounds(0, 35, 900, 60);
		nowRotate.setForeground(new Color(62, 87, 141));
		return nowRotate;
	}

	//
	public void onlyRotate(JButton[][] boardbtns) {

		rotates[0].setVisible(true);
		rotates[1].setVisible(true);
		rotates[2].setVisible(true);
		rotates[3].setVisible(true);
		rotates[4].setVisible(true);
		rotates[5].setVisible(true);
		rotates[6].setVisible(true);
		rotates[7].setVisible(true);
		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 6; j++) {
				boardbtns[i][j].setEnabled(false);
			}
		}
		text.setText(phraseLists[5]);
		this.revalidate();
		this.repaint();

	}

	//
	public static int[] victorySituation(JButton[][] boardbtns) {

		// if someone win ==> array [0]= i , [1] = j if some one not wins array
		// [0] = 9 , [1] = 9

		int[] winerPiecesLocation = new int[2];
		winerPiecesLocation[0] = 9;
		winerPiecesLocation[1] = 9;
		// winerPiecesLocation = null;
		boolean victory = false;
		for (int i = 0; i < 6 && victory == false; i++) {
			for (int j = 0; j < 6 && victory == false; j++) {
				if (victoryCheck(i, j, boardbtns) == true) {
					victory = true;
					winerPiecesLocation[0] = i;
					winerPiecesLocation[1] = j;
					return winerPiecesLocation;
				}

			}
		}
		return winerPiecesLocation;
	}

	//
	public static boolean victoryCheck(int i, int j, JButton[][] boardbtns) {

		int x;
		int y;
		int wins;

		if (boardbtns[i][j].getBackground() == Color.WHITE) {
			return false;
		} else {

			// check up
			wins = 0;
			y = j;

			for (x = i - 1; x >= 0; x--) {
				if (checkThePlaceToBeInPage(x, y) == true) {

					if (sameTeam(i, j, x, y, boardbtns) == true) {
						wins++;

						if (wins >= 4) {
							return true;
						}
					} else {
						break;
					}

				} else {
					break;
				}
			}

			// check down]
			wins = 0;
			y = j;

			for (x = i + 1; x < 6; x++) {
				if (checkThePlaceToBeInPage(x, y) == true) {

					if (sameTeam(i, j, x, y, boardbtns) == true) {
						wins++;

						if (wins >= 4) {
							return true;
						}
					} else {
						break;
					}

				} else {
					break;
				}
			}

			// check right
			wins = 0;
			x = i;

			for (y = j + 1; y < 6; y++) {
				if (checkThePlaceToBeInPage(x, y) == true) {

					if (sameTeam(i, j, x, y, boardbtns) == true) {
						wins++;

						if (wins >= 4) {
							return true;
						}
					} else {
						break;
					}

				} else {
					break;
				}
			}

			// check left
			wins = 0;
			x = i;

			for (y = j - 1; y >= 0; y--) {
				if (checkThePlaceToBeInPage(x, y) == true) {

					if (sameTeam(i, j, x, y, boardbtns) == true) {
						wins++;

						if (wins >= 4) {
							return true;
						}
					} else {
						break;
					}

				} else {
					break;
				}
			}

			// check up left
			wins = 0;
			for (y = j - 1, x = i - 1; x >= 0 && y >= 0; y--, x--) {
				if (checkThePlaceToBeInPage(x, y) == true) {

					if (sameTeam(i, j, x, y, boardbtns) == true) {
						wins++;

						if (wins >= 4) {
							return true;
						}
					} else {
						break;
					}

				} else {
					break;
				}
			}

			// check up right
			wins = 0;
			for (y = j + 1, x = i - 1; x >= 0 && y < 6; y++, x--) {
				if (checkThePlaceToBeInPage(x, y) == true) {

					if (sameTeam(i, j, x, y, boardbtns) == true) {
						wins++;

						if (wins >= 4) {
							return true;
						}
					} else {
						break;
					}

				} else {
					break;
				}
			}

			// check down left
			wins = 0;
			for (y = j - 1, x = i + 1; x < 6 && y >= 0; y--, x++) {
				if (checkThePlaceToBeInPage(x, y) == true) {

					if (sameTeam(i, j, x, y, boardbtns) == true) {
						wins++;

						if (wins >= 4) {
							return true;
						}
					} else {
						break;
					}

				} else {
					break;
				}
			}

			// check down right
			wins = 0;
			for (y = j + 1, x = i + 1; x < 6 && y < 6; y++, x++) {
				if (checkThePlaceToBeInPage(x, y) == true) {

					if (sameTeam(i, j, x, y, boardbtns) == true)

					{
						wins++;

						if (wins >= 4) {
							return true;
						}
					} else {
						break;
					}

				} else {
					break;
				}
			}

			return false;
		}
	}

	//
	public static boolean sameTeam(int i, int j, int x, int y, JButton[][] boardbtns) {
		if (boardbtns[x][y].getBackground() == (boardbtns[i][j]).getBackground()) {
			return true;
		} else {
			return false;
		}

	}

	//
	public static boolean checkThePlaceToBeInPage(int x, int y) {
		if (x >= 0 && y >= 0 && x < 6 && x < 6) {
			return true;
		} else {
			return false;
		}
	}

	//
	private JPanel getPaneExit(String[] phrase) {
		MenuPanel panel = new MenuPanel(phrase, sound);
		panel.setSize(new Dimension(1000, 1000));
		panel.setBounds(0, 0, 1100, 1080);
		return panel;

	}

	public void nowinner() {
		// check if there is any white button or not
		boolean nowinner = true;
		for (int i = 0; i < 6; i++) {
			if (nowinner == false) {
				break;
			}
			for (int j = 0; j < 6; j++) {
				if (boardbtns[i][j].getBackground() == Color.WHITE) {
					nowinner = false;
					break;
				}
			}
		}
		if (nowinner == true) {
			// check if user wants to play again or not
			JOptionPane.showMessageDialog(null, phraseLists[6] + "");
			String[] options = { phraseLists[22], phraseLists[23] };
			int reply = JOptionPane.showOptionDialog(null, phraseLists[18], phraseLists[3], 0,
					JOptionPane.INFORMATION_MESSAGE, null, options, null);
			for (int i = 0; i < 6; i++) {
				for (int j = 0; j < 6; j++) {
					boardbtns[i][j].setBackground(Color.WHITE);
					boardbtns[i][j].setEnabled(true);

				}
			}
			//
			if (reply == JOptionPane.NO_OPTION) {

				// show and save users score
				teamscore.setText(user1 + " " + scoreTeam1 + " _ " + scoreTeam2 + " " + user2);
				user1Label.setText(user1 + " " + phraseLists[14] + " " + mainScoreUser1);
				saveUsersScore(mainScoreUser1, user1);
				// if no go back to menu
				removeAll();
				add(getPaneExit(phraseLists));
				revalidate();
				repaint();
			}
			if (reply == JOptionPane.YES_OPTION) {
				for (int i = 0; i < 8; i++) {
					rotates[i].setVisible(false);
				}
				// if yes make all the buttons white
				for (int i = 0; i < 6; i++) {
					for (int j = 0; j < 6; j++) {
						boardbtns[i][j].setBackground(Color.WHITE);
						boardbtns[i][j].setEnabled(true);

					}
				}
				for (int i = 0; i < 8; i++) {
					rotates[i].setVisible(false);
				}
				text.setText(phraseLists[4]);

				for (int i = 0; i < 6; i++) {
					for (int j = 0; j < 6; j++) {
						boardbtns[i][j].setBackground(Color.WHITE);
						boardbtns[i][j].setEnabled(true);

					}
				}
				//
				teamscore.setText(user1 + " " + scoreTeam1 + " _ " + scoreTeam2 + " " + user2);
				user1Label.setText(user1 + " " + phraseLists[14] + " " + mainScoreUser1);
				saveUsersScore(mainScoreUser1, user1);
				repaint();
				revalidate();
			}
		}

	}

	//
	//
	public Boolean hasBeenWon(int rotate) {
		boolean rotateNeeded = true;
		int[] victory = victorySituation(boardbtns);
		// if rotate=0 the last action was rotate
		// if rotate=1 the last action was marble putting
		if (victory[0] != 9) {
			if (boardbtns[victory[0]][victory[1]].getBackground() == Color.RED) {
				JOptionPane.showMessageDialog(null, phraseLists[6] + " " + user2 + "!");
				scoreTeam2++;
			} else {
				JOptionPane.showMessageDialog(null, phraseLists[6] + " " + user1 + "!");
				mainScoreUser1++;
				scoreTeam1++;
			}

		}
		if (victory[0] != 9) {
			String[] options = { phraseLists[22], phraseLists[23] };
			int reply = JOptionPane.showOptionDialog(null, phraseLists[18], phraseLists[3], 0,
					JOptionPane.INFORMATION_MESSAGE, null, options, null);
			for (int i = 0; i < 6; i++) {
				for (int j = 0; j < 6; j++) {
					boardbtns[i][j].setBackground(Color.WHITE);
					boardbtns[i][j].setEnabled(true);

				}
			}
			if (reply == JOptionPane.NO_OPTION) {

				// show and save users score

				if (boardbtns[victory[0]][victory[1]].getBackground() == Color.RED) {
					scoreTeam2++;
				} else {
					mainScoreUser1++;
					scoreTeam1++;
				}

				teamscore.setText(user1 + " " + scoreTeam1 + " _ " + scoreTeam2 + " " + user2);
				user1Label.setText(user1 + " " + phraseLists[14] + " " + mainScoreUser1);
				saveUsersScore(mainScoreUser1, user1);
				//
				removeAll();
				add(getPaneExit(phraseLists));
				revalidate();
				repaint();
			}
			if (reply == JOptionPane.YES_OPTION) {
				if (rotate == 1) {
					rotateNeeded = false;
				}
				victory[0] = 9;
				victory[1] = 9;
				for (int i = 0; i < 8; i++) {
					rotates[i].setVisible(false);
				}
				for (int i = 0; i < 6; i++) {
					for (int j = 0; j < 6; j++) {
						boardbtns[i][j].setBackground(Color.WHITE);
						boardbtns[i][j].setEnabled(true);

					}
				}
				for (int i = 0; i < 8; i++) {
					rotates[i].setVisible(false);
				}
				text.setText(phraseLists[4]);

				for (int i = 0; i < 6; i++) {
					for (int j = 0; j < 6; j++) {
						boardbtns[i][j].setBackground(Color.WHITE);
						boardbtns[i][j].setEnabled(true);

					}
				}
				//
				teamscore.setText(user1 + " " + scoreTeam1 + " _ " + scoreTeam2 + " " + user2);
				user1Label.setText(user1 + " " + phraseLists[14] + " " + mainScoreUser1);
				saveUsersScore(mainScoreUser1, user1);

				repaint();
				revalidate();
			}
		}
		return rotateNeeded;
	}

	// file + scores
	public static String[] readFile() {
		// this method read files from data.txt and change them to string
		// if it was empty, it return *
		File allsource = new File("src/data.txt");

		try {
			// for if you don't have file
			//
			if (!allsource.exists()) {
				allsource.createNewFile();
				String[] all = new String[5];
				makeArrFullOfStar(all); // make one star array
				return all;
			}

			// reading from file
			Scanner sc = new Scanner(allsource);
			String[] all = new String[200];

			if (sc.hasNextLine()) {
				for (int i = 0; sc.hasNextLine(); i++) {
					all[i] = sc.nextLine();
				}
			} else {
				all = new String[5];
				makeArrFullOfStar(all); // make one star array

			}
			sc.close();
			return all;

		} catch (Exception e) {

			e.printStackTrace();
			return null;
		}

	}

	public static void makeArrFullOfStar(String[] arr) {
		for (int i = 0; i < arr.length; i++) {
			arr[i] = "*";
		}
	}

	public static int showUsersScoreInGame(String username) {
		// this method read the user score from file and show it in game panel

		// reading file and put it in the String []
		String[] previousData = readFile();

		File allsource = new File("src/data.txt");

		// when the file is empty or it is make right now and there is no info
		if (previousData[0].equals("*")) {
			try {

				Formatter output = new Formatter(allsource);
				int zero = 0;
				output.format("%s&%d%n", username, zero);
				output.close();
				return 0;

			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		} // if the database text file is not empty :
		return Integer.valueOf(userScore(username));

	}

	public static String userScore(String username) {
		// this method check the user name and score

		// get the data string
		String[] data = readFile();
		//
		for (int i = 0; i <= data.length; i++) {
			// when the counter arrive to the
			if (data[i] == null) {
				break;
			}

			// split the Name%Score temp[0] = name , temp[1] = score
			String[] temp = data[i].split("&");

			// if we have the user score then it return the user score
			if (temp[0].equals(username)) {
				return temp[1];
			}

		}
		// if we have not the user score, so:

		// first we put user's name and score"0" into our data.txt (data base)
		String[] all = readFile();
		File allsource = new File("src/data.txt");

		try {
			Formatter output = new Formatter(allsource);
			for (int i = 0; i < all.length; i++) {

				if (all[i] == null) {
					break;
				}
				output.format("%s%n", all[i]);
			}
			int zero = 0;
			output.format("%s&%d%n", username, zero);
			output.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		// then we return zero to the game panel
		return "0";

	}

	public static void saveUsersScore(int score, String user) {
		// this method save the user score in the database file (src/data.txt)

		File allsource = new File("src/data.txt");
		String[] all = readFile();

		for (int i = 0; i < all.length; i++) {
			if (all[i] == null) {
				break;
			}
			String[] splited = all[i].split("&");
			if (splited[0].equals(user)) {
				all[i] = splited[0] + "&" + Integer.toString(score);

				try {
					Formatter saveToFile = new Formatter(allsource);
					for (int x = 0; x < all.length; x++) {

						if (all[x] == null) {
							break;
						}

						saveToFile.format("%s%n", all[x]);
					}
					saveToFile.close();
					break;

				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}

			}

		}

	}

	public static boolean existingUsername(String username) {
		// get the data string
		String[] data = readFile();
		//
		for (int i = 0; i <= data.length; i++) {
			// when the counter arrive to the
			if (data[i] == null) {
				return false;

			}

			// split the Name%Score temp[0] = name , temp[1] = score
			String[] temp = data[i].split("&");

			// if we have the user score then it return the user score
			if (temp[0].equals(username)) {
				return true;
			}

		}
		return false;

	}

	// robot color is red
	// user color is blue
public JButton[][] RobotAction(JButton[][] boardbtns, JButton[] zoneA, JButton[] zoneB, JButton[] zoneC,
      JButton[] zoneD) {
    rivalAvoidToWin(boardbtns, zoneA, zoneB, zoneC, zoneD);
    tryToWin(boardbtns, zoneA, zoneB, zoneC, zoneD);
    int i = 0;
    int j = 0;
    int board = 0;
    int turn = 0;
    // The default numbers of array are all 10
    // if it was not 10 so there is a good choice in it
 // if it was not 10 so there is a good choice in it
    // first step check if you can win with putting one marble or not
    JButton [][] rotatedBtn = boardbtns;
    do {
    	
    
    
    if (attentionSelfLocations[0] != 10) {
      i = attentionSelfLocations[0];
      j = attentionSelfLocations[1];
      board = attentionSelfLocations[2];
      turn = attentionSelfLocations[3];
      //...
    } else if (middleSelfStrategies[0] != 10) {
      i = middleSelfStrategies[0];
      j = middleSelfStrategies[1];
      board = middleSelfStrategies[2];
      turn = middleSelfStrategies[3];
      //...
    }  else if (doubleSelfMarbles[0] != 10) {
      i = doubleSelfMarbles[0];
      j = doubleSelfMarbles[1];
      board = doubleSelfMarbles[2];
      turn = doubleSelfMarbles[3];
    }else if (attentionRivalLocations[0] != 10) {
      i = attentionRivalLocations[0];
      j = attentionRivalLocations[1];
      board = attentionRivalLocations[2];
      turn = attentionRivalLocations[3];
      // // prevent rival
    } else if (middleRivalStrategy[0] != 10) {
      i = middleRivalStrategy[0];
      j = middleRivalStrategy[1];
      board = middleRivalStrategy[2];
      turn = middleRivalStrategy[3];
      //...
    } else if (doubleRivalMarbles[0] != 10) {
      i = doubleRivalMarbles[0];
      j = doubleRivalMarbles[1];
      board = doubleRivalMarbles[2];
      turn = doubleRivalMarbles[3];
      //// use your strategies otherwise put randomly
    } else {
      //
      int [] movement = robotSrategyPlayer(boardbtns, zoneA, zoneB, zoneC, zoneD);
      
      i = movement[0];
      j = movement[1];
      board = movement[2];
      turn = movement[3];
    }// board=0--> zoneA =1-->zoneB =2-->zoneC =3-->zoneD
    // turn=0--> clockwiseRotate   =1-->  conterClockwiseRotate
    
    if (board == 0) {
      if (turn == 0) {
    	  rotatedBtn = clockwiseRotateRobat(0, boardbtns, zoneA, zoneB, zoneC, zoneD);
      } else if (turn == 1) {
    	  rotatedBtn = conterClockwiseRotateRobat(0, boardbtns, zoneA, zoneB, zoneC, zoneD);
      }
    }
    if (board == 1) {
      if (turn == 0) {
    	  rotatedBtn = clockwiseRotateRobat(1, boardbtns, zoneA, zoneB, zoneC, zoneD);
      } else if (turn == 1) {
    	  rotatedBtn = conterClockwiseRotateRobat(1, boardbtns, zoneA, zoneB, zoneC, zoneD);
      }
    }
    if (board == 2) {
      if (turn == 0) {
    	  rotatedBtn = clockwiseRotateRobat(2, boardbtns, zoneA, zoneB, zoneC, zoneD);
      } else if (turn == 1) {
    	  rotatedBtn = conterClockwiseRotateRobat(2, boardbtns, zoneA, zoneB, zoneC, zoneD);
      }
      if (board == 3) {
        if (turn == 0) {
        	rotatedBtn = clockwiseRotateRobat(3, boardbtns, zoneA, zoneB, zoneC, zoneD);
        } else if (turn == 1) {
        	rotatedBtn = conterClockwiseRotateRobat(3, boardbtns, zoneA, zoneB, zoneC, zoneD);
        }
      }
    }
    }while(rotatedBtn[i][j].getBackground()!=Color.WHITE);
    rotatedBtn[i][j].setBackground(Color.RED);
    rotatedBtn[i][j].setEnabled(false);
    repaint();
    revalidate();
    return rotatedBtn;
  }
	public void rivalAvoidToWin(JButton[][] boardbtns, JButton[] zoneA, JButton[] zoneB, JButton[] zoneC,
			JButton[] zoneD) {
		JButton[][] rotatedBoardbtns = boardbtns;
		// String[] whichBoard = { "btnA", "btnB", "btnC", "btnD" };
		// JButton[][] whichBoardButton = { zoneA, zoneB, zoneC, zoneD };
		// String[] rotate = { "clockwise", "counter-clockwise" };
		String[] side = { "right", "left", "up", "down", "upRight", "upLeft", "downLeft", "downRight" };
		for (int j = 0; j < 4; j++) {
			//check all boards
			for (int j2 = 0; j2 < 2; j2++) {
				//in both rotation manners
				for (int i = 0; i < side.length; i++) {
					//all buttons in all sides
					if (j2 == 0) {
						rotatedBoardbtns = clockwiseRotateRobat(j, boardbtns, zoneA, zoneB, zoneC, zoneD);
					} else {
						rotatedBoardbtns = conterClockwiseRotateRobat(j, boardbtns, zoneA, zoneB, zoneC, zoneD);
					}
					possibilityFinder(rotatedBoardbtns, j, j2, "rival", side[i]);

				}
			}
		}

	}

	public void tryToWin(JButton[][] boardbtns, JButton[] zoneA, JButton[] zoneB, JButton[] zoneC, JButton[] zoneD) {
		// check all board in both turn
		JButton[][] rotatedBoardbtns = boardbtns;
		// String[] rotate = { "clockwise", "counter-clockwise" };
		// JButton[][] whichBoard = { zoneA, zoneB, zoneC, zoneD };
		String[] side = { "right", "left", "up", "down", "upRight", "upLeft", "downLeft", "downRight" };
		for (int j = 0; j < 4; j++) {
			//check all boards
			for (int j2 = 0; j2 < 2; j2++) {
				//in both rotation manners
				for (int i = 0; i < side.length; i++) {
					if (j2 == 0) {
						rotatedBoardbtns = clockwiseRotateRobat(j, boardbtns, zoneA, zoneB, zoneC, zoneD);
					} else {
						rotatedBoardbtns = conterClockwiseRotateRobat(j, boardbtns, zoneA, zoneB, zoneC, zoneD);
					}
					possibilityFinder(rotatedBoardbtns, j, j2, "rival", side[i]);
				}

			}
		}
	}

	public int[][] sidesAnalyse(String side, int k, int k2) {
		// how to check in different side-Check
		if (side.equals("right")) {
			int[][] location = { { k, k2 + 1 }, { k, k2 + 2 }, { k, k2 + 3 }, { k, k2 + 4 } };
			location = locationArrayCheck(location);
			return location;
		} else if (side.equals("left")) {
			int[][] location = { { k, k2 - 1 }, { k, k2 - 2 }, { k, k2 - 3 }, { k, k2 - 4 } };
			location = locationArrayCheck(location);
			return location;
		} else if (side.equals("up")) {
			int[][] location = { { k - 1, k2 }, { k - 2, k2 }, { k - 3, k2 }, { k - 4, k2 } };
			location = locationArrayCheck(location);
			return location;
		} else if (side.equals("down")) {
			int[][] location = { { (k + 1), (k2) }, { (k + 2), (k2) }, { (k + 3), k2 }, { (k + 4), k2 } };
			location = locationArrayCheck(location);
			return location;
		} else if (side.equals("upRight")) {
			int[][] location = { { (k - 1), (k2 + 1) }, { (k - 2), (k2 + 2) }, { (k - 3), (k2 + 3) },
					{ (k - 4), (k2 + 4) } };
			location = locationArrayCheck(location);
			return location;
		} else if (side.equals("upLeft")) {
			int[][] location = { { (k - 1), (k2 - 1) }, { (k - 2), (k2 - 2) }, { (k - 3), (k2 - 3) },
					{ (k - 4), (k2 - 4) } };
			location = locationArrayCheck(location);
			return location;
		} else if (side.equals("downLeft")) {
			int[][] location = { { (k + 1), (k2 - 1) }, { (k + 2), (k2 - 2) }, { (k + 3), (k2 - 3) },
					{ (k + 4), (k2 - 4) } };
			location = locationArrayCheck(location);
			return location;
		} else if (side.equals("downRight")) {
			int[][] location = { { (k + 1), (k2 + 1) }, { (k + 2), (k2 + 2) }, { (k + 3), (k2 + 3) },
					{ (k + 4), (k2 + 4) } };
			location = locationArrayCheck(location);
			return location;
		} else {
			int[][] location = { { 10, 10 }, { 10, 10 }, { 10, 10 }, { 10, 10 } };
			return location;
		}
	}

	public int[][] locationArrayCheck(int[][] location) {
		// if there is any invalid location make the first place of array 10
		boolean validLocation = true;
		for (int i = 0; i < 4; i++) {
			if (location[i][0] < 0 || location[i][0] >= 6 || location[i][1] < 0 || location[i][1] >= 6) {
				location[0][0] = 10;
				validLocation = false;
				break;
			}
		}
		if (validLocation == true) {
			return location;
		} else {
			location[0][0] = 10;
			return location;
		}
	}

	public void possibilityFinder(JButton[][] boardbtns, int whichBoard, int rotate, String rivalOrSelf, String side) {
    // String[] whichBoard = { "btnA", "btnB", "btnC", "btnD" };
    // String[] rotate = { "clockwise", "unclockwise" };
    for (int k = 0; k < 6; k++) {
      for (int k2 = 0; k2 < 6; k2++) {
        // check that next step exists in the board or not
        int[][] location = sidesAnalyse(side, k, k2);
        if (location[0][0] != 10) {
          if (rivalOrSelf.equals("rival")) {
            if (boardbtns[k][k2].getBackground() == Color.BLUE) {
              // prevent rival to win or Try itself to win
              if (boardbtns[location[0][0]][location[0][1]].getBackground() == Color.BLUE
                  && boardbtns[location[1][0]][location[1][1]].getBackground() == Color.BLUE
                  && boardbtns[location[2][0]][location[2][1]].getBackground() == Color.BLUE
                  && boardbtns[location[3][0]][location[3][1]].getBackground() == Color.WHITE) {
                // 4 joined
                attentionRivalLocations = new int[] { location[3][0], location[3][1], whichBoard,
                    rotate };
              } else if (boardbtns[location[0][0]][location[0][1]].getBackground() == Color.BLUE
                  && boardbtns[location[1][0]][location[1][1]].getBackground() == Color.BLUE
                  && boardbtns[location[2][0]][location[2][1]].getBackground() == Color.WHITE
                  && boardbtns[location[1][0]][location[1][1]].getBackground() == Color.BLUE) {
                // middle strategy
                middleRivalStrategy = new int[] { location[2][0], location[2][1], whichBoard, rotate };
              } 
              else if (boardbtns[location[0][0]][location[0][1]].getBackground() == Color.BLUE
                  && boardbtns[location[1][0]][location[1][1]].getBackground() == Color.WHITE
                  && boardbtns[location[2][0]][location[2][1]].getBackground() == Color.BLUE
                  && boardbtns[location[3][0]][location[3][1]].getBackground() == Color.BLUE) {
                // prevent three marbles
                doubleRivalMarbles = new int[] { location[1][0], location[1][1], whichBoard, rotate };
              }
            }
          } else if (rivalOrSelf.equals("self")) {
            if (boardbtns[k][k2].getBackground() == Color.RED) {
              // prevent rival to win or Try itself to win
              if (boardbtns[location[0][0]][location[0][1]].getBackground() == Color.RED
                  && boardbtns[location[1][0]][location[1][1]].getBackground() == Color.RED
                  && boardbtns[location[2][0]][location[2][1]].getBackground() == Color.RED
                  && boardbtns[location[3][0]][location[3][1]].getBackground() == Color.WHITE) {
                // 4marbles joined
                attentionSelfLocations = new int[] { location[3][0], location[3][1], whichBoard,
                    rotate };
              } else if (boardbtns[location[0][0]][location[0][1]].getBackground() == Color.RED
                  && boardbtns[location[1][0]][location[1][1]].getBackground() == Color.RED
                  && boardbtns[location[2][0]][location[2][1]].getBackground() == Color.WHITE
                  && boardbtns[location[3][0]][location[3][1]].getBackground() == Color.RED) {
                // middle strategy
                middleSelfStrategies = new int[] { location[2][0], location[2][1], whichBoard, rotate };
            }else if (boardbtns[location[0][0]][location[0][1]].getBackground() == Color.RED
                && boardbtns[location[1][0]][location[1][1]].getBackground() == Color.WHITE
                && boardbtns[location[2][0]][location[2][1]].getBackground() == Color.RED
                && boardbtns[location[3][0]][location[3][1]].getBackground() == Color.RED) {
              // 4marbles joined
              doubleSelfMarbles = new int[] { location[1][0], location[1][1], whichBoard,
                  rotate };
            } 
            }
          }
        }
      }
    }

  }
	public JButton[][] clockwiseRotateRobat(int whichBoard, JButton[][] boardBtn, JButton[] zoneA, JButton[] zoneB,
			JButton[] zoneC, JButton[] zoneD) {
		JButton[] btnZone;
		if (whichBoard == 0) {
			btnZone = zoneA;
		} else if (whichBoard == 1) {
			btnZone = zoneB;
		} else if (whichBoard == 2) {
			btnZone = zoneC;
		} else if (whichBoard == 3) {
			btnZone = zoneD;
		} else {
			btnZone = zoneA;
		}
		for (int i = 0; i < 9; i++) {
			if (i == 4) {
				continue;
			}
			btnZone[i].setEnabled(true);
		}
		JButton a = new JButton();
		a.setBackground(btnZone[0].getBackground());
		btnZone[0].setBackground(btnZone[2].getBackground());
		if (btnZone[0].getBackground() == Color.WHITE) {
			btnZone[0].setEnabled(true);
		} else {
			btnZone[0].setEnabled(false);
		}
		btnZone[2].setBackground(btnZone[8].getBackground());
		if (btnZone[2].getBackground() == Color.WHITE) {
			btnZone[2].setEnabled(true);
		} else {
			btnZone[2].setEnabled(false);
		}
		btnZone[8].setBackground(btnZone[6].getBackground());
		if (btnZone[8].getBackground() == Color.WHITE) {
			btnZone[8].setEnabled(true);
		} else {
			btnZone[8].setEnabled(false);
		}
		btnZone[6].setBackground(a.getBackground());
		if (btnZone[6].getBackground() == Color.WHITE) {
			btnZone[6].setEnabled(true);
		} else {
			btnZone[6].setEnabled(false);
		}

		a.setBackground(btnZone[1].getBackground());
		btnZone[1].setBackground(btnZone[5].getBackground());
		if (btnZone[1].getBackground() == Color.WHITE) {
			btnZone[1].setEnabled(true);
		} else {
			btnZone[1].setEnabled(false);
		}
		btnZone[5].setBackground(btnZone[7].getBackground());
		if (btnZone[5].getBackground() == Color.WHITE) {
			btnZone[5].setEnabled(true);
		} else {
			btnZone[5].setEnabled(false);
		}
		btnZone[7].setBackground(btnZone[3].getBackground());
		if (btnZone[7].getBackground() == Color.WHITE) {
			btnZone[7].setEnabled(true);
		} else {
			btnZone[7].setEnabled(false);
		}
		btnZone[3].setBackground(a.getBackground());
		if (btnZone[3].getBackground() == Color.WHITE) {
			btnZone[3].setEnabled(true);
		} else {
			btnZone[3].setEnabled(false);
		}
		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 6; j++) {
				if (boardBtn[i][j].getBackground() == Color.WHITE) {
					boardBtn[i][j].setEnabled(true);
				}
			}
		}
		boardBtn = boardButtonConstructor(zoneA, zoneB, zoneC, zoneD);
		return boardBtn;
	}

	public JButton[][] conterClockwiseRotateRobat(int whichBoard, JButton[][] boardBtn, JButton[] zoneA, JButton[] zoneB,
			JButton[] zoneC, JButton[] zoneD) {
		JButton[] btnZone;
		if (whichBoard == 0) {
			btnZone = zoneA;
		} else if (whichBoard == 1) {
			btnZone = zoneB;
		} else if (whichBoard == 2) {
			btnZone = zoneC;
		} else if (whichBoard == 3) {
			btnZone = zoneD;
		} else {
			btnZone = zoneA;
		}
		for (int i = 0; i < 9; i++) {
			if (i == 4) {
				continue;
			}
			btnZone[i].setEnabled(true);
		}
		JButton a = new JButton();
		a.setBackground(btnZone[0].getBackground());
		btnZone[0].setBackground(btnZone[6].getBackground());
		if (btnZone[0].getBackground() == Color.WHITE) {
			btnZone[0].setEnabled(true);
		} else {
			btnZone[0].setEnabled(false);
		}
		btnZone[6].setBackground(btnZone[8].getBackground());
		if (btnZone[6].getBackground() == Color.WHITE) {
			btnZone[6].setEnabled(true);
		} else {
			btnZone[6].setEnabled(false);
		}
		btnZone[8].setBackground(btnZone[2].getBackground());
		if (btnZone[8].getBackground() == Color.WHITE) {
			btnZone[8].setEnabled(true);
		} else {
			btnZone[8].setEnabled(false);
		}
		btnZone[2].setBackground(a.getBackground());
		if (btnZone[2].getBackground() == Color.WHITE) {
			btnZone[2].setEnabled(true);
		} else {
			btnZone[2].setEnabled(false);
		}

		a.setBackground(btnZone[1].getBackground());
		btnZone[1].setBackground(btnZone[3].getBackground());
		if (btnZone[1].getBackground() == Color.WHITE) {
			btnZone[1].setEnabled(true);
		} else {
			btnZone[1].setEnabled(false);
		}
		btnZone[3].setBackground(btnZone[7].getBackground());
		if (btnZone[3].getBackground() == Color.WHITE) {
			btnZone[3].setEnabled(true);
		} else {
			btnZone[3].setEnabled(false);
		}
		btnZone[7].setBackground(btnZone[5].getBackground());
		if (btnZone[7].getBackground() == Color.WHITE) {
			btnZone[7].setEnabled(true);
		} else {
			btnZone[7].setEnabled(false);
		}
		btnZone[5].setBackground(a.getBackground());
		if (btnZone[5].getBackground() == Color.WHITE) {
			btnZone[5].setEnabled(true);
		} else {
			btnZone[5].setEnabled(false);
		}
		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 6; j++) {
				if (boardBtn[i][j].getBackground() == Color.WHITE) {
					boardBtn[i][j].setEnabled(true);
				}
			}
		}
		boardBtn = boardButtonConstructor(zoneA, zoneB, zoneC, zoneD);
		return boardBtn;
	}
	public int[][][] planMaker() {
		int plan[][][] = new int[32][5][2];

		// 0-7 of first [] ---> Two Way Strategy

		// [0]
		for (int i = 0; i < 5; i++) {
			plan[0][i][0] = i;
			plan[0][i][1] = 1;
		}

		// [1]

		for (int i = 0; i < 5; i++) {
			plan[1][i][0] = i + 1;
			plan[1][i][1] = 1;
		}

		// [2]

		for (int i = 0; i < 5; i++) {
			plan[2][i][0] = i;
			plan[2][i][1] = 4;
		}
		// [3]

		for (int i = 0; i < 5; i++) {
			plan[3][i][0] = i + 1;
			plan[3][i][1] = 4;
		}

		// [4]

		for (int i = 0; i < 5; i++) {
			plan[4][i][0] = 1;
			plan[4][i][1] = i;
		}

		// [5]

		for (int i = 0; i < 5; i++) {
			plan[5][i][0] = 1;
			plan[5][i][1] = i + 1;
		}

		// [6]

		for (int i = 0; i < 5; i++) {
			plan[6][i][0] = 4;
			plan[6][i][1] = i;
		}

		// [7]

		for (int i = 0; i < 5; i++) {
			plan[7][i][0] = 4;
			plan[7][i][1] = i + 1;
		}

		// 8 - 23 of first [] ---> for Square Strategy

		// [8]
		for (int i = 0; i < 5; i++) {
			plan[8][i][0] = i;
			plan[8][i][1] = 0;
		}

		// [9]

		for (int i = 0; i < 5; i++) {
			plan[9][i][0] = i;
			plan[9][i][1] = 2;
		}

		// [10]

		for (int i = 0; i < 5; i++) {
			plan[10][i][0] = i;
			plan[10][i][1] = 3;
		}

		// [11]
		for (int i = 0; i < 5; i++) {
			plan[11][i][0] = i;
			plan[11][i][1] = 5;
		}

		// [12]

		for (int i = 0; i < 5; i++) {
			plan[12][i][0] = 0;
			plan[12][i][1] = i;
		}

		// [13]

		for (int i = 0; i < 5; i++) {
			plan[13][i][0] = 2;
			plan[13][i][1] = i;
		}

		// [14]
		for (int i = 0; i < 5; i++) {
			plan[14][i][0] = 3;
			plan[14][i][1] = i;
		}

		// [15]
		for (int i = 0; i < 5; i++) {
			plan[15][i][0] = 5;
			plan[15][i][1] = i;
		}

		// [16]
		for (int i = 0; i < 5; i++) {
			plan[16][i][0] = i + 1;
			plan[16][i][1] = 0;
		}

		// [17]
		for (int i = 0; i < 5; i++) {
			plan[17][i][0] = i + 1;
			plan[17][i][1] = 2;
		}

		// [18]
		for (int i = 0; i < 5; i++) {
			plan[18][i][0] = i + 1;
			plan[18][i][1] = 3;
		}

		// [19]
		for (int i = 0; i < 5; i++) {
			plan[19][i][0] = i + 1;
			plan[19][i][1] = 5;
		}

		// [20]
		for (int i = 0; i < 5; i++) {
			plan[20][i][0] = 0;
			plan[20][i][1] = i + 1;
		}

		// [21]
		for (int i = 0; i < 5; i++) {
			plan[21][i][0] = 2;
			plan[21][i][1] = i + 1;
		}

		// [22]
		for (int i = 0; i < 5; i++) {
			plan[22][i][0] = 3;
			plan[22][i][1] = i + 1;
		}

		// [23]
		for (int i = 0; i < 5; i++) {
			plan[23][i][0] = 5;
			plan[23][i][1] = i + 1;
		}
		// 24 - 27 of first [] ----> for Double X Strategy

		// [24]
		plan[24][0][0] = 0;
		plan[24][0][1] = 1;
		//
		plan[24][1][0] = 1;
		plan[24][1][1] = 2;
		//
		plan[24][2][0] = 2;
		plan[24][2][1] = 3;
		//
		plan[24][3][0] = 3;
		plan[24][3][1] = 4;
		//
		plan[24][4][0] = 4;
		plan[24][4][1] = 5;

		// [25]

		plan[25][0][0] = 1;
		plan[25][0][1] = 0;
		//
		plan[25][1][0] = 2;
		plan[25][1][1] = 1;
		//
		plan[25][2][0] = 3;
		plan[25][2][1] = 2;
		//
		plan[25][3][0] = 4;
		plan[25][3][1] = 3;
		//
		plan[25][4][0] = 5;
		plan[25][4][1] = 4;

		// [26]
		plan[26][0][0] = 0;
		plan[26][0][1] = 4;
		//
		plan[26][1][0] = 1;
		plan[26][1][1] = 3;
		//
		plan[26][2][0] = 2;
		plan[26][2][1] = 2;
		//
		plan[26][3][0] = 3;
		plan[26][3][1] = 1;
		//
		plan[26][4][0] = 4;
		plan[26][4][1] = 0;

		// [27]
		plan[27][0][0] = 1;
		plan[27][0][1] = 5;
		//
		plan[27][1][0] = 2;
		plan[27][1][1] = 4;
		//
		plan[27][2][0] = 3;
		plan[27][2][1] = 3;
		//
		plan[27][3][0] = 4;
		plan[27][3][1] = 2;
		//
		plan[27][4][0] = 5;
		plan[27][4][1] = 1;

		// 28 - 31 of first [] ----> for X Strategy

		// [28]
		plan[28][0][0] = 1;
		plan[28][0][1] = 1;
		//
		plan[28][1][0] = 4;
		plan[28][1][1] = 4;
		//
		plan[28][2][0] = 2;
		plan[28][2][1] = 2;
		//
		plan[28][3][0] = 3;
		plan[28][3][1] = 3;
		//
		plan[28][4][0] = 0;
		plan[28][4][1] = 0;

		// [29]

		plan[29][0][0] = 4;
		plan[29][0][1] = 1;
		//
		plan[29][1][0] = 1;
		plan[29][1][1] = 4;
		//
		plan[29][2][0] = 3;
		plan[29][2][1] = 2;
		//
		plan[29][3][0] = 0;
		plan[29][3][1] = 5;
		//
		plan[29][4][0] = 2;
		plan[29][4][1] = 3;

		// [30]
		plan[30][0][0] = 1;
		plan[30][0][1] = 1;
		//
		plan[30][1][0] = 4;
		plan[30][1][1] = 4;
		//
		plan[30][2][0] = 2;
		plan[30][2][1] = 2;
		//
		plan[30][3][0] = 3;
		plan[30][3][1] = 3;
		//
		plan[30][4][0] = 5;
		plan[30][4][1] = 5;

		// [31]
		plan[31][0][0] = 4;
		plan[31][0][1] = 1;
		//
		plan[31][1][0] = 1;
		plan[31][1][1] = 4;
		//
		plan[31][2][0] = 3;
		plan[31][2][1] = 2;
		//
		plan[31][3][0] = 5;
		plan[31][3][1] = 0;
		//
		plan[31][4][0] = 2;
		plan[31][4][1] = 3;

		return plan;
	}

	public boolean brokenPlan(int[][] plan, JButton[][] page) {
		// this method show if the BOT can continue the plan or not
		// if one of rival's marble is in the plan locations , the method return
		// true
		// TRUE means BOT can't continue the Plan
		// False means BOT can continue the Plan
		// BOT color is RED

		for (int x = 0; x < 5; x++) {

			if (page[putPlan(planMaker(), planNumb)[x][0]][putPlan(planMaker(), planNumb)[x][1]]
					.getBackground() == Color.BLUE) {
				return true;
			}
		}
		return false;

	}

	public int[] showThePlanZone(int[][] plan) {

		// this method return the zone of each marble

		int[] zone = new int[5];

		for (int x = 0; x < 5; x++) {
			int a = putPlan(planMaker(), planNumb)[x][0];
			int b = putPlan(planMaker(), planNumb)[x][0];

			if (a == 0 || a == 1 || a == 2) {
				if (b == 0 || b == 1 || b == 2) {
					zone[x] = 0;
				} else {
					zone[x] = 1;
				}
			} else {
				if (b == 0 || b == 1 || b == 2) {
					zone[x] = 2;
				} else {
					zone[x] = 3;
				}
			}
		}
		return zone;

	}

	public int[] locationOfMarbleForPut(int[][] plan, JButton[][] page) {
		// this method return the location of the marble that BOT should put in
		// the page in its turn
		// 9 means the location is used
		// 0 to 4 means the location is not used
		while (true) {
			int random = ThreadLocalRandom.current().nextInt(0, 5);
			// find the location that is empty and not used before
			if (page[putPlan(planMaker(), planNumb)[random][0]][putPlan(planMaker(), planNumb)[random][1]]
					.getBackground() == Color.BLACK) {
				plan[random][0] = 9;
				plan[random][1] = 9;
			}

			if (plan[random][0] != 9 && plan[random][1] != 9
					&& page[plan[random][0]][plan[random][1]].getBackground() == Color.WHITE) {
				int[] location = new int[2];
				location[0] = plan[random][0];
				location[1] = plan[random][1];
				plan[random][0] = 9;
				plan[random][1] = 9;
				return location;

			}
		}
	}

	public int[][] putPlan(int[][][] plan, int planNumb) {
		// change plan [][][] to plan [][]

		int[][] localPlan = new int[5][2];

		for (int i = 0; i < 5; i++) {
			localPlan[i][0] = plan[planNumb][i][0];
			localPlan[i][1] = plan[planNumb][i][1];
		}

		return localPlan;

	}

	public int[] randomRotate(int[] rotateColection) {
		int random = ThreadLocalRandom.current().nextInt(0, 4);
		while (random == rotateColection[0] || random == rotateColection[1] || random == rotateColection[2]
				|| random == rotateColection[3] || random == rotateColection[4]) {
			random = ThreadLocalRandom.current().nextInt(0, 4);
		}
		int random2 = ThreadLocalRandom.current().nextInt(0, 2);
		int[] fin = { random, random2 };
		return fin;

	}

	public int[] robotSrategyPlayer(JButton[][] boardBtn, JButton[] zoneA, JButton[] zoneB, JButton[] zoneC,
			JButton[] zoneD) {

		int[] movement = new int[4];

		// until the strategies are finished
		while (planNumb < 32) {
			if (!brokenPlan(planLocations, boardBtn)) // if BOT can complete the
														// plan
			{
				// choose random location
				int[] location = locationOfMarbleForPut(planLocations, boardBtn);
				movement[0] = location[0];
				movement[1] = location[1];

				// choose random rotate not from the plan Location
				int[] rotate = randomRotate(showThePlanZone(planLocations));
				movement[2] = rotate[0];
				movement[3] = rotate[1];

				return movement;

			} else // if the BOT's plan is damaged by a rival marble
			{
				int[] rotate = rotatedPlan(boardBtn, zoneA, zoneB, zoneC, zoneD, planLocations);

				// can the Plan be OK with rotate
				if (rotate[0] != 9 || rotate[1] != 9) {
					// if yes

					// choose random location
					int[] location = locationOfMarbleForPut(planLocations, boardBtn);

					// put marble on this location
					movement[0] = location[0];
					movement[1] = location[1];
					movement[2] = rotate[0];
					movement[3] = rotate[1];

					return movement;
				} else {
					// if NO
					planNumb++;
					planLocations = putPlan(planMaker(), planNumb);
				}
			}
		}

		// put random marble and random rotate

		movement[2] = ThreadLocalRandom.current().nextInt(0, 4);
		movement[3] = ThreadLocalRandom.current().nextInt(0, 2);
		do {
			movement[0] = ThreadLocalRandom.current().nextInt(0, 5);
			movement[1] = ThreadLocalRandom.current().nextInt(0, 5);
		} while (boardBtn[movement[0]][movement[1]].getBackground() != Color.WHITE);

		return movement;

	}

	public int[] rotatedPlan(JButton[][] boardbtns, JButton[] zoneA, JButton[] zoneB, JButton[] zoneC, JButton[] zoneD,
			int[][] plan) {
		JButton[][] rotatedBoardbtns = boardbtns;
		// String[] whichBoard = { "btnA", "btnB", "btnC", "btnD" };
		// JButton[][] whichBoardButton = { zoneA, zoneB, zoneC, zoneD };
		// String[] rotate = { "clockwise", "counter-clockwise" };
		for (int j = 0; j < 4; j++) {
			for (int j2 = 0; j2 < 2; j2++) {

				if (j2 == 0) {
					rotatedBoardbtns = clockwiseRotateRobat(j, boardbtns, zoneA, zoneB, zoneC, zoneD);
					if (!brokenPlan(plan, rotatedBoardbtns)) {
						int[] movement = new int[2];
						movement[0] = j;
						movement[1] = j2;
						return movement;
					}

				} else {
					rotatedBoardbtns = conterClockwiseRotateRobat(j, boardbtns, zoneA, zoneB, zoneC, zoneD);
					if (!brokenPlan(plan, rotatedBoardbtns)) {
						int[] movement = new int[2];
						movement[0] = j;
						movement[1] = j2;
						return movement;
					}
				}

			}
		}

		int[] movement = new int[2];
		movement[0] = 9;
		movement[1] = 9;
		return movement;

	}

}
