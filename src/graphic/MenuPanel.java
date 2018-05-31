package graphic;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Scanner;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import resources.Resources;

public class MenuPanel extends JPanel {
	private JButton multiplayer;
	private JButton exit;
	private JButton records;
	private JButton instructions;
	private String user1;
	private String user2;
	private JButton sound;
	private JButton singlePlayer;

	public MenuPanel(String[] phrase, JButton sound1) {
		this.setBackground(new Color(186, 205, 234));
		this.setLayout(null);
		JLabel welcome = new JLabel("P E N T A B A L L", JLabel.CENTER);
		Font font = new Font("Courier", Font.BOLD, 40);
		welcome.setFont(font);
		welcome.setBounds(0, 0, 900, 50);
		welcome.setForeground(new Color(62, 87, 141));
		this.add(welcome);
		// enable user to mute the music
		sound = sound1;
		sound.setBounds(90, 620, 30, 30);
		this.add(sound);
		//
		JLabel iconLabel = new JLabel();
		iconLabel.setIcon(new ImageIcon(Resources.getImage("pentaball.jpg")));
		iconLabel.setBounds(325, 50, 250, 250);
		this.add(iconLabel);
		//
		multiplayer = new JButton(phrase[0]);
		multiplayer.setBounds(450, 320, 150, 50);
		multiplayer.setBackground(new Color(26, 36, 60));
		multiplayer.setForeground(Color.WHITE);
		this.add(multiplayer);

		multiplayer.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				multiplayerAction();
			}
		});
		singlePlayer = new JButton(phrase[21]);
		singlePlayer.setBounds(300, 320, 150, 50);
		singlePlayer.setBackground(new Color(26, 36, 60));
		singlePlayer.setForeground(Color.WHITE);
		this.add(singlePlayer);

		singlePlayer.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				singlePlayerAction();
			}
		});
		//
		instructions = new JButton(phrase[1]);
		instructions.setBounds(350, 385, 200, 50);
		instructions.setBackground(new Color(62, 87, 141));
		instructions.setForeground(Color.WHITE);
		instructions.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				String phrase[] =phrase();
				removeAll();
				add(getinstrutionPanel(phrase));
				revalidate();
				repaint();
			}
		});

		this.add(instructions);
		//
		records = new JButton(phrase[2]);
		records.setBounds(350, 450, 200, 50);
		records.setBackground(Color.WHITE);
		records.setForeground(Color.BLACK);
		records.addActionListener(new ActionListener() {

	@Override
			public void actionPerformed(ActionEvent arg0) {
				String phrase[] = phrase();
				removeAll();
				add(getHighScorePanel(phrase));
				revalidate();
				repaint();
			}
		});
		this.add(records);
		//
		exit = new JButton(phrase[3]);
		exit.setBounds(350, 580, 200, 50);
		exit.setBackground(new Color(45, 65, 102));
		exit.setForeground(Color.WHITE);
		exit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				String phrase[] = phrase();
				String[] options = { phrase[22], phrase[23] };
				int reply = JOptionPane.showOptionDialog(null, phrase[9], phrase[3], 0, JOptionPane.INFORMATION_MESSAGE,
						null, options, null);
				if (reply == JOptionPane.YES_OPTION) {
					JOptionPane.showMessageDialog(null, phrase[13]);
					System.exit(0);
				}
			}
		});
		this.add(exit);
		//
		String chooseLanguage = "Choosing Language";
		if (exit.getText().equals("Exit")) {
			chooseLanguage = "Choosing Language";
		} else if (exit.getText().equals("خروج")) {
			chooseLanguage = "انتخاب زبان";
		} else if (exit.getText().equals("Beenden")) {
			chooseLanguage = "Sprachen Wählen";
		} else if (exit.getText().equals("Çik")) {
			chooseLanguage = "Dil seçimi";
		} else if (exit.getText().equals("Sortie")) {
			chooseLanguage = "Choisir le langage";
		}
		String[] languages = { chooseLanguage, "English", "Français", "Deutsch", "Turkçe", "فارسی" };
		JComboBox language = new JComboBox(languages);
		language.setBounds(350, 515, 200, 50);
		language.setSelectedIndex(0);
		language.setBackground(new Color(125, 166, 228));
		language.setForeground(Color.BLACK);
		language.addActionListener(new ActionListener() {
			private String[] phrases;

			public void actionPerformed(ActionEvent e) {
				JComboBox combo = (JComboBox) e.getSource();
				String chosenlanguage = (String) combo.getSelectedItem();
				phrases = languageArray(chosenlanguage);
				exit.setText(phrases[3]);
				records.setText(phrases[2]);
				multiplayer.setText(phrases[0]);
				instructions.setText(phrases[1]);
				singlePlayer.setText(phrases[21]);
			}
		});
		this.add(language);
		//

	}
	//

	private JPanel getMultiPanel(String[] languageArray, String username1, String username2) {
		MultiPlayPanel panel = new MultiPlayPanel(languageArray, username1, username2, sound);
		panel.setSize(new Dimension(1000, 1000));
		panel.setBounds(0, 0, 1100, 1080);
		return panel;

	}

	private JPanel getSinglePanel(String[] languageArray, String username1) {
		SinglePlayerPanel panel = new SinglePlayerPanel(languageArray, username1, sound);
		panel.setSize(new Dimension(1000, 1000));
		panel.setBounds(0, 0, 1100, 1080);
		return panel;
	}

	private JPanel getHighScorePanel(String[] languageArray) {
		HighScorePanel panel = new HighScorePanel(languageArray, sound);
		panel.setSize(new Dimension(1000, 1000));
		panel.setBounds(0, 0, 1100, 1080);
		return panel;

	}

	private JPanel getinstrutionPanel(String[] languageArray) {
		Instruction panel = new Instruction(languageArray, sound);
		panel.setSize(new Dimension(1000, 1000));
		panel.setBounds(0, 0, 1100, 1080);
		return panel;

	}

	private String[] languageArray(String languages) {
		switch (languages) {
		case "English":
			String[] english = { "Two Player", "Instructions", "Best Scores", "Exit", "Put Your Marble", "Rotate!",
					"Congratulation", "Username1: ", "Username2: ", "Are you sure you want to exit",
					"Please enter different names!", "Enter your name", "Please fill in the both blanks correctly!",
					"Thank you for devoting your time!", " Score:", "PUT THE MARBLE!", "ROTATE ANY BOARD!",
					"WIN WITH 5 MARBLES!", "Do you want to play again?", "Choosing Language", "Name", "Single player",
					"Yes", "No", "OK", "Cancle", "Please fill the blank!", " Are you new user?",
					" choose another name, Please" };
			return english;
		case "Français":
			String[] french = { "Deux joueurs", "l'instruction", "les meiuex scores", "Sortie", "Mettez votre écrou",
					"Tourner!", "la félititation", "l'utilisateur 1: ", "l'utilisateur 2: ",
					"est ce que vous etes sure que vous voulez sortie?", "s'il vous plaît entrez des nom différents!",
					"entrez votre nom", "S'il vous plaît remplirez les deux blanc correctement!", "Merci!",
					" Les scores:", "mettez votre écrou!", "Tournez toutes les planches!", "Gagnez avec 5 écrou!",
					"Est ce que vous voulez jouer encore?", "Choisir le langage", "Prénom", "un joueur", "Oui", "non",
					"D'accord", "Annuler", "Remplissez les blancs", " Es-tu Nouveau?", " Choisir un autre nom" };
			return french;
		case "Deutsch":
			String[] german = { "Zwei Spieler", "Tutorials", "Erfolge", "Beenden", "Setzen Sie Ihre Figur!",
					"Rotieren Sie!", "Ausgezeichnet", "Benutzername1: ", "Benutzername2: ", "Sind Sie Sicher?",
					"Schreiben Sie verschidene Benutzernamen ein!", "Schreiben Sie Ihre Name!",
					"Füllen Sie die beiden lücken aus!", "Vielen Dank für Ihre Zeit!", " Ergebnis:",
					"SETZEN SIE DIE FIGUR!", "ROTIEREN SIE DIE BRETT!", "GEWONNEN SIE MIT FÜNF FIGUREN!",
					"Möchten Sie wiederspielen?", "Sprache wählen", "Name", "Ein Spieler", "Ja", "Nein", "Okay",
					"Beenden", "Füllen Sie die lücke aus ,bitte!", " Bist du Neuer?",
					" Wählen Sie andere Name,bitte!" };

			return german;
		case "Turkçe":
			String[] turkish = { "Iki oyuncu", "Talimatlar", "En iyi Puanlar", "Çik", "Misketlerini koy", "Döndür!",
					"Tebrikler!", "1inci kullanıcı adı: ", "2inci kullanıcı adı: ",
					"Çıkış  yapmak istediğinize emin misin?", "Lütfen farklı Kullanıcı adları girin!",
					"Lütfen kullanıcı adınız girın", "Lütfen boşlukları doldurun", "Teşekkürler", " Puanlar:",
					"Misketlerini koy", "Her hangi bir Masayi döndür", "5 adet misketlerle kazan!",
					"Tekrar oynamak istiyor musunuz?", "Dil seçimi", "Isim", "Bir oyuncu", "Evet", "Hayir", "Tamam",
					"Iptal", "Hata", " Yeni misiniz?", " Başka bir isim seç ,lütfan!" };
			return turkish;
		case "فارسی":
			String[] persian = { " دونفره", "راهنمای بازی", "امتیاز ها", "خروج", "مهره ات را بگذار!", "صفحه را بچرخان!",
					"آفرین", "نام کاربری1: ", "نام کاربری2: ", "آیا مطمینید که میخواهید از بازی خارج شوید؟",
					"نام ها مشابه است!", "نام خود را وارد کنید", "لطفا هر دو جای خالی را پر کنید!",
					"با تشکر از انتخاب بازی ما!", " امتیاز:", "مهره را بگذار!", "یکی از صفحه ها را بچرخان!",
					"با جور کردن 5 مهره ببر!", " میخواهید دوباره بازی کنید؟", "انتخاب زبان", "اسم", " یک نفره", "بلی",
					"خیر", "قبول", "انصراف", "لطفا جای خالی را پر کنید!", " آیا شما بازیکن جدید هستید?",
					" لطفا نام دیگری انتخاب کن!" };

			return persian;
		default:
			String[] phrases = { "Two Player", "Instructions", "Best Scores", "Exit", "Put Your Marble", "Rotate!",
					"Congratulation", "Username1: ", "Username2: ", "Are you sure you want to exit",
					"Please enter different names!", "Enter your name", "Please fill in the both blanks correctly!",
					"Thank you for devoting your time!", " Score:", "PUT THE MARBLE!", "ROTATE ANY BOARD!",
					"WIN WITH 5 MARBLES!", "Do you want to play again?", "Choosing Language", "Name", "Single player",
					"yes", "No", "OK", "Cancle", "Please fill the blank!", " Are you new user?",
					" choose another name, Please" };
			return phrases;
		}
	}

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

	public String checkUser(String username, String[] phrase) {
		boolean existanceUser = existingUsername(username);
		if (existanceUser == true) {
			String[] options = { phrase[22], phrase[23] };
			int reply = JOptionPane.showOptionDialog(null, username + " ! " + phrase[27], phrase[3], 0,
					JOptionPane.INFORMATION_MESSAGE, null, options, null);
			if (reply == JOptionPane.YES_OPTION) {
				JOptionPane.showMessageDialog(null, username + " ! " + phrase[28]);
				username = "";
			}
		}
		return username;
	}

	public String[] phrase() {
		String phrase[] = languageArray("English");
		if (exit.getText().equals("Exit")) {
			phrase = languageArray("English");
		} else if (exit.getText().equals("خروج")) {
			phrase = languageArray("فارسی");
		} else if (exit.getText().equals("Beenden")) {
			phrase = languageArray("Deutsch");
		} else if (exit.getText().equals("Çik")) {
			phrase = languageArray("Turkçe");
		} else if (exit.getText().equals("Sortie")) {
			phrase = languageArray("Français");
		}
		return phrase;
	}

	public void multiplayerAction() {
		String phrase[] = phrase();
		user2 = phrase[11];
		user1 = phrase[11];
		JTextField usre1Field = new JTextField(user1);
		JTextField user2Field = new JTextField(user2);

		JPanel getUsernameMulti;
		getUsernameMulti = new JPanel();
		getUsernameMulti.add(new JLabel(phrase[7]));
		getUsernameMulti.add(usre1Field);
		getUsernameMulti.add(Box.createHorizontalStrut(20)); // create space
		// between
		// fields
		getUsernameMulti.add(new JLabel(phrase[8]));
		getUsernameMulti.add(user2Field);
		String[] options = { phrase[24], phrase[25] };
		int result = JOptionPane.showOptionDialog(null, getUsernameMulti, phrase[11], 0,
				JOptionPane.INFORMATION_MESSAGE, null, options, null);
		user1 = usre1Field.getText();
		user2 = user2Field.getText();
		if (result == JOptionPane.YES_OPTION) {
			if (user1.trim() == null || user2.trim() == null) {
				JOptionPane.showMessageDialog(null, phrase[12]);
			} else if (checkUser(user1, phrase).equals("") == false && checkUser(user2, phrase).equals("") == false) {
				if (user1.trim().equals(user2.trim()) == true && user1.trim().equals(phrase[11]) == false
						&& user2.trim().equals(phrase[11]) == false) {
					JOptionPane.showMessageDialog(null, phrase[10]);
				}
				if (user1.trim().isEmpty() == false && user1.trim().equals(phrase[11]) == false
						&& user2.trim().isEmpty() == false && user2.trim().equals(phrase[11]) == false
						&& user1.trim().equals(user2.trim()) == false) {
					removeAll();
					if (exit.getText().equals("Exit")) {
						add(getMultiPanel(languageArray("English"), user1, user2));
					} else if (exit.getText().equals("خروج")) {
						add(getMultiPanel(languageArray("فارسی"), user1, user2));
					} else if (exit.getText().equals("Beenden")) {
						add(getMultiPanel(languageArray("Deutsch"), user1, user2));
					} else if (exit.getText().equals("Çik")) {
						add(getMultiPanel(languageArray("Turkçe"), user1, user2));
					} else if (exit.getText().equals("Sortie")) {
						add(getMultiPanel(languageArray("Français"), user1, user2));
					}
					revalidate();
					repaint();
				} else {
					JOptionPane.showMessageDialog(null, phrase[12]);
				}
			}
		}
	}

	public void singlePlayerAction() {
		String phrase[] = phrase();
		//
		user1 = phrase[11];
		JTextField usre1Field = new JTextField(user1);
		JPanel getUsername = new JPanel();
		getUsername.add(new JLabel(phrase[7]));
		getUsername.add(usre1Field);
		String[] options = { phrase[24], phrase[25] };
		int result = JOptionPane.showOptionDialog(null, getUsername, phrase[11], 0, JOptionPane.INFORMATION_MESSAGE,
				null, options, null);
		user1 = usre1Field.getText();
		if (result == JOptionPane.YES_OPTION) {
			if (user1 == null) {
				JOptionPane.showMessageDialog(null, phrase[26]);
			} else if (checkUser(user1, phrase).equals("") == false) {
				if (user1.trim().isEmpty() == false && user1.trim().equals(phrase[11]) == false) {
					removeAll();
					if (exit.getText().equals("Exit")) {
						add(getSinglePanel(languageArray("English"), user1));
					} else if (exit.getText().equals("خروج")) {
						add(getSinglePanel(languageArray("فارسی"), user1));
					} else if (exit.getText().equals("Beenden")) {
						add(getSinglePanel(languageArray("Deutsch"), user1));
					} else if (exit.getText().equals("Çik")) {
						add(getSinglePanel(languageArray("Turkçe"), user1));
					} else if (exit.getText().equals("Sortie")) {
						add(getSinglePanel(languageArray("Français"), user1));
					}
					revalidate();
					repaint();
				} else {
					JOptionPane.showMessageDialog(null, phrase[26]);
				}

			}
		}
	}

}
