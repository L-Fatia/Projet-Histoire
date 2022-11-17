package data;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;


public class Information {
	private ConcurrentHashMap<String,Tribes> culture;
	private HashMap<String,Natural> natural;
	private HashMap<String,Human> human;
	private String pathTribe;
	private String pathNatural;
	private String pathHuman;
	private static String originalPath =System.getProperty("user.dir")+"//src//data//donnee//";
	public Information(String path) {
		pathTribe = originalPath+"tribe" +path +".csv";
		pathNatural = originalPath+"natural" +path +".csv";
		pathHuman = originalPath+"human" +path +".csv";
		culture = new ConcurrentHashMap<String,Tribes>();
		natural = new HashMap<String,Natural>();
		human = new HashMap<String,Human>();
	}
	//une methode pour creer l'objet
	public void initYear(double year) {
		initCulture(year);
		initNatural(year);
		initHuman(year);
	}
	
	//ici on initialise l'hashmap des tribes
	private void initCulture(double year) {
		try {
			String line;
			//on va lire le csv et recuperer que les informations de l'annee et les ajouter
			BufferedReader infoTribes =new BufferedReader(new FileReader(pathTribe));
			
			while((line= infoTribes.readLine()) != null) {
				Tribes tribe =new Tribes(line);
				if(tribe.getdebut()== year) {
					if(!culture.containsKey(tribe.getName())) {
						//on les ajoute ici
						addTribe(tribe);
					}
				}
				if(tribe.getdebut()>year) {
					break;
				}
				line ="";
			}
			infoTribes.close();
		}catch(FileNotFoundException e) {
			
		}catch(IOException e) {
			
		}
	}
	//ici on initialise l'hashmap des evt naturels
	private void initNatural(double year) {
		
		try {
			String line;
			BufferedReader infoNatural =new BufferedReader(new FileReader(pathNatural));
			while((line =infoNatural.readLine()) != null) {
				Natural nature =new Natural(line);
				if(nature.getDebut()==year) {
					if(natural.containsKey(nature.getName())) {
					}
					else {
						addNatural(nature);
					}
				}
				
				
				
			}
			infoNatural.close();
		}catch(FileNotFoundException e) {
			
		}catch(IOException e) {
			
		}
	}
	//ici on initialise l'hashmap des evt humain
	private void initHuman(double year) {
		
		try {
			String line;
			BufferedReader infoNatural =new BufferedReader(new FileReader(pathHuman));
			while((line =infoNatural.readLine()) != null) {
				Human hum =new Human(line);
				if(hum.getDebut()==year) {
					if(human.containsKey(hum.getName())) {
					}
					else {
						addHuman(hum);
					}
				}
				
				
				
			}
			infoNatural.close();
		}catch(FileNotFoundException e) {
			
		}catch(IOException e) {
			
		}
	}
	
	//ici on va permettre l'acces aux ajouts a l'utilisateur pour permettre l'initialisation d'information de maniere manuelle
	public void addHuman(Human hum) {
		human.put(hum.getName(),hum);
	}
	public void addNatural(Natural nature) {
		natural.put(nature.getName(), nature);
	}
	public void addTribe(Tribes tribe) {
		culture.put(tribe.getName(), tribe);
	}
	
	//ici on fait des methodes pour supprimer un element de l'objet
	public void suppTribe(String key) {
		culture.remove(key);
	}
	public void suppNatural(String key) {
		
		natural.remove(key);
		
	}
	public void suppHuman(String key) {
		human.remove(key);
	}
	
	// ici on a les get pour les information de l'instance
	public ConcurrentHashMap<String,Tribes> getCulture() {
		return culture;
	}
	public HashMap<String,Natural> getNatural(){
		return natural;
	}
	public HashMap<String,Human> getHuman(){
		return human;
	}
	public String toString() {
		String a ="Les peuples :\n";
		Iterator<Tribes> tribe=culture.values().iterator();
		while(tribe.hasNext()) {
			a += tribe.next().toString()+ "\n";
		}
		a+="Les evenements naturels :\n";
		Iterator<Natural> nat=natural.values().iterator();
		while(nat.hasNext()) {
			a += nat.next().toString()+ "\n";
		}
		a+="Les evenements humains :\n";
		Iterator<Human> hum=human.values().iterator();
		while(hum.hasNext()) {
			a += hum.next().toString()+ "\n";
		}
		return a;
	}
}