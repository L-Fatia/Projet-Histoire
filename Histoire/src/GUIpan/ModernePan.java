package GUIpan;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;




public class ModernePan extends JPanel {
	private static final long serialVersionUID = 1L;
	
    public ModernePan() {
	    setLayout(null);
	    setSize(GParameters.FRAME_WIDTH,GParameters.FRAME_HEIGHT);
	    //setBackground(Color.DARK_GRAY);
 
    }
    
    public static void prehis() {
    	JFrame cont = new JFrame(); 
        //cont.setSize(800, 600); 
        cont.setTitle("prehistory"); 
        JPanel panneau = new IntroPan(); 
        panneau.setPreferredSize(new Dimension(800, 600));
        cont.add(panneau); 
        cont.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        cont.setVisible(true);
    	
    }
    
    
   
    
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Image img;
		try {
			
				img = ImageIO.read(new File(System.getProperty("user.dir")+"//src//image//moderne.jpg"));
				g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
				
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
}

