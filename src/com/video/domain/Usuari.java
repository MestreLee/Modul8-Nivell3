package com.video.domain;

import java.time.LocalDate;

public class Usuari {
	protected String nom;
	protected String cognom;
	protected String password;
	protected LocalDate dataRegistre;
	
	public Usuari() {} // Constructor buit, per si de cas
	
	public Usuari(String nom, String cognom, String password) {
		this.nom = nom;
		this.cognom = cognom;
		this.password = password;
		this.dataRegistre = LocalDate.now();
	}
	
	public String getNom() {
		return this.nom;
	}
	
	public String getCognom() {
		return this.cognom;
	}
	
	public String getPassword() {
		return this.password;
	}

	@Override
	public String toString() {
		return "Usuari [nom=" + nom + ", cognom=" + cognom + ", password=" + password + ", dataRegistre=" + dataRegistre
				+ "]";
	}
	
	
	
}
