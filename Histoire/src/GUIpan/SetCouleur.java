package GUIpan;

import java.awt.Color;

public class SetCouleur {
	private Color[] couleur = {Color.cyan,Color.black,Color.GREEN,Color.gray,Color.magenta,Color.ORANGE,Color.red};
	private int palette;
	
	private static SetCouleur instance = new SetCouleur();
	
	private SetCouleur() {
		palette =0;
	}
	public static SetCouleur getInstance() {
		return instance;
	}
	public void setColor() {
		if(palette <5) {
			palette++;
		}
		else if(palette ==6) {
			palette =0;
		}
		else {
			palette=0;
		}
	}
	public Color getCouleur() {
		return couleur[palette];
	}
	public Color white() {
		return Color.white;
	}
}
