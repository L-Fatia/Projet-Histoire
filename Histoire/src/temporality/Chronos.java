package temporality;

public class Chronos {
	private long debut = -1598000;
	private long fin = 2019;
	private long annee;
	public Chronos(long debut) {
		annee = debut;
	}
	public void init() {
		annee = debut;
	}
	public void increment() {
		if(annee <= -758000) {
			annee += 10000;
		}
		else if(annee <=-348000) {
			annee += 1000;
		}
		else if(annee <= -158000) {
			annee+=1000;
		}
		else if(annee <= -43000) {
			annee += 500;
		}
		else if(annee<=-15000) {
			annee += 500;
		}
		else if(annee <= -6000) {
			annee += 100;
		}
		else if(annee >=fin) {
			
		}
		else {
			annee ++;
		}
	}
	public void SetChronos(long date) {
		annee = date;
	}
	public void setFin(long fin) {
		this.fin = fin;
	}
	public long getFin() {
		return fin;
	}
	public long getAnnee() {
		return annee;
	}
	public String toString() {
		return annee +"";
	}
}
