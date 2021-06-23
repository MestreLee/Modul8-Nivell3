package com.video.application;

import java.util.List;

import com.video.domain.Usuari;
import com.video.persistence.UsuariRepository;

public class UsuariController {
	
	private UsuariRepository repository = new UsuariRepository();
	
	public UsuariController(){}
		
		public void createUsuari(String nom, String cognom, String password) throws Exception{		
			Usuari usuari = new Usuari(nom, cognom, password);
			repository.addUsuari(usuari);
		}
		
		public String getAllUsuaris() {
			
			String res         = "";
			List<Usuari> lista = repository.getAllUsuaris();
			
			for(Usuari e: lista) {
				res = res + e;
			}
			
			return res;
		}
		
		public String validaUsuari(String nom, String pass) {
			
			String missatge    = "";
			boolean trobat     = false;
			List<Usuari> lista = repository.getAllUsuaris();
			
			for(Usuari e: lista) {
				if (nom.toLowerCase().equals(e.getNom().toLowerCase())){
					if (pass.equals(e.getPassword())) {
						missatge = "ok"; 
					}
					else {
						missatge = "Contrassenya incorrecta!";
					}
					trobat = true;
				}
			}
			
			if (!trobat) missatge = "l'usuari no existeix";
			
			return missatge;
		}
		
		public boolean usuariDuplicat (String nom, String cognom) {
			
			boolean bool 	   = true;
			
			List<Usuari> lista = repository.getAllUsuaris();
			
			for(Usuari e: lista) {
				if (nom.toLowerCase().equals(e.getNom().toLowerCase()) && cognom.toLowerCase().equals(e.getCognom().toLowerCase())){
					bool = false;
				}
			}
			
			return bool;
		}

}
