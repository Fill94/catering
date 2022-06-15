package it.uniroma3.siw.catering.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
@Entity
public class Chef {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	@NotBlank
	private String nome;
	@NotBlank
	private String cognome;
	@NotBlank
	private String nazionalita;
	@OneToMany(mappedBy = "chef")
	private List<Buffet> elencoBuffet;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCognome() {
		return cognome;
	}
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}
	public String getNazionalita() {
		return nazionalita;
	}
	public void setNazionalita(String nazionalita) {
		this.nazionalita = nazionalita;
	}
	public List<Buffet> getElencoBuffet() {
		return elencoBuffet;
	}
	public void setElencoBuffet(List<Buffet> elencoBuffet) {
		this.elencoBuffet = elencoBuffet;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.getNome();
	}
	
	
	
	
	
}
