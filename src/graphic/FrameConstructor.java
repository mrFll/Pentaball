package graphic;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import resources.Resources;

public class FrameConstructor extends JFrame {

  /**
   * 
   */
  private static final long serialVersionUID = 7063489965144256968L;

  private static final int WIDTH = 750;
  private static final int HEIGHT = 700;

  private JButton sound;

  public FrameConstructor(JButton music) {
    Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
    int x = dim.width / 2 - WIDTH / 2;
    int y = dim.height / 2 - HEIGHT / 2;
    this.setBounds(x, y, WIDTH, HEIGHT);
    this.setResizable(false);
    this.setForeground(new Color(186, 205, 234));
    sound = music;

		//
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		//
		ImageIcon img = new ImageIcon(Resources.getImage("pentaball.jpg"));
		this.setIconImage(img.getImage());
		this.setTitle("PENTABALL");
		this.setLayout(new FlowLayout());
		JPanel pentaball = new JPanel(null);
		pentaball.setPreferredSize(new Dimension(2000, 2000));
		pentaball.setBackground(new Color(186, 205, 234));
		//
		pentaball.add(getmenuPanelPanel());

		this.add(pentaball);
	}

	private JPanel getmenuPanelPanel() {
		String[] phrases = { "Two Player", "Instructions", "Best Scores", "Exit", "Put Your Marble", "Rotate!",
				"Congratulation", "Username1: ", "Username2: ", "Are you sure you want to exit",
				"Please enter different names!", "Enter your name", "Please fill in the both blanks correctly!",
				"Thank you for devoting your time!", " Score:", "PUT THE MARBLE!", "ROTATE ANY BOARD!",
				"WIN WITH 5 MARBLES!", "Do you want to play again?", "Choosing Language", "Name", "Single player"  };
		MenuPanel panel = new MenuPanel(phrases,sound);
		panel.setSize(new Dimension(1000, 1000));
		panel.setBounds(550, 0, 1100, 1080);
		return panel;
	}
}