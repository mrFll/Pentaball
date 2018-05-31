package graphic;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import resources.Resources;

public class Main {
  public static void main(String[] args) {
    JButton music=playSound();
    FrameConstructor frame = new FrameConstructor(music);
    frame.setVisible(true);
    
  }
  public static JButton playSound() {
    JButton music=new JButton("");
    music.setIcon(new ImageIcon(Resources.getImage("volume.png")));
    try {
          AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("strings.wav").getAbsoluteFile());
          Clip clip = AudioSystem.getClip();
          clip.open(audioInputStream);
          clip.start();
          clip.loop(clip.LOOP_CONTINUOUSLY);
          music.addActionListener(new ActionListener() {
  
        @Override
        public void actionPerformed(ActionEvent arg0) {
          if(clip.isActive()==true){
            music.setIcon(new ImageIcon(Resources.getImage("mute.png")));
            clip.stop();
            
          }else{
            clip.start();
            music.setIcon(new ImageIcon(Resources.getImage("volume.png")));
            clip.loop(clip.LOOP_CONTINUOUSLY);
          }
        }
      });
          
      } catch(Exception ex) {
          System.out.println("Error with playing sound.");
          ex.printStackTrace();
      }
      return music;
  }
}