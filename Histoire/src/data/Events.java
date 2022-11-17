package data;


public abstract class Events {
	// ici on met dans des variables toutes les infos dont a besoin( pour l'ordre allez voir le fichier texte "explication.txt")
	private String type;
	private String nom;
	private long debut;
	private long fin;	
	private String lieu;
	private String consequence;
	private String cause;
// ici on les recupere dans l'ordre mis en place dans le csv
	// le String recuperer correspond a la ligne a etudier dans le csv. c'est pour eviter de faire transiter des fichiers
	public Events(String line) {
		debut = Long.decode(line.split(";")[0]);
		fin = Long.decode(line.split(";")[1]);
		type = line.split(";")[2];
		lieu = line.split(";")[3];
		nom = line.split(";")[4];
		cause = line.split(";")[5];
		consequence = line.split(";")[6];
	}
	public String getName() {
		return nom;
	}
	public String getLieu() {
		return lieu;
	}
	public double getDebut() {
		return debut;
	}
	public double getFin() {
		return fin;
	}
	public String getConsequence() {
		return consequence;
	}
	public String getCause() {
		return cause;
	}
	public String getType() {
		return type;
	}
	public abstract String toString();
}
