package data;

import java.util.ArrayList;
import java.util.Iterator;

public class Tribes {
	// ici on met dans des variables toutes les infos dont a besoin( pour l'ordre allez voir le fichier texte "explication.txt")

	private String nom;
	private long debut;
	private long fin;
	private ArrayList<String> lieu;
	private String regime;
	private String population;
	private String langue;

	// ici on les recupere dans l'ordre mis en place dans le csv
// le String recuperer correspond a la ligne a etudier dans le csv. c'est pour eviter de faire transiter des fichiers
	public Tribes(String line) {
		lieu = new ArrayList<String>();
		debut = Long.decode(line.split(";")[0]);
		fin = Long.decode(line.split(";")[1]);

		nom = line.split(";")[2];
		String[] places=line.split(";")[3].split(",");
		for(int i=0;i<places.length;i++) {
			lieu.add(places[i]);
		}
		regime = line.split(";")[4];
		population = line.split(";")[5];
		langue = line.split(";")[6];
	}
	
	public String getName() {
		return nom;
	}
	public ArrayList<String> getLieu() {
		return lieu;
	}
	public String getRegime() {
		return regime;
	}
	public String getPopulation() {
		return population;
	}
	public double getdebut() {
		return debut;
	}
	public double getFin() {
		return fin;
	}
	public String getLangue() {
		return langue;
	}
	public String toString() {
		String a ="<html><table><tr><td>nom</td><td>"+nom+"</td></tr>" +"<tr><td>Lieu</td><td><ul>";
		for(Iterator<String> it=lieu.iterator();it.hasNext();) {
			a+="<li>"+it.next()+"</li>";
		}
		a+="</ul></td></tr><tr><td>Régime</td><td>"+regime+"</td></tr><tr><td>Population</td><td>" +population+"</td></tr><tr><td>Apparition</td><td>" +debut+"</td></tr><tr><td>Disparition</td><td>" +fin+"</td></tr><tr><td>Langue</td><td>"+langue+"<html><table>";
		return a;
	}

}
