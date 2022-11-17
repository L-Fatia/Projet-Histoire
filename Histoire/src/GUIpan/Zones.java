package GUIpan;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Iterator;

public class Zones {
	private String name;
	private Color couleur;
	private ArrayList<Case> cases;
	
	public Zones(String line, Case [][] grille) {
		couleur = Color.white;
		cases = new ArrayList<Case>();
		String[] split = line.split(";");
		name =split[0];

		for(int i=1;i<split.length;i++) {
			int[] xy= {Integer.parseInt(split[i].split(",")[0]),Integer.parseInt(split[i].split(",")[1])};
			cases.add(grille[xy[0]][xy[1]]);
		}
	}
	public void drawZone(Graphics g) {
		g.setColor(couleur);
		for(Iterator<Case> it=cases.iterator();it.hasNext();) {
			g.fillPolygon(it.next().getRectangle());
		}
	}
	public void setCouleur(Color c) {
		couleur = c;
	}
	public Color getColor() {
		return couleur;
	}
	public String getName() {
		return name;
	}
	public ArrayList<Case> getCases(){
		return cases;
	}
	
}
