package graphic;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

public class CounterClockwiseRotate extends JButton {
	/**
	* 
	*/
	private static final long serialVersionUID = -8171480466875183154L;

	public CounterClockwiseRotate(JButton[] btnZone,JButton[][] boardbtns) {
		this.setContentAreaFilled(false);
	    this.setBorderPainted(false);
		this.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				for(int i=0;i<9;i++){
					if(i==4){
						continue;
					}
					btnZone[i].setEnabled(true);	
				}
				
				JButton a= new JButton();
				a.setBackground(btnZone[0].getBackground());
				btnZone[0].setBackground(btnZone[6].getBackground());
				if (btnZone[0].getBackground() == Color.WHITE) {
					btnZone[0].setEnabled(true);
				}else{
					btnZone[0].setEnabled(false);
				}
				btnZone[6].setBackground(btnZone[8].getBackground());
				if (btnZone[6].getBackground() == Color.WHITE) {
					btnZone[6].setEnabled(true);
				}else{
					btnZone[6].setEnabled(false);
				}
				btnZone[8].setBackground(btnZone[2].getBackground());
				if (btnZone[8].getBackground() == Color.WHITE) {
					btnZone[8].setEnabled(true);
				}else{
					btnZone[8].setEnabled(false);
				}
				btnZone[2].setBackground(a.getBackground());
				if (btnZone[2].getBackground() == Color.WHITE) {
					btnZone[2].setEnabled(true);
				}else{
					btnZone[2].setEnabled(false);
				}
				
				a.setBackground(btnZone[1].getBackground());
				btnZone[1].setBackground(btnZone[3].getBackground());
				if (btnZone[1].getBackground() == Color.WHITE) {
					btnZone[1].setEnabled(true);
				}else{
					btnZone[1].setEnabled(false);
				}
				btnZone[3].setBackground(btnZone[7].getBackground());
				if (btnZone[3].getBackground() == Color.WHITE) {
					btnZone[3].setEnabled(true);
				}else{
					btnZone[3].setEnabled(false);
				}
				btnZone[7].setBackground(btnZone[5].getBackground());
				if (btnZone[7].getBackground() == Color.WHITE) {
					btnZone[7].setEnabled(true);
				}else{
					btnZone[7].setEnabled(false);
				}
				btnZone[5].setBackground(a.getBackground());
				if (btnZone[5].getBackground() == Color.WHITE) {
					btnZone[5].setEnabled(true);
				}else{
					btnZone[5].setEnabled(false);
				}
				for (int i = 0; i < 6; i++) {
					for (int j = 0; j < 6; j++) {
						if(boardbtns[i][j].getBackground()==Color.WHITE){
							boardbtns[i][j].setEnabled(true);
						}
					}
				}
				
			}
		});
	}
}