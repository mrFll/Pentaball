package graphic;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.Scanner;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import resources.Resources;

public class HighScorePanel extends JPanel {
  private static final long serialVersionUID = 5764965463393273529L;
  private JButton sound;

  public HighScorePanel(String[] phrase,JButton sound1)

  {
    sound = sound1;
    String[][] topFives = new String[5][5];
    topFives = makeTopFive();
    Font font = new Font("Courier", Font.ITALIC, 40);
//    //
//    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
//    this.setSize(new Dimension( screenSize.width / 2 - 45, screenSize.height - 50));
    this.setBackground(new Color(186, 205, 234));
    this.setLayout(null);
    //
    JButton exit = new JButton("");
    exit.setBounds(90, 620, 30,30);
    exit.setBackground(new Color(182, 209, 252));
	exit.setBorderPainted(false);
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

        removeAll();
        add(goBackToMenu(phrase));
        revalidate();
        repaint();

      }
    });
    this.add(exit);
    //
    JLabel topic = new JLabel();
    topic.setBounds(338, 2, 400, 300);
    topic.setIcon(new ImageIcon(resources.Resources.getImage("high.png")));
    this.add(topic);
    //

    JLabel names = new JLabel(phrase[20], JLabel.CENTER);
    names.setFont(new Font("Ariel", Font.PLAIN, 25));
    names.setForeground(new Color(47, 90, 155));
    names.setFont(font);
    names.setBounds(170, 260, 280, 43);
    this.add(names);
    //

JLabel highScores = new JLabel(phrase[14], JLabel.CENTER);
    highScores.setFont(new Font("Ariel", Font.PLAIN, 25));
    highScores.setForeground(new Color(47, 90, 155));
    highScores.setBounds(470, 260, 280, 43);
    highScores.setFont(font);
    this.add(highScores);
    //
    // names labels
    //
    JLabel name1 = new JLabel(topFives[0][0], JLabel.CENTER);
    name1.setFont(new Font("Ariel", Font.PLAIN, 20));
    name1.setForeground(new Color(54, 103, 177));
    name1.setBounds(170, 310, 280, 23);
    this.add(name1);
    //
    JLabel name2 = new JLabel(topFives[1][0], JLabel.CENTER);
    name2.setFont(new Font("Ariel", Font.PLAIN, 20));
    name2.setForeground(new Color(58, 112, 190));
    name2.setBounds(170, 360, 280, 23);
    this.add(name2);
    //
    JLabel name3 = new JLabel(topFives[2][0], JLabel.CENTER);
    name3.setFont(new Font("Ariel", Font.PLAIN, 20));
    name3.setForeground(new Color(66, 119, 198));
    name3.setBounds(170, 410, 280, 23);
    this.add(name3);
    //
    JLabel name4 = new JLabel(topFives[3][0], JLabel.CENTER);
    name4.setFont(new Font("Ariel", Font.PLAIN, 20));
    name4.setForeground(new Color(81, 130, 202));
    name4.setBounds(170, 460, 280, 23);
    this.add(name4);
    //
    JLabel name5 = new JLabel(topFives[4][0], JLabel.CENTER);
    name5.setFont(new Font("Ariel", Font.PLAIN, 20));
    name5.setForeground(new Color(106, 148, 210));
    name5.setBounds(170, 510, 280, 23);
    this.add(name5);
    //
    // scores labels
    //
    JLabel score1 = new JLabel(topFives[0][1], JLabel.CENTER);
    score1.setFont(new Font("Ariel", Font.PLAIN, 20));
    score1.setForeground(new Color(54, 103, 177));
    score1.setBounds(470, 310, 280, 23);
    this.add(score1);
    //
    JLabel score2 = new JLabel(topFives[1][1], JLabel.CENTER);
    score2.setFont(new Font("Ariel", Font.PLAIN, 20));
    score2.setForeground(new Color(58, 112, 190));
    score2.setBounds(470, 360, 280, 23);
    this.add(score2);
    //
    JLabel score3 = new JLabel(topFives[2][1], JLabel.CENTER);
    score3.setFont(new Font("Ariel", Font.PLAIN, 20));

    score3.setForeground(new Color(66, 119, 198));
    score3.setBounds(470, 410, 280, 23);
    this.add(score3);
    //
    JLabel score4 = new JLabel(topFives[3][1], JLabel.CENTER);
    score4.setFont(new Font("Ariel", Font.PLAIN, 20));
    score4.setForeground(new Color(81, 130, 202));

    score4.setBounds(470, 460, 280, 23);
    this.add(score4);
    //
    JLabel score5 = new JLabel(topFives[4][1], JLabel.CENTER);
    score5.setFont(new Font("Ariel", Font.PLAIN, 20));
    score5.setForeground(new Color(106, 148, 210));
    score5.setBounds(470, 510, 280, 23);
    this.add(score5);
    //

  }

  public JPanel goBackToMenu(String[] phrase) {
    MenuPanel panel = new MenuPanel(phrase,sound);
    panel.setSize(new Dimension(1000, 1000));
    panel.setBounds(0, 0, 1100, 1080);
    return panel;

  }

   public static String[] readFile() {
    File allsource = new File("src/data.txt");

    try {
      // for if you don't have file
      if (!allsource.exists()) {
        allsource.createNewFile();
        String[] all = new String[5];
        makeArrFullOfStar(all); // make one star array
        return all;
      }

      // reading from file
      Scanner sc = new Scanner(allsource);
      String[] all = new String[800]; 

      int i = 0;
      if (sc.hasNextLine() == true) {
        while (sc.hasNextLine()) {
          all[i] = sc.nextLine();
          i++;

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

  public static String[][] makeTopFive() {

String[][] topFive = new String[5][2]; // array of the top five scores
    //
    String[] all = readFile(); // the data that read from the text file
    // (name&score)
    // IF the program start for the first time we return
    // '-'*************************************************************************
    if (all[0] == "*") {
      for (int x = 0; x < 5; x++) {
        topFive[x][0] = "-";
        topFive[x][1] = "-";
      }
      return topFive;
    } else {
      //
      // if we have some users info and data
      String[] names = new String[all.length]; // the array of all names
      makeArrFullOfStar(names);
      String[] score = new String[all.length]; // the array of all scores
      makeArrFullOfStar(score);
      //
      for (int i = 0; i <= all.length; i++) {
        if (all[i] == null) {
          break;
        }

        String[] temp = all[i].split("&");
        names[i] = temp[0];
        score[i] = temp[1];
      }

      topFive = findHighScores(names, score);
      return topFive;

    }
  }

  public static String[][] findHighScores(String[] names, String[] score) {
    String[][] topFive = new String[5][2];
    // make full all the top five array with the users data
    for (int i = 0; i < 5; i++) {

      int topNumb = 0; // the location of the biggest number in both array
      long big = Long.valueOf(score[0]); // long of biggest score

      for (int j = 1; j < score.length; j++) {

        boolean finish = usersUperThanFive(score);
        if (finish == true) {
          topFive[i][0] = "-";
          topFive[i][1] = "-";
          break;
        } else {

          if (score[j] == "*") // if the data is finished
          {
            topFive[i][0] = names[topNumb];
            topFive[i][1] = score[topNumb];
            score[topNumb] = "0";
            break;
          }

          // counting the max
          long temp = Long.valueOf(score[j]);
          if (temp > big) {
            big = temp;
            topNumb = j;
          }
        }
      }

    }
    return topFive;

  }

  public static void makeArrFullOfStar(String[] arr) {
    for (int i = 0; i < arr.length; i++) {
      arr[i] = "*";
    }
  }

  public static boolean usersUperThanFive(String[] scores) {
    boolean finish = true;
    for (int x = 0; x < scores.length; x++) {

      // for know if the scores of scores array finished or not
      if (scores[x] == "*") {
        break;
      }
      // we wan to check here if we have any value in scores more than 0
      // if all = 0 -> finish= true \\ if find one or more value bigger
      // that 0 -> finish = false
      if (!scores[x].equals("0")) {
        finish = false;
      }

    }
    return finish;

  }

}