package data;



public class Natural extends Events {
//la on va juste instancier la classe events parce que l'on ne rajoute pas d'informations a traiter	
	public Natural(String line){
		super(line);
	}

	@Override
	public String toString() {
		String a;
		a = "<tr><td>"+super.getDebut()+"</td><td>"+super.getFin()+"</td><td>"+super.getType()+"</td><td>"+super.getLieu()+"</td><td>"
				+super.getConsequence()+"</td></tr>";
		return a;
		
	}

}