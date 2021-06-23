package com.video.persistence;

import java.util.ArrayList;
import java.util.List;

import com.video.domain.Usuari;

public class UsuariRepository {
	
private static List<Usuari> usuaris=new ArrayList<>();
	
	public UsuariRepository(){} // constructor buit
	
	public List<Usuari> getAllUsuaris(){
		return new ArrayList<>(usuaris);
	}
	
	
	public void addUsuari(Usuari usuari) throws Exception{
		if(usuari==null) throw new Exception();
		usuaris.add(usuari);
	}

}
