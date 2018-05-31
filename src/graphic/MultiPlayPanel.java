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
import java.util.Scanner;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import resources.Resources;

public class MultiPlayPanel extends JPanel implements ActionListener, MouseListener {

	private static final long serialVersionUID = 1L;
	private JButton[] btnC;
	private JButton[] btnB;
	private JButton[] btnA;
	private JButton[] btnD;
	private int k;
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
	private int mainScoreUser2;
	private JLabel user1Label;
	private JLabel user2Label;

	public MultiPlayPanel(String[] phrase, String username1, String username2, JButton sound1) {
		// enable user to mute the music
		sound = sound1;
		sound.setBounds(130, 620, 30, 30);
		this.add(sound);
		// The name of users that is entered in the menuPanel class
		user1 = username1;
		user2 = username2;
		scoreTeam1 = 0;
		scoreTeam2 = 0;

		mainScoreUser1 = showUsersScoreInGame(username1);
		mainScoreUser2 = showUsersScoreInGame(username2);

		teamscore = new JLabel(username1 + "  " + scoreTeam1 + "  _  " + scoreTeam2 + "  " + username2);
		teamscore.setBounds(100, 10, 200, 30);
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
		user1Label.setBounds(650, 0, 200, 30);
		user1Label.setForeground(Color.BLUE);
		this.add(user1Label);
		//
		user2Label = new JLabel(username2 + " " + phraseLists[14] + " " + mainScoreUser2);
		user2Label.setBounds(650, 20, 200, 30);
		user2Label.setForeground(Color.RED);
		this.add(user2Label);
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

		boardbtns = boardButtonConstructor();

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

		k = 0;
	}

	public JButton getrightbtn(JButton[] btnZone, JButton[][] boardbtns) {
		ClockwiseRotate rightRotate = new ClockwiseRotate(btnZone, boardbtns);
		rightRotate.setSize(new Dimension(10, 10));
		if (btnZone == btnA) {
			rightRotate.setBounds(155, 130, 70, 70);
			rightRotate.setIcon(new ImageIcon(Resources.getImage("A2.png")));
		}
		if (btnZone == btnB) {
			rightRotate.setBounds(610, 65, 70, 70);
			rightRotate.setIcon(new ImageIcon(Resources.getImage("B2.png")));
		}
		if (btnZone == btnC) {
			rightRotate.setBounds(210, 555, 70, 70);
			rightRotate.setIcon(new ImageIcon(Resources.getImage("C2.png")));
		}
		if (btnZone == btnD) {
			rightRotate.setBounds(665, 490, 70, 70);
			rightRotate.setIcon(new ImageIcon(Resources.getImage("D2.png")));
		}

		return rightRotate;
	}

	public JButton getleftbtn(JButton[] btnZone, JButton[][] boardbtns) {
		CounterClockwiseRotate leftRotate = new CounterClockwiseRotate(btnZone, boardbtns);
		leftRotate.setSize(new Dimension(10, 10));
		if (btnZone == btnA) {
			leftRotate.setBounds(210, 65, 70, 70);
			leftRotate.setIcon(new ImageIcon(Resources.getImage("A1.png")));
		}
		if (btnZone == btnB) {
			leftRotate.setBounds(665, 130, 70, 70);
			leftRotate.setIcon(new ImageIcon(Resources.getImage("B1.png")));
		}
		if (btnZone == btnC) {
			leftRotate.setBounds(155, 490, 70, 70);
			leftRotate.setIcon(new ImageIcon(Resources.getImage("C1.png")));
		}
		if (btnZone == btnD) {
			leftRotate.setBounds(610, 555, 70, 70);
			leftRotate.setIcon(new ImageIcon(Resources.getImage("D1.png")));
		}
		return leftRotate;

	}

	//
	private JButton[] rotateBtns() {
		JButton[] rotate = new JButton[8];
		rotate[0] = getrightbtn(btnA, boardbtns);
		rotate[1] = getrightbtn(btnB, boardbtns);
		rotate[2] = getrightbtn(btnC, boardbtns);
		rotate[3] = getrightbtn(btnD, boardbtns);
		rotate[4] = getleftbtn(btnA, boardbtns);
		rotate[5] = getleftbtn(btnB, boardbtns);
		rotate[6] = getleftbtn(btnC, boardbtns);
		rotate[7] = getleftbtn(btnD, boardbtns);
		for (int i = 0; i < 8; i++) {
			rotate[i].setVisible(false);
			rotate[i].addMouseListener(new MouseAdapter() {
				@Override
				public void mouseReleased(MouseEvent e) {
					// when you click and release the mouse button all the
					// buttons will disappear!
					for (int i = 0; i < 8; i++) {
						rotate[i].setVisible(false);
						text.setText(phraseLists[4]);
						revalidate();
						repaint();
					}
					// after every rotate check victory situation
					int[] victory = victorySituation(boardbtns);
					if (victory[0] != 9) {
						hasBeenWon(0);
					}
					// after every rotate check if there is any white button or not
					nowinner();
				}
			});
		}
		return rotate;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		boolean rotate = true;
		//after every turn
		int[] victory = victorySituation(boardbtns);
		if (victory[0] != 9) {
			rotate = hasBeenWon(1);
			for (int i = 0; i < 6; i++) {
				for (int j = 0; j < 6; j++) {
					boardbtns[i][j].setBackground(Color.WHITE);
					boardbtns[i][j].setEnabled(true);
					repaint();
				}
			}
			k = 0;
		}
		if (rotate == true) {
			onlyRotate(boardbtns);
		}
		this.repaint();
		k++;
		for (int j = 0; j < 6; j++) {
			for (int i = 0; i < victory.length; i++) {
				if (boardbtns[j][i] == e.getSource()) {
					if (k % 2 == 0) {//if k is even second person turn
						boardbtns[j][i].setBackground(Color.RED);
					} else {//if k is odd is first person turn
						boardbtns[j][i].setBackground(Color.BLUE);
					}
					boardbtns[j][i].setEnabled(false);

					break;
				}
			}
		}
		if (victory[0] != 9) {
			//check victory situation
			rotate = hasBeenWon(1);
			for (int i = 0; i < 6; i++) {
				for (int k = 0; k < 6; k++) {
					boardbtns[i][k].setBackground(Color.WHITE);
					boardbtns[i][k].setEnabled(true);
					repaint();
				}
			}
			k = 0;
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
						if ((k + 1) % 2 == 0) {
							boardbtns[j][i].setBackground(Color.RED);
						} else {
							boardbtns[j][i].setBackground(Color.BLUE);
						}
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
	private JButton[][] boardButtonConstructor() {
		// attach all the zones together
		JButton[][] boardbtns = new JButton[6][6];
		int i = 0;
		for (int j = 0; j <= 2; j++) {
			boardbtns[0][j] = btnA[i];
			i++;
		}
		i = 0;
		for (int j = 0; j <= 2; j++) {
			boardbtns[3][j] = btnC[i];
			i++;
		}
		i = 0;
		for (int j = 3; j <= 5; j++) {
			boardbtns[0][j] = btnB[i];
			i++;
		}
		i = 0;
		for (int j = 3; j <= 5; j++) {
			boardbtns[3][j] = btnD[i];
			i++;
		}
		i = 3;
		for (int j = 0; j <= 2; j++) {
			boardbtns[1][j] = btnA[i];
			i++;
		}
		i = 3;
		for (int j = 0; j <= 2; j++) {
			boardbtns[4][j] = btnC[i];
			i++;
		}
		i = 3;
		for (int j = 3; j <= 5; j++) {
			boardbtns[1][j] = btnB[i];
			i++;
		}
		i = 3;
		for (int j = 3; j <= 5; j++) {
			boardbtns[4][j] = btnD[i];
			i++;
		}
		i = 6;
		for (int j = 0; j <= 2; j++) {
			boardbtns[2][j] = btnA[i];
			i++;
		}
		i = 6;
		for (int j = 0; j <= 2; j++) {
			boardbtns[5][j] = btnC[i];
			i++;
		}
		i = 6;
		for (int j = 3; j <= 5; j++) {
			boardbtns[2][j] = btnB[i];
			i++;
		}
		i = 6;
		for (int j = 3; j <= 5; j++) {
			boardbtns[5][j] = btnD[i];
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
			if (reply == JOptionPane.NO_OPTION) {
				// show and save users score
				teamscore.setText(user1 + " " + scoreTeam1 + " _ " + scoreTeam2 + " " + user2);
				user1Label.setText(user1 + " " + phraseLists[14] + " " + mainScoreUser1);
				user2Label.setText(user2 + " " + phraseLists[14] + " " + mainScoreUser2);
				saveUsersScore(mainScoreUser1, user1);
				saveUsersScore(mainScoreUser2, user2);
				//
				removeAll();
				add(getPaneExit(phraseLists));
				revalidate();
				repaint();
			}
			if (reply == JOptionPane.YES_OPTION) {
				// if yes make all the buttons white and put k as 0 again
				k = 0;
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
				user2Label.setText(user2 + " " + phraseLists[14] + " " + mainScoreUser2);
				saveUsersScore(mainScoreUser1, user1);
				saveUsersScore(mainScoreUser2, user2);

				repaint();
				revalidate();
			}
		}

	}

	//
	public Boolean hasBeenWon(int rotate) {
		boolean rotateNeeded = true;
		int[] victory = victorySituation(boardbtns);
		// if rotate=0 the last action was rotate
		// if rotate=1 the last action was marble putting
		if (victory[0] != 9) {
			if (boardbtns[victory[0]][victory[1]].getBackground() == Color.RED) {
				JOptionPane.showMessageDialog(null, phraseLists[6] + " " + user2 + "!");
				mainScoreUser2++;
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
					mainScoreUser2++;
					scoreTeam2++;
				} else {
					mainScoreUser1++;
					scoreTeam1++;
				}

				teamscore.setText(user1 + " " + scoreTeam1 + " _ " + scoreTeam2 + " " + user2);
				user1Label.setText(user1 + " " + phraseLists[14] + " " + mainScoreUser1);
				user2Label.setText(user2 + " " + phraseLists[14] + " " + mainScoreUser2);
				saveUsersScore(mainScoreUser1, user1);
				saveUsersScore(mainScoreUser2, user2);
				//
				removeAll();
				add(getPaneExit(phraseLists));
				revalidate();
				repaint();
			}
			if (reply == JOptionPane.YES_OPTION) {
				k = 0;
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
				user2Label.setText(user2 + " " + phraseLists[14] + " " + mainScoreUser2);
				saveUsersScore(mainScoreUser1, user1);
				saveUsersScore(mainScoreUser2, user2);

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

}
