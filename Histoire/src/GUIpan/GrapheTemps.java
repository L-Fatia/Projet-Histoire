package GUIpan;

import java.awt.Color;import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import javax.swing.JPanel;

import data.Tribes;
import temporality.Age;

//Classe de la carte
public class GrapheTemps extends JPanel {
	
	private static final long serialVersionUID = 1L;
	//Quadrillage du repère
	private GridCase grid = new GridCase();
	//La pallette de couleur
	private SetCouleur coul = SetCouleur.getInstance();
	//Les tribus en activités
	private ConcurrentHashMap<String,Tribes> nameCount;
	
	public GrapheTemps() {
		nameCount=new ConcurrentHashMap<String,Tribes>();
		setPreferredSize(new Dimension(600,600));
		setBackground(Color.BLUE);
		
	}
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		drawGrille(g);
		drawZones(g);
	}
	//Affiche la grille (peut être enlevé si voulus)
	private void drawGrille(Graphics g) {
		int a =grid.getXmax();
	
		for(int x =0;x<a;x+=1) {
        	for(int y =0; y<a;y+=1) {
        		Case c = grid.getGrid()[x][y];
        		g.drawPolygon(c.getRectangle());
        	}
		}
	}
	//Peint les zones peuplés
	private void drawZones(Graphics g) {
		for(Iterator<Zones> it=grid.getZones().values().iterator();it.hasNext();) {
			it.next().drawZone(g);;
		}
	}
	//Va mettre à jour les couleurs
	public void up(Age age) {
		
		for(Iterator<Tribes> it =age.getInfo().getCulture().values().iterator();it.hasNext();) {
			Tribes a =it.next();
			if(nameCount.contains(a)) {
				
			}
			else {
				ArrayList<String> lieu = a.getLieu();
				coul.setColor();
				for(int i=0; i<lieu.size();i++) {
					if(grid.getZones().containsKey(lieu.get(i))) {
						grid.getZones().get(lieu.get(i)).setCouleur(coul.getCouleur());
					}
				}
				nameCount.put(a.getName(), a);
			}
		}
		reset(age);
	}
	//Va mettre à jour les informations et repeindre ne blanc les zones vides
	public void reset(Age age) {
		boolean oui = false;
		ArrayList<Tribes> stock = new ArrayList<Tribes>();
		if(nameCount.isEmpty()) {
			
		}
		else {
			//on parcours less informations que l'on a déja
			for(Iterator<Tribes> it =nameCount.values().iterator();it.hasNext();) {
				
					Tribes a = it.next();
					//on parcours les nouvelles informations
					for(Iterator<Tribes> it2 =age.getInfo().getCulture().values().iterator();it2.hasNext();) {
						//Si il y a déja l'information on ne fait rien
						if(a == it2.next()) {
							oui = true;
						}
					}
					//Si l'information n'est plus présente on repaint en blanc les zones
					if(oui == false) {
						if(!a.getLieu().isEmpty()) {
							ArrayList<String> lieu = new ArrayList<String>();
							lieu = a.getLieu();
							coul.setColor();
							for(Iterator<String>at=lieu.iterator();at.hasNext();) {
								String aa =at.next();
								if(grid.getZones().containsKey(aa)) {
									grid.getZones().get(aa).setCouleur(coul.white());
								}
							}
							stock.add(a);
						}
					}
				
				oui = false;
			}
			//On supprime les informations qui ont disparus
			for(Iterator<Tribes> bt =stock.iterator();bt.hasNext();) {
				Tribes bb = bt.next();
				nameCount.remove(bb.getName());
			}	
		}
		
	}
	public void reset() {
		grid = new GridCase();
	}
	public GridCase getGrid() {
		return grid;
	}
}

