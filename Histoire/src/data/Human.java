package data;

import java.util.ArrayList;
import java.util.Iterator;
//ici on va simplement ajouter en plus des informations de bases des evenements le ou les auteurs potentiels
public class Human extends Events {
	private ArrayList<String> auteurs;
	public Human(String line) {
		super(line);
		auteurs = new ArrayList<String>();
		String auteur = line.split(";")[7];
		for(int i=0;i<auteur.split(",").length;i++) {
			auteurs.add(auteur.split(",")[i]);
		}
	}
	@Override
	public String toString() {
		String a;
		a ="<tr><td>"+super.getDebut()+"</td><td>"+super.getFin()+"</td><td>"+super.getType()+"</td><td>"+super.getLieu()+"</td><td>"
				+super.getConsequence()+"</td><td><ul>";
		for(Iterator<String> it=auteurs.iterator();it.hasNext();) {
			a+="<li>"+it.next()+"</li>";
		}
		a+="</ul></td></tr>";
		return a;
	}
	

}
