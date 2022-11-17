package GUIpan;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.Iterator;

import javax.swing.JLabel;
import javax.swing.JPanel;

import data.Human;
import data.Natural;
import temporality.Age;
//---------------------------------------------------------------------------------------------


public class Panelevents extends JPanel{
	
	private static final long serialVersionUID = 1L;
    private JLabel texte=new JLabel("");
     
    public Panelevents(Age age){
        setBounds(500,500,500,500);
        this.setBackground(Color.WHITE);
        setTexte(age);
        this.add(texte,BorderLayout.EAST);
      }

    
    public void paint(Graphics g) {
        
    	super.paint(g);
    
		Image fond=null;
		Toolkit kit = Toolkit.getDefaultToolkit();
   		fond=kit.getImage(System.getProperty("user.dir")+"//src//image//pluie.gif");
   		g.drawImage(fond,10,30,100,100,this);
   		fond=kit.getImage(System.getProperty("user.dir")+"//src//image//militaire.gif");
   		g.drawImage(fond,10,200,100,100,this);
         
    }
    public void setTexte(Age age) {
    	String naturel ="";
    	String humain ="";
    	naturel="<h1>Evénements</h1><h2>Naturel</h2><table><tr><th>Début</th><th>Fin</th><th>Type</th><th>lieu</th><th>Conséquence</th></tr>";
    	humain="<h2>Humain</h2><table><tr><th>Début</th><th>Fin</th><th>Type</th><th>lieu</th><th>Conséquence</th><th>Auteurs</th></tr>";
    	for(Iterator<Natural> nat = age.getInfo().getNatural().values().iterator();nat.hasNext();) {
    		naturel += nat.next().toString();
    	}
    	for(Iterator<Human> hum = age.getInfo().getHuman().values().iterator();hum.hasNext();) {
    		
    		humain += hum.next().toString();
    		
    	}
    	naturel +="</table>";
    	humain +="</table>";
    	texte.setText("<html>"+naturel+humain+"</html>");
    }
	
	

}
