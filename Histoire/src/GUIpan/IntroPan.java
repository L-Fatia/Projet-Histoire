package GUIpan;

import java.awt.Graphics;
import java.awt.Image;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;

import javax.swing.JPanel;



public class IntroPan extends JPanel {
	private static final long serialVersionUID = 1L;
	private JButton jbplay , jbhome ;
	
	
    
    public IntroPan() {
    	
	    setLayout(null);
	    setSize(GParameters.FRAME_WIDTH,GParameters.FRAME_HEIGHT);
	    
		jbplay = new JButton("Commencer la simulation",GParameters.ICON_BUTTON) ;
		jbhome= new JButton("Retour",GParameters.ICON_BUTTON);

	  	jbplay.setBounds(800,400,GParameters.BUTTON_WIDTH,GParameters.BUTTON_HEIGHT);
	  	jbhome.setBounds(400,400,GParameters.BUTTON_WIDTH,GParameters.BUTTON_HEIGHT);
    }
    

   
    
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Image img;
		try {
			
			img = ImageIO.read(new File(System.getProperty("user.dir")+"//src//image//play.jpg"));
			g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
 
    public JButton getJbPlay () {
	    return jbplay ;
    }
    
    public JButton getJbHOME () {
	    return jbhome ;
    }
}
