package GUIpan;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;


public class GridCase{

	
	private int xmax = 30;
	private Case [][] grille = new Case [xmax][xmax];
	private HashMap<String,Zones> zones;
	private int echelle = 20;
	private String pathZones =System.getProperty("user.dir")+"//src//data//donnee//Zones.txt";
		
	public GridCase() {
		zones = new HashMap<String,Zones>();
		initGrille();
		initZones();
	}
	private void initZones() {
		try {
			String line;
			//on va lire le csv et recuperer que les informations de l'annee et les ajouter
			BufferedReader zonesreader =new BufferedReader(new FileReader(pathZones));
			while((line= zonesreader.readLine()) != null) {
				Zones temp = new Zones(line, grille);
				zones.put(temp.getName(), temp);
				line ="";
			}
			zonesreader.close();
		}catch(FileNotFoundException e) {
			
		}catch(IOException e) {
			
		}
		
	}
	private void initGrille() {
        //x de l'hexa
        for(int x =0;x<xmax;x+=1) {
        	//y de l'hexa
        	for(int y =0; y<xmax;y+=1) {
        		
        		//creer le rectangle
        		Case cases = new Case(x*20,y*20,echelle);
        		grille[x][y] = cases;
        		
        	}
        }
	}

	public int getXmax() {
		return xmax;
	}
	public Case[][] getGrid(){
		return grille;
	}
	public HashMap<String,Zones> getZones(){
		return zones;
	}
}
