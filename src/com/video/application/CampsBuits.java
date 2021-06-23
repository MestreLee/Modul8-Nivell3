package com.video.application;

public class CampsBuits {
	
	public CampsBuits() {}
	
	/*public boolean comprovarTitol(String nom) throws Exception{	
		boolean bool = false;
		
		if (comprovarStringsBuits(nom)) {
			if(comprovarLlargada(nom, 8)){
					bool = true;
			}
		}
		
		return bool;
	}
	
	public boolean comprovarUrl(String url) throws Exception{	
		boolean bool = false;
		
		if (comprovarStringsBuits(url)) {
			if(comprovarLlargada(url, 8)){
					bool = true;
			}
		}
		
		return bool;
	}*/
	
	public boolean comprovarNom(String nom) throws Exception{	
		boolean bool = false;
		
		if (comprovarCampBuit(nom)) {
			if(comprovarLlargada(nom, 3)){
				if(comprovarCaracters(nom)) 
					bool = true;
			}
		}
		
		return bool;
	}
	
	public boolean comprovarPassword(String pass) throws Exception{	
		boolean bool = false;
		
		if (comprovarCampBuit(pass)) {
			if(comprovarLlargada(pass, 6)){
					bool = true;
			}
		}
		
		return bool;
	}
	
	public boolean comprovarCampBuit(String string) throws Exception{
		
		boolean bool = false;
		
		if (string.equals("")) throw new Exception ("No pots deixar el camp buit!");
		else bool = true;
		
		return bool;
	}
	
	public boolean comprovarLlargada(String string, int llarg) throws Exception{
		
		boolean bool = false;
		
		if (string.length() < llarg) throw new Exception("El camp és massa curt!");
		else bool = true;
		return bool;
	}
	
	public boolean comprovarCaracters(String string) throws Exception{
		
		boolean bool = true;
		int i = 0;
		while (i < string.length() && bool == true) {
			Boolean flag = Character.isDigit(string.charAt(i));
	    	if(flag) {
	    			bool = false;
	    			throw new Exception("El nom no pot contenir caracters numèrics!!");	
	    	}
	    	i++;
		}
		
		return bool;
	}
	
}
