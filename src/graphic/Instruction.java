package graphic;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import resources.Resources;

public class Instruction extends JPanel {

	private static final long serialVersionUID = 3213248559712551985L;
	private JButton sound;

	public Instruction(String[] phrase, JButton sound1) {
		sound = sound1;
		//
		this.setBackground(new Color(182, 209, 252));
		this.setLayout(null);
		//
		Font font = new Font("Courier", Font.ITALIC, 40);
		if (phrase[15].equals("PUT THE MARBLE!")) {
			font = new Font("Courier", Font.ITALIC, 40);
		} else if (phrase[15].equals("مهره را بگذار!")) {
			font = new Font("Courier", Font.BOLD, 40);
		}
		//
		JLabel putMarble = new JLabel();
		putMarble.setIcon(new ImageIcon(Resources.getImage("putMarble.jpg")));
		putMarble.setBounds(380, 50, 145, 157);
		this.add(putMarble);
		//
		JLabel putTheMarble = new JLabel(phrase[15], JLabel.CENTER);
		putTheMarble.setFont(font);
		putTheMarble.setForeground(new Color(62, 87, 141));
		putTheMarble.setBounds(0, 2, 900, 50);
		this.add(putTheMarble);
		//
		JLabel rotate = new JLabel();
		rotate.setIcon(new ImageIcon(Resources.getImage("rotate.jpg")));
		rotate.setBounds(365, 255, 170, 157);
		this.add(rotate);
		//
		JLabel RotateAnyBoard = new JLabel(phrase[16], JLabel.CENTER);
		RotateAnyBoard.setFont(font);
		RotateAnyBoard.setForeground(new Color(62, 87, 141));
		RotateAnyBoard.setBounds(0, 205, 900, 50);
		this.add(RotateAnyBoard);
		//
		JLabel win = new JLabel();
		win.setIcon(new ImageIcon(Resources.getImage("win.jpg")));
		win.setBounds(380, 465, 145, 157);
		this.add(win);
		//
		JLabel winFive = new JLabel(phrase[17], JLabel.CENTER);
		winFive.setFont(font);
		winFive.setBounds(0, 420, 900, 50);
		winFive.setForeground(new Color(62, 87, 141));

		this.add(winFive);
		//
		JButton exit = new JButton("");
		exit.setBounds(90, 620, 30,30);
		exit.setBackground(new Color(182, 209, 252));
		exit.setBorderPainted(false);
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
				add(getPaneExit(phrase));
				revalidate();
				repaint();
			}
		});
		this.add(exit);

	}

	private JPanel getPaneExit(String[] phrase) {
		MenuPanel panel = new MenuPanel(phrase, sound);
		panel.setSize(new Dimension(1000, 1000));
		panel.setBounds(0, 0, 1100, 1080);
		return panel;
	}
}
