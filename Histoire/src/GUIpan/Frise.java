package GUIpan;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Frise extends JPanel {
	private static final long serialVersionUID = 1L;
	private String s="";
	private float chronometer =0;
	public Frise() {
		this.setBackground(Color.WHITE);
	}
	
	//public long avancement() {
	//	long a;
	//	a=chronometer.getAnnee();
	//	return a;
	//}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawString(s,1000,1000);
		g.setColor(Color.RED);
		g.drawImage(SimulationUtility.readImage(System.getProperty("user.dir")+"//src//image//homme.gif"), (int) chronometer, 30, 150, 150, null);
		
	}
	 
	public void setChronos() {
		chronometer +=0.15;
	}
	
	public static void fenetre() {
		JFrame cont = new JFrame(); 
        cont.setSize(2800, 300); 
        cont.setTitle("prehistory"); 
        JPanel panneau = new Frise(); 
        panneau.setPreferredSize(new Dimension(2800, 300));
        cont.add(panneau); 
        cont.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        cont.setVisible(true);
	}
	public static void main(String[] args) {
		fenetre();
	}
}
