package GUIpan;

import java.awt.Color;
import java.awt.Polygon;

public class Case {
	
	private int []x = new int[4];
	private int []y = new int[4];
	private Polygon rec;
	private Color couleur;
	public Case(int x,int y,int echelle) {
		initXY(x,y, echelle);
		
		rec = new Polygon(this.x,this.y,4);
	}
	private void initXY(int x2, int y2, int echelle) {
		x[0] =x2;y[0]=y2;
		x[1]=x2+echelle;y[1]=y2;
		x[3]=x2;y[3]=y2-echelle;
		x[2]=x2+echelle;y[2]=y2-echelle;
		
	}
	public Polygon getRectangle() {
		return rec;
	}
	public Color getCouleur() {
		return couleur;
	}
	
}

