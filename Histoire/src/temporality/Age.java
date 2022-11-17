package temporality;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.Iterator;

import data.Human;
import data.Information;
import data.Natural;
import data.Tribes;

public class Age {
	private Information information;
	private double year;
	private String path;
	public Age(String path) {
		this.path = path;
		information = new Information(path);
	}
	public void update() {
		suppDate();
		//on ajoute les informations de l'année
		information.initYear(year);
		
	}
	//ici on va supprimer les informations depassee
	private void suppDate() {

		ArrayList<Tribes> at =new ArrayList<Tribes>();
		ArrayList<Natural> an =new ArrayList<Natural>();
		ArrayList<Human> am =new ArrayList<Human>();
		Tribes supp;
		Natural natu;
		Human huma;
		try {
			
			Iterator<Tribes> tribe=information.getCulture().values().iterator();
			while(tribe.hasNext()) {
				supp =tribe.next();
				if (supp.getFin() <year) {
					at.add(supp);
				}
			}
			Iterator<Natural> nat=information.getNatural().values().iterator();
			while(nat.hasNext()) {
				natu =nat.next();
				if (natu.getFin() <year) {
					an.add(natu);
				}
			}
			Iterator<Human> hum=information.getHuman().values().iterator();
			while(hum.hasNext()) {
				huma=hum.next();
				if (huma.getFin() <year) {
					am.add(huma);
				}
			}
			for(int i =0;i<at.size();i++) {
				information.suppTribe(at.get(i).getName());
			}
			for(int i =0;i<am.size();i++) {
				information.suppHuman(am.get(i).getName());
			}
			for(int i =0;i<an.size();i++) {
				information.suppNatural(an.get(i).getName());
			}
		}catch(NullPointerException e) {
			
		}
	}

	public void setYear(double year) {
		this.year = year;
	}
	public String getPath() {
		return path;
	}
	
	public Information getInfo() {
		return information;
	}
	public double getYear() {
		return year;
	}
	public String toString() {
		return information.toString();
	}
}